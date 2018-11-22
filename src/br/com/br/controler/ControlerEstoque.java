/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Produto;
import br.com.bar.model.ProdutoPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class ControlerEstoque {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    /* Retira um produto no estoque */
    public void retiraEstoque(ProdutoPedido pp, String qtdSaida) {

        String sql = "SELECT * FROM tbproduto where id=?";
        int qtd = 0;
        int qtdAtualizada = 0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, pp.getTbproduto_id());
            rs = pst.executeQuery();

            while (rs.next()) {
                qtd = rs.getInt("qtd");

            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerEstoque.retiraEstoque()" + e);

        }
        // Checa se aquantidade no estoque é maior que zero
        if (Integer.parseInt(qtdSaida) > qtd) {
            JOptionPane.showMessageDialog(null, "A quantidade solicitada não está disponível! \n Só existem -> " + qtd + " em estoque! Tente novamente para Continuar!");
        } else {

            qtdAtualizada = qtd - Integer.parseInt(qtdSaida);

            String update = "UPDATE tbproduto SET qtd=? WHERE id=?";

            try {

                pst = conexao.prepareStatement(update);
                pst.setInt(1, qtdAtualizada);
                pst.setString(2, pp.getTbproduto_id());
                pst.executeUpdate();

            } catch (SQLException e) {
                System.out.println("br.com.br.controler.ControlerEstoque.retiraEstoque()" + e);
            }

        }
    }

    public void retiraEstoque(Produto p, String qtdSaida) {

        String sql = "SELECT * FROM tbproduto where id=?";
        int qtd = 0;
        int qtdAtualizada;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getId());
            rs = pst.executeQuery();

            while (rs.next()) {
                qtd = rs.getInt("qtd");

            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerEstoque.retiraEstoque()" + e);

        }

        qtdAtualizada = qtd - Integer.parseInt(qtdSaida);

        String update = "UPDATE tbproduto SET qtd=? WHERE id=?";

        try {

            pst = conexao.prepareStatement(update);
            pst.setInt(1, qtdAtualizada);
            pst.setString(2, p.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerEstoque.retiraEstoque()" + e);
        }

    }

    /*
        Verifica se o produto inserido possui quantidade em estoque.
        e retor sua quantidade.
     */
    
    public int temNoEstoque(String id) {

        String sql = "SELECT * FROM dbbar.tbproduto WHERE id=?";
        boolean tem = false;
        int qtd=0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            // Verifica se a quantidade em estoque é maior que zero
            while (rs.next()) {

                tem = rs.getInt("qtd") > 0;
                if (tem){
                  qtd = rs.getInt("qtd");
                }

            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.EstoqueControler.temNoEstoque()" + e);
        }

        return qtd;
    }

    // Carrega os tipos de movimentação
    public void carregaComboOperacao(JComboBox combo) {

        String sql = "SELECT * FROM tboperacao";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                combo.addItem(rs.getString("operacao"));
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerEstoque.carregaComboOperacao()" + e);
        }
    }

    // Realiza pesquisa no banco por nome ou por código do produto
    public ResultSet pesquisarProduto(String coluna, String localizar) {

        String sql = "SELECT * FROM tbproduto WHERE " + coluna + " like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, localizar + '%');
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerEstoque.pesquisaPorNome()" + e);
            System.out.println(coluna + localizar);

        }

        return rs;

    }

    /*
        Registra a movimentação relizada no banco
     */
    public boolean registraMovimentacao(String idProduto, String qtd, String idOperacao, String observacao) {

        boolean op = false;
        Date dtAtual = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        //id, data, id_produto, qtd, id_operacao, observacao
        String sql = "INSERT INTO tbmovestoque (data, id_produto, qtd, id_operacao, observacao) VALUES (?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, df.format(dtAtual));
            pst.setString(2, idProduto);
            pst.setString(3, qtd);
            pst.setString(4, idOperacao);
            pst.setString(5, observacao);

            pst.executeUpdate();

            op = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerEstoque.registraMovimentacao()" + e);
        }

        return op;

    }

    public String localizaIdOperacao(String operacao) {

        String id = null;

        String sql = "SELECT * FROM tboperacao WHERE operacao=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, operacao);
            rs = pst.executeQuery();

            while (rs.next()) {

                id = rs.getString("id");
            }

        } catch (SQLException e) {
        }

        return id;
    }

    public boolean entradaDeProduto(String idProduto, String qtd) {
        boolean resp = false;
        int qtdAtual = 0;
        int qtdAtualizada = 0;

        String pesquisa = "SELECT * FROM tbproduto WHERE id=?";

        try {
            pst = conexao.prepareStatement(pesquisa);
            pst.setString(1, idProduto);
            rs = pst.executeQuery();

            while (rs.next()) {
                qtdAtual = Integer.parseInt(rs.getString("qtd"));
            }

        } catch (NumberFormatException | SQLException e) {
            System.out.println("br.com.br.controler.ControlerEstoque.adicionaProduto()" + e);
        }

        qtdAtualizada = qtdAtual + Integer.parseInt(qtd);

        String sql = "UPDATE tbproduto SET qtd=? WHERE id=?";
        // Atualiza a quantidade em estoque
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, String.valueOf(qtdAtualizada));
            pst.setString(2, idProduto);

            pst.executeUpdate();

            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerEstoque.adicionaProduto()" + e);
        }

        return resp;
    }

    // Localiza o id do produto cadastrado no banco 
    public String localizaIdProduto(String nomeNroduto) {

        String sql = "SELECT id, nome FROM tbproduto WHERE nome=?";
        String id = null;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeNroduto);
            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getString("id");

            }

        } catch (SQLException e) {

            System.out.println("br.com.br.controler.ControlerEstoque.localizaIdProduto()" + e);
        }

        return id;
    }
}
