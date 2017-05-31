package br.mdfe.model;

import java.util.ArrayList;

public class MdfeInfANTT {

	//infANTT - nivel 1 grupo
	private String RNTRC; //nivel 2
	
	private ArrayList<MdfeInfCIOT> infCIOT;
	
	private ArrayList<MdfeValePed> valePed;
	
	private ArrayList<MdfeInfContratante> infContratantes;

	public String getRNTRC() {
		return RNTRC;
	}
	public void setRNTRC(String rNTRC) {
		RNTRC = rNTRC;
	}
	public ArrayList<MdfeInfCIOT> getInfCIOT() {
		return infCIOT;
	}
	public void setInfCIOT(ArrayList<MdfeInfCIOT> infCIOT) {
		this.infCIOT = infCIOT;
	}
	public ArrayList<MdfeValePed> getValePed() {
		return valePed;
	}
	public void setValePed(ArrayList<MdfeValePed> valePed) {
		this.valePed = valePed;
	}
	public ArrayList<MdfeInfContratante> getInfContratantes() {
		return infContratantes;
	}
	public void setInfContratantes(ArrayList<MdfeInfContratante> infContratantes) {
		this.infContratantes = infContratantes;
	}
	
	
}
