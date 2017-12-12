/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.core.xml;

import br.mdfe.core.CertDig;
import br.mdfe.model.Empresa;
import br.mdfe.model.MdfeEvento;
import br.mdfe.utils.Arquivo;
import br.mdfe.utils.Configuracoes;
import br.mdfe.utils.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Derli
 */
public class XmlEventoMdfe {

    private static final XmlEventoMdfe instancia = new XmlEventoMdfe();
    private String id;
    private MdfeEvento evento;
    private final SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat formatador2 = new SimpleDateFormat("HH:mm:ss");
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") {
        public StringBuffer format(Date date, StringBuffer toAppendTo, java.text.FieldPosition pos) {
            StringBuffer toFix = super.format(date, toAppendTo, pos);
            return toFix.insert(toFix.length()-2, ':');
        };
    };
    
    public String geraXmlDistEvento(MdfeEvento evento, Empresa empresa) {
    	return this.geraXmlDistEvento(evento, empresa, "3.00");
    }

    public String geraXmlDistEvento(MdfeEvento evento, Empresa empresa, String versao) {
        //String nomeArquivoLote = Configuracoes.getInstance().getTmpDir() + System.getProperty("file.separator") + evento.getTpEvento() + "-" + "" + evento.getChMDFe() + "-" + evento.getNSeqEvento() + "-procEventoMDFe.xml";
        String nomeArquivoLote = Configuracoes.getInstance().getDistDir(evento.getCNPJ(),"58", evento.getDhEvento()) + evento.getChMDFe()  +"-"+evento.getTpEvento()+ "-" + evento.getNSeqEvento()+ "-procEventoMDFe.xml";        
        File f = new File(nomeArquivoLote);
        if (!f.exists()) {

            String nomeArquivoNota = br.mdfe.utils.Configuracoes.getInstance().getTmpDir() + System.getProperty("file.separator") + "evt-" + evento.getChMDFe() + ".xml";
            String nomeArquivoNotaAss = br.mdfe.utils.Configuracoes.getInstance().getTmpDir() + System.getProperty("file.separator") + "evt-" + evento.getChMDFe() + "-ass.xml";

            Arquivo a = new Arquivo(nomeArquivoNota);
            a.abrirEscrita();
            String xml = this.getXml(evento, empresa, versao);
            a.escreverLinha(xml);
            a.fecharArquivo();

            if (!CertDig.getInstance().Ass(nomeArquivoNota, nomeArquivoNotaAss, "4", empresa)) {
                return "ERro ao assina: " + CertDig.getInstance().getErro();
            } else {
                String dados = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
                dados += "<procEventoMDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\" versao=\"" + versao + "\">";
                Arquivo aAssinado = new Arquivo(nomeArquivoNotaAss);
                if (aAssinado.abrirLeitura()) {
                    dados += aAssinado.ler();
                    dados += "<retEventoMDFe versao=\"" + versao + "\">"
                            + "<infEvento>"
                            + "<tpAmb>" + evento.getTpAmb() + "</tpAmb>"
                            + "<verAplic>" + evento.getVerAplic() + "</verAplic>"
                            + "<cOrgao>" + evento.getCOrgao() + "</cOrgao>"
                            + "<cStat>" + evento.getCStat() + "</cStat>"
                            + "<xMotivo>" + evento.getXMotivo() + "</xMotivo>"
                            + "<chMDFe>" + evento.getChMDFe() + "</chMDFe>"
                            + "<tpEvento>" + evento.getTpEvento() + "</tpEvento>"
                            + "<xEvento>" + evento.getXEvento() + "</xEvento>"
                            + "<nSeqEvento>" + evento.getNSeqEvento() + "</nSeqEvento>"
                            + "<dhRegEvento>" + dateFormat.format(evento.getDhEvento());
                    dados += "</dhRegEvento>"
                            + "<nProt>" + evento.getNProtRegEvento() + "</nProt>"
                            + "</infEvento>"
                            + "</retEventoMDFe>"
                            + "</procEventoMDFe>";

                    xml += getXml(evento, empresa, versao);
                    Arquivo a1 = new Arquivo(nomeArquivoLote);
                    a1.abrirEscrita();
                    a1.escreverLinha(dados);
                    a1.fecharArquivo();
                    return "Arquivo gerado!";
                } else {
                    return "ERro ao ler arquivo assinado!";
                }
            }
        } else {
            System.out.println("Arquivo ja existe!");
            return "Arquivo ja existe!";
        }
    }

    public String getXml(MdfeEvento evento, Empresa empresa, String versao) {
        this.evento = evento;
        criaId();
        String xml = "<eventoMDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\" versao=\"" + versao + "\">"
                + "<infEvento Id=\"" + id + "\">"
                + "<cOrgao>" + evento.getCOrgao() + "</cOrgao>"
                + "<tpAmb>" + evento.getTpAmb() + "</tpAmb>"
                + "<CNPJ>" + evento.getCNPJ() + "</CNPJ>"
                + "<chMDFe>" + evento.getChMDFe() + "</chMDFe>"
                + "<dhEvento>" + dateFormat.format(evento.getDhEvento());
        xml += "</dhEvento>"
                + "<tpEvento>" + evento.getTpEvento() + "</tpEvento>"
                + "<nSeqEvento>" + evento.getNSeqEvento() + "</nSeqEvento>"
                + "<detEvento versaoEvento=\"" + versao + "\">";

        //CANCELAMENTO
        if (evento.getTpEvento() == 110111) {
            xml += "<evCancMDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\">"
                    + "<descEvento>" + evento.getDescEvento() + "</descEvento>"
                    + "<nProt>" + evento.getNProtAprovacaoMDFe() + "</nProt>"
                    + "<xJust>" + evento.getXJust() + "</xJust>"
                    + "</evCancMDFe>";
        }
        //ENCERRAMENTO
        if (evento.getTpEvento() == 110112) {
            xml += "<evEncMDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\">"
                    + "<descEvento>" + evento.getDescEvento() + "</descEvento>"
                     + "<nProt>" + evento.getNProtAprovacaoMDFe() + "</nProt>"
                    + "<dtEnc>" +  formatador.format(evento.getDtEnc()) + "</dtEnc>"
                    + "<cUF>" + evento.getCUFEnc() + "</cUF>"
                    + "<cMun>" + evento.getCMun()+ "</cMun>"
                    + "</evEncMDFe>";
        }
        //TROCA MOTORISTA
        if (evento.getTpEvento() == 110114) {
            xml += "<evIncCondutorMDFe xmlns=\"http://www.portalfiscal.inf.br/mdfe\">"
                    + "<descEvento>" + evento.getDescEvento() + "</descEvento>"
                    + "<condutor>"
                    + "<xNome>" + evento.getXNomeCondutor() + "</xNome>"
                    + "<CPF>" + evento.getCPFCondutor() + "</CPF>"
                    + "</condutor>"
                    + "</evIncCondutorMDFe>";
        }
        xml += "</detEvento>"
                + "</infEvento>"
                + "</eventoMDFe>";
        return xml;
    }

    private void criaId() {
        String s = Utils.getInstance().zeroFill("" + evento.getTpEvento(), 6);
        String seq = Utils.getInstance().zeroFill("" + evento.getNSeqEvento(), 2);
        id = "ID" + s + evento.getChMDFe() + seq;
    }

    public XmlEventoMdfe() {
    }

    public static XmlEventoMdfe getInstance() {
        return (instancia);
    }
}
