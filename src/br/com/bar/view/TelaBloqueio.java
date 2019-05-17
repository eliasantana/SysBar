/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.AutenticaUsuario;
import br.com.br.controler.ControlerFuncionario;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

/**
 *
 * @author Elias Santana
 */
public class TelaBloqueio extends JDialog {

    ControlerFuncionario cf = new ControlerFuncionario();
    AutenticaUsuario auth = new AutenticaUsuario();

    /**
     * Creates new form TelaBloqueio
     */
    public TelaBloqueio() {
        initComponents();
        cf.carregaComboFuncionarioGerenteGarcom(comboLogin);
        txtSenha.setEnabled(false);
        btnDesbloquear.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblMsg = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboLogin = new javax.swing.JComboBox<>();
        txtSenha = new javax.swing.JPasswordField();
        btnDesbloquear = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(38, 53, 61));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Insira suas credenciais para desbloqueio");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 36);

        lblMsg.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblMsg);
        lblMsg.setBounds(10, 110, 390, 30);

        jLabel3.setText("Senha");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(190, 40, 39, 30);

        comboLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoginActionPerformed(evt);
            }
        });
        jPanel2.add(comboLogin);
        comboLogin.setBounds(10, 70, 170, 30);

        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });
        jPanel2.add(txtSenha);
        txtSenha.setBounds(190, 70, 100, 30);

        btnDesbloquear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesbloquear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cadeado2.png"))); // NOI18N
        btnDesbloquear.setText("Desbloquear");
        btnDesbloquear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDesbloquearMouseClicked(evt);
            }
        });
        jPanel2.add(btnDesbloquear);
        btnDesbloquear.setBounds(290, 70, 110, 30);

        jLabel5.setText("Login");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 42, 50, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 400, 140);

        setSize(new java.awt.Dimension(400, 141));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDesbloquearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesbloquearMouseClicked
        desbloquear();

    }//GEN-LAST:event_btnDesbloquearMouseClicked

    private void comboLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoginActionPerformed
        lblMsg.setText(null);
        if (!"Selecione...".equals(comboLogin.getSelectedItem().toString())) {
            txtSenha.setEnabled(true);
            txtSenha.requestFocus();
        }
    }//GEN-LAST:event_comboLoginActionPerformed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        btnDesbloquear.setEnabled(true);
        lblMsg.setText(null);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            desbloquear();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

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
            java.util.logging.Logger.getLogger(TelaBloqueio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaBloqueio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaBloqueio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaBloqueio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaBloqueio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDesbloquear;
    private javax.swing.JComboBox<String> comboLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables

    private void desbloquear() {
        if (auth.isExistsSenha(txtSenha.getText())) {
            if (auth.autentica2(comboLogin.getSelectedItem().toString(), txtSenha.getText())) {

                try {
                    Robot r = new Robot();
                    r.mouseMove(600, 300);
                } catch (AWTException ex) {
                    Logger.getLogger(TelaBloqueio.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();

            } else {
                lblMsg.setText("*Senha inválida!");
                txtSenha.setText(null);
            }
        } else {
            lblMsg.setText("*Senha inválida!");
            txtSenha.setText(null);
        }
    }

}
