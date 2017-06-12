/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core;

import br.mdfe.core.assinador.Assinador;
import br.mdfe.core.assinador.AssinadorA3;
import br.mdfe.core.assinador.SocketFactoryDinamico;
import br.mdfe.model.Empresa;
import br.mdfe.model.InformacoesCertificado;
import br.mdfe.utils.Configuracoes;
import br.mdfe.utils.Utils;
import br.mdfe.utils.ZipUtils;

import java.io.*;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.httpclient.protocol.Protocol;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author derli
 */
public class CertDig {

    private static CertDig instancia = new CertDig();
    private String chaveMestra = "liquid***###+++";
    private String erro;

    private CertDig() {
    }

    /**
     * Retorna uma instância da classe
     *
     * @return uma instância
     */
    public static CertDig getInstance() {
        return (instancia);
    }

    /**
     *
     * @param empresa
     * @return
     */
    public Boolean setProprerties(Empresa empresa) {
        String pass = "";
        try {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword("A" + chaveMestra);
            pass = textEncryptor.decrypt(empresa.getSenha());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (empresa.getTipoCertificado() == 3) {
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            System.setProperty("javax.net.debug", "all");
            try {
                Provider p = new sun.security.pkcs11.SunPKCS11(Configuracoes.getInstance().getAppDir() + "\\certificados\\token.cfg");
                Security.addProvider(p);

                System.setProperty("javax.net.ssl.keyStoreType", "PKCS11");
                System.setProperty("javax.net.ssl.keyStore", "NONE");
                //System.setProperty("javax.net.ssl.keyStoreProvider", "SunPKCS11-SmartCard");
                System.setProperty("javax.net.ssl.keyStorePassword", pass);
                java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
                System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");

                System.setProperty("javax.net.ssl.trustStoreType", "JKS");
                System.setProperty("javax.net.ssl.trustStore", Configuracoes.getInstance().getAppDir() + "\\certificados\\novoKeystore.jks");
                System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
//            int SSL_PORT = 443;
//            try {
//                Provider p = new sun.security.pkcs11.SunPKCS11(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + "token.cfg");
//                Security.addProvider(p);
//                char[] pin = pass.toCharArray();
//                KeyStore ks = KeyStore.getInstance("PKCS11", p);
//                ks.load(null, pin);
//
//                String alias = "";
//                Enumeration<String> aliasesEnum = ks.aliases();
//                while (aliasesEnum.hasMoreElements()) {
//                    alias = (String) aliasesEnum.nextElement();
//                    if (ks.isKeyEntry(alias)) {
//                        break;
//                    }
//                }
//                X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
//                PrivateKey privateKey = (PrivateKey) ks.getKey(alias, pass.toCharArray());
//                SocketFactoryDinamico socketFactoryDinamico = new SocketFactoryDinamico(certificate, privateKey);
//                socketFactoryDinamico.setFileCacerts(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + "novoKeystore.jks");
//
//                Protocol protocol = new Protocol("https", socketFactoryDinamico, SSL_PORT);
//                Protocol.registerProtocol("https", protocol);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
        } else {
            try {
                int SSL_PORT = 443;
                InputStream entrada = new FileInputStream(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + empresa.getCertificado());
                KeyStore ks = KeyStore.getInstance("pkcs12");
                try {
                    ks.load(entrada, pass.toCharArray());
                } catch (IOException e) {
                    throw new Exception("Senha do Certificado Digital esta incorreta ou Certificado inválido.");
                }
                String alias = "";
                Enumeration<String> aliasesEnum = ks.aliases();
                while (aliasesEnum.hasMoreElements()) {
                    alias = (String) aliasesEnum.nextElement();
                    if (ks.isKeyEntry(alias)) {
                        break;
                    }
                }
                X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
                PrivateKey privateKey = (PrivateKey) ks.getKey(alias, pass.toCharArray());
                SocketFactoryDinamico socketFactoryDinamico = new SocketFactoryDinamico(certificate, privateKey);
                socketFactoryDinamico.setFileCacerts(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + "novoKeystore.jks");

                Protocol protocol = new Protocol("https", socketFactoryDinamico, SSL_PORT);
                Protocol.registerProtocol("https", protocol);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public Boolean setProprertiesOld(Empresa empresa) {
        String pass = "";
        try {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword("A" + chaveMestra);
            pass = textEncryptor.decrypt(empresa.getSenha());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (empresa.getTipoCertificado() == 3) {
            //acceptSSL();
            //System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            //System.setProperty("javax.net.debug", "all");
            try {

                Provider p = new sun.security.pkcs11.SunPKCS11(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + "token.cfg");
                int x = Security.addProvider(p);
                System.out.println("retorno provider" + x);

                System.setProperty("javax.net.ssl.keyStoreType", "PKCS11");
                System.setProperty("javax.net.ssl.keyStore", "NONE");
                //System.setProperty("javax.net.ssl.keyStoreProvider", "SunPKCS11-SmartCard");
                System.setProperty("javax.net.ssl.keyStorePassword", pass);
                java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");

                System.setProperty("javax.net.ssl.trustStoreType", "JKS");
                System.setProperty("javax.net.ssl.trustStore", Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + "novoKeystore.jks");
                System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.setProperty("javax.net.ssl.trustStoreType", "JKS");
            System.setProperty("javax.net.ssl.trustStore", Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + "novoKeystore.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
            System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
            System.setProperty("javax.net.ssl.keyStore", Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + empresa.getCertificado());
            System.setProperty("javax.net.ssl.keyStorePassword", "" + pass);
            // System.out.println(pass);
        }
        return true;
    }

    public Boolean removeProvider() {
        Provider p = new sun.security.pkcs11.SunPKCS11(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + "token.cfg");
        Security.removeProvider(p.getName());
        System.out.println("provider removido!!");
        return true;
    }

    /**
     * *
     *
     * @param a
     * @param b
     * @param t
     * @param empresa
     * @return
     */
    public Boolean Ass(String a, String b, String t, Empresa empresa) {
        Boolean assinou = true;
        String pass = "";
        try {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword("A" + chaveMestra);
            pass = textEncryptor.decrypt(empresa.getSenha());
        } catch (Exception e) {
            e.printStackTrace();
            assinou = false;
            erro = "Erro ao desincriptar senha do certificado";
        }

        if (assinou) {
            if (empresa.getTipoCertificado() == 3) {
                AssinadorA3 as = new AssinadorA3();
                try {
                    as.assinar(a, pass, b, t);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    erro = "Erro ao assinar utilizando cert A3";
                    assinou = false;
                }
            } else {
                Assinador as = new Assinador();
                try {
                    as.assinar(a, Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + empresa.getCertificado(), pass, b, t);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    erro = "Erro ao assinar utilizando cert A3";
                    assinou = false;
                }
            }
        }
        return assinou;
    }

    public InformacoesCertificado getInformacoes(Empresa empresa) {
        //tenta extrar jks caso seja possivel
        extrairJks();

        InformacoesCertificado inf = null;
        String pass = "";
        try {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword("A" + chaveMestra);
            pass = textEncryptor.decrypt(empresa.getSenha());
        } catch (Exception e) {
            e.printStackTrace();
            erro = "Erro ao desincriptar senha do certificado";
        }
        //System.out.println(pass);
        if (empresa.getTipoCertificado() == 3) {
            try {
                // Load the KeyStore and get the signing key and certificate.
                Provider p = new sun.security.pkcs11.SunPKCS11(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + "token.cfg");
                Security.addProvider(p);
                KeyStore ks = KeyStore.getInstance("PKCS11");
                ks.load(null, pass.toCharArray());

                Enumeration aliasesEnum = ks.aliases();
                String alias = "";
                while (aliasesEnum.hasMoreElements()) {
                    alias = (String) aliasesEnum.nextElement();
                    if (ks.isKeyEntry(alias)) {
                        //System.out.println(alias);
                        break;
                    }
                }
                //
                KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(pass.toCharArray()));

                X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

                inf = new InformacoesCertificado();
                inf.setValidoDe(cert.getNotBefore());
                inf.setValidoAte(cert.getNotAfter());
                inf.setEmissor(cert.getIssuerDN().getName());
                if (cert.getSerialNumber() != null) {
                    BigInteger n = cert.getSerialNumber();
                    String hex =Utils.getInstance().zeroFill(n.toString(16),16);
                    inf.setSerialNumber(hex.toUpperCase());
                }
                //inf.setSerialNumber(cert.getSerialNumber());
                //System.out.println(""+cert.getIssuerDN().getName() + " -> "+cert.getSubjectDN().getName()+" "+cert.get);
                String aux = cert.getSubjectX500Principal().getName();
                String[] s = aux.split("=");
                if (s.length > 1) {
                    inf.setEmitidoPara(s[1]);
                }

                this.removeProvider();

            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("ERro ao ler certificao A3 => " + e.getLocalizedMessage());
                return null;
            }
        } else {

            String keystorefile = Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + empresa.getCertificado();
            File f = new File(keystorefile);
            if (!f.exists()) {
                return null;
            }
            try {
                KeyStore ks = KeyStore.getInstance("PKCS12");
                ks.load(new FileInputStream(keystorefile), pass.toCharArray());
                Enumeration aliasesEnum = ks.aliases();
                String alias = "";
                while (aliasesEnum.hasMoreElements()) {
                    alias = (String) aliasesEnum.nextElement();

                    if (ks.isKeyEntry(alias)) {
                        //System.out.println("=>>> "+alias);
                        break;
                    }
                }
                KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(pass.toCharArray()));
                X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

                inf = new InformacoesCertificado();
                inf.setValidoDe(cert.getNotBefore());
                inf.setValidoAte(cert.getNotAfter());
                inf.setEmissor(cert.getIssuerDN().getName());                
                if (cert.getSerialNumber() != null) {
                   BigInteger n = cert.getSerialNumber();
                    String hex =Utils.getInstance().zeroFill(n.toString(16),16);
                    inf.setSerialNumber(hex.toUpperCase());
                }
                String aux = cert.getSubjectX500Principal().getName();
                String[] s = aux.split("=");
                if (s.length > 1) {
                    inf.setEmitidoPara(s[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inf;
    }

    public static void acceptSSL() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void extrairJks() {

        if (Configuracoes.getInstance().getExtrairJks() == null || Configuracoes.getInstance().getExtrairJks().equals("s")) {
            File fileOut = null;
            try {
                InputStream in = this.getClass().getResourceAsStream("/br/nfe/update/novoKeystore.jks");
                fileOut = new File(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + "novoKeystore.jks");
                //System.out.println("extraindo jks... para .. " + fileOut.getAbsolutePath());
                OutputStream out = new FileOutputStream(fileOut);
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                InputStream in = this.getClass().getResourceAsStream("/br/nfe/update/webservices.xml");
                fileOut = new File(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "dados" + System.getProperty("file.separator") + "webservices.xml");
                //System.out.println("extraindo jks... para .. " + fileOut.getAbsolutePath());
                OutputStream out = new FileOutputStream(fileOut);
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                InputStream in = this.getClass().getResourceAsStream("/br/nfe/update/webservices.xml");
                fileOut = new File(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "dados" + System.getProperty("file.separator") + "webservices.xml");
                //System.out.println("extraindo jks... para .. " + fileOut.getAbsolutePath());
                OutputStream out = new FileOutputStream(fileOut);
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                InputStream in = this.getClass().getResourceAsStream("/br/nfe/update/schemas.zip");
                fileOut = new File(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "dados" + System.getProperty("file.separator") + "schemas.zip");
                OutputStream out = new FileOutputStream(fileOut);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();

                ZipUtils.extract(fileOut, new File(Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "schemas" + System.getProperty("file.separator")));
                if (!fileOut.delete()) {
                    fileOut.deleteOnExit();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public String getErro() {
        return erro;
    }
}
