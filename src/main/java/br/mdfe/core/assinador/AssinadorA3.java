/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core.assinador;

import br.utils.Arquivo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Enumeration;
import javax.xml.transform.OutputKeys;
import br.utils.Configuracoes;
import java.io.*;
import org.w3c.dom.Node;

/**
 *
 * @author Rafael
 */
public class AssinadorA3 {

    private static final String C14N_TRANSFORM_METHOD = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";

    public void assinar(String caminhoXml, String senha, String caminhoXmlNovo, String tipo) throws Exception {

        Arquivo a = new Arquivo(caminhoXml);
        a.abrirLeitura();
        String xml = a.ler();
        a.fecharArquivo();

        System.setProperty("org.jcp.xml.dsig.secureValidation", "false");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().parse(
                new ByteArrayInputStream(xml.getBytes()));

        doc.getDocumentElement().removeAttribute("xmlns:ns2");

        Node element = doc.getDocumentElement().getFirstChild();

        if (element.getNodeType() != Node.ELEMENT_NODE) {
            element = element.getNextSibling();
        }

        Element el = (Element) element;
        String id = el.getAttribute("Id");
        el.setIdAttribute("Id", true);

        // Create a DOM XMLSignatureFactory that will be used to   
        // generate the enveloped signature.   
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Create a Reference to the enveloped document (in this case,   
        // you are signing the whole document, so a URI of "" signifies   
        // that, and also specify the SHA1 digest algorithm and   
        // the ENVELOPED Transform.   
        ArrayList transformList = new ArrayList();
        TransformParameterSpec tps = null;
        Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED,
                tps);
        Transform c14NTransform = fac.newTransform(
                "http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);

        transformList.add(envelopedTransform);
        transformList.add(c14NTransform);


        Provider p = new sun.security.pkcs11.SunPKCS11(Configuracoes.getInstance().getAppDir() +System.getProperty("file.separator")+ "certificados"+System.getProperty("file.separator")+"token.cfg");
        Security.addProvider(p);
        KeyStore ks = KeyStore.getInstance("PKCS11");
        ks.load(null, senha.toCharArray());

        Enumeration aliasesEnum = ks.aliases();
        String alias = "";
        while (aliasesEnum.hasMoreElements()) {
            alias = (String) aliasesEnum.nextElement();

            if (ks.isKeyEntry(alias)) {
                //System.out.println(alias);
                break;
            }
        }
        
        // Load the KeyStore and get the signing key and certificate.   
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(senha.toCharArray()));
        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

        // Create the KeyInfo containing the X509Data.   
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        List x509Content = new ArrayList();
        x509Content.add(cert);
        X509Data xd = kif.newX509Data(x509Content);
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

        // Create a DOM XMLSignatureFactory that will be used to   
        // generate the enveloped signature.   

        Reference ref = fac.newReference("#" + id, fac.newDigestMethod(
                DigestMethod.SHA1, null), transformList, null, null);

        // Create the SignedInfo.   
        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
                CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        // Create the XMLSignature, but don't sign it yet.   
        XMLSignature signature = fac.newXMLSignature(si, ki);

        // Marshal, generate, and sign the enveloped signature.   
        // Create a DOMSignContext and specify the RSA PrivateKey and   
        // location of the resulting XMLSignature's parent element.  
        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());
        signature.sign(dsc);

        // Output the resulting document.   
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));

        Arquivo a2 = new Arquivo(caminhoXmlNovo);
        a2.abrirEscrita();
        String xmlAss = os.toString();
        xmlAss = xmlAss.replaceAll("\\<\\?xml(.+?)\\?\\>", "");
        a2.escreverLinhaSemNovaLinha(xmlAss);
        a2.fecharArquivo();
    }
    
    /**
     *
     * @param caminhoXml
     * @param senha
     * @param caminhoXmlNovo
     * @param tipo
     * @throws Exception
     */
    public void assinarOLD(String caminhoXml, String senha, String caminhoXmlNovo, String tipo) throws Exception {
        // tipo
        // '1' - NFE
        // '2' - CANCELAMENTO
        // '3' - INUTILIZACAO
        //
        String tag = "";
        if (tipo.equals("1")) {
            tag = "infNFe";
        } else if (tipo.equals("2")) {
            tag = "infCanc";
        } else if (tipo.equals("3")) {
            tag = "infInut";
        }else if (tipo.equals("4")) {
            tag = "infEvento";
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document docs = builder.parse(new File(caminhoXml));
        //Document docs = builder.parse(new File("c:/xml/430802017886010001735500000000010000030371-nfe.xml"));

        // Obtem elemento do documento a ser assinado, será criado uma
        // REFERENCE para o mesmo
        NodeList elements = docs.getElementsByTagName(tag);
        Element el = (Element) elements.item(0);
        String id = el.getAttribute("Id");
        //System.out.println(id);

        // Create a DOM XMLSignatureFactory that will be used to
        // generate the enveloped signature.
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", new org.jcp.xml.dsig.internal.dom.XMLDSigRI());

        // Create a Reference to the enveloped document (in this case,
        // you are signing the whole document, so a URI of "" signifies
        // that, and also specify the SHA1 digest algorithm and
        // the ENVELOPED Transform.
        ArrayList transformList = new ArrayList();
        TransformParameterSpec tps = null;
        Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps);
        Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps);
        transformList.add(envelopedTransform);
        transformList.add(c14NTransform);

        Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
        // Create the SignedInfo.
        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
                CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        // Load the KeyStore and get the signing key and certificate.
        Provider p = new sun.security.pkcs11.SunPKCS11(Configuracoes.getInstance().getAppDir() +System.getProperty("file.separator")+ "certificados"+System.getProperty("file.separator")+"token.cfg");
        Security.addProvider(p);
        KeyStore ks = KeyStore.getInstance("PKCS11");
        ks.load(null, senha.toCharArray());

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
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(senha.toCharArray()));

        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
        // Create the KeyInfo containing the X509Data.
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        List x509Content = new ArrayList();
        // x509Content.add(cert.getSubjectX500Principal().getName());

        x509Content.add(cert);
        X509Data xd = kif.newX509Data(x509Content);
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

        // Instantiate the document to be signed.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(caminhoXml));

        // Create a DOMSignContext and specify the RSA PrivateKey and
        // location of the resulting XMLSignature's parent element.
        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());

        // Create the XMLSignature, but don't sign it yet.
        XMLSignature signature = fac.newXMLSignature(si, ki);

        // Marshal, generate, and sign the enveloped signature.
        signature.sign(dsc);

        // Output the resulting document.
        OutputStream os = new FileOutputStream(caminhoXmlNovo);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.transform(new DOMSource(doc), new StreamResult(os));

        // Find Signature element.
        NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");

        if (nl.getLength() == 0) {
            throw new Exception("Cannot find Signature element");
        }
        // Create a DOMValidateContext and specify a KeySelector and document
        // context.
        DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(ks), nl.item(0));
        // Unmarshal the XMLSignature.
        XMLSignature signatures = fac.unmarshalXMLSignature(valContext);
        // Validate the XMLSignature.
        boolean coreValidity = signatures.validate(valContext);
        // Check core validation status.
        if (coreValidity == false) {
            System.err.println("Falha na Assinatura!");
        } else {
            System.out.println("Assinatura Correta!");
        }
    }
}
