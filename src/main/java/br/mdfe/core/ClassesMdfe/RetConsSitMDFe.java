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
 * @author Rafael
 */
@XStreamAlias("retConsSitMDFe")
public class RetConsSitMDFe extends MDFeRetorno {

    @XStreamAlias("protMDFe")
    private ProtMDFe protMDFe;

    @XStreamImplicit(itemFieldName = "procEventoMDFe")  
    private List<ProcEventoMDFe> procEventoMDFe;

    public ProtMDFe getProtMDFe() {
        return protMDFe;
    }

    public void setProtMDFe(ProtMDFe protMDFe) {
        this.protMDFe = protMDFe;
    }

    public List<ProcEventoMDFe> getProcEventoMDFe() {
        return procEventoMDFe;
    }

    public void setProcEventoMDFe(List<ProcEventoMDFe> procEventoMDFe) {
        this.procEventoMDFe = procEventoMDFe;
    }

}
