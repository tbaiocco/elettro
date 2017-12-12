/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Collection;
import java.util.Date;

/**
 * Dados complementares do CT-e para
 * fins operacionais ou comerciais
 * @author DerliRiffel
 */
public class CteCompl {

    private String xCaracAd;
    private String xCaracSer;
    private String xEmi;
    private String xOrig;
    private String xDest;
    private String xRota;
    private Collection<CtePass> ctePass;
    private Integer tpPer;
    private Date dProg;
    private Date dIni;
    private Date dFim;
    private Integer tpHor;
    private String hProg;
    private String hIni;
    private String hFim;
    private String origCalc;
    private String destCalc;
    private String xObs;
    private Collection<CteObs> obsCont;
    private Collection<CteObs> obsFisco;

    public String getXCaracAd() {
        return xCaracAd;
    }

    /**
     * Característica adicional do transporte
     * @param xCaracAd
     */
    public void setXCaracAd(String xCaracAd) {
        this.xCaracAd = xCaracAd;
    }

    public String getXCaracSer() {
        return xCaracSer;
    }

    /**
     * Característica adicional do serviço
     * @param xCaracSer
     */
    public void setXCaracSer(String xCaracSer) {
        this.xCaracSer = xCaracSer;
    }

    public String getXDest() {
        return xDest;
    }

    public String getXOrig() {
        return xOrig;
    }

    /**
     * Sigla ou código interno da
     * Filial/Porto/Estação/ Aeroporto de Origem
     * @param xOrig
     */
    public void setXOrig(String xOrig) {
        this.xOrig = xOrig;
    }

    /**
     * Sigla ou código interno da
     * Filial/Porto/Estação/Aeroporto de Destino
     * @param xDest
     */
    public void setXDest(String xDest) {
        this.xDest = xDest;
    }

    public String getXEmi() {
        return xEmi;
    }

    /**
     * Funcionário emissor do CTe
     * @param xEmi
     */
    public void setXEmi(String xEmi) {
        this.xEmi = xEmi;
    }

    public String getXRota() {
        return xRota;
    }

    /**
     * Código da Rota de Entrega
     * @param xRota
     */
    public void setXRota(String xRota) {
        this.xRota = xRota;
    }

    public Collection<CtePass> getCtePass() {
        return ctePass;
    }

    /**
     * Informações de passagem
     * @param ctePass
     */
    public void setCtePass(Collection<CtePass> ctePass) {
        this.ctePass = ctePass;
    }

    public Integer getTpPer() {
        return tpPer;
    }

    /**
     * Tipo de data/período programado para entrega
     * 0- Sem data definida 1-Na data; 2-Até a data; 3-A partir da data
     * @param tpPer
     */
    public void setTpPer(Integer tpPer) {
        this.tpPer = tpPer;
    }

    public Date getDProg() {
        return dProg;
    }

    /**
     * Data programada
     * @param dProg
     */
    public void setDProg(Date dProg) {
        this.dProg = dProg;
    }

    public Date getDFim() {
        return dFim;
    }

    /**
     * Data Final
     * @param dFim
     */
    public void setDFim(Date dFim) {
        this.dFim = dFim;
    }

    public Date getDIni() {
        return dIni;
    }

    /**
     * Data Inicial
     * @param dIni
     */
    public void setDIni(Date dIni) {
        this.dIni = dIni;
    }

    public String getHFim() {
        return hFim;
    }

    /**
     * Formato HH:MM:SS
     * Obrigatorio quando tpHor=4
     * Hora final
     * @param hFim
     */
    public void setHFim(String hFim) {
        this.hFim = hFim;
    }

    public String getHIni() {
        return hIni;
    }

    /**
     * Formato HH:MM:SS
     * Obrigatorio quando tpHor=4
     * hora final
     * @param hIni
     */
    public void setHIni(String hIni) {
        this.hIni = hIni;
    }

    public String getHProg() {
        return hProg;
    }

    /**
     * Formato HH:MM:SS
     * Hora programada
     * @param hProg
     */
    public void setHProg(String hProg) {
        this.hProg = hProg;
    }

    public Integer getTpHor() {
        return tpHor;
    }

    /**
     * Formato HH:MM:SS
     * 0- Sem hora definida 1--No horário; 2-Até o horário;
     * 3-A partir do horário 4-No intervalo de tempo
     * @param tpHor
     */
    public void setTpHor(int tpHor) {
        this.tpHor = tpHor;
    }

    public String getDestCalc() {
        return destCalc;
    }

    /**
     * Município de destino para efeito de
     * cálculo do frete
     * TAM: 40
     * @param destCalc
     */
    public void setDestCalc(String destCalc) {
        this.destCalc = destCalc;
    }

    public String getOrigCalc() {
        return origCalc;
    }

    /**
     * Município de origem para efeito de
     * cálculo do frete
     * Tam: 40
     * @param origCalc
     */
    public void setOrigCalc(String origCalc) {
        this.origCalc = origCalc;
    }

    public String getXObs() {
        return xObs;
    }

    /**
     * Observações Gerais
     * Tam: 2000
     * @param xObs
     */
    public void setXObs(String xObs) {
        this.xObs = xObs;
    }

    public Collection<CteObs> getObsCont() {
        return obsCont;
    }

    public void setObsCont(Collection<CteObs> obsCont) {
        this.obsCont = obsCont;
    }

    public Collection<CteObs> getObsFisco() {
        return obsFisco;
    }

    public void setObsFisco(Collection<CteObs> obsFisco) {
        this.obsFisco = obsFisco;
    }

}
