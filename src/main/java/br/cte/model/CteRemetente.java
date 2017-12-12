/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Collection;

/**
 *
 * @author DerliRiffel
 */
public class CteRemetente extends CteEndereco {

    private String CNPJ;
    private String CPF;
    private String IE;
    private String xNome;
    private String xFant;
    private String xFone;
    private Collection<CteInfNF> infNF;
    private Collection<CteInfNFe> infNFe;
    private Collection<CteInfOutros> infOutros;
    private CteLocColeta locColeta;
    private String email;

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

    public String getXFant() {
        return xFant;
    }

    public void setXFant(String xFant) {
        this.xFant = xFant;
    }

    public String getXNome() {
        return xNome;
    }

    public void setXNome(String xNome) {
        this.xNome = xNome;
    }

    public String getXFone() {
        return xFone;
    }

    public void setXFone(String xFone) {
        this.xFone = xFone;
    }

    public Collection<CteInfNF> getInfNF() {
        return infNF;
    }

    /**
     * INFORMAÇÕES DAS NOTAS FISCAIS
     *
     * @param infNF
     */
    public void setInfNF(Collection<CteInfNF> infNF) {
        this.infNF = infNF;
    }

    public Collection<CteInfNFe> getInfNFe() {
        return infNFe;
    }

    public void setInfNFe(Collection<CteInfNFe> infNFe) {
        this.infNFe = infNFe;
    }

    public Collection<CteInfOutros> getInfOutros() {
        return infOutros;
    }

    public void setInfOutros(Collection<CteInfOutros> infOutros) {
        this.infOutros = infOutros;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CteLocColeta getLocColeta() {
        return locColeta;
    }
    
    /*
     * Local da Coleta
     * Informar apenas quando diferente do endereco do remetente.
     */
    public void setLocColeta(CteLocColeta locColeta) {
        this.locColeta = locColeta;
    }

}
