package br.cte.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DerliRiffel
 */
public class CteRodo {

    private String RNTRC;
    private Date dPrev;
    private Integer lota;
    private Integer serieCTRB;
    private Integer nCTRB;
    private Collection<CteOcc> occ;
    private List<CteValePed> valePed;
    private Collection<CteVeic> veic;
    private Collection<CteLacRodo> lacRodo;
    private Collection<CteMoto> moto;

    public String getRNTRC() {
        return RNTRC;
    }

    public void setRNTRC(String RNTRC) {
        this.RNTRC = RNTRC;
    }

    public Date getdPrev() {
        return dPrev;
    }

    public void setdPrev(Date dPrev) {
        this.dPrev = dPrev;
    }

    public Integer getLota() {
        return lota;
    }

    public void setLota(Integer lota) {
        this.lota = lota;
    }

    public Integer getNCTRB() {
        return nCTRB;
    }

    public void setNCTRB(Integer nCTRB) {
        this.nCTRB = nCTRB;
    }

    public Collection<CteOcc> getOcc() {
        return occ;
    }

    public void setOcc(Collection<CteOcc> occ) {
        this.occ = occ;
    }

    public Integer getSerieCTRB() {
        return serieCTRB;
    }

    public void setSerieCTRB(Integer serieCTRB) {
        this.serieCTRB = serieCTRB;
    }

    public List<CteValePed> getValePed() {
        return valePed;
    }

    /**
     * Informações de Vale Pedágio
     *
     * @param valePed
     */
    public void setValePed(List<CteValePed> valePed) {
        this.valePed = valePed;
    }

    public Collection<CteVeic> getVeic() {
        return veic;
    }

    /**
     * Dados dos Veículos
     * 
     * @param veic
     */
    public void setVeic(Collection<CteVeic> veic) {
        this.veic = veic;
    }

    public Collection<CteLacRodo> getLacRodo() {
        return lacRodo;
    }

    /**
     * LACRES
     * 
     * @param lacRodo
     */
    public void setLacRodo(Collection<CteLacRodo> lacRodo) {
        this.lacRodo = lacRodo;
    }

    public Collection<CteMoto> getMoto() {
        return moto;
    }

    /**
     * MOTORISTA
     * @param moto
     */
    public void setMoto(Collection<CteMoto> moto) {
        this.moto = moto;
    }
}
