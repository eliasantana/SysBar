/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Entregador;
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
   /**
    * Retorna um entregador
    * @param nome Nome do Entregador
    * @return Entregador Retorna um obj do tipo Entregador.
    */
   public Entregador localizaEntregador(String nome){
       String sql="SELECT * FROM tbentregador WHERE nome=? ";
       Entregador e = new Entregador();
       try {
           conexao=ConexaoBd.conector();           
           pst=conexao.prepareStatement(sql);
           pst.setString(1, nome);
           rs=pst.executeQuery();
           
           while (rs.next()){
               e.setId(rs.getInt("id"));
               e.setNome(rs.getString("nome"));
               e.setStatus(rs.getInt("status"));
           }
           conexao.close();
       } catch (SQLException ex) {
           System.out.println("br.com.br.controler.ControlerEntregador.localizaEntregador()"+ex);
       }
       return e;
   }
   
    /**
     *  Atualiza status do Entregador
     *  @param e Objeto tipo Entregador
     *  @return Boolean Retorna TRUE ou FALSE.
     */
    public boolean atualizaStatusEntregador(Entregador e){
        String sql="UPDATE tbentregador SET status=? WHERE id=?";
        boolean resp=false;
        try {
            conexao = ConexaoBd.conector();
            pst=conexao.prepareStatement(sql);
            pst.setInt(1, e.getStatus());
            pst.setInt(2, e.getId());
            pst.executeUpdate();
            
            resp=true;
        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerDelivery.autualizaStatusEntregador()"+ex);
            
        }
        return resp;
    }
}
