/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.util;

import java.text.DecimalFormat;
import java.util.Locale;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Elias Santana
 */
public class FormataValor {

    // Formata uma String convertendo-a para Double e retorna o valor formatado
    // com vírgula no formato Decimal
    public String Formata(String valor) {

        DecimalFormat df = new DecimalFormat();
        Locale.setDefault(new Locale("pt", "BR"));
        Double valorRecebido=0.00;
        try {
             // Caso ocorra uma Multiple Point Exception o método retornará 0.00
             valorRecebido = Double.parseDouble(valor.replace(",", "."));
        } catch (Exception e) {
            
        }

        try {
            //df.applyPattern("#,###0.00");
            df.applyPattern("#,##0.00");

        } catch (NumberFormatException e) {
            //System.out.println("br.com.bar.util.FormataValor.Formata()");
        }
        return df.format(valorRecebido);

    }
    
    /**
     * Aplica máscara de formatação de valores
     * @param campo Campo do tipo JFormattedTextField que receberá a mascará
     * @param maxInt Número máximo de digitos initeiros 
     * @param minInt  Número minimo de digitos initeiros
     */
     public void aplicaMascara(JFormattedTextField campo, int maxInt, int minInt) {
        // Define a máscara
        DecimalFormat decimal = new DecimalFormat("##,###,###.00");
        // Limita o número máximo e mínimo de inteiros
        decimal.setMaximumIntegerDigits(maxInt);
        decimal.setMinimumIntegerDigits(minInt);
        // Formata o valor
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        campo.setFormatterFactory(dfFactory);
    }

}
