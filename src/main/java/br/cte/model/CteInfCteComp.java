package br.cte.model;

import java.util.Collection;

/**
 * Detalhamento do CT-e complementado
 *
 * @author DerliRiffel
 */
public class CteInfCteComp {

    private String chave;
    private Double vTPrest;
    private Collection<CteCompComp> compComp;
    private CteImposto impComp;
    private String infAdFisco;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Collection<CteCompComp> getCompComp() {
        return compComp;
    }

    public void setCompComp(Collection<CteCompComp> compComp) {
        this.compComp = compComp;
    }

    public Double getVTPrest() {
        return vTPrest;
    }

    public void setVTPrest(Double vTPrest) {
        this.vTPrest = vTPrest;
    }

    public CteImposto getImpComp() {
        return impComp;
    }

    public void setImpComp(CteImposto impComp) {
        this.impComp = impComp;
    }

    public String getInfAdFisco() {
        return infAdFisco;
    }

    public void setInfAdFisco(String infAdFisco) {
        this.infAdFisco = infAdFisco;
    }
    
}
