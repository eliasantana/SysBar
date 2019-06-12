/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Pedido;
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
            System.out.println("br.com.br.controler.ControlerPedido.myDataAtual()" + e);
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
            System.out.println("br.com.br.controler.ControlerPedido.geraPedido()" + e);
        }
        return false;
    }

    public ResultSet listaPedidos() {

        String sql = "SELECT\n"
                + "	m.numero_mesa AS 'N. MESA',\n"
                + "	p.id_pedido as 'N. PEDIDO',\n"
                + "	date_format(p.data,'%d/%m/%Y') AS 'DATA',\n"
                + "	CASE WHEN p.status=0 THEN 'Aberto'\n"
                + "	ELSE p.status \n"
                + "	END as 'STATUS', \n"
                + "	g.nome as 'GARÇOM' \n"
                + "FROM cadpedido p \n"
                + "	INNER JOIN cadmesa m on m.id=p.cadmesa_id \n"
                + "	INNER JOIN tbcadfuncionario g on g.id = p.tbcadfuncionario_id\n"
                + "WHERE p.status=0;	";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.listaPedidos()" + e);
        }
        return rs;
    }

    public ResultSet listaPedidos(String idFuncionario) {

        String sql = "SELECT\n"
                + "	m.numero_mesa AS 'MESA',\n"
                + "	p.id_pedido as 'PEDIDO',\n"
                + "	date_format(p.data,'%d/%m/%Y') AS 'DATA',\n"
                + "	CASE WHEN p.status=0 THEN 'Aberto'\n"
                + "	ELSE p.status \n"
                + "	END as 'STATUS', \n"
                + "	g.nome as 'GARÇOM' \n"
                + "FROM cadpedido p \n"
                + "	INNER JOIN cadmesa m on m.id=p.cadmesa_id \n"
                + "	INNER JOIN tbcadfuncionario g on g.id = p.tbcadfuncionario_id\n"
                + "WHERE p.status=0 AND p.tbcadfuncionario_id=?;	";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idFuncionario);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.listaPedidos()" + e);
        }
        return rs;
    }
    // Lista todos os Pedidos do dia com statuss aberto ordenados de forma decrescente.

    public ResultSet listaPedidosReimpressao(String numeroMesa, String data) {

        String sql = "SELECT\n"
                + "	m.numero_mesa as 'MESA',\n"
                + "	p.id_pedido AS 'PEDIDO',\n"
                + "	format(p.comissao,2,'de_DE') as 'TX. SERVIÇO R$',	\n"
                + "	format(dp.valor_desconto,2,'de_DE') as 'DESCONTO R$',\n"
                + "    format(p.total,2,'de_DE') as 'TOTAL R$',   \n"
                + "    f.nome as 'GARÇOM'\n"
                + "FROM cadpedido p\n"
                + "	INNER JOIN cadmesa m on m.id = p.cadmesa_id\n"
                + "    INNER JOIN tbdesconto_pedido dp on dp.cadpedido_id_pedido = p.id_pedido\n"
                + "    INNER JOIN tbcadfuncionario f on f.id = p.tbcadfuncionario_id\n"
                + "	WHERE date_format(p.data,'%Y-%m-%d') = ? AND p.status=1 AND m.numero_mesa LIKE ? ORDER BY p.id_pedido desc;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, data);
            pst.setString(2, numeroMesa + "%");
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerPedido.listaPedidosReimpressao()" + ex);
        }
        return rs;

    }
    // Lista todos os produtos do pedido informado

    public ResultSet detalhePorPedido(String numeroMesa, String numeroPedido) {
        // Lista todos os produtos do pedido para a mesa informada ordenados por id
        // Em ordem Decrescente
        // Nesta Query os produtos são listados de forma unidatária sem agrupamento
        // De suas quantidades.

        String sql = "SELECT "
                + "      dbbar.detalhe_mesa.tbproduto_id AS 'CÓDIGO',\n"
                + "dbbar.tbproduto.nome AS 'PRODUTO',\n"
                + "      dbbar.detalhe_mesa.qtd AS 'QTD',\n"
                + "      format(dbbar.detalhe_mesa.`valorUnit`,2,'de_DE') AS 'VLR UNITÁRIO R$',\n"
                + "      format(dbbar.detalhe_mesa.`Total`,2,'de_DE') AS 'VLR TOTAL R$'\n"
                + "      \n"
                + "	FROM dbbar.detalhe_mesa\n"
                + " INNER JOIN dbbar.cadmesa ON \n"
                + "      dbbar.detalhe_mesa.cadmesa_id = dbbar.cadmesa.id \n"
                + " INNER JOIN dbbar.tbproduto ON \n"
                + "    dbbar.detalhe_mesa.tbproduto_id = dbbar.tbproduto.id \n"
                + " WHERE numero_mesa =?   and cadpedido_id_pedido=? ORDER BY dbbar.detalhe_mesa.id desc";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroMesa);
            pst.setString(2, numeroPedido);
            rs = pst.executeQuery();
            
            

        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerPedido.detalhePorPedido()" + ex);

        }
        return rs;

    }

    public ResultSet detalhePorPedidoId(String numeroMesa, String numeroPedido) {

        String sql = "SELECT "
                + "	dbbar.detalhe_mesa.tbproduto_id AS 'CÓD',\n"
                + "dbbar.tbproduto.nome AS 'PRODUTO',\n"
                + "	dbbar.detalhe_mesa.qtd AS 'QTD',\n"
                + "	format(dbbar.detalhe_mesa.`valorUnit`,2,'de_DE') AS 'VLR UNITÁRIO R$',\n"
                + "	format(dbbar.detalhe_mesa.`Total`,2,'de_DE') AS 'VLR TOTAL R$',\n"
                + "	dbbar.detalhe_mesa.id AS 'CÓD. INTERNO'\n"
                + "FROM dbbar.detalhe_mesa INNER JOIN dbbar.cadmesa ON \n"
                + "	dbbar.detalhe_mesa.cadmesa_id = dbbar.cadmesa.id \n"
                + "INNER JOIN dbbar.tbproduto ON \n"
                + "	dbbar.detalhe_mesa.tbproduto_id = dbbar.tbproduto.id\n"
                + "WHERE numero_mesa =?   and cadpedido_id_pedido=? order by dbbar.detalhe_mesa.id desc";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroMesa);
            pst.setString(2, numeroPedido);
            rs = pst.executeQuery();

        } catch (SQLException ex) {
            System.out.println("br.com.br.controler.ControlerPedido.detalhePorPedidoId()" + ex);

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
            System.out.println("br.com.br.controler.ControlerPedido.detalhePedido()" + ex);

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
            System.out.println("br.com.br.controler.ControlerPedido.temPedido()" + e);
        }
        return false;
    }

    // Fecha o pedido armazenando informações do pagamento, da mesa e do pedido,
    // atualizando a data do pedido para a data atual do recebimento
    public boolean fechaPedido(Pedido p) {
        String sql = "UPDATE cadpedido SET status=?, total=?, comissao=?, formaPagto=?,operador=?, autenticacao=?, data=?, permanencia=? WHERE cadmesa_id=? AND id_pedido=?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, p.getStatus());
            pst.setString(2, p.getTotal());
            pst.setString(3, p.getComissao());
            pst.setString(4, p.getFormaPagto());
            pst.setString(5, p.getOperador());
            pst.setString(6, p.getAutenticacao());
            pst.setString(7, p.getData());
            pst.setString(8, p.getPermanencia());
            pst.setString(9, p.getCadMesaId());
            pst.setString(10, p.getId());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.fechaPedido()" + e);
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
            System.out.println("br.com.br.controler.ControlerPedido.LocalizaIdPedido()" + e);
        }

        return idPedido;
    }

    public void limpaTabela(JTable tabela) {

        DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
        tableModel.getDataVector().removeAllElements();
        tabela.updateUI();
    }

    // Envia um produto para a cozinha
    public boolean enviaProdutoCozinha(ArrayList<String> produtoCozinha) {
        boolean resp = false;
        String sql = "INSERT INTO tbcozinha (codProduto, produto, qtd, funcionario, mesa, status, npedido,hora_solicitacao) VALUES (?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, produtoCozinha.get(1));
            pst.setString(2, produtoCozinha.get(0));
            pst.setString(3, produtoCozinha.get(2));
            pst.setString(4, produtoCozinha.get(3));
            pst.setString(5, produtoCozinha.get(4));
            pst.setString(6, produtoCozinha.get(5));
            pst.setString(7, produtoCozinha.get(6));
            pst.setString(8, produtoCozinha.get(7));

            pst.executeUpdate();
            resp = true;

        } catch (HeadlessException | SQLException e) {

            System.out.println("br.com.br.controler.ControlerPedido.enviaProdutoCozinha()" + e);
        }
        return resp;
    }

    // Lista o número de todos os pedidos abertos 
    public void carregaComboPedido(JComboBox combo) {
        String sql = "SELECT id_pedido FROM dbbar.cadpedido where  status=0;";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            combo.removeAllItems();
            combo.addItem("Selecione...");
            while (rs.next()) {
                combo.addItem(rs.getString("id_pedido"));
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.carregaComboPedido()");
        }
    }

    // Remove um item do pedido.
    public boolean excluiItemPedido(String idItemPEdido) {

        boolean resp = false;
        String sql = "DELETE FROM detalhe_mesa WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idItemPEdido);
            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.excluiItemPedido()");
        }
        return resp;
    }

    public String autentica(String idFunc, String nMesa, String nPedido, String data) {
        String autenticacao = "";

        autenticacao = idFunc + "." + nMesa + "." + nPedido + "." + data;

        return autenticacao;
    }

    // Cancela o pedido informado no parâmetro
    public boolean cancelaPedido(int numeroPedido) {

        String sql = "DELETE FROM cadpedido WHERE id_pedido=?";
        boolean resp = false;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, numeroPedido);
            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.cancelaPedido()" + e);
            JOptionPane.showMessageDialog(null, "Obrigatório remover todos os itens antes de cancelar o pedido!");
        }

        return resp;
    }

    // Atualiza a quantidade do item no pedido
    public void atualizaQtdItem(String idItemPedido, int qtd, Double total) {

        String sql = "UPDATE detalhe_mesa SET qtd=?, total=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, qtd);
            pst.setDouble(2, total);
            pst.setString(3, idItemPedido);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.atualizaQtdItem()" + e);
        }
    }

    /**
     * Retorna código do pedido aberto pelo operador
     *
     * @param idOperador  Id do operador que está gerando o pedido.
     * @return id do pedido gerado.
     *
     */
    public String idUltimoPedido(String idOperador) {
        String id=null;
        String sql = " SELECT MAX("
                + " id_pedido\n"
                + "        ) as 'id'\n"
                + "        FROM dbbar\n"
                + "        .cadpedido WHERE status = 0 and tbcadfuncionario_id = ?";
        
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idOperador);
            rs = pst.executeQuery();
            while (rs.next()){
                id = rs.getString("id");
            }
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.idUiltimoPedido()"+e);
        }
        
        return  id;
    }
    /**
     * Calcula a permanência do cliente no estabelecimento a contar da ocupação da mesa
     * @param idPedido Número do Pedido.
     * @return Retorna o tempo de pernencia.
     */
    public String calculaPermanencia(String idPedido){
        String permanencia="";
        
        String sql="SELECT id_pedido, timediff(current_timestamp(), data) as 'permanencia' from cadpedido where id_pedido = ?";      
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            
            rs = pst.executeQuery();
            
            while (rs.next()){
                permanencia = rs.getString("permanencia");
                
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.calculaPermanencia()"+e);
        }
        
        return permanencia;
    }
}
