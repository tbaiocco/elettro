/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Derli
 */
public class MdfeInfNF {

    private String CNPJ;
    private String UF;
    private String nNF;
    private String serie;
    private Date dEmi;
    private Double vNF;
    private String PIN;
    private ArrayList<MdfeInfUnidTransp> infUnidTransp;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getNNF() {
        return nNF;
    }

    public void setNNF(String nNF) {
        this.nNF = nNF;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Date getdEmi() {
        return dEmi;
    }

    public void setdEmi(Date dEmi) {
        this.dEmi = dEmi;
    }

    public Double getVNF() {
        return vNF;
    }

    public void setVNF(Double vNF) {
        this.vNF = vNF;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public ArrayList<MdfeInfUnidTransp> getInfUnidTransp() {
        return infUnidTransp;
    }

    public void setInfUnidTransp(ArrayList<MdfeInfUnidTransp> infUnidTransp) {
        this.infUnidTransp = infUnidTransp;
    }
}
