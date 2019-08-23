/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

/**
 *
 * @author Elias Santana
 * 
 */
public class ControlerEntregador {
    Connection conexao;
    PreparedStatement pst=null;
    ResultSet rs = null;    
   
     /**
     * Lista todos os entregadores disponíveis 
     * @param combo  JcomboBox a ser preenchido;
     * 
     */
    
   public void listaEntregador(JComboBox combo){
       String sql="SELECT nome FROM dbbar.tbentregador WHERE status=0;";
       
       try {
           conexao=ConexaoBd.conector();
           pst = conexao.prepareStatement(sql);
           rs = pst.executeQuery();
           
           combo.removeAll();
           combo.addItem("Selecione...");
           while (rs.next()){
               combo.addItem(rs.getString("nome"));
           }
       } catch (SQLException e) {
           System.out.println("br.com.br.controler.ControlerEntregador.listaEntregador()"+e);
       }
   }
}
