/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

//        String sql = "SELECT    \n"
//                + "    d.cadpedido_id_pedido AS 'PEDIDO',\n"
//                + "    d.nmesa AS 'MESA',\n"
//                + "    c.nome as 'CLIENTE',   \n"
//                + "    e.nome as 'ENTREGADOR',\n"
//                + "    time_format(d.hora_saida, '%H:%m') AS 'SAÍDA'   \n"
//                + "FROM\n"
//                + "    dbbar.tbdelivery d\n"
//                + "        INNER JOIN\n"
//                + "    tbentregador e ON e.id = d.tbentregador_id\n"
//                + "        INNER JOIN\n"
//                + "    tbcliente c ON c.id = tbcliente_id\n"
//                + "        INNER JOIN\n"
//                + "    tbcadfuncionario f ON f.id = d.cadpedido_tbcadfuncionario_id"
//                + " WHERE  d.cadpedido_id_pedido=?;";
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
                + "	tbcadfuncionario f ON f.id = d.cadpedido_tbcadfuncionario_id\n" +
                "WHERE  c.nome = ? AND d.hora_saida is NULL;";
               

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
     * @param txEntrega  Taxa de Entrega
     * @param tGeral  Total Geral do Pedido
     * @param txServico Taxa de Serviço
     * @param idpeido Número do Pedido
     * @return Boolean Retorna TRUE ou FALSE
     *
     */
    public Boolean AtualizaVlrDelivery(double totalPedido, double txEntrega, double txServico, double tGeral, String idpeido) {
        boolean resp = false;
        String sql = "UPDATE tbdelivery SET total_pedido=?, tx_servico=?, tx_entrega=?, total_geral=? WHERE cadpedido_id_pedido=?";
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
     * @param idPEdido  Número do Pedido
     * @return id ID do Cliente informado
     * 
     */
    public String retornaIdCliente(String idPEdido){
        String sql="SELECT tbcliente_id as 'id' FROM tbdelivery WHERE cadpedido_id_pedido=?";
        
        String id=null;
        try {
            conexao=ConexaoBd.conector();
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idPEdido);
            rs = pst.executeQuery();
            
            while (rs.next()){
                id=rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCliente.retornaIdCiente()"+e);
        }
        
        return id;
    }
}
