
package br.cte.model;

import java.util.Date;

/**
 * Informações do modal Aéreo
 *
 * @author DerliRiffel
 */
public class CteAereo {
    private Integer nMinu;
    private String nOCA;
    private Date dPrev;
    private String xLAgEmi;
    private String cIATA;
    private String trecho;
    private String CL;
    private String cTar;
    private Double vTar;
    private String xDime;
    private String cInfManu;
    private String cImp;
    private String idT;

    public String getCL() {
        return CL;
    }

    public void setCL(String CL) {
        this.CL = CL;
    }

    public String getCIATA() {
        return cIATA;
    }

    public void setCIATA(String cIATA) {
        this.cIATA = cIATA;
    }

    public String getCTar() {
        return cTar;
    }

    public void setCTar(String cTar) {
        this.cTar = cTar;
    }

    public Date getDPrev() {
        return dPrev;
    }

    public void setDPrev(Date dPrev) {
        this.dPrev = dPrev;
    }

    public Integer getNMinu() {
        return nMinu;
    }

    public void setNMinu(Integer nMinu) {
        this.nMinu = nMinu;
    }

    public String getNOCA() {
        return nOCA;
    }

    public void setNOCA(String nOCA) {
        this.nOCA = nOCA;
    }

    public String getTrecho() {
        return trecho;
    }

    public void setTrecho(String trecho) {
        this.trecho = trecho;
    }

    public Double getVTar() {
        return vTar;
    }

    public void setVTar(Double vTar) {
        this.vTar = vTar;
    }

    public String getXLAgEmi() {
        return xLAgEmi;
    }

    public void setXLAgEmi(String xLAgEmi) {
        this.xLAgEmi = xLAgEmi;
    }

    public String getcImp() {
        return cImp;
    }

    public void setcImp(String cImp) {
        this.cImp = cImp;
    }

    public String getcInfManu() {
        return cInfManu;
    }

    public void setcInfManu(String cInfManu) {
        this.cInfManu = cInfManu;
    }

    public String getxDime() {
        return xDime;
    }

    public void setxDime(String xDime) {
        this.xDime = xDime;
    }

    public String getIdT() {
        return idT;
    }

    public void setIdT(String idT) {
        this.idT = idT;
    }
    
}
