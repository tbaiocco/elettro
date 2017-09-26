/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core.ClassesCte.v200;

/**
 *
 * @author DerliRiffel
 */
public class RetConsStatServ extends CTeRetorno {

    private String dhRecbto;
    private String tMed;
    private String dhRetorno;
    private String xObs;

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

    public String getxObs() {
        return xObs;
    }

    public void setxObs(String xObs) {
        this.xObs = xObs;
    }
}
