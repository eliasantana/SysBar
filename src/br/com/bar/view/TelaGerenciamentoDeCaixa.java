/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.dao.ReportUtil;
import br.com.bar.model.MovimentacaoCaixa;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerCaixa;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerFuncionario;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaGerenciamentoDeCaixa extends JFrame {

    /**
     * Creates new form TelaGerenciamentoDeCaixa
     */
    ControlerCaixa cc = new ControlerCaixa();
    ControlerFuncionario cf = new ControlerFuncionario();
    ControlerCaixa caixa = new ControlerCaixa();
    MovimentacaoCaixa cx = new MovimentacaoCaixa();
    ControlerDadosEmpresa de = new ControlerDadosEmpresa();
    ReportUtil rpu = new ReportUtil();

    Log l = new Log();
    Util u = new Util();

    public TelaGerenciamentoDeCaixa() {
        initComponents();
        txtIdCaixa.setVisible(false);
        tblGerenciamentoCaixa.setModel(DbUtils.resultSetToTableModel(cc.listaCaixa()));    
        lblCargo.setVisible(false);
        lblOperador.setVisible(false);
    }

    public void recebeOperador(String operador, String cargo) {

        lblOperador.setText(operador);
        lblCargo.setText(cargo);

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
        jLabel6 = new javax.swing.JLabel();
        txtIdCaixa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGerenciamentoCaixa = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnLiberarCaixa = new javax.swing.JButton();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(243, 156, 18));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel1.setText("Gerenciamento");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(110, 10, 230, 48);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel6.setText("de Caixa");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(290, 60, 86, 32);
        jPanel1.add(txtIdCaixa);
        txtIdCaixa.setBounds(400, 50, 51, 20);

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(423, 0, 40, 40);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/caixa.png"))); // NOI18N
        jPanel1.add(jLabel17);
        jLabel17.setBounds(0, 20, 130, 70);

        tblGerenciamentoCaixa.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        tblGerenciamentoCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblGerenciamentoCaixa.setRowHeight(21);
        tblGerenciamentoCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGerenciamentoCaixaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGerenciamentoCaixa);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLiberarCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/liberaCaixa2.png"))); // NOI18N
        btnLiberarCaixa.setText("Liberar Caixa");
        btnLiberarCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiberarCaixaActionPerformed(evt);
            }
        });
        jPanel3.add(btnLiberarCaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 11, 193, -1));

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("Operador");
        jPanel3.add(lblOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 100, 30));

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("cargo");
        jPanel3.add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 15, 81, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(463, 331));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // Fecha janela
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btnLiberarCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiberarCaixaActionPerformed
        if (txtIdCaixa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um caixa para continuar");
        } else {

            if (cc.liberaCaixa(txtIdCaixa.getText())) {
                JOptionPane.showMessageDialog(null, "Caixa liberado com sucesso!");
                tblGerenciamentoCaixa.setModel(DbUtils.resultSetToTableModel(cc.listaCaixa()));
                //Inicio do Registro de Log

                l.setFuncionalidade("Caixa");
                l.setDescricao(lblOperador.getText() + " liberou o caixa");
                l.setUsuario(lblOperador.getText());
                l.gravaLog(l);

                //Fim do Registro de Log
            }
        }

    }//GEN-LAST:event_btnLiberarCaixaActionPerformed

    private void tblGerenciamentoCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGerenciamentoCaixaMouseClicked

        int linha = tblGerenciamentoCaixa.getSelectedRow();
        txtIdCaixa.setText(tblGerenciamentoCaixa.getValueAt(linha, 0).toString());
    }//GEN-LAST:event_tblGerenciamentoCaixaMouseClicked

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
            java.util.logging.Logger.getLogger(TelaGerenciamentoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciamentoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciamentoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciamentoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGerenciamentoDeCaixa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLiberarCaixa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JTable tblGerenciamentoCaixa;
    private javax.swing.JTextField txtIdCaixa;
    // End of variables declaration//GEN-END:variables
    /*EXcluir este método após todos os testes
    Métodos copiado para a classe  ControlerCaixa
    private Boolean temMovAnterior() {
        boolean resp = false;
        try {

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_WEEK, -1);
            String dataAnterior = u.formataDataBanco(c.getTime());
            Double saida = caixa.totalizaSaida(combofuncionario.getSelectedItem().toString(), dataAnterior);
            Double entrada = caixa.totalizaEntradas(combofuncionario.getSelectedItem().toString(), dataAnterior);
            Double saldo = entrada - saida;
            if (0 != saida || 0 != entrada) {
                btnLiberaCxAnterior.setEnabled(true);
            } else {
                btnLiberaCxAnterior.setEnabled(false);
            }
            resp = true;

        } catch (Exception e) {
            System.out.println("br.com.bar.view.TelaGerenciamentoDeCaixa.temMovAnterior()" + e);
        }
        return resp;
    }
*/
    
}
