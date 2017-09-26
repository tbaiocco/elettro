/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core.ClassesCte.v200;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author Rafael
 */
@XStreamAlias("infCanc")
public class InfCanc extends CTeRetorno {

    private String chCTe;
    private String dhRecbto;
    private String nProt;

    public String getChCTe() {
        return chCTe;
    }

    public void setChCTe(String chCTe) {
        this.chCTe = chCTe;
    }

    public String getDhRecbto() {
        return dhRecbto;
    }

    public void setDhRecbto(String dhRecbto) {
        this.dhRecbto = dhRecbto;
    }

    public String getNProt() {
        return nProt;
    }

    public void setNProt(String nProt) {
        this.nProt = nProt;
    }
}
