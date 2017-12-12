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
import br.cte.core.ClassesCte.v200.RetConsSitCTe;
import br.cte.core.webservices.service.CTeConsultaProtocolo.CteConsultaStub;
import br.cte.core.webservices.service.CTeConsultaProtocolo.CteConsultaStub.CteCabecMsg;
import br.cte.core.webservices.service.CTeConsultaProtocolo.CteConsultaStub.CteCabecMsgE;
import br.cte.core.webservices.service.CTeConsultaProtocolo.CteConsultaStub.CteConsultaCTResult;
import br.cte.core.webservices.service.CTeConsultaProtocolo.CteConsultaStub.CteDadosMsg;
import br.cte.model.CteConsulta;
import br.cte.model.Empresa;
import br.cte.model.WebServiceCte;
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
public class ConsultaCte {

    private String chCTe;
    private int tpAmbiente;
    private String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebServiceCte webservice = null;

    public ConsultaCte(String chCTe, int tpAmbiente, String cnpjEmissor) {
        this.cnpjEmissor = cnpjEmissor;
        this.tpAmbiente = tpAmbiente;
        this.chCTe = chCTe;
    }

    public CteConsulta executar() {
        EmpresaDb eDb = new EmpresaDb();
        empresa = eDb.getEmpresa(cnpjEmissor);
        if (empresa == null) {
            erros.put("empresa", "Não foi possível carregar dados da empresa");
        } else {
            WebServiceCteDb wDb = new WebServiceCteDb();
            webservice = wDb.getWebServer(empresa.getcUf(), tpAmbiente);

            if (webservice == null) {
                erros.put("webservice", "Não foi possível carregar dados do webservice");
            } else if (!CertDig.getInstance().setProprierts(empresa)) {
                erros.put("certificado digital", "Não foi possível setar propriedades do certificado digital.");
            } else {
                return processa200();
            }
        }
        return null;
    }

    private CteConsulta processa200() {
        CteConsulta retorno = null;
        CteConsultaStub cteUrl = null;
        try {
            String url = webservice.getUrlConsultaProtocolo();
            System.out.println("conectando em... " + url);
            cteUrl = new CteConsultaStub(url);
        } catch (AxisFault ex) {
            ex.printStackTrace();
            erros.put("ErroConexaoConsultaCte", "" + ex.getMessage());
        }

        CteCabecMsgE cteCabecMsg1 = new CteCabecMsgE();
        CteCabecMsg param = new CteCabecMsg();

        param.setCUF("" + empresa.getcUf());
        param.setVersaoDados("" + webservice.getVersaoPadrao());
        cteCabecMsg1.setCteCabecMsg(param);
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(""
                    + "<consSitCTe xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\"" + webservice.getVersaoPadrao() + "\">"
                    + "<tpAmb>" + this.tpAmbiente + "</tpAmb>"
                    + "<xServ>CONSULTAR</xServ>"
                    + "<chCTe>" + this.chCTe + "</chCTe>"
                    + "</consSitCTe>");
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }

        String result = null;
        CteDadosMsg cte = new CteDadosMsg();
        cte.setExtraElement(ome);
        System.out.println(cte.getExtraElement().toString());
        CteConsultaCTResult resp = null;
        try {
            resp = cteUrl.cteConsultaCT(cte, cteCabecMsg1);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            erros.put("ErroConexaoConsultaCTe", "" + ex.getMessage());
        }
        result = resp.getExtraElement().toString();
        System.out.println(result);

        if (erros.size() <= 0) {
            retorno = new CteConsulta();

            XStream x = new XStream(new DomDriver());
            x.processAnnotations(RetConsSitCTe.class);
            x.processAnnotations(InfProt.class);
            RetConsSitCTe ret = (RetConsSitCTe) x.fromXML(result);
            if (ret.getProtCTe() != null) {
                System.out.println("motivo: " + ret.getXMotivo());
                retorno.setXMotivo(ret.getProtCTe().getInfProt().getxMotivo());
                retorno.setCStat(ret.getProtCTe().getInfProt().getcStat());
                retorno.setNProt(ret.getProtCTe().getInfProt().getnProt());
                retorno.setDhRecbto(ret.getProtCTe().getInfProt().getDhRecbto());
            } else if (ret.getRetCancCTe() != null) {
                //caso tenha sido cancelado
                retorno.setXMotivo(ret.getRetCancCTe().getInfCanc().getXMotivo());
                retorno.setCStat(ret.getRetCancCTe().getInfCanc().getCStat());
                retorno.setNProt(ret.getRetCancCTe().getInfCanc().getNProt());
                retorno.setDhRecbto(ret.getRetCancCTe().getInfCanc().getDhRecbto());
            } else {
                System.out.println("motivo: " + ret.getXMotivo());
                retorno.setXMotivo(ret.getXMotivo());
                retorno.setCStat(ret.getCStat());
            }
        }
        return retorno;
    }

    public HashMap getErros() {
        return erros;
    }
}
