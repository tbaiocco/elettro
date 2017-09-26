package br.cte.teste;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import br.cte.base.WebServiceCteDb;
import br.cte.model.WebServiceCte;

/**
 *
 * @author DerliRiffel
 */
public class CadastroWebServiceCte {

    public static void main(String[] args) {
        WebServiceCte rs = new WebServiceCte();
        rs.setcUf(43);
        rs.setUf("RS");
        rs.setVersaoPadrao("1.04");
        rs.setTpAmbiente(1);
        rs.setUrlRecepcao("https://cte.sefaz.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx?WSDL");
        rs.setUrlRetRecepcao("https://cte.sefaz.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx?WSDL");
        rs.setUrlStatusServico("https://cte.sefaz.rs.gov.br/ws/ctestatusservico/CteStatusServico.asmx?WSDL");
        rs.setUrlCancelamento("https://cte.sefaz.rs.gov.br/ws/ctecancelamento/ctecancelamento.asmx?WSDL");
        rs.setUrlInutilizacao("https://cte.sefaz.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx?WSDL");
        rs.setUrlConsultaProtocolo("https://cte.sefaz.rs.gov.br/ws/cteconsulta/CteConsulta.asmx?WSDL");
/*
        rs.setTpAmbiente(2);
        rs.setUrlRecepcao("https://homologacao.cte.sefaz.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx?WSDL");
        rs.setUrlRetRecepcao("https://homologacao.cte.sefaz.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx?WSDL");
        rs.setUrlStatusServico("https://homologacao.cte.sefaz.rs.gov.br/ws/ctestatusservico/CteStatusServico.asmx?WSDL");
        rs.setUrlCancelamento("https://homologacao.cte.sefaz.rs.gov.br/ws/ctecancelamento/ctecancelamento.asmx?WSDL");
        rs.setUrlInutilizacao("https://homologacao.cte.sefaz.rs.gov.br/ws/cteinutilizacao/cteinutilizacao.asmx?WSDL");
        rs.setUrlConsultaProtocolo("https://homologacao.cte.sefaz.rs.gov.br/ws/cteconsulta/CteConsulta.asmx?WSDL");
*/
        WebServiceCteDb wDb = new WebServiceCteDb();
        if (wDb.salvaUrl(rs)) {
            System.out.println("Uf: foi salva com sucesso.");
            System.out.println("\n\n");

            WebServiceCte w = wDb.getWebServer(rs.getcUf(), rs.getTpAmbiente());
            System.out.println("Uf salva no xml: " + w.getUf() + " ambiente: " + w.getTpAmbiente());
        } else {
            System.out.println("UF " + rs.getUf() + ", não pode ser salva");
        }
    }
}
