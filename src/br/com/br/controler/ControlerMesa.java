/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Funcionario;
import br.com.bar.model.Mesa;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class ControlerMesa {

    Connection conexao = ConexaoBd.conector();
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ResultSet listaMesa(String filtro, boolean tudo) {
        //Lista os mesas e os garçons responsáveis.

        if (tudo) {

            String sql = "SELECT dbbar.cadmesa.id as 'ID',\n"
                    + "	   dbbar.tbcadfuncionario.nome as 'GARÇOM RESPONSÁVEL',\n"
                    + "        dbbar.cadmesa.numero_mesa AS 'NÚMERO DA MESA',\n"
                    + "        dbbar.cadmesa.status AS 'STATUS'\n"
                    + "   FROM dbbar.cadmesa\n"
                    + "   INNER JOIN dbbar.tbcadfuncionario ON \n"
                    + "        dbbar.cadmesa.`tbCadFuncionario_id` = dbbar.tbcadfuncionario.id "
                    + "WHERE cadmesa.id > 0\n ORDER BY cadmesa.numero_mesa ASC";
            try {
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro listaMesa " + e);

            }

        } else {

            String sql = "SELECT dbbar.cadmesa.id as 'ID',\n"
                    + "	   dbbar.tbcadfuncionario.nome as 'GARÇOM RESPONSÁVEL',\n"
                    + "        dbbar.cadmesa.numero_mesa AS 'NÚMERO DA MESA',\n"
                    + "        dbbar.cadmesa.status AS 'STATUS'\n"
                    + "   FROM dbbar.cadmesa\n"
                    + "   INNER JOIN dbbar.tbcadfuncionario ON \n"
                    + "        dbbar.cadmesa.`tbCadFuncionario_id` = dbbar.tbcadfuncionario.id "
                    + "WHERE tbcadfuncionario.nome=?\n";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, filtro);
                rs = pst.executeQuery();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro listaMesa (Todas as mesas )" + e);

            }

        }

        return rs;
    }

    public boolean adicionaMesa(Mesa m) {

        String sql = "INSERT INTO cadmesa (numero_mesa,status,tbCadfuncionario_id) VALUES (?, ?, ?)";
        boolean resp = false;
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, m.getNumeroMesa());
            pst.setString(2, m.getStatus());
            pst.setString(3, m.getId_funcionario());

            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Mesa Cadastrada, selecione outra!");
        }
        return resp;

    }

    // Estemétodo exclui uma mesa selecionada
    public boolean excluiMesa(Mesa m) {
        boolean resp = false;
        String sql = "DELETE FROM cadmesa where id=?";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, m.getId());
            pst.executeUpdate();

            resp = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro excluirMesa");
        }
        return resp;

    }

    // Troca o garçom responsável de mesa
    public boolean trocaGarcom(Funcionario antigo, Funcionario novo) {

        String sql = "UPDATE cadmesa SET tbCadFuncionario_id=? where tbCadFuncionario_id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, novo.getId());
            pst.setString(2, antigo.getId());
            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro trocaGarcom() " + e);
        }

        return false;
    }

    public boolean alteraMesa(Mesa m) {

        String sql = "UPDATE cadmesa SET numero_mesa=?, status=?, tbCadFuncionario_id=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, m.getNumeroMesa());
            pst.setString(2, m.getStatus());
            pst.setString(3, m.getId_funcionario());
            pst.setString(4, m.getId());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro alteraMesa () " + e);
        }
        return false;
    }

    // Lista apenas o número das mesas livres
    public void listaMesaLivre(JComboBox combo, String idGarcom) {

        String sql = "SELECT numero_mesa FROM cadmesa WHERE status='0' AND tbCadFuncionario_id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idGarcom);
            rs = pst.executeQuery();

            combo.removeAllItems();

            while (rs.next()) {
                combo.addItem(rs.getString("numero_mesa"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro listaMesaLivre" + e);
        }
    }
    // Lista as mesas livres do garçom selecionado
    public ResultSet listaMesaLivre(String idGarcom) {

        String sql = "SELECT numero_mesa AS 'Mesas Disponíveis' FROM cadmesa WHERE status='0' AND tbCadFuncionario_id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idGarcom);
            rs = pst.executeQuery();     

            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro listaMesaLivre" + e);
        }
        return rs;
    }
    

    public String localizaIdMesa(String numeroMesa) {
        String id = null;
        String sql = "SELECT id FROM cadmesa WHERE numero_mesa=? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroMesa);
            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getString("id");
            }

        } catch (SQLException e) {

        }
        return id;
    }

    // altera o status da mesa para ocupado
    public boolean trocaStatusMesa(String numeroMesa, String status) {
        String sql = "UPDATE cadmesa SET status=? WHERE numero_mesa =?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, status);
            pst.setString(2, numeroMesa);

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro trocaStatusMesa" + e);
        }
        return false;
    }

    public ArrayList estatistica() {
        ArrayList<Double> estatisticas = new ArrayList<>();
        double mesaslivres = 0;

        String totalMesas = "SELECT count(id) as 'total' FROM dbbar.cadmesa";
        double total = 0;
        // Totaliza a quantidade de mesas cadastradas
        try {
            pst = conexao.prepareStatement(totalMesas);
            rs = pst.executeQuery();
            while (rs.next()) {
                total = Double.parseDouble(rs.getString("total"));

            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro estatisticas()" + e);
        }

        // Mesas ocupadas 
        String mesasOcupadas = "SELECT count(id) as 'ocupadas' FROM dbbar.cadmesa where status=1";
        double totalOcupadas = 0;

        try {
            pst = conexao.prepareStatement(mesasOcupadas);
            rs = pst.executeQuery();

            while (rs.next()) {
                totalOcupadas = Double.parseDouble(rs.getString("ocupadas"));
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erro mesasOcupadas" + e);
        }

        mesaslivres = total - totalOcupadas;

        double percentualLivre;
        double percentualOcupadas;

        percentualLivre = (mesaslivres / total) * 100;
        percentualOcupadas = (totalOcupadas / total) * 100;

        estatisticas.add(percentualLivre);
        estatisticas.add(percentualOcupadas);
        estatisticas.add(total - totalOcupadas);
        estatisticas.add(totalOcupadas);

        return estatisticas;
    }

    public void listaMesasOcupadas(JComboBox combo) {

        String sql = "SELECT numero_mesa FROM dbbar.cadmesa where status > 0";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            combo.removeAll();
            while (rs.next()) {
                combo.addItem(rs.getString("numero_mesa"));
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerMesa.listaMesasOcupadas()");
        }

    }

    // Localiza o numero da mesa recebendo como parâmetro o número do pedido
    public String localizaNumeroMesa(String numeroPedido) {
        
        String numeroMEsa = "";
       
        String sql = "SELECT       \n"
                + "     cadmesa.`numero_mesa` AS mesa\n"
                + "FROM\n"
                + "     `cadmesa` cadmesa INNER JOIN `cadpedido` cadpedido ON cadmesa.`id` = cadpedido.`cadmesa_id`\n"
                + "WHERE id_pedido = ?;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroPedido);
            rs = pst.executeQuery();

            while (rs.next()) {
                numeroMEsa = rs.getString("mesa");
            }

             } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerMesa.localizaNumeroMesa()");
        }

        return numeroMEsa;
    }

}
