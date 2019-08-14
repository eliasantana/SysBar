/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Localidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Elias Santana
 */
public class ControlerLodalidade {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Retorna todos os registros
     *
     * @return ReseultSet
     */
    public ResultSet listaLocalidade() {

        String sql = "SELECT localidade as 'Localidade', format(taxa,2,'de_DE') as 'Taxa R$' FROM tblocalidade;";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.listaLocalidade()" + e);
        }

        return rs;
    }

    /**
     * Retorn o id da lodalida informada
     *
     * @param l Localidade
     * @return id Id da lodalidade Informada
     */

    public String retornaIdLocalidade(Localidade l) {

        String sql = "SELECT id FROM tblocalidade where localidade=?;";
        String id = null;
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, l.getNome());
            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getString("id");
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.retornaIdLocalidade()" + e);
        }

        return id;
    }

    /**
     * Adiciona uma lodalidede
     * Data:14/08/2019
     * @param l Localidade
     * @return boolean
     */
    
    public boolean adicionaLocalidade(Localidade l) {

        boolean resp = false;
        
        String sql = "INSERT INTO `dbbar`.`tblocalidade`\n"
                + "(\n"
                + "`localidade`,\n"
                + "`taxa`)\n"
                + "VALUES(?,?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, l.getNome());
            pst.setDouble(2, l.getTaxa());
            pst.executeUpdate();      
            
            resp=true;
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.adicionaLocalidade()"+e);
        }
        
        return resp;
    }
    /**
     * Verifica se a localidade existe
     * Data:14/08/2019
     * @param l Localidade
     * @return boolean
     */
    
    public boolean temLocalidade(Localidade l) {

        boolean resp = false;
        
        String sql = "SELECT id FROM tblocalidade WHERE lower(localidade)=?;";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, l.getNome().toLowerCase());
            rs=pst.executeQuery();
            
            while (rs.next()){
                resp=true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.adicionaLocalidade()"+e);
        }
        
        return resp;
    }
    
      /**
     * Exclui uma localidade
     * Data:14/08/2019
     * @param id Id da localidade
     * @return Boolean Retorna TRUE OU FALSE
     */
    
     public boolean excluiLocalidade(String id){
         
         boolean resp=false;
         
         String sql="DELETE FROM tblocalidade WHERE id=?";
         
         try {
             pst=conexao.prepareStatement(sql);
             pst.setString(1, id);
             pst.executeUpdate();
             
             resp=true;
             
         } catch (SQLException e) {
             System.out.println("br.com.br.controler.ControlerLodalidade.excluiLocalidade()"+e);
         }
         
         return resp;
     }
     
     /**
      * Altera uma localidade
      * @param l Localidade
      * @return Boolean Retorna TRUE OU FALSE
      */
     public boolean alteraLocalidade(Localidade l){
         boolean resp=false;
         String sql="UPDATE tblocalidade SET localidade=?, taxa=? WHERE id=?";
         
         try {
             pst=conexao.prepareStatement(sql);
             pst.setString(1, l.getNome());
             pst.setDouble(2, l.getTaxa());
             pst.setInt(3, l.getId());
             
             pst.executeUpdate();
             resp=true;
             
         } catch (SQLException e) {
             System.out.println("br.com.br.controler.ControlerLodalidade.alteraLocalidade()"+e);
         }
         
         return  resp;
     }
}
