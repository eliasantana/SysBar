/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.dao.Log;
import br.com.bar.dao.ReportUtil;
import br.com.bar.model.Autorizar;
import br.com.bar.model.DadosEmpresa;
import br.com.bar.model.DescontoPedido;
import br.com.bar.model.Entregador;
import br.com.bar.model.Funcionario;
import br.com.bar.model.MovimentacaoCaixa;
import br.com.bar.model.Nfce;
import br.com.bar.model.Pedido;
import br.com.bar.model.ProdutoNota;
import br.com.bar.model.TableModelCaixa;
import br.com.bar.util.ConexaoInternet;
import br.com.bar.util.FormataValor;
import br.com.br.controler.ControlerCaixa;
import br.com.br.controler.ControlerFuncionario;
import br.com.br.controler.ControlerMesa;
import br.com.br.controler.ControlerParametro;
import br.com.br.controler.ControlerPedido;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerCozinha;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerDelivery;
import br.com.br.controler.ControlerEntregador;
import br.com.br.controler.ControlerNFCe;
import br.com.br.controler.ControlerProduto;
import com.google.zxing.WriterException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author elias javax.swing.JFrame
 */
public class TelaCaixa extends javax.swing.JFrame {

    /**
     * Creates new form TelaCaixa
     */
    TelaProcessaPamento tpp;
    TelaAlteraSenha2 alteraSenha2;
    TelaPesquisaPreco pesquisa;
    TelaDetalheMesa detalheMesa;
    ControlerMesa cm = new ControlerMesa();
    ControlerPedido cp = new ControlerPedido();
    ControlerCaixa caixa = new ControlerCaixa();
    ControlerFuncionario func = new ControlerFuncionario();
    ControlerParametro cparam = new ControlerParametro();
    Connection conexao = ConexaoBd.conector();
    ControlerDadosEmpresa de = new ControlerDadosEmpresa();
    ControlerDelivery dl = new ControlerDelivery();
    TableModelCaixa modelCaixa = new TableModelCaixa();
    ControlerEntregador ce = new ControlerEntregador();
    ControlerNFCe cNFCe = new ControlerNFCe();
    ControlerCozinha cc = new ControlerCozinha();

    DadosEmpresa dadosEmpresa = de.selecionaDados();
    FormataValor fv = new FormataValor();

    //------------------------------------------------------------
    // As variaveis abaixo determinam o ambiente de Emissão de cupom fiscal
    int flagFiscal = 0;             // 1 - Para autorizar e ler retorno SEFAZ     
    private final int ambiente = 0; //Ambiente de Emissão  (0 - Homologação - 1  Produção
    //-------------------------------------------------------------
    boolean foiCancelada;
    // Dados da NFC-e
    Nfce nota = new Nfce();
    // HashMap para armazenamento os itens da NFC-e

    HashMap<String, String> nfce = new HashMap<>();
    HashMap<String, String> formasPagamento = new HashMap<>();

    JSONObject json;// JSON Principal
    JSONObject jSonItens; // Json de Itens
    JSONObject jsonPagamento; // Json de forma de pagameto e valor total do pedido

    // Fim dados NFC-e
    ReportUtil rpu = new ReportUtil();
    // Instância da tela principal usada para atualização após inclusão de contas;
    TelaPrincipal principal = new TelaPrincipal();
    Util utils = new Util();
    Log l = new Log();
    Date data = new Date();
    ArrayList<String> listAutoDesconto;
    /*
        Variável idGarçom é utilizada para guardar o ID do garçom listado no pedido
        e é necessária quando o caixa vai incluir algum produto de última hora.
     */
    String idGarcom = "";
    Entregador entregador;
    // Captura os valores para mais de uma forma de pagamento.
    double dinheiro = 0;
    double credito = 0;
    double debito = 0;
    double voucher = 0;
    double dinheiroPago = 0;
    String idDelivery;
    String operador;
    String cargo;
    // Opção da tela de confirmação
    Object[] opcao = {"   Não  ","  Sim   "};

    public TelaCaixa() {
        initComponents();
        // Exibe ou oculta botão de teste
        btnTesteCalculoPedidoTela.setVisible(false);
        labelExibeCalculo.setVisible(false);
        
        // Aplica máscara de entrada nos campos de pagamento misto
        fv.aplicaMascara(txtValorPago, 4, 1);
        fv.aplicaMascara(txtMistoDinheiro, 4, 1);
        fv.aplicaMascara(txtMistoCredito, 4, 1);
        fv.aplicaMascara(txtMistoDebito, 4, 1);
        fv.aplicaMascara(txtMistoVoucher, 4, 1);

        if (ambiente == 1 && flagFiscal == 1) {
            //lblAmbiante.setVisible(false);
            lblAmbiante.setText("NFC-e em Produção");
        } else if (ambiente == 0 && flagFiscal == 0) {
            lblAmbiante.setText("Versão não Fiscal");
        } else {
            lblAmbiante.setText("NFC-e em Homologação");
        }

        caixa.listaMesaOcupada(comboMesa);
        checkTxServico.setSelected(true);
        txtIdMEsa.setVisible(false);
        txtIdPedido.setVisible(false);
        radioDebito.setEnabled(false);
        radioVoucher.setEnabled(false);
        //Desabilita o painel de movimentação e o painel de Gráfico
        panelMovimentacao.setVisible(false);
        panelGrafico.setVisible(false);
        // Formata a data retornando um string com o número correspondente ao mês atual
        String mes = utils.formataData(data);
        // Carrega o combobox com os meses menores que o atual
        carregaComboPeriodo(comboMes, mes);
        comboMes.setSelectedIndex(Integer.parseInt(mes) - 1);
        lblData.setText(utils.formataDataBr(data));
        bloqueiaControlePagamento();
        lblMsgStatus.setVisible(false);

        txtDesconto.setText("0,00");
        txtValorPago.setText("0,00");
        txtTroco.setText("0,00");
        lblCargo.setVisible(false);
        modelCaixa.redimensionaColunas(tblDetalhePedido);

        checkReimpressao.setEnabled(true);
        jpanelSubTotal.setEnabled(false);
        jpanelTotalGeral.setEnabled(false);

        caixa.statusCaixa(lblStatus, foiCancelada, lblMsgStatus);

        // Zera valores do campo pagamento misto
        txtMistoDinheiro.setText("0,00");
        txtMistoCredito.setText("0,00");
        txtMistoDebito.setText("0,00");
        txtMistoVoucher.setText("0,00");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        painelEsquerdo = new javax.swing.JPanel();
        lblLLogo = new javax.swing.JLabel();
        panelMovimentacao = new javax.swing.JPanel();
        labelEntradas = new javax.swing.JLabel();
        labelSaidas = new javax.swing.JLabel();
        labelSaldo = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        lblSaidas = new javax.swing.JLabel();
        lblEntradas = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSaldoInicial = new javax.swing.JLabel();
        checkExibir = new javax.swing.JCheckBox();
        panelGrafico = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboMes = new javax.swing.JComboBox<>();
        btnGrafico = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblAmbiante = new javax.swing.JLabel();
        labelExibeCalculo = new javax.swing.JLabel();
        btnTesteCalculoPedidoTela = new javax.swing.JButton();
        painelDireito = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JLabel();
        jpanelSubTotal = new javax.swing.JPanel();
        lbl_total_parcial = new javax.swing.JLabel();
        lbl_valor_servico = new javax.swing.JLabel();
        percent = new javax.swing.JLabel();
        tgeral = new javax.swing.JLabel();
        lbl_cifra_servico = new javax.swing.JLabel();
        lbl_cifra_parcial = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblReceber = new javax.swing.JLabel();
        lblReceberPAgamento = new javax.swing.JLabel();
        btnFecharCaixa = new javax.swing.JLabel();
        labelFecharCaixa = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpanelTotalGeral = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lbl_cifra_total = new javax.swing.JLabel();
        lblNpessoas = new javax.swing.JLabel();
        jSpinFieldPessoas = new javax.swing.JSpinner();
        btnImprimir = new javax.swing.JLabel();
        checkReimpressao = new javax.swing.JCheckBox();
        jtabedFormaPagto = new javax.swing.JTabbedPane();
        jPanelFormaPagamento = new javax.swing.JPanel();
        radioDinheiro = new javax.swing.JRadioButton();
        radioCartao = new javax.swing.JRadioButton();
        radioDebito = new javax.swing.JRadioButton();
        radioVoucher = new javax.swing.JRadioButton();
        panelPagParcial = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMistoDinheiro = new javax.swing.JFormattedTextField();
        txtMistoCredito = new javax.swing.JFormattedTextField();
        txtMistoDebito = new javax.swing.JFormattedTextField();
        txtMistoVoucher = new javax.swing.JFormattedTextField();
        checkTxServico = new javax.swing.JCheckBox();
        checkConcedeDesconto = new javax.swing.JCheckBox();
        txtDesconto = new javax.swing.JTextField();
        lblPago = new javax.swing.JLabel();
        lblTroco = new javax.swing.JLabel();
        txtTroco = new javax.swing.JTextField();
        lblValorDesc = new javax.swing.JLabel();
        panelValores = new javax.swing.JPanel();
        txtValorPago = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comboMesa = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalhePedido = new javax.swing.JTable();
        txtIdMEsa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtIdPedido = new javax.swing.JTextField();
        btnListar = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        lblGarcom = new javax.swing.JLabel();
        lblContasAPagar = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblMsgStatus = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        lblNPedido = new javax.swing.JLabel();
        labelPedido = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAlteraSenha = new javax.swing.JLabel();
        lblLancarPedido = new javax.swing.JLabel();
        lblConsultarPrecos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        painelEsquerdo.setBackground(new java.awt.Color(38, 53, 61));
        painelEsquerdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        painelEsquerdo.add(lblLLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 22, 240, 180));

        panelMovimentacao.setBackground(new java.awt.Color(38, 53, 61));

        labelEntradas.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        labelEntradas.setForeground(new java.awt.Color(255, 255, 255));
        labelEntradas.setText("Entradas............ R$");

        labelSaidas.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        labelSaidas.setForeground(new java.awt.Color(255, 255, 255));
        labelSaidas.setText("Saídas................ R$");

        labelSaldo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        labelSaldo.setForeground(new java.awt.Color(255, 255, 255));
        labelSaldo.setText("Saldo Final...... R$");

        lblSaldo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(255, 255, 255));
        lblSaldo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaldo.setText("Saldo");
        lblSaldo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblSaidas.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblSaidas.setForeground(new java.awt.Color(255, 255, 255));
        lblSaidas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaidas.setText("jLabel14");
        lblSaidas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblEntradas.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblEntradas.setForeground(new java.awt.Color(255, 255, 255));
        lblEntradas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEntradas.setText("jLabel14");
        lblEntradas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Saldo Inicial ..... R$");

        lblSaldoInicial.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblSaldoInicial.setForeground(new java.awt.Color(255, 255, 255));
        lblSaldoInicial.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaldoInicial.setText("jLabel11");
        lblSaldoInicial.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelMovimentacaoLayout = new javax.swing.GroupLayout(panelMovimentacao);
        panelMovimentacao.setLayout(panelMovimentacaoLayout);
        panelMovimentacaoLayout.setHorizontalGroup(
            panelMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovimentacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSaidas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSaidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSaldoInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelMovimentacaoLayout.setVerticalGroup(
            panelMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovimentacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblSaldoInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(panelMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEntradas)
                    .addComponent(lblEntradas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSaidas)
                    .addComponent(lblSaidas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSaldo)
                    .addComponent(lblSaldo)))
        );

        painelEsquerdo.add(panelMovimentacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 356, -1, -1));

        checkExibir.setBackground(new java.awt.Color(38, 53, 61));
        checkExibir.setForeground(new java.awt.Color(255, 255, 255));
        checkExibir.setText("Exibir Movimentação");
        checkExibir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkExibirMouseClicked(evt);
            }
        });
        checkExibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkExibirActionPerformed(evt);
            }
        });
        painelEsquerdo.add(checkExibir, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 326, 191, -1));

        panelGrafico.setBackground(new java.awt.Color(38, 53, 61));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Selecione o mês");

        btnGrafico.setForeground(new java.awt.Color(255, 255, 255));
        btnGrafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnGrfico.png"))); // NOI18N
        btnGrafico.setText("Gerar Gráfico do Período");
        btnGrafico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGraficoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelGraficoLayout = new javax.swing.GroupLayout(panelGrafico);
        panelGrafico.setLayout(panelGraficoLayout);
        panelGraficoLayout.setHorizontalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraficoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGrafico)
                    .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelGraficoLayout.setVerticalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraficoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGrafico)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        painelEsquerdo.add(panelGrafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 480, 200, -1));

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblOperador.setForeground(new java.awt.Color(255, 255, 255));
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario_branco.png"))); // NOI18N
        lblOperador.setText("jLabel2");
        painelEsquerdo.add(lblOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 100, 40));

        lblData.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/calendar24x24.png"))); // NOI18N
        lblData.setText("jLabel5");
        painelEsquerdo.add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 630, 120, 40));

        lblAmbiante.setForeground(new java.awt.Color(255, 255, 255));
        lblAmbiante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAmbiante.setText("jLabel10");
        painelEsquerdo.add(lblAmbiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 190, -1));

        labelExibeCalculo.setForeground(new java.awt.Color(255, 255, 255));
        labelExibeCalculo.setText("jLabel11");
        painelEsquerdo.add(labelExibeCalculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        btnTesteCalculoPedidoTela.setText("Calcular");
        btnTesteCalculoPedidoTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTesteCalculoPedidoTelaActionPerformed(evt);
            }
        });
        painelEsquerdo.add(btnTesteCalculoPedidoTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        getContentPane().add(painelEsquerdo);
        painelEsquerdo.setBounds(0, 0, 260, 700);

        painelDireito.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(38, 53, 61));

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar.setForeground(new java.awt.Color(255, 255, 255));
        btnFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        btnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(btnFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelDireito.add(jPanel2);
        jPanel2.setBounds(330, 0, 50, 40);

        jpanelSubTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sub-total")));

        lbl_total_parcial.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        lbl_total_parcial.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_total_parcial.setText("Total Parcial");

        lbl_valor_servico.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        lbl_valor_servico.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_valor_servico.setText("Valor do Serviço");

        percent.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        percent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        percent.setText("0,00");

        tgeral.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        tgeral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tgeral.setText("0,00");

        lbl_cifra_servico.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_cifra_servico.setText("R$");

        lbl_cifra_parcial.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_cifra_parcial.setText("R$");

        javax.swing.GroupLayout jpanelSubTotalLayout = new javax.swing.GroupLayout(jpanelSubTotal);
        jpanelSubTotal.setLayout(jpanelSubTotalLayout);
        jpanelSubTotalLayout.setHorizontalGroup(
            jpanelSubTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelSubTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_cifra_parcial)
                .addGroup(jpanelSubTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelSubTotalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbl_total_parcial, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(lbl_valor_servico, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelSubTotalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tgeral, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(lbl_cifra_servico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(percent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpanelSubTotalLayout.setVerticalGroup(
            jpanelSubTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelSubTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelSubTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_total_parcial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_valor_servico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelSubTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelSubTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(percent, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_cifra_servico))
                    .addGroup(jpanelSubTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tgeral, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_cifra_parcial)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelDireito.add(jpanelSubTotal);
        jpanelSubTotal.setBounds(10, 40, 350, 120);

        lblReceber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/money48x48.png"))); // NOI18N
        lblReceber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReceberMouseClicked(evt);
            }
        });

        lblReceberPAgamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReceberPAgamento.setText("Receber Pagamento");

        btnFecharCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Maquineta.png"))); // NOI18N
        btnFecharCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFecharCaixaMouseClicked(evt);
            }
        });

        labelFecharCaixa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFecharCaixa.setText("Fechar Caixa");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/power.png"))); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sair");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFecharCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(labelFecharCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblReceberPAgamento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblReceber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnFecharCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFecharCaixa)
                    .addComponent(lblReceberPAgamento)
                    .addComponent(jLabel3))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        painelDireito.add(jPanel6);
        jPanel6.setBounds(10, 590, 350, 110);

        jpanelTotalGeral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Total Geral"));

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("0,00");

        lbl_cifra_total.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbl_cifra_total.setText("R$");

        javax.swing.GroupLayout jpanelTotalGeralLayout = new javax.swing.GroupLayout(jpanelTotalGeral);
        jpanelTotalGeral.setLayout(jpanelTotalGeralLayout);
        jpanelTotalGeralLayout.setHorizontalGroup(
            jpanelTotalGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelTotalGeralLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl_cifra_total)
                .addGap(10, 10, 10)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpanelTotalGeralLayout.setVerticalGroup(
            jpanelTotalGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelTotalGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTotal)
                .addComponent(lbl_cifra_total))
        );

        painelDireito.add(jpanelTotalGeral);
        jpanelTotalGeral.setBounds(10, 160, 350, 80);

        lblNpessoas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNpessoas.setText("N. Pessoas");
        painelDireito.add(lblNpessoas);
        lblNpessoas.setBounds(30, 550, 65, 30);

        jSpinFieldPessoas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
        painelDireito.add(jSpinFieldPessoas);
        jSpinFieldPessoas.setBounds(100, 550, 51, 32);

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Impressora32x32.png"))); // NOI18N
        btnImprimir.setText(" Parcial");
        btnImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimirMouseClicked(evt);
            }
        });
        painelDireito.add(btnImprimir);
        btnImprimir.setBounds(170, 550, 80, 40);

        checkReimpressao.setText("Reimprimir");
        checkReimpressao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkReimpressaoMouseClicked(evt);
            }
        });
        checkReimpressao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkReimpressaoActionPerformed(evt);
            }
        });
        painelDireito.add(checkReimpressao);
        checkReimpressao.setBounds(260, 560, 90, 23);

        jtabedFormaPagto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabedFormaPagtoMouseClicked(evt);
            }
        });

        jPanelFormaPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder())));
        jPanelFormaPagamento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup2.add(radioDinheiro);
        radioDinheiro.setFont(new java.awt.Font("Yu Gothic Light", 0, 14)); // NOI18N
        radioDinheiro.setText("Dinheiro");
        radioDinheiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioDinheiroMouseClicked(evt);
            }
        });
        jPanelFormaPagamento.add(radioDinheiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));

        buttonGroup2.add(radioCartao);
        radioCartao.setFont(new java.awt.Font("Yu Gothic Light", 0, 14)); // NOI18N
        radioCartao.setText("Cartão de Crédito");
        radioCartao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioCartaoMouseClicked(evt);
            }
        });
        radioCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCartaoActionPerformed(evt);
            }
        });
        jPanelFormaPagamento.add(radioCartao, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, 30));

        buttonGroup2.add(radioDebito);
        radioDebito.setFont(new java.awt.Font("Yu Gothic Light", 0, 14)); // NOI18N
        radioDebito.setText("Cartão de Débito");
        radioDebito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioDebitoMouseClicked(evt);
            }
        });
        jPanelFormaPagamento.add(radioDebito, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 130, 30));

        buttonGroup2.add(radioVoucher);
        radioVoucher.setFont(new java.awt.Font("Yu Gothic Light", 0, 14)); // NOI18N
        radioVoucher.setText("Voucher");
        radioVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioVoucherMouseClicked(evt);
            }
        });
        jPanelFormaPagamento.add(radioVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 110, -1));

        jtabedFormaPagto.addTab("Pagamento Único", jPanelFormaPagamento);

        panelPagParcial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelPagParcial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelPagParcialMouseClicked(evt);
            }
        });
        panelPagParcial.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel5.setText("Dinheiro R$");
        panelPagParcial.add(jLabel5);
        jLabel5.setBounds(12, 22, 80, 30);

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel7.setText("C. Crédito R$");
        panelPagParcial.add(jLabel7);
        jLabel7.setBounds(174, 22, 82, 30);

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel8.setText("C . Débito R$");
        panelPagParcial.add(jLabel8);
        jLabel8.setBounds(12, 62, 85, 30);

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel9.setText("Voucher R$");
        panelPagParcial.add(jLabel9);
        jLabel9.setBounds(174, 62, 82, 30);

        txtMistoDinheiro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMistoDinheiro.setText("0,00");
        txtMistoDinheiro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtMistoDinheiro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMistoDinheiroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMistoDinheiroFocusLost(evt);
            }
        });
        txtMistoDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMistoDinheiroActionPerformed(evt);
            }
        });
        txtMistoDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMistoDinheiroKeyPressed(evt);
            }
        });
        panelPagParcial.add(txtMistoDinheiro);
        txtMistoDinheiro.setBounds(100, 20, 69, 30);

        txtMistoCredito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMistoCredito.setText("0,00");
        txtMistoCredito.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtMistoCredito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMistoCreditoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMistoCreditoFocusLost(evt);
            }
        });
        txtMistoCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMistoCreditoKeyPressed(evt);
            }
        });
        panelPagParcial.add(txtMistoCredito);
        txtMistoCredito.setBounds(260, 20, 69, 30);

        txtMistoDebito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMistoDebito.setText("0,00");
        txtMistoDebito.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtMistoDebito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMistoDebitoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMistoDebitoFocusLost(evt);
            }
        });
        txtMistoDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMistoDebitoKeyPressed(evt);
            }
        });
        panelPagParcial.add(txtMistoDebito);
        txtMistoDebito.setBounds(100, 60, 69, 30);

        txtMistoVoucher.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMistoVoucher.setText("0,00");
        txtMistoVoucher.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtMistoVoucher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMistoVoucherFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMistoVoucherFocusLost(evt);
            }
        });
        txtMistoVoucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMistoVoucherKeyPressed(evt);
            }
        });
        panelPagParcial.add(txtMistoVoucher);
        txtMistoVoucher.setBounds(260, 60, 69, 30);

        jtabedFormaPagto.addTab("Pagamento Misto", panelPagParcial);

        painelDireito.add(jtabedFormaPagto);
        jtabedFormaPagto.setBounds(10, 240, 350, 150);

        checkTxServico.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        checkTxServico.setText("Taxa de Serviço");
        checkTxServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkTxServicoMouseClicked(evt);
            }
        });
        checkTxServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTxServicoActionPerformed(evt);
            }
        });
        painelDireito.add(checkTxServico);
        checkTxServico.setBounds(30, 400, 150, 29);

        checkConcedeDesconto.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        checkConcedeDesconto.setText("Conceder Desconto");
        checkConcedeDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkConcedeDescontoMouseClicked(evt);
            }
        });
        painelDireito.add(checkConcedeDesconto);
        checkConcedeDesconto.setBounds(30, 430, 143, 29);

        txtDesconto.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtDesconto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDesconto.setText("0,00");
        painelDireito.add(txtDesconto);
        txtDesconto.setBounds(210, 420, 134, 40);

        lblPago.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        lblPago.setText("Valor Pago R$");
        painelDireito.add(lblPago);
        lblPago.setBounds(30, 460, 110, 30);

        lblTroco.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        lblTroco.setText("Troco R$");
        painelDireito.add(lblTroco);
        lblTroco.setBounds(210, 460, 74, 30);

        txtTroco.setEditable(false);
        txtTroco.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtTroco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTroco.setText("0,00");
        txtTroco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTrocoFocusGained(evt);
            }
        });
        txtTroco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrocoActionPerformed(evt);
            }
        });
        txtTroco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTrocoKeyPressed(evt);
            }
        });
        painelDireito.add(txtTroco);
        txtTroco.setBounds(210, 490, 134, 40);

        lblValorDesc.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        lblValorDesc.setText("Valor Desconto R$");
        painelDireito.add(lblValorDesc);
        lblValorDesc.setBounds(210, 400, 160, 20);

        panelValores.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelValores.setLayout(null);

        txtValorPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorPago.setText("0,00");
        txtValorPago.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtValorPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValorPagoMouseClicked(evt);
            }
        });
        txtValorPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorPagoKeyPressed(evt);
            }
        });
        panelValores.add(txtValorPago);
        txtValorPago.setBounds(20, 100, 129, 40);

        painelDireito.add(panelValores);
        panelValores.setBounds(10, 390, 350, 200);

        getContentPane().add(painelDireito);
        painelDireito.setBounds(900, 0, 370, 700);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel6.setText("Mesa:");

        comboMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMesaActionPerformed(evt);
            }
        });

        tblDetalhePedido = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblDetalhePedido.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 0, 12)); // NOI18N
        tblDetalhePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "PRODUTO", "QTD", "VLR UNITÁRIO R$", "VLR TOTAL R$"
            }
        ));
        tblDetalhePedido.setRowHeight(22);
        tblDetalhePedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalhePedidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalhePedido);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Caixa");

        btnListar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        btnListar.setText("Listar");
        btnListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListarMouseClicked(evt);
            }
        });
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel22.setText("Garçom:");

        lblGarcom.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N

        lblContasAPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/calendario (2).png"))); // NOI18N
        lblContasAPagar.setText("Pagamentos");
        lblContasAPagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblContasAPagarMouseClicked(evt);
            }
        });

        lblStatus.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblMsgStatus.setText("jLabel10");

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("jLabel2");

        lblNPedido.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblNPedido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        labelPedido.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        labelPedido.setText("Pedido:");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/calculadora48x48.png"))); // NOI18N
        jLabel4.setText("Calculadora");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        lblAlteraSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/chave48x48.png"))); // NOI18N
        lblAlteraSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlteraSenhaMouseClicked(evt);
            }
        });

        lblLancarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/pedidos32x32.png"))); // NOI18N
        lblLancarPedido.setText("Lançar Pedido");
        lblLancarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLancarPedidoMouseClicked(evt);
            }
        });

        lblConsultarPrecos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/pesquisa_preco.png"))); // NOI18N
        lblConsultarPrecos.setText("Consultar Preço");
        lblConsultarPrecos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblConsultarPrecosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGarcom, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lblAlteraSenha)
                                .addGap(29, 29, 29)
                                .addComponent(lblContasAPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblLancarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblConsultarPrecos, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblNPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(comboMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdMEsa, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMsgStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jLabel6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdMEsa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMsgStatus)
                            .addComponent(lblCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(lblGarcom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lblNPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblContasAPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlteraSenha)
                    .addComponent(lblLancarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConsultarPrecos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtIdMEsa, txtIdPedido});

        getContentPane().add(jPanel1);
        jPanel1.setBounds(260, 0, 640, 700);

        setSize(new java.awt.Dimension(1269, 699));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseClicked
        //Sai da tela Caixa
        if (principal != null) {
            principal.atualizaTela();
        }
        if ("Gerente".equals(lblCargo.getText())) {
            dispose();
        } else {
            dispose();
            TelaLogin login = new TelaLogin();
            login.setVisible(true);
        }

    }//GEN-LAST:event_btnFecharMouseClicked

    private void comboMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMesaActionPerformed
        btnListar.setEnabled(false);
        if (!"Selecione...".equals(comboMesa.getSelectedItem())) {
            btnListar.setEnabled(true);
        } else {
            lblReceber.setEnabled(false);
            lblReceberPAgamento.setEnabled(false);

            radioCartao.setEnabled(false);
            radioDinheiro.setEnabled(false);
            radioVoucher.setEnabled(false);
            radioDebito.setEnabled(false);
            jSpinFieldPessoas.setEnabled(false);
            btnImprimir.setEnabled(false);

            lblTotal.setEnabled(false);
            lblTotal.setText("0,00");
            percent.setEnabled(false);
            percent.setText("0,00");
            tgeral.setEnabled(false);
            tgeral.setText("0,00");
            checkTxServico.setEnabled(false);
            lblGarcom.setText(null);
            lblNPedido.setText(null);
            txtValorPago.setEnabled(false);
            txtValorPago.setText("0,00");
            checkConcedeDesconto.setEnabled(false);
            tblDetalhePedido.setModel(modelCaixa);
            modelCaixa.redimensionaColunas(tblDetalhePedido);
            jtabedFormaPagto.setEnabled(false);
            jtabedFormaPagto.setSelectedIndex(0);
            txtDesconto.setText("0,00");
            mudaEstadoCaposMisto(false);
            jpanelSubTotal.setEnabled(false);
            jpanelTotalGeral.setEnabled(false);

            desabilitaLabels();
        }
    }//GEN-LAST:event_comboMesaActionPerformed

    private void checkTxServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkTxServicoMouseClicked
        // Retira taxa de serviço
        if (checkTxServico.isEnabled()) {
            txtTroco.setText("0,00");
            calculaTaxa();
            //txtValorPago.setText(lblTotal.getText());
            String stvValor = fv.Formata(lblTotal.getText());
            txtValorPago.setText(stvValor);

            if (checkTxServico.isSelected()) {
                lbl_valor_servico.setEnabled(true);
                lbl_cifra_servico.setEnabled(true);
                percent.setEnabled(true);
                lblPago.setEnabled(true);
                // 29/07/2019 Inicio da alteração Excluir após validação
                double total = dinheiro + credito + debito + voucher;
                if (jtabedFormaPagto.getSelectedIndex() == 1 && total > 0) {
                    calculaPagamentoMisto();
                    validaPagamentoMisto();
                }
            } else {
                // 29/07/2019 Inicio da alteração Excluir após validação
                double total = dinheiro + credito + debito + voucher;
                if (jtabedFormaPagto.getSelectedIndex() == 1 && total > 0) {
                    calculaPagamentoMisto();
                    validaPagamentoMisto();
                }
                // 29/07/2019 Fim da alteração 
                lbl_valor_servico.setEnabled(false);
                lbl_cifra_servico.setEnabled(false);
                percent.setEnabled(false);
            }
        }

    }//GEN-LAST:event_checkTxServicoMouseClicked

    private void txtTrocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrocoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrocoActionPerformed

    private void txtTrocoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTrocoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calculaTaxa();
        }
    }//GEN-LAST:event_txtTrocoKeyPressed

    private void checkTxServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTxServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkTxServicoActionPerformed

    private void txtTrocoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTrocoFocusGained

    }//GEN-LAST:event_txtTrocoFocusGained

    private void lblReceberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReceberMouseClicked
        
        if (flagFiscal==0 && ambiente==0){
            // Não informa cpf se o sistema estiver em execução no modo não fiscal
            receber("","");            
        }else{
            TelaInformaCpf informaCpf = new TelaInformaCpf();
            informaCpf.recebeTela(this);
            informaCpf.setAlwaysOnTop(true);
            informaCpf.setVisible(true);
        }
        
        

    }//GEN-LAST:event_lblReceberMouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        //Sai da tela Caixa
        if ("Gerente".equals(lblCargo.getText())) {
            dispose();
        } else {
            dispose();
            TelaLogin login = new TelaLogin();
            login.setVisible(true);

        }

    }//GEN-LAST:event_jLabel17MouseClicked

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        atualizaPedidoNoCaixa();
        atualizaPedidoNoCaixa();
        mudaEstadoCaposMisto(true);
        checkConcedeDesconto.setEnabled(true);
        lblValorDesc.setEnabled(false);
        jpanelSubTotal.setEnabled(true);
        jpanelTotalGeral.setEnabled(true);

        if ("delivery".equals(lblGarcom.getText().toLowerCase())) {
            String nome = dl.localizaEntregador(lblNPedido.getText());
            entregador = ce.localizaEntregador(nome);
            // Desabilita checkBox
            checkTxServico.setSelected(false);
            checkTxServico.setEnabled(false);
            idDelivery = txtIdPedido.getText();
            // Zera taxa de Delivery e recalcula o valor a ser pago.

            String txEnt = dl.retornaTaxaEntrega(txtIdPedido.getText());
            calculaTaxa(txEnt); //0,00
            percent.setText(txEnt);
            lbl_valor_servico.setText("Valor da Entrega");
            //percent.setText("0,00");

        } else {
            entregador = new Entregador();
            lbl_valor_servico.setText("Valor do Serviço");
        }

        if (foiCancelada = cp.foiCancelado(lblNPedido.getText())) {
            lblNPedido.setText("R".concat(lblNPedido.getText()));
        }
        
        // Desabilida componentes
        buttonGroup2.clearSelection();
        lblReceber.setEnabled(false);
        lblReceberPAgamento.setEnabled(false);
        txtValorPago.setEnabled(false);
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimirMouseClicked
        if (btnImprimir.isEnabled()) {
            btnImprimir.setForeground(Color.black);
            //Imprime cupom
            // Calcula valor
            DadosEmpresa dadosEmpresa = de.selecionaDados();

            int nPesoas = Integer.parseInt(jSpinFieldPessoas.getValue().toString());
            String strTotal = lblTotal.getText().replace(".", "");
            strTotal = strTotal.replace(",", ".");
            Double totalGeral = Double.parseDouble(strTotal);
            Double totalPessoas = totalGeral / nPesoas;
            //System.out.println(nPesoas);

            //
            HashMap dados = new HashMap();
            Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            dados.put("data", df.format(dt));
            dados.put("id_pedido", txtIdPedido.getText());
            dados.put("garcom", lblGarcom.getText());
            String strTx = percent.getText().replace(".", "");
            strTx = strTx.replace(",", ".");
            dados.put("tx", Double.parseDouble(strTx));
            dados.put("npessoas", nPesoas);
            dados.put("total_pessoas", totalPessoas);
            dados.put("mesa", comboMesa.getSelectedItem().toString());
            dados.put("nome_empresa", dadosEmpresa.getNome_empresa());

            String strDesc = txtDesconto.getText().replace(".", "");
            strDesc = strDesc.replace(",", ".");
            dados.put("desc", Double.parseDouble(strDesc));
            //System.out.println(dadosEmpresa.getImprimir_na_tela());
            try {
                if (dadosEmpresa.getImprimir_na_tela() == 0) {

                    rpu.imprimeRelatorioTela("consumo.jasper", dados, "Consumo");

                } else {

                    rpu.impressaoDireta("consumo.jasper", dados);
                }
            } catch (JRException e) {
                //System.out.println("br.com.bar.view.TelaCaixa.btnImprimirMouseClicked()" + e);
            }
            // Fecha conexao com o banco de dados.
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(TelaCaixa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnImprimirMouseClicked

    private void btnListarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListarMouseClicked

    private void btnFecharCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharCaixaMouseClicked
        // Realiza o fechamento do caixa se o botão estiver habilitado
        if (btnFecharCaixa.isEnabled()) {

            Date dataAtual = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            MovimentacaoCaixa cx = new MovimentacaoCaixa();
            int idCaixa = caixa.retornaIdCaixa(func.localizaIdLogin(lblOperador.getText()));
            cx.setId(idCaixa);
            cx.setData(df.format(dataAtual));
            // Totaliza entradas e saida do 
            cx.setEntrada(caixa.totalizaEntradas(lblOperador.getText()));
            cx.setSaida(caixa.totalizaSaida(lblOperador.getText()));
            cx.setSaldo(caixa.totalizaEntradas(lblOperador.getText()) - caixa.totalizaSaida(lblOperador.getText()));
            cx.setStatus(1);
            cx.setIdFuncionario(Integer.parseInt(func.localizaIdLogin(lblOperador.getText())));
            // Conta os elementos de um combobox
            int op;
            if (comboMesa.getItemCount() > 1) {
                op = JOptionPane.showOptionDialog(null, lblOperador.getText().toUpperCase() + ", existe(m) Mesas aberta(s)! Tem certeza que deseja fechar o Caixa mesmo assim?", "Atenção!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, opcao, opcao[1]);
            } else {
                op = JOptionPane.showOptionDialog(null, lblOperador.getText().toUpperCase() + ", tem certeza que deseja fechar seu caixa?", "Atenção!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, opcao, opcao[1]);
            }

            if (op == 1) {

                if (caixa.retornaStatusCaixa(cx.getIdFuncionario()) == 1) { // Verifica se existe movimentação no dia para este operador.
                    JOptionPane.showMessageDialog(null, "Caixa fechado, contate o administrador!");

                } else {
                    //if (caixa.gravaMovimentacao(cx)) {
                    if (caixa.atualizaMovimentacao(cx)) {
                        JOptionPane.showMessageDialog(null, "Caixa fechado com sucesso!");

                        // Desabilita botões Listar e Fechar caixa após o fechamento
                        btnListar.setEnabled(false);
                        btnFecharCaixa.setEnabled(false);
                        lblContasAPagar.setEnabled(false);
                        percent.setEnabled(false);
                        tgeral.setEnabled(false);
                        labelFecharCaixa.setEnabled(false);
                        lblReceber.setEnabled(false);
                        lblReceberPAgamento.setEnabled(false);

                        // Inicio do Registro de Log
                        l.setFuncionalidade("Caixa");
                        l.setDescricao("Fechou o caixa -> " + utils.formataDataHora(new Date(), "dh"));
                        l.setUsuario(lblOperador.getText());
                        l.gravaLog(l);
                        // Fim do Registro de Log
                        //Desabilita o combo Mesa
                        comboMesa.setEnabled(false);
                        // Imprime relatório de caixa 
                        HashMap param = new HashMap();
                        Date dt = new Date();
                        param.put("data", df.format(dt));
                        param.put("id_perador", cx.getIdFuncionario());
                        param.put("titulo", "=-=-=-= Caixa Diário =-=-=-=");
                        // Troca imagem de status
                        caixa.statusCaixa(lblStatus, caixa.temMovimentacao(cx.getIdFuncionario()), lblMsgStatus);
                        //DadosEmpresa dadosEmpresa = de.selecionaDados();
                        param.put("empresa", dadosEmpresa.getNome_empresa());
                        // Oculta paineis movimentação e painel gráfico
                        panelGrafico.setVisible(false);
                        panelMovimentacao.setVisible(false);

                        try {
                            ReportUtil rpu = new ReportUtil();
                            if (dadosEmpresa.getImprimir_na_tela() == 0) {

                                rpu.imprimeRelatorioTela("relMovimentacaoOperador.jasper", rpu.rodape(dadosEmpresa, param), "Relátorio de Movimentações");

                            } else {
                                JasperPrint print = JasperFillManager.fillReport(cparam.getRELATORIOS() + "relMovimentacaoOperador.jasper", rpu.rodape(dadosEmpresa, param), conexao);
                                JasperPrintManager.printPage(print, 0, false);

                            }

                        } catch (JRException e) {
                            //System.out.println("br.com.bar.view.TelaCaixa.btnFecharCaixaMouseClicked()" + e);
                        }
                        //*************************** TESTE FECHAMENTO ANTERIOR ***************************************
                        // Verifica se existe movimentação na data anterior caso existea executa o fechamento retroativo
                        if (caixa.temMovAnterior(lblOperador.getText())) {
                            // Fecha caixa anterio do operador informado
                            caixa.fechaCaixaAnterior(String.valueOf(lblOperador.getText()), String.valueOf(cx.getIdFuncionario()));

                        }
                        //Imprime Detalhamento de Vendas
                        HashMap mapDetalhe = new HashMap();
                        mapDetalhe.put("operador", lblOperador.getText());
                        try {
                            rpu.imprimeRelatorioTela("detalhe.jasper", mapDetalhe, "Detalhamento de Vendas");
                        } catch (JRException ex) {
                            Logger.getLogger(TelaCaixa.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
            // Fecha conexao com o banco
            try {
                conexao.close();
            } catch (Exception e) {
                //System.out.println("br.com.bar.view.TelaCaixa.btnFecharCaixaMouseClicked()" + e);
            }

        }
    }//GEN-LAST:event_btnFecharCaixaMouseClicked

    private void lblContasAPagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContasAPagarMouseClicked
        // Chama a tela de Cadastro de Contas a pagar
        TelaContasApagar contaApagar = new TelaContasApagar();
        contaApagar.recebeOperador(principal, lblOperador.getText(), lblCargo.getText());
        contaApagar.recebeTelaCaixa(this);
        contaApagar.setModal(true);
        contaApagar.setVisible(true);


    }//GEN-LAST:event_lblContasAPagarMouseClicked

    private void btnGraficoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGraficoMouseClicked
        // Cria gráfico do Período

        //Date dtAtual = new Date();
        //String mesAtual = String.valueOf(comboMes.getSelectedIndex()+1);
        String mesAtual = String.valueOf(comboMes.getSelectedIndex() + 1);
        System.out.println("Mês selecionado: " + mesAtual);
        ResultSet rs;
        rs = caixa.listaMovimentacao(mesAtual);

        // Cria data set para o gráfico
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        try {

            while (rs.next()) {
                ds.setValue(rs.getDouble("entradas"), "Entradas", rs.getString("data"));
                ds.setValue(rs.getDouble("saidas"), "Saídas", rs.getString("data"));
            }

        } catch (SQLException e) {
            System.out.println("Não há movimentação a ser listada ");
        }

        // Criando Gráfico de Barras'
        JFreeChart graficoBarras = ChartFactory.createBarChart("Movimentação Financeira " + comboMes.getSelectedItem().toString(), "Período", "Movimentação em R$", ds, PlotOrientation.VERTICAL, true, true, false);
        // Cria e recebe a categoria de plotagem do gráfico de barras
        CategoryPlot categoryPlot = graficoBarras.getCategoryPlot();
        // Atribui cores as barras do gráfico
        graficoBarras.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.BLUE);
        graficoBarras.getCategoryPlot().getRenderer().setSeriesPaint(1, Color.RED);
        // Adiciona linhas de grade ao gráfico
        categoryPlot.setRangeGridlinePaint(Color.blue);
        // Cria janela do gráfico
        ChartFrame frame = new ChartFrame("Gráfico Movimentação do Período", graficoBarras);
        // Define a dimensão do gráfico
        frame.setSize(800, 600);
        //

        frame.setVisible(true);

    }//GEN-LAST:event_btnGraficoMouseClicked

    private void checkExibirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkExibirMouseClicked
        if ("Caixa Aberto".equals(lblMsgStatus.getText())) {
            if (checkExibir.isSelected()) {
                if ("Gerente".equals(lblCargo.getText())) {
                    //panelGrafico.setVisible(true);
                    panelMovimentacao.setVisible(true);
                    atualizaCaixa();
                } else {

                    atualizaCaixa();
                    panelMovimentacao.setVisible(true);
                    // panelGrafico.setVisible(false);
                }

            } else {
                panelMovimentacao.setVisible(false);
                //panelGrafico.setVisible(false);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Não é possível exibir a movimentação com o Caixa Fechado!");

        }

    }//GEN-LAST:event_checkExibirMouseClicked

    private void checkExibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExibirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkExibirActionPerformed

    private void tblDetalhePedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalhePedidoMouseClicked

    }//GEN-LAST:event_tblDetalhePedidoMouseClicked

    private void checkConcedeDescontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkConcedeDescontoMouseClicked
        // Chama a Tela de Autorização do Desconto
        // Passa dados do pedido
        if (checkConcedeDesconto.isEnabled()) {

            ArrayList<String> dadosDoPedido = new ArrayList<>();

            dadosDoPedido.add(tgeral.getText()); // Total da Conta (Sem Tx. de Serviço)
            dadosDoPedido.add(percent.getText()); // Tx. de Serviço
            dadosDoPedido.add(lblTotal.getText()); // Total + Taxa de Servico = Total Geral
            dadosDoPedido.add(comboMesa.getSelectedItem().toString()); // Numero da Mesa
            dadosDoPedido.add(txtIdPedido.getText()); // Número do Pedido

            // Forma de Pagamento
            String formaPagto = "";
            if (radioCartao.isSelected()) {
                formaPagto = radioCartao.getText();
            } else {
                formaPagto = radioDinheiro.getText();
            }
            dadosDoPedido.add(formaPagto);

            TelaAutorizacao ta = new TelaAutorizacao();
            // Envia dados do pedido e guia selecionada
            int index = jtabedFormaPagto.getSelectedIndex();
            //System.out.println("Guia Selecionada no momento do desconto: " + index);
            ta.recebeValor(this, dadosDoPedido, index);
            ta.setModal(true);
            ta.setVisible(true);
        }


    }//GEN-LAST:event_checkConcedeDescontoMouseClicked

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_formFocusGained

    private void checkReimpressaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkReimpressaoActionPerformed
        // Reimpressão de cupom
        if (checkReimpressao.isEnabled()) {
            if (checkReimpressao.isSelected()) {
                TelaReImpressao rImpressao = new TelaReImpressao();
                rImpressao.setModal(true);
                rImpressao.setVisible(true);
                checkReimpressao.setSelected(false);
            }
        }
    }//GEN-LAST:event_checkReimpressaoActionPerformed
    private void mensagem() {
        JOptionPane.showMessageDialog(this, "Existe(m) prato(s) ainda sendo preparado(s) na cozinha!", "Atenção!", JOptionPane.ERROR_MESSAGE);
        lblReceber.setEnabled(false);
        lblReceberPAgamento.setEnabled(false);
    }
    private void radioDinheiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioDinheiroMouseClicked
        //Abilita textFild para recebimento em dinheiro
        if (radioDinheiro.isSelected()) {
            if (cc.temNaCozinha(lblNPedido.getText())) {
                // Informa ao usuário se existe parato pendente na cozinha
                mensagem();
            } else {
                txtValorPago.setText(lblTotal.getText());
                txtValorPago.setEnabled(true);
                lblPago.setEnabled(true);
                jSpinFieldPessoas.setEnabled(true);
                btnImprimir.setEnabled(true);
                checkConcedeDesconto.setEnabled(true);
                lblReceber.setEnabled(true);
                lblReceberPAgamento.setEnabled(true);
                lblNpessoas.setEnabled(true);
                txtTroco.setText("0,00");
                lblTroco.setEnabled(false);
            }
        }
    }//GEN-LAST:event_radioDinheiroMouseClicked

    private void radioCartaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioCartaoMouseClicked
        if (radioCartao.isSelected()) {
            if (cc.temNaCozinha(lblNPedido.getText())) {
                // Informa ao usuário se existe parato pendente na cozinha
                mensagem();
            } else {
                txtValorPago.setText(lblTotal.getText());
                txtValorPago.setEnabled(false);
                lblTroco.setEnabled(false);
                txtTroco.setText("0,00");
                jSpinFieldPessoas.setEnabled(true);
                btnImprimir.setEnabled(true);
                checkConcedeDesconto.setEnabled(true);
                lblReceber.setEnabled(true);
                lblReceberPAgamento.setEnabled(true);
                lblNpessoas.setEnabled(true);
                lblPago.setEnabled(true);
            }
        }
    }//GEN-LAST:event_radioCartaoMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        utils.abrirCalculadora();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void lblAlteraSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlteraSenhaMouseClicked
        if (alteraSenha2 == null) {
            alteraSenha2 = new TelaAlteraSenha2();
            alteraSenha2.receberOperador(lblOperador.getText());
            alteraSenha2.recebeTela(this, "Caixa"); // Envia Tela Caixa como parâmetro e informa o nome da tela
        }
        // alteraSenha2.setAlwaysOnTop(true);
        alteraSenha2.setVisible(true);
    }//GEN-LAST:event_lblAlteraSenhaMouseClicked
    // Atualiza a tela de senha após clique no botão cancelar
    public void atualizaTelaSenha() {
        alteraSenha2 = null;
    }

    private void lblLancarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLancarPedidoMouseClicked
        // Chama a tela de Pedidos
        if (lblLancarPedido.isEnabled()) {

            if (detalheMesa == null) {
                detalheMesa = new TelaDetalheMesa();
                detalheMesa.recebeOperador(lblOperador.getText().toLowerCase(), lblCargo.getText());
                detalheMesa.recebeTelaCaixa(this);
                if ("Selecione".equals(comboMesa.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(this, "Selecione uma mesa para continuar!");
                } else {
                    detalheMesa.recebeMesa(comboMesa.getSelectedItem().toString());
                }
            }
            detalheMesa.setVisible(true);

        }
    }//GEN-LAST:event_lblLancarPedidoMouseClicked

    private void lblConsultarPrecosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConsultarPrecosMouseClicked
        // Chama tela de Pesquisa de Preços
        if (pesquisa == null) {
            pesquisa = new TelaPesquisaPreco();
        }
        pesquisa.setVisible(true);

    }//GEN-LAST:event_lblConsultarPrecosMouseClicked

    private void radioDebitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioDebitoMouseClicked
        if (radioDebito.isSelected()) {
            if (cc.temNaCozinha(lblNPedido.getText())) {
                // Informa ao usuário se existe parato pendente na cozinha
                mensagem();
            } else {

                txtValorPago.setText(lblTotal.getText());
                txtValorPago.setEnabled(false);
                lblTroco.setEnabled(false);
                txtTroco.setText("0,00");
                jSpinFieldPessoas.setEnabled(true);
                btnImprimir.setEnabled(true);
                checkConcedeDesconto.setEnabled(true);
                lblReceber.setEnabled(true);
                lblReceberPAgamento.setEnabled(true);
                lblNpessoas.setEnabled(true);
                lblPago.setEnabled(true);
            }
        }
    }//GEN-LAST:event_radioDebitoMouseClicked

    private void radioCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCartaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCartaoActionPerformed

    private void radioVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioVoucherMouseClicked
        if (radioVoucher.isSelected()) {
            if (cc.temNaCozinha(lblNPedido.getText())) {
                // Informa ao usuário se existe parato pendente na cozinha
                mensagem();
            } else {

                txtValorPago.setText(lblTotal.getText());
                txtValorPago.setEnabled(false);
                lblTroco.setEnabled(false);
                txtTroco.setText("0,00");
                jSpinFieldPessoas.setEnabled(true);
                btnImprimir.setEnabled(true);
                checkConcedeDesconto.setEnabled(true);
                lblReceber.setEnabled(true);
                lblReceberPAgamento.setEnabled(true);
                lblNpessoas.setEnabled(true);
                lblPago.setEnabled(true);
            }
        }
    }//GEN-LAST:event_radioVoucherMouseClicked

    private void jtabedFormaPagtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabedFormaPagtoMouseClicked

        if (jtabedFormaPagto.getSelectedIndex() != 0) { // Guia Pagamento Misto
            // Retorna os valores ao estado original do pedido ao clicar na guia Pagamento Misto
            atualizaPedidoNoCaixa();
            atualizaPedidoNoCaixa();
            desabilitaBtnRadio();
            lblPago.setEnabled(false);
            lblTroco.setEnabled(false);
            btnImprimir.setEnabled(true);
            jSpinFieldPessoas.setEnabled(true);
            lblNpessoas.setEnabled(true);
            txtTroco.setText("0,00");
            String strValorPago = txtValorPago.getText().replace(",", ".");//9999.00
            strValorPago = strValorPago.replace(".", "");
            double valorPpago = Double.parseDouble(strValorPago);
            double totalmisto = dinheiro + credito + debito + voucher;
            if ((valorPpago > 0 && jtabedFormaPagto.getSelectedIndex() != 0) && totalmisto == 0) {
                txtValorPago.setText("0,00");
            } else {

                if (radioCartao.isSelected() || radioDebito.isSelected() || radioDinheiro.isSelected() || radioVoucher.isSelected()) {

                } else {
                    txtValorPago.setText("0,00");
                    txtTroco.setText("0,00");
                    txtDesconto.setText("0,00");
                }

            }
        } else {
            // Retorna os valores ao estado original do pedido após sair da guia
            // Pagamento misto
            if ((dinheiro+credito+debito + voucher)>0){
                atualizaPedidoNoCaixa();
                atualizaPedidoNoCaixa();                
            }
            // Se Primeira aba for selecionada
            txtValorPago.setText("0,00");
            txtTroco.setText("0,00");
            lblReceber.setEnabled(false);
            txtMistoDinheiro.setText("0,00");
            txtMistoDebito.setText("0,00");
            txtMistoCredito.setText("0,00");
            txtMistoVoucher.setText("0,00");
            lblPago.setEnabled(false);
            lblTroco.setEnabled(false);
            buttonGroup2.clearSelection();

            if (radioCartao.isSelected() || radioDebito.isSelected() || radioDinheiro.isSelected() || radioVoucher.isSelected()) {

            } else {
                // Zera os campos de pagamento misto se senhum RadioButton estiver sido selecionado.    
                txtValorPago.setText("0,00");
                txtTroco.setText("0,00");
                lblReceber.setEnabled(false);
                txtMistoDinheiro.setText("0,00");
                txtMistoDebito.setText("0,00");
                txtMistoCredito.setText("0,00");
                txtMistoVoucher.setText("0,00");
                buttonGroup2.clearSelection();
            }

        }
    }//GEN-LAST:event_jtabedFormaPagtoMouseClicked

    private void panelPagParcialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPagParcialMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelPagParcialMouseClicked

    private void checkReimpressaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkReimpressaoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_checkReimpressaoMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        chamaTelaSaldoInicial();
    }//GEN-LAST:event_formWindowOpened

    private void txtValorPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorPagoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTroco.requestFocus();
            double totalGeral = Double.parseDouble(lblTotal.getText().replace(",", "."));
            String strValorPago = txtValorPago.getText().replace(".", ""); //99.999,99
            strValorPago = strValorPago.replace(",", ".");
            double totalPago = Double.parseDouble(strValorPago);

            if (totalPago >= totalGeral) {
                //txtTroco.setText(String.format("%9.2f", totalPago - totalGeral));
                txtTroco.setText(fv.Formata(String.valueOf(totalPago - totalGeral)));

                if ("     0,00".equals(txtTroco.getText())) {
                    lblTroco.setEnabled(false);
                } else {
                    lblTroco.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "O valor a ser pago não pode ser menor que o total da conta!");
                txtTroco.setText("00,00");
                txtValorPago.setText(lblTotal.getText());

            }
        }
    }//GEN-LAST:event_txtValorPagoKeyPressed

    private void txtValorPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValorPagoMouseClicked
        // Limpa valor pago ao clicar
        if (txtValorPago.isEnabled()) {
            txtValorPago.setText(null);
        }
    }//GEN-LAST:event_txtValorPagoMouseClicked

    private void txtMistoDinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMistoDinheiroKeyPressed
        // Muda de caixa de texto após pressionar enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMistoCredito.requestFocus();
        }
    }//GEN-LAST:event_txtMistoDinheiroKeyPressed

    private void txtMistoDinheiroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMistoDinheiroFocusGained
        txtMistoDinheiro.selectAll();
    }//GEN-LAST:event_txtMistoDinheiroFocusGained

    private void txtMistoDinheiroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMistoDinheiroFocusLost
        // Formata o valor informado e calcula o total pago
        try {
            calculaPagamentoMisto();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor Inválido!");
            System.out.println("br.com.bar.view.TelaCaixa.txtMistoDinheiroKeyPressed()" + e);
        }
    }//GEN-LAST:event_txtMistoDinheiroFocusLost

    private void txtMistoDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMistoDinheiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMistoDinheiroActionPerformed

    private void txtMistoCreditoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMistoCreditoFocusGained
        txtMistoCredito.selectAll();
    }//GEN-LAST:event_txtMistoCreditoFocusGained

    private void txtMistoCreditoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMistoCreditoFocusLost
        // Formata o valor informado e calcula o total pago
        try {
            calculaPagamentoMisto();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor Inválido!");
            System.out.println("br.com.bar.view.TelaCaixa.txtMistoCreditoKeyPressed()" + e);
        }
    }//GEN-LAST:event_txtMistoCreditoFocusLost

    private void txtMistoCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMistoCreditoKeyPressed
        // Muda de caixa de texto após pressionar enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMistoDebito.requestFocus();
        }
    }//GEN-LAST:event_txtMistoCreditoKeyPressed

    private void txtMistoDebitoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMistoDebitoFocusGained
        txtMistoDebito.selectAll();
    }//GEN-LAST:event_txtMistoDebitoFocusGained

    private void txtMistoDebitoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMistoDebitoFocusLost
        // Formata o valor informado e calcula o total pago      
        try {
            txtMistoVoucher.requestFocus();
            calculaPagamentoMisto();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor Inválido!");
            System.out.println("br.com.bar.view.TelaCaixa.txtMistoDebitoKeyPressed()" + e);
        }
    }//GEN-LAST:event_txtMistoDebitoFocusLost

    private void txtMistoDebitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMistoDebitoKeyPressed
        // Muda de caixa de texto após pressionar enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMistoVoucher.requestFocus();
        }
    }//GEN-LAST:event_txtMistoDebitoKeyPressed

    private void txtMistoVoucherFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMistoVoucherFocusGained
        txtMistoVoucher.selectAll();
    }//GEN-LAST:event_txtMistoVoucherFocusGained

    private void txtMistoVoucherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMistoVoucherFocusLost
        // Formata o valor informado e calcula o total pago
        try {

//            double vlrVoucher = Double.parseDouble(txtMistoVoucher.getText().replace(",", "."));
//            txtMistoVoucher.setText(String.format("%9.2f", vlrVoucher));
            calculaPagamentoMisto();
            validaPagamentoMisto();
            checkConcedeDesconto.setEnabled(true);
        } catch (NumberFormatException e) {
            //System.out.println("br.com.bar.view.TelaCaixa.txtMistoVoucherKeyPressed()" + e);
            //System.out.println(txtMistoVoucher);
            JOptionPane.showMessageDialog(this, "Valor Inválido!");
        }
    }//GEN-LAST:event_txtMistoVoucherFocusLost

    private void txtMistoVoucherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMistoVoucherKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMistoDinheiro.requestFocus();
        }
    }//GEN-LAST:event_txtMistoVoucherKeyPressed

    private void btnTesteCalculoPedidoTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTesteCalculoPedidoTelaActionPerformed

        int linhas = tblDetalhePedido.getRowCount();
        double total = 0;

        for (int i = 0; i < linhas; i++) {
            double valor = Double.parseDouble(tblDetalhePedido.getValueAt(i, 4).toString().replace(",", "."));
            total = total + valor;
        }

        labelExibeCalculo.setText(String.valueOf(total));


    }//GEN-LAST:event_btnTesteCalculoPedidoTelaActionPerformed
    public void recebeOperador(TelaPrincipal tela, String operador, String cargo) {
        lblLLogo.setIcon(utils.carregaLogo());
        lblOperador.setText(operador);
        lblCargo.setText(cargo);
        l.setUsuario(operador);
        String id = func.localizaIdLogin(operador);
        this.operador = operador;
        this.cargo = cargo;
        btnListar.setEnabled(false);

        caixa.statusCaixa(lblStatus, caixa.retornaStatusCaixa(Integer.parseInt(id)), lblMsgStatus);

        if ("Caixa Fechado".equals(lblMsgStatus.getText())) {

            lblReceber.setEnabled(false);
            lblReceberPAgamento.setEnabled(false);
            btnFecharCaixa.setEnabled(false);
            btnListar.setEnabled(false);
            lblContasAPagar.setEnabled(false);
            comboMesa.setEnabled(false);
            lblReceberPAgamento.setEnabled(false);
            tgeral.setEnabled(false);
            percent.setEnabled(false);
            labelFecharCaixa.setEnabled(false);

        } else {

            if (comboMesa.getItemCount() == 1) {
                btnListar.setEnabled(false);
                comboMesa.setEnabled(false);

            }
        }
        if ("Gerente".equals(lblCargo.getText())) {
            lblAlteraSenha.setVisible(false);
        }

        // Armazena a instância da tela Principal
        this.principal = tela;

    }

    public void trocaicone(String statusCaixa) {

        if ("Fechado".equals(statusCaixa)) {
            caixa.statusCaixa(lblStatus, true, lblMsgStatus);
        } else {
            caixa.statusCaixa(lblStatus, false, lblMsgStatus);
        }
    }

    public void chamaTelaSaldoInicial() {

        boolean mov = caixa.temMovimentacao(Integer.parseInt(func.localizaIdLogin(operador)));
        if (!mov) {
            caixa.statusCaixa(lblStatus, true, lblMsgStatus);
            TelaSaldoInicial saldoInicial = new TelaSaldoInicial();
            saldoInicial.setModal(true);
            saldoInicial.recebeTela(this);
            saldoInicial.recebeOperador(operador, cargo);
            saldoInicial.setVisible(true);
        }
    }

    public void limpaForm() {
        txtIdMEsa.setText(null);
        txtIdPedido.setText(null);
        percent.setText(null);
        tgeral.setText(null);
        txtTroco.setText(null);
        txtValorPago.setText(null);
    }

    private void carregaComboPeriodo(JComboBox combo, String mes) {

        List<String> listaMes = new ArrayList<>();
        listaMes.add("Janeiro");
        listaMes.add("Fevereiro");
        listaMes.add("Março");
        listaMes.add("Abril");
        listaMes.add("Maio");
        listaMes.add("Junho");
        listaMes.add("Julho");
        listaMes.add("Agosto");
        listaMes.add("Setembro");
        listaMes.add("Outubro");
        listaMes.add("Novembro");
        listaMes.add("Dezembro");

        for (int i = 0; i < Integer.parseInt(mes); i++) {
            combo.addItem(listaMes.get(i));
        }

    }

    public void recebeDadosComDesconto(ArrayList<String> dados, int index) {
        listAutoDesconto = dados;
        // total - tx de servico - total geral - Desconto - Mesa
        tgeral.setText(dados.get(0));
        percent.setText(dados.get(1));
        lblTotal.setText(dados.get(2));
        txtIdPedido.setText(dados.get(4));
        if ("Cartão".equals(dados.get(5))) {
            //checkCartao.setSelected(true);// Excluir
            radioCartao.setSelected(true);
        } else {
            //checkDinheiro.setSelected(true); // Excluir
            //radioDinheiro.setSelected(rootPaneCheckingEnabled);
        }
        txtDesconto.setText(dados.get(6));

        comboMesa.setSelectedItem(dados.get(3));
        tblDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(comboMesa.getSelectedItem().toString(), txtIdPedido.getText())));
        modelCaixa.redimensionaColunas(tblDetalhePedido);
        // checkCartao.setEnabled(true); // Excluir
        radioCartao.setEnabled(true);
        // Se o pedido for um Delivery ele desabilitará o check desconto.
        if ("delivery".equals(lblGarcom.getText().toLowerCase())) {
            checkTxServico.setSelected(false);
            checkTxServico.setEnabled(false);
        } else {

            checkTxServico.setEnabled(true);
        }
        //checkDinheiro.setEnabled(true); // Excluir
        radioDinheiro.setEnabled(true);
        txtValorPago.setText(dados.get(2));
        lblPago.setEnabled(true);
        lblValorDesc.setEnabled(true);
        checkConcedeDesconto.setSelected(false);

        //****************************************** TESTE ********************/// APAGAR APÓS VALIDAÇÃO
        //System.out.println("Selecionando a guia: " + index);
        //Remove a seleção dos botões de rádio se a aguia for 1=Pagamento Misto
        if (index == 1) {
            buttonGroup2.clearSelection();
        }
        jtabedFormaPagto.setSelectedIndex(index);

        //**** FIM DO TESTE
        if (jtabedFormaPagto.getSelectedIndex() == 1) {
            calculaPagamentoMisto();
            validaPagamentoMisto();
            /// ************* Teste ******************////

        }

        aplicaDescontoTela3(Double.parseDouble(txtDesconto.getText().replace(",", ".")), Double.parseDouble(tgeral.getText().replace(",", ".")));

    }
    // Atualiza o pedido no caixa pós inclusão de item de última hora

    public void atualizaPedidoNoCaixa() {

        try {

            if (!"Selecione...".equals(comboMesa.getSelectedItem().toString())) {
                //checkCartao.setEnabled(true);// Excluir
                checkTxServico.setEnabled(true);
                //checkDinheiro.setEnabled(true);  // Excluir
                radioDinheiro.setEnabled(true);
                radioCartao.setEnabled(true);
                radioDebito.setEnabled(true);
                radioVoucher.setEnabled(true);
                // Localiza o id da mesa selecionada
                txtIdMEsa.setText(cm.localizaIdMesa(comboMesa.getSelectedItem().toString()));
                //Localiza o id do  Pedido
                txtIdPedido.setText(cp.LocalizaIdPedido(comboMesa.getSelectedItem().toString()));
                lblNPedido.setText(txtIdPedido.getText());
                // Exibe detalhe do pedido por mesa de acordo com o id do pedido
                tblDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(comboMesa.getSelectedItem().toString(), txtIdPedido.getText())));
                modelCaixa.redimensionaColunas(tblDetalhePedido);

                // Muda Estado dos campos
                jtabedFormaPagto.setEnabled(true);
                mudaEstadoCaposMisto(true);
                // Inicializa das variáveis TOTAL E totalGeral
                double total = 0;
                double totalGeral = 0;

                try {
                    // Lista resultado da pesquisa e totaliza os produtos do pedido
                    ResultSet rs = cp.detalhePorPedido(comboMesa.getSelectedItem().toString(), txtIdPedido.getText());

                    while (rs.next()) {
                        NumberFormat nf = NumberFormat.getNumberInstance();
                        try {
                            total = nf.parse(rs.getString("VLR TOTAL R$")).doubleValue();
                            totalGeral = totalGeral + total;
                        } catch (ParseException ex) {
                            Logger.getLogger(TelaCaixa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    // Habilita Labels                    
                    habilitaLabels();
                    lblPago.setEnabled(false);
                    lblTroco.setEnabled(false);

                    // Formata e exibe o total geral do pedido
                    tgeral.setText(String.format("%9.2f", totalGeral));
                    tgeral.setEnabled(true);
                    lblTotal.setEnabled(true);
                    percent.setEnabled(true);
                    int linhas = tblDetalhePedido.getRowCount();
                    // Desabilita labels caso não exista pedidos abertos
                    if (linhas == 0) {
                        percent.setEnabled(false);
                        tgeral.setEnabled(false);
                        lblTotal.setEnabled(false);
                        radioDinheiro.setEnabled(false);
                        radioCartao.setEnabled(false);
                        radioDebito.setEnabled(false);
                        radioVoucher.setEnabled(false);
                        checkTxServico.setEnabled(false);
                        //Labels
                        desabilitaLabels();
                        jtabedFormaPagto.setEnabled(false);
                        mudaEstadoCaposMisto(false);
                    }

                } catch (NumberFormatException | SQLException e) {
                    System.out.println("br.com.bar.view.TelaCaixa.btnListarActionPerformed()" + e);
                }
                // Calcula a taxa de serviço somando ao total do pedido
                calculaTaxa();

                lblGarcom.setText(func.retornaGarcom(comboMesa.getSelectedItem().toString()));

                //
                txtValorPago.setText("0,00");
                txtTroco.setText("0,00");
                txtDesconto.setText("0,00");
                //lblReceber.setEnabled(true);
                // Substituição multipla na sequencia para evitar NumberFormatException: Multiple Points
                // Calsada pela formatação 0.000,00
                String sTotal = lblTotal.getText().replace(".", "");
                sTotal = sTotal.replace(",", ".");
                if (Double.parseDouble(sTotal) == 0) {
                    btnImprimir.setEnabled(false);
                    lblReceber.setEnabled(false);
                } else {
                    //lblReceber.setEnabled(true);
                    btnImprimir.setEnabled(true);
                }

                // Formata valores após listagem
                FormataValor fv = new FormataValor();
                tgeral.setText(fv.Formata(tgeral.getText()));
                lblTotal.setText(fv.Formata(lblTotal.getText()));
                jSpinFieldPessoas.setEnabled(true);

            }
        } catch (NumberFormatException e) {

        }
        idGarcom = func.localizaId(lblGarcom.getText());
        // Fecha a conexao com o banco. 
        try {
            conexao.close();
        } catch (SQLException e) {
            System.out.println("br.com.bar.view.TelaCaixa.btnListarActionPerformed()" + e);
        }
//        // Zera taxa de Serviço para pedidos Delivery
//        if ("delivery".equals(lblGarcom.getText().toLowerCase())){
//            lbl_valor_servico.setText("Taxa de Entrega");
//            percent.setText("0,00");
//        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCaixa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnFechar;
    private javax.swing.JLabel btnFecharCaixa;
    private javax.swing.JLabel btnGrafico;
    private javax.swing.JLabel btnImprimir;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnTesteCalculoPedidoTela;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox checkConcedeDesconto;
    private javax.swing.JCheckBox checkExibir;
    private javax.swing.JCheckBox checkReimpressao;
    private javax.swing.JCheckBox checkTxServico;
    private javax.swing.JComboBox<String> comboMes;
    private javax.swing.JComboBox<String> comboMesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelFormaPagamento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinFieldPessoas;
    private javax.swing.JPanel jpanelSubTotal;
    private javax.swing.JPanel jpanelTotalGeral;
    private javax.swing.JTabbedPane jtabedFormaPagto;
    private javax.swing.JLabel labelEntradas;
    private javax.swing.JLabel labelExibeCalculo;
    private javax.swing.JLabel labelFecharCaixa;
    private javax.swing.JLabel labelPedido;
    private javax.swing.JLabel labelSaidas;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JLabel lblAlteraSenha;
    private javax.swing.JLabel lblAmbiante;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblConsultarPrecos;
    private javax.swing.JLabel lblContasAPagar;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblEntradas;
    private javax.swing.JLabel lblGarcom;
    private javax.swing.JLabel lblLLogo;
    private javax.swing.JLabel lblLancarPedido;
    private javax.swing.JLabel lblMsgStatus;
    private javax.swing.JLabel lblNPedido;
    private javax.swing.JLabel lblNpessoas;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPago;
    private javax.swing.JLabel lblReceber;
    private javax.swing.JLabel lblReceberPAgamento;
    private javax.swing.JLabel lblSaidas;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblSaldoInicial;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTroco;
    private javax.swing.JLabel lblValorDesc;
    private javax.swing.JLabel lbl_cifra_parcial;
    private javax.swing.JLabel lbl_cifra_servico;
    private javax.swing.JLabel lbl_cifra_total;
    private javax.swing.JLabel lbl_total_parcial;
    private javax.swing.JLabel lbl_valor_servico;
    private javax.swing.JPanel painelDireito;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JPanel panelGrafico;
    private javax.swing.JPanel panelMovimentacao;
    private javax.swing.JPanel panelPagParcial;
    private javax.swing.JPanel panelValores;
    private javax.swing.JLabel percent;
    private javax.swing.JRadioButton radioCartao;
    private javax.swing.JRadioButton radioDebito;
    private javax.swing.JRadioButton radioDinheiro;
    private javax.swing.JRadioButton radioVoucher;
    private javax.swing.JTable tblDetalhePedido;
    private javax.swing.JLabel tgeral;
    private javax.swing.JTextField txtDesconto;
    private javax.swing.JTextField txtIdMEsa;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JFormattedTextField txtMistoCredito;
    private javax.swing.JFormattedTextField txtMistoDebito;
    private javax.swing.JFormattedTextField txtMistoDinheiro;
    private javax.swing.JFormattedTextField txtMistoVoucher;
    private javax.swing.JTextField txtTroco;
    private javax.swing.JFormattedTextField txtValorPago;
    // End of variables declaration//GEN-END:variables

    private void calculaTaxa() {
        FormataValor fv = new FormataValor();

        //double txServico = 0.10; // 10%
        double txServico = 0.10;

        //double totalConta = Double.parseDouble(lblTotal.getText().replaceAll(",", "."));
        String tConta = tgeral.getText().replace(".", "");
        String nomeGarcom = lblGarcom.getText();
        tConta = tConta.replace(",", ".");
        double totalConta = Double.parseDouble(tConta);

        double totalTxServico = totalConta * txServico;
        String desc = txtDesconto.getText().replace(".", "");
        desc = desc.replace(",", ".");
        double desconto = Double.parseDouble(desc);
        double totalPago = 0;
        double troco = 0;

        if (checkTxServico.isSelected()) {

            double tTotal = (totalConta + totalTxServico) - desconto;
            lblTotal.setText(fv.Formata(String.valueOf(tTotal)));
            percent.setText(fv.Formata(String.valueOf(totalTxServico)));

        } else {

            lblTotal.setText(tgeral.getText());
            percent.setText("0,00");
            double totalGeral = Double.parseDouble(lblTotal.getText().replace(",", "."));
            double vlrDesconto = Double.parseDouble(txtDesconto.getText().replace(",", "."));
            lblTotal.setText(String.format("%9.2f", totalGeral - vlrDesconto));
            //lblPago.setEnabled(true);
        }

    }

    private void calculaTaxa(String txDeEntrega) {
        FormataValor fv = new FormataValor();

        //double txServico = 0.10; // 10%
        double txServico = 0;
        double entrega = 0;
        try {
            entrega = Double.parseDouble(txDeEntrega.replace(",", "."));
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("br.com.bar.view.TelaCaixa.calculaTaxa()");
            percent.setText("0,00");
        }
        //double totalConta = Double.parseDouble(lblTotal.getText().replaceAll(",", "."));
        String tConta = tgeral.getText().replace(".", "");
        String nomeGarcom = lblGarcom.getText();
        tConta = tConta.replace(",", ".");
        double totalConta = Double.parseDouble(tConta);

        double totalTxServico = totalConta * txServico;
        String desc = txtDesconto.getText().replace(".", "");
        desc = desc.replace(",", ".");
        double desconto = Double.parseDouble(desc);
        double totalPago = 0;
        double troco = 0;

        if (checkTxServico.isSelected()) {

            double tTotal = (totalConta + totalTxServico + entrega) - desconto;
            lblTotal.setText(fv.Formata(String.valueOf(tTotal)));
            percent.setText(fv.Formata(String.valueOf(totalTxServico)));

        } else {

            lblTotal.setText(tgeral.getText());
            percent.setText("0,00");
            double totalGeral = Double.parseDouble(lblTotal.getText().replace(",", "."));
            double vlrDesconto = Double.parseDouble(txtDesconto.getText().replace(",", "."));
            lblTotal.setText(String.format("%9.2f", (totalGeral+entrega) - vlrDesconto));
            //lblPago.setEnabled(true);
        }

    }

    private void habilitaTextFildPagamento() {

        txtValorPago.setVisible(true);
        txtTroco.setVisible(true);
        lblPago.setVisible(true);
        lblTroco.setVisible(true);

    }

    private void desabilitaTextFildPagamento() {

        txtValorPago.setVisible(false);
        txtTroco.setVisible(false);
        lblPago.setVisible(false);
        lblTroco.setVisible(false);

    }

    // Atualiza movimentação do Caixa
    public void atualizaCaixa() {
        try {
            lblSaidas.setText(String.format("%9.2f", caixa.totalizaSaida(lblOperador.getText())));
        } catch (NullPointerException e) {
            lblSaidas.setText("0.00");
        }

        try {
            lblEntradas.setText(String.format("%9.2f", caixa.totalizaEntradas(lblOperador.getText())));
        } catch (NullPointerException e) {
            lblEntradas.setText("0.00");
        }
        double saldoInicial = caixa.retornaSaldoInicial(func.localizaIdLogin(lblOperador.getText()));
        lblSaldoInicial.setText(String.format("%9.2f", saldoInicial));
        // Saldo Final
        double saldo = caixa.totalizaEntradas(lblOperador.getText()) - caixa.totalizaSaida(lblOperador.getText());
        saldo = saldo + saldoInicial;
        lblSaldo.setText(String.format("%9.2f", saldo));

    }

    private void bloqueiaControlePagamento() {
        txtValorPago.setEnabled(false);
        txtTroco.setEnabled(false);

        radioDinheiro.setEnabled(false);
        radioCartao.setEnabled(false);

        checkTxServico.setEnabled(false);
        jSpinFieldPessoas.setEnabled(false);
        btnImprimir.setEnabled(false);
        //checkConcedeDesconto.setEnabled(false);
        lblValorDesc.setEnabled(false);
        txtDesconto.setEnabled(false);
        lblReceber.setEnabled(false);

        desabilitaLabels();

    }

    // Habilita Labels
    private void habilitaLabels() {
        lbl_valor_servico.setEnabled(true);
        lbl_total_parcial.setEnabled(true);
        lbl_cifra_servico.setEnabled(true);
        lbl_cifra_total.setEnabled(true);
        lbl_cifra_parcial.setEnabled(true);
        lblPago.setEnabled(true);
        lblTroco.setEnabled(true);
        lblNpessoas.setEnabled(true);
        lblLancarPedido.setEnabled(true);
        lblConsultarPrecos.setEnabled(true);
    }

    // Desabilita Labels
    private void desabilitaLabels() {

        lbl_valor_servico.setEnabled(false);
        lbl_total_parcial.setEnabled(false);
        lbl_cifra_servico.setEnabled(false);
        lbl_cifra_total.setEnabled(false);
        lbl_cifra_parcial.setEnabled(false);

        lblPago.setEnabled(false);
        lblTroco.setEnabled(false);
        lblNpessoas.setEnabled(false);
        lblLancarPedido.setEnabled(false);
        //lblConsultarPrecos.setEnabled(false);

    }

    private void desabilitaBtnRadio() {
        int index = jtabedFormaPagto.getSelectedIndex();

        if ("Pagamento Misto".equals(jtabedFormaPagto.getTitleAt(index))) {
            buttonGroup2.clearSelection();
            txtValorPago.setEnabled(false);
            jSpinFieldPessoas.setEnabled(false);
            lblReceber.setEnabled(false);
            lblReceberPAgamento.setEnabled(false);
            buttonGroup2.clearSelection();
        } else {
            txtMistoDinheiro.setText("0,00");
            txtMistoCredito.setText("0,00");
            txtMistoDebito.setText("0,00");
            txtMistoVoucher.setText("0,00");
            lblReceber.setEnabled(false);
            lblReceberPAgamento.setEnabled(false);

        }

    }

    private void calculaPagamentoMisto() {

        try {
            dinheiro = Double.parseDouble(txtMistoDinheiro.getText().replace(",", "."));
            credito = Double.parseDouble(txtMistoCredito.getText().replace(",", "."));
            debito = Double.parseDouble(txtMistoDebito.getText().replace(",", "."));
            voucher = Double.parseDouble(txtMistoVoucher.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            //System.out.println("Informe um valor válido!");
        }

        //double totalPedido = Double.parseDouble(lblTotal.getText().replace(",", "."));
        double totalCartao = credito + debito + voucher;

        double total = totalCartao + dinheiro;
        txtValorPago.setText(fv.Formata(String.valueOf(total)));

    }

    // Realiza a validação dos valores informados para o pagamenot misto
    private void validaPagamentoMisto() {
        //  Verifica antes de liberar o pagamento se existe na cozinha algum 
        //  prato com situação diferente de liberado para este pedido.
        if (cc.temNaCozinha(lblNPedido.getText())) {
            //JOptionPane.showMessageDialog(this, "Há prato(s) ainda sendo preparado(s) na cozinha!", "Atenção!", JOptionPane.ERROR_MESSAGE);
            mensagem();
        } else {

            double totalCartao = credito + debito + voucher;
            String arreTotalCartao = String.format("%9.2f", totalCartao).replace(",", ".");
            totalCartao = Double.parseDouble(arreTotalCartao);
            double totalMisto = dinheiro + totalCartao; // 
            String arredTotalMisto = String.format("%9.2f", totalMisto).replace(",", ".");
            totalMisto = Double.parseDouble(arredTotalMisto);
            double totalPedido = Double.parseDouble(lblTotal.getText().replace(",", "."));
            int cont = 0;
            if (dinheiro > 0) {
                cont = cont + 1;
            }
            if (credito > 0) {
                cont = cont + 1;
            }
            if (debito > 0) {
                cont = cont + 1;
            }
            if (voucher > 0) {
                cont = cont + 1;
            }
            // Vontinua a validação apenas se houve mais de uma forma de pagamento.
            if (cont > 1) {
                // Habilita label valor pago
                lblPago.setEnabled(true);
                if (totalCartao > totalPedido) {
                    JOptionPane.showMessageDialog(this, "O valor informado em cartão(ões) excede o total da conta!");
                } else if (totalCartao < totalPedido) {
                    if ((dinheiro > 0) && (totalMisto > totalPedido)) {
                        double troco = totalMisto - totalPedido;
                        //Reduz para duas casas decimal o total misto
                        txtTroco.setText(String.format("%9.2f", troco));
                        // Habilita Troco
                        lblTroco.setEnabled(true);
                        // Habilita Recebimento
                        lblReceber.setEnabled(true);
                        lblReceberPAgamento.setEnabled(true);
                        troco = Double.parseDouble(txtTroco.getText().replace(",", "."));
                        String vlrDrinheiroPago = String.format("%9.2f", dinheiro - troco).replace(",", ".");
                        dinheiro = Double.parseDouble(vlrDrinheiroPago);
                        dinheiroPago = Double.parseDouble(vlrDrinheiroPago);
                        dinheiro = dinheiroPago;
                        //System.out.println("Valor Real Dinheiro" + dinheiro);
                        //else if ((dinheiro > 0) && (totalMisto == totalPedido)) // Excluir após validação
                    } else if (totalMisto == totalPedido) {
                        // Habilita Recebimento
                        lblReceber.setEnabled(true);
                        lblReceberPAgamento.setEnabled(true);
                        //Desabilita Troco
                        lblTroco.setEnabled(false);
                        txtTroco.setText("0,00");
                        dinheiroPago = dinheiro;

                    } else {
                        JOptionPane.showMessageDialog(this, "O valor informado é menor que o total da conta!");
                    }
                } else if (totalCartao == totalPedido) {

                    if (dinheiro > 0) {
                        JOptionPane.showMessageDialog(this, "O valor informado em dinheiro excede o total da conta!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                        //Desabilita Troco
                        lblTroco.setEnabled(false);
                        txtTroco.setText("0,00");

                    } else {
                        if (totalMisto > totalPedido) {
                            double troco = totalMisto - totalPedido;
                            txtTroco.setText(String.format("%9.2f", troco));
                            lblTroco.setEnabled(true);
                            // Habilita Recebimento
                            lblReceber.setEnabled(true);
                            lblReceberPAgamento.setEnabled(true);
                        } else {
                            // Habilita Recebimento
                            lblReceber.setEnabled(true);
                            lblReceberPAgamento.setEnabled(true);
                            //Desabilita Troco
                            lblTroco.setEnabled(false);
                            txtTroco.setText("0,00");
                        }
                    }
                    // habilita recebimento
                    lblReceber.setEnabled(true);
                    lblReceberPAgamento.setEnabled(true);

                }
            } else {
                JOptionPane.showMessageDialog(this, "É necessário preencher com mais de uma forma de pagamento.", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Detecta a forma de Pagamento
    private String detectaFormaDePagamento() {
        // detecta forma de pagamento

        boolean rCartao = radioCartao.isSelected();
        boolean rVoucher = radioVoucher.isSelected();
        boolean rDebito = radioDebito.isSelected();
        boolean rDinheiro = radioDinheiro.isSelected();

        String resp = "";

        if (rCartao || rVoucher || rDebito || rDinheiro) {
            resp = "Único";
        } else {
            try {
                double mDinheiro = Double.parseDouble(txtMistoDinheiro.getText().replace(",", "."));
                double mcredito = Double.parseDouble(txtMistoCredito.getText().replace(",", "."));
                double mVoucher = Double.parseDouble(txtMistoVoucher.getText().replace(",", "."));
                double mDedebito = Double.parseDouble(txtMistoDebito.getText().replace(",", "."));

                double total = mDinheiro + mcredito + mDedebito + mVoucher;

                if (total > 0) {
                    resp = "MISTO";
                } else {
                    resp = "Selecione";
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Valor Inválido!");
                System.out.println("br.com.bar.view.TelaCaixa.detectaFormaDePagamento()" + e);
            }
        }

        return resp;
    }

    private void mudaEstadoCaposMisto(boolean estado) {
        txtMistoCredito.setEnabled(estado);
        txtMistoDebito.setEnabled(estado);
        txtMistoDinheiro.setEnabled(estado);
        txtMistoVoucher.setEnabled(estado);

    }

    public void desabilitaCheckBoxDesconto() {
        checkConcedeDesconto.setSelected(false);
    }

    /**
     * Adicionado em: 10-09-2019 Este método é executado quando o pedido possui
     * apenas um item, ele autoriza a emissão de Nota Fiscal ao Consumidor
     * (NFC-e) e cria um arquivo no formato Json (retorno.Json) contendo os
     * dados de retorno da API com a mensagem do SEFAZ.
     *
     * @param codFormaPagamento Código da Forma de Pagamento Valores possíveis:
     * 01: Dinheiro. 02: Cheque. 03: Cartão de Crédito. 04: Cartão de Débito.
     * 05: Crédito Loja. 10: Vale Alimentação. 11: Vale Refeição. 12: Vale
     * Presente. 13: Vale Combustível. 99: Outros
     * @param valorPedido Valor do Pedido (Ex: 12.00).
     * @param nPedido Número do pedido a ser autorizado
     */
    private void autorizarNfCe(String codFormaPagamento, String valorPedido, String nPedido, String cpfContribuinte) throws JSONException {
        ControlerProduto controlProduto = new ControlerProduto();
        String login;
        // Dados de Conexao com a API - FocusNFe
        if (ambiente == 1) {
            // Token de Produção
            login = "DhdwJcAsy0jGvNDRv7mGZyWeJ19CBRUT";
        } else {
            // Token de Homologação
            login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";
        }

        /* Substituir pela sua identificação interna da nota. */
        String ref = nPedido; // Código do pedido

        /* Para ambiente de produção use a variável abaixo:*/
        String server;
        // Verifica em que ambiente a nota deverá ser emitida (1 - Produção  0 - Homologação)
        if (ambiente == 1) {
            // Produção
            server = "https://api.focusnfe.com.br/";
        } else {
            // Homoloação
            server = "https://homologacao.focusnfe.com.br/";
        }
        String url = server.concat("v2/nfce?ref=" + ref + "&completa=1");
        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        // AdicIona itens do pedido no HashMap Itens
        int linhas = tblDetalhePedido.getRowCount();

        ArrayList<ProdutoNota> listaProdutosNota = new ArrayList<>();
        for (int i = 0; i < linhas; i++) {
            int item = i + 1;
            ProdutoNota pnota = new ProdutoNota();
            pnota.setItem(String.valueOf(item));
            String idProduto = tblDetalhePedido.getModel().getValueAt(i, 0).toString();
            String ncm = controlProduto.localizaNCM(idProduto);
            pnota.setCodProduto(idProduto);
            pnota.setCodNcm(ncm);
            pnota.setDescricao(tblDetalhePedido.getModel().getValueAt(i, 1).toString());
            pnota.setQtd(tblDetalhePedido.getModel().getValueAt(i, 2).toString());
            pnota.setvUnit(tblDetalhePedido.getModel().getValueAt(i, 3).toString().replace(",", "."));
            pnota.setVlrTotal(tblDetalhePedido.getModel().getValueAt(i, 4).toString().replace(",", "."));

            listaProdutosNota.add(pnota);

        }
        // Campos da NFc-e

        //-=--=--=-=-=-= CAMPOS DA NFCe =-==-=-=-==-=- 
        Date dataAtual = new Date();
        String hoje = utils.formataDateTime(dataAtual);

        nfce.put("consumidor_final", "1");
        nfce.put("presenca_comprador", "1");
        nfce.put("forma_pagamento", codFormaPagamento);
        nfce.put("natureza_operacao", "Venda ao Consumidor");
        nfce.put("tipo_documento", "1"); // 1 - Nota Fiscal de Saída
        nfce.put("cpf_destinatario", cpfContribuinte);
        nfce.put("icms_valor_total", "0.0000");
        nfce.put("icms_modalidade_base_calculo", "3");
        nfce.put("valor_frete", "0.0");
        nfce.put("modalidade_frete", "9");// 0 – Por conta do emitente; 1 – Por conta do destinatário; 2 – Por conta de terceiros; 9 – Sem frete;
        nfce.put("icms_base_calculo", "0.0000");
        nfce.put("data_emissao", hoje);
        //nfce.put("cnpj_emitente", "34257575000106"); 
        nfce.put("cnpj_emitente", dadosEmpresa.retornaCnpj()); 
        //nfce.put("valor_desconto", txtDesconto.getText().replace(",", "."));
        json = new JSONObject(this.nfce);
        HashMap<String, String> itens = new HashMap<>();
        for (int i = 0; i < listaProdutosNota.size(); i++) {

            itens.put("numero_item", listaProdutosNota.get(i).getItem());
            itens.put("codigo_produto", listaProdutosNota.get(i).getCodProduto());
            itens.put("valor_desconto", "0.00");
            itens.put("quantidade_comercial", listaProdutosNota.get(i).getQtd());
            itens.put("cfop", "5102"); // Venda de mercadoria adquirida ou recebida de terceiros
            itens.put("icms_situacao_tributaria", "103");
            itens.put("unidade_comercial", "un");
            if (ambiente == 1) {
                itens.put("descricao", listaProdutosNota.get(i).getDescricao()); //Substituir pela descrição do produto no ambiente de produção
            } else {
                itens.put("descricao", "NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL"); //Substituir pela descrição do produto no ambiente de produção
            }
            itens.put("pis_situacao_tributaria", "07");
            itens.put("codigo_ncm", listaProdutosNota.get(i).getCodNcm());
            itens.put("quantidade_tributavel", listaProdutosNota.get(i).getQtd());
            itens.put("unidade_tributavel", "un");
            itens.put("cofins_situacao_tributaria", "07");
            itens.put("valor_unitario_comercial", listaProdutosNota.get(i).getvUnit());
            itens.put("icms_origem", "0");
            itens.put("valor_bruto", listaProdutosNota.get(i).getVlrTotal());
            itens.put("valor_unitario_tributavel", listaProdutosNota.get(i).getvUnit());
            itens.put("valor_frete", "0");

            jSonItens = new JSONObject(itens);
        }
        json.append("items", jSonItens);

        formasPagamento.put("forma_pagamento", codFormaPagamento);
        formasPagamento.put("valor_pagamento", valorPedido);
        jsonPagamento = new JSONObject(this.formasPagamento);

        json.append("formas_pagamento", jsonPagamento);
        // Visualiza contúdo do objeto Json Principal
        //System.out.println(json);

        // Prepara Conexao a API
        /*Envia arquivo para Autorização */
        WebResource request = client.resource(url);

        ClientResponse resposta = request.post(ClientResponse.class, json);

        int httpCode = resposta.getStatus();

        String body = resposta.getEntity(String.class);


        /* As três linhas a seguir exibem as informações retornadas pela nossa API. 
		 * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
        System.out.print("HTTP Code: ");
        System.out.print(httpCode);
        System.out.print(body);

        // Grava saída no arquivo
        try {
            FileOutputStream out = new FileOutputStream("c://sysbar/retorno.json");
            PrintStream ps = new PrintStream(out);
            System.setOut(ps);
            //System.out.println("[" + body + "]");
            System.out.println(body);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Autorizar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Adicionado em: 10-09-2019 Este método é executado quando o pedido possui
     * mais de um item, ele autoriza a emissão de Nota Fiscal ao Consumidor
     * (NFC-e) e cria um arquivo no formato Json (retorno.Json) contendo os
     * dados de retorno da API com a mensagem do SEFAZ.
     *
     * @param codFormaPagamento Código da Forma de Pagamento Valores possíveis:
     * 01: Dinheiro. 02: Cheque. 03: Cartão de Crédito. 04: Cartão de Débito.
     * 05: Crédito Loja. 10: Vale Alimentação. 11: Vale Refeição. 12: Vale
     * Presente. 13: Vale Combustível. 99: Outros
     * @param valorPedido Valor do Pedido (Ex: 12.00).
     * @param nPedido Número do pedido a ser autorizado
     */
    private int autorizarNfCe2(String codFormaPagamento, String valorPedido, String nPedido, String cpfContribuinte) throws JSONException {
        ControlerProduto controlProduto = new ControlerProduto();
        String login;
        if (ambiente == 1) {
            // Dados de Conexao com a API - FocusNFe
            // Ambiente de Produção
            login = "DhdwJcAsy0jGvNDRv7mGZyWeJ19CBRUT";
        } else {
            // Ambiente de Homologação
            login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";
        }

        /* Substituir pela sua identificação interna da nota. */
        String ref = nPedido; // Código do pedido

        /* Para ambiente de produção use a variável abaixo:*/
        String server;
        // Verifica em que ambiente a nota deverá ser emitida (1 - Produção  0 - Homologação)
        if (ambiente == 1) {
            // Ambiente de Produção
            server = "https://api.focusnfe.com.br/";
        } else {
            //Ambiente de Homologação
            server = "https://homologacao.focusnfe.com.br/";
        }
        String url = server.concat("v2/nfce?ref=" + ref + "&completa=0"); // 0 - para retorno simples  / 1- Retorno completo

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        // AdicIona itens do pedido no HashMap Itens
        int linhas = tblDetalhePedido.getRowCount();
        ArrayList<ProdutoNota> listaProdutosNota = new ArrayList<>();
        for (int i = 0; i < linhas; i++) {

            int item = i + 1;
            ProdutoNota pnota = new ProdutoNota();
            pnota.setItem(String.valueOf(item));
            String idProduto = tblDetalhePedido.getModel().getValueAt(i, 0).toString();
            String ncm = controlProduto.localizaNCM(idProduto);
            pnota.setCodProduto(idProduto);
            pnota.setCodNcm(ncm);
            pnota.setDescricao(tblDetalhePedido.getModel().getValueAt(i, 1).toString());
            pnota.setQtd(tblDetalhePedido.getModel().getValueAt(i, 2).toString());
            pnota.setvUnit(tblDetalhePedido.getModel().getValueAt(i, 3).toString().replace(",", "."));
            pnota.setVlrTotal(tblDetalhePedido.getModel().getValueAt(i, 4).toString().replace(",", "."));

            listaProdutosNota.add(pnota);

        }
        // Campos da NFc-e

        //-=--=--=-=-=-= CAMPOS DA NFCe =-==-=-=-==-=- 
        Date dataAtual = new Date();
        String hoje = utils.formataDateTime(dataAtual);

        nfce.put("consumidor_final", "1");
        nfce.put("presenca_comprador", "1");
        nfce.put("forma_pagamento", codFormaPagamento);
        nfce.put("natureza_operacao", "Venda ao Consumidor");
        nfce.put("tipo_documento", "1"); // 1 - Nota Fiscal de Saída
        nfce.put("cpf_destinatario", cpfContribuinte);
        nfce.put("icms_valor_total", "0.0000");
        nfce.put("icms_modalidade_base_calculo", "3");
        nfce.put("valor_frete", "0.0");
        nfce.put("modalidade_frete", "9");// 0 – Por conta do emitente; 1 – Por conta do destinatário; 2 – Por conta de terceiros; 9 – Sem frete;
        nfce.put("icms_base_calculo", "0.0000");
        nfce.put("data_emissao", hoje);
        //nfce.put("cnpj_emitente", "34257575000106");
        nfce.put("cnpj_emitente", dadosEmpresa.retornaCnpj());
        // taxa de Desconto
        //nfce.put("valor_desconto", txtDesconto.getText().replace(",", "."));
        json = new JSONObject(this.nfce);

        HashMap<String, String> itens;

        for (int i = 0; i < listaProdutosNota.size(); i++) {
            itens = new HashMap<>();
            itens.put("numero_item", listaProdutosNota.get(i).getItem());
            itens.put("codigo_produto", listaProdutosNota.get(i).getCodProduto());
            itens.put("valor_desconto", "0.00");
            itens.put("quantidade_comercial", listaProdutosNota.get(i).getQtd());
            itens.put("cfop", "5102"); // Venda de mercadoria adquirida ou recebida de terceiros
            itens.put("icms_situacao_tributaria", "103");
            itens.put("unidade_comercial", "un");
            if (ambiente == 1) {
                itens.put("descricao", listaProdutosNota.get(i).getDescricao()); //Substituir pela descrição do produto no ambiente de produção
            } else {
                itens.put("descricao", "NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL"); //Substituir pela descrição do produto no ambiente de produção
            }
            itens.put("pis_situacao_tributaria", "07");
            itens.put("codigo_ncm", listaProdutosNota.get(i).getCodNcm());
            itens.put("quantidade_tributavel", listaProdutosNota.get(i).getQtd());
            itens.put("unidade_tributavel", "un");
            itens.put("cofins_situacao_tributaria", "07");
            itens.put("valor_unitario_comercial", listaProdutosNota.get(i).getvUnit());
            itens.put("icms_origem", "0");
            itens.put("valor_bruto", listaProdutosNota.get(i).getVlrTotal());
            itens.put("valor_unitario_tributavel", listaProdutosNota.get(i).getvUnit());
            itens.put("valor_frete", "0");

            JSONObject obj = new JSONObject(itens);

            json.accumulate("items", obj);

        }

        formasPagamento.put("forma_pagamento", codFormaPagamento);
        formasPagamento.put("valor_pagamento", valorPedido);
        jsonPagamento = new JSONObject(this.formasPagamento);

        json.append("formas_pagamento", jsonPagamento);

        // Visualiza contúdo do objeto Json Principal
        //System.out.println(json);
        // Prepara Conexao a API
        /*Envia arquivo para Autorização */
        WebResource request = client.resource(url);

        ClientResponse resposta = request.post(ClientResponse.class, json);

        int httpCode = resposta.getStatus();

        String body = resposta.getEntity(String.class);

        /* As três linhas a seguir exibem as informações retornadas pela nossa API. 
        /* Aqui o seu sistema deverá interpretar e lidar com o retorno. */
        System.out.print("HTTP Code: ");
        System.out.print(httpCode);
        System.out.print(body);
        //Grava saída no arquivo
        try {
            FileOutputStream out = new FileOutputStream("c://sysbar/retorno.json");
            PrintStream ps = new PrintStream(out);
            System.setOut(ps);

            //System.out.println(body);
            System.out.println(body);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Autorizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return httpCode;
    }

    // Aplica o desconto no valor dos produtos da na tela
    // Atenção o vlaor do desconto não incide sobre o valor do serviço
    private void aplicaDescontoTela3(double vlrDesconto, double valorPedido) {
        int linhas = tblDetalhePedido.getRowCount();
        double percentualDesconto;
        double valorItem;
        double totalItem;
        double total = 0;
        double qtd;

        percentualDesconto = (vlrDesconto / valorPedido);

        for (int i = 0; i < linhas; i++) {
            valorItem = Double.parseDouble(tblDetalhePedido.getModel().getValueAt(i, 3).toString().replace(",", "."));
            qtd = Double.parseDouble(tblDetalhePedido.getModel().getValueAt(i, 2).toString().replace(",", "."));
            valorItem = Double.parseDouble(fv.Formata(String.valueOf(valorItem - (valorItem * percentualDesconto))).replace(",", "."));
            tblDetalhePedido.setValueAt(String.format("%9.2f", valorItem), i, 3);
            totalItem = valorItem * qtd;
            tblDetalhePedido.setValueAt(String.format("%9.2f", totalItem), i, 4);
            total = total + totalItem;
        }

        double pedidoTela = totalizaPedidoTela();
        //tgeral.setText(String.format("%9.2f", total));
        tgeral.setText(String.format("%9.2f", pedidoTela));
        txtDesconto.setText(String.format("%9.2f", vlrDesconto));

        double vlrServico = Double.parseDouble(percent.getText().replace(",", "."));
        // Teste         
        double vlrDescServico;
               vlrDescServico  = (percentualDesconto * vlrServico);
               vlrDescServico = (vlrServico - vlrDescServico);
               vlrDescServico = Double.parseDouble(String.format("%9.2f", vlrDescServico).replace(",","."));
               //System.out.println("Valor do Serviço com Desconto: " + String.valueOf(vlrDescServico));
               vlrServico = vlrDescServico;
               percent.setText(String.format("%9.2f", vlrServico));
        double soma = pedidoTela + vlrServico;
        double tGeralTela = Double.parseDouble(lblTotal.getText().replace(",", "."));
        tGeralTela = Double.parseDouble(String.format("%9.2f", tGeralTela).replace(",", "."));
        double dif = tGeralTela - soma;
        dif = Double.parseDouble(String.format("%9.2f", dif).replace(",", "."));
        if (dif > 0) {
            tGeralTela = tGeralTela - dif;
            lblTotal.setText(String.format("%9.2f", tGeralTela));
            txtValorPago.setText(fv.Formata(lblTotal.getText()));
        } else if (dif < 0) {
            dif = dif * -1;
            tGeralTela = tGeralTela + dif;
            lblTotal.setText(String.format("%9.2f", tGeralTela));
            txtValorPago.setText(fv.Formata(lblTotal.getText()));    
        }

//        if (tGeralTela > soma){
//            tGeralTela = tGeralTela - dif;
//            tgeral.setText(String.format("%9.2f", tGeralTela));
//        }
//        double diferenca = total - pedidoTela;
//        diferenca = Double.parseDouble(String.format("%9.2f",diferenca).replace(",", "."));
//        Double vlrItemTela = Double.parseDouble(tblDetalhePedido.getValueAt(0, 4).toString().replace(",","."));
//        vlrItemTela = Double.parseDouble(String.format("%9.2f",vlrItemTela).replace(",", "."));
//        vlrItemTela = vlrItemTela+diferenca;
//        tblDetalhePedido.setValueAt(String.format("%9.2f", vlrItemTela), 3, WIDTH);
//        qtd = Integer.parseInt(tblDetalhePedido.getValueAt(0, 2).toString());
//        totalItem = vlrItemTela * qtd;
//        tblDetalhePedido.setValueAt(String.format("%9.2f", totalItem), 4, WIDTH);
//        percentualDesconto = (vlrDesconto / valorPedido);
//
//        for (int i = 0; i < linhas; i++) {
//            valorItem = Double.parseDouble(tblDetalhePedido.getModel().getValueAt(i, 3).toString().replace(",", "."));
//            qtd = Double.parseDouble(tblDetalhePedido.getModel().getValueAt(i, 2).toString().replace(",", "."));
//            valorItem = valorItem - (valorItem * (percentualDesconto));
//            tblDetalhePedido.setValueAt(String.format("%9.2f", valorItem), i, 3);
//            totalItem = valorItem * qtd;           
//            tblDetalhePedido.setValueAt(String.format("%9.2f", totalItem), i, 4);
//            total = total + totalItem;
//        }
//        tgeral.setText(String.format("%9.2f", total));
//        txtDesconto.setText(String.format("%9.2f", vlrDesconto));
    }

    private void telaProcessamento(String msg) {
        tpp = new TelaProcessaPamento();
        tpp.setModal(true);
        tpp.mensagem(msg);
        tpp.setVisible(true);
    }

    private double totalizaPedidoTela() {
        int linhas = tblDetalhePedido.getRowCount();
        double total = 0;

        for (int i = 0; i < linhas; i++) {
            double valor = Double.parseDouble(tblDetalhePedido.getValueAt(i, 4).toString().replace(",", "."));
            total = total + valor;
        }
        total = Double.parseDouble(String.format("%9.2f", total).replace(",", "."));
        return total;
    }

    public void receber(String cpfContribuinte, String cpfNaoFormatado) {
        
        ConexaoInternet ci = new ConexaoInternet();
        // Verifica se Existe conexao com a internet
        // Caso não haja será emitido um cupom não fiscal
        boolean internet = ci.temConexao();
        if (internet || flagFiscal==0) {
            if (lblReceber.isEnabled()) {
                if ("delivery".equals(lblGarcom.getText().toLowerCase()) && "0,00".equals(percent.getText())) {
                    JOptionPane.showMessageDialog(this, "É necessário realizar a entrega do pedido antes do seu fechamento!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    lblReceber.setEnabled(false);
                } else {
                    // Fecha pedido
                    // Calcula valor
                    int nPesoas = Integer.parseInt(jSpinFieldPessoas.getValue().toString());
                    String tGeral = lblTotal.getText().replace(".", "");
                    tGeral = tGeral.replace(",", ".");
                    Double totalGeral = Double.parseDouble(tGeral);
                    Double totalPessoas = totalGeral / nPesoas;
                    // Instancia um produto
                    Pedido p = new Pedido();
                    //p.setTotal(lblTotal.getText().replaceAll(",", "."));
                    p.setTotal(tGeral);
                    String comissao = percent.getText().replace(".", "");
                    comissao = comissao.replace(",", ".");
                    p.setComissao(comissao);
                    String vlrPago = txtValorPago.getText().replace(".", "");
                    vlrPago = vlrPago.replace(",", ".");
                    p.setTotalPago(vlrPago);
                    p.setStatus("1");
                    p.setOperador(lblOperador.getText());
                    p.setId(txtIdPedido.getText());
                    // Data de Fechamento do Pedido
                    Date dataPedido = new Date();
                    Timestamp datapedidoTms = new Timestamp(dataPedido.getTime());
                    p.setData(String.valueOf(datapedidoTms));
                    // Pega forma de pagamento selecionada 
                    if (radioCartao.isSelected()) {
                        p.setFormaPagto("Crédito");
                    } else if (radioDinheiro.isSelected()) {
                        p.setFormaPagto("Dinheiro");
                    } else if (radioDebito.isSelected()) {
                        p.setFormaPagto("Débito");
                    } else if (radioVoucher.isSelected()) {
                        p.setFormaPagto("Voucher");
                    } else {
                        p.setFormaPagto("MISTO");
                    }
                    // Pega id da mesa.

                    p.setCadMesaId(txtIdMEsa.getText());
                    // Calcula e retorna a permanência do cliente no estabelecimento.
                    p.setPermanencia(cp.calculaPermanencia(txtIdPedido.getText()));
                    // Solicita confirmação do usuário
                    int op = JOptionPane.showOptionDialog(this, "Deseja realmente fechar este Pedido?", "Atenção!", JOptionPane.YES_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, opcao, opcao[1]);

                    if (op == 1) {  // Se confirmado fecha o pedido
                        // Exibe tela de processamento
                        if (flagFiscal == 1) {
                            tpp = new TelaProcessaPamento();
                            tpp.setModal(true);
                            tpp.setVisible(true);
                        }
                        // Retorna a forma de pagamento 
                        String formaPagto = detectaFormaDePagamento();
                        /*  Utilize o valor 1 para a flagFiscal para realizar a autorização
                        e a leitura do retorno do SEFAZ*/
                        //------------------ Autoriza NFCe ----------------------------------------------

                        String codPagto; // Código de pagamento Da API
                        /*
                                Valores possíveis:
                                01: Dinheiro.      02: Cheque.           03: Cartão de Crédito. 04: Cartão de Débito.
                                05: Crédito Loja.  10: Vale Alimentação. 11: Vale Refeição.
                                12: Vale Presente. 13: Vale Combustível. 99: Outros
                         */
                        if (flagFiscal == 1) {
                            try {
                                switch (formaPagto) {

                                    case "Dinheiro":
                                        codPagto = "01";
                                        break;
                                    case "Crédito":
                                        codPagto = "03";
                                        break;
                                    case "Débito":
                                        codPagto = "04";
                                        break;
                                    case "Voucher":
                                        codPagto = "11";
                                        break;
                                    default:
                                        codPagto = "99";
                                }
                                if (flagFiscal == 1) {

                                    // Autoriza pedido com apenas 1 item
                                    telaProcessamento("Iniciando autorização....");

                                    if (tblDetalhePedido.getRowCount() == 1) {
                                        telaProcessamento("Solicitando autorização.....");
                                        autorizarNfCe(codPagto, tgeral.getText().replace(",", "."), lblNPedido.getText(),cpfContribuinte);
                                        telaProcessamento("Autorizando.....");
                                    } else {
                                        // autoriza pedido com mais de 1 item                                    
                                        telaProcessamento("Solicitando autorização.....");
                                        int codPgto = autorizarNfCe2(codPagto, tgeral.getText().replace(",", "."), lblNPedido.getText(),cpfContribuinte);
                                        telaProcessamento("Autorizando autorização.....");
                                    }
                                }
                            } catch (JSONException ex) {
                                Logger.getLogger(TelaCaixa.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (flagFiscal == 1) {

                                //------------------ Ler Retorno da Autorização ------------------//
                                try {
                                    nota = cNFCe.lerRetorno("retorno.json");
                                    telaProcessamento("Autorizado:" + nota.getChave_nfe());
                                } catch (org.json.simple.parser.ParseException | IOException ex) {
                                    Logger.getLogger(TelaCaixa.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                //------------------ Gera QRCode ---------------------------------------//
                                try {
                                    // Gera QRcod
                                    if (nota.getQrcode_url() != null) {
                                        utils.generateQRCodeImage(nota.getQrcode_url(), 120, 120, "C:/Sysbar/qr.jpg");
                                    }
                                } catch (WriterException | IOException ex) {
                                    Logger.getLogger(TelaReImpressao.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }

                        if (!"Selecione".equals(formaPagto)) {
                            // Pega a data Atual

                            String dtAtual = utils.formataDataBr(data).replaceAll("/", "");
                            // Pega a hora atual
                            String horaAtual = utils.formataDataHora(data, "h");
                            //Gera Autenticacao
                            String autentica = cp.autentica(func.localizaId(lblGarcom.getText()), comboMesa.getSelectedItem().toString(), txtIdPedido.getText(), dtAtual + "." + horaAtual);
                            p.setAutenticacao(autentica);

                            // Fecha o pedido após o recebimento
                            cp.fechaPedido(p);

                            if ("MISTO".equals(p.getFormaPagto())) {
                                //dinheiro -> dinheiro pago
                                if (cp.gravaPagamentoMisto(p.getId(), dinheiroPago, credito, debito, voucher)) {

                                    jtabedFormaPagto.setSelectedIndex(0);

                                } else {
                                    System.out.println("Erro ao gravar pagamento misto");
                                }
                            }
                            //Se a forma de pagamento for Mista (M) grava no banco a forma de pagamento.

                            //Início do Registro de log
                            l.setFuncionalidade("Recebimento");
                            l.setDescricao("Recebeu R$ " + p.getTotalPago().replace(".", ",") + " Pedido N.->" + txtIdPedido.getText() + " Comissão: R$ " + p.getComissao().replace(".", ","));
                            l.gravaLog(l);

                            // Libera a mesa após o pagamento
                            cm.trocaStatusMesa(comboMesa.getSelectedItem().toString(), "0");
                            //Fecha Tela de processamento
                            if (flagFiscal == 1) {
                                tpp.fechaTela();
                            }
                            //JOptionPane.showMessageDialog(this, "Pedido fechado com sucesso!");
                            // ===============================================================//
                            if (flagFiscal == 1) {
                                telaProcessamento("Preparando impressão do Cumpom Fiscal");
                            }

                            // Registra desconto se o valor for > que 0
                            Funcionario f = new Funcionario();
                            Double desconto;
                            if (!"0,00".equals(txtDesconto.getText())) {

                                f.setId(listAutoDesconto.get(7));
                                String strDesc = txtDesconto.getText().replace(".", "");
                                strDesc = strDesc.replace(",", ".");
                                desconto = Double.parseDouble(strDesc);
                                // Instacia o objeto desconto e adiciona como parâmetro o motivo, o do idPedido e 
                                // o id do Funcionário eque concedeu o desconto
                                DescontoPedido descPedido = new DescontoPedido(desconto, listAutoDesconto.get(8), f, p);
                                //Registra desconto informado
                                caixa.registraDesconto(descPedido);
                            } else {

                                f.setId(func.localizaIdLogin(lblOperador.getText()));
                                //System.out.println("Id do Funcionário:" + f.getId());
                                desconto = 0.0;
                                DescontoPedido descPedido = new DescontoPedido(desconto, "", f, p);
                                caixa.registraDesconto(descPedido);
                            }

                            // Imprime cupom de pagamento
                            HashMap dados = new HashMap();
                            Date dt = new Date();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                            dados.put("data", df.format(dt));
                            dados.put("garcom", lblGarcom.getText());
                            dados.put("titulo", "COMPROVANTE DE PAGAMENTO");
                            String strTx = percent.getText().replace(".", "");
                            strTx = strTx.replace(",", ".");
                            dados.put("tx", Double.parseDouble(strTx));
                            dados.put("id_pedido", p.getId());
                            //System.out.println(p.getId());
                            dados.put("npessoas", nPesoas); 
                            dados.put("total_pessoas", totalPessoas); 
                            //DadosEmpresa dadosEmpresa = de.selecionaDados();
                            dados.put("mesa", comboMesa.getSelectedItem().toString());
                            dados.put("nome_empresa", dadosEmpresa.getNome_empresa());
                            dados.put("end", dadosEmpresa.getEndereco() + "," + dadosEmpresa.getNumero() + " - " + dadosEmpresa.getBairro());
                            dados.put("end2", dadosEmpresa.getCep() + " " + dadosEmpresa.getCidade() + "-" + dadosEmpresa.getUf() + " " + dadosEmpresa.getTelefone());
                            dados.put("cnpj", dadosEmpresa.getCnpj());
                            dados.put("desc", Double.parseDouble(txtDesconto.getText().replaceAll(",", ".")));
                            dados.put("desc", Double.parseDouble(txtDesconto.getText().replaceAll(",", ".")));
                            dados.put("forma_pag", p.getFormaPagto());
                            // Parametros Fiscais
                            dados.put("chave_nfe", nota.getChave_nfe());
                            dados.put("url_consulta_nf", nota.getUrl_consulta_nf());
                            dados.put("serie", nota.getSerie());
                            dados.put("numero", nota.getNumero());
                            dados.put("cpfContribuinte", cpfNaoFormatado);

                            // Adiciona os valores pagos ao cupom. Para pagament na forma Mista
                            if ("MISTO".equals(p.getFormaPagto())) {

                                dados.put("dinheiro", dinheiroPago);
                                dados.put("credito", credito);
                                dados.put("debito", debito);
                                dados.put("voucher", voucher);
                            } else {
                                // Zera variáveis do pagamento Misto
                                dados.put("dinheiro", 0.0);
                                dados.put("credito", 0.0);
                                dados.put("debito", 0.0);
                                dados.put("voucher", 0.0);
                            }

                            try {
                                // Verifica o método de impressão 0 -> Impressção em tela 1 - Impressão direta
                                if (dadosEmpresa.getImprimir_na_tela() == 0) {
                                    //rpu.imprimeRelatorioTela("cupom2.jasper", dados);
                                    rpu.imprimeRelatorioTela("cupom2_7.jasper", dados, "Comprovante de Pagamento");
                                   
                                } else {
                                    // rpu.impressaoDireta("cupom2.jasper", dados);
                                    rpu.impressaoDireta("cupom2_7.jasper", dados);
                                }

                                lblTotal.setText("0,00");
                                tgeral.setText("0,00");
                                percent.setText("0,00");

                            } catch (JRException e) {
                                //System.out.println("br.com.bar.view.TelaCaixa.btnImprimirMouseClicked()");
                                lblTotal.setText("0,00");
                                tgeral.setText("0,00");
                                percent.setText("0,00");
                            }
                            // Fim da impressão de cupom
                            checkReimpressao.setEnabled(true);
                            cp.limpaTabela(tblDetalhePedido);
                            limpaForm();
                            lblTotal.setText("0,00");
                            tgeral.setText("0,00");
                            percent.setText("0,00");
                            txtValorPago.setText("0,00");
                            txtDesconto.setText("0,00");
                            lblReceber.setEnabled(false);
                            txtTroco.setText("0,00");

                            buttonGroup2.clearSelection();

                            lblNPedido.setText(null);
                            lblEntradas.setText(String.format("%9.2f", caixa.totalizaEntradas()));
                            lblGarcom.setText(null);
                            jpanelSubTotal.setEnabled(false);
                            jpanelTotalGeral.setEnabled(false);

                            atualizaCaixa();
                            bloqueiaControlePagamento();
                            // Desabilita ComboBox caso não exista mesa a serem listadas.
                            if (comboMesa.getItemCount() > 1) {
                                btnListar.setEnabled(false);
                            }
                            try {

                                caixa.listaMesaOcupada(comboMesa);

                            } catch (NullPointerException e) {

                            }

                            // Zera textFilds pagameto Misto
                            txtMistoCredito.setText("0,00");
                            txtMistoDebito.setText("0,00");
                            txtMistoVoucher.setText("0,00");
                            txtMistoDinheiro.setText("0,00");

                        } else {
                            JOptionPane.showMessageDialog(this, "Selecione uma forma de pagameto!");
                        }
                        jSpinFieldPessoas.setValue(1);
                        JOptionPane.showMessageDialog(this, "Pedido fechado com sucesso!");
                    }
                    try {
                        // Fecha a conexão após impressão dos cupons.
                        conexao.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaCaixa.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Zera as variáveis
                    dinheiro = 0;
                    credito = 0;
                    debito = 0;
                    voucher = 0;
                } // fim da verificação delivery

            }

            // Atualiza o status do entregador 
            if (entregador.getNome() != null) {
                // Muda o status atual do entregador para 0 (zero) = Livre
                entregador.setStatus(0);
                if (ce.atualizaStatusEntregador(entregador)) {
                    System.out.println("Status do Entregador atualizado com Sucesso...");
                    // Remove o pedido da tabela Delivery para tbHistoricoDelivery
                    if (dl.movePedidoParaHistoricoDelivery(idDelivery)) {
                        System.out.println("Pedido movido com sucesso!");
                        if (dl.excluiPedidoDoDelivery(idDelivery)) {
                            System.out.println("Pedido excluído da tabela delivery com sucesso!");
                        }
                    } else {
                        System.out.println("Não foi possível mover o pedido!");
                    }
                } else {
                    System.out.println("Erro ao atualizar o status do entregador");

                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sem conexao com a internet!\nImprima uma Parcial de Consumo ou aguarde até que a conexao seja restabelecida\npara emissao do Cupom Fiscal (NFC-e).", "Atenção", JOptionPane.ERROR_MESSAGE);
            btnImprimir.setForeground(Color.red);
        }
        
    }
}
