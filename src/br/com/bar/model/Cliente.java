/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

/**
 *
 * @author Elias Santana
 * Data: 19/08/2019
 * Adicionada após a versão 1.0 MasterFood
 */
public class Cliente extends Pessoa {

    private  Localidade localidade;
    private String historico;
    private String referencia;
    

    public Cliente() {

    }

    public Cliente(Localidade localidade) {
        this.localidade = localidade;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getHistorico() {
        return historico;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getReferencia() {
        return referencia;
    }

    
}
