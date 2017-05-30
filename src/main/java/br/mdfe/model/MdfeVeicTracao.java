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
public class MdfeVeicTracao {

    private Integer cInt;//Código interno do veículo
    private String placa;
    private String RENAVAM;
    private Double tara;//informar a Tara em KG.
    private Double capKG;
    private Double capM3;
    private MdfeProp prop;
    private ArrayList<MdfeCondutor> condutor;
    private Integer tpRod;
    private Integer tpCar;
    private String UF;

    public Integer getCInt() {
        return cInt;
    }

    public void setCInt(Integer cInt) {
        this.cInt = cInt;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getTara() {
        return tara;
    }

    public void setTara(Double tara) {
        this.tara = tara;
    }

    public Double getCapKG() {
        return capKG;
    }

    public void setCapKG(Double capKG) {
        this.capKG = capKG;
    }

    public Double getCapM3() {
        return capM3;
    }

    public void setCapM3(Double capM3) {
        this.capM3 = capM3;
    }

    public MdfeProp getProp() {
        return prop;
    }

    public void setProp(MdfeProp prop) {
        this.prop = prop;
    }

    public ArrayList<MdfeCondutor> getCondutor() {
        return condutor;
    }

    public void setCondutor(ArrayList<MdfeCondutor> condutor) {
        this.condutor = condutor;
    }

    public Integer getTpRod() {
        return tpRod;
    }

    public void setTpRod(Integer tpRod) {
        this.tpRod = tpRod;
    }

    public Integer getTpCar() {
        return tpCar;
    }

    public void setTpCar(Integer tpCar) {
        this.tpCar = tpCar;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

	public String getRENAVAM() {
		return RENAVAM;
	}

	public void setRENAVAM(String rENAVAM) {
		RENAVAM = rENAVAM;
	}

}
