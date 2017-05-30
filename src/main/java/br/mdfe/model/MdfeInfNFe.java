/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.model;

import java.util.ArrayList;

/**
 *
 * @author Derli
 */
public class MdfeInfNFe {

    private String chNFe;
    private String segCodBarra;
    private ArrayList<MdfeInfUnidTransp> infUnidTransp;
    //novo 3.0
    private Integer indReentrega;
    private MdfePeri peri;
    
    public String getChNFe() {
        return chNFe;
    }

    public void setChNFe(String chNFe) {
        this.chNFe = chNFe;
    }

    public String getSegCodBarra() {
        return segCodBarra;
    }

    public void setSegCodBarra(String segCodBarra) {
        this.segCodBarra = segCodBarra;
    }

    public ArrayList<MdfeInfUnidTransp> getInfUnidTransp() {
        return infUnidTransp;
    }

    public void setInfUnidTransp(ArrayList<MdfeInfUnidTransp> infUnidTransp) {
        this.infUnidTransp = infUnidTransp;
    }

	public Integer getIndReentrega() {
		return indReentrega;
	}

	public void setIndReentrega(Integer indReentrega) {
		this.indReentrega = indReentrega;
	}

	public MdfePeri getPeri() {
		return peri;
	}

	public void setPeri(MdfePeri peri) {
		this.peri = peri;
	}

}
