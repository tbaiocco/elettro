/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Collection;

/**
 *
 * @author Derli
 */
public class CteInfUnidTransp {

    private Integer tpUnidTransp;
    private String idUnidTransp;
    private Collection<CteLacUnidTransp> lacUnidTransp;
    private Collection<CteInfUnidCarga> infUnidCarga;

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

    public Collection<CteLacUnidTransp> getLacUnidTransp() {
        return lacUnidTransp;
    }

    public void setLacUnidTransp(Collection<CteLacUnidTransp> lacUnidTransp) {
        this.lacUnidTransp = lacUnidTransp;
    }

    public Collection<CteInfUnidCarga> getInfUnidCarga() {
        return infUnidCarga;
    }

    public void setInfUnidCarga(Collection<CteInfUnidCarga> infUnidCarga) {
        this.infUnidCarga = infUnidCarga;
    }

}
