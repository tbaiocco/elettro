/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core.validadores;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import br.utils.Configuracoes;

/**
 *
 * @author derli
 */
public class XmlEnvioCte implements ErrorHandler {

    private String xmlInput;
    private String xsdInput;
    private Boolean valido = false;
    private HashMap erros = new HashMap();
    private int seq = 0;

    /**
     *
     * @param xml
     * @param tipo 1 - cte envio
     * @return true-arquivo ok 
     */
    public Boolean valida(String xml, int tipo, String versao) {
            valido = true;
            this.xmlInput = xml;
            this.xsdInput = Configuracoes.getInstance().getAppDir() + "schemasCte"+System.getProperty("file.separator")+"enviCTe_v2.00.xsd";
            try {
                this.validate();
                if (valido) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                System.out.println("File '" + this.getXmlInput() + "' validate fail...");
                ex.printStackTrace();
            }
       
        return false;
    }
    /*
    public XmlValidate(String xmlInput, String xsdInput) {

    }
     */

    private String getXmlInput() {
        return xmlInput;
    }
    /*
    private void setXmlInput(String xmlInput) {
    this.xmlInput = xmlInput;
    }

    private String getXsdInput() {
    return xsdInput;
    }

    private void setXsdInput(String xsdInput) {
    this.xsdInput = xsdInput;
    }*/

    private void validate() throws Exception {
        try {

            //Setting Xerces-J parser because the standard parser not recognize <import ...>
            System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
                    "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");

            // Parse an XML document em um DOM tree.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document document = parser.parse(new File(xmlInput));

            // Cria um SchemaFactory capaz de compreender WXS schemas.
            SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // carrega um WXS schema, representada por uma instacia Schema.
            Source schemaFile = new StreamSource(new File(xsdInput));
            Schema schema = schemaFactory.newSchema(schemaFile);

            // Cria um objeto Validator que pode ser usado para validar
            // uma instancia document.
            Validator validator = schema.newValidator();
            validator.setErrorHandler(this);

            // Valida o Dom tree
            validator.validate(new DOMSource(document));
        } catch (ParserConfigurationException e) {
            throw new Exception(e.getMessage());
        } catch (SAXException e) {
            throw new Exception(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void error(SAXParseException exception) throws SAXException {
        seq++;
        erros.put("xml-" + seq, exception.getMessage());
        System.out.println("ERRO: " + exception.getMessage());
        valido = false;
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        //System.out.println("ERRO FATAL: " + exception.getMessage());
        seq++;
        erros.put("xml-" + seq, exception.getMessage());
        valido = false;
    }

    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("AVISO: " + exception.getMessage());
        //retorno = "AVISO: " + exception.getMessage();
        valido = false;
    }

    public HashMap getErros() {
        return erros;
    }
}
