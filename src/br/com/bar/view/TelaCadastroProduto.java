/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.Fornecedor;
import br.com.bar.model.Produto;
import br.com.bar.model.TableModelCadastroProduto;
import br.com.bar.util.FormataValor;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerEstoque;
import br.com.br.controler.ControlerFornecedor;
import br.com.br.controler.ControlerGrupo;
import br.com.br.controler.ControlerProduto;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class TelaCadastroProduto extends javax.swing.JFrame {

    ControlerProduto cp = new ControlerProduto();
    ControlerGrupo g = new ControlerGrupo();
    ControlerEstoque e = new ControlerEstoque();
    ControlerFornecedor cf = new ControlerFornecedor();
    Util u = new Util();
    TableModelCadastroProduto modelCadastroProduto = new TableModelCadastroProduto();
    Log l = new Log();
    TelaPesquisaProduto telaPesquisa;
    Produto produto = new Produto();
    Object[] opcao = {"   Não   ", "   Sim   "};

    /**
     * Creates new form TelaCadastroProduto
     */
    public TelaCadastroProduto() {
        initComponents();

        g.carregaComboGrupoProduto(comboGrupoProduto);

        cf.carregaComboFornecedor(comboFornecedor);
        if ("Gerente".equals(lblCargo.getText())) {
            btnAdicionarFornecedor.setVisible(true);
            btnAdicionarGrupo.setVisible(true);
        } else {
            btnAdicionarFornecedor.setVisible(false);
            btnAdicionarGrupo.setVisible(false);
        }

        lblCargo.setVisible(false);
        lblOperador.setVisible(false);

    }

    public void recebeOperador(String operador, String cargo, String titulo) {

        lblCargo.setText(cargo);
        lblOperador.setText(operador);
        // Adiciona nome do operador ao log
        l.setUsuario(lblOperador.getText());
        lblTitulo.setText(titulo);
        if ("Gerente".equals(lblCargo.getText())) {
            btnAdicionarFornecedor.setVisible(true);
            btnAdicionarGrupo.setVisible(true);
        } else {
            btnAdicionarFornecedor.setVisible(false);
        }

        if ("Alterar".equals(titulo)) {
            bloqueiaCampos();
        }

    }

    public void recebeProduto(TelaPesquisaProduto tela, Produto p) {
        //Localiza o codigo interno e adiciona ao objeto produto

        txtDescricao.setText(p.getNome());
        txtQtdMin.setText(p.getQtdMin());
        txtQtdMax.setText(p.getQtdMax());
        txtQuantidade.setText(p.getQtd());
        txtValor.setText(p.getValor());
        comboFornecedor.setSelectedIndex(0);
        comboGrupoProduto.setSelectedIndex(0);
        txtCodigoNcm.setText(p.getCodNCM());
        txtCodigoProduto.setText(p.getCodigoProduto());
        this.telaPesquisa = tela;
        this.produto = p;
        comboGrupoProduto.setSelectedItem(produto.getTbGrupoId());
        comboFornecedor.setSelectedItem(cf.retornaFornecedor(p));

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
        bordas = new javax.swing.JPanel();
        painelEsquerdo = new javax.swing.JPanel();
        lblCodigoNcm = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblqtd = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        lblFornecedor = new javax.swing.JLabel();
        lblQtdMax = new javax.swing.JLabel();
        comboGrupoProduto = new javax.swing.JComboBox<>();
        btnAdicionarFornecedor = new javax.swing.JLabel();
        lblQtdMin = new javax.swing.JLabel();
        lblGrupo = new javax.swing.JLabel();
        comboFornecedor = new javax.swing.JComboBox<>();
        btnAdicionarGrupo = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JLabel();
        lblMensagem = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JFormattedTextField();
        txtQtdMin = new javax.swing.JFormattedTextField();
        txtQtdMax = new javax.swing.JFormattedTextField();
        txtCodigoNcm = new javax.swing.JFormattedTextField();
        lblDescricao1 = new javax.swing.JLabel();
        lblCodigoProduto = new javax.swing.JLabel();
        txtCodigoProduto = new javax.swing.JFormattedTextField();
        panelSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        bordas.setBackground(new java.awt.Color(204, 204, 204));
        bordas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bordas.setLayout(null);

        painelEsquerdo.setLayout(null);

        lblCodigoNcm.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblCodigoNcm.setText("Cód. NCM");
        painelEsquerdo.add(lblCodigoNcm);
        lblCodigoNcm.setBounds(20, 0, 110, 30);

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
        txtDescricao.setBounds(20, 100, 330, 40);

        lblqtd.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblqtd.setText("Quantidade");
        painelEsquerdo.add(lblqtd);
        lblqtd.setBounds(20, 140, 130, 30);

        txtValor.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorFocusGained(evt);
            }
        });
        txtValor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValorMouseClicked(evt);
            }
        });
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorKeyPressed(evt);
            }
        });
        painelEsquerdo.add(txtValor);
        txtValor.setBounds(220, 170, 130, 40);

        lblValor.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblValor.setText("Valor R$");
        painelEsquerdo.add(lblValor);
        lblValor.setBounds(220, 140, 130, 30);

        lblFornecedor.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblFornecedor.setText("Fornecedor");
        painelEsquerdo.add(lblFornecedor);
        lblFornecedor.setBounds(20, 345, 170, 20);

        lblQtdMax.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblQtdMax.setText("Quantidade Máxima");
        painelEsquerdo.add(lblQtdMax);
        lblQtdMax.setBounds(220, 210, 170, 30);

        comboGrupoProduto.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
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
        comboGrupoProduto.setBounds(20, 304, 200, 40);

        btnAdicionarFornecedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdicionarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionas32x32.png"))); // NOI18N
        btnAdicionarFornecedor.setToolTipText("Adicionar Novo Grupo");
        btnAdicionarFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdicionarFornecedorMouseClicked(evt);
            }
        });
        painelEsquerdo.add(btnAdicionarFornecedor);
        btnAdicionarFornecedor.setBounds(230, 370, 50, 40);

        lblQtdMin.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblQtdMin.setText("Quantidade Mínima");
        painelEsquerdo.add(lblQtdMin);
        lblQtdMin.setBounds(20, 210, 170, 30);

        lblGrupo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblGrupo.setText("Grupo ");
        painelEsquerdo.add(lblGrupo);
        lblGrupo.setBounds(20, 280, 170, 20);

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
        comboFornecedor.setBounds(20, 370, 200, 40);

        btnAdicionarGrupo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdicionarGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionas32x32.png"))); // NOI18N
        btnAdicionarGrupo.setToolTipText("Adicionar Novo Grupo");
        btnAdicionarGrupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdicionarGrupoMouseClicked(evt);
            }
        });
        painelEsquerdo.add(btnAdicionarGrupo);
        btnAdicionarGrupo.setBounds(230, 304, 50, 40);

        btnSalvar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/salvar32x32.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalvarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalvarMouseEntered(evt);
            }
        });
        btnSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnSalvarKeyReleased(evt);
            }
        });
        painelEsquerdo.add(btnSalvar);
        btnSalvar.setBounds(180, 430, 110, 50);

        lblMensagem.setForeground(new java.awt.Color(255, 0, 0));
        painelEsquerdo.add(lblMensagem);
        lblMensagem.setBounds(20, 415, 340, 20);

        txtQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQuantidade.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyPressed(evt);
            }
        });
        painelEsquerdo.add(txtQuantidade);
        txtQuantidade.setBounds(20, 170, 130, 40);

        txtQtdMin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQtdMin.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtQtdMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtdMinKeyPressed(evt);
            }
        });
        painelEsquerdo.add(txtQtdMin);
        txtQtdMin.setBounds(20, 240, 130, 40);

        txtQtdMax.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtQtdMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtdMaxKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtdMaxKeyReleased(evt);
            }
        });
        painelEsquerdo.add(txtQtdMax);
        txtQtdMax.setBounds(220, 240, 130, 40);

        txtCodigoNcm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCodigoNcm.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtCodigoNcm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoNcmKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoNcmKeyReleased(evt);
            }
        });
        painelEsquerdo.add(txtCodigoNcm);
        txtCodigoNcm.setBounds(20, 30, 130, 40);

        lblDescricao1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblDescricao1.setText("Descrição");
        painelEsquerdo.add(lblDescricao1);
        lblDescricao1.setBounds(20, 70, 220, 30);

        lblCodigoProduto.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblCodigoProduto.setText("Cód. Produto");
        painelEsquerdo.add(lblCodigoProduto);
        lblCodigoProduto.setBounds(220, 0, 110, 30);

        txtCodigoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCodigoProduto.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        txtCodigoProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoProdutoFocusLost(evt);
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
        painelEsquerdo.add(txtCodigoProduto);
        txtCodigoProduto.setBounds(220, 30, 130, 40);

        bordas.add(painelEsquerdo);
        painelEsquerdo.setBounds(2, 110, 435, 480);

        panelSuperior.setBackground(new java.awt.Color(243, 156, 18));
        panelSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/estoque.png"))); // NOI18N
        panelSuperior.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, 70));

        lblTitulo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        lblTitulo.setText("titulo");
        panelSuperior.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 200, 40));

        jPanel3.setBackground(new java.awt.Color(38, 53, 61));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
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

        panelSuperior.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 40, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel8.setText("Produto");
        panelSuperior.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblOperador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("Operador:");
        panelSuperior.add(lblOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 120, 30));

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("Cargo:");
        panelSuperior.add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 100, 30));

        bordas.add(panelSuperior);
        panelSuperior.setBounds(1, 1, 440, 110);

        getContentPane().add(bordas);
        bordas.setBounds(0, 0, 440, 600);

        setBounds(500, 140, 440, 596);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        if ("Alterar".equals(lblTitulo.getText())) {
            telaPesquisa.atualizaTabela();
        }
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
        lblDescricao1.setForeground(Color.black);
        lblMensagem.setText(null);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtQuantidade.requestFocus();
        }
    }//GEN-LAST:event_txtDescricaoKeyPressed

    private void txtValorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyPressed
        lblValor.setForeground(Color.BLACK);
        lblMensagem.setText(null);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                FormataValor fv = new FormataValor();

                txtValor.setText(fv.Formata(txtValor.getText()));
                txtQtdMin.requestFocus();
            } catch (NumberFormatException ex) {
                System.out.println("br.com.bar.view.TelaCadastroProduto.txtValorKeyPressed()" + e);
                JOptionPane.showMessageDialog(this, "Valor Inválido!", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtValorKeyPressed

    private void comboGrupoProdutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboGrupoProdutoItemStateChanged


    }//GEN-LAST:event_comboGrupoProdutoItemStateChanged

    private void comboGrupoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGrupoProdutoActionPerformed
        lblGrupo.setForeground(Color.BLACK);
        lblMensagem.setText(null);

    }//GEN-LAST:event_comboGrupoProdutoActionPerformed

    private void btnSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseClicked
        if (btnSalvar.isEnabled()) {

            // Salva um produto
            Fornecedor f = new Fornecedor();
            f.setNome(comboFornecedor.getSelectedItem().toString());

            Produto p = new Produto();

            //p.setNome(txtDescricao.getText());
            p.setNome(u.retiraAcento(txtDescricao.getText()));
            p.setQtd(txtQuantidade.getText());
            p.setQtdMax(txtQtdMax.getText());
            p.setQtdMin(txtQtdMin.getText());
            p.setValor(txtValor.getText().replace(",", "."));
            p.setCodNCM(txtCodigoNcm.getText());
            p.setCodigoProduto(txtCodigoProduto.getText());

            if (!"Selecione...".equals(comboFornecedor.getSelectedItem().toString())) {
                p.setIdFornecedor(Integer.parseInt(cf.localizaForecedor(f)));
            }
            if (!"Selecione...".equals(comboGrupoProduto.getSelectedItem().toString())) {
                p.setTbGrupoId(g.localizaIdGrupoProduto(comboGrupoProduto));
            }

            if ("Incluir".equals(lblTitulo.getText())) {
//Excluir após validação
//                if (cp.temProduto(p)) {
                if (cp.isCodigoProduto(p.getCodigoProduto())) {
                    JOptionPane.showMessageDialog(this, "O código informado já existe!");
                    limpaForm();
                    comboFornecedor.setSelectedIndex(0);
                    comboGrupoProduto.setSelectedIndex(0);
                } else {
                    if (valida(p)) {
                        if (cp.adicionaProduto(p)) {
                            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
                            limpaForm();
                            comboFornecedor.setSelectedIndex(0);
                            comboGrupoProduto.setSelectedIndex(0);
                            // Início do Registro de Log
                            l.setDescricao("Adicionou o produto " + p.getNome() + " ao estoque");
                            l.setFuncionalidade("Salvar");
                            l.gravaLog(l);
                            // Fim do Log

                            // Localiza id do produto adicionado             
                            String id = e.localizaIdProduto(p.getNome());
                            // Registra movimentação
                            e.registraMovimentacao(id, p.getQtd(), e.localizaIdOperacao("Entrada"), "Produto Novo");

                        } else {
                            JOptionPane.showMessageDialog(this, "O código informádo já existe, utilize outro!");
                            txtCodigoProduto.setText(null);
                            txtCodigoProduto.requestFocus();
                            
                        }

                    }
                }

            } else {

                if (!"Selecione...".equals(comboFornecedor.getSelectedItem().toString())) {
                    p.setIdFornecedor(Integer.parseInt(cf.localizaForecedor(f)));
                }
                if (!"Selecione...".equals(comboGrupoProduto.getSelectedItem().toString())) {

                    p.setTbGrupoId(g.localizaIdGrupoProduto(comboGrupoProduto));
                }

                p.setId(produto.getId());

                if (valida(p)) {
                    if (!produto.getCodigoProduto().equals(p.getCodigoProduto())) {
                        if (e.existeCodigoProdutuo(p.getCodigoProduto())) {
                            JOptionPane.showMessageDialog(this, "O código do produto já existe! Informe outro!");
                            txtCodigoProduto.requestFocus();
                            lblCodigoProduto.setForeground(Color.red);
                            
                        } else {
                            int resp = JOptionPane.showOptionDialog(this, "Confirma a alteração para o produto selecionado?", "Atenção", JOptionPane.ERROR_MESSAGE,
                                    JOptionPane.YES_NO_OPTION, null, opcao, opcao[1]);
                            if (resp == 1) {

                                if (cp.alteraProduto(p)) {
                                    JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso!");
                                    telaPesquisa.atualizaTabela();
                                    dispose();
                                }
                            }
                        }
                    } else {
                        int resp = JOptionPane.showOptionDialog(this, "Confirma a alteração para o produto selecionado?", "Atenção", JOptionPane.ERROR_MESSAGE,
                                JOptionPane.YES_NO_OPTION, null, opcao, opcao[1]);
                        if (resp == 1) {

                            if (cp.alteraProduto(p)) {
                                JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso!");
                                telaPesquisa.atualizaTabela();
                                dispose();
                            }
                        }
                    }

                }

            }
        }

    }//GEN-LAST:event_btnSalvarMouseClicked

    private void btnAdicionarFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarFornecedorMouseClicked
        // Chama Tela Fornecedores
        TelaCadastroFornecedor cadfor = new TelaCadastroFornecedor();
        cadfor.setAlwaysOnTop(true);
        cadfor.recebeOperador(lblOperador.getText(), lblCargo.getText(), "Incluir", null);
        //cadfor.recebeOperador(lblOperador.getText(), lblCargo.getText());
        cadfor.setVisible(true);
    }//GEN-LAST:event_btnAdicionarFornecedorMouseClicked

    private void btnAdicionarGrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarGrupoMouseClicked
        // Chama o cadastro de Grupos
        TelaGruposProdutos gp = new TelaGruposProdutos();
        gp.setAlwaysOnTop(true);
        gp.recebeOperador(lblOperador.getText());
        gp.setVisible(true);
    }//GEN-LAST:event_btnAdicionarGrupoMouseClicked
    private boolean valida(Produto p) {
        boolean resp = true;
        if ("".equals(p.getCodigoProduto())) {
            lblMensagem.setText("*Informe o código do produto para continuar!");
            txtCodigoProduto.requestFocus();
            lblCodigoProduto.setForeground(Color.red);
            resp = false;
        } else if ("".equals(p.getNome())) {
            lblMensagem.setText("*Informe a Descrição do produto para continuar!");
            txtDescricao.requestFocus();
            lblDescricao1.setForeground(Color.red);
            resp = false;
        } else if ("".equals(txtQuantidade.getText()) || Integer.parseInt(txtQuantidade.getText()) <= 0) {
            lblMensagem.setText("*Informe um Valor válido!");
            lblqtd.setForeground(Color.red);
            txtQuantidade.requestFocus();
            resp = false;
        } else if ("".equals(p.getValor())
                || "0,00".equals(p.getValor())
                || "0".equals(p.getValor())) {
            lblMensagem.setText("*Informe um Valor válido para continuar!");
            lblValor.setForeground(Color.red);
            txtValor.requestFocus();
            resp = false;
        } else if ("".equals(p.getQtdMin()) || "0".equals(p.getQtdMin())) {
            lblMensagem.setText("*Informe uma Quantidade Míninima válida!");
            lblQtdMin.setForeground(Color.red);
            txtQtdMin.requestFocus();
            resp = false;
        } else if ("".equals(p.getQtdMax()) || "0".equals(p.getQtdMax())) {
            lblMensagem.setText("*Informe uma Quantidade Máxima válida!");
            lblQtdMax.setForeground(Color.red);
            txtQtdMax.requestFocus();
            resp = false;
        } else if ("Selecione...".equals(comboGrupoProduto.getSelectedItem().toString())) {
            lblMensagem.setText("*Informe um Grupo válido para continuar!");
            lblGrupo.setForeground(Color.red);
            comboGrupoProduto.requestFocus();
            resp = false;
        } else if ("Selecione...".equals(comboFornecedor.getSelectedItem().toString())) {
            lblMensagem.setText("*Informe um Fornecedor válido para continuar!");
            lblFornecedor.setForeground(Color.red);
            comboFornecedor.requestFocus();
            resp = false;
        }
        return resp;
    }
    private void comboFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFornecedorActionPerformed
        lblFornecedor.setForeground(Color.BLACK);
        lblMensagem.setText(null);

    }//GEN-LAST:event_comboFornecedorActionPerformed

    private void comboFornecedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFornecedorItemStateChanged

    }//GEN-LAST:event_comboFornecedorItemStateChanged

    private void txtValorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValorMouseClicked

    }//GEN-LAST:event_txtValorMouseClicked

    private void txtValorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorFocusGained

    }//GEN-LAST:event_txtValorFocusGained

    private void btnSalvarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarMouseEntered

    private void txtQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyPressed
        // Vai para o campo valor
        lblqtd.setForeground(Color.black);
        lblMensagem.setText(null);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtValor.requestFocus();

        }
    }//GEN-LAST:event_txtQuantidadeKeyPressed

    private void txtQtdMinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdMinKeyPressed
        // Vai para quantidade mínima
        lblQtdMin.setForeground(Color.black);
        lblMensagem.setText(null);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtQtdMax.requestFocus();

        }
    }//GEN-LAST:event_txtQtdMinKeyPressed

    private void txtQtdMaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdMaxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdMaxKeyPressed

    private void txtQtdMaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdMaxKeyReleased
        // TODO add your handling code here:
        lblQtdMax.setForeground(Color.black);
        lblMensagem.setText(null);
    }//GEN-LAST:event_txtQtdMaxKeyReleased

    private void txtCodigoNcmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoNcmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodigoProduto.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoNcmKeyPressed

    private void txtCodigoNcmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoNcmKeyReleased
        txtCodigoNcm.setText(txtCodigoNcm.getText().replaceAll("[^0-9]", ""));
        txtCodigoNcm.setText(u.tamanhoMaximo(txtCodigoNcm.getText(), 8));
    }//GEN-LAST:event_txtCodigoNcmKeyReleased

    private void txtCodigoProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdutoKeyPressed
        lblCodigoProduto.setForeground(Color.black);
        lblMensagem.setText(null);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {                      
                    txtDescricao.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoProdutoKeyPressed

    private void txtCodigoProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdutoKeyReleased
        txtCodigoProduto.setText(txtCodigoProduto.getText().replaceAll("[^0-9]", ""));
        txtCodigoProduto.setText(u.tamanhoMaximo(txtCodigoProduto.getText(), 8));
    }//GEN-LAST:event_txtCodigoProdutoKeyReleased

    private void txtCodigoProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoProdutoFocusLost
        // Chama a função de validação ao perder o foco

    }//GEN-LAST:event_txtCodigoProdutoFocusLost

    private void btnSalvarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalvarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarKeyReleased
    private void limpaForm() {

        txtDescricao.setText(null);
        txtQuantidade.setText(null);
        txtQtdMax.setText(null);
        txtQtdMin.setText(null);
        txtValor.setText(null);
        txtCodigoNcm.setText(null);
        txtCodigoProduto.setText(null);

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
    private javax.swing.JPanel bordas;
    private javax.swing.JLabel btnAdicionarFornecedor;
    private javax.swing.JLabel btnAdicionarGrupo;
    private javax.swing.JLabel btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboFornecedor;
    private javax.swing.JComboBox<String> comboGrupoProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblCodigoNcm;
    private javax.swing.JLabel lblCodigoProduto;
    private javax.swing.JLabel lblDescricao1;
    private javax.swing.JLabel lblFornecedor;
    private javax.swing.JLabel lblGrupo;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblQtdMax;
    private javax.swing.JLabel lblQtdMin;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblqtd;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JFormattedTextField txtCodigoNcm;
    private javax.swing.JFormattedTextField txtCodigoProduto;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JFormattedTextField txtQtdMax;
    private javax.swing.JFormattedTextField txtQtdMin;
    private javax.swing.JFormattedTextField txtQuantidade;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    private void bloqueiaCampos() {
        //txtDescricao.setEnabled(false);
        txtQuantidade.setEnabled(false);
        txtValor.setEnabled(false);
    }

}
