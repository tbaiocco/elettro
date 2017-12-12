/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteCompPrestacaoServico {

    private String xNome;
    private Double vComp;

    public Double getVComp() {
        return vComp;
    }

    /**
     * Valor do componente
     * TAM MAX: 13,2
     * @param vComp
     */
    public void setVComp(Double vComp) {
        this.vComp = vComp;
    }

    public String getXNome() {
        return xNome;
    }

    /**
     * Nome do componente
     * TAM MAX: 15
     * @param xNome
     */
    public void setXNome(String xNome) {
        this.xNome = xNome;
    }
}
