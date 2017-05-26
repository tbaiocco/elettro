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
public class MdfeInfUnidCarga {
    private Integer tpUnidCarga;
    private String idUnidCarga;
    private ArrayList<MdfeLacUnidCarga> lacUnidCarga;
    private Double qtdRat;

    public Integer getTpUnidCarga() {
        return tpUnidCarga;
    }

    public void setTpUnidCarga(Integer tpUnidCarga) {
        this.tpUnidCarga = tpUnidCarga;
    }

    public String getIdUnidCarga() {
        return idUnidCarga;
    }

    public void setIdUnidCarga(String idUnidCarga) {
        this.idUnidCarga = idUnidCarga;
    }

    public ArrayList<MdfeLacUnidCarga> getLacUnidCarga() {
        return lacUnidCarga;
    }

    public void setLacUnidCarga(ArrayList<MdfeLacUnidCarga> lacUnidCarga) {
        this.lacUnidCarga = lacUnidCarga;
    }

    public Double getQtdRat() {
        return qtdRat;
    }

    public void setQtdRat(Double qtdRat) {
        this.qtdRat = qtdRat;
    }
    
}
