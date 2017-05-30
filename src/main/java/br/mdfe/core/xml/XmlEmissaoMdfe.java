/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core.xml;

import br.mdfe.core.CertDig;
import br.mdfe.model.Mdfe;
import br.mdfe.model.MdfeAutXML;
import br.mdfe.model.MdfeCondutor;
import br.mdfe.model.MdfeInfANTT;
import br.mdfe.model.MdfeInfCIOT;
import br.mdfe.model.MdfeInfCT;
import br.mdfe.model.MdfeInfCTe;
import br.mdfe.model.MdfeInfMunCarrega;
import br.mdfe.model.MdfeInfMunDescarga;
import br.mdfe.model.MdfeInfNF;
import br.mdfe.model.MdfeInfNFe;
import br.mdfe.model.MdfeInfPercurso;
import br.mdfe.model.MdfeInfUnidCarga;
import br.mdfe.model.MdfeInfUnidTransp;
import br.mdfe.model.MdfeLacUnidCarga;
import br.mdfe.model.MdfePeri;
import br.mdfe.model.MdfeRodo;
import br.mdfe.model.MdfeSeg;
import br.mdfe.model.MdfeValePed;
import br.mdfe.model.MdfeVeicReboque;
import br.utils.Arquivo;
import br.utils.Configuracoes;
import br.utils.Formatador;
import br.utils.Utils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author derli
 */
public class XmlEmissaoMdfe {

    private final static XmlEmissaoMdfe instancia = new XmlEmissaoMdfe();
    private final SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") {
        public StringBuffer format(Date date, StringBuffer toAppendTo, java.text.FieldPosition pos) {
            StringBuffer toFix = super.format(date, toAppendTo, pos);
            return toFix.insert(toFix.length()-2, ':');
        };
    };
    
    private XmlEmissaoMdfe() {
    }

    public static XmlEmissaoMdfe getInstance() {
        return (instancia);
    }

    public String geraXmlMdfeLiberada(Mdfe mdfe) {
        String nomeArquivoDist = Configuracoes.getInstance().getDistDir(mdfe.getEmit().getCNPJ(), mdfe.getMod() + "", mdfe.getDhEmi()) + mdfe.getChAcesso() + "-procMDFE.xml";
        File fdist = new File(nomeArquivoDist);
        if (!fdist.exists()) {
            String versao = mdfe.getVersao();
            String dadosMsgXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
                    + "<mdfeProc xmlns=\"http://www.portalfiscal.inf.br/mdfe\" versao=\"" + versao + "\">";

            String xmlNota = XmlEmissaoMdfe.getInstance().getXml(mdfe, versao);

            String nomeArquivoNota = Configuracoes.getInstance().getTmpDir() + "mdfe-" + mdfe.getNMDF() + ".xml";
            String nomeArquivoNotaAss = Configuracoes.getInstance().getTmpDir() + "mdfe-" + mdfe.getNMDF() + ".xml";
            Arquivo a = new Arquivo(nomeArquivoNota);
            a.abrirEscrita();
            a.escreverLinha(xmlNota);
            a.fecharArquivo();

            CertDig.getInstance().Ass(nomeArquivoNota, nomeArquivoNotaAss, "1", mdfe.getEmpresa());

            Arquivo aAssinado = new Arquivo(nomeArquivoNota);
            aAssinado.abrirLeitura();
            dadosMsgXml += aAssinado.ler();
            aAssinado.fecharArquivo();
            dadosMsgXml += "<protMDFe versao=\"" + versao + "\">";
            dadosMsgXml += "<infProt Id=\"ID" + mdfe.getNProt() + "\">";
           dadosMsgXml += "<tpAmb>" + mdfe.getTpAmb() + "</tpAmb>";
            dadosMsgXml += "<verAplic>" + mdfe.getVerAplic() + "</verAplic>";
            dadosMsgXml += "<chMDFe>" + mdfe.getChAcesso() + "</chMDFe>";
            if (mdfe.getDhRecbto() != null) {
//                SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
//                fDate.setLenient(false);
                String dt = dateFormat.format(mdfe.getDhRecbto());
                //dt = dt.substring(0, dt.length() - 2) + ":00";
                dadosMsgXml += "<dhRecbto>" + dt + "</dhRecbto>";
            }
            dadosMsgXml += "<nProt>" + mdfe.getNProt() + "</nProt>";
            dadosMsgXml += "<digVal>" + mdfe.getDigVal() + "</digVal>";
            dadosMsgXml += "<cStat>" + mdfe.getCStat() + "</cStat>";
            dadosMsgXml += "<xMotivo>" + mdfe.getXMotivo() + "</xMotivo>";
            dadosMsgXml += "</infProt>";
            dadosMsgXml += "</protMDFe>";

            dadosMsgXml += "</mdfeProc>";

            Arquivo a1 = new Arquivo(nomeArquivoDist);
            a1.abrirEscrita();
            a1.escreverLinha(dadosMsgXml);
            a1.fecharArquivo();

            //exclui arquivos temporarios
            aAssinado.excluir();
        }
        return nomeArquivoDist;
    }

    public String getXml(Mdfe mdfe, String versao) {
        if (mdfe.getVersao() != null) {
            versao = mdfe.getVersao();
        }
        String saida;
        String uf = mdfe.getEmit().getCMun() + "";
        uf = uf.substring(0, 2);
        saida = "<MDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\">"
                + "<infMDFe Id=\"MDFe" + mdfe.getChAcesso() + "\" versao=\"" + versao + "\">"
                + "<ide>"
                + "<cUF>" + Utils.getInstance().zeroFill(uf, 2) + "</cUF>";
        saida += "<tpAmb>" + mdfe.getTpAmb() + "</tpAmb>";
        saida += "<tpEmit>" + mdfe.getTpEmit() + "</tpEmit>";
        
        if(mdfe.getTpTransp() != null && mdfe.getTpTransp() > 0)
        	saida += "<tpTransp>" + mdfe.getTpTransp() + "</tpTransp>";
        
        saida += "<mod>" + Utils.getInstance().zeroFill(mdfe.getMod() + "", 2) + "</mod>";

        saida += "<serie>" + mdfe.getSerie() + "</serie>"
                + "<nMDF>" + mdfe.getNMDF() + "</nMDF>"
                + "<cMDF>" + Utils.getInstance().zeroFill(mdfe.getCMDF() + "", 8) + "</cMDF>"
                + "<cDV>" + mdfe.getCDV() + "</cDV>";

        saida += "<modal>" + mdfe.getModal() + "</modal>";

//        SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
//        fDate.setLenient(false);
        String dt = dateFormat.format(mdfe.getDhEmi());
        //dt = dt.substring(0, dt.length() - 2) + ":00";
        saida += "<dhEmi>" + dt + "</dhEmi>";

        saida += "<tpEmis>" + mdfe.getTpEmis() + "</tpEmis>";
        saida += "<procEmi>" + mdfe.getProcEmi() + "</procEmi>";
        saida += "<verProc>" + mdfe.getVerProc() + "</verProc>";
        saida += "<UFIni>" + mdfe.getUFIni() + "</UFIni>";
        saida += "<UFFim>" + mdfe.getUFFim() + "</UFFim>";

        if (mdfe.getInfMunCarrega() != null) {
            for (MdfeInfMunCarrega c : mdfe.getInfMunCarrega()) {
                saida += "<infMunCarrega>";
                saida += "<cMunCarrega>" + c.getCMunCarrega() + "</cMunCarrega>";
                saida += "<xMunCarrega>" + c.getXMunCarrega() + "</xMunCarrega>";
                saida += "</infMunCarrega>";
            }
        }
        if (mdfe.getInfPercurso() != null && !mdfe.getInfPercurso().isEmpty()) {
            for (MdfeInfPercurso c : mdfe.getInfPercurso()) {
                saida += "<infPercurso>";
                saida += "<UFPer>" + c.getUFPer() + "</UFPer>";
                saida += "</infPercurso>";
            }
        }
        saida += "</ide>";

        saida += "<emit>";
        saida += "<CNPJ>" + Utils.getInstance().getDigitos(mdfe.getEmit().getCNPJ()) + "</CNPJ>";
        saida += "<IE>" + Utils.getInstance().replace(mdfe.getEmit().getIE()) + "</IE>";
        saida += "<xNome>" + mdfe.getEmit().getXNome() + "</xNome>";
        if (mdfe.getEmit().getXFant().length() > 2) {
            saida += "<xFant>" + mdfe.getEmit().getXFant() + "</xFant>";
        }
        saida += "<enderEmit>" + "<xLgr>" + mdfe.getEmit().getXLgr() + "</xLgr>";
        saida += "<nro>" + mdfe.getEmit().getNro() + "</nro>";
        if (mdfe.getEmit().getXCpl() != null && !mdfe.getEmit().getXCpl().trim().equals("")) {
            saida += "<xCpl>" + Utils.getInstance().removeAcentuacao(mdfe.getEmit().getXCpl()) + "</xCpl>";
        }
        saida += "<xBairro>" + Utils.getInstance().removeAcentuacao(mdfe.getEmit().getXBairro()) + "</xBairro>"
                + "<cMun>" + Utils.getInstance().zeroFill("" + mdfe.getEmit().getCMun(), 7) + "</cMun>"
                + "<xMun>" + Utils.getInstance().removeAcentuacao(mdfe.getEmit().getXMun()) + "</xMun>"
                + "<CEP>" + Utils.getInstance().zeroFill("" + mdfe.getEmit().getCEP(), 8) + "</CEP>"
                + "<UF>" + mdfe.getEmit().getUF() + "</UF>";
        if (mdfe.getEmit().getFone() != null && mdfe.getEmit().getFone().trim().length() > 5) {
            saida += "<fone>" + Utils.getInstance().replace(mdfe.getEmit().getFone()) + "</fone>";
        }
        if (mdfe.getEmit().getEmail() != null && mdfe.getEmit().getEmail().length() > 0) {
            saida += "<email>" + mdfe.getEmit().getEmail() + "</email>";
        }
        saida += "</enderEmit>";
        saida += "</emit>";

        if (mdfe.getRodo() != null) {
            MdfeRodo rodo = mdfe.getRodo();
            if(Utils.doValida(mdfe.getVersaoModal())) {
            	saida += "<infModal versaoModal=\"" + mdfe.getVersaoModal() + "\">"
                        + "<rodo xmlns=\"http://www.portalfiscal.inf.br/mdfe\">";
            } else {
            	saida += "<infModal versaoModal=\"3.00\">"
                        + "<rodo xmlns=\"http://www.portalfiscal.inf.br/mdfe\">";
            }
            
            if(rodo.getInfANTT() != null) {
            	MdfeInfANTT infANTT = rodo.getInfANTT();
            	saida += "<infANTT>";
            	if (infANTT.getRNTRC() != null && infANTT.getRNTRC().length() > 0) {
                    saida += "<RNTRC>" + infANTT.getRNTRC() + "</RNTRC>";
                }
            	if(infANTT.getInfCIOT() != null) {
            		for(MdfeInfCIOT infCIOT : infANTT.getInfCIOT()) {
                		saida += "<infCIOT>";
                		saida += "<CIOT>" + infCIOT.getCIOT() + "</CIOT>";
                		if(Utils.doValida(infCIOT.getCPF())) {
                			saida += "<CPF>" + infCIOT.getCPF() + "</CPF>";
                		} else {
                			saida += "<CNPJ>" + infCIOT.getCNPJ() + "</CNPJ>";
                		}
                		saida += "</infCIOT>";
                	}
            	}
            	
            	if (infANTT.getValePed() != null && !infANTT.getValePed().isEmpty()) {
                    saida += "<valePed>";
                    for(MdfeValePed valePed : infANTT.getValePed()) {
                    	saida += "<disp>";
                        saida += "<CNPJForn>" + valePed.getCNPJForn() + "</CNPJForn>";
                        if(Utils.doValida(valePed.getCPFPg())) {
                            saida += "<CPFPg>" + valePed.getCPFPg() + "</CPFPg>";
                        } else {
                        	saida += "<CNPJPg>" + valePed.getCNPJPg() + "</CNPJPg>";
                        }
                        saida += "<nCompra>" + valePed.getNCompra() + "</nCompra>";
                        saida += "<vValePed>" + valePed.getvValePed() + "</vValePed>";
                        saida += "</disp>";
                    }
                    saida += "</valePed>";
                }
            	
            	if(Utils.doValida(infANTT.getCNPJ()) ||Utils.doValida(infANTT.getCPF())) {
            		saida += "<infContratante>";
            		if(Utils.doValida(infANTT.getCNPJ())) {
            			saida += "<CNPJ>" + infANTT.getCNPJ() + "</CNPJ>";
            		} else {
            			saida += "<CPF>" + infANTT.getCPF() + "</CPF>";
            		}
            		saida += "</infContratante>";
            	}
            	
            	saida += "</infANTT>";
            }
            
            saida += "<veicTracao>";
            if (rodo.getVeicTracao().getCInt() != null) {
                saida += "<cInt>" + rodo.getVeicTracao().getCInt() + "</cInt>";
            }
            saida += "<placa>" + rodo.getVeicTracao().getPlaca() + "</placa>";
            if (rodo.getVeicTracao().getTara() != null) {
                saida += "<tara>" + Utils.getInstance().getDecimalFormatter(10, 0).format(rodo.getVeicTracao().getTara()) + "</tara>";
            } else {
                saida += "<tara>0</tara>";
            }
            if (rodo.getVeicTracao().getCapKG() != null) {
                saida += "<capKG>" + Utils.getInstance().getDecimalFormatter(10, 0).format(rodo.getVeicTracao().getCapKG()) + "</capKG>";
            }
            if (rodo.getVeicTracao().getCapM3() != null) {
                saida += "<capM3>" + Utils.getInstance().getDecimalFormatter(10, 0).format(rodo.getVeicTracao().getCapM3()) + "</capM3>";
            }
            if (rodo.getVeicTracao().getProp() != null) {
                saida += "<prop>";
                if (rodo.getVeicTracao().getProp().getCPF() != null) {
                    saida += "<CPF>" + rodo.getVeicTracao().getProp().getCPF() + "</CPF>";
                }
                if (rodo.getVeicTracao().getProp().getCNPJ() != null) {
                    saida += "<CNPJ>" + rodo.getVeicTracao().getProp().getCNPJ() + "</CNPJ>";
                }
                saida += "<RNTRC>" + rodo.getVeicTracao().getProp().getRNTRC() + "</RNTRC>";
                saida += "<xNome>" + rodo.getVeicTracao().getProp().getXNome() + "</xNome>";
                saida += "<IE>" + rodo.getVeicTracao().getProp().getIE() + "</IE>";
                saida += "<UF>" + rodo.getVeicTracao().getProp().getUF() + "</UF>";
                saida += "<tpProp>" + rodo.getVeicTracao().getProp().getTpProp() + "</tpProp>";
                saida += "</prop>";
            }
            if (rodo.getVeicTracao().getCondutor() != null) {
                for (MdfeCondutor c : rodo.getVeicTracao().getCondutor()) {
                    saida += "<condutor>";
                    saida += "<xNome>" + c.getXNome() + "</xNome>";
                    saida += "<CPF>" + c.getCPF() + "</CPF>";
                    saida += "</condutor>";
                }
            }
            
             saida += "<tpRod>" + Utils.getInstance().zeroFill(rodo.getVeicTracao().getTpRod() + "", 2) + "</tpRod>";
            saida += "<tpCar>" + Utils.getInstance().zeroFill(rodo.getVeicTracao().getTpCar() + "", 2) + "</tpCar>";
            saida += "<UF>" + rodo.getVeicTracao().getUF() + "</UF>";
            saida += "</veicTracao>";
            
            if (rodo.getVeicReboque() != null) {
                for (MdfeVeicReboque r : rodo.getVeicReboque()) {
                    saida += "<veicReboque>";
                    if (r.getCInt() != null) {
                        saida += "<cInt>" + r.getCInt() + "</cInt>";
                    }
                    saida += "<placa>" + r.getPlaca() + "</placa>";
                    if (r.getTara() != null) {
                        saida += "<tara>" + Utils.getInstance().getDecimalFormatter(10, 0).format(r.getTara()) + "</tara>";
                    } else {
                        saida += "<tara>0</tara>";
                    }
                    if (r.getCapKG() != null) {
                        saida += "<capKG>" + Utils.getInstance().getDecimalFormatter(10, 0).format(r.getCapKG()) + "</capKG>";
                    }
                    if (r.getCapM3() != null) {
                        saida += "<capM3>" + Utils.getInstance().getDecimalFormatter(10, 0).format(r.getCapM3()) + "</capM3>";
                    }
                    if (r.getProp() != null) {
                        saida += "<prop>";
                        if (r.getProp().getCPF() != null) {
                            saida += "<CPF>" + r.getProp().getCPF() + "</CPF>";
                        }
                        if (r.getProp().getCNPJ() != null) {
                            saida += "<CNPJ>" + r.getProp().getCNPJ() + "</CNPJ>";
                        }
                        saida += "<RNTRC>" + r.getProp().getRNTRC() + "</RNTRC>";
                        saida += "<xNome>" + r.getProp().getXNome() + "</xNome>";
                        saida += "<IE>" + r.getProp().getIE() + "</IE>";
                        saida += "<UF>" + r.getProp().getUF() + "</UF>";
                        saida += "<tpProp>" + r.getProp().getTpProp() + "</tpProp>";
                        saida += "</prop>";
                    }
                    saida += "<tpCar>" + Utils.getInstance().zeroFill(r.getTpCar() + "", 2) + "</tpCar>";
                    saida += "<UF>" + r.getUF() + "</UF>";
                    saida += "</veicReboque>";
                }
            }
            if(Utils.doValida(rodo.getCodAgPorto())) {
                saida += "<codAgPorto>" + rodo.getCodAgPorto() + "</codAgPorto>";
            }
            if (rodo.getnLacre() != null) {
                for (String l : rodo.getnLacre()) {
                    saida += "<lacRodo>";
                    saida += "<nLacre>" + l + "</nLacre>";
                    saida += "</lacRodo>";
                }
            }
            
            saida += "</rodo></infModal>";
        }
        if (mdfe.getAereo() != null) {
            saida += "<infModal versaoModal=\"3.00\">";
            saida += "<nac>" + mdfe.getAereo().getNac() + "</nac>";
            saida += "<nVoo>" + mdfe.getAereo().getNVoo() + "</nVoo>";
            saida += "<cAerEmb>" + mdfe.getAereo().getCAerEmb() + "</cAerEmb>";
            saida += "<cAerDes>" + mdfe.getAereo().getCAerDes() + "</cAerDes>";
            saida += "<dVoo>" + formatador.format(mdfe.getAereo().getDVoo()) + "</dVoo>";
            saida += "</infModal>";
        }
        saida += "<infDoc>";
        if (mdfe.getInfMunDescarga() != null) {
            for (MdfeInfMunDescarga desc : mdfe.getInfMunDescarga()) {
                saida += "<infMunDescarga>";
                saida += "<cMunDescarga>" + desc.getCMunDescarga() + "</cMunDescarga>";
                saida += "<xMunDescarga>" + desc.getXMunDescarga() + "</xMunDescarga>";

                // *** CTe *** //
                if (desc.getInfCTe() != null) {
                    for (MdfeInfCTe c : desc.getInfCTe()) {
                        saida += "<infCTe>";
                        saida += "<chCTe>" + c.getChCTe() + "</chCTe>";
                        if (c.getSegCodBarra() != null && c.getSegCodBarra().length() > 0) {
                            saida += "<SegCodBarra>" + c.getSegCodBarra() + "</SegCodBarra>";
                        }
                        if (c.getIndReentrega() != null && c.getIndReentrega() > 0) {
                            saida += "<indReentrega>" + c.getIndReentrega() + "</indReentrega>";
                        }
                        if (c.getInfUnidTransp() != null) {
                            for (MdfeInfUnidTransp t : c.getInfUnidTransp()) {
                                saida += "<infUnidTransp>";
                                saida += "<tpUnidTransp>" + t.getTpUnidTransp() + "</tpUnidTransp>";
                                saida += "<idUnidTransp>" + t.getIdUnidTransp() + "</idUnidTransp>";
                                if (t.getNLacre() != null) {
                                    for (String l : t.getNLacre()) {
                                        saida += "<lacUnidTransp>";
                                        saida += "<nLacre>" + l + "</nLacre>";
                                        saida += "</lacUnidTransp>";
                                    }
                                }
                                if (t.getInfUnidCarga() != null) {
                                    for (MdfeInfUnidCarga u : t.getInfUnidCarga()) {
                                        saida += "<infUnidCarga>";
                                        saida += "<tpUnidCarga>" + u.getTpUnidCarga() + "</tpUnidCarga>";
                                        saida += "<idUnidCarga>" + u.getIdUnidCarga() + "</idUnidCarga>";
                                        if (u.getLacUnidCarga() != null) {
                                            for (MdfeLacUnidCarga l : u.getLacUnidCarga()) {
                                                saida += "<lacUnidCarga>";
                                                saida += "<nLacre>" + l.getNLacre() + "</nLacre>";
                                                saida += "</lacUnidCarga>";
                                            }
                                        }
                                        if (u.getQtdRat() != null && u.getQtdRat() > 0) {
                                            saida += "<qtdRat>" + Utils.getInstance().getDecimalFormatter(3, 2).format(u.getQtdRat()) + "</qtdRat>";
                                        }
                                        saida += "</infUnidCarga>";
                                    }
                                }
                                if (t.getQtdRat() != null && t.getQtdRat() > 0) {
                                    saida += "<qtdRat>" + Utils.getInstance().getDecimalFormatter(3, 2).format(t.getQtdRat()) + "</qtdRat>";
                                }
                                saida += "</infUnidTransp>";
                            }
                        }
                        
                        //nao implementado ainda
                        if(c.getPeri() != null) {
                        	for(MdfePeri peri : c.getPeri()) {
                        
                        	}
                        }
                        
                        saida += "</infCTe>";
                    }
                }

                // *** CT *** //
                if (desc.getInfCT() != null) {
                    for (MdfeInfCT c : desc.getInfCT()) {
                        saida += "<infCT>";
                        saida += "<nCT>" + c.getNCT() + "</nCT>";
                        saida += "<serie>" + c.getSerie() + "</serie>";
                        if (c.getSubser() != null && c.getSubser().length() > 0) {
                            saida += "<subser>" + c.getSubser() + "</subser>";
                        }
                        if (c.getdEmi() != null) {
                        	
                            saida += "<dEmi>" + dateFormat.format(c.getdEmi()) + "</dEmi>";
                        }
                        saida += "<vCarga>" + Utils.getInstance().getDecimalFormatter(15, 2).format(c.getVCarga()) + "</vCarga>";
                        if (c.getInfUnidTransp() != null) {
                            for (MdfeInfUnidTransp t : c.getInfUnidTransp()) {
                                saida += "<infUnidTransp>";
                                saida += "<tpUnidTransp>" + t.getTpUnidTransp() + "</tpUnidTransp>";
                                saida += "<idUnidTransp>" + t.getIdUnidTransp() + "</idUnidTransp>";
                                if (t.getNLacre() != null) {
                                    for (String l : t.getNLacre()) {
                                        saida += "<lacUnidTransp>";
                                        saida += "<nLacre>" + l + "</nLacre>";
                                        saida += "</lacUnidTransp>";
                                    }
                                }
                                if (t.getInfUnidCarga() != null) {
                                    for (MdfeInfUnidCarga u : t.getInfUnidCarga()) {
                                        saida += "<infUnidCarga>";
                                        saida += "<tpUnidCarga>" + u.getTpUnidCarga() + "</tpUnidCarga>";
                                        saida += "<idUnidCarga>" + u.getIdUnidCarga() + "</idUnidCarga>";
                                        if (u.getLacUnidCarga() != null) {
                                            for (MdfeLacUnidCarga l : u.getLacUnidCarga()) {
                                                saida += "<lacUnidCarga>";
                                                saida += "<nLacre>" + l.getNLacre() + "</nLacre>";
                                                saida += "</lacUnidCarga>";
                                            }
                                        }
                                        if (u.getQtdRat() != null && u.getQtdRat() > 0) {
                                            saida += "<qtdRat>" + Utils.getInstance().getDecimalFormatter(3, 2).format(u.getQtdRat()) + "</qtdRat>";
                                        }
                                        saida += "</infUnidCarga>";
                                    }
                                }
                                if (t.getQtdRat() != null && t.getQtdRat() > 0) {
                                    saida += "<qtdRat>" + Utils.getInstance().getDecimalFormatter(3, 2).format(t.getQtdRat()) + "</qtdRat>";
                                }
                                saida += "</infUnidTransp>";
                            }
                        }
                        saida += "</infCT>";
                    }
                }

                // *** NFe *** //
                if (desc.getInfNFe() != null) {
                    for (MdfeInfNFe c : desc.getInfNFe()) {
                        saida += "<infNFe>";
                        saida += "<chNFe>" + c.getChNFe() + "</chNFe>";
                        if (c.getSegCodBarra() != null && c.getSegCodBarra().length() > 0) {
                            saida += "<SegCodBarra>" + c.getSegCodBarra() + "</SegCodBarra>";
                        }
                        if (c.getIndReentrega() != null && c.getIndReentrega() > 0) {
                            saida += "<indReentrega>" + c.getIndReentrega() + "</indReentrega>";
                        }
                        if (c.getInfUnidTransp() != null) {
                            for (MdfeInfUnidTransp t : c.getInfUnidTransp()) {
                                saida += "<infUnidTransp>";
                                saida += "<tpUnidTransp>" + t.getTpUnidTransp() + "</tpUnidTransp>";
                                saida += "<idUnidTransp>" + t.getIdUnidTransp() + "</idUnidTransp>";
                                if (t.getNLacre() != null) {
                                    for (String l : t.getNLacre()) {
                                        saida += "<lacUnidTransp>";
                                        saida += "<nLacre>" + l + "</nLacre>";
                                        saida += "</lacUnidTransp>";
                                    }
                                }
                                if (t.getInfUnidCarga() != null) {
                                    for (MdfeInfUnidCarga u : t.getInfUnidCarga()) {
                                        saida += "<infUnidCarga>";
                                        saida += "<tpUnidCarga>" + u.getTpUnidCarga() + "</tpUnidCarga>";
                                        saida += "<idUnidCarga>" + u.getIdUnidCarga() + "</idUnidCarga>";
                                        if (u.getLacUnidCarga() != null) {
                                            for (MdfeLacUnidCarga l : u.getLacUnidCarga()) {
                                                saida += "<lacUnidCarga>";
                                                saida += "<nLacre>" + l.getNLacre() + "</nLacre>";
                                                saida += "</lacUnidCarga>";
                                            }
                                        }
                                        if (u.getQtdRat() != null && u.getQtdRat() > 0) {
                                            saida += "<qtdRat>" + Utils.getInstance().getDecimalFormatter(3, 2).format(u.getQtdRat()) + "</qtdRat>";
                                        }
                                        saida += "</infUnidCarga>";
                                    }
                                }
                                if (t.getQtdRat() != null && t.getQtdRat() > 0) {
                                    saida += "<qtdRat>" + Utils.getInstance().getDecimalFormatter(3, 2).format(t.getQtdRat()) + "</qtdRat>";
                                }
                                saida += "</infUnidTransp>";
                            }
                        }
                        saida += "</infNFe>";
                    }
                }

                // *** NF *** //
                if (desc.getInfNF() != null) {
                    for (MdfeInfNF c : desc.getInfNF()) {
                        saida += "<infNF>";
                        saida += "<CNPJ>" + c.getCNPJ() + "</CNPJ>";
                        saida += "<UF>" + c.getUF() + "</UF>";
                        saida += "<nNF>" + c.getNNF() + "</nNF>";
                        saida += "<serie>" + c.getSerie() + "</serie>";
                        if (c.getdEmi() != null) {
                            saida += "<dEmi>" + dateFormat.format(c.getdEmi()) + "</dEmi>";
                        }
                        saida += "<vNF>" + Utils.getInstance().getDecimalFormatter(15, 2).format(c.getVNF()) + "</vNF>";
                        if (c.getPIN() != null && c.getPIN().length() > 0) {
                            saida += "<PIN>" + c.getPIN() + "</PIN>";
                        }
                        if (c.getInfUnidTransp() != null) {
                            for (MdfeInfUnidTransp t : c.getInfUnidTransp()) {
                                saida += "<infUnidTransp>";
                                saida += "<tpUnidTransp>" + t.getTpUnidTransp() + "</tpUnidTransp>";
                                saida += "<idUnidTransp>" + t.getIdUnidTransp() + "</idUnidTransp>";
                                if (t.getNLacre() != null) {
                                    for (String l : t.getNLacre()) {
                                        saida += "<lacUnidTransp>";
                                        saida += "<nLacre>" + l + "</nLacre>";
                                        saida += "</lacUnidTransp>";
                                    }
                                }
                                if (t.getInfUnidCarga() != null) {
                                    for (MdfeInfUnidCarga u : t.getInfUnidCarga()) {
                                        saida += "<infUnidCarga>";
                                        saida += "<tpUnidCarga>" + u.getTpUnidCarga() + "</tpUnidCarga>";
                                        saida += "<idUnidCarga>" + u.getIdUnidCarga() + "</idUnidCarga>";
                                        if (u.getLacUnidCarga() != null) {
                                            for (MdfeLacUnidCarga l : u.getLacUnidCarga()) {
                                                saida += "<lacUnidCarga>";
                                                saida += "<nLacre>" + l.getNLacre() + "</nLacre>";
                                                saida += "</lacUnidCarga>";
                                            }
                                        }
                                        if (u.getQtdRat() != null && u.getQtdRat() > 0) {
                                            saida += "<qtdRat>" + Utils.getInstance().getDecimalFormatter(3, 2).format(u.getQtdRat()) + "</qtdRat>";
                                        }
                                        saida += "</infUnidCarga>";
                                    }
                                }
                                if (t.getQtdRat() != null && t.getQtdRat() > 0) {
                                    saida += "<qtdRat>" + Utils.getInstance().getDecimalFormatter(3, 2).format(t.getQtdRat()) + "</qtdRat>";
                                }
                                saida += "</infUnidTransp>";
                            }
                        }
                        saida += "</infNF>";
                    }
                }
                saida += "</infMunDescarga>";
            }
        }

        saida += "</infDoc>";
        
        if(mdfe.getSeg() != null) {
        	for(MdfeSeg seg : mdfe.getSeg()) {
            	saida += "<seg>";
            	if(seg.getRespSeg() != null && seg.getRespSeg() > 0){
            		saida += "<infResp>";
            		saida += "<respSeg>" + seg.getRespSeg() + "</respSeg>";
            		if(Utils.doValida(seg.getCPF())) {
            			saida += "<CPF>" + seg.getCPF() + "</CPF>";
            		} else {
            			saida += "<CNPJ>" + seg.getCNPJ() + "</CNPJ>";
            		}
            		saida += "</infResp>";
            	}
            	if(Utils.doValida(seg.getxSeg())){
            		saida += "<infSeg>";
            		saida += "<xSeg>" + seg.getxSeg() + "</xSeg>";
            		saida += "<CNPJ>" + seg.getCnpjSeg() + "</CNPJ>";
            		saida += "</infSeg>";
            	}
            	
            	if(Utils.doValida(seg.getnApol())) {
            		saida += "<nApol>" + seg.getnApol() + "</nApol>";
            	}
            	if(Utils.doValida(seg.getnAver())) {
            		saida += "<nAver>" + seg.getnAver() + "</nAver>";
            	}
            	saida += "</seg>";
            }
        }
        
        saida += "<tot>";
        if (mdfe.getQCTe() != null && mdfe.getQCTe() > 0) {
            saida += "<qCTe>" + mdfe.getQCTe() + "</qCTe>";
        }
        if (mdfe.getQCT() != null && mdfe.getQCT() > 0) {
            saida += "<qCT>" + mdfe.getQCT() + "</qCT>";
        }
        if (mdfe.getQNFe() != null && mdfe.getQNFe() > 0) {
            saida += "<qNFe>" + mdfe.getQNFe() + "</qNFe>";
        }
        if (mdfe.getQNF() != null && mdfe.getQNF() > 0) {
            saida += "<qNF>" + mdfe.getQNF() + "</qNF>";
        }
        if (mdfe.getQMDFe() != null && mdfe.getQMDFe() > 0) {
            saida += "<qMDFe>" + mdfe.getQMDFe() + "</qMDFe>";
        }
        saida += "<vCarga>" + Utils.getInstance().getDecimalFormatter(12, 2).format(mdfe.getVCarga()) + "</vCarga>";
        saida += "<cUnid>" + Utils.getInstance().zeroFill(mdfe.getCUnid() + "", 2) + "</cUnid>";
        saida += "<qCarga>" + Utils.getInstance().getDecimalFormatter(12, 4).format(mdfe.getQCarga()) + "</qCarga>";
        saida += "</tot>";
        if (mdfe.getNLacre() != null) {
            for (String l : mdfe.getNLacre()) {
                saida += "<lacres>";
                saida += "<nLacre>" + l + "</nLacre>";
                saida += "</lacres>";
            }
        }
        if (mdfe.getAutXML() != null) {
            for (MdfeAutXML a : mdfe.getAutXML()) {
                saida += "<autXML>";
                if (a.getCNPJ() != null) {
                    saida += "<CNPJ>" + a.getCNPJ() + "</CNPJ>";
                } else if (a.getCPF() != null) {
                    saida += "<CPF>" + a.getCPF() + "</CPF>";
                }
                saida += "</autXML>";
            }
        }
        if (mdfe.getInfAdFisco() != null || mdfe.getInfCpl() != null) {
            saida += "<infAdic>";
            if (mdfe.getInfAdFisco() != null) {
                saida += "<infAdFisco>" + mdfe.getInfAdFisco() + "</infAdFisco>";
            } else if (mdfe.getInfCpl() != null) {
                saida += "<infCpl>" + mdfe.getInfCpl() + "</infCpl>";
            }
            saida += "</infAdic>";
        }
        saida += "</infMDFe>";
        saida += "</MDFe>";
        return saida;
    }
}
