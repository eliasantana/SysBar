/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.AutenticaUsuario;
import br.com.br.controler.ControlerFuncionario;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Elias Santana
 */
public class TelaAlteraSenha2 extends javax.swing.JFrame {

    ControlerFuncionario cf = new ControlerFuncionario();
    AutenticaUsuario auth = new AutenticaUsuario();
    String loginOperador;
    JFrame tela;        // Armazena a tela passada como patâmetro no método recebeTela
    String nomeJanela; // Nome da Janela que está sendo passada como parâmetro

    public TelaAlteraSenha2() {
        initComponents();
        loginOperador = "Admin";
        estadoInicial();
    }

    private void estadoInicial() {
        lblSenhaAtualOK.setVisible(false);
        lblOkConfirmacao.setVisible(false);
        lblSenhaAtual.setEnabled(true);
        lblNovaSenha.setEnabled(false);
        lblSenhaConfirmada.setEnabled(false);
        txtConfirmaSnh.setEnabled(false);
        txtNovaSnh.setEnabled(false);
        btnAlterar.setEnabled(false);
        lblError.setVisible(false);

    }

    private void habilitaTroca() {
        txtSenhaAtual.setEnabled(false);
        lblSenhaAtualOK.setEnabled(true);
        lblNovaSenha.setEnabled(true);
        lblOkConfirmacao.setEnabled(true);
        txtNovaSnh.setEnabled(true);
        txtConfirmaSnh.setEnabled(true);
        lblSenhaConfirmada.setEnabled(true);
    }

    public void receberOperador(String operador) {
        this.loginOperador = operador;
    }

    public void recebeTela(JFrame frame, String nomeJanela) {
        this.tela = frame;
        this.nomeJanela = nomeJanela;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblSenhaAtual = new javax.swing.JLabel();
        txtSenhaAtual = new javax.swing.JPasswordField();
        lblNovaSenha = new javax.swing.JLabel();
        txtNovaSnh = new javax.swing.JPasswordField();
        lblSenhaConfirmada = new javax.swing.JLabel();
        txtConfirmaSnh = new javax.swing.JPasswordField();
        lblSenhaAtualOK = new javax.swing.JLabel();
        lblOkConfirmacao = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(38, 53, 61));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Alteração de Senha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 332, 48);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setForeground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(null);

        lblSenhaAtual.setText("Senha Atual");
        jPanel2.add(lblSenhaAtual);
        lblSenhaAtual.setBounds(16, 12, 190, 20);

        txtSenhaAtual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaAtualKeyPressed(evt);
            }
        });
        jPanel2.add(txtSenhaAtual);
        txtSenhaAtual.setBounds(16, 32, 119, 30);

        lblNovaSenha.setText("Nova Senha");
        jPanel2.add(lblNovaSenha);
        lblNovaSenha.setBounds(16, 68, 120, 20);

        txtNovaSnh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNovaSnhKeyPressed(evt);
            }
        });
        jPanel2.add(txtNovaSnh);
        txtNovaSnh.setBounds(16, 88, 119, 30);

        lblSenhaConfirmada.setText("Confirmação de Senha");
        jPanel2.add(lblSenhaConfirmada);
        lblSenhaConfirmada.setBounds(140, 68, 160, 20);

        txtConfirmaSnh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConfirmaSnhKeyPressed(evt);
            }
        });
        jPanel2.add(txtConfirmaSnh);
        txtConfirmaSnh.setBounds(140, 88, 119, 30);

        lblSenhaAtualOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/aplicar.png"))); // NOI18N
        jPanel2.add(lblSenhaAtualOK);
        lblSenhaAtualOK.setBounds(140, 32, 24, 30);

        lblOkConfirmacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/aplicar.png"))); // NOI18N
        jPanel2.add(lblOkConfirmacao);
        lblOkConfirmacao.setBounds(269, 88, 40, 30);

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAlterar);
        btnAlterar.setBounds(16, 129, 119, 33);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar);
        btnCancelar.setBounds(140, 129, 119, 33);

        lblMsg.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblMsg);
        lblMsg.setBounds(16, 162, 243, 22);

        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fechar_vermelho.png"))); // NOI18N
        jPanel2.add(lblError);
        lblError.setBounds(269, 88, 40, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 49, 318, 188);

        setSize(new java.awt.Dimension(318, 237));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        switch (nomeJanela) {
            case "DetalheMesa":
                TelaDetalheMesa detMesa = (TelaDetalheMesa) tela;
                detMesa.atualizaTelaSenha();
                dispose();
                break;
            case "Caixa":
                TelaCaixa caixa = (TelaCaixa) tela;
                caixa.atualizaTelaSenha();
                dispose();
                break;
            case "Cozinha":
                TelaConzinha cozinha = (TelaConzinha) tela;
                cozinha.atualizaTelaSenha();
                dispose();
                break;

        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        String idOperador = cf.localizaIdLogin(loginOperador);
        if (txtSenhaAtual.getText().equals(txtConfirmaSnh.getText())) {
            lblMsg.setText("*A nova senha não pode ser igual a anterior!");
        } else {

            if (cf.alteraSenha(txtConfirmaSnh.getText(), idOperador)) {
                JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
                switch (nomeJanela) {
                    case "DetalheMesa":
                        TelaDetalheMesa detMesa = (TelaDetalheMesa) tela;
                        detMesa.atualizaTelaSenha();
                        dispose();
                        break;
                    case "Caixa":
                        TelaCaixa caixa = (TelaCaixa) tela;
                        caixa.atualizaTelaSenha();
                        dispose();
                        break;

                }
            }
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void txtSenhaAtualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaAtualKeyPressed
        String senha = txtSenhaAtual.getText().toLowerCase();
        //Verifica se o TextFild senha atual está vazio
        if (senha.length() == 0) {
            lblMsg.setText(null); // Limpa Label de mensagem se o TextFild senhaAtual estiver vazio
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auth.isExistsSenha(txtSenhaAtual.getText().toLowerCase())) {

                if (auth.autentica2(loginOperador, senha)) {

                    lblSenhaAtualOK.setVisible(true);
                    habilitaTroca();
                    txtNovaSnh.requestFocus();

                }
            } else {
                lblMsg.setText("*Senha inválida!");

            }
        }
    }//GEN-LAST:event_txtSenhaAtualKeyPressed

    private void txtNovaSnhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNovaSnhKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtConfirmaSnh.requestFocus();
        }
    }//GEN-LAST:event_txtNovaSnhKeyPressed

    private void txtConfirmaSnhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmaSnhKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtNovaSnh.getText().equals(txtConfirmaSnh.getText())) {
                btnAlterar.setEnabled(true);
                lblOkConfirmacao.setVisible(true);
                lblError.setVisible(false);
                lblMsg.setText(null);

            } else {
                lblOkConfirmacao.setVisible(false);
                lblError.setVisible(true);
                lblMsg.setForeground(Color.red);
                lblMsg.setText("*Confirmação de nova senha inconsistente!");

            }
        }
    }//GEN-LAST:event_txtConfirmaSnhKeyPressed

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
            java.util.logging.Logger.getLogger(TelaAlteraSenha2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraSenha2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraSenha2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraSenha2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAlteraSenha2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblNovaSenha;
    private javax.swing.JLabel lblOkConfirmacao;
    private javax.swing.JLabel lblSenhaAtual;
    private javax.swing.JLabel lblSenhaAtualOK;
    private javax.swing.JLabel lblSenhaConfirmada;
    private javax.swing.JPasswordField txtConfirmaSnh;
    private javax.swing.JPasswordField txtNovaSnh;
    private javax.swing.JPasswordField txtSenhaAtual;
    // End of variables declaration//GEN-END:variables
}
