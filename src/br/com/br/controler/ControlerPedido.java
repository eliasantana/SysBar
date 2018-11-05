/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Mesa;
import br.com.bar.model.Pedido;
import br.com.bar.model.Produto;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elias
 */
public class ControlerPedido {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    ControlerMesa cm = new ControlerMesa();
    
    //Formata data atual para o banco de dados
    public String myDataAtual() {
        Date dataAtual = new Date();
        String data = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            data = df.format(dataAtual.getTime());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return data;
    }

    // Gera Pedido
    public boolean geraPedido(Pedido p) {

        String sql = "INSERT INTO cadpedido (data, status, cadmesa_id, tbcadfuncionario_id) VALUES (?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, p.getData());
            pst.setString(2, p.getStatus());
            pst.setString(3, p.getCadMesaId());
            pst.setString(4, p.getIdFuncionario());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro geraPedido" + e);
        }
        return false;
    }

    public ResultSet listaPedidos(String idFuncionario) {

        String sql = "SELECT \n"
                + "	m.numero_mesa AS 'MESA',\n"
                + "	date_format(p.data,'%d/%m/%Y') AS 'DATA', \n"
                + "	p.status as 'SITUAÇÃO', \n"
                + "	p.id_pedido as 'N. PEDIDO' \n"
                + "FROM cadpedido p \n"
                + "INNER JOIN cadmesa m on m.id=p.cadmesa_id \n"
                + "WHERE p.status=0 AND p.tbcadfuncionario_id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idFuncionario);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro listaPedidos" + e);
        }
        return rs;
    }
    // Lista todos os produtos do pedido informado

    public ResultSet detalhePorPedido(String numeroMesa, String numeroPedido) {

        String sql = "SELECT dbbar.tbproduto.nome AS 'PRODUTO',\n"
                + "      dbbar.detalhe_mesa.tbproduto_id AS 'COD.',\n"
                + "      dbbar.detalhe_mesa.qtd AS 'QTD',\n"
                + "      dbbar.detalhe_mesa.`valorUnit` AS 'VALOR',\n"
                + "      dbbar.detalhe_mesa.`Total` AS 'TOTAL'\n"
                + "      \n"
                + "	FROM dbbar.detalhe_mesa\n"
                + " INNER JOIN dbbar.cadmesa ON \n"
                + "      dbbar.detalhe_mesa.cadmesa_id = dbbar.cadmesa.id \n"
                + " INNER JOIN dbbar.tbproduto ON \n"
                + "    dbbar.detalhe_mesa.tbproduto_id = dbbar.tbproduto.id \n"
                + " WHERE numero_mesa =?   and cadpedido_id_pedido=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroMesa);
            pst.setString(2, numeroPedido);
            rs = pst.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro detalhePedido " + ex);

        }
        return rs;

    }

    public ResultSet detalhePorPedidoId(String numeroMesa, String numeroPedido) {

        String sql = "SELECT dbbar.tbproduto.nome AS 'PRODUTO',\n"
                + "	dbbar.detalhe_mesa.tbproduto_id AS 'COD.',\n"
                + "	dbbar.detalhe_mesa.qtd AS 'QTD',\n"
                + "	dbbar.detalhe_mesa.`valorUnit` AS 'VALOR',\n"
                + "	dbbar.detalhe_mesa.`Total` AS 'TOTAL',\n"
                + "	dbbar.detalhe_mesa.id AS 'COD_ITEM'\n"
                + "FROM dbbar.detalhe_mesa INNER JOIN dbbar.cadmesa ON \n"
                + "	dbbar.detalhe_mesa.cadmesa_id = dbbar.cadmesa.id \n"
                + "INNER JOIN dbbar.tbproduto ON \n"
                + "	dbbar.detalhe_mesa.tbproduto_id = dbbar.tbproduto.id\n"
                + "WHERE numero_mesa =?   and cadpedido_id_pedido=?;";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroMesa);
            pst.setString(2, numeroPedido);
            rs = pst.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro detalhePedido " + ex);

        }
        return rs;

    }

    // Exibe os produtos do pedido inforamado
    public ResultSet detalhePedido(String numeroMesa) {

        String sql = "SELECT dbbar.tbproduto.nome AS 'PRODUTO',\n"
                + "	dbbar.detalhe_mesa.tbproduto_id AS 'COD.',\n"
                + "	dbbar.detalhe_mesa.qtd AS 'QTD',\n"
                + "	dbbar.detalhe_mesa.`valorUnit` AS 'VALOR',\n"
                + "	dbbar.detalhe_mesa.`Total` AS 'TOTAL'\n"
                + "	\n"
                + "FROM dbbar.detalhe_mesa\n"
                + "	INNER JOIN dbbar.cadmesa ON \n"
                + "	 dbbar.detalhe_mesa.cadmesa_id = dbbar.cadmesa.id \n"
                + "	INNER JOIN dbbar.tbproduto ON \n"
                + "	 dbbar.detalhe_mesa.tbproduto_id = dbbar.tbproduto.id \n"
                + "	WHERE numero_mesa = ? AND dbbar.detalhe_mesa.data=curdate()"; // motra produtos adicionados no dia.

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroMesa);

            rs = pst.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro detalhePedido " + ex);

        }
        return rs;

    }

    public boolean temPedido(String idMesa) {

        String sql = "SELECT cadmesa_id FROM cadpedido WHERE cadmesa_id=? AND status=0";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idMesa);
            rs = pst.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro temPedido() " + e);
        }
        return false;
    }

    public boolean fechaPedido(Pedido p) {
        String sql = "UPDATE cadpedido SET status=?, total=?, comissao=?, formaPagto=?,operador=?, autenticacao=? WHERE cadmesa_id=? AND id_pedido=?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, p.getStatus());
            pst.setString(2, p.getTotal());
            pst.setString(3, p.getComissao());
            pst.setString(4, p.getFormaPagto());
            pst.setString(5, p.getOperador());
            pst.setString(6, p.getAutenticacao());
            pst.setString(7, p.getCadMesaId());
            pst.setString(8, p.getId());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro fechaPedido" + e);
        }
        return false;
    }

    // Localiza o número do pedido recebendo o número da mesa como parâmetro
    public String LocalizaIdPedido(String numeroMesa) {
        String idPedido = null;
        String sql = "SELECT m.numero_mesa, p.id_pedido, p.status\n"
                + "FROM cadpedido p \n"
                + "INNER JOIN cadmesa m on m.id=p.cadmesa_id\n"
                + "WHERE numero_mesa = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroMesa);
            rs = pst.executeQuery();

            while (rs.next()) {
                idPedido = rs.getString("id_pedido");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro localizarIdPedido" + e);
        }

        return idPedido;
    }

    public void limpaTabela(JTable tabela) {

        DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
        tableModel.getDataVector().removeAllElements();
        tabela.updateUI();
    }

    // Envia um produto para a cozinha
    public void enviaProdutoCozinha(ArrayList<String> produtoCozinha) {

        String sql = "INSERT INTO tbcozinha (codProduto, produto, qtd, funcionario, mesa, data, status) VALUES (?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, produtoCozinha.get(0));
            pst.setString(2, produtoCozinha.get(1));
            pst.setString(3, produtoCozinha.get(2));
            pst.setString(4, produtoCozinha.get(3));
            pst.setString(5, produtoCozinha.get(4));
            pst.setString(6, produtoCozinha.get(5));
            pst.setString(7, produtoCozinha.get(6));

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto " + produtoCozinha.get(0) + " - " + produtoCozinha.get(1) + " Enviado para a Cozinha");

        } catch (HeadlessException | SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro enviaProdutoCozinha()" + e);
        }

    }

    // Lista o número de todos os pedidos abertos na data atual
    public void carregaComboPedido(JComboBox combo) {
        String sql = "SELECT id_pedido FROM dbbar.cadpedido where data=curdate() and status=0;";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                combo.addItem(rs.getString("id_pedido"));
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.carregaComboPedido()");
        }
    }

    public boolean excluiItemPedido(String idItemPEdido) {
    
       boolean resp=false;
       String sql="DELETE FROM detalhe_mesa WHERE id=?";
       
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idItemPEdido);
            pst.executeUpdate();
            resp=true;
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.excluiItemPedido()");
        }
        return resp;
    }
    
    
    public String autentica(String idFunc, String nMesa, String nPedido, String data){
        String autenticacao="";
        
        autenticacao = idFunc+"."+nMesa+"."+nPedido+"."+data;
        
        return autenticacao;
    }
    
}
