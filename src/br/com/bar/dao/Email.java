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

    String cliente = "CURRASQUINHO DO PROFF";

    public Email() {
    }
   


    public boolean enviaEmail(String e_mail,String assunto, String mensagem) {
        boolean resp = false;
        try {
            SimpleEmail email = new SimpleEmail();
            email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            email.setAuthentication("rese7.suporte@gmail.com", "JE09@2018");
            email.setSSL(true);
            // se.addTo("rese7.comercial@gmail.com");
            email.addTo(e_mail);
            email.setFrom("rese7.suporte@gmail.com");
            email.setSubject(assunto);
            email.setMsg(mensagem);
            email.send();
            resp=true;

        } catch (EmailException e) {
            System.out.println("br.com.bar.dao.Email.enviaEmail()" + e);
            resp=false;
        }
        
        return resp;
    }
    
  

}
