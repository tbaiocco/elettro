/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DerliRiffel
 */
public class CteInfNFe {

    private String chave;
    private Integer PIN;
    private Date dPrev;
    private Collection<CteInfUnidTransp> infUnidTransp;

    public Integer getPIN() {
        return PIN;
    }

    public void setPIN(Integer PIN) {
        this.PIN = PIN;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Date getDPrev() {
        return dPrev;
    }

    public void setDPrev(Date dPrev) {
        this.dPrev = dPrev;
    }

    public Collection<CteInfUnidTransp> getInfUnidTransp() {
        return infUnidTransp;
    }

    public void setInfUnidTransp(Collection<CteInfUnidTransp> infUnidTransp) {
        this.infUnidTransp = infUnidTransp;
    }
    
}
