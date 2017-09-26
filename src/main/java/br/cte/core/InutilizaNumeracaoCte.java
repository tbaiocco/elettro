/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core;

import br.cte.base.EmpresaDb;
import br.cte.base.WebServiceCteDb;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import br.cte.core.ClassesCte.v200.RetInutCte;
import br.cte.core.webservices.service.CTeInutilizacao.CteInutilizacaoStub;
import br.cte.core.webservices.service.CTeInutilizacao.CteInutilizacaoStub.CteCabecMsg;
import br.cte.core.webservices.service.CTeInutilizacao.CteInutilizacaoStub.CteCabecMsgE;
import br.cte.core.webservices.service.CTeInutilizacao.CteInutilizacaoStub.CteDadosMsg;
import br.cte.core.webservices.service.CTeInutilizacao.CteInutilizacaoStub.CteInutilizacaoCTResult;
import br.cte.model.CteInutilizacao;
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
public class InutilizaNumeracaoCte {

    private int tpAmbiente;
    private String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebServiceCte webservice = null;
    private String ano;
    private String mod;
    private String serie;
    private String nCTIni;
    private String nCTFin;
    private String xJust;
    private String id;

    public InutilizaNumeracaoCte(int tpAmbiente, String cnpjEmissor, String ano, String mod, String serie, String nCTIni, String nCTFin, String xJust) {
        this.cnpjEmissor = cnpjEmissor;
        this.tpAmbiente = tpAmbiente;
        if (ano.length() == 4) {
            this.ano = ano.substring(3, 4);
        } else {
            this.ano = ano;
        }
        this.mod = mod;
        this.serie = serie;
        this.nCTIni = nCTIni;
        this.nCTFin = nCTFin;
        this.xJust = xJust;
    }

    public CteInutilizacao executar() {
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
            return processa103();
        }
        return null;
    }

    private CteInutilizacao processa103() {
        CteInutilizacao retorno = new CteInutilizacao();
        String nomeArquivo = Configuracoes.getInstance().getAppDir() + "cte"+System.getProperty("file.separator")+"inut-" + nCTIni + "a" + nCTFin + ".xml";
        String nomeArquivoAss = Configuracoes.getInstance().getAppDir() + "cte"+System.getProperty("file.separator")+"inut-" + nCTIni + "a" + nCTFin + "-ass.xml";
        Arquivo a = new Arquivo(nomeArquivo);
        if (a.abrirEscrita()) {

            //gera id
            this.criaId();

            String xml = "<inutCTe xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\""+webservice.getVersaoPadrao()+"\">"
                    + "<infInut Id=\"ID" + this.id + "\">"
                    + "<tpAmb>" + this.tpAmbiente + "</tpAmb>"
                    + "<xServ>INUTILIZAR</xServ>"
                    + "<cUF>" + this.empresa.getcUf() + "</cUF>"
                    + "<ano>" + this.ano + "</ano>"
                    + "<CNPJ>" + Utils.getInstance().getDigitos(this.cnpjEmissor) + "</CNPJ>"
                    + "<mod>" + this.mod + "</mod>"
                    + "<serie>" + this.serie + "</serie>"
                    + "<nCTIni>" + this.nCTIni + "</nCTIni>"
                    + "<nCTFin>" + this.nCTFin + "</nCTFin>"
                    + "<xJust>" + this.xJust + "</xJust>"
                    + "</infInut>"
                    + "</inutCTe>";
            System.out.println(xml);
            a.escreverLinha(xml);
            a.fecharArquivo();

            if (!CertDig.getInstance().Ass(nomeArquivo, nomeArquivoAss, "3", empresa)) {
                erros.put("AssinarXmlInutilizacao", CertDig.getInstance().getErro());
            } else {

                String dadosMsg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
                Arquivo aAssinado = new Arquivo(nomeArquivoAss);
                if (aAssinado.abrirLeitura()) {
                    dadosMsg += aAssinado.ler();

                    CteInutilizacaoStub cteUrl = null;
                    try {
                        String url = webservice.getUrlInutilizacao();
                        System.out.println("conectando em... " + url);
                        cteUrl = new CteInutilizacaoStub(url);
                    } catch (AxisFault ex) {
                        ex.printStackTrace();
                    }

                    OMElement ome = null;
                    try {
                        ome = AXIOMUtil.stringToOM(dadosMsg);
                    } catch (XMLStreamException ex) {
                        ex.printStackTrace();
                    }

                    CteCabecMsgE cteCabecMsg1 = new CteCabecMsgE();
                    CteCabecMsg param = new CteCabecMsg();
                    param.setCUF("" + empresa.getcUf());
                    param.setVersaoDados(webservice.getVersaoPadrao());
                    cteCabecMsg1.setCteCabecMsg(param);

                    CteDadosMsg dadosCte = new CteDadosMsg();
                    dadosCte.setExtraElement(ome);

                    String result = null;
                    try {
                        CteInutilizacaoCTResult resultInut = cteUrl.cteInutilizacaoCT(dadosCte, cteCabecMsg1);
                        result = resultInut.getExtraElement().toString();
                    } catch (RemoteException ex) {
                        erros.put("ErroConexaoInutilizacaoCte", "Erro ao conectar ao webserver do SEFAZ(InutilizacaoCte) Verifique a sua conexão com a internet." + ex.getMessage());
                        ex.printStackTrace();
                    }

                    if (result != null) {
                        System.out.println(result);

                        XStream x = new XStream(new DomDriver());
                        x.processAnnotations(RetInutCte.class);
                        RetInutCte ret = (RetInutCte) x.fromXML(result);

                        retorno.setAno(ret.getInfInut().getAno());
                        retorno.setDhRecbto(ret.getInfInut().getDhRecbto());
                        retorno.setDigVal(ret.getInfInut().getDigVal());
                        retorno.setMod(ret.getInfInut().getMod());
                        retorno.setSerie(ret.getInfInut().getSerie());
                        retorno.setTpAmb(ret.getInfInut().getTpAmb());
                        retorno.setVerAplic(ret.getInfInut().getVerAplic());
                        retorno.setCStat(ret.getInfInut().getCStat());
                        retorno.setCUF(ret.getInfInut().getCUF());
                        retorno.setnCTIni(ret.getInfInut().getNCTIni());
                        retorno.setNCTFin(ret.getInfInut().getNCTFin());
                        retorno.setNProt(ret.getInfInut().getNProt());
                        retorno.setXMotivo(ret.getInfInut().getXMotivo());
                    }
                    aAssinado.fecharArquivo();
                    aAssinado.excluir();
                } else {
                    erros.put("LendoXmlAssinadoInutilizacao", "Erro ao abrir arquivo para leitura, verifique se o arquivo existe e se o aplicativo tem permissão para leitura.");
                }
                a.fecharArquivo();
                a.excluir();
            }
        } else {
            erros.put("CriandoXmlInutilizacao", "Erro ao abrir arquivo para escrita, verifique se o diretorio nfe existe e se o aplicativo tem permissão para escrita.");
        }
        return retorno;
    }

    private void criaId() {
        String cUF = Utils.getInstance().zeroFill("" + empresa.getcUf(), 2);
        String s = Utils.getInstance().zeroFill("" + serie, 3);
        String nCTi = Utils.getInstance().zeroFill("" + nCTIni, 9);
        String nCTf = Utils.getInstance().zeroFill("" + nCTFin, 9);
        id = cUF + "" + Utils.getInstance().getDigitos(this.cnpjEmissor) + "" + mod + "" + s + "" + nCTi + "" + nCTf;
    }

    public HashMap getErros() {
        return erros;
    }
}
