/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.model.TableModelStatusCozinha;
import br.com.br.controler.ControlerCozinha;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaStatusCozinha_ORIGINAL extends javax.swing.JFrame {
    TableModelStatusCozinha modelStatusCozinha = new TableModelStatusCozinha();
    
    public TelaStatusCozinha_ORIGINAL() {
        initComponents();
         //Lista pratos enviados pelo garçom que estão com status pendente
        
    }

    public void recebeOperador(String operador, String nPedido, String nmesa) {     
         
        lblGarcom.setText(operador);
        lblMesa.setText(nmesa);
        lblNPedido.setText(nPedido);
        ControlerCozinha status = new ControlerCozinha();
        tblStatus.setModel(DbUtils.resultSetToTableModel(status.statusCozinha(nPedido)));
        modelStatusCozinha.redimensionaColunas(tblStatus);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFechar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnFechar1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblGarcom = new javax.swing.JLabel();
        lblMesa = new javax.swing.JLabel();
        lblNPedido = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStatus = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFechar.setText("X");
        btnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFecharMouseClicked(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(243, 156, 18));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel1.setText("Status Cozinha");

        jPanel2.setBackground(new java.awt.Color(38, 53, 91));

        btnFechar1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar1.setForeground(new java.awt.Color(255, 255, 255));
        btnFechar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFechar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        btnFechar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFechar1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFechar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnFechar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnCozinha.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 65, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 620, 100);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(null);

        lblGarcom.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblGarcom.setText("jLabel2");
        jPanel3.add(lblGarcom);
        lblGarcom.setBounds(90, 10, 460, 30);

        lblMesa.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblMesa.setText("jLabel3");
        jPanel3.add(lblMesa);
        lblMesa.setBounds(70, 40, 100, 25);

        lblNPedido.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblNPedido.setText("jLabel3");
        jPanel3.add(lblNPedido);
        lblNPedido.setBounds(290, 40, 100, 25);

        tblStatus.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        tblStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "PEDIDO", "PRATO", "QUANTIDADE", "MESA", "STATUS"
            }
        ));
        tblStatus.setRowHeight(22);
        tblStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStatusMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStatus);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(5, 70, 608, 310);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel3.setText("Pedido:");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(210, 40, 80, 25);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel4.setText("Mesa:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 40, 70, 25);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel5.setText("Garçom:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(10, 10, 90, 30);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 100, 618, 388);

        setBounds(0, 0, 618, 487);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseClicked
        // Fecha tela
        dispose();
    }//GEN-LAST:event_btnFecharMouseClicked

    private void btnFechar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFechar1MouseClicked
        // Fecha tela
        dispose();
    }//GEN-LAST:event_btnFechar1MouseClicked

    private void tblStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStatusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblStatusMouseClicked

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
            java.util.logging.Logger.getLogger(TelaStatusCozinha_ORIGINAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaStatusCozinha_ORIGINAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaStatusCozinha_ORIGINAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaStatusCozinha_ORIGINAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaStatusCozinha_ORIGINAL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnFechar;
    private javax.swing.JLabel btnFechar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGarcom;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblNPedido;
    private javax.swing.JTable tblStatus;
    // End of variables declaration//GEN-END:variables
}
