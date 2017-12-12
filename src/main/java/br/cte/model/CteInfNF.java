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
public class CteInfNF extends CteEndereco {

    private String nRoma;
    private String nPed;
    private String mod; //01 - NF normal | 04 - NF produtor
    private String serie;
    private String nDoc;
    private Date dEmi;
    private Double vBC;
    private Double vICMS;
    private Double vBCST;
    private Double vST;
    private Double vProd;
    private Double vNF;
    private Integer nCFOP;
    private Double nPeso;
    private Integer PIN;
    //Local Retirada
    private String CNPJ;
    private String CPF;
    private String xNome;
    private Date dPrev;//data prev entrega
     private Collection<CteInfUnidTransp> infUnidTransp;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Integer getPIN() {
        return PIN;
    }

    public void setPIN(Integer PIN) {
        this.PIN = PIN;
    }

    public Date getDEmi() {
        return dEmi;
    }

    public void setDEmi(Date dEmi) {
        this.dEmi = dEmi;
    }

    public Integer getNCFOP() {
        return nCFOP;
    }

    public void setNCFOP(Integer nCFOP) {
        this.nCFOP = nCFOP;
    }

    public String getNDoc() {
        return nDoc;
    }

    public void setNDoc(String nDoc) {
        this.nDoc = nDoc;
    }

    public String getNPed() {
        return nPed;
    }

    public void setNPed(String nPed) {
        this.nPed = nPed;
    }

    public Double getNPeso() {
        return nPeso;
    }

    public void setNPeso(Double nPeso) {
        this.nPeso = nPeso;
    }

    public String getNRoma() {
        return nRoma;
    }

    public void setNRoma(String nRoma) {
        this.nRoma = nRoma;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Double getVBC() {
        return vBC;
    }

    public void setVBC(Double vBC) {
        this.vBC = vBC;
    }

    public Double getVBCST() {
        return vBCST;
    }

    public void setVBCST(Double vBCST) {
        this.vBCST = vBCST;
    }

    public Double getVICMS() {
        return vICMS;
    }

    public void setVICMS(Double vICMS) {
        this.vICMS = vICMS;
    }

    public Double getVNF() {
        return vNF;
    }

    public void setVNF(Double vNF) {
        this.vNF = vNF;
    }

    public Double getVProd() {
        return vProd;
    }

    public void setVProd(Double vProd) {
        this.vProd = vProd;
    }

    public Double getVST() {
        return vST;
    }

    public void setVST(Double vST) {
        this.vST = vST;
    }

    public String getXNome() {
        return xNome;
    }

    public void setXNome(String xNome) {
        this.xNome = xNome;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public Date getDPrev() {
        return dPrev;
    }

    public void setDPrev(Date dPrev) {
        this.dPrev = dPrev;
    }

    public Collection<CteInfUnidTransp> getInfUnidTransp() {
        return infUnidTransp;
    }

    public void setInfUnidTransp(Collection<CteInfUnidTransp> infUnidTransp) {
        this.infUnidTransp = infUnidTransp;
    }
    
}
