/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteObs {

    private String xCampo;
    private String xTexto;

    public String getXCampo() {
        return xCampo;
    }

    /**
     * Identificação do campo
     * TAM: 20
     * @param xCampo
     */
    public void setXCampo(String xCampo) {
        this.xCampo = xCampo;
    }

    public String getXTexto() {
        return xTexto;
    }

    /**
     * Conteúdo do campo
     * TAM: 160
     * @param xTexto
     */
    public void setXTexto(String xTexto) {
        this.xTexto = xTexto;
    }
}
