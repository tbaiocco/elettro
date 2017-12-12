/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.base;

import com.thoughtworks.xstream.XStream;
import br.cte.model.WebServiceCte;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import br.utils.Arquivo;
import br.utils.Configuracoes;

/**
 *
 * @author DerliRiffel
 */
public class WebServiceCteDb {

    private HashMap erros = new HashMap();

    /**
     * busca dados de urls da uf
     * @param cUf: codido da uf
     * @return WebService
     */
    public WebServiceCte getWebServer(int cUf, int tpAmbiente) {
        String fXml = Configuracoes.getInstance().getAppDir() + "//dados//webservicesCte.xml";
        System.out.println("arq WS:"+fXml);
        WebServiceCte w = null;
        XStream xstream = new XStream();
        xstream.alias("webservice", WebServiceCte.class);
        Arquivo a = new Arquivo(fXml);
        a.abrirLeitura();
        String xml2 = a.ler();
        a.fecharArquivo();
        List list = (List) xstream.fromXML(xml2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            w = (WebServiceCte) it.next();
            if (w.getcUf() == cUf && w.getTpAmbiente() == tpAmbiente) {
                return w;
            }
        }
        return null;
    }

    public Boolean salvaUrl(WebServiceCte webservice) {
        List listWebservices = new ArrayList();
        String fXml = Configuracoes.getInstance().getAppDir() + "//dados//webservicesCte.xml";
        File f = new File(fXml);
        if (!f.exists()) {
            listWebservices.add(webservice);
            XStream xstream = new XStream();
            xstream.alias("webservice", WebServiceCte.class);
            String xml = xstream.toXML(listWebservices);
            Arquivo a = new Arquivo(fXml);
            a.abrirEscrita();
            a.escreverLinha(xml);
            a.fecharArquivo();
            return true;
        } else {
            XStream xstream = new XStream();
            xstream.alias("webservice", WebServiceCte.class);
            Arquivo a = new Arquivo(fXml);
            a.abrirLeitura();
            String xml2 = a.ler();
            a.fecharArquivo();
            List listEmpresas = (List) xstream.fromXML(xml2);
            Iterator it = listEmpresas.iterator();
            while (it.hasNext()) {
                WebServiceCte w = (WebServiceCte) it.next();
                if (w.getcUf() == webservice.getcUf() && w.getTpAmbiente() == webservice.getTpAmbiente()) {
                    listWebservices.add(webservice);
                } else {
                    listWebservices.add(w);
                }
            }
            if (!listWebservices.contains(webservice)) {
                listWebservices.add(webservice);
            }
            String xml = xstream.toXML(listWebservices);
            Arquivo a2 = new Arquivo(fXml);
            a2.abrirEscrita();
            a2.escreverLinha(xml);
            a2.fecharArquivo();
            listWebservices.add(webservice);
            return true;
        }
    }

    public HashMap getErros() {
        return erros;
    }
}
