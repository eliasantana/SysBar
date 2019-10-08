/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.dao;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Elias Santana
 */
public class CriptoGrafa {

    public CriptoGrafa() {
        
    }
    

    public String encripta(byte[]mensagem) {
        
       String licenca;
        byte[]msgCodificada = Base64.encodeBase64(mensagem);
        licenca = new String(msgCodificada);
        return licenca;
    }
    
    public String encripta(String mensagem) {
        
        String msgCodificada;
        msgCodificada = Base64.encodeBase64String(mensagem.getBytes());
        mensagem = msgCodificada;
        return  mensagem;
    }
    
    public String decripta(String mensagemEncriptada){
            String msg=null;
        try {
            byte[] msgDecriptada = Base64.decodeBase64(mensagemEncriptada.getBytes());
            msg = new String(msgDecriptada);
            
        } catch (Exception e) {
            System.out.println("O sistema inda não possui licença");
        }
       return msg;
    }
    
    
  
    
}
