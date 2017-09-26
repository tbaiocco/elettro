/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.teste;

import br.cte.base.EmpresaDb;
import br.cte.base.WebServiceCteDb;
import br.cte.bean.BeanDacte;
import br.cte.model.Cte;
import br.cte.model.CteCancelamento;
import br.cte.model.CteConsulta;
import br.cte.model.CteEmitente;
import br.cte.model.CteEvento;
import br.cte.model.CteInfCorrecao;
import br.cte.model.CteInutilizacao;
import br.cte.model.CteLote;
import br.cte.model.CteRetornoEnvioLote;
import br.cte.model.CteStatusServico;
import br.cte.model.Empresa;
import br.cte.model.InformacoesCertificado;
import br.cte.model.WebServiceCte;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jasypt.util.text.BasicTextEncryptor;
import br.servicos.CteServicos;
import br.utils.Configuracoes;

/**
 *
 * @author teo
 */
public class TestaCTe {

    public static void main(String args[]) {
        try {
            //getProperties();
            //getEnv();
            //getInfCertificado();
            //consultaStatusServico();
            //cadastraEmpresa();

            enviaCTe();
            //cancelaCTe();
            //cartaCorrecaoCTe();
           // EventoRegMultimodal();
        } catch (Exception ex) {
            System.out.println("entao fodeu...");
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

    public static void cadastraEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setCnpj("68.751.486/0001-33");
        empresa.setIe("3820007824");
        empresa.setRazaosocial("GM SUL EXPRESS LTDA");
        empresa.setTipoCertificado(1);
        empresa.setCertificado("gmsul.pfx");

        String senha = "141100";
        BasicTextEncryptor textEncryptorNew = new BasicTextEncryptor();
        String senhaMestra = "liquid***###+++";
        textEncryptorNew.setPassword("A" + senhaMestra);
        empresa.setSenha(textEncryptorNew.encrypt(senha));

        empresa.setcUf(43);
        empresa.setMun("GRAVATAI");
        empresa.setcMun(430920);
        empresa.setUf("RS");

        empresa.setDbHost("//127.0.0.1:5432");
        empresa.setDbPort(5432);
        empresa.setDbURL("jdbc:postgresql://127.0.0.1:5432/sistema");
        empresa.setDbDriver("org.postgresql.Driver");
        empresa.setDbUser("exito");
        empresa.setDbPass("nalthus");

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

    public static void cadastraWebService() {
        WebServiceCte rs = new WebServiceCte();
        rs.setcUf(43);
        rs.setUf("RS");
        rs.setVersaoPadrao("1.04");
        /*   rs.setTpAmbiente(1);
         rs.setUrlRecepcao("https://cte.sefaz.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx?WSDL");
         rs.setUrlRetRecepcao("https://cte.sefaz.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx?WSDL");
         rs.setUrlStatusServico("https://cte.sefaz.rs.gov.br/ws/ctestatusservico/CteStatusServico.asmx?WSDL");
         rs.setUrlCancelamento("https://cte.sefaz.rs.gov.br/ws/ctecancelamento/ctecancelamento.asmx?WSDL");
         rs.setUrlInutilizacao("https://cte.sefaz.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx?WSDL");
         rs.setUrlConsultaProtocolo("https://cte.sefaz.rs.gov.br/ws/cteconsulta/CteConsulta.asmx?WSDL");
         */
        rs.setTpAmbiente(2);
        rs.setUrlRecepcao("https://homologacao.cte.sefaz.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx?WSDL");
        rs.setUrlRetRecepcao("https://homologacao.cte.sefaz.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx?WSDL");
        rs.setUrlStatusServico("https://homologacao.cte.sefaz.rs.gov.br/ws/ctestatusservico/CteStatusServico.asmx?WSDL");
        rs.setUrlCancelamento("https://homologacao.cte.sefaz.rs.gov.br/ws/ctecancelamento/ctecancelamento.asmx?WSDL");
        rs.setUrlInutilizacao("https://homologacao.cte.sefaz.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx?WSDL");
        rs.setUrlConsultaProtocolo("https://homologacao.cte.sefaz.rs.gov.br/ws/cteconsulta/CteConsulta.asmx?WSDL");

        WebServiceCteDb wDb = new WebServiceCteDb();
        if (wDb.salvaUrl(rs)) {
            System.out.println("Uf: foi salva com sucesso.");
            System.out.println("\n\n");

            WebServiceCte w = wDb.getWebServer(rs.getcUf(), rs.getTpAmbiente());
            System.out.println("Uf salva no xml: " + w.getUf() + " ambiente: " + w.getTpAmbiente());
        } else {
            System.out.println("UF " + rs.getUf() + ", não pode ser salva");
        }
    }

    public static void EventoRegMultimodal() {

        CteServicos servico = new CteServicos();

        CteEvento cte = new CteEvento();
        cte.setCOrgao(43);
        cte.setCNPJ("04882329000184");
        cte.setTpAmb(2);
        cte.setChCTe("43140304882329000184570000000000051000000050");
        cte.setTpEvento(110160);
        cte.setNProtAprovacaoCte("143140000061796");
        cte.setDescEvento("Registro Multimodal");
        cte.setNSeqEvento(2);
        cte.setDhEvento(new Date());
        cte.setXRegistro("Texto livre de Informacoes sobre o tipo de documento utlizado");
        CteEvento retorno = servico.eventoCte(cte.getTpAmb(), cte.getCNPJ(), cte);
        if (retorno != null) {
            System.out.println("Retorno Registro Multimodal 2.00 ok!!!\n"
                    + " Protocolo: " + retorno.getNProtRegEvento()+ "\n"
                    + " Data: " + retorno.getDhRegEvento()+ "\n"
                    + " cstat: " + retorno.getCStat() + " \n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao buscar cancelamento 2.00 ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }
    
    public static void cartaCorrecaoCTe() {

        CteServicos servico = new CteServicos();

        CteEvento cte = new CteEvento();
        cte.setCOrgao(43);
        cte.setCNPJ("04882329000184");
        cte.setTpAmb(2);
        cte.setChCTe("43140304882329000184570000000000051000000050");
        cte.setTpEvento(110110);
        cte.setNProtAprovacaoCte("143140000061796");
        cte.setDescEvento("Carta de Correcao");
        cte.setNSeqEvento(2);
        cte.setDhEvento(new Date());
        //campo alterado
        CteInfCorrecao c = new CteInfCorrecao();
        c.setGrupoAlterado("rem");
        c.setCampoAlterado("fone");
        c.setValorAlterado("14991001000");
        ArrayList<CteInfCorrecao> l = new ArrayList<CteInfCorrecao>();
        l.add(c);
        cte.setInfCorrecao(l);
        cte.setXCondUso("A Carta de Correcao e disciplinada pelo Art. 58-B do CONVENIO/SINIEF 06/89: Fica permitida a utilizacao de carta de correcao, para regularizacao de erro ocorrido na emissao de documentos fiscais relativos a prestacao de servico de transporte, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da prestacao;II - a correcao de dados cadastrais que implique mudanca do emitente, tomador, remetente ou do destinatario;III - a data de emissao ou de saida.");

        CteEvento retorno = servico.eventoCte(cte.getTpAmb(), cte.getCNPJ(), cte);
        if (retorno != null) {
            System.out.println("Retorno carta correcao 2.00 ok!!!\n"
                    + " Protocolo: " + retorno.getNProtRegEvento()+ "\n"
                    + " Data: " + retorno.getDhRegEvento()+ "\n"
                    + " cstat: " + retorno.getCStat() + " \n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao buscar cancelamento 2.00 ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void cancelaCTe() {

        CteServicos servico = new CteServicos();

        CteEvento cte = new CteEvento();
        cte.setCOrgao(43);
        cte.setCNPJ("04882329000184");
        cte.setTpAmb(2);
        cte.setXJust("Justificativa de cancelamento");
        cte.setChCTe("43140304882329000184570000000000041000000044");
        cte.setTpEvento(110111);
        cte.setNProtAprovacaoCte("143140000053119");
        cte.setDescEvento("Cancelamento");
        cte.setNSeqEvento(1);
        cte.setDhEvento(new Date());

        CteEvento retorno = servico.eventoCte(cte.getTpAmb(), cte.getCNPJ(), cte);
        if (retorno != null) {
            System.out.println("Retorno cancelamento 2.00 ok!!!\n"
                    + " Protocolo: " + retorno.getNProtRegEvento()+ "\n"
                    + " Data: " + retorno.getDhRegEvento()+ "\n"
                    + " cstat: " + retorno.getCStat() + " \n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao buscar cancelamento 2.00 ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void consultaCTe() {
        CteServicos servico = new CteServicos();

        CteConsulta retorno = servico.consultaCte("43100792521475000141570000000000030000000030", 2, "87.016.747/0001-16");
        if (retorno != null) {
            System.out.println("Retorno 1.04 ok!!!\n"
                    + " Protocolo: " + retorno.getNProt() + "\n"
                    + " Data: " + retorno.getDhRecbto() + "\n"
                    + " cstat: " + retorno.getCStat() + "\n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao buscar status do servico 1.04 ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void getInfCertificado() {
        CteServicos servico = new CteServicos();

        InformacoesCertificado retorno = servico.getInformacoesCertificado("92.521.475/0001-41");
        //CteStatusServico retorno = servico.consultaStatusServico(2, "91.665.554/0001-63");
        if (retorno != null) {
            System.out.println("Retorno 1.04 ok!!!\n"
                    + " por: " + retorno.getEmissor() + "ms\n"
                    + " para: " + retorno.getEmitidoPara() + "\n"
                    + " ate: " + retorno.getValidoAte());
        } else {
            System.out.println("Erro ao buscar status do servico 1.04 ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void consultaRetornoEnvioCTe() {
        cteExemplo ex = new cteExemplo();
        Cte cte = ex.getCte(2);
        CteServicos servico = new CteServicos();

        CteRetornoEnvioLote retorno = servico.retornoEnvioCte("431000001112098", cte);
        if (retorno != null) {
            System.out.println("Retorno 1.04 ok!!!\n"
                    + " Protocolo: " + retorno.getNProt() + "\n"
                    + " Data: " + retorno.getDhRecbto() + "\n"
                    + " cstat: " + retorno.getCStat() + "\n"
                    + " Motivo: " + retorno.getXMotivo());

        } else {
            System.out.println("Erro ao buscar status do servico 1.04 ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void consultaStatusServico() {
        CteServicos servico = new CteServicos();

        CteStatusServico retorno = servico.consultaStatusServico(2, "04.882.329/0001-84");
        if (retorno != null) {
            System.out.println("Retorno 2.00 ok!!!\n"
                    + " Tempo: " + retorno.getTMed() + "ms\n"
                    + " Data: " + retorno.getDhRecbto() + "\n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao buscar status do servico 2.00 ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void inutilizaNumeracaoCTe() {
        CteServicos servico = new CteServicos();
        CteInutilizacao retorno = servico.inutilizaNumeracaoCte(2, "87.016.747/0001-16", "10", "57", "0", "106", "106", "TESTE DE INUTILIZACAO");
        if (retorno != null) {
            System.out.println("Retorno 1.04 ok!!!\n"
                    + " Protocolo: " + retorno.getNProt() + "\n"
                    + " Data: " + retorno.getDhRecbto() + "\n"
                    + " cstat: " + retorno.getCStat() + "\n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao buscar INUTILIZACAO 1.04 ... consulte hashMap");
            showErrors(servico.getErros());
        }
    }

    public static void enviaCTe() {

        cteExemplo ex = new cteExemplo();
        Cte cte = ex.getCte(7);
        CteServicos servico = new CteServicos();

        CteLote retorno = servico.enviaCte(cte);
        if (retorno != null) {
            System.out.println("Retorno 2.00 ok!!!\n"
                    + " CHAVE: " + retorno.getCte().getChaveAcesso() + "\n"
                    + " Recibo: " + retorno.getNRec() + "\n"
                    + " Data: " + retorno.getData() + "\n"
                    + " cstat: " + retorno.getCStat() + " \n"
                    + " Motivo: " + retorno.getXMotivo());
        } else {
            System.out.println("Erro ao transmitir cte 2.00 ... consulte hashMap");
            showErrors(servico.getErros());
        }

        try {
            System.out.println("aguardando 3s para consultar recibo!!!");
            Thread.sleep(3000);
        } catch (InterruptedException exx) {
            exx.printStackTrace();
        }

        if (retorno.getNRec() != null) {
            CteRetornoEnvioLote retorno2 = servico.retornoEnvioCte(retorno.getNRec(), cte);
            if (retorno2 != null) {
                System.out.println("Retorno 2.00 ok!!!\n"
                        + " Protocolo: " + retorno2.getNProt() + "\n"
                        + " Data: " + retorno2.getDhRecbto() + "\n"
                        + " cstat: " + retorno2.getCStat() + "\n"
                        + " Motivo: " + retorno2.getXMotivo());
            } else {
                System.out.println("Erro ao buscar status do servico 2.00 ... consulte hashMap");
                showErrors(servico.getErros());
            }
        }
    }

    public static void imprime() {

        cteExemplo ex = new cteExemplo();
        Cte cte = ex.getCte(2);

        cte.setChaveAcesso("43100792521475000141570000000000030000000030");
        cte.setProtocolo("1234567890");

        CteServicos servico = new CteServicos();
        BeanDacte dacte = servico.getDacte(cte);

        ArrayList<BeanDacte> list = new ArrayList<BeanDacte>();
        list.add(dacte);

        HashMap parametros = new HashMap();
        String img = Configuracoes.getInstance().getAppDir() + "/logo.png";
        File f = new File(img);
        if (f.exists()) {
            parametros.put("logo", img);
        }
        String pathJasper = Configuracoes.getInstance().getAppDir() + "/relatorios/";
        JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(list);

        String arquivo = "dacte_teste";
        String path = pathJasper + arquivo + ".jasper";
        try {
            JasperPrint jp = JasperFillManager.fillReport(path, parametros, jrbcds);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle("Relatório");
            jv.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public static void getProperties() {

        // Get all system properties
        Properties props = System.getProperties();
        // Enumerate all system properties
        Enumeration en = props.propertyNames();
        for (; en.hasMoreElements();) {
            // Get property name
            String propName = (String) en.nextElement();

            // Get property value
            String propValue = (String) props.get(propName);
            System.out.println(propName + "=" + propValue);
        }

    }

    public static void getEnv() {

        Map keys = System.getenv();
        //Set vars = keys.keySet();
        Iterator it = keys.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = (String) keys.get(key);

            System.out.println(key + " = " + value);
        }
    }

}
