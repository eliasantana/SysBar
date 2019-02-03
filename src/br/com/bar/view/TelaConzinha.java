/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.dao.ReportUtil;
import br.com.bar.model.Funcionario;
import br.com.bar.model.TableModelCozinha;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerCozinha;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerFuncionario;
import java.awt.Color;
import java.util.Date;
import java.util.HashMap;
import java.util.TimerTask;
import javax.swing.ImageIcon;
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

    Util u = new Util();

    ControlerDadosEmpresa ce = new ControlerDadosEmpresa();

    public TelaConzinha() {
        initComponents();
        lblCargo.setVisible(false);
        Date dt = new Date();
        lblData.setText(u.formataDataBr(dt));
        txtidProdutoCozinha.setVisible(false);

        desabilitaTodosBtns();

        // Atualiza a lista de pedidos da cozinha após período de tempo informado
        long minutos = 60000; //milisegundos = 1 minuto
        java.util.Timer timer = new java.util.Timer();
        TimerTask atualizaCozinha = new TimerTask() {
            @Override
            public void run() {

                tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
                // Modifica o tamanho das colunas da tabela
                modelCozinha.redimensionaColunas(tblCozinha);
                modelCozinha.adicionaCoresTabela(tblCozinha);

                lblPreparar.setEnabled(false);
                lblLiberaRefeicao.setEnabled(false);
            }
        };

        timer.scheduleAtFixedRate(atualizaCozinha, 0, minutos);

    }

    public void recebeOperador(String operador, String cargo) {

        lblOperador.setText(operador);
        lblCargo.setText(cargo);

        lblLogo.setIcon(u.carregaLogo());
        //bloqueiaBotoes(true);

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
        jLabel5 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        painelEsquerdo.setBackground(new java.awt.Color(38, 53, 61));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("MasterFood");
        jLabel5.setToolTipText("");

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

        javax.swing.GroupLayout painelEsquerdoLayout = new javax.swing.GroupLayout(painelEsquerdo);
        painelEsquerdo.setLayout(painelEsquerdoLayout);
        painelEsquerdoLayout.setHorizontalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(txtidProdutoCozinha, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelEsquerdoLayout.setVerticalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtidProdutoCozinha, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        getContentPane().add(painelEsquerdo);
        painelEsquerdo.setBounds(0, 0, 280, 690);

        paineldireito.setLayout(null);

        tblCozinha.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        tblCozinha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SEQ", "PRATO", "QTD", "GARÇOM", "MESA", "COZINHEIRO", "HORÁRIO", "ESPERA", "STATUS"
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
        jScrollPane1.setBounds(0, 119, 1020, 490);

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
        jLabel1.setText("X");
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
        lblREmovePrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fechar48x48.png"))); // NOI18N
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
        lblREmovePrato.setBounds(480, 610, 160, 70);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cozinha");
        paineldireito.add(jLabel3);
        jLabel3.setBounds(27, 22, 960, 64);

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
        lblSair.setBounds(620, 610, 141, 70);

        lblLiberaRefeicao.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblLiberaRefeicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnCozinha.png"))); // NOI18N
        lblLiberaRefeicao.setText("Liberar Prato");
        lblLiberaRefeicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLiberaRefeicaoMouseClicked(evt);
            }
        });
        paineldireito.add(lblLiberaRefeicao);
        lblLiberaRefeicao.setBounds(320, 610, 160, 70);

        lblPreparar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblPreparar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/preparar32x32.png"))); // NOI18N
        lblPreparar.setText("Iniciar Preparo");
        lblPreparar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrepararMouseClicked(evt);
            }
        });
        paineldireito.add(lblPreparar);
        lblPreparar.setBounds(160, 610, 140, 70);

        getContentPane().add(paineldireito);
        paineldireito.setBounds(280, 0, 1030, 690);

        setSize(new java.awt.Dimension(1309, 693));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // Fecha Janela
        Log l = new Log();
        l.setUsuario(lblOperador.getText());
        l.setFuncionalidade("Fechar");

        String cargo = lblCargo.getText();
        if ("Gerente".equals(cargo)) {
            l.setDescricao(l.getUsuario() + " Fechou a tela cozinha");
            l.gravaLog(l);
            dispose();
        } else {

            if (cc.pratoPendente() > 0) { // Verifica se existem pratos pendentes na cozinha.
                JOptionPane.showMessageDialog(null, "Realize a liberação dos pratos pendentes antes de sair!");
                // Só permite fechar a Tela Cozinha após liberação de todos os pratos.

            } else {
                l.setDescricao(l.getUsuario() + " Saiu da tela cozinha");
                l.gravaLog(l);
                System.exit(0);
            }
        }

    }//GEN-LAST:event_jLabel1MouseClicked

    private void tblCozinhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCozinhaMouseClicked
        int linha = tblCozinha.getSelectedRow();
        // Captura o Status do prato
        String status = tblCozinha.getModel().getValueAt(linha, 8).toString();
        switch (status) {

            case "Em preparação":
                lblLiberaRefeicao.setEnabled(true);
                lblPreparar.setEnabled(false);
                break;
            case "Pendente":
                lblPreparar.setEnabled(true);
                lblLiberaRefeicao.setEnabled(false);
                break;
        }
        if ("Gerente".equals(lblCargo.getText())) {
            lblREmovePrato.setEnabled(true);

        } else {
            lblREmovePrato.setEnabled(false);
        }

        // Captura o Id do prato
        txtidProdutoCozinha.setText(tblCozinha.getModel().getValueAt(linha, 0).toString());

    }//GEN-LAST:event_tblCozinhaMouseClicked

    private void lblLiberaRefeicaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLiberaRefeicaoMouseClicked
        // Libera produto refeição  da cozinha
        if (lblLiberaRefeicao.isEnabled()) {
            int linha = tblCozinha.getSelectedRow();
            // Captura o tempo de "Espera" da tabela
            String tempoEspera = tblCozinha.getModel().getValueAt(linha, 7).toString();

            int op = JOptionPane.showConfirmDialog(null, "Deseja realmente liberar o prato selecionado?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {
                // Registra liberação do produto e o tempo de espera
                if (cc.liberaProduto(txtidProdutoCozinha.getText(), tempoEspera)) {
                    JOptionPane.showMessageDialog(null, "Prato librado com sucesso!");
                    Log l = new Log();
                    l.setDescricao(lblOperador.getText() + " liberou o prato " + tblCozinha.getModel().getValueAt(0, 1).toString() + " da cozinha");
                    l.setFuncionalidade("Liberar Prato");
                    l.setUsuario(lblOperador.getText());
                    l.gravaLog(l);

                    tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
                    modelCozinha.redimensionaColunas(tblCozinha);
                    modelCozinha.adicionaCoresTabela(tblCozinha);
                    lblLiberaRefeicao.setEnabled(false);
                    txtidProdutoCozinha.setText(null);
                    // Solicita confirmação de impressão
                    
                    int confirma = JOptionPane.showConfirmDialog(null, "Imprimir Comprovante de Liberação?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (confirma == JOptionPane.YES_OPTION) {
                        cc.imprimeComprovanteCozinha(txtidProdutoCozinha.getText());
                    }

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

                // Faz logout
                Log l = new Log();

                l.setDescricao(lblOperador.getText() + "Fez logout no sistema");
                l.setFuncionalidade("Logout");
                l.setUsuario(lblOperador.getText());
                l.gravaLog(l);

                dispose();
                TelaLogin login = new TelaLogin();
                login.setVisible(true);
            }
        }
    }//GEN-LAST:event_lblSairMouseClicked

    private void lblREmovePratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblREmovePratoMouseClicked
        if (lblREmovePrato.isEnabled()) {

            // Remove prato Cozinha se o usuário logado tiver o perfil de Gerente e 
            
            if ("Gerente".equals(lblCargo.getText())&&"".equals(txtidProdutoCozinha.getText())) {

                int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente remover este prato?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    if (cc.removePrato(txtidProdutoCozinha.getText())) {
                        JOptionPane.showMessageDialog(null, "Prato removido com sucesso!");
                        tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
                        modelCozinha.redimensionaColunas(tblCozinha);
                        modelCozinha.adicionaCoresTabela(tblCozinha);
                    }

                }

            }
        }

    }//GEN-LAST:event_lblREmovePratoMouseClicked

    private void lblPrepararMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrepararMouseClicked
        if (lblPreparar.isEnabled()) {

            // Registra Preparação do Prato
            Funcionario f = new Funcionario();
            String idProdutoCozinha = null;
            String codUsuario = null;
            // Localiza o usuário pelo codigo informado
            codUsuario = JOptionPane.showInputDialog(null, "Informe o código do Cozinheiro:");
            f = cf.localizaFuncionario(codUsuario);

            while (codUsuario.equals("") || !"Cozinheiro".equals(f.getCargo()) || f.getNome() == null) {
                codUsuario = JOptionPane.showInputDialog("Por favor, informe um código Válido!");
                System.out.println("id do prato cozinha " + idProdutoCozinha);
                f = cf.localizaFuncionario(codUsuario);
            }
            int linha = tblCozinha.getSelectedRow();
            idProdutoCozinha = tblCozinha.getModel().getValueAt(linha, 0).toString();

            if ("Cozinheiro".equals(f.getCargo())) {
                // Registra a solicitação do preparo 
                cc.registraPreparo(idProdutoCozinha, f.getNome());
                // Atualiza Tabela
                tblCozinha.setModel(DbUtils.resultSetToTableModel(cc.listaProdutosCozinha()));
                modelCozinha.redimensionaColunas(tblCozinha);
                modelCozinha.adicionaCoresTabela(tblCozinha);
                // Possibilita a impressao de 'Solicitacao de Prato' atraves do perfil 'Gerente' (APENAS)
                // Verifica se o usuário logado é o Gerente
                if ("Gerente".equals(lblCargo.getText())) {
                    int op = JOptionPane.showConfirmDialog(null, "Contingência! Deseja imprimir essa Solicitação?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                    if (op == JOptionPane.YES_OPTION) {
                        ReportUtil rpu = new ReportUtil();
                        HashMap parametro = new HashMap();
                        parametro.put("id", idProdutoCozinha);
                        try {
                            rpu.imprimiRelatorioTela("contigencia_3.jasper", parametro);
                        } catch (JRException e) {
                            System.out.println("br.com.bar.view.TelaConzinha.lblPrepararMouseClicked()" + e);
                            JOptionPane.showMessageDialog(null, "Erro ao tentar imprimir Solicitação - contate o SUPORTE!");
                        }
                    }
                }
            }
        }

    }//GEN-LAST:event_lblPrepararMouseClicked

    private void lblREmovePratoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblREmovePratoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblREmovePratoKeyPressed

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblLiberaRefeicao;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPreparar;
    private javax.swing.JLabel lblREmovePrato;
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

}
