/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.Funcionario;
import br.com.bar.model.Pedido;
import br.com.bar.model.Produto;
import br.com.bar.model.ProdutoPedido;
import br.com.bar.model.TableModelDetalhePedido;
import br.com.bar.model.TableModelMesaPedido;
import br.com.bar.model.TableModelNumeroMesas;
import br.com.bar.model.TableModelPedidosAbertos;
import br.com.bar.model.TableModelProdutoEstoque;
import br.com.bar.util.FormataValor;
import br.com.bar.util.LeitorDeTeclas;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerCliente;
import br.com.br.controler.ControlerCozinha;
import br.com.br.controler.ControlerDelivery;
import br.com.br.controler.ControlerEstoque;
import br.com.br.controler.ControlerFuncionario;
import br.com.br.controler.ControlerGrupo;
import br.com.br.controler.ControlerMesa;
import br.com.br.controler.ControlerPedido;
import br.com.br.controler.ControlerProduto;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaPedido2 extends javax.swing.JFrame {

    ControlerFuncionario cFunc = new ControlerFuncionario();
    ControlerMesa cm = new ControlerMesa();
    ControlerPedido cp = new ControlerPedido();
    ControlerProduto cproduto = new ControlerProduto();
    ControlerEstoque est = new ControlerEstoque();
    ControlerGrupo cg = new ControlerGrupo();
    ControlerCozinha cc = new ControlerCozinha();
    ControlerDelivery cd = new ControlerDelivery();

    TableModelPedidosAbertos modelPedidos = new TableModelPedidosAbertos();
    TableModelDetalhePedido modelDetPedido = new TableModelDetalhePedido();
    TableModelProdutoEstoque modelProduroEstoque = new TableModelProdutoEstoque();
    TableModelMesaPedido modelMesa = new TableModelMesaPedido();
    TableModelNumeroMesas modelNmesas = new TableModelNumeroMesas();
    // Telas
    TelaStatusCozinha status;
    TelaGerenciarPedido gp;
    TelaBloqueio tb;
    TelaAlteraSenha2 telap2;
    TelaDelivery td;

    JFrame tlGerenciarPedido;
    Util u = new Util();
    Funcionario funcLogado;

    TimerTask task;
    // Limite em segundo para bloquear a tela
    int limite = 120;
    // Contador - Segundos
    int s = 0;
    /**
     * Dados de Renvido do produto cozinha Estas variáveis estão sendo
     * utilizadas no botão [REEVIO COZINHA]
     */
    String descricao;
    String qtd;
    String idProduto;
    // Recebe dados da tela de caixa
    TelaCaixa tc;
    /**
     * Creates new form TelaPedido2
     */
    ArrayList<String> obsevacaoPrato; // Recebe a observação do prato

    String cliente;
    String tipoPedido;

    public TelaPedido2() {

        initComponents();
        lbllogo.setIcon(u.carregaLogo());
        cFunc.carregaComboFuncionario(comboGarcom, "Garçom");

        Calendar c = Calendar.getInstance();
        lblData.setText(u.formataDataBr(c.getTime()));
        bloqueiaCampos();
        // Oculta caixa de pesquisa
        lblPesquisa.setVisible(false);
        txtPesquisa.setVisible(false);
        txtIdGarcom.setVisible(false);
        txtIdMesa.setVisible(false);
        txtNumeroPedido.setVisible(false);
        txtNumeroMesa.setVisible(false);
        txtDescricao.setEnabled(false);
        txtValorUnit.setEnabled(false);
        txtValorTotal.setEnabled(false);
        lblLupa.setVisible(false);
        lblStatusCozinha.setEnabled(false);
        lbl_status_cozinha.setEnabled(false);
        btnAbrirPedido.setEnabled(false);
        lblCargo.setVisible(true);
        lblSegundos.setVisible(false);
        lblReenvioCozinha.setEnabled(false);
        lblBtnReenvioCozinha.setEnabled(false);
        modelNmesas.redimensionaColunas(tblNumeroMesa);
        lblTextocozinha.setVisible(false);
        lblCozinha.setVisible(false);
        lblBtnAnexar.setEnabled(false);
        labelAnexar.setEnabled(false);
        //Torna a tela Selecionavel 'Necessário para que o evento de bloqueio ocorra'
        this.setFocusable(true);
        // Adiciona Listner para bloquear tela
        addKeyListener(new LeitorDeTeclas());
        //cronometro();
    }

    public void recebeOperador(String operador, String perfil) {
        lblOperador.setText(operador);
        lblCargo.setText(perfil);
        lblCargo.setVisible(false); // Setar false após teste
        //lblOperador.setVisible(true);// Excluir após teste
        modelPedidos.redimensionaColunas(tblPedidosAbertos);

        if ("Gerente".equals(lblCargo.getText())) {

            lblGerenciarPedido.setVisible(true);
            textoLblPedido.setVisible(true);
            lblAlterarSenha.setVisible(false);
            lblTextocozinha.setVisible(true);
            lblCozinha.setVisible(true);
        } else {

            lblGerenciarPedido.setVisible(false);
            textoLblPedido.setVisible(false);
        }

        //Recupera o id do funcionario logado.
        funcLogado = cFunc.localizaFuncionario(cFunc.localizaIdLogin(operador));

    }
    // Recebe dados do funcionário referente ao pedido listado, este método é
    // Utilizado no momento em que o caixa vai adicionar um ítem ao pedido no 
    // Momento do fehamento da conta.

    public void recebePedido(TelaCaixa tela, String funcionario, String idGarcom) {
        this.tc = tela;

        comboGarcom.setEnabled(false);
        comboGarcom.setSelectedItem(funcionario);
        tblPedidosAbertos.setModel(DbUtils.resultSetToTableModel(cp.listaPedidos(idGarcom)));
        modelPedidos.redimensionaColunas(tblPedidosAbertos);
        // Oculta Botões
        lblAlterarSenha.setVisible(false);
        lblCadeado.setVisible(false);
        lblBloquearTela.setVisible(false);
        lblGerenciarPedido.setVisible(false);
        textoLblPedido.setVisible(false);
        lblStatusCozinha.setVisible(false);
        //jLabel1.setVisible(false);
        lblReenvioCozinha.setVisible(false);
        lblBtnReenvioCozinha.setVisible(false);
        jLabel6.setVisible(false);
        jLabel9.setVisible(false);
        lbl_status_cozinha.setVisible(false);
        btnListar.setEnabled(false);
        //task.cancel();
    }

    public void atuDetalheDoPedido(String nMesa, String nPedido) {

        // Lista os produtos do pedido selecionado
        tblDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(nMesa, nPedido)));
        modelDetPedido.redimensionaColunas(tblDetalhePedido);
    }

    public void atualizaPedidos() {
        // Atualiza pedido após. Este método é executado após remoção do pedido 
        // Na tela de Gerenciamento de Peidido.
        tblPedidosAbertos.setModel(DbUtils.resultSetToTableModel(cp.listaPedidos(txtIdGarcom.getText())));
        modelPedidos.redimensionaColunas(tblPedidosAbertos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtIdGarcom = new javax.swing.JTextField();
        txtIdMesa = new javax.swing.JTextField();
        txtNumeroPedido = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lbllogo = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblSegundos = new javax.swing.JLabel();
        lblData2 = new javax.swing.JLabel();
        txtNumeroMesa = new javax.swing.JTextField();
        panelFechar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblStatusCozinha = new javax.swing.JLabel();
        textoLblPedido = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblGerenciarPedido = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblReenvioCozinha = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblQtd = new javax.swing.JLabel();
        txtQtd = new javax.swing.JTextField();
        txtCodigoProduto = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtValorUnit = new javax.swing.JTextField();
        lblCodigo1 = new javax.swing.JLabel();
        lblQtd1 = new javax.swing.JLabel();
        lblQtd2 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        lblPesquisa = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jTabbedPanePedido = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPedidosAbertos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalhePedido = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblListaProduto = new javax.swing.JTable();
        lblLupa = new javax.swing.JLabel();
        lblMensagem = new javax.swing.JLabel();
        lblMsgRetorno = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        comboGarcom = new javax.swing.JComboBox<>();
        btnListar = new javax.swing.JButton();
        panelAbrirPedido = new javax.swing.JPanel();
        btnAbrirPedido = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNumeroMesa = new javax.swing.JTable();
        lblCadeado = new javax.swing.JLabel();
        lblBloquearTela = new javax.swing.JLabel();
        lblAlterarSenha = new javax.swing.JLabel();
        lblBtnReenvioCozinha = new javax.swing.JLabel();
        lbl_status_cozinha = new javax.swing.JLabel();
        lblCozinha = new javax.swing.JLabel();
        lblTextocozinha = new javax.swing.JLabel();
        lblBtnAnexar = new javax.swing.JLabel();
        labelAnexar = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(null);

        txtIdGarcom.setText("idGarcom");
        getContentPane().add(txtIdGarcom);
        txtIdGarcom.setBounds(310, 10, 50, 30);

        txtIdMesa.setText("idMesa");
        getContentPane().add(txtIdMesa);
        txtIdMesa.setBounds(370, 50, 50, 30);

        txtNumeroPedido.setText("nPedido");
        getContentPane().add(txtNumeroPedido);
        txtNumeroPedido.setBounds(370, 10, 50, 30);

        jPanel4.setBackground(new java.awt.Color(38, 53, 61));
        jPanel4.setForeground(new java.awt.Color(52, 73, 94));

        lbllogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbllogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/logo.png"))); // NOI18N

        lblData.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Calendar32x32.png"))); // NOI18N
        lblData.setText("data");

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblOperador.setForeground(new java.awt.Color(255, 255, 255));
        lblOperador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario_branco.png"))); // NOI18N
        lblOperador.setText("usuário");

        lblSegundos.setForeground(new java.awt.Color(255, 255, 255));
        lblSegundos.setText("jLabel5");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(lblSegundos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbllogo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lbllogo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(lblSegundos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 250, 700);

        lblData2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        lblData2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblData2.setText("Pedidos");
        getContentPane().add(lblData2);
        lblData2.setBounds(260, 20, 980, 80);

        txtNumeroMesa.setText("nMesa");
        getContentPane().add(txtNumeroMesa);
        txtNumeroMesa.setBounds(310, 50, 50, 30);

        panelFechar.setBackground(new java.awt.Color(52, 73, 94));
        panelFechar.setForeground(new java.awt.Color(52, 73, 94));
        panelFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFecharMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N

        javax.swing.GroupLayout panelFecharLayout = new javax.swing.GroupLayout(panelFechar);
        panelFechar.setLayout(panelFecharLayout);
        panelFecharLayout.setHorizontalGroup(
            panelFecharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFecharLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelFecharLayout.setVerticalGroup(
            panelFecharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFecharLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(panelFechar);
        panelFechar.setBounds(1210, 0, 40, 40);

        lblStatusCozinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatusCozinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnCozinha.png"))); // NOI18N
        lblStatusCozinha.setToolTipText("");
        lblStatusCozinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStatusCozinhaMouseClicked(evt);
            }
        });
        getContentPane().add(lblStatusCozinha);
        lblStatusCozinha.setBounds(800, 610, 70, 48);

        textoLblPedido.setFont(new java.awt.Font("Yu Gothic Light", 0, 14)); // NOI18N
        textoLblPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoLblPedido.setText("Gestão de Pedidos");
        getContentPane().add(textoLblPedido);
        textoLblPedido.setBounds(490, 660, 150, 23);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Sair");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(1180, 660, 70, 20);

        lblGerenciarPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGerenciarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/relatorios32x32.png"))); // NOI18N
        lblGerenciarPedido.setToolTipText("");
        lblGerenciarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGerenciarPedidoMouseClicked(evt);
            }
        });
        getContentPane().add(lblGerenciarPedido);
        lblGerenciarPedido.setBounds(530, 610, 80, 50);

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("usuário");
        getContentPane().add(lblCargo);
        lblCargo.setBounds(430, 10, 120, 32);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/power.png"))); // NOI18N
        jLabel9.setToolTipText("Sair");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel9);
        jLabel9.setBounds(1180, 610, 70, 50);

        lblReenvioCozinha.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblReenvioCozinha.setText("Reenvio Cozinha");
        getContentPane().add(lblReenvioCozinha);
        lblReenvioCozinha.setBounds(900, 660, 130, 20);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Produto")));

        lblCodigo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblCodigo.setText("Código");

        lblQtd.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblQtd.setText("Qtd");

        txtQtd.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtQtd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQtd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQtdFocusLost(evt);
            }
        });
        txtQtd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtQtdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtQtdMouseEntered(evt);
            }
        });
        txtQtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtdKeyReleased(evt);
            }
        });

        txtCodigoProduto.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtCodigoProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodigoProdutoMouseClicked(evt);
            }
        });
        txtCodigoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProdutoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProdutoKeyReleased(evt);
            }
        });

        txtDescricao.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N

        txtValorUnit.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtValorUnit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorUnit.setText("0,00");

        lblCodigo1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblCodigo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo1.setText("Descrição");

        lblQtd1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblQtd1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQtd1.setText("V. Unit R$");

        lblQtd2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblQtd2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblQtd2.setText("Total R$");

        txtValorTotal.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        txtValorTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorTotal.setText("0,00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblQtd)
                    .addComponent(lblCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQtd1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescricao)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtValorUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblQtd2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodigo1))
                        .addGap(50, 50, 50))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCodigo))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblQtd)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblQtd1)
                                .addComponent(txtValorUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblQtd2)
                                .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel1);
        jPanel1.setBounds(10, 10, 690, 130);

        lblPesquisa.setText("Pesquisar Produto");
        jPanel6.add(lblPesquisa);
        lblPesquisa.setBounds(10, 140, 120, 14);

        txtPesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPesquisaFocusGained(evt);
            }
        });
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });
        jPanel6.add(txtPesquisa);
        txtPesquisa.setBounds(10, 160, 220, 30);

        jTabbedPanePedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPanePedidoMouseClicked(evt);
            }
        });

        tblPedidosAbertos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MESA", "PEDIDO", "DATA", "STATUS", "GARÇOM"
            }
        ));
        tblPedidosAbertos.setRowHeight(25);
        tblPedidosAbertos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosAbertosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPedidosAbertos);

        jTabbedPanePedido.addTab("Pedidos Abertos", jScrollPane2);

        tblDetalhePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDetalhePedido.setRowHeight(25);
        tblDetalhePedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalhePedidoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDetalhePedido);

        jTabbedPanePedido.addTab("Detalhe Pedido", jScrollPane3);

        tblListaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ));
        tblListaProduto.setRowHeight(25);
        tblListaProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblListaProdutoFocusLost(evt);
            }
        });
        tblListaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaProdutoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblListaProduto);

        jTabbedPanePedido.addTab("Lista de Produtos", jScrollPane6);

        jPanel6.add(jTabbedPanePedido);
        jTabbedPanePedido.setBounds(10, 200, 690, 280);

        lblLupa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        jPanel6.add(lblLupa);
        lblLupa.setBounds(240, 160, 40, 30);

        lblMensagem.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        lblMensagem.setForeground(new java.awt.Color(153, 153, 153));
        lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(lblMensagem);
        lblMensagem.setBounds(270, 140, 430, 20);

        lblMsgRetorno.setForeground(new java.awt.Color(0, 0, 255));
        lblMsgRetorno.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel6.add(lblMsgRetorno);
        lblMsgRetorno.setBounds(280, 160, 420, 20);

        lblCliente.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(153, 153, 153));
        lblCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel6.add(lblCliente);
        lblCliente.setBounds(280, 180, 420, 20);

        getContentPane().add(jPanel6);
        jPanel6.setBounds(530, 110, 710, 490);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Garçom")));

        comboGarcom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGarcomActionPerformed(evt);
            }
        });

        btnListar.setBackground(new java.awt.Color(204, 204, 204));
        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/mesa32x32.png"))); // NOI18N
        btnListar.setText("Listar Mesas / Pedidos");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboGarcom, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(comboGarcom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAbrirPedido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));
        panelAbrirPedido.setLayout(null);

        btnAbrirPedido.setBackground(new java.awt.Color(204, 204, 204));
        btnAbrirPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Pedido.png"))); // NOI18N
        btnAbrirPedido.setText("Abrir Pedido");
        btnAbrirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirPedidoActionPerformed(evt);
            }
        });
        panelAbrirPedido.add(btnAbrirPedido);
        btnAbrirPedido.setBounds(20, 260, 187, 50);

        tblNumeroMesa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblNumeroMesa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Mesas Disponíveis"
            }
        ));
        tblNumeroMesa.setRowHeight(25);
        tblNumeroMesa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNumeroMesaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNumeroMesa);

        panelAbrirPedido.add(jScrollPane1);
        jScrollPane1.setBounds(20, 13, 190, 240);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAbrirPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAbrirPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel7);
        jPanel7.setBounds(260, 110, 260, 490);

        lblCadeado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cadeado.png"))); // NOI18N
        lblCadeado.setToolTipText("");
        lblCadeado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCadeadoMouseClicked(evt);
            }
        });
        getContentPane().add(lblCadeado);
        lblCadeado.setBounds(380, 610, 60, 50);

        lblBloquearTela.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblBloquearTela.setText("Bloquear Tela (F8)");
        getContentPane().add(lblBloquearTela);
        lblBloquearTela.setBounds(360, 660, 120, 20);

        lblAlterarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/chave48x48.png"))); // NOI18N
        lblAlterarSenha.setToolTipText("Alterar Senha");
        lblAlterarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlterarSenhaMouseClicked(evt);
            }
        });
        getContentPane().add(lblAlterarSenha);
        lblAlterarSenha.setBounds(300, 610, 90, 50);

        lblBtnReenvioCozinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBtnReenvioCozinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/enviar32x32_2.png"))); // NOI18N
        lblBtnReenvioCozinha.setToolTipText("");
        lblBtnReenvioCozinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBtnReenvioCozinhaMouseClicked(evt);
            }
        });
        getContentPane().add(lblBtnReenvioCozinha);
        lblBtnReenvioCozinha.setBounds(910, 610, 70, 50);

        lbl_status_cozinha.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lbl_status_cozinha.setText("Status Cozinha");
        getContentPane().add(lbl_status_cozinha);
        lbl_status_cozinha.setBounds(790, 660, 90, 20);

        lblCozinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCozinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cozinha32x32.png"))); // NOI18N
        lblCozinha.setToolTipText("");
        lblCozinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCozinhaMouseClicked(evt);
            }
        });
        getContentPane().add(lblCozinha);
        lblCozinha.setBounds(710, 610, 70, 50);

        lblTextocozinha.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblTextocozinha.setText("Cozinha");
        lblTextocozinha.setToolTipText("");
        getContentPane().add(lblTextocozinha);
        lblTextocozinha.setBounds(720, 660, 80, 20);

        lblBtnAnexar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBtnAnexar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/anexar.png"))); // NOI18N
        lblBtnAnexar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBtnAnexarMouseClicked(evt);
            }
        });
        getContentPane().add(lblBtnAnexar);
        lblBtnAnexar.setBounds(1040, 610, 70, 50);

        labelAnexar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        labelAnexar.setText("Anexar ao Delivery");
        getContentPane().add(labelAnexar);
        labelAnexar.setBounds(1020, 660, 130, 20);

        setSize(new java.awt.Dimension(1251, 693));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboGarcomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGarcomActionPerformed

        if ("Selecione...".equals(comboGarcom.getSelectedItem().toString())) {
            btnListar.setEnabled(false);
            btnAbrirPedido.setEnabled(false);
            lblStatusCozinha.setEnabled(false);
            lbl_status_cozinha.setEnabled(false);
        } else {
            btnListar.setEnabled(true);
            lblStatusCozinha.setEnabled(true);
            lbl_status_cozinha.setEnabled(true);
        }
        limpaform();
        bloqueiaCampos();
        //Limpa as tabelas [PEDIDOS, MESAS,DETALHE PEDIDO,LISTA DE PRODUTOS]
        tblPedidosAbertos.setModel(modelPedidos);
        modelPedidos.redimensionaColunas(tblPedidosAbertos);
        tblNumeroMesa.setModel(modelMesa);
        modelMesa.redimensionaColunas(tblNumeroMesa);
        tblDetalhePedido.setModel(modelDetPedido);
        modelDetPedido.redimensionaColunas(tblDetalhePedido);
        tblListaProduto.setModel(modelProduroEstoque);
        modelProduroEstoque.redimensionaColunas(tblListaProduto);
        // Seta valor do capo número mesa parao estado inicial
        // Este valor é necessário na checagem do comportamento conforme aba seleciona.
        txtNumeroPedido.setText("nPedido");
        btnAbrirPedido.setEnabled(false);
    }//GEN-LAST:event_comboGarcomActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // Lista todas as mesas livres do funcionário selecionado
        String nomeFunc = comboGarcom.getSelectedItem().toString();
        String idFunc = cFunc.localizaId(nomeFunc);
        txtIdGarcom.setText(cFunc.localizaId(comboGarcom.getSelectedItem().toString()));
        tblNumeroMesa.setModel(DbUtils.resultSetToTableModel(cm.listaMesaLivre(idFunc)));
        modelNmesas.redimensionaColunas(tblNumeroMesa);
        //modelMesa.redimensionaColunas(tblNumeroMesa);
        tblPedidosAbertos.setModel(DbUtils.resultSetToTableModel(cp.listaPedidos(txtIdGarcom.getText())));
        modelPedidos.redimensionaColunas(tblPedidosAbertos);

        btnAbrirPedido.setEnabled(false);
        // Vai para a primeira guia
        jTabbedPanePedido.setSelectedIndex(0);
        bloqueiaCampos();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnAbrirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirPedidoActionPerformed
        // Abre pedido
        if (cp.temPedido(txtIdMesa.getText())) {
            JOptionPane.showMessageDialog(null, "Esta mesa já possui um pedido, escolha outra!");
        } else {
            Pedido p = new Pedido();
            p.setCadMesaId(txtIdMesa.getText());
            //String dataAtual = cp.myDataAtual();
            Date data = new Date();
            Timestamp dtAtualTms = new Timestamp(data.getTime());
            //p.setData(dataAtual);
            p.setData(String.valueOf(dtAtualTms));
            p.setStatus("0"); // Pedido Aberto
            p.setIdFuncionario(txtIdGarcom.getText());
            //p.setId_pedido(txtNumeroPedido.getText());

            if (cp.geraPedido(p)) {
                int linha = tblNumeroMesa.getSelectedRow();
                String numero_mesa = tblNumeroMesa.getModel().getValueAt(linha, 0).toString();
                tblPedidosAbertos.setModel(DbUtils.resultSetToTableModel(cp.listaPedidos(txtIdGarcom.getText())));
                modelPedidos.redimensionaColunas(tblPedidosAbertos);
                cm.trocaStatusMesa(numero_mesa, "1");
                tblNumeroMesa.setModel(DbUtils.resultSetToTableModel(cm.listaMesaLivre(txtIdGarcom.getText())));
                //modelMesa.redimensionaColunas(tblNumeroMesa);
                modelNmesas.redimensionaColunas(tblNumeroMesa);
                txtNumeroMesa.setText(null);
                lblMsgRetorno.setText("*Pedido gerado com sucesso!");
                btnAbrirPedido.setEnabled(false);
                //Log
                String idPedidoGErado = cp.idUltimoPedido(txtIdGarcom.getText());
                Log l = new Log();
                l.setUsuario(lblOperador.getText());
                l.setFuncionalidade("Pedido");
                String idFuncLogado = funcLogado.getId();

                if (idFuncLogado.equals(txtIdGarcom.getText())) {
                    l.setDescricao("Abriu o pedido -> " + idPedidoGErado);
                } else {
                    Funcionario funcCombo = cFunc.localizaFuncionario(cFunc.localizaId(comboGarcom.getSelectedItem().toString()));
                    l.setUsuario(funcCombo.getLogin());
                    l.setDescricao("Abriu o pedido -> " + idPedidoGErado + " [USUÁRIO LOGADO: " + funcLogado.getLogin() + " ]");
                }

                l.gravaLog(l);

            }

            jTabbedPanePedido.setSelectedIndex(0);

        }
    }//GEN-LAST:event_btnAbrirPedidoActionPerformed

    private void tblNumeroMesaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNumeroMesaMouseClicked
        try {

            // Seleciona uma mesa na tabela
            int linha = tblNumeroMesa.getSelectedRow();
            txtNumeroMesa.setText(tblNumeroMesa.getModel().getValueAt(linha, 0).toString());
            txtIdMesa.setText(cm.localizaIdMesa(tblNumeroMesa.getModel().getValueAt(linha, 0).toString()));
            txtIdGarcom.setText(cFunc.localizaId(comboGarcom.getSelectedItem().toString()));
            btnAbrirPedido.setEnabled(true);
        } catch (Exception e) {
            System.out.println("br.com.bar.view.TelaPedido2.tblNumeroMesaMouseClicked()" + e);
            btnAbrirPedido.setEnabled(false);
        }

    }//GEN-LAST:event_tblNumeroMesaMouseClicked

    private void tblPedidosAbertosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosAbertosMouseClicked
        // Selciona um pedido
        int linha = tblPedidosAbertos.getSelectedRow();
        //Captura exceção gerada no momento do click na tabela pedidos abertos quando
        // a mesma está vazia.
        lblMsgRetorno.setText(null);
        //Zera contado do cronômetro.

        try {

            String numeroMesa = tblPedidosAbertos.getModel().getValueAt(linha, 0).toString();
            txtNumeroMesa.setText(numeroMesa);
            String numPedido = tblPedidosAbertos.getModel().getValueAt(linha, 1).toString();
            // Lista os produtos do pedido selecionado
            tblDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(numeroMesa, numPedido)));
            modelDetPedido.redimensionaColunas(tblDetalhePedido);

            txtNumeroPedido.setText(numPedido);
            txtIdGarcom.setText(cFunc.localizaId(tblPedidosAbertos.getModel().getValueAt(linha, 4).toString()));
            txtIdMesa.setText(cm.localizaIdMesa(numeroMesa));
            txtQtd.setEnabled(false);
            txtCodigoProduto.requestFocus();

            // comboGarcom.setSelectedItem(tblPedidosAbertos.getModel().getValueAt(linha, 4).toString());
            jTabbedPanePedido.setSelectedIndex(1);
            txtCodigoProduto.setEnabled(true);
            // Bloqueia gerenciar pedido caso o ususário logado não seja gerente.
            if ("Gerente".equals(lblCargo.getText())) {
                lblGerenciarPedido.setEnabled(true);
            }

            if (!"".equals(lblCliente.getText()) && !"nPedido".equals(txtNumeroPedido.getText())) {
                labelAnexar.setEnabled(true);
                lblBtnAnexar.setEnabled(true);
            }

        } catch (NullPointerException e) {
            System.out.println("br.com.bar.view.TelaPedido2.tblPedidosAbertosMouseClicked()" + e);
        }

    }//GEN-LAST:event_tblPedidosAbertosMouseClicked

    private void jTabbedPanePedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPanePedidoMouseClicked

        s = 0;
        if (!"nPedido".equals(txtNumeroPedido.getText())) {

            // Exibe caixa de pesquisa e lista todos os produtos disponível no estoque
            int index = jTabbedPanePedido.getSelectedIndex();
            if ("Pedidos Abertos".equals(jTabbedPanePedido.getTitleAt(index))) {
                bloqueiaCampos();
                lblPesquisa.setVisible(false);
                txtPesquisa.setVisible(false);
                limpaform();
                // Desabilita botões Enviar para a Cozinha e Status Cozinha após clique 
                // em outra aba.

            } else if ("Detalhe Pedido".equals(jTabbedPanePedido.getTitleAt(index))) {
                txtQtd.setEnabled(false);
                txtCodigoProduto.setEnabled(true);
                lblPesquisa.setVisible(false);
                txtPesquisa.setVisible(false);
                // Desabilita botões Enviar para a Cozinha e Status Cozinha após clique 
                // em outra aba.

                tblDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(txtNumeroMesa.getText(), txtNumeroPedido.getText())));
                modelDetPedido.redimensionaColunas(tblDetalhePedido);
                limpaform();
            } else {
                lblPesquisa.setVisible(true);
                txtPesquisa.setVisible(true);
                lblLupa.setEnabled(true);
                txtValorTotal.setText("0,00");
                txtCodigoProduto.setEnabled(false);
                txtQtd.setEnabled(false);
                // Desabilita botões Enviar para a Cozinha e Status Cozinha após clique 
                // em outra aba.

                tblListaProduto.setModel(DbUtils.resultSetToTableModel(cproduto.listaProdutoDisponivel()));
                modelProduroEstoque.redimensionaColunas(tblListaProduto);
            }
        } else {
            bloqueiaCampos();
            int index = jTabbedPanePedido.getSelectedIndex();
            if ("Lista de Produtos".equals(jTabbedPanePedido.getTitleAt(index))) {
                lblPesquisa.setVisible(true);
                txtPesquisa.setVisible(true);
                txtPesquisa.setText(null);
                txtQtd.setText(null);
            } else {
                lblPesquisa.setVisible(false);
                txtPesquisa.setVisible(false);
                limpaform();
            }

        }


    }//GEN-LAST:event_jTabbedPanePedidoMouseClicked

    private void txtCodigoProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigoProdutoMouseClicked

    }//GEN-LAST:event_txtCodigoProdutoMouseClicked

    private void txtCodigoProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdutoKeyPressed
        // Vai para a caixa quantidade 
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //txtIdMesa.setText(cm.localizaIdMesa(comboNumeroMEsa.getSelectedItem().toString()));
            if (txtIdMesa.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Selecione um pedido!");
            } else {

                Produto produto = new Produto();
                produto.setId(txtCodigoProduto.getText());

                try {

                    Produto pLocalizado = cproduto.localizaProduto(produto);
                    txtDescricao.setText(pLocalizado.getNome());
                    txtValorUnit.setText(String.format("%7.2f", Double.parseDouble(pLocalizado.getValor())));
                    txtQtd.setEnabled(true);
                    txtQtd.requestFocus();

                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Produto não localizado!");
                }
            }

        }
    }//GEN-LAST:event_txtCodigoProdutoKeyPressed

    private void txtQtdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQtdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdMouseClicked
    // Calcula o total da venda 
    private void txtQtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdKeyPressed
        // Verifica se a tecla pressionada é a tecla enter

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("nPedido".equals(txtNumeroPedido.getText())) {
                JOptionPane.showMessageDialog(null, "Selecione um pedido para continuar!", "Nenhum pedido selecionado!", JOptionPane.ERROR_MESSAGE);
            } else {

                adicionaItemNoPedido();
                tblDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(txtNumeroMesa.getText(), txtNumeroPedido.getText())));
                modelDetPedido.redimensionaColunas(tblDetalhePedido);
                txtQtd.requestFocus();
                txtPesquisa.setText(null);

                if ("Lista de Produtos".equals(jTabbedPanePedido.getTitleAt(2))) {
                    txtCodigoProduto.setEnabled(true);
                }

            }

        }
    }//GEN-LAST:event_txtQtdKeyPressed

    private void tblListaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaProdutoMouseClicked
        // Carega dados do produtoao clicar na tabela Lista de Produtos

        int linha = tblListaProduto.getSelectedRow();
        txtCodigoProduto.setText(tblListaProduto.getModel().getValueAt(linha, 0).toString());
        txtDescricao.setText(tblListaProduto.getModel().getValueAt(linha, 1).toString());
        txtValorUnit.setText(tblListaProduto.getModel().getValueAt(linha, 3).toString());
        txtQtd.requestFocus();
        txtQtd.setText(null);
        txtQtd.setEnabled(true);
        txtValorTotal.setText("0,00");
        txtQtd.setEnabled(true);
        lblMsgRetorno.setText(null);
        //Zera o cronômetro
        s = 0;


    }//GEN-LAST:event_tblListaProdutoMouseClicked

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        // Lista o produto localizado pelas inicias do nome
        tblListaProduto.setModel(DbUtils.resultSetToTableModel(cproduto.pesquisarProduto(txtPesquisa.getText())));
        modelProduroEstoque.redimensionaColunas(tblListaProduto);
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void txtPesquisaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesquisaFocusGained
        // Vai para a tabela de listagem de produto
        jTabbedPanePedido.setSelectedIndex(2);
        tblListaProduto.setModel(DbUtils.resultSetToTableModel(cproduto.listaProdutoDisponivel()));
        modelProduroEstoque.redimensionaColunas(tblListaProduto);
    }//GEN-LAST:event_txtPesquisaFocusGained

    private void tblDetalhePedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalhePedidoMouseClicked
        // Envia produto para a cozinha
        s = 0;
        int linha = tblDetalhePedido.getSelectedRow();

        idProduto = tblDetalhePedido.getModel().getValueAt(linha, 0).toString();
        descricao = tblDetalhePedido.getModel().getValueAt(linha, 1).toString();
        qtd = tblDetalhePedido.getModel().getValueAt(linha, 2).toString();

        String grupo = cproduto.localizaGrupoProduto(Integer.parseInt(idProduto));

        if (grupo.toLowerCase().equals("cozinha")) {

            // Habilita os botões [REEVIO COZINHA] se o prato não existir.
            if (!cc.temNaCozinha(idProduto, txtNumeroPedido.getText())) {
                lblReenvioCozinha.setEnabled(true);
                lblBtnReenvioCozinha.setEnabled(true);
            } else {
                lblReenvioCozinha.setEnabled(false);
                lblBtnReenvioCozinha.setEnabled(false);
            }

        } else {

            lblReenvioCozinha.setEnabled(false);
            lblBtnReenvioCozinha.setEnabled(false);
        }
        lblMsgRetorno.setText(null);


    }//GEN-LAST:event_tblDetalhePedidoMouseClicked

    private void panelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFecharMouseClicked
        // Fecha a janela atual se o usuário logado for Gerente
        // Para demais usuário a janela será fechada e chamará a Tela de Login
        if ("Gerente".equals(lblCargo.getText()) || "Caixa".equals(lblCargo.getText())) {
            if ("Caixa".equals(lblCargo.getText())) {
                tc.atualizaPedidoNoCaixa();

            }
            this.dispose();
            //task.cancel();//Cancela a contagem do tempo do método conômetro
        } else {
            this.dispose();
            TelaLogin login = new TelaLogin();
            login.setVisible(true);

        }
        //task.cancel();//Cancela a contagem do tempo do método conômetro
    }//GEN-LAST:event_panelFecharMouseClicked

    private void txtQtdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQtdMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdMouseEntered

    private void lblStatusCozinhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStatusCozinhaMouseClicked
        // Chama Tela Cozinha
        s = 0;
        if (lblStatusCozinha.isEnabled()) {
            // Se tela Status não estiver instanciada. Instancia e abre a tela.
            if (status == null) {
                status = new TelaStatusCozinha();
                status.setAlwaysOnTop(true);
                status.recebeOperador(comboGarcom.getSelectedItem().toString(), txtNumeroPedido.getText(), txtNumeroMesa.getText());
            }
            status.setVisible(true);

        }
    }//GEN-LAST:event_lblStatusCozinhaMouseClicked

    private void lblGerenciarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGerenciarPedidoMouseClicked
        s = 0;
        if (lblGerenciarPedido.isEnabled()) {
            if (gp == null) {

                gp = new TelaGerenciarPedido();
                gp.recebeOperador(this, lblOperador.getText(), lblCargo.getText());
            }
            gp.setVisible(true);
        }
    }//GEN-LAST:event_lblGerenciarPedidoMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // Sai da tela
        if ("Gerente".equals(lblCargo.getText())) {
            this.dispose();
            //task.cancel();
        } else {
            this.dispose();
            TelaLogin login = new TelaLogin();
            login.setVisible(true);
            //task.cancel();
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void txtQtdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtdFocusLost

    }//GEN-LAST:event_txtQtdFocusLost

    private void txtCodigoProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdutoKeyReleased
        // Aceita a penas número        
        txtCodigoProduto.setText(txtCodigoProduto.getText().replaceAll("[^0-9]", ""));
        // Limita o tamanho do campo
        txtCodigoProduto.setText(u.tamanhoMaximo(txtCodigoProduto.getText(), 10));

    }//GEN-LAST:event_txtCodigoProdutoKeyReleased

    private void txtQtdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdKeyReleased
        // Aceita apenas número
        txtQtd.setText(txtQtd.getText().replaceAll("[^0-9]", ""));
        txtQtd.setText(u.tamanhoMaximo(txtQtd.getText(), 3));
        try {

            int qtd = Integer.parseInt(txtQtd.getText());
            double total = qtd * Double.parseDouble(txtValorUnit.getText().replace(",", "."));
            FormataValor fv = new FormataValor();

            txtValorTotal.setText(fv.Formata(String.valueOf(total)));
        } catch (NumberFormatException e) {
            txtValorTotal.setText("0,00");
        }

    }//GEN-LAST:event_txtQtdKeyReleased

    private void tblListaProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblListaProdutoFocusLost

    }//GEN-LAST:event_tblListaProdutoFocusLost

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // Chama a tela de Bloqueio

    }//GEN-LAST:event_formKeyPressed

    private void lblCadeadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCadeadoMouseClicked
        // Chama a tela de Bloqueio
        s = 70;
        if (tb == null) {
            tb = new TelaBloqueio();
            tb.setAlwaysOnTop(true);
        }
        tb.setVisible(true);
    }//GEN-LAST:event_lblCadeadoMouseClicked

    private void lblAlterarSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlterarSenhaMouseClicked
        s = 0;
        if (telap2 == null) {
            telap2 = new TelaAlteraSenha2();
            telap2.setAlwaysOnTop(true);
            telap2.receberOperador(lblOperador.getText());
        }
        telap2.setVisible(true);
    }//GEN-LAST:event_lblAlterarSenhaMouseClicked

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // zera o contador 
        s = 0;
    }//GEN-LAST:event_formMouseMoved

    private void lblBtnReenvioCozinhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBtnReenvioCozinhaMouseClicked
        // Reenvia para to para a cozinha.
        if (lblBtnReenvioCozinha.isEnabled()) {

            s = 0;
            ArrayList<String> pCozinha = new ArrayList<>();

            //codProduto, produto, qtd, funcionario, mesa, data, status
            pCozinha.add(descricao); // Descrição do Produto
            pCozinha.add(idProduto); // // Código Produto
            pCozinha.add(qtd); // Qtd         
            pCozinha.add(comboGarcom.getSelectedItem().toString()); // Nome do Funcionario
            pCozinha.add(txtNumeroMesa.getText()); // Numero da mesa
            pCozinha.add("Pendente"); // Status Pendente - Liberado
            pCozinha.add(txtNumeroPedido.getText());
            Date dtAtual = new Date();
            Timestamp tms = new Timestamp(dtAtual.getTime());
            pCozinha.add(String.valueOf(tms)); // Data Atual 
            int op = JOptionPane.showConfirmDialog(this, "Deseja adicionar uma observação?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            // Chama a tela de observação
            if (op == JOptionPane.YES_OPTION) {

                TelaObservacaoProduto telaObs = new TelaObservacaoProduto();
                telaObs.recebeTela(this, pCozinha); // Envia dados do produto
                telaObs.setAlwaysOnTop(true);

            } else {

                pCozinha.add(null);//Obsrevação do prato                               
                // Envia prato para cozinha
                enviaParaCozinha(pCozinha);
            }
            // Envia prato para a cozinha
            //enviaParaCozinha(pCozinha);
            lblReenvioCozinha.setEnabled(false);
            lblBtnReenvioCozinha.setEnabled(false);
        }
    }//GEN-LAST:event_lblBtnReenvioCozinhaMouseClicked

    private void lblCozinhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCozinhaMouseClicked
        //task.cancel();
        TelaConzinha telaCozinha = new TelaConzinha();
        telaCozinha.recebeOperador(lblOperador.getText(), lblCargo.getText());
        telaCozinha.setVisible(true);
        //Cancela Cronometro ao abrir a tela de cozinha
    }//GEN-LAST:event_lblCozinhaMouseClicked

    private void lblBtnAnexarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBtnAnexarMouseClicked
        if (lblBtnAnexar.isEnabled()) {
            // Anexa Pedido ao Delivery
            if (td == null) {
                td = new TelaDelivery();
            }
            // Verfica se o pedido selecionado já existe no delivery
            if (cd.temNoDelivery(txtNumeroPedido.getText())) {

            } else {
                ControlerCliente controlCliente = new ControlerCliente();
                String idCliente = controlCliente.retornaIdCliente(cliente);
                if (cd.anexaPedido(txtNumeroPedido.getText(), idCliente, txtIdGarcom.getText(), txtNumeroMesa.getText())) {
                    JOptionPane.showMessageDialog(this, "Pedido anexado ao Delivery com sucesso!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
                    td.recebePedido(txtNumeroPedido.getText(), txtNumeroMesa.getText());
                }
            }
            this.dispose();
            td.setVisible(true);
        }
    }//GEN-LAST:event_lblBtnAnexarMouseClicked

    private double calculaPedido() {
        double valor = Double.parseDouble(txtValorUnit.getText().replaceAll(",", "."));
        int qtd = Integer.parseInt(txtQtd.getText());
        double total = valor * qtd;

        return total;
    }

    private void cronometro() {
        long segundos = 1000;
        Timer timer = new Timer();

        task = new TimerTask() {
            @Override
            public void run() {
                //Realiza uma contagem a incremento de 1 acadas segundo.

                s = s + 1;
                lblSegundos.setText(String.valueOf(s));
                // Verifica se a contagem atingiu o limite informado para bloqueio da tela.
                if (s == limite) {

                    TelaBloqueio tb = new TelaBloqueio();
                    //tb.setModal(true);
                    tb.setAlwaysOnTop(true);
                    tb.setVisible(true);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, segundos);

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
            java.util.logging.Logger.getLogger(TelaPedido2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPedido2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPedido2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPedido2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPedido2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirPedido;
    private javax.swing.JButton btnListar;
    private javax.swing.JComboBox<String> comboGarcom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPanePedido;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel labelAnexar;
    private javax.swing.JLabel lblAlterarSenha;
    private javax.swing.JLabel lblBloquearTela;
    private javax.swing.JLabel lblBtnAnexar;
    private javax.swing.JLabel lblBtnReenvioCozinha;
    private javax.swing.JLabel lblCadeado;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigo1;
    private javax.swing.JLabel lblCozinha;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblData2;
    private javax.swing.JLabel lblGerenciarPedido;
    private javax.swing.JLabel lblLupa;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblMsgRetorno;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblQtd;
    private javax.swing.JLabel lblQtd1;
    private javax.swing.JLabel lblQtd2;
    private javax.swing.JLabel lblReenvioCozinha;
    private javax.swing.JLabel lblSegundos;
    private javax.swing.JLabel lblStatusCozinha;
    private javax.swing.JLabel lblTextocozinha;
    private javax.swing.JLabel lbl_status_cozinha;
    private javax.swing.JLabel lbllogo;
    private javax.swing.JPanel panelAbrirPedido;
    private javax.swing.JPanel panelFechar;
    private javax.swing.JTable tblDetalhePedido;
    private javax.swing.JTable tblListaProduto;
    private javax.swing.JTable tblNumeroMesa;
    private javax.swing.JTable tblPedidosAbertos;
    private javax.swing.JLabel textoLblPedido;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtIdGarcom;
    private javax.swing.JTextField txtIdMesa;
    private javax.swing.JTextField txtNumeroMesa;
    private javax.swing.JTextField txtNumeroPedido;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtQtd;
    private javax.swing.JTextField txtValorTotal;
    private javax.swing.JTextField txtValorUnit;
    // End of variables declaration//GEN-END:variables

    private void limpaform() {
        txtCodigoProduto.setText(null);
        txtDescricao.setText(null);
        //txtValorUnit.setText(null);
        txtValorUnit.setText("0,00");
        txtQtd.setText(null);
        txtValorTotal.setText("0,00");
        txtPesquisa.setText(null);
        txtPesquisa.setVisible(false);
        lblPesquisa.setVisible(false);

    }

    private void bloqueiaCampos() {
        txtCodigoProduto.setEnabled(false);
        txtQtd.setEnabled(false);
    }

    // Este método adiciona um item ao pedido e é chamado pela execusão dos 
    // Eventos KeyPressed e LostFocus
    private void adicionaItemNoPedido() {

        try {
            // Caso a quantidade não seja informada pede ao usuário que digite novamente!
            if (txtQtd.getText().isEmpty()) {
                lblMensagem.setText("*Informe uma quantidade válida!");
                lblMensagem.setForeground(Color.red);
                txtQtd.requestFocus();

            } else {
                Double totalpedido = calculaPedido();
                txtValorTotal.setText(String.format("%9.2f", totalpedido));

                lblMensagem.setText(null);

                // Verifica se o pedido foi selecionado
                if (txtNumeroPedido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um pedido!");
                } else {
                    // Confirma pedido
                    // Instancia um objeto Produto Pedido
                    ProdutoPedido pp = new ProdutoPedido();
                    // Seta dados no objeto
                    pp.setTbproduto_id(txtCodigoProduto.getText());
                    pp.setQtd(txtQtd.getText());
                    pp.setValorUnit(txtValorUnit.getText().replaceAll(",", "."));
                    pp.setTotal(txtValorTotal.getText().replaceAll(",", "."));
                    pp.setCadmesa_id(txtIdMesa.getText());
                    pp.setCadpedido_id_pedido(txtNumeroPedido.getText());
                    pp.setTbcadfuncionario_id(txtIdGarcom.getText());

                    ControlerEstoque ec = new ControlerEstoque();
                    int qtdEstoque = ec.temNoEstoque(pp.getTbproduto_id());

                    if (Integer.parseInt(pp.getQtd()) <= qtdEstoque) {

                        // Adiciona o produto ao pedido
                        if (cproduto.adicionaProdutoAoPedido(pp)) {
                            lblMsgRetorno.setText("*Produto adicionado com sucesso!");

                        }
                        //Log

                        Log l = new Log(lblOperador.getText(), "Adicionar", "Adicionou o item " + txtDescricao.getText() + " no pedido N." + txtNumeroPedido.getText());
                        l.gravaLog(l);

                        // Retira o produto do estoque                 
                        ec.retiraEstoque(pp, pp.getQtd());

                        // Registra movimentação 
                        if (est.registraMovimentacao(pp.getTbproduto_id(), pp.getQtd(), est.localizaIdOperacao("Venda"), null)) {
                            System.out.println("Movimentação registrada!");
                        }

                        // ------ Verifica Grupo de Produto e Envia para acozinha ---/
                        String grupo = cproduto.localizaGrupoProduto(Integer.parseInt(txtCodigoProduto.getText()));

                        if (grupo.equals("Cozinha")) {

                            ArrayList<String> pCozinha = new ArrayList<>();

                            //codProduto, produto, qtd, funcionario, mesa, data, status
                            pCozinha.add(txtDescricao.getText()); // Produto
                            pCozinha.add(txtCodigoProduto.getText()); // // Código Produto
                            pCozinha.add(txtQtd.getText()); // Qtd         
                            pCozinha.add(comboGarcom.getSelectedItem().toString()); // Nome do Funcionario
                            pCozinha.add(txtNumeroMesa.getText()); // Numero da mesa
                            pCozinha.add("Pendente"); // Status Pendente - Liberado
                            pCozinha.add(txtNumeroPedido.getText());
                            Date dtAtual = new Date();
                            Timestamp tms = new Timestamp(dtAtual.getTime());
                            pCozinha.add(String.valueOf(tms)); // Data Atual
                            int op = JOptionPane.showConfirmDialog(this, "Deseja adicionar uma observação?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                            // Chama a tela de observação
                            if (op == JOptionPane.YES_OPTION) {
                                s = 0; // Zera Cronômetro de Bloqueio
                                TelaObservacaoProduto telaObs = new TelaObservacaoProduto();
                                telaObs.recebeTela(this, pCozinha); // Envia dados do produto
                                telaObs.setAlwaysOnTop(true);
                                telaObs.setVisible(true);

                            } else {

                                pCozinha.add(null);//Obsrevação do prato                               
                                // Envia prato para cozinha
                                enviaParaCozinha(pCozinha);
                            }

                        }

                        // Limpa label de mensagem de produto indisponível 
                        lblMensagem.setText(null);

                        bloqueiaCampos();
                        txtCodigoProduto.setEnabled(true);
                        txtQtd.setEnabled(false);

                        //limpaform();
                    } else {
                        // Exibe mensagem de produto indisponpivel no momento
                        lblMensagem.setForeground(Color.red);
                        lblMensagem.setText("*Quantidade insuficiente! " + "Existe apenas " + qtdEstoque + " unidade(s) em estoque.");
                        txtQtd.requestFocus();
                        //txtCodigoProduto.setText(null);
                        txtQtd.setText(null);

                    }
                    //Atualiza a tabela de detalhe do pedido    
                    tblDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(txtIdMesa.getText(), txtNumeroPedido.getText())));
                    modelDetPedido.redimensionaColunas(tblDetalhePedido);
                    // Atualiza os produtos disponível no Estoque
                    tblListaProduto.setModel(DbUtils.resultSetToTableModel(cproduto.listaProdutoDisponivel()));
                    modelProduroEstoque.redimensionaColunas(tblListaProduto);
                    s = 0; // Zera Cronômetro de Bloqueio
                }

            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println(txtQtd);
            System.out.println("br.com.bar.view.TelaPedido2.txtQtdKeyPressed()" + e);
            lblMensagem.setText("*Quantidade inválida, tente novamente!");
            lblMensagem.setOpaque(false);
            lblMensagem.setForeground(Color.red);
            txtQtd.requestFocus();
        }
        limpaform();
    }

    // Recebe um ArrayList de String com os dados do prato a ser enviado para cozinha
    // na inclusão do produto no pedido.
    private void enviaParaCozinha(ArrayList<String> prato) {

        ControlerCozinha cc = new ControlerCozinha(); // Instancia o controler cozinha
        if (cp.enviaProdutoCozinha(prato)) {
            JOptionPane.showMessageDialog(this, "Solicitação de prato enviada para a cozinha!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao tentar enviar solicitação de prato para a cozinha - Contate o SUPORTE!");
        }

    }

    /**
     * Recebe dados atualizados do produto para envio a cozinha .
     *
     * @param listaAtualizada - Listagem
     */
    public void recebeObsPrato(ArrayList<String> listaAtualizada, TelaObservacaoProduto obs) {
        this.obsevacaoPrato = listaAtualizada;
        obs.dispose();
        enviaParaCozinha(listaAtualizada);
    }

    //Recebe o nome do Cliente do Delivery
    public void recebeCliente(TelaDelivery tela, String tipoPedido, String nomeCliente) {
        this.cliente = nomeCliente;
        this.tipoPedido = tipoPedido;
        this.td = tela;
        lblCliente.setText("Cliente: " + nomeCliente);
        comboGarcom.setSelectedItem("delivery");

        // Lista mesas 
        String nomeFunc = comboGarcom.getSelectedItem().toString();
        String idFunc = cFunc.localizaId(nomeFunc);
        txtIdGarcom.setText(cFunc.localizaId(comboGarcom.getSelectedItem().toString()));
        tblNumeroMesa.setModel(DbUtils.resultSetToTableModel(cm.listaMesaLivre(idFunc)));
        modelNmesas.redimensionaColunas(tblNumeroMesa);
        //modelMesa.redimensionaColunas(tblNumeroMesa);
        tblPedidosAbertos.setModel(DbUtils.resultSetToTableModel(cp.listaPedidos(txtIdGarcom.getText())));
        modelPedidos.redimensionaColunas(tblPedidosAbertos);

        btnAbrirPedido.setEnabled(false);
        // Vai para a primeira guia
        jTabbedPanePedido.setSelectedIndex(0);
        bloqueiaCampos();

    }

}
