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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author elias
 */
public class ControlerCozinha {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ResultSet listaProdutosCozinha() {

        String sql = "SELECT id AS 'ORDEM', "
                + "produto AS 'PRODUTO',"
                + "qtd AS 'QTD',"
                + "funcionario AS 'FUNCIONÁRIO', "
                + "mesa AS 'MESA', "
                + "status AS 'SITUAÇÃO' "
                + "FROM dbbar.tbcozinha WHERE status='Pendente'";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro listaProdutoCozinha()" + e);
        }

        return rs;
    }

    public ArrayList estatisticas(ResultSet rs) {
        int pendente = 0;
        int liberados = 0;
        String status = null;

        ArrayList<Integer> resultado;
        resultado = new ArrayList<>();

        try {
            while (rs.next()) {

                status = rs.getString("status");
                if (status.equals("Pendente")) {
                    pendente = pendente + 1;
                } else {
                    liberados = liberados + 1;
                }
            }
            resultado.add(pendente);
            resultado.add(liberados);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro estatiscicas()" + e);
        }
        return resultado;
    }

    public boolean liberaProduto(String id) {

        String sql = "UPDATE tbcozinha SET status='Liberado' WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "erro liberaProduto()" + e);
        }

        return false;
    }
    
    // Retorna os pedidos pendentes do operador informado no parâmetro do método
     public ResultSet statusCozinha(String operador ){
        
         String sql="SELECT "
                 + "produto as 'PRODUTO', "
                 + "qtd as 'QTD', "
                 + "mesa as 'N. MESA', "
                 + "status as 'STATUS'"
                 + "FROM "
                 + "dbbar.tbcozinha where funcionario=? and status='pendente';";
         
         try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, operador);
            rs=pst.executeQuery();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erroStatusCozinha"+e);
        }
         
         return rs;
    }
     
     public int pratoPendente(){
         
         int qtd=0;
         String sql="SELECT * FROM dbbar.tbcozinha where status='pendente'";
         
         try {
            pst=conexao.prepareStatement(sql);
            rs=pst.executeQuery();
                        
            while (rs.next()){
                qtd = qtd+1;
            }
             
         } catch (SQLException e) {
             System.out.println("br.com.br.controler.ControlerCozinha.pratoPendente()");
         }
         
         return  qtd;
     }
}
