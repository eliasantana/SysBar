/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class ControlerFuncionario extends Funcionario {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean adicionaFuncionario(Funcionario f) {
        boolean resp = false;
        String sql = "INSERT INTO tbcadfuncionario "
                + "(nome, endereco, bairro, cep, cidade, email,foto,telefone,login,cargo,senha,status,bloqueado,cpf,rg,cnh,observacao,numero,uf,telefone_recado,complemento) \n"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, f.getNome());
            pst.setString(2, f.getEndereco());
            pst.setString(3, f.getBairro());
            pst.setString(4, f.getCep());
            pst.setString(5, f.getCidade());
            pst.setString(6, f.getEmail());
            pst.setString(7, f.getFoto());
            pst.setString(8, f.getTelefone());
            pst.setString(9, f.getLogin());
            pst.setString(10, f.getCargo());
            pst.setString(11, f.getSenha());
            pst.setString(12, f.getStatus());
            pst.setString(13, f.getBloqueado());
            pst.setString(14, f.getCpf());
            pst.setString(15, f.getRg());
            pst.setString(16, f.getCnh());
            pst.setString(17, f.getObservacao());
            pst.setString(18, f.getNumero());
            pst.setString(19, f.getUf());
            pst.setString(20, f.getTelefone_recado());
            pst.setString(21, f.getComplemento());

            pst.executeUpdate();

            resp = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar adicionar o funcionario!");
            System.out.println("br.com.br.controler.ControlerFuncionario.adicionaFuncionario()" + e);
        }

        return resp;

    }

    public ResultSet carregaFuncionario(String texto) {

        String slq = "SELECT id AS 'CÓDIGO', nome AS 'NOME', cpf AS 'CPF', rg AS 'RG',telefone_recado AS 'CELULAR'\n"
                + "FROM dbbar.tbcadfuncionario\n"
                + "WHERE nome like ?;";

        try {
            pst = conexao.prepareStatement(slq);
            pst.setString(1, texto + "%");
            rs = pst.executeQuery();

        } catch (SQLException e) {

            System.out.println("br.com.br.controler.ControlerFuncionario.carregaFuncionario()" + e);
        }

        return rs;
    }

    /*Excluir depois 
    public void contatoFuncionario(JTable tabela) {

        String slq = "SELECT \n"
                + "id as 'ID',\n"
                + "	nome as 'FUNCIONÁRIO', \n"
                + "	telefone AS 'TELEFONE',\n"
                + "	email AS 'E-MAIL', \n"
                + "	cargo AS 'CARGO' \n"
                + "from tbcadfuncionario ORDER BY nome ASC";

        try {
            pst = conexao.prepareStatement(slq);
            rs = pst.executeQuery();

            tabela.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro contatoFuncionario()");
        }

    }
     */
    public String exibeHistorico(Funcionario f) {

        String sql = "SELECT observacao FROM tbcadfuncionario WHERE id =?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, f.getId());
            rs = pst.executeQuery();
            while (rs.next()) {
                f.setObservacao(rs.getString("observacao"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro exibeHistorico" + e);
        }
        return f.getObservacao();
    }

    // Método para excluir funcionário 
    public boolean excluirFuncionario(String id) {

        boolean resposta = false;

        String sql = "DELETE from tbcadfuncionario where id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            resposta = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Este funcionário possui mesas e não pode ser excluído!");
        }
        return resposta;
    }

    public boolean alterar(Funcionario f, String id) {
        // Este método altera os dados do funcionário a partir do identificador  
        boolean resp = false;

        String sql = "UPDATE tbcadfuncionario SET "
              
                + "endereco=?, "
                + "bairro=?, "
                + "cep=?, "
                + "cidade=?, "
                + "email=?,"
                + "foto=?,"
                + "telefone=?,"              
                + "cargo=?,"
                + "senha=?,"
                + "status=?,"
                + "bloqueado=?,"                
                + "observacao=?,"
                + "numero=?,"
                + "uf=?,"
                + "telefone_recado=?,"
                + "complemento=?"
                + "WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);            
            pst.setString(1, f.getEndereco());
            pst.setString(2, f.getBairro());
            pst.setString(3, f.getCep());
            pst.setString(4, f.getCidade());
            pst.setString(5, f.getEmail());
            pst.setString(6, f.getFoto());
            pst.setString(7, f.getTelefone());          
            pst.setString(8, f.getCargo());
            pst.setString(9, f.getSenha());
            pst.setString(10, f.getStatus());
            pst.setString(11, f.getBloqueado());
            pst.setString(12, f.getObservacao());
            pst.setString(13, f.getNumero());
            pst.setString(14, f.getUf());
            pst.setString(15, f.getTelefone_recado());
            pst.setString(16, f.getComplemento());
            pst.setString(17, id);

            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.alterar()" + e);
        }

        return resp;
    }

    public boolean validaInterface(JTextField nome, JTextField telefone) {

        boolean resp = false;

        if (nome.getText().equals("") && telefone.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha os cambos obrigatórios");

        } else {
            resp = true;
        }

        return resp;
    }

    public void carregaComboFuncionario(JComboBox combo, String filtro) {

        // Carrega combo com o nome do funciário a partir do critério indicado em filtro
        String sql = "SELECT * FROM tbcadfuncionario where cargo=? AND status=0 AND bloqueado=0";
        String sqlTodos = "SELECT * FROM tbcadfuncionario WHERE status=0 AND bloqueado=0";

        if ("todos".equals(filtro)) {

            try {
                pst = conexao.prepareStatement(sql);

                rs = pst.executeQuery(sqlTodos);

                combo.removeAllItems();
                while (rs.next()) {
                    combo.addItem(rs.getString("nome"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro carregaComboFuncionário" + e);
            }
        } else {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, filtro);
                rs = pst.executeQuery();
                combo.removeAllItems();
                while (rs.next()) {
                    combo.addItem(rs.getString("nome"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro carregaComboFuncionário" + e);
            }
        }

    }

    public void carregaComboFuncionario(JComboBox combo, String filtro, String remove) {

        // Carrega combo com o nome do funciário a partir do critério indicado em filtro e retira o nome do uncionario
        // indicado em remove
        String sql = "SELECT * FROM tbcadfuncionario where cargo=? AND status=0 AND bloqueado=0 AND nome<>?";
        String sqlTodos = "SELECT * FROM tbcadfuncionario WHERE status=0 AND bloqueado=0";

        if ("todos".equals(filtro)) {

            try {
                pst = conexao.prepareStatement(sqlTodos);

                rs = pst.executeQuery(sqlTodos);

                combo.removeAllItems();
                while (rs.next()) {
                    combo.addItem(rs.getString("nome"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro carregaComboFuncionário" + e);
            }
        } else {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, filtro);
                pst.setString(2, remove);
                rs = pst.executeQuery();
                combo.removeAllItems();
                while (rs.next()) {
                    combo.addItem(rs.getString("nome"));
                }

            } catch (SQLException e) {
                System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboFuncionario()" + e);
            }
        }

    }

    // Carrega combo com o login do funcionário
    public void carregaComboFuncionario(JComboBox combo) {

        // Recurso utilizado na tela de log
        String sql = "SELECT * FROM tbcadfuncionario";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            combo.addItem("Selecione...");
            while (rs.next()) {
                combo.addItem(rs.getString("login"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro carregaComboFuncionário" + e);
        }

    }

    public String localizaId(String nome) {

        String sql = "SELECT id FROM tbcadfuncionario where nome=?";
        String id = "";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getString("id");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro localizaId" + e);
        }
        return id;
    }

    public String localizaIdLogin(String login) {

        String sql = "SELECT id FROM tbcadfuncionario where login=?";
        String id = "";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getString("id");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro localizaId" + e);
        }
        return id;
    }

    public boolean temMesa(Funcionario f) {
        // verifica se o funcionário garçom possui mesa

        String sql = "SELECT * FROM  cadmesa WHERE tbCadFuncionario_id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, f.getId());

            rs = pst.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro temMesa() " + e);
        }

        return false;

    }

    public boolean temFuncionario(String id) {

        String sql = "SELECT nome FROM tbcadfuncionario where id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro localizaId" + e);
        }
        return false;
    }

    public void carregaComboCargo(JComboBox combo) {

        String sql = "select cargo from tbcadfuncionario group by cargo;";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            combo.removeAllItems();
            while (rs.next()) {
                combo.addItem(rs.getString("cargo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro carregaComboCarg" + e);
        }

    }

    public String retornaGarcom(String numeroMesa) {
        String garcom = null;
        String sql = "SELECT cm.numero_mesa, cf.nome \n"
                + "FROM dbbar.cadmesa cm\n"
                + "INNER JOIN tbcadfuncionario cf on cf.id=cm.tbcadFuncionario_id\n"
                + "WHERE cm.numero_mesa=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroMesa);
            rs = pst.executeQuery();

            while (rs.next()) {
                garcom = rs.getString("nome");
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.retornaGarcom()");
        }
        return garcom;
    }

    public Funcionario localizaFuncionario(String id) {

        String sql = "SELECT * FROM tbcadfuncionario WHERE id=?";

        Funcionario f = new Funcionario();
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {

                f.setId(rs.getString("id"));
                f.setNome(rs.getString("nome"));
                f.setEndereco(rs.getString("endereco"));
                f.setBairro(rs.getString("bairro"));
                f.setCep(rs.getString("cep"));
                f.setCidade(rs.getString("cidade"));
                f.setEmail(rs.getString("email"));
                f.setFoto(rs.getString("foto"));
                f.setLogin(rs.getString("login"));
                f.setCargo(rs.getString("cargo"));
                f.setSenha(rs.getString("senha"));
                f.setStatus(rs.getString("status"));
                f.setBloqueado(rs.getString("bloqueado"));
                f.setRg(rs.getString("rg"));
                f.setCpf(rs.getString("cpf"));
                f.setCnh(rs.getString("cnh"));
                f.setObservacao(rs.getString("observacao"));
                f.setNumero(rs.getString("numero"));
                f.setUf(rs.getString("uf"));
                f.setTelefone_recado(rs.getString("telefone_recado"));
                f.setTelefone(rs.getString("telefone"));
                f.setComplemento(rs.getString("complemento"));

            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.localizaFuncionario()" + e);
        }
        return f;
    }
}
