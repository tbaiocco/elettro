/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author DerliRiffel
 */
public class Configuracoes {

    private Properties props = null;
    private static Configuracoes config = null;

    /**
     * Creates a new instance of Configuracoes
     */
    private Configuracoes() {
        props = new Properties();
        Properties baseProps = new Properties();
        try {
        	
	    	try {
	    		java.net.URL url = new java.net.URL(getClass().getProtectionDomain().getCodeSource().getLocation(), "../elettro.properties");
	    		if(Utils.doValida(url.getFile()) && new File(url.getFile()).exists()) {
		    		System.out.println("Usando baseCFG: "+url.getFile());
		    		InputStream is = url.openStream();
		    		baseProps.load(is);
		    	} else {
		        	InputStream is = getClass().getClassLoader().getResourceAsStream("elettro.properties");
		        	System.out.println("Usando arquivo elettro INTERNO do jar ");
		        	baseProps.load(is);
		    	}
	    	} catch (Exception e) {
	    		//faz nada...
	    		System.out.println("não leu o props...:"+e.getMessage());
	    		InputStream is = getClass().getClassLoader().getResourceAsStream("elettro.properties");
	        	System.out.println("Usando arquivo elettro INTERNO do jar ");
	        	baseProps.load(is);
	    	}
	    	
//            System.out.println("Reading... " + System.getProperty("user.dir") + "" + System.getProperty("file.separator") + "sistema.properties");
//            String x = System.getProperty("user.dir") + "" + System.getProperty("file.separator") + "sistema.properties";
            String x = baseProps.getProperty("props.location");
            System.out.println("Reading... " + x);
            props.load(new FileInputStream(x));
            System.out.println("Application Home..:" + props.getProperty("appDir"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Configuracoes getInstance() {
//        config = null;
        if (config == null) {
            config = new Configuracoes();
        }
        return config;
    }

    /**
     * Retorna o diretorio base do sistema
     *
     * @return
     */
    public String getAppDir() {
        return props.getProperty("appDir");
    }

    public String getDadosDir() {
        return props.getProperty("appDir") + "" + System.getProperty("file.separator") + "dados" + System.getProperty("file.separator");
    }

    public String getExtrairJks() {
        return props.getProperty("extrairJks");
    }

    public String getTmpDir() {
        String dir = props.getProperty("appDir") + System.getProperty("file.separator") + "mdfe" + System.getProperty("file.separator") + System.getProperty("file.separator") + "tmp" + System.getProperty("file.separator");
        File f = new File(dir);
        if (!f.exists()) {
            f.mkdirs();
        }
        return dir;
    }

    /**
     * Dir salvar notas fiscais emitdas
     *
     * @param cnpjEmitente
     * @param tp
     * @param dataEmissao
     * @return
     */
    public String getDistDir(String cnpjEmitente, String tp, Date dataEmissao) {
        SimpleDateFormat out = new SimpleDateFormat("yyyyMM");
        String dir = props.getProperty("appDir") + System.getProperty("file.separator") + "mdfe" + System.getProperty("file.separator") + cnpjEmitente + "_" + tp + System.getProperty("file.separator") + out.format(dataEmissao) + System.getProperty("file.separator");
        if (props.getProperty("mdfeDistDir") != null) {
            File f = new File(props.getProperty("mdfeDistDir"));
            if (!f.exists()) {
                if (f.mkdirs()) {
                    //
                }
            }
            if (f.exists()) {
                dir = props.getProperty("mdfeDistDir") + cnpjEmitente + "_" + tp + System.getProperty("file.separator") + out.format(dataEmissao) + System.getProperty("file.separator");
            }
        }
        File f = new File(dir);
        if (!f.exists()) {
            f.mkdirs();
        }
        return dir;
    }

    /**
     * dir salvar notas fiscal de entrada
     *
     * @param cnpj
     * @param chAcesso
     * @return
     */
    public String getDistMdfeEntradaDir(String cnpj, String chAcesso) {
        String dir = System.getProperty("user.dir") + System.getProperty("file.separator") + "mdfeentrada" + System.getProperty("file.separator");
        System.out.print(props.getProperty("mdfeEntradaOrganizar"));
        if (props.getProperty("mdfeEntradaOrganizar") != null && props.getProperty("mdfeEntradaOrganizar").trim().equals("1")) {
            String mod = chAcesso.substring(20, 22);
            String mes = "20" + chAcesso.substring(2, 6);
            if (props.getProperty("mdfeEntradaDistDir") != null) {
                File f = new File(props.getProperty("mdfeEntradaDistDir"));
                if (!f.exists()) {
                    if (f.mkdirs()) {
                        //
                    }
                }
                if (f.exists()) {
                    dir = props.getProperty("mdfeEntradaDistDir") + cnpj + "_" + mod + System.getProperty("file.separator") + mes + System.getProperty("file.separator");
                }
            }
            File f = new File(dir);
            if (!f.exists()) {
                f.mkdirs();
            }
            return dir;
        } else {
            if (props.getProperty("mdfeEntradaDistDir") != null) {
                dir = props.getProperty("mdfeEntradaDistDir") + System.getProperty("file.separator");
            }
            File f = new File(dir);
            if (!f.exists()) {
                f.mkdirs();
            }
            return dir;
        }
    }
}
