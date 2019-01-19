/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.MovimentacaoCaixa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class ControlerCaixa {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    // Lista Mesas Ocupadas
    public void listaMesaOcupada(JComboBox combo) {

        String sql = "SELECT numero_mesa FROM cadmesa WHERE status='1'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            combo.removeAllItems();
            combo.addItem("Selecione...");
            while (rs.next()) {
                combo.addItem(rs.getString("numero_mesa"));
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.listaMesaOcupada()"+e);
        }
    }

    public double totalizaSaida() {

        String sql = "SELECT sum(valor_pagto) as 'saidas'FROM dbbar.tbcontas_a_pagar where data_pagto=curdate()";
        double totalSaidas = 0;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("saidas") == null) {
                    totalSaidas = 0.0;
                } else {

                    totalSaidas = Double.parseDouble(rs.getString("saidas"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro totalizaSaída()" + e);

        }
        return totalSaidas;
    }

    // Totaliza saídas por operador
    public double totalizaSaida(String operador) {

        String sql = "SELECT sum(valor_pagto) as 'saidas'FROM dbbar.tbcontas_a_pagar where data_pagto=curdate() AND operador=?";
        double totalSaidas = 0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, operador);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("saidas") == null) {
                    totalSaidas = 0.0;
                } else {

                    totalSaidas = Double.parseDouble(rs.getString("saidas"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro totalizaSaída()" + e);

        }
        return totalSaidas;
    }

    public double totalizaEntradas() {
        String sql = "SELECT sum(total) as 'total' FROM dbbar.cadpedido where data = curdate()";
        //String sql = "SELECT sum(total) as 'total' FROM dbbar.detalhe_mesa where data=curdate()";

        double total = 0;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("total") == null) {
                    total = 0.0;
                } else {

                    total = Double.parseDouble(rs.getString("total"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro totalizaEntradas()" + e);

        }
        return total;
    }

    /*
        Totaliza entradas por operador
     */
    public double totalizaEntradas(String operador) {
        String sql = "SELECT sum(total) as 'total' FROM dbbar.cadpedido where data = curdate() AND operador=?";
        
        double total = 0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, operador);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("total") == null) {
                    total = 0.0;
                } else {

                    total = Double.parseDouble(rs.getString("total"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro totalizaEntradas()" + e);

        }
        return total;
    }

    // Grava movimentação 
    public boolean gravaMovimentacao(MovimentacaoCaixa m) {
        boolean resp = false;
        String sql = "INSERT INTO tbmovimentacao (data, entradas, saidas, saldo, status, tbcadfuncionario_id) VALUES (?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, m.getData());
            pst.setDouble(2, m.getEntrada());
            pst.setDouble(3, m.getSaida());
            pst.setDouble(4, m.getSaldo());
            pst.setInt(5, m.getStatus());
            pst.setInt(6, m.getIdFuncionario());

            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("Erro ao gravarMovimentação()");
        }

        return resp;
    }
    // Checa se existe movimentação

    public boolean temMovimentacao(int idOperador) {
        boolean resp = false;

        String sql = "SELECT * FROM tbmovimentacao WHERE data=curdate() AND tbcadfuncionario_id = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idOperador);
            rs = pst.executeQuery();

            while (rs.next()) {
                resp = true;
            }
        } catch (SQLException e) {

            System.out.println("br.com.br.controler.ControlerCaixa.temMovimentacao()" + e);
        }

        return resp;
    }
    // Lista todos os caixas fechados

    public ResultSet listaCaixa() {

        String sql = "SELECT m.id AS 'ID', m.data AS 'DATA', format(m.saldo,2,'de_DE') AS 'SALDO', f.nome AS 'OPERADOR' FROM dbbar.tbmovimentacao m\n"
                + "INNER JOIN tbcadfuncionario f on f.id = m.tbcadfuncionario_id\n"
                + "WHERE data=curdate();";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.listaCaixa()" + e);
        }

        return rs;
    }

    public boolean liberaCaixa(String id) {
        boolean resp = false;
        String sql = "DELETE FROM tbmovimentacao WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.liberaCaixa()" + e);
        }
        return resp;
    }

    // Lista a movimentação do período e retorna um ResultSet
    public ResultSet listaMovimentacao(String mes) {

        String sql = "SELECT id, date_format(data,'%d/%m') as 'data', sum(entradas) as 'entradas', sum(saidas) as 'saidas', sum(saldo) as 'saldo' \n"
                + "FROM dbbar.tbmovimentacao\n"
                + "WHERE month(data)=?\n"
                + "GROUP BY data";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, mes);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("Erro listaMovimentacao" + e);
        }

        return rs;
    }

    // Este método lista a totalização de vendas de todos os gaçoms no período
    public ResultSet rakingDeVendas(String dataInicial, String dataFinal) {

        String sql = "SELECT f.nome, sum(dt.total) as 'total' FROM dbbar.detalhe_mesa dt\n"
                + "INNER JOIN tbcadfuncionario f on f.id = dt.cadpedido_tbcadfuncionario_id\n"
                + "WHERE data between ? AND ?\n"
                + "GROUP BY f.nome;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataInicial);
            pst.setString(2, dataFinal);
            rs = pst.executeQuery();

        } catch (SQLException e) {

            System.out.println("Erro ao gerarRanking de vendas");
        }

        return rs;
    }
    
    public void statusCaixa(JLabel label, boolean status,JLabel msg){
        
        if (status){
            label.setIcon(new ImageIcon(getClass().getResource("/br/com/bar/imagens/btnCancel.png")));           
            msg.setText("Caixa Fechado");
        }else {
            label.setIcon(new ImageIcon(getClass().getResource("/br/com/bar/imagens/btnOk.png")));            
            msg.setText("Caixa Aberto");
        }
        
    }

}
