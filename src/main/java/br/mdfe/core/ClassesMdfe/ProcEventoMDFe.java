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
@XStreamAlias("procEventoMDFe")
public class ProcEventoMDFe extends MDFeRetorno {

    @XStreamAlias("eventoMDFe")
    private EventoMDFe eventoMDFe;

    public EventoMDFe getEventoMDFe() {
        return eventoMDFe;
    }

    public void setEventoMDFe(EventoMDFe eventoMDFe) {
        this.eventoMDFe = eventoMDFe;
    }

}
