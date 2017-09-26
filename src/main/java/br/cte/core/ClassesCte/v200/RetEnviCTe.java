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
@XStreamAlias("retEnviCte")
public class RetEnviCTe extends CTeRetorno {

    @XStreamAlias("infRec")
    private InfRec recibo;

    public RetEnviCTe() {
    }

    public InfRec getRecibo() {
        return recibo;
    }

    public void setRecibo(InfRec recibo) {
        this.recibo = recibo;
    }
}
