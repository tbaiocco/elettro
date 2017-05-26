/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.model;

import java.util.ArrayList;

/**
 *
 * @author Derli
 */
public class MdfeInfUnidTransp {

    private Integer tpUnidTransp;
    private String idUnidTransp;
    private ArrayList<String> nLacre;
    private ArrayList<MdfeInfUnidCarga> infUnidCarga;
    private Double  qtdRat;

    public Integer getTpUnidTransp() {
        return tpUnidTransp;
    }

    public void setTpUnidTransp(Integer tpUnidTransp) {
        this.tpUnidTransp = tpUnidTransp;
    }

    public String getIdUnidTransp() {
        return idUnidTransp;
    }

    public void setIdUnidTransp(String idUnidTransp) {
        this.idUnidTransp = idUnidTransp;
    }

    public ArrayList<String> getNLacre() {
        return nLacre;
    }

    public void setNLacre(ArrayList<String> nLacre) {
        this.nLacre = nLacre;
    }

    public ArrayList<MdfeInfUnidCarga> getInfUnidCarga() {
        return infUnidCarga;
    }

    public void setInfUnidCarga(ArrayList<MdfeInfUnidCarga> infUnidCarga) {
        this.infUnidCarga = infUnidCarga;
    }

    public Double getQtdRat() {
        return qtdRat;
    }

    public void setQtdRat(Double qtdRat) {
        this.qtdRat = qtdRat;
    }

}
