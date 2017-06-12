/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.utils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author DerliRiffel
 */
public class GZip {

    public String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        System.out.println("String length : " + str.length());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        String outStr = out.toString("ISO-8859-1");
        System.out.println("Output String lenght : " + outStr.length());
        return outStr;
    }

    public String decompressUtf8(String str, String arquivo) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str.getBytes("UTF-8")));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        String outStr = "";
        String line;
        while ((line = bf.readLine()) != null) {
            outStr += line;
        }
        System.out.println("Output: " + outStr);
        Arquivo a = new Arquivo(arquivo);
        a.abrirEscrita();
        a.escreverLinhaSemNovaLinha(outStr);
        a.fecharArquivo();
        return outStr;
    }
    
    public String decompress(String str, String arquivo) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str.getBytes()));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis));
        String outStr = "";
        String line;
        while ((line = bf.readLine()) != null) {
            outStr += line;
        }
        //System.out.println("Output: " + outStr);
        Arquivo a = new Arquivo(arquivo);
        a.abrirEscrita();
        a.escreverLinhaSemNovaLinha(outStr);
        a.fecharArquivo();
        return outStr;
    }

    public static String getFileData(String filepath) throws FileNotFoundException,
            IOException {
        BufferedReader bf = new BufferedReader(new FileReader(filepath));
        String data = "";
        String line;
        while ((line = bf.readLine()) != null) {
            data += line;
        }
        return data;
    }

    public String Base64Binary(String dados){
        byte[] decoded = Base64.decodeBase64(dados.getBytes());
        return new String(decoded);
    } 
}
