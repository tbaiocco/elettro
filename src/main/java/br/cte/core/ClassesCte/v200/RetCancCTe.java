package br.cte.core.ClassesCte.v200;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author DerliRiffel
 */
@XStreamAlias("retCancCTe")
public class RetCancCTe {

    @XStreamAlias("infCanc")
    private InfCanc infCanc;

    public InfCanc getInfCanc() {
        return infCanc;
    }

    public void setInfCanc(InfCanc infCanc) {
        this.infCanc = infCanc;
    }
}