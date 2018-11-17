/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Ponto;
import br.com.bar.util.Util;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class ControlerPonto {

    Connection conexao = ConexaoBd.conector();
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void listaPonto(JTable tablela) {

        String sql = "SELECT \n"
                + "	p.id as 'Código', \n"
                + "	p.tbcadfuncionario_id as 'C.Func',\n"
                + "    f.nome as 'Funcionario',\n"
                + "    date_format(p.data,'%d/%m/%Y') as Data,  \n"
                + "    p.entrada as 'Entrada', \n"
                + "    p.saida as 'Saída', \n"
                + "    p.ent_inter as 'Entrada Invervalo', \n"
                + "    p.sai_inter as 'Saída Intervalo', \n"
                + "    p.h_trab as 'Hotas Trabalhadas', \n"
                + "    p.h_extra as 'Hora Extra', \n"
                + "    p.homologado as 'Homologado', \n"
                + "    o.descricao as 'Descrição'\n"
                + "    \n"
                + "FROM dbbar.tbponto p\n"
                + "INNER JOIN tbcadfuncionario f on f.id = p.tbcadfuncionario_id\n"
                + "INNER JOIN tbocorrencia o on o.id = p.tbocorrencia_id;";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            tablela.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPonto.listaPonto()" + e);
        }
    }

    public void listaPonto(JTable tablela, String funcionario) {

        String sql = "SELECT \n"
                + "	p.id as 'Código', \n"
                + "	p.tbcadfuncionario_id as 'C.Func',\n"
                + "    f.nome as 'Funcionario',\n"
                + "    date_format(p.data,'%d/%m/%Y') as Data,  \n"
                + "    p.entrada as 'Entrada', \n"
                + "    p.saida as 'Saída', \n"
                + "    p.ent_inter as 'Entrada Invervalo', \n"
                + "    p.sai_inter as 'Saída Intervalo', \n"
                + "    p.h_trab as 'Hotas Trabalhadas', \n"
                + "    p.h_extra as 'Hora Extra', \n"
                + "    p.homologado as 'Homologado', \n"
                + "    o.descricao as 'Descrição'\n"
                + "    \n"
                + "FROM dbbar.tbponto p\n"
                + "INNER JOIN tbcadfuncionario f on f.id = p.tbcadfuncionario_id\n"
                + "INNER JOIN tbocorrencia o on o.id = p.tbocorrencia_id\n"
                + "WHERE f.nome = ?;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, funcionario);
            rs = pst.executeQuery();

            tablela.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPonto.listaPonto()" + e);
        }
    }

    public ResultSet listaPontoPeriodo(JComboBox combo, JDateChooser dtInicio, JDateChooser dtFim) {
        Util u = new Util();
        
        
        // Captura o nome do funcionário no combobox
        String funcionario = combo.getSelectedItem().toString();
        
        // Recebe a data inicial selecionada no combobox
        String dataInicial = u.formataDataBanco(dtInicio.getDate());
        
        // Recebe a data final do combobox
        String dataFinal = u.formataDataBanco(dtFim.getDate());
       
        
        String sql = "SELECT \n"
                + "p.id as 'Código',\n"
                + "p.tbcadfuncionario_id as 'C.Func',\n"
                + "f.nome as 'Funcionario',\n"
                + "date_format(p.data,'%d/%m/%Y') as Data,  \n"
                + "p.entrada as 'Entrada', \n"
                + "p.saida as 'Saída',\n"
                + "p.ent_inter as 'Entrada Invervalo', \n"
                + "p.sai_inter as 'Saída Intervalo',\n"
                + "p.h_trab as 'Hotas Trabalhadas', \n"
                + "p.h_extra as 'Hora Extra', \n"
                + "p.homologado as 'Homologado', \n"
                + "o.descricao as 'Descrição'\n"
                + "\n"
                + "FROM dbbar.tbponto p\n"
                + "INNER JOIN tbcadfuncionario f on f.id = p.tbcadfuncionario_id\n"
                + "INNER JOIN tbocorrencia o on o.id = p.tbocorrencia_id\n"
                + "WHERE f.nome = ? AND data between ? and ?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,funcionario);
            pst.setString(2,dataInicial);
            pst.setString(3,dataFinal);
            
            rs=pst.executeQuery();
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPonto.listaPontoPeriodo()" + e);
            
        }
        return rs;
    }

    // Ete método retorna o cálculo da hora trabalhada no dia e funcionário informado informado 
    public String calculoHoraTrab(Ponto p) {
        String total_hora = null;
        String sql = "SELECT  tbcadfuncionario_id, \n"
                + "data, \n"
                + "entrada, \n"
                + "saida,\n"
                + "ent_inter, \n"
                + "sai_inter,"
                + "timediff(timediff(saida,entrada),timediff(sai_inter,ent_inter)) as 'HORA_TRABALHADA'\n"
                + "FROM dbbar.tbponto \n"
                + "WHERE tbcadfuncionario_id=? AND data=?;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, p.getTbcadfuncionario_id());
            pst.setString(2, p.getData());

            rs = pst.executeQuery();

            while (rs.next()) {
                total_hora = rs.getString("HORA_TRABALHADA");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao calcularHoraTrab" + e);
        }

        return total_hora;
    }

    //Homologa ponto do funcionário na data informada - homologação individual baseadano dia; 
    public void registraHoraTrab(Ponto p) {

        String sql = "UPDATE tbponto SET h_trab=? WHERE tbcadfuncionario_id=? AND data=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getH_trab());
            pst.setInt(2, p.getTbcadfuncionario_id());
            pst.setString(3, p.getData());

            pst.executeUpdate();

            System.out.println("Hora trabalhada registrada com sucesso!");
            JOptionPane.showMessageDialog(null, "Hora trabalhada registrada com sucesso!");

        } catch (SQLException e) {

            System.out.println("br.com.ponto.projBar.controler.ControlerPonto.homologaPonto()" + e);

        }

    }

    // Calcula horas extras para um determinado funcionario
    public String calculaExtra(Ponto p) {
        String horaExtra = null;
        String sql = "SELECT  tbcadfuncionario_id, data, entrada, saida, ent_inter, sai_inter, h_trab, timediff(h_trab,'08:00:00') as 'extra', homologado, tbocorrencia_id, id \n"
                + "FROM dbbar.tbponto \n"
                + "WHERE h_trab > '00:00:00' and tbcadfuncionario_id=? AND data=?;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, p.getTbcadfuncionario_id());
            pst.setString(2, p.getData());
            rs = pst.executeQuery();

            while (rs.next()) {
                horaExtra = rs.getString("extra");
            }
        } catch (SQLException e) {
            //System.out.println("br.com.ponto.projBar.controler.ControlerPonto.calculaExtra()" + e);
            System.out.println("Não possui horas Extras a calcular");
        }

        return horaExtra;

    }

    public void registraHoraExtra(Ponto p) {
        String updateExtra = "UPDATE tbponto SET h_extra=?,  homologado=? WHERE tbcadfuncionario_id=? AND data=?";

        try {

            pst = conexao.prepareStatement(updateExtra);
            pst.setString(1, p.getH_extra());
            pst.setInt(2, p.getHomologado());
            pst.setInt(3, p.getTbcadfuncionario_id());
            pst.setString(4, p.getData());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao gravar hora extra" + e);
        }
    }
}
