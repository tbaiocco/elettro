
package br.mdfe.core;

import br.mdfe.core.ClassesMdfe.InfEvento;
import br.mdfe.core.ClassesMdfe.RetEventoMDFe;
import br.mdfe.core.base.EmpresaDb;
import br.mdfe.core.base.WebServiceDb;
import br.mdfe.core.validador.ValidadorXmlEventoMdfe;
import br.mdfe.core.webservices.service.MDFeRecepcaoEvento.MDFeRecepcaoEventoStub;
import br.mdfe.core.xml.XmlEventoMdfe;
import br.mdfe.model.Empresa;
import br.mdfe.model.MdfeEvento;
import br.mdfe.model.WebService;
import br.utils.Arquivo;
import br.utils.Utils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axis2.AxisFault;

/**
 *
 * @author DerliRiffel
 */
public class RecepcaoEventoMdfe {

    private final int tpAmbiente;
    private final String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebService webservice = null;
    
    private final String versao = "3.00";
    //
    private final MdfeEvento evento;

    public RecepcaoEventoMdfe(int tpAmbiente, String cnpjEmissor, MdfeEvento evento) {
        this.cnpjEmissor = cnpjEmissor;
        this.tpAmbiente = tpAmbiente;
        this.evento = evento;
    }

    public MdfeEvento executar() {
        MdfeEvento retorno = null;
        EmpresaDb eDb = new EmpresaDb();
        empresa = eDb.getEmpresa(cnpjEmissor);

        WebServiceDb wDb = new WebServiceDb();
        webservice = wDb.getWebServer(empresa.getcUf(), tpAmbiente);

        if (empresa == null) {
            erros.put("empresa", "Não foi possível carregar dados da empresa");
        } else if (webservice == null) {
            erros.put("webservice", "Não foi possível carregar dados do webservice");
        } else if (!CertDig.getInstance().setProprerties(empresa)) {
            erros.put("certificado digital", "Não foi possível setar propriedades do certificado digital.");
        } else {

            retorno = this.processaRetorno();

        }
        return retorno;
    }

    private MdfeEvento processaRetorno() {
        MdfeEvento retorno = new MdfeEvento();

        String nomeArquivoNota = br.utils.Configuracoes.getInstance().getTmpDir()+ "evt-" + evento.getChMDFe() + ".xml";
        String nomeArquivoNotaAss = br.utils.Configuracoes.getInstance().getTmpDir() +"evt-" + evento.getChMDFe() + "-ass.xml";

        br.utils.Arquivo a = new br.utils.Arquivo(nomeArquivoNota);
        if (a.abrirEscrita()) {

            String xml = XmlEventoMdfe.getInstance().getXml(evento, empresa, versao);
//            System.out.println(xml);
            a.escreverLinha(xml);
            a.fecharArquivo();

            ValidadorXmlEventoMdfe validador = new ValidadorXmlEventoMdfe();
            if (!CertDig.getInstance().Ass(nomeArquivoNota, nomeArquivoNotaAss, "4", empresa)) {
                erros.put("AssinarXmlEvento", CertDig.getInstance().getErro());
            } else if (!validador.valida(nomeArquivoNotaAss, 1, versao)) {
                System.out.println("retorno de erro na validacao do xml eventos");
                erros = validador.getErros();
                retorno = null;
            } else {

                String cteDadosMsg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//                cteDadosMsg += xml1;
                Arquivo aAssinado = new Arquivo(nomeArquivoNotaAss);
                if (aAssinado.abrirLeitura()) {
                    cteDadosMsg += aAssinado.ler();
                    System.out.println(cteDadosMsg);
//                    cteDadosMsg += xml2;                                        
                    MDFeRecepcaoEventoStub murl = null;
                    try {
                        String url = webservice.getUrlRecepcaoEvento();
                        System.out.println("=> " + url);
                        murl = new MDFeRecepcaoEventoStub(url);
                    } catch (AxisFault ex) {
                        ex.printStackTrace();
                    }

                    MDFeRecepcaoEventoStub.MdfeCabecMsgE cabecMsg1 = new MDFeRecepcaoEventoStub.MdfeCabecMsgE();
                    MDFeRecepcaoEventoStub.MdfeCabecMsg param = new MDFeRecepcaoEventoStub.MdfeCabecMsg();

                    param.setCUF("" + empresa.getcUf());
//                    param.setVersaoDados("1.00");
                    param.setVersaoDados(versao);
                    cabecMsg1.setMdfeCabecMsg(param);

                    OMElement ome = null;
                    try {
                        ome = AXIOMUtil.stringToOM(cteDadosMsg);
                    } catch (XMLStreamException ex) {
                        ex.printStackTrace();
                    }

                    String result;
                    MDFeRecepcaoEventoStub.MdfeDadosMsg ct2 = new MDFeRecepcaoEventoStub.MdfeDadosMsg();
                    ct2.setExtraElement(ome);
                    MDFeRecepcaoEventoStub.MdfeRecepcaoEventoResult resp = null;
                    try {
                        resp = murl.mdfeRecepcaoEvento(ct2, cabecMsg1);
                    } catch (RemoteException ex) {
                        erros.put("ErroConexaoRecepcaoEventoNfe", "" + ex.getMessage());
                    }

                    if (resp != null) {

                        result = resp.getExtraElement().toString();
                        System.out.println(result);

                        XStream x = new XStream(new DomDriver());
                        x.processAnnotations(RetEventoMDFe.class);
                        x.processAnnotations(InfEvento.class);
                        RetEventoMDFe ret = (RetEventoMDFe) x.fromXML(result);
                        if (ret != null || ret.getInfEvento().getCStat().equals("128")) {

                            retorno.setDhRegEvento(ret.getInfEvento().getDhRegEvento());
                            retorno.setVerAplic(ret.getInfEvento().getVerAplic());
                            retorno.setCStat(ret.getInfEvento().getCStat() + "");
                            retorno.setNProtRegEvento(ret.getInfEvento().getNProt());
                            retorno.setXMotivo("cStat = " + ret.getInfEvento().getCStat() + " " + ret.getInfEvento().getXMotivo());

                            if (ret.getInfEvento().getCStat().equals("135")) {
                                evento.setDhRegEvento(ret.getInfEvento().getDhRegEvento());
                                evento.setXMotivo(ret.getInfEvento().getXMotivo());
                                evento.setVerAplic(ret.getInfEvento().getVerAplic());
                                evento.setCStat(ret.getInfEvento().getCStat() + "");
                                evento.setNProtRegEvento(ret.getInfEvento().getNProt());
                                evento.setXEvento(Utils.getInstance().removeAcentuacao(ret.getInfEvento().getXEvento()));

                                XmlEventoMdfe.getInstance().geraXmlDistEvento(evento, empresa);
                                retorno.setCStat(ret.getInfEvento().getCStat());
                                retorno.setNProtRegEvento(ret.getInfEvento().getNProt());
                            }
                        } else {
                            retorno.setCStat(ret.getInfEvento().getCStat() + "");
                            retorno.setXMotivo(ret.getInfEvento().getXMotivo());
                        }
                    }
                    aAssinado.fecharArquivo();
                    if (evento.getTpAmb() == 1) {
                        aAssinado.excluir();
                    }
                } else {
                    erros.put("LendoXmlAssinadoEvento", "Erro ao abrir arquivo para leitura, verifique se o arquivo existe e se o aplicativo tem permissão para leitura.");
                }
                a.fecharArquivo();
                if (evento.getTpAmb() == 1) {
                    a.excluir();
                }
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
