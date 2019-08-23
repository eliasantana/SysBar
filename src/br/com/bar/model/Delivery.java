/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Elias Santana
 */
public class Delivery {
    
  // id, cadpedido_id_pedido, tbcliente_id, data, total_pedido, tx_servico, tx_entrega, total_geral, tbentregador_id, hora_saida, cadpedido_tbcadfuncionario_id
  
  private int id;
  private int idPedido;
  private int idCliente;
  private Timestamp data;
  private double totalPedido;
  private double txServico;
  private double txEntrega;
  private double totalGeral;
  private int idEntregador;
  private Time horaSaida;
  private int idFuncionario;
  private String nesa;
    public Delivery() {
    }

    public Delivery(int id, int idPedido, int idCliente, Timestamp data, double totalPedido, double txServico, double txEntrega, double totalGeral, int idEntregador, Time horaSaida, int idFuncionario) {
        this.id = id;
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.data = data;
        this.totalPedido = totalPedido;
        this.txServico = txServico;
        this.txEntrega = txEntrega;
        this.totalGeral = totalGeral;
        this.idEntregador = idEntregador;
        this.horaSaida = horaSaida;
        this.idFuncionario = idFuncionario;
    }
    
    public Delivery(int idPedido, int idCliente, Timestamp data, double totalPedido, double txServico, double txEntrega, double totalGeral, int idEntregador, Time horaSaida, int idFuncionario) {
        
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.data = data;
        this.totalPedido = totalPedido;
        this.txServico = txServico;
        this.txEntrega = txEntrega;
        this.totalGeral = totalGeral;
        this.idEntregador = idEntregador;
        this.horaSaida = horaSaida;
        this.idFuncionario = idFuncionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public double getTxServico() {
        return txServico;
    }

    public void setTxServico(double txServico) {
        this.txServico = txServico;
    }

    public double getTxEntrega() {
        return txEntrega;
    }

    public void setTxEntrega(double txEntrega) {
        this.txEntrega = txEntrega;
    }

    public double getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(double totalGeral) {
        this.totalGeral = totalGeral;
    }

    public int getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(int idEntregador) {
        this.idEntregador = idEntregador;
    }

    public Time getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Time horaSaida) {
        this.horaSaida = horaSaida;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNesa() {
        return nesa;
    }

    public void setNesa(String nesa) {
        this.nesa = nesa;
    }
  
   
    
 
}
