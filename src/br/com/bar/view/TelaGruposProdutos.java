/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.Grupo;
import br.com.bar.model.TableModelGrupoProduto;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerGrupo;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaGruposProdutos extends javax.swing.JFrame {

    ControlerGrupo cg = new ControlerGrupo();
    Log l = new Log();
    Util u = new Util();
    TableModelGrupoProduto modelGrupo = new TableModelGrupoProduto();
    /**
     * Creates new form TelaPaametro
     */
    public TelaGruposProdutos() {
        initComponents();
        panelTabela.setVisible(false);
        txtIdGrupo.setVisible(false);
        txtNome.setVisible(false);
        lblOperador.setVisible(false);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
       

    }
    
    public void recebeOperador(String operador){
        lblOperador.setText(operador);
       
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
        lblOperador = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblFechar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeGrupo = new javax.swing.JTextField();
        panelAdicionar = new javax.swing.JPanel();
        btnAdicionar = new javax.swing.JLabel();
        panelConsultar = new javax.swing.JPanel();
        lblConsultar = new javax.swing.JLabel();
        panelAlterar = new javax.swing.JPanel();
        btnAlterar = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JLabel();
        panelExcluir = new javax.swing.JPanel();
        panelTabela = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGrupos = new javax.swing.JTable();
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
        jLabel1.setBounds(130, 10, 147, 64);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/grupo128x128.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(0, 0, 120, 90);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(52, 73, 94));
        jLabel6.setText("de Produtos");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(250, 70, 160, 25);

        lblOperador.setText("jLabel4");
        jPanel1.add(lblOperador);
        lblOperador.setBounds(24, 93, 34, 14);

        jPanel3.setBackground(new java.awt.Color(44, 62, 80));

        lblFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFechar.setForeground(new java.awt.Color(255, 255, 255));
        lblFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        lblFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(lblFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(390, 0, 40, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 430, 120);

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nome do Grupo");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        txtNomeGrupo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jPanel2.add(txtNomeGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 300, 39));

        panelAdicionar.setBackground(new java.awt.Color(44, 62, 80));
        panelAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAdicionarMouseClicked(evt);
            }
        });

        btnAdicionar.setBackground(new java.awt.Color(153, 153, 153));
        btnAdicionar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        btnAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionar.png"))); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdicionarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelAdicionarLayout = new javax.swing.GroupLayout(panelAdicionar);
        panelAdicionar.setLayout(panelAdicionarLayout);
        panelAdicionarLayout.setHorizontalGroup(
            panelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdicionarLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(btnAdicionar))
        );
        panelAdicionarLayout.setVerticalGroup(
            panelAdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdicionarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
        );

        jPanel2.add(panelAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 108, -1, -1));

        panelConsultar.setBackground(new java.awt.Color(44, 62, 80));
        panelConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelConsultarMouseClicked(evt);
            }
        });

        lblConsultar.setBackground(new java.awt.Color(153, 153, 153));
        lblConsultar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblConsultar.setForeground(new java.awt.Color(255, 255, 255));
        lblConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/localizar32x32.png"))); // NOI18N
        lblConsultar.setText("Consultar");
        lblConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblConsultarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelConsultarLayout = new javax.swing.GroupLayout(panelConsultar);
        panelConsultar.setLayout(panelConsultarLayout);
        panelConsultarLayout.setHorizontalGroup(
            panelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConsultarLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(lblConsultar))
        );
        panelConsultarLayout.setVerticalGroup(
            panelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
        );

        jPanel2.add(panelConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        panelAlterar.setBackground(new java.awt.Color(44, 62, 80));
        panelAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAlterarMouseClicked(evt);
            }
        });

        btnAlterar.setBackground(new java.awt.Color(153, 153, 153));
        btnAlterar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lapisCinza.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlterarMouseClicked(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(153, 153, 153));
        btnExcluir.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lixeiraCinza.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExcluirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelAlterarLayout = new javax.swing.GroupLayout(panelAlterar);
        panelAlterar.setLayout(panelAlterarLayout);
        panelAlterarLayout.setHorizontalGroup(
            panelAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlterarLayout.createSequentialGroup()
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAlterarLayout.setVerticalGroup(
            panelAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlterarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.add(panelAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        panelExcluir.setBackground(new java.awt.Color(44, 62, 80));
        panelExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelExcluirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelExcluirLayout = new javax.swing.GroupLayout(panelExcluir);
        panelExcluir.setLayout(panelExcluirLayout);
        panelExcluirLayout.setHorizontalGroup(
            panelExcluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
        );
        panelExcluirLayout.setVerticalGroup(
            panelExcluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        jPanel2.add(panelExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 110, -1, -1));

        panelTabela.setBackground(new java.awt.Color(44, 62, 80));
        panelTabela.setLayout(null);

        tblGrupos.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        tblGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "DESCRIÇÃO"
            }
        ));
        tblGrupos.setRowHeight(20);
        tblGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGruposMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGrupos);

        panelTabela.add(jScrollPane2);
        jScrollPane2.setBounds(20, 0, 390, 170);

        jPanel2.add(panelTabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 168, 420, 182));
        jPanel2.add(txtIdGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 56, -1));
        jPanel2.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 56, -1));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 120, 430, 350);

        setSize(new java.awt.Dimension(429, 468));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        // Fecha janela deconfigurações
        this.dispose();
    }//GEN-LAST:event_lblFecharMouseClicked

    private void panelExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelExcluirMouseClicked
        
       
    }//GEN-LAST:event_panelExcluirMouseClicked

    private void panelAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAdicionarMouseClicked
        
    }//GEN-LAST:event_panelAdicionarMouseClicked

    private void panelConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelConsultarMouseClicked
        
             

    }//GEN-LAST:event_panelConsultarMouseClicked

    private void tblGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGruposMouseClicked
        // Seleciona Grupo

        int linha = tblGrupos.getSelectedRow();
        txtIdGrupo.setText(tblGrupos.getModel().getValueAt(linha, 0).toString());
        txtNome.setText(tblGrupos.getModel().getValueAt(linha, 1).toString());
        txtNomeGrupo.setText(tblGrupos.getModel().getValueAt(linha, 1).toString());
        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);

    }//GEN-LAST:event_tblGruposMouseClicked

    private void btnExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseClicked
       
        Grupo g = new Grupo();

        g.setId(txtIdGrupo.getText());
        g.setNomeGrupo(txtNome.getText());
        if (btnExcluir.isEnabled()){
            
            int op = JOptionPane.showConfirmDialog(this, "Confirma a exclusão do grupo " + g.getNomeGrupo()+"?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            if (op == JOptionPane.YES_OPTION) {

                if (cg.excluirGrupoProduto(g)) {
                   
                    limpaForm();
                    tblGrupos.setModel(DbUtils.resultSetToTableModel(cg.atualizaGrupoProduto(tblGrupos)));
                    // Registro de log
                    l.setUsuario(lblOperador.getText());
                    l.setFuncionalidade("Excluir");
                    l.setDescricao(l.getUsuario() + " excluiu o grupo -> " + g.getNomeGrupo());
                    l.gravaLog(l);
                    JOptionPane.showMessageDialog(this, "Grupo excluído com sucesso!");
                }
                
            }else {
                    JOptionPane.showMessageDialog(this, "Exclusão cancelada com sucesso!");               
            }
        }
    }//GEN-LAST:event_btnExcluirMouseClicked

    private void panelAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAlterarMouseClicked
        
    }//GEN-LAST:event_panelAlterarMouseClicked

    private void btnAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionarMouseClicked
        
        // Adiciona um grupo
        Grupo g = new Grupo();
        g.setNomeGrupo(txtNomeGrupo.getText());
        // Se o grupo de produto for vazio exibe mensagem
        if (g.getNomeGrupo().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do grupo para continuar!");
        } else {
            // Verifica se o grupo de produto existe
            if (cg.temGrupoProduto(txtNomeGrupo.getText())){
                JOptionPane.showMessageDialog(this, "Este grupo já existe!");
            }else {
                
                if (cg.adicionarGrupoProduto(g)) {
                    JOptionPane.showMessageDialog(this, "Grupo adicionado com sucesso!");
                    limpaForm();
                    tblGrupos.setModel(DbUtils.resultSetToTableModel(cg.atualizaGrupoProduto(tblGrupos)));
                    //Regisra log
                    l.setUsuario(lblOperador.getText());
                    l.setFuncionalidade("Salvar");
                    l.setDescricao(l.getUsuario() + " adicionou o grupo -> " + g.getNomeGrupo());
                    l.gravaLog(l);

                }
            }

        }
    }//GEN-LAST:event_btnAdicionarMouseClicked

    private void lblConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConsultarMouseClicked
        String nomeBotao = lblConsultar.getText();
        
        if (nomeBotao.equals("Consultar")) {
            panelTabela.setVisible(true);
            lblConsultar.setText("Ocultar");
            tblGrupos.setModel(DbUtils.resultSetToTableModel(cg.atualizaGrupoProduto(tblGrupos)));
            modelGrupo.redimensionaColunas(tblGrupos);
        } else {
            panelTabela.setVisible(false);
            lblConsultar.setText("Consultar");
        }
        // Exibe painel da tabela grupos
    }//GEN-LAST:event_lblConsultarMouseClicked

    private void btnAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseClicked
        // Altera Registro
        
        Grupo g = new Grupo();
        g.setId(txtIdGrupo.getText());
        g.setNomeGrupo(txtNomeGrupo.getText());
        //Checa se o botão está habilitado
        if (btnAlterar.isEnabled()){
            int op = JOptionPane.showConfirmDialog(this, "Deseja realmente realizar a alteração?","Atenção!",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
            //Solicita confirmação ao usuário
            if (op==JOptionPane.YES_OPTION){
                // Realiza a alteração
                if (cg.alteraGrupoProduto(g)) {
                    JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso!");
                    limpaForm();
                    tblGrupos.setModel(DbUtils.resultSetToTableModel(cg.atualizaGrupoProduto(tblGrupos)));
                    // regisra log
                    l.setUsuario(lblOperador.getText());
                    l.setFuncionalidade("Alterar");
                    l.setDescricao(l.getUsuario() + " alterou o grupo " + g.getNomeGrupo());
                    l.gravaLog(l);
                }
            }else {
                 JOptionPane.showMessageDialog(this, "Alteração cancelada com sucesso!");
            }
        }
        
    }//GEN-LAST:event_btnAlterarMouseClicked

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
            java.util.logging.Logger.getLogger(TelaGruposProdutos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGruposProdutos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGruposProdutos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGruposProdutos.class
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGruposProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAdicionar;
    private javax.swing.JLabel btnAlterar;
    private javax.swing.JLabel btnExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel lblOperador;
    private javax.swing.JPanel panelAdicionar;
    private javax.swing.JPanel panelAlterar;
    private javax.swing.JPanel panelConsultar;
    private javax.swing.JPanel panelExcluir;
    private javax.swing.JPanel panelTabela;
    private javax.swing.JTable tblGrupos;
    private javax.swing.JTextField txtIdGrupo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeGrupo;
    // End of variables declaration//GEN-END:variables
}
