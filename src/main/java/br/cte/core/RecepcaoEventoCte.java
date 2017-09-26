/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core;

import br.cte.base.EmpresaDb;
import br.cte.base.WebServiceCteDb;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import br.cte.core.ClassesCte.v200.InfEvento;
import br.cte.core.ClassesCte.v200.RetEventoCTe;
import br.cte.core.validadores.ValidadorXmlEventoCte;
import br.cte.core.webservices.service.CTeEventos.CteRecepcaoEventoStub;
import br.cte.core.xml.XmlEventoCte;
import br.cte.model.CteEvento;
import br.cte.model.Empresa;
import br.cte.model.WebServiceCte;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import br.utils.Arquivo;

/**
 *
 * @author DerliRiffel
 */
public class RecepcaoEventoCte {

    private final int tpAmbiente;
    private final String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebServiceCte webservice = null;
    //
    private final CteEvento evento;

    public RecepcaoEventoCte(int tpAmbiente, String cnpjEmissor, CteEvento evento) {
        this.cnpjEmissor = cnpjEmissor;
        this.tpAmbiente = tpAmbiente;
        this.evento = evento;
    }

    public CteEvento executar() {
        CteEvento retorno = null;
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

            retorno = this.processaRetorno();

        }
        return retorno;
    }

    private CteEvento processaRetorno() {
        CteEvento retorno = new CteEvento();

        String nomeArquivoNota = br.utils.Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + "evt-" + evento.getChCTe() + ".xml";
        String nomeArquivoNotaAss = br.utils.Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + "evt-" + evento.getChCTe() + "-ass.xml";

        br.utils.Arquivo a = new br.utils.Arquivo(nomeArquivoNota);
        if (a.abrirEscrita()) {

            String xml = XmlEventoCte.getInstance().getXml(evento, empresa);
            System.out.println(xml);
            a.escreverLinha(xml);
            a.fecharArquivo();

            ValidadorXmlEventoCte validador = new ValidadorXmlEventoCte();
            if (!CertDig.getInstance().Ass(nomeArquivoNota, nomeArquivoNotaAss, "4", empresa)) {
                erros.put("AssinarXmlEvento", CertDig.getInstance().getErro());
            } else if (!validador.valida(nomeArquivoNotaAss, 1, webservice.getVersaoPadrao())) {//valida cte-lote
                System.out.println("retorno de erro na validacao do xml eventos");
                erros = validador.getErros();
                retorno = null;
            } else {

                String cteDadosMsg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//                cteDadosMsg += xml1;
                Arquivo aAssinado = new Arquivo(nomeArquivoNotaAss);
                if (aAssinado.abrirLeitura()) {
                    cteDadosMsg += aAssinado.ler();

//                    cteDadosMsg += xml2;                                        
                    CteRecepcaoEventoStub cteUrl = null;
                    try {
                        String url = webservice.getUrlEvento();
                        System.out.println("=> " + url);
                        cteUrl = new CteRecepcaoEventoStub(url);
                    } catch (AxisFault ex) {
                        ex.printStackTrace();
                    }

                    CteRecepcaoEventoStub.CteCabecMsgE cteCabecMsg1 = new CteRecepcaoEventoStub.CteCabecMsgE();
                    CteRecepcaoEventoStub.CteCabecMsg param = new CteRecepcaoEventoStub.CteCabecMsg();

                    param.setCUF("" + empresa.getcUf());
                    param.setVersaoDados(webservice.getVersaoPadrao());
                    cteCabecMsg1.setCteCabecMsg(param);

                    OMElement ome = null;
                    try {
                        ome = AXIOMUtil.stringToOM(cteDadosMsg);
                    } catch (XMLStreamException ex) {
                        ex.printStackTrace();
                    }

                    String result;
                    CteRecepcaoEventoStub.CteDadosMsg ct2 = new CteRecepcaoEventoStub.CteDadosMsg();
                    ct2.setExtraElement(ome);
                    CteRecepcaoEventoStub.CteRecepcaoEventoResult resp = null;
                    try {
                        resp = cteUrl.cteRecepcaoEvento(ct2, cteCabecMsg1);
                    } catch (RemoteException ex) {
                        erros.put("ErroConexaoRecepcaoEventoNfe", "" + ex.getMessage());
                    }

                    if (resp != null) {

                        result = resp.getExtraElement().toString();
                        System.out.println(result);

                        XStream x = new XStream(new DomDriver());
                        x.processAnnotations(RetEventoCTe.class);
                        x.processAnnotations(InfEvento.class);
                        RetEventoCTe ret = (RetEventoCTe) x.fromXML(result);
                        if (ret != null || ret.getInfEvento().getCStat().equals("128")) {

                            retorno.setDhRegEvento(ret.getInfEvento().getDhRegEvento());
                            retorno.setVerAplic(ret.getInfEvento().getVerAplic());
                            retorno.setCStat(ret.getInfEvento().getCStat() + "");
                            retorno.setNProtRegEvento(ret.getInfEvento().getNProt());
                            retorno.setXMotivo("cStat = " + ret.getInfEvento().getCStat() + " " + ret.getInfEvento().getXMotivo());

                            if (ret.getInfEvento().getCStat().equals("135")) {
                                evento.setDhRegEvento(ret.getInfEvento().getDhRegEvento());
                                evento.setVerAplic(ret.getInfEvento().getVerAplic());
                                evento.setCStat(ret.getInfEvento().getCStat() + "");
                                evento.setNProtRegEvento(ret.getInfEvento().getNProt());
                                evento.setXEvento(ret.getInfEvento().getXEvento());

                                XmlEventoCte.getInstance().geraXmlDistEvento(evento, empresa);
                                retorno.setCStat(ret.getInfEvento().getCStat());
                                retorno.setNProtRegEvento(ret.getInfEvento().getNProt());
                            }
                        } else {
                            retorno.setCStat(ret.getInfEvento().getCStat() + "");
                            retorno.setXMotivo(ret.getInfEvento().getXMotivo());
                        }
                    }
                    aAssinado.fecharArquivo();
                    //if (evento.getTpAmb() == 1) {
                        aAssinado.excluir();
                    //}
                } else {
                    erros.put("LendoXmlAssinadoEvento", "Erro ao abrir arquivo para leitura, verifique se o arquivo existe e se o aplicativo tem permissão para leitura.");
                }
                a.fecharArquivo();
               // if (evento.getTpAmb() == 1) {
                    a.excluir();
               /// }
            }
        } else {
            erros.put("CriandoXmlAssinadoEvento", "Erro ao abrir arquivo para escrita, verifique se o diretorio nfe existe e se o aplicativo tem permissão para escrita.");
        }
        return retorno;
    }

    public HashMap getErros() {
        return erros;
    }
}
