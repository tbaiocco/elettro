/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cte.model;

import java.util.Collection;

/**
 *
 * @author DerliRiffel
 */
public class CteValePed {

    private String CNPJForn;
    private Integer nCompra;
    private String CNPJPg;
    private Double vValePed;

    public String getCNPJForn() {
        return CNPJForn;
    }

    public void setCNPJForn(String CNPJForn) {
        this.CNPJForn = CNPJForn;
    }

    public String getCNPJPg() {
        return CNPJPg;
    }

    public void setCNPJPg(String CNPJPg) {
        this.CNPJPg = CNPJPg;
    }

    public Integer getnCompra() {
        return nCompra;
    }

    public void setnCompra(Integer nCompra) {
        this.nCompra = nCompra;
    }

    public Double getVValePed() {
        return vValePed;
    }

    public void setVValePed(Double vValePed) {
        this.vValePed = vValePed;
    }
    
}
