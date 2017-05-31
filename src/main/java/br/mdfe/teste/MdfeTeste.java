/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mdfe.teste;

import br.mdfe.model.Mdfe;
import br.mdfe.model.MdfeAutXML;
import br.mdfe.model.MdfeCondutor;
import br.mdfe.model.MdfeEmit;
import br.mdfe.model.MdfeInfANTT;
import br.mdfe.model.MdfeInfCIOT;
import br.mdfe.model.MdfeInfContratante;
import br.mdfe.model.MdfeInfMunCarrega;
import br.mdfe.model.MdfeInfMunDescarga;
import br.mdfe.model.MdfeInfNFe;
import br.mdfe.model.MdfeProp;
import br.mdfe.model.MdfeRodo;
import br.mdfe.model.MdfeValePed;
import br.mdfe.model.MdfeVeicReboque;
import br.mdfe.model.MdfeVeicTracao;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Derli
 */
public class MdfeTeste {

    public Mdfe getMdfe(int nro) {
        Mdfe mdfe = new Mdfe();

        /**
         * EMITENTE
         */
        MdfeEmit emitente = new MdfeEmit();
        emitente.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");//Razão Social/Nome
        emitente.setXFant("THOMAS");//Nome fantasia
        emitente.setIE("4000000259");//Inscrição Estadual
        emitente.setCNPJ("91278119000186");//CNPJ emitente
        emitente.setXLgr("AV 28 DE MAIO");//Logradouro
        emitente.setNro("921");//Número do endereço
        emitente.setXCpl("");//complemento do endereço
        emitente.setXBairro("CENTRO");//Bairro
        emitente.setCMun(4321451);//Código do município no IBGE
        emitente.setXMun("SANTA CLARA DO SUL");
        emitente.setUF("RS");//sigla UF
        emitente.setCEP("95915000");//Código do CEP
        emitente.setFone("51-3714-1234");//Telefone
        emitente.setEmail("derli.riffel@gmail.com");//E-mail do emitente

        /**
         * MODAL RODOVIARIO
         */
        MdfeRodo rodo = new MdfeRodo();
        rodo.setRNTRC("22334455");
        rodo.setCIOT("111222333444");

        MdfeVeicTracao tracao = new MdfeVeicTracao();
        tracao.setCInt(1234);
        tracao.setPlaca("INR4123");
        tracao.setTara(15000d);
        tracao.setCapKG(10000d);
        tracao.setCapM3(10d);
        //Proprietários do Veículo. Só preenchido quando o veículo não pertencer à empresa emitente do MDF-e
        MdfeProp prop = new MdfeProp();
        prop.setCNPJ("91665554000163");
        prop.setXNome("CALCADOS ANDREZA SA");
        prop.setUF("RS");
        prop.setIE("ISENTO");
        prop.setRNTRC("12342443");
        prop.setTpProp(1);//Tipo Proprietário
        tracao.setProp(prop);

        ArrayList<MdfeCondutor> lCond = new ArrayList<MdfeCondutor>();
        MdfeCondutor cond = new MdfeCondutor();
        cond.setCPF("00027296016");
        cond.setXNome("DERLI ELIAS RIFFEL");
        lCond.add(cond);

        MdfeCondutor cond2 = new MdfeCondutor();
        cond2.setCPF("59437282870");
        cond2.setXNome("TEO BAIOCCO");
        lCond.add(cond2);
        tracao.setCondutor(lCond);
        tracao.setTpRod(1);//01 - Truck;
        tracao.setTpCar(0);//00 - não aplicável;
        tracao.setUF("RS");
        rodo.setVeicTracao(tracao);

        MdfeVeicReboque reboque = new MdfeVeicReboque();
        reboque.setCInt(1234);
        reboque.setPlaca("INR4123");
        reboque.setTara(15000d);
        reboque.setCapKG(10000d);
        reboque.setCapM3(10d);
        //Proprietários do Veículo. Só preenchido quando o veículo não pertencer à empresa emitente do MDF-e
        MdfeProp propreb = new MdfeProp();
        propreb.setCNPJ("91665554000163");
        propreb.setXNome("CALCADOS ANDREZA SA");
        propreb.setUF("RS");
        propreb.setIE("ISENTO");
        propreb.setRNTRC("12342443");
        propreb.setTpProp(1);//Tipo Proprietário
        reboque.setProp(propreb);

        reboque.setTpCar(01);//01 - aberta;
        reboque.setUF("RS");
        ArrayList<MdfeVeicReboque> lReb = new ArrayList<MdfeVeicReboque>();
        lReb.add(reboque);
        rodo.setVeicReboque(lReb);

        MdfeInfANTT infANTT = new MdfeInfANTT();
        infANTT.setRNTRC("12342443");
        MdfeInfCIOT infCIOT = new MdfeInfCIOT();
        infCIOT.setCIOT("123456789012");
        infCIOT.setCNPJ("91665554000163");
        
        ArrayList<MdfeValePed> lVped = new ArrayList<MdfeValePed>();
        MdfeValePed vped = new MdfeValePed();
        vped.setCNPJForn("91665554000163");
        vped.setCNPJPg("91665554000163");
        vped.setNCompra("123423434");
        vped.setvValePed(352.98d);
        infANTT.getValePed().add(vped);

        vped = new MdfeValePed();
        vped.setCNPJForn("08593038000127");
        vped.setCPFPg("00492343900");
        vped.setNCompra("123423435");
        vped.setvValePed(35.0d);
        lVped.add(vped);
        infANTT.getValePed().add(vped);
        
        infANTT.setInfContratantes(new ArrayList<MdfeInfContratante>());
        MdfeInfContratante infContratante = new MdfeInfContratante();
        infContratante.setCNPJ("91665554000163");
        infANTT.getInfContratantes().add(infContratante);
        infContratante = new MdfeInfContratante();
        infContratante.setCPF("00492343900");
        infANTT.getInfContratantes().add(infContratante);
        
        rodo.setInfANTT(infANTT);

        /**
         * INFORMACAO DE DOCUMENTOS
         */
        MdfeInfMunDescarga desc = new MdfeInfMunDescarga();
        desc.setCMunDescarga(4205407);
        desc.setXMunDescarga("Florianopolis");
        
//        ArrayList<MdfeInfCTe> lCte = new ArrayList<MdfeInfCTe>();
//        MdfeInfCTe cte = new MdfeInfCTe();
//        cte.setChCTe("43100792521475000141570000000000030000000030");
//        lCte.add(cte);
//        desc.setInfCTe(lCte);
//        mdfe.setQCTe(1);
//
//        ArrayList<MdfeInfCT> lCt = new ArrayList<MdfeInfCT>();
//        MdfeInfCT ct = new MdfeInfCT();
//        ct.setNCT("1231234");
//        ct.setSerie("1");
//        ct.setVCarga(100d);
//        ct.setdEmi(new Date());
//        desc.setInfCT(lCt);
//        mdfe.setQCT(1);

        ArrayList<MdfeInfNFe> lNfe = new ArrayList<MdfeInfNFe>();
        MdfeInfNFe nfe = new MdfeInfNFe();
        nfe.setChNFe("43140591278119000186550010000000811000008550");
        lNfe.add(nfe);
        lNfe.add(nfe);
        desc.setInfNFe(lNfe);
        mdfe.setQNFe(1);

//        ArrayList<MdfeInfNF> lNf = new ArrayList<MdfeInfNF>();
//        MdfeInfNF nf = new MdfeInfNF();
//        nf.setCNPJ("91278119000186");
//        nf.setNNF("1000");
//        nf.setSerie("1");
//        nf.setUF("RS");
//        nf.setVNF(100d);
//        nf.setdEmi(new Date());
//        lNf.add(nf);
//        desc.setInfNF(lNf);
//        mdfe.setQNF(1);

        ArrayList<MdfeInfMunDescarga> lMunDesc = new ArrayList<MdfeInfMunDescarga>();
        lMunDesc.add(desc);
        mdfe.setInfMunDescarga(lMunDesc);
                
        /**
         * DADOS DA MDFE
         */
        mdfe.setCUF(43);
        mdfe.setTpAmb(2);
        mdfe.setTpEmit(2);
        mdfe.setMod(58);
        mdfe.setSerie("1");
        mdfe.setNMDF(nro);//nro mdfe
        mdfe.setCMDF(nro);
        mdfe.setModal(1);
        mdfe.setRodo(rodo);
        mdfe.setDhEmi(new Date());
        mdfe.setTpEmis(1);
        mdfe.setProcEmi(0);
        mdfe.setVerProc("0.001");
        mdfe.setUFIni("RS");
        mdfe.setUFFim("SC");
        //totais
        mdfe.setVCarga(1000d);
        mdfe.setQCarga(100d);
        mdfe.setCUnid(01);

        ArrayList<MdfeInfMunCarrega> lMunCarga = new ArrayList<MdfeInfMunCarrega>();
        MdfeInfMunCarrega cmun = new MdfeInfMunCarrega();
        cmun.setCMunCarrega(4316758);
        cmun.setXMunCarrega("SANTA CLARA DO SUL");
        lMunCarga.add(cmun);

        MdfeInfMunCarrega cmun2 = new MdfeInfMunCarrega();
        cmun2.setCMunCarrega(4311403);
        cmun2.setXMunCarrega("LAJEADO");
        lMunCarga.add(cmun2);
        mdfe.setInfMunCarrega(lMunCarga);

        ArrayList<String> lacres = new ArrayList<String>();
        lacres.add("AKUX10021KX");
        lacres.add("AKUX10020KX");
        mdfe.setNLacre(lacres);

        mdfe.setInfCpl("informacao complementar");
        mdfe.setInfAdFisco("INF ADD FISCO");

        MdfeAutXML a = new MdfeAutXML();
        a.setCNPJ("08593038000127");
        ArrayList<MdfeAutXML> lAutXml = new ArrayList<MdfeAutXML>();
        lAutXml.add(a);

        mdfe.setAutXML(lAutXml);
        mdfe.setEmit(emitente);
        
        mdfe.setVersao("3.00");
        
        return mdfe;
    }
}
