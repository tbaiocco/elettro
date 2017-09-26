/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.cte.core.ClassesCte.v200;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author DerliRiffel
 */
@XStreamAlias("retInutCTe")
public class RetInutCte {

    @XStreamAlias("infInut")
    private InfInut infInut;

    public InfInut getInfInut() {
        return infInut;
    }

    public void setInfInut(InfInut infInut) {
        this.infInut = infInut;
    }
}
