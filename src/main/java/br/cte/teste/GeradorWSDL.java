package br.cte.teste;

import br.cte.base.EmpresaDb;
import br.cte.core.CertDig;
import br.cte.model.Empresa;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.axis2.wsdl.WSDL2Java;
import br.utils.Configuracoes;

public class GeradorWSDL {

    public static void main(String[] args) {
//        String url = "https://cte.sefaz.rs.gov.br/ws/cterecepcaoevento/cterecepcaoevento.asmx?WSDL";
//
//        GeradorWSDL gerador = new GeradorWSDL();
//        gerador.setProperties();
//        gerador.geraWSDL(url, "CTeEventos");
    }

    public void geraWSDL(String wsdl, String pack) {
        try {
            WSDL2Java.main(new String[]{"-o", "src", "-p", "cte.core.webservices.service." + pack, "-uri", wsdl});
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
        System.setProperty("javax.net.ssl.keyStore", Configuracoes.getInstance().getAppDir() + "certificados" + System.getProperty("file.separator") + "montanha.pfx");
        System.setProperty("javax.net.ssl.keyStorePassword", "");
    }
}
