/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.model;

/**
 *
 * @author DerliRiffel
 */
public class WebService {

    private int cUf;
    private int tpAmbiente;
    private String uf;
    private String urlRecepcao;
    private String urlRetRecepcao;
    private String urlStatusServico;
    private String urlConsultaMdfe;
    private String versaoPadrao;
    private String urlRecepcaoEvento;

    public int getcUf() {
        return cUf;
    }

    public void setcUf(int cUf) {
        this.cUf = cUf;
    }

    public int getTpAmbiente() {
        return tpAmbiente;
    }

    public void setTpAmbiente(int tpAmbiente) {
        this.tpAmbiente = tpAmbiente;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }


    public String getUrlRecepcao() {
        return urlRecepcao;
    }

    public void setUrlRecepcao(String urlRecepcao) {
        this.urlRecepcao = urlRecepcao;
    }

    public String getUrlRetRecepcao() {
        return urlRetRecepcao;
    }

    public void setUrlRetRecepcao(String urlRetRecepcao) {
        this.urlRetRecepcao = urlRetRecepcao;
    }

    public String getUrlStatusServico() {
        return urlStatusServico;
    }

    public void setUrlStatusServico(String urlStatusServico) {
        this.urlStatusServico = urlStatusServico;
    }

    public String getVersaoPadrao() {
        return versaoPadrao;
    }

    public void setVersaoPadrao(String versaoPadrao) {
        this.versaoPadrao = versaoPadrao;
    }

    public String getUrlRecepcaoEvento() {
        return urlRecepcaoEvento;
    }

    public void setUrlRecepcaoEvento(String urlRecepcaoEvento) {
        this.urlRecepcaoEvento = urlRecepcaoEvento;
    }

    public String getUrlConsultaMdfe() {
        return urlConsultaMdfe;
    }

    public void setUrlConsultaMdfe(String urlConsultaMdfe) {
        this.urlConsultaMdfe = urlConsultaMdfe;
    }

}
