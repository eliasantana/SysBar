/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Backup;

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
        lblMensagem.setText("Realizando backup do sistema aguarde um momento...");
        new Thread(){
            public void run(){
                b.realizaBackup();
                for (int i=0; i< 101;i++){
                    try {
                        sleep(80); // Ajusta a velocidade de atualização da barra de progresso
                        barraDeProgresso.setValue(i);
                        //Exibe Mensagems de acordo do o nível de carregamento da barra de progresso
                        if (i<30){
                             lblMensagem.setText("Carregando módulos do Sistema...");
                        }else if (i>30 && i<60){
                             lblMensagem.setText("Módulos Carregados!....");
                        }else if (i>=60 && i<90){
                             lblMensagem.setText("Checando licença de uso...");
                        }else {
                             lblMensagem.setText("Carregando o Sistema...");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        barraDeProgresso = new javax.swing.JProgressBar();
        lblMensagem = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/logo_1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("rese7.contato@gmail.com");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 230, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Copyright todos os direitos reservados para:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 290, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("RESE7 - Soluções em TI");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 280, -1));

        barraDeProgresso.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 10)); // NOI18N
        barraDeProgresso.setStringPainted(true);
        getContentPane().add(barraDeProgresso, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 320, 20));
        barraDeProgresso.getAccessibleContext().setAccessibleName("");

        lblMensagem.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 10)); // NOI18N
        lblMensagem.setText("mensagem");
        getContentPane().add(lblMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 320, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("(81) 99749-6602 | (81) 99678-1659");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 240, -1));

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
    private javax.swing.JLabel lblMensagem;
    // End of variables declaration//GEN-END:variables
}
