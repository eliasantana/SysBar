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


/**
 *
 * @author elias
 */
public class ControlerCozinha {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ResultSet listaProdutosCozinha() {

        String sql = "SELECT id AS 'SEQUÊNCIA', "
                + "produto AS 'PRODUTO',"
                + "qtd AS 'QTD',"
                + "funcionario AS 'GARÇOM', "
                + "mesa AS 'MESA', "
                + "status AS 'STATUS' "
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

    // Retorna a lista dos produtos enviados para a cozinha pelo operador garçom
    
    public ResultSet statusCozinha(String operador) {
        
        // Listas os pratos enviados a cozinha pelo garçom
        String sql = "SELECT\n"
                + "npedido as 'N.PEDIDO',\n"
                + "produto as 'PRATO', \n"
                + "qtd as 'QTD', \n"
                + "mesa as 'N. MESA', \n"
                + "status as 'STATUS'\n"                
                + "FROM \n"
                + "dbbar.tbcozinha where funcionario=? and data=curdate()\n"
                + "ORDER BY id asc;";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, operador);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erroStatusCozinha" + e);
        }

        return rs;
    }

    public int pratoPendente() {

        int qtd = 0;
        String sql = "SELECT * FROM dbbar.tbcozinha where status='pendente'";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                qtd = qtd + 1;
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCozinha.pratoPendente()");
        }

        return qtd;
    }
    // Remove produto da tabela cozinha 
    // Recurso utilizado quando o produto for descartado
    public boolean removePrato(String idProduto){
        boolean resp=false;
        
        String sql="DELETE FROM tbcozinha where id=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idProduto);
            pst.executeUpdate();
            resp=true;
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCozinha.removePrato()" + e);
        }
        
        return resp;
    }
}
