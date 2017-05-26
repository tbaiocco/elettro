/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.teste;

import br.mdfe.core.ClassesMdfe.InfProt;
import br.mdfe.core.ClassesMdfe.ProcEventoMDFe;
import br.mdfe.core.ClassesMdfe.ProtMDFe;
import br.mdfe.core.ClassesMdfe.RetConsSitMDFe;
import br.mdfe.core.base.EmpresaDb;
import br.mdfe.model.Empresa;
import br.mdfe.model.Mdfe;
import br.mdfe.model.MdfeConsulta;
import br.mdfe.model.MdfeEvento;
import br.mdfe.model.MdfeLote;
import br.mdfe.model.MdfeRetornoEnvioLote;
import br.mdfe.model.MdfeStatusServico;
import br.servicos.MdfeServicos;
import br.utils.Arquivo;
import br.utils.Configuracoes;
import br.utils.Formatador;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.jasypt.util.text.BasicTextEncryptor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author teo
 */
public class TestaMdfe {

    public static void main(String args[]) {
        try {
            //cadastraEmpresa();
            //statusServico();

//            enviaMdfe();
//            consulta();
            // EventoTrocaMotorista();
            //  EventoCancelamento();
//          EventoEncerramento();
//            consulta();
//            xml();
            imprimir();
        } catch (Exception ex) {
            System.out.println("ops!!!... erroooo!!");
            ex.printStackTrace();
        }
    }

    public static void showErrors(HashMap errors) {
        Set set = errors.entrySet();

        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.println(me.getKey() + " : " + me.getValue());
        }
    }

    public static void enviaMdfe() {

        MdfeTeste ex = new MdfeTeste();
        Mdfe mdfe = ex.getMdfe(5);
        MdfeServicos servico = new MdfeServicos();

        MdfeLote retorno = servico.enviaMdfe(mdfe);
        if (retorno != null) {
            System.out.println("Retorno 1.00 ok!!!\n"
                    + " CHAVE: " + retorno.getMdfe().getChAcesso() + "\n"
                    + " Recibo: " + retorno.getNRec() + "\n"
                    + " Data: " + retorno.getData() + "\n"
                    + " cstat: " + retorno.getCStat() + " \n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao transmitir 1.00 ... consulte hashMap");
            showErrors(servico.getErros());
        }

        try {
            System.out.println("aguardando 3s para consultar recibo!!!");
            Thread.sleep(3000);
        } catch (InterruptedException exx) {
            exx.printStackTrace();
        }

        if (retorno.getNRec() != null) {
            MdfeRetornoEnvioLote retorno2 = servico.retornoEnvioMdfe(retorno.getNRec(), mdfe);
            if (retorno2 != null) {
                System.out.println("Retorno 1.00 ok!!!\n"
                        + " Protocolo: " + retorno2.getNProt() + "\n"
                        + " Data: " + retorno2.getDhRecbto() + "\n"
                        + " cstat: " + retorno2.getCStat() + "\n"
                        + " Motivo: " + retorno2.getXMotivo());
            } else {
                System.out.println("Erro ao buscar status do servico 1.00 ... consulte hashMap");
                showErrors(servico.getErros());
            }
        }
    }

    public static void consulta() throws Exception {
        MdfeServicos servico = new MdfeServicos();

        MdfeConsulta retorno = servico.consultaMdfe("43140691278119000186580010000000011000000014", 2,
                "91278119000186");
        if (retorno != null) {
            System.out.println("Retorno MDFE 1.00 ok!!!\n"
                    + " Protocolo: " + retorno.getNProt() + "\n"
                    + " Data: " + retorno.getDhRecbto() + "\n"
                    + " cstat: " + retorno.getCStat() + "\n"
                    + " Motivo: " + retorno.getXMotivo());
            if (retorno.getCStat().equals("101")) {
                System.out.println("MDFe ESTA CANCELADO!!!!!");
            } else if (retorno.getCStat().equals("100")) {
                System.out.println("MDFe ATIVO: \nprotocolo de emissao: " + retorno.getNProt());
            }

        } else {
            System.out.println("Erro ao consulta mdfe 1.00 ... consulte hashMap");
            showErrors(servico.getErros());
        }

    }

    public static void EventoTrocaMotorista() {

        MdfeServicos servico = new MdfeServicos();

        MdfeEvento mdfe = new MdfeEvento();
        mdfe.setCOrgao(43);
        mdfe.setCNPJ("91278119000186");
        mdfe.setTpAmb(2);
        mdfe.setChMDFe("43140691278119000186580010000000041000000040");
        mdfe.setTpEvento(110114);
        mdfe.setNProtAprovacaoMDFe("943140000062501");
        mdfe.setDescEvento("Inclusao Condutor");
        mdfe.setXNomeCondutor("DERLI ELIAS RIFFEL");
        mdfe.setCPFCondutor("00027296016");
        mdfe.setNSeqEvento(5);//<<<<<<======= alterar para 1
        mdfe.setDhEvento(new Date());
        MdfeEvento retorno = servico.eventoMdfe(mdfe.getTpAmb(), mdfe.getCNPJ(), mdfe);
        if (retorno != null) {
            System.out.println("Retorno " + mdfe.getDescEvento() + " 1.00 ok!!!\n"
                    + " Protocolo: " + retorno.getNProtRegEvento() + "\n"
                    + " Data: " + retorno.getDhRegEvento() + "\n"
                    + " cstat: " + retorno.getCStat() + " \n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao registrar evento ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void EventoCancelamento() {

        MdfeServicos servico = new MdfeServicos();

        MdfeEvento mdfe = new MdfeEvento();
        mdfe.setCOrgao(43);
        mdfe.setCNPJ("91278119000186");
        mdfe.setTpAmb(2);
        mdfe.setChMDFe("43140691278119000186580010000000041000000040");
        mdfe.setTpEvento(110111);
        mdfe.setNProtAprovacaoMDFe("943140000062501");
        mdfe.setDescEvento("Cancelamento");
        mdfe.setXJust("JUSTIFICATIVA DE CANCELAMENTO DE MDFE");
        mdfe.setNSeqEvento(1);
        mdfe.setDhEvento(new Date());
        MdfeEvento retorno = servico.eventoMdfe(mdfe.getTpAmb(), mdfe.getCNPJ(), mdfe);
        if (retorno != null) {
            System.out.println("Retorno " + mdfe.getDescEvento() + " 1.00 ok!!!\n"
                    + " Protocolo: " + retorno.getNProtRegEvento() + "\n"
                    + " Data: " + retorno.getDhRegEvento() + "\n"
                    + " cstat: " + retorno.getCStat() + " \n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao registrar evento ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void EventoEncerramento() {

        MdfeServicos servico = new MdfeServicos();

        MdfeEvento mdfe = new MdfeEvento();
        mdfe.setCOrgao(43);
        mdfe.setCNPJ("91278119000186");
        mdfe.setTpAmb(2);
        mdfe.setChMDFe("43140691278119000186580010000000011000000014");
        mdfe.setTpEvento(110112);
        mdfe.setNProtAprovacaoMDFe("943140000062494");
        mdfe.setDescEvento("Encerramento");
        mdfe.setNSeqEvento(1);
        mdfe.setDhEvento(new Date());
        mdfe.setDtEnc(new Date());
        mdfe.setCUFEnc(42);
        mdfe.setCMun(4205407);
        MdfeEvento retorno = servico.eventoMdfe(mdfe.getTpAmb(), mdfe.getCNPJ(), mdfe);
        if (retorno != null) {
            System.out.println("Retorno " + mdfe.getDescEvento() + " 1.00 ok!!!\n"
                    + " Protocolo: " + retorno.getNProtRegEvento() + "\n"
                    + " Data: " + retorno.getDhRegEvento() + "\n"
                    + " cstat: " + retorno.getCStat() + " \n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao registrar evento ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void statusServico() throws Exception {
        MdfeServicos servico = new MdfeServicos();
        MdfeStatusServico retorno = servico.consultaStatusServico(2, "91.278.119/0001-86");
        if (retorno != null) {
            System.out.println("Retorno MDFe 1.00 ok!!!\n"
                    + " Tempo: " + retorno.gettMed() + "ms\n"
                    + " Data: " + retorno.getDhRecbto() + "\n"
                    + " Motivo: " + retorno.getxMotivo());
        } else {
            System.out.println("Erro ao buscar status do servico 1.00 ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    private static void xml() {
        Arquivo a = new Arquivo("e:\\teste.xml");
        a.abrirLeitura();
        String xml = a.ler();
        a.fecharArquivo();

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
//            x.processAnnotations(ProcEventoMDFe.class);

        RetConsSitMDFe ret = (RetConsSitMDFe) x.fromXML(xml);
    }

    public static void cadastraEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setCnpj("91.278.119/0001-86");
        empresa.setIe("4000000259");
        empresa.setRazaosocial("HEITOR ARNO THOMAS");
        empresa.setTipoCertificado(1);
        empresa.setCertificado("thomas2013.pfx");

        String senha = "";
        BasicTextEncryptor textEncryptorNew = new BasicTextEncryptor();
        String senhaMestra = "liquid***###+++";
        textEncryptorNew.setPassword("A" + senhaMestra);
        empresa.setSenha(textEncryptorNew.encrypt(senha));

        empresa.setcUf(43);
        empresa.setMun("SANTA CLARA DO SUL");
        empresa.setcMun(430000);
        empresa.setUf("RS");

        EmpresaDb eDb = new EmpresaDb();
        if (eDb.salvaEmpresa(empresa)) {
            System.out.println("Empresa " + empresa.getRazaosocial() + ", foi salva com sucesso.");
            System.out.println("\n\n");

            Empresa e = eDb.getEmpresa(empresa.getCnpj());
            System.out.println("Empresa salva no xml: " + e.getRazaosocial() + " " + e.getCnpj());
        } else {
            System.out.println("Empresa " + empresa.getRazaosocial() + ", não pode ser salva");
        }
    }

    private static void imprimir() {
//          String img = Configuracoes.getInstance().getAppDir() + "/logo.png";
//        File f = new File(img);
//        if (f.exists()) {
//            parametros.put("logo", img);
//        }
        // String pathJasper = Configuracoes.getInstance().getAppDir() + "/relatorios/";
        HashMap parametros = new HashMap();
        String jasper = "E:\\projetos-java\\nalthusMdfe\\src\\br\\mdfe\\report\\DAMDFe.jasper";

        MdfeTeste ex = new MdfeTeste();
        Mdfe mdfe = ex.getMdfe(5);

        mdfe.getEmit().setCNPJ(Formatador.getInstance().formataCnpjNumberToString( mdfe.getEmit().getCNPJ()));
        mdfe.getEmit().setCEP(Formatador.getInstance().formataCepNumberString(mdfe.getEmit().getCEP()));
        
        mdfe.setDhRecbto(new Date());
        mdfe.setChAcesso("43140691278119000186580010000000041000000040");
        mdfe.setNProt("123456789");
        
        ArrayList<Mdfe> l = new ArrayList<Mdfe>();
        l.add(mdfe);
        JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(l);

        try {
            JasperPrint jp = JasperFillManager.fillReport(jasper, parametros, jrbcds);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("MDF-E");
            jv.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
