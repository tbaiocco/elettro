/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.bean;

import br.cte.model.Cte;
import br.cte.model.CteCompPrestacaoServico;
import br.cte.model.CteInfNF;
import br.cte.model.CteInfNFe;
import br.cte.model.CteInfOutros;
import br.cte.model.CteInfQCarga;
import br.cte.model.CteMoto;
import br.cte.model.CteObs;
import br.cte.model.CtePass;
import br.cte.model.CtePeri;
import br.cte.model.CteSeg;
import br.cte.model.CteVeic;
import java.util.ArrayList;
import java.util.Iterator;
import br.utils.Formatador;
import br.utils.Utils;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author DerliRiffel
 */
public class Dacte {

    public BeanDacte getDacte(Cte cte) {
        BeanDacte dacte = new BeanDacte();

        dacte.setEmitenteNome(cte.getEmitente().getXNome());
        dacte.setEmitenteCnpj(Formatador.getInstance().formataCnpjNumberToString(cte.getEmitente().getCNPJ()));
        dacte.setEmitenteIe(cte.getEmitente().getIE());
        dacte.setEmitenteLogradouro(cte.getEmitente().getXLgr());
        dacte.setEmitenteNro(cte.getEmitente().getNro());
        dacte.setEmitenteCpl(cte.getEmitente().getXCpl());
        dacte.setEmitenteBairro(cte.getEmitente().getXBairro());
        dacte.setEmitenteMun(cte.getEmitente().getXMun());
        dacte.setEmitenteCep(cte.getEmitente().getCEP());
        dacte.setEmitenteUf(cte.getEmitente().getUF());
        dacte.setEmitenteFone(cte.getEmitente().getFone());

        dacte.setRemetenteNome(cte.getRemetente().getXNome());
        if (cte.getRemetente().getCNPJ() != null) {
            dacte.setRemetenteCnpj(Formatador.getInstance().formataCnpjNumberToString(cte.getRemetente().getCNPJ()));
        } else {
            dacte.setRemetenteCnpj(Formatador.getInstance().formataCpfNumberToString(cte.getRemetente().getCPF()));
        }
        dacte.setRemetenteIe(cte.getRemetente().getIE());
        dacte.setRemetenteLogradouro(cte.getRemetente().getXLgr());
        dacte.setRemetenteNro(cte.getRemetente().getNro());
        dacte.setRemetenteCpl(cte.getRemetente().getXCpl());
        dacte.setRemetenteBairro(cte.getRemetente().getXBairro());
        dacte.setRemetenteMun(cte.getRemetente().getXMun());
        dacte.setRemetenteCep(cte.getRemetente().getCEP());
        dacte.setRemetenteUf(cte.getRemetente().getUF());
        dacte.setRemetenteFone(cte.getRemetente().getXFone());
        dacte.setRemetentePais(cte.getRemetente().getXPais());

        dacte.setDestinatarioNome(cte.getDestinatario().getXNome());
        if (cte.getDestinatario().getCNPJ() != null) {
            dacte.setDestinatarioCnpj(Formatador.getInstance().formataCnpjNumberToString(cte.getDestinatario().getCNPJ()));
        } else {
            dacte.setDestinatarioCnpj(Formatador.getInstance().formataCpfNumberToString(cte.getDestinatario().getCPF()));
        }
        dacte.setDestinatarioIe(cte.getDestinatario().getIE());
        dacte.setDestinatarioLogradouro(cte.getDestinatario().getXLgr());
        dacte.setDestinatarioNro(cte.getDestinatario().getNro());
        dacte.setDestinatarioCpl(cte.getDestinatario().getXCpl());
        dacte.setDestinatarioBairro(cte.getDestinatario().getXBairro());
        dacte.setDestinatarioMun(cte.getDestinatario().getXMun());
        dacte.setDestinatarioCep(cte.getDestinatario().getCEP());
        dacte.setDestinatarioUf(cte.getDestinatario().getUF());
        dacte.setDestinatarioFone(cte.getDestinatario().getFone());
        dacte.setDestinatarioPais(cte.getDestinatario().getXPais());

        dacte.setRecebedorNome(Utils.getInstance().getValue(cte.getRecebedor().getXNome()));
        if (cte.getRecebedor().getCNPJ() != null) {
            dacte.setRecebedorCnpj(Formatador.getInstance().formataCnpjNumberToString(cte.getRecebedor().getCNPJ()));
        } else {
            dacte.setRecebedorCnpj(Formatador.getInstance().formataCpfNumberToString(cte.getRecebedor().getCPF()));
        }
        dacte.setRecebedorIe(Utils.getInstance().getValue(cte.getRecebedor().getIE()));
        dacte.setRecebedorLogradouro(Utils.getInstance().getValue(cte.getRecebedor().getXLgr()));
        dacte.setRecebedorNro(Utils.getInstance().getValue(cte.getRecebedor().getNro()));
        dacte.setRecebedorCpl(Utils.getInstance().getValue(cte.getRecebedor().getXCpl()));
        dacte.setRecebedorBairro(Utils.getInstance().getValue(cte.getRecebedor().getXBairro()));
        dacte.setRecebedorMun(Utils.getInstance().getValue(cte.getRecebedor().getXMun()));
        dacte.setRecebedorCep(Utils.getInstance().getValue(cte.getRecebedor().getCEP()));
        dacte.setRecebedorUf(Utils.getInstance().getValue(cte.getRecebedor().getUF()));
        dacte.setRecebedorFone(Utils.getInstance().getValue(cte.getRecebedor().getFone()));
        dacte.setRecebedorPais(Utils.getInstance().getValue(cte.getRecebedor().getXPais()));
        if (cte.getDestinatario().getISUF() != null) {
            dacte.setDestinatarioSuframa(cte.getDestinatario().getISUF() + "");
        }

        dacte.setExpedidorNome(Utils.getInstance().getValue(cte.getExpedidor().getXNome()));
        if (cte.getExpedidor().getCNPJ() != null) {
            dacte.setExpedidorCnpj(Formatador.getInstance().formataCnpjNumberToString(cte.getExpedidor().getCNPJ()));
        } else {
            dacte.setExpedidorCnpj(Formatador.getInstance().formataCpfNumberToString(cte.getExpedidor().getCPF()));
        }
        dacte.setExpedidorIe(Utils.getInstance().getValue(cte.getExpedidor().getIE()));
        dacte.setExpedidorLogradouro(Utils.getInstance().getValue(cte.getExpedidor().getXLgr()));
        dacte.setExpedidorNro(Utils.getInstance().getValue(cte.getExpedidor().getNro()));
        dacte.setExpedidorCpl(Utils.getInstance().getValue(cte.getExpedidor().getXCpl()));
        dacte.setExpedidorBairro(Utils.getInstance().getValue(cte.getExpedidor().getXBairro()));
        dacte.setExpedidorMun(Utils.getInstance().getValue(cte.getExpedidor().getXMun()));
        dacte.setExpedidorCep(Utils.getInstance().getValue(cte.getExpedidor().getCEP()));
        dacte.setExpedidorUf(Utils.getInstance().getValue(cte.getExpedidor().getUF()));
        dacte.setExpedidorFone(Utils.getInstance().getValue(cte.getExpedidor().getFone()));
        dacte.setExpedidorPais(Utils.getInstance().getValue(cte.getExpedidor().getXPais()));

        dacte.setTomadorNome(cte.getToma().getXNome());
        if (cte.getToma().getCNPJ() != null) {
            dacte.setTomadorCnpj(Formatador.getInstance().formataCnpjNumberToString(cte.getToma().getCNPJ()));
        } else {
            dacte.setTomadorCnpj(Formatador.getInstance().formataCpfNumberToString(cte.getToma().getCPF()));
        }
        dacte.setTomadorIe(cte.getToma().getIE());
        dacte.setTomadorLogradouro(cte.getToma().getXLgr());
        dacte.setTomadorNro(cte.getToma().getNro());
        dacte.setTomadorCpl(cte.getToma().getXCpl());
        dacte.setTomadorBairro(cte.getToma().getXBairro());
        dacte.setTomadorMun(cte.getToma().getXMun());
        dacte.setTomadorCep(cte.getToma().getCEP());
        dacte.setTomadorUf(cte.getToma().getUF());
        dacte.setTomadorFone(cte.getToma().getFone());
        dacte.setTomadorPais(cte.getToma().getXPais());

        String modal = "";
        if (cte.getModal() == 1) {
            modal = "RODOVIARIO";
        } else if (cte.getModal() == 2) {
            modal = "AEREO";
        } else if (cte.getModal() == 3) {
            modal = "AQUAVIARIO";
        } else if (cte.getModal() == 4) {
            modal = "FERROVIARIO";
        } else if (cte.getModal() == 5) {
            modal = "DUTOVIARIO";
        }
        dacte.setModal(modal);

        String tpCte = "";
        if (cte.getTpCTe() == 0) {
            tpCte = "NORMAL";
        } else if (cte.getTpCTe() == 1) {
            tpCte = "COMPLEMENTO DE VALORES";
        } else if (cte.getTpCTe() == 2) {
            tpCte = "ANULACAO DE VALORES";
        } else if (cte.getTpCTe() == 3) {
            tpCte = "SUBSTITUTO";
        }
        dacte.setTipoCte(tpCte);

        String tpServ = "";
        if (cte.getTpServ() == 0) {
            tpServ = "NORMAL";
        } else if (cte.getTpServ() == 1) {
            tpServ = "SUBCONTRATACAO";
        } else if (cte.getTpServ() == 2) {
            tpServ = "REDESPACHO";
        } else if (cte.getTpServ() == 3) {
            tpServ = "REDESP.INTERMEDIARIO";
        }
        dacte.setTipoServico(tpServ);

        String tomador = "";
        if (cte.getToma() != null) {
            if (cte.getToma().getToma() == 0) {
                tomador = "REMETENTE";
            } else if (cte.getToma().getToma() == 1) {
                tomador = "EXPEDIDOR";
            } else if (cte.getToma().getToma() == 2) {
                tomador = "RECEBEDOR";
            } else if (cte.getToma().getToma() == 3) {
                tomador = "DESTINATARIO";
            } else if (cte.getToma().getToma() == 4) {
                tomador = "OUTROS";
            }
        }
        String forma = "";
        if (cte.getForPag() == 0) {
            forma = "PAGO";
        } else if (cte.getForPag() == 1) {
            forma = "A PAGAR";
        } else if (cte.getForPag() == 2) {
            forma = "OUTROS";
        }
        dacte.setFormaPagamento(forma);

        dacte.setTomadorServico(tomador);
        dacte.setSerie(cte.getSerie() + "");
        dacte.setNumero(cte.getNCT() + "");
        dacte.setModelo(cte.getMod());
        dacte.setProtocolo(cte.getProtocolo());
        dacte.setDataEmissao(cte.getDhEmi());

        Date vctoAux = Utils.getVcto(cte.getDhEmi());
        Calendar cal = Calendar.getInstance();
        cal.setTime(vctoAux);
		
	// SP soma mais um dia para desviar
        if(cte.getEmitente().getCNPJ().equals("04882329000265")) {
//            cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
	}		
		
        int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
	if(diaSemana == Calendar.SUNDAY)
            cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
	if(diaSemana == Calendar.SATURDAY)
            cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 2);
        vctoAux = cal.getTime();
		
        if(cte.getEmitente().getCNPJ().startsWith("04882329"))
            dacte.setDataVcto(vctoAux);
        
        dacte.setChave(cte.getChaveAcesso());
        dacte.setNaturezaOperacao(cte.getCFOP() + " " + cte.getNatOp());
        dacte.setOrigemPrestacao(cte.getCompl().getXOrig());
        dacte.setDestinoPrestacao(cte.getCompl().getXDest());
        dacte.setValorTotalServico(cte.getVTPrest());
        dacte.setValorReceber(cte.getVRec());

        if (cte.getInfCTeNorm() != null) {
            dacte.setProdutoPredominante(cte.getInfCTeNorm().getProPred());
            dacte.setOutrasCaracteristicas(cte.getInfCTeNorm().getXOutCat());
            dacte.setValorTotalMercadoria(cte.getInfCTeNorm().getVCarga());
            if (cte.getInfCTeNorm().getSeg() != null) {
                CteSeg seg = cte.getInfCTeNorm().getSeg().iterator().next();

                String resp = "";
                if (seg.getRespSeg() == 0) {
                    resp = "REMETENTE";
                } else if (seg.getRespSeg() == 0) {
                    resp = "EXPEDIDOR";
                } else if (seg.getRespSeg() == 2) {
                    resp = "RECEBEDOR";
                } else if (seg.getRespSeg() == 3) {
                    resp = "DESTINATARIO";
                } else if (seg.getRespSeg() == 4) {
                    resp = "EMITENTE CTE";
                } else if (seg.getRespSeg() == 5) {
                    resp = "TOMADOR DE SERVICO";
                }
                dacte.setNomeSeguradora(seg.getXSeg());
                dacte.setResponsavelSeguro(resp);
                dacte.setNumeroApolice(seg.getNApol());
                dacte.setNumeroAverbacao(seg.getNAver());
            }
            if (cte.getInfCTeNorm().getInfQ() != null) {
                int conta=1;
                Iterator<CteInfQCarga> infQ = cte.getInfCTeNorm().getInfQ().iterator();
                while(infQ.hasNext()){
                    CteInfQCarga infq = infQ.next();
                    if(conta==1){
                        dacte.setNmUnMed1(infq.getTpMed());
                        dacte.setQntUnMed1(infq.getQCarga());
                    } else if(conta==2){
                        dacte.setNmUnMed2(infq.getTpMed());
                        dacte.setQntUnMed2(infq.getQCarga());
                    } else if(conta==3){
                        dacte.setNmUnMed3(infq.getTpMed());
                        dacte.setQntUnMed3(infq.getQCarga());
                    } else if(conta==4){
                        dacte.setNmUnMed4(infq.getTpMed());
                        dacte.setQntUnMed4(infq.getQCarga());
                    } else if(conta==5){
                        dacte.setNmUnMed5(infq.getTpMed());
                        dacte.setQntUnMed5(infq.getQCarga());
                    }
                    conta++;
                }

            }
        }
        if (cte.getImp() != null) {
            String cst = "00-Tributada Integralmente";
            if(cte.getImp().getCST()!= null ){
                if(cte.getImp().getCST()==10 )	cst="10-Tributada e c/ Cobrança do ICMS por Subst. Tributaria";
                if(cte.getImp().getCST()==20 )	cst="20-Com Reducao de Base de Calculo";
                if(cte.getImp().getCST()==30 )	cst="30-Isenta ou Nao Tributada e c/ Cobranca do ICMS por Subst. Tributaria";
                if(cte.getImp().getCST()==40 )	cst="40-Isenta";
                if(cte.getImp().getCST()==41 )	cst="41-Nao Tributada";
                if(cte.getImp().getCST()==50 )	cst="50-Suspensao";
                if(cte.getImp().getCST()==51 )	cst="51-Diferimento";
                if(cte.getImp().getCST()==60 )	cst="60-ICMS Cobrado Anteriormente por Subst. Tributaria";
                if(cte.getImp().getCST()==70 )	cst="70-Com Reducao de Base de Calculo e Cobranca do ICMS por Subst. Tributaria";
                if(cte.getImp().getCST()==90 )	cst="90-Outras";
            }
            dacte.setSituacaoTributaria(cst);
            //dacte.setSituacaoTributaria(cte.getImp().getCST() + "-" + cst);
            dacte.setValorIcms(cte.getImp().getVICMS());
            dacte.setAliquotaIcms(cte.getImp().getPICMS());
            dacte.setBaseCalculo(cte.getImp().getVBC());
            dacte.setReducaoBaseCalculo(cte.getImp().getPRedBC());
            dacte.setIcmsSt(0d);
            dacte.setTotTrib(cte.getImp().getvTotTrib());
        }

        String observacoes = "";
        if (cte.getCompl().getObsCont() != null) {
            Iterator itObsCont = cte.getCompl().getObsCont().iterator();
            while (itObsCont.hasNext()) {
                CteObs obs = (CteObs) itObsCont.next();
                observacoes += obs.getXTexto() + "\n";
            }
        }
        if (cte.getCompl().getObsFisco() != null) {
            Iterator itObsCont2 = cte.getCompl().getObsFisco().iterator();
            while (itObsCont2.hasNext()) {
                CteObs obs2 = (CteObs) itObsCont2.next();
                observacoes += obs2.getXTexto() + "\n";
            }
        }
        dacte.setObservacoes(observacoes);

        ArrayList<BeanDacteDocs> listDocs = new ArrayList<BeanDacteDocs>();
        Iterator<CteInfNF> it = cte.getRemetente().getInfNF().iterator();
        while(it.hasNext()){
            CteInfNF nf = it.next();
            BeanDacteDocs docs = new BeanDacteDocs();
            docs.setTipo("NF");
            docs.setDescricao(dacte.getRemetenteCnpj()+"                                             "+nf.getSerie()+" / "+nf.getNDoc());
            if(nf.getCNPJ()!=null && !nf.getCNPJ().equals("") && !nf.getCNPJ().equals("null"))
                docs.setDescricao(nf.getCNPJ()+"                                             "+nf.getSerie()+" / "+nf.getNDoc());
            listDocs.add(docs);
        }
        Iterator<CteInfNFe> ite = cte.getRemetente().getInfNFe().iterator();
        while(ite.hasNext()){
            CteInfNFe nf = ite.next();
            BeanDacteDocs docs = new BeanDacteDocs();
            docs.setTipo("NFe");
            docs.setDescricao("CHAVE: "+nf.getChave());
            listDocs.add(docs);
        }
        Iterator<CteInfOutros> ito = cte.getRemetente().getInfOutros().iterator();
        while(ito.hasNext()){
            CteInfOutros out = ito.next();
            BeanDacteDocs docs = new BeanDacteDocs();
            docs.setTipo("OUTROS");
            docs.setDescricao(dacte.getDestinatarioCnpj()+"                                             "+" / "+out.getNDoc());
            listDocs.add(docs);
        }

        ArrayList<BeanDacteComp> listComp = new ArrayList<BeanDacteComp>();
        Iterator<CteCompPrestacaoServico> servs = cte.getCompPrestacaoServico().iterator();
        while(servs.hasNext()){
            CteCompPrestacaoServico serv = servs.next();
            BeanDacteComp docs = new BeanDacteComp();
            docs.setNmComp(serv.getXNome());
            docs.setVlComp(serv.getVComp());
            listComp.add(docs);
        }
        dacte.setDacteComp(listComp);
        dacte.setDacteDocs(listDocs);

        if(cte.getInfCTeNorm().getRodo()!=null){
            dacte.setRntc(cte.getInfCTeNorm().getRodo().getRNTRC());
//System.out.println(cte.getInfCTeNorm().getRodo().getdPrev().toString());
            dacte.setDtPrevisao(Utils.getInstance().convertDateToString(cte.getInfCTeNorm().getRodo().getdPrev(),"dd/MM/yyyy HH:mm"));
//System.out.println(dacte.getDtPrevisao());
            dacte.setLotacao("NAO");
            if(cte.getInfCTeNorm().getRodo().getLota()>0)
                dacte.setLotacao("SIM");
            Iterator<CteVeic> vei = cte.getInfCTeNorm().getRodo().getVeic().iterator();
            while(vei.hasNext()){
                CteVeic v = vei.next();
                if(v.getTpVeic()==0){
                    dacte.setNrPlaca(v.getPlaca());
                    dacte.setUfPlaca(v.getUF());
                    dacte.setRntcPlaca(v.getProp().getRNTRC());
                }
                if(v.getTpVeic()>0){
                    dacte.setNrPlacaC1(v.getPlaca());
                    dacte.setUfPlacaC1(v.getUF());
                    dacte.setRntcPlacaC1(v.getProp().getRNTRC());
                }
            }
            Iterator<CteMoto> mot = cte.getInfCTeNorm().getRodo().getMoto().iterator();
            if(mot.hasNext()){
                CteMoto v = mot.next();
                dacte.setNrMotorista(v.getCPF());
                dacte.setNmMotorista(v.getxNome());
            }

        }
        //Peri
        Iterator<CtePeri> p = cte.getInfCTeNorm().getPeri().iterator();
        int conta=1;
        while(p.hasNext()){
                CtePeri peri = p.next();
                if(conta==1){
                    dacte.setnONU1(peri.getNONU());
                    dacte.setxClaRisco1(peri.getXClaRisco());
                    dacte.setGrEmb1(peri.getGrEmb());
                } else if(conta==2){
                    dacte.setnONU2(peri.getNONU());
                    dacte.setxClaRisco2(peri.getXClaRisco());
                    dacte.setGrEmb2(peri.getGrEmb());
                } else if(conta==3){
                    dacte.setnONU3(peri.getNONU());
                    dacte.setxClaRisco3(peri.getXClaRisco());
                    dacte.setGrEmb3(peri.getGrEmb());
                } else if(conta==4){
                    dacte.setnONU4(peri.getNONU());
                    dacte.setxClaRisco4(peri.getXClaRisco());
                    dacte.setGrEmb4(peri.getGrEmb());
                }
                conta++;
            }

        //aereo
        dacte.setRetira(cte.getRetira().toString());
        dacte.setxDetRetira(cte.getXDetRetira());
        if(cte.getInfCTeNorm().getAereo()!=null){
            dacte.setnOCA(cte.getInfCTeNorm().getAereo().getNOCA());

            dacte.setxOrig(cte.getInfCTeNorm().getAereo().getCIATA());
            if(cte.getCompl().getCtePass()!=null){
                CtePass pass = cte.getCompl().getCtePass().iterator().next();
                dacte.setxPass(pass.getXPass());
                dacte.setxDest(pass.getXDest());
            }

            dacte.setCL(cte.getInfCTeNorm().getAereo().getCL());
            dacte.setcTar(cte.getInfCTeNorm().getAereo().getCTar());
            dacte.setvTar(cte.getInfCTeNorm().getAereo().getVTar());
            dacte.setIdT(cte.getInfCTeNorm().getAereo().getIdT());
            //dacte.setDtPrevisao(Utils.getInstance().convertDataToString(cte.getInfCTeNorm().getAereo().getDPrev()));
            dacte.setDtPrevisao(Utils.getInstance().convertDateToString(cte.getInfCTeNorm().getAereo().getDPrev(),"dd/MM/yyyy HH:mm"));
            dacte.setxDime(cte.getInfCTeNorm().getAereo().getxDime());
            dacte.setcInfManu(cte.getInfCTeNorm().getAereo().getcInfManu());
            dacte.setcImp(cte.getInfCTeNorm().getAereo().getcImp());
            dacte.setxLagEmi(cte.getInfCTeNorm().getAereo().getXLAgEmi());
            dacte.setnMinu(cte.getInfCTeNorm().getAereo().getNMinu().toString());
        }

        return dacte;
    }
}
