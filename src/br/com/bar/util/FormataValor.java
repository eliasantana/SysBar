/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.util;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 *
 * @author Elias Santana
 */
public class FormataValor {
    // Formata uma String convertendo-a para Double e retorna o valor formatado
    // com vírgula no formato Decimal
    public String Formata(String valor){
        
            DecimalFormat df = new DecimalFormat();
            Locale.setDefault(new Locale("pt", "BR"));
            Double valorRecebido = Double.parseDouble(valor.replace(",", "."));
        try {
            
            
            //df.applyPattern("#,###0.00");
            df.applyPattern("#,##0.00");

        } catch (NumberFormatException e) {
            System.out.println("br.com.bar.util.FormataValor.Formata()");
        }
            return df.format(valorRecebido);
        
    
   
    }
    
}
