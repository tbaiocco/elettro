/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Derli
 */
public class Formatador {

    private static Formatador instancia = new Formatador();

    private Formatador() {
    }

    public static Formatador getInstance() {
        return (instancia);
    }

    public String formataCepNumberString(String c) {
        String cep = "";
        c = Utils.getInstance().getDigitos(c);
        if ((c != null) && (c.length() == 8)) {
            cep = c.substring(0, 5) + "-" + c.substring(5, 8);
        } else {
            cep = c;
        }
        return cep;
    }

    public String formataFoneNumberString(String f) {
        String fone = "";
        f = Utils.getInstance().getDigitos(f);
        if ((f != null) && (f.length() == 10)) {
            fone = f.substring(0, 2) + "-" + f.substring(2, 6) + "-" + f.substring(6, 10);
        } else {
            fone = f;
        }
        return fone;
    }

    public String formataDataString(String f) {
        String data = "";
        f = Utils.getInstance().getDigitos(f);
        if ((f != null) && (f.length() == 8)) {
            data = f.substring(0, 2) + "/" + f.substring(2, 4) + "/" + f.substring(4, 8);
        } else {
            data = f;
        }
        return data;
    }

    public String formataCnpjNumberToString(String c) {
        String cnpj = "";
        c = Utils.getInstance().getDigitos(c);
        if ((c != null) && (c.length() == 14)) {
            cnpj = c.substring(0, 2) + "." + c.substring(2, 5) + "." + c.substring(5, 8) + "/" + c.substring(8, 12) + "-" + c.substring(12, 14);
        } else {
            cnpj = formataCpfNumberToString(c);
        }
        return cnpj;
    }

    public String formataCpfNumberToString(String c) {
        String cpf = "";
        c = Utils.getInstance().getDigitos(c);
        if ((c != null) && (c.length() == 11)) {
            cpf = c.substring(0, 3) + "." + c.substring(3, 6) + "." + c.substring(6, 9) + "-" + c.substring(9, 11);
        } else {
            cpf = "";
        }
        return cpf;
    }

    public String[] formataEndereco(String endereco) {
        String end[] = new String[3];
        String s[] = endereco.split("\\,");

        end[0] = s[0];//seta endereco

        s = s[1].split("\\(");
        end[1] = s[0];//seta nro

        if (s.length == 2) {
            end[2] = s[1].replaceAll("\\)", "");
        }
        return end;
    }

    public String ajustaTamanho(String s, int tam) {
        while (s.length() < tam) {
            s = "0" + s;
        }
        return s;
    }

    public String removeExessoEspaco(String texto) {
        String res = "";
        if (texto == null) {
            //
        } else {
            texto = texto.replace("|", " ");
            texto = texto.trim();
            String padrao = "\\s{2,}";
            Pattern regPat = Pattern.compile(padrao);
            Matcher matcher = regPat.matcher(texto);
            res = matcher.replaceAll(" ").trim();
        }
        return res;
    }

    public void formataTextoPipeToBreakLine() {
    }
}
