/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.mdfe.model;

import java.util.Date;

/**
 *
 * @author Derli
 */
public class MdfeLote {
    
    private Integer codigoLote;
    private Date data;
    private String cStat;
    private String cUF;
    private String nRec;
    private String xMotivo;
    private Integer processado;
    private Empresa empresa;
    private Integer tpAmbiente;
    private Mdfe mdfe;

    public MdfeLote() {
    }

    public MdfeLote(Integer codigoLote) {
        this.codigoLote = codigoLote;
    }

    public MdfeLote(Integer codigoLote, Date data) {
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

    public Mdfe getMdfe() {
        return mdfe;
    }

    public void setMdfe(Mdfe mdfe) {
        this.mdfe = mdfe;
    }
}
