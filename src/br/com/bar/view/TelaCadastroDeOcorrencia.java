/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.model.Ocorrencia;
import br.com.br.controler.ControlerOcorrencia;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaCadastroDeOcorrencia extends javax.swing.JFrame {

    ControlerOcorrencia o = new ControlerOcorrencia();
    Ocorrencia oc = new Ocorrencia();

    public TelaCadastroDeOcorrencia() {
        initComponents();
        panelCadastro.setVisible(false);
        o.listaOcorrencias(tblOcorrencia);
        txtId.setVisible(false);
    }

    public void recebeOperador(String operador, String cargo) {

        lblLogin.setText(operador);
        lblCargo.setText(cargo);
        txtId.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDireito = new javax.swing.JPanel();
        btnCadastrar = new javax.swing.JPanel();
        lblBtnNovaOcorrencia = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JPanel();
        lblAlterar = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnXcluir = new javax.swing.JPanel();
        lblExcluir = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        camadas = new javax.swing.JLayeredPane();
        panelCadastro = new javax.swing.JPanel();
        txtDescricao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOcorrencia = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        panelDireito.setBackground(new java.awt.Color(243, 156, 18));
        panelDireito.setLayout(null);

        btnCadastrar.setBackground(new java.awt.Color(232, 126, 4));
        btnCadastrar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnCadastrarFocusGained(evt);
            }
        });
        btnCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrarMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCadastrarMousePressed(evt);
            }
        });

        lblBtnNovaOcorrencia.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblBtnNovaOcorrencia.setText("Nova ocorrência");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnAdicionar.png"))); // NOI18N

        javax.swing.GroupLayout btnCadastrarLayout = new javax.swing.GroupLayout(btnCadastrar);
        btnCadastrar.setLayout(btnCadastrarLayout);
        btnCadastrarLayout.setHorizontalGroup(
            btnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnCadastrarLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBtnNovaOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnCadastrarLayout.setVerticalGroup(
            btnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCadastrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblBtnNovaOcorrencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelDireito.add(btnCadastrar);
        btnCadastrar.setBounds(0, 100, 240, 54);

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setText("Cargo");
        panelDireito.add(lblCargo);
        lblCargo.setBounds(30, 50, 130, 20);

        lblLogin.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblLogin.setText("Login");
        panelDireito.add(lblLogin);
        lblLogin.setBounds(30, 30, 130, 20);

        btnAlterar.setBackground(new java.awt.Color(232, 126, 4));
        btnAlterar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAlterarFocusGained(evt);
            }
        });
        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlterarMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAlterarMousePressed(evt);
            }
        });

        lblAlterar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblAlterar.setText("Alterar");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lapis.png"))); // NOI18N

        javax.swing.GroupLayout btnAlterarLayout = new javax.swing.GroupLayout(btnAlterar);
        btnAlterar.setLayout(btnAlterarLayout);
        btnAlterarLayout.setHorizontalGroup(
            btnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAlterarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnAlterarLayout.setVerticalGroup(
            btnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAlterarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelDireito.add(btnAlterar);
        btnAlterar.setBounds(0, 155, 240, 58);

        btnXcluir.setBackground(new java.awt.Color(232, 126, 4));
        btnXcluir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnXcluirFocusGained(evt);
            }
        });
        btnXcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXcluirMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXcluirMousePressed(evt);
            }
        });

        lblExcluir.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblExcluir.setText("Excluir");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N

        javax.swing.GroupLayout btnXcluirLayout = new javax.swing.GroupLayout(btnXcluir);
        btnXcluir.setLayout(btnXcluirLayout);
        btnXcluirLayout.setHorizontalGroup(
            btnXcluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnXcluirLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnXcluirLayout.setVerticalGroup(
            btnXcluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnXcluirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelDireito.add(btnXcluir);
        btnXcluir.setBounds(0, 210, 240, 58);

        getContentPane().add(panelDireito);
        panelDireito.setBounds(0, 0, 240, 440);

        jPanel1.setBackground(new java.awt.Color(38, 53, 61));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(243, 156, 18));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(520, 0, 40, 40);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ocorrencias de Ponto");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(170, 50, 250, 60);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cadastro");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(50, 10, 250, 60);

        panelCadastro.setLayout(null);

        txtDescricao.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtDescricao.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelCadastro.add(txtDescricao);
        txtDescricao.setBounds(23, 49, 343, 32);

        jLabel5.setText("Descrição");
        panelCadastro.add(jLabel5);
        jLabel5.setBounds(23, 19, 100, 14);

        tblOcorrencia.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        tblOcorrencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tblOcorrencia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tblOcorrencia.setGridColor(new java.awt.Color(255, 255, 255));
        tblOcorrencia.setRowHeight(21);
        tblOcorrencia.setSelectionBackground(new java.awt.Color(250, 190, 88));
        tblOcorrencia.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblOcorrencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOcorrenciaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOcorrencia);

        panelCadastro.add(jScrollPane1);
        jScrollPane1.setBounds(23, 102, 486, 220);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnSalvar.png"))); // NOI18N
        jLabel6.setText("Salvar");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        panelCadastro.add(jLabel6);
        jLabel6.setBounds(399, 42, 110, 48);
        panelCadastro.add(txtId);
        txtId.setBounds(375, 17, 49, 20);

        camadas.setLayer(panelCadastro, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout camadasLayout = new javax.swing.GroupLayout(camadas);
        camadas.setLayout(camadasLayout);
        camadasLayout.setHorizontalGroup(
            camadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        camadasLayout.setVerticalGroup(
            camadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(camadasLayout.createSequentialGroup()
                .addComponent(panelCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(camadas);
        camadas.setBounds(30, 100, 530, 350);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(210, 0, 560, 440);

        setSize(new java.awt.Dimension(766, 439));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // Fecha janela 
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMouseClicked
        // Exibe painel Cadastro
        panelCadastro.setVisible(true);
       


    }//GEN-LAST:event_btnCadastrarMouseClicked

    private void btnCadastrarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnCadastrarFocusGained
        // Muda acor do botão

    }//GEN-LAST:event_btnCadastrarFocusGained

    private void btnCadastrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMousePressed
        selecionado(btnCadastrar);
       
        naoSelecionado(btnAlterar);
        naoSelecionado(btnXcluir);


    }//GEN-LAST:event_btnCadastrarMousePressed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        // Salva uma ocorrência
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(txtDescricao.getText());

        if (ocorrencia.getDescricao().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha a ocorrencia para continuar!");
        } else {

            if (o.salvarOcorrencia(ocorrencia)) {
                JOptionPane.showMessageDialog(null, "Ocorrência adicionada com sucesso");
                o.listaOcorrencias(tblOcorrencia);
                limpatForm();
            }
        }

    }//GEN-LAST:event_jLabel6MouseClicked

    private void btnAlterarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAlterarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarFocusGained

    private void btnAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseClicked
        // Altera dados selecionado
        if (txtDescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma ocorrência para alterar");
        } else {

            oc.setId(Integer.parseInt(txtId.getText()));
            oc.setDescricao(txtDescricao.getText());

            if (o.alterarOcorrencia(oc)) {

                JOptionPane.showMessageDialog(null, "Ocorrencia altearda com sucesso!");
                o.listaOcorrencias(tblOcorrencia);

            }

        }


    }//GEN-LAST:event_btnAlterarMouseClicked

    private void btnAlterarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMousePressed
        // Muda a cor do botão Consultar
        selecionado(btnAlterar);
        naoSelecionado(btnCadastrar);
        naoSelecionado(btnXcluir);

    }//GEN-LAST:event_btnAlterarMousePressed

    private void tblOcorrenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOcorrenciaMouseClicked
        // Seleciona item da tabela
        int linha = tblOcorrencia.getSelectedRow();
        txtId.setText(tblOcorrencia.getModel().getValueAt(linha, 0).toString());
        txtDescricao.setText(tblOcorrencia.getModel().getValueAt(linha, 1).toString());
    }//GEN-LAST:event_tblOcorrenciaMouseClicked

    private void btnXcluirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnXcluirFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXcluirFocusGained

    private void btnXcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXcluirMouseClicked
        // Excluir produto

        // Verifica se o objeto foi selecionado
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma ocorrência para excluir");
        } else {
            // Instacia objeto
            oc.setId(Integer.parseInt(txtId.getText()));
            oc.setDescricao(txtDescricao.getText());
            // Solicita aconfirmação do usuario
            int op = JOptionPane.showConfirmDialog(null, "Deseja Excluir " + oc.getDescricao() + "?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION) {
                // Verifica se o registro foi excluído com sucesso!
                if (o.excluiOcorrencia(oc)) {
                    
                    JOptionPane.showMessageDialog(null, "Ocorrencia excluída!");
                    // Atualiza a tabela
                    o.listaOcorrencias(tblOcorrencia);

                }
            }
        }


    }//GEN-LAST:event_btnXcluirMouseClicked

    private void btnXcluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXcluirMousePressed
        // Modifica estado do botão excluir
        selecionado(btnXcluir);
        naoSelecionado(btnCadastrar);
        naoSelecionado(btnAlterar);

    }//GEN-LAST:event_btnXcluirMousePressed

    private void selecionado(JPanel panel) {

        panel.setBackground(new Color(249, 105, 14));

    }

    private void naoSelecionado(JPanel panel) {

        panel.setBackground(new Color(232, 126, 4));

    }

    private void limpatForm() {

        txtDescricao.setText(null);
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
            java.util.logging.Logger.getLogger(TelaCadastroDeOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDeOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDeOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDeOcorrencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroDeOcorrencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAlterar;
    private javax.swing.JPanel btnCadastrar;
    private javax.swing.JPanel btnXcluir;
    private javax.swing.JLayeredPane camadas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlterar;
    private javax.swing.JLabel lblBtnNovaOcorrencia;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblExcluir;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JPanel panelCadastro;
    private javax.swing.JPanel panelDireito;
    private javax.swing.JTable tblOcorrencia;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
