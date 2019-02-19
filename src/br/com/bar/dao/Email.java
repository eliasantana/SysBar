/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.dao;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


/**
 *
 * @author Elias Santana
 */
public class Email {

   //String cliente = "CURRASQUINHO DO PROFF";

    public Email() {
    }
   

    // Envia um e-mail a partir de uma caixa postal SMTP indidaca nos parâmetros
    /**
     * @param e_mail Email do Destinatário
     * @param assunto Assunto 
     * @param mensagem Mensagem 
     * @return Retorna um Boolean como resposta.
     */
    
    public boolean enviaEmail(String e_mail,String assunto, String mensagem) {
        boolean resp = false;
        try {
            SimpleEmail email = new SimpleEmail();
            email.setDebug(true);
            // Servidor SMTP
            
            email.setHostName("smtp.rese7.com.br");
            //Autentica E-mail
            email.setAuthentication("contato@rese7.com.br", "JanielElias09Setembro@2018");
            //Habilitar casao o envio seja realizado pelo servidor Smtp do Gmail
            //email.setSSL(true);
            email.setSSL(false);
            // Definição da porta exigida pela hospedagem da caixa de e-mail
            email.setSslSmtpPort("587");
            
            //Destinatário'
            email.addTo(e_mail);
            //Remetente           
            email.setFrom("contato@rese7.com.br");
            
            //Assunto da mensagem
            email.setSubject(assunto);
            
            //Mensagem
            email.setMsg(mensagem);
            
            //Envia E-mail
            email.send();
            resp=true;

        } catch (EmailException e) {
            System.out.println("br.com.bar.dao.Email.enviaEmail()" + e);
            resp=false;
        }
        
        return resp;
    }
    
  

}
