/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Date;

/**
 * Detalhamento do CT-e substituido
 *
 * @author Téo
 */
public class CteInfCteSub {

	private String chCte;
	private boolean icms; //aqui determina qual grupo de tags usar tomaICMS ou tomaNaoICMS
	private String chNFe;
	private String CNPJ;
	private String mod;
	private String serie;
	private String subserie;
	private String nro;
	private String valor;
	private Date dEmi;
	private String refCte;
	private String refCteAnu;

	public String getChCte() {
		return chCte;
	}
	public void setChCte(String chCte) {
		this.chCte = chCte;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cnpj) {
		CNPJ = cnpj;
	}
	public Date getDEmi() {
		return dEmi;
	}
	public void setDEmi(Date emi) {
		dEmi = emi;
	}
	public boolean isIcms() {
		return icms;
	}
	public void setIcms(boolean icms) {
		this.icms = icms;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	public String getRefCte() {
		return refCte;
	}
	public void setRefCte(String refCte) {
		this.refCte = refCte;
	}
	public String getRefCteAnu() {
		return refCteAnu;
	}
	public void setRefCteAnu(String refCteAnu) {
		this.refCteAnu = refCteAnu;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getSubserie() {
		return subserie;
	}
	public void setSubserie(String subserie) {
		this.subserie = subserie;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getChNFe() {
		return chNFe;
	}
	public void setChNFe(String chNFe) {
		this.chNFe = chNFe;
	}
    
}
