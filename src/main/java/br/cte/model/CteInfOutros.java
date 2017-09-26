/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author DerliRiffel
 */
public class CteInfOutros {

    private Integer tpDoc;
    private String descOutros;
    private String nDoc;
    private Date dEmi;
    private Double vDocFisc;
    private Collection<CteInfUnidTransp> infUnidTransp;

    public Collection<CteInfUnidTransp> getInfUnidTransp() {
        return infUnidTransp;
    }

    public void setInfUnidTransp(Collection<CteInfUnidTransp> infUnidTransp) {
        this.infUnidTransp = infUnidTransp;
    }
    private Collection<CteLacUnidTransp> lacUnidTransp;
    private Collection<CteInfUnidCarga> infUnidCarga;

    public Date getDEmi() {
        return dEmi;
    }

    public void setDEmi(Date dEmi) {
        this.dEmi = dEmi;
    }

    public String getDescOutros() {
        return descOutros;
    }

    public void setDescOutros(String descOutros) {
        this.descOutros = descOutros;
    }

    public String getNDoc() {
        return nDoc;
    }

    public void setNDoc(String nDoc) {
        this.nDoc = nDoc;
    }

    public Integer getTpDoc() {
        return tpDoc;
    }

    public void setTpDoc(Integer tpDoc) {
        this.tpDoc = tpDoc;
    }

    public Double getVDocFisc() {
        return vDocFisc;
    }

    public void setVDocFisc(Double vDocFisc) {
        this.vDocFisc = vDocFisc;
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
