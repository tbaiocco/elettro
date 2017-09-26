/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.bean;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author DerliRiffel
 */
public class BeanDacte {
    //informacoes de cabecalho cte

    private String modal;
    private String modelo;
    private String serie;
    private String numero;
    private Date dataEmissao;
    private String chave;
    private String protocolo;
    private String tipoCte;
    private String tipoServico;
    private String tomadorServico;
    private String formaPagamento;
    private String naturezaOperacao;
    private String origemPrestacao;
    //destinatario
    private String destinatarioSuframa;
    private String destinoPrestacao;
    private String destinatarioNome;
    private String destinatarioLogradouro;
    private String destinatarioNro;
    private String destinatarioCpl;
    private String destinatarioBairro;
    private String destinatarioCep;
    private String destinatarioMun;
    private String destinatarioUf;
    private String destinatarioPais;
    private String destinatarioIe;
    private String destinatarioCnpj;
    private String destinatarioFone;
    //emitente
    private String emitenteNome;
    private String emitenteLogradouro;
    private String emitenteNro;
    private String emitenteCpl;
    private String emitenteBairro;
    private String emitenteCep;
    private String emitenteMun;
    private String emitenteUf;
    private String emitentePais;
    private String emitenteIe;
    private String emitenteCnpj;
    private String emitenteFone;
    //remetente
    private String remetenteNome;
    private String remetenteLogradouro;
    private String remetenteNro;
    private String remetenteCpl;
    private String remetenteBairro;
    private String remetenteCep;
    private String remetenteMun;
    private String remetenteUf;
    private String remetentePais;
    private String remetenteIe;
    private String remetenteCnpj;
    private String remetenteFone;
    //expedidor
    private String expedidorNome;
    private String expedidorLogradouro;
    private String expedidorNro;
    private String expedidorCpl;
    private String expedidorBairro;
    private String expedidorCep;
    private String expedidorMun;
    private String expedidorUf;
    private String expedidorPais;
    private String expedidorIe;
    private String expedidorCnpj;
    private String expedidorFone;
    //recebedor
    private String recebedorNome;
    private String recebedorLogradouro;
    private String recebedorNro;
    private String recebedorCpl;
    private String recebedorBairro;
    private String recebedorCep;
    private String recebedorMun;
    private String recebedorUf;
    private String recebedorPais;
    private String recebedorIe;
    private String recebedorCnpj;
    private String recebedorFone;
    //tomador
    private String tomadorNome;
    private String tomadorLogradouro;
    private String tomadorNro;
    private String tomadorCpl;
    private String tomadorBairro;
    private String tomadorCep;
    private String tomadorMun;
    private String tomadorUf;
    private String tomadorPais;
    private String tomadorIe;
    private String tomadorCnpj;
    private String tomadorFone;
    //
    private String produtoPredominante;
    private String outrasCaracteristicas;
    private Double valorTotalMercadoria;
    //seguradora
    private String nomeSeguradora;
    private String responsavelSeguro;
    private String numeroApolice;
    private String numeroAverbacao;
    //
    private Double valorTotalServico;
    private Double valorReceber;
    private String situacaoTributaria;
    private Double baseCalculo;
    private Double aliquotaIcms;
    private Double valorIcms;
    private Double reducaoBaseCalculo;
    private Double icmsSt;
    private Double totTrib;

    public Double getTotTrib() {
        return totTrib;
    }

    public void setTotTrib(Double totTrib) {
        this.totTrib = totTrib;
    }
    //QNT. / UN. MEDIDA
    private String nmUnMed1;
    private String nmUnMed2;
    private String nmUnMed3;
    private String nmUnMed4;
    private String nmUnMed5;
    private Double qntUnMed1;
    private Double qntUnMed2;
    private Double qntUnMed3;
    private Double qntUnMed4;
    private Double qntUnMed5;
    //
    private String observacoes;
    private String informacoesEspecificas;
    private String usoExclusivoEmissor;
    private Collection<BeanDacteDocs> dacteDocs;
    private Collection<BeanDacteComp> dacteComp;

    private String rntc;
    private String lotacao;
    private String dtPrevisao;

    private String nrPlaca;
    private String nrPlacaC1;
    private String nrPlacaC2;
    private String nrPlacaC3;
    private String ufPlaca;
    private String ufPlacaC1;
    private String ufPlacaC2;
    private String ufPlacaC3;
    private String rntcPlaca;
    private String rntcPlacaC1;
    private String rntcPlacaC2;
    private String rntcPlacaC3;
    private String nmMotorista;
    private String nrMotorista;

    //aereo
    private String nOCA;
    private String xOrig;
    private String xPass;
    private String xDest;
    private String CL;
    private String cTar;
    private Double vTar;
    private String retira;
    private String xDetRetira;
    private String idT;
    private String xDime;
    private String cInfManu;
    private String cImp;
    private String xLagEmi;
    private String nMinu;

    //PERI
    private String nONU1;
    private String xNomeAE1;
    private String xClaRisco1;
    private String grEmb1;
    private String nONU2;
    private String xNomeAE2;
    private String xClaRisco2;
    private String grEmb2;
    private String nONU3;
    private String xNomeAE3;
    private String xClaRisco3;
    private String grEmb3;
    private String nONU4;
    private String xNomeAE4;
    private String xClaRisco4;
    private String grEmb4;
    
    private Date dataVcto;

    public Date getDataVcto() {
        return dataVcto;
    }

    public void setDataVcto(Date dataVcto) {
        this.dataVcto = dataVcto;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public Double getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(Double aliquotaIcms) {
        this.aliquotaIcms = aliquotaIcms;
    }

    public Double getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(Double baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Collection<BeanDacteComp> getDacteComp() {
        return dacteComp;
    }

    public void setDacteComp(Collection<BeanDacteComp> dacteComp) {
        this.dacteComp = dacteComp;
    }

    public Collection<BeanDacteDocs> getDacteDocs() {
        return dacteDocs;
    }

    public void setDacteDocs(Collection<BeanDacteDocs> dacteDocs) {
        this.dacteDocs = dacteDocs;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDestinatarioBairro() {
        return destinatarioBairro;
    }

    public void setDestinatarioBairro(String destinatarioBairro) {
        this.destinatarioBairro = destinatarioBairro;
    }

    public String getDestinatarioCep() {
        return destinatarioCep;
    }

    public void setDestinatarioCep(String destinatarioCep) {
        this.destinatarioCep = destinatarioCep;
    }

    public String getDestinatarioCnpj() {
        return destinatarioCnpj;
    }

    public void setDestinatarioCnpj(String destinatarioCnpj) {
        this.destinatarioCnpj = destinatarioCnpj;
    }

    public String getDestinatarioCpl() {
        return destinatarioCpl;
    }

    public void setDestinatarioCpl(String destinatarioCpl) {
        this.destinatarioCpl = destinatarioCpl;
    }

    public String getDestinatarioFone() {
        return destinatarioFone;
    }

    public void setDestinatarioFone(String destinatarioFone) {
        this.destinatarioFone = destinatarioFone;
    }

    public String getDestinatarioIe() {
        return destinatarioIe;
    }

    public void setDestinatarioIe(String destinatarioIe) {
        this.destinatarioIe = destinatarioIe;
    }

    public String getDestinatarioLogradouro() {
        return destinatarioLogradouro;
    }

    public void setDestinatarioLogradouro(String destinatarioLogradouro) {
        this.destinatarioLogradouro = destinatarioLogradouro;
    }

    public String getDestinatarioMun() {
        return destinatarioMun;
    }

    public void setDestinatarioMun(String destinatarioMun) {
        this.destinatarioMun = destinatarioMun;
    }

    public String getDestinatarioNome() {
        return destinatarioNome;
    }

    public void setDestinatarioNome(String destinatarioNome) {
        this.destinatarioNome = destinatarioNome;
    }

    public String getDestinatarioNro() {
        return destinatarioNro;
    }

    public void setDestinatarioNro(String destinatarioNro) {
        this.destinatarioNro = destinatarioNro;
    }

    public String getDestinatarioSuframa() {
        return destinatarioSuframa;
    }

    public void setDestinatarioSuframa(String destinatarioSuframa) {
        this.destinatarioSuframa = destinatarioSuframa;
    }

    public String getDestinatarioUf() {
        return destinatarioUf;
    }

    public void setDestinatarioUf(String destinatarioUf) {
        this.destinatarioUf = destinatarioUf;
    }

    public String getDestinoPrestacao() {
        return destinoPrestacao;
    }

    public void setDestinoPrestacao(String destinoPrestacao) {
        this.destinoPrestacao = destinoPrestacao;
    }

    public String getEmitenteBairro() {
        return emitenteBairro;
    }

    public void setEmitenteBairro(String emitenteBairro) {
        this.emitenteBairro = emitenteBairro;
    }

    public String getEmitenteCep() {
        return emitenteCep;
    }

    public void setEmitenteCep(String emitenteCep) {
        this.emitenteCep = emitenteCep;
    }

    public String getEmitenteCnpj() {
        return emitenteCnpj;
    }

    public void setEmitenteCnpj(String emitenteCnpj) {
        this.emitenteCnpj = emitenteCnpj;
    }

    public String getEmitenteCpl() {
        return emitenteCpl;
    }

    public void setEmitenteCpl(String emitenteCpl) {
        this.emitenteCpl = emitenteCpl;
    }

    public String getEmitenteFone() {
        return emitenteFone;
    }

    public void setEmitenteFone(String emitenteFone) {
        this.emitenteFone = emitenteFone;
    }

    public String getEmitenteIe() {
        return emitenteIe;
    }

    public void setEmitenteIe(String emitenteIe) {
        this.emitenteIe = emitenteIe;
    }

    public String getEmitenteLogradouro() {
        return emitenteLogradouro;
    }

    public void setEmitenteLogradouro(String emitenteLogradouro) {
        this.emitenteLogradouro = emitenteLogradouro;
    }

    public String getEmitenteMun() {
        return emitenteMun;
    }

    public void setEmitenteMun(String emitenteMun) {
        this.emitenteMun = emitenteMun;
    }

    public String getEmitenteNome() {
        return emitenteNome;
    }

    public void setEmitenteNome(String emitenteNome) {
        this.emitenteNome = emitenteNome;
    }

    public String getEmitenteNro() {
        return emitenteNro;
    }

    public void setEmitenteNro(String emitenteNro) {
        this.emitenteNro = emitenteNro;
    }

    public String getEmitenteUf() {
        return emitenteUf;
    }

    public void setEmitenteUf(String emitenteUf) {
        this.emitenteUf = emitenteUf;
    }

    public String getExpedidorBairro() {
        return expedidorBairro;
    }

    public void setExpedidorBairro(String expedidorBairro) {
        this.expedidorBairro = expedidorBairro;
    }

    public String getExpedidorCep() {
        return expedidorCep;
    }

    public void setExpedidorCep(String expedidorCep) {
        this.expedidorCep = expedidorCep;
    }

    public String getExpedidorCnpj() {
        return expedidorCnpj;
    }

    public void setExpedidorCnpj(String expedidorCnpj) {
        this.expedidorCnpj = expedidorCnpj;
    }

    public String getExpedidorCpl() {
        return expedidorCpl;
    }

    public void setExpedidorCpl(String expedidorCpl) {
        this.expedidorCpl = expedidorCpl;
    }

    public String getExpedidorFone() {
        return expedidorFone;
    }

    public void setExpedidorFone(String expedidorFone) {
        this.expedidorFone = expedidorFone;
    }

    public String getExpedidorIe() {
        return expedidorIe;
    }

    public void setExpedidorIe(String expedidorIe) {
        this.expedidorIe = expedidorIe;
    }

    public String getExpedidorLogradouro() {
        return expedidorLogradouro;
    }

    public void setExpedidorLogradouro(String expedidorLogradouro) {
        this.expedidorLogradouro = expedidorLogradouro;
    }

    public String getExpedidorMun() {
        return expedidorMun;
    }

    public void setExpedidorMun(String expedidorMun) {
        this.expedidorMun = expedidorMun;
    }

    public String getExpedidorNome() {
        return expedidorNome;
    }

    public void setExpedidorNome(String expedidorNome) {
        this.expedidorNome = expedidorNome;
    }

    public String getExpedidorNro() {
        return expedidorNro;
    }

    public void setExpedidorNro(String expedidorNro) {
        this.expedidorNro = expedidorNro;
    }

    public String getExpedidorUf() {
        return expedidorUf;
    }

    public void setExpedidorUf(String expedidorUf) {
        this.expedidorUf = expedidorUf;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getIcmsSt() {
        return icmsSt;
    }

    public void setIcmsSt(Double icmsSt) {
        this.icmsSt = icmsSt;
    }

    public String getInformacoesEspecificas() {
        return informacoesEspecificas;
    }

    public void setInformacoesEspecificas(String informacoesEspecificas) {
        this.informacoesEspecificas = informacoesEspecificas;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNaturezaOperacao() {
        return naturezaOperacao;
    }

    public void setNaturezaOperacao(String naturezaOperacao) {
        this.naturezaOperacao = naturezaOperacao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroApolice() {
        return numeroApolice;
    }

    public void setNumeroApolice(String numeroApolice) {
        this.numeroApolice = numeroApolice;
    }

    public String getNumeroAverbacao() {
        return numeroAverbacao;
    }

    public void setNumeroAverbacao(String numeroAverbacao) {
        this.numeroAverbacao = numeroAverbacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getOrigemPrestacao() {
        return origemPrestacao;
    }

    public void setOrigemPrestacao(String origemPrestacao) {
        this.origemPrestacao = origemPrestacao;
    }

    public String getOutrasCaracteristicas() {
        return outrasCaracteristicas;
    }

    public void setOutrasCaracteristicas(String outrasCaracteristicas) {
        this.outrasCaracteristicas = outrasCaracteristicas;
    }

    public String getProdutoPredominante() {
        return produtoPredominante;
    }

    public void setProdutoPredominante(String produtoPredominante) {
        this.produtoPredominante = produtoPredominante;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Double getQntUnMed1() {
        return qntUnMed1;
    }

    public void setQntUnMed1(Double qntUnMed1) {
        this.qntUnMed1 = qntUnMed1;
    }

    public Double getQntUnMed2() {
        return qntUnMed2;
    }

    public void setQntUnMed2(Double qntUnMed2) {
        this.qntUnMed2 = qntUnMed2;
    }

    public Double getQntUnMed3() {
        return qntUnMed3;
    }

    public void setQntUnMed3(Double qntUnMed3) {
        this.qntUnMed3 = qntUnMed3;
    }

    public Double getQntUnMed4() {
        return qntUnMed4;
    }

    public void setQntUnMed4(Double qntUnMed4) {
        this.qntUnMed4 = qntUnMed4;
    }

    public Double getQntUnMed5() {
        return qntUnMed5;
    }

    public void setQntUnMed5(Double qntUnMed5) {
        this.qntUnMed5 = qntUnMed5;
    }

    public String getRecebedorBairro() {
        return recebedorBairro;
    }

    public void setRecebedorBairro(String recebedorBairro) {
        this.recebedorBairro = recebedorBairro;
    }

    public String getRecebedorCep() {
        return recebedorCep;
    }

    public void setRecebedorCep(String recebedorCep) {
        this.recebedorCep = recebedorCep;
    }

    public String getRecebedorCnpj() {
        return recebedorCnpj;
    }

    public void setRecebedorCnpj(String recebedorCnpj) {
        this.recebedorCnpj = recebedorCnpj;
    }

    public String getRecebedorCpl() {
        return recebedorCpl;
    }

    public void setRecebedorCpl(String recebedorCpl) {
        this.recebedorCpl = recebedorCpl;
    }

    public String getRecebedorFone() {
        return recebedorFone;
    }

    public void setRecebedorFone(String recebedorFone) {
        this.recebedorFone = recebedorFone;
    }

    public String getRecebedorIe() {
        return recebedorIe;
    }

    public void setRecebedorIe(String recebedorIe) {
        this.recebedorIe = recebedorIe;
    }

    public String getRecebedorLogradouro() {
        return recebedorLogradouro;
    }

    public void setRecebedorLogradouro(String recebedorLogradouro) {
        this.recebedorLogradouro = recebedorLogradouro;
    }

    public String getRecebedorMun() {
        return recebedorMun;
    }

    public void setRecebedorMun(String recebedorMun) {
        this.recebedorMun = recebedorMun;
    }

    public String getRecebedorNome() {
        return recebedorNome;
    }

    public void setRecebedorNome(String recebedorNome) {
        this.recebedorNome = recebedorNome;
    }

    public String getRecebedorNro() {
        return recebedorNro;
    }

    public void setRecebedorNro(String recebedorNro) {
        this.recebedorNro = recebedorNro;
    }

    public String getRecebedorUf() {
        return recebedorUf;
    }

    public void setRecebedorUf(String recebedorUf) {
        this.recebedorUf = recebedorUf;
    }

    public Double getReducaoBaseCalculo() {
        return reducaoBaseCalculo;
    }

    public void setReducaoBaseCalculo(Double reducaoBaseCalculo) {
        this.reducaoBaseCalculo = reducaoBaseCalculo;
    }

    public String getRemetenteBairro() {
        return remetenteBairro;
    }

    public void setRemetenteBairro(String remetenteBairro) {
        this.remetenteBairro = remetenteBairro;
    }

    public String getRemetenteCep() {
        return remetenteCep;
    }

    public void setRemetenteCep(String remetenteCep) {
        this.remetenteCep = remetenteCep;
    }

    public String getRemetenteCnpj() {
        return remetenteCnpj;
    }

    public void setRemetenteCnpj(String remetenteCnpj) {
        this.remetenteCnpj = remetenteCnpj;
    }

    public String getRemetenteCpl() {
        return remetenteCpl;
    }

    public void setRemetenteCpl(String remetenteCpl) {
        this.remetenteCpl = remetenteCpl;
    }

    public String getRemetenteFone() {
        return remetenteFone;
    }

    public void setRemetenteFone(String remetenteFone) {
        this.remetenteFone = remetenteFone;
    }

    public String getRemetenteIe() {
        return remetenteIe;
    }

    public void setRemetenteIe(String remetenteIe) {
        this.remetenteIe = remetenteIe;
    }

    public String getRemetenteLogradouro() {
        return remetenteLogradouro;
    }

    public void setRemetenteLogradouro(String remetenteLogradouro) {
        this.remetenteLogradouro = remetenteLogradouro;
    }

    public String getRemetenteMun() {
        return remetenteMun;
    }

    public void setRemetenteMun(String remetenteMun) {
        this.remetenteMun = remetenteMun;
    }

    public String getRemetenteNome() {
        return remetenteNome;
    }

    public void setRemetenteNome(String remetenteNome) {
        this.remetenteNome = remetenteNome;
    }

    public String getRemetenteNro() {
        return remetenteNro;
    }

    public void setRemetenteNro(String remetenteNro) {
        this.remetenteNro = remetenteNro;
    }

    public String getRemetentePais() {
        return remetentePais;
    }

    public void setRemetentePais(String remetentePais) {
        this.remetentePais = remetentePais;
    }

    public String getRemetenteUf() {
        return remetenteUf;
    }

    public void setRemetenteUf(String remetenteUf) {
        this.remetenteUf = remetenteUf;
    }

    public String getResponsavelSeguro() {
        return responsavelSeguro;
    }

    public void setResponsavelSeguro(String responsavelSeguro) {
        this.responsavelSeguro = responsavelSeguro;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSituacaoTributaria() {
        return situacaoTributaria;
    }

    public void setSituacaoTributaria(String situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

    public String getTipoCte() {
        return tipoCte;
    }

    public void setTipoCte(String tipoCte) {
        this.tipoCte = tipoCte;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getTomadorBairro() {
        return tomadorBairro;
    }

    public void setTomadorBairro(String tomadorBairro) {
        this.tomadorBairro = tomadorBairro;
    }

    public String getTomadorCep() {
        return tomadorCep;
    }

    public void setTomadorCep(String tomadorCep) {
        this.tomadorCep = tomadorCep;
    }

    public String getTomadorCnpj() {
        return tomadorCnpj;
    }

    public void setTomadorCnpj(String tomadorCnpj) {
        this.tomadorCnpj = tomadorCnpj;
    }

    public String getTomadorCpl() {
        return tomadorCpl;
    }

    public void setTomadorCpl(String tomadorCpl) {
        this.tomadorCpl = tomadorCpl;
    }

    public String getTomadorFone() {
        return tomadorFone;
    }

    public void setTomadorFone(String tomadorFone) {
        this.tomadorFone = tomadorFone;
    }

    public String getTomadorIe() {
        return tomadorIe;
    }

    public void setTomadorIe(String tomadorIe) {
        this.tomadorIe = tomadorIe;
    }

    public String getTomadorLogradouro() {
        return tomadorLogradouro;
    }

    public void setTomadorLogradouro(String tomadorLogradouro) {
        this.tomadorLogradouro = tomadorLogradouro;
    }

    public String getTomadorMun() {
        return tomadorMun;
    }

    public void setTomadorMun(String tomadorMun) {
        this.tomadorMun = tomadorMun;
    }

    public String getTomadorNome() {
        return tomadorNome;
    }

    public void setTomadorNome(String tomadorNome) {
        this.tomadorNome = tomadorNome;
    }

    public String getTomadorNro() {
        return tomadorNro;
    }

    public void setTomadorNro(String tomadorNro) {
        this.tomadorNro = tomadorNro;
    }

    public String getTomadorPais() {
        return tomadorPais;
    }

    public void setTomadorPais(String tomadorPais) {
        this.tomadorPais = tomadorPais;
    }

    public String getTomadorServico() {
        return tomadorServico;
    }

    public void setTomadorServico(String tomadorServico) {
        this.tomadorServico = tomadorServico;
    }

    public String getTomadorUf() {
        return tomadorUf;
    }

    public void setTomadorUf(String tomadorUf) {
        this.tomadorUf = tomadorUf;
    }

    public String getUsoExclusivoEmissor() {
        return usoExclusivoEmissor;
    }

    public void setUsoExclusivoEmissor(String usoExclusivoEmissor) {
        this.usoExclusivoEmissor = usoExclusivoEmissor;
    }

    public Double getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(Double valorIcms) {
        this.valorIcms = valorIcms;
    }

    public Double getValorReceber() {
        return valorReceber;
    }

    public void setValorReceber(Double valorReceber) {
        this.valorReceber = valorReceber;
    }

    public Double getValorTotalMercadoria() {
        return valorTotalMercadoria;
    }

    public void setValorTotalMercadoria(Double valorTotalMercadoria) {
        this.valorTotalMercadoria = valorTotalMercadoria;
    }

    public Double getValorTotalServico() {
        return valorTotalServico;
    }

    public void setValorTotalServico(Double valorTotalServico) {
        this.valorTotalServico = valorTotalServico;
    }

    public String getDestinatarioPais() {
        return destinatarioPais;
    }

    public void setDestinatarioPais(String destinatarioPais) {
        this.destinatarioPais = destinatarioPais;
    }

    public String getEmitentePais() {
        return emitentePais;
    }

    public void setEmitentePais(String emitentePais) {
        this.emitentePais = emitentePais;
    }

    public String getExpedidorPais() {
        return expedidorPais;
    }

    public void setExpedidorPais(String expedidorPais) {
        this.expedidorPais = expedidorPais;
    }

    public String getRecebedorPais() {
        return recebedorPais;
    }

    public void setRecebedorPais(String recebedorPais) {
        this.recebedorPais = recebedorPais;
    }

    public String getDtPrevisao() {
        return dtPrevisao;
    }

    public void setDtPrevisao(String dtPrevisao) {
        this.dtPrevisao = dtPrevisao;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public String getNmMotorista() {
        return nmMotorista;
    }

    public void setNmMotorista(String nmMotorista) {
        this.nmMotorista = nmMotorista;
    }

    public String getNmUnMed1() {
        return nmUnMed1;
    }

    public void setNmUnMed1(String nmUnMed1) {
        this.nmUnMed1 = nmUnMed1;
    }

    public String getNmUnMed2() {
        return nmUnMed2;
    }

    public void setNmUnMed2(String nmUnMed2) {
        this.nmUnMed2 = nmUnMed2;
    }

    public String getNmUnMed3() {
        return nmUnMed3;
    }

    public void setNmUnMed3(String nmUnMed3) {
        this.nmUnMed3 = nmUnMed3;
    }

    public String getNmUnMed4() {
        return nmUnMed4;
    }

    public void setNmUnMed4(String nmUnMed4) {
        this.nmUnMed4 = nmUnMed4;
    }

    public String getNmUnMed5() {
        return nmUnMed5;
    }

    public void setNmUnMed5(String nmUnMed5) {
        this.nmUnMed5 = nmUnMed5;
    }

    public String getNomeSeguradora() {
        return nomeSeguradora;
    }

    public void setNomeSeguradora(String nomeSeguradora) {
        this.nomeSeguradora = nomeSeguradora;
    }

    public String getNrMotorista() {
        return nrMotorista;
    }

    public void setNrMotorista(String nrMotorista) {
        this.nrMotorista = nrMotorista;
    }

    public String getNrPlaca() {
        return nrPlaca;
    }

    public void setNrPlaca(String nrPlaca) {
        this.nrPlaca = nrPlaca;
    }

    public String getNrPlacaC1() {
        return nrPlacaC1;
    }

    public void setNrPlacaC1(String nrPlacaC1) {
        this.nrPlacaC1 = nrPlacaC1;
    }

    public String getNrPlacaC2() {
        return nrPlacaC2;
    }

    public void setNrPlacaC2(String nrPlacaC2) {
        this.nrPlacaC2 = nrPlacaC2;
    }

    public String getNrPlacaC3() {
        return nrPlacaC3;
    }

    public void setNrPlacaC3(String nrPlacaC3) {
        this.nrPlacaC3 = nrPlacaC3;
    }

    public String getRntc() {
        return rntc;
    }

    public void setRntc(String rntc) {
        this.rntc = rntc;
    }

    public String getRntcPlaca() {
        return rntcPlaca;
    }

    public void setRntcPlaca(String rntcPlaca) {
        this.rntcPlaca = rntcPlaca;
    }

    public String getRntcPlacaC1() {
        return rntcPlacaC1;
    }

    public void setRntcPlacaC1(String rntcPlacaC1) {
        this.rntcPlacaC1 = rntcPlacaC1;
    }

    public String getRntcPlacaC2() {
        return rntcPlacaC2;
    }

    public void setRntcPlacaC2(String rntcPlacaC2) {
        this.rntcPlacaC2 = rntcPlacaC2;
    }

    public String getRntcPlacaC3() {
        return rntcPlacaC3;
    }

    public void setRntcPlacaC3(String rntcPlacaC3) {
        this.rntcPlacaC3 = rntcPlacaC3;
    }

    public String getUfPlaca() {
        return ufPlaca;
    }

    public void setUfPlaca(String ufPlaca) {
        this.ufPlaca = ufPlaca;
    }

    public String getUfPlacaC1() {
        return ufPlacaC1;
    }

    public void setUfPlacaC1(String ufPlacaC1) {
        this.ufPlacaC1 = ufPlacaC1;
    }

    public String getUfPlacaC2() {
        return ufPlacaC2;
    }

    public void setUfPlacaC2(String ufPlacaC2) {
        this.ufPlacaC2 = ufPlacaC2;
    }

    public String getUfPlacaC3() {
        return ufPlacaC3;
    }

    public void setUfPlacaC3(String ufPlacaC3) {
        this.ufPlacaC3 = ufPlacaC3;
    }

    public String getCL() {
        return CL;
    }

    public void setCL(String CL) {
        this.CL = CL;
    }

    public String getcImp() {
        return cImp;
    }

    public void setcImp(String cImp) {
        this.cImp = cImp;
    }

    public String getcInfManu() {
        return cInfManu;
    }

    public void setcInfManu(String cInfManu) {
        this.cInfManu = cInfManu;
    }

    public String getcTar() {
        return cTar;
    }

    public void setcTar(String cTar) {
        this.cTar = cTar;
    }

    public String getIdT() {
        return idT;
    }

    public void setIdT(String idT) {
        this.idT = idT;
    }

    public String getnOCA() {
        return nOCA;
    }

    public void setnOCA(String nOCA) {
        this.nOCA = nOCA;
    }

    public String getRetira() {
        return retira;
    }

    public void setRetira(String retira) {
        this.retira = retira;
    }

    public Double getvTar() {
        return vTar;
    }

    public void setvTar(Double vTar) {
        this.vTar = vTar;
    }

    public String getxDest() {
        return xDest;
    }

    public void setxDest(String xDest) {
        this.xDest = xDest;
    }

    public String getxDetRetira() {
        return xDetRetira;
    }

    public void setxDetRetira(String xDetRetira) {
        this.xDetRetira = xDetRetira;
    }

    public String getxDime() {
        return xDime;
    }

    public void setxDime(String xDime) {
        this.xDime = xDime;
    }

    public String getxOrig() {
        return xOrig;
    }

    public void setxOrig(String xOrig) {
        this.xOrig = xOrig;
    }

    public String getxPass() {
        return xPass;
    }

    public void setxPass(String xPass) {
        this.xPass = xPass;
    }

    public String getGrEmb1() {
        return grEmb1;
    }

    public void setGrEmb1(String grEmb1) {
        this.grEmb1 = grEmb1;
    }

    public String getGrEmb2() {
        return grEmb2;
    }

    public void setGrEmb2(String grEmb2) {
        this.grEmb2 = grEmb2;
    }

    public String getGrEmb3() {
        return grEmb3;
    }

    public void setGrEmb3(String grEmb3) {
        this.grEmb3 = grEmb3;
    }

    public String getGrEmb4() {
        return grEmb4;
    }

    public void setGrEmb4(String grEmb4) {
        this.grEmb4 = grEmb4;
    }

    public String getnONU1() {
        return nONU1;
    }

    public void setnONU1(String nONU1) {
        this.nONU1 = nONU1;
    }

    public String getnONU2() {
        return nONU2;
    }

    public void setnONU2(String nONU2) {
        this.nONU2 = nONU2;
    }

    public String getnONU4() {
        return nONU4;
    }

    public void setnONU4(String nONU4) {
        this.nONU4 = nONU4;
    }

    public String getxClaRisco1() {
        return xClaRisco1;
    }

    public void setxClaRisco1(String xClaRisco1) {
        this.xClaRisco1 = xClaRisco1;
    }

    public String getxClaRisco3() {
        return xClaRisco3;
    }

    public void setxClaRisco3(String xClaRisco3) {
        this.xClaRisco3 = xClaRisco3;
    }

    public String getxClaRisco4() {
        return xClaRisco4;
    }

    public void setxClaRisco4(String xClaRisco4) {
        this.xClaRisco4 = xClaRisco4;
    }

    public String getxNomeAE1() {
        return xNomeAE1;
    }

    public void setxNomeAE1(String xNomeAE1) {
        this.xNomeAE1 = xNomeAE1;
    }

    public String getxNomeAE2() {
        return xNomeAE2;
    }

    public void setxNomeAE2(String xNomeAE2) {
        this.xNomeAE2 = xNomeAE2;
    }

    public String getxNomeAE3() {
        return xNomeAE3;
    }

    public void setxNomeAE3(String xNomeAE3) {
        this.xNomeAE3 = xNomeAE3;
    }

    public String getxNomeAE4() {
        return xNomeAE4;
    }

    public void setxNomeAE4(String xNomeAE4) {
        this.xNomeAE4 = xNomeAE4;
    }

    public String getnONU3() {
        return nONU3;
    }

    public void setnONU3(String nONU3) {
        this.nONU3 = nONU3;
    }

    public String getxClaRisco2() {
        return xClaRisco2;
    }

    public void setxClaRisco2(String xClaRisco2) {
        this.xClaRisco2 = xClaRisco2;
    }

    public String getnMinu() {
        return nMinu;
    }

    public void setnMinu(String nMinu) {
        this.nMinu = nMinu;
    }

    public String getxLagEmi() {
        return xLagEmi;
    }

    public void setxLagEmi(String xLagEmi) {
        this.xLagEmi = xLagEmi;
    }
    
}
