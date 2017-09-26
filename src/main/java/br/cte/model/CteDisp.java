/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Date;

/**
 *
 * @author DerliRiffel
 */
public class CteDisp {

    private Integer tpDisp;
    private String xEmp;
    private Date dVig;
    private String nDisp;
    private String nCompC;

    public Date getDVig() {
        return dVig;
    }

    public void setDVig(Date dVig) {
        this.dVig = dVig;
    }

    public String getNCompC() {
        return nCompC;
    }

    public void setNCompC(String nCompC) {
        this.nCompC = nCompC;
    }

    public String getNDisp() {
        return nDisp;
    }

    public void setNDisp(String nDisp) {
        this.nDisp = nDisp;
    }

    public Integer getTpDisp() {
        return tpDisp;
    }

    public void setTpDisp(Integer tpDisp) {
        this.tpDisp = tpDisp;
    }

    public String getXEmp() {
        return xEmp;
    }

    public void setXEmp(String xEmp) {
        this.xEmp = xEmp;
    }
}
