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
import javax.swing.JTable;

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

    public ResultSet listaLog(JTable tabela, String dataInicio, String dataFim, String filtro) {

        String sql = "SELECT \n"
                + "id as 'ID', \n"
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

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.listaLog()" + e);
        }

        return rs;
    }

}
