/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.model;

/**
 *
 * @author DerliRiffel
 */
public class Empresa {

    private String razaosocial;
    private String cnpj;
    private String ie;
    private String certificado;
    private String senha;
    private String uf;
    private int cUf;
    private int cMun;
    private String mun;
    private String versao;
    private int tipoCertificado;

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public int getcMun() {
        return cMun;
    }

    public void setcMun(int cMun) {
        this.cMun = cMun;
    }

    public int getcUf() {
        return cUf;
    }

    public void setcUf(int cUf) {
        this.cUf = cUf;
    }

    public String getMun() {
        return mun;
    }

    public void setMun(String mun) {
        this.mun = mun;
    }

    public int getTipoCertificado() {
        return tipoCertificado;
    }

    public void setTipoCertificado(int tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }
    
}
