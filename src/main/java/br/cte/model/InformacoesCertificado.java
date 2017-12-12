/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

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
    private String cnpj;
    private String path;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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
}
