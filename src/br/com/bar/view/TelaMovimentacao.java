/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.Produto;
import br.com.br.controler.ControlerEstoque;
import br.com.br.controler.ControlerLog;
import br.com.br.controler.ControlerProduto;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaMovimentacao extends javax.swing.JFrame {

    /**
     * Creates new form TelaMovimentacao
     */
    ControlerProduto controlProduto = new ControlerProduto();
    ControlerEstoque est = new ControlerEstoque();
    Log l = new Log();

    public TelaMovimentacao() {
        initComponents();
        panelProdutos.setVisible(false);
        est.carregaComboOperacao(comboOperacao);
        Date dataAtual = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        lblData.setText(df.format(dataAtual));
        txtIdProduto.setVisible(false);
        txtIdOperacao.setVisible(false);
    }

    public void recebeOperador(String operador, String cargo) {

        lblOperador.setText(operador);
        lblCargo.setText(cargo);

    }

    private void limpaTela() {
        txtQuantidade.setText(null);
        txtPesquisar.setText(null);

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
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        radioProdutoNovo = new javax.swing.JRadioButton();
        panelProdutos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        txtPesquisar = new javax.swing.JTextField();
        txtIdProduto = new javax.swing.JTextField();
        lblPesquisa = new javax.swing.JLabel();
        blbIncluir = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaObservacao = new javax.swing.JTextArea();
        txtIdOperacao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        comboOperacao = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        radioPesquisaNome = new javax.swing.JRadioButton();
        radioPesquisaCodigo = new javax.swing.JRadioButton();
        painelTopo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblDica = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de Produto")));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Novo");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioProdutoNovo);
        radioProdutoNovo.setText("Existente");
        radioProdutoNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioProdutoNovoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(radioProdutoNovo)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(radioProdutoNovo))
                .addGap(36, 36, 36))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(20, 140, 262, 80);

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProduto.setRowHeight(22);
        tblProduto.setSelectionBackground(new java.awt.Color(255, 51, 0));
        tblProduto.setSelectionForeground(new java.awt.Color(204, 204, 0));
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProduto);

        jLabel6.setText("Quantidade");

        txtQuantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtQuantidadeFocusGained(evt);
            }
        });

        txtPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPesquisarMouseClicked(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        lblPesquisa.setText("Pesquisar");

        blbIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionas32x32.png"))); // NOI18N
        blbIncluir.setText("Incluir");
        blbIncluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                blbIncluirMouseClicked(evt);
            }
        });

        txtAreaObservacao.setColumns(20);
        txtAreaObservacao.setLineWrap(true);
        txtAreaObservacao.setRows(5);
        jScrollPane1.setViewportView(txtAreaObservacao);

        jLabel7.setText("Observação");

        javax.swing.GroupLayout panelProdutosLayout = new javax.swing.GroupLayout(panelProdutos);
        panelProdutos.setLayout(panelProdutosLayout);
        panelProdutosLayout.setHorizontalGroup(
            panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProdutosLayout.createSequentialGroup()
                        .addComponent(lblPesquisa)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdutosLayout.createSequentialGroup()
                        .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProdutosLayout.createSequentialGroup()
                                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelProdutosLayout.createSequentialGroup()
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blbIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6))
                        .addGap(88, 88, 88))))
        );
        panelProdutosLayout.setVerticalGroup(
            panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdutosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(panelProdutosLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(blbIncluir, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelProdutos);
        panelProdutos.setBounds(10, 240, 830, 320);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Operação")));

        comboOperacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboOperacaoItemStateChanged(evt);
            }
        });
        comboOperacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOperacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(470, 140, 170, 80);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filtrar por")));

        buttonGroup2.add(radioPesquisaNome);
        radioPesquisaNome.setText("Nome");
        radioPesquisaNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioPesquisaNomeMouseClicked(evt);
            }
        });
        radioPesquisaNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPesquisaNomeActionPerformed(evt);
            }
        });

        buttonGroup2.add(radioPesquisaCodigo);
        radioPesquisaCodigo.setText("Código");
        radioPesquisaCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioPesquisaCodigoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioPesquisaNome)
                    .addComponent(radioPesquisaCodigo))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioPesquisaNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioPesquisaCodigo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(330, 140, 100, 80);

        painelTopo.setBackground(new java.awt.Color(243, 156, 18));
        painelTopo.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        jLabel4.setText("Gerenciamento Estoque");
        painelTopo.add(jLabel4);
        jLabel4.setBounds(90, 10, 525, 64);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/produto.png"))); // NOI18N
        painelTopo.add(jLabel9);
        jLabel9.setBounds(30, 30, 32, 32);

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("Operador");
        painelTopo.add(lblOperador);
        lblOperador.setBounds(440, 90, 90, 30);

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("Cargo");
        painelTopo.add(lblCargo);
        lblCargo.setBounds(560, 90, 110, 30);

        jPanel4.setBackground(new java.awt.Color(38, 53, 91));

        btnFechar.setBackground(new java.awt.Color(38, 53, 91));
        btnFechar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnFechar.setForeground(new java.awt.Color(255, 255, 255));
        btnFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFechar.setText("X");
        btnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelTopo.add(jPanel4);
        jPanel4.setBounds(800, 0, 40, 41);

        lblData.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/calendario24x24.png"))); // NOI18N
        lblData.setText("jLabel9");
        painelTopo.add(lblData);
        lblData.setBounds(670, 90, 140, 30);

        getContentPane().add(painelTopo);
        painelTopo.setBounds(0, 0, 840, 130);

        lblDica.setText("Dica:");
        getContentPane().add(lblDica);
        lblDica.setBounds(470, 220, 360, 14);

        setSize(new java.awt.Dimension(841, 560));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void radioProdutoNovoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioProdutoNovoMouseClicked

        if (radioProdutoNovo.isSelected()) {
           
            panelProdutos.setVisible(true);
            tblProduto.setModel(DbUtils.resultSetToTableModel(controlProduto.listaEquantidade()));
        }


    }//GEN-LAST:event_radioProdutoNovoMouseClicked

    private void btnFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseClicked
        // Fecha a janela
        dispose();
    }//GEN-LAST:event_btnFecharMouseClicked

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        // Chama a tela de cadastro de Produtos

        panelProdutos.setVisible(false);
        TelaCadastroProduto cp = new TelaCadastroProduto();
        cp.recebeOperador(lblOperador.getText(), lblCargo.getText());
        cp.setVisible(true);
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void radioPesquisaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPesquisaNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioPesquisaNomeActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        // Seleciona dados da tabela

        int linha = tblProduto.getSelectedRow();
        try {
            txtIdProduto.setText(tblProduto.getModel().getValueAt(linha, 0).toString());

        } catch (ArrayIndexOutOfBoundsException e) {          

        }
        txtIdProduto.setText(tblProduto.getModel().getValueAt(linha, 0).toString());
        txtQuantidade.requestFocus();
        txtIdOperacao.setText(est.localizaIdOperacao(comboOperacao.getSelectedItem().toString()));
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void radioPesquisaNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioPesquisaNomeMouseClicked
        lblPesquisa.setText("Pesquisar por nome:");


    }//GEN-LAST:event_radioPesquisaNomeMouseClicked

    private void radioPesquisaCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioPesquisaCodigoMouseClicked
        lblPesquisa.setText("Pesquisar por código:");
    }//GEN-LAST:event_radioPesquisaCodigoMouseClicked

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed

        if (radioPesquisaNome.isSelected()) {

            tblProduto.setModel(DbUtils.resultSetToTableModel(est.pesquisarProduto("nome", txtPesquisar.getText())));
        } else {
            tblProduto.setModel(DbUtils.resultSetToTableModel(est.pesquisarProduto("id", txtPesquisar.getText())));

        }

    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void txtPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPesquisarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarMouseClicked

    private void comboOperacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOperacaoActionPerformed
        
        String op = comboOperacao.getSelectedItem().toString();
        
        switch(op){
            case "Entrada":
                lblDica.setText("Utilize quando receber produto do fornecedor.");
            break;
            case "Saída":
                lblDica.setText("Utilize para retirada direta do estoque.");
            break;
            case "Devolução":
                lblDica.setText("Devolve produto ao forcedor.");
            break;
            case "Descarte":
                lblDica.setText("Utilize quando não for possível o reaproveitamento.");
            break;
            
        }
            
    }//GEN-LAST:event_comboOperacaoActionPerformed

    private void comboOperacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboOperacaoItemStateChanged

    }//GEN-LAST:event_comboOperacaoItemStateChanged

    private void blbIncluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blbIncluirMouseClicked
        txtIdOperacao.setText(est.localizaIdOperacao(comboOperacao.getSelectedItem().toString()));

        String operacao = comboOperacao.getSelectedItem().toString();

        Produto p = new Produto();

        switch (operacao) {
            // Realiza a entrada de um produto já cadastrado
            case "Entrada":

                if (est.registraMovimentacao(txtIdProduto.getText(), txtQuantidade.getText(), txtIdOperacao.getText(), txtAreaObservacao.getText() )) {
                    // Adiciona quantidae no estoque
                    if (est.entradaDeProduto(txtIdProduto.getText(), txtQuantidade.getText())) {
                        JOptionPane.showMessageDialog(null, "Produto Adicionado com sucesso");
                        limpaTela();
                        tblProduto.setModel(DbUtils.resultSetToTableModel(controlProduto.listaEquantidade()));

                        // Início do Registro de Log
                        l.setFuncionalidade(comboOperacao.getSelectedItem().toString());
                        l.setUsuario(lblOperador.getText());
                        l.setDescricao(l.getUsuario() + " realizou uma " + l.getFuncionalidade() + "Produto-> " + txtIdProduto.getText() + " Motivo : " + txtAreaObservacao.getText());
                        l.gravaLog(l);
                        // Fim do Registro de Lg

                        // limpa campos
                        txtAreaObservacao.setText(null);
                        txtQuantidade.setText(null);
                        txtIdProduto.setText(null);
                        txtIdOperacao.setText(null);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Valor Inválido");
                }
                break;

            case "Saída": // retira um prpoduto do estoque

                p.setId(txtIdProduto.getText());
                p.setQtd(txtQuantidade.getText());

                // Retira o produto do estoque
                est.retiraEstoque(p, p.getQtd());
                // Registra movimentação
                if (est.registraMovimentacao(p.getId(), p.getQtd(), est.localizaIdOperacao("Saída"), txtAreaObservacao.getText())) {

                    JOptionPane.showMessageDialog(null, "Produto retirado do estoque com sucesso");

                    tblProduto.setModel(DbUtils.resultSetToTableModel(controlProduto.listaEquantidade()));
                    txtQuantidade.setText(null);
                    txtAreaObservacao.setText(null);

                    // Início do Registro de Log
                    l.setFuncionalidade(comboOperacao.getSelectedItem().toString());
                    l.setUsuario(lblOperador.getText());
                    l.setDescricao(l.getUsuario() + " realizou uma " + l.getFuncionalidade() + "Produto-> " + txtIdProduto.getText() + " Motivo : " + txtAreaObservacao.getText());
                    l.gravaLog(l);
                    // Fim do Registro de Log
                    // limpa campos
                    txtAreaObservacao.setText(null);
                    txtQuantidade.setText(null);
                    txtIdProduto.setText(null);
                    txtIdOperacao.setText(null);
                }
                break;

            case "Devolução":// Devolve o produto ao fornecedor gerando uma saída no estoque
                // Exibe painel
                panelProdutos.setVisible(true);
                

                Produto produto = new Produto();
                produto.setId(txtIdProduto.getText());
                produto.setQtd(txtQuantidade.getText());
                int op= JOptionPane.showConfirmDialog(null, "Devolver produto ao fornecedor?","Atenção!",JOptionPane.YES_NO_OPTION);
                // Solicita confirmação ao operador
                if (op==JOptionPane.YES_OPTION) {
                    
                    //Retira do estoque o produto 
                    est.retiraEstoque(produto, produto.getQtd());
                    JOptionPane.showMessageDialog(null, "Devolução de produto realizada com sucesso!");
                    est.registraMovimentacao(produto.getId(), produto.getQtd(), est.localizaIdOperacao(operacao), txtAreaObservacao.getText());
                    tblProduto.setModel(DbUtils.resultSetToTableModel(controlProduto.listaEquantidade()));

                    // Início do Registro de Log
                    l.setFuncionalidade(comboOperacao.getSelectedItem().toString());
                    l.setUsuario(lblOperador.getText());
                    l.setDescricao(l.getUsuario() + " realizou uma " + l.getFuncionalidade() + "Produto-> " + txtIdProduto.getText() + " Motivo : " + txtAreaObservacao.getText());
                    l.gravaLog(l);

                    // Fim do Registro de Log 
                    // limpa campos
                    txtAreaObservacao.setText(null);
                    txtQuantidade.setText(null);
                    txtIdProduto.setText(null);
                    txtIdOperacao.setText(null);

                }else {
                    JOptionPane.showMessageDialog(null, "Operação cancelada!");                    
                }

                break;
                
            case "Descarte":// Retira o produto o estoque
                // Exibe painel
                panelProdutos.setVisible(true);               

                Produto pDescarte = new Produto();
                pDescarte.setId(txtIdProduto.getText());
                pDescarte.setQtd(txtQuantidade.getText());
                int confirma= JOptionPane.showConfirmDialog(null, "Descartar este produto?","Atenção!",JOptionPane.YES_NO_OPTION);
                // Solicita confirmação ao operador
                if (confirma==JOptionPane.YES_OPTION) {
                    
                    //Retira do estoque o produto 
                    est.retiraEstoque(pDescarte, pDescarte.getQtd());
                    JOptionPane.showMessageDialog(null, "O produto foi descartado!");
                    est.registraMovimentacao(pDescarte.getId(), pDescarte.getQtd(), est.localizaIdOperacao(operacao), txtAreaObservacao.getText());
                    tblProduto.setModel(DbUtils.resultSetToTableModel(controlProduto.listaEquantidade()));

                    // Início do Registro de Log
                    l.setFuncionalidade(comboOperacao.getSelectedItem().toString());
                    l.setUsuario(lblOperador.getText());
                    l.setDescricao(l.getUsuario() + " realizou uma " + l.getFuncionalidade() + "Produto-> " + txtIdProduto.getText() + " Motivo : " + txtAreaObservacao.getText());
                    l.gravaLog(l);

                    // Fim do Registro de Log 
                    // limpa campos
                    txtAreaObservacao.setText(null);
                    txtQuantidade.setText(null);
                    txtIdProduto.setText(null);
                    txtIdOperacao.setText(null);

                }else {
                    JOptionPane.showMessageDialog(null, "Operação cancelada!");                    
                }

                break;
        }


    }//GEN-LAST:event_blbIncluirMouseClicked

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void txtQuantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantidadeFocusGained
              txtAreaObservacao.setText(comboOperacao.getSelectedItem().toString() +" - "+ lblOperador.getText() +": ");            
    }//GEN-LAST:event_txtQuantidadeFocusGained

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
            java.util.logging.Logger.getLogger(TelaMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMovimentacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel blbIncluir;
    private javax.swing.JLabel btnFechar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> comboOperacao;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDica;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JPanel painelTopo;
    private javax.swing.JPanel panelProdutos;
    private javax.swing.JRadioButton radioPesquisaCodigo;
    private javax.swing.JRadioButton radioPesquisaNome;
    private javax.swing.JRadioButton radioProdutoNovo;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextArea txtAreaObservacao;
    private javax.swing.JTextField txtIdOperacao;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
