/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.util.Util;
import java.util.ArrayList;

/**
 *
 * @author Elias Santana
 */
public class TelaObservacaoProduto extends javax.swing.JFrame {

    TelaPedido2 telaPedido = new TelaPedido2();
    ArrayList<String> listaAtualizada = new ArrayList<>();
    
    public TelaObservacaoProduto() {
        initComponents();
        jTextAreaObservacao.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bordas = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(400, 197));

        bordas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bordas.setLayout(null);

        panelTitulo.setBackground(new java.awt.Color(38, 53, 61));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Obervação do Prato");

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bordas.add(panelTitulo);
        panelTitulo.setBounds(1, 1, 400, 44);

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setLineWrap(true);
        jTextAreaObservacao.setRows(5);
        jTextAreaObservacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextAreaObservacaoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaObservacao);

        bordas.add(jScrollPane1);
        jScrollPane1.setBounds(11, 56, 378, 100);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        bordas.add(btnSalvar);
        btnSalvar.setBounds(86, 167, 117, 33);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fechar32x32.png"))); // NOI18N
        btnCancelar.setText("Fechar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        bordas.add(btnCancelar);
        btnCancelar.setBounds(209, 167, 117, 33);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bordas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bordas, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(400, 212));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Fecha janela
           
           listaAtualizada.add(null);
           telaPedido.recebeObsPrato(listaAtualizada,this);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
            if (jTextAreaObservacao.getText().isEmpty()){
                listaAtualizada.add(null);                
                telaPedido.recebeObsPrato(listaAtualizada,this);
            }else {                
                listaAtualizada.add(jTextAreaObservacao.getText());
                telaPedido.recebeObsPrato(listaAtualizada,this);               
            }
           
            
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jTextAreaObservacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaObservacaoKeyPressed
        Util u = new Util();
        jTextAreaObservacao.setText(u.tamanhoMaximo(jTextAreaObservacao.getText(), 200));
    }//GEN-LAST:event_jTextAreaObservacaoKeyPressed
    // Recebe tela de pedido
    public void recebeTela(TelaPedido2 tl, ArrayList<String>lista){
         
       this.telaPedido=tl;
       this.listaAtualizada = lista;
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
            java.util.logging.Logger.getLogger(TelaObservacaoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaObservacaoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaObservacaoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaObservacaoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaObservacaoProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bordas;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JPanel panelTitulo;
    // End of variables declaration//GEN-END:variables
}
