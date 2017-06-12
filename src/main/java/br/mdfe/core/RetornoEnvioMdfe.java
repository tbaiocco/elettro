/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core;

import br.mdfe.core.ClassesMdfe.InfProt;
import br.mdfe.core.ClassesMdfe.ProtMDFe;
import br.mdfe.core.ClassesMdfe.RetConsReciMDFe;
import br.mdfe.core.base.EmpresaDb;
import br.mdfe.core.base.WebServiceDb;
import br.mdfe.core.webservices.service.MDFeRetRecepcao.MDFeRetRecepcaoStub;
import br.mdfe.core.webservices.service.MDFeRetRecepcao.MDFeRetRecepcaoStub.MdfeDadosMsg;
import br.mdfe.core.webservices.service.MDFeRetRecepcao.MDFeRetRecepcaoStub.MdfeRetRecepcaoResult;
import br.mdfe.core.xml.XmlEmissaoMdfe;
import br.mdfe.model.Empresa;
import br.mdfe.model.Mdfe;
import br.mdfe.model.MdfeRetornoEnvioLote;
import br.mdfe.model.WebService;
import br.mdfe.utils.Utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axis2.AxisFault;

/**
 *
 * @author DerliRiffel
 */
public class RetornoEnvioMdfe {

    private final Mdfe mdfe;
    private final int tpAmbiente;
    private final String cnpjEmissor;
    private Empresa empresa = null;
    private final HashMap erros = new HashMap();
    private WebService webservice = null;
    private final String nRec;

    public RetornoEnvioMdfe(String nRec, Mdfe mdfe) {
        this.cnpjEmissor = mdfe.getEmit().getCNPJ();
        this.tpAmbiente = mdfe.getTpAmb();
        this.mdfe = mdfe;
        this.nRec = nRec;
    }

    public MdfeRetornoEnvioLote executar() {
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
        	if(mdfe.getVersao() == null)
        		mdfe.setVersao(webservice.getVersaoPadrao());
            return processa100();
        }
        return null;
    }

    private MdfeRetornoEnvioLote processa100() {
        MdfeRetornoEnvioLote retorno = null;

        MDFeRetRecepcaoStub aUrl = null;
        try {
            String url = webservice.getUrlRetRecepcao();
            System.out.println("conectando em... " + url);
            aUrl = new MDFeRetRecepcaoStub(url);
        } catch (AxisFault ex) {
            ex.printStackTrace();
            erros.put("ErroConexaoConsultaProtocolo", "" + ex.getMessage());
        }

        MDFeRetRecepcaoStub.MdfeCabecMsgE cteCabecMsg1 = new MDFeRetRecepcaoStub.MdfeCabecMsgE();
        MDFeRetRecepcaoStub.MdfeCabecMsg param = new MDFeRetRecepcaoStub.MdfeCabecMsg();

        param.setCUF("" + empresa.getcUf());
        param.setVersaoDados("" + mdfe.getVersao());
        cteCabecMsg1.setMdfeCabecMsg(param);
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(""
                    + "<consReciMDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\" versao=\"" + mdfe.getVersao() + "\">"
                    + "<tpAmb>" + this.tpAmbiente + "</tpAmb>"
                    + "<nRec>" + this.nRec + "</nRec>"
                    + "</consReciMDFe>");
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }

        String result = null;
        MdfeDadosMsg cteDadosMsg = new MdfeDadosMsg();
        cteDadosMsg.setExtraElement(ome);
        System.out.println(cteDadosMsg.getExtraElement().toString());
        MdfeRetRecepcaoResult resp = null;
        try {
            resp = aUrl.mdfeRetRecepcao(cteDadosMsg, cteCabecMsg1);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            erros.put("ErroConexaoConsultaProtocolo", "" + ex.getMessage());
        }
        result = resp.getExtraElement().toString();
        System.out.println(result);
        if (erros.size() <= 0) {
            retorno = new MdfeRetornoEnvioLote();
            XStream x = new XStream(new DomDriver());
            x.processAnnotations(RetConsReciMDFe.class);
            x.processAnnotations(ProtMDFe.class);
            x.processAnnotations(InfProt.class);
            RetConsReciMDFe ret = (RetConsReciMDFe) x.fromXML(result);

            retorno.setCStat(ret.getCStat());
            retorno.setXMotivo(ret.getXMotivo());

            if (retorno.getCStat().equals("104")) {
                for (ProtMDFe prot : ret.getListProtNFe()) {
                    retorno.setNProt(prot.getInfProt().getnProt());
                    retorno.setDigVal(prot.getInfProt().getDigVal());
                    retorno.setCStat(prot.getInfProt().getcStat());
                    retorno.setXMotivo(prot.getInfProt().getxMotivo());
                    retorno.setVerAplic(prot.getInfProt().getVerAplic());
                    retorno.setDhRecbto(prot.getInfProt().getDhRecbto());

                    //somente gerar aprovação caso a cte esteja denegada(110) ou liberada para uso(100)
                    if (prot.getInfProt() != null
                            && prot.getInfProt().getcStat().equals("100") || prot.getInfProt().getcStat().equals("110")) {
                        mdfe.setNProt(prot.getInfProt().getnProt());
                        mdfe.setDigVal(prot.getInfProt().getDigVal());
                        mdfe.setCStat(prot.getInfProt().getcStat());
                        mdfe.setXMotivo(prot.getInfProt().getxMotivo());
                        mdfe.setVerAplic(prot.getInfProt().getVerAplic());
                        mdfe.setDhRecbto(Utils.getInstance().convertStringDateSefaztoData(prot.getInfProt().getDhRecbto()));

                        XmlEmissaoMdfe.getInstance().geraXmlMdfeLiberada(mdfe);
                    }
                    retorno.setMdfe(mdfe);
                }
            }
        }
        return retorno;
    }

    public HashMap getErros() {
        return erros;
    }
}
