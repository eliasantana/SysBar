/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class ControlerFornecedor {
    
    Fornecedor f = new Fornecedor();
    PreparedStatement pst = null;
    Connection conexao = ConexaoBd.conector();
    ResultSet rs = null;
    
    
    public void cadastraFornecedor(Fornecedor f){
        // Cadastra um fornecedor no banco de dados
        String sql="INSERT INTO tbfornecedores (nome, telefone, email) VALUES (?,?,?)";
        
        try {
            
            
            if (f.getNome().isEmpty()){
                JOptionPane.showMessageDialog(null, "Dados incorretos! \n Preencha todos os dados para continuar");
            }else {
                
                pst = conexao.prepareStatement(sql);

                pst.setString(1, f.getNome());
                pst.setString(2, f.getTelefone());
                pst.setString(3, f.getEmail());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso");
            }
            
        
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.cadastraFornecedor()" + e.getMessage());
            
        }
        
    }
    
    public ResultSet listaFornecedor(){
        // Lista todos os fornecedores
        String sql="SELECT * FROM  tbfornecedores";
        
        try {
            pst=conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
        } catch (SQLException e) {
            
            System.out.println("br.com.br.controler.ControlerFornecedor.listaFornecedor()" + e.getMessage());
        }
        
        return rs;
    }
    
   
    
    public void excluirFornecedor (Fornecedor f){
        //Exclui fornecedor
        String sql="DELETE FROM tbfornecedores WHERE id=?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setInt(1, f.getCodigo());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fornecedor Excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.excluirFornecedor()" + e);
            JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor!");
        }
    }
    
    public void alteraFornecedor(Fornecedor f){
        // Altera dados do fornecedor
        String sql="UPDATE tbfornecedores SET nome=?, telefone=?, email=? WHERE id=?";
        try {
            pst=conexao.prepareStatement(sql);
            
            pst.setString(1, f.getNome());
            pst.setString(2, f.getTelefone());
            pst.setString(3, f.getEmail());
            pst.setInt(4, f.getCodigo());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar fornecedor!" + e );
        }
        
    }
    
    public void carregaComboFornecedor(JComboBox combo){
        // Lista o nome dos fornecedores em ordem alfabética
        String sql="SELECT nome FROM tbFornecedores ORDER BY nome ASC";
        
        try {
            pst=conexao.prepareStatement(sql);
            rs=pst.executeQuery();
            
            while(rs.next()){
                combo.addItem(rs.getString("nome"));
            }
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.comboFornecedor()");
        }
        
        
    }
    
    // Este método localiza um fornecedor
    public String localizaForecedor(Fornecedor f){
        
        String sql="SELECT * FROM tbfornecedores WHERE nome=?";
        String idFor=null;
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, f.getNome());
            rs=pst.executeQuery();
            // Se locakuzadi retirbará o id
            while (rs.next()){
                
                idFor = rs.getString("id");
            }
          
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.localizaForecedor()");
        }
        
        return idFor;
    }
}
