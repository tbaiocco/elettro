/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.servicos;

import br.mdfe.core.CertDig;
import br.mdfe.core.ConsultaMdfe;
import br.mdfe.core.ConsultaStatusServicoMdfe;
import br.mdfe.core.EnvioLoteMdfe;
import br.mdfe.core.RecepcaoEventoMdfe;
import br.mdfe.core.RetornoEnvioMdfe;
import br.mdfe.core.base.EmpresaDb;
import br.mdfe.model.Empresa;
import br.mdfe.model.InformacoesCertificado;
import br.mdfe.model.Mdfe;
import br.mdfe.model.MdfeConsulta;
import br.mdfe.model.MdfeEvento;
import br.mdfe.model.MdfeLote;
import br.mdfe.model.MdfeRetornoEnvioLote;
import br.mdfe.model.MdfeStatusServico;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author DerliRiffel
 */
public class MdfeServicos {

    private HashMap erros = new HashMap();

    public MdfeLote enviaMdfe(Mdfe cte) {
    	
    	System.out.println("Versão via Elettro!!!");
        this.erros = new HashMap();
        EnvioLoteMdfe envio = new EnvioLoteMdfe(cte);
        MdfeLote retorno = envio.executar();
        this.erros = envio.getErros();
        return retorno;
    }

    public MdfeStatusServico consultaStatusServico(int tpAmbiente, String cnpjEmissor) {
        this.erros = new HashMap();
        ConsultaStatusServicoMdfe status = new ConsultaStatusServicoMdfe(tpAmbiente, cnpjEmissor);
        MdfeStatusServico retorno = status.executar();
        this.erros = status.getErros();
        return retorno;
    }

    /**
     * Consulta retorno de lote enviado anteriormente
     *
     * @param nRec
     * @param mdfe
     * @return
     */
    public MdfeRetornoEnvioLote retornoEnvioMdfe(String nRec, Mdfe m) {
        this.erros = new HashMap();
        RetornoEnvioMdfe r = new RetornoEnvioMdfe(nRec, m);
        MdfeRetornoEnvioLote retorno = r.executar();
        this.erros = r.getErros();
        return retorno;
    }

    /**
     * CONSULTA A MDFE
     *
     * @param ch
     * @param tpAmbiente
     * @param cnpjEmissor
     * @return
     */
    public MdfeConsulta consultaMdfe(String ch, int tpAmbiente, String cnpjEmissor) {
        this.erros = new HashMap();
        ConsultaMdfe status = new ConsultaMdfe(ch, tpAmbiente, cnpjEmissor);
        MdfeConsulta retorno = status.executar();
        this.erros = status.getErros();
        return retorno;
    }

    /**
     * Evento de MDFe
     *
     * @param mdfe com todos os dados preenchidos
     * @return
     */
    public MdfeEvento eventoMdfe(int tpAmbiente, String cnpjEmissor, MdfeEvento evento) {
        this.erros = new HashMap();
        RecepcaoEventoMdfe cancela = new RecepcaoEventoMdfe(tpAmbiente, cnpjEmissor, evento);
        MdfeEvento retorno = cancela.executar();
        this.erros = cancela.getErros();
        return retorno;
    }

    public InformacoesCertificado getInformacoesCertificado(String cnpjEmissor) {
        EmpresaDb eDb = new EmpresaDb();
        Empresa empresa = eDb.getEmpresa(cnpjEmissor);
        if (empresa != null) {
            try {
                return CertDig.getInstance().getInformacoes(empresa);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     *
     * @return: retorna um hasmap com os erros encontrados durante o
     * processamento
     */
    public HashMap getErros() {
        return erros;
    }

}
