/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core.base;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import br.mdfe.model.WebService;
import br.utils.Arquivo;
import br.utils.Configuracoes;

/**
 *
 * @author DerliRiffel
 */
public class WebServiceDb {

    private final HashMap erros = new HashMap();

    /**
     * busca dados de urls da uf
     *
     * @param cUf: codido da uf
     * @return WebService
     */
    public WebService getWebServer(int cUf, int tpAmbiente) {

        String fXml = Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "dados" + System.getProperty("file.separator") + "webservices_mdfe.xml";
        //System.out.println("Lendo propriedades de WS em...: "+fXml);
        WebService w;
        XStream xstream = new XStream();
        xstream.alias("webservice", WebService.class);
        Arquivo a = new Arquivo(fXml);
        a.abrirLeitura();
        String xml2 = a.ler();
        a.fecharArquivo();
        List list = (List) xstream.fromXML(xml2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            w = (WebService) it.next();
            if (//w.getcUf() == cUf &&
                    w.getTpAmbiente() == tpAmbiente) {
                return w;
            }
        }
        return null;
    }

    public Boolean salvaUrl(WebService webservice) {
        List listWebservices = new ArrayList();
        String fXml = Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "dados" + System.getProperty("file.separator") + "webservices_mdfe.xml";
        File f = new File(fXml);
        if (!f.exists()) {
            listWebservices.add(webservice);
            XStream xstream = new XStream();
            xstream.alias("webservice", WebService.class);
            String xml = xstream.toXML(listWebservices);
            Arquivo a = new Arquivo(fXml);
            a.abrirEscrita();
            a.escreverLinha(xml);
            a.fecharArquivo();
            return true;
        } else {
            XStream xstream = new XStream();
            xstream.alias("webservice", WebService.class);
            Arquivo a = new Arquivo(fXml);
            a.abrirLeitura();
            String xml2 = a.ler();
            a.fecharArquivo();
            List listEmpresas = (List) xstream.fromXML(xml2);
            Iterator it = listEmpresas.iterator();
            while (it.hasNext()) {
                WebService w = (WebService) it.next();
                if (//w.getcUf() == webservice.getcUf() && 
                        w.getTpAmbiente() == webservice.getTpAmbiente()) {
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
