/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

/**
 *
 * @author DerliRiffel
 */
public class WebServiceCte {

    private int cUf;
    private int tpAmbiente;
    private String uf;
    private String urlRecepcao;
    private String urlRetRecepcao;
    private String urlCancelamento;
    private String urlInutilizacao;
    private String urlConsultaProtocolo;
    private String urlStatusServico;
    private String versaoPadrao;
    private String urlEvento;

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

    public String getUrlCancelamento() {
        return urlCancelamento;
    }

    public void setUrlCancelamento(String urlCancelamento) {
        this.urlCancelamento = urlCancelamento;
    }

    public String getUrlConsultaProtocolo() {
        return urlConsultaProtocolo;
    }

    public void setUrlConsultaProtocolo(String urlConsultaProtocolo) {
        this.urlConsultaProtocolo = urlConsultaProtocolo;
    }

    public String getUrlInutilizacao() {
        return urlInutilizacao;
    }

    public void setUrlInutilizacao(String urlInutilizacao) {
        this.urlInutilizacao = urlInutilizacao;
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

    public String getUrlEvento() {
        return urlEvento;
    }

    public void setUrlEvento(String urlEvento) {
        this.urlEvento = urlEvento;
    }

}
