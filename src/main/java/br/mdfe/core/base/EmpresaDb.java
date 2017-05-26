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
import br.mdfe.model.Empresa;
import br.utils.Arquivo;
import br.utils.Configuracoes;
import br.utils.Formatador;
import br.utils.Validador;

/**
 *
 * @author DerliRiffel
 */
public class EmpresaDb {

    private final HashMap erros = new HashMap();

    /**
     * busca dados da empresa no arquivo XML
     * @param cnpj: xxxxxxxxxxxxxx ou xx.xxx.xxx/xxxx-xx
     * @return empresa
     */
    public Empresa getEmpresa(String cnpj) {
        if (cnpj.length() == 14) {
            cnpj = Formatador.getInstance().formataCnpjNumberToString(cnpj);
        }
        String fXml = Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "dados" + System.getProperty("file.separator") + "empresas.xml";
        File f = new File(fXml);

        //System.out.println("Lendo propriedades de EMISSOR em...: "+fXml);
        Empresa e;
        if (f.exists()) {
            XStream xstream = new XStream();
            xstream.alias("empresa", Empresa.class);
            Arquivo a = new Arquivo(fXml);
            a.abrirLeitura();
            String xml2 = a.ler();
            a.fecharArquivo();
            List listEmpresas = (List) xstream.fromXML(xml2);
            Iterator it = listEmpresas.iterator();
            while (it.hasNext()) {
                e = (Empresa) it.next();
                //System.out.println("Emis.:"+e.getRazaosocial()+" |UF.:"+e.getUf()+"/"+e.getcUf()+" |Cert.:"+e.getTipoCertificado());
                //System.out.println("CNPJ:"+e.getCnpj()+"=="+cnpj+"?"+(e.getCnpj().equals(cnpj)));
                if (e.getCnpj().equals(cnpj)) {
                    return e;
                } else {
                    erros.put("empresa", "Emitente (" + cnpj + ") não cadastrado no sistema de ENVIO DE NFe!!!");
                }
            }
        }
        return null;
    }

    /**
     * Salva os dados da empresa no xml
     * cnpj: xxxxxxxxxxxxxx ou xx.xxx.xxx/xxxx-xx
     * @param empresa
     * @return
     */
    public Boolean salvaEmpresa(Empresa empresa) {
        XStream xstream = new XStream();
        xstream.alias("empresa", Empresa.class);
        if (empresa.getCnpj().length() == 14) {
            empresa.setCnpj(Formatador.getInstance().formataCnpjNumberToString(empresa.getCnpj()));
        }
        if (!Validador.getInstance().cnpj(empresa.getCnpj())) {
            erros.put("Cnpj", "Cnpj informado não é válido");
            return false;
        } else {
            List empresas = new ArrayList();
            String fXml = Configuracoes.getInstance().getAppDir() + System.getProperty("file.separator") + "dados" + System.getProperty("file.separator") + "empresas.xml";
            File f = new File(fXml);
            if (!f.exists()) {
                empresas.add(empresa);
                String xml = xstream.toXML(empresas);
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
                List listEmpresas = (List) xstream.fromXML(xml2);
                Iterator it = listEmpresas.iterator();
                while (it.hasNext()) {
                    Empresa e = (Empresa) it.next();
                    //System.out.println(e.getCnpj() + " " + empresa.getCnpj());
                    if (e.getCnpj().equals(empresa.getCnpj())) {
                        empresas.add(empresa);
                    } else {
                        empresas.add(e);
                    }
                }
                if (!empresas.contains(empresa)) {
                    empresas.add(empresa);
                }
                String xml = xstream.toXML(empresas);
                Arquivo a2 = new Arquivo(fXml);
                a2.abrirEscrita();
                a2.escreverLinha(xml);
                a2.fecharArquivo();
                empresas.add(empresa);
                return true;
            }
        }
    }

    public HashMap getErros() {
        return erros;
    }
}
