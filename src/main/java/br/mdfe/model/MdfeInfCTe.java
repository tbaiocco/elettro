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
public class MdfeInfCTe {
    private String chCTe;
    private String segCodBarra;
    private ArrayList<MdfeInfUnidTransp> infUnidTransp;
    
    

    public String getChCTe() {
        return chCTe;
    }

    public void setChCTe(String chCTe) {
        this.chCTe = chCTe;
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
