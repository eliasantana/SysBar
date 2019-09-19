/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

import com.google.gson.Gson;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.codehaus.jettison.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Elias Santana
 */
public class TesteJesonString {
    
    
    public static void main(String[] args) throws org.codehaus.jettison.json.JSONException {
        
        File retornoJeson = new File("C:\\Sysbar\\retorno.json");
        Gson gson = new Gson();
        Nfce nfce;
        try {
            FileReader fr = new FileReader(retornoJeson);
            String linha;
            BufferedReader bw = new BufferedReader(fr);
            nfce = gson.fromJson(bw, Nfce.class);
            
            System.out.println(nfce.getDescricao());
            
//            while ((linha = bw.readLine())!=null){
//                System.out.println(linha);
//            }
        } catch (IOException ex) {
            Logger.getLogger(TesteJesonString.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        
       
    }
}
