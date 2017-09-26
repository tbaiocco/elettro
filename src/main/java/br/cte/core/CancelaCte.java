/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core;

import br.cte.base.EmpresaDb;
import br.cte.base.WebServiceCteDb;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import br.cte.core.ClassesCte.v200.RetCancCTe;
import br.cte.core.webservices.service.CTeCancelamento.CteCancelamentoStub;
import br.cte.core.webservices.service.CTeCancelamento.CteCancelamentoStub.CteCabecMsg;
import br.cte.core.webservices.service.CTeCancelamento.CteCancelamentoStub.CteCabecMsgE;
import br.cte.core.webservices.service.CTeCancelamento.CteCancelamentoStub.CteDadosMsg;
import br.cte.model.Cte;
import br.cte.model.CteCancelamento;
import br.cte.model.Empresa;
import br.cte.model.WebServiceCte;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import br.utils.Arquivo;
import br.utils.Configuracoes;
import br.utils.Utils;

/**
 *
 * @author DerliRiffel
 */
public class CancelaCte {

    private Cte cte;
    private int tpAmbiente;
    private String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebServiceCte webservice = null;

    public CancelaCte(Cte cte) {
        this.cnpjEmissor = cte.getEmitente().getCNPJ();
        this.tpAmbiente = cte.getTpAmb();
        this.cte = cte;
    }

    public CteCancelamento executar() {
        EmpresaDb eDb = new EmpresaDb();
        empresa = eDb.getEmpresa(cnpjEmissor);

        WebServiceCteDb wDb = new WebServiceCteDb();
        webservice = wDb.getWebServer(empresa.getcUf(), tpAmbiente);

        if (empresa == null) {
            erros.put("empresa", "Não foi possível carregar dados da empresa");
        } else if (webservice == null) {
            erros.put("webservice", "Não foi possível carregar dados do webservice");
        } else if (!CertDig.getInstance().setProprierts(empresa)) {
            erros.put("certificado digital", "Não foi possível setar propriedades do certificado digital.");
        } else {
            return processaXml();
        }
        return null;
    }

    private CteCancelamento processaXml() {
        String nomeArquivoNota = Configuracoes.getInstance().getAppDir() + "cte"+System.getProperty("file.separator")+"cancel-" + cte.getNCT() + ".xml";
        String nomeArquivoNotaAss = Configuracoes.getInstance().getAppDir() + "cte"+System.getProperty("file.separator")+"cancel-" + cte.getNCT() + "-ass.xml";
        Arquivo a = new Arquivo(nomeArquivoNota);
        if (a.abrirEscrita()) {
            String xmlCanc = ""
                    + "<cancCTe  xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\"" + webservice.getVersaoPadrao() + "\">"
                    + "<infCanc Id=\"ID" + cte.getChaveAcesso() + "\">"
                    + "<tpAmb>" + cte.getTpAmb() + "</tpAmb>"
                    + "<xServ>CANCELAR</xServ>"
                    + "<chCTe>" + cte.getChaveAcesso() + "</chCTe>"
                    + "<nProt>" + cte.getProtocolo() + "</nProt>"
                    + "<xJust>" + Utils.getInstance().removeAcentuacao(cte.getJustificativaCancelamento()) + "</xJust>"
                    + "</infCanc>"
                    + "</cancCTe>";
            a.escreverLinha(xmlCanc);
            a.fecharArquivo();

            if (!CertDig.getInstance().Ass(nomeArquivoNota, nomeArquivoNotaAss, "2", empresa)) {
                erros.put("AssinarXmlCancelamento", CertDig.getInstance().getErro());
            } else {
                String nfeDadosMsg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
                Arquivo aAssinado = new Arquivo(nomeArquivoNotaAss);
                if (aAssinado.abrirLeitura()) {
                    nfeDadosMsg += aAssinado.ler();
                    aAssinado.fecharArquivo();
                    aAssinado.excluir();
                    return processa103(nfeDadosMsg);

                } else {
                    erros.put("LendoXmlAssinadoCancelamento", "Erro ao abrir arquivo para leitura, verifique se o arquivo existe e se o aplicativo tem permissão para leitura.");
                }
            }
            a.fecharArquivo();
            a.excluir();
        } else {
            erros.put("CriandoXmlCancelamento", "Erro ao abrir arquivo para escrita, verifique se o diretorio nfe existe e se o aplicativo tem permissão para escrita.");
        }
        return null;
    }

    private CteCancelamento processa103(String nfeDadosMsg) {
        CteCancelamento retorno = null;

        CteCancelamentoStub nfeUrl = null;
        try {
            String url = webservice.getUrlCancelamento();
            System.out.println("conectando em... " + url);
            nfeUrl = new CteCancelamentoStub(url);
        } catch (AxisFault ex) {
            ex.printStackTrace();
        }
        CteCabecMsgE nfeCabecMsg1 = new CteCabecMsgE();
        CteCabecMsg param = new CteCabecMsg();

        param.setCUF("" + this.empresa.getcUf());
        param.setVersaoDados(webservice.getVersaoPadrao());
        nfeCabecMsg1.setCteCabecMsg(param);
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(nfeDadosMsg);
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }

        CteDadosMsg nf2 = new CteDadosMsg();
        nf2.setExtraElement(ome);

        CteCancelamentoStub.CteCancelamentoCTResult resp = null;
        try {
            resp = nfeUrl.cteCancelamentoCT(nf2, nfeCabecMsg1);
        } catch (RemoteException ex) {
            erros.put("ErroConexaoCancelarCTe", "Erro ao conectar ao webserver do SEFAZ(cancelamentoNfe) Verifique a sua conexão com a internet." + ex.getMessage());
            //ex.printStackTrace();
        }
        if (resp != null) {
            retorno = new CteCancelamento();
            
            String result = resp.getExtraElement().toString();
            System.out.println(result);
            
            XStream x = new XStream(new DomDriver());
            x.processAnnotations(RetCancCTe.class);
            RetCancCTe ret = (RetCancCTe) x.fromXML(result);
            System.out.println(ret + " - "+ret.getInfCanc()+" - "+ret.getInfCanc().getNProt());

            retorno.setNProt(ret.getInfCanc().getNProt());
            retorno.setCStat(ret.getInfCanc().getCStat());
            retorno.setXMotivo(ret.getInfCanc().getXMotivo());
            retorno.setDhRecbto(ret.getInfCanc().getDhRecbto());
            retorno.setCUf(ret.getInfCanc().getCUF());
            retorno.setVerAplic(ret.getInfCanc().getVerAplic());
        }
        return retorno;
    }

    public HashMap getErros() {
        return erros;
    }
}
