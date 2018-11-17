/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Ocorrencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;


public class ControlerOcorrencia {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    // Lista as ocorrencias dadastradas
    public void listaOcorrencias(JTable table){
        
       String sql="SELECT id as 'ID', descricao AS 'Descrição' FROM tbocorrencia";
       
        try {
            pst=conexao.prepareStatement(sql);
            rs=pst.executeQuery();
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao listarOcorrencias "+ e );
        }
    }
    
    
    public boolean salvarOcorrencia(Ocorrencia o){
        boolean resp = false;
        String sql="INSERT INTO tbocorrencia (descricao) VALUES (?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, o.getDescricao());
            pst.executeUpdate();
            resp=true;
            
        } catch (SQLException e) {
            System.out.println("Erro ao salvar Ocorrencia " + e );
        }
        
        return resp;
    }
    
    public boolean alterarOcorrencia(Ocorrencia o){
        boolean resp = false;
        
        String sql="UPDATE tbocorrencia SET descricao=? WHERE id=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, o.getDescricao());
            pst.setInt(2, o.getId());
            pst.executeUpdate();
            resp = true;
            
        } catch (SQLException e) {
            System.out.println("Erro ao altearOcorrencia " + e);
        }
        
        return resp;
    }
   
    
    // Exclui uma ocorrência
    public boolean excluiOcorrencia(Ocorrencia o){
        
        String sql="DELETE FROM tbocorrencia WHERE id=?";
        boolean resp=false;
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setInt(1, o.getId());
            pst.executeUpdate();
            resp=true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto");
        }
        return resp;
    }
    
    public ResultSet localizar(String texto){
        
        String sql="SELECT * FROM dbbar.tbocorrencia WHERE descricao LIKE ?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, '%' +texto);
            rs=pst.executeQuery();
                       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao localiar ocorrencia"+e);
        }
        
        return rs;
    }
}
