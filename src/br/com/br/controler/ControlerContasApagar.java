/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.dao.Log;
import br.com.bar.model.Contas;
import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author elias
 */
public class ControlerContasApagar {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Lista todas as contas cadastradas
    public ResultSet listaContasApagar(String opcao) {

        String filtro = "";
        String ordenar="";

        if ("Pagas".equals(opcao)) {
            filtro = "ca.data_pagto IS NOT NULL";
            ordenar = "ca.data_pagto DESC";
        } else {
            filtro = "ca.data_pagto IS NULL";
            ordenar = "ca.data_vencito";
        } 

        String sql = "SELECT \n"
                + "	ca.id as 'CÓD. INT.', \n"
                + "	ca.descricao as 'DESCRIÇÃO', \n"
                + "	format(ca.valor,2,'de_DE') as 'VALOR R$',  \n"
                + "	date_format(ca.data_vencito,'%d/%m/%Y') AS 'VENCIMENTO', \n"
                + "	date_format(ca.data_pagto,'%d/%m/%Y') AS 'PAGAMENTO', \n"
                + "	format(ca.valor_pagto,2,'de_DE') AS 'VLR PAGO R$', \n"
                + "	f.nome AS 'OPERADOR', \n"
                + "	gp.grupo AS 'GRUPO'\n"
                + "FROM tbcontas_a_pagar ca\n"
                + "INNER JOIN tbcadfuncionario f on f.id=ca.tbcadfuncionario_id\n"
                + "INNER JOIN tbgrupo gp on gp.id=ca.tbGrupo_id "
               // + "WHERE " + filtro + " ORDER BY ca.data_vencito DESC";
                + "WHERE " + filtro + " ORDER BY " +ordenar;
                
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerContasApagar.listaContasApagar()" + e);
        }
        return rs;
    }

    public ResultSet listaContasPorCategoria() {

        String sql = "SELECT c.Descricao, sum(c.valor_pagto) as 'valor', g.grupo FROM dbbar.tbcontas_a_pagar c\n"
                + "INNER JOIN tbgrupo g on g.id=c.tbGrupo_id\n"
                + "GROUP BY g.grupo;";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("Erro ao listarContasPorCategoria" + e);
        }

        return rs;

    }

    // Lista grupos cadastrados em ordem alfabética
    public ResultSet listaGrupos(JComboBox combo) {

        String sql = "SELECT grupo FROM dbbar.tbgrupo ORDER BY grupo ASC";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            combo.removeAllItems();
            combo.addItem("Selecione...");
            while (rs.next()) {
                combo.addItem(rs.getString("grupo"));
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerContasApagar.listaGrupos()" + e);
        }
        return rs;
    }

    public String myData(JDateChooser comboData) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String data = "";
        try {
            data = df.format(comboData.getCalendar().getTime());
        } catch (Exception e) {

        }
        return data;
    }

    public boolean adicionaConta(Contas c) {
        // Agenda uma conta no banco
        String sql = "INSERT INTO tbcontas_a_pagar (Descricao, valor, data_vencito, tbcadfuncionario_id, tbGrupo_id) VALUES (?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, c.getDescricao());
            pst.setString(2, c.getValor());
            pst.setString(3, c.getDataVencto());
            pst.setString(4, c.getFuncionarioId());
            pst.setString(5, c.getGrupoId());

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerContasApagar.adicionaConta()" + e);
        }
        return false;
    }

    // Registra Pagamento da Conta
    /**
     *  Realiza o pagamento da conta atualizado a tabela conta a pagar.
     *  @param c Objeto do tipo Conta
     *  @return Retorna um Boolean como resultado da alteração.
     */
    public boolean baixarConta(Contas c) {

        String sql = "UPDATE tbcontas_a_pagar SET data_pagto=?, valor_pagto=?, operador=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, c.getDataPagto());
            pst.setString(2, c.getValoPagto());
            pst.setString(3, c.getOperador());
            pst.setString(4, c.getId());

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {

            System.out.println("br.com.br.controler.ControlerContasApagar.baixarConta()"+e);
        }
        return true;
    }

    public boolean extornaConta(Contas c) {
        //public void extornaConta(Contas c, JLabel operador) {

        boolean resp = false;

        String sql = "UPDATE tbcontas_a_pagar SET data_pagto=?, valor_pagto=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, c.getDataPagto());
            pst.setString(2, c.getValoPagto());
            pst.setString(3, c.getId());

            int op = JOptionPane.showConfirmDialog(null, "Confirma Extorno?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            if (op == JOptionPane.YES_OPTION) {
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Extorno realizado com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada");
            }
            resp = true;
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro Extorna Conta" + e);
        }

        return resp;

    }

    // Exibe menu ao clicar com o botão direito do mouse.
    // Extorna uma conta paga
    public JPopupMenu menu(Contas c, JLabel operador, JComboBox combo) {

        JPopupMenu menudireito = new JPopupMenu();

        JMenuItem extornar = new JMenuItem();
        extornar.setText("Extorna Conta");

        extornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlerContasApagar cc = new ControlerContasApagar();

                int op = JOptionPane.showConfirmDialog(null, "Confirma o Extorno? \n", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    if (cc.baixarConta(c)) {
                        // Registro de log
                        Log log = new Log();
                        log.setUsuario(operador.getText());
                        log.setFuncionalidade("Extorno");
                        log.setDescricao(log.getUsuario() + " extornou a conta -> " + c.getDescricao());
                        log.gravaLog(log);
                        // Fim do registro de log
                        JOptionPane.showMessageDialog(null, "Extorno realizado com sucesso!");
                        combo.setSelectedItem("Abertas");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Extorno cancelado");
                }
            }
        });

        menudireito.add(extornar);
        return menudireito;
    }

    // Excluir uma conta
    public void excluiConta(Contas c) {

        String sql = "DELETE FROM tbcontas_a_pagar WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, c.getId());

            int op = JOptionPane.showConfirmDialog(null, "Confirma Exclusão?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Exclusão cancelada!");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro excluiConta!");

        }
    }

    public boolean contasVencidas() {

        boolean resultado = false;

        String sql = "SELECT * FROM dbbar.tbcontas_a_pagar where data_vencito <= curdate() and valor_pagto is null";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerContasApagar.contasVencidas()");
        }

        return resultado;
    }

    public boolean alteraConta(Contas c) {

        String sql = "UPDATE tbcontas_a_pagar SET Descricao=?, valor=?, data_vencito=?,tbGrupo_id=? WHERE id=?";
        boolean resp = false;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, c.getDescricao());
            pst.setString(2, c.getValor());
            pst.setString(3, c.getDataVencto());
            pst.setString(4, c.getGrupoId());
            pst.setString(5, c.getId());

            pst.executeUpdate();

            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerContasApagar.alteraConta()" + e);
        }

        return resp;
    }

    // Verifica se a conta informada existe
    public boolean existeConta(String id) {
        boolean resp = false;

        String sql = "SELECT * from tbcontas_a_pagar WHERE id=?;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {

                resp = true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerContasApagar.existeConta()" + e);
        }

        return resp;
    }

    // Este método realiza um lançamento multiplo conforme quantidade de parcelas informadas
    public boolean lancamentoMultiplo(int qtdVezes, JDateChooser dateChooser, Contas conta) {
        boolean resp = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicial = dateChooser.getCalendar().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(dataInicial);

        String novaDescricao = null;
        String descricao = conta.getDescricao();
        int prazo = 0;
        // Adiciona 30 dias ao prazo inicial incrementando mais 30 a cada interação

        for (int i = 1; i <= qtdVezes; i++) {

            dataInicial = dateChooser.getCalendar().getTime();
            c = Calendar.getInstance();
            c.setTime(dataInicial);

            prazo = prazo + 30;
            //Seta no calendário o prazo informado
            c.add(c.MONTH, i); // Incrementa o mês em mais 1
            // altera o vencimento da parcela
            conta.setDataVencto(df.format(c.getTime()));
            // Adiciona o número de referencia da parcela ex: 1/3
            novaDescricao = descricao + " " + i + "/" + qtdVezes;

            // Seta nova descrição
            conta.setDescricao(novaDescricao);

            adicionaConta(conta); // Adiciona conta ao banco de dados

            // Inicia um nova instância
            dataInicial = new Date();

            // Seta a nova instancia ao calendário
            c.setTime(dataInicial);

            resp = true;
        }

        return resp;
    }

}
