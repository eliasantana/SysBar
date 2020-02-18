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
import javax.swing.JTextField;

/**
 *
 * @author Elias Santana
 *
 */
public class ControlerFuncionario extends Funcionario {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     *
     * Adiciona um funcionário e retorna um boolean
     *
     * @param f 'Funcionario'
     * @return boolean
     *
     */

    public boolean adicionaFuncionario(Funcionario f) {
        boolean resp = false;
        String sql = "INSERT INTO tbcadfuncionario "
                + "(nome, endereco, bairro, cep, cidade, email,foto,telefone,"
                + "login,cargo,senha,status,bloqueado,cpf,rg,cnh,observacao,"
                + "numero,uf,telefone_recado,complemento,dt_nascimento,dt_admissao,cnh_validade) \n"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

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
            pst.setString(22, f.getDtAdmissao());
            pst.setString(23, f.getDtNascimento());
            pst.setString(24, f.getDtvalidadeCNH());

            pst.executeUpdate();

            resp = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar adicionar o funcionário - Contate o SUPORTE!");
            System.out.println("br.com.br.controler.ControlerFuncionario.adicionaFuncionario()" + e);
        }

        return resp;

    }

    public ResultSet carregaFuncionario(String texto) {

               
        String sql = "SELECT id AS 'CÓDIGO', \n"
                + "	nome AS 'NOME', \n"
                + "	cpf AS 'CPF', rg AS 'RG',\n"
                + "	telefone_recado AS 'CELULAR',\n"
                + "	date_format(dt_admissao,'%d/%m/%Y') as 'ADMISSÃO', \n"
                + "    CASE \n"
                + "		WHEN  status =0 THEN 'Ativo'\n"
                + "		WHEN status =1 THEN  'Inativo' \n"
                + "	END AS 'STATUS', \n"
                + "    CASE\n"
                + "		WHEN bloqueado =0 THEN 'Desbloqueado'\n"
                + "		WHEN bloqueado =1 THEN 'Bloqueado'\n"
                + "    END AS 'BLOQUEIO'\n"
                + "FROM dbbar.tbcadfuncionario WHERE nome like ? AND nome <> 'admin' ORDER BY BLOQUEIO DESC, NOME";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, texto + "%");
            rs = pst.executeQuery();

        } catch (SQLException e) {

            System.out.println("br.com.br.controler.ControlerFuncionario.carregaFuncionario()" + e);
        }

        return rs;
    }

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
            System.out.println("br.com.br.controler.ControlerFuncionario.exibeHistorico()"+e);
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
            System.out.println("br.com.br.controler.ControlerFuncionario.excluirFuncionario()");
            resposta=false;
        }
        return resposta;
    }

    public boolean alterar(Funcionario f, String id) {
        // Este método altera os dados do funcionário a partir do identificador  
        boolean resp = false;
       
        String sql = "UPDATE tbcadfuncionario SET\n"
                + "endereco=?, \n"
                + "bairro=?, \n"
                + "cep=?, \n"
                + "cidade=?, \n"
                + "email=?,\n"
                + "foto=?,\n"
                + "telefone=?,\n"
                + "cargo=?,\n"
                + "senha=?,\n"
                + "status=?,\n"
                + "bloqueado=?,\n"
                + "observacao=?,\n"
                + "numero=?,\n"
                + "uf=?,\n"
                + "telefone_recado=?,\n"
                + "complemento=?,\n"
                + "cnh_validade=?,\n"
                + "dt_desligamento=?,\n"
                + "dt_admissao=?,\n"
                + "cnh=?\n"
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
            pst.setString(17, f.getDtvalidadeCNH());
            pst.setString(18, f.getDtDesligamento());
            pst.setString(19, f.getDtAdmissao());
            pst.setString(20, f.getCnh());
            pst.setString(21, id);

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
                System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboFuncionario()" + e);
            }
        } else {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, filtro);
                rs = pst.executeQuery();
                combo.removeAllItems();
                combo.addItem("Selecione...");
                while (rs.next()) {
                    combo.addItem(rs.getString("nome"));
                }

            } catch (SQLException e) {
                System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboFuncionario()" + e);
            }
        }

    }

    // Lista funcionários adicionando o conteúdo do último parâmetro no indice0 do objeto ComboBox
    // Este método é especialmente utilizado na tela de Autorização de Desconto
    public void carregaComboFuncionario2(JComboBox combo, String filtro, String indice0) {

        // Carrega combo com o nome do funciário a partir do critério indicado em filtro
        String sql = "SELECT * FROM tbcadfuncionario where cargo=? OR cargo='Gerente' AND status=0 AND bloqueado=0";
        String sqlTodos = "SELECT * FROM tbcadfuncionario WHERE status=0 AND bloqueado=0";

        if ("todos".equals(filtro)) {

            try {
                pst = conexao.prepareStatement(sql);

                rs = pst.executeQuery(sqlTodos);

                combo.removeAllItems();
                combo.addItem(indice0);

                while (rs.next()) {
                    combo.addItem(rs.getString("nome"));
                }

            } catch (SQLException e) {
                System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboFuncionario2()"+e);
            }
        } else {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, filtro);
                rs = pst.executeQuery();
                combo.removeAllItems();
                combo.addItem(indice0);
                while (rs.next()) {
                    combo.addItem(rs.getString("login"));
                }

            } catch (SQLException e) {
                System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboFuncionario2()+e");
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
                System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboFuncionario()"+e);
                        
            }
        } else {

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, filtro);
                pst.setString(2, remove);
                rs = pst.executeQuery();
                combo.removeAllItems();
                combo.addItem("Selecione...");
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
        String sql = "SELECT * FROM tbcadfuncionario ORDER BY login asc";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            combo.addItem("Selecione...");
            while (rs.next()) {
                combo.addItem(rs.getString("login"));
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboFuncionario()"+e);
        }
    }
    /**
     * Carrega combo com o login dos funcionarios com status ATIVO.
     * @param combo Combo a ser preenchido.
     */ 
    public void carregaComboFuncionarioAtivo(JComboBox combo) {

        // Recurso utilizado na tela de log
        String sql = "SELECT login FROM tbcadfuncionario WHERE status=0 ORDER BY login ASC";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            combo.addItem("Selecione...");
            while (rs.next()) {
                combo.addItem(rs.getString("login"));
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboFuncionarioAtivo()"+e);
        }
    }
    /**
     * Carrega combo com o login dos funcionarios com status ATIVO com cargos
     * de Gerente e Garçom.
     * @param combo Combo a ser preenchido.
     */ 
    public void carregaComboFuncionarioGerenteGarcom(JComboBox combo) {

        // Recurso utilizado na tela de log
        String sql = "SELECT login FROM dbbar.tbcadfuncionario where cargo in('Garçom','Gerente') and status=0";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            combo.addItem("Selecione...");
            while (rs.next()) {
                combo.addItem(rs.getString("login"));
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboFuncionarioGerenteGarcom()"+e);
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
            System.out.println("br.com.br.controler.ControlerFuncionario.localizaId()");
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
            System.out.println("br.com.br.controler.ControlerFuncionario.localizaIdLogin()");
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
            System.out.println("br.com.br.controler.ControlerFuncionario.temMesa()"+e);
        }

        return false;

    }

    /*  
        Pesquisa um nome no banco de dados remove os espaços em branco e 
        compara com o nome localizado na base.
     */
    public boolean temFuncionario(String nome) {
        boolean resp = false;
        String str = "";
        String strNome = "";
        String nomeBanco = "";

        String sql = "SELECT nome FROM tbcadfuncionario where nome=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();

            while (rs.next()) {
                nomeBanco = rs.getString("nome");
            }

            str = nomeBanco.replaceAll(" ", "").toLowerCase();
            strNome = nome.replaceAll(" ", "").toLowerCase();
            System.out.println("Nome Digitado: " + strNome);
            System.out.println("Nome Banco: " + str);

            if (strNome.equals(str)) {
                resp = true;
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.temFuncionario()"+e);
        }
        return resp;
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
            System.out.println("br.com.br.controler.ControlerFuncionario.carregaComboCargo()"+e);
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
                f.setDtAdmissao(rs.getString("dt_admissao"));
                f.setDtDesligamento(rs.getString("dt_desligamento"));
                f.setDtvalidadeCNH(rs.getString("cnh_validade"));
                f.setDtNascimento(rs.getString("dt_nascimento"));
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.localizaFuncionario()" + e);
        }
        return f;
    }
    /**
     * Altera a senha do funcionário informado.
     * @param senha Nova Senha.
     * @param id ID do usuário.
     * @return Retorna True se a operação for bem sucedida.
     */
    
    public boolean alteraSenha(String senha, String id){
        String sql="UPDATE tbcadfuncionario SET senha=? WHERE id=?";
        boolean resp=false;
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, senha);
            pst.setString(2, id);
            pst.executeUpdate();
           
            resp=true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.alteraSenha()" +e);
        }
        
        return resp;
    }
    
    /**
     * Retorna o cargo do Funcionário informado
     * @param login - Login do Usuário
     * @return String - Retorna o cago do usuário informado
     */ 
    
    public String cargoFuncionario(String login){
        String sql="SELECT cargo FROM tbcadfuncionario WHERE login=?";
        String nome = "";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, login);
            rs=pst.executeQuery();
            
            while(rs.next()){
                nome=rs.getString("cargo");
            }
                    
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerFuncionario.cargoFuncionario()");
        }
        return nome;
    }
    
   
}
