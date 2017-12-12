/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteLocalEntrega extends CteEndereco{

    private String CNPJ;
    private String CPF;
    private String xNome;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getXNome() {
        return xNome;
    }

    public void setXNome(String xNome) {
        this.xNome = xNome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
