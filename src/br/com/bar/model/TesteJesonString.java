/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Elias Santana
 */
public class TesteJesonString {
    
    
    
    public static void main(String[] args) throws org.codehaus.jettison.json.JSONException, ParseException, IOException {
    
        org.json.simple.JSONObject jo;
        JSONParser parser = new JSONParser();
        Nfce nota = new Nfce();
        
        try {
           jo = (org.json.simple.JSONObject) parser.parse(new FileReader("C:\\Sysbar\\retorno.json"));
           nota.setChave_nfe((String) jo.get("chave_nfe"));
           nota.setUrl_consulta_nf((String) jo.get("url_consulta_nf"));
           nota.setSerie((String) jo.get("serie"));
           nota.setNumero((String) jo.get("numero"));
           nota.setQrcode_url((String) jo.get("qrcode_url"));
           nota.setNumero_protocolo((String) jo.get("numero_protocolo"));
           nota.setData_emissao((String) jo.get("data_emissao"));
           nota.setInformacoes_adicionais_contribuinte((String) jo.get("informacoes_adicionais_contribuinte"));
                      
          System.out.println(nota.toString());
           
           
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TesteJesonString.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
