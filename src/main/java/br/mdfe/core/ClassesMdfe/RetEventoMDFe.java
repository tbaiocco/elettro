/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core.ClassesMdfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author Derli
 */
@XStreamAlias("retEventoMDFe")
public class RetEventoMDFe {

    private InfEvento infEvento;

    public InfEvento getInfEvento() {
        return infEvento;
    }

    public void setInfEvento(InfEvento infEvento) {
        this.infEvento = infEvento;
    }

}
