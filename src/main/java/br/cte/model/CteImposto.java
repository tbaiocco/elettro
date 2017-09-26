/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteImposto {

    private Integer CST;
    private Double vBC;
    private Double pICMS;
    private Double vICMS;
    private Double pRedBC;
    private Double vCred;
    private String infAdFisco;
    private Double vBCSTRet;
    private Double pICMSSTRet;
    private Double vICMSSTRet;
    private Double vTotTrib;
    private Integer indSN;
    
    private Double pRedBCOutraUF;
    private Double vBCOutraUF;
    private Double pICMSOutraUF;
    private Double vICMSOutraUF;

    public Double getvTotTrib() {
        return vTotTrib;
    }

    public void setvTotTrib(Double vTotTrib) {
        this.vTotTrib = vTotTrib;
    }

    public Integer getCST() {
        return CST;
    }

    public void setCST(Integer CST) {
        this.CST = CST;
    }

    public String getInfAdFisco() {
        return infAdFisco;
    }

    public void setInfAdFisco(String infAdFisco) {
        this.infAdFisco = infAdFisco;
    }

    public Double getPICMS() {
        return pICMS;
    }

    public void setPICMS(Double pICMS) {
        this.pICMS = pICMS;
    }

    public Double getPRedBC() {
        return pRedBC;
    }

    public void setPRedBC(Double pRedBC) {
        this.pRedBC = pRedBC;
    }

    public Double getVBC() {
        return vBC;
    }

    public void setVBC(Double vBC) {
        this.vBC = vBC;
    }

    public Double getVCred() {
        return vCred;
    }

    public void setVCred(Double vCred) {
        this.vCred = vCred;
    }

    public Double getVICMS() {
        return vICMS;
    }

    public void setVICMS(Double vICMS) {
        this.vICMS = vICMS;
    }

    public Double getVBCSTRet() {
        return vBCSTRet;
    }

    public void setVBCSTRet(Double vBCSTRet) {
        this.vBCSTRet = vBCSTRet;
    }

    public Double getPICMSSTRet() {
        return pICMSSTRet;
    }

    public void setPICMSSTRet(Double pICMSSTRet) {
        this.pICMSSTRet = pICMSSTRet;
    }

    public Double getVICMSSTRet() {
        return vICMSSTRet;
    }

    public void setVICMSSTRet(Double vICMSSTRet) {
        this.vICMSSTRet = vICMSSTRet;
    }

    public Integer getIndSN() {
        return indSN;
    }

    public void setIndSN(Integer indSN) {
        this.indSN = indSN;
    }

    public Double getPRedBCOutraUF() {
        return pRedBCOutraUF;
    }

    public void setPRedBCOutraUF(Double pRedBCOutraUF) {
        this.pRedBCOutraUF = pRedBCOutraUF;
    }

    public Double getVBCOutraUF() {
        return vBCOutraUF;
    }

    public void setVBCOutraUF(Double vBCOutraUF) {
        this.vBCOutraUF = vBCOutraUF;
    }

    public Double getPICMSOutraUF() {
        return pICMSOutraUF;
    }

    public void setPICMSOutraUF(Double pICMSOutraUF) {
        this.pICMSOutraUF = pICMSOutraUF;
    }

    public Double getVICMSOutraUF() {
        return vICMSOutraUF;
    }

    public void setVICMSOutraUF(Double vICMSOutraUF) {
        this.vICMSOutraUF = vICMSOutraUF;
    }

}
