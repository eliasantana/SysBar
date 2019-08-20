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
            resp=true;
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCliente.adicionaCliente()"+e);            
        }

        return resp;
    }

}
