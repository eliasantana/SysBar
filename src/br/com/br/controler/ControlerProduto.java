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

    // Lista todos os produtos em estoque
    public ResultSet listaProduto() {
        //Query Original sem exclusão de produto inativo
//
//        String sql = "SELECT \n"
//                //+ "	p.id as 'CÓDIGO', \n"
//                + "	p.cod_produto as 'CÓDIGO', \n"
//                + "	p.nome as 'PRODUTO',\n"
//                + "	p.qtd as 'QTD', \n"
//                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
//                + "	p.qtd_min AS 'MIN',\n"
//                + "	p.qtd_max AS 'MAX',\n"
//                + "	g.nome as 'GRUPO'\n"
//                + "FROM tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id;";
        String sql = "SELECT \n"
                //+ "	p.id as 'CÓDIGO', \n"
                + "	p.cod_produto as 'CÓDIGO', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
                + "	p.qtd_min AS 'MIN',\n"
                + "	p.qtd_max AS 'MAX',\n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE p.ativo =1;";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.listaProduto()" + e);
        }
        return rs;
    }

    public ResultSet listaProduto(Fornecedor f) {
        // Query Original sem exclusão lógica
//        String sql = "SELECT \n"
//                //+ "	p.id as 'CÓDIGO', \n"
//                + "	p.cod_produto as 'CÓDIGO', \n"
//                + "	p.nome as 'PRODUTO',\n"
//                + "	p.qtd as 'QTD', \n"
//                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
//                + "	p.qtd_min AS 'MIN',\n"
//                + "	p.qtd_max AS 'MAX',\n"
//                + "	g.nome as 'GRUPO'\n"
//                + "FROM tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE tbFornecedores_id=?";
        
String sql = "SELECT \n"
                //+ "	p.id as 'CÓDIGO', \n"
                + "	p.cod_produto as 'CÓDIGO', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
                + "	p.qtd_min AS 'MIN',\n"
                + "	p.qtd_max AS 'MAX',\n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE tbFornecedores_id=? AND p.ativo=1";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, f.getId());
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.listaProduto()" + e);
        }
        return rs;
    }

    public ResultSet listaProdutoDisponivel() {
        // Query original sem exlusão lógica
        
//        String sql = "SELECT \n"
//                + "	p.id as 'CÓDIGO', \n"
//                + "	p.nome as 'DESCRIÇÃO',\n"
//                + "	p.qtd as 'ESTOQUE', \n"
//                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
//                + "	g.nome as 'GRUPO'\n"
//                + "FROM tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id "
//                + "WHERE p.qtd > 0 ORDER BY g.nome";
        String sql = "SELECT \n"
                + "	p.id as 'CÓDIGO', \n"
                + "	p.nome as 'DESCRIÇÃO',\n"
                + "	p.qtd as 'ESTOQUE', \n"
                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id "
                + "WHERE p.qtd > 0 AND p.ativo=1 ORDER BY g.nome";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.listaProdutoDisponivel()" + e);
        }
        return rs;
    }

    // Método utilizado exclusivamente na tela de pesquisa de produtos
    public ResultSet listaProdutoEstoque() {
// Query Original - lista produtos sem exclusão lógica
//        String sql = "SELECT \n"
//                // Apagar linha  108 após validação de janiel
//                //+ "	p.id as 'CÓDIGO', \n"
//                + "	p.cod_produto as 'CÓDIGO', \n"
//                + "	p.nome as 'DESCRIÇÃO',\n"
//                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
//                + "	p.qtd as 'ESTOQUE' \n"
//                + "FROM tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id "
//                + "WHERE p.qtd > 0 ORDER BY g.nome";

        String sql = "SELECT \n"
                // Apagar linha  108 após validação de janiel
                //+ "	p.id as 'CÓDIGO', \n"
                + "	p.cod_produto as 'CÓDIGO', \n"
                + "	p.nome as 'DESCRIÇÃO',\n"
                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
                + "	p.qtd as 'ESTOQUE' \n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id "
                + "WHERE p.qtd > 0 AND p.ativo=1 ORDER BY g.nome";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.listaProdutoDisponivel()" + e);
        }
        return rs;
    }

    // Método utilizado exclusivamente na tela de pesquisa de produtos
    // Localiza produto informado no parâmetro
    public ResultSet listaProdutoEstoque(String nomeProduto) {
        // Query orignal sem exclusão lógica
//        String sql = "SELECT \n"
//               // + "	p.id as 'CÓDIGO', \n"
//                + "	p.cod_produto as 'CÓDIGO', \n"
//                + "	p.nome as 'DESCRIÇÃO',\n"
//                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
//                + "	p.qtd as 'ESTOQUE' \n"
//                + "FROM tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id "
//                + "WHERE p.nome LIKE ? AND p.qtd > 0 ORDER BY g.nome";


        String sql = "SELECT \n"
               // + "	p.id as 'CÓDIGO', \n"
                + "	p.cod_produto as 'CÓDIGO', \n"
                + "	p.nome as 'DESCRIÇÃO',\n"
                + "	format(p.valor,2,'de_DE') as 'VALOR R$',\n"
                + "	p.qtd as 'ESTOQUE' \n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id "
                + "WHERE p.nome LIKE ? AND p.qtd AND p.ativo=1 > 0 ORDER BY g.nome";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + nomeProduto + "%");
            
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.listaProdutoDisponivel()" + e);
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
 // Query sem exclusão de produto inativo
//        String sql = "SELECT \n"
//                + "	p.cod_produto as 'CÓDIGO', \n"
//                + "	p.nome as 'PRODUTO',\n"
//                + "	p.qtd as 'QTD', \n"
//                + "	g.nome as 'GRUPO'\n"
//                + "FROM tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id;";

        String sql = "SELECT \n"
                + "	p.cod_produto as 'CÓDIGO', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE p.ativo=1;";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.listaEquantidade()" + e);
        }
        return rs;
    }

    public ResultSet listaEquantidade2(String coluna, String pesquisa) {
        String sql;
        if ("nome".equals(coluna)) {
            coluna = "p.nome";
            //Query Original sem exclusão
//            sql = "SELECT\n"
//                    //+ "p.id as 'CÓDIGO', \n"
//                    + "	p.cod_produto as 'CÓDIGO', \n"
//                    + "	p.nome as 'PRODUTO',\n"
//                    + "	p.qtd as 'QTD', \n"
//                    + "	g.nome as 'GRUPO'\n"
//                    + "FROM tbproduto p\n"
//                    + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id\n"
//                    + "WHERE " + coluna + " LIKE ?;";
            
            sql = "SELECT\n"
                    //+ "p.id as 'CÓDIGO', \n"
                    + "	p.cod_produto as 'CÓDIGO', \n"
                    + "	p.nome as 'PRODUTO',\n"
                    + "	p.qtd as 'QTD', \n"
                    + "	g.nome as 'GRUPO'\n"
                    + "FROM tbproduto p\n"
                    + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id\n"
                    + "WHERE " + coluna + " LIKE ? AND p.ativo=1;";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, "%" + pesquisa + "%");

                rs = pst.executeQuery();

            } catch (SQLException e) {
                System.out.println("br.com.br.controler.ControlerProduto.listaEquantidade() - NOME" + e);
            }
        } else {
            //coluna = "p.id";
            coluna = "p.cod_produto";
            //Query Original sem Exclusão lógica
//            sql = "SELECT\n"
//                    //+ "	p.id as 'CÓDIGO', \n"
//                    + "	p.cod_produto as 'CÓDIGO', \n"
//                    + "	p.nome as 'PRODUTO',\n"
//                    + "	p.qtd as 'QTD', \n"
//                    + "	g.nome as 'GRUPO'\n"
//                    + "FROM tbproduto p\n"
//                    + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id\n"
//                    + "WHERE " + coluna + "= ?";
            
            sql = "SELECT\n"
                    //+ "	p.id as 'CÓDIGO', \n"
                    + "	p.cod_produto as 'CÓDIGO', \n"
                    + "	p.nome as 'PRODUTO',\n"
                    + "	p.qtd as 'QTD', \n"
                    + "	g.nome as 'GRUPO'\n"
                    + "FROM tbproduto p\n"
                    + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id\n"
                    + "WHERE " + coluna + "= ? AND p.ativo=1";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, pesquisa);
                rs = pst.executeQuery();

            } catch (SQLException e) {
                System.out.println("br.com.br.controler.ControlerProduto.listaEquantidade() - LIKE" + e);
            }
        }

        return rs;
    }

    public ResultSet filtrarProduto(String localizarTexto, String opcao) {
        
// Apagar códigoos comentados após validação de Janiel
//        String filtro = "";
//        String nomeProduto = "p.nome";
//        String grupo = "g.nome";
//        String codProduto = "p.cod_produto";
//
//        if ("Nome".equals(opcao)) {
//            filtro = nomeProduto; // Filtra pelo nome do produto
//
//        }else if ("Codigo".equals(opcao)){
//            filtro = codProduto;
//        }else {
//            filtro = grupo; // filtra pelo nome do grupo
//        }
//
//        String sql = "SELECT \n"
//                //+ "	p.id as 'CÓDIGO', \n"
//                + "	p.cod_produto as 'CÓDIGO', \n"
//                + "	p.nome as 'PRODUTO',\n"
//                + "	p.qtd as 'QTD', \n"
//                + "	format(p.valor, 2,'de_DE') as 'VALOR R$',\n"
//                + "	p.qtd_min AS 'MIN',\n"
//                + "	p.qtd_max AS 'MAX',\n"
//                + "	g.nome as 'GRUPO'\n"
//                + "FROM tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE " + filtro + " LIKE ?";
//
//        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setString(1, "%" + localizarTexto + "%");
//            rs = pst.executeQuery();
//
//        } catch (SQLException e) {
//            System.out.println("br.com.br.controler.ControlerProduto.filtrarProduto()" + e);
//        }
//        return rs;
        String filtro =null;
        String nomeProduto = "p.nome";
        String grupo = "g.nome";
        String codProduto = "p.cod_produto";
        int tipoPesquisa=1;
        
        if ("Nome".equals(opcao)) {
            filtro = nomeProduto; // Filtra pelo nome do produto
        }else if ("Codigo".equals(opcao)){
            filtro = codProduto;
            tipoPesquisa =2;
        }else {
            filtro = grupo; // filtra pelo nome do grupo
        }
          // Query Original sem exclusão lógica
//        String sql = "SELECT \n"
//                //+ "	p.id as 'CÓDIGO', \n"
//                + "	p.cod_produto as 'CÓDIGO', \n"
//                + "	p.nome as 'PRODUTO',\n"
//                + "	p.qtd as 'QTD', \n"
//                + "	format(p.valor, 2,'de_DE') as 'VALOR R$',\n"
//                + "	p.qtd_min AS 'MIN',\n"
//                + "	p.qtd_max AS 'MAX',\n"
//                + "	g.nome as 'GRUPO'\n"
//                + "FROM tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE " + filtro + " LIKE ?";
//
//        String sql2 = "SELECT \n"
//                //+ "	p.id as 'CÓDIGO', \n"
//                + "	p.cod_produto as 'CÓDIGO', \n"
//                + "	p.nome as 'PRODUTO',\n"
//                + "	p.qtd as 'QTD', \n"
//                + "	format(p.valor, 2,'de_DE') as 'VALOR R$',\n"
//                + "	p.qtd_min AS 'MIN',\n"
//                + "	p.qtd_max AS 'MAX',\n"
//                + "	g.nome as 'GRUPO'\n"
//                + "FROM tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE " + filtro +"=?";
       
        String sql = "SELECT \n"
                //+ "	p.id as 'CÓDIGO', \n"
                + "	p.cod_produto as 'CÓDIGO', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	format(p.valor, 2,'de_DE') as 'VALOR R$',\n"
                + "	p.qtd_min AS 'MIN',\n"
                + "	p.qtd_max AS 'MAX',\n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE " + filtro + " LIKE ? AND p.ativo=1";

        String sql2 = "SELECT \n"
                //+ "	p.id as 'CÓDIGO', \n"
                + "	p.cod_produto as 'CÓDIGO', \n"
                + "	p.nome as 'PRODUTO',\n"
                + "	p.qtd as 'QTD', \n"
                + "	format(p.valor, 2,'de_DE') as 'VALOR R$',\n"
                + "	p.qtd_min AS 'MIN',\n"
                + "	p.qtd_max AS 'MAX',\n"
                + "	g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g ON g.id=p.cad_grupo_produto_id WHERE " + filtro +"=?  AND p.ativo=1";
        
        try {
            pst = conexao.prepareStatement(sql);
            if (tipoPesquisa==1){
                pst.setString(1, "%" + localizarTexto + "%");                
            }else{
                pst.setString(1, localizarTexto);
            }
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.filtrarProduto()" + e);
        }
        return rs;
    }

    // Métododo sem uso - Verificar sua utilização - Descartar se não utilizado.
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
            System.out.println("br.com.br.controler.ControlerProduto.localizaIdGrupo()" + e);
        }

        return id;

    }

    public boolean adicionaProduto(Produto p) {

        String sql = "INSERT INTO tbproduto (nome, qtd, qtd_min,qtd_max,valor,cad_grupo_produto_id, tbFornecedores_id,cod_ncm,cod_produto, ativo) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getQtd());
            pst.setString(3, p.getQtdMin());
            pst.setString(4, p.getQtdMax());
            pst.setString(5, p.getValor());
            pst.setString(6, p.getTbGrupoId());
            pst.setInt(7, p.getIdFornecedor());
            pst.setString(8, p.getCodNCM());
            pst.setString(9, p.getCodigoProduto());
            pst.setString(10, "1");
            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.adicionaProduto()" + e);
        }

        return false;
    }

    public boolean alteraProduto(Produto p) {

        String sql = "UPDATE tbproduto SET nome=?, qtd=?, qtd_min=?, qtd_max=?, valor=?, cad_grupo_produto_id=?, tbFornecedores_id=?, cod_ncm=?, cod_produto=?  WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, p.getNome());
            pst.setString(2, p.getQtd());
            pst.setString(3, p.getQtdMin());
            pst.setString(4, p.getQtdMax());
            pst.setString(5, p.getValor());
            pst.setString(6, p.getTbGrupoId());
            pst.setInt(7, p.getIdFornecedor());
            pst.setString(8, p.getCodNCM());
            pst.setString(9, p.getCodigoProduto());
            pst.setString(10, p.getId());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.alteraProduto()" + e);
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
            System.out.println("br.com.br.controler.ControlerProduto.excluiProduto()");
            
        }

        return false;
    }
    //Realiza a Exclusão Logica para produto que possui relação com pedido
    public Boolean exclusaoLogica(Produto p){
        String sql = "UPDATE tbproduto SET ativo=0, cod_produto=0 WHERE id=?";
        boolean resp=false;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getId());
            pst.executeUpdate();
            resp=true;
        } catch (SQLException e) {
            System.out.println("Não foi possivel realizar a exclusão lógica " + e);
        }
        return resp;
    }

    public Produto localizaProduto(Produto p) {

       //QUERY ORIGINAL SEM EXCLUSÃO LOÓGICA
        //String sql = "SELECT * from tbProduto WHERE cod_produto=?";
        String sql = "SELECT * from tbProduto WHERE cod_produto=? AND ativo=1";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getId());
            rs = pst.executeQuery();

            while (rs.next()) {
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getString("valor"));
                p.setCodigoProduto(rs.getString("cod_produto"));
                p.setId(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.localizaProduto()" + e);
        }
        return p;
    }

    // Retorna o grupo do produto informado no parâmetro
    public String localizaGrupoProduto(int id) {

//        String sql = "SELECT g.nome as 'grupo' FROM dbbar.tbproduto p\n"
//                + "INNER JOIN cad_grupo_produto g on g.id = p.cad_grupo_produto_id\n"
//                + "WHERE p.id=?";
        String sql = "SELECT g.nome as 'grupo' FROM dbbar.tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g on g.id = p.cad_grupo_produto_id\n"
                + "WHERE p.cod_produto=?";
        String nomeGrupo = "";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                nomeGrupo = rs.getString("grupo");

            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.localizaProduto()" + e);
        }
        return nomeGrupo;
    }

    // Adiciona um produto ao pedido
    public boolean adicionaProdutoAoPedido(ProdutoPedido pp) {

        String sql = "INSERT INTO detalhe_mesa (tbproduto_id, qtd, valorUnit, Total, data, cadmesa_id, cadpedido_id_pedido, cadpedido_tbcadfuncionario_id) VALUES (?,?,?,?,current_timestamp(),?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, pp.getTbproduto_id());
            pst.setString(2, pp.getQtd());
            pst.setString(3, pp.getValorUnit());
            pst.setString(4, pp.getTotal());
            pst.setString(5, pp.getCadmesa_id());
            pst.setString(6, pp.getCadpedido_id_pedido());
            pst.setString(7, pp.getTbcadfuncionario_id());

            pst.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.adicionaProdutoAoPedido()" + e);
        }

        return false;
    }

    public ResultSet listaProdutoParaReajuste() {

        String sql = "SELECT id AS 'CÓDIGO', nome AS 'DESCRIÇÃO', format(valor,2,'de_DE') as 'VALOR R$' FROM dbbar.tbproduto";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.listaProdutoParaReajuste()");
        }

        return rs;
    }
    // Lista produto para reajuste filtrando pelo nome do produto informado
    public ResultSet listaProdutoParaReajuste(String nomeProduto) {

        String sql = "SELECT \n"
                + "    id AS 'CÓDIGO',\n"
                + "    nome AS 'DESCRIÇÃO',\n"
                + "    FORMAT(valor, 2, 'de_DE') AS 'VALOR R$'\n"
                + "FROM\n"
                + "    dbbar.tbproduto\n"
                + "WHERE nome LIKE ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%"+nomeProduto+"%");
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.listaProdutoParaReajuste()");
        }

        return rs;
    }

    public boolean reajustaValorProduto(String id, double valorProduto, double fator) {
        DecimalFormat formatador = new DecimalFormat("0.00");
        String sqlUpdate = "UPDATE tbproduto SET valor=? WHERE id=?";
        boolean resp = false;
        //double novoValor = (valorProduto * fator) + valorProduto;
        double novoValor = (valorProduto * fator / 100) + valorProduto;

        try {
            pst = conexao.prepareStatement(sqlUpdate);
            pst.setString(1, formatador.format(novoValor).replaceAll(",", "."));
            pst.setString(2, id);
            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.reajustaValorProduto()" + e);
        }
        return resp;
    }

    // Reajusta valor do produto pelo valor informado
    public void reajustaValorProduto(String id, double valorProduto) {
        DecimalFormat formatador = new DecimalFormat("0.00");
        String sqlUpdate = "UPDATE tbproduto SET valor=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sqlUpdate);
            pst.setString(1, formatador.format(valorProduto).replaceAll(",", "."));
            pst.setString(2, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.reajustaValorProduto() - Valor Direto" + e);
        }

    }

    public boolean reajusteGrupoProduto(String idGrupo, String fator) {
        boolean resp = false;
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
            resp = true;
            while (rs.next()) {
                double valorProduto = Double.parseDouble(rs.getString("tbproduto_valor"));

                reajustaValorProduto(rs.getString("id"), Double.parseDouble(rs.getString("tbproduto_valor")), Double.parseDouble(fator));

            }

        } catch (NumberFormatException | SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.reajusteGrupoProduto()" + e);
        }
        return resp;
    }

    /**
     * Pesquisa o produto informado no parâmetro.
     *
     * @param nome Nome do Produto a ser localizado.
     * @return ResultSet - Contendo o(s) produto(s) localizado(s).
     */
    public ResultSet pesquisarProduto(String nome) {

        String sql = "SELECT p.id as 'CÓDIGO', p.nome as 'DESCRIÇÃO', p.qtd as 'ESTOQUE',format(p.valor,2,'de_DE') as 'VALOR R$', g.nome as 'GRUPO'\n"
                + "FROM tbproduto p\n"
                + "INNER JOIN cad_grupo_produto g on g.id = p.cad_grupo_produto_id\n"
                + "WHERE p.nome LIKE ? AND p.qtd > 0 ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + nome + "%");
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.pesquisarProduto()" + e);
        }
        return rs;

    }

    // Verifica se o produto informado existe e retorna um Boolean
    public boolean temProduto(Produto p) {
        boolean resp = false;
        String sql = "SELECT nome FROM tbproduto WHERE nome=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getNome());
            rs = pst.executeQuery();

            while (rs.next()) {
                resp = true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.temProduto()" + e);
        }
        return resp;
    }

    //Retorna o ncm do produto cadastrado
    public String localizaNCM(Produto p) {
        String sql = "SELECT cod_ncm FROM dbbar.tbproduto where id=?";
        String codigo_ncm = null;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getId());
            rs = pst.executeQuery();
            while (rs.next()) {
                codigo_ncm = rs.getString("cod_ncm");
            }
        } catch (SQLException e) {
        }

        return codigo_ncm;
    }

    /**
     * Retorna o ncm do produto cadastrado
     * @param cod_poduto - Código interno no produto
     * @return Retorna o código NCM
     */
    public String localizaNCM(String cod_poduto) {
        //String sql = "SELECT cod_ncm FROM dbbar.tbproduto where id=?";
        String sql = "SELECT cod_ncm FROM dbbar.tbproduto where cod_produto=?";
        String codigo_ncm = null;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cod_poduto);
            rs = pst.executeQuery();
            while (rs.next()) {
                codigo_ncm = rs.getString("cod_ncm");
            }
        } catch (SQLException e) {
        }

        return codigo_ncm;
    }
    
    //Retorna o código do produto informado
    public String localizaCodigoInterno(String idProduto){
        String sql = "SELECT cod_produto FROM tbproduto WHERE id=?";
        String codProduto=null;
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idProduto);
            rs = pst.executeQuery();
            while (rs.next()){
                codProduto = rs.getString("cod_produto");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.localizaCodigoInterno()"+e);
        }        
        return codProduto;
    }
    
    // Retorna o id do produto selecionado
    // Método chamada na TelaPesquisaProduto
    public String localizaIdProduto(String codigoProduto){
        String sql="SELECT id FROM tbproduto WHERE cod_produto=?";
        String id=null;
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, codigoProduto);
            rs = pst.executeQuery();
            while (rs.next()){
                id = rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.localizaIdProduto()"+e);
        }
        
        return id;
    }
    // Verifica se o código informado existe
    public Boolean isCodigoProduto(String cdProduto){
        
        boolean resp=false;
        
        String sql ="SELECT 1 FROM dbbar.tbproduto where cod_produto=?";
        
        try {
            
            pst=conexao.prepareStatement(sql);
            pst.setString(1, cdProduto);
            rs=pst.executeQuery();
            
            if (rs.next()){
               resp=true;
            }
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.isCodigoProduto()");
        }
        
        return  resp;
        
    }
}
