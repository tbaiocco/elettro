/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.servicos;

import br.cte.base.EmpresaDb;
import br.cte.bean.BeanDacte;
import br.cte.bean.Dacte;
import br.cte.core.CertDig;
import br.cte.core.ConsultaCte;
import br.cte.core.ConsultaStatusServicoCte;
import br.cte.core.EnvioLoteCte;
import br.cte.core.InutilizaNumeracaoCte;
import br.cte.core.RecepcaoEventoCte;
import br.cte.core.RetornoEnvioCte;
import br.cte.model.Cte;
import br.cte.model.CteConsulta;
import br.cte.model.CteEvento;
import br.cte.model.CteInutilizacao;
import br.cte.model.CteLote;
import br.cte.model.CteRetornoEnvioLote;
import br.cte.model.CteStatusServico;
import br.cte.model.Empresa;
import br.cte.model.InformacoesCertificado;
import java.util.HashMap;

/**
 *
 * @author DerliRiffel
 */
public class CteServicos {

    private HashMap erros = new HashMap();

    /**
     * *
     * Processa envio da cte, retorna os dados do lote processado
     *
     * @param nota
     * @return cteLote : retorna os dados do lote processado
     */
    public CteLote enviaCte(Cte cte) {
        this.erros = new HashMap();
        EnvioLoteCte envio = new EnvioLoteCte(cte);
        CteLote retorno = envio.executar();
        this.erros = envio.getErros();
        return retorno;
    }

    /**
     * CONSULTA A STATUS DO SERVICO CTE
     *
     * @param tpAmbiente
     * @param cnpjEmissor
     * @return
     */
    public CteStatusServico consultaStatusServico(int tpAmbiente, String cnpjEmissor) {
        this.erros = new HashMap();
        ConsultaStatusServicoCte status = new ConsultaStatusServicoCte(tpAmbiente, cnpjEmissor);
        CteStatusServico retorno = status.executar();
        this.erros = status.getErros();
        return retorno;
    }

    public HashMap getErros() {
        return erros;
    }

    /**
     * Solicitacao de inutilizacao de faixa de numeracao de CTE
     *
     * @param tpAmbiente
     * @param cnpjEmissor
     * @param ano : Ano de inutilização da numeração
     * @param mod : Modelo da CT-e (57)
     * @param serie : Série da CT-e
     * @param nCTIni: Número da CT-e inicial a ser inutilizada
     * @param nCTFin: Número da CT-e final a ser inutilizada
     * @param xJust : Informar a justificativa do pedido de inutilização
     * @return
     */
    public CteInutilizacao inutilizaNumeracaoCte(int tpAmbiente, String cnpjEmissor, String ano, String mod, String serie, String nCTIni, String nCTFin, String xJust) {
        this.erros = new HashMap();
        InutilizaNumeracaoCte consulta = new InutilizaNumeracaoCte(tpAmbiente, cnpjEmissor, ano, mod, serie, nCTIni, nCTFin, xJust);
        CteInutilizacao retorno = consulta.executar();
        this.erros = consulta.getErros();
        return retorno;
    }

    /**
     * Cancelamento de Cte
     *
     * @param cte com todos os dados preenchidos
     * @return
     */
    public CteEvento eventoCte(int tpAmbiente, String cnpjEmissor, CteEvento evento) {
        this.erros = new HashMap();
        RecepcaoEventoCte cancela = new RecepcaoEventoCte(tpAmbiente, cnpjEmissor, evento);
        CteEvento retorno = cancela.executar();
        this.erros = cancela.getErros();
        return retorno;
    }

    /**
     * Consulta Status de cte
     *
     * @param chCTe
     * @param tpAmbiente
     * @param cnpjEmissor
     * @return
     */
    public CteConsulta consultaCte(String chCTe, int tpAmbiente, String cnpjEmissor) {
        this.erros = new HashMap();
        ConsultaCte consulta = new ConsultaCte(chCTe, tpAmbiente, cnpjEmissor);
        CteConsulta retorno = consulta.executar();
        this.erros = consulta.getErros();
        return retorno;
    }

    /**
     * Consulta retorno de lote enviado anteriormente
     *
     * @param nRec
     * @param cte
     * @return
     */
    public CteRetornoEnvioLote retornoEnvioCte(String nRec, Cte cte) {
        this.erros = new HashMap();
        RetornoEnvioCte r = new RetornoEnvioCte(nRec, cte);
        CteRetornoEnvioLote retorno = r.executar();
        this.erros = r.getErros();
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
     * @param cte
     * @return BeanDacte - retorna o bean pronto para gerar a impressao do dacte
     */
    public BeanDacte getDacte(Cte cte) {
        Dacte dacte = new Dacte();
        return dacte.getDacte(cte);
    }
}
