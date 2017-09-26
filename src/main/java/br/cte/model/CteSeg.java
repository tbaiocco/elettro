package br.cte.model;

/**
 *
 * Informações de Seguro da Carga
 *
 * @author DerliRiffel
 */
public class CteSeg {

    private Integer respSeg;
    private String xSeg;
    private String nApol;
    private String nAver;
    private Double vCarga;

    public String getNApol() {
        return nApol;
    }

    public void setNApol(String nApol) {
        this.nApol = nApol;
    }

    public String getNAver() {
        return nAver;
    }

    public void setNAver(String nAver) {
        this.nAver = nAver;
    }

    public Integer getRespSeg() {
        return respSeg;
    }

    public void setRespSeg(Integer respSeg) {
        this.respSeg = respSeg;
    }

    public Double getVCarga() {
        return vCarga;
    }

    public void setVCarga(Double vCarga) {
        this.vCarga = vCarga;
    }

    public String getXSeg() {
        return xSeg;
    }

    public void setXSeg(String xSeg) {
        this.xSeg = xSeg;
    }
}
