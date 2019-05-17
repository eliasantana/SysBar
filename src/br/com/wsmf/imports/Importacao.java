/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wsmf.imports;

import br.com.bar.dao.ConexaoBd;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta classe tem com objetivo importadar os dados do banco local e envialos
 * para um banco da web
 *
 * @author Elias Santana Data: 14/05/2019
 *
 */
public class Importacao {
    
    Connection conexcao = ConexaoBd.conector();
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    File f;
    FileWriter fw;
    PrintWriter pw;
    String url="C:\\xampp\\htdocs\\wsmf\\movimentacao.csv";
    String  data;  
    String  entradas;  
    String  saidas;  
    String  saldo;  
    String  funcionario;  
    
    public boolean importarMovimentacao(){
        
        boolean resp=false;
        
        String sql = "SELECT \n"
            + "    m.id,\n"
            + "    m.data,\n"
            + "    m.entradas as 'entrada',\n"
            + "    m.saidas as 'saida',\n"
            + "    m.saldo as 'saldo',\n"
            + "    f.nome as 'funcionario'\n"
            + "FROM\n"
            + "    dbbar.tbmovimentacao m\n"
            + "    INNER JOIN tbcadfuncionario f ON f.id = m.tbcadfuncionario_id ";
 
        
        try {
            pst=conexcao.prepareStatement(sql);
            rs=pst.executeQuery();
            
            f = new File(url);
            try {
                fw =new FileWriter(f);
            } catch (IOException ex) {
                Logger.getLogger(Importacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            pw=new PrintWriter(fw);
            
            while (rs.next()){
                data = rs.getString("data");
                entradas = rs.getString("entrada");
                saidas = rs.getString("saida");
                saldo = rs.getString("saldo");
                funcionario = rs.getString("funcionario");
                
                pw.println(data+";"+entradas+";"+saidas+";"+saldo+";"+funcionario);
                
            }
            try {
                
            fw.flush();
            } catch (IOException e) {
                System.out.println("br.com.wsmf.imports.Importacao.importarMovimentacao()");
            }
            resp=true;
           
            
        } catch (SQLException e) {
            System.out.println("br.com.wsmf.imports.ImportMovimentaco.importarMovimentacao()");
        }
        
        System.out.println("Dados importados com sucesso! ->" + url);
      return resp;
    }
    
   
    
}
