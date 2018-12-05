/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Fornecedor;
import br.com.bar.model.Produto;
import br.com.bar.model.ProdutoPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class ControlerProduto {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ResultSet listaProduto() {

        String sql = "SELECT \n"
                + "	p.id as 'ID', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	p.valor as 'VALOR',\n"
                + "	p.qtd_max AS 'MAX',\n"
                + "	p.qtd_min AS 'MIN',\n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id;";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ErroListarProduto" + e);
        }
        return rs;
    }

    public ResultSet listaProduto(Fornecedor f) {

        String sql = "SELECT \n"
                + "	p.id as 'ID', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	p.valor as 'VALOR',\n"
                + "	p.qtd_max AS 'MAX',\n"
                + "	p.qtd_min AS 'MIN',\n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE tbFornecedores_id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, f.getCodigo());
            rs = pst.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ErroListarProdutoPorFornecedor" + e);
        }
        return rs;
    }

    public ResultSet listaProdutoDisponivel() {

        String sql = "SELECT \n"
                + "	p.id as 'ID', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	p.valor as 'VALOR',\n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id "
                + "WHERE p.qtd > 0";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ErroListarProdutoPorFornecedor" + e);
        }
        return rs;
    }

    public ResultSet rankingProdutosVendidos() {

        String sql = "SELECT p.nome, sum(dtm.qtd) as 'quantidade', dtm.data \n"
                + "FROM dbbar.detalhe_mesa dtm\n"
                + "INNER JOIN tbproduto p on p.id=dtm.tbproduto_id \n"
                + "group by p.nome";

        try {

            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("Erro rankingProdutosVendidos");
        }

        return rs;
    }

    public ResultSet listaEquantidade() {

        String sql = "SELECT \n"
                + "	p.id as 'CÓDIGO', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id;";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ErroListarProduto" + e);
        }
        return rs;
    }

    public ResultSet filtrarProduto(String localizarTexto, String opcao) {

        String filtro = "";

        String nomeProduto = "p.nome";
        String grupo = "g.nome";

        if ("Nome".equals(opcao)) {
            filtro = nomeProduto; // Filtra pelo nome do produto

        } else {
            filtro = grupo; // filtra pelo nome do grupo
        }

        String sql = "SELECT \n"
                + "	p.id as 'ID', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	p.valor as 'VALOR',\n"
                + "	p.qtd_max AS 'MAX',\n"
                + "	p.qtd_min AS 'MIN',\n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE " + filtro + " LIKE ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, localizarTexto + '%');
            rs = pst.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ErroListarProduto" + e);
        }
        return rs;
    }

    public String localizaIdGrupo(JComboBox combo) {

        String sql = "SELECT * FROM tbgrupo";
        String id = null;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getString("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ErroLocalizaId" + e);
        }

        return id;

    }

    public boolean adicionaProduto(Produto p) {

        String sql = "INSERT INTO tbproduto (nome, qtd, qtd_min,qtd_max,valor,cad_grupo_produto_id, tbFornecedores_id) VALUES (?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getQtd());
            pst.setString(3, p.getQtdMin());
            pst.setString(4, p.getQtdMax());
            pst.setString(5, p.getValor());
            pst.setString(6, p.getTbGrupoId());
            pst.setInt(7, p.getIdFornecedor());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ErroAdicionaProduto " + e);
        }

        return false;
    }

    public boolean alteraProduto(Produto p) {

        String sql = "UPDATE tbproduto SET nome=?, qtd=?, qtd_min=?, qtd_max=?, valor=?, cad_grupo_produto_id=?, tbFornecedores_id=?  WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, p.getNome());
            pst.setString(2, p.getQtd());
            pst.setString(3, p.getQtdMin());
            pst.setString(4, p.getQtdMax());
            pst.setString(5, p.getValor());
            pst.setString(6, p.getTbGrupoId());
            pst.setInt(7, p.getIdFornecedor());
            pst.setString(8, p.getId());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "alteraProduto " + e);
        }
        return false;
    }

    public boolean excluiProduto(Produto p) {

        String sql = "DELETE FROM tbproduto WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Este produto não pode ser excluído!");

        }

        return false;
    }

    public Produto localizaProduto(Produto p) {

        String sql = "SELECT * from tbProduto WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getId());
            rs = pst.executeQuery();

            while (rs.next()) {
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getString("valor"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro localizaProduto() " + e);
        }
        return p;
    }

    // Adiciona um produto ao pedido
    public boolean adicionaProdutoAoPedido(ProdutoPedido pp) {

        String sql = "INSERT INTO detalhe_mesa (tbproduto_id, qtd, valorUnit, Total, data, cadmesa_id, cadpedido_id_pedido, cadpedido_tbcadfuncionario_id) VALUES (?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, pp.getTbproduto_id());
            pst.setString(2, pp.getQtd());
            pst.setString(3, pp.getValorUnit());
            pst.setString(4, pp.getTotal());
            pst.setString(5, pp.getData());
            pst.setString(6, pp.getCadmesa_id());
            pst.setString(7, pp.getCadpedido_id_pedido());
            pst.setString(8, pp.getTbcadfuncionario_id());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Item adicionado!");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro adicionaProdutoPedido()" + e);
        }

        return false;
    }

    public ResultSet listaProdutoParaReajuste() {

        String sql = "SELECT id, nome, valor FROM dbbar.tbproduto";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.listaProdutoParaReajuste()");
        }

        return rs;
    }

    public void reajustaValorProduto(String id, double valorProduto, double fator) {
        DecimalFormat formatador = new DecimalFormat("0.00");
        String sqlUpdate = "UPDATE tbproduto SET valor=? WHERE id=?";
        System.out.println(fator);
        //double novoValor = (valorProduto * fator) + valorProduto;
        double novoValor = (valorProduto * fator/100) + valorProduto;
        System.out.println("novo valor"+novoValor);

        try {
            pst = conexao.prepareStatement(sqlUpdate);
            pst.setString(1, formatador.format(novoValor).replaceAll(",", "."));
            pst.setString(2, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.reajustaValorProduto()" + e);
        }

    }

    public void reajusteGrupoProduto(String idGrupo, String fator) {

        String sql = "SELECT \n"
                + "	tbproduto.`id` AS id,\n"
                + "     tbproduto.`nome` AS tbproduto_nome,\n"
                + "     tbproduto.`valor` AS tbproduto_valor,\n"
                + "     cad_grupo_produto.`nome` AS cad_grupo_produto_nome\n"
                + "FROM\n"
                + "       `cad_grupo_produto` cad_grupo_produto \n"
                + "INNER JOIN `tbproduto` tbproduto ON cad_grupo_produto.`id` = tbproduto.`cad_grupo_produto_id` \n"
                + "WHERE cad_grupo_produto.`id` = ? ORDER BY  cad_grupo_produto.`nome`  ASC;";

        // Atualiza valor do produto;
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, idGrupo);
            rs = pst.executeQuery();

            while (rs.next()) {
                double valorProduto = Double.parseDouble(rs.getString("tbproduto_valor"));

                reajustaValorProduto(rs.getString("id"), Double.parseDouble(rs.getString("tbproduto_valor")), Double.parseDouble(fator));

            }
            JOptionPane.showMessageDialog(null, "Grupo de produtos reajustados com sucesso!");
        } catch (NumberFormatException | SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.reajusteGrupoProduto()" + e);
        }

    }

    public ResultSet pesquisarProduto(String nome) {
        
        String sql = "SELECT p.id as 'ID', p.nome as 'PRODUTO', p.qtd as 'QTD',  p.valor as 'VALOR', g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id\n"
                + "WHERE p.nome LIKE ? AND p.qtd > 0 ";
        
        try {
             pst=conexao.prepareStatement(sql);
             pst.setString(1, nome+"%");
             rs=pst.executeQuery();
             
        } catch (Exception e) {
            System.out.println("br.com.br.controler.ControlerProduto.pesquisarProduto()" + e);
        }
        return rs;
    
    }

}
