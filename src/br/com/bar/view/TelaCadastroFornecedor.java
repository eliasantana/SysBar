/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.Fornecedor;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerFornecedor;
import java.util.Calendar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaCadastroFornecedor extends javax.swing.JFrame {

    ControlerFornecedor cf = new ControlerFornecedor();
    Log log = new Log();
    Util u = new Util();

    /**
     * Creates new form TelaCadastroFornecedor
     */
    public TelaCadastroFornecedor() {
        initComponents();
        tblFornecedores.setModel(DbUtils.resultSetToTableModel(cf.listaFornecedor()));
        txtIdFor.setVisible(false);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        
    }

    public void recebeOperador(String operador, String cargo) {

        lblCargo.setText(cargo);
        lblOperador.setText(operador);
        Calendar c = Calendar.getInstance();
        lblData.setText(u.formataDataBr(c.getTime()));

    }

    private void limparform() {

        txtFornecedor.setText(null);
        txtTelefone.setText(null);
        txtEmail.setText(null);

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
        jLabel3 = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        txtIdFor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtFornecedor = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFornecedores = new javax.swing.JTable();
        txtTelefone = new javax.swing.JFormattedTextField();
        btnSalvar = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(243, 156, 18));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel1.setText("de Fornecedores");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(180, 50, 270, 48);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnFornecedor.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 0, 116, 100);

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("Operador");
        jPanel1.add(lblOperador);
        lblOperador.setBounds(160, 110, 120, 32);
        jPanel1.add(jLabel12);
        jLabel12.setBounds(122, 156, 42, 0);

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("Cargo");
        jPanel1.add(lblCargo);
        lblCargo.setBounds(270, 110, 110, 30);
        jPanel1.add(txtIdFor);
        txtIdFor.setBounds(388, 156, 34, 20);

        jPanel3.setBackground(new java.awt.Color(52, 73, 94));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("X");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(440, 0, 50, 38);

        lblData.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/calendario24x24.png"))); // NOI18N
        lblData.setText("jLabel2");
        jPanel1.add(lblData);
        lblData.setBounds(380, 110, 100, 30);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel2.setText("Cadastro");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(120, 10, 135, 48);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-2, 0, 490, 150);

        jLabel4.setText("Fornecedor");

        txtFornecedor.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N

        lblTelefone.setText("Telefone");

        txtEmail.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N

        jLabel6.setText("E-mail");

        tblFornecedores.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        tblFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblFornecedores.setRowHeight(25);
        tblFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFornecedores);

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefone)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtEmail)))
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTelefone, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtEmail, txtTelefone});

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 140, 490, 310);

        btnSalvar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionas32x32.png"))); // NOI18N
        btnSalvar.setText("Adicionar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalvarMouseClicked(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(90, 450, 110, 48);

        btnAlterar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lapis.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlterarMouseClicked(evt);
            }
        });
        getContentPane().add(btnAlterar);
        btnAlterar.setBounds(200, 450, 100, 50);

        btnExcluir.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(300, 450, 110, 50);

        setSize(new java.awt.Dimension(488, 514));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseClicked
        // Cadastra fornecedor

        Fornecedor f = new Fornecedor();
        f.setNome(txtFornecedor.getText());
        f.setEmail(txtEmail.getText());
        f.setTelefone(txtTelefone.getText());

        if (cf.temFornecedor(f)) {
            JOptionPane.showMessageDialog(null, "O fornecedor " + f.getNome() + " já está cadastrado!");
        } else {

            cf.cadastraFornecedor(f);
        }
        // Inicio do Registro de Log

        // Limpa Formulário após inclusão
        limparform();

        Log l = new Log();
        l.setUsuario(lblOperador.getText());
        l.setDescricao("Tela Fornecedores");
        l.setFuncionalidade(l.getUsuario() + " Cadastrou um novo fornecedor " + f.getNome());
        l.gravaLog(l);

        // Atualiza Tabela
        tblFornecedores.setModel(DbUtils.resultSetToTableModel(cf.listaFornecedor()));

    }//GEN-LAST:event_btnSalvarMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // Fecha a janela
        dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btnExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseClicked
        // Executa  método de exclusão de fornecedores 

        if (btnExcluir.isEnabled()){
        Fornecedor f = new Fornecedor();
        f.setCodigo(Integer.parseInt(txtIdFor.getText()));
        f.setNome(txtFornecedor.getText());
            
            int op = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do fornecedor " + f.getNome() + "?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {

                cf.excluirFornecedor(f);
                // Atualiza tabela forncedores
                tblFornecedores.setModel(DbUtils.resultSetToTableModel(cf.listaFornecedor()));
                // Início do Registro de log
                log.setFuncionalidade("Exclusão");
                log.setUsuario(lblOperador.getText());
                log.setDescricao(log.getUsuario() + " Excluiu o fornecedor ->" + f.getNome());
                log.gravaLog(log);
                // Fim do registro de log
            } else {
                JOptionPane.showMessageDialog(null, "Exclusão cancelada com sucesso!");
            }
        }

    }//GEN-LAST:event_btnExcluirMouseClicked

    private void tblFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFornecedoresMouseClicked
        // Seleciona forncedor
        int linha = tblFornecedores.getSelectedRow();
        // captura o id do fornecedor ao clicar sobre ele
        txtIdFor.setText(tblFornecedores.getModel().getValueAt(linha, 0).toString());
        txtFornecedor.setText(tblFornecedores.getModel().getValueAt(linha, 1).toString());
        txtTelefone.setText(tblFornecedores.getModel().getValueAt(linha, 2).toString());
        txtEmail.setText(tblFornecedores.getModel().getValueAt(linha, 3).toString());
        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);

    }//GEN-LAST:event_tblFornecedoresMouseClicked

    private void btnAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseClicked
        // Executa o método de alteração de produto

        
        if (btnAlterar.isEnabled()){            
            Fornecedor f = new Fornecedor();
            f.setCodigo(Integer.parseInt(txtIdFor.getText()));
            f.setNome(txtFornecedor.getText());
            f.setTelefone(txtTelefone.getText());
            f.setEmail(txtEmail.getText());
            int op = JOptionPane.showConfirmDialog(null, "Deseja realmente realizar a alteração?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {

                cf.alteraFornecedor(f);
                tblFornecedores.setModel(DbUtils.resultSetToTableModel(cf.listaFornecedor()));
                // Inicio do Resigtro de Log
                log.setUsuario(lblOperador.getText());
                log.setFuncionalidade("Alterar");
                log.setDescricao(log.getUsuario() + " alterou o fornecedor -> " + f.getNome());
                // Fim do registro de log
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada!");
            }
        }
            
    }//GEN-LAST:event_btnAlterarMouseClicked

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
            java.util.logging.Logger.getLogger(TelaCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroFornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAlterar;
    private javax.swing.JLabel btnExcluir;
    private javax.swing.JLabel btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JTable tblFornecedores;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFornecedor;
    private javax.swing.JTextField txtIdFor;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
