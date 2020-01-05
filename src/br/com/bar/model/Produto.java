/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

/**
 *
 * @author elias
 */
public class Produto {
    
   private String id;
   private String codNCM; // Código NCM do produto
   private String codigoProduto; // Código interno do Produto
   private String nome;
   private String qtd;
   private String qtdMax;
   private String qtdMin;
   private String cad_grupo_produto;
   private int idFornecedor;
   private String valor;
   
   
    public String getTbGrupoId() {
        return cad_grupo_produto;
    }

    public void setTbGrupoId(String tbGrupoId) {
        this.cad_grupo_produto = tbGrupoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getQtdMax() {
        return qtdMax;
    }

    public void setQtdMax(String qtdMax) {
        this.qtdMax = qtdMax;
    }

    public String getQtdMin() {
        return qtdMin;
    }

    public void setQtdMin(String qtdMin) {
        this.qtdMin = qtdMin;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
   

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getCodNCM() {
        return codNCM;
    }

    public void setCodNCM(String codNCM) {
        this.codNCM = codNCM;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }
   
}