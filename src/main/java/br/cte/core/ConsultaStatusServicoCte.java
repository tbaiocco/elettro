/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core;

import br.cte.base.EmpresaDb;
import br.cte.base.WebServiceCteDb;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import br.cte.core.ClassesCte.v200.RetConsStatServ;
import br.cte.core.webservices.service.CTeStatusServico.CteStatusServicoStub;
import br.cte.core.webservices.service.CTeStatusServico.CteStatusServicoStub.CteCabecMsg;
import br.cte.core.webservices.service.CTeStatusServico.CteStatusServicoStub.CteCabecMsgE;
import br.cte.core.webservices.service.CTeStatusServico.CteStatusServicoStub.CteDadosMsg;
import br.cte.core.webservices.service.CTeStatusServico.CteStatusServicoStub.CteStatusServicoCTResult;
import br.cte.model.CteStatusServico;
import br.cte.model.Empresa;
import br.cte.model.WebServiceCte;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axis2.AxisFault;

/**
 *
 * @author DerliRiffel
 */
public class ConsultaStatusServicoCte {

    private int tpAmbiente;
    private String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebServiceCte webservice = null;

    public ConsultaStatusServicoCte(int tpAmbiente, String cnpjEmissor) {
        this.cnpjEmissor = cnpjEmissor;
        this.tpAmbiente = tpAmbiente;
    }

    public CteStatusServico executar() {
        EmpresaDb eDb = new EmpresaDb();
        System.out.println("buscar empresa...");
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
                return processa();
            }
        }
        return null;
    }

    private CteStatusServico processa() {
        CteStatusServico retorno = new CteStatusServico();
        CteStatusServicoStub stubCte = null;
        try {
            String url = webservice.getUrlStatusServico();
            stubCte = new CteStatusServicoStub(url);
        } catch (AxisFault ex) {
            ex.printStackTrace();
        }

        CteCabecMsgE cteCabecMsg1 = new CteCabecMsgE();
        CteCabecMsg param = new CteCabecMsg();
        param.setCUF("" + empresa.getcUf());
        param.setVersaoDados(webservice.getVersaoPadrao());
        cteCabecMsg1.setCteCabecMsg(param);

        String xml = "<consStatServCte versao=\"" + webservice.getVersaoPadrao() + "\" xmlns=\"http://www.portalfiscal.inf.br/cte\">"
                + "<tpAmb>" + this.tpAmbiente + "</tpAmb>"
                + "<xServ>STATUS</xServ>"
                + "</consStatServCte>";
        System.out.println(xml);
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(xml);
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        CteDadosMsg dadosCte = new CteDadosMsg();
        dadosCte.setExtraElement(ome);
        CteStatusServicoCTResult resp = null;
        try {
            resp = stubCte.cteStatusServicoCT(dadosCte, cteCabecMsg1);
            System.out.println(resp.getExtraElement().toString());
        } catch (Exception e) {
            e.printStackTrace();
            erros.put("ErroConexaoStatusServico", "" + e.getMessage());
        }
        if (erros.size() <= 0) {
            System.out.println("" + resp.getExtraElement().toString());

            XStream x = new XStream(new DomDriver());
            x.alias("retConsStatServCte", RetConsStatServ.class);
            RetConsStatServ s = (RetConsStatServ) x.fromXML(resp.getExtraElement().toString());

            retorno.setCStat(s.getCStat());
            retorno.setDhRecbto(s.getDhRecbto());
            retorno.setTMed(s.gettMed());
            retorno.setXMotivo(s.getXMotivo());
            return retorno;
        } else {
            return null;
        }
    }

    public HashMap getErros() {
        return erros;
    }
}
