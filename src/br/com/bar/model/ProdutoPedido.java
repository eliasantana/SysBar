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
public class ProdutoPedido {
    
    String tbproduto_id, qtd, valorUnit, Total, data, cadmesa_id, cadpedido_id_pedido, cadpedido_tbcadfuncionario_id;

    public String getTbproduto_id() {
        return tbproduto_id;
    }

    public void setTbproduto_id(String tbproduto_id) {
        this.tbproduto_id = tbproduto_id;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(String valorUnit) {
        this.valorUnit = valorUnit;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCadmesa_id() {
        return cadmesa_id;
    }

    public void setCadmesa_id(String cadmesa_id) {
        this.cadmesa_id = cadmesa_id;
    }

    public String getCadpedido_id_pedido() {
        return cadpedido_id_pedido;
    }

    public void setCadpedido_id_pedido(String cadpedido_id_pedido) {
        this.cadpedido_id_pedido = cadpedido_id_pedido;
    }

    public String getTbcadfuncionario_id() {
        return cadpedido_tbcadfuncionario_id;
    }

    public void setTbcadfuncionario_id(String cadpedido_tbcadfuncionario_id) {
        this.cadpedido_tbcadfuncionario_id = cadpedido_tbcadfuncionario_id;
    }
    
    
    
    
    
    
}
