/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.dao.Log;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerFuncionario;
import br.com.br.controler.ControlerLog;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author elias
 */
public class TelaLog extends javax.swing.JFrame {

    /**
     * Creates new form TelaLog
     */
    Log l = new Log();
    ControlerLog lc = new ControlerLog();
    ControlerFuncionario f = new ControlerFuncionario();
    Connection conexao = ConexaoBd.conector();
    Util u = new Util();

    public TelaLog() {
        initComponents();
        f.carregaComboFuncionario(comboUsuario);
        Date dataAtual = new Date();
        jDateChooserInicio.setDate(dataAtual);
        jDateChooserFim.setDate(dataAtual);
        btnImprimir.setEnabled(false);
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
        lbltitulo = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbltitulo1 = new javax.swing.JLabel();
        painelCentral = new javax.swing.JPanel();
        jDateChooserInicio = new com.toedter.calendar.JDateChooser();
        jDateChooserFim = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLog = new javax.swing.JTable();
        comboUsuario = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JLabel();
        btnListar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(243, 156, 18));
        jPanel1.setForeground(new java.awt.Color(243, 156, 18));

        lbltitulo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        lbltitulo.setText("de Logs");

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("jLabel6");

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("lblCargo");

        jPanel2.setBackground(new java.awt.Color(52, 73, 94));

        btnFechar.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFechar.setForeground(new java.awt.Color(255, 255, 255));
        btnFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        btnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/log64x64.png"))); // NOI18N

        lbltitulo1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        lbltitulo1.setText("Gerenciamento ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbltitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addComponent(lblOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbltitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOperador)
                    .addComponent(lblCargo))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1040, 130);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel2.setText("Início");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel3.setText("Fim");

        tblLog.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "DATA", "HORA", "FUNCIONALIDADE", "DESCRIÇÃO"
            }
        ));
        jScrollPane1.setViewportView(tblLog);

        comboUsuario.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        comboUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboUsuarioItemStateChanged(evt);
            }
        });
        comboUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUsuarioActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel4.setText("Usuário");

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Impressora32x32.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimirMouseClicked(evt);
            }
        });

        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/localizar32x32.png"))); // NOI18N
        btnListar.setText("Pesquisar");
        btnListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout painelCentralLayout = new javax.swing.GroupLayout(painelCentral);
        painelCentral.setLayout(painelCentralLayout);
        painelCentralLayout.setHorizontalGroup(
            painelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCentralLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(painelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(painelCentralLayout.createSequentialGroup()
                        .addGroup(painelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFim, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(painelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelCentralLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(497, Short.MAX_VALUE))
                            .addGroup(painelCentralLayout.createSequentialGroup()
                                .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        painelCentralLayout.setVerticalGroup(
            painelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCentralLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(painelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooserInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(painelCentral);
        painelCentral.setBounds(0, 90, 1030, 440);

        setSize(new java.awt.Dimension(1038, 530));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseClicked
        // Fecha Janela
        dispose();
    }//GEN-LAST:event_btnFecharMouseClicked

    private void btnListarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListarMouseClicked
        // Lista todos os logs
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if ("Selecione...".equals(comboUsuario.getSelectedItem().toString())){
            JOptionPane.showMessageDialog(null, "Selecione um usuário!");
        }else {
            
            try {
                // Pega data do componente e converte para string
                String dtInicio = df.format(jDateChooserInicio.getDate().getTime());
                String dtFim = df.format(jDateChooserFim.getDate().getTime());
                // Lista a cessos com base no intervalor de datas informardos e habilita ou desabilita o botão conforme resultado da pesquisa.
                //tblLog.setModel(DbUtils.resultSetToTableModel(lc.listaLog(tblLog, dtInicio, dtFim, comboUsuario.getSelectedItem().toString())));

                lc.listaLog(tblLog, dtInicio, dtFim, comboUsuario.getSelectedItem().toString(), btnImprimir);

            } catch (Exception e) {
                System.out.println("br.com.bar.view.TelaLog.btnListarMouseClicked()" + e);

            }
        }


    }//GEN-LAST:event_btnListarMouseClicked

    private void comboUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUsuarioActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_comboUsuarioActionPerformed

    private void comboUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboUsuarioItemStateChanged
        // TODO add your handling code here:
        //String funcionario = comboUsuario.getSelectedItem().toString();
        // tblLog.setModel(DbUtils.resultSetToTableModel(lc.listaLog(tblLog, funcionario)));

    }//GEN-LAST:event_comboUsuarioItemStateChanged

    private void btnImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimirMouseClicked
        
        // Imprime o resultado da pesquisa e exibe o relatório em tela
        if (btnImprimir.isEnabled()){
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dtIni = df.format(jDateChooserInicio.getDate().getTime());
            String dtfim = df.format(jDateChooserFim.getDate().getTime());

            HashMap param = new HashMap();
            param.put("nome", comboUsuario.getSelectedItem().toString());
            param.put("dtinicio", dtIni);
            param.put("dtfim", dtfim);
            // Adiciona o período ao relatório as datas no formato BR 
            param.put("dataInicio", u.formataDataBr(jDateChooserInicio.getDate()));
            param.put("dataFim", u.formataDataBr(jDateChooserFim.getDate()));

            try {
                JasperPrint print = JasperFillManager.fillReport("c:/SysBar/rel/relatorioDeLog.jasper", param, conexao);
                JasperViewer.viewReport(print, false);

            } catch (JRException e) {
                System.out.println("br.com.bar.view.TelaLog.jLabel5MouseClicked() + e");
            }
        }

    }//GEN-LAST:event_btnImprimirMouseClicked

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
            java.util.logging.Logger.getLogger(TelaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnFechar;
    private javax.swing.JLabel btnImprimir;
    private javax.swing.JLabel btnListar;
    private javax.swing.JComboBox<String> comboUsuario;
    private com.toedter.calendar.JDateChooser jDateChooserFim;
    private com.toedter.calendar.JDateChooser jDateChooserInicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JLabel lbltitulo1;
    private javax.swing.JPanel painelCentral;
    private javax.swing.JTable tblLog;
    // End of variables declaration//GEN-END:variables
}
