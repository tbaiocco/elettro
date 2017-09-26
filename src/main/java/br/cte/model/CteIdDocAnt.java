package br.cte.model;

import java.util.Date;

/**
 * Documentos de transporte anterior
 * eletrônicos
 *
 * @author DerliRiffel
 */
public class CteIdDocAnt {

    private String chave;
    private Integer tpDoc;
    private String serie;
    private String subser;
    private Integer nDoc;
    private Date dEmi;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Integer getNDoc() {
        return nDoc;
    }

    public void setNDoc(Integer nDoc) {
        this.nDoc = nDoc;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSubser() {
        return subser;
    }

    public void setSubser(String subser) {
        this.subser = subser;
    }

    public Integer getTpDoc() {
        return tpDoc;
    }

    public void setTpDoc(Integer tpDoc) {
        this.tpDoc = tpDoc;
    }

    public Date getDEmi() {
        return dEmi;
    }

    public void setDEmi(Date dEmi) {
        this.dEmi = dEmi;
    }
}
