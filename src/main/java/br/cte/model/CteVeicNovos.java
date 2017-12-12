/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteVeicNovos {
    private String chassi;
    private String cCor;
    private String xCor;
    private String cMod;
    private Double vUnit;
    private Double vFrete;

    public String getCCor() {
        return cCor;
    }

    public void setCCor(String cCor) {
        this.cCor = cCor;
    }

    public String getCMod() {
        return cMod;
    }

    public void setCMod(String cMod) {
        this.cMod = cMod;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public Double getVFrete() {
        return vFrete;
    }

    public void setVFrete(Double vFrete) {
        this.vFrete = vFrete;
    }

    public Double getVUnit() {
        return vUnit;
    }

    public void setVUnit(Double vUnit) {
        this.vUnit = vUnit;
    }

    public String getXCor() {
        return xCor;
    }

    public void setXCor(String xCor) {
        this.xCor = xCor;
    }


}
