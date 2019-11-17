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
public class ProdutoCozinha {

    private String seq;
    private String prato;
    private String qtd;
    private String garcom;
    private String nMesa;
    private String cozinheiro;
    private String solicitacao;
    private String tEspera;
    private String status;
    
    public ProdutoCozinha() {
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getPrato() {
        return prato;
    }

    public void setPrato(String prato) {
        this.prato = prato;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getGarcom() {
        return garcom;
    }

    public void setGarcom(String garcom) {
        this.garcom = garcom;
    }

    public String getnMesa() {
        return nMesa;
    }

    public void setnMesa(String nMesa) {
        this.nMesa = nMesa;
    }

    public String getCozinheiro() {
        return cozinheiro;
    }

    public void setCozinheiro(String cozinheiro) {
        this.cozinheiro = cozinheiro;
    }

    public String getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(String solicitacao) {
        this.solicitacao = solicitacao;
    }

    public String gettEspera() {
        return tEspera;
    }

    public void settEspera(String tEspera) {
        this.tEspera = tEspera;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
