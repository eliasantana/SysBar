/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

/**
 *
 * @author Elias Santana
 */


public class ProdutoNota {

    private String codProduto;
    private String codNcm;
    private String descricao;
    private String qtd;
    private String vUnit;
    private String vlrTotal;   
    private String vlrTotalGeral;
    
    
    public ProdutoNota() {
        
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getCodNcm() {
        return codNcm;
    }

    public void setCodNcm(String codNcm) {
        this.codNcm = codNcm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getvUnit() {
        return vUnit;
    }

    public void setvUnit(String vUnit) {
        this.vUnit = vUnit;
    }

    public String getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(String vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public String getVlrTotalGeral() {
        return vlrTotalGeral;
    }

    public void setVlrTotalGeral(String vlrTotalGeral) {
        this.vlrTotalGeral = vlrTotalGeral;
    }
    
    
    
}
