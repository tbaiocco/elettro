/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author DerliRiffel
 */
public class Cte {

	private String versao;
	
    public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	private Empresa empresa;
    private String chaveAcesso;
    private Integer cUF;
    private Integer cCT;
    private Integer CFOP;
    private String natOp;
    private Integer forPag;
    private String mod;
    private Integer serie;
    private Integer nCT;
    private Date dhEmi;
    private Integer tpImp;
    private Integer tpEmis;
    private Integer cDV;
    private Integer tpAmb;
    private Integer tpCTe;
    private Integer procEmi;
    private String verProc;
    private String refCTE;
    private Integer cMunEnv;
    private String xMunEnv;
    private String UFEEnv;
    private Integer modal;
    private Integer tpServ;
    private Integer cMunIni;
    private String xMunIni;
    private String UFIni;
    private Integer cMunFim;
    private String xMunFim;
    private String UFFim;
    private Integer retira;
    private String xDetRetira;
    private String indIEToma; // 1 - contribuinte, 2 - isento, 9 - nao contribuinte
    private CteTomador toma;
    private CteCompl compl;
    private CteEmitente emitente;
    private CteRemetente remetente;
    private CteExpedidor expedidor;
    private CteRecebedor recebedor;
    private CteDestinatario destinatario;
    private CteRodo rodo;
    private Double vTPrest;
    private Double vRec;
    private Collection<CteCompPrestacaoServico> compPrestacaoServico;
    private CteImposto imp;
    private CteInfCTeNorm infCTeNorm;
    private CteInfCteComp infCTeComp;
    //protocolos
    private String verAPlic;
    private Date dhRecbto;
    private int cStat;
    private String xMotivo;
    private String cancelVerAPlic;
    private Date cancelDhRecbto;
    private int cancelCStat;
    private String cancelXMotivo;
    private String cancelNProt;
    private int cancelCUf;
    private String protocolo;
    private String digestValue;
    private String justificativaCancelamento;
    private CteInfModal infModal;
    
    private CteInfAnu infAnu;
    
    private String QRCode;

    public CteInfAnu getInfAnu() {
        return infAnu;
    }

    public void setInfAnu(CteInfAnu infAnu) {
        this.infAnu = infAnu;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    /**
     * Chave de acesso da nfe
     *
     * @param chaveAcesso
     */
    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    /**
     * Desc: C�digo da UF do emitente do CT-e. Obs: Utilizar a Tabela do IBGE.
     */
    public Integer getCUF() {
        return cUF;
    }

    /**
     * Desc: C�digo da UF do emitente do CT-e. Obs: Utilizar a Tabela do IBGE.
     * Tam.Max: 2
     *
     * @param cUF
     */
    public void setCUF(Integer cUF) {
        this.cUF = cUF;
    }

    /**
     * Desc: C�digo num�rico que comp�e a Chave Obs: N�mero aleat�rio gerado
     * pelo emitente
     */
    public Integer getCCT() {
        return cCT;
    }

    /**
     * Desc: C�digo num�rico que comp�e a Chave Obs: N�mero aleat�rio gerado
     * pelo emitente Tam.Max: 9
     *
     * @param cCT
     */
    public void setCCT(Integer cCT) {
        this.cCT = cCT;
    }

    /**
     * Desc: C�digo Fiscal de Opera��es e Obs:
     */
    public Integer getCFOP() {
        return CFOP;
    }

    /**
     * Desc: C�digo Fiscal de Opera��es e Obs: Tam.Max: 4
     *
     * @param CFOP
     */
    public void setCFOP(Integer CFOP) {
        this.CFOP = CFOP;
    }

    /**
     * Desc: Natureza da Opera��o Obs:
     */
    public String getNatOp() {
        return natOp;
    }

    /**
     * Desc: Natureza da Opera��o Obs: Tam.Max: 60
     *
     * @param natOp
     */
    public void setNatOp(String natOp) {
        this.natOp = natOp;
    }

    /**
     * Desc: Forma de pagamento do servi�o Obs: 0 - Pago
     */
    public Integer getForPag() {
        return forPag;
    }

    /**
     * Desc: Forma de pagamento do servi�o Obs: 0 - Pago Tam.Max: 1
     *
     * @param forPag
     */
    public void setForPag(Integer forPag) {
        this.forPag = forPag;
    }

    /**
     * Desc: Modelo do documento fiscal Obs: Utilizar o c�digo 57 para
     * identifica��o do cte
     */
    public String getMod() {
        return mod;
    }

    /**
     * Desc: Modelo do documento fiscal Obs: Utilizar o c�digo 57 para
     * identifica��o do cte Tam.Max: 2
     *
     * @param mod
     */
    public void setMod(String mod) {
        this.mod = mod;
    }

    /**
     * Desc: S�rie do CT-e Obs: Preencher com 0 no caso de s�rie �nica
     */
    public Integer getSerie() {
        return serie;
    }

    /**
     * Desc: S�rie do CT-e Obs: Preencher com 0 no caso de s�rie �nica Tam.Max:
     * 3
     *
     * @param serie
     */
    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    /**
     * Desc: N�mero do CT-e Obs:
     */
    public Integer getNCT() {
        return nCT;
    }

    /**
     * Desc: N�mero do CT-e Obs: Tam.Max: 9
     *
     * @param nCT
     */
    public void setNCT(Integer nCT) {
        this.nCT = nCT;
    }

    /**
     * Desc: Data e hora de emiss�o do CT-e Obs: Formato AAAA-MM-DDTHH:MM:DD
     */
    public Date getDhEmi() {
        return dhEmi;
    }

    /**
     * Desc: Data e hora de emiss�o do CT-e Obs: Formato AAAA-MM-DDTHH:MM:DD
     * Tam.Max: 19
     *
     * @param dhEmi
     */
    public void setDhEmi(Date dhEmi) {
        this.dhEmi = dhEmi;
    }

    /**
     * Desc: Formato de impress�o do DACTE Obs: 1-retrato 2-paisagem
     */
    public Integer getTpImp() {
        return tpImp;
    }

    /**
     * Desc: Formato de impress�o do DACTE Obs: 1-retrato 2-paisagem Tam.Max: 1
     *
     * @param tpImp
     */
    public void setTpImp(Integer tpImp) {
        this.tpImp = tpImp;
    }

    /**
     * Desc: Forma de emiss�o do CT-e Obs: 1-normal 2-contingencia
     */
    public Integer getTpEmis() {
        return tpEmis;
    }

    /**
     * Desc: Forma de emiss�o do CT-e Obs: 1-normal 2-contingencia Tam.Max: 1
     *
     * @param tpEmis
     */
    public void setTpEmis(Integer tpEmis) {
        this.tpEmis = tpEmis;
    }

    /**
     * Desc: Digito Verificador da chave de acesso cte Obs: Informar o d�gito de
     * controle da chave
     */
    public Integer getCDV() {
        return cDV;
    }

    /**
     * Desc: Digito Verificador da chave de acesso cte Obs: Informar o d�gito de
     * controle da chave Tam.Max: 1
     *
     * @param cDV
     */
    public void setCDV(Integer cDV) {
        this.cDV = cDV;
    }

    /**
     * Desc: Tipo do Ambiente: Obs: 1-producao 2 - homologacao
     */
    public Integer getTpAmb() {
        return tpAmb;
    }

    /**
     * Desc: Tipo do Ambiente: Obs: 1-producao 2 - homologacao Tam.Max: 1
     *
     * @param tpAmb
     */
    public void setTpAmb(Integer tpAmb) {
        this.tpAmb = tpAmb;
    }

    /**
     * Desc: Tipo do CT-e: Obs: 1-cte normal 2 - cte complemento valor 3-cte
     * substituto
     */
    public Integer getTpCTe() {
        return tpCTe;
    }

    /**
     * Desc: Tipo do CT-e: Obs: 1-cte normal 2 - cte complemento valor 3-cte
     * substituto Tam.Max: 1
     *
     * @param tpCTe
     */
    public void setTpCTe(Integer tpCTe) {
        this.tpCTe = tpCTe;
    }

    /**
     * Desc: Identificador do processo de emiss�o Obs: 0 - emiss�o de CT-e com
     * aplicativo do
     */
    public Integer getProcEmi() {
        return procEmi;
    }

    /**
     * Desc: Identificador do processo de emiss�o Obs: 0 - emiss�o de CT-e com
     * aplicativo do Tam.Max: 1
     *
     * @param procEmi
     */
    public void setProcEmi(Integer procEmi) {
        this.procEmi = procEmi;
    }

    /**
     * Desc: Vers�o do processo de emiss�o Obs: Iinformar a vers�o do aplicativo
     * emissor
     */
    public String getVerProc() {
        return verProc;
    }

    /**
     * Desc: Vers�o do processo de emiss�o Obs: Iinformar a vers�o do aplicativo
     * emissor Tam.Max: 20
     *
     * @param verProc
     */
    public void setVerProc(String verProc) {
        this.verProc = verProc;
    }

    /**
     * Desc: Chave de acesso do CT-e referenciado Obs:
     */
    public String getRefCTE() {
        return refCTE;
    }

    /**
     * Desc: Chave de acesso do CT-e referenciado Obs: Tam.Max: 44
     *
     * @param refCTE
     */
    public void setRefCTE(String refCTE) {
        this.refCTE = refCTE;
    }

    /**
     * Desc: C�digo do Munic�pio onde o CT-e est� Obs: Utilizar a tabela do
     * IBGE.
     */
    public Integer getCMunEnv() {
        return cMunEnv;
    }

    /**
     * Desc: C�digo do Munic�pio onde o CT-e est� Obs: Utilizar a tabela do
     * IBGE. Tam.Max: 7
     *
     * @param cMunEnv
     */
    public void setCMunEnv(Integer cMunEnv) {
        this.cMunEnv = cMunEnv;
    }

    /**
     * Desc: Nome do Munic�pio onde o CT-e est� Obs: Informar 'EXTERIOR' para as
     * opera��es com exterior
     */
    public String getXMunEnv() {
        return xMunEnv;
    }

    /**
     * Desc: Nome do Munic�pio onde o CT-e est� Obs: Informar 'EXTERIOR' para as
     * opera��es com exterior Tam.Max: 60
     *
     * @param xMunEnv
     */
    public void setXMunEnv(String xMunEnv) {
        this.xMunEnv = xMunEnv;
    }

    /**
     * Desc: Sigla da UF onde o CT-e est� sendo Obs: Informar 'EX' para
     * opera��es com o
     */
    public String getUFEnv() {
        return UFEEnv;
    }

    /**
     * Desc: Sigla da UF onde o CT-e est� sendo Obs: Informar 'EX' para
     * opera��es com o Tam.Max: 2
     *
     * @param UFEnv
     */
    public void setUFEnv(String UFEnv) {
        this.UFEEnv = UFEnv;
    }

    /**
     * Desc: Modal: Obs: 01-rodoviario 02-a�reo 03-aquaviario 04-ferroviario
     * 05-dutoviario
     */
    public Integer getModal() {
        return modal;
    }

    /**
     * Desc: Modal: Obs: 01-rodoviario 02-a�reo 03-aquaviario 04-ferroviario
     * 05-dutoviario Tam.Max: 2
     *
     * @param modal
     */
    public void setModal(Integer modal) {
        this.modal = modal;
    }

    /**
     * Desc: Tipo do Servi�o: Obs: 0 - Normal
     */
    public Integer getTpServ() {
        return tpServ;
    }

    /**
     * Desc: Tipo do Servi�o: Obs: 0 - Normal Tam.Max: 1
     *
     * @param tpServ
     */
    public void setTpServ(Integer tpServ) {
        this.tpServ = tpServ;
    }

    /**
     * Desc: C�digo do Munic�pio de in�cio da presta��o Obs: Utilizar a tabela
     * do IBGE. Informar
     */
    public Integer getCMunIni() {
        return cMunIni;
    }

    /**
     * Desc: C�digo do Munic�pio de in�cio da presta��o Obs: Utilizar a tabela
     * do IBGE. Informar Tam.Max: 7
     *
     * @param cMunIni
     */
    public void setCMunIni(Integer cMunIni) {
        this.cMunIni = cMunIni;
    }

    /**
     * Desc: Nome do Munic�pio do in�cio da presta��o Obs: Informar 'EXTERIOR'
     * para opera��es com o exterior.
     */
    public String getXMunIni() {
        return xMunIni;
    }

    /**
     * Desc: Nome do Munic�pio do in�cio da presta��o Obs: Informar 'EXTERIOR'
     * para opera��es com o exterior. Tam.Max: 60
     *
     * @param xMunIni
     */
    public void setXMunIni(String xMunIni) {
        this.xMunIni = xMunIni;
    }

    /**
     * Desc: UF do in�cio da presta��o Obs: Informar 'EX' para opera��es com o
     * exterior
     */
    public String getUFIni() {
        return UFIni;
    }

    /**
     * Desc: UF do in�cio da presta��o Obs: Informar 'EX' para opera��es com o
     * exterior Tam.Max: 2
     *
     * @param UFIni
     */
    public void setUFIni(String UFIni) {
        this.UFIni = UFIni;
    }

    /**
     * Desc: C�digo do Munic�pio de t�rmino da Obs: Utilizar a tabela do IBGE.
     */
    public Integer getCMunFim() {
        return cMunFim;
    }

    /**
     * Desc: C�digo do Munic�pio de t�rmino da Obs: Utilizar a tabela do IBGE.
     * Tam.Max: 7
     *
     * @param cMunFim
     */
    public void setCMunFim(Integer cMunFim) {
        this.cMunFim = cMunFim;
    }

    /**
     * Desc: Nome do Munic�pio do t�rmino da Obs: Informar 'EXTERIOR' para
     * opera��es com exterior
     */
    public String getXMunFim() {
        return xMunFim;
    }

    /**
     * Desc: Nome do Munic�pio do t�rmino da Obs: Informar 'EXTERIOR' para
     * opera��es com exterior Tam.Max: 60
     *
     * @param xMunFim
     */
    public void setXMunFim(String xMunFim) {
        this.xMunFim = xMunFim;
    }

    /**
     * Desc: UF do t�rmino da presta��o Obs: Informar 'EX' para opera��es com o
     * exterior
     */
    public String getUFFim() {
        return UFFim;
    }

    /**
     * Desc: UF do t�rmino da presta��o Obs: Informar 'EX' para opera��es com o
     * exterior Tam.Max: 2
     *
     * @param UFFim
     */
    public void setUFFim(String UFFim) {
        this.UFFim = UFFim;
    }

    /**
     * Desc: Indicador se o Recebedor retira no Aeroporto, Filial, Porto ou
     * Esta��o Obs: 0-sim 1-n�o
     */
    public Integer getRetira() {
        return retira;
    }

    /**
     * Desc: Indicador se o Recebedor retira no Aeroporto, Filial, Porto ou
     * Esta��o Obs: 0-sim 1-n�o Tam.Max: 1
     *
     * @param retira
     */
    public void setRetira(Integer retira) {
        this.retira = retira;
    }

    /**
     * Desc: Detalhes do retira Obs:
     */
    public String getXDetRetira() {
        return xDetRetira;
    }

    /**
     * Desc: Detalhes do retira Obs: Tam.Max: 160
     *
     * @param xDetRetira
     */
    public void setXDetRetira(String xDetRetira) {
        this.xDetRetira = xDetRetira;
    }

    public CteTomador getToma() {
        return toma;
    }

    /**
     * TOMADOR DO SERVICO
     *
     * @param toma
     */
    public void setToma(CteTomador toma) {
        this.toma = toma;
    }

    public CteCompl getCompl() {
        return compl;
    }

    /**
     * Dados complementares do CT-e para fins operacionais ou comerciais
     *
     * @param compl
     */
    public void setCompl(CteCompl compl) {
        this.compl = compl;
    }

    public CteRemetente getRemetente() {
        return remetente;
    }

    /**
     * Dados do destinatario
     *
     * @param destinatario
     */
    public void setRemetente(CteRemetente remetente) {
        this.remetente = remetente;
    }

    public CteEmitente getEmitente() {
        return emitente;
    }

    /**
     * Dados do emitente
     *
     * @param emitente
     */
    public void setEmitente(CteEmitente emitente) {
        this.emitente = emitente;
    }

    public CteExpedidor getExpedidor() {
        return expedidor;
    }

    public void setExpedidor(CteExpedidor expedidor) {
        this.expedidor = expedidor;
    }

    public CteDestinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(CteDestinatario destinatario) {
        this.destinatario = destinatario;
    }

    public CteRecebedor getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(CteRecebedor recebedor) {
        this.recebedor = recebedor;
    }

    public Double getVRec() {
        return vRec;
    }

    public void setVRec(Double vRec) {
        this.vRec = vRec;
    }

    public Double getVTPrest() {
        return vTPrest;
    }

    public void setVTPrest(Double vTPrest) {
        this.vTPrest = vTPrest;
    }

    public Collection<CteCompPrestacaoServico> getCompPrestacaoServico() {
        return compPrestacaoServico;
    }

    public void setCompPrestacaoServico(Collection<CteCompPrestacaoServico> compPrestacaoServico) {
        this.compPrestacaoServico = compPrestacaoServico;
    }

    public CteImposto getImp() {
        return imp;
    }

    /**
     * Informa��es relativas aos Impostos Informa��es relativas ao ICMS
     *
     * @param imp
     */
    public void setImp(CteImposto imp) {
        this.imp = imp;
    }

    public CteInfCTeNorm getInfCTeNorm() {
        return infCTeNorm;
    }

    /**
     * Grupo de informa��es do CT-e Normal e Substituto
     *
     * @param infCTeNorm
     */
    public void setInfCTeNorm(CteInfCTeNorm infCTeNorm) {
        this.infCTeNorm = infCTeNorm;
    }

    public CteRodo getRodo() {
        return rodo;
    }

    /**
     * Informa��es do modal Rodovi�rio
     *
     * @param rodo
     */
    public void setRodo(CteRodo rodo) {
        this.rodo = rodo;
    }

    public CteInfCteComp getInfCTeComp() {
        return infCTeComp;
    }

    /**
     * Detalhamento do CT-e complementado
     *
     * @param infCTeComp
     */
    public void setInfCTeComp(CteInfCteComp infCTeComp) {
        this.infCTeComp = infCTeComp;
    }

    public int getCStat() {
        return cStat;
    }

    public void setCStat(int cStat) {
        this.cStat = cStat;
    }

    public int getCancelCStat() {
        return cancelCStat;
    }

    public void setCancelCStat(int cancelCStat) {
        this.cancelCStat = cancelCStat;
    }

    public int getCancelCUf() {
        return cancelCUf;
    }

    public void setCancelCUf(int cancelCUf) {
        this.cancelCUf = cancelCUf;
    }

    public Date getCancelDhRecbto() {
        return cancelDhRecbto;
    }

    public void setCancelDhRecbto(Date cancelDhRecbto) {
        this.cancelDhRecbto = cancelDhRecbto;
    }

    public String getCancelNProt() {
        return cancelNProt;
    }

    public void setCancelNProt(String cancelNProt) {
        this.cancelNProt = cancelNProt;
    }

    public String getCancelVerAPlic() {
        return cancelVerAPlic;
    }

    public void setCancelVerAPlic(String cancelVerAPlic) {
        this.cancelVerAPlic = cancelVerAPlic;
    }

    public String getCancelXMotivo() {
        return cancelXMotivo;
    }

    public void setCancelXMotivo(String cancelXMotivo) {
        this.cancelXMotivo = cancelXMotivo;
    }

    public Date getDhRecbto() {
        return dhRecbto;
    }

    public void setDhRecbto(Date dhRecbto) {
        this.dhRecbto = dhRecbto;
    }

    public String getDigestValue() {
        return digestValue;
    }

    public void setDigestValue(String digestValue) {
        this.digestValue = digestValue;
    }

    public String getJustificativaCancelamento() {
        return justificativaCancelamento;
    }

    public void setJustificativaCancelamento(String justificativaCancelamento) {
        this.justificativaCancelamento = justificativaCancelamento;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getVerAPlic() {
        return verAPlic;
    }

    public void setVerAPlic(String verAPlic) {
        this.verAPlic = verAPlic;
    }

    public String getXMotivo() {
        return xMotivo;
    }

    public void setXMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }

    public CteInfModal getInfModal() {
        return infModal;
    }

    public void setInfModal(CteInfModal infModal) {
        this.infModal = infModal;
    }

	public String getIndIEToma() {
		return indIEToma;
	}

	public void setIndIEToma(String indIEToma) {
		this.indIEToma = indIEToma;
	}

	public String getQRCode() {
		return QRCode;
	}

	public void setQRCode(String qRCode) {
		QRCode = qRCode;
	}
}
