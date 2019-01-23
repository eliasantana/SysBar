/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.br.controler.ControlerEstoque;
import br.com.br.controler.ControlerMesa;
import br.com.br.controler.ControlerPedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.AccessibleRole;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaGerenciarPedido extends javax.swing.JFrame {

    /**
     * Creates new form TelaGerenciarPedido
     */
    ControlerMesa cm = new ControlerMesa();
    ControlerPedido cp = new ControlerPedido();
    ControlerEstoque ce = new ControlerEstoque();

    Log l = new Log();

    public TelaGerenciarPedido() {
        initComponents();
        cp.carregaComboPedido(jcomboPedido);
        txtIdProduto.setVisible(false);
        txtQtd.setVisible(false);
        txtIDItem.setVisible(false);
        lblRemoverItemDoPedido.setEnabled(false);
        lblOperador.setVisible(false);
        LblCargo.setVisible(false);
        btnCancelarPedido.setEnabled(false);
    }

    public void recebeOperador(String operador, String cargo) {

        lblOperador.setText(operador);
        LblCargo.setText(cargo);

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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        LblCargo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jcomboPedido = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalhe = new javax.swing.JTable();
        btnListar = new javax.swing.JButton();
        lblNumeroMesa = new javax.swing.JLabel();
        lblNumeroMesa1 = new javax.swing.JLabel();
        lblNumeroMesa2 = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        lblRemoverItemDoPedido = new javax.swing.JLabel();
        txtQtd = new javax.swing.JTextField();
        txtIDItem = new javax.swing.JTextField();
        btnCancelarPedido = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(243, 156, 18));
        jPanel1.setForeground(new java.awt.Color(243, 156, 18));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel2.setText("Gerenciamento");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(110, 20, 260, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/pedidos.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(60, 10, 64, 64);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel5.setText("de Pedidos");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(290, 50, 160, 32);

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("Operador");
        jPanel1.add(lblOperador);
        lblOperador.setBounds(460, 50, 90, 32);

        LblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        LblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        LblCargo.setText("Cargo");
        jPanel1.add(LblCargo);
        LblCargo.setBounds(560, 50, 100, 30);

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("X");
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
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(640, 0, 40, 30);

        jcomboPedido.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jcomboPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboPedidoActionPerformed(evt);
            }
        });

        tblDetalhe.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        tblDetalhe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "PRODUTO", "QUANTIDADE", "VLR UNITÁRIO R$", "VLR TOTAL R$"
            }
        ));
        tblDetalhe.setRowHeight(22);
        tblDetalhe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalheMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalhe);

        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        lblNumeroMesa.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        lblNumeroMesa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lblNumeroMesa1.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        lblNumeroMesa1.setText("N. Pedido:");

        lblNumeroMesa2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        lblNumeroMesa2.setText("N. Mesa:");

        lblRemoverItemDoPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        lblRemoverItemDoPedido.setText("Remover item do pedido");
        lblRemoverItemDoPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRemoverItemDoPedidoMouseClicked(evt);
            }
        });

        btnCancelarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fechar.png"))); // NOI18N
        btnCancelarPedido.setText("Cancelar Pedido");
        btnCancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(lblRemoverItemDoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNumeroMesa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcomboPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDItem, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelarPedido))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNumeroMesa2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNumeroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumeroMesa1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcomboPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIDItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumeroMesa2)
                    .addComponent(lblNumeroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRemoverItemDoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnListar, jcomboPedido});

        setSize(new java.awt.Dimension(678, 446));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // Fecha janela
        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // Lista produtos do pedido
        try {
            tblDetalhe.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedidoId(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString())));
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnListarActionPerformed

    private void jcomboPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboPedidoActionPerformed
        try {
            String numeroPedido = jcomboPedido.getSelectedItem().toString();
            lblNumeroMesa.setText(cm.localizaNumeroMesa(numeroPedido));
        } catch (NullPointerException e) {
            lblNumeroMesa.setText(null);
            btnCancelarPedido.setEnabled(false);
        }
    }//GEN-LAST:event_jcomboPedidoActionPerformed

    private void tblDetalheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalheMouseClicked
        // Captura número da linha selecionada 

        int linha = tblDetalhe.getSelectedRow();
        txtIdProduto.setText(tblDetalhe.getModel().getValueAt(linha, 0).toString());
        txtQtd.setText(tblDetalhe.getModel().getValueAt(linha, 2).toString());
        txtIDItem.setText(tblDetalhe.getModel().getValueAt(linha, 5).toString());
        lblRemoverItemDoPedido.setEnabled(true);
    }//GEN-LAST:event_tblDetalheMouseClicked

    private void lblRemoverItemDoPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoverItemDoPedidoMouseClicked
        if (lblRemoverItemDoPedido.isEnabled()) {

            // Remove item do pedido e devolte ao estoque
            int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este item do pedido?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {

                if (cp.excluiItemPedido(txtIDItem.getText())) {
                    JOptionPane.showMessageDialog(null, "Item removido do pedido com sucesso!");
                    lblRemoverItemDoPedido.setEnabled(false);
                    // Devolve produdto ao estoque
                    if (ce.entradaDeProduto(txtIdProduto.getText(), txtQtd.getText())) {
                        JOptionPane.showMessageDialog(null, "Produto devolvido ao estoque com sucesso!");
                        // Inicio do Registro de Log
                        l.setFuncionalidade("Devolução");
                        l.setUsuario(lblOperador.getText());
                        l.setDescricao(l.getUsuario() + " removeu o item " + txtIDItem.getText() + " do pedido ->" + jcomboPedido.getSelectedItem().toString());
                        l.gravaLog(l);
                        // Fim do Registro de Log

                        // Registra Movimentação
                        ce.registraMovimentacao(txtIdProduto.getText(), txtQtd.getText(), ce.localizaIdOperacao("Devolução"), "O cliente desistiu do produto");
                        tblDetalhe.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedidoId(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString())));
                        ResultSet rs = cp.detalhePorPedidoId(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString());
                        try {
                            if (rs.next()) {

                            } else {
                                btnCancelarPedido.setEnabled(true);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(TelaGerenciarPedido.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível devolver o produto ao estoque!");

                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Remoção cancelada com sucesso!");

            }
        }

    }//GEN-LAST:event_lblRemoverItemDoPedidoMouseClicked

    private void btnCancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPedidoActionPerformed
        // Solicita ao usuário a confirmação do pedido
        int op = JOptionPane.showConfirmDialog(null, "Confirma o cancelamento do pedido?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {
            // Captura o número do pedido antes da exclusão
            String nPedido = jcomboPedido.getSelectedItem().toString();
            String nmesa = lblNumeroMesa.getText();
            // Exclui pedido
            if (cp.cancelaPedido(Integer.parseInt(jcomboPedido.getSelectedItem().toString()))) {
                JOptionPane.showMessageDialog(null, "Pedido cancelado com sucesso!");
                // Libera a mesa trocando seu status para 0 - livre
                cm.trocaStatusMesa(nmesa, "0");
                cp.carregaComboPedido(jcomboPedido);
                // Registra log da operação              
                l.setUsuario(lblOperador.getText());
                l.setFuncionalidade("Cancelamento Pedido");
                l.setDescricao(l.getUsuario() + " cancelou o pedido ->" + nPedido);
            }
        }
    }//GEN-LAST:event_btnCancelarPedidoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaGerenciarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGerenciarPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblCargo;
    private javax.swing.JButton btnCancelarPedido;
    private javax.swing.JButton btnListar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcomboPedido;
    private javax.swing.JLabel lblNumeroMesa;
    private javax.swing.JLabel lblNumeroMesa1;
    private javax.swing.JLabel lblNumeroMesa2;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblRemoverItemDoPedido;
    private javax.swing.JTable tblDetalhe;
    private javax.swing.JTextField txtIDItem;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtQtd;
    // End of variables declaration//GEN-END:variables
}
