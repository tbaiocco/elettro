package br.mdfe.model;

public class MdfeSeg {
	
	//infResp - nivel 2 grupo
	private Integer respSeg; //1-emi, 2-contratante
	private String CNPJ; //nivel 3
	private String CPF; //nivel 3
	
	//infSeg - nivel 2 grupo
	private String xSeg; //nivel 3
	private String cnpjSeg; //nivel 3
	
	private String nApol; //nivel 2
	private String nAver; //nivel 2
	
	public Integer getRespSeg() {
		return respSeg;
	}
	public void setRespSeg(Integer respSeg) {
		this.respSeg = respSeg;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getxSeg() {
		return xSeg;
	}
	public void setxSeg(String xSeg) {
		this.xSeg = xSeg;
	}
	public String getCnpjSeg() {
		return cnpjSeg;
	}
	public void setCnpjSeg(String cnpjSeg) {
		this.cnpjSeg = cnpjSeg;
	}
	public String getnApol() {
		return nApol;
	}
	public void setnApol(String nApol) {
		this.nApol = nApol;
	}
	public String getnAver() {
		return nAver;
	}
	public void setnAver(String nAver) {
		this.nAver = nAver;
	}
	
}
