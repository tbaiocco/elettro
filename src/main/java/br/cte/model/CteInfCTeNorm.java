package br.cte.model;

import java.util.Collection;

/**
 *
 * @author DerliRiffel
 */
public class CteInfCTeNorm {

    private Double vCarga;
    private String proPred;
    private String xOutCat;
    private Collection<CteInfQCarga> infQ;
    private Collection<CteContQt> contQt;
    private Collection<CteDocAnt> docAnt;
    private Collection<CteSeg> seg;
    private CteRodo rodo;
    private CteAereo aereo;
    private CteAquav aquav;
    private Collection<CtePeri> peri;
    private Collection<CteVeicNovos> veicNovos;
    private CteFat fat;
    private Collection<CteAutXML> autXml;

    private CteInfCteSub infCteSub;

    public CteInfCteSub getInfCteSub() {
        return infCteSub;
    }

    public void setInfCteSub(CteInfCteSub infCteSub) {
        this.infCteSub = infCteSub;
    }
    
    public Collection<CteInfQCarga> getInfQ() {
        return infQ;
    }

    /**
     * INFORMAÇÕES SOBRE QUANTIDADE DA CARGA
     * @param infQ
     */
    public void setInfQ(Collection<CteInfQCarga> infQ) {
        this.infQ = infQ;
    }

    public String getProPred() {
        return proPred;
    }

    public void setProPred(String proPred) {
        this.proPred = proPred;
    }

    public Double getVCarga() {
        return vCarga;
    }

    public void setVCarga(Double vCarga) {
        this.vCarga = vCarga;
    }



    public String getXOutCat() {
        return xOutCat;
    }

    public void setXOutCat(String xOutCat) {
        this.xOutCat = xOutCat;
    }

    public Collection<CteContQt> getContQt() {
        return contQt;
    }

    /**
     * Informações dos containers
     * 
     * @param contQt
     */
    public void setContQt(Collection<CteContQt> contQt) {
        this.contQt = contQt;
    }

    public Collection<CteDocAnt> getDocAnt() {
        return docAnt;
    }

    /**
     * Informações de identificação dos
     * documentos de Transporte Anterior
     * @param docAnt
     */
    public void setDocAnt(Collection<CteDocAnt> docAnt) {
        this.docAnt = docAnt;
    }

    public Collection<CteSeg> getSeg() {
        return seg;
    }

    /**
     * INFORMAÇÕES DO SEGURO
     *
     * @param seg
     */
    public void setSeg(Collection<CteSeg> seg) {
        this.seg = seg;
    }

    public CteRodo getRodo() {
        return rodo;
    }

    /***
     * Informações do modal Rodoviário
     * 
     * @param rodo
     */
    public void setRodo(CteRodo rodo) {
        this.rodo = rodo;
    }

    public CteAereo getAereo() {
        return aereo;
    }

    /**
     * Informações do modal Aéreo
     * 
     * @param aereo
     */
    public void setAereo(CteAereo aereo) {
        this.aereo = aereo;
    }

    public CteAquav getAquav() {
        return aquav;
    }

    /**
     * Informações do modal Aquaviário
     *
     * @param aquav
     */
    public void setAquav(CteAquav aquav) {
        this.aquav = aquav;
    }

    public Collection<CtePeri> getPeri() {
        return peri;
    }

    /**
     * produtos classificados pela ONU como
     * perigosos.
     * @param peri
     */
    public void setPeri(Collection<CtePeri> peri) {
        this.peri = peri;
    }

    public Collection<CteVeicNovos> getVeicNovos() {
        return veicNovos;
    }

    /**
     * informações dos veículos transportados
     *
     * @param veicNovos
     */
    public void setVeicNovos(Collection<CteVeicNovos> veicNovos) {
        this.veicNovos = veicNovos;
    }

    public CteFat getFat() {
        return fat;
    }

    public void setFat(CteFat fat) {
        this.fat = fat;
    }

    public Collection<CteAutXML> getAutXml() {
        return autXml;
    }

    public void setAutXml(Collection<CteAutXML> autXml) {
        this.autXml = autXml;
    }
    
}
