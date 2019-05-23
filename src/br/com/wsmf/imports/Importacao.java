/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wsmf.imports;

import br.com.bar.dao.ConexaoBd;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta classe tem com objetivo importadar os dados do banco local e envialos
 * para um banco da web
 *
 * @author Elias Santana Data: 14/05/2019
 *
 */
public class Importacao {

    Connection conexcao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    File f;
    FileWriter fw;
    PrintWriter pw;
    String url = "C:\\xampp\\htdocs\\wscadastro\\site\\";
    String arquivo = "movimentacao.csv";
    String data;
    String entradas;
    String saidas;
    String saldo;
    String funcionario;

    public boolean importarMovimentacao() {

        boolean resp = false;

        String sql = "SELECT \n"
                + "    m.id,\n"
                + "    m.data,\n"
                + "    m.entradas as 'entrada',\n"
                + "    m.saidas as 'saida',\n"
                + "    m.saldo as 'saldo',\n"
                + "    f.nome as 'funcionario'\n"
                + "FROM\n"
                + "    dbbar.tbmovimentacao m\n"
                + "    INNER JOIN tbcadfuncionario f ON f.id = m.tbcadfuncionario_id ";

        try {
            pst = conexcao.prepareStatement(sql);
            rs = pst.executeQuery();

            f = new File(url + arquivo);
            try {
                fw = new FileWriter(f);
            } catch (IOException ex) {
                Logger.getLogger(Importacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            pw = new PrintWriter(fw);

            while (rs.next()) {
                data = rs.getString("data");
                entradas = rs.getString("entrada");
                saidas = rs.getString("saida");
                saldo = rs.getString("saldo");
                funcionario = rs.getString("funcionario");

                pw.println(data + ";" + entradas + ";" + saidas + ";" + saldo + ";" + funcionario);

            }
            try {

                fw.flush();
            } catch (IOException e) {
                System.out.println("br.com.wsmf.imports.Importacao.importarMovimentacao()");
            }
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.wsmf.imports.ImportMovimentaco.importarMovimentacao()");
        }

        System.out.println("Dados importados com sucesso! ->" + url);
        return resp;
    }

    public boolean importaPedido() {
        arquivo = "pedidos.csv";
        boolean resp = false;
        String sql = "SELECT \n"
                + "    p.id_pedido as 'pedido',\n"
                + "    date_format(p.data,'%Y-%m-%d') as 'data',   \n"
                + "    p.total as 'total',\n"
                + "    p.comissao as 'comissao',\n"
                + "    p.formaPagto as 'forma_pagto',\n"
                + "    m.numero_mesa as 'mesa',\n"
                + "    f.nome as 'nome',\n"
                + "    p.operador as 'operador'\n"
                + "   \n"
                + "FROM\n"
                + "    dbbar.cadpedido p\n"
                + "INNER JOIN cadmesa m on m.id = p.cadmesa_id\n"
                + "INNER JOIN tbcadfuncionario f on f.id = p.tbcadfuncionario_id\n"
                + "WHERE date_format(data,'%Y-%m-%d') =curdate();";

        try {
            pst = conexcao.prepareStatement(sql);
            rs = pst.executeQuery();
            try {

                File fPedido = new File(url + arquivo);
                FileWriter fwPedido = new FileWriter(fPedido);
                PrintWriter pwPedido = new PrintWriter(fwPedido);

                while (rs.next()) {
                    String pedido = rs.getString("pedido");
                    String dataPedido = rs.getString("data");
                    String total = rs.getString("total");
                    String comissao = rs.getString("comissao");
                    String forma_pagto = rs.getString("forma_pagto");
                    String mesa = rs.getString("mesa");
                    String nome = rs.getString("nome");
                    String operador = rs.getString("operador");

                    pwPedido.println(pedido + ";" + dataPedido + ";" + total + ";" + comissao + ";" + forma_pagto + ";" + mesa + ";" + nome + ";" + operador);
                }
                resp = true;
                System.out.println(url + arquivo);
                fwPedido.flush();
            } catch (IOException e) {
                System.out.println("br.com.wsmf.imports.Importacao.imortaPedido()");
            }

        } catch (SQLException e) {
            System.out.println("br.com.wsmf.imports.Importacao.importaPedido()");
        }
        System.out.println("Pedidos importados com sucesso -> " + url + arquivo);
        return resp;

    }
  // Importa todas as contas pagas
    public boolean importaContas() {

        boolean resp = false;
        arquivo = "contas.csv";
        String sql = "SELECT \n"
                + "	c.id,\n"
                + "	c.Descricao,\n"
                + "	c.valor,\n"
                + "	c.data_vencito,\n"
                + "	c.data_pagto,\n"
                + "	c.valor_pagto,\n"
                + "	g.grupo\n"
                + "\n"
                + "FROM\n"
                + "	dbbar.tbcontas_a_pagar c\n"
                + "INNER JOIN tbcadfuncionario f on f.id = c.tbcadfuncionario_id\n"
                + "INNER JOIN tbgrupo g on g.id = c.tbGrupo_id\n"
                + "WHERE c.data_pagto is not null";

        try {
            pst = conexcao.prepareStatement(sql);
            rs = pst.executeQuery();

            try {
                File fContas = new File(url + arquivo);
                FileWriter fwContas = new FileWriter(fContas);
                PrintWriter pwContas = new PrintWriter(fwContas);

                while (rs.next()) {

                    String descricao = rs.getString("Descricao");
                    String valor = rs.getString("valor");
                    String dataVencito = rs.getString("data_vencito");
                    String dataPagto = rs.getString("data_pagto");
                    String valorPagto = rs.getString("valor_pagto");
                    String grupo = rs.getString("grupo");

                    pwContas.println(descricao + ";" + valor + ";" + dataVencito + ";" + dataPagto + ";" + valorPagto + ";" + grupo);
                }

                resp = true;
                fwContas.flush();

            } catch (IOException ex) {
                Logger.getLogger(Importacao.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException e) {
        }

        System.out.println("Contas importadas com sucesso!->" + url + arquivo);
        return resp;
    }

}
