/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.model.Funcionario;
import br.com.br.controler.ControlerFuncionario;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elias Santana
 */
public class TelaPesquisaFuncionario extends javax.swing.JFrame {

    ControlerFuncionario cf = new ControlerFuncionario();
    
    public TelaPesquisaFuncionario() {
        initComponents();
        bloqueiaBotoes();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtFuncionario = new javax.swing.JTextField();
        lblPesquisa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionario = new javax.swing.JTable();
        lblExcluir = new javax.swing.JLabel();
        lblAdicionar = new javax.swing.JLabel();
        lblAlterar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(243, 156, 18));
        jPanel1.setForeground(new java.awt.Color(52, 73, 94));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(52, 73, 94));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("x");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 48)); // NOI18N
        jLabel3.setText("Cadastro ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel5.setText("de Funcionários");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/funcionario (3).png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 640, 120);

        jLabel1.setText("Funcionário");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 130, 150, 14);
        getContentPane().add(txtFuncionario);
        txtFuncionario.setBounds(10, 150, 349, 30);

        lblPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/localizar32x32.png"))); // NOI18N
        lblPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPesquisaMouseClicked(evt);
            }
        });
        getContentPane().add(lblPesquisa);
        lblPesquisa.setBounds(370, 150, 32, 32);

        tblFuncionario.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        tblFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "NOME", "CPF", "RG", "TELEFONE"
            }
        ));
        tblFuncionario.setRowHeight(21);
        tblFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFuncionario);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 190, 601, 259);

        lblExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        lblExcluir.setText("Excluir");
        getContentPane().add(lblExcluir);
        lblExcluir.setBounds(410, 450, 120, 50);

        lblAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionas32x32.png"))); // NOI18N
        lblAdicionar.setText("Adicionar");
        lblAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(lblAdicionar);
        lblAdicionar.setBounds(160, 450, 120, 50);

        lblAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lapis.png"))); // NOI18N
        lblAlterar.setText("Alterar");
        lblAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlterarMouseClicked(evt);
            }
        });
        getContentPane().add(lblAlterar);
        lblAlterar.setBounds(290, 450, 120, 50);

        setSize(new java.awt.Dimension(642, 496));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // Fecha janela
        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void lblPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPesquisaMouseClicked
        // Realiza Pesquisa
        tblFuncionario.setModel(DbUtils.resultSetToTableModel(cf.carregaFuncionario(txtFuncionario.getText())));
        
    }//GEN-LAST:event_lblPesquisaMouseClicked

    private void lblAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdicionarMouseClicked
        // Chama o cadastro de Funcionários
        TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
        tcf.setVisible(true);
    }//GEN-LAST:event_lblAdicionarMouseClicked

    private void tblFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionarioMouseClicked
        // Desbloqueia botões
        desbloqueiaBotoes();
    }//GEN-LAST:event_tblFuncionarioMouseClicked

    private void lblAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlterarMouseClicked
        //
        int linha = tblFuncionario.getSelectedRow();
        Funcionario f = new Funcionario();
        f.setId(String.valueOf(tblFuncionario.getModel().getValueAt(linha, 0).toString()));
        Funcionario fLocalizado = cf.localizaFuncionario(f.getId());
        TelaCadastroFuncionario cf = new TelaCadastroFuncionario();
        cf.recebeFuncionario(fLocalizado);
        cf.setVisible(true); 
        
    }//GEN-LAST:event_lblAlterarMouseClicked

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
            java.util.logging.Logger.getLogger(TelaPesquisaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPesquisaFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdicionar;
    private javax.swing.JLabel lblAlterar;
    private javax.swing.JLabel lblExcluir;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JTable tblFuncionario;
    private javax.swing.JTextField txtFuncionario;
    // End of variables declaration//GEN-END:variables

    private void bloqueiaBotoes() {
      
       lblAlterar.setEnabled(false);
       lblExcluir.setEnabled(false);
    }
    private void desbloqueiaBotoes() {
      
       lblAlterar.setEnabled(true);
       lblExcluir.setEnabled(true);
    }
}
