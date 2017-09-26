package br.cte.model;

import java.util.Collection;
import java.util.Date;

/**
 * Informações dos containers
 *
 * @author DerliRiffel
 */
public class CteContQt {

    private Integer nCont;
    private Date dPrev;
    private Collection<CteLacContQt> lacContQt;

    public Integer getNCont() {
        return nCont;
    }

    public void setNCont(Integer nCont) {
        this.nCont = nCont;
    }

    public Date getDPrev() {
        return dPrev;
    }

    public void setDPrev(Date dPrev) {
        this.dPrev = dPrev;
    }

    public Collection<CteLacContQt> getLacContQt() {
        return lacContQt;
    }

    /**
     * Lacres dos containers
     * 
     * @param lacContQt
     */
    public void setLacContQt(Collection<CteLacContQt> lacContQt) {
        this.lacContQt = lacContQt;
    }

}
