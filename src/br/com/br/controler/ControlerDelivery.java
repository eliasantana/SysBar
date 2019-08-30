/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Cliente;
import br.com.bar.model.Entregador;
import br.com.bar.model.Localidade;
import br.com.bar.util.FormataValor;
import br.com.bar.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Elias Santana Data: 22/08/2019 Adicionada após a versão 1.0.0
 * @
 */
public class ControlerDelivery {

    PreparedStatement pst = null;
    Connection conexao = null;
    ResultSet rs = null;

    /**
     * Lista todos os pedidos delivery
     *
     * @return rs Retorna um ResultSet
     */
    public ResultSet listaDelivery() {

        String sql = "SELECT    \n"
                + "    d.cadpedido_id_pedido AS 'PEDIDO',\n"
                + "    date_format(d.data, '%d/%m/%Y %H:%m') AS DATA,    \n"
                + "    e.nome as 'ENTREGADOR',\n"
                + "    c.nome as 'CLIENTE',   \n"
                + "    f.nome AS 'GARÇOM',\n"
                + "    time_format(d.hora_saida, '%H:%m') AS 'SAÍDA'   \n"
                + "FROM\n"
                + "    dbbar.tbdelivery d\n"
                + "        INNER JOIN\n"
                + "    tbentregador e ON e.id = d.tbentregador_id\n"
                + "        INNER JOIN\n"
                + "    tbcliente c ON c.id = tbcliente_id\n"
                + "        INNER JOIN\n"
                + "    tbcadfuncionario f ON f.id = d.cadpedido_tbcadfuncionario_id;";

        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.listaDelivery()" + e);
        }

        return rs;
    }

    /**
     * Lista pedido do Cliente
     *
     * @param idPedido Número do Pedido
     * @return rs Retorna um ResultSet
     */
    public ResultSet listaDelivery(String idPedido) {

        String sql = "SELECT    \n"
                + "    d.cadpedido_id_pedido AS 'PEDIDO',\n"
                + "    d.nmesa AS 'MESA',\n"
                + "    c.nome as 'CLIENTE',   \n"
                + "    d.entregador as 'ENTREGADOR',\n"
                + "    time_format(d.hora_saida, '%H:%m') AS 'SAÍDA'   \n"
                + "FROM\n"
                + "    dbbar.tbdelivery d\n"
                + "        INNER JOIN\n"
                + "    tbcliente c ON c.id = tbcliente_id\n"
                + "        INNER JOIN\n"
                + "    tbcadfuncionario f ON f.id = d.cadpedido_tbcadfuncionario_id"
                + " WHERE  d.cadpedido_id_pedido=?;";

        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.listaDelivery()" + e);
        }

        return rs;
    }

    /**
     * Lista pedido do Cliente
     *
     * @param nomeCliente Nome do Cliente
     * @return rs Retorna um ResultSet
     */
    public ResultSet listaDeliveryCliente(String nomeCliente) {

        String sql = "SELECT\n"
                + "	d.cadpedido_id_pedido AS 'PEDIDO',\n"
                + "	d.nmesa AS 'MESA', \n"
                + "	c.nome as 'CLIENTE',\n"
                + "	d.entregador as 'ENTREGADOR',\n"
                + "	time_format(d.hora_saida, '%H:%m') AS 'SAÍDA'  \n"
                + "	FROM\n"
                + "	dbbar.tbdelivery d\n"
                + "INNER JOIN\n"
                + "	tbcliente c ON c.id = tbcliente_id\n"
                + "INNER JOIN\n"
                + "	tbcadfuncionario f ON f.id = d.cadpedido_tbcadfuncionario_id\n"
                + "WHERE  c.nome = ? AND d.hora_saida is NULL;";

        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeCliente);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.listaDelivery()" + e);
        }

        return rs;
    }

    /**
     * Registra Delivery no Banco de Dados
     *
     * @param idPedido Número do Pedido
     * @param idCliente Nome do Cliente
     * @param idFuncionario ID do Funcionário
     * @param nMesa Número da Mesa
     * @return Boolean Retorna TRUE ou FALSE
     *
     */
    public Boolean anexaPedido(String idPedido, String idCliente, String idFuncionario, String nMesa) {
        boolean resp = false;
        String sql = "INSERT INTO tbdelivery (cadpedido_id_pedido, tbcliente_id, data, cadpedido_tbcadfuncionario_id, nmesa) VALUES (?,?,current_timestamp(),?,?);";
        try {

            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            pst.setString(2, idCliente);
            pst.setString(3, idFuncionario);
            pst.setString(4, nMesa);

            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.anexaPedido()" + e);
        }

        return resp;
    }

    /**
     * Atualiza Delivery no Banco de Dados
     *
     * @param idPedido Número do Pedido
     * @param idCliente Nome do Cliente
     * @param idFuncionario ID do Funcionário
     * @param nMesa Número da Mesa
     * @return Boolean Retorna TRUE ou FALSE
     *
     */
    public Boolean atualizaDelivery(String idPedido, String idCliente, String idFuncionario, String nMesa) {
        boolean resp = false;
        String sql = "UPDATE tbdelivery SET cadpedido_id_pedido, tbcliente_id, data, cadpedido_tbcadfuncionario_id, nmesa) VALUES (?,?,current_timestamp(),?,?);";
        try {

            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            pst.setString(2, idCliente);
            pst.setString(3, idFuncionario);
            pst.setString(4, nMesa);

            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.anexaPedido()" + e);
        }

        return resp;
    }

    /**
     * Atualiza Valores do Delivery no Banco de Dados
     *
     * @param totalPedido Total do Pedido
     * @param txEntrega Taxa de Entrega
     * @param tGeral Total Geral do Pedido
     * @param txServico Taxa de Serviço
     * @param idpeido Número do Pedido
     * @return Boolean Retorna TRUE ou FALSE
     *
     */
    public Boolean AtualizaVlrDelivery(double totalPedido, double txEntrega, double txServico, double tGeral, String idpeido) {
        boolean resp = false;
        String sql = "UPDATE tbdelivery SET total_pedido=?,tx_entrega=?, tx_servico=?, total_geral=? WHERE cadpedido_id_pedido=?";
        try {

            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, totalPedido);
            pst.setDouble(2, txEntrega);
            pst.setDouble(3, txServico);
            pst.setDouble(4, tGeral);
            pst.setString(5, idpeido);

            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.anexaPedido() - aqui" + e);
        }

        return resp;
    }

    /**
     * Verifica se o pedido já existe no delivery
     *
     * @param nPedido Número do Pedido
     * @return boolean Retorna TRUE ou FALSE
     */
    public boolean temNoDelivery(String nPedido) {
        String sql = "SELECT cadpedido_id_pedido as 'pedido' FROM dbbar.tbdelivery WHERE cadpedido_id_pedido=?;";
        boolean resp = false;
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nPedido);
            rs = pst.executeQuery();

            while (rs.next()) {
                resp = true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.temNoDelivery()" + e);
        }

        return resp;
    }

    /**
     * Retorna um o id do Cliente no Delivery
     *
     * @param idPEdido Número do Pedido
     * @return id ID do Cliente informado
     *
     */
    public String retornaIdCliente(String idPEdido) {
        String sql = "SELECT tbcliente_id as 'id' FROM tbdelivery WHERE cadpedido_id_pedido=?";

        String id = null;
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPEdido);
            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCliente.retornaIdCiente()" + e);
        }

        return id;
    }

    /**
     * Adiciona um entregador
     *
     * @param nome Nome do Entregador
     * @param idPedido Número do Pedido
     * @return Boolean Retorna TRUE OU FALSE
     */
    public boolean adicionaEntregador(String nome, String idPedido) {
        String sql = "UPDATE tbdelivery SET entregador = ? WHERE cadpedido_id_pedido=?";
        boolean resp = false;
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, idPedido);

            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.adicionaEntregador()" + e);
        }

        return resp;
    }

    /**
     * Atualiza a hora de entrega do pedido ao Entregador
     *
     * @param idPedido Número do Pedido.
     * @param nomeEntregador Nome do Entregador
     */
    public void atualizaHoraSaida(String idPedido, String nomeEntregador) {
        Util u = new Util();
        Date dtAtual = new Date();
        String hora = u.formataDataHora(dtAtual, "h");
        String sql = "UPDATE tbdelivery SET hora_saida=?, entregador=? WHERE cadpedido_id_pedido=?";
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, hora);
            pst.setString(2, nomeEntregador);
            pst.setString(3, idPedido);
            pst.executeUpdate();

            conexao.close();
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.atualizaHoraSaida()" + e);
        }
    }

    /**
     * Retorna a localidade e a taxa de entrega do Cliente informado
     *
     * @param nomeCliente Nome do Cliente.
     * @return Localidade Retorna um Obj do tipo Localidade
     */
    public Localidade retornaLocalidadeEtaxa(String nomeCliente) {
        Localidade l = new Localidade();
        String sql = "SELECT \n"
                + "    c.nome, l.localidade, l.taxa\n"
                + "FROM\n"
                + "    dbbar.tbcliente c\n"
                + "INNER JOIN tblocalidade l on l.id = c.id_localidade \n"
                + "WHERE c.nome = ?";

        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeCliente);
            rs = pst.executeQuery();
            while (rs.next()) {
                l.setNome(rs.getString("localidade"));
                l.setTaxa(rs.getDouble("taxa"));
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.retornaLocalidadeEtaxa()" + e);
        }

        return l;
    }

    /**
     * Localiza um entregador na tabela Delivery
     *
     * @param idPEdido Número do Pedido
     * @return entregador Nome do Entregador
     */
    public String localizaEntregador(String idPEdido) {
        String sql = "SELECT entregador  FROM tbdelivery WHERE cadpedido_id_pedido = ?";
        String entregador = null;
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPEdido);
            rs = pst.executeQuery();

            while (rs.next()) {
                entregador = rs.getString("entregador");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.localizaEntregador()" + e);
        }

        return entregador;
    }

    /**
     * Move pedido para tabela histórica
     *
     * @param idPedido Número do pedido
     * @return Boolean Retorna TRUE ou FALSE
     */
    public boolean movePedidoParaHistoricoDelivery(String idPedido) {
        boolean resp = false;
        String sql = "INSERT INTO tbhistoricodelivery SELECT * from tbdelivery where cadpedido_id_pedido = ?";
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            pst.executeUpdate();

            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.movePedidoParaHistoricoDelivery()" + e);

        }

        return resp;
    }

    /**
     * Exclui o pedido da tabela Delivery
     *
     * @param idPedido Número do Pedido
     * @return Boolean Retorna TRUE OU FALSE
     */
    public boolean excluiPedidoDoDelivery(String idPedido) {

        boolean resp = false;
        String sql2 = "DELETE FROM tbdelivery WHERE cadpedido_id_pedido = ?";
        // Remove o pedido da tabela delivery
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql2);
            pst.setString(1, idPedido);
            pst.executeUpdate();

            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.movePedidoParaHistoricoDelivery()" + e);
        }

        return resp;
    }

    /**
     * @param nPedido Número do Pedido
     * @return String Valor de Entrega formatado.
     */
    public String retornaTaxaEntrega(String nPedido) {
        String sql = "SELECT  tx_entrega FROM tbdelivery WHERE cadpedido_id_pedido=?";
        FormataValor fv = new FormataValor();
        String valor = null;

        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nPedido);
            rs = pst.executeQuery();

            while (rs.next()) {
                valor = rs.getString("tx_entrega");
            }
            try {

                valor = fv.Formata(valor);
            } catch (Exception e) {
                
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDelivery.retornaTaxaEntrega()" + e);
        }

        return valor;

    }
}
