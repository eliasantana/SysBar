/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.model.Grupo;
import br.com.bar.dao.ConexaoBd;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author elias
 */

public class ControlerGrupo {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Adiciona um grupo a tabela grupo
    public boolean adicionarGrupo(Grupo g) {

        String sql = "INSERT INTO tbgrupo (grupo) VALUE (?)";

        try {
            pst =conexao.prepareStatement(sql);
            pst.setString(1, g.getNomeGrupo());
            pst.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro adicionarGrupo"+e);
            
        }
        return false;
    }
    
    // Exclui um grupo
    public boolean excluirGrupo(Grupo g){
        
        String sql="DELETE FROM tbgrupo where id=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, g.getId());
            pst.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro excluirGrupo"+e);
        }
        return false;
    }
    
    //altera um grupo
            
    public boolean alteraGrupo(Grupo g){
        
        String sql="UPDATE tbgrupo SET grupo=? WHERE id=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, g.getNomeGrupo());
            pst.setString(2, g.getId());
            pst.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro alteraGrupo"+e);
        }
        return false;
    }

    public ResultSet atualizaTabela(JTable tabela){
        
        String sql="SELECT * FROM tbgrupo";
        
        try {
            pst=conexao.prepareStatement(sql);
            rs=pst.executeQuery();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro atualizaTabela"+e);
            
        }
        return rs;
    }
    
    // Grupo Produto
    
    public ResultSet atualizaGrupoProduto(JTable tabela){
        
        //String sql="SELECT * FROM cad_grupo_produto";
        String sql="SELECT id AS 'ID', nome AS 'NOME' FROM cad_grupo_produto";
        
        try {
            pst=conexao.prepareStatement(sql);
            rs=pst.executeQuery();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro atualizaGrupoProduto"+e);
            
        }
        return rs;
    }
    
     public boolean adicionarGrupoProduto(Grupo gp) {

        String sql = "INSERT INTO cad_grupo_produto (nome) VALUE (?)";

        try {
            pst =conexao.prepareStatement(sql);
            pst.setString(1, gp.getNomeGrupo());
            pst.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro adicionarGrupoPrpduto"+e);
            
        }
        return false;
    }
     public boolean excluirGrupoProduto(Grupo g){
        
        String sql="DELETE FROM cad_grupo_produto where id=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, g.getId());
            pst.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro excluirGrupo"+e);
        }
        return false;
    }
      public boolean alteraGrupoProduto(Grupo g){
        
        String sql="UPDATE cad_grupo_produto SET nome=? WHERE id=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, g.getNomeGrupo());
            pst.setString(2, g.getId());
            pst.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro alteraGrupo"+e);
        }
        return false;
    }
      // Carrega o combo grupo produto
      public void carregaComboGrupoProduto(JComboBox combo){
          
          String sql="SELECT nome FROM cad_grupo_produto";
          
          try {
              pst=conexao.prepareStatement(sql);
              rs =pst.executeQuery();
              
              combo.removeAllItems();
              
              while (rs.next()){
                  combo.addItem(rs.getString("nome"));
              }
              
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Erro CarregaComboGrupoProduto "+e);
          }
      }
      
      
      // Retorna o id dogrupo escolhido
      public String localizaIdGrupoProduto(JComboBox combo){
          
          String sql="SELECT id FROM cad_grupo_produto where nome=?";
          String id=null;
          try {
              pst=conexao.prepareStatement(sql);
              pst.setString(1, combo.getSelectedItem().toString());
              rs=pst.executeQuery();
              
              while (rs.next()){
                  id=rs.getString("id");
              }
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Erro localizaIdGrupoProduto "+e);
          }
          
          return id;
      }
      //Sobregarcar de método
      public String localizaIdGrupoProduto(String nomeGrupo){
          
          String sql="SELECT id FROM cad_grupo_produto where nome=?";
          String id=null;
          try {
              pst=conexao.prepareStatement(sql);
              pst.setString(1, nomeGrupo);
              rs=pst.executeQuery();
              
              while (rs.next()){
                  id=rs.getString("id");
              }
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Erro localizaIdGrupoProduto "+e);
          }
          
          return id;
      }
      
      // Retorna o id dogrupo escolhido
      public String localizaIdGrupo(JComboBox combo){
          
          String sql="SELECT id FROM tbgrupo where grupo=?";
          String id=null;
          try {
              pst=conexao.prepareStatement(sql);
              pst.setString(1, combo.getSelectedItem().toString());
              rs=pst.executeQuery();
              
              while (rs.next()){
                  id=rs.getString("id");
              }
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Erro localizaIdGrupoProduto "+e);
          }
          
          return id;
      }
      
      public ResultSet listaGrupoProduto(){
          
          String sql="SELECT id,nome FROM cad_grupo_produto";
          
          try {
              pst=conexao.prepareStatement(sql);
              rs =pst.executeQuery();
                           
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Erro CarregaComboGrupoProduto "+e);
          } 
          
          return rs;
      }
}
