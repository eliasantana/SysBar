/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.wsmf.imports.Importacao;

/**
 *
 * @author Elias Santana
 */
public class TelaImportacao extends javax.swing.JFrame {

    /**
     * Creates new form TelaImportacao
     */
    public TelaImportacao() {
        initComponents();
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
        btnMovimentacao = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblMovimentacao = new javax.swing.JLabel();
        btnImportaPedido = new javax.swing.JButton();
        lblPedidos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnImportaPedido1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblContas = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMovimentacao.setText("Movimentação");
        btnMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovimentacaoActionPerformed(evt);
            }
        });

        jLabel2.setText("-");

        lblMovimentacao.setText("-");

        btnImportaPedido.setText("Pedidos");
        btnImportaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportaPedidoActionPerformed(evt);
            }
        });

        lblPedidos.setText("-");

        jLabel3.setText("-");

        btnImportaPedido1.setText("Contas");
        btnImportaPedido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportaPedido1ActionPerformed(evt);
            }
        });

        jLabel4.setText("-");

        lblContas.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnImportaPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImportaPedido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContas, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMovimentacao)
                    .addComponent(jLabel2)
                    .addComponent(lblMovimentacao))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnImportaPedido)
                    .addComponent(lblPedidos))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnImportaPedido1)
                    .addComponent(lblContas))
                .addContainerGap(161, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovimentacaoActionPerformed
       Importacao i = new Importacao();
        
            if (i.importarMovimentacao()){
                lblMovimentacao.setText("Importação Realizada com sucesso!");
            }else {
                lblMovimentacao.setText("Erro ao tentar importar!");
            }
      
    }//GEN-LAST:event_btnMovimentacaoActionPerformed

    private void btnImportaPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportaPedidoActionPerformed
       
        Importacao i = new Importacao();
        if (i.importaPedido()){
            lblPedidos.setText("Importação Realizada com sucesso!");
        }else {
            lblPedidos.setText("Erro ao importar os pedidos");
        }
        
    }//GEN-LAST:event_btnImportaPedidoActionPerformed

    private void btnImportaPedido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportaPedido1ActionPerformed
       Importacao i = new Importacao();
       if (i.importaContas()){
           lblContas.setText("Importação Realizada com sucesso!");
       }else {
            lblContas.setText("Erro ao importar os pedidos");
       }
    }//GEN-LAST:event_btnImportaPedido1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaImportacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaImportacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportaPedido;
    private javax.swing.JButton btnImportaPedido1;
    private javax.swing.JButton btnMovimentacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblContas;
    private javax.swing.JLabel lblMovimentacao;
    private javax.swing.JLabel lblPedidos;
    // End of variables declaration//GEN-END:variables
}
