/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.dao;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Elias Santana
 */
public class Email {

    
    public Email() {
    }

    // Envia um e-mail a partir de uma caixa postal SMTP indidaca nos parâmetros
    /**
     * @param e_mail Email do Destinatário
     * @param assunto Assunto
     * @param mensagem Mensagem
     * @return Retorna um Boolean como resposta.
     */
    
    public boolean enviaEmail(String e_mail, String assunto, String mensagem) {
        boolean resp = false;
        try {
            SimpleEmail email = new SimpleEmail();
            email.setDebug(true);
            // Servidor SMTP

            //email.setHostName("smtp.rese7.com.br");
            email.setHostName("smtp.umbler.com");
            //Autentica E-mail
            //email.setAuthentication("contato@rese7.com.br", "JanielElias09Setembro@2018");
            email.setAuthentication("suporte@rese7.com.br", "e@Bw],D3y9Lv");

            //Habilitar casao o envio seja realizado pelo servidor Smtp do Gmail
            email.setSSL(false);
            //email.setSSL(false);
            // Definição da porta exigida pela hospedagem da caixa de e-mail
            email.setSslSmtpPort("587");

            //Destinatário'
            email.addTo(e_mail);
            //Remetente           
            email.setFrom("suporte@rese7.com.br");

            //Assunto da mensagem
            email.setSubject(assunto);

            //Mensagem
            email.setMsg(mensagem);

            //Envia E-mail
            email.send();
            resp = true;

        } catch (EmailException e) {
            System.out.println("br.com.bar.dao.Email.enviaEmail()" + e);
            resp = false;
        }

        return resp;
    }

    /**
     * Data: 31-08-2019 Versão: 1.0.2d Envia um e-mail para o destinatário
     *
     * @param hostname Nome do Host do e-mail remetene ex: smtp.host.com.br.
     * @param auth Email de Autenticação o mesmo do remetente.
     * @param password Senha do e-mail de autenticação
     * @param destinatario Email de destino
     * @param assunto Assunto da Mensagem
     * @param mensagem Mensagem
     * @return Boolean Retorna TRUE se o envio for bem sucedido.
     *
     */
    public boolean enviaEmail(String hostname, String auth, String password, String destinatario, String assunto, String mensagem) {
        boolean resp = false;
        try {
            SimpleEmail email = new SimpleEmail();
            email.setDebug(true);
            // Servidor SMTP
            //smtp.rese7.com.br
            email.setHostName(hostname);
            //Autentica E-mail
            //"contato@rese7.com.br", "JanielElias09Setembro@2018"
            email.setAuthentication(auth, password);
            email.setSSLOnConnect(true);
            //Habilitar casao o envio seja realizado pelo servidor Smtp do Gmail
            //email.setSSL(true);
            email.setSSL(true);
            // Definição da porta exigida pela hospedagem da caixa de e-mail
            email.setSmtpPort(465);

            //Destinatário'
            email.addTo(destinatario);
            //Remetente  
            //"contato@rese7.com.br"
            email.setFrom(auth);

            //Assunto da mensagem
            email.setSubject(assunto);

            //Mensagem
            email.setMsg(mensagem);

            //Envia E-mail
            email.send();
            resp = true;

        } catch (EmailException e) {
            System.out.println("br.com.bar.dao.Email.enviaEmail()" + e);
            resp = false;
        }

        return resp;
    }

    public void emailSimples() {

        SimpleEmail mail = new SimpleEmail();
        try {
           
            
            mail.setDebug(true);
            mail.setFrom("suporte@rese7.com.br", "Master Food");
            mail.setSubject("E-mail exemplo");
            mail.setMsg("E-mail de exemplo");
            mail.setSSLOnConnect(false);
            mail.setAuthentication("suporte@rese7.com.br", "e@Bw],D3y9Lv");
            mail.setHostName("smtp.umbler.com");
            mail.setSmtpPort(587);
            mail.addTo("eliasantanasilva@gmail.com", "Elias Sanatna");
            mail.send();
            
        } catch (EmailException e) {
        }
    }
    
    public void htmlMail(String para, String de, String assunto, StringBuilder mensagem, String empresa) throws EmailException{
        HtmlEmail mail = new HtmlEmail();
        mail.setHtmlMsg(mensagem.toString());
        // Habilia ou Desabilita o Debug do envio.
        mail.setDebug(false);
        mail.setFrom(de, "Master Food");
        mail.setSubject(assunto);
        //mail.setMsg("E-mail de exemplo");
        mail.setSSLOnConnect(false);
        mail.setAuthentication("suporte@rese7.com.br", "e@Bw],D3y9Lv");
        mail.setHostName("smtp.umbler.com");
        mail.setSmtpPort(587);
        mail.addTo(para, empresa);
        mail.send();
        
    }

}
