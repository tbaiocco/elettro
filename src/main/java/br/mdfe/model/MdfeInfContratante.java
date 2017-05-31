package br.mdfe.model;

public class MdfeInfContratante {
	
	//infContratante - nivel 2 grupo
	private String CPF;
	private String CNPJ;

	public MdfeInfContratante() {
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