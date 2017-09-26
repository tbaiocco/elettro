/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.cte.core.ClassesCte.v200;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author Derli
 */
@XStreamAlias("retConsReciCTe")
public class RetConsReciCTe extends CTeRetorno{

    private String nRec;

    public String getNRec() {
        return nRec;
    }

    public void setNRec(String nRec) {
        this.nRec = nRec;
    }

    @XStreamImplicit
    private List<ProtCTe> listProtCTe;

    public List<ProtCTe> getListProtNFe() {
        return listProtCTe;
    }

    public void setListProtNFe(List<ProtCTe> listProtCTe) {
        this.listProtCTe = listProtCTe;
    }

}
