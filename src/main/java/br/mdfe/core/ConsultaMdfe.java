/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core;

import br.mdfe.core.ClassesMdfe.InfProt;
import br.mdfe.core.ClassesMdfe.ProcEventoMDFe;
import br.mdfe.core.ClassesMdfe.ProtMDFe;
import br.mdfe.core.ClassesMdfe.RetConsSitMDFe;
import br.mdfe.core.base.EmpresaDb;
import br.mdfe.core.base.WebServiceDb;
import br.mdfe.core.webservices.service.MDFeConsulta.MDFeConsultaStub;
import br.mdfe.core.webservices.service.MDFeConsulta.MDFeConsultaStub.MdfeCabecMsg;
import br.mdfe.core.webservices.service.MDFeConsulta.MDFeConsultaStub.MdfeCabecMsgE;
import br.mdfe.core.webservices.service.MDFeConsulta.MDFeConsultaStub.MdfeDadosMsg;
import br.mdfe.model.Empresa;
import br.mdfe.model.MdfeConsulta;
import br.mdfe.model.WebService;
import br.utils.Arquivo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.MapperWrapper;
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
public class ConsultaMdfe {

    private String chMdfe;
    private int tpAmbiente;
    private String cnpjEmissor;
    private Empresa empresa = null;
    private HashMap erros = new HashMap();
    private WebService webservice = null;

    public ConsultaMdfe(String chMdfe, int tpAmbiente, String cnpjEmissor) {
        this.cnpjEmissor = cnpjEmissor;
        this.tpAmbiente = tpAmbiente;
        this.chMdfe = chMdfe;
    }

    public MdfeConsulta executar() {
        EmpresaDb eDb = new EmpresaDb();
        empresa = eDb.getEmpresa(cnpjEmissor);
        if (empresa == null) {
            erros.put("empresa", "Não foi possível carregar dados da empresa");
        } else {
            WebServiceDb wDb = new WebServiceDb();
            webservice = wDb.getWebServer(empresa.getcUf(), tpAmbiente);

            if (webservice == null) {
                erros.put("webservice", "Não foi possível carregar dados do webservice");
            } else if (!CertDig.getInstance().setProprerties(empresa)) {
                erros.put("certificado digital", "Não foi possível setar propriedades do certificado digital.");
            } else {
                return processa100();
            }
        }
        return null;
    }

    private MdfeConsulta processa100() {
        MdfeConsulta retorno = null;
        MDFeConsultaStub cteUrl = null;
        try {
            String url = webservice.getUrlConsultaMdfe();
            System.out.println("conectando em... " + url);
            cteUrl = new MDFeConsultaStub(url);
        } catch (AxisFault ex) {
            ex.printStackTrace();
            erros.put("ErroConexaoConsultaCte", "" + ex.getMessage());
        }

        MdfeCabecMsgE cabecMsg1 = new MdfeCabecMsgE();
        MdfeCabecMsg param = new MdfeCabecMsg();

        param.setCUF("" + empresa.getcUf());
        param.setVersaoDados("" + webservice.getVersaoPadrao());
        cabecMsg1.setMdfeCabecMsg(param);
        OMElement ome = null;
        try {
            ome = AXIOMUtil.stringToOM(""
                    + "<consSitMDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\" versao=\"1.00\">"
                    + "<tpAmb>" + this.tpAmbiente + "</tpAmb>"
                    + "<xServ>CONSULTAR</xServ>"
                    + "<chMDFe>" + this.chMdfe + "</chMDFe>"
                    + "</consSitMDFe>");
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }

        String result = null;
        MdfeDadosMsg dmsg = new MdfeDadosMsg();
        dmsg.setExtraElement(ome);
        System.out.println(dmsg.getExtraElement().toString());
        MDFeConsultaStub.MdfeConsultaMDFResult resp = null;
        try {
            resp = cteUrl.mdfeConsultaMDF(dmsg, cabecMsg1);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            erros.put("ErroConexaoConsultaCTe", "" + ex.getMessage());
            return null;
        }
        result = resp.getExtraElement().toString();
        System.out.println(result);
//        Arquivo a = new Arquivo("e:\\teste.xml");
//        a.abrirEscrita();
//        a.escreverLinha(result);
//        a.fecharArquivo();

        if (erros.size() <= 0) {
            retorno = new MdfeConsulta();

            XStream x = new XStream(new DomDriver()) {

                @Override
                protected MapperWrapper wrapMapper(MapperWrapper next) {
                    return new MapperWrapper(next) {

                        @Override
                        public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                            try {
                                return definedIn != Object.class || realClass(fieldName) != null;
                            } catch (CannotResolveClassException cnrce) {
                                return false;
                            }
                        }
                    };
                }
            };
            x.processAnnotations(RetConsSitMDFe.class);
            x.processAnnotations(ProtMDFe.class);
            x.processAnnotations(InfProt.class);

            RetConsSitMDFe ret = (RetConsSitMDFe) x.fromXML(result);
            if (ret.getProtMDFe() != null) {
                System.out.println("motivo: " + ret.getXMotivo());
                retorno.setXMotivo(ret.getProtMDFe().getInfProt().getxMotivo());
                retorno.setCStat(ret.getProtMDFe().getInfProt().getcStat());
                retorno.setNProt(ret.getProtMDFe().getInfProt().getnProt());
                retorno.setDhRecbto(ret.getProtMDFe().getInfProt().getDhRecbto());
            } else {
                System.out.println("motivo: " + ret.getXMotivo());
                retorno.setXMotivo(ret.getXMotivo());
                retorno.setCStat(ret.getCStat());
            }
            if (ret.getProcEventoMDFe() != null) {
                //caso tenha sido registrado evento
                System.out.println("Evento motivo: " + ret.getXMotivo());
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
