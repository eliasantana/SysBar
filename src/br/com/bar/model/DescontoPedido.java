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
public class DescontoPedido {
    
    private int id;
    private double valorDesconto;
    private String motivo;
    private Funcionario idFuncionario;
    private Pedido idPedido;

    public DescontoPedido() {
    }

    public DescontoPedido(int id, double valorDesconto, String motivo, Funcionario idFuncionario, Pedido idPedido) {
        this.id = id;
        this.valorDesconto = valorDesconto;
        this.motivo = motivo;
        this.idFuncionario = idFuncionario;
        this.idPedido = idPedido;
    }
    
    
    public DescontoPedido(double valorDesconto, String motivo, Funcionario idFuncionario, Pedido idPedido) {
       
        this.valorDesconto = valorDesconto;
        this.motivo = motivo;
        this.idFuncionario = idFuncionario;
        this.idPedido = idPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Funcionario getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionario idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }
    
    
    
    
    
}
