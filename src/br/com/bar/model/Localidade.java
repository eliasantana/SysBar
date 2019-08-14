/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

/**
 *
 * @author Elias Santana
 * 14/08/2019
 */
public class Localidade {
    
    private int id;
    private String nome;
    private Double taxa;
    
    public Localidade() {
    }

    public Localidade(String nome, Double taxa) {       
        this.nome = nome;
        this.taxa = taxa;
    }

    public Localidade(int id, String nome, Double taxa) {
        this.id = id;
        this.nome = nome;
        this.taxa = taxa;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }
    
    
    
    
}
