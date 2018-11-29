/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.model.Grupo;
import br.com.br.controler.ControlerGrupo;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaGruposFinanceiro extends javax.swing.JFrame {

    ControlerGrupo cg = new ControlerGrupo();

    /**
     * Creates new form TelaPaametro
     */
    public TelaGruposFinanceiro() {
        initComponents();
        panelTabela.setVisible(false);
        txtIdGrupo.setVisible(false);
        txtNome.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblFechar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeGrupo = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnExceluir = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JPanel();
        lblConsultar = new javax.swing.JLabel();
        panelTabela = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGruposFinanceiro = new javax.swing.JTable();
        txtIdGrupo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SysBar - Parâmetro");
        setBackground(new java.awt.Color(44, 62, 80));
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(243, 156, 18));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(52, 73, 94));
        jLabel1.setText("Grupos");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(114, 12, 147, 64);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/grupos2 (2).png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 20, 64, 64);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(52, 73, 94));
        jLabel6.setText("Financeiro");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(267, 68, 78, 25);

        jPanel3.setBackground(new java.awt.Color(38, 53, 61));

        lblFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFechar.setForeground(new java.awt.Color(255, 255, 255));
        lblFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechar.setText("X");
        lblFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(360, 0, 40, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 110);

        jPanel2.setBackground(new java.awt.Color(38, 53, 61));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nome do Grupo");

        txtNomeGrupo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N

        btnSalvar.setBackground(new java.awt.Color(38, 53, 61));
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalvarMouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/salvarCinza.png"))); // NOI18N
        jLabel3.setText("Salvar");

        javax.swing.GroupLayout btnSalvarLayout = new javax.swing.GroupLayout(btnSalvar);
        btnSalvar.setLayout(btnSalvarLayout);
        btnSalvarLayout.setHorizontalGroup(
            btnSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSalvarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        btnSalvarLayout.setVerticalGroup(
            btnSalvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSalvarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExceluir.setBackground(new java.awt.Color(38, 53, 61));
        btnExceluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExceluirMouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lixeiraCinza.png"))); // NOI18N
        jLabel4.setText("Exluir");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnExceluirLayout = new javax.swing.GroupLayout(btnExceluir);
        btnExceluir.setLayout(btnExceluirLayout);
        btnExceluirLayout.setHorizontalGroup(
            btnExceluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnExceluirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        btnExceluirLayout.setVerticalGroup(
            btnExceluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExceluirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEditar.setBackground(new java.awt.Color(38, 53, 61));
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarMouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lapisCinza.png"))); // NOI18N
        jLabel5.setText("Alterar");

        javax.swing.GroupLayout btnEditarLayout = new javax.swing.GroupLayout(btnEditar);
        btnEditar.setLayout(btnEditarLayout);
        btnEditarLayout.setHorizontalGroup(
            btnEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEditarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        btnEditarLayout.setVerticalGroup(
            btnEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEditarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnConsultar.setBackground(new java.awt.Color(38, 53, 61));
        btnConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConsultarMouseClicked(evt);
            }
        });

        lblConsultar.setBackground(new java.awt.Color(153, 153, 153));
        lblConsultar.setForeground(new java.awt.Color(255, 255, 255));
        lblConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/tabelaCinza.png"))); // NOI18N
        lblConsultar.setText("Consultar");

        javax.swing.GroupLayout btnConsultarLayout = new javax.swing.GroupLayout(btnConsultar);
        btnConsultar.setLayout(btnConsultarLayout);
        btnConsultarLayout.setHorizontalGroup(
            btnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnConsultarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblConsultar)
                .addContainerGap())
        );
        btnConsultarLayout.setVerticalGroup(
            btnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConsultar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTabela.setBackground(new java.awt.Color(44, 62, 80));
        panelTabela.setLayout(null);

        tblGruposFinanceiro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        tblGruposFinanceiro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblGruposFinanceiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGruposFinanceiroMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGruposFinanceiro);

        panelTabela.add(jScrollPane2);
        jScrollPane2.setBounds(0, 0, 400, 190);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNomeGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtIdGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExceluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelTabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtIdGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExceluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 110, 400, 360);

        setSize(new java.awt.Dimension(395, 470));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        // Fecha janela deconfigurações
        this.dispose();
    }//GEN-LAST:event_lblFecharMouseClicked

    private void btnConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarMouseClicked
        // Exibe painel da tabela grupos
        String nomeBotao = lblConsultar.getText();
        
        if (nomeBotao.equals("Consultar")) {
            panelTabela.setVisible(true);
            lblConsultar.setText("Ocultar");
            tblGruposFinanceiro.setModel(DbUtils.resultSetToTableModel(cg.atualizaTabela(tblGruposFinanceiro)));
        } else {
            panelTabela.setVisible(false);
            lblConsultar.setText("Consultar");
        }

    }//GEN-LAST:event_btnConsultarMouseClicked

    private void btnSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseClicked
        // Adiciona um grupo Financeiro
        Grupo g = new Grupo();
        g.setNomeGrupo(txtNomeGrupo.getText());

        if (g.getNomeGrupo().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o nome do grupo para continuar!");
        } else {
            if (cg.adicionarGrupo(g)) {
                JOptionPane.showMessageDialog(null, "Grupo adicionado com sucesso!");
                limpaForm();
                tblGruposFinanceiro.setModel(DbUtils.resultSetToTableModel(cg.atualizaTabela(tblGruposFinanceiro)));

            }

        }
    }//GEN-LAST:event_btnSalvarMouseClicked

    private void btnExceluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExceluirMouseClicked
        
             

    }//GEN-LAST:event_btnExceluirMouseClicked

    private void tblGruposFinanceiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGruposFinanceiroMouseClicked
        // Seleciona Grupo

        int linha = tblGruposFinanceiro.getSelectedRow();
        txtIdGrupo.setText(tblGruposFinanceiro.getModel().getValueAt(linha, 0).toString());
        txtNome.setText(tblGruposFinanceiro.getModel().getValueAt(linha, 1).toString());
        txtNomeGrupo.setText(tblGruposFinanceiro.getModel().getValueAt(linha, 1).toString());

    }//GEN-LAST:event_tblGruposFinanceiroMouseClicked
        // Exclui grupo financeiro
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Grupo g = new Grupo();

        g.setId(txtIdGrupo.getText());
        g.setNomeGrupo(txtNome.getText());
        
        int op = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do grupo " + g.getNomeGrupo()+ "?", "Atenção",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
        
        if (op == JOptionPane.YES_OPTION){
                
            if (cg.excluirGrupo(g)){
                JOptionPane.showMessageDialog(null, "Grupo Excluído com sucesso!");
                limpaForm();
                tblGruposFinanceiro.setModel(DbUtils.resultSetToTableModel(cg.atualizaTabela(tblGruposFinanceiro)));
            }else {
                JOptionPane.showMessageDialog(null, "O peração Cancelada!");
                
            }
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        // Altera Gegistro
        
        Grupo g = new Grupo();
        g.setId(txtIdGrupo.getText());
        g.setNomeGrupo(txtNomeGrupo.getText());
        
        if(cg.alteraGrupo(g)){
            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
            limpaForm();
            tblGruposFinanceiro.setModel(DbUtils.resultSetToTableModel(cg.atualizaTabela(tblGruposFinanceiro)));
        }
        
    }//GEN-LAST:event_btnEditarMouseClicked

    private void limpaForm() {
        txtNomeGrupo.setText(null);
        txtNome.setText(null);
        txtIdGrupo.setText(null);
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
            java.util.logging.Logger.getLogger(TelaGruposFinanceiro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGruposFinanceiro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGruposFinanceiro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGruposFinanceiro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGruposFinanceiro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnConsultar;
    private javax.swing.JPanel btnEditar;
    private javax.swing.JPanel btnExceluir;
    private javax.swing.JPanel btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblConsultar;
    private javax.swing.JLabel lblFechar;
    private javax.swing.JPanel panelTabela;
    private javax.swing.JTable tblGruposFinanceiro;
    private javax.swing.JTextField txtIdGrupo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeGrupo;
    // End of variables declaration//GEN-END:variables
}
