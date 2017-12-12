/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteTomador extends CteEndereco{

    private Integer toma;
    private String CNPJ;
    private String CPF;
    private String IE;
    private String xNome;
    private String xFant;
    private String fone;

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

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Integer getToma() {
        return toma;
    }

    public void setToma(Integer toma) {
        this.toma = toma;
    }

    public String getXFant() {
        return xFant;
    }

    public void setXFant(String xFant) {
        this.xFant = xFant;
    }

    public String getXNome() {
        return xNome;
    }

    public void setXNome(String xNome) {
        this.xNome = xNome;
    }
    
}
