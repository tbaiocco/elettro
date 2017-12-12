package br.cte.model;

/**
 * Dados dos Veículos
 *
 * @author DerliRiffel
 */
public class CteVeic {

    private String cInt;
    private String RENAVAM;
    private String placa;
    private Integer tara;
    private Integer capKG;
    private Integer capM3;
    private String tpProp;
    private Integer tpVeic;
    private Integer tpRod;
    private Integer tpCar;
    private String UF;
    private CteProp prop;

    public String getRENAVAM() {
        return RENAVAM;
    }

    public void setRENAVAM(String RENAVAM) {
        this.RENAVAM = RENAVAM;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCInt() {
        return cInt;
    }

    public void setCInt(String cInt) {
        this.cInt = cInt;
    }

    public Integer getCapKG() {
        return capKG;
    }

    public void setCapKG(Integer capKG) {
        this.capKG = capKG;
    }

    public Integer getCapM3() {
        return capM3;
    }

    public void setCapM3(Integer capM3) {
        this.capM3 = capM3;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public CteProp getProp() {
        return prop;
    }

    public void setProp(CteProp prop) {
        this.prop = prop;
    }

    public Integer getTara() {
        return tara;
    }

    public void setTara(Integer tara) {
        this.tara = tara;
    }

    public Integer getTpCar() {
        return tpCar;
    }

    public void setTpCar(Integer tpCar) {
        this.tpCar = tpCar;
    }

    public String getTpProp() {
        return tpProp;
    }

    public void setTpProp(String tpProp) {
        this.tpProp = tpProp;
    }

    public Integer getTpRod() {
        return tpRod;
    }

    public void setTpRod(Integer tpRod) {
        this.tpRod = tpRod;
    }

    public Integer getTpVeic() {
        return tpVeic;
    }

    public void setTpVeic(Integer tpVeic) {
        this.tpVeic = tpVeic;
    }
}
