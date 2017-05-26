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
public class MdfeInfMunDescarga {

    private Integer cMunDescarga;
    private String xMunDescarga;
    private ArrayList<MdfeInfCTe> infCTe;
    private ArrayList<MdfeInfCT> infCT;
    private ArrayList<MdfeInfNFe> infNFe;
    private ArrayList<MdfeInfNF> infNF;

    public Integer getCMunDescarga() {
        return cMunDescarga;
    }

    public void setCMunDescarga(Integer cMunDescarga) {
        this.cMunDescarga = cMunDescarga;
    }

    public String getXMunDescarga() {
        return xMunDescarga;
    }

    public void setXMunDescarga(String xMunDescarga) {
        this.xMunDescarga = xMunDescarga;
    }

    public ArrayList<MdfeInfCTe> getInfCTe() {
        return infCTe;
    }

    public void setInfCTe(ArrayList<MdfeInfCTe> infCTe) {
        this.infCTe = infCTe;
    }

    public ArrayList<MdfeInfCT> getInfCT() {
        return infCT;
    }

    public void setInfCT(ArrayList<MdfeInfCT> infCT) {
        this.infCT = infCT;
    }

    public ArrayList<MdfeInfNFe> getInfNFe() {
        return infNFe;
    }

    public void setInfNFe(ArrayList<MdfeInfNFe> infNFe) {
        this.infNFe = infNFe;
    }

    public ArrayList<MdfeInfNF> getInfNF() {
        return infNF;
    }

    public void setInfNF(ArrayList<MdfeInfNF> infNF) {
        this.infNF = infNF;
    }

}
