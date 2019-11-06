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
                + "upper(dbbar.tbproduto.nome) AS 'PRODUTO',\n"
                + "      dbbar.detalhe_mesa.qtd AS 'QTD',\n"
                + "      format(dbbar.detalhe_mesa.`valorUnit`,2,'de_DE') AS 'VLR UNITÁRIO R$',\n"
                + "      format(dbbar.detalhe_mesa.`Total`,2,'de_DE') AS 'VLR TOTAL R$'\n"
                + "      \n"
                + "	FROM dbbar.detalhe_mesa\n"
                + " INNER JOIN dbbar.cadmesa ON \n"
                + "      dbbar.detalhe_mesa.cadmesa_id = dbbar.cadmesa.id \n"
                + " INNER JOIN dbbar.tbproduto ON \n"
                + "    dbbar.detalhe_mesa.tbproduto_id = dbbar.tbproduto.id \n"
                //+ " WHERE numero_mesa =?   and cadpedido_id_pedido=? ORDER BY dbbar.detalhe_mesa.id desc";
                + " WHERE numero_mesa =?   and cadpedido_id_pedido=? ORDER BY dbbar.detalhe_mesa.data asc";

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
                + "     upper(dbbar.tbproduto.nome) AS 'PRODUTO',\n"
                + "	dbbar.detalhe_mesa.qtd AS 'QTD',\n"
                + "	format(dbbar.detalhe_mesa.`valorUnit`,2,'de_DE') AS 'VLR UNITÁRIO R$',\n"
                + "	format(dbbar.detalhe_mesa.`Total`,2,'de_DE') AS 'VLR TOTAL R$',\n"
                + "	dbbar.detalhe_mesa.id AS 'CÓD. INTERNO'\n"
                + "FROM dbbar.detalhe_mesa INNER JOIN dbbar.cadmesa ON \n"
                + "	dbbar.detalhe_mesa.cadmesa_id = dbbar.cadmesa.id \n"
                + "INNER JOIN dbbar.tbproduto ON \n"
                + "	dbbar.detalhe_mesa.tbproduto_id = dbbar.tbproduto.id\n"
                + "WHERE numero_mesa =? and cadpedido_id_pedido=? order by dbbar.detalhe_mesa.data asc";
                
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
        String sql = "INSERT INTO tbcozinha (codProduto, produto, qtd, funcionario, mesa, status, npedido,hora_solicitacao,observacao) VALUES (?,?,?,?,?,?,?,?,?)";

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
            pst.setString(9, produtoCozinha.get(8));

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
     * @param idOperador Id do operador que está gerando o pedido.
     * @return id do pedido gerado.
     *
     */
    public String idUltimoPedido(String idOperador) {
        String id = null;
        String sql = " SELECT MAX("
                + " id_pedido\n"
                + "        ) as 'id'\n"
                + "        FROM dbbar\n"
                + "        .cadpedido WHERE status = 0 and tbcadfuncionario_id = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idOperador);
            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getString("id");
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.idUiltimoPedido()" + e);
        }

        return id;
    }

    /**
     * Calcula a permanência do cliente no estabelecimento a contar da ocupação
     * da mesa
     *
     * @param idPedido Número do Pedido.
     * @return Retorna o tempo de pernencia.
     */
    public String calculaPermanencia(String idPedido) {
        String permanencia = "";

        String sql = "SELECT id_pedido, timediff(current_timestamp(), data) as 'permanencia' from cadpedido where id_pedido = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);

            rs = pst.executeQuery();

            while (rs.next()) {
                permanencia = rs.getString("permanencia");

            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.calculaPermanencia()" + e);
        }

        return permanencia;
    }

    /**
     * Grava o detalhe do pagamento
     *
     * @param idPedido - ID do pedido
     * @param dinheiro - Valor pago em dinheiro.
     * @param credito - Valor pago no Cartão de Crédito
     * @param debito - Valor Pago no Cartão de Débito
     * @param voucher - Valor pago em Tikets.
     * @return boolean - Retorna TRUE ou False
     */
    public boolean gravaPagamentoMisto(String idPedido, double dinheiro, double credito, double debito, double voucher) {

        String sql = "INSERT INTO detalhe_pagameto (cadpedido_id_pedido, dinheiro, credito, debito, voucher) VALUES (?,?,?,?,?)";
        boolean resp = false;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            pst.setDouble(2, dinheiro);
            pst.setDouble(3, credito);
            pst.setDouble(4, debito);
            pst.setDouble(5, voucher);

            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.gravaPagamentoMisto()" + e);
        }

        return resp;
    }

    /**
     * Retorna os valores de pagamento para pedidos com forma de pagamento
     * misto.
     *
     * @param idPedido - id do pedido.
     * @return Retorna um Array com os valores pagos.
     */
    public ArrayList retornaVlrPagamentoMisto(String idPedido) {

        String sql = "  SELECT \n"
                + "          dinheiro, credito, debito, voucher\n"
                + "     FROM\n"
                + "     detalhe_pagameto\n"
                + "          WHERE\n"
                + "    cadpedido_id_pedido = ?";
        ArrayList<Double> listaDeValores = new ArrayList<>();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            rs = pst.executeQuery();

            while (rs.next()) {

                listaDeValores.add(rs.getDouble("dinheiro"));
                listaDeValores.add(rs.getDouble("credito"));
                listaDeValores.add(rs.getDouble("debito"));
                listaDeValores.add(rs.getDouble("voucher"));

            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.retornaVlrPagamentoMisto()" + e);
        }

        return listaDeValores;
    }

    //Retorna a forma de pagamento
    public String retornaFormaPagto(String idpedido) {

        String sql = "SELECT formaPagto FROM cadpedido where id_pedido=?";
        String formaPagto = "";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idpedido);
            rs = pst.executeQuery();
            while (rs.next()) {
                formaPagto = rs.getString("formaPagto");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.retornaFormaPagto()" + e);
        }
        return formaPagto;
    }

    /**
     * Localiza um pedido pelo id informado
     *
     * @param idPedido Número do Pedido
     * @return Retorna um objeti do tipo Pedido
     */
    public Pedido localizaPedido(String idPedido) {
        String sql = "SELECT * FROM cadpedido where id_pedido=?";
        Pedido p = new Pedido();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            rs = pst.executeQuery();

            while (rs.next()) {
                p.setId(rs.getString("id_pedido"));
                p.setData(rs.getString("data"));
                p.setStatus(rs.getString("status"));
                p.setTotal(rs.getString("total"));
                p.setComissao(rs.getString("comissao"));
                p.setFormaPagto(rs.getString("formaPagto"));
                p.setCadMesaId(rs.getString("cadmesa_id"));
                p.setIdFuncionario(rs.getString("tbcadfuncionario_id"));
                p.setOperador(rs.getString("operador"));
                p.setAutenticacao(rs.getString("autenticacao"));
                p.setPermanencia(rs.getString("permanencia"));
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.localizaPedido()");
        }
        return p;

    }

    // Verifica se o pedido possui desconto
    public String temDesconto(String idPedido) {

        String sql = "SELECT id FROM tbdesconto_pedido WHERE cadpedido_id_pedido=?";
        String idDesconto = null;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            rs = pst.executeQuery();
            while (rs.next()) {
                idDesconto = rs.getString("id");
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.temDesconto()");
        }
        return idDesconto;
    }

    // Exclui o detalhamento de pagamento do pedido informado.
    public boolean excluiDetalhePagamento(String idPedido) {
        // Localiza o identificador do detalhe do pagamento
        String sql = "SELECT id FROM detalhe_pagameto WHERE cadpedido_id_pedido=?";
        boolean resp = false;
        String id = null;
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            rs = pst.executeQuery();
            while (rs.next()) {
                // recupera o id do pedido
                id = rs.getString("id");
                resp = true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.excluiDetalhePagamento()");

        }
        // Deleta o detalhe do pagamento do pedido informado
        String deleta = "DELETE FROM detalhe_pagameto WHERE id=" + id;
        try {
            pst = conexao.prepareStatement(deleta);
            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.excluiDetalhePagamento()");

        }

        return resp;

    }

    //Exclui desconto para o pedido informado
    public boolean excluiDesconto(String id) {
        boolean resp = false;
        String sql = "DELETE FROM tbdesconto_pedido WHERE id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.excluiDesconto()" + e);

        }
        return resp;
    }

    /**
     * Verifica se o pedido possui cancelamento
     *
     * @param nPedido Número do pedido
     * @return Boolean Retorna TRUE ou FALSE
     */
    public boolean foiCancelado(String nPedido) {
        String sql = "SELECT id FROM tbhistorico_cancelamento WHERE numero_cupom=?";
        boolean resp = false;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nPedido);
            rs = pst.executeQuery();
            while (rs.next()) {
                resp = true;
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.foiCancelado()" + e);
        }

        return resp;
    }

    /**
     * Localiza o id do detalhe pagamento para o pedido informado
     *
     * @param idPedido Número do Pedido
     * @return String Retorna o id od pedido
     */
    public String localizaIdDetalheDesconto(String idPedido) {
        String sql = "SELECT id FROM detalhe_pagameto WHERE cadpedido_id_pedido=?";
        String resp = null;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            rs = pst.executeQuery();
            while (rs.next()) {
                resp = rs.getString("id");
            }
        } catch (SQLException e) {

        }
        return resp;
    }

    /**
     * Verifica se o pedido informado está no Delivery e devolve o ID do pedido
     * na tabela Delivery
     *
     * @param idPedido Número do Pedido
     * @return idDelivery Retorna o id do pedido na tabela develivery
     */
    public String estaNoDelivery(String idPedido) {

        String sql = "SELECT id FROM tbdelivery WHERE cadpedido_id_pedido=?";
        String idDelivery = null;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            rs = pst.executeQuery();
            while (rs.next()) {
                idDelivery = rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.estaNoDelivery()" + e);
        }
        return idDelivery;
    }

    /**
     * Verifica se o pedido informado esta na Tabela Histórico de Delivery
     *
     * @param idPedido Número do Pedido
     * @return Retorna TRUE ou FALSE
     */
    public String estaNoHistoricoDelivery(String idPedido) {

        String sql = "SELECT id FROM tbhistoricodelivery WHERE cadpedido_id_pedido=?";
        String idPedidoHistorico = null;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            rs = pst.executeQuery();
            while (rs.next()) {
                idPedidoHistorico = rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.estaNoHistoricoDelivery()" + e);
        }
        return idPedidoHistorico;
    }

    /**
     * Move o pedido da tabela histórico para a tabela Delivery e em sequencia
     * apaga-o da tabela histórica Este método é utilizado quando um peido é
     * extornado
     *
     * @param idPedido Número do Pedido
     * @return boolean Retorna TRUE ou FALSE
     */
    public boolean moveHistoricoParaDelivery(String idPedido) {
        boolean resp = false;
        String sql = "INSERT INTO tbdelivery (SELECT * FROM tbhistoricodelivery WHERE cadpedido_id_pedido=?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idPedido);
            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.moveHistoricoParaDelivery()" + e);
        }
        // Se o pedido for movido com sucesso ele será excluído da Tabela Histórica
        if (resp) {
            String sqlDelete = "DELETE FROM tbdelivery WHERE id=?";
            try {
                pst = conexao.prepareStatement(sqlDelete);
                pst.setString(1, idPedido);
                pst.executeUpdate();

            } catch (SQLException e) {
                System.out.println("br.com.br.controler.ControlerPedido.moveHistoricoParaDelivery()" + e);
            }

        }
        return resp;
    }

    /**
     * Deleta um registro informando a tabelae o id do registro a ser excluída
     *
     * @param tabela Nome da Tabela onde o registro será excluído
     * @param id Id do Registro a ser excluído
     * @return Retorna TRUE ou FALSE
     */
    public boolean deletaRegistro(String tabela, String id) {
        boolean resp = false;
        String sql = "DELETE FROM " + tabela + " WHERE id=?";
        System.out.println(sql);
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerPedido.deletaRegistro()" + e);
        }

        return resp;
    }

    /**
     * Extorna o pedino Delivery
     *
     * @param idDelivery ID do pedido Delivery a ser Extornado
     * @return Retorna TRUE ou FALSE
     */
    public boolean extornaDelivery(String idDelivery) {
        boolean resp=false;
        String sql = "UPDATE tbdelivery \n"
                + "SET \n"
                + "    total_pedido = NULL,\n"
                + "    tx_servico = NULL,\n"
                + "    tx_entrega = NULL,\n"
                + "    total_geral = NULL,\n"
                + "    hora_saida = NULL,\n"
                + "    entregador = NULL\n"
                + "WHERE\n"
                + "    id = ?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idDelivery);
            pst.executeUpdate();
            resp=true;
            
        } catch (SQLException e) {
            //System.out.println("br.com.br.controler.ControlerPedido.extornaDelivery()"+e);            
        }
        return resp;
    }

    /**
     * Extorna um pedido excluindo seu desconto e se houver também as formas de
     * pagamento.
     *
     * @param idPedido Número do Pedido
     * @param operador Nome do operador
     * @return String Retorna uma String com a mesagem de erro
     */
    public String extornaPedido(String idPedido, String operador) {
        String msg = null;

        Pedido p = localizaPedido(idPedido);
        // Retorna desconto do pedido
        String idDesconto = temDesconto(p.getId());
        // Localiza id o número da mesa
        String nMesa = cm.localizaNumeroMesa(p.getId());
        // Verifica se o pedido está vazio (Null)
        if (cm.estaLivre(nMesa)) {

            if (p.getId() != null && !"".equals(nMesa)) {
                if (idDesconto != null) {
                    if (excluiDesconto(idDesconto)) {

                    }
                }
                if ("MISTO".equals(p.getFormaPagto())) {
                    if (excluiDetalhePagamento(p.getId())) {

                    }
                }
                // Muda o status  do pedido para 0 (zero) = Aberto e limpa valores
                try {
                    String sql = "UPDATE cadpedido SET status='0', total=null, comissao=null, formaPagto=null, operador=null, autenticacao=null, permanencia=null WHERE id_pedido=?";
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, p.getId());
                    pst.executeUpdate();
                    // System.out.println("Pedido Extornado!");
                } catch (SQLException e) {

                }
                // Muda status da mesa
                if (cm.trocaStatusMesa(nMesa, "1")) {
                    // System.out.println("Mesa retornada ao estado anterior (Ocupada)");
                }
                String idPedidoDelivery;
                // Verifica se o pedido é um delivery e armazena seu ID
                idPedidoDelivery = estaNoDelivery(idPedido);
                // Verifica se o pedido está na tabela histórica e devolve seu ID
                String idHistoricoDelivery = estaNoHistoricoDelivery(idPedido);

                if (idHistoricoDelivery != null) {
                    if (moveHistoricoParaDelivery(idPedido)) {
                        // Deleta o registro após remoção 
                        if (deletaRegistro("tbhistoricodelivery", idHistoricoDelivery)) {
                            idPedidoDelivery = estaNoDelivery(idPedido);
                            //Extorna pedido no Delivery
                            if (extornaDelivery(idPedidoDelivery)){
                                
                            }
                        }
                    }
                }

            } else {
                msg = "O número do Pedido ou o Número da mesa são inválidos!";
            }
        } else {
            msg = "A Mesa " + nMesa + " está ocupada e por isso não é possível extornar este pedido!\nPorfavor aguarde a mesa " + nMesa + " finalizar o seu pedido para continuar!";
        }
        return msg;
    }

}
