/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.model;

import java.util.Date;

/**
 *
 * @author DerliRiffel
 */
public class InformacoesCertificado {

    private String emissor;
    private Date validoAte;
    private Date validoDe;
    private String emitidoPara;
    private String serialNumber;

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public String getEmitidoPara() {
        return emitidoPara;
    }

    public void setEmitidoPara(String emitidoPara) {
        this.emitidoPara = emitidoPara;
    }

    public Date getValidoAte() {
        return validoAte;
    }

    public void setValidoAte(Date validoAte) {
        this.validoAte = validoAte;
    }

    public Date getValidoDe() {
        return validoDe;
    }

    public void setValidoDe(Date validoDe) {
        this.validoDe = validoDe;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
}
