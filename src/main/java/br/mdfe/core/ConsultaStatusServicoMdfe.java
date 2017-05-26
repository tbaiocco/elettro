package br.mdfe.core;

import br.mdfe.core.ClassesMdfe.RetConsStatServ;
import br.mdfe.core.base.EmpresaDb;
import br.mdfe.core.base.WebServiceDb;
import br.mdfe.core.webservices.service.MDFeStatusServico.MDFeStatusServicoStub;
import br.mdfe.core.webservices.service.MDFeStatusServico.MDFeStatusServicoStub.MdfeCabecMsg;
import br.mdfe.core.webservices.service.MDFeStatusServico.MDFeStatusServicoStub.MdfeCabecMsgE;
import br.mdfe.core.webservices.service.MDFeStatusServico.MDFeStatusServicoStub.MdfeDadosMsg;
import br.mdfe.core.webservices.service.MDFeStatusServico.MDFeStatusServicoStub.MdfeStatusServicoMDFResult;
import br.mdfe.model.Empresa;
import br.mdfe.model.MdfeStatusServico;
import br.mdfe.model.WebService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DerliRiffel
 */
public class ConsultaStatusServicoMdfe {

    private final int tpAmbiente;
    private final String cnpjEmissor;
    private Empresa empresa = null;
    private final HashMap erros = new HashMap();
    private WebService webservice = null;

    /**
     *
     * @param tpAmbiente
     * @param cnpjEmissor
     */
    public ConsultaStatusServicoMdfe(int tpAmbiente, String cnpjEmissor) {
        this.cnpjEmissor = cnpjEmissor;
        this.tpAmbiente = tpAmbiente;
    }

    /**
     * *
     * Excuta consulta do webservice
     *
     * @return NfeStatusServico
     */
    public MdfeStatusServico executar() {
        EmpresaDb eDb = new EmpresaDb();
        empresa = eDb.getEmpresa(cnpjEmissor);

        if (empresa == null) {
            erros.put("empresa", "Não foi possível carregar dados da empresa");
        } else {
            WebServiceDb wDb = new WebServiceDb();
            webservice = wDb.getWebServer(empresa.getcUf(), tpAmbiente);
        }
        if (webservice == null) {
            erros.put("webservice", "Não foi possível carregar dados do webservice");
        } else if (!CertDig.getInstance().setProprerties(empresa)) {
            erros.put("certificado digital", "Não foi possível setar propriedades do certificado digital.");
        } else {
            MdfeStatusServico retorno = this.processaRetorno100();

            return retorno;
        }
        return null;
    }

    private MdfeStatusServico processaRetorno100() {
        MdfeStatusServico retorno = new MdfeStatusServico();

        MDFeStatusServicoStub nfe = null;
        try {
            String url = webservice.getUrlStatusServico();
            nfe = new MDFeStatusServicoStub(url);
            System.out.println("\nconectando url:" + url);
        } catch (AxisFault ex) {
            ex.printStackTrace();
        }

        MdfeCabecMsgE nfeCabecMsg1 = new MdfeCabecMsgE();
        MdfeCabecMsg param = new MdfeCabecMsg();

        param.setCUF("" + empresa.getcUf());
        param.setVersaoDados("1.00");
        nfeCabecMsg1.setMdfeCabecMsg(param);

        String xml = "<consStatServMDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\" versao=\"1.00\">"
                + "<tpAmb>" + this.tpAmbiente + "</tpAmb>"
                + "<xServ>STATUS</xServ>"
                + "</consStatServMDFe>";
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(xml);
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }

        MdfeDadosMsg m2 = new MdfeDadosMsg();
        m2.setExtraElement(ome);

        MdfeStatusServicoMDFResult resp = null;
        try {
            resp = nfe.mdfeStatusServicoMDF(m2, nfeCabecMsg1);
        } catch (RemoteException ex) {
            System.out.println("\nErro ao consultar status do servico\n\n" + ex);
            erros.put("ErroConexaoStatusServico", "" + ex.getMessage());
        }

        if (erros.size() <= 0) {
            System.out.println("" + resp.getExtraElement().toString());

            XStream x = new XStream(new DomDriver());
            x.alias("retConsStatServMDFe", RetConsStatServ.class);
            RetConsStatServ s = (RetConsStatServ) x.fromXML(resp.getExtraElement().toString());

            retorno.setcStat(s.getCStat());
            retorno.setDhRecbto(s.getDhRecbto());
            retorno.settMed(s.gettMed());
            retorno.setxMotivo(s.getXMotivo());
            return retorno;
        } else {
            return null;
        }
    }

    public HashMap getErros() {
        return erros;
    }
}
