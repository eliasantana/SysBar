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
   

    // Envia um e-mail a partir de uma caixa postal Gmail
    public boolean enviaEmail(String e_mail,String assunto, String mensagem) {
        boolean resp = false;
        try {
            SimpleEmail email = new SimpleEmail();
            email.setDebug(true);
            // Servidor SMTP
            email.setHostName("smtp.gmail.com");
            //Autentica E-mail
            email.setAuthentication("rese7.contato@gmail.com", "JE09@2018");
            email.setSSL(true);
            // se.addTo("rese7.comercial@gmail.com");
            //Destinatário
            email.addTo(e_mail);
            //Remetente
            email.setFrom("rese7.contato@gmail.com");
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
