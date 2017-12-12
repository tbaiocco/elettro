/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core.ClassesCte.v200;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author Derli
 */
@XStreamAlias("infInut")
public class InfInut extends CTeRetorno {

    private String dhRecbto;
    private String nProt;
    private String digVal;
    private String nCTIni;
    private String nCTFin;
    private String mod;
    private String serie;
    private String ano;
    private String CNPJ;

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDhRecbto() {
        return dhRecbto;
    }

    public void setDhRecbto(String dhRecbto) {
        this.dhRecbto = dhRecbto;
    }

    public String getDigVal() {
        return digVal;
    }

    public void setDigVal(String digVal) {
        this.digVal = digVal;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getNCTFin() {
        return nCTFin;
    }

    public void setNCTFin(String nCTFin) {
        this.nCTFin = nCTFin;
    }

    public String getNCTIni() {
        return nCTIni;
    }

    public void setNCTIni(String nCTIni) {
        this.nCTIni = nCTIni;
    }



    public String getNProt() {
        return nProt;
    }

    public void setNProt(String nProt) {
        this.nProt = nProt;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }    
}
