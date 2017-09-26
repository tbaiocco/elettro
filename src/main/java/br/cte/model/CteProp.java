/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteProp {
    private String CPF;
    private String CNPJ;
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

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getRNTRC() {
        return RNTRC;
    }

    public void setRNTRC(String RNTRC) {
        this.RNTRC = RNTRC;
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

    public String getXNome() {
        return xNome;
    }

    public void setXNome(String xNome) {
        this.xNome = xNome;
    }

}
