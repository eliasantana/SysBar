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
    //id, veiculo, status, endereco, numero, bairro, cep, cidade, uf, telefone, celular, historico, email, cpf, rg, cnh, validade, complemento
    private int id;
    private String veiculo;
    private int status;
    private String cpf;
    private String rg;
    private String historico;
    private String cnh;
    private String validade;
    private String placa;
    
    

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    
    
}
