/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class CteDestinatario extends CteEndereco {

    private String CNPJ;
    private String CPF;
    private String IE;
    private String xNome;
    private String fone;
    private Integer ISUF;
    private CteLocalEntrega localEntrega;
    private String email;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getXNome() {
        return xNome;
    }

    public void setXNome(String xNome) {
        this.xNome = xNome;
    }

    public Integer getISUF() {
        return ISUF;
    }

    public void setISUF(Integer ISUF) {
        this.ISUF = ISUF;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public CteLocalEntrega getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(CteLocalEntrega localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
