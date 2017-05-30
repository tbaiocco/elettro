package br.mdfe.model;

public class MdfeInfANTT {

	//infANTT - nivel 1 grupo
	private String RNTRC; //nivel 2
	
	private MdfeInfCIOT infCIOT;
	
	private MdfeValePed valePed;
	
	//infContratante - nivel 2 grupo
	private String CPF; //nivel 3
	private String CNPJ; //nivel 3
	
	public String getRNTRC() {
		return RNTRC;
	}
	public void setRNTRC(String rNTRC) {
		RNTRC = rNTRC;
	}
	public MdfeInfCIOT getInfCIOT() {
		return infCIOT;
	}
	public void setInfCIOT(MdfeInfCIOT infCIOT) {
		this.infCIOT = infCIOT;
	}
	public MdfeValePed getValePed() {
		return valePed;
	}
	public void setValePed(MdfeValePed valePed) {
		this.valePed = valePed;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	
}
