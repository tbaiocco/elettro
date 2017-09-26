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
public class CteFat {
    private String nFat;
    private Double vOrig;
    private Double vDesc;
    private Double vLiq;
    private Collection<CteDup> dup;

    public String getNFat() {
        return nFat;
    }

    public void setNFat(String nFat) {
        this.nFat = nFat;
    }

    public Double getVOrig() {
        return vOrig;
    }

    public void setVOrig(Double vOrig) {
        this.vOrig = vOrig;
    }

    public Double getVDesc() {
        return vDesc;
    }

    public void setVDesc(Double vDesc) {
        this.vDesc = vDesc;
    }

    public Double getVLiq() {
        return vLiq;
    }

    public void setVLiq(Double vLiq) {
        this.vLiq = vLiq;
    }

    public Collection<CteDup> getDup() {
        return dup;
    }

    public void setDup(Collection<CteDup> dup) {
        this.dup = dup;
    }
    
}
