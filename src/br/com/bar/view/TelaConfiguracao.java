/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.util.Util;

/**
 *
 * @author elias
 */
public class TelaConfiguracao extends javax.swing.JFrame {

    Util u = new Util();
    
    public TelaConfiguracao() {
        initComponents();      
        
        lblCargo.setVisible(false);
        lblOperador.setVisible(false);
    }
   public void recebeOperador (String operador, String cargo){
       
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

        bordas = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnFuncionario = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnDadosEmpresa = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblFechar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblCargo = new javax.swing.JLabel();
        lblitulo = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SysBar - Parâmetro");
        setBackground(new java.awt.Color(44, 62, 80));
        setUndecorated(true);
        getContentPane().setLayout(null);

        bordas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bordas.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(38, 53, 61));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        btnFuncionario.setBackground(new java.awt.Color(204, 204, 204));
        btnFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFuncionarioMouseClicked(evt);
            }
        });
        btnFuncionario.setLayout(null);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/bd64x64.png"))); // NOI18N
        btnFuncionario.add(jLabel8);
        jLabel8.setBounds(0, 10, 150, 70);

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(52, 73, 94));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Banco de Dados");
        btnFuncionario.add(jLabel18);
        jLabel18.setBounds(0, 70, 150, 40);

        btnDadosEmpresa.setBackground(new java.awt.Color(204, 204, 204));
        btnDadosEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDadosEmpresaMouseClicked(evt);
            }
        });
        btnDadosEmpresa.setLayout(null);

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(52, 73, 94));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Dados Empresa");
        btnDadosEmpresa.add(jLabel28);
        jLabel28.setBounds(0, 70, 160, 40);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cadastro-64.png"))); // NOI18N
        btnDadosEmpresa.add(jLabel29);
        jLabel29.setBounds(0, 10, 160, 70);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(btnFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnDadosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDadosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        bordas.add(jPanel2);
        jPanel2.setBounds(20, 90, 570, 190);

        jPanel3.setBackground(new java.awt.Color(38, 53, 61));

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

        bordas.add(jPanel3);
        jPanel3.setBounds(570, 0, 40, 40);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(null);

        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("jLabel3");
        jPanel1.add(lblCargo);
        lblCargo.setBounds(10, 60, 110, 30);

        lblitulo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        lblitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblitulo.setText("Configurações do Sistema");
        jPanel1.add(lblitulo);
        lblitulo.setBounds(0, 0, 520, 60);

        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("jLabel3");
        jPanel1.add(lblOperador);
        lblOperador.setBounds(90, 60, 110, 30);

        bordas.add(jPanel1);
        jPanel1.setBounds(50, 0, 520, 310);

        getContentPane().add(bordas);
        bordas.setBounds(0, 0, 610, 311);

        setSize(new java.awt.Dimension(610, 312));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        // Fecha janela deconfigurações
        this.dispose();
    }//GEN-LAST:event_lblFecharMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void btnDadosEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDadosEmpresaMouseClicked
        // Chama tela de Cadastro de dados da empresa
        TelaCadastroEmpresa empresa = new TelaCadastroEmpresa();
        empresa.recebeOperador(lblOperador.getText(), lblCargo.getText());
        empresa.setVisible(true);
    }//GEN-LAST:event_btnDadosEmpresaMouseClicked

    private void btnFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFuncionarioMouseClicked
        TelaPametro paraPametro = new TelaPametro();
        paraPametro.setVisible(true);

    }//GEN-LAST:event_btnFuncionarioMouseClicked

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
            java.util.logging.Logger.getLogger(TelaConfiguracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaConfiguracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaConfiguracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaConfiguracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaConfiguracao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bordas;
    private javax.swing.JPanel btnDadosEmpresa;
    private javax.swing.JPanel btnFuncionario;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblFechar;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblitulo;
    // End of variables declaration//GEN-END:variables
}
