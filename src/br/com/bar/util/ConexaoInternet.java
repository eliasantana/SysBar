/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elias Santana
 * Esta classe reliza um teste de conexão com a internet
 * caso haja uma conexão da máquina com a internet a mesma retornará um boolean
 * como resposta 
 * 
 */
public class ConexaoInternet {

    public ConexaoInternet() {
    }

    public boolean temConexao() {
        boolean resp = false;
      
           try{
           
            java.net.URL url = new java.net.URL("http://www.google.com.br");
                      
            java.net.URLConnection conn = url.openConnection();
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) conn;
            httpConn.connect();
            int x = httpConn.getResponseCode();
            if(x == 200){
                   resp=true;
            }
        }
        catch(java.net.MalformedURLException urlmal){
        }
        catch(java.io.IOException ioexcp){
        }
        return resp;
    }

}
