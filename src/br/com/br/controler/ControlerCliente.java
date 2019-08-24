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
 * @author Elias Santana Data: 19/08/2019 Adicionado desde: Versão 1.0
 */
public class ControlerCliente {

    Connection conexao = ConexaoBd.conector();
    ResultSet rs = null;
    PreparedStatement pst = null;

    /**
     * Adiciona um Cliente na Base de Dados
     *
     * @param c Recebe um Cliente
     * @return Retorna TRUE ou FALSE
     */
    public boolean adicionaCliente(Cliente c) {
        boolean resp = false;
        String sql = "INSERT INTO `dbbar`.`tbcliente`\n"
                + "(\n"
                + "`nome`,\n"
                + "`endereco`,\n"
                + "`bairro`,\n"
                + "`cep`,\n"
                + "`cidade`,\n"
                + "`email`,\n"
                + "`telefone`,\n"
                + "`numero`,\n"
                + "`uf`,\n"
                + "`telefone_fixo`,\n"
                + "`complemento`,\n"
                + "`referencia`,\n"
                + "`id_localidade`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getEndereco());
            pst.setString(3, c.getBairro());
            pst.setString(4, c.getCep());
            pst.setString(5, c.getCidade());
            pst.setString(6, c.getEmail());
            pst.setString(7, c.getTelefone());
            pst.setString(8, c.getNumero());
            pst.setString(9, c.getUf());
            pst.setString(10, c.getTelefone());
            pst.setString(11, c.getComplemento());
            pst.setString(12, c.getReferencia());
            pst.setInt(13, c.getLocalidade().getId());
            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCliente.adicionaCliente()" + e);
        }

        return resp;
    }

    /**
     * Lista todos os clientes Data: 20/08/2019
     * @param nome Nome do Cliente
     * @return ResultSet 
     */
    public ResultSet listaCliente(String nome) {

        String sql = "SELECT \n"
                + "    c.nome AS 'NOME',\n"
                + "    c.email AS 'E-MAIL',\n"
                + "    l.localidade AS 'LOCALIDADE',\n"
                + "    format(l.taxa,2,'de_DE') AS 'TAXA'\n"
                + "FROM\n"
                + "    dbbar.tbcliente c\n"
                + "        INNER JOIN\n"
                + "    tblocalidade l ON l.id = c.id_localidade\n"
                + "WHERE c.nome LIKE ?;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%"+nome+"%");
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCliente.listaTodosOsClientes()" + e);
        }

        return rs;
    }
    /**
     * Retorna um o id do Cliente informado
     * @param nomeCliente Nome do Cliente
     * @return id ID do Cliente informado
     * 
     */
    public String retornaIdCliente(String nomeCliente){
        String sql="SELECT id FROM tbcliente WHERE nome=?";
        boolean resp=false;
        String id=null;
        try {
            conexao=ConexaoBd.conector();
            pst=conexao.prepareStatement(sql);
            pst.setString(1, nomeCliente);
            rs = pst.executeQuery();
            
            while (rs.next()){
                id=rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCliente.retornaIdCiente()"+e);
        }
        
        return id;
    }
   
    /**
     * Retorna Nome do Cliente
     * @param idCliente  Nome do Cliente
     * @return id Nome do cliente.
     * 
     */
    public String retornaNomeCliente(String idCliente){
        String sql="SELECT nome FROM tbcliente WHERE id=?";
       
        String nomeCliente=null;
        try {
            conexao=ConexaoBd.conector();
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idCliente);
            rs = pst.executeQuery();
            
            while (rs.next()){
                nomeCliente=rs.getString("nome");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCliente.retornaIdCiente()"+e);
        }
        
        return nomeCliente;
    }
    
    
}
