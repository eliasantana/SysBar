/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.dao.ReportUtil;
import br.com.bar.model.NFCeCancelamento;
import br.com.bar.util.ConexaoInternet;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerNFCe;
import br.com.br.controler.ControlerPedido;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Elias Santana
 */
public class TelaCancelamentoNFCe extends javax.swing.JFrame {

    Util u = new Util();
    String operador = null;
    String cargo = null;
    ControlerNFCe cnfec = new ControlerNFCe();
    public TelaCancelamentoNFCe() {
        initComponents();
        lblBtnCancelar.setEnabled(false);
        txtAreaMensagem.setEnabled(false);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNumeroNota = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaMensagem = new javax.swing.JTextArea();
        lblMensagem = new javax.swing.JLabel();
        lblBtnCancelar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cancelamento de Cupom Fiscal (NFC-e)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(1, 1, 346, 40);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("N. do Cupom");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(20, 50, 90, 30);

        txtNumeroNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroNotaKeyPressed(evt);
            }
        });
        jPanel2.add(txtNumeroNota);
        txtNumeroNota.setBounds(110, 50, 100, 30);

        txtAreaMensagem.setColumns(20);
        txtAreaMensagem.setLineWrap(true);
        txtAreaMensagem.setRows(5);
        txtAreaMensagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAreaMensagemMouseClicked(evt);
            }
        });
        txtAreaMensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAreaMensagemKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtAreaMensagem);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(20, 110, 310, 90);

        lblMensagem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMensagem.setForeground(new java.awt.Color(255, 0, 0));
        lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMensagem.setText("Tamanho mínimo 15 caracteres");
        jPanel2.add(lblMensagem);
        lblMensagem.setBounds(20, 210, 300, 15);

        lblBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cancelar.png"))); // NOI18N
        lblBtnCancelar.setText("Cancelar NFCe");
        lblBtnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBtnCancelarMouseClicked(evt);
            }
        });
        jPanel2.add(lblBtnCancelar);
        lblBtnCancelar.setBounds(210, 50, 140, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Justificativa:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 90, 130, 20);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 348, 238);

        setSize(new java.awt.Dimension(348, 238));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        dispose();
        txtNumeroNota.requestFocus();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void txtNumeroNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroNotaKeyPressed
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Verifica antes de proseguir se o cupom informado está cancelado
            if (!cnfec.estaCancelada(txtNumeroNota.getText())) {
                txtAreaMensagem.setEnabled(true);
                txtAreaMensagem.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "O Cupom informado já está cancelado!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                txtNumeroNota.setText(null);
                txtAreaMensagem.setText(null);
                txtAreaMensagem.setEnabled(false);
                lblBtnCancelar.setEnabled(false);

            }

        }
    }//GEN-LAST:event_txtNumeroNotaKeyPressed

    private void txtAreaMensagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAreaMensagemMouseClicked

    }//GEN-LAST:event_txtAreaMensagemMouseClicked

    private void txtAreaMensagemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaMensagemKeyPressed

        String textoDigitado = u.retiraAcento(txtAreaMensagem.getText());

        txtAreaMensagem.setText(u.tamanhoMaximo(textoDigitado, 60));

        int tamanho = textoDigitado.length();

        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            tamanho = tamanho - 1;
            lblMensagem.setText("Caractere(s) " + String.valueOf(tamanho));
            if (tamanho <= 15) {
                lblBtnCancelar.setEnabled(false);
            }
            if (tamanho <= 0) {
                lblMensagem.setText("Deve conter no mínimo de 15 caracteres.");
            }
        } else {
            if (tamanho >= 0) {
                tamanho = tamanho + 1;
                lblMensagem.setText("Caractere(s) " + String.valueOf(tamanho));
            }
            if (tamanho < 15) {
                lblBtnCancelar.setEnabled(false);
            } else {
                if (!txtNumeroNota.getText().isEmpty()) {
                    lblBtnCancelar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Informe o cod. do compom!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }//GEN-LAST:event_txtAreaMensagemKeyPressed

    private void lblBtnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBtnCancelarMouseClicked
        // Cancela nota

        
        if (lblBtnCancelar.isEnabled()) {
            // Verifica localmente se a nota está cancelada, se estiver exibe mensagem ao usuário
            ConexaoInternet i = new ConexaoInternet();
            // Avisa ao usuário se existe conexao com a internet
            if (i.temConexao()) {
                NFCeCancelamento c = new NFCeCancelamento();
                Log l = new Log();
                int op = JOptionPane.showConfirmDialog(this, "Confirma o cancelamento do Cupom N.° " + txtNumeroNota.getText() + "?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {
                    this.setVisible(false);
                    processamento("Calancelando Cupom N." + txtNumeroNota.getText());
                    int codCancelamento = cnfec.cancelaNFCe(txtAreaMensagem.getText(), txtNumeroNota.getText(), "cancelamento.json");
                    if (codCancelamento == 404) {
                        processamento("Cupom não localizado!");
                        JOptionPane.showMessageDialog(this, "O Cupom de cod. " + txtNumeroNota.getText() + " não foi localizado! Verifique o código informado.");
                    } else {
                        try {
                            c = cnfec.lerRetornoCancelamento("cancelamento.json");
                            System.out.println(c.toString());
                            ReportUtil rpu = new ReportUtil();
                            HashMap map = new HashMap();
                            // Imprime nota de Cancelamento se o caminho da nota for diferente de NULL
                            if (c.getCaminho_xml_cancelamento() != null) {
                                map.put("status_sefaz", c.getStatus_sefaz());
                                map.put("mensagem_sefaz", c.getMensagem_sefaz());
                                map.put("status", c.getStatus());
                                map.put("caminho_xml_cancelamento", c.getCaminho_xml_cancelamento());
                                map.put("numero_nota", txtNumeroNota.getText());
                                map.put("justificativa", txtAreaMensagem.getText());
                                // Desabilitado temporariamente até que seja descoberta a causa da excessão na exibição do relatorio de cancelamento
                                //processamento("Preparando Impressão....");
//                                    try {
//                                        rpu.imprimeRelatorioTela("cancelamento.jasper", map, "Cancelamento de Cupom Fiscal - NFCe");
//                                    } catch (JRException ex) {
//                                        Logger.getLogger(TelaCancelamentoNFCe.class.getName()).log(Level.SEVERE, null, ex);
//                                    }
                            }
                        } catch (ParseException | IOException ex) {
                            Logger.getLogger(TelaCancelamentoNFCe.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        // Registra o cancelamento no Log
                        l.setDescricao(operador + " - Cancelou o Cupom " + txtNumeroNota.getText() + " Cód. Retorno: ");
                        l.setFuncionalidade("Cancelamento NFCe");
                        l.setUsuario(operador);
                        l.gravaLog(l);
                        // Confirma se a nota foi cancelada no SEFAZ realiza o extorno do pedido
                        if ("cancelado".equals(c.getStatus())) {
                            ControlerPedido cp = new ControlerPedido();
                            //Extorna o pedido cujo o cupom foi cancelado
                            cp.extornaPedido(txtNumeroNota.getText(), operador);
                            // Registra cancelamento na base local
                            cnfec.registraCancelamento(txtNumeroNota.getText(), operador);                            
                            JOptionPane.showMessageDialog(this, "O Cupom N.° " + txtNumeroNota.getText() + " foi cancelado com sucesso!");
                        }
                        txtAreaMensagem.setText(null);
                        txtAreaMensagem.setEnabled(false);
                        lblBtnCancelar.setEnabled(false);
                        txtNumeroNota.setText(null);
                        this.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sem conexao com a internet!\nNão foi possível cancelar o Cupom! Tente novamente mais tarde.", "Atenção!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_lblBtnCancelarMouseClicked

    public void recebeOperador(String operador, String cargo) {
        this.operador = operador;
        this.cargo = cargo;
    }

    private void processamento(String msg) {

        TelaProcessaPamento p = new TelaProcessaPamento();
        p.setModal(true);
        p.mensagem(msg);
        p.setVisible(true);
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
            java.util.logging.Logger.getLogger(TelaCancelamentoNFCe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCancelamentoNFCe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCancelamentoNFCe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCancelamentoNFCe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCancelamentoNFCe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBtnCancelar;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JTextArea txtAreaMensagem;
    private javax.swing.JTextField txtNumeroNota;
    // End of variables declaration//GEN-END:variables
}
