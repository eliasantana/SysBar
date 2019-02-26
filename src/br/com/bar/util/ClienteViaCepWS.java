/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta classse utiliza classes nativas do java para consumir Webservices
 * do correio SIGEP WEB (http://www.correios.com.br), viacep (https://viacep.com.br) 
 * e postmon (http://postmon.com.br), para disponibilizar informações de endereço.
 *
 */
public class ClienteViaCepWS {
    /**
     * Busca inforações de um endereço no WebServices do Correio
     * @param cep Cep a ser localizado
     * @return json Retorna uma String Json contendo os dados localizados
     */
     public static String buscarCep(String cep) {
        String json;

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));

            json = jsonSb.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return json;
    }
    /**
     * Formata CEP localizado um Map com todas as informações localizadas
     * @param jsonCep CEP localizado pelo método buscarCep().
     * @return mapa Retorna um objeto Map 
     */
    public static Map formataCepWs(String jsonCep){
        Map<String,String> mapa = new HashMap<>();

        Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(jsonCep);
        while (matcher.find()) {
            String[] group = matcher.group().split(":");
            mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
        }
        // Informações contidas no mapa
        // "uf", "logradouro"=(Endereço),"complemento","bairro","localidade"=(Endereço), "cep"
        return mapa;
    }
    
    public static void main(String[] args) throws IOException {
        String json = buscarCep("69046000");
        System.out.println(json);

        Map<String,String> mapa = new HashMap<>();

        Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
        while (matcher.find()) {
            String[] group = matcher.group().split(":");
            mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
        }

        System.out.println(mapa);
    }
}

