/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core.xml;

import br.cte.core.CertDig;
import br.cte.model.Cte;
import br.cte.model.CteAereo;
import br.cte.model.CteAquav;
import br.cte.model.CteAutXML;
import br.cte.model.CteCompComp;
import br.cte.model.CteCompPrestacaoServico;
import br.cte.model.CteDocAnt;
import br.cte.model.CteDup;
import br.cte.model.CteFat;
import br.cte.model.CteIdDocAnt;
import br.cte.model.CteImposto;
import br.cte.model.CteInfCteSub;
import br.cte.model.CteInfNF;
import br.cte.model.CteInfNFe;
import br.cte.model.CteInfOutros;
import br.cte.model.CteInfQCarga;
import br.cte.model.CteInfUnidCarga;
import br.cte.model.CteInfUnidTransp;
import br.cte.model.CteLacRodo;
import br.cte.model.CteLacUnidTransp;
import br.cte.model.CteMoto;
import br.cte.model.CteObs;
import br.cte.model.CteOcc;
import br.cte.model.CtePass;
import br.cte.model.CtePeri;
import br.cte.model.CteRodo;
import br.cte.model.CteSeg;
import br.cte.model.CteValePed;
import br.cte.model.CteVeic;
import br.cte.model.CteVeicNovos;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import br.utils.Arquivo;
import br.utils.Configuracoes;
import br.utils.Utils;

/**
 *
 * @author DerliRiffel
 */
public class XmlEmissaoCte {

    private static XmlEmissaoCte instancia = new XmlEmissaoCte();
    private SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") {
	    public StringBuffer format(Date date, StringBuffer toAppendTo, java.text.FieldPosition pos) {
	        StringBuffer toFix = super.format(date, toAppendTo, pos);
	        return toFix.insert(toFix.length()-2, ':');
	    };
	};

    private XmlEmissaoCte() {
    }

    public static XmlEmissaoCte getInstance() {
        return (instancia);
    }

    public void geraXmlCteLiberada(Cte cte, String versao) {
        String dadosMsgXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<cteProc xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\"" + versao + "\">";

        String xml = getXml(cte, versao);

        String nomeArquivoNota = Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + Utils.getInstance().getDigitos(cte.getEmitente().getCNPJ()) + System.getProperty("file.separator") + "cte-" + cte.getNCT() + ".xml";
        String nomeArquivoNotaAss = Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + Utils.getInstance().getDigitos(cte.getEmitente().getCNPJ()) + System.getProperty("file.separator") + "cte-" + cte.getNCT() + ".xml";
        Arquivo a = new Arquivo(nomeArquivoNota);
        a.abrirEscrita();
        a.escreverLinha(xml);
        a.fecharArquivo();

        CertDig.getInstance().Ass(nomeArquivoNota, nomeArquivoNotaAss, "1", cte.getEmpresa());

        Arquivo aAssinado = new Arquivo(nomeArquivoNota);
        aAssinado.abrirLeitura();
        dadosMsgXml += aAssinado.ler();
        aAssinado.fecharArquivo();
        dadosMsgXml += "<protCTe versao=\"" + versao + "\">";
        dadosMsgXml += "<infProt>";
        dadosMsgXml += "<tpAmb>" + cte.getTpAmb() + "</tpAmb>";
        dadosMsgXml += "<verAplic>" + cte.getVerAPlic() + "</verAplic>";
        dadosMsgXml += "<chCTe>" + cte.getChaveAcesso() + "</chCTe>";
        dadosMsgXml += "<dhRecbto>" + formatador.format(cte.getDhRecbto()) + "T" + Utils.getInstance().convertHoraToString(cte.getDhRecbto()) + ":00</dhRecbto>";
        dadosMsgXml += "<nProt>" + cte.getProtocolo() + "</nProt>";
        dadosMsgXml += "<digVal>" + cte.getDigestValue() + "</digVal>";
        dadosMsgXml += "<cStat>" + cte.getCStat() + "</cStat>";
        dadosMsgXml += "<xMotivo>" + cte.getXMotivo() + "</xMotivo>";
        dadosMsgXml += "</infProt>";
        dadosMsgXml += "</protCTe>";

        dadosMsgXml += "</cteProc>";
        String nomeArquivoLote = Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + Utils.getInstance().getDigitos(cte.getEmitente().getCNPJ()) + System.getProperty("file.separator") + "" + cte.getChaveAcesso() + "-procCte.xml";
        Arquivo a1 = new Arquivo(nomeArquivoLote);
        a1.abrirEscrita();
        a1.escreverLinha(dadosMsgXml);
        a1.fecharArquivo();

        //exclui arquivos temporarios
        aAssinado.excluir();
    }

    public String getXml(Cte cte, String versao) {
        String xml = "";
        if (cte.getCUF() == 41) {
//            xml += "<CTe>"
            xml += "<CTe xmlns=\"http://www.portalfiscal.inf.br/cte\">"
                    + "<infCte Id=\"CTe" + cte.getChaveAcesso() + "\" versao=\"" + versao + "\">"
                    + "<ide>";
        } else {
            xml += "<CTe xmlns=\"http://www.portalfiscal.inf.br/cte\">"
                    + "<infCte Id=\"CTe" + cte.getChaveAcesso() + "\" versao=\"" + versao + "\">"
                    + "<ide>";
        }

        xml += "<cUF>" + cte.getCUF() + "</cUF>";
        xml += "<cCT>" + Utils.getInstance().zeroFill("" + cte.getCCT(), 8) + "</cCT>";
        xml += "<CFOP>" + cte.getCFOP() + "</CFOP>";
        xml += "<natOp>" + cte.getNatOp() + "</natOp>";
        
        //xml += "<forPag>" + cte.getForPag() + "</forPag>"; removido versao 3.00
        xml += "<mod>" + cte.getMod() + "</mod>";
        xml += "<serie>" + cte.getSerie() + "</serie>";
        xml += "<nCT>" + cte.getNCT() + "</nCT>";

//        xml += "<dhEmi>" + Utils.getInstance().convertDataToStringSefaz(cte.getDhEmi()) + "T" + Utils.getInstance().convertHoraToString(cte.getDhEmi()) + ":00</dhEmi>";
        xml += "<dhEmi>" + dateFormat.format(cte.getDhEmi()) + "</dhEmi>";
        xml += "<tpImp>" + cte.getTpImp() + "</tpImp>";
        xml += "<tpEmis>" + cte.getTpEmis() + "</tpEmis>";
        xml += "<cDV>" + cte.getCDV() + "</cDV>";
        xml += "<tpAmb>" + cte.getTpAmb() + "</tpAmb>";
        xml += "<tpCTe>" + cte.getTpCTe() + "</tpCTe>";
        xml += "<procEmi>" + cte.getProcEmi() + "</procEmi>";
        xml += "<verProc>" + cte.getVerProc() + "</verProc>";
        if (cte.getRefCTE() != null && cte.getRefCTE().length() > 0) {
            xml += "<refCTE>" + cte.getRefCTE() + "</refCTE>";
        }
        xml += "<cMunEnv>" + cte.getCMunEnv() + "</cMunEnv>";
        xml += "<xMunEnv>" + cte.getXMunEnv() + "</xMunEnv>";
        xml += "<UFEnv>" + cte.getUFEnv() + "</UFEnv>";
        xml += "<modal>" + Utils.getInstance().zeroFill("" + cte.getModal(), 2) + "</modal>";
        xml += "<tpServ>" + cte.getTpServ() + "</tpServ>";
        xml += "<cMunIni>" + cte.getCMunIni() + "</cMunIni>";
        xml += "<xMunIni>" + cte.getXMunIni() + "</xMunIni>";
        xml += "<UFIni>" + cte.getUFIni() + "</UFIni>";
        xml += "<cMunFim>" + cte.getCMunFim() + "</cMunFim>";
        xml += "<xMunFim>" + cte.getXMunFim() + "</xMunFim>";
        xml += "<UFFim>" + cte.getUFFim() + "</UFFim>";
        xml += "<retira>" + cte.getRetira() + "</retira>";
        if (cte.getXDetRetira() != null && cte.getXDetRetira().length() > 0) {
            xml += "<xDetRetira>" + cte.getXDetRetira() + "</xDetRetira>";
        }
        xml += "<indIEToma>" + cte.getIndIEToma() + "</indIEToma>";
        //INICIO TOMADOR DO SERVICO
        if (cte.getToma() != null) {
            if (cte.getToma().getToma() == 4) {
                xml += "<toma4>";
                xml += "<toma>4</toma>";
                if (cte.getToma().getCNPJ() != null && cte.getToma().getCNPJ().length() > 0) {
                    xml += "<CNPJ>" + cte.getToma().getCNPJ() + "</CNPJ>";
                } else {
                    xml += "<CPF>" + cte.getToma().getCPF() + "</CPF>";
                }
                if (cte.getToma().getIE() != null && cte.getToma().getIE().length() > 0) {
                    xml += "<IE>" + cte.getToma().getIE() + "</IE>";
                }
                xml += "<xNome>" + cte.getToma().getXNome() + "</xNome>";
                if (cte.getToma().getXFant() != null && cte.getToma().getXFant().length() > 0) {
                    xml += "<xFant>" + cte.getToma().getXFant() + "</xFant>";
                }
                if (cte.getToma().getFone() != null && cte.getToma().getFone().length() > 0) {
                    xml += "<fone>" + cte.getToma().getFone() + "</fone>";
                }
                xml += "<enderToma>";
                xml += "<xLgr>" + cte.getToma().getXLgr() + "</xLgr>";
                xml += "<nro>" + cte.getToma().getNro() + "</nro>";
                if (cte.getToma().getXCpl() != null && cte.getToma().getXCpl().length() > 0) {
                    xml += "<xCpl>" + cte.getToma().getXCpl() + "</xCpl>";
                }
                xml += "<xBairro>" + cte.getToma().getXBairro() + "</xBairro>";
                xml += "<cMun>" + cte.getToma().getCMun() + "</cMun>";
                xml += "<xMun>" + cte.getToma().getXMun() + "</xMun>";
                if (cte.getToma().getCEP() != null && cte.getToma().getCEP().length() > 0) {
                    xml += "<CEP>" + cte.getToma().getCEP() + "</CEP>";
                }
                xml += "<UF>" + cte.getToma().getUF() + "</UF>";
                if (cte.getToma().getCPais() != null && cte.getToma().getCPais() > 0) {
                    xml += "<cPais>" + cte.getToma().getCPais() + "</cPais>";
                    xml += "<xPais>" + cte.getToma().getXPais() + "</xPais>";
                }
                xml += "</enderToma>";
                xml += "</toma4>";
            } else {
                xml += "<toma3>";
                xml += "<toma>" + cte.getToma().getToma() + "</toma>";
                xml += "</toma3>";
            }
            //FIM TOMADOR SERVICO
        }
        xml += "</ide>";

        //INICIO COMPLEMENTO
        xml += "<compl>";
        if (cte.getCompl().getXCaracAd() != null && cte.getCompl().getXCaracAd().length() > 0) {
            xml += "<xCaracAd>" + cte.getCompl().getXCaracAd() + "</xCaracAd>";
        }
        if (cte.getCompl().getXCaracSer() != null && cte.getCompl().getXCaracSer().length() > 0) {
            xml += "<xCaracSer>" + cte.getCompl().getXCaracSer() + "</xCaracSer>";
        }
        if (cte.getCompl().getXEmi() != null && cte.getCompl().getXEmi().length() > 0) {
            xml += "<xEmi>" + cte.getCompl().getXEmi() + "</xEmi>";
        }

        xml += "<fluxo>";
        if (cte.getCompl().getXOrig() != null && cte.getCompl().getXOrig().length() > 0) {
            xml += "<xOrig>" + cte.getCompl().getXOrig() + "</xOrig>";
        }
        Collection<CtePass> listPass = cte.getCompl().getCtePass();
        if (listPass != null) {
            for (CtePass pass : listPass) {
                xml += "<pass>";
                xml += "<xPass>" + pass.getXPass() + "</xPass>";
                xml += "</pass>";
            }
        }
        if (cte.getCompl().getXDest() != null && cte.getCompl().getXDest().length() > 0) {
            xml += "<xDest>" + cte.getCompl().getXDest() + "</xDest>";
        }
        if (cte.getCompl().getXRota() != null && cte.getCompl().getXRota().length() > 0) {
            xml += "<xRota>" + cte.getCompl().getXRota() + "</xRota>";
        }
        xml += "</fluxo>";

        if (cte.getCompl().getTpPer() != null || cte.getCompl().getTpHor() != null) {
            xml += "<Entrega>";
            if (cte.getCompl().getTpPer() != null && cte.getCompl().getTpPer() > -1) {
                if (cte.getCompl().getTpPer() == 0) {
                    xml += "<semData><tpPer>0</tpPer></semData>";
                } else if (cte.getCompl().getTpPer() == 4) {
                    xml += "<noPeriodo>";
                    xml += "<tpPer>4</tpPer>";
                    if (cte.getCompl().getDIni() != null) {
                        xml += "<dIni>" + Utils.getInstance().convertDataToStringSefaz(cte.getCompl().getDIni()) + "</dIni>";
                    }
                    if (cte.getCompl().getDFim() != null) {
                        xml += "<dFim>" + Utils.getInstance().convertDataToStringSefaz(cte.getCompl().getDFim()) + "</dFim>";
                    }
                    xml += "</noPeriodo>";
                } else {
                    xml += "<comData>";
                    xml += "<tpPer>" + cte.getCompl().getTpPer() + "</tpPer>";
                    if (cte.getCompl().getDProg() != null) {
                        xml += "<dProg>" + Utils.getInstance().convertDataToStringSefaz(cte.getCompl().getDProg()) + "</dProg>";
                    }
                    xml += "</comData>";
                }
            }
            if (cte.getCompl().getTpHor() != null && cte.getCompl().getTpHor() > -1) {
                if (cte.getCompl().getTpHor() == 0) {
                    xml += "<semHora><tpHor>0</tpHor></semHora>";
                } else if (cte.getCompl().getTpHor() == 4) {
                    xml += "<noInter>";
                    xml += "<tpHor>" + cte.getCompl().getTpHor() + "</tpHor>";
                    xml += "<hIni>" + cte.getCompl().getHIni() + "</hIni>";
                    xml += "<hFim>" + cte.getCompl().getHIni() + "</hFim>";
                    xml += "</noInter>";
                } else {
                    xml += "<comHora>";
                    xml += "<tpHor>" + cte.getCompl().getTpHor() + "</tpHor>";
                    xml += "<hProg>" + cte.getCompl().getHProg() + "</hProg>";
                    xml += "</comHora>";
                }
            }
            xml += "</Entrega>";
        }
        if (cte.getCompl().getOrigCalc() != null && cte.getCompl().getOrigCalc().length() > 0) {
            xml += "<origCalc>" + cte.getCompl().getOrigCalc() + "</origCalc>";
        }
        if (cte.getCompl().getDestCalc() != null && cte.getCompl().getDestCalc().length() > 0) {
            xml += "<destCalc>" + cte.getCompl().getDestCalc() + "</destCalc>";
        }
        if (cte.getCompl().getXObs() != null && cte.getCompl().getXObs().length() > 0) {
            xml += "<xObs>" + cte.getCompl().getXObs() + "</xObs>";
        }
        Collection<CteObs> listObsCont = cte.getCompl().getObsCont();
        if (listObsCont != null) {
            for (CteObs obs : listObsCont) {
                xml += "<ObsCont xCampo=\"" + obs.getXCampo() + "\">";
                xml += "<xTexto>" + obs.getXTexto() + "</xTexto>";
                xml += "</ObsCont>";
            }
        }
        Collection<CteObs> listObFisco = cte.getCompl().getObsFisco();
        if (listObFisco != null) {
            for (CteObs obs : listObFisco) {
                xml += "<ObsFisco xCampo=\"" + obs.getXCampo() + "\">";
                xml += "<xTexto>" + obs.getXTexto() + "</xTexto>";
                xml += "</ObsFisco>";
            }
        }
        xml += "</compl>";
        //FIM COMPLEMENTO

        //EMITENTE
        xml += "<emit>";
        xml += "<CNPJ>" + Utils.getInstance().getDigitos(cte.getEmitente().getCNPJ()) + "</CNPJ>";
        xml += "<IE>" + cte.getEmitente().getIE() + "</IE>";
        xml += "<xNome>" + cte.getEmitente().getXNome() + "</xNome>";
        if (cte.getEmitente().getXFant() != null && cte.getEmitente().getXFant().length() > 0) {
            xml += "<xFant>" + cte.getEmitente().getXFant() + "</xFant>";
        }

        xml += "<enderEmit>";
        xml += "<xLgr>" + cte.getEmitente().getXLgr() + "</xLgr>";
        xml += "<nro>" + cte.getEmitente().getNro() + "</nro>";
        if (cte.getEmitente().getXCpl() != null && cte.getEmitente().getXCpl().length() > 0) {
            xml += "<xCpl>" + cte.getEmitente().getNro() + "</xCpl>";
        }
        xml += "<xBairro>" + cte.getEmitente().getXBairro() + "</xBairro>";
        xml += "<cMun>" + cte.getEmitente().getCMun() + "</cMun>";
        xml += "<xMun>" + cte.getEmitente().getXMun() + "</xMun>";
        if (cte.getEmitente().getCEP() != null && cte.getEmitente().getCEP().length() > 0) {
            xml += "<CEP>" + cte.getEmitente().getCEP() + "</CEP>";
        }
        xml += "<UF>" + cte.getEmitente().getUF() + "</UF>";
        if (cte.getEmitente().getFone() != null && cte.getEmitente().getFone().length() > 0) {
            xml += "<fone>" + cte.getEmitente().getFone() + "</fone>";
        }
        xml += "</enderEmit>";
        xml += "</emit>";
        //FIM EMITENTE

        //INICIO REMETENTE
        if (cte.getRemetente() != null) {
            xml += "<rem>";
            if (cte.getRemetente().getCNPJ() != null && cte.getRemetente().getCNPJ().length() > 0) {
                xml += "<CNPJ>" + Utils.getInstance().getDigitos(cte.getRemetente().getCNPJ()) + "</CNPJ>";
            }
            if (cte.getRemetente().getCPF() != null && cte.getRemetente().getCPF().length() > 0) {
                xml += "<CPF>" + Utils.getInstance().getDigitos(cte.getRemetente().getCPF()) + "</CPF>";
            }
            xml += "<IE>" + cte.getRemetente().getIE() + "</IE>";
            xml += "<xNome>" + cte.getRemetente().getXNome() + "</xNome>";
            if (cte.getRemetente().getXFant() != null && cte.getRemetente().getXFant().length() > 0) {
                xml += "<xFant>" + cte.getRemetente().getXFant() + "</xFant>";
            }
            if (cte.getRemetente().getXFone() != null && cte.getRemetente().getXFone().length() > 0) {
                xml += "<fone>" + cte.getRemetente().getXFone() + "</fone>";
            }
            xml += "<enderReme>";
            xml += "<xLgr>" + cte.getRemetente().getXLgr() + "</xLgr>";
            xml += "<nro>" + cte.getRemetente().getNro() + "</nro>";
            if (cte.getRemetente().getXCpl() != null && cte.getRemetente().getXCpl().length() > 0) {
                xml += "<xCpl>" + cte.getRemetente().getNro() + "</xCpl>";
            }
            xml += "<xBairro>" + cte.getRemetente().getXBairro() + "</xBairro>";
            xml += "<cMun>" + cte.getRemetente().getCMun() + "</cMun>";
            xml += "<xMun>" + cte.getRemetente().getXMun() + "</xMun>";
            if (cte.getRemetente().getCEP() != null && cte.getRemetente().getCEP().length() > 0) {
                xml += "<CEP>" + cte.getRemetente().getCEP() + "</CEP>";
            }
            xml += "<UF>" + cte.getRemetente().getUF() + "</UF>";
            if (cte.getRemetente().getCPais() != null && cte.getRemetente().getCPais() > 0) {
                xml += "<cPais>" + cte.getRemetente().getCPais() + "</cPais>";
                xml += "<xPais>" + cte.getRemetente().getXPais() + "</xPais>";
            }
            xml += "</enderReme>";
            if (cte.getRemetente().getEmail() != null && cte.getRemetente().getEmail().length() > 0) {
                xml += "<email>" + cte.getRemetente().getEmail() + "</email>";
            }

            if (cte.getRemetente().getLocColeta() != null) {
                if (cte.getRemetente().getLocColeta().getCNPJ() != null && cte.getRemetente().getLocColeta().getCNPJ().length() > 0) {
                    xml += "<CNPJ>" + Utils.getInstance().getDigitos(cte.getRemetente().getLocColeta().getCNPJ()) + "</CNPJ>";
                }
                if (cte.getRemetente().getLocColeta().getCPF() != null && cte.getRemetente().getLocColeta().getCPF().length() > 0) {
                    xml += "<CPF>" + Utils.getInstance().getDigitos(cte.getRemetente().getLocColeta().getCPF()) + "</CPF>";
                }
                xml += "<xNome>" + cte.getRemetente().getLocColeta().getXNome() + "</xNome>";
                if (cte.getRemetente().getLocColeta().getXLgr() != null && cte.getRemetente().getLocColeta().getXLgr().length() > 0) {
                    xml += "<xLgr>" + cte.getRemetente().getLocColeta().getXLgr() + "</xLgr>";
                }
                if (cte.getRemetente().getLocColeta().getNro() != null && cte.getRemetente().getLocColeta().getNro().length() > 0) {
                    xml += "<nro>" + cte.getRemetente().getLocColeta().getNro() + "</nro>";
                }
                if (cte.getRemetente().getLocColeta().getXCpl() != null && cte.getRemetente().getLocColeta().getXCpl().length() > 0) {
                    xml += "<xCpl>" + cte.getRemetente().getLocColeta().getXCpl() + "</xCpl>";
                }
                if (cte.getRemetente().getLocColeta().getXBairro() != null && cte.getRemetente().getLocColeta().getXBairro().length() > 0) {
                    xml += "<xBairro>" + cte.getRemetente().getLocColeta().getXBairro() + "</xBairro>";
                }
                xml += "<cMun>" + cte.getRemetente().getLocColeta().getCMun() + "</cMun>";
                xml += "<xMun>" + cte.getRemetente().getLocColeta().getXMun() + "</xMun>";
                xml += "<UF>" + cte.getRemetente().getLocColeta().getUF() + "</UF>";

            }
//            //INFORMACOES DA NF
//            Collection<CteInfNF> listInfNf = cte.getRemetente().getInfNF();
//            if (listInfNf != null) {
//                for (CteInfNF nf : listInfNf) {
//                    xml += "<infNF>";
//                    if (nf.getNRoma() != null && nf.getNRoma().length() > 0) {
//                        xml += "<nRoma>" + nf.getNRoma() + "</nRoma>";
//                    }
//                    if (nf.getNPed() != null && nf.getNPed().length() > 0) {
//                        xml += "<nPed>" + nf.getNPed() + "</nPed>";
//                    }
//                    if (nf.getMod() != null && nf.getMod().length() > 0) {
//                        xml += "<mod>" + nf.getMod() + "</mod>";
//                    } else {
//                        xml += "<mod>" + "01" + "</mod>";
//                    }
//                    xml += "<serie>" + nf.getSerie() + "</serie>";
//                    if (nf.getNDoc() != null && nf.getNDoc().length() > 0) {
//                        xml += "<nDoc>" + nf.getNDoc() + "</nDoc>";
//                    }
//                    xml += "<dEmi>" + Utils.getInstance().convertDataToStringSefaz(nf.getDEmi()) + "</dEmi>";
//                    xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVBC()) + "</vBC>";
//                    xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVICMS()) + "</vICMS>";
//                    xml += "<vBCST>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVBCST()) + "</vBCST>";
//                    xml += "<vST>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVBC()) + "</vST>";
//                    xml += "<vProd>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVProd()) + "</vProd>";
//                    xml += "<vNF>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVNF()) + "</vNF>";
//                    xml += "<nCFOP>" + nf.getNCFOP() + "</nCFOP>";
//                    if (nf.getNPeso() > 0) {
//                        xml += "<nPeso>" + Utils.getInstance().getDecimalFormatter(12, 3).format(nf.getNPeso()) + "</nPeso>";
//                    }
//                    if (nf.getPIN() != null && nf.getPIN() > 0) {
//                        xml += "<PIN>" + nf.getPIN() + "</PIN>";
//                    }
//                    /**
//                     * nao colocar local de retirada /** if (nf.getCNPJ() !=
//                     * null || nf.getCPF() != null) { xml += "<locRet>"; if
//                     * (nf.getCNPJ() != null) { xml += "<CNPJ>" +
//                     * Utils.getInstance().getDigitos(nf.getCNPJ()) + "</CNPJ>";
//                     * } else if (nf.getCPF() != null) { xml += "<CPF>" +
//                     * Utils.getInstance().getDigitos(nf.getCPF()) + "</CPF>"; }
//                     * xml += "<xNome>" + nf.getXNome() + "</xNome>"; xml +=
//                     * "<xLgr>" + nf.getXLgr() + "</xLgr>"; xml += "<nro>" +
//                     * nf.getNro() + "</nro>"; if (nf.getXCpl() != null &&
//                     * nf.getXCpl().length() > 0) { xml += "<xCpl>" +
//                     * nf.getXCpl() + "</xCpl>"; } xml += "<xBairro>" +
//                     * nf.getXBairro() + "</xBairro>"; xml += "<cMun>" +
//                     * nf.getCMun() + "</cMun>"; xml += "<xMun>" + nf.getXMun()
//                     * + "</xMun>"; xml += "<UF>" + nf.getUF() + "</UF>"; xml +=
//                     * "</locRet>"; }
//                     *
//                     */
//                    xml += "</infNF>";
//                }
//            }//FIM INF NF

//            //INF NFE
//            Collection<CteInfNFe> listInfNfe = cte.getRemetente().getInfNFe();
//            if (listInfNfe != null) {
//                for (CteInfNFe nfe : listInfNfe) {
//                    xml += "<infNFe>";
//                    xml += "<chave>" + nfe.getChave() + "</chave>";
//                    if (nfe.getPIN() != null && nfe.getPIN() > 0) {
//                        xml += "<PIN>" + nfe.getPIN() + "</PIN>";
//                    }
//                    xml += "</infNFe>";
//                }
//            }
//            //INF OUTROS
//            Collection<CteInfOutros> listInfOutros = cte.getRemetente().getInfOutros();
//            if (listInfOutros != null) {
//                for (CteInfOutros outros : listInfOutros) {
//                    xml += "<infOutros>";
//                    xml += "<tpDoc>" + Utils.getInstance().zeroFill("" + outros.getTpDoc(), 2) + "</tpDoc>";
//                    if (outros.getDescOutros() != null && outros.getDescOutros().length() > 0) {
//                        xml += "<descOutros>" + outros.getDescOutros() + "</descOutros>";
//                    }
//                    if (outros.getNDoc() != null && outros.getNDoc().length() > 0) {
//                        xml += "<nDoc>" + outros.getNDoc() + "</nDoc>";
//                    }
//                    xml += "<dEmi>" + Utils.getInstance().convertDataToStringSefaz(outros.getDEmi()) + "</dEmi>";
//                    if (outros.getVDocFisc() != null && outros.getVDocFisc() > 0) {
//                        xml += "<vDocFisc>" + Utils.getInstance().getDecimalFormatter(13, 2).format(outros.getVDocFisc()) + "</vDocFisc>";
//                    }
//                    xml += "</infOutros>";
//                }
//            }
            xml += "</rem>";
        }//FIM REMETENTE

        //INICIO EXPEDIDOR
        if (cte.getExpedidor() != null) {
            xml += "<exped>";
            if (cte.getExpedidor().getCNPJ() != null && cte.getExpedidor().getCNPJ().length() > 0) {
                xml += "<CNPJ>" + Utils.getInstance().getDigitos(cte.getExpedidor().getCNPJ()) + "</CNPJ>";
            }
            if (cte.getExpedidor().getCPF() != null && cte.getExpedidor().getCPF().length() > 0) {
                xml += "<CPF>" + Utils.getInstance().getDigitos(cte.getExpedidor().getCPF()) + "</CPF>";
            }
            xml += "<IE>" + cte.getExpedidor().getIE() + "</IE>";
            xml += "<xNome>" + cte.getExpedidor().getXNome() + "</xNome>";
            if (cte.getExpedidor().getFone() != null && cte.getExpedidor().getFone().length() > 0) {
                xml += "<fone>" + cte.getExpedidor().getFone() + "</fone>";
            }
            xml += "<enderExped>";
            xml += "<xLgr>" + cte.getExpedidor().getXLgr() + "</xLgr>";
            xml += "<nro>" + cte.getExpedidor().getNro() + "</nro>";
            if (cte.getExpedidor().getXCpl() != null && cte.getExpedidor().getXCpl().length() > 0) {
                xml += "<xCpl>" + cte.getExpedidor().getXCpl() + "</xCpl>";
            }
            xml += "<xBairro>" + cte.getExpedidor().getXBairro() + "</xBairro>";
            xml += "<cMun>" + cte.getExpedidor().getCMun() + "</cMun>";
            xml += "<xMun>" + cte.getExpedidor().getXMun() + "</xMun>";
            if (cte.getExpedidor().getCEP() != null && cte.getExpedidor().getCEP().length() > 0) {
                xml += "<CEP>" + cte.getExpedidor().getCEP() + "</CEP>";
            }
            xml += "<UF>" + cte.getExpedidor().getUF() + "</UF>";
            if (cte.getExpedidor().getCPais() != null && cte.getExpedidor().getCPais() > 0) {
                xml += "<cPais>" + cte.getExpedidor().getCPais() + "</cPais>";
                xml += "<xPais>" + cte.getExpedidor().getXPais() + "</xPais>";
            }
            xml += "</enderExped>";
            if (cte.getExpedidor().getEmail() != null && cte.getExpedidor().getEmail().length() > 0) {
                xml += "<email>" + cte.getExpedidor().getEmail() + "</email>";
            }
            xml += "</exped>";
        }
        //FIM EXPEDIDOR

        //INICIO RECEBEDOR
        if (cte.getRecebedor() != null) {
            xml += "<receb>";
            if (cte.getRecebedor().getCNPJ() != null && cte.getRecebedor().getCNPJ().length() > 0) {
                xml += "<CNPJ>" + Utils.getInstance().getDigitos(cte.getRecebedor().getCNPJ()) + "</CNPJ>";
            }
            if (cte.getRecebedor().getCPF() != null && cte.getRecebedor().getCPF().length() > 0) {
                xml += "<CPF>" + Utils.getInstance().getDigitos(cte.getRecebedor().getCPF()) + "</CPF>";
            }
            xml += "<IE>" + cte.getRecebedor().getIE() + "</IE>";
            xml += "<xNome>" + cte.getRecebedor().getXNome() + "</xNome>";
            if (cte.getRecebedor().getFone() != null && cte.getRecebedor().getFone().length() > 0) {
                xml += "<fone>" + cte.getRecebedor().getFone() + "</fone>";
            }
            xml += "<enderReceb>";
            xml += "<xLgr>" + cte.getRecebedor().getXLgr() + "</xLgr>";
            xml += "<nro>" + cte.getRecebedor().getNro() + "</nro>";
            if (cte.getRecebedor().getXCpl() != null && cte.getRecebedor().getXCpl().length() > 0) {
                xml += "<xCpl>" + cte.getRecebedor().getXCpl() + "</xCpl>";
            }
            xml += "<xBairro>" + cte.getRecebedor().getXBairro() + "</xBairro>";
            xml += "<cMun>" + cte.getRecebedor().getCMun() + "</cMun>";
            xml += "<xMun>" + cte.getRecebedor().getXMun() + "</xMun>";
            if (cte.getRecebedor().getCEP() != null && cte.getRecebedor().getCEP().length() > 0) {
                xml += "<CEP>" + cte.getRecebedor().getCEP() + "</CEP>";
            }
            xml += "<UF>" + cte.getRecebedor().getUF() + "</UF>";
            if (cte.getRecebedor().getCPais() != null && cte.getRecebedor().getCPais() > 0) {
                xml += "<cPais>" + cte.getRecebedor().getCPais() + "</cPais>";
                xml += "<xPais>" + cte.getRecebedor().getXPais() + "</xPais>";
            }
            xml += "</enderReceb>";
            if (cte.getExpedidor().getEmail() != null && cte.getExpedidor().getEmail().length() > 0) {
                xml += "<email>" + cte.getExpedidor().getEmail() + "</email>";
            }
            xml += "</receb>";
        }
        //FIM RECEBEDOR

        //INICIO DESTINATARIO
        if (cte.getDestinatario() != null) {
            xml += "<dest>";
            if (cte.getDestinatario().getCNPJ() != null && cte.getDestinatario().getCNPJ().length() > 0) {
                xml += "<CNPJ>" + Utils.getInstance().getDigitos(cte.getDestinatario().getCNPJ()) + "</CNPJ>";
            }
            if (cte.getDestinatario().getCPF() != null && cte.getDestinatario().getCPF().length() > 0) {
                xml += "<CPF>" + Utils.getInstance().getDigitos(cte.getDestinatario().getCPF()) + "</CPF>";
            }
            if (cte.getDestinatario().getIE() != null && cte.getDestinatario().getIE().length() > 0) {
                xml += "<IE>" + cte.getDestinatario().getIE() + "</IE>";
            }
            xml += "<xNome>" + cte.getDestinatario().getXNome() + "</xNome>";
            if (cte.getDestinatario().getFone() != null && cte.getDestinatario().getFone().length() > 0) {
                xml += "<fone>" + cte.getDestinatario().getFone() + "</fone>";
            }
            if (cte.getDestinatario().getISUF() != null && cte.getDestinatario().getISUF() > 0) {
                xml += "<ISUF>" + Utils.getInstance().zeroFill("" + cte.getDestinatario().getISUF(), 9) + "</ISUF>";
            }
            xml += "<enderDest>";
            xml += "<xLgr>" + cte.getDestinatario().getXLgr() + "</xLgr>";
            xml += "<nro>" + cte.getDestinatario().getNro() + "</nro>";
            if (cte.getDestinatario().getXCpl() != null && cte.getDestinatario().getXCpl().length() > 0) {
                xml += "<xCpl>" + cte.getDestinatario().getXCpl() + "</xCpl>";
            }
            xml += "<xBairro>" + cte.getDestinatario().getXBairro() + "</xBairro>";
            xml += "<cMun>" + cte.getDestinatario().getCMun() + "</cMun>";
            xml += "<xMun>" + cte.getDestinatario().getXMun() + "</xMun>";
            if (cte.getDestinatario().getCEP() != null && cte.getDestinatario().getCEP().length() > 0) {
                xml += "<CEP>" + cte.getDestinatario().getCEP() + "</CEP>";
            }
            xml += "<UF>" + cte.getDestinatario().getUF() + "</UF>";
            if (cte.getDestinatario().getCPais() != null && cte.getDestinatario().getCPais() > 0) {
                xml += "<cPais>" + cte.getDestinatario().getCPais() + "</cPais>";
                xml += "<xPais>" + cte.getDestinatario().getXPais() + "</xPais>";
            }
            xml += "</enderDest>";
            if (cte.getDestinatario().getEmail() != null && cte.getDestinatario().getEmail().length() > 0) {
                xml += "<email>" + cte.getDestinatario().getEmail() + "</email>";
            }
            if (cte.getDestinatario().getLocalEntrega() != null) {
                xml += "<locEnt>";
                if (cte.getDestinatario().getLocalEntrega().getCNPJ() != null && cte.getDestinatario().getLocalEntrega().getCNPJ().length() > 0) {
                    xml += "<CNPJ>" + Utils.getInstance().getDigitos(cte.getDestinatario().getLocalEntrega().getCNPJ()) + "</CNPJ>";
                }
                if (cte.getDestinatario().getLocalEntrega().getCPF() != null && cte.getDestinatario().getLocalEntrega().getCPF().length() > 0) {
                    xml += "<CPF>" + Utils.getInstance().getDigitos(cte.getDestinatario().getLocalEntrega().getCPF()) + "</CPF>";
                }
                xml += "<xNome>" + cte.getDestinatario().getLocalEntrega().getXNome() + "</xNome>";
                xml += "<xLgr>" + cte.getDestinatario().getLocalEntrega().getXLgr() + "</xLgr>";
                xml += "<nro>" + cte.getDestinatario().getLocalEntrega().getNro() + "</nro>";
                if (cte.getDestinatario().getLocalEntrega().getXCpl() != null && cte.getDestinatario().getLocalEntrega().getXCpl().length() > 0) {
                    xml += "<xCpl>" + cte.getDestinatario().getLocalEntrega().getXCpl() + "</xCpl>";
                }
                xml += "<xBairro>" + cte.getDestinatario().getLocalEntrega().getXBairro() + "</xBairro>";
                xml += "<cMun>" + cte.getDestinatario().getLocalEntrega().getCMun() + "</cMun>";
                xml += "<xMun>" + cte.getDestinatario().getLocalEntrega().getXMun() + "</xMun>";
                if (cte.getDestinatario().getLocalEntrega().getCEP() != null && cte.getDestinatario().getLocalEntrega().getCEP().length() > 0) {
                    xml += "<CEP>" + cte.getDestinatario().getLocalEntrega().getCEP() + "</CEP>";
                }
                xml += "<UF>" + cte.getDestinatario().getLocalEntrega().getUF() + "</UF>";
                xml += "</locEnt>";
            }//fim local entrega destinatario
            xml += "</dest>";
        }
        //FIM DESTINATARIO

        //INICIO PRESTACAO SERVICO
        xml += "<vPrest>";
        xml += "<vTPrest>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getVTPrest()) + "</vTPrest>";
        xml += "<vRec>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getVRec()) + "</vRec>";
        Collection<CteCompPrestacaoServico> listCompPrestServ = cte.getCompPrestacaoServico();
        if (listCompPrestServ != null) {
            for (CteCompPrestacaoServico comp : listCompPrestServ) {
                xml += "<Comp>";
                xml += "<xNome>" + comp.getXNome() + "</xNome>";
                xml += "<vComp>" + Utils.getInstance().getDecimalFormatter(13, 2).format(comp.getVComp()) + "</vComp>";
                xml += "</Comp>";
            }
        }
        xml += "</vPrest>";
        //FIM PRESTACAO SERVICO

        //INICIO IMPOSTOS
        xml += "<imp>";
        if (cte.getImp() != null) {
            xml += "<ICMS>";
            if (cte.getImp().getCST() != null) {
                if (cte.getImp().getCST() == 0) {
                    xml += "<ICMS00>";
                    xml += "<CST>00</CST>";
                    xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBC()) + "</vBC>";
                    xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getPICMS()) + "</pICMS>";
                    xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getVICMS()) + "</vICMS>";
                    xml += "</ICMS00>";
                } else if (cte.getImp().getCST() == 20) {
                    xml += "<ICMS20>";
                    xml += "<CST>20</CST>";
                    xml += "<pRedBC>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getPRedBC()) + "</pRedBC>";
                    xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBC()) + "</vBC>";
                    xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getPICMS()) + "</pICMS>";
                    xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getVICMS()) + "</vICMS>";
                    xml += "</ICMS20>";
                } else if (cte.getImp().getCST() == 40 || cte.getImp().getCST() == 41 || cte.getImp().getCST() == 51) {
                    xml += "<ICMS45>";
                    xml += "<CST>" + cte.getImp().getCST() + "</CST>";
                    xml += "</ICMS45>";
                } else if (cte.getImp().getCST() == 60) {//03-03-14
                    xml += "<ICMS60>";
                    xml += "<CST>60</CST>";
                    xml += "<vBCSTRet>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBCSTRet()) + "</vBCSTRet>";
                    xml += "<vICMSSTRet>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVICMSSTRet()) + "</vICMSSTRet>";
                    xml += "<pICMSSTRet>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getPICMSSTRet()) + "</pICMSSTRet>";
                    if (cte.getImp().getVCred() > 0) {
                        xml += "<vCred>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVCred()) + "</vCred>";
                    }
                    xml += "</ICMS60>";
                } else if (cte.getImp().getCST() == 90) {
                    if (cte.getImp().getPRedBC() > 0 && cte.getImp().getVCred() > 0) {
                        xml += "<ICMS90>";
                        xml += "<CST>90</CST>";
                        xml += "<pRedBC>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getPRedBC()) + "</pRedBC>";
                        xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBC()) + "</vBC>";
                        xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getPICMS()) + "</pICMS>";
                        xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getVICMS()) + "</vICMS>";
                        if (cte.getImp().getVCred() > 0) {
                            xml += "<vCred>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVCred()) + "</vCred>";
                        }
                        xml += "</ICMS90>";
                    } else if (cte.getImp().getPRedBC() > 0) {
                        xml += "<ICMS81>";
                        xml += "<CST>90</CST>";
                        xml += "<pRedBC>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getPRedBC()) + "</pRedBC>";
                        xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBC()) + "</vBC>";
                        xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getPICMS()) + "</pICMS>";
                        xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getVICMS()) + "</vICMS>";
                        xml += "</ICMS81>";
                    } else {
                        xml += "<ICMS80>";
                        xml += "<ICMS>90</CST>";
                        xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBC()) + "</vBC>";
                        xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getPICMS()) + "</pICMS>";
                        xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getVICMS()) + "</vICMS>";
                        if (cte.getImp().getVCred() > 0) {
                            xml += "<vCred>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVCred()) + "</vCred>";
                        }
                        xml += "</ICMS80>";
                    }
                }
                /**
                 * CASO FOR 100(NAO EXISTE) regra criada para utilizar o metodo
                 * outras
                 */
                if (cte.getImp().getCST() == 100) {//03-03-14
                    xml += "<ICMSOutraUF>";
                    xml += "<CST>90</CST>";
                    if (cte.getImp().getPRedBCOutraUF() != null && cte.getImp().getPRedBCOutraUF() > 0) {
                        xml += "<pRedBCOutraUF>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getPRedBCOutraUF()) + "</pRedBCOutraUF>";
                    }
                    xml += "<vBCOutraUF>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBCOutraUF()) + "</vBCOutraUF>";
                    xml += "<pICMSOutraUF>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getPICMSOutraUF()) + "</pICMSOutraUF>";
                    xml += "<vICMSOutraUF>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVICMSOutraUF()) + "</vICMSOutraUF>";
//                    if (cte.getImp().getVCred() > 0) {
//                        xml += "<vCred>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVCred()) + "</vCred>";
//                    }
                    xml += "</ICMSOutraUF>";
                }
                
                if (cte.getImp() != null && cte.getImp().getIndSN() != null && cte.getImp().getIndSN() == 1) {
                    xml += "<ICMSSN>";
                    xml += "<CST>90</CST>";
                    xml += "<indSN>" + cte.getImp().getIndSN() + "</indSN>";
                    xml += "</ICMSSN>";
                }
            }
            xml += "</ICMS>";
            if (cte.getImp().getvTotTrib() != null && cte.getImp().getvTotTrib() > 0) {
                xml += "<vTotTrib>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getvTotTrib()) + "</vTotTrib>";
            }
            if (cte.getImp().getInfAdFisco() != null && cte.getImp().getInfAdFisco().length() > 0) {
                xml += "<infAdFisco>" + cte.getImp().getInfAdFisco() + "</infAdFisco>";
            }
            
            /**
             * Tomador NAO Contribuinte entre UFs
             */
            if (cte.getIndIEToma().equals("9")&& (!cte.getEmitente().getUF().equals(cte.getToma().getUF()))) {
                xml += "<ICMSUFFim>";
                xml += "<vBCUFFim>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getvBCUFFim()) + "</vBCUFFim>";
                xml += "<pFCPUFFim>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getpFCPUFFim()) + "</pFCPUFFim>";
                xml += "<pICMSUFFim>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getpICMSUFFim()) + "</pICMSUFFim>";
                xml += "<pICMSInter>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getpICMSInter()) + "</pICMSInter>";
                xml += "<pICMSInterPart>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getpICMSInterPart()) + "</pICMSInterPart>";
                xml += "<vFCPUFFim>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getvFCPUFFim()) + "</vFCPUFFim>";
                xml += "<vICMSUFFim>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getvICMSUFFim()) + "</vICMSUFFim>";
                xml += "<vICMSUFIni>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getvICMSUFIni()) + "</vICMSUFIni>";
                xml += "</ICMSUFFim>";
            }
            
        }
        xml += "</imp>";
        //FIM IMPOSTOS

        if (cte.getInfCTeNorm() != null) {
            xml += "<infCTeNorm>";
            //INFORMACOES DE CARGA
            xml += "<infCarga>";
            xml += "<vCarga>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getInfCTeNorm().getVCarga()) + "</vCarga>";
            xml += "<proPred>" + cte.getInfCTeNorm().getProPred() + "</proPred>";
            if (cte.getInfCTeNorm().getXOutCat() != null && cte.getInfCTeNorm().getXOutCat().length() > 0) {
                xml += "<xOutCat>" + cte.getInfCTeNorm().getXOutCat() + "</xOutCat>";
            }
            Collection<CteInfQCarga> listInfCarga = cte.getInfCTeNorm().getInfQ();
            if (listInfCarga != null) {
                for (CteInfQCarga infQ : listInfCarga) {
                    xml += "<infQ>";
                    xml += "<cUnid>" + Utils.getInstance().zeroFill("" + infQ.getCUnid(), 2) + "</cUnid>";
                    xml += "<tpMed>" + infQ.getTpMed() + "</tpMed>";
                    xml += "<qCarga>" + Utils.getInstance().getDecimalFormatter(11, 4).format(infQ.getQCarga()) + "</qCarga>";
                    xml += "</infQ>";
                }
            }
            xml += "</infCarga>";
            //FIM INFORMACOES CARGA

            //03-03-14
//            //INFORMACOES DE CONTAINERS
//            Collection<CteContQt> listContQt = cte.getInfCTeNorm().getContQt();
//            if (listContQt != null) {
//                xml += "<contQt>";
//                for (CteContQt contQt : listContQt) {
//                    xml += "<nCont>" + contQt.getNCont() + "</nCont>";
//                    Collection<CteLacContQt> listLac = contQt.getLacContQt();
//                    if (listLac != null) {
//                        Iterator itLac = listContQt.iterator();
//                        while (itLac.hasNext()) {
//                            CteLacContQt lac = (CteLacContQt) itLac.next();
//                            xml += "<lacContQt>";
//                            xml += "<nLacre>" + lac.getNLacre() + "</nLacre>";
//                            xml += "</lacContQt>";
//                        }
//                    }
//                    xml += "<dPrev>" + Utils.getInstance().convertDataToStringSefaz(contQt.getDPrev()) + "</dPrev>";
//                }
//                xml += "</contQt>";
//            }
//            //FIM INFORMACOES CONTAINERS
            if (cte.getRemetente().getInfNF() != null || cte.getRemetente().getInfNFe() != null || cte.getRemetente().getInfOutros() != null) {
                xml += "<infDoc>";
                if (cte.getRemetente().getInfNF() != null) {
                    for (CteInfNF nf : cte.getRemetente().getInfNF()) {
                        xml += "<infNF>";
                        if (nf.getNRoma() != null && nf.getNRoma().length() > 0) {
                            xml += "<nRoma>" + nf.getNRoma() + "</nRoma>";
                        }
                        if (nf.getNPed() != null && nf.getNPed().length() > 0) {
                            xml += "<nPed>" + nf.getNPed() + "</nPed>";
                        }
                        if (nf.getMod() != null && nf.getMod().length() > 0) {
                            xml += "<mod>" + nf.getMod() + "</mod>";
                        } else {
                            xml += "<mod>" + "01" + "</mod>";
                        }
                        xml += "<serie>" + nf.getSerie() + "</serie>";
                        if (nf.getNDoc() != null && nf.getNDoc().length() > 0) {
                            xml += "<nDoc>" + nf.getNDoc() + "</nDoc>";
                        }
                        xml += "<dEmi>" + Utils.getInstance().convertDataToStringSefaz(nf.getDEmi()) + "</dEmi>";
                        xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVBC()) + "</vBC>";
                        xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVICMS()) + "</vICMS>";
                        xml += "<vBCST>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVBCST()) + "</vBCST>";
                        xml += "<vST>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVBC()) + "</vST>";
                        xml += "<vProd>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVProd()) + "</vProd>";
                        xml += "<vNF>" + Utils.getInstance().getDecimalFormatter(13, 2).format(nf.getVNF()) + "</vNF>";
                        xml += "<nCFOP>" + nf.getNCFOP() + "</nCFOP>";
                        if (nf.getNPeso() > 0) {
                            xml += "<nPeso>" + Utils.getInstance().getDecimalFormatter(12, 3).format(nf.getNPeso()) + "</nPeso>";
                        }
                        if (nf.getPIN() != null && nf.getPIN() > 0) {
                            xml += "<PIN>" + nf.getPIN() + "</PIN>";
                        }
                        if (nf.getDPrev() != null) {
                            xml += "<dPrev>" + Utils.getInstance().convertDataToStringSefaz(nf.getDPrev()) + "</dPrev>";
                        }
                        if (nf.getInfUnidTransp() != null) {
                            for (CteInfUnidTransp unid : nf.getInfUnidTransp()) {
                                xml += "<infUnidTransp>";
                                xml += "<tpUnidTransp>" + unid.getTpUnidTransp() + "</tpUnidTransp>";
                                xml += "<idUnidTransp>" + unid.getIdUnidTransp() + "</idUnidTransp>";
                                if (unid.getLacUnidTransp() != null) {
                                    for (CteLacUnidTransp lacUnidTransp : unid.getLacUnidTransp()) {
                                        xml += "<lacUnidTransp>";
                                        xml += "<nLacre>" + lacUnidTransp.getNLacre() + "</nLacre>";
                                        xml += "</lacUnidTransp>";
                                    }
                                }
                                if (unid.getInfUnidCarga() != null) {
                                    for (CteInfUnidCarga u : unid.getInfUnidCarga()) {
                                        xml += "<infUnidCarga>";
                                        xml += "<tpUnidCarga>" + u.getTpUnidCarga() + "</tpUnidCarga>";
                                        xml += "<idUnidCarga>" + u.getIdUnidCarga() + "</idUnidCarga>";
                                        xml += "</infUnidCarga>";
                                    }
                                }
                                xml += "</infUnidTransp>";
                            }
                        }
                        xml += "</infNF>";
                    }
                }//FIM INF NF

                if (cte.getRemetente().getInfNFe() != null) {
                    for (CteInfNFe nf : cte.getRemetente().getInfNFe()) {
                        xml += "<infNFe>";
                        xml += "<chave>" + nf.getChave() + "</chave>";
                        if (nf.getPIN() != null && nf.getPIN() > 0) {
                            xml += "<PIN>" + nf.getPIN() + "</PIN>";
                        }
                        if (nf.getDPrev() != null) {
                            xml += "<dPrev>" + Utils.getInstance().convertDataToStringSefaz(nf.getDPrev()) + "</dPrev>";
                        }
                        if (nf.getInfUnidTransp() != null) {
                            for (CteInfUnidTransp unid : nf.getInfUnidTransp()) {
                                xml += "<infUnidTransp>";
                                xml += "<tpUnidTransp>" + unid.getTpUnidTransp() + "</tpUnidTransp>";
                                xml += "<idUnidTransp>" + unid.getIdUnidTransp() + "</idUnidTransp>";
                                if (unid.getLacUnidTransp() != null) {
                                    for (CteLacUnidTransp lacUnidTransp : unid.getLacUnidTransp()) {
                                        xml += "<lacUnidTransp>";
                                        xml += "<nLacre>" + lacUnidTransp.getNLacre() + "</nLacre>";
                                        xml += "</lacUnidTransp>";
                                    }
                                }
                                if (unid.getInfUnidCarga() != null) {
                                    for (CteInfUnidCarga u : unid.getInfUnidCarga()) {
                                        xml += "<infUnidCarga>";
                                        xml += "<tpUnidCarga>" + u.getTpUnidCarga() + "</tpUnidCarga>";
                                        xml += "<idUnidCarga>" + u.getIdUnidCarga() + "</idUnidCarga>";
                                        xml += "</infUnidCarga>";
                                    }
                                }
                                xml += "</infUnidTransp>";
                            }
                        }
                        xml += "</infNFe>";
                    }
                }
                // FIM NFE
                
                //OUTROS
                if (cte.getRemetente().getInfOutros() != null) {
                    for (CteInfOutros outros : cte.getRemetente().getInfOutros()) {
                        
                        xml += "<infOutros>";
                        xml += "<tpDoc>" + Utils.getInstance().zeroFill("" + outros.getTpDoc(), 2) + "</tpDoc>";
                        if (outros.getDescOutros() != null && outros.getDescOutros().length() > 0) {
                            xml += "<descOutros>" + outros.getDescOutros() + "</descOutros>";
                        }
                        if (outros.getNDoc() != null && outros.getNDoc().length() > 0) {
                            xml += "<nDoc>" + outros.getNDoc() + "</nDoc>";
                        }
                        xml += "<dEmi>" + Utils.getInstance().convertDataToStringSefaz(outros.getDEmi()) + "</dEmi>";
                        if (outros.getVDocFisc() != null && outros.getVDocFisc() > 0) {
                            xml += "<vDocFisc>" + Utils.getInstance().getDecimalFormatter(13, 2).format(outros.getVDocFisc()) + "</vDocFisc>";
                        }
                        if (outros.getInfUnidTransp() != null) {
                            for (CteInfUnidTransp unid : outros.getInfUnidTransp()) {
                                xml += "<infUnidTransp>";
                                xml += "<tpUnidTransp>" + unid.getTpUnidTransp() + "</tpUnidTransp>";
                                xml += "<idUnidTransp>" + unid.getIdUnidTransp() + "</idUnidTransp>";
                                if (unid.getLacUnidTransp() != null) {
                                    for (CteLacUnidTransp lacUnidTransp : unid.getLacUnidTransp()) {
                                        xml += "<lacUnidTransp>";
                                        xml += "<nLacre>" + lacUnidTransp.getNLacre() + "</nLacre>";
                                        xml += "</lacUnidTransp>";
                                    }
                                }
                                if (unid.getInfUnidCarga() != null) {
                                    for (CteInfUnidCarga u : unid.getInfUnidCarga()) {
                                        xml += "<infUnidCarga>";
                                        xml += "<tpUnidCarga>" + u.getTpUnidCarga() + "</tpUnidCarga>";
                                        xml += "<idUnidCarga>" + u.getIdUnidCarga() + "</idUnidCarga>";
                                        xml += "</infUnidCarga>";
                                    }
                                }
                                xml += "</infUnidTransp>";
                            }
                        }
                        xml += "</infOutros>";
                    }
                }
                //FIM OUTROS
                xml += "</infDoc>";
            }
            //INFORMACOES DOCS ANTERIOR
            Collection<CteDocAnt> listDocAnt = cte.getInfCTeNorm().getDocAnt();
            if (listDocAnt != null) {
                for (CteDocAnt docAnt : listDocAnt) {
                    xml += "<docAnt>";
                    xml += "<emiDocAnt>";
                    if (docAnt.getCNPJ() != null && docAnt.getCNPJ().length() > 0) {
                        xml += "<CNPJ>" + Utils.getInstance().getDigitos(docAnt.getCNPJ()) + "</CNPJ>";
                    }
                    if (docAnt.getCPF() != null && docAnt.getCPF().length() > 0) {
                        xml += "<CPF>" + Utils.getInstance().getDigitos(docAnt.getCPF()) + "</CPF>";
                    }
                    xml += "<IE>" + docAnt.getIE() + "</IE>";
                    xml += "<UF>" + docAnt.getUF() + "</UF>";
                    xml += "<xNome>" + docAnt.getXNome() + "</xNome>";

                    Collection<CteIdDocAnt> listIdDocAnt = docAnt.getIdDocAnt();
                    if (listIdDocAnt != null) {
                        for (CteIdDocAnt idDocAnt : listIdDocAnt) {
                            xml += "<idDocAnt>";
                            if (idDocAnt.getChave() != null && idDocAnt.getChave().length() > 0) {
                                xml += "<idDocAntEle>";
                                xml += "<chCTe>" + idDocAnt.getChave() + "</chCTe>";
                                xml += "</idDocAntEle>";
                            } else {
                                xml += "<idDocAntPap>";
                                xml += "<tpDoc>" + Utils.getInstance().zeroFill(idDocAnt.getTpDoc() + "", 2) + "</tpDoc>";
                                xml += "<serie>" + idDocAnt.getSerie() + "</serie>";
                                if (idDocAnt.getSubser() != null && idDocAnt.getSubser().length() > 0) {
                                    xml += "<subser>" + idDocAnt.getSubser() + "</subser>";
                                }
                                xml += "<nDoc>" + idDocAnt.getNDoc() + "</nDoc>";
                                xml += "<dEmi>" + Utils.getInstance().convertDataToStringSefaz(idDocAnt.getDEmi()) + "</dEmi>";
                                xml += "</idDocAntPap>";
                            }
                            xml += "</idDocAnt>";
                        }
                    }
                    xml += "</emiDocAnt>";
                    xml += "</docAnt>";
                }
            }
            //FIM DOCS ANTERIOR

//            NAO TEM MAIS GRUPO SEG NA 3.00
//            //INICIO SEGURO
//            Collection<CteSeg> listSeg = cte.getInfCTeNorm().getSeg();
//            if (listSeg != null) {
//                for (CteSeg seg : listSeg) {
//                    xml += "<seg>";
//                    xml += "<respSeg>" + seg.getRespSeg() + "</respSeg>";
//                    if (seg.getXSeg() != null && seg.getXSeg().length() > 0) {
//                        xml += "<xSeg>" + seg.getXSeg() + "</xSeg>";
//                    }
//                    if (seg.getNApol() != null && seg.getNApol().length() > 0) {
//                        xml += "<nApol>" + seg.getNApol() + "</nApol>";
//                    }
//                    if (seg.getNAver() != null && seg.getNAver().length() > 0) {
//                        xml += "<nAver>" + seg.getNAver() + "</nAver>";
//                    }
//                    if (seg.getVCarga() != null && seg.getVCarga() > 0) {
//                        xml += "<vCarga>" + Utils.getInstance().getDecimalFormatter(12, 2).format(seg.getVCarga()) + "</vCarga>";
//                    }
//                    xml += "</seg>";
//                }
//            }
//            //FIM SEGURO

            if (cte.getInfModal() != null) {
                xml += "<infModal versaoModal=\"" + versao + "\">";

                //INICIO Informações do modal Rodoviário
                CteRodo rodo = cte.getInfCTeNorm().getRodo();
                if (rodo != null) {
                    xml += "<rodo xmlns=\"http://www.portalfiscal.inf.br/cte\">";
                    if (rodo.getRNTRC() != null && !rodo.getRNTRC().equals("null")) {
                        xml += "<RNTRC>" + Utils.getInstance().zeroFill("" + rodo.getRNTRC(), 8) + "</RNTRC>";
                    }
                    //eliminados na 3.00
//                    xml += "<dPrev>" + Utils.getInstance().convertDataToStringSefaz(rodo.getdPrev()) + "</dPrev>";
//                    if (rodo.getLota() != null) {
//                        xml += "<lota>" + rodo.getLota() + "</lota>";
//                    }
                    Collection<CteOcc> listOcc = rodo.getOcc();
                    if (listOcc != null) {
                        for (CteOcc occ : listOcc) {
                            xml += "<occ>";
                            if (occ.getSerie() != null && occ.getSerie().length() > 0) {
                                xml += "<serie>" + occ.getSerie() + "</serie>";
                            }
                            xml += "<nOcc>" + occ.getNOcc() + "</nOcc>";
                            xml += "<dEmi>" + Utils.getInstance().convertDataToStringSefaz(occ.getDEmi()) + "</dEmi>";
                            xml += "<emiOcc>";
                            xml += "<CNPJ>" + Utils.getInstance().getDigitos(occ.getCNPJ()) + "</CNPJ>";
                            if (occ.getCInt() != null && occ.getCInt().length() > 0) {
                                xml += "<cInt>" + occ.getCInt() + "</cInt>";
                            }
                            xml += "<IE>" + Utils.getInstance().getDigitos(occ.getIE()) + "</IE>";
                            xml += "<UF>" + occ.getUF() + "</UF>";
                            if (occ.getFone() != null && occ.getFone().length() >= 7) {
                                xml += "<fone>" + occ.getFone() + "</fone>";
                            }
                            xml += "</emiOcc>";
                            xml += "</occ>";
                        }
                    }
                  //eliminados na 3.00
//                    Collection<CteValePed> listValePed = cte.getInfCTeNorm().getRodo().getValePed();
//                    if (listValePed != null) {
//                        for (CteValePed valePed : listValePed) {
//                            xml += "<valePed>";
//                            xml += "<CNPJForn>" + Utils.getInstance().getDigitos(valePed.getCNPJForn()) + "</CNPJForn>";
//                            xml += "<nCompra>" + valePed.getnCompra() + "</nCompra>";
//                            xml += "<CNPJPg>" + Utils.getInstance().getDigitos(valePed.getCNPJPg()) + "</CNPJPg>";
//                            xml += "<vValePed>" + Utils.getInstance().getDecimalFormatter(12, 2).format(valePed.getVValePed()) + "</vValePed>";
//                            xml += "</valePed>";
//                        }
//                    }
//                    Collection<CteVeic> listVeic = rodo.getVeic();
//                    if (listVeic != null) {
//                        for (CteVeic veic : listVeic) {
//                            xml += "<veic>";
//                            if (veic.getCInt() != null && veic.getCInt().length() > 0) {
//                                xml += "<cInt>" + veic.getCInt() + "</cInt>";
//                            }
//                            xml += "<RENAVAM>" + veic.getRENAVAM() + "</RENAVAM>";
//                            xml += "<placa>" + veic.getPlaca() + "</placa>";
//                            xml += "<tara>" + veic.getTara() + "</tara>";
//                            xml += "<capKG>" + veic.getCapKG() + "</capKG>";
//                            xml += "<capM3>" + veic.getCapM3() + "</capM3>";
//                            xml += "<tpProp>" + veic.getTpProp() + "</tpProp>";
//                            xml += "<tpVeic>" + veic.getTpVeic() + "</tpVeic>";
//                            xml += "<tpRod>" + Utils.getInstance().zeroFill("" + veic.getTpRod(), 2) + "</tpRod>";
//                            xml += "<tpCar>" + Utils.getInstance().zeroFill("" + veic.getTpCar(), 2) + "</tpCar>";
//                            xml += "<UF>" + veic.getUF() + "</UF>";
//                            if (veic.getProp() != null) {
//                                xml += "<prop>";
//                                if (veic.getProp().getCNPJ() != null && veic.getProp().getCNPJ().length() > 0) {
//                                    xml += "<CNPJ>" + Utils.getInstance().getDigitos(veic.getProp().getCNPJ()) + "</CNPJ>";
//                                }
//                                if (veic.getProp().getCPF() != null && veic.getProp().getCPF().length() > 0) {
//                                    xml += "<CPF>" + Utils.getInstance().getDigitos(veic.getProp().getCPF()) + "</CPF>";
//                                }
//                                xml += "<RNTRC>" + Utils.getInstance().zeroFill(veic.getProp().getRNTRC(), 8) + "</RNTRC>";
//                                xml += "<xNome>" + veic.getProp().getXNome() + "</xNome>";
//                                xml += "<IE>" + veic.getProp().getIE() + "</IE>";
//                                xml += "<UF>" + veic.getProp().getUF() + "</UF>";
//                                xml += "<tpProp>" + veic.getProp().getTpProp() + "</tpProp>";
//                                xml += "</prop>";
//                            }
//                            xml += "</veic>";
//                        }
//                    }
//                    Collection<CteLacRodo> listLacRodo = rodo.getLacRodo();
//                    if (listLacRodo != null) {
//                        for (CteLacRodo lacRodo : listLacRodo) {
//                            xml += "<lacRodo>";
//                            xml += "<nLacre>" + lacRodo.getNLacre() + "</nLacre>";
//                            xml += "</lacRodo>";
//                        }
//                    }
//                    Collection<CteMoto> listMoto = rodo.getMoto();
//                    if (listMoto != null) {
//                        for (CteMoto moto : listMoto) {
//                            xml += "<moto>";
//                            xml += "<xNome>" + moto.getxNome() + "</xNome>";
//                            xml += "<CPF>" + moto.getCPF() + "</CPF>";
//                            xml += "</moto>";
//                        }
//                    }
                    xml += "</rodo>";
                }//FIM Informações do modal Rodoviário

                //INICIO MODAL AEREO
                CteAereo aereo = cte.getInfCTeNorm().getAereo();
                if (aereo != null) {
                    xml += "<aereo>";
                    if (aereo.getNMinu() != null && aereo.getNMinu() > 0) {
                        xml += "<nMinu>" + Utils.getInstance().zeroFill("" + aereo.getNMinu(), 9) + "</nMinu>";
                    }
                    if (aereo.getNOCA() != null && aereo.getNOCA().length() > 0) {
                        xml += "<nOCA>" + aereo.getNOCA() + "</nOCA>";
                    }
                    if (aereo.getDPrev() != null) {
                        xml += "<dPrev>" + Utils.getInstance().convertDataToStringSefaz(aereo.getDPrev()) + "</dPrev>";
                    }
                    if (aereo.getXLAgEmi() != null && aereo.getXLAgEmi().length() > 0) {
                        xml += "<xLAgEmi>" + aereo.getXLAgEmi() + "</xLAgEmi>";
                    }
                    if (aereo.getCIATA() != null && aereo.getCIATA().length() > 0) {
                        xml += "<IdT>" + aereo.getCIATA() + "</IdT>";
                    } else if (aereo.getIdT() != null && aereo.getIdT().length() > 0) {
                        xml += "<IdT>" + aereo.getIdT() + "</IdT>";
                    }
                    xml += "<tarifa>";
                    if (aereo.getTrecho() != null && aereo.getTrecho().length() > 0) {
                        //xml += "<trecho>" + aereo.getTrecho() + "</trecho>";
                    }
                    if (aereo.getCL() != null && aereo.getCL().length() > 0) {
                        xml += "<CL>" + aereo.getCL() + "</CL>";
                    }
                    if (aereo.getCTar() != null && aereo.getCTar().length() > 0) {
                        xml += "<cTar>" + aereo.getCTar() + "</cTar>";
                    }
                    //Eh obrigatorio o campo no XML...
                    //if (aereo.getVTar() != null && aereo.getVTar().doubleValue() > 0) {
                    xml += "<vTar>" + Utils.getInstance().getDecimalFormatter(13, 2).format(aereo.getVTar()) + "</vTar>";
                    //}
                    xml += "</tarifa>";
                    xml += "<natCarga>";
                    if (aereo.getxDime() != null && aereo.getxDime().length() > 0) {
                        xml += "<xDime>" + aereo.getxDime() + "</xDime>";
                    }
                    if (aereo.getcInfManu() != null && aereo.getcInfManu().length() > 0) {
                        xml += "<cInfManu>" + aereo.getcInfManu() + "</cInfManu>";
                    }
                    if (aereo.getcImp() != null && aereo.getcImp().length() > 0) {
                        xml += "<cIMP>" + aereo.getcImp() + "</cIMP>";
                    }
                    xml += "</natCarga>";
                    xml += "</aereo>";
                }
                //FIM MODAL AEREO

                //INICIO MODAL aquav
                CteAquav aquav = cte.getInfCTeNorm().getAquav();
                if (aquav != null) {
                    xml += "<aquav>";
                    if (aquav.getVPrest() != null && aquav.getVPrest() > 0) {
                        xml += "<vPrest>" + Utils.getInstance().getDecimalFormatter(13, 2).format(aquav.getVPrest()) + "</vPrest>";
                    }
                    if (aquav.getVAFRMM() != null && aquav.getVAFRMM() > 0) {
                        xml += "<vAFRMM>" + Utils.getInstance().getDecimalFormatter(13, 2).format(aquav.getVAFRMM()) + "</vAFRMM>";
                    }
                    if (aquav.getNBooking() != null && aquav.getNBooking().length() > 0) {
                        xml += "<nBooking>" + aquav.getNBooking() + "</nBooking>";
                    }
                    if (aquav.getNCtrl() != null && aquav.getNCtrl().length() > 0) {
                        xml += "<nCtrl>" + aquav.getNCtrl() + "</nCtrl>";
                    }
                    if (aquav.getXNavio() != null && aquav.getXNavio().length() > 0) {
                        xml += "<xNavio>" + aquav.getXNavio() + "</xNavio>";
                    }
                    if (aquav.getNViag() != null && aquav.getNViag().length() > 0) {
                        xml += "<nViag>" + aquav.getNViag() + "</nViag>";
                    }
                    if (aquav.getDirec() != null && aquav.getDirec().length() > 0) {
                        xml += "<direc>" + aquav.getDirec() + "</direc>";
                    }
                    if (aquav.getPrtEmb() != null && aquav.getPrtEmb().length() > 0) {
                        xml += "<prtEmb>" + aquav.getPrtEmb() + "</prtEmb>";
                    }
                    if (aquav.getPrtTrans() != null && aquav.getPrtTrans().length() > 0) {
                        xml += "<prtTrans>" + aquav.getPrtTrans() + "</prtTrans>";
                    }
                    if (aquav.getPrtDest() != null && aquav.getPrtDest().length() > 0) {
                        xml += "<prtDest>" + aquav.getPrtDest() + "</prtDest>";
                    }
                    if (aquav.getTpNav() != null && aquav.getTpNav() > 0) {
                        xml += "<tpNav>" + aquav.getTpNav() + "</tpNav>";
                    }
                    if (aquav.getIrin() != null && aquav.getIrin().length() > 0) {
                        xml += "<irin>" + aquav.getIrin() + "</irin>";
                    }
                    xml += "</aquav>";
                }

                xml += "</infModal>";
            }

            Collection<CtePeri> listPeri = cte.getInfCTeNorm().getPeri();
            if (listPeri != null) {
                for (CtePeri peri : listPeri) {
                    xml += "<peri>";
                    xml += "<nONU>" + peri.getNONU() + "</nONU>";
                    xml += "<xNomeAE>" + peri.getXNomeAE() + "</xNomeAE>";
                    xml += "<xClaRisco>" + peri.getXClaRisco() + "</xClaRisco>";
                    if (peri.getGrEmb() != null && peri.getGrEmb().length() > 0) {
                        xml += "<grEmb>" + peri.getGrEmb() + "</grEmb>";
                    }
                    xml += "<qTotProd>" + peri.getQTotProd() + "</qTotProd>";
                    if (peri.getqVolTipo() != null && peri.getqVolTipo().length() > 0) {
                        xml += "<qVolTipo>" + peri.getqVolTipo() + "</qVolTipo>";
                    }
                    if (peri.getPontoFulgor() != null && peri.getPontoFulgor().length() > 0) {
                        xml += "<pontoFulgor>" + peri.getPontoFulgor() + "</pontoFulgor>";
                    }
                    xml += "</peri>";
                }
            }

            Collection<CteVeicNovos> listVeicNovos = cte.getInfCTeNorm().getVeicNovos();
            if (listVeicNovos != null) {
                for (CteVeicNovos veic : listVeicNovos) {
                    xml += "<veicNovos>";
                    xml += "<chassi>" + veic.getChassi() + "</chassi>";
                    xml += "<cCor>" + veic.getCCor() + "</cCor>";
                    xml += "<xCor>" + veic.getXCor() + "</xCor>";
                    xml += "<cMod>" + veic.getCMod() + "</cMod>";
                    xml += "<vUnit>" + Utils.getInstance().getDecimalFormatter(13, 2).format(veic.getVUnit()) + "</vUnit>";
                    xml += "<vFrete>" + Utils.getInstance().getDecimalFormatter(13, 2).format(veic.getVFrete()) + "</vFrete>";
                    xml += "</veicNovos>";
                }
            }

            if (cte.getInfCTeNorm().getFat() != null) {
                CteFat f = cte.getInfCTeNorm().getFat();
                xml += "<cobr><fat>";
                xml += "<nFat>" + f.getNFat() + "</nFat>";
                if (f.getVOrig() > 0) {
                    xml += "<vOrig>" + Utils.getInstance().getDecimalFormatter(13, 2).format(f.getVOrig()) + "</vOrig>";
                }
                if (f.getVDesc() > 0) {
                    xml += "<vDesc>" + Utils.getInstance().getDecimalFormatter(13, 2).format(f.getVDesc()) + "</vDesc>";
                }
                if (f.getVLiq() > 0) {
                    xml += "<vLiq>" + Utils.getInstance().getDecimalFormatter(13, 2).format(f.getVLiq()) + "</vLiq>";
                }
                xml += "</fat>";
                for (CteDup dup : f.getDup()) {
                    xml += "<dup>";
                    xml += "<nDup>" + dup.getNDup() + "</nDup>";
                    xml += "<dVenc>" + Utils.getInstance().convertDataToStringSefaz(dup.getDVenct()) + "</dVenc>";
                    if (dup.getVDup() > 0) {
                        xml += "<vDup>" + Utils.getInstance().getDecimalFormatter(13, 2).format(dup.getVDup()) + "</vDup>";
                    }
                    xml += "</dup>";
                }
                xml += "</cobr>";
            }

            //CTe Substituto
            if (cte.getInfCTeNorm().getInfCteSub() != null) {
                CteInfCteSub sub = cte.getInfCTeNorm().getInfCteSub();
                xml += "<infCteSub>";
                xml += "<chCte>" + sub.getChCte() + "</chCte>";
                if (sub.isIcms()) {
                    xml += "<tomaICMS>";
                    if (sub.getChNFe() != null) {
                        xml += "<refNFe>" + sub.getChNFe() + "</refNFe>";
                    } else {
                        xml += "<refNF>";
                        if (sub.getCNPJ() != null) {
                            xml += "<CNPJ>" + sub.getCNPJ() + "</CNPJ>";
                        }
                        if (sub.getMod() != null) {
                            xml += "<mod>" + sub.getMod() + "</mod>";
                        }
                        if (sub.getSerie() != null) {
                            xml += "<serie>" + sub.getSerie() + "</serie>";
                        }
                        if (sub.getSubserie() != null) {
                            xml += "<subserie>" + sub.getSubserie() + "</subserie>";
                        }
                        if (sub.getNro() != null) {
                            xml += "<nro>" + sub.getNro() + "</nro>";
                        }
                        if (sub.getValor() != null) {
                            xml += "<valor>" + Utils.getInstance().getDecimalFormatter(13, 2).format(sub.getValor()) + "</valor>";
                        }
                        if (sub.getDEmi() != null) {
                            xml += "<dEmi>" + Utils.getInstance().convertDataToStringSefaz(sub.getDEmi()) + "</dEmi>";
                        }

                        xml += "</refNF>";
                        if (sub.getRefCte() != null) {
                            xml += "<refCte>" + sub.getRefCte() + "</refCte>";
                        }
                    }
                    xml += "</tomaICMS>";
                } else {
                    xml += "<tomaNaoICMS>";
                    xml += "<refCteAnu>" + sub.getRefCteAnu() + "</refCteAnu>";
                    xml += "</tomaNaoICMS>";
                }
                xml += "</infCteSub>";
            }
            xml += "</infCTeNorm>";
        }

        //Detalhamento do CT-e complementado
        if (cte.getInfCTeComp()
                != null) {
            xml += "<infCteComp>";
            xml += "<chCTe>" + cte.getInfCTeComp().getChave() + "</chCTe>";
            xml += "<vPresComp>";
            xml += "<vTPrest>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getInfCTeComp().getVTPrest()) + "</vTPrest>";

            Collection<CteCompComp> listCompComp = cte.getInfCTeComp().getCompComp();
            if (listCompComp != null) {
                for (CteCompComp compComp : listCompComp) {
                    xml += "<compComp>";
                    xml += "<xNome>" + compComp.getXNome() + "</xNome>";
                    xml += "<vComp>" + Utils.getInstance().getDecimalFormatter(13, 2).format(compComp.getVComp()) + "</vComp>";
                    xml += "</compComp>";
                }
            }
            xml += "</vPresComp>";
            //INICIO IMPOSTOS
            xml += "<impComp>";
            if (cte.getInfCTeComp().getImpComp() != null) {
                CteImposto impComp = cte.getInfCTeComp().getImpComp();
                xml += "<ICMSComp>";
                if (impComp.getCST() != null) {
                    if (impComp.getCST() == 0) {
                        xml += "<CST00>";
                        xml += "<CST>00</CST>";
                        xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(impComp.getVBC()) + "</vBC>";
                        xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(impComp.getPICMS()) + "</pICMS>";
                        xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(impComp.getVICMS()) + "</vICMS>";
                        xml += "</CST00>";
                    } else if (impComp.getCST() == 20) {
                        xml += "<CST20>";
                        xml += "<CST>20</CST>";
                        xml += "<pRedBC>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getPRedBC()) + "</pRedBC>";
                        xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBC()) + "</vBC>";
                        xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getPICMS()) + "</pICMS>";
                        xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getVICMS()) + "</vICMS>";
                        xml += "</CST20>";
                    } else if (cte.getImp().getCST() == 40 || cte.getImp().getCST() == 41 || cte.getImp().getCST() == 51) {
                        xml += "<CST45>";
                        xml += "<CST>" + cte.getImp().getCST() + "</CST>";
                        xml += "</CST45>";
                    } else if (cte.getImp().getCST() == 90) {
                        if (cte.getImp().getPRedBC() > 0 && cte.getImp().getVCred() > 0) {
                            xml += "<CST90>";
                            xml += "<CST>90</CST>";
                            xml += "<pRedBC>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getPRedBC()) + "</pRedBC>";
                            xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBC()) + "</vBC>";
                            xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getPICMS()) + "</pICMS>";
                            xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getVICMS()) + "</vICMS>";
                            if (cte.getImp().getVCred() > 0) {
                                xml += "<vCred>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVCred()) + "</vCred>";
                            }
                            xml += "</CST90>";
                        } else if (cte.getImp().getPRedBC() > 0) {
                            xml += "<CST81>";
                            xml += "<CST>90</CST>";
                            xml += "<pRedBC>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getPRedBC()) + "</pRedBC>";
                            xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBC()) + "</vBC>";
                            xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getPICMS()) + "</pICMS>";
                            xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getVICMS()) + "</vICMS>";
                            xml += "</CST81>";
                        } else {
                            xml += "<CST80>";
                            xml += "<CST>90</CST>";
                            xml += "<vBC>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVBC()) + "</vBC>";
                            xml += "<pICMS>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getPICMS()) + "</pICMS>";
                            xml += "<vICMS>" + Utils.getInstance().getDecimalFormatter(3, 2).format(cte.getImp().getVICMS()) + "</vICMS>";
                            if (cte.getImp().getVCred() > 0) {
                                xml += "<vCred>" + Utils.getInstance().getDecimalFormatter(13, 2).format(cte.getImp().getVCred()) + "</vCred>";
                            }
                            xml += "</CST80>";
                        }
                    }
                }
                xml += "</ICMSComp>";
                if (cte.getImp().getInfAdFisco() != null && cte.getImp().getInfAdFisco().length() > 0) {
                    xml += "<infAdFisco>" + cte.getImp().getInfAdFisco() + "</infAdFisco>";
                }
            }
            xml += "</impComp>";
            //FIM IMPOSTOS
            xml += "</infCteComp>";
        }
        //FIM Detalhamento do CT-e complementado

        //      CTe Anulacao
        if (cte.getInfAnu() != null) {
            xml += "<infCteAnu>";
            xml += "<chCte>" + cte.getInfAnu().getChCte() + "</chCte>";
            xml += "<dEmi>" + Utils.getInstance().convertDataToStringSefaz(cte.getInfAnu().getDEmi()) + "</dEmi>";
            xml += "</infCteAnu>";
        }

        if (cte.getInfCTeNorm() != null && cte.getInfCTeNorm().getAutXml() != null) {
            for (CteAutXML a : cte.getInfCTeNorm().getAutXml()) {
                xml += "<autXML>";
                if (a.getCnpj() != null && a.getCnpj().length() > 0) {
                    xml += "<CNPJ>" + a.getCnpj() + "</CNPJ>";
                } else {
                    xml += "<CPF>" + a.getCpf() + "</CPF>";
                }
                xml += "</autXML>";
            }
        }

        xml += "</infCte>";
        xml += "</CTe>";
//        System.out.println(xml);
        return xml;
    }
}
