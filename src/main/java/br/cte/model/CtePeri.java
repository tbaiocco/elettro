package br.cte.model;

/**
 * Preenchido quando for transporte de
 * produtos classificados pela ONU como
 * perigosos.
 * Não deve ser preenchido para modais
 * aéreo e dutoviário.
 *
 * @author DerliRiffel
 */
public class CtePeri {
    private String nONU;
    private String xNomeAE;
    private String xClaRisco;
    private String grEmb;
    private String qTotProd;
    private String qVolTipo;
    private String pontoFulgor;

    public String getGrEmb() {
        return grEmb;
    }

    public void setGrEmb(String grEmb) {
        this.grEmb = grEmb;
    }

    public String getNONU() {
        return nONU;
    }

    public void setNONU(String nONU) {
        this.nONU = nONU;
    }

    public String getPontoFulgor() {
        return pontoFulgor;
    }

    public void setPontoFulgor(String pontoFulgor) {
        this.pontoFulgor = pontoFulgor;
    }

    public String getQTotProd() {
        return qTotProd;
    }

    public void setQTotProd(String qTotProd) {
        this.qTotProd = qTotProd;
    }

    public String getqVolTipo() {
        return qVolTipo;
    }

    public void setqVolTipo(String qVolTipo) {
        this.qVolTipo = qVolTipo;
    }

    public String getXClaRisco() {
        return xClaRisco;
    }

    public void setXClaRisco(String xClaRisco) {
        this.xClaRisco = xClaRisco;
    }

    public String getXNomeAE() {
        return xNomeAE;
    }

    public void setXNomeAE(String xNomeAE) {
        this.xNomeAE = xNomeAE;
    }


}
