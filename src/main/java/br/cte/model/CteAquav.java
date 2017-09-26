/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.cte.model;

/**
 * Informações do modal Aquaviário
 *
 * @author DerliRiffel
 */
public class CteAquav {
    private Double vPrest;
    private Double vAFRMM;
    private String nBooking;
    private String nCtrl;
    private String xNavio;
    private String nViag;
    private String direc;
    private String prtEmb;
    private String prtTrans;
    private String prtDest;
    private Integer tpNav;
    private String irin;

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getIrin() {
        return irin;
    }

    public void setIrin(String irin) {
        this.irin = irin;
    }

    public String getNBooking() {
        return nBooking;
    }

    public void setNBooking(String nBooking) {
        this.nBooking = nBooking;
    }

    public String getNCtrl() {
        return nCtrl;
    }

    public void setNCtrl(String nCtrl) {
        this.nCtrl = nCtrl;
    }

    public String getNViag() {
        return nViag;
    }

    public void setNViag(String nViag) {
        this.nViag = nViag;
    }

    public String getPrtDest() {
        return prtDest;
    }

    public void setPrtDest(String prtDest) {
        this.prtDest = prtDest;
    }

    public String getPrtEmb() {
        return prtEmb;
    }

    public void setPrtEmb(String prtEmb) {
        this.prtEmb = prtEmb;
    }

    public String getPrtTrans() {
        return prtTrans;
    }

    public void setPrtTrans(String prtTrans) {
        this.prtTrans = prtTrans;
    }

    public Integer getTpNav() {
        return tpNav;
    }

    public void setTpNav(Integer tpNav) {
        this.tpNav = tpNav;
    }

    public Double getVAFRMM() {
        return vAFRMM;
    }

    public void setVAFRMM(Double vAFRMM) {
        this.vAFRMM = vAFRMM;
    }

    public Double getVPrest() {
        return vPrest;
    }

    public void setVPrest(Double vPrest) {
        this.vPrest = vPrest;
    }

    public String getXNavio() {
        return xNavio;
    }

    public void setXNavio(String xNavio) {
        this.xNavio = xNavio;
    }

}
