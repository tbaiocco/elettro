/*
 * Validador.java
 *
 * Created on 28 de Janeiro de 2008, 09:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Servidor
 */
public class Validador {

    private static Validador instancia = new Validador();

    /** Creates a new instance of Validador */
    private Validador() {
    }

    public static Validador getInstance() {
        if (instancia == null) {
            instancia = new Validador();
        }
        return instancia;
    }

    public boolean cnpj(String xCGC) {
        try {
            //Testa se o CGC é válido ou não
            int d1, d4, xx, nCount, fator, resto, digito1, digito2;
            String Check, s_aux;
            String Separadores = "/-.";
            d1 = 0;
            d4 = 0;
            xx = 0;
            for (nCount = 0; nCount < xCGC.length() - 2; nCount++) {
                s_aux = xCGC.substring(nCount, nCount + 1);
                if (Separadores.indexOf(s_aux) == -1) {
                    if (xx < 4) {
                        fator = 5 - xx;
                    } else {
                        fator = 13 - xx;
                    }

                    d1 = d1 + Integer.valueOf(s_aux).intValue() * fator;
                    if (xx < 5) {
                        fator = 6 - xx;
                    } else {
                        fator = 14 - xx;
                    }
                    d4 += Integer.valueOf(s_aux).intValue() * fator;
                    xx++;
                }
                ;
            }
            resto = (d1 % 11);
            if (resto < 2) {
                digito1 = 0;
            } else {
                digito1 = 11 - resto;
            }

            d4 = d4 + 2 * digito1;
            resto = (d4 % 11);
            if (resto < 2) {
                digito2 = 0;
            } else {
                digito2 = 11 - resto;
            }

            Check = String.valueOf(digito1) + String.valueOf(digito2);
            //System.out.println (Check);
            //System.out.println (xCGC.substring(xCGC.length()-2, xCGC.length() ));

            if (Check.compareTo(xCGC.substring(xCGC.length() - 2, xCGC.length())) != 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String formataCnpj(String cnpj) {
        String novo = "";
        for (int i = 0; i < cnpj.length(); i++) {
            if (i == 2) {
                novo += ".";
            } else if (i == 5) {
                novo += ".";
            } else if (i == 8) {
                novo += "/";
            } else if (i == 12) {
                novo += "-";
            }
            novo += cnpj.charAt(i);
        }
        return novo;
    }

    public boolean cpf(String strCpf) {
        if(strCpf==null){
            return false;
        }
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String Separadores = "/-.";
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;
        String straux = "";
        //-- descatar separadores
        for (int nCount = 0; nCount < strCpf.length(); nCount++) {
            if (Separadores.indexOf(strCpf.substring(nCount, nCount + 1)) == -1) {
                straux += strCpf.substring(nCount, nCount + 1);
            }
        }
        strCpf = straux;
        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {

            digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;

            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;

        }

        //Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        //Digito verificador do CPF que está sendo validado.
        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        return nDigVerific.equals(nDigResult);
    }

    public Boolean validaEmail(String email) {
        if(email==null){
            return false;
        }
        email = email.toLowerCase();
        //Set the email pattern string
        Pattern p = Pattern.compile("[a-zA-Z0-9]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\\.+[a-z]{2,4}");
        //Match the given string with the pattern
        Matcher m = p.matcher(email);
        //check whether match is found
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }
}
