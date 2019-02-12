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
public class ModelReimpressao {
    //MESA, PEDIDO, TOTAL R$, DESCONTO R$, COMISSÃO, GARÇOM
    private String mesa;
    private String pedido;
    private String total;
    private String desc;
    private String comissao;
    private String garcom;

    public ModelReimpressao() {
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getComissao() {
        return comissao;
    }

    public void setComissao(String comissao) {
        this.comissao = comissao;
    }

    public String getGarcom() {
        return garcom;
    }

    public void setGarcom(String garcom) {
        this.garcom = garcom;
    }
    
    
    
}
