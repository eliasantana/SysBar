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
   private String nome;
   private String qtd;
   private String qtdMax;
   private String qtdMin;
   private String cad_grupo_produto;
   private int idFornecedor;
   
   
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
   private String valor;

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
   
   
}