
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.AutenticaUsuario;
import br.com.bar.dao.Log;
import br.com.bar.util.FormataValor;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerCaixa;
import br.com.br.controler.ControlerFuncionario;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JDialog;

/**
 *
 * @author Elias Santana javax.swing.JFrame
 */
public class TelaAutorizacao extends JDialog {

    ControlerFuncionario cf = new ControlerFuncionario();
    ControlerCaixa cc = new ControlerCaixa();
    Util u = new Util();
    TelaCaixa cx;
    ArrayList<String> listaDeValores; // Refatorado de listaDeSValores.

    public TelaAutorizacao() {
        initComponents();
        desabilitaDesconto();
        cf.carregaComboFuncionario2(comboFuncionario, "Gerente", "Selecione...");
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
        lblFechar = new javax.swing.JLabel();
        lblTítulo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboFuncionario = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtValorDesconto = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMotivoDesconto = new javax.swing.JTextArea();
        lblMensagem = new javax.swing.JLabel();
        btnAutorizar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(245, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        lblFechar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblFechar.setForeground(new java.awt.Color(255, 255, 255));
        lblFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechar.setText("X");
        lblFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFecharMouseClicked(evt);
            }
        });
        jPanel2.add(lblFechar);
        lblFechar.setBounds(260, 0, 50, 40);

        lblTítulo.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        lblTítulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTítulo.setText("Autorização de Desconto");
        jPanel2.add(lblTítulo);
        lblTítulo.setBounds(10, 0, 250, 33);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(1, 0, 310, 40);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Usuário");

        comboFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFuncionarioActionPerformed(evt);
            }
        });

        jLabel6.setText("Senha");

        txtSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSenhaMouseClicked(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 45, 310, 85);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Valor:");

        txtValorDesconto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorDesconto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtValorDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorDescontoKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("R$");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Motivo:");

        txtMotivoDesconto.setColumns(20);
        txtMotivoDesconto.setLineWrap(true);
        txtMotivoDesconto.setRows(5);
        txtMotivoDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMotivoDescontoFocusGained(evt);
            }
        });
        txtMotivoDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMotivoDescontoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtMotivoDesconto);

        lblMensagem.setForeground(new java.awt.Color(0, 51, 255));
        lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMensagem.setText("jLabel1");

        btnAutorizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/aplicar.png"))); // NOI18N
        btnAutorizar.setText("Autorizar");
        btnAutorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutorizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(9, 9, 9)
                .addComponent(txtValorDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAutorizar)
                        .addGap(101, 101, 101))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMensagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAutorizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 135, 310, 240);

        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 42, 310, 2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(310, 377));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void recebeValor(TelaCaixa telaCaixa, ArrayList<String> listaDadosDoPedido) {
        // Recebe os valores do pedido
        this.cx = telaCaixa;
        listaDeValores = listaDadosDoPedido;
        System.out.println("Valor: " + listaDeValores.get(0)); // Valor sem tx de Serviço
        System.out.println("Serviço: " + listaDeValores.get(1)); // Tx. de Serviço
        System.out.println("Total Geral: " + listaDeValores.get(2)); // Total Geral
        System.out.println("Mesa: " + listaDeValores.get(3)); // Número da Mesa
        System.out.println("Id do Pedido: " + listaDeValores.get(4));

    }
    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        // Fecha a Tela
       if ("".equals(txtValorDesconto.getText())){
           cx.desabilitaCheckBoxDesconto();
       }
        dispose();


    }//GEN-LAST:event_lblFecharMouseClicked
    private void comboFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFuncionarioActionPerformed
        // Ativa o campo senha se usuário for diferente de Selecione
        if (!"Selecione...".equals(comboFuncionario.getSelectedItem().toString())) {
            txtSenha.setEnabled(true);
            txtSenha.requestFocus();
            lblMensagem.setText("Informe sua senha!");
        } else {
            txtSenha.setEnabled(false);
            lblMensagem.setText("Selecione um usuário!");
        }
    }//GEN-LAST:event_comboFuncionarioActionPerformed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        // Realiza autenticação caso a tecla enter seja pressionada
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AutenticaUsuario autentica = new AutenticaUsuario();
            String funcionario = comboFuncionario.getSelectedItem().toString().toLowerCase();
            String senha = txtSenha.getText().toLowerCase();
            System.out.println("Usuario: "+funcionario+" Senha "+senha);
            if (autentica.autentica(funcionario, senha)) {
                habilitaDesconto();
                txtValorDesconto.requestFocus();
                lblMensagem.setText(null);

            } else {
                lblMensagem.setForeground(Color.RED);
                lblMensagem.setText("Senha inválida!");
            }
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSenhaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaMouseClicked

    private void txtValorDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorDescontoKeyPressed
        // Formata o valor informado
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            double valor = (Double.parseDouble(txtValorDesconto.getText().replace(",", ".")));
            txtValorDesconto.setText(String.format("%9.2f", valor));
            txtMotivoDesconto.requestFocus();
            lblMensagem.setText("Tamanho (Caracteres): Min: 20 - Max: 255");
        }
    }//GEN-LAST:event_txtValorDescontoKeyPressed

    private void btnAutorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutorizarActionPerformed
        // Aplica Desconto
        FormataValor fv = new FormataValor();
        String desc = txtValorDesconto.getText().replace(".", "");
        desc = desc.replace(",", ".");
        double desconto = Double.parseDouble(desc);
        try {
            // Converte o valor total para Double
            String tgeral = listaDeValores.get(2).replace(".", "");
            tgeral = tgeral.replace(",", ".");
            double totalGeral = Double.parseDouble(tgeral);
            if (desconto <= totalGeral && desconto > 0) {
                totalGeral = totalGeral - desconto;
                                
                listaDeValores.set(2, fv.Formata(String.valueOf(totalGeral)));
                listaDeValores.add(fv.Formata(String.valueOf(desconto)));// Adiciona o desconto
                listaDeValores.add(cf.localizaIdLogin(comboFuncionario.getSelectedItem().toString()));//ID de quem autorizou o desconto
                listaDeValores.add(txtMotivoDesconto.getText());//Motivo
                
                // Registra log da operação
                Log l = new Log();
                l.setFuncionalidade("Desconto");
                l.setUsuario(comboFuncionario.getSelectedItem().toString());
                l.setDescricao("Autorizou o desconto para o pedido-> " + listaDeValores.get(4) + " Mesa-> " + listaDeValores.get(3) + " Valor desconto: R$"+listaDeValores.get(6));
                l.gravaLog(l);
                
                cx.recebeDadosComDesconto(listaDeValores);

                this.dispose();
            } else {
                lblMensagem.setForeground(Color.red);
                lblMensagem.setText("Informe um valor válido para o desconto!");
            }
        } catch (NumberFormatException e) {
            System.out.println("br.com.bar.view.TelaAutorizacao.btnAutorizarActionPerformed()" + e);
        }
    }//GEN-LAST:event_btnAutorizarActionPerformed

    private void txtMotivoDescontoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMotivoDescontoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMotivoDescontoFocusGained

    private void txtMotivoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoDescontoKeyPressed
        // Habilita botão após digitação

        String texto = txtMotivoDesconto.getText();
        texto = u.tamanhoMaximo(texto, 255);
        txtMotivoDesconto.setText(texto);
        int tamanho = texto.length();
        
        int restam = 255-tamanho;
        if (tamanho > 20) {
            btnAutorizar.setEnabled(true);
            lblMensagem.setText(null);

        } else {
            btnAutorizar.setEnabled(false);

        }
        lblMensagem.setText("Caracteres: "+String.valueOf(tamanho) + "   Restam: "+restam);
        if (tamanho<20 || tamanho>240){
            lblMensagem.setForeground(Color.red);
            
        }else {
             lblMensagem.setForeground(Color.blue);
        }
    }//GEN-LAST:event_txtMotivoDescontoKeyPressed

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
            java.util.logging.Logger.getLogger(TelaAutorizacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAutorizacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAutorizacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAutorizacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAutorizacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAutorizar;
    private javax.swing.JComboBox<String> comboFuncionario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFechar;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblTítulo;
    private javax.swing.JTextArea txtMotivoDesconto;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JFormattedTextField txtValorDesconto;
    // End of variables declaration//GEN-END:variables

    private void desabilitaDesconto() {
        txtValorDesconto.setEnabled(false);
        txtMotivoDesconto.setEnabled(false);
        txtSenha.setEnabled(false);
        btnAutorizar.setEnabled(false);
    }

    private void habilitaDesconto() {
        txtValorDesconto.setEnabled(true);
        txtMotivoDesconto.setEnabled(true);
        txtSenha.setEnabled(true);
    }

}
