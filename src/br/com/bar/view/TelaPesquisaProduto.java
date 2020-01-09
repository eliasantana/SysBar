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
import br.com.bar.util.Util;
import br.com.br.controler.ControlerFornecedor;
import br.com.br.controler.ControlerGrupo;
import br.com.br.controler.ControlerProduto;
import java.util.Calendar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaPesquisaProduto extends javax.swing.JFrame {

    ControlerProduto cp = new ControlerProduto();
    ControlerFornecedor cf = new ControlerFornecedor();
    ControlerGrupo g = new ControlerGrupo();
    Util u = new Util();
    TableModelCadastroProduto modelCadastroProduto = new TableModelCadastroProduto();
    // Armazena um objeto tela principal.
    TelaPrincipal telaPrincipal;
    /**
     * Creates new form TelaCadastroProduto
     */
    public TelaPesquisaProduto() {
        initComponents();

        txtIdGrupo.setVisible(false);
        txtIdProduto.setVisible(false);
        txtIdFor.setVisible(false);
        btnAlteraProduto.setEnabled(false);
        btnExcluir.setEnabled(false);
        cf.carregaComboFornecedor(comboFornecedor);
        Calendar c = Calendar.getInstance();
        lblData.setText(u.formataDataBr(c.getTime()));
        lblCargo.setVisible(false);
        lblData.setVisible(false);
        lblOperador.setVisible(false);
        radioProduto.setSelected(true);

    }

    public void recebeOperador(TelaPrincipal tp,String operador, String cargo) {

        lblCargo.setText(cargo);
        lblOperador.setText(operador);
        // Adiciona nome do operador ao log

        txtLocalizar.requestFocus();
        this.telaPrincipal=tp;
    }

    public void atualizaTabela() {
        tblProduto.setModel(DbUtils.resultSetToTableModel(cp.listaProduto()));
        modelCadastroProduto.redimensionaColunas(tblProduto);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        txtLocalizar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        comboFornecedor = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        panelSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIdGrupo = new javax.swing.JTextField();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        txtIdFor = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnExcluir = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JLabel();
        btnAlteraProduto = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        radioProduto = new javax.swing.JRadioButton();
        radioGrupo = new javax.swing.JRadioButton();
        radioCodigo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        bordas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bordas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProduto = new javax.swing.JTable(){
            public boolean isCellEditable(int colIndex, int rowIndex){
                return false;
            }

        };
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

        bordas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 197, 730, 420));

        txtLocalizar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtLocalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLocalizarMouseClicked(evt);
            }
        });
        txtLocalizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLocalizarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalizarKeyReleased(evt);
            }
        });
        bordas.add(txtLocalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 142, 256, 36));

        jPanel4.setLayout(null);

        comboFornecedor.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
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
        jPanel4.add(comboFornecedor);
        comboFornecedor.setBounds(170, 0, 280, 40);

        bordas.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 142, 460, 45));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Fornecedor");
        bordas.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(457, 110, 170, 30));

        panelSuperior.setBackground(new java.awt.Color(243, 156, 18));
        panelSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/estoque.png"))); // NOI18N
        panelSuperior.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 99));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel2.setText("Cadastro ");
        panelSuperior.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 330, 40));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelSuperior.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel8.setText("de Produto");
        panelSuperior.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));
        panelSuperior.add(txtIdGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 60, 30));

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblOperador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("Operador:");
        panelSuperior.add(lblOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 120, 30));

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("Cargo:");
        panelSuperior.add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 100, 30));

        lblData.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/calendario24x24.png"))); // NOI18N
        lblData.setText("jLabel8");
        panelSuperior.add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 100, 30));
        panelSuperior.add(txtIdProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 60, 30));
        panelSuperior.add(txtIdFor, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 60, 30));

        bordas.add(panelSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 748, -1));

        jPanel2.setLayout(null);

        btnExcluir.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExcluirMouseClicked(evt);
            }
        });
        jPanel2.add(btnExcluir);
        btnExcluir.setBounds(440, 20, 100, 50);

        btnSalvar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionas32x32.png"))); // NOI18N
        btnSalvar.setText("Adicionar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalvarMouseClicked(evt);
            }
        });
        jPanel2.add(btnSalvar);
        btnSalvar.setBounds(190, 20, 110, 50);

        btnAlteraProduto.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        btnAlteraProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lapis.png"))); // NOI18N
        btnAlteraProduto.setText("Alterar");
        btnAlteraProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlteraProdutoMouseClicked(evt);
            }
        });
        jPanel2.add(btnAlteraProduto);
        btnAlteraProduto.setBounds(330, 20, 110, 50);

        jPanel5.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(130, 0, 20, 15);

        jLabel10.setText("Realizar Compra");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(160, 0, 130, 15);

        jPanel6.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6);
        jPanel6.setBounds(0, 0, 20, 15);

        jLabel11.setText("Estoque Baixo");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(30, 0, 130, 15);

        bordas.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 730, 70));

        buttonGroup1.add(radioProduto);
        radioProduto.setText("Produto");
        radioProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioProdutoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                radioProdutoMouseEntered(evt);
            }
        });
        radioProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioProdutoActionPerformed(evt);
            }
        });
        bordas.add(radioProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 90, -1));

        buttonGroup1.add(radioGrupo);
        radioGrupo.setText("Grupo");
        radioGrupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioGrupoMouseClicked(evt);
            }
        });
        radioGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioGrupoActionPerformed(evt);
            }
        });
        bordas.add(radioGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 80, -1));

        buttonGroup1.add(radioCodigo);
        radioCodigo.setText("Código");
        radioCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioCodigoMouseClicked(evt);
            }
        });
        bordas.add(radioCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        getContentPane().add(bordas);
        bordas.setBounds(0, 0, 750, 710);

        setSize(new java.awt.Dimension(750, 710));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // Atualiza dados da tela principal
        telaPrincipal.atualizaInformativo();
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void btnSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseClicked

        TelaCadastroProduto cadastroProduto = new TelaCadastroProduto();
        cadastroProduto.recebeOperador(lblOperador.getText(), lblCargo.getText(), "Incluir");
        cadastroProduto.setAlwaysOnTop(true);
        cadastroProduto.setVisible(true);

    }//GEN-LAST:event_btnSalvarMouseClicked

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        // Seleciona dados da tabela     

        btnAlteraProduto.setEnabled(true);
        btnExcluir.setEnabled(true);
        int linha = tblProduto.getSelectedRow();


    }//GEN-LAST:event_tblProdutoMouseClicked

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // Localiza produto
        String opcao = null;
        if (radioProduto.isSelected()) {
            opcao = "Nome";
        } else if (radioGrupo.isSelected()) {
            opcao = "Grupo";
        }else if (radioCodigo.isSelected()){
            opcao = "Codigo";
        }else {
            opcao = "Nome";
        }
        
        tblProduto.setModel(DbUtils.resultSetToTableModel(cp.filtrarProduto(txtLocalizar.getText(), opcao)));
        modelCadastroProduto.redimensionaColunas(tblProduto);

    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void radioProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioProdutoActionPerformed

    private void radioProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioProdutoMouseClicked
        // Seleciona caixa de texto localizar e adiciona o ponteiro do mouse
        txtLocalizar.setText(null);
        txtLocalizar.requestFocus();
        comboFornecedor.setSelectedIndex(0);
      
    }//GEN-LAST:event_radioProdutoMouseClicked

    private void radioGrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioGrupoMouseClicked
        // Seleciona caixa de texto localizar e adiciona o ponteiro do mouse
        txtLocalizar.setText(null);
        txtLocalizar.requestFocus();
        comboFornecedor.setSelectedIndex(0);
      
    }//GEN-LAST:event_radioGrupoMouseClicked

    private void btnAlteraProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlteraProdutoMouseClicked
        int linha = tblProduto.getSelectedRow();
        if (btnAlteraProduto.isEnabled()) {
            Produto p = new Produto();
            //p.setId(tblProduto.getModel().getValueAt(linha, 0).toString());
            p.setCodigoProduto(tblProduto.getModel().getValueAt(linha, 0).toString());
            p.setNome(tblProduto.getModel().getValueAt(linha, 1).toString());            
            p.setQtd(tblProduto.getModel().getValueAt(linha, 2).toString());
            p.setValor(tblProduto.getModel().getValueAt(linha, 3).toString());
            p.setQtdMin(tblProduto.getModel().getValueAt(linha, 4).toString());
            p.setQtdMax(tblProduto.getModel().getValueAt(linha, 5).toString());
            p.setTbGrupoId(tblProduto.getModel().getValueAt(linha, 6).toString());
            p.setId(cp.localizaIdProduto(p.getCodigoProduto()));
             
            //Localiza código ncm do produto
            p.setCodNCM(cp.localizaNCM(p));
            Fornecedor f = cf.localizaFornecedor(cf.retornaFornecedor(p));
            p.setIdFornecedor(f.getId());

            TelaCadastroProduto cadastroProduto = new TelaCadastroProduto();
            cadastroProduto.recebeOperador(lblOperador.getText(), lblCargo.getText(), "Alterar");
            cadastroProduto.recebeProduto(this, p);
            cadastroProduto.setAlwaysOnTop(true);
            cadastroProduto.setVisible(true);
            btnAlteraProduto.setEnabled(false);
        }


    }//GEN-LAST:event_btnAlteraProdutoMouseClicked

    private void btnExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseClicked
        int linha = tblProduto.getSelectedRow();

        Produto p = new Produto();
        String codProduto = tblProduto.getModel().getValueAt(linha, 0).toString();
        p.setId(cp.localizaIdProduto(codProduto));

        int op = JOptionPane.showConfirmDialog(this, "Confirma a exclusão do produto?", "Atenção!", JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {

            if (cp.excluiProduto(p)){
                JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
                atualizaTabela();
                Log l = new Log(lblOperador.getText(), "Excluir", "Excluiu o produto "+p.getNome());
                l.gravaLog(l);
            }else {
                 JOptionPane.showMessageDialog(this, "Este produto não pode ser excluído!");
            }
        }
    }//GEN-LAST:event_btnExcluirMouseClicked

    private void comboFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFornecedorActionPerformed

        listaProdutoPorFornecedor();
        txtLocalizar.setText(null);
    }//GEN-LAST:event_comboFornecedorActionPerformed

    private void comboFornecedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFornecedorItemStateChanged

    }//GEN-LAST:event_comboFornecedorItemStateChanged

    private void radioGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioGrupoActionPerformed
    private void listaProdutoPorFornecedor() {
        if (!"Selecione...".equals(comboFornecedor.getSelectedItem().toString())) {
            try {

                Fornecedor f = new Fornecedor();
                f.setNome(comboFornecedor.getSelectedItem().toString());
                f.setId(Integer.parseInt(cf.localizaForecedor(f)));

                tblProduto.setModel(DbUtils.resultSetToTableModel(cp.listaProduto(f)));
                modelCadastroProduto.redimensionaColunas(tblProduto);

            } catch (NumberFormatException nf) {
            }
        } else {
            tblProduto.setModel(DbUtils.resultSetToTableModel(cp.listaProduto()));
            modelCadastroProduto.redimensionaColunas(tblProduto);
        }
    }
    private void txtLocalizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLocalizarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalizarMouseClicked

    private void txtLocalizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalizarKeyPressed

    private void radioProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioProdutoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_radioProdutoMouseEntered

    private void radioCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioCodigoMouseClicked
    // Seleciona caixa de texto localizar e adiciona o ponteiro do mouse
        txtLocalizar.setText(null);
        txtLocalizar.requestFocus();
        comboFornecedor.setSelectedIndex(0);
    }//GEN-LAST:event_radioCodigoMouseClicked

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
            java.util.logging.Logger.getLogger(TelaPesquisaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPesquisaProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bordas;
    private javax.swing.JLabel btnAlteraProduto;
    private javax.swing.JLabel btnExcluir;
    private javax.swing.JLabel btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboFornecedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JRadioButton radioCodigo;
    private javax.swing.JRadioButton radioGrupo;
    private javax.swing.JRadioButton radioProduto;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtIdFor;
    private javax.swing.JTextField txtIdGrupo;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtLocalizar;
    // End of variables declaration//GEN-END:variables

}
