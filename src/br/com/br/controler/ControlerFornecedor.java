/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class ControlerFornecedor {

    Fornecedor f = new Fornecedor();
    PreparedStatement pst = null;
    Connection conexao = ConexaoBd.conector();
    ResultSet rs = null;

    public void cadastraFornecedor(Fornecedor f) {
        // Cadastra um fornecedor no banco de dados
        String sql = "INSERT INTO tbfornecedores "
                + "(nome, endereco, numero, complemento, bairro, cep, cidade, uf, telefone, email, cnpj, representante, site, status, obs)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            pst = conexao.prepareStatement(sql);

            pst.setString(1, f.getNome());
            pst.setString(2, f.getEndereco());
            pst.setString(3, f.getNumero());
            pst.setString(4, f.getComplemento());
            pst.setString(5, f.getBairro());
            pst.setString(6, f.getCep());
            pst.setString(7, f.getCidade());
            pst.setString(8, f.getUf());
            pst.setString(9, f.getTelefone());
            pst.setString(10, f.getEmail());
            pst.setString(11, f.getCnpj());
            pst.setString(12, f.getRepresentante());
            pst.setString(13, f.getSite());
            pst.setString(14, f.getStatus());
            pst.setString(15, f.getObs());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.cadastraFornecedor()" + e.getMessage());

        }

    }

    public ResultSet listaFornecedor(String fornecedor) {
        // Lista todos os fornecedores
        String sql = "SELECT \n"
                + "    nome as 'RAZÃO SOCIAL',    \n"
                + "    CNPJ as 'CNPJ',\n"
                + "    representante as 'REPRESENTANTE',\n"
                + "	telefone as 'CELULAR',\n"
                + "    email as 'E-MAIL',\n"
                + "    status as 'STATUS'    \n"
                + "FROM  tbfornecedores WHERE nome like ? ORDER BY status, nome";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, fornecedor + "%");
            rs = pst.executeQuery();

        } catch (SQLException e) {

            System.out.println("br.com.br.controler.ControlerFornecedor.listaFornecedor()" + e.getMessage());
        }

        return rs;
    }

    // Exclui um fornecedor se o mesmo estiver relacionado com algum produto
    // o fornecedor será inativado ao invés de ser excluído.
    public boolean excluirFornecedor(Fornecedor f) {
        //Exclui fornecedor
        String sql = "DELETE FROM tbfornecedores WHERE id=?";
        boolean resp = false;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, f.getId());
            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.excluirFornecedor()" + e);
            JOptionPane.showMessageDialog(null, "Fornecedor Inativado, pois possui produtos cadastrados e não pode ser excluído!");
            mudaStatus(f.getId(), "Inativo");

        }
        return resp;
    }

    public void alteraFornecedor(Fornecedor f) {
        // Altera dados do fornecedor
        String sql = "UPDATE tbfornecedores SET nome=?, endereco=?, numero=?, complemento=?, "
                + "bairro=?, cep=?, cidade=?, uf=?, telefone=?, email=?, cnpj=?, representante=?, site=?, status=?, obs=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, f.getNome());
            pst.setString(2, f.getEndereco());
            pst.setString(3, f.getNumero());
            pst.setString(4, f.getComplemento());
            pst.setString(5, f.getBairro());
            pst.setString(6, f.getCep());
            pst.setString(7, f.getCidade());
            pst.setString(8, f.getUf());
            pst.setString(9, f.getTelefone());
            pst.setString(10, f.getEmail());
            pst.setString(11, f.getCnpj());
            pst.setString(12, f.getRepresentante());
            pst.setString(13, f.getSite());
            pst.setString(14, f.getStatus());
            pst.setString(15, f.getObs());
            pst.setInt(16, f.getId());

            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso!");

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.alteraFornecedor()" + e);
        }

    }

    public void carregaComboFornecedor(JComboBox combo) {
        // Lista o nome dos fornecedores em ordem alfabética
        String sql = "SELECT nome FROM tbFornecedores ORDER BY nome ASC";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                combo.addItem(rs.getString("nome"));
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.comboFornecedor()");
        }

    }

    // Este método localiza um fornecedor
    public String localizaForecedor(Fornecedor f) {

        String sql = "SELECT * FROM tbfornecedores WHERE nome=?";
        String idFor = null;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, f.getNome());
            rs = pst.executeQuery();
            // Se locakuzadi retirbará o id
            while (rs.next()) {

                idFor = rs.getString("id");
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.localizaForecedor()");
        }

        return idFor;
    }

    // Este método localiza um fornecedor
    public Fornecedor localizaFornecedor(String nome) {

        String sql = "SELECT * FROM tbfornecedores WHERE nome=?";

        Fornecedor fornec = new Fornecedor();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            // Se locakuzadi retirbará o id
            while (rs.next()) {

                fornec.setId(rs.getInt("id"));
                fornec.setNome(rs.getString("nome"));
                fornec.setEndereco(rs.getString("endereco"));
                fornec.setNumero(rs.getString("numero"));
                fornec.setBairro(rs.getString("bairro"));
                fornec.setCep(rs.getString("cep"));
                fornec.setCidade(rs.getString("cidade"));
                fornec.setUf(rs.getString("uf"));
                fornec.setTelefone(rs.getString("telefone"));
                fornec.setEmail(rs.getString("email"));
                fornec.setCnpj(rs.getString("cnpj"));
                fornec.setRepresentante(rs.getString("representante"));
                fornec.setSite(rs.getString("site"));
                fornec.setStatus(rs.getString("status"));
                fornec.setObs(rs.getString("obs"));

            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.localizaForecedor()");
        }

        return fornec;
    }

    public boolean temFornecedor(Fornecedor f) {

        boolean resp = false;
        String sql = "SELECT nome FROM tbfornecedores WHERE nome =?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, f.getNome());
            rs = pst.executeQuery();

            while (rs.next()) {
                resp = true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.temFornecedor()" + e);
        }
        return resp;
    }

    /**
     * Este métodos inativa um fornecedor na base.
     *
     * @param id Id do fornecedor
     * @param status Status a ser definido.
     * @return boolean Retornas TRUE ou FALSE.
     */

    public boolean mudaStatus(int id, String status) {
        String sql = "UPDATE tbfornecedores SET status=? WHERE id=?";
        boolean resp = false;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, status);
            pst.setInt(2, id);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFornecedor.inativaFornecedor()" + e);
        }
        return resp;
    }
}
