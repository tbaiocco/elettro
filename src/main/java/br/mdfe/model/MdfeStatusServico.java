/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.mdfe.model;

/**
 *
 * @author DerliRiffel
 */
public class MdfeStatusServico {
    private String cStat;
    private String dhRecbto;
    private String tMed;
    private String dhRetorno;
    private String xMotivo;

    public String getcStat() {
        return cStat;
    }

    public void setcStat(String cStat) {
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

    public String gettMed() {
        return tMed;
    }

    public void settMed(String tMed) {
        this.tMed = tMed;
    }

    public String getxMotivo() {
        return xMotivo;
    }

    public void setxMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }

}
