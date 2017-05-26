/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core;

import br.mdfe.core.ClassesMdfe.RetEnviMDFe;
import br.mdfe.core.base.EmpresaDb;
import br.mdfe.core.base.LoteMdfeDb;
import br.mdfe.core.validador.XmlEnvioMdfe;
import br.mdfe.core.webservices.service.MDFeRecepcao.MDFeRecepcaoStub;
import br.mdfe.core.webservices.service.MDFeRecepcao.MDFeRecepcaoStub.MdfeCabecMsg;
import br.mdfe.core.webservices.service.MDFeRecepcao.MDFeRecepcaoStub.MdfeCabecMsgE;
import br.mdfe.core.webservices.service.MDFeRecepcao.MDFeRecepcaoStub.MdfeDadosMsg;
import br.mdfe.core.webservices.service.MDFeRecepcao.MDFeRecepcaoStub.MdfeRecepcaoLoteResult;
import br.mdfe.core.xml.XmlEmissaoMdfe;
import br.mdfe.model.Empresa;
import br.mdfe.model.Mdfe;
import br.mdfe.model.MdfeLote;
import br.mdfe.model.WebService;
import br.utils.Arquivo;
import br.utils.Configuracoes;
import br.utils.Utils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axis2.AxisFault;

/**
 *
 * @author DerliRiffel
 */
public class EnvioLoteMdfe {

    private final Mdfe mdfe;
    private final int tpAmbiente;
    private final String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebService webservice = null;

    public EnvioLoteMdfe(Mdfe mdfe) {
        this.cnpjEmissor = mdfe.getEmit().getCNPJ();
        this.tpAmbiente = mdfe.getTpAmb();
        if(mdfe.getVersao()==null){
            mdfe.setVersao("1.00");
        }
        this.mdfe = mdfe;
    }

    public MdfeLote executar() {
        MdfeLote retorno = null;
        EmpresaDb eDb = new EmpresaDb();

        empresa = eDb.getEmpresa(cnpjEmissor);
        if (empresa == null) {
            erros.put("empresa", "Não foi possível carregar dados da empresa");
        } else {
            br.mdfe.core.base.WebServiceDb wDb = new br.mdfe.core.base.WebServiceDb();
            webservice = wDb.getWebServer(mdfe.getCUF(), mdfe.getTpAmb());

            if (webservice == null) {
                erros.put("webservice", "Não foi possível carregar dados do webservice");
            } else if (!CertDig.getInstance().setProprerties(empresa)) {
                erros.put("certificado digital", "Não foi possível setar propriedades do certificado digital.");
            } else {
                
                retorno = new MdfeLote();
                retorno.setData(new Date());
                //salva a data do envio do lote no xml
                LoteMdfeDb ldb = new LoteMdfeDb();
                ldb.salvaLote(retorno);
                //busca nro sequencial do lote salvo
                int codigoLote = ldb.getCodigoLote();
                //gera chave de acesso caso nao esteja preenchida no objeto                
                if (mdfe.getChAcesso() == null) {
                    criaChaveAcesso();
                }
                if (mdfe.getCDV() == null) {
                    String ch = mdfe.getChAcesso();
                    mdfe.setCDV(Integer.parseInt(ch.substring(ch.length() - 1)));
                }
                mdfe.setEmpresa(empresa);

                String dadosMsgXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<enviMDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\" versao=\"" + mdfe.getVersao() + "\">"
                        + "<idLote>" + retorno.getCodigoLote() + "</idLote>";

                String xmlNota = XmlEmissaoMdfe.getInstance().getXml(mdfe,mdfe.getVersao());

                String nomeArquivo = Configuracoes.getInstance().getTmpDir() + System.getProperty("file.separator") + "mdfe-" + mdfe.getNMDF() + ".xml";
                String nomeArquivoAss = Configuracoes.getInstance().getTmpDir() + System.getProperty("file.separator") + "mdfe-" + mdfe.getNMDF() + ".xml";
                Arquivo a = new Arquivo(nomeArquivo);

                //boolean resDir = a.validateDir(diretorio);
                //System.out.println(resDir+"|diretorio:"+diretorio);
                a.abrirEscrita();
                a.escreverLinha(xmlNota);
                a.fecharArquivo();

                CertDig.getInstance().Ass(nomeArquivo, nomeArquivoAss, "1", empresa);

                Arquivo aAssinado = new Arquivo(nomeArquivoAss);
                aAssinado.abrirLeitura();
                dadosMsgXml += aAssinado.ler();
                aAssinado.fecharArquivo();

                dadosMsgXml = dadosMsgXml.trim() + "</enviMDFe>";

                String nomeArquivoLote = Configuracoes.getInstance().getTmpDir() + System.getProperty("file.separator") + "lote-" + ldb.getCodigoLote() + ".xml";
                Arquivo aLote = new Arquivo(nomeArquivoLote);
                aLote.abrirEscrita();
                aLote.escreverLinha(dadosMsgXml.trim());
                aLote.fecharArquivo();

                XmlEnvioMdfe validador = new XmlEnvioMdfe();
                //System.out.println("validando xml");
                if (!validador.valida(nomeArquivoLote, 1, mdfe.getVersao())) {
                    System.out.println("retorno de erro na validacao do xml");
                    erros = validador.getErros();
                    retorno = null;
                } else {
                    retorno = this.processaRetorno100(dadosMsgXml);
                    //adiciona a nota ao lote para que a mesma possa ser retornada
                    retorno.setMdfe(mdfe);
                    retorno.setCodigoLote(codigoLote);

                    //exclui arquivos temporarios
//                    if (mdfe.getTpAmb() == 1) {
//                        aAssinado.excluir();
//                        aLote.excluir();
//                    }
                }
            }
        }
        return retorno;
    }

    private MdfeLote processaRetorno100(String dadosMsgXml) {
        MdfeLote retorno = new MdfeLote();
        MDFeRecepcaoStub aurl = null;
        try {
            String url = webservice.getUrlRecepcao();
            System.out.println("conectando em... " + url);
            aurl = new MDFeRecepcaoStub(url);
        } catch (AxisFault ex) {
            erros.put("ErroConexaoEnvioLote1", "" + ex.getMessage());
            ex.printStackTrace();
        }
        MdfeCabecMsgE cabecMsg1 = new MdfeCabecMsgE();
        MdfeCabecMsg param = new MdfeCabecMsg();
        param.setCUF("" + empresa.getcUf());
        param.setVersaoDados(mdfe.getVersao());
        cabecMsg1.setMdfeCabecMsg(param);
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(dadosMsgXml);
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        MdfeDadosMsg dados = new MdfeDadosMsg();
        dados.setExtraElement(ome);
        MdfeRecepcaoLoteResult resp = null;
        try {
            resp = aurl.mdfeRecepcaoLote(dados, cabecMsg1);
        } catch (RemoteException ex) {
            erros.put("ErroConexaoEnvioLote", "" + ex.getMessage());
            ex.printStackTrace();
        }
        if (erros.size() <= 0) {
            String result = resp.getExtraElement().toString();
            System.out.println(result);
            XStream x = new XStream(new DomDriver());
            x.processAnnotations(RetEnviMDFe.class);
            RetEnviMDFe ret = (RetEnviMDFe) x.fromXML(result);
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
        String AAMM = Utils.getInstance().getAnoFim(Utils.getInstance().convertDataToString(mdfe.getDhEmi())) + "" + Utils.getInstance().getMes(Utils.getInstance().convertDataToString(mdfe.getDhEmi()));
        String CNPJ = Utils.getInstance().replace(mdfe.getEmit().getCNPJ());
        String mod = mdfe.getMod() + "";
        String serie = Utils.getInstance().zeroFill("" + mdfe.getSerie(), 3);
        String nCT = Utils.getInstance().zeroFill("" + mdfe.getNMDF(), 9);
        String cCT = Utils.getInstance().zeroFill("" + mdfe.getCMDF(), 8);
        String idSemDV = cUF + "" + AAMM + "" + CNPJ + "" + mod + "" + serie + "" + nCT + "" + mdfe.getTpEmis() + cCT;
        String cDV = Utils.getInstance().getChave(idSemDV);

        mdfe.setChAcesso(idSemDV + "" + cDV);
        mdfe.setCDV(Utils.getInstance().convertToNumber(cDV));
        //System.out.println(cUF + "-" + AAMM + "-" + CNPJ + "-" + mod + "-" + serie + "-" + nCT + "-" +mdfe.getTpEmis()+"-"+ cCT + " " + cDV);
    }

    public HashMap getErros() {
        return erros;
    }
}
