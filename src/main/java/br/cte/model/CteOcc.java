package br.cte.model;

import java.util.Date;

/**
 *
 * @author DerliRiffel
 */
public class CteOcc {

    private String serie;
    private Integer nOcc;
    private Date dEmi;
    //emiOcc
    private String CNPJ;
    private String cInt;
    private String IE;
    private String UF;
    private String fone;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
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

    public String getCInt() {
        return cInt;
    }

    public void setCInt(String cInt) {
        this.cInt = cInt;
    }

    public Date getDEmi() {
        return dEmi;
    }

    public void setDEmi(Date dEmi) {
        this.dEmi = dEmi;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Integer getNOcc() {
        return nOcc;
    }

    public void setNOcc(Integer nOcc) {
        this.nOcc = nOcc;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
}
