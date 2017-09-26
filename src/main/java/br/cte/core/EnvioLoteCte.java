/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core;

import br.cte.base.EmpresaDb;
import br.cte.base.LoteCteDb;
import br.cte.base.WebServiceCteDb;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import br.cte.core.ClassesCte.v200.RetEnviCTe;
import br.cte.core.validadores.XmlEnvioCte;
import br.cte.core.webservices.service.CTeRecepcao.CteRecepcaoStub;
import br.cte.core.webservices.service.CTeRecepcao.CteRecepcaoStub.CteCabecMsg;
import br.cte.core.webservices.service.CTeRecepcao.CteRecepcaoStub.CteCabecMsgE;
import br.cte.core.webservices.service.CTeRecepcao.CteRecepcaoStub.CteDadosMsg;
import br.cte.core.webservices.service.CTeRecepcao.CteRecepcaoStub.CteRecepcaoLoteResult;
import br.cte.core.xml.XmlEmissaoCte;
import br.cte.model.Cte;
import br.cte.model.CteLote;
import br.cte.model.Empresa;
import br.cte.model.WebServiceCte;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import br.utils.Arquivo;
import br.utils.Configuracoes;
import br.utils.Utils;
import java.util.Iterator;

/**
 *
 * @author DerliRiffel
 */
public class EnvioLoteCte {

    private final Cte cte;
    private final int tpAmbiente;
    private final String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebServiceCte webservice = null;

    public EnvioLoteCte(Cte cte) {
        this.cnpjEmissor = cte.getEmitente().getCNPJ();
        this.tpAmbiente = cte.getTpAmb();
        this.cte = cte;
    }

    public CteLote executar() {
        CteLote retorno = null;
        EmpresaDb eDb = new EmpresaDb();

        empresa = eDb.getEmpresa(cnpjEmissor);
        if (empresa == null) {
            erros.put("empresa", "Não foi possível carregar dados da empresa");
        } else {
            WebServiceCteDb wDb = new WebServiceCteDb();
            webservice = wDb.getWebServer(empresa.getcUf(), tpAmbiente);

            String testePR = "";

            if (webservice == null) {
                erros.put("webservice", "Não foi possível carregar dados do webservice");
            } else if (!CertDig.getInstance().setProprierts(empresa)) {
                erros.put("certificado digital", "Não foi possível setar propriedades do certificado digital.");
            } else {
                //String diretorio = Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + Utils.getInstance().getDigitos(cte.getEmitente().getCNPJ()) + System.getProperty("file.separator");

                retorno = new CteLote();
                retorno.setData(new Date());
                //salva a data do envio do lote no xml
                LoteCteDb ldb = new LoteCteDb();
                ldb.salvaLote(retorno);
                //busca nro sequencial do lote salvo
                int codigoLote = ldb.getCodigoLote();
                //gera chave de acesso caso nao esteja preenchida no objeto
                //System.out.println("ch cte:"+cte.getChaveAcesso());
                if (cte.getChaveAcesso() == null) {
                    criaChaveAcesso();
                }
                if (cte.getCDV() == null) {
                    String ch = cte.getChaveAcesso();
                    cte.setCDV(Integer.parseInt(ch.substring(ch.length() - 1)));
                }
                cte.setEmpresa(empresa);

                String dadosMsgXml;
                if (cte.getCUF() == 41) {
                    dadosMsgXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                            + "<enviCTe xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\"" + webservice.getVersaoPadrao() + "\">"
//                            + "<enviCTe versao=\"" + webservice.getVersaoPadrao() + "\">"
                            + "<idLote>" + retorno.getCodigoLote() + "</idLote>";
                } else {
                    dadosMsgXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                            + "<enviCTe xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\"" + webservice.getVersaoPadrao() + "\">"
                            + "<idLote>" + retorno.getCodigoLote() + "</idLote>";
                }                
                String xmlNota = XmlEmissaoCte.getInstance().getXml(cte, webservice.getVersaoPadrao());

                String nomeArquivo = Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + Utils.getInstance().getDigitos(cte.getEmitente().getCNPJ()) + System.getProperty("file.separator") + "cte-" + cte.getNCT() + ".xml";
                String nomeArquivoAss = Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + Utils.getInstance().getDigitos(cte.getEmitente().getCNPJ()) + System.getProperty("file.separator") + "cte-" + cte.getNCT() + ".xml";
                Arquivo a = new Arquivo(nomeArquivo);

                //boolean resDir = a.validateDir(diretorio);
                //System.out.println(resDir+"|diretorio:"+diretorio);
                a.abrirEscrita();
                a.escreverLinha(xmlNota);
                a.fecharArquivo();

                CertDig.getInstance().Ass(nomeArquivo, nomeArquivoAss, "1", empresa);

                Arquivo aAssinado = new Arquivo(nomeArquivoAss);
                aAssinado.abrirLeitura();
                testePR = aAssinado.ler();
                dadosMsgXml += testePR;
                //dadosMsgXml += aAssinado.ler();
                aAssinado.fecharArquivo();

                if (cte.getCUF() == 41) {
                    dadosMsgXml = dadosMsgXml.trim() + "</enviCTe>";
                } else {
                    dadosMsgXml = dadosMsgXml.trim() + "</enviCTe>";
                }
                String nomeArquivoLote = Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + Utils.getInstance().getDigitos(cte.getEmitente().getCNPJ()) + System.getProperty("file.separator") + "lote-" + ldb.getCodigoLote() + ".xml";
                Arquivo aLote = new Arquivo(nomeArquivoLote);
                aLote.abrirEscrita();
                aLote.escreverLinha(dadosMsgXml.trim());
                aLote.fecharArquivo();

                /**
                 * VALIDA ESTRUTURA DO XML CTE-LOTE CRIANDO NOVO ARQUIVO PARA
                 * TESTE MG (deve-se evitar abrir/alterar o arquivo antes de
                 * enviar)
                 */
                String nomeArquivoLoteTeste = Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + Utils.getInstance().getDigitos(cte.getEmitente().getCNPJ()) + System.getProperty("file.separator") + "lote-" + ldb.getCodigoLote() + "tst.xml";
                String dadosMsgXmlTeste = dadosMsgXml;
                System.out.println(dadosMsgXmlTeste.trim());
                Arquivo aTeste = new Arquivo(nomeArquivoLoteTeste);
                aTeste.abrirEscrita();
                aTeste.escreverLinha(dadosMsgXmlTeste.trim());
                aTeste.fecharArquivo();

                XmlEnvioCte validador = new XmlEnvioCte();
                //System.out.println("validando xml");
                if (!validador.valida(nomeArquivoLoteTeste, 1, webservice.getVersaoPadrao())) {//valida cte-lote
                    System.out.println("retorno de erro na validacao do xml");
                    erros = validador.getErros();
                    retorno = null;
                } else {
                    /**
                     * PREAPARA PARA ENVIAR CTE
                     */
                    if (cte.getCUF() == 41) {
                        //retorno = this.processaRetorno104PR(testePR);
                        retorno = this.processaRetorno200PR(dadosMsgXml);
                    } else {
                        retorno = this.processaRetorno200(dadosMsgXml);
                    }
                    //adiciona a nota ao lote para que a mesma possa ser retornada
                    retorno.setCte(cte);
                    retorno.setCodigoLote(codigoLote);

                    //exclui arquivos temporarios
                    //aAssinado.excluir();
                    //aLote.excluir();
                }
            }
        }
        return retorno;
    }

    private CteLote processaRetorno200(String dadosMsgXml) {
        CteLote retorno = new CteLote();
        CteRecepcaoStub cteUrl = null;
        try {
            String url = webservice.getUrlRecepcao();
            System.out.println("conectando em... " + url);
            cteUrl = new CteRecepcaoStub(url);
        } catch (AxisFault ex) {
            erros.put("ErroConexaoEnvioLote1", "" + ex.getMessage());
            ex.printStackTrace();
        }
        CteCabecMsgE cabecMsg1 = new CteCabecMsgE();
        CteCabecMsg param = new CteCabecMsg();
        param.setCUF("" + empresa.getcUf());
        param.setVersaoDados(webservice.getVersaoPadrao());
        cabecMsg1.setCteCabecMsg(param);
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(dadosMsgXml);
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        CteDadosMsg cteDados = new CteDadosMsg();
        cteDados.setExtraElement(ome);
        CteRecepcaoLoteResult resp = null;
        try {
            resp = cteUrl.cteRecepcaoLote(cteDados, cabecMsg1);
        } catch (RemoteException ex) {
            erros.put("ErroConexaoEnvioLote", "" + ex.getMessage());
            ex.printStackTrace();
        }
        if (erros.size() <= 0) {
            String result = resp.getExtraElement().toString();
            System.out.println(result);
            XStream x = new XStream(new DomDriver());
            x.processAnnotations(RetEnviCTe.class);
            RetEnviCTe ret = (RetEnviCTe) x.fromXML(result);
            retorno.setCUF(ret.getCUF());
            retorno.setNRec(ret.getRecibo().getNRec());
            retorno.setData(Utils.getInstance().convertStringDateSefaztoData(ret.getRecibo().getDhRecbto()));
            retorno.setCStat(ret.getCStat());
            retorno.setXMotivo(ret.getXMotivo());
        }
        return retorno;
    }

    private CteLote processaRetorno200PR(String dadosMsgXml) {
        CteLote retorno = new CteLote();
        CteRecepcaoStub cteUrl = null;
        CteDadosMsg cteDados = new CteDadosMsg();
        try {
            System.out.println("Vai pelo PR...");
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            //Propriedade utilizada para manter o namespace <NFe xmlns="www.portalfiscal.inf.br/nfe" >   
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StringReader(dadosMsgXml));

            //cteDados = CteRecepcaoStub.CteDadosMsg.Factory.parse(xmlStreamReader);
            System.out.println("Vai pelo PR... Dados OK...");
            String url = webservice.getUrlRecepcao();
            System.out.println("conectando em... " + url);
            cteUrl = new CteRecepcaoStub(url);
        } catch (AxisFault ex) {
            erros.put("ErroConexaoEnvioLote1", "" + ex.getMessage());
            ex.printStackTrace();
        } catch (XMLStreamException xmle) {
            erros.put("Erro StreamReader", "" + xmle.getMessage());
            xmle.printStackTrace();
        } catch (Exception e) {
            erros.put("Erro generico PR", "" + e.getMessage());
            e.printStackTrace();
        }
        CteCabecMsgE cabecMsg1 = new CteCabecMsgE();
        CteCabecMsg param = new CteCabecMsg();
        param.setCUF("" + empresa.getcUf());
        param.setVersaoDados(webservice.getVersaoPadrao());
        cabecMsg1.setCteCabecMsg(param);
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(dadosMsgXml);
            
            Iterator children = ome.getChildrenWithLocalName("CTe");  
            while(children.hasNext()) { 
                OMElement elementCTe = (OMElement)children.next();
                if(elementCTe != null && "CTe".equals(elementCTe.getLocalName())){  
                    elementCTe.addAttribute("xmlns", "http://www.portalfiscal.inf.br/cte", null);  
                }
            }
            
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        //CteDadosMsg cteDados = new CteDadosMsg();
        
        //System.out.println(ome.toString());
        cteDados.setExtraElement(ome);
        
        CteRecepcaoLoteResult resp = null;
        try {
            resp = cteUrl.cteRecepcaoLote(cteDados, cabecMsg1);
            System.out.println("Vai pelo PR... Chamou servico...");
        } catch (RemoteException ex) {
            erros.put("ErroConexaoEnvioLote", "" + ex.getMessage());
            ex.printStackTrace();
        }
        if (erros.size() <= 0) {
            String result = resp.getExtraElement().toString();
            System.out.println(result);
            XStream x = new XStream(new DomDriver());
            x.processAnnotations(RetEnviCTe.class);
            RetEnviCTe ret = (RetEnviCTe) x.fromXML(result);
            retorno.setCUF(ret.getCUF());
            retorno.setNRec(ret.getRecibo().getNRec());
            retorno.setData(Utils.getInstance().convertStringDateSefaztoData(ret.getRecibo().getDhRecbto()));
            retorno.setCStat(ret.getCStat());
            retorno.setXMotivo(ret.getXMotivo());
        }
        return retorno;
    }

    private void criaChaveAcesso() {
        String cUF = Utils.getInstance().zeroFill("" + empresa.getcUf(), 2);
        String AAMM = Utils.getInstance().getAnoFim(Utils.getInstance().convertDataToString(cte.getDhEmi())) + "" + Utils.getInstance().getMes(Utils.getInstance().convertDataToString(cte.getDhEmi()));
        String CNPJ = Utils.getInstance().replace(cte.getEmitente().getCNPJ());
        String mod = cte.getMod();
        String serie = Utils.getInstance().zeroFill("" + cte.getSerie(), 3);
        String nCT = Utils.getInstance().zeroFill("" + cte.getNCT(), 9);
        String cCT = Utils.getInstance().zeroFill("" + cte.getCCT(), 8);
        String idSemDV = cUF + "" + AAMM + "" + CNPJ + "" + mod + "" + serie + "" + nCT + "" + cte.getTpEmis() + cCT;
        String cDV = Utils.getInstance().getChave(idSemDV);

        cte.setChaveAcesso(idSemDV + "" + cDV);
        cte.setCDV(Utils.getInstance().convertToNumber(cDV));
        //System.out.println(cUF + "-" + AAMM + "-" + CNPJ + "-" + mod + "-" + serie + "-" + nCT + "-" +cte.getTpEmis()+"-"+ cCT + " " + cDV);
    }

    public HashMap getErros() {
        return erros;
    }
}
