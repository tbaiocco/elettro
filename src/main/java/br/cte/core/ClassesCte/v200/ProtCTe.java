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
@XStreamAlias("protCTe")
public class ProtCTe {
    private String cStat;
    private String xMotivo;

    @XStreamAlias("infProt")
    private InfProt infProt;

    public InfProt getInfProt() {
        return infProt;
    }

    public void setINfProt(InfProt infProt) {
        this.infProt = infProt;
    }

    public String getCStat() {
        return cStat;
    }

    public void setCStat(String cStat) {
        this.cStat = cStat;
    }

    public String getXMotivo() {
        return xMotivo;
    }

    public void setXMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }
    
}
