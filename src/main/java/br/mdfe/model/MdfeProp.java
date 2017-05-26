/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.model;

/**
 *
 * @author Derli
 */
public class MdfeProp {

    private String CNPJ;
    private String CPF;
    private String RNTRC;
    private String xNome;
    private String IE;
    private String UF;
    private Integer tpProp;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getRNTRC() {
        return RNTRC;
    }

    public void setRNTRC(String RNTRC) {
        this.RNTRC = RNTRC;
    }

    public String getXNome() {
        return xNome;
    }

    public void setXNome(String xNome) {
        this.xNome = xNome;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public Integer getTpProp() {
        return tpProp;
    }

    public void setTpProp(Integer tpProp) {
        this.tpProp = tpProp;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

}
