/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Derli
 */
public class CteEvento {

    private int cOrgao;
    private int tpAmb;
    private String CNPJ;
    private String chCTe;
    private Date dhEvento;
    private int nSeqEvento;
    private Integer tpEvento;
    private String descEvento;

    private String xJust;
    private String xRegistro;
    private String nDoc;
    private Collection<CteInfCorrecao> infCorrecao;
    private String xCondUso;
    private String nProtAprovacaoCte;

    private String nProtRegEvento;
    private String xMotivo;
    private String verAplic;
    private String dhRegEvento;
    private String cStat;
    private String xEvento;

    public int getCOrgao() {
        return cOrgao;
    }

    public void setCOrgao(int cOrgao) {
        this.cOrgao = cOrgao;
    }

    public int getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(int tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getChCTe() {
        return chCTe;
    }

    public void setChCTe(String chCTe) {
        this.chCTe = chCTe;
    }

    public Date getDhEvento() {
        return dhEvento;
    }

    public void setDhEvento(Date dhEvento) {
        this.dhEvento = dhEvento;
    }

    public int getNSeqEvento() {
        return nSeqEvento;
    }

    public void setNSeqEvento(int nSeqEvento) {
        this.nSeqEvento = nSeqEvento;
    }

    public Integer getTpEvento() {
        return tpEvento;
    }

    public void setTpEvento(Integer tpEvento) {
        this.tpEvento = tpEvento;
    }

    public String getDescEvento() {
        return descEvento;
    }

    public void setDescEvento(String descEvento) {
        this.descEvento = descEvento;
    }

    public String getXJust() {
        return xJust;
    }

    public void setXJust(String xJust) {
        this.xJust = xJust;
    }

    public String getXRegistro() {
        return xRegistro;
    }

    public void setXRegistro(String xRegistro) {
        this.xRegistro = xRegistro;
    }

    public String getNDoc() {
        return nDoc;
    }

    public void setNDoc(String nDoc) {
        this.nDoc = nDoc;
    }

    public Collection<CteInfCorrecao> getInfCorrecao() {
        return infCorrecao;
    }

    public void setInfCorrecao(Collection<CteInfCorrecao> infCorrecao) {
        this.infCorrecao = infCorrecao;
    }

    public String getNProtAprovacaoCte() {
        return nProtAprovacaoCte;
    }

    public void setNProtAprovacaoCte(String nProtAprovacaoCte) {
        this.nProtAprovacaoCte = nProtAprovacaoCte;
    }

    public String getNProtRegEvento() {
        return nProtRegEvento;
    }

    public void setNProtRegEvento(String nProtRegEvento) {
        this.nProtRegEvento = nProtRegEvento;
    }

    public String getXMotivo() {
        return xMotivo;
    }

    public void setXMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }

    public String getVerAplic() {
        return verAplic;
    }

    public void setVerAplic(String verAplic) {
        this.verAplic = verAplic;
    }

    public String getDhRegEvento() {
        return dhRegEvento;
    }

    public void setDhRegEvento(String dhRegEvento) {
        this.dhRegEvento = dhRegEvento;
    }

    public String getCStat() {
        return cStat;
    }

    public void setCStat(String cStat) {
        this.cStat = cStat;
    }

    public String getXEvento() {
        return xEvento;
    }

    public void setXEvento(String xEvento) {
        this.xEvento = xEvento;
    }

    public String getXCondUso() {
        return xCondUso;
    }

    public void setXCondUso(String xCondUso) {
        this.xCondUso = xCondUso;
    }

}
