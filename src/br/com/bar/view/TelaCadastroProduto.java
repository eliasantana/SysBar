/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.Fornecedor;
import br.com.bar.model.Produto;
import br.com.br.controler.ControlerEstoque;
import br.com.br.controler.ControlerFornecedor;
import br.com.br.controler.ControlerGrupo;
import br.com.br.controler.ControlerProduto;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaCadastroProduto extends javax.swing.JFrame {
    
    ControlerProduto cp = new ControlerProduto();
    ControlerGrupo g = new ControlerGrupo();
    ControlerEstoque e = new ControlerEstoque();
    ControlerFornecedor cf = new ControlerFornecedor();
    
    Log l = new Log();

    /**
     * Creates new form TelaCadastroProduto
     */
    public TelaCadastroProduto() {
        initComponents();
        
        txtIdGrupo.setVisible(false);
        txtIdProduto.setVisible(false);
        txtIdFor.setVisible(false);
        g.carregaComboGrupoProduto(comboGrupoProduto);
        tblProduto.setModel(DbUtils.resultSetToTableModel(cp.listaProduto()));
        cf.carregaComboFornecedor(comboFornecedor);
        if ("Gerente".equals(lblCargo.getText())) {
            btnAdicionarFornecedor.setEnabled(true);
        } else {
            btnAdicionarFornecedor.setEnabled(false);
        }
        
    }
    
    public void recebeOperador(String operador, String cargo) {
        
        lblCargo.setText(cargo);
        lblOperador.setText(operador);
        // Adiciona nome do operador ao log
        l.setUsuario(lblOperador.getText());
        
        if ("Gerente".equals(lblCargo.getText())) {
            btnAdicionarFornecedor.setVisible(true);
        } else {
            btnAdicionarFornecedor.setVisible(false);
        }
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
        painelEsquerdo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        txtQuantidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtQtdMin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtQtdMax = new javax.swing.JTextField();
        comboGrupoProduto = new javax.swing.JComboBox<>();
        btnAdicionarFornecedor = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboFornecedor = new javax.swing.JComboBox<>();
        btnAdicionarGrupo1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        lblRotulo = new javax.swing.JLabel();
        txtLocalizar = new javax.swing.JTextField();
        radioNome = new javax.swing.JRadioButton();
        radioGrupo = new javax.swing.JRadioButton();
        btnFiltrar = new javax.swing.JButton();
        txtIdGrupo = new javax.swing.JTextField();
        txtIdProduto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnExcluir = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JLabel();
        btnAlteraProduto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        txtIdFor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        painelEsquerdo.setBackground(new java.awt.Color(38, 53, 61));
        painelEsquerdo.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/produto128x128.png"))); // NOI18N
        painelEsquerdo.add(jLabel1);
        jLabel1.setBounds(130, 10, 130, 130);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cadastro de Produto");
        painelEsquerdo.add(jLabel2);
        jLabel2.setBounds(30, 140, 330, 40);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Descrição do Produto");
        painelEsquerdo.add(jLabel3);
        jLabel3.setBounds(20, 190, 220, 30);

        txtDescricao.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtDescricao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDescricaoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtDescricaoMouseEntered(evt);
            }
        });
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyPressed(evt);
            }
        });
        painelEsquerdo.add(txtDescricao);
        txtDescricao.setBounds(20, 220, 330, 40);

        txtQuantidade.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyPressed(evt);
            }
        });
        painelEsquerdo.add(txtQuantidade);
        txtQuantidade.setBounds(20, 300, 130, 40);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantidade");
        painelEsquerdo.add(jLabel4);
        jLabel4.setBounds(20, 270, 130, 30);

        txtValor.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorKeyPressed(evt);
            }
        });
        painelEsquerdo.add(txtValor);
        txtValor.setBounds(220, 300, 130, 40);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Valor");
        painelEsquerdo.add(jLabel5);
        jLabel5.setBounds(220, 270, 130, 30);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fornecedores");
        painelEsquerdo.add(jLabel6);
        jLabel6.setBounds(20, 480, 170, 30);

        txtQtdMin.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtQtdMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtdMinKeyPressed(evt);
            }
        });
        painelEsquerdo.add(txtQtdMin);
        txtQtdMin.setBounds(20, 370, 130, 40);

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Quantidade Máxima");
        painelEsquerdo.add(jLabel7);
        jLabel7.setBounds(220, 340, 170, 30);

        txtQtdMax.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        painelEsquerdo.add(txtQtdMax);
        txtQtdMax.setBounds(220, 370, 130, 40);

        comboGrupoProduto.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboGrupoProduto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboGrupoProdutoItemStateChanged(evt);
            }
        });
        comboGrupoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGrupoProdutoActionPerformed(evt);
            }
        });
        painelEsquerdo.add(comboGrupoProduto);
        comboGrupoProduto.setBounds(20, 440, 200, 40);

        btnAdicionarFornecedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdicionarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionar.png"))); // NOI18N
        btnAdicionarFornecedor.setToolTipText("Adicionar Novo Grupo");
        btnAdicionarFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdicionarFornecedorMouseClicked(evt);
            }
        });
        painelEsquerdo.add(btnAdicionarFornecedor);
        btnAdicionarFornecedor.setBounds(230, 510, 50, 40);

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Quantidade Minima");
        painelEsquerdo.add(jLabel9);
        jLabel9.setBounds(20, 340, 170, 30);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tipo");
        painelEsquerdo.add(jLabel11);
        jLabel11.setBounds(20, 410, 170, 30);

        comboFornecedor.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        comboFornecedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFornecedorItemStateChanged(evt);
            }
        });
        comboFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFornecedorActionPerformed(evt);
            }
        });
        painelEsquerdo.add(comboFornecedor);
        comboFornecedor.setBounds(20, 510, 200, 40);

        btnAdicionarGrupo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdicionarGrupo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionar.png"))); // NOI18N
        btnAdicionarGrupo1.setToolTipText("Adicionar Novo Grupo");
        btnAdicionarGrupo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdicionarGrupo1MouseClicked(evt);
            }
        });
        painelEsquerdo.add(btnAdicionarGrupo1);
        btnAdicionarGrupo1.setBounds(230, 440, 50, 40);

        getContentPane().add(painelEsquerdo);
        painelEsquerdo.setBounds(0, 0, 420, 570);

        tblProduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProduto.setRowHeight(22);
        tblProduto.setShowHorizontalLines(false);
        tblProduto.setShowVerticalLines(false);
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        lblRotulo.setText("Nome do Produto");

        txtLocalizar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtLocalizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalizarKeyReleased(evt);
            }
        });

        buttonGroup1.add(radioNome);
        radioNome.setText("Nome");
        radioNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioNomeMouseClicked(evt);
            }
        });
        radioNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomeActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioGrupo);
        radioGrupo.setText("Grupo");
        radioGrupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioGrupoMouseClicked(evt);
            }
        });

        btnFiltrar.setText("Listar por Fornedcedor");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRotulo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radioNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioGrupo)
                                .addGap(29, 29, 29)
                                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblRotulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioNome)
                        .addComponent(radioGrupo)
                        .addComponent(btnFiltrar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(420, 90, 640, 360);
        getContentPane().add(txtIdGrupo);
        txtIdGrupo.setBounds(430, 40, 60, 30);
        getContentPane().add(txtIdProduto);
        txtIdProduto.setBounds(500, 40, 80, 30);

        jPanel2.setLayout(null);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExcluirMouseClicked(evt);
            }
        });
        jPanel2.add(btnExcluir);
        btnExcluir.setBounds(400, 20, 100, 40);

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/limparCinza.png"))); // NOI18N
        jLabel10.setText("Limpar");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel10);
        jLabel10.setBounds(20, 14, 90, 50);

        btnSalvar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/SalvarMesas.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalvarMouseClicked(evt);
            }
        });
        jPanel2.add(btnSalvar);
        btnSalvar.setBounds(150, 20, 110, 40);

        btnAlteraProduto.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        btnAlteraProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lapisCinza.png"))); // NOI18N
        btnAlteraProduto.setText("Alterar");
        btnAlteraProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlteraProdutoMouseClicked(evt);
            }
        });
        jPanel2.add(btnAlteraProduto);
        btnAlteraProduto.setBounds(290, 20, 110, 40);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(430, 450, 620, 70);

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Cargo:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(800, 60, 70, 20);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Operador:");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(800, 30, 70, 20);

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblOperador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOperador.setText("Operador:");
        getContentPane().add(lblOperador);
        lblOperador.setBounds(880, 30, 70, 20);

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCargo.setText("Cargo:");
        getContentPane().add(lblCargo);
        lblCargo.setBounds(880, 60, 70, 20);
        getContentPane().add(txtIdFor);
        txtIdFor.setBounds(590, 40, 100, 30);

        jPanel3.setBackground(new java.awt.Color(38, 53, 61));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("x");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(1020, 0, 40, 40);

        setSize(new java.awt.Dimension(1058, 570));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void txtDescricaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDescricaoMouseEntered
        // Vai para o próximo campo

    }//GEN-LAST:event_txtDescricaoMouseEntered

    private void txtDescricaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDescricaoMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtDescricaoMouseClicked

    private void txtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtQuantidade.requestFocus();
        }
    }//GEN-LAST:event_txtDescricaoKeyPressed

    private void txtQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyPressed
        // Vai para o campo valor

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtValor.requestFocus();
        }
    }//GEN-LAST:event_txtQuantidadeKeyPressed

    private void txtValorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyPressed
        // Vai para quantidade mínima
        String valor = txtValor.getText();
        String novovalor = valor.replaceAll(",", ".");
        txtValor.setText(novovalor);
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtQtdMin.requestFocus();
        }
    }//GEN-LAST:event_txtValorKeyPressed

    private void txtQtdMinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdMinKeyPressed
        // Vai para Quantidade Máxima
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtQtdMax.requestFocus();
            txtQtdMax.setText(txtQuantidade.getText());
        }
    }//GEN-LAST:event_txtQtdMinKeyPressed

    private void comboGrupoProdutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboGrupoProdutoItemStateChanged
        

    }//GEN-LAST:event_comboGrupoProdutoItemStateChanged

    private void comboGrupoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGrupoProdutoActionPerformed
        

    }//GEN-LAST:event_comboGrupoProdutoActionPerformed

    private void btnSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseClicked
        // Salva um produto

        Fornecedor f = new Fornecedor();
        f.setNome(comboFornecedor.getSelectedItem().toString());
        
        Produto p = new Produto();
        p.setNome(txtDescricao.getText());
        p.setQtd(txtQuantidade.getText());
        p.setQtdMax(txtQtdMax.getText());
        p.setQtdMin(txtQtdMin.getText());
        p.setValor(txtValor.getText());
        p.setIdFornecedor(Integer.parseInt(cf.localizaForecedor(f)));
        
        p.setTbGrupoId(g.localizaIdGrupoProduto(comboGrupoProduto));
        
        if (p.getValor() == null) {
            JOptionPane.showMessageDialog(null, "O produto não pode ser adicionado");
        } else {
            if (cp.adicionaProduto(p)) {
                JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
                tblProduto.setModel(DbUtils.resultSetToTableModel(cp.listaProduto()));

                // Início do Registro de Log
                l.setDescricao(lblOperador.getText() + " adicionou um produto " + p.getNome() + " ao estoque");
                l.setFuncionalidade("Salvar");
                l.gravaLog(l);
                // Fim do Log

                // Localiza id do produto adicionado             
                String id = e.localizaIdProduto(p.getNome());
                // Registra movimentação
                e.registraMovimentacao(id, p.getQtd(), "1", "Produto Novo");
                
            }
            
        }

    }//GEN-LAST:event_btnSalvarMouseClicked

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        // Seleciona dados da tabela
        int linha = tblProduto.getSelectedRow();
        txtIdProduto.setText(tblProduto.getModel().getValueAt(linha, 0).toString());
        txtDescricao.setText(tblProduto.getModel().getValueAt(linha, 1).toString());
        txtQuantidade.setText(tblProduto.getModel().getValueAt(linha, 2).toString());
        txtValor.setText(tblProduto.getModel().getValueAt(linha, 3).toString());
        txtQtdMax.setText(tblProduto.getModel().getValueAt(linha, 4).toString());
        txtQtdMin.setText(tblProduto.getModel().getValueAt(linha, 5).toString());
        comboGrupoProduto.setSelectedItem(tblProduto.getModel().getValueAt(linha, 6).toString());
        
        txtIdGrupo.setText(g.localizaIdGrupoProduto(comboGrupoProduto));
        

    }//GEN-LAST:event_tblProdutoMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // limpa formulário
        limpaForm();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // Localiza produto
        String opcao = null;
        
        if (radioNome.isSelected()) {
            opcao = "Nome";
            
        } else if (radioGrupo.isSelected()) {
            opcao = "Grupo";
            
        } else {
            opcao = "Nome";
            
        }
        tblProduto.setModel(DbUtils.resultSetToTableModel(cp.filtrarProduto(txtLocalizar.getText(), opcao)));

    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void radioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNomeActionPerformed

    private void radioNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioNomeMouseClicked
        // Seleciona caixa de texto localizar e adiciona o ponteiro do mouse
        txtLocalizar.setText(null);
        txtLocalizar.requestFocus();
        lblRotulo.setText("Nome do Produto");
    }//GEN-LAST:event_radioNomeMouseClicked

    private void radioGrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioGrupoMouseClicked
        // Seleciona caixa de texto localizar e adiciona o ponteiro do mouse
        txtLocalizar.setText(null);
        txtLocalizar.requestFocus();
        lblRotulo.setText("Nome do Grupo");
    }//GEN-LAST:event_radioGrupoMouseClicked

    private void btnAlteraProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlteraProdutoMouseClicked
        
        Fornecedor f = new Fornecedor();
        f.setNome(comboFornecedor.getSelectedItem().toString());
        f.setCodigo(Integer.parseInt(cf.localizaForecedor(f)));
        
        Produto p = new Produto();
        
        p.setId(txtIdProduto.getText());
        p.setNome(txtDescricao.getText());
        p.setQtd(txtQuantidade.getText());
        p.setValor(txtValor.getText());
        p.setQtdMax(txtQtdMax.getText());
        p.setQtdMin(txtQtdMin.getText());
        p.setTbGrupoId(txtIdGrupo.getText());
        p.setIdFornecedor(f.getCodigo());
        
        int op = JOptionPane.showConfirmDialog(null, "Confirma alteração?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {
            
            if (cp.alteraProduto(p)) {
                JOptionPane.showMessageDialog(null, "Produto Adicionado com sucesso");
                tblProduto.setModel(DbUtils.resultSetToTableModel(cp.listaProduto()));
                //Registra log
                l.setFuncionalidade("Alterar");
                l.setDescricao(l.getUsuario() + " alterou o produto " + txtDescricao.getText());
                l.gravaLog(l);
                
            } else {
                JOptionPane.showMessageDialog(null, "Adição cancelada");
                
            }
        }

    }//GEN-LAST:event_btnAlteraProdutoMouseClicked

    private void btnAdicionarFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarFornecedorMouseClicked
        // Chama o cadastro de Grupos
        TelaGruposProdutos gp = new TelaGruposProdutos();
        gp.recebeOperador(lblOperador.getText());
        gp.setVisible(true);
    }//GEN-LAST:event_btnAdicionarFornecedorMouseClicked

    private void btnExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseClicked
        // Exclui um produto
        Produto p = new Produto();
        p.setId(txtIdProduto.getText());
        p.setNome(txtDescricao.getText());
        int op = JOptionPane.showConfirmDialog(null, "Deseja Excluir este produto? \n" + p.getNome(), "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {
            if (cp.excluiProduto(p)) {

                // Registra exlusão no log
                Log log = new Log();
                log.setFuncionalidade("Excluir");
                log.setDescricao(l.getUsuario() + " Excluiu o produto " + p.getNome() + " do estoque");
                log.gravaLog(log);
                
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
                tblProduto.setModel(DbUtils.resultSetToTableModel(cp.listaProduto()));
            }
        }

    }//GEN-LAST:event_btnExcluirMouseClicked

    private void btnAdicionarGrupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarGrupo1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarGrupo1MouseClicked

    private void comboFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFornecedorActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_comboFornecedorActionPerformed

    private void comboFornecedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFornecedorItemStateChanged
        
    }//GEN-LAST:event_comboFornecedorItemStateChanged

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        
        if ("Limpar".equals(btnFiltrar.getText())) {
            tblProduto.setModel(DbUtils.resultSetToTableModel(cp.listaProduto()));
            btnFiltrar.setText("Listar por Fornedcedor");
        } else {
            
            Fornecedor f = new Fornecedor();
            f.setNome(comboFornecedor.getSelectedItem().toString());
            f.setCodigo(Integer.parseInt(cf.localizaForecedor(f)));
            // Lista os produtos do fornecedor selecionado na combobox
            tblProduto.setModel(DbUtils.resultSetToTableModel(cp.listaProduto(f)));
            btnFiltrar.setText("Limpar");
        }
        
    }//GEN-LAST:event_btnFiltrarActionPerformed
    private void limpaForm() {
        
        txtDescricao.setText(null);
        txtIdGrupo.setText(null);
        txtIdProduto.setText(null);
        txtLocalizar.setText(null);
        txtQuantidade.setText(null);
        txtQtdMax.setText(null);
        txtQtdMin.setText(null);
        txtValor.setText(null);
        
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
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAdicionarFornecedor;
    private javax.swing.JLabel btnAdicionarGrupo1;
    private javax.swing.JLabel btnAlteraProduto;
    private javax.swing.JLabel btnExcluir;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JLabel btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboFornecedor;
    private javax.swing.JComboBox<String> comboGrupoProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblRotulo;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JRadioButton radioGrupo;
    private javax.swing.JRadioButton radioNome;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtIdFor;
    private javax.swing.JTextField txtIdGrupo;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtLocalizar;
    private javax.swing.JTextField txtQtdMax;
    private javax.swing.JTextField txtQtdMin;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

}
