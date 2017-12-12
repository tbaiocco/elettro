/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

/**
 * Informações de quantidades da Carga do
 * CT-e
 *
 * @author DerliRiffel
 */
public class CteInfQCarga {

    private Integer cUnid;
    private String tpMed;
    private Double qCarga;

    public Integer getCUnid() {
        return cUnid;
    }

    public void setCUnid(Integer cUnid) {
        this.cUnid = cUnid;
    }

    public Double getQCarga() {
        return qCarga;
    }

    public void setQCarga(Double qCarga) {
        this.qCarga = qCarga;
    }

    public String getTpMed() {
        return tpMed;
    }

    public void setTpMed(String tpMed) {
        this.tpMed = tpMed;
    }
}
