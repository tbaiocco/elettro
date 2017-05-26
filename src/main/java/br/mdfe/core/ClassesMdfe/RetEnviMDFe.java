/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core.ClassesMdfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author Rafael
 */
@XStreamAlias("retEnviMDFe")
public class RetEnviMDFe extends MDFeRetorno {

    @XStreamAlias("infRec")
    private InfRec recibo;

    public RetEnviMDFe() {
    }

    public InfRec getRecibo() {
        return recibo;
    }

    public void setRecibo(InfRec recibo) {
        this.recibo = recibo;
    }
}
