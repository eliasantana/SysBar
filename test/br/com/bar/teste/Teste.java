/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.teste;

import br.com.br.controler.ControlerFuncionario;

/**
 *
 * @author Elias Santana
 */
public class Teste {
    
    public static void main(String[] args) {
        ControlerFuncionario cf = new ControlerFuncionario();
        String nome = cf.cargoFuncionario("Janaina");
        System.out.println(nome);
    }
    
}
