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
@XStreamAlias("infEvento")
public class InfEvento {

    private int tpAmb;
    private String verAplic;
    private String cOrgao;
    private String cStat;
    private String xMotivo;
    private String chMDFe;
    private String tpEvento;
    private String xEvento;
    private int nSeqEvento;
    private String dhRegEvento;
    private String nProt;

    public int getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(int tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getVerAplic() {
        return verAplic;
    }

    public void setVerAplic(String verAplic) {
        this.verAplic = verAplic;
    }

    public String getCOrgao() {
        return cOrgao;
    }

    public void setCOrgao(String cOrgao) {
        this.cOrgao = cOrgao;
    }

    public String getCStat() {
        return cStat;
    }

    public void setCStat(String cStat) {
        this.cStat = cStat;
    }

    public String getXMotivo() {
        return xMotivo;
    }

    public void setXMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }

    public String getChMDFe() {
        return chMDFe;
    }

    public void setChMDFe(String chMDFe) {
        this.chMDFe = chMDFe;
    }

  

    public String getTpEvento() {
        return tpEvento;
    }

    public void setTpEvento(String tpEvento) {
        this.tpEvento = tpEvento;
    }

    public String getXEvento() {
        return xEvento;
    }

    public void setXEvento(String xEvento) {
        this.xEvento = xEvento;
    }

    public int getnSeqEvento() {
        return nSeqEvento;
    }

    public void setnSeqEvento(int nSeqEvento) {
        this.nSeqEvento = nSeqEvento;
    }

    public String getDhRegEvento() {
        return dhRegEvento;
    }

    public void setDhRegEvento(String dhRegEvento) {
        this.dhRegEvento = dhRegEvento;
    }

    public String getNProt() {
        return nProt;
    }

    public void setNProt(String nProt) {
        this.nProt = nProt;
    }
    
    
}
