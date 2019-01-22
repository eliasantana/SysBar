/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.Funcionario;
import br.com.br.controler.ControlerFuncionario;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elias Santana
 */
public class TelaPesquisaFuncionario extends javax.swing.JFrame {

    ControlerFuncionario cf = new ControlerFuncionario();
    Log l = new Log();

    public TelaPesquisaFuncionario() {
        initComponents();
        bloqueiaBotoes();
        
        //Matem a tela de pesquisa a frente da janela anterior.

    }

    public void recebeOperador(String operador, String perfil) {
        lblOperador.setText(operador);
        lblPerfil.setText(perfil);
    }

    public void atualizaTabela(String nome) {
        // Realiza Pesquisa
        txtFuncionario.setText(nome);
        tblFuncionario.setModel(DbUtils.resultSetToTableModel(cf.carregaFuncionario(nome)));
        
        System.out.println(nome);
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
        lblOperador = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtFuncionario = new javax.swing.JTextField();
        lblPesquisa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionario = new javax.swing.JTable();
        lblExcluir = new javax.swing.JLabel();
        lblAdicionar = new javax.swing.JLabel();
        lblAlterar = new javax.swing.JLabel();
        lblConsultar = new javax.swing.JLabel();

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 48)); // NOI18N
        jLabel3.setText("Cadastro ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel5.setText("de Funcionários");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/funcionario (3).png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("jLabel2");
        jPanel1.add(lblOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));

        lblPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblPerfil.setText("jLabel2");
        jPanel1.add(lblPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 640, 120);

        jLabel1.setText("Funcionário");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 130, 150, 14);

        txtFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFuncionarioKeyPressed(evt);
            }
        });
        getContentPane().add(txtFuncionario);
        txtFuncionario.setBounds(10, 150, 349, 30);

        lblPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        lblPesquisa.setText("Pesquisar");
        lblPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPesquisaMouseClicked(evt);
            }
        });
        getContentPane().add(lblPesquisa);
        lblPesquisa.setBounds(370, 150, 120, 32);

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
                "CÓD. INTERNO", "NOME", "CPF", "RG", "CELULAR"
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
        jScrollPane1.setBounds(10, 190, 620, 259);

        lblExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        lblExcluir.setText("Excluir");
        lblExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(lblExcluir);
        lblExcluir.setBounds(450, 450, 100, 50);

        lblAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionas32x32.png"))); // NOI18N
        lblAdicionar.setText("Adicionar");
        lblAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(lblAdicionar);
        lblAdicionar.setBounds(100, 450, 100, 50);

        lblAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lapis.png"))); // NOI18N
        lblAlterar.setText("Alterar");
        lblAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlterarMouseClicked(evt);
            }
        });
        getContentPane().add(lblAlterar);
        lblAlterar.setBounds(340, 450, 100, 50);

        lblConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/pasta32x32 (2).png"))); // NOI18N
        lblConsultar.setText("Consultar");
        lblConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblConsultarMouseClicked(evt);
            }
        });
        getContentPane().add(lblConsultar);
        lblConsultar.setBounds(220, 450, 100, 50);

        setSize(new java.awt.Dimension(636, 496));
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
        tcf.recebeOperador(lblOperador.getText(), lblPerfil.getText(), "Adicionar");
        tcf.setVisible(true);
        

    }//GEN-LAST:event_lblAdicionarMouseClicked

    private void tblFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionarioMouseClicked
        // Desbloqueia botões da tela de pesquisa e habilita os botões caso a lista esteja preechida.
        try {
            tblFuncionario.getModel().getValueAt(0,0).toString();
            desbloqueiaBotoes();
            lblConsultar.setEnabled(true);
            
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_tblFuncionarioMouseClicked

    private void lblAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlterarMouseClicked
        try {
            if (lblAlterar.isEnabled()) {
                int linha = tblFuncionario.getSelectedRow();
                Funcionario f = new Funcionario();
                f.setId(String.valueOf(tblFuncionario.getModel().getValueAt(linha, 0).toString()));
                Funcionario fLocalizado = cf.localizaFuncionario(f.getId());

                TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
                tcf.recebeFuncionario(fLocalizado);
                tcf.recebeOperador(lblOperador.getText(), lblPerfil.getText(), "Alterar");
                tcf.setVisible(true);
               
                this.dispose();
            }
        } catch (NullPointerException e) {
            lblAlterar.setEnabled(false);
        }


    }//GEN-LAST:event_lblAlterarMouseClicked

    private void lblExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExcluirMouseClicked
        // Solicita confirmação do usuário antes de excluir
        if (lblExcluir.isEnabled()) {

            int op = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do funcionário?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (op == JOptionPane.YES_OPTION) {
                int linha = tblFuncionario.getSelectedRow();
                l.setFuncionalidade("Excluir");
                l.setDescricao(l.getUsuario() + " Excluiu ->" + tblFuncionario.getModel().getValueAt(linha, 1).toString() + " do cadastro de funcionários");
                l.gravaLog(l);
                //Fim do registro de log   
                //Atualiza tela
                if (cf.excluirFuncionario(tblFuncionario.getModel().getValueAt(linha, 0).toString())) {
                    tblFuncionario.setModel(DbUtils.resultSetToTableModel(cf.carregaFuncionario("")));
                    JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
                    // Início do registro de log

                }

            } else {
                JOptionPane.showMessageDialog(null, "Exclusão cancelada com sucesso!");
            }
        }
    }//GEN-LAST:event_lblExcluirMouseClicked

    private void txtFuncionarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFuncionarioKeyPressed
        // Realiza Pesquisa
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            tblFuncionario.setModel(DbUtils.resultSetToTableModel(cf.carregaFuncionario(txtFuncionario.getText())));
        }
    }//GEN-LAST:event_txtFuncionarioKeyPressed

    private void lblConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConsultarMouseClicked
        /*
        * Chama a tela de cadastro com todos os campos desbloqueados
         */
        if (lblConsultar.isEnabled()){
            
            try {

                int linha = tblFuncionario.getSelectedRow();
                Funcionario f = new Funcionario();
                f.setId(String.valueOf(tblFuncionario.getModel().getValueAt(linha, 0).toString()));
                Funcionario fLocalizado = cf.localizaFuncionario(f.getId());

                TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
                tcf.recebeFuncionario(fLocalizado);
                tcf.recebeOperador(lblOperador.getText(), lblPerfil.getText(), "Detalhe");
                tcf.setVisible(true);
               
                this.dispose();

            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_lblConsultarMouseClicked

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
    private javax.swing.JLabel lblConsultar;
    private javax.swing.JLabel lblExcluir;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JTable tblFuncionario;
    private javax.swing.JTextField txtFuncionario;
    // End of variables declaration//GEN-END:variables

    private void bloqueiaBotoes() {

        lblAlterar.setEnabled(false);
        lblExcluir.setEnabled(false);
        lblConsultar.setEnabled(false);
    }

    private void desbloqueiaBotoes() {

        lblAlterar.setEnabled(true);
        lblExcluir.setEnabled(true);
    }

}