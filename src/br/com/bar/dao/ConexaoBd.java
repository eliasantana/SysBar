/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.dao;

import br.com.br.controler.ControlerParametro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author elias
 */
public class ConexaoBd {

    

    public static Connection conector() {
        
        
        //Instancia o controler parâmetro e carrega os dados do arquivo param.txt
        
        
        ControlerParametro cp = new ControlerParametro();
        ArrayList<String> d = new ArrayList<>();
        d=cp.lerArquivoParametro();
        
                  
        String ipServidor = d.get(0);
        String usuario = d.get(1);
        String senha = d.get(2);
        String banco = d.get(3);        
        String porta = d.get(4);
         
        
        /*
          String driver="com.mysql.cj.jdbc.Driver";
          String url="jdbc:mysql://localhost:3306/dbbar?useTimezone=true&serverTimezone=UTC";
          String user="root";
          String password="202649";
         
        */
        Connection conexao = null;
        

        // Informa os dados registrados no arquivo de parâmetro
        
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://"+ipServidor+":"+porta+"/"+banco ;
        String user = usuario;
        String password = senha;

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);           
            return conexao;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("br.com.bar.dao.ConexaoBd.conector()" + e);
            
        }
        return conexao;
    }

    public void desconectaBd() {
        try {
            ConexaoBd.conector().close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
