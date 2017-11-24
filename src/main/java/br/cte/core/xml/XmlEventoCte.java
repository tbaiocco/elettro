/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.core.xml;

import br.cte.core.CertDig;
import br.cte.model.CteEvento;
import br.cte.model.CteInfCorrecao;
import br.cte.model.Empresa;
import java.io.File;
import java.text.SimpleDateFormat;
import br.utils.Arquivo;
import br.utils.Configuracoes;
import br.utils.Utils;

import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Derli
 */
public class XmlEventoCte {

    private static final XmlEventoCte instancia = new XmlEventoCte();
    private String id;
    private CteEvento evento;
    private TimeZone timeZone = TimeZone.getDefault();
    private final SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat formatador2 = new SimpleDateFormat("HH:mm:ss");
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") {
	    public StringBuffer format(Date date, StringBuffer toAppendTo, java.text.FieldPosition pos) {
	        StringBuffer toFix = super.format(date, toAppendTo, pos);
	        return toFix.insert(toFix.length()-2, ':');
	    };
	};
    
    public String geraXmlDistEvento(CteEvento evento, Empresa empresa) {
    	return this.geraXmlDistEvento(evento, empresa, "3.00");
    }

    public String geraXmlDistEvento(CteEvento evento, Empresa empresa, String versao) {
        String nomeArquivoLote = Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + Utils.getInstance().getDigitos(evento.getCNPJ()) + System.getProperty("file.separator") + evento.getTpEvento() + "-" + "" + evento.getChCTe() + "-" + evento.getNSeqEvento() + "-procEventoCte.xml";

        File f = new File(nomeArquivoLote);
        if (!f.exists()) {

            String nomeArquivoNota = br.utils.Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + "evt-" + evento.getChCTe() + ".xml";
            String nomeArquivoNotaAss = br.utils.Configuracoes.getInstance().getAppDir() + "cte" + System.getProperty("file.separator") + "evt-" + evento.getChCTe() + "-ass.xml";

            Arquivo a = new Arquivo(nomeArquivoNota);
            a.abrirEscrita();
            String xml = this.getXml(evento, empresa, versao);
            a.escreverLinha(xml);
            a.fecharArquivo();

            if (!CertDig.getInstance().Ass(nomeArquivoNota, nomeArquivoNotaAss, "4", empresa)) {
                return "ERro ao assina: " + CertDig.getInstance().getErro();
            } else {
                String dados = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
                dados += "<procEventoCTe xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\"" + versao + "\">";
                Arquivo aAssinado = new Arquivo(nomeArquivoNotaAss);
                if (aAssinado.abrirLeitura()) {
                    dados += aAssinado.ler();
                    dados += "<retEventoCTe versao=\"" + versao + "\">"
                            + "<infEvento>"
                            + "<tpAmb>" + evento.getTpAmb() + "</tpAmb>"
                            + "<verAplic>" + evento.getVerAplic() + "</verAplic>"
                            + "<cOrgao>" + evento.getCOrgao() + "</cOrgao>"
                            + "<cStat>" + evento.getCStat() + "</cStat>"
                            + "<xMotivo>" + evento.getXMotivo() + "</xMotivo>"
                            + "<chCTe>" + evento.getChCTe() + "</chCTe>"
                            + "<tpEvento>" + evento.getTpEvento() + "</tpEvento>"
                            + "<xEvento>" + evento.getXEvento() + "</xEvento>"
                            + "<nSeqEvento>" + evento.getNSeqEvento() + "</nSeqEvento>"
                            + "<dhRegEvento>" + formatador.format(evento.getDhEvento()) + "T"
                            + formatador2.format(evento.getDhEvento());
                    //if(timeZone.inDaylightTime(evento.getDhEvento())){
                    //    dados += "-02:00";
                    //}
                    dados += "</dhRegEvento>"
                            + "<nProt>" + evento.getNProtRegEvento() + "</nProt>"
                            + "</infEvento>"
                            + "</retEventoCTe>"
                            + "</procEventoCTe>";

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

    public String getXml(CteEvento evento, Empresa empresa, String versao) {
        this.evento = evento;
        criaId();
        
        System.out.println(timeZone.getDisplayName(true,0));
		System.out.println(timeZone.getID());
		System.out.println(timeZone.inDaylightTime(evento.getDhEvento()));
		SimpleDateFormat formatadorT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		System.out.println(formatadorT.format(evento.getDhEvento()));
			
		System.out.println(dateFormat.format(evento.getDhEvento()));
		
		SimpleDateFormat formatSemZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        String xml = "<eventoCTe xmlns=\"http://www.portalfiscal.inf.br/cte\" versao=\"" + versao + "\">"
                + "<infEvento Id=\"" + id + "\">"
                + "<cOrgao>" + evento.getCOrgao() + "</cOrgao>"
                + "<tpAmb>" + evento.getTpAmb() + "</tpAmb>"
                + "<CNPJ>" + evento.getCNPJ() + "</CNPJ>"
                + "<chCTe>" + evento.getChCTe() + "</chCTe>"
//                + "<dhEvento>" + formatador.format(evento.getDhEvento()) + "T"
//                + formatador2.format(evento.getDhEvento());
                + "<dhEvento>" + dateFormat.format(evento.getDhEvento());
//        if(timeZone.inDaylightTime(evento.getDhEvento())){
//                        xml += "-02:00";
//                    }
        xml += "</dhEvento>"
                + "<tpEvento>" + evento.getTpEvento() + "</tpEvento>"
                + "<nSeqEvento>" + evento.getNSeqEvento() + "</nSeqEvento>"
                + "<detEvento versaoEvento=\"" + versao + "\">";
        //Registro Multimodal
        if (evento.getTpEvento() == 110160) {
            xml += "<evRegMultimodal xmlns=\"http://www.portalfiscal.inf.br/cte\">"
                    + "<descEvento>" + evento.getDescEvento() + "</descEvento>"
                    + "<xRegistro>" + evento.getXRegistro()+ "</xRegistro>"
                    + "</evRegMultimodal>";
        }
        //CANCELAMENTO
        if (evento.getTpEvento() == 110111) {
            xml += "<evCancCTe xmlns=\"http://www.portalfiscal.inf.br/cte\">"
                    + "<descEvento>" + evento.getDescEvento() + "</descEvento>"
                    + "<nProt>" + evento.getNProtAprovacaoCte() + "</nProt>"
                    + "<xJust>" + evento.getXJust() + "</xJust>"
                    + "</evCancCTe>";
        }
        //CARTA CORRECAO
        if (evento.getTpEvento() == 110110) {
            xml += "<evCCeCTe xmlns=\"http://www.portalfiscal.inf.br/cte\">"
                    + "<descEvento>" + evento.getDescEvento() + "</descEvento>";
            for (CteInfCorrecao c : evento.getInfCorrecao()) {
                xml += "<infCorrecao>"
                        + "<grupoAlterado>" + c.getGrupoAlterado() + "</grupoAlterado>"
                        + "<campoAlterado>" + c.getCampoAlterado() + "</campoAlterado>"
                        + "<valorAlterado>" + c.getValorAlterado() + "</valorAlterado>"
                        + "</infCorrecao>";
            }
            xml += "<xCondUso>" + evento.getXCondUso() + "</xCondUso>";
            xml += "</evCCeCTe>";
        }
        xml += "</detEvento>"
                + "</infEvento>"
                + "</eventoCTe>";
        return xml;
    }

    private void criaId() {
        String s = Utils.getInstance().zeroFill("" + evento.getTpEvento(), 6);
        String seq = Utils.getInstance().zeroFill("" + evento.getNSeqEvento(), 2);
        id = "ID" + s + evento.getChCTe() + seq;
    }

    public XmlEventoCte() {
    }

    public static XmlEventoCte getInstance() {
        return (instancia);
    }
}
