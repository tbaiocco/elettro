/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author DerliRiffel
 */
public class CteLote {

    private Integer codigoLote;
    private Date data;
    private String cStat;
    private String cUF;
    private String nRec;
    private String xMotivo;
    private Integer processado;
    private Empresa empresa;
    private Integer tpAmbiente;
    private Cte cte;

    public CteLote() {
    }

    public CteLote(Integer codigoLote) {
        this.codigoLote = codigoLote;
    }

    public CteLote(Integer codigoLote, Date data) {
        this.codigoLote = codigoLote;
        this.data = data;
    }

    public Integer getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(Integer codigoLote) {
        this.codigoLote = codigoLote;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

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

    public String getNRec() {
        return nRec;
    }

    public void setNRec(String nRec) {
        this.nRec = nRec;
    }

    public String getXMotivo() {
        return xMotivo;
    }

    public void setXMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }

    public Integer getProcessado() {
        return processado;
    }

    public void setProcessado(Integer processado) {
        this.processado = processado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getTpAmbiente() {
        return tpAmbiente;
    }

    public void setTpAmbiente(Integer tpAmbiente) {
        this.tpAmbiente = tpAmbiente;
    }

    public Cte getCte() {
        return cte;
    }

    public void setCte(Cte cte) {
        this.cte = cte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoLote != null ? codigoLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CteLote)) {
            return false;
        }
        CteLote other = (CteLote) object;
        if ((this.codigoLote == null && other.codigoLote != null) || (this.codigoLote != null && !this.codigoLote.equals(other.codigoLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.NfeLote[codigoLote=" + codigoLote + "]";
    }
}
