/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.cte.model;

import java.util.Date;

/**
 *
 * @author Derli
 */
public class CteDup {
    private String nDup;
  private Date dVenct;
private Double vDup;

    public String getNDup() {
        return nDup;
    }

    public void setNDup(String nDup) {
        this.nDup = nDup;
    }

    public Date getDVenct() {
        return dVenct;
    }

    public void setDVenct(Date dVenct) {
        this.dVenct = dVenct;
    }

    public Double getVDup() {
        return vDup;
    }

    public void setVDup(Double vDup) {
        this.vDup = vDup;
    }

}
