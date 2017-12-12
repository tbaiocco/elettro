/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteEndereco {

    private String xLgr;
    private String nro;
    private String xCpl;
    private String xBairro;
    private Integer cMun;
    private String xMun;
    private String CEP;
    private String UF;
    private Integer cPais;
    private String xPais;

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public Integer getCMun() {
        return cMun;
    }

    public void setCMun(Integer cMun) {
        this.cMun = cMun;
    }

    public Integer getCPais() {
        return cPais;
    }

    public void setCPais(Integer cPais) {
        this.cPais = cPais;
    }

    public String getXPais() {
        return xPais;
    }

    public void setXPais(String xPais) {
        this.xPais = xPais;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getXBairro() {
        return xBairro;
    }

    public void setXBairro(String xBairro) {
        this.xBairro = xBairro;
    }

    public String getXCpl() {
        return xCpl;
    }

    public void setXCpl(String xCpl) {
        this.xCpl = xCpl;
    }

    public String getXLgr() {
        return xLgr;
    }

    public void setXLgr(String xLgr) {
        this.xLgr = xLgr;
    }

    public String getXMun() {
        return xMun;
    }

    public void setXMun(String xMun) {
        this.xMun = xMun;
    }
    
}
