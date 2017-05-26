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

}
