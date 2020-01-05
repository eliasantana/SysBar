/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.util.FormataValor;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerCaixa;
import br.com.br.controler.ControlerFuncionario;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Elias Santana
 */
public class TelaSaldoInicial extends JDialog {

    ControlerCaixa cx = new ControlerCaixa();
    ControlerFuncionario cf = new ControlerFuncionario();
    Util u = new Util();
    FormataValor fv = new FormataValor();
    
    int idFuncLogado;

    TelaCaixa telaCaixa;
    TelaPrincipal telaPrincipal;
    String operador;
    String cargo;
    // Armazena a opção de botões da janela de confirmação
    Object[] opcao = {"   Não  ","   Sim   "};
    public TelaSaldoInicial() {
        initComponents();
        this.setModal(true);
        btnAbrirCaixa.setEnabled(false);
        fv.aplicaMascara(txtSaldoInicial,5,1);
        
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSaldoInicial = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAbrirCaixa = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Informe o Saldo Inicial");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(1, 1, 330, 40);

        txtSaldoInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtSaldoInicial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSaldoInicial.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        txtSaldoInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSaldoInicialMouseClicked(evt);
            }
        });
        txtSaldoInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSaldoInicialKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSaldoInicialKeyReleased(evt);
            }
        });
        jPanel1.add(txtSaldoInicial);
        txtSaldoInicial.setBounds(50, 50, 130, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("R$");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 50, 50, 40);

        btnAbrirCaixa.setText("Abrir Caixa");
        btnAbrirCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCaixaActionPerformed(evt);
            }
        });
        btnAbrirCaixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAbrirCaixaKeyPressed(evt);
            }
        });
        jPanel1.add(btnAbrirCaixa);
        btnAbrirCaixa.setBounds(190, 50, 130, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 332, 110);

        setSize(new java.awt.Dimension(332, 110));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
        if (telaCaixa != null) {
            this.telaCaixa.dispose();
            telaCaixa.principal.atualizaTela();
        }
        if (!"Gerente".equals(cargo)) {
            TelaLogin login = new TelaLogin();
            login.setVisible(true);
        }

    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtSaldoInicialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoInicialKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !txtSaldoInicial.getText().isEmpty()) {

            btnAbrirCaixa.requestFocus();
            btnAbrirCaixa.setEnabled(true);
        }
    }//GEN-LAST:event_txtSaldoInicialKeyPressed

    private void txtSaldoInicialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoInicialKeyReleased
       
    }//GEN-LAST:event_txtSaldoInicialKeyReleased

    private void btnAbrirCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCaixaActionPerformed
        abrirCaixa();
    }//GEN-LAST:event_btnAbrirCaixaActionPerformed

    private void txtSaldoInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSaldoInicialMouseClicked
        txtSaldoInicial.setText(null);
    }//GEN-LAST:event_txtSaldoInicialMouseClicked

    private void btnAbrirCaixaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAbrirCaixaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrirCaixa();
        }
    }//GEN-LAST:event_btnAbrirCaixaKeyPressed
    public void recebeTela(TelaPrincipal principal) {
        //this.telaCaixa = tcx;
        this.telaPrincipal = principal;
    }

    public void recebeOperador(String operador, String cargo) {

        this.cargo = cargo;
        this.operador = operador;
        this.idFuncLogado = Integer.parseInt(cf.localizaIdLogin(operador));

    }

    public void recebeTela(TelaCaixa tc) {
        this.telaCaixa = tc;
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
            java.util.logging.Logger.getLogger(TelaSaldoInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSaldoInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSaldoInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSaldoInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSaldoInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCaixa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFormattedTextField txtSaldoInicial;
    // End of variables declaration//GEN-END:variables

    private void abrirCaixa() {
        // Realiza a abertura do caixa
        double saldoIni = 0;
        String vlr = txtSaldoInicial.getText().replace(".", ""); //99.999,99 -> 99999,99
        vlr = vlr.replace(",","."); // 99999.99
        try {
            saldoIni = Double.parseDouble(vlr); 

            // Colicita confirmação do usuário. 
           
            int op = JOptionPane.showOptionDialog(this, "Abrir o Caixa com o saldo inicial de R$ " + txtSaldoInicial.getText() + "?", "Confirma a abertura do caixa?", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE,null,opcao,opcao[1]);

            if (op == 1) {
                // Realiza a abertura do caixa com o saldo incial informado
                if (cx.abreCaixa(saldoIni, idFuncLogado)) {
                    telaCaixa.trocaicone("Aberto");
                    this.dispose();
                }
            } else {
                txtSaldoInicial.setText("0,00");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um valor válido!");
        }
    }
}
