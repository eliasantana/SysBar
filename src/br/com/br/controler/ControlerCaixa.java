/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.dao.Log;
import br.com.bar.dao.ReportUtil;
import br.com.bar.model.DadosEmpresa;
import br.com.bar.model.DescontoPedido;
import br.com.bar.model.MovimentacaoCaixa;
import br.com.bar.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author elias
 */
public class ControlerCaixa {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    Util u = new Util();
    Log l = new Log();

    ControlerFuncionario cf = new ControlerFuncionario();

    // Lista Mesas Ocupadas
    public void listaMesaOcupada(JComboBox combo) {

        String sql = "SELECT numero_mesa FROM cadmesa WHERE status='1'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            combo.removeAllItems();
            combo.addItem("Selecione...");
            while (rs.next()) {
                combo.addItem(rs.getString("numero_mesa"));
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.listaMesaOcupada()" + e);
        }
    }

    public double totalizaSaida() {

        String sql = "SELECT sum(valor_pagto) as 'saidas' FROM dbbar.tbcontas_a_pagar where data_pagto=curdate()";
        double totalSaidas = 0;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("saidas") == null) {
                    totalSaidas = 0.0;
                } else {

                    totalSaidas = Double.parseDouble(rs.getString("saidas"));
                }
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.totalizaSaida()" + e);

        }
        return totalSaidas;
    }

    // Totaliza saídas por operador
    public double totalizaSaida(String operador) {

        String sql = "SELECT sum(valor_pagto) as 'saidas' FROM dbbar.tbcontas_a_pagar where date_format(data_pagto,'%Y-%m-%d')=curdate() AND operador=?";
        double totalSaidas = 0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, operador);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("saidas") == null) {
                    totalSaidas = 0.0;
                } else {

                    totalSaidas = Double.parseDouble(rs.getString("saidas"));
                }
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.totalizaSaida()" + e);

        }
        return totalSaidas;
    }

    // Totaliza saídas por operador na data Informada
    public double totalizaSaida(String operador, String data) {

        String sql = "SELECT sum(valor_pagto) as 'saidas'FROM dbbar.tbcontas_a_pagar where date_format(data_pagto,'%Y-%m-%d')=? AND operador=?";
        double totalSaidas = 0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, data);
            pst.setString(2, operador);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("saidas") == null) {
                    totalSaidas = 0.0;
                } else {

                    totalSaidas = Double.parseDouble(rs.getString("saidas"));
                }
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.totalizaSaida()" + e);

        }
        return totalSaidas;
    }

    // Totaliza entradas na data atual para 
    public double totalizaEntradas() {
        String sql = "SELECT sum(total) as 'total' FROM dbbar.cadpedido where data = curdate()";
        //String sql = "SELECT sum(total) as 'total' FROM dbbar.detalhe_mesa where data=curdate()";

        double total = 0;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("total") == null) {
                    total = 0.0;
                } else {

                    total = Double.parseDouble(rs.getString("total"));
                }
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.totalizaEntradas()" + e);
        }
        return total;
    }


    /*
        Totaliza entradas por operador
     */
    public double totalizaEntradas(String operador) {
        String sql = "SELECT sum(total) as 'total' FROM dbbar.cadpedido where date_format(data,'%Y-%m-%d') = curdate() AND operador=?";

        double total = 0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, operador);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("total") == null) {
                    total = 0.0;
                } else {

                    total = Double.parseDouble(rs.getString("total"));
                }
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.totalizaEntradas()" + e);

        }
        return total;
    }

    /*
        Totaliza entradas por operador
     */
    public double totalizaEntradas(String operador, String data) {
        String sql = "SELECT sum(total) as 'total' FROM dbbar.cadpedido where data = ? AND operador=?";

        double total = 0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, data);
            pst.setString(2, operador);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("total") == null) {
                    total = 0.0;
                } else {

                    total = Double.parseDouble(rs.getString("total"));
                }
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.totalizaEntradas()" + e);

        }
        return total;
    }

    /*
        Totaliza as entradas realizadas em especie do operador locago;.
     */
    public double totalizaTipoEntrada(String operador, String formaPagto) {
        String sql = "SELECT sum(total) as '" + formaPagto + "' FROM dbbar.cadpedido where date_format(data,'%Y-%m-%d') = curdate() AND operador=? AND formaPagto=?";

        double total = 0;
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, operador);
            pst.setString(2, formaPagto);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString(formaPagto) == null) {
                    total = 0.0;
                } else {

                    total = Double.parseDouble(rs.getString(formaPagto));
                }
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.totalizaEntradas()" + e);

        }
        return total;
    }

    // Excluir depois de validar alterações
    // Grava movimentação 
    public boolean gravaMovimentacao(MovimentacaoCaixa m) {
        boolean resp = false;
        String sql = "INSERT INTO tbmovimentacao (data, entradas, saidas, saldo, status, tbcadfuncionario_id) VALUES (?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, m.getData());
            pst.setDouble(2, m.getEntrada());
            pst.setDouble(3, m.getSaida());
            pst.setDouble(4, m.getSaldo());
            pst.setInt(5, m.getStatus());
            pst.setInt(6, m.getIdFuncionario());

            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.gravaMovimentacao()" + e);
        }

        return resp;
    }

    // Atualiza valores de movimentação do caixa informado
    public boolean atualizaMovimentacao(MovimentacaoCaixa m) {
        boolean resp = false;
        String sql = "UPDATE tbmovimentacao SET entradas=?, saidas=?, saldo=?, status=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, m.getEntrada());
            pst.setDouble(2, m.getSaida());
            pst.setDouble(3, m.getSaldo());
            pst.setInt(4, m.getStatus());
            pst.setInt(5, m.getId());

            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.atualizaMovimentacao()" + e);
        }

        return resp;

    }

    // Checa se existe movimentação
    public boolean temMovimentacao(int idOperador) {
        boolean resp = false;

        String sql = "SELECT * FROM tbmovimentacao WHERE data=curdate() AND tbcadfuncionario_id =?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idOperador);
            rs = pst.executeQuery();

            while (rs.next()) {
                resp = true;
            }
        } catch (SQLException e) {

            System.out.println("br.com.br.controler.ControlerCaixa.temMovimentacao()" + e);
        }

        return resp;
    }

    public boolean temMovimentacao(int idOperador, String data) {
        boolean resp = false;

        String sql = "SELECT * FROM tbmovimentacao WHERE data=? AND tbcadfuncionario_id = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, data);
            pst.setInt(2, idOperador);
            rs = pst.executeQuery();

            while (rs.next()) {
                resp = true;
            }
        } catch (SQLException e) {

            System.out.println("br.com.br.controler.ControlerCaixa.temMovimentacao()" + e);
        }

        return resp;
    }
    // Lista todos os caixas fechados

    public ResultSet listaCaixa() {
// Excluir após validação do caixa

//        String sql = "SELECT m.id AS 'CÓD. INTERNO', date_format(m.data,'%d/%m/%Y') AS 'DATA', format(m.saldo,2,'de_DE') AS 'SALDO R$', f.nome AS 'OPERADOR' FROM dbbar.tbmovimentacao m\n"
//                + "INNER JOIN tbcadfuncionario f on f.id = m.tbcadfuncionario_id\n"
//                + "WHERE data=curdate();";
        String sql = "SELECT \n"
                + "    m.id AS 'CÓD. INTERNO',\n"
                + "    DATE_FORMAT(m.data, '%d/%m/%Y') AS 'DATA',\n"
                + "    FORMAT(m.saldo, 2, 'de_DE') AS 'SALDO R$',\n"
                + "    f.nome AS 'OPERADOR'\n"
                + "FROM\n"
                + "    dbbar.tbmovimentacao m\n"
                + "        INNER JOIN\n"
                + "    tbcadfuncionario f ON f.id = m.tbcadfuncionario_id\n"
                + "WHERE\n"
                + "    data = CURDATE() AND m.status = 1;";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.listaCaixa()" + e);
        }

        return rs;
    }

    public boolean liberaCaixa(String id) {
        boolean resp = false;
        // Excluir após validação de caixa
        //String sql = "DELETE FROM tbmovimentacao WHERE id=?";
        String sql = "UPDATE tbmovimentacao SET status=0 WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.liberaCaixa()" + e);
        }
        return resp;
    }

    // Lista a movimentação do período e retorna um ResultSet
    public ResultSet listaMovimentacao(String mes) {

        String sql = "SELECT id, date_format(data,'%d/%m') as 'data', sum(entradas) as 'entradas', sum(saidas) as 'saidas', sum(saldo) as 'saldo' \n"
                + "FROM dbbar.tbmovimentacao\n"
                + "WHERE month(data)=?\n"
                + "GROUP BY data";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, mes);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println("Erro listaMovimentacao" + e);
        }

        return rs;
    }

    // Este método lista a totalização de vendas de todos os gaçoms no período
    public ResultSet rakingDeVendas(String dataInicial, String dataFinal) {

        String sql = "SELECT f.nome, sum(dt.total) as 'total' FROM dbbar.detalhe_mesa dt\n"
                + "INNER JOIN tbcadfuncionario f on f.id = dt.cadpedido_tbcadfuncionario_id\n"
                + "WHERE data between ? AND ?\n"
                + "GROUP BY f.nome;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataInicial);
            pst.setString(2, dataFinal);
            rs = pst.executeQuery();

        } catch (SQLException e) {

            System.out.println("Erro ao gerarRanking de vendas");
        }

        return rs;
    }

    /**
     * Troca a imagem do Jlabel Informado
     *
     * @param label Jlabel que receberá a imagem
     * @param status Informar TRUE para Fechado
     * @param msg Mensagem
     */
    public void statusCaixa(JLabel label, boolean status, JLabel msg) {

        if (status) {
            label.setIcon(new ImageIcon(getClass().getResource("/br/com/bar/imagens/btnCancel.png")));
            msg.setText("Caixa Fechado");
        } else {
            label.setIcon(new ImageIcon(getClass().getResource("/br/com/bar/imagens/btnOk.png")));
            msg.setText("Caixa Aberto");
        }

    }

    /**
     * Troca a imagem do Jlabel Informado
     *
     * @param label Jlabel que receberá a imagem
     * @param status Informar TRUE para Fechado
     * @param msg Mensagem
     */
    public void statusCaixa(JLabel label, int status, JLabel msg) {

        if (status == 1) {
            label.setIcon(new ImageIcon(getClass().getResource("/br/com/bar/imagens/btnCancel.png")));
            msg.setText("Caixa Fechado");
        } else {
            label.setIcon(new ImageIcon(getClass().getResource("/br/com/bar/imagens/btnOk.png")));
            msg.setText("Caixa Aberto");
        }

    }

    /**
     * Retorna o Status do caixa do operador logado
     *
     * @param idFuncionario ID do funcionário logado
     * @return Retorna Inteiro 0 (CAIXA ABERTO) 1 (CAIXA FECHADO)
     */
    public int retornaStatusCaixa(int idFuncionario) {
        int status = 0;
        String sql = "SELECT status FROM tbmovimentacao WHERE data=curdate() AND tbcadfuncionario_id=?";
        try {

            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idFuncionario);
            rs = pst.executeQuery();

            while (rs.next()) {
                status = rs.getInt("status");
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.retornaStatusCaixa()" + e);
        }

        return status;

    }
    // Retorna o saldo inicial do usuário logado.
    public double retornaSaldoInicial(String idFuncionario){
        double saldoIni = 0;
        String sql="SELECT saldo_inicial FROM tbmovimentacao WHERE data=curdate() AND tbcadfuncionario_id=?";
        try {
            conexao=ConexaoBd.conector();          
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idFuncionario);
            rs=pst.executeQuery();
            while (rs.next()){
                saldoIni = rs.getDouble("saldo_inicial");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.retornaSaldoInicial()"+e);
        }
        return saldoIni;
    }

    public Boolean temMovAnterior(JComboBox comboFunc, JButton btn) {
        boolean resp = false;
        try {

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_WEEK, -1);
            String dataAnterior = u.formataDataBanco(c.getTime());
            Double saida = totalizaSaida(comboFunc.getSelectedItem().toString(), dataAnterior);
            Double entrada = totalizaEntradas(comboFunc.getSelectedItem().toString(), dataAnterior);
            Double saldo = entrada - saida;
            if (0 != saida || 0 != entrada) {
                btn.setEnabled(true);
            } else {
                btn.setEnabled(false);
            }
            resp = true;

        } catch (Exception e) {
            System.out.println("br.com.bar.view.TelaGerenciamentoDeCaixa.temMovAnterior()" + e);
        }
        return resp;
    }

    public Boolean temMovAnterior(String operador) {
        boolean resp = false;
        try {

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_WEEK, -1);
            String dataAnterior = u.formataDataBanco(c.getTime());
            Double saida = totalizaSaida(operador, dataAnterior);
            Double entrada = totalizaEntradas(operador, dataAnterior);
            Double saldo = entrada - saida;
            System.out.println("Entradas:" + entrada);
            System.out.println("SAída:" + saida);
            System.out.println("SAldo:" + saldo);

            if (0 != saida || 0 != entrada) {

                resp = true;
            }

        } catch (Exception e) {
            System.out.println("br.com.bar.view.TelaGerenciamentoDeCaixa.temMovAnterior()" + e);
        }
        return resp;
    }
    // Fecha caixa Anterior para o operador informado

    public void fechaCaixaAnterior(String operador, String id) {
        // Pega a data atual
        Calendar c = Calendar.getInstance();
        // Volta um dia na data
        c.add(Calendar.DAY_OF_WEEK, -1);
        // Formata a data
        String dataAnterior = u.formataDataBanco(c.getTime());
        // Realiza o cálculo das saídas na data anterior
        Double saida = totalizaSaida(operador, dataAnterior);
        // Realiza o cálculo das entradas na data enterior
        Double entrada = totalizaEntradas(operador, dataAnterior);
        // Calcula do saldo
        Double saldo = entrada - saida;
        //Localiza o 'ID' do usuário logado no caixa
        int idFunc = Integer.parseInt(id);
        // Verifica se existe movimentação enterior para este usuário
        if (temMovAnterior(operador)) {

            MovimentacaoCaixa cx = new MovimentacaoCaixa();
            cx.setData(dataAnterior);
            cx.setEntrada(entrada);
            cx.setSaida(saida);
            cx.setSaldo(entrada - saida);
            cx.setSaldo(saldo);

            cx.setStatus(1);// 1 - Fechado - 0 - Aberto
            cx.setIdFuncionario(idFunc);
            if (temMovimentacao(idFunc, dataAnterior)) {

            } else {

                if (gravaMovimentacao(cx)) {
                    JOptionPane.showMessageDialog(null, "Caixa Anterior fechado com sucesso!");
                    // Inicio do Registro de Log
                    l.setFuncionalidade("Caixa");
                    l.setUsuario(operador);
                    l.setDescricao(" Realizou um Fehamento Retroativo de Caixa");
                    l.gravaLog(l);
                    // Fim do Registro de Log

                    // Imprime relatório de caixa 
                    HashMap param = new HashMap();
                    param.put("data", dataAnterior);
                    param.put("id_perador", cx.getIdFuncionario());
                    param.put("titulo", "=-=-=-= Retroativo =-=-=-=");
                    ControlerDadosEmpresa de = new ControlerDadosEmpresa();
                    DadosEmpresa dadosEmpresa = de.selecionaDados();
                    param.put("empresa", dadosEmpresa.getNome_empresa());
                    ReportUtil rpu = new ReportUtil();
                    try {

                        if (dadosEmpresa.getImprimir_na_tela() == 0) {

                            // Já existe acima DadosEmpresa dados_empresa = de.selecionaDados();// Retorna dadados da empresa
                            rpu.imprimeRelatorioTela("relMovimentacaoOperador.jasper", rpu.rodape(dadosEmpresa, param), "Movimentação de Caixa");

                        } else {
                            rpu.impressaoDireta("relMovimentacaoOperador.jasper", rpu.rodape(dadosEmpresa, param));

                        }

                    } catch (JRException e) {
                        System.out.println("br.com.bar.view.TelaCaixa.btnFecharCaixaMouseClicked()" + e);
                    }
                }

            }
        }

    }

    // Registra desconto concedido ao pedido pelo gerente
    public void registraDesconto(DescontoPedido dp) {

        String sql = "INSERT INTO tbdesconto_pedido (valor_desconto, motivo, tbcadfuncionario_id, cadpedido_id_pedido) VALUES (?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, dp.getValorDesconto());
            pst.setString(2, dp.getMotivo());
            pst.setInt(3, Integer.parseInt(dp.getIdFuncionario().getId()));
            pst.setInt(4, Integer.parseInt(dp.getIdPedido().getId()));

            pst.executeUpdate();

        } catch (NumberFormatException | SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.registraDesconto()" + e);
        }
    }

    /**
     * Realiza abertura do caixa para o funcionário logado
     *
     * @param saldoInical Valor de Abertura do caixa
     * @param idFuncionario ID do funcionário logado
     * @return Retorna um Boolean
     */
    public boolean abreCaixa(double saldoInical, int idFuncionario) {

        boolean resp = false;
        String sql = "INSERT INTO tbmovimentacao (data, saldo_inicial, entradas, saidas, saldo, status, tbcadfuncionario_id) VALUES (?,?,?,?,?,?,?)";
        Date dt = new Date();
        String dataAtual = u.formataDataBanco(dt);
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataAtual);
            pst.setDouble(2, saldoInical);
            pst.setDouble(3, 0);
            pst.setDouble(4, 0);
            pst.setDouble(5, 0);
            pst.setInt(6, 0);
            pst.setInt(7, idFuncionario);
            pst.executeUpdate();
            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.abreCaixa()" + e);
        }

        return resp;
    }

    // Retorna o id do caixa aberto para o funcionário informado
    public int retornaIdCaixa(String idFuncLogado) {
        String sql = "SELECT id FROM tbmovimentacao WHERE data = curdate() and tbcadfuncionario_id=?";
        int id = 0;
        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idFuncLogado);
            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCaixa.retornaIdCaixa()" + e);
        }

        return id;
    }

}
