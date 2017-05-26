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
public class MdfeInfCT {

    private String nCT;
    private String serie;
    private String subser;
    private Date dEmi;
    private Double vCarga;
    private ArrayList<MdfeInfUnidTransp> infUnidTransp;
  

    public String getNCT() {
        return nCT;
    }

    public void setNCT(String nCT) {
        this.nCT = nCT;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSubser() {
        return subser;
    }

    public void setSubser(String subser) {
        this.subser = subser;
    }

    public Date getdEmi() {
        return dEmi;
    }

    public void setdEmi(Date dEmi) {
        this.dEmi = dEmi;
    }

    public Double getVCarga() {
        return vCarga;
    }

    public void setVCarga(Double vCarga) {
        this.vCarga = vCarga;
    }

    public ArrayList<MdfeInfUnidTransp> getInfUnidTransp() {
        return infUnidTransp;
    }

    public void setInfUnidTransp(ArrayList<MdfeInfUnidTransp> infUnidTransp) {
        this.infUnidTransp = infUnidTransp;
    }
}
