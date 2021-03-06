/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.base;

import com.thoughtworks.xstream.XStream;
import br.cte.model.CteLote;
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
public class LoteCteDb {

    private HashMap erros = new HashMap();
    private int codigoLote = 0;

    /**
     * Salva dados do lote no xml
     * @param lote
     * @return
     */
    public Boolean salvaLote(CteLote lote) {
        XStream xstream = new XStream();
        xstream.alias("lote", CteLote.class);

        codigoLote = 1;
        List lotes = new ArrayList();
        String fXml = Configuracoes.getInstance().getAppDir() + "//dados//lotesCte.xml";
        File f = new File(fXml);
        if (!f.exists()) {
            lote.setCodigoLote(codigoLote);
            lotes.add(lote);
            String xml = xstream.toXML(lotes);
            Arquivo a = new Arquivo(fXml);
            a.abrirEscrita();
            a.escreverLinha(xml);
            a.fecharArquivo();
            return true;
        } else {
            Arquivo a = new Arquivo(fXml);
            a.abrirLeitura();
            String xml2 = a.ler();
            a.fecharArquivo();
            List listLotes = (List) xstream.fromXML(xml2);
            Iterator it = listLotes.iterator();
            int i = 1;
            int nReg = 50;//nro de registros a serem armazenados no xml
            while (it.hasNext()) {
                CteLote l = (CteLote) it.next();
                codigoLote = l.getCodigoLote();
                if (listLotes.size() > nReg) {
                    if (i > (listLotes.size() - nReg)) {
                        lotes.add(l);
                    }
                } else {
                    lotes.add(l);
                }
                i++;
            }
            codigoLote++;
            lote.setCodigoLote(codigoLote);
            lotes.add(lote);

            String xml = xstream.toXML(lotes);
            Arquivo a2 = new Arquivo(fXml);
            a2.abrirEscrita();
            a2.escreverLinha(xml);
            a2.fecharArquivo();
        }
        return false;
    }

    public HashMap getErros() {
        return erros;
    }

    public int getCodigoLote() {
        return codigoLote;
    }
}
