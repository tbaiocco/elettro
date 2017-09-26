/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core;

import br.utils.Configuracoes;
import br.cte.core.assinador.CteAssinador;
import br.cte.core.assinador.CteAssinadorA3;
import br.cte.core.assinador.SocketFactoryDinamico;
import br.cte.model.Empresa;
import br.cte.model.InformacoesCertificado;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
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
    public Boolean setProprierts(Empresa empresa) {
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
            try {
                Provider p = new sun.security.pkcs11.SunPKCS11(Configuracoes.getInstance().getAppDir() + "certificados" + System.getProperty("file.separator") + "token.cfg");
                Security.addProvider(p);
                System.setProperty("javax.net.ssl.keyStoreType", "PKCS11");
                System.setProperty("javax.net.ssl.keyStore", "NONE");
                System.setProperty("javax.net.ssl.keyStoreProvider", "SunPKCS11-SmartCard");
                System.setProperty("javax.net.ssl.keyStorePassword", pass);
                java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");

                System.setProperty("javax.net.ssl.trustStoreType", "JKS");
                System.setProperty("javax.net.ssl.trustStore", Configuracoes.getInstance().getAppDir() + "certificados" + System.getProperty("file.separator") + "novoKeystore.jks");
                System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            /**
             * #03.03.14#
             * nao he necessario exportar certificado com toda a cadeia
             */
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
//        else {
//            System.setProperty("javax.net.ssl.trustStoreType", "JKS");
//            System.setProperty("javax.net.ssl.trustStore", Configuracoes.getInstance().getAppDir() + "certificados" + System.getProperty("file.separator") + "novoKeystore.jks");
//            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
//            java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
//            System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
//            System.setProperty("javax.net.ssl.keyStore", Configuracoes.getInstance().getAppDir()+ "certificados" + System.getProperty("file.separator") + empresa.getCertificado());
//            System.setProperty("javax.net.ssl.keyStorePassword", "" + pass);
//            //System.out.println("==>> "+pass);
//        }

        try {
//        	XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", new org.jcp.xml.dsig.internal.dom.XMLDSigRI());

            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(Configuracoes.getInstance().getAppDir() + "certificados" + System.getProperty("file.separator") + empresa.getCertificado()), pass.toCharArray());
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
//            // Create the KeyInfo containing the X509Data.
//            KeyInfoFactory kif = fac.getKeyInfoFactory();
//            List x509Content = new ArrayList();
//            // x509Content.add(cert.getSubjectX500Principal().getName());
//
//            x509Content.add(cert);
//            X509Data xd = kif.newX509Data(x509Content);
//            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

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
                CteAssinadorA3 as = new CteAssinadorA3();
                try {
                    as.assinar(a, pass, b, t);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    erro = "Erro ao assinar utilizando cert A3";
                    assinou = false;
                }
            } else {
                CteAssinador as = new CteAssinador();
                try {
                    as.assinar(a, Configuracoes.getInstance().getAppDir() + "certificados" + System.getProperty("file.separator") + empresa.getCertificado(), pass, b, t);
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
        InformacoesCertificado inf = null;
        String pass = "";
        try {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword("A" + chaveMestra);
            pass = textEncryptor.decrypt(empresa.getSenha());
            // System.out.println("-> "+pass);
        } catch (Exception e) {
            e.printStackTrace();
            erro = "Erro ao desincriptar senha do certificado";
        }

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
                //System.out.println(""+cert.getIssuerDN().getName() + " -> "+cert.getSubjectDN().getName()+" "+cert.get);
                String aux = cert.getSubjectX500Principal().getName();
                String[] s = aux.split("=");
                if (s.length > 1) {
                    inf.setEmitidoPara(s[1]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            String keystorefile = Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "certificados" + System.getProperty("file.separator") + empresa.getCertificado();
            System.out.println(keystorefile);
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
                String aux = cert.getSubjectX500Principal().getName();
                String[] s = aux.split("=");
                if (s.length > 1) {
                    inf.setEmitidoPara(s[1]);
                }
                inf.setCnpj(empresa.getCnpj());
                inf.setPath(keystorefile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inf;
    }

    public String getErro() {
        return erro;
    }
}
