/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.mdfe.core.ClassesMdfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author Rafael
 */
@XStreamAlias("infProt")
public class InfProt {

    private String tpAmb;
    private String verAplic;
    private String chMDFe;
    private String dhRecbto;
    private String nProt;
    private String digVal;
    private String cStat;
    private String xMotivo;
    private String cUF;
    

    public InfProt(){
        
    }

    public String getcStat() {
        return cStat;
    }

    public void setcStat(String cStat) {
        this.cStat = cStat;
    }

    public String getChMDFe() {
        return chMDFe;
    }

    public void setChMDFe(String chMDFe) {
        this.chMDFe = chMDFe;
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

    public String getnProt() {
        return nProt;
    }

    public void setnProt(String nProt) {
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

    public String getxMotivo() {
        return xMotivo;
    }

    public void setxMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }

    public String getcUF() {
        return cUF;
    }

    public void setcUF(String cUF) {
        this.cUF = cUF;
    }



}
