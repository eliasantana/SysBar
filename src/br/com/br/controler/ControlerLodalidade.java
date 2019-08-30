/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Cliente;
import br.com.bar.model.Localidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

/**
 *
 * @author Elias Santana
 */
public class ControlerLodalidade {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Retorna todos os registros
     *
     * @return ReseultSet
     */
    public ResultSet listaLocalidade() {

        String sql = "SELECT localidade, format(taxa,2,'de_DE') as 'Taxa R$' FROM tblocalidade;";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.listaLocalidade()" + e);
        }

        return rs;
    }

    /**
     * Retorn o id da lodalida informada
     *
     * @param l Localidade
     * @return id Id da lodalidade Informada
     */
    public String retornaIdLocalidade(Localidade l) {

        String sql = "SELECT id FROM tblocalidade where localidade=?;";
        String id = null;
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, l.getNome());
            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getString("id");
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.retornaIdLocalidade()" + e);
        }

        return id;
    }

    /**
     * Adiciona uma lodalidede Data:14/08/2019
     *
     * @param l Localidade
     * @return boolean
     */
    public boolean adicionaLocalidade(Localidade l) {

        boolean resp = false;

        String sql = "INSERT INTO `dbbar`.`tblocalidade`\n"
                + "(\n"
                + "`localidade`,\n"
                + "`taxa`)\n"
                + "VALUES(?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, l.getNome());
            pst.setDouble(2, l.getTaxa());
            pst.executeUpdate();

            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.adicionaLocalidade()" + e);
        }

        return resp;
    }

    /**
     * Verifica se a localidade existe Data:14/08/2019
     *
     * @param l Localidade
     * @return boolean
     */

    public boolean temLocalidade(Localidade l) {

        boolean resp = false;

        String sql = "SELECT id FROM tblocalidade WHERE lower(localidade)=?;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, l.getNome().toLowerCase());
            rs = pst.executeQuery();

            while (rs.next()) {
                resp = true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.adicionaLocalidade()" + e);
        }

        return resp;
    }

    /**
     * Exclui uma localidade Data:14/08/2019
     *
     * @param id Id da localidade
     * @return Boolean Retorna TRUE OU FALSE
     */
    public boolean excluiLocalidade(String id) {

        boolean resp = false;

        String sql = "DELETE FROM tblocalidade WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();

            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.excluiLocalidade()" + e);
        }

        return resp;
    }

    /**
     * Altera uma localidade
     *
     * @param l Localidade
     * @return Boolean Retorna TRUE OU FALSE
     */
    public boolean alteraLocalidade(Localidade l) {
        boolean resp = false;
        String sql = "UPDATE tblocalidade SET localidade=?, taxa=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, l.getNome());
            pst.setDouble(2, l.getTaxa());
            pst.setInt(3, l.getId());

            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.alteraLocalidade()" + e);
        }

        return resp;
    }

    public void carregaComboLocalidade(JComboBox combo) {

        String sql = "SELECT * FROM tblocalidade";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            combo.removeAll();
            combo.addItem("Selecione...");
            while (rs.next()){
                combo.addItem(rs.getString("localidade"));
            }
            
        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerLodalidade.carregaComboLocalidade()" + ex);
        }

    }
    /**
     * Retorna a taxa da localidade informada
     * Data: 19/08/2019
     * @param l Objeto do tipo Localidade
     * @return Retorna um boolean
     */
    public double retornaTaxa(Localidade l){
        double tx=0;
        String sql="SELECT taxa FROM tblocalidade WHERE localidade=?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, l.getNome());
            rs=pst.executeQuery();
            while (rs.next()){
                 tx= rs.getDouble("taxa");                      
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.retornaTaxa()");
        }
        return tx;
    }
    /**
     *  Retorna a localidade do ID informado
     *  Data: 30/08/2019
     *  Versão: 1.0.2d
     *  @param idLocalidade ID a ser localizado
     *  @return localide Retorna o nome da localidade
     */
    public String retornaNomeLocalidade(String idLocalidade){
        String sql="SELECT * localidade WHERE id =?";
        String localidade= null;
        try {
            conexao = ConexaoBd.conector();
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idLocalidade);            
            rs=pst.executeQuery();
            
            while (rs.next()){
                localidade = rs.getString("localidade");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.retornaNomeLocalidade()"+e);
        }
        
        return localidade;
    }
    
    public boolean alteraCliente (Cliente c){
        boolean resp=false;
        String sql="UPDATE tbcliente "
                + "SET nome=?, endereco=?, bairro=?, cep=?, cidade=?, email=?, telefone=?, "
                + "numero=?, uf=?, telefone_fixo=?, complemento=?, referencia=?, id_localidade=? WHERE id=?";
        try {
            conexao = ConexaoBd.conector();
            pst=conexao.prepareStatement(sql);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getEndereco());
            pst.setString(3, c.getBairro());
            pst.setString(4, c.getCep());
            pst.setString(5, c.getCidade());
            pst.setString(6, c.getEmail());
            pst.setString(7, c.getTelefone());
            pst.setString(8, c.getNumero());
            pst.setString(9, c.getUf());
            pst.setString(10, c.getTelefone_recado());
            pst.setString(11, c.getComplemento());
            pst.setString(12, c.getReferencia());
            pst.setInt(13, c.getLocalidade().getId());
            pst.setString(14, c.getId());
            
            pst.executeUpdate();
            resp=true;
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerLodalidade.alteraCliente()"+e);
        }
        return resp;
    }
}

