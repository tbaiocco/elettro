package br.mdfe.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Derli
 */
public class Mdfe {

    private Integer idMdfe;
    private String versao;
    private Integer cUF;
    private Integer tpAmb;
    private Integer tpEmit;
    //novo 3.0
    private Integer tpTransp;
    
    private Integer mod;
    private Integer nMDF;
    private Integer cMDF;
    private Integer cDV;
    private String serie;
    private String chAcesso;
    private Integer modal;
    private Date dhEmi;
    private Integer tpEmis;
    private Integer procEmi;
    private String verProc;
    private String UFIni;
    private String UFFim;
    private ArrayList<MdfeInfMunCarrega> infMunCarrega;
    private ArrayList<MdfeInfMunDescarga> infMunDescarga;
    private ArrayList<MdfeInfPercurso> infPercurso;
    private MdfeEmit emit;
    private String versaoModal;
    private MdfeRodo rodo;
    private MdfeAereo aereo;
    
    //novo 3.0
    private MdfeSeg seg;
    
    private Integer qCTe;
    private Integer qCT;
    private Integer qNFe;
    private Integer qNF;
    private Integer qMDFe;
    private Double vCarga;
    private Integer cUnid;//01 ? KG; 02 - TON
    private Double qCarga;
    private ArrayList<MdfeAutXML> autXML;
    private ArrayList<String> nLacre;
    private String infAdFisco;
    private String infCpl;
    //protocolo
    private Date dhRecbto;
    private String nProt;
    private String digVal;
    private String cStat;
    private String xMotivo;
    private String verAplic;
    private Empresa empresa;
    
    public Integer getIdMdfe() {
        return idMdfe;
    }

    public void setIdMdfe(Integer idMdfe) {
        this.idMdfe = idMdfe;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Integer getCUF() {
        return cUF;
    }

    public void setCUF(Integer cUF) {
        this.cUF = cUF;
    }

    public Integer getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(Integer tpAmb) {
        this.tpAmb = tpAmb;
    }

    public Integer getTpEmit() {
        return tpEmit;
    }

    public void setTpEmit(Integer tpEmit) {
        this.tpEmit = tpEmit;
    }

    public Integer getMod() {
        return mod;
    }

    public void setMod(Integer mod) {
        this.mod = mod;
    }

    public Integer getNMDF() {
        return nMDF;
    }

    public void setNMDF(Integer nMDF) {
        this.nMDF = nMDF;
    }

    public Integer getCMDF() {
        return cMDF;
    }

    public void setCMDF(Integer cMDF) {
        this.cMDF = cMDF;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getCDV() {
        return cDV;
    }

    public void setCDV(Integer cDV) {
        this.cDV = cDV;
    }

    public String getChAcesso() {
        return chAcesso;
    }

    public void setChAcesso(String chAcesso) {
        this.chAcesso = chAcesso;
    }

    public Integer getModal() {
        return modal;
    }

    public void setModal(Integer modal) {
        this.modal = modal;
    }

    public Date getDhEmi() {
        return dhEmi;
    }

    public void setDhEmi(Date dhEmi) {
        this.dhEmi = dhEmi;
    }

    public Integer getTpEmis() {
        return tpEmis;
    }

    public void setTpEmis(Integer tpEmis) {
        this.tpEmis = tpEmis;
    }

    public Integer getProcEmi() {
        return procEmi;
    }

    public void setProcEmi(Integer procEmi) {
        this.procEmi = procEmi;
    }

    public String getVerProc() {
        return verProc;
    }

    public void setVerProc(String verProc) {
        this.verProc = verProc;
    }

    public String getUFIni() {
        return UFIni;
    }

    public void setUFIni(String UFIni) {
        this.UFIni = UFIni;
    }

    public String getUFFim() {
        return UFFim;
    }

    public void setUFFim(String UFFim) {
        this.UFFim = UFFim;
    }

    public ArrayList<MdfeInfMunCarrega> getInfMunCarrega() {
        return infMunCarrega;
    }

    public void setInfMunCarrega(ArrayList<MdfeInfMunCarrega> infMunCarrega) {
        this.infMunCarrega = infMunCarrega;
    }

    public ArrayList<MdfeInfPercurso> getInfPercurso() {
        return infPercurso;
    }

    public void setInfPercurso(ArrayList<MdfeInfPercurso> infPercurso) {
        this.infPercurso = infPercurso;
    }

    public MdfeEmit getEmit() {
        return emit;
    }

    public void setEmit(MdfeEmit emit) {
        this.emit = emit;
    }

    public String getVersaoModal() {
        return versaoModal;
    }

    public void setVersaoModal(String versaoModal) {
        this.versaoModal = versaoModal;
    }

    public MdfeRodo getRodo() {
        return rodo;
    }

    public void setRodo(MdfeRodo rodo) {
        this.rodo = rodo;
    }

    public MdfeAereo getAereo() {
        return aereo;
    }

    public void setAereo(MdfeAereo aereo) {
        this.aereo = aereo;
    }

    public Integer getQCTe() {
        return qCTe;
    }

    public void setQCTe(Integer qCTe) {
        this.qCTe = qCTe;
    }

    public Integer getQCT() {
        return qCT;
    }

    public void setQCT(Integer qCT) {
        this.qCT = qCT;
    }

    public Integer getQNFe() {
        return qNFe;
    }

    public void setQNFe(Integer qNFe) {
        this.qNFe = qNFe;
    }

    public Integer getQNF() {
        return qNF;
    }

    public void setQNF(Integer qNF) {
        this.qNF = qNF;
    }

    public Integer getQMDFe() {
        return qMDFe;
    }

    public void setQMDFe(Integer qMDFe) {
        this.qMDFe = qMDFe;
    }

    public Double getVCarga() {
        return vCarga;
    }

    public void setVCarga(Double vCarga) {
        this.vCarga = vCarga;
    }

    public Integer getCUnid() {
        return cUnid;
    }

    public void setCUnid(Integer cUnid) {
        this.cUnid = cUnid;
    }

    public Double getQCarga() {
        return qCarga;
    }

    public void setQCarga(Double qCarga) {
        this.qCarga = qCarga;
    }

    public ArrayList<String> getNLacre() {
        return nLacre;
    }

    public void setNLacre(ArrayList<String> nLacre) {
        this.nLacre = nLacre;
    }

    public String getInfAdFisco() {
        return infAdFisco;
    }

    public void setInfAdFisco(String infAdFisco) {
        this.infAdFisco = infAdFisco;
    }

    public String getInfCpl() {
        return infCpl;
    }

    public void setInfCpl(String infCpl) {
        this.infCpl = infCpl;
    }

    public Date getDhRecbto() {
        return dhRecbto;
    }

    public void setDhRecbto(Date dhRecbto) {
        this.dhRecbto = dhRecbto;
    }

    public String getNProt() {
        return nProt;
    }

    public void setNProt(String nProt) {
        this.nProt = nProt;
    }

    public String getDigVal() {
        return digVal;
    }

    public void setDigVal(String digVal) {
        this.digVal = digVal;
    }

    public String getCStat() {
        return cStat;
    }

    public void setCStat(String cStat) {
        this.cStat = cStat;
    }

    public String getXMotivo() {
        return xMotivo;
    }

    public void setXMotivo(String xMotivo) {
        this.xMotivo = xMotivo;
    }

    public String getVerAplic() {
        return verAplic;
    }

    public void setVerAplic(String verAplic) {
        this.verAplic = verAplic;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public ArrayList<MdfeInfMunDescarga> getInfMunDescarga() {
        return infMunDescarga;
    }

    public void setInfMunDescarga(ArrayList<MdfeInfMunDescarga> infMunDescarga) {
        this.infMunDescarga = infMunDescarga;
    }

    public ArrayList<MdfeAutXML> getAutXML() {
        return autXML;
    }

    public void setAutXML(ArrayList<MdfeAutXML> autXML) {
        this.autXML = autXML;
    }

	public Integer getTpTransp() {
		return tpTransp;
	}

	public void setTpTransp(Integer tpTransp) {
		this.tpTransp = tpTransp;
	}

	public MdfeSeg getSeg() {
		return seg;
	}

	public void setSeg(MdfeSeg seg) {
		this.seg = seg;
	}

}
