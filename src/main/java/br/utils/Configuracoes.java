/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import br.mdfe.utils.Utils;

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

    public String getAppDirTmp() {
        File f = new File(this.getAppDir() + System.getProperty("file.separator") +"tmp");
        if (!f.exists()) {
            f.mkdir();
        }
        if (f.exists()) {
            return f.getAbsolutePath()+ System.getProperty("file.separator");
        } else {
            return this.getAppDir()+ System.getProperty("file.separator");
        }
    }
}
