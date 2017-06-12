package br.mdfe.teste;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.axis2.wsdl.WSDL2Java;

import br.mdfe.utils.Configuracoes;

public class GeradorWSDL {

    public static void main(String[] args) {
        String status = "https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeStatusServico/MDFeStatusServico.asmx?WSDL";
        String consulta = "https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeConsulta/MDFeConsulta.asmx?WSDL";
        String MDFeRecepcaoEvento = "https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRecepcaoEvento/MDFeRecepcaoEvento.asmx?WSDL";
        String MDFeRetRecepcao = "https://mdfe-hml.sefaz.rs.gov.br/ws/MDFeRetRecepcao/MDFeRetRecepcao.asmx?WSDL";
        String MDFeRecepcao = "https://mdfe-hml.sefaz.rs.gov.br/ws/MDFerecepcao/MDFeRecepcao.asmx?WSDL";

        GeradorWSDL gerador = new GeradorWSDL();
        gerador.setProperties();
        gerador.geraWSDL(status, "MDFeStatusServico");
        gerador.geraWSDL(consulta, "MDFeConsulta");
        gerador.geraWSDL(MDFeRecepcao, "MDFeRecepcao");
        gerador.geraWSDL(MDFeRetRecepcao, "MDFeRetRecepcao");
        gerador.geraWSDL(MDFeRecepcaoEvento, "MDFeRecepcaoEvento");

    }

    public void geraWSDL(String wsdl, String pack) {
        try {
            WSDL2Java.main(new String[]{"-o", "src", "-p", "br.mdfe.core.webservices.service." + pack, "-uri", wsdl});
        } catch (Exception ex) {
            Logger.getLogger(GeradorWSDL.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("*** Geracao concluida ***");
    }

    public void setProperties() {
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStore", Configuracoes.getInstance().getAppDir() + "certificados" + System.getProperty("file.separator") + "novoKeystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.keyStore", Configuracoes.getInstance().getAppDir() + "certificados" + System.getProperty("file.separator") + "thomas2013.pfx");
        System.setProperty("javax.net.ssl.keyStorePassword", "th1714th");
    }
}
