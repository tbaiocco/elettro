/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteStatusServico {

    private String cStat;
    private String dhRecbto;
    private String tMed;
    private String dhRetorno;
    private String xMotivo;

    public String getCStat() {
        return cStat;
    }

    public void setCStat(String cStat) {
        this.cStat = cStat;
    }

    public String getDhRecbto() {
        return dhRecbto;
    }

    public void setDhRecbto(String dhRecbto) {
        this.dhRecbto = dhRecbto;
    }

    public String getDhRetorno() {
        return dhRetorno;
    }

    public void setDhRetorno(String dhRetorno) {
        this.dhRetorno = dhRetorno;
    }

    public String getTMed() {
        return tMed;
    }

    public void setTMed(String tMed) {
        this.tMed = tMed;
    }

    public String getXMotivo() {
        return xMotivo;
    }

    public void setXMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }
}
