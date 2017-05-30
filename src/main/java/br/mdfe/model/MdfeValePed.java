/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.model;

/**
 *
 * @author Derli
 */
public class MdfeValePed {

	//valePed - nivel 2 grupo
  	//disp - nivel 3 grupo
	private String CNPJForn; //nivel 4
  	private String CNPJPg; //nivel 4
  	private String CPFPg; //nivel 4
  	private String nCompra; //nivel 4
  	private Double vValePed; //nivel 4

    public String getCNPJForn() {
        return CNPJForn;
    }

    public void setCNPJForn(String CNPJForn) {
        this.CNPJForn = CNPJForn;
    }

    public String getCNPJPg() {
        return CNPJPg;
    }

    public void setCNPJPg(String CNPJPg) {
        this.CNPJPg = CNPJPg;
    }

    public String getNCompra() {
        return nCompra;
    }

    public void setNCompra(String nCompra) {
        this.nCompra = nCompra;
    }

	public String getCPFPg() {
		return CPFPg;
	}

	public void setCPFPg(String cPFPg) {
		CPFPg = cPFPg;
	}

	public String getnCompra() {
		return nCompra;
	}

	public void setnCompra(String nCompra) {
		this.nCompra = nCompra;
	}

	public Double getvValePed() {
		return vValePed;
	}

	public void setvValePed(Double vValePed) {
		this.vValePed = vValePed;
	}

}
