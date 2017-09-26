package br.cte.model;

import java.util.Collection;

/**
 * Documentos de Transporte Anterior
 *
 * @author DerliRiffel
 */
public class CteDocAnt {

    private String CNPJ;
    private String CPF;
    private String IE;
    private String UF;
    private String xNome;
    private Collection<CteIdDocAnt> idDocAnt;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getXNome() {
        return xNome;
    }

    public void setXNome(String xNome) {
        this.xNome = xNome;
    }

    public Collection<CteIdDocAnt> getIdDocAnt() {
        return idDocAnt;
    }

    /**
     * Documentos de transporte anterior
     * eletrônicos
     *
     * @param idDocAnt
     */
    public void setIdDocAnt(Collection<CteIdDocAnt> idDocAnt) {
        this.idDocAnt = idDocAnt;
    }

}
