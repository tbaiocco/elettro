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
public class MdfeRodo {

    private String RNTRC;
    private String CIOT;
    private MdfeInfANTT infANTT;
    private MdfeVeicTracao veicTracao;
    private ArrayList<MdfeVeicReboque> veicReboque;
    
    //mantido por questoes de compatibilidade
    private ArrayList<MdfeValePed> valePed;
    
    private String codAgPorto; // nivel 1
    //lacRodo - nivel 1 grupo
    private ArrayList<String> nLacre; // nivel 2

    public String getRNTRC() {
        return RNTRC;
    }

    public void setRNTRC(String RNTRC) {
        this.RNTRC = RNTRC;
    }

    public String getCIOT() {
        return CIOT;
    }

    public void setCIOT(String CIOT) {
        this.CIOT = CIOT;
    }

    public MdfeVeicTracao getVeicTracao() {
        return veicTracao;
    }

    public void setVeicTracao(MdfeVeicTracao veicTracao) {
        this.veicTracao = veicTracao;
    }

    public ArrayList<MdfeVeicReboque> getVeicReboque() {
        return veicReboque;
    }

    public void setVeicReboque(ArrayList<MdfeVeicReboque> veicReboque) {
        this.veicReboque = veicReboque;
    }
//
//    public ArrayList<MdfeValePed> getValePed() {
//        return valePed;
//    }
//
//    public void setValePed(ArrayList<MdfeValePed> valePed) {
//        this.valePed = valePed;
//    }

	public MdfeInfANTT getInfANTT() {
		return infANTT;
	}

	public void setInfANTT(MdfeInfANTT infANTT) {
		this.infANTT = infANTT;
	}

	public String getCodAgPorto() {
		return codAgPorto;
	}

	public void setCodAgPorto(String codAgPorto) {
		this.codAgPorto = codAgPorto;
	}

	public ArrayList<String> getnLacre() {
		return nLacre;
	}

	public void setnLacre(ArrayList<String> nLacre) {
		this.nLacre = nLacre;
	}

	public ArrayList<MdfeValePed> getValePed() {
		return valePed;
	}

	public void setValePed(ArrayList<MdfeValePed> valePed) {
		this.valePed = valePed;
	}

}
