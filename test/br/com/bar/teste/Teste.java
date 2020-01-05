/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.teste;

import br.com.br.controler.ControlerEstoque;

/**
 *
 * @author Elias Santana
 */
public class Teste {
    
    public static void main(String[] args) {
        ControlerEstoque ce = new ControlerEstoque();
        System.out.println(ce.existeCodigoProdutuo("10000"));
    }
    
}
