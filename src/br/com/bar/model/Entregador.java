/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

/**
 *
 * @author Elias Santana
 * Data: 21/08/2019
 * Adicionado: Após versão 1.0
 */
public class Entregador extends Pessoa{
    private int id;
    private String veiculo;
    private int status;
    

    public Entregador() {
    }

    public Entregador(String veiculo, int status) {
        this.veiculo = veiculo;
        this.status = status;
    }

    public Entregador(int id, String veiculo, int status) {
        this.id = id;
        this.veiculo = veiculo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
    
}
