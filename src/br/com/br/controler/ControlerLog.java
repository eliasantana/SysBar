/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class ControlerLog {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ResultSet listaLog(JTable tabela) {

        String sql = "SELECT \n"
                + "id as 'ID', \n"
                + "date_format(data,'%d/%m/%Y')  as 'DATA', \n"
                + "hora as 'HORA', \n"
                + "usuario as 'USUÁRIO', \n"
                + "funcionalidade as 'FUNCIONALIDADE', \n"
                + "descricao  as 'DESCRIÇÃO'\n"
                + "FROM dbbar.tb_log";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.listaLog()" + e);
        }

        return rs;
    }

    public ResultSet listaLog(JTable tabela, String filtro) {

        String sql = "SELECT \n"
                + "id as 'ID', \n"
                + "date_format(data,'%d/%m/%Y')  as 'DATA', \n"
                + "hora as 'HORA', \n"
                + "usuario as 'USUÁRIO', \n"
                + "funcionalidade as 'FUNCIONALIDADE', \n"
                + "descricao  as 'DESCRIÇÃO'\n"
                + "FROM dbbar.tb_log\n"
                + "WHERE usuario=?;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, filtro);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.listaLog()" + e);
        }

        return rs;
    }
    // Lista os registros de log no período especificado retornando um ResultSet
   

    public ResultSet listaLog(JTable tabela, String dataInicio, String dataFim, String filtro) {

        String sql = "SELECT \n"
                + "id as 'CÓDIGO', \n"
                + "date_format(data,'%d/%m/%Y')  as 'DATA', \n"
                + "hora as 'HORA', \n"
                + "usuario as 'USUÁRIO', \n"
                + "funcionalidade as 'FUNCIONALIDADE', \n"
                + "descricao  as 'DESCRIÇÃO'\n"
                + "FROM dbbar.tb_log where usuario=? and data between ? and ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, filtro);
            pst.setString(2, dataInicio);
            pst.setString(3, dataFim);

            rs = pst.executeQuery();
            // Avalia e retorna uma Mensagem conforme o resultado da pesquisa.
            if (rs.next()) {

            } else {
                JOptionPane.showMessageDialog(null, "Não há nenhum registro encontrado para o período e usuário informados!");
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.listaLog()" + e);
        }

        return rs;
    }

    // Lista os registros de Log no período especificado retornando um Boolean
    // mudando o estado do objeto btn passado no parâmetro conforme resultado da pesquisa.
    public void listaLog(JTable tabela, String dataInicio, String dataFim, String filtro, JLabel btn) {
       
        String sql = "SELECT \n"
                + "id as 'CÓDIGO', \n"
                + "date_format(data,'%d/%m/%Y')  as 'DATA', \n"
                + "hora as 'HORA', \n"
                + "usuario as 'USUÁRIO', \n"
                + "funcionalidade as 'FUNCIONALIDADE', \n"
                + "descricao  as 'DESCRIÇÃO'\n"
                + "FROM dbbar.tb_log where usuario=? and data between ? and ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, filtro);
            pst.setString(2, dataInicio);
            pst.setString(3, dataFim);

            rs = pst.executeQuery();
            // Avalia retorno do Objeto ResultSet carregando a tabela e habilitando
            // os objetos necessarios.
            if (rs.next()) {
               
                btn.setEnabled(true);
                tabela.setModel(DbUtils.resultSetToTableModel(rs));
            } else {
                tabela.setModel(DbUtils.resultSetToTableModel(rs));
                JOptionPane.showMessageDialog(null, "Não há nenhum registro encontrado para o período e usuário informados!");
                 btn.setEnabled(false);
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.listaLog()" + e);
        }

       
    }
    
    public boolean expurgo(){
        // Retorna o menor ID da lista de data anterior a 3 meses
        String sql="select MIN(id) as 'id' from tb_log where data < (SELECT DATE_FORMAT(adddate(now(), INTERVAL -3 MONTH ),'%Y-%m-%d')); ";
        String menorId=null;
        boolean resp=false;
        try {
            
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                menorId=rs.getString("id");
                
            }
            
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.expurgo()"+e);
            
        }
        // Exclui todos os registros a partir do menor ID identificado
       
        String sqlDelete="DELETE FROM tb_log WHERE ID >="+menorId;
        try {
            pst=conexao.prepareStatement(sqlDelete);
            pst.executeUpdate();
            resp=true;
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.expurgo() - sqlDelete" + e);
        }
        
        return resp;
    }

}
