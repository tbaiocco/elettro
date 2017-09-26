/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

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
        try {
            //CTE_HOME=/home/teo/nuovo-e
            //System.out.println("Reading... " + System.getProperty("user.dir") + "" + System.getProperty("file.separator") + "sistema.properties");
            //System.getenv("CTE_HOME")
//            String x = System.getProperty("user.dir") + "" + System.getProperty("file.separator") + "sistema.properties";
            System.out.println("Reading... " + System.getenv("CTE_HOME") + "" + System.getProperty("file.separator") + "sistema.properties");
            String x = System.getenv("CTE_HOME") + "" + System.getProperty("file.separator") + "sistema.properties";
            //String x = "sistema.properties";
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
