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
@XStreamAlias("retConsSitCTe")
public class RetConsSitCTe extends CTeRetorno {

    private String chCTe;
    
    @XStreamAlias("retCancCTe")
    private RetCancCTe retCancCTe;

    @XStreamAlias("protCTe")
    private ProtCTe protCTe;

    public ProtCTe getProtCTe() {
        return protCTe;
    }

    public void setProtCTe(ProtCTe protCTe) {
        this.protCTe = protCTe;
    }

    public String getChCTe() {
        return chCTe;
    }

    public RetCancCTe getRetCancCTe() {
        return retCancCTe;
    }

    public void setRetCancCTe(RetCancCTe retCancCTe) {
        this.retCancCTe = retCancCTe;
    }
}
