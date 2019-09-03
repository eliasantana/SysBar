/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Entregador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elias Santana
 *
 */
public class ControlerEntregador {

    Connection conexao;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Lista todos os entregadores disponíveis
     *
     * @param combo JcomboBox a ser preenchido;
     *
     */
    public void listaEntregador(JComboBox combo) {
        String sql = "SELECT nome FROM dbbar.tbentregador WHERE status=0;";

        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            combo.removeAllItems();
            combo.addItem("Selecione...");
            while (rs.next()) {
                combo.addItem(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerEntregador.listaEntregador()" + e);
        }
    }

    /**
     * Lista todos os entregadores
     *
     * @param tabela Tabela a ser preenchida
     */
    public void listaEntregador(JTable tabela) {
        String sql = "SELECT \n"
                + "    nome AS 'NOME',\n"
                + "    celular AS 'CELULAR',\n"
                + "    veiculo AS 'VEÍCULO',\n"
                + "    UPPER(placa) as 'PLACA',\n"
                + "    cpf AS 'CPF',\n"
                + "    rg AS 'RG',\n"
                + "    CASE\n"
                + "        WHEN status = 0 THEN 'LIVRE'\n"
                + "        ELSE 'OCUPADO'\n"
                + "    END AS 'STATUS'\n"
                + "FROM\n"
                + "    dbbar.tbentregador;";

        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            // Preenche tabela com os dados da consulta
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            // Aplica Model           
            conexao.close();
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerEntregador.listaEntregador()" + e);
        }
    }

    /**
     * Retorna um entregador
     *
     * @param nome Nome do Entregador
     * @return Entregador Retorna um obj do tipo Entregador.
     */
    public Entregador localizaEntregador(String nome) {
        String sql = "SELECT * FROM tbentregador WHERE nome=? ";
        Entregador e = new Entregador();
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();

            while (rs.next()) {
                
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setVeiculo(rs.getString("veiculo"));
                e.setStatus(rs.getInt("status"));
                e.setEndereco(rs.getString("endereco"));
                e.setNumero(rs.getString("numero"));
                e.setBairro(rs.getString("bairro"));
                e.setCep(rs.getString("cep"));
                e.setCidade(rs.getString("cidade"));
                e.setUf(rs.getString("uf"));
                e.setTelefone(rs.getString("celular"));
                e.setTelefone_recado(rs.getString("telefone"));
                e.setHistorico(rs.getString("historico"));
                e.setEmail(rs.getString("email"));
                e.setCpf(rs.getString("cpf"));
                e.setRg(rs.getString("rg"));
                e.setCnh(rs.getString("cnh"));
                e.setValidade(rs.getString("validade"));
                e.setComplemento(rs.getString("complemento"));
                e.setPlaca(rs.getString("placa"));
                     
            }
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerEntregador.localizaEntregador()" + ex);
        }
        return e;
    }

    /**
     * Atualiza status do Entregador
     *
     * @param e Objeto tipo Entregador
     * @return Boolean Retorna TRUE ou FALSE.
     */
    public boolean atualizaStatusEntregador(Entregador e) {
        String sql = "UPDATE tbentregador SET status=? WHERE id=?";
        boolean resp = false;
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, e.getStatus());
            pst.setInt(2, e.getId());
            pst.executeUpdate();

            resp = true;
        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerDelivery.autualizaStatusEntregador()" + ex);

        }
        return resp;
    }

    /**
     * Adiciona um entregador Data: 02/09/2019
     *
     * @param e Obj do tipo Entregador
     * @return Boolean Retorna TRUE ou FALSE
     */
    public boolean adicionaEntregador(Entregador e) {

        boolean resp = false;
        //id, nome, veiculo, status, endereco, numero, bairro, cep, cidade, uf, telefone, celular, historico, email, cpf, rg, cnh, validade, complemento
        String sql = "INSERT INTO tbentregador SET nome=?, veiculo=?, status=?, endereco=?, "
                + "numero=?, bairro=?, cep=?, cidade=?, uf=?, telefone=?, celular=?, historico=?, "
                + "email=?, cpf=?, rg=?, cnh=?, validade=?, complemento=?, placa=?";

        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, e.getNome());
            pst.setString(2, e.getVeiculo());
            pst.setInt(3, e.getStatus());
            pst.setString(4, e.getEndereco());
            pst.setString(5, e.getNumero());
            pst.setString(6, e.getBairro());
            pst.setString(7, e.getCep());
            pst.setString(8, e.getCidade());
            pst.setString(9, e.getUf());
            pst.setString(10, e.getTelefone_recado());
            pst.setString(11, e.getTelefone());
            pst.setString(12, e.getHistorico());
            pst.setString(13, e.getEmail());
            pst.setString(14, e.getCpf());
            pst.setString(15, e.getRg());
            pst.setString(16, e.getCnh());
            pst.setString(17, e.getValidade());
            pst.setString(18, e.getComplemento());
            pst.setString(19, e.getPlaca());

            pst.executeUpdate();
            resp = true;

        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerEntregador.adicionaEntregador()" + ex);
        }

        return resp;
    }
    /**
     * Altera um entregador Data: 02/09/2019
     *
     * @param e Obj do tipo Entregador
     * @return Boolean Retorna TRUE ou FALSE
     */
    public boolean alteraEntregador(Entregador e) {

        boolean resp = false;
        //id, nome, veiculo, status, endereco, numero, bairro, cep, cidade, uf, telefone, celular, historico, email, cpf, rg, cnh, validade, complemento
            String sql = "UPDATE tbentregador SET nome=?, veiculo=?, status=?, endereco=?, "
                + "numero=?, bairro=?, cep=?, cidade=?, uf=?, telefone=?, celular=?, historico=?, "
                + "email=?, cpf=?, rg=?, cnh=?, validade=?, complemento=?, placa=? WHERE id=?";

        try {
            conexao = ConexaoBd.conector();            
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, e.getNome());
            pst.setString(2, e.getVeiculo());
            pst.setInt(3, e.getStatus());
            pst.setString(4, e.getEndereco());
            pst.setString(5, e.getNumero());
            pst.setString(6, e.getBairro());
            pst.setString(7, e.getCep());
            pst.setString(8, e.getCidade());
            pst.setString(9, e.getUf());
            pst.setString(10, e.getTelefone_recado());
            pst.setString(11, e.getTelefone());
            pst.setString(12, e.getHistorico());
            pst.setString(13, e.getEmail());
            pst.setString(14, e.getCpf());
            pst.setString(15, e.getRg());
            pst.setString(16, e.getCnh());
            pst.setString(17, e.getValidade());
            pst.setString(18, e.getComplemento());
            pst.setString(19, e.getPlaca());
            pst.setInt(20, e.getId());
            pst.executeUpdate();
            resp = true;
             
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerEntregador.adicionaEntregador()" + ex);
        }

        return resp;
    }
    /**
     * Exclui um Entregador
     * Data:03-09-2019
     * Versão: 1.0.0d
     * @param e Obj Entregador
     * @return Boolean Retorna  TRUE OU FALSE
     * 
     */
     
    public boolean excluiEntregador (Entregador e){
        
        boolean resp=false;
        
        String sql="DELETE FROM tbentregador WHERE id=?";
        try {
            conexao=ConexaoBd.conector();
            pst=conexao.prepareStatement(sql);
            pst.setInt(1, e.getId());
            pst.executeUpdate();
            resp=true;
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerEntregador.excluiEntregador()"+e);
        }
        
        return resp;
    }
}
