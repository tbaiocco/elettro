/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core;

import br.cte.base.EmpresaDb;
import br.cte.base.WebServiceCteDb;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import br.cte.core.ClassesCte.v200.InfProt;
import br.cte.core.ClassesCte.v200.ProtCTe;
import br.cte.core.ClassesCte.v200.RetConsReciCTe;
import br.cte.core.webservices.service.CTeRetRecepcao.CteRetRecepcaoStub;
import br.cte.core.webservices.service.CTeRetRecepcao.CteRetRecepcaoStub.CteCabecMsg;
import br.cte.core.webservices.service.CTeRetRecepcao.CteRetRecepcaoStub.CteCabecMsgE;
import br.cte.core.webservices.service.CTeRetRecepcao.CteRetRecepcaoStub.CteDadosMsg;
import br.cte.core.webservices.service.CTeRetRecepcao.CteRetRecepcaoStub.CteRetRecepcaoResult;
import br.cte.core.xml.XmlEmissaoCte;
import br.cte.model.Cte;
import br.cte.model.CteRetornoEnvioLote;
import br.cte.model.Empresa;
import br.cte.model.WebServiceCte;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import br.utils.Utils;

/**
 *
 * @author DerliRiffel
 */
public class RetornoEnvioCte {

    private Cte cte;
    private int tpAmbiente;
    private String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebServiceCte webservice = null;
    private String nRec;

    public RetornoEnvioCte(String nRec, Cte cte) {
        this.cnpjEmissor = cte.getEmitente().getCNPJ();
        this.tpAmbiente = cte.getTpAmb();
        if(cte.getVersao()==null){
            cte.setVersao("3.00");
        }
        this.cte = cte;
        this.nRec = nRec;
    }

    public CteRetornoEnvioLote executar() {
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

    private CteRetornoEnvioLote processa103() {
        CteRetornoEnvioLote retorno = null;

        CteRetRecepcaoStub cteUrl = null;
        try {
            String url = webservice.getUrlRetRecepcao();
            System.out.println("conectando em... " + url);
            cteUrl = new CteRetRecepcaoStub(url);
        } catch (AxisFault ex) {
            ex.printStackTrace();
            erros.put("ErroConexaoConsultaProtocolo", "" + ex.getMessage());
        }

        CteCabecMsgE cteCabecMsg1 = new CteCabecMsgE();
        CteCabecMsg param = new CteCabecMsg();

        param.setCUF("" + empresa.getcUf());
//        param.setVersaoDados("" + webservice.getVersaoPadrao());
        param.setVersaoDados("" + cte.getVersao());
        cteCabecMsg1.setCteCabecMsg(param);
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(""
//            		+ "<consReciCTe xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\"" + webservice.getVersaoPadrao() + "\">"
                    + "<consReciCTe xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\"" + cte.getVersao() + "\">"
                    + "<tpAmb>" + this.tpAmbiente + "</tpAmb>"
                    + "<nRec>" + this.nRec + "</nRec>"
                    + "</consReciCTe>");
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }

        String result = null;
        CteDadosMsg cteDadosMsg = new CteDadosMsg();
        cteDadosMsg.setExtraElement(ome);
        System.out.println(cteDadosMsg.getExtraElement().toString());
        CteRetRecepcaoResult resp = null;
        try {
            resp = cteUrl.cteRetRecepcao(cteDadosMsg, cteCabecMsg1);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            erros.put("ErroConexaoConsultaProtocolo", "" + ex.getMessage());
        }
        result = resp.getExtraElement().toString();
        System.out.println(result);
        if (erros.size() <= 0) {
            retorno = new CteRetornoEnvioLote();
            XStream x = new XStream(new DomDriver());
            x.processAnnotations(RetConsReciCTe.class);
            x.processAnnotations(ProtCTe.class);
            x.processAnnotations(InfProt.class);
            RetConsReciCTe ret = (RetConsReciCTe) x.fromXML(result);

            retorno.setCStat(ret.getCStat());
            retorno.setXMotivo(ret.getXMotivo());

            if (retorno.getCStat().equals("104")) {
                for (ProtCTe prot : ret.getListProtNFe()) {
                    retorno.setNProt(prot.getInfProt().getnProt());
                    retorno.setDigVal(prot.getInfProt().getDigVal());
                    retorno.setCStat(prot.getInfProt().getcStat());
                    retorno.setXMotivo(prot.getInfProt().getxMotivo());
                    retorno.setVerAplic(prot.getInfProt().getVerAplic());
                    retorno.setDhRecbto(prot.getInfProt().getDhRecbto());

                    //somente gerar aprovação caso a cte esteja denegada(110) ou liberada para uso(100)
                    if (prot.getInfProt() != null
                            && prot.getInfProt().getcStat().equals("100") || prot.getInfProt().getcStat().equals("110")) {
                        cte.setProtocolo(prot.getInfProt().getnProt());
                        cte.setDigestValue(prot.getInfProt().getDigVal());
                        cte.setCStat(Utils.getInstance().convertToNumber(prot.getInfProt().getcStat()));
                        cte.setXMotivo(prot.getInfProt().getxMotivo());
                        cte.setVerAPlic(prot.getInfProt().getVerAplic());
                        cte.setDhRecbto(Utils.getInstance().convertStringDateSefaztoData(prot.getInfProt().getDhRecbto()));

                        XmlEmissaoCte.getInstance().geraXmlCteLiberada(cte, cte.getVersao());
                    }
                    retorno.setCte(cte);
                }
            }
        }
        return retorno;
    }

    public HashMap getErros() {
        return erros;
    }
}
