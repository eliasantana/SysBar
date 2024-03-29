/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.dao.ReportUtil;
import br.com.bar.model.Funcionario;
import br.com.bar.model.ProdutoCozinha;
import br.com.bar.model.TableModelCozinha;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerCozinha;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerFuncionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author elias
 */
public class TelaConzinha extends javax.swing.JFrame {

    /**
     * Creates new form TelaConzinha
     */
    ControlerCozinha cc = new ControlerCozinha();
    ControlerFuncionario cf = new ControlerFuncionario();
    TableModelCozinha modelCozinha = new TableModelCozinha();
    TelaAlteraSenha2 alteraSenha2;
    ProdutoCozinha pc;
    
    Funcionario f = new Funcionario();
    Util u = new Util();
    String idProdutoCozinha = null; // Id do Prato a ser preparado 
    ControlerDadosEmpresa ce = new ControlerDadosEmpresa();
    //Guarda o id do prato liberado
    String id_pratoLiberado = null;
    // Armazena o número da mesa selecionada
    int nMesa = 0;
    String status = "";
    // Opções da janela de Confirmação
    Object[] obj = {"   Não  ","   Sim   "};
    public TelaConzinha() {
        initComponents();
        lblCargo.setVisible(false);
        Date dt = new Date();
        lblData.setText(u.formataDataBr(dt));
        txtidProdutoCozinha.setVisible(false);
        jTextAreaObservacao.setVisible(false);
        lblObservacao.setVisible(false);
        lblImprimirSolicitacoes.setEnabled(false);
        desabilitaTodosBtns();

        // Atualiza a lista de pedidos da cozinha após período de tempo informado
        //long minutos = 60000; //milisegundos = 1 minuto
        long minutos = 15000; //milisegundos = 15 segundos

        java.util.Timer timer = new java.util.Timer();
        TimerTask atualizaCozinha = new TimerTask() {
            @Override
            public void run() {

                tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
                // Modifica o tamanho das colunas da tabela
                modelCozinha.redimensionaColunas(tblCozinha);
                modelCozinha.adicionaCoresTabela(tblCozinha);

                //lblPreparar.setEnabled(false);
                //lblLiberaRefeicao.setEnabled(false);
                lblMsg.setText(null);

            }
        };

        timer.scheduleAtFixedRate(atualizaCozinha, 0, minutos);
        relogio();

    }

    public void recebeOperador(String operador, String cargo) {

        lblOperador.setText(operador);
        lblCargo.setText(cargo);

        lblLogo.setIcon(u.carregaLogo());
        if ("Gerente".equals(lblCargo.getText())) {
            lblAlteraSenha.setVisible(false);
            lblSair.setVisible(false);
        }

    }
    
    public void atualizaTabelaCozinha(){
                tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
                // Modifica o tamanho das colunas da tabela
                modelCozinha.redimensionaColunas(tblCozinha);
                modelCozinha.adicionaCoresTabela(tblCozinha);
                lblObservacao.setVisible(false);
                jTextAreaObservacao.setText(null);
                jTextAreaObservacao.setVisible(false);
    }
    
    // Atualiza a tela de senha após clique no botão cancelar
    public void atualizaTelaSenha() {
        alteraSenha2 = null;
    }
    
    // Recebe o código do funcionário retornado da Tela ConfirmaCozinheiro.
    public void recebeCozinheiro(Funcionario f, String idPrato) {
        this.f = f;
        this.idProdutoCozinha = idPrato;
        preparar();
        ocultaObservacao();

    }
  
    private void relogio() {
        long segundos = 1000;
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Calendar c = Calendar.getInstance();

                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                lblRelogio.setText(df.format(c.getTime()));
            }
        };
        timer.scheduleAtFixedRate(task, 0, segundos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelEsquerdo = new javax.swing.JPanel();
        txtidProdutoCozinha = new javax.swing.JTextField();
        lblLogo = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblRelogio = new javax.swing.JLabel();
        lblObservacao = new javax.swing.JLabel();
        jTextAreaObservacao = new javax.swing.JTextArea();
        paineldireito = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCozinha = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblREmovePrato = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblSair = new javax.swing.JLabel();
        lblLiberaRefeicao = new javax.swing.JLabel();
        lblPreparar = new javax.swing.JLabel();
        lblMsg = new javax.swing.JLabel();
        lblAlteraSenha = new javax.swing.JLabel();
        lblImprimirSolicitacoes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        painelEsquerdo.setBackground(new java.awt.Color(38, 53, 61));
        painelEsquerdo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/refeicao128x128.png"))); // NOI18N
        lblLogo.setToolTipText("");

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblOperador.setForeground(new java.awt.Color(255, 255, 255));
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario_branco.png"))); // NOI18N
        lblOperador.setText("jLabel9");

        lblData.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Calendar32x32.png"))); // NOI18N
        lblData.setText("jLabel10");

        lblRelogio.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblRelogio.setForeground(new java.awt.Color(255, 255, 255));
        lblRelogio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRelogio.setText("00:00:00");

        lblObservacao.setForeground(new java.awt.Color(255, 255, 255));
        lblObservacao.setText("Observações do Prato");

        jTextAreaObservacao.setEditable(false);
        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setLineWrap(true);
        jTextAreaObservacao.setRows(5);
        jTextAreaObservacao.setFocusable(false);

        javax.swing.GroupLayout painelEsquerdoLayout = new javax.swing.GroupLayout(painelEsquerdo);
        painelEsquerdo.setLayout(painelEsquerdoLayout);
        painelEsquerdoLayout.setHorizontalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblObservacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextAreaObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                                .addComponent(lblOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(txtidProdutoCozinha, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblRelogio, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelEsquerdoLayout.setVerticalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblLogo)
                .addGap(18, 18, 18)
                .addComponent(txtidProdutoCozinha, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRelogio, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblObservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextAreaObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        getContentPane().add(painelEsquerdo);
        painelEsquerdo.setBounds(0, 0, 250, 690);

        paineldireito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        paineldireito.setLayout(null);

        tblCozinha = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCozinha.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        tblCozinha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SEQ", "PRATO", "QTD", "GARÇOM", "MESA", "COZINHEIRO", "HORA", "ESPERA", "STATUS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCozinha.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblCozinha.setRowHeight(25);
        tblCozinha.setSelectionBackground(new java.awt.Color(0, 255, 204));
        tblCozinha.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblCozinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCozinhaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCozinha);
        if (tblCozinha.getColumnModel().getColumnCount() > 0) {
            tblCozinha.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblCozinha.getColumnModel().getColumn(1).setPreferredWidth(180);
            tblCozinha.getColumnModel().getColumn(2).setPreferredWidth(30);
            tblCozinha.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        paineldireito.add(jScrollPane1);
        jScrollPane1.setBounds(10, 119, 1010, 480);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Solicitações de Pratos:");
        paineldireito.add(jLabel2);
        jLabel2.setBounds(10, 90, 270, 30);

        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("jLabel10");
        paineldireito.add(lblCargo);
        lblCargo.setBounds(20, 20, 110, 30);

        jPanel1.setBackground(new java.awt.Color(52, 73, 94));

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        paineldireito.add(jPanel1);
        jPanel1.setBounds(990, 0, 40, 40);

        lblREmovePrato.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblREmovePrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        lblREmovePrato.setText("Remover Prato");
        lblREmovePrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblREmovePratoMouseClicked(evt);
            }
        });
        lblREmovePrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblREmovePratoKeyPressed(evt);
            }
        });
        paineldireito.add(lblREmovePrato);
        lblREmovePrato.setBounds(540, 630, 160, 50);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cozinha");
        paineldireito.add(jLabel3);
        jLabel3.setBounds(7, 22, 990, 64);

        lblSair.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblSair.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/power.png"))); // NOI18N
        lblSair.setText("Sair");
        lblSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSairMouseClicked(evt);
            }
        });
        paineldireito.add(lblSair);
        lblSair.setBounds(880, 630, 141, 50);

        lblLiberaRefeicao.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblLiberaRefeicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnCozinha.png"))); // NOI18N
        lblLiberaRefeicao.setText("Liberar Prato");
        lblLiberaRefeicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLiberaRefeicaoMouseClicked(evt);
            }
        });
        paineldireito.add(lblLiberaRefeicao);
        lblLiberaRefeicao.setBounds(380, 630, 160, 50);

        lblPreparar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblPreparar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/preparar32x32.png"))); // NOI18N
        lblPreparar.setText("Iniciar Preparo");
        lblPreparar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrepararMouseClicked(evt);
            }
        });
        paineldireito.add(lblPreparar);
        lblPreparar.setBounds(220, 630, 140, 50);

        lblMsg.setForeground(new java.awt.Color(0, 0, 153));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMsg.setText("msg");
        paineldireito.add(lblMsg);
        lblMsg.setBounds(120, 610, 900, 20);

        lblAlteraSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/chave48x48.png"))); // NOI18N
        lblAlteraSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlteraSenhaMouseClicked(evt);
            }
        });
        paineldireito.add(lblAlteraSenha);
        lblAlteraSenha.setBounds(20, 630, 100, 50);

        lblImprimirSolicitacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Impressora32x32.png"))); // NOI18N
        lblImprimirSolicitacoes.setText("Solicitações");
        lblImprimirSolicitacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImprimirSolicitacoesMouseClicked(evt);
            }
        });
        paineldireito.add(lblImprimirSolicitacoes);
        lblImprimirSolicitacoes.setBounds(720, 630, 140, 50);

        getContentPane().add(paineldireito);
        paineldireito.setBounds(240, 0, 1028, 690);

        setSize(new java.awt.Dimension(1269, 690));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        String cargo = lblCargo.getText();
        if ("Gerente".equals(cargo)) {
            dispose();
        } else {

            if (cc.pratoPendente() > 0) { // Verifica se existem pratos pendentes na cozinha.
                JOptionPane.showMessageDialog(this, "Realize a liberação dos pratos pendentes antes de sair!");
                // Só permite fechar a Tela Cozinha após liberação de todos os pratos.
            } else {
                dispose();
                TelaLogin login = new TelaLogin();
                login.setVisible(true);
            }
        }

    }//GEN-LAST:event_jLabel1MouseClicked

    private void tblCozinhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCozinhaMouseClicked
        int linha = tblCozinha.getSelectedRow();
        //SEQ - PRATO - QTD - GARÇOM - MESA - COZINHEIRO - HORA - ESPERA - STATUS
        pc= new ProdutoCozinha();
        pc.setSeq(tblCozinha.getModel().getValueAt(linha, 0).toString());
        pc.setPrato(tblCozinha.getModel().getValueAt(linha, 1).toString());
        pc.setQtd(tblCozinha.getModel().getValueAt(linha, 2).toString());
        pc.setGarcom(tblCozinha.getModel().getValueAt(linha, 3).toString());
        pc.setnMesa(tblCozinha.getModel().getValueAt(linha, 4).toString());
        pc.setCozinheiro(tblCozinha.getModel().getValueAt(linha, 5).toString());
        pc.settEspera(tblCozinha.getModel().getValueAt(linha, 7).toString());
        pc.setStatus(tblCozinha.getModel().getValueAt(linha, 8).toString());

        
        lblMsg.setText(null);
        // Captura o Status do prato
        status = tblCozinha.getModel().getValueAt(linha, 8).toString();
        String observacao = cc.temObs(tblCozinha.getModel().getValueAt(linha, 0).toString());
        nMesa = Integer.parseInt(tblCozinha.getModel().getValueAt(linha, 4).toString()); //Número da mesa

        switch (status) {

            case "EM PREPARAÇÃO":
                lblLiberaRefeicao.setEnabled(true);
                lblPreparar.setEnabled(false);
                lblImprimirSolicitacoes.setEnabled(false);
                break;
            case "PENDENTE":
                lblPreparar.setEnabled(true);
                lblLiberaRefeicao.setEnabled(false);
                lblImprimirSolicitacoes.setEnabled(true);
                break;
        }
        if ("Gerente".equals(lblCargo.getText())) {
            lblREmovePrato.setEnabled(true);

        } else {
            lblREmovePrato.setEnabled(false);
        }

        // Captura o Id do prato
        txtidProdutoCozinha.setText(tblCozinha.getModel().getValueAt(linha, 0).toString());

        if (observacao != null) {
            lblObservacao.setVisible(true);
            jTextAreaObservacao.setText(observacao);
            jTextAreaObservacao.setVisible(true);
        } else {
            lblObservacao.setVisible(false);
            jTextAreaObservacao.setVisible(false);
        }

    }//GEN-LAST:event_tblCozinhaMouseClicked

    private void lblLiberaRefeicaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLiberaRefeicaoMouseClicked
        // Libera produto refeição  da cozinha
        if (lblLiberaRefeicao.isEnabled()) {
            int linha = tblCozinha.getSelectedRow();
            // Captura o tempo de "Espera" da tabela
            String tempoEspera = tblCozinha.getModel().getValueAt(linha, 7).toString();

            // Registra liberação do produto e o tempo de espera
            if (cc.liberaProduto(txtidProdutoCozinha.getText(), tempoEspera)) {
                String prato = tblCozinha.getModel().getValueAt(0, 1).toString();
                lblMsg.setText("*Prato " + "'" + prato + "'" + " liberado com sucesso! ");
                tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
                modelCozinha.redimensionaColunas(tblCozinha);
                modelCozinha.adicionaCoresTabela(tblCozinha);
                lblLiberaRefeicao.setEnabled(false);
                lblREmovePrato.setEnabled(false);
                id_pratoLiberado = txtidProdutoCozinha.getText();
                txtidProdutoCozinha.setText(null);
                // Solicita confirmação de impressão
                ocultaObservacao(); // Oculta observação do Prato
                
                int confirma = JOptionPane.showOptionDialog(this, "Imprimir Comprovante de Liberação?", "Atenção", 
                               JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, obj, obj[1]);
                if (confirma == 1) {
                    cc.imprimeComprovanteCozinha(id_pratoLiberado);
                }
            }

        }

    }//GEN-LAST:event_lblLiberaRefeicaoMouseClicked

    private void lblSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSairMouseClicked
        if ("Gerente".equals(lblCargo.getText())) {
            dispose();
        } else {
            if (cc.pratoPendente() > 0) {
                JOptionPane.showMessageDialog(null, "Realize a liberação dos pratos pendentes antes de sair!");
            } else {
                dispose();
                TelaLogin login = new TelaLogin();
                login.setVisible(true);
            }
        }
    }//GEN-LAST:event_lblSairMouseClicked

    private void lblREmovePratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblREmovePratoMouseClicked
        if (lblREmovePrato.isEnabled()) {

            // Remove prato Cozinha se o usuário logado tiver o perfil de Gerente e 
            if ("Gerente".equals(lblCargo.getText()) && !"".equals(txtidProdutoCozinha.getText())) {

                int resp = JOptionPane.showOptionDialog(this, "Confirma a exclusão deste prato?", "Atenção!", JOptionPane.YES_NO_OPTION, 
                           JOptionPane.ERROR_MESSAGE,null,obj,obj[1]);
                if (resp == 1) {
                    if (Integer.parseInt(pc.getQtd())>1){
                        TelaAtualizaItemCozinha atualizaItem = new TelaAtualizaItemCozinha();
                        atualizaItem.setAlwaysOnTop(true);
                        atualizaItem.setModal(true);
                        atualizaItem.recebeItemCozinha(this, pc);
                        atualizaItem.setVisible(true);
                    }else{
                        
                        if (cc.removePrato(txtidProdutoCozinha.getText())) {

                            ResultSet result = cc.listaProdutosCozinha();
                            // Log
                            Log l = new Log(lblOperador.getText(), "Remover", "Removeu o prato->" + txtidProdutoCozinha.getText());
                            l.gravaLog(l);
                            try {

                                if (result.next()) {
                                    tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
                                    modelCozinha.redimensionaColunas(tblCozinha);
                                    modelCozinha.adicionaCoresTabela(tblCozinha);
                                } else {
                                    lblREmovePrato.setEnabled(false);
                                    lblLiberaRefeicao.setEnabled(false);
                                    lblPreparar.setEnabled(false);
                                    tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
                                    modelCozinha.redimensionaColunas(tblCozinha);
                                    modelCozinha.adicionaCoresTabela(tblCozinha);
                                }
                            } catch (SQLException e) {
                                System.out.println("br.com.bar.view.TelaConzinha.lblREmovePratoMouseClicked()" + e);
                            }
                            lblObservacao.setVisible(false);
                            jTextAreaObservacao.setText(null);
                            jTextAreaObservacao.setVisible(false);
                        }
                    }

                } else {
                    lblREmovePrato.setEnabled(false);
                }

            }
        }

    }//GEN-LAST:event_lblREmovePratoMouseClicked

    private void lblPrepararMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrepararMouseClicked
        if (lblPreparar.isEnabled()) {

            TelaConfirmaCozinheiro tcc = new TelaConfirmaCozinheiro();
            tcc.recebeIdPrato(txtidProdutoCozinha.getText(), this);
            tcc.setAlwaysOnTop(true);
            tcc.setModal(true);  
            tcc.setVisible(true);

        }

    }//GEN-LAST:event_lblPrepararMouseClicked

    private void lblREmovePratoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblREmovePratoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblREmovePratoKeyPressed

    private void lblAlteraSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlteraSenhaMouseClicked
        TelaAlteraSenha2 alteraSenha = new TelaAlteraSenha2();
        alteraSenha.setAlwaysOnTop(true);
        alteraSenha.receberOperador(lblOperador.getText());
        alteraSenha.recebeTela(this,"Cozinha");
        alteraSenha.setVisible(true);
    }//GEN-LAST:event_lblAlteraSenhaMouseClicked

    private void lblImprimirSolicitacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImprimirSolicitacoesMouseClicked
        // Imprime solicitações para a mesa informada.
        if (lblImprimirSolicitacoes.isEnabled()) {

            if (nMesa > 0) {
                ReportUtil report = new ReportUtil();
                HashMap map = new HashMap();
                map.put("mesa", nMesa);
                try {
                    report.imprimeRelatorioTela("solicitacoes.jasper", map, "Solicitações");
                } catch (JRException e) {
                    System.out.println("br.com.bar.view.TelaConzinha.lblImprimirSolicitacoesMouseClicked()" + e);
                }
                nMesa = 0;
                lblImprimirSolicitacoes.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um prato continuar!", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_lblImprimirSolicitacoesMouseClicked

    private void preparar() {
        int linha = tblCozinha.getSelectedRow();
        // String idProdutoCozinha = idProduto;

        if ("Cozinheiro".equals(f.getCargo())) {
            // Registra a solicitação do preparo 
            cc.registraPreparo(idProdutoCozinha, f.getNome());
            Log l = new Log(lblOperador.getText(), "Preparo", "Iniciou a preparação do prato-> " + idProdutoCozinha);
            l.gravaLog(l);
            lblMsg.setText("*Preparo iniciado com sucesso!");
            //Habilita botões remover e preparar
            lblPreparar.setEnabled(false);
            lblREmovePrato.setEnabled(false);
            // Atualiza Tabela
            tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
            modelCozinha.redimensionaColunas(tblCozinha);
            modelCozinha.adicionaCoresTabela(tblCozinha);
            // Possibilita a impressao de 'Solicitacao de Prato' atraves do perfil 'Gerente' (APENAS)
            // Verifica se o usuário logado é o Gerente

            if ("Gerente".equals(lblCargo.getText())) {
                int op = JOptionPane.showOptionDialog(this, "Contingência! Deseja imprimir essa Solicitação?", "Atenção!", JOptionPane.YES_NO_OPTION, 
                         JOptionPane.ERROR_MESSAGE,null,obj,obj[1]);

                if (op == 1) {
                    ReportUtil rpu = new ReportUtil();
                    HashMap parametro = new HashMap();
                    parametro.put("id", idProdutoCozinha);
                    try {
                        rpu.imprimeRelatorioTela("contigencia_3.jasper", parametro, "Solicitação");
                    } catch (JRException e) {
                        System.out.println("br.com.bar.view.TelaConzinha.lblPrepararMouseClicked()" + e);
                        JOptionPane.showMessageDialog(null, "Erro ao tentar imprimir Solicitação - contate o SUPORTE!");
                    }
                }
                lblImprimirSolicitacoes.setEnabled(false);
            }
        }

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
            java.util.logging.Logger.getLogger(TelaConzinha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaConzinha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaConzinha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaConzinha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaConzinha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JLabel lblAlteraSenha;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblImprimirSolicitacoes;
    private javax.swing.JLabel lblLiberaRefeicao;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPreparar;
    private javax.swing.JLabel lblREmovePrato;
    private javax.swing.JLabel lblRelogio;
    private javax.swing.JLabel lblSair;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JPanel paineldireito;
    private javax.swing.JTable tblCozinha;
    private javax.swing.JTextField txtidProdutoCozinha;
    // End of variables declaration//GEN-END:variables

    private void desabilitaTodosBtns() {
        lblLiberaRefeicao.setEnabled(false);
        lblREmovePrato.setEnabled(false);
        lblPreparar.setEnabled(false);

    }

    // Oculta campos de observação do prato.
    private void ocultaObservacao() {
        lblObservacao.setVisible(false);
        jTextAreaObservacao.setVisible(false);
    }

}
