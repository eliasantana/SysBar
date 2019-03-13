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

    /**
     * Lista todos os Logs registrados no período informado, paginados conforme o número de registros
     * informado no parâmetro limit.
     * @param tabela Tabela onde será exibido o resultado da Query
     * @param dataInicio Data de Início da pesquisa.
     * @param dataFim Data Final da pesquisa.
     * @param filtro Usuário a ser pesquisado.
     * @param limit Limite de Resgistros por página.
     * @param offset Pagina a ser exibida com os próximos registros.
     * 
     */
    public void listaLog(JTable tabela, String dataInicio, String dataFim, String filtro, int limit, int offset) {

        String sql = "SELECT \n"               
                + "date_format(data,'%d/%m/%Y')  as 'DATA', \n"
                + "TIME_FORMAT(hora,'%H:%i') as 'HORA', \n"                
                + "funcionalidade as 'FUNCIONALIDADE', \n"
                + "descricao  as 'AÇÃO'\n"
                + "FROM dbbar.tb_log where usuario=? and data between ? and ? ORDER BY data DESC, HORA DESC limit ? offset ? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, filtro);
            pst.setString(2, dataInicio);
            pst.setString(3, dataFim);
            pst.setInt(4, limit);
            pst.setInt(5, offset);

            rs = pst.executeQuery();
            // Avalia retorno do Objeto ResultSet carregando a tabela e habilitando
            // os objetos necessarios.

            tabela.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.listaLog()" + e);
        }

    }

    public boolean expurgo() {
        // Retorna o menor ID da lista de data anterior a 3 meses
        String sql = "select MIN(id) as 'id' from tb_log where data < (SELECT DATE_FORMAT(adddate(now(), INTERVAL -3 MONTH ),'%Y-%m-%d')); ";
        String menorId = null;
        boolean resp = false;
        try {

            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                menorId = rs.getString("id");

            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.expurgo()" + e);

        }
        // Exclui todos os registros a partir do menor ID identificado

        String sqlDelete = "DELETE FROM tb_log WHERE ID >=" + menorId;
        try {
            pst = conexao.prepareStatement(sqlDelete);
            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.expurgo() - sqlDelete" + e);
        }

        return resp;
    }
    /**
     * Retorna a totalização dos registros localizados no intevalo de datas informado.
     * @param dataInicio Data Inicial.
     * @param dataFim Data Final.
     * @param filtro Nome do funcionário.
     */
    public int totalizaLog(String dataInicio, String dataFim, String filtro) {
        int reg = 0;

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

            while (rs.next()) {
                reg = reg + 1;
            }
            // Avalia retorno do Objeto ResultSet carregando a tabela e habilitando
            // os objetos necessarios.

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLog.listaLog()" + e);
        }

        return reg;
    }
}
