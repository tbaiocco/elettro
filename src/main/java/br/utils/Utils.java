/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DerliRiffel
 */
public class Utils {

    private static Utils instancia = new Utils();

    /**
     * Creates a new instance
     */
    private Utils() {
    }

    /**
     * Retorna uma inst‚ncia
     *
     * @return uma inst‚ncia
     */
    public static Utils getInstance() {
        return (instancia);
    }

    public String convertHoraToString(Date data) {
        SimpleDateFormat out = new SimpleDateFormat("HH:mm");
        String result = out.format(data);
        return result;
    }

    public String convertDataToString(Date data) {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        String result = out.format(data);
        return result;
    }

    public String removeAcentuacao(String text) {
        if (text == null) {
            return "";
        }
        text = text.trim();
        text = text.toUpperCase();
        Pattern[] PATTERNS = null;
        String[] REPLACES = {"a", "e", "i", "o", "u", "c", "A", "E", "I", "O", "U", "C", " ", "", "CA", "C", "C", "1_o"};

        PATTERNS = new Pattern[REPLACES.length];
        PATTERNS[0] = Pattern.compile("[‚„·‡‰]");
        PATTERNS[1] = Pattern.compile("[ÈËÍÎ&]");
        PATTERNS[2] = Pattern.compile("[ÌÏÓÔ]");
        PATTERNS[3] = Pattern.compile("[ÛÚÙıˆ]");
        PATTERNS[4] = Pattern.compile("[˙˘˚¸]");
        PATTERNS[5] = Pattern.compile("[Á]");
        PATTERNS[6] = Pattern.compile("[¬√¡¿ƒ]");
        PATTERNS[7] = Pattern.compile("[…» À]");
        PATTERNS[8] = Pattern.compile("[ÕÃŒœ]");
        PATTERNS[9] = Pattern.compile("[”“‘’÷]");
        PATTERNS[10] = Pattern.compile("[⁄Ÿ€‹]");
        PATTERNS[11] = Pattern.compile("[«]");
        PATTERNS[12] = Pattern.compile("[']");
        PATTERNS[13] = Pattern.compile("[`]");
        PATTERNS[14] = Pattern.compile("[?∆]");
        PATTERNS[15] = Pattern.compile("[?]");
        PATTERNS[16] = Pattern.compile("[?]");
        PATTERNS[17] = Pattern.compile("[¯]");
        String result = text;
        for (int i = 0; i < PATTERNS.length; i++) {
            Matcher matcher = PATTERNS[i].matcher(result);
            result = matcher.replaceAll(REPLACES[i]);
        }
        try {
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //System.out.println(result);
        return result.trim();
    }

    public String removeAcentuacaoXML(String text) {
        if (text == null) {
            return null;
        }
        String result = text.replaceAll("[^\\x20-\\x7e]", "");
        result = result.replaceAll("\\n\\r", " ");
        result = result.replaceAll("\\n", " ");
        result = result.replaceAll("\\t", " ");
        return result;
    }

    public float convertStringFloatToFloat(String f) {
        float valor = 0f;
        if (f != null) {
            //verifica quantos pontos tem na string
            int charCount = f.replaceAll("[^.]", "").length();
            if (charCount > 1) {
                f = f.replaceFirst("\\.", "");
            }
            try {
                valor = Float.parseFloat(f);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                //
            }
        }
        return valor;
    }

    public Double convertStringDoubleToDouble(String f) {
        double valor = 0d;
        if (f != null) {
            //verifica quantos pontos tem na string
            int charCount = f.replaceAll("[^.]", "").length();
            if (charCount > 1) {
                f = f.replaceFirst("\\.", "");
            }
            try {
                valor = Double.parseDouble(f);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                //
            }
        }
        return valor;
    }

    public String removeSpaces(String s) {
        StringTokenizer st = new StringTokenizer(s, " ", false);
        String t = "";
        while (st.hasMoreElements()) {
            t += st.nextElement();
        }
        return t;
    }

    public String replace(String r) {
        if (r != null) {
            r = r.replaceAll("\\.", "");
            r = r.replaceAll("\\(", "");
            r = r.replaceAll("\\)", "");
            r = r.replaceAll("\\-", "");
            r = r.replaceAll("\\/", "");
            r = removeSpaces(r);
        } else {
            r = "";
        }
        return r;
    }

    public String zeroFill(String str, int tamanho) {
        if (str.length() < tamanho) {
            String retorno = "";
            for (int i = tamanho - str.length(); i > 0; i--) {
                retorno += "0";
            }
            retorno += str;
            return retorno;
        } else {
            return str;
        }
    }

    public int convertToNumber(String string) {
        int num = 0;
        try {
            num = Integer.parseInt(string);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return (num);
    }

    public String getDigitos(String str) {
        String result = "";
        if (!str.equals("null")) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '1') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '2') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '3') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '4') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '5') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '6') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '7') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '8') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '9') {
                    result = result + str.charAt(i);
                }
            }
        }
        return result;
    }

    public String preencheString(String str, int tamanho, String caracter) {
        if (str == null) {
            str = "";
        }
        if (str.length() <= tamanho) {
            int aux = tamanho - str.length();
            for (int i = 0; i < aux; i++) {
                str += caracter;
            }
            return str;
        } else {
            return str;
        }
    }

    public DecimalFormat getDecimalFormatter(int qtdInteiro, int qtdFracao) {
        Locale myLocale = new Locale("en", "US");
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(myLocale);
        df.setMaximumFractionDigits(qtdFracao);
        df.setMinimumFractionDigits(qtdFracao);
        //df.setMaximumIntegerDigits(qtdInteiro);
        //df.setMinimumIntegerDigits(qtdInteiro);
        df.setGroupingSize(0);
        return df;
    }

    public Date convertStringDatetoData(String data) {
        Date out = null;
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            out = formatador.parse(data);
        } catch (ParseException ex) {
            out = null;
        }
        return out;
    }

    public String convertHoraSegundosToString(Date data) {
        SimpleDateFormat out = new SimpleDateFormat("HH:mm:ss");
        String result = out.format(data);
        return result;
    }

    public Date convertStringDateSefaztoData(String data) {
        Date out = null;
        data = data.replace("T", " ");
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            out = formatador.parse(data);
        } catch (ParseException ex) {
            out = null;
        }
        return out;
    }

    public String convertStringToDateCompleto(Date data) {
        String result = "";
        if (data != null) {
            SimpleDateFormat out = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            result = out.format(data);
        }
        return result;
    }

    public String convertStringToDateMesAnoAbreviado(Date data) {
        String result = "";
        if (data != null) {
            SimpleDateFormat out = new SimpleDateFormat("yyMM");
            result = out.format(data);
        }
        return result;
    }

    public String getChave(String chave) {
        int[] pesos = {4, 3, 2, 9, 8, 7, 6, 5};
        int somaPonderada = 0;
        for (int i = 0; i < chave.length(); i++) {
            somaPonderada += pesos[i % 8] * (Integer.parseInt(chave.substring(i, i + 1)));
        }
        if ((somaPonderada % 11) > 1) {
            return "" + (11 - somaPonderada % 11);
        } else {
            return "0";
        }
    }

    public String getAnoFim(String data) {
        if (data.substring(8, 9).equals("0")) {
            int ano = Integer.parseInt(data.substring(8, 10));
            return ("0" + ano);

        } else {
            int ano = Integer.parseInt(data.substring(8, 10));
            return ("" + ano);
        }
    }

    public String getMes(String data) {
        if (data.substring(3, 4).equals("0")) {
            int mes = Integer.parseInt(data.substring(3, 5));
            return ("0" + mes);
        } else {
            int mes = Integer.parseInt(data.substring(3, 5));
            return ("" + mes);
        }
    }

    public String getAno(String data) {
        int ano = Integer.parseInt(data.substring(6, 10));
        return ("" + ano);
    }

    public Boolean isHorarioVerao(Date d) {
        TimeZone tz = TimeZone.getDefault();
        return tz.inDaylightTime(d);
    }
}
