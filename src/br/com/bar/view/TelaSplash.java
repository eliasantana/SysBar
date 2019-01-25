/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Backup;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elias Santana
 */
public class TelaSplash extends javax.swing.JFrame {

    /**
     * Creates new form TelaSplash
     */
    public TelaSplash() {
        initComponents();
        this.setAlwaysOnTop(true);
        Backup b = new Backup();

        // Inicializa tela splash
        lblMensagem.setText("Realizando backup do sistema, aguarde um momento...");
       
        new Thread() {
            public void run() {
                try {                
                    b.realizaBackup();
                } catch (IOException ex) {
                    Logger.getLogger(TelaSplash.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for (int i = 0; i < 101; i++) {
                    try {
                        sleep(80); // Ajusta a velocidade de atualização da barra de progresso
                        barraDeProgresso.setValue(i);
                        //Exibe Mensagems de acordo do o nível de carregamento da barra de progresso
                        if (i < 30) {
                            lblMensagem.setText("Carregando módulos do sistema...");
                        } else if (i > 30 && i < 60) {
                            lblMensagem.setText("Módulos carregados com sucesso!");
                        } else if (i >= 60 && i < 90) {
                            lblMensagem.setText("Checando licença de uso...");
                        } else {
                            lblMensagem.setText("Carregando o sistema...");
                        }
                    } catch (InterruptedException e) {
                        System.out.println(".run()" + e);
                    }
                }
                // Techa tela Splash
                dispose();
                // Chama proxima tela
                TelaLogin login = new TelaLogin();
                login.setVisible(true);
            }
        }.start();
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
        barraDeProgresso = new javax.swing.JProgressBar();
        lblMensagem = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/logo_1.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 20, 200, 175);

        barraDeProgresso.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 10)); // NOI18N
        barraDeProgresso.setStringPainted(true);
        jPanel1.add(barraDeProgresso);
        barraDeProgresso.setBounds(200, 160, 320, 20);
        barraDeProgresso.getAccessibleContext().setAccessibleName("");

        lblMensagem.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 10)); // NOI18N
        lblMensagem.setText("mensagem");
        jPanel1.add(lblMensagem);
        lblMensagem.setBounds(200, 140, 320, 14);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("(81) 99749-6602 | (81) 99678-1659");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(200, 110, 240, 20);

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("contato.rese7@gmail.com");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(200, 90, 230, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("RESE7 - Soluções em TI");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(200, 70, 280, 22);

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Copyright todos os direitos reservados para:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(200, 50, 290, 20);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 250));

        setSize(new java.awt.Dimension(539, 252));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TelaSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSplash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraDeProgresso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMensagem;
    // End of variables declaration//GEN-END:variables
}
