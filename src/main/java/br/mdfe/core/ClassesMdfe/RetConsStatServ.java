/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 

 
 */
package br.mdfe.core.ClassesMdfe;

/**
 *
 * @author Liquid_Works
 */
public class RetConsStatServ extends MDFeRetorno {

    private String dhRecbto;
    private String tMed;
    private String dhRetorno;
    private String xObs;

    public RetConsStatServ() {

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

    public String getxObs() {
        return xObs;
    }

    public void setxObs(String xObs) {
        this.xObs = xObs;
    }
}
