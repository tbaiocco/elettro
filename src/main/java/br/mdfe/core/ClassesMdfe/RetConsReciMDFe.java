/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.mdfe.core.ClassesMdfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author Derli
 */
@XStreamAlias("retConsReciMDFe")
public class RetConsReciMDFe extends MDFeRetorno{

    private String nRec;

    public String getNRec() {
        return nRec;
    }

    public void setNRec(String nRec) {
        this.nRec = nRec;
    }

    @XStreamImplicit
    private List<ProtMDFe> listProtMDFe;

    public List<ProtMDFe> getListProtNFe() {
        return listProtMDFe;
    }

    public void setListProtNFe(List<ProtMDFe> listProtMDFe) {
        this.listProtMDFe = listProtMDFe;
    }

}
