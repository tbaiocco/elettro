/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.model;

/**
 *
 * @author DerliRiffel
 */
public class MdfeRetornoEnvioLote {

    private String tpAmb;
    private String verAplic;
    private String chCTe;
    private String dhRecbto;
    private String nProt;
    private String digVal;
    private String cStat;
    private String xMotivo;
    private String cUF;
    private Mdfe mdfe;

    public String getCStat() {
        return cStat;
    }

    public void setCStat(String cStat) {
        this.cStat = cStat;
    }

    public String getCUF() {
        return cUF;
    }

    public void setCUF(String cUF) {
        this.cUF = cUF;
    }

    public String getCTe() {
        return chCTe;
    }

    public void setChCTe(String chCTe) {
        this.chCTe = chCTe;
    }

    public String getDhRecbto() {
        return dhRecbto;
    }

    public void setDhRecbto(String dhRecbto) {
        this.dhRecbto = dhRecbto;
    }

    public String getDigVal() {
        return digVal;
    }

    public void setDigVal(String digVal) {
        this.digVal = digVal;
    }

    public String getNProt() {
        return nProt;
    }

    public void setNProt(String nProt) {
        this.nProt = nProt;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getVerAplic() {
        return verAplic;
    }

    public void setVerAplic(String verAplic) {
        this.verAplic = verAplic;
    }

    public String getXMotivo() {
        return xMotivo;
    }

    public void setXMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }

    public Mdfe getMdfe() {
        return mdfe;
    }

    public void setMdfe(Mdfe mdfe) {
        this.mdfe = mdfe;
    }

}
