/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.teste;

import br.cte.model.CteInfUnidTransp;
import br.cte.model.CteInfModal;
import br.cte.model.CteInfUnidCarga;
import br.cte.model.CteLocColeta;
import br.cte.model.CteAutXML;
import br.cte.model.CteDup;
import br.cte.model.CteFat;
import java.util.ArrayList;
import br.cte.model.CteAereo;
import br.cte.model.CteAquav;
import br.cte.model.CteCompComp;
import br.cte.model.CteCompPrestacaoServico;
import br.cte.model.CteCompl;
import br.cte.model.CteContQt;
import br.cte.model.CteDestinatario;
import br.cte.model.CteDisp;
import br.cte.model.CteDocAnt;
import br.cte.model.CteRemetente;
import br.cte.model.CteEmitente;
import br.cte.model.CteExpedidor;
import br.cte.model.CteIdDocAnt;
import br.cte.model.CteImposto;
import br.cte.model.CteInfCTeNorm;
import br.cte.model.CteInfCteComp;
import br.cte.model.CteInfNF;
import br.cte.model.CteInfNFe;
import br.cte.model.CteInfQCarga;
import br.cte.model.CteLacRodo;
import br.cte.model.CteMoto;
import br.cte.model.CteObs;
import br.cte.model.CteOcc;
import br.cte.model.CtePass;
import br.cte.model.CtePeri;
import br.cte.model.CteProp;
import br.cte.model.CteRecebedor;
import br.cte.model.CteRodo;
import br.cte.model.CteSeg;
import br.cte.model.CteTomador;
import br.cte.model.CteValePed;
import br.cte.model.CteVeic;
import br.cte.model.CteVeicNovos;
import br.cte.model.Cte;
import br.utils.Utils;
import br.cte.core.xml.XmlEmissaoCte;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author DerliRiffel
 */
public class cteExemplo {

    //SAO
    public Cte getCteSAO() {
        Cte cte = new Cte();
        cte.setCUF(35);
        cte.setCCT(193249);//CODIGO NUMERICO PARA CHAVE DE ACESSO
        cte.setCFOP(5351);
        cte.setNatOp("FRETE");
        cte.setForPag(1);
        cte.setMod("57");
        cte.setSerie(0);
        cte.setNCT(193249);//NUMERO DA CTE
        cte.setDhEmi(new Date());
        cte.setTpImp(1);
        cte.setTpEmis(1);
        cte.setTpAmb(2);
        cte.setTpCTe(0);
        cte.setProcEmi(0);
        cte.setVerProc("1.04b");
        cte.setRefCTE(null);
        cte.setCMunEnv(3550308);//**
        cte.setXMunEnv("SAO PAULO");//**
        cte.setUFEnv("SP");//**
        cte.setModal(1);//01-Rodoviário;  02-Aéreo; 03-Aquaviário; 04-Ferroviário; 05-Dutoviário
        cte.setTpServ(3);
        cte.setCMunIni(4314902);
        cte.setXMunIni("PORTO ALEGRE");
        cte.setUFIni("RS");
        cte.setCMunFim(4314902);
        cte.setXMunFim("PORTO ALEGRE");
        cte.setUFFim("RS");
        cte.setRetira(0);
        cte.setXDetRetira("DETALHES DE RETIRADA");

        CteTomador toma = new CteTomador();
        toma.setToma(2);
        toma.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        toma.setCNPJ("04882329000184");
        toma.setFone("5137141234");
        toma.setIE("0050034359");
        toma.setXLgr("PLINIO KROEFF");
        toma.setNro("1550");
        toma.setXBairro("PORTO SECO");
        toma.setXCpl("complemento");
        toma.setCEP("91150170");
        toma.setCMun(4314902);
        toma.setXMun("PORTO ALEGRE");
        toma.setUF("RS");
        toma.setCPais(1058);
        toma.setXPais("BRASIL");
        cte.setToma(toma);

        CteCompl compl = new CteCompl();
        compl.setXCaracAd("xCaracAd");
        compl.setXCaracSer("xCaracSer");
        compl.setXDest("xDest");
        compl.setXEmi("NOME DO EMISSSOR");
        compl.setXOrig("SIG ORIG");

        ArrayList<CtePass> listPass = new ArrayList<CtePass>();
        CtePass passagem = new CtePass();
        passagem.setXPass("PASSAG 1");
        listPass.add(passagem);

        CtePass passagem2 = new CtePass();
        passagem2.setXPass("PASSAG 2");
        listPass.add(passagem2);

        compl.setCtePass(listPass);
        compl.setXDest("POA");
        compl.setXRota("DIR");
        compl.setTpPer(0);
        compl.setTpHor(0);

        //obs contribuinte
        ArrayList<CteObs> listObsContrib = new ArrayList<CteObs>();
        CteObs obsContrib = new CteObs();
        obsContrib.setXCampo("1");
        obsContrib.setXTexto("MALOTE ITAIPU");
        listObsContrib.add(obsContrib);
        CteObs obsContrib2 = new CteObs();
        obsContrib2.setXCampo("2");
        obsContrib2.setXTexto("TEXT DA OBS 2");
        listObsContrib.add(obsContrib2);
        compl.setObsCont(listObsContrib);

        //obs fisco
        ArrayList<CteObs> listObsFisco = new ArrayList<CteObs>();
        CteObs obsFisco = new CteObs();
        obsFisco.setXCampo("1");
        obsFisco.setXTexto("TEXT DA OBS 1");
        listObsFisco.add(obsContrib);
        CteObs obsFisco2 = new CteObs();
        obsFisco2.setXCampo("2");
        obsFisco2.setXTexto("TEXT DA OBS 2");
        listObsFisco.add(obsFisco2);
        compl.setObsFisco(listObsFisco);
        cte.setCompl(compl);

        //EMITENTE
        CteEmitente emitente = new CteEmitente();
        emitente.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        emitente.setXFant("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        emitente.setCNPJ("04882329000184");
        emitente.setIE("0050034359");
        emitente.setXLgr("ROD RS 130 KM 77");
        emitente.setNro("1361");
        emitente.setXBairro("BARRA DA FORQUETA");
        emitente.setXCpl("");
        emitente.setCEP("95940000");
        emitente.setCMun(4301008);
        emitente.setXMun("ARROIO DO MEIO");
        emitente.setUF("RS");
        emitente.setCPais(1058);
        emitente.setXPais("BRASIL");
        emitente.setFone("5137141234");
        cte.setEmitente(emitente);

        //remetente
        CteRemetente remetente = new CteRemetente();
        remetente.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        remetente.setXFant("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        remetente.setCNPJ("04882329000184");
        remetente.setIE("0050034359");
        remetente.setXLgr("R PAULO EMILIO");
        remetente.setNro("0");
        remetente.setXBairro("VILA SABRINA");
        remetente.setXCpl("QUADRA 6 BOX 4");
        remetente.setCEP("02162040");
        remetente.setCMun(3518800);
        remetente.setXMun("SAO PAULO");
        remetente.setUF("SP");
        remetente.setCPais(1058);
        remetente.setXPais("BRASIL");
        remetente.setXFone("5137141234");
        remetente.setEmail("teste@teste.com");//03-03-14

        //03-03-14
        CteLocColeta loc = new CteLocColeta();
        loc.setCMun(remetente.getCMun());
        loc.setCNPJ(remetente.getCNPJ());
        loc.setUF(remetente.getUF());
        loc.setCMun(remetente.getCMun());
        loc.setXMun(remetente.getXMun());
        loc.setNro(remetente.getNro());
        loc.setXNome(remetente.getXNome());
        loc.setXLgr(remetente.getXLgr());
        remetente.setLocColeta(loc);//03-03-14

        //INFORMACOES DE NF
        ArrayList<CteInfNF> listNf = new ArrayList<CteInfNF>();
        CteInfNF nf = new CteInfNF();
        nf.setNRoma("ROM");
        nf.setNPed("PED");
        nf.setMod("01");
        nf.setSerie("1");
        nf.setNDoc("11");
        nf.setDEmi(new Date());
        nf.setVBC(100d);
        nf.setVICMS(17d);
        nf.setVBCST(0d);
        nf.setVST(0d);
        nf.setVProd(100d);
        nf.setVNF(100d);
        nf.setNCFOP(5101);
        nf.setNPeso(10d);
        nf.setPIN(null);
        listNf.add(nf);
        remetente.setInfNF(listNf);
        ArrayList<CteInfNFe> listNfe = new ArrayList<CteInfNFe>();
        CteInfNFe nfe = new CteInfNFe();
        nfe.setChave("29100705858273000195550000000000010000077270");
        //listNfe.add(nfe);
        //remetente.setInfNFe(listNfe);
        cte.setRemetente(remetente);

        CteExpedidor expedidor = new CteExpedidor();
        expedidor.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        expedidor.setCNPJ("04869369000358");
        expedidor.setIE("116559857110");
        expedidor.setXLgr("PAULO EMILIO");
        expedidor.setNro("0");
        expedidor.setXBairro("VILA SABRINA");
        expedidor.setXCpl("CPL");
        expedidor.setCEP("02162040");
        expedidor.setCMun(3518800);
        expedidor.setXMun("SAO PAULO");
        expedidor.setUF("SP");
        expedidor.setCPais(1058);
        expedidor.setXPais("BRASIL");
        expedidor.setFone("1169514242");
        expedidor.setEmail("teste@teste.com");//03-03-14
        cte.setExpedidor(expedidor);

        CteRecebedor recebedor = new CteRecebedor();
        recebedor.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        recebedor.setCNPJ("04882329000184");
        recebedor.setIE("0050034359");
        recebedor.setXLgr("R PLINIO KROEFF");
        recebedor.setNro("1550");
        recebedor.setXBairro("PORTO SECO");
        recebedor.setXCpl("CPL");
        recebedor.setCEP("91150170");
        recebedor.setCMun(4314902);
        recebedor.setXMun("PORTO ALEGRE");
        recebedor.setUF("RS");
        recebedor.setCPais(1058);
        recebedor.setXPais("BRASIL");
        recebedor.setFone("5130770555");
        recebedor.setEmail("teste@teste.com");//03-03-14
        cte.setRecebedor(recebedor);

        CteDestinatario destinatario = new CteDestinatario();
        destinatario.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        destinatario.setCNPJ("04882329000184");
        destinatario.setIE("0050034359");
        destinatario.setXLgr("R PLINIO KROEFF");
        destinatario.setNro("1550");
        destinatario.setXBairro("PORTO SECO");
        destinatario.setXCpl("CPL");
        destinatario.setCEP("91150170");
        destinatario.setCMun(4314902);
        destinatario.setXMun("PORTO ALEGRE");
        destinatario.setUF("RS");
        destinatario.setCPais(1058);
        destinatario.setXPais("BRASIL");
        destinatario.setFone("5130770555");
        recebedor.setEmail("teste@teste.com");//03-03-14
        cte.setDestinatario(destinatario);

        cte.setVTPrest(21.02d);
        cte.setVRec(21.02d);
        ArrayList<CteCompPrestacaoServico> listCompPrestacaoServico = new ArrayList<CteCompPrestacaoServico>();
        CteCompPrestacaoServico comp = new CteCompPrestacaoServico();
        comp.setXNome("FRETE PESO(inf)");
        comp.setVComp(20.00d);
        listCompPrestacaoServico.add(comp);

        CteCompPrestacaoServico comp2 = new CteCompPrestacaoServico();
        comp2.setXNome("ADICIONAL");
        comp2.setVComp(1.02d);
        listCompPrestacaoServico.add(comp2);

        cte.setCompPrestacaoServico(listCompPrestacaoServico);

        CteImposto imp = new CteImposto();
        imp.setCST(0);
        imp.setPICMS(17d);
        imp.setPRedBC(0d);
        imp.setVBC(100d);
        imp.setVICMS(17d);
        imp.setVCred(0d);
        cte.setImp(imp);

        CteInfCTeNorm cteInfNor = new CteInfCTeNorm();
        cteInfNor.setProPred("DIVERSOS");
        cteInfNor.setXOutCat("DOCUMENTOS");
        cteInfNor.setVCarga(100d);//**

        ArrayList<CteInfQCarga> listInfQ = new ArrayList<CteInfQCarga>();
        CteInfQCarga infq = new CteInfQCarga();
        infq.setCUnid(0);
        infq.setTpMed("UN");
        infq.setQCarga(1000d);
        listInfQ.add(infq);
        cteInfNor.setInfQ(listInfQ);

        ArrayList<CteContQt> listContainer = new ArrayList<CteContQt>();
        CteContQt container = new CteContQt();
        container.setNCont(1234);
        container.setDPrev(new Date());
        listContainer.add(container);
        cteInfNor.setContQt(listContainer);

        ArrayList<CteDocAnt> listDocAnt = new ArrayList<CteDocAnt>();
        CteDocAnt docAnt = new CteDocAnt();
        docAnt.setCNPJ("91665554000163");
        docAnt.setIE("4000000763");
        docAnt.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        docAnt.setUF("RS");

        ArrayList<CteIdDocAnt> listIdDocAnt = new ArrayList<CteIdDocAnt>();
        CteIdDocAnt idDocAnt = new CteIdDocAnt();
        idDocAnt.setChave("43100708693058000170570010000000020001011065");
        listIdDocAnt.add(idDocAnt);

        CteIdDocAnt idDocAnt2 = new CteIdDocAnt();
        idDocAnt2.setNDoc(1);
        idDocAnt2.setSerie("0");
        idDocAnt2.setTpDoc(4);
        idDocAnt2.setDEmi(new Date());
        listIdDocAnt.add(idDocAnt2);

        docAnt.setIdDocAnt(listIdDocAnt);
        listDocAnt.add(docAnt);
        //cteInfNor.setDocAnt(listDocAnt);

        ArrayList<CteSeg> listSeg = new ArrayList<CteSeg>();
        CteSeg seg = new CteSeg();
        seg.setNApol("APO-123");
        seg.setNAver("22222222222222222222");
        seg.setRespSeg(0);
        seg.setVCarga(1000d);//**
        listSeg.add(seg);
        cteInfNor.setSeg(listSeg);

        //RODOVIARIO
        CteRodo rodo = new CteRodo();
        rodo.setLota(0);
        rodo.setdPrev(new Date());
        rodo.setRNTRC("12345678");//RNTRC 8 digitos

        ArrayList<CteOcc> listOcc = new ArrayList<CteOcc>();
        CteOcc occ = new CteOcc();
        occ.setSerie("123");
        occ.setNOcc(128);//Número da Ordem de coleta
        occ.setDEmi(new Date());//Data de emissão da ordem de coleta

        occ.setCInt("145875");
        occ.setCNPJ("04869369000358");
        occ.setIE("116559857110");
        occ.setUF("SP");
        occ.setFone("1190876543");//fone
        listOcc.add(occ);
        rodo.setOcc(listOcc);

        ArrayList<CteValePed> listValePed = new ArrayList<CteValePed>();
        CteValePed valePed = new CteValePed();
        valePed.setCNPJForn("91.665.554/0001-63");
        valePed.setnCompra(1234);//Número do comprovante de compra
        valePed.setCNPJPg("91.665.554/0001-63");
        //listValePed.add(valePed);
//        valePed.setNroRE("12345");
//        valePed.setRespPg(0);
//        valePed.setVTValePed(100d);
//        ArrayList<CteDisp> listDisp = new ArrayList<CteDisp>();
//        CteDisp disp = new CteDisp();
//        disp.setXEmp("NOEM DA EMPRESA DISP");
//        disp.setTpDisp(1);
//        disp.setDVig(new Date());
//        listDisp.add(disp);
//        valePed.setDisp(listDisp);
        rodo.setValePed(listValePed);

        ArrayList<CteVeic> listVeic = new ArrayList<CteVeic>();
        CteVeic veic = new CteVeic();
        CteProp prop = new CteProp();
        veic.setUF("RS");
        veic.setCapKG(1000);
        veic.setCapM3(100);
        veic.setRENAVAM("911965513");
        veic.setPlaca("INR4123");
        veic.setTara(10);
        veic.setTpCar(0);
        veic.setTpProp("T");
        veic.setTpVeic(0);
        veic.setTpRod(0);
        //prop
        prop.setCNPJ("91665554000163");
        prop.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        prop.setUF("RS");
        prop.setRNTRC("12345678");
        prop.setIE("4000000763");
        prop.setTpProp(1);
        veic.setProp(prop);
        listVeic.add(veic);
        rodo.setVeic(listVeic);

        ArrayList<CteLacRodo> listLacRodo = new ArrayList<CteLacRodo>();
        CteLacRodo lacRodo = new CteLacRodo();
        lacRodo.setNLacre("1");
        listLacRodo.add(lacRodo);
        CteLacRodo lacRodo2 = new CteLacRodo();
        lacRodo2.setNLacre("2");
        listLacRodo.add(lacRodo2);
        rodo.setLacRodo(listLacRodo);

        ArrayList<CteMoto> listMoto = new ArrayList<CteMoto>();
        CteMoto moto = new CteMoto();
        moto.setCPF("00492343900");
        moto.setxNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        listMoto.add(moto);
        rodo.setMoto(listMoto);
        cteInfNor.setRodo(rodo);

        //AEREO
        CteAereo aereo = new CteAereo();
        aereo.setCIATA("111");
        aereo.setNMinu(1);
        aereo.setXLAgEmi("X AG EMISSAO");
        //cteInfNor.setAereo(aereo);

        CteAquav aquav = new CteAquav();
        aquav.setDirec("O");
        aquav.setIrin("1");
        aquav.setNBooking("NB222");
        aquav.setNCtrl("NCon");
        aquav.setNViag("1");
        aquav.setPrtDest("PORT DEST");
        aquav.setPrtEmb("POR EMB");
        aquav.setPrtTrans("PRT TRA");
        aquav.setTpNav(1);
        aquav.setVAFRMM(100.55);
        aquav.setXNavio("NOME NAV");
        aquav.setVPrest(100d);
        //cteInfNor.setAquav(aquav);

        //** INF MODAL
        CteInfModal infmodal = new CteInfModal();//**
        infmodal.setVersaoModal("1.04");//**
        infmodal.setXsany("cteModalRodoviario_v1.04.xsd");//**
        cte.setInfModal(infmodal);

        ArrayList<CtePeri> listPeri = new ArrayList<CtePeri>();
        CtePeri peri = new CtePeri();
        peri.setNONU("1");
        peri.setPontoFulgor("1");
        peri.setQTotProd("100");
        peri.setXClaRisco("1");
        peri.setXNomeAE("Nome AE");
        peri.setqVolTipo("100");
        listPeri.add(peri);
        //cteInfNor.setPeri(listPeri);

        ArrayList<CteVeicNovos> listVeicNovos = new ArrayList<CteVeicNovos>();
        CteVeicNovos veicNovos = new CteVeicNovos();
        veicNovos.setChassi("22222222222222222");
        veicNovos.setCCor("1");
        veicNovos.setXCor("COR");
        veicNovos.setVFrete(1000d);
        veicNovos.setVUnit(1000d);
        veicNovos.setCMod("1");
        listVeicNovos.add(veicNovos);
        //cteInfNor.setVeicNovos(listVeicNovos);
        cte.setInfCTeNorm(cteInfNor);

        CteInfCteComp infCteComp = new CteInfCteComp();
        infCteComp.setChave("43100708693058000170570010000000020001011065");
        infCteComp.setVTPrest(150.33);

        ArrayList<CteCompComp> listCompComp = new ArrayList<CteCompComp>();
        CteCompComp compComp = new CteCompComp();
        compComp.setVComp(333d);
        compComp.setXNome("NOME COMP");
        listCompComp.add(compComp);
        //infCteComp.setCompComp(listCompComp);

        CteImposto impComp = new CteImposto();
        impComp.setCST(0);
        impComp.setPICMS(17d);
        impComp.setPRedBC(5d);
        impComp.setVBC(21.02d);
        impComp.setVICMS(17d);
        impComp.setVCred(3.57d);
        infCteComp.setImpComp(impComp);
        //Enviacte.setInfCTeComp(infCteComp);

        return cte;
    }

    //POA
    public Cte getCte(int nrocte) {
        Cte cte = new Cte();
        cte.setCUF(43);
        cte.setCCT(nrocte);//CODIGO NUMERICO PARA CHAVE DE ACESSO
        cte.setCFOP(5351);
        cte.setNatOp("FRETE");
        cte.setForPag(1);
        cte.setMod("57");
        cte.setSerie(0);
        cte.setNCT(nrocte);//NUMERO DA CTE
        cte.setDhEmi(new Date());
        cte.setTpImp(1);
        cte.setTpEmis(1);
        cte.setTpAmb(2);
        cte.setTpCTe(0);
        cte.setProcEmi(0);
        cte.setVerProc("2.00");
        cte.setRefCTE(null);
        cte.setCMunEnv(3550308);//**
        cte.setXMunEnv("SAO PAULO");//**
        cte.setUFEnv("SP");//**
        cte.setModal(1);//01-Rodoviário;  02-Aéreo; 03-Aquaviário; 04-Ferroviário; 05-Dutoviário
        cte.setTpServ(0);
        cte.setCMunIni(4314902);
        cte.setXMunIni("PORTO ALEGRE");
        cte.setUFIni("RS");
        cte.setCMunFim(4314902);
        cte.setXMunFim("PORTO ALEGRE");
        cte.setUFFim("RS");
        cte.setRetira(0);
        cte.setXDetRetira("DETALHES DE RETIRADA");

        CteTomador toma = new CteTomador();
        toma.setToma(2);
        toma.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        toma.setCNPJ("04882329000184");
        toma.setFone("5137821490");
        toma.setIE("0050034359");
        toma.setXLgr("PLINIO KROEFF");
        toma.setNro("1550");
        toma.setXBairro("PORTO SECO");
        toma.setXCpl("complemento");
        toma.setCEP("91150170");
        toma.setCMun(4314902);
        toma.setXMun("PORTO ALEGRE");
        toma.setUF("RS");
        toma.setCPais(1058);
        toma.setXPais("BRASIL");
        cte.setToma(toma);

        CteCompl compl = new CteCompl();
        compl.setXCaracAd("xCaracAd");
        compl.setXCaracSer("xCaracSer");
        compl.setXDest("xDest");
        compl.setXEmi("NOME DO EMISSSOR");
        compl.setXOrig("SIG ORIG");

        ArrayList<CtePass> listPass = new ArrayList<CtePass>();
        CtePass passagem = new CtePass();
        passagem.setXPass("PASSAG 1");
        listPass.add(passagem);

        CtePass passagem2 = new CtePass();
        passagem2.setXPass("PASSAG 2");
        listPass.add(passagem2);

        compl.setCtePass(listPass);
        compl.setXDest("POA");
        compl.setXRota("DIR");
        compl.setTpPer(0);
        compl.setTpHor(0);

        //obs contribuinte
        ArrayList<CteObs> listObsContrib = new ArrayList<CteObs>();
        CteObs obsContrib = new CteObs();
        obsContrib.setXCampo("1");
        obsContrib.setXTexto("MALOTE");
        listObsContrib.add(obsContrib);
        CteObs obsContrib2 = new CteObs();
        obsContrib2.setXCampo("2");
        obsContrib2.setXTexto("TEXT DA OBS 2");
        listObsContrib.add(obsContrib2);
        compl.setObsCont(listObsContrib);

        //obs fisco
        ArrayList<CteObs> listObsFisco = new ArrayList<CteObs>();
        CteObs obsFisco = new CteObs();
        obsFisco.setXCampo("1");
        obsFisco.setXTexto("TEXT DA OBS 1");
        listObsFisco.add(obsContrib);
        CteObs obsFisco2 = new CteObs();
        obsFisco2.setXCampo("2");
        obsFisco2.setXTexto("TEXT DA OBS 2");
        listObsFisco.add(obsFisco2);
        compl.setObsFisco(listObsFisco);
        cte.setCompl(compl);

        //EMITENTE
        CteEmitente emitente = new CteEmitente();
        emitente.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        emitente.setXFant("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        emitente.setCNPJ("04882329000184");
        emitente.setIE("0050034359");
        emitente.setXLgr("PLINIO KROEFF");
        emitente.setNro("1550");
        emitente.setXBairro("PORTO SECO");
        emitente.setXCpl("DOCAS 29 E 30");
        emitente.setCEP("07180000");
        emitente.setCMun(4314902);
        emitente.setXMun("PORTO ALEGRE");
        emitente.setUF("RS");
        emitente.setCPais(1058);
        emitente.setXPais("BRASIL");
        emitente.setFone("1124127221");
        cte.setEmitente(emitente);

        //remetente
        CteRemetente remetente = new CteRemetente();
        remetente.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        remetente.setXFant("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        remetente.setCNPJ("04882329000184");
        remetente.setIE("0050034359");
        remetente.setXLgr("R PLINIO KROEFF");
        remetente.setNro("1550");
        remetente.setXBairro("PORTO SECO");
        remetente.setXCpl("CPL");
        remetente.setCEP("91150170");
        remetente.setCMun(4314902);
        remetente.setXMun("PORTO ALEGRE");
        remetente.setUF("RS");
        remetente.setCPais(1058);
        remetente.setXPais("BRASIL");
        remetente.setXFone("5130770555");

        //INFORMACOES DE NF
        ArrayList<CteInfNF> listNf = new ArrayList<CteInfNF>();
        CteInfNF nf = new CteInfNF();
        nf.setNRoma("ROM");
        nf.setNPed("PED");
        nf.setMod("01");
        nf.setSerie("1");
        nf.setNDoc("11");
        nf.setDEmi(new Date());
        nf.setVBC(100d);
        nf.setVICMS(17d);
        nf.setVBCST(0d);
        nf.setVST(0d);
        nf.setVProd(100d);
        nf.setVNF(100d);
        nf.setNCFOP(5101);
        nf.setNPeso(10d);
        nf.setPIN(null);
        nf.setDPrev(new Date());//03-03-14
        //03-03-14
        CteInfUnidTransp unid = new CteInfUnidTransp();
        unid.setTpUnidTransp(2);
        unid.setIdUnidTransp("TESTE");

        CteInfUnidCarga unidcarga = new CteInfUnidCarga();
        unidcarga.setIdUnidCarga("TESTE");
        unidcarga.setTpUnidCarga(1);
        ArrayList<CteInfUnidCarga> lunid = new ArrayList<CteInfUnidCarga>();
        lunid.add(unidcarga);
        unid.setInfUnidCarga(lunid);

        Collection<CteInfUnidTransp> infUnidTransp = new ArrayList<CteInfUnidTransp>();
        infUnidTransp.add(unid);
        nf.setInfUnidTransp(infUnidTransp);
        listNf.add(nf);
        //remetente.setInfNF(listNf);
        //atencao... ou é informada a NF ou a NF
        ArrayList<CteInfNFe> listNfe = new ArrayList<CteInfNFe>();
        CteInfNFe nfe = new CteInfNFe();//03-03-14
        nfe.setChave("29100705858273000195550000000000010000077270");
        nfe.setDPrev(new Date());//03-03-14
        nfe.setInfUnidTransp(infUnidTransp);
        listNfe.add(nfe);
        remetente.setInfNFe(listNfe);
        cte.setRemetente(remetente);

        //*** FIM REMETENTE ***//
        CteExpedidor expedidor = new CteExpedidor();
        expedidor.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        expedidor.setCNPJ("04882329000184");
        expedidor.setIE("0050034359");
        expedidor.setXLgr("R NAO INFORMADA");
        expedidor.setNro("0");
        expedidor.setXBairro("CENTRO");
        expedidor.setXCpl("CPL");
        expedidor.setCEP("95900000");
        expedidor.setCMun(4314902);
        expedidor.setXMun("PORTO ALEGRE");
        expedidor.setUF("RS");
        expedidor.setCPais(1058);
        expedidor.setXPais("BRASIL");
        expedidor.setFone("5130770555");
        cte.setExpedidor(expedidor);

        CteRecebedor recebedor = new CteRecebedor();
        recebedor.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        recebedor.setCNPJ("08593038000127");
        recebedor.setIE("ISENTO");
        recebedor.setXLgr("RUA FIALHO DE VARGAS");
        recebedor.setNro("320");
        recebedor.setXBairro("CENTRO");
        recebedor.setXCpl("CPL");
        recebedor.setCEP("95900000");
        recebedor.setCMun(4311403);
        recebedor.setXMun("LAJEADO");
        recebedor.setUF("RS");
        recebedor.setCPais(1058);
        recebedor.setXPais("BRASIL");
        recebedor.setFone("1169514242");
        cte.setRecebedor(recebedor);

        CteDestinatario destinatario = new CteDestinatario();
        destinatario.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        destinatario.setCNPJ("08593038000127");
        destinatario.setIE("ISENTO");
        destinatario.setXLgr("RUA FIALHO DE VARGAS");
        destinatario.setNro("320");
        destinatario.setXBairro("CENTRO");
        destinatario.setXCpl("SALA 904");
        destinatario.setCEP("95900000");
        destinatario.setCMun(4311403);
        destinatario.setXMun("LAJEADO");
        destinatario.setUF("RS");
        destinatario.setCPais(1058);
        destinatario.setXPais("BRASIL");
        destinatario.setFone("5137141234");
        cte.setDestinatario(destinatario);

        cte.setVTPrest(21.02d);
        cte.setVRec(21.02d);
        ArrayList<CteCompPrestacaoServico> listCompPrestacaoServico = new ArrayList<CteCompPrestacaoServico>();
        CteCompPrestacaoServico comp = new CteCompPrestacaoServico();
        comp.setXNome("FRETE PESO(inf)");
        comp.setVComp(20.00d);
        listCompPrestacaoServico.add(comp);

        CteCompPrestacaoServico comp2 = new CteCompPrestacaoServico();
        comp2.setXNome("ADICIONAL");
        comp2.setVComp(1.02d);
        listCompPrestacaoServico.add(comp2);

        cte.setCompPrestacaoServico(listCompPrestacaoServico);

        CteImposto imp = new CteImposto();
        imp.setCST(0);
        imp.setPICMS(17d);
        imp.setPRedBC(0d);
        imp.setVBC(100d);
        imp.setVICMS(17d);
        imp.setVCred(0d);
        cte.setImp(imp);

        CteInfCTeNorm cteInfNor = new CteInfCTeNorm();
        cteInfNor.setProPred("DIVERSOS");
        cteInfNor.setXOutCat("DOCUMENTOS");
        cteInfNor.setVCarga(100d);//**

        ArrayList<CteInfQCarga> listInfQ = new ArrayList<CteInfQCarga>();
        CteInfQCarga infq = new CteInfQCarga();
        infq.setCUnid(0);
        infq.setTpMed("UN");
        infq.setQCarga(1000d);
        listInfQ.add(infq);
        cteInfNor.setInfQ(listInfQ);

        ArrayList<CteContQt> listContainer = new ArrayList<CteContQt>();
        CteContQt container = new CteContQt();
        container.setNCont(1234);
        container.setDPrev(new Date());
        listContainer.add(container);
        cteInfNor.setContQt(listContainer);

        ArrayList<CteDocAnt> listDocAnt = new ArrayList<CteDocAnt>();
        CteDocAnt docAnt = new CteDocAnt();
        docAnt.setCNPJ("91665554000163");
        docAnt.setIE("4000000763");
        docAnt.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        docAnt.setUF("RS");

        ArrayList<CteIdDocAnt> listIdDocAnt = new ArrayList<CteIdDocAnt>();
        CteIdDocAnt idDocAnt = new CteIdDocAnt();
        idDocAnt.setChave("43100708693058000170570010000000020001011065");
        listIdDocAnt.add(idDocAnt);

        CteIdDocAnt idDocAnt2 = new CteIdDocAnt();
        idDocAnt2.setNDoc(1);
        idDocAnt2.setSerie("0");
        idDocAnt2.setTpDoc(4);
        idDocAnt2.setDEmi(new Date());
        listIdDocAnt.add(idDocAnt2);

        docAnt.setIdDocAnt(listIdDocAnt);
        listDocAnt.add(docAnt);
        cteInfNor.setDocAnt(listDocAnt);

        ArrayList<CteSeg> listSeg = new ArrayList<CteSeg>();
        CteSeg seg = new CteSeg();
        seg.setNApol("APO-123");
        seg.setNAver("22222222222222222222");
        seg.setRespSeg(0);
        seg.setVCarga(1000d);//**
        listSeg.add(seg);
        cteInfNor.setSeg(listSeg);

        //RODOVIARIO
        CteRodo rodo = new CteRodo();
        rodo.setLota(0);
        rodo.setdPrev(new Date());
        rodo.setRNTRC("12345678");//RNTRC 8 digitos

        ArrayList<CteOcc> listOcc = new ArrayList<CteOcc>();
        CteOcc occ = new CteOcc();
        occ.setSerie("123");
        occ.setNOcc(128);//Número da Ordem de coleta
        occ.setDEmi(new Date());//Data de emissão da ordem de coleta

        occ.setCInt("145875");
        occ.setCNPJ("04882329000184");
        occ.setIE("0050034359");
        occ.setUF("RS");
        occ.setFone("5137141234");//fone
        listOcc.add(occ);
        rodo.setOcc(listOcc);

        ArrayList<CteValePed> listValePed = new ArrayList<CteValePed>();
        CteValePed valePed = new CteValePed();
        valePed.setCNPJForn("91.665.554/0001-63");
        valePed.setnCompra(1234);//Número do comprovante de compra
        valePed.setCNPJPg("91.665.554/0001-63");
        valePed.setVValePed(10d);//03-03-14
        listValePed.add(valePed);
        rodo.setValePed(listValePed);

        ArrayList<CteVeic> listVeic = new ArrayList<CteVeic>();
        CteVeic veic = new CteVeic();
        CteProp prop = new CteProp();
        veic.setUF("RS");
        veic.setCapKG(1000);
        veic.setCapM3(100);
        veic.setRENAVAM("911965513");
        veic.setPlaca("INR4123");
        veic.setTara(10);
        veic.setTpCar(0);
        veic.setTpProp("T");
        veic.setTpVeic(0);
        veic.setTpRod(0);
        //prop
        prop.setCNPJ("91665554000163");
        prop.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        prop.setUF("RS");
        prop.setRNTRC("12345678");
        prop.setIE("4000000763");
        prop.setTpProp(1);
        veic.setProp(prop);
        listVeic.add(veic);
        rodo.setVeic(listVeic);

        ArrayList<CteLacRodo> listLacRodo = new ArrayList<CteLacRodo>();
        CteLacRodo lacRodo = new CteLacRodo();
        lacRodo.setNLacre("1");
        listLacRodo.add(lacRodo);
        CteLacRodo lacRodo2 = new CteLacRodo();
        lacRodo2.setNLacre("2");
        listLacRodo.add(lacRodo2);
        rodo.setLacRodo(listLacRodo);

        ArrayList<CteMoto> listMoto = new ArrayList<CteMoto>();
        CteMoto moto = new CteMoto();
        moto.setCPF("00492343900");
        moto.setxNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        listMoto.add(moto);
        rodo.setMoto(listMoto);
        cteInfNor.setRodo(rodo);

        //AEREO
        CteAereo aereo = new CteAereo();
        aereo.setCIATA("111");
        aereo.setNMinu(1);
        aereo.setXLAgEmi("X AG EMISSAO");
        //cteInfNor.setAereo(aereo);

        CteAquav aquav = new CteAquav();
        aquav.setDirec("O");
        aquav.setIrin("1");
        aquav.setNBooking("NB222");
        aquav.setNCtrl("NCon");
        aquav.setNViag("1");
        aquav.setPrtDest("PORT DEST");
        aquav.setPrtEmb("POR EMB");
        aquav.setPrtTrans("PRT TRA");
        aquav.setTpNav(1);
        aquav.setVAFRMM(100.55);
        aquav.setXNavio("NOME NAV");
        aquav.setVPrest(100d);
        //cteInfNor.setAquav(aquav);

        //** INF MODAL
        CteInfModal infmodal = new CteInfModal();//**
        infmodal.setVersaoModal("2.00");//**
        infmodal.setXsany("cteModalRodoviario_v2.00");//**
        cte.setInfModal(infmodal);

        ArrayList<CtePeri> listPeri = new ArrayList<CtePeri>();
        CtePeri peri = new CtePeri();
        peri.setNONU("1");
        peri.setPontoFulgor("1");
        peri.setQTotProd("100");
        peri.setXClaRisco("1");
        peri.setXNomeAE("Nome AE");
        peri.setqVolTipo("100");
        listPeri.add(peri);
        //cteInfNor.setPeri(listPeri);

        ArrayList<CteVeicNovos> listVeicNovos = new ArrayList<CteVeicNovos>();
        CteVeicNovos veicNovos = new CteVeicNovos();
        veicNovos.setChassi("22222222222222222");
        veicNovos.setCCor("1");
        veicNovos.setXCor("COR");
        veicNovos.setVFrete(100d);
        veicNovos.setVUnit(100d);
        veicNovos.setCMod("1");
        listVeicNovos.add(veicNovos);
        //cteInfNor.setVeicNovos(listVeicNovos);

        //*** FATURA ***//03-03-14
        CteFat fat = new CteFat();
        fat.setNFat("NFAT1");
        fat.setVDesc(0d);
        fat.setVLiq(100d);
        fat.setVOrig(100d);
        CteDup dup = new CteDup();
        dup.setDVenct(new Date());
        dup.setVDup(100d);
        dup.setNDup("Dup1");
        ArrayList<CteDup> ldup = new ArrayList<CteDup>();
        ldup.add(dup);
        fat.setDup(ldup);
        cteInfNor.setFat(fat);
        
        CteAutXML aut = new CteAutXML();//03-03-14
        aut.setCnpj("08593038000127");        
        
        ArrayList<CteAutXML> laut = new ArrayList<CteAutXML>();
        laut.add(aut);
        cteInfNor.setAutXml(laut);
        
        cte.setInfCTeNorm(cteInfNor);

        CteInfCteComp infCteComp = new CteInfCteComp();
        infCteComp.setChave("43100708693058000170570010000000020001011065");
        infCteComp.setVTPrest(150.33);

        ArrayList<CteCompComp> listCompComp = new ArrayList<CteCompComp>();
        CteCompComp compComp = new CteCompComp();
        compComp.setVComp(333d);
        compComp.setXNome("NOME COMP");
        listCompComp.add(compComp);
        //infCteComp.setCompComp(listCompComp);

        CteImposto impComp = new CteImposto();
        impComp.setCST(0);
        impComp.setPICMS(17d);
        impComp.setPRedBC(0d);
        impComp.setVBC(100d);
        impComp.setVICMS(17d);
        impComp.setVCred(0d);
        infCteComp.setImpComp(impComp);
        //Enviacte.setInfCTeComp(infCteComp);

        return cte;
    }
}
