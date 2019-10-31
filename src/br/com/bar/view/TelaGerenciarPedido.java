/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.TableModelGerenciarPedido;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerCozinha;
import br.com.br.controler.ControlerEstoque;
import br.com.br.controler.ControlerMesa;
import br.com.br.controler.ControlerPedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author elias
 */
public class TelaGerenciarPedido extends javax.swing.JFrame {
    
    TelaPedido3 tela;
    ControlerMesa cm = new ControlerMesa();
    ControlerPedido cp = new ControlerPedido();
    ControlerEstoque ce = new ControlerEstoque();
    ControlerCozinha cc = new ControlerCozinha();
    Util u = new Util();
    TelaDetalheMesa tlPedido;
    JButton btnMesa;
    TableModelGerenciarPedido modelGerPedido = new TableModelGerenciarPedido();
    Log l = new Log();
    double vUnit;

    // Armazena Retorno da tela de Remoção de Intens
    int qtdAtualizada;

    public TelaGerenciarPedido() {
        initComponents();
        cp.carregaComboPedido(jcomboPedido);
        txtIdProduto.setVisible(false);
        txtQtd.setVisible(false);
        txtIDItem.setVisible(false);
        lblRemoverItemDoPedido.setEnabled(false);
        lblOperador.setVisible(false);
        lblCargo.setVisible(false);
        btnCancelarPedido.setEnabled(false);
        modelGerPedido.redimensionaColunas(tblDetalhe);
        lblCargo.setVisible(false);
        lblOperador.setVisible(false);

        if ("Selecione...".equals(jcomboPedido.getSelectedItem().toString())) {
            btnListar.setEnabled(false);
        }

    }
    
    public void recebeTela(TelaPedido3 tp3, JButton btn){
        this.tela = tp3;
        this.btnMesa = btn;
    }

    //  Reebe dados da vindo da tela de Pedidos
    public void recebeOperador(JFrame janela, String operador, String cargo) {

        lblOperador.setText(operador);
        lblCargo.setText(cargo);
        this.tlPedido = (TelaDetalheMesa) janela;

    }

    // Atualiza a tabela após remoção do item do pedido.
    public void atualizaTabela() {
        listaItensDoPedido();
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
        bordas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jcomboPedido = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalhe = new javax.swing.JTable();
        btnListar = new javax.swing.JButton();
        lblNumeroMesa = new javax.swing.JLabel();
        lblNumeroMesa1 = new javax.swing.JLabel();
        lblItensDoPedido = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        lblRemoverItemDoPedido = new javax.swing.JLabel();
        txtQtd = new javax.swing.JTextField();
        txtIDItem = new javax.swing.JTextField();
        btnCancelarPedido = new javax.swing.JButton();
        lblNumeroMesa3 = new javax.swing.JLabel();
        lblQtdItens = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bordas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bordas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(243, 156, 18));
        jPanel1.setForeground(new java.awt.Color(243, 156, 18));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel2.setText("Gestão");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 230, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/pedidos.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 64, 64));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel5.setText("de Pedidos");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 160, -1));

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("Operador");
        jPanel1.add(lblOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 90, -1));

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("Cargo");
        jPanel1.add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 100, 30));

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
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
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(689, 0, 40, 40));

        jPanel3.setLayout(null);
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        bordas.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 99));

        jcomboPedido.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jcomboPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcomboPedidoMouseClicked(evt);
            }
        });
        jcomboPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboPedidoActionPerformed(evt);
            }
        });
        bordas.add(jcomboPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, 121, 37));

        tblDetalhe = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblDetalhe.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        tblDetalhe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "PRODUTO", "QTD", "VLR UNITÁRIO R$", "VLR TOTAL R$", "CÓD. INTERNO"
            }
        ));
        tblDetalhe.setRowHeight(22);
        tblDetalhe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalheMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalhe);

        bordas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 218, 710, 170));

        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        bordas.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 139, 106, 37));

        lblNumeroMesa.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        lblNumeroMesa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bordas.add(lblNumeroMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 83, 30));

        lblNumeroMesa1.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        lblNumeroMesa1.setText("Pedido:");
        bordas.add(lblNumeroMesa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 81, -1));

        lblItensDoPedido.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        lblItensDoPedido.setText("Quantidade de Itens:");
        bordas.add(lblItensDoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 182, 199, -1));
        bordas.add(txtIdProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 61, -1));

        lblRemoverItemDoPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        lblRemoverItemDoPedido.setText("Remover item");
        lblRemoverItemDoPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRemoverItemDoPedidoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRemoverItemDoPedidoMouseEntered(evt);
            }
        });
        bordas.add(lblRemoverItemDoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 139, 130, 40));
        bordas.add(txtQtd, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 61, -1));
        bordas.add(txtIDItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 61, -1));

        btnCancelarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fechar.png"))); // NOI18N
        btnCancelarPedido.setText("Cancelar Pedido");
        btnCancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPedidoActionPerformed(evt);
            }
        });
        bordas.add(btnCancelarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 139, -1, -1));

        lblNumeroMesa3.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        lblNumeroMesa3.setText("Mesa:");
        bordas.add(lblNumeroMesa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 61, 30));

        lblQtdItens.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        bordas.add(lblQtdItens, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 90, 30));

        getContentPane().add(bordas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 400));

        setSize(new java.awt.Dimension(730, 402));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // Fecha janela
        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        listaItensDoPedido();

    }//GEN-LAST:event_btnListarActionPerformed

    private void jcomboPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboPedidoActionPerformed

        btnCancelarPedido.setEnabled(false);
        btnListar.setEnabled(false);

        if (jcomboPedido.getItemCount() > 1) {
            btnListar.setEnabled(true);

        }


    }//GEN-LAST:event_jcomboPedidoActionPerformed
    public void recebeNovaQtd(int novaQtd) {
        this.qtdAtualizada = novaQtd;//1
        int qtdPedido = Integer.parseInt(txtQtd.getText());//3
        System.out.println("Qtd recebida: " + qtdAtualizada);

        if (qtdAtualizada == 0) {
            // Remove Item
            cp.excluiItemPedido(txtIDItem.getText());
            //Devolve quantidade removida
            ce.entradaDeProduto(txtIdProduto.getText(), txtQtd.getText());
            //Registra a movimentação
            ce.registraMovimentacao(txtIdProduto.getText(), String.valueOf(txtQtd.getText()), ce.localizaIdOperacao("Devolução"), "O cliente desistiu do produto");

        } else {
            Double total = vUnit * novaQtd;
            // Atualiza Quantidade
            cp.atualizaQtdItem(txtIDItem.getText(), novaQtd, total);
            int devolve = qtdPedido - novaQtd;
            if (ce.entradaDeProduto(txtIdProduto.getText(), String.valueOf(devolve))) {
                JOptionPane.showMessageDialog(null, "Produto(s) devolvido(s) ao estoque com sucesso!");
            }
            //Registra a movimentação
            ce.registraMovimentacao(txtIdProduto.getText(), String.valueOf(devolve), ce.localizaIdOperacao("Devolução"), "O cliente desistiu do produto");
        }
        //Atualiza a Tabela
        ResultSet rs = cp.detalhePorPedidoId(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString());
        tblDetalhe.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedidoId(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString())));
        modelGerPedido.redimensionaColunas(tblDetalhe);
        lblQtdItens.setText(String.valueOf(contaItens(rs)));
    }
    private void tblDetalheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalheMouseClicked
        // Captura número da linha selecionada 

        int linha = tblDetalhe.getSelectedRow();
        Double total = Double.parseDouble(tblDetalhe.getModel().getValueAt(linha, 4).toString().replaceAll(",", "."));
        vUnit = Double.parseDouble(tblDetalhe.getModel().getValueAt(linha, 3).toString().replaceAll(",", "."));

        txtIdProduto.setText(tblDetalhe.getModel().getValueAt(linha, 0).toString());
        txtQtd.setText(tblDetalhe.getModel().getValueAt(linha, 2).toString());
        txtIDItem.setText(tblDetalhe.getModel().getValueAt(linha, 5).toString());
        lblRemoverItemDoPedido.setEnabled(true);

    }//GEN-LAST:event_tblDetalheMouseClicked

    private void lblRemoverItemDoPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoverItemDoPedidoMouseClicked
        if (lblRemoverItemDoPedido.isEnabled()) {

            // Remove item do pedido e devolte ao estoque
            int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este item do pedido?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {
                // Armazena quantidade do item selecionado
                int qtdItem = Integer.parseInt(txtQtd.getText());
                if (qtdItem > 1) {
                    TelaAtualizaItem atualizaItem = new TelaAtualizaItem();
                    atualizaItem.recebeQtdItem(this, txtQtd.getText());
                    atualizaItem.setModal(true);
                    atualizaItem.setVisible(true);
                    //Atualiza a tabela detalhe do pedido na tela Gerenciar pedido.
                    tlPedido.atuDetalheDoPedido(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString());

                } else {
                    // Se o pedido tiver quantidade igual a 1
                    if (cp.excluiItemPedido(txtIDItem.getText())) {

                        // Atualiza a tabela a tela de Pedido
                        tlPedido.atuDetalheDoPedido(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString());
                        //Remove item do pedido
                        lblRemoverItemDoPedido.setEnabled(false);
                        // Devolve produdto ao estoque
                        if (ce.entradaDeProduto(txtIdProduto.getText(), txtQtd.getText())) {
                            JOptionPane.showMessageDialog(this, "Produto devolvido ao estoque com sucesso!");
                            // Inicio do Registro de Log
                            l.setFuncionalidade("Devolução");
                            l.setUsuario(lblOperador.getText());
                            l.setDescricao("Removeu o item " + txtIDItem.getText() + " do pedido ->" + jcomboPedido.getSelectedItem().toString());
                            l.gravaLog(l);
                            // Fim do Registro de Log

                            // Registra Movimentação
                            ce.registraMovimentacao(txtIdProduto.getText(), txtQtd.getText(), ce.localizaIdOperacao("Devolução"), "O cliente desistiu do produto");
                            tblDetalhe.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedidoId(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString())));
                            modelGerPedido.redimensionaColunas(tblDetalhe);
                            ResultSet rs = cp.detalhePorPedidoId(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString());

                            int itens = contaItens(rs);
                            if (itens > 0) {
                                btnCancelarPedido.setEnabled(false);
                            } else {
                                btnCancelarPedido.setEnabled(true);
                                btnListar.setEnabled(false);
                            }
                            lblQtdItens.setText(String.valueOf(itens));

                        }
                    }
                }

            }
        }

    }//GEN-LAST:event_lblRemoverItemDoPedidoMouseClicked
    // Seleciona a mesa informada no parâmetro
    public void selecionaPedido(String nPedido, String nMesa) {
        jcomboPedido.setSelectedItem(nPedido);
        lblNumeroMesa.setText(nMesa);
        listaItensDoPedido();
        btnListar.setEnabled(false);
        jcomboPedido.setEnabled(false);
    }
    private void btnCancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPedidoActionPerformed
        // Solicita ao usuário a confirmação do pedido
        int op = JOptionPane.showConfirmDialog(this, "Confirma o cancelamento do pedido?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {
            // Captura o número do pedido antes da exclusão
            String nPedido = jcomboPedido.getSelectedItem().toString();
            String nmesa = lblNumeroMesa.getText();
            // Exclui pedido
            if (cp.cancelaPedido(Integer.parseInt(jcomboPedido.getSelectedItem().toString()))) {
                JOptionPane.showMessageDialog(this, "Pedido cancelado com sucesso!");
                // Libera a mesa trocando seu status para 0 - livre
                cm.trocaStatusMesa(nmesa, "0");
                cp.carregaComboPedido(jcomboPedido);
                // Registra log da operação              
                l.setUsuario(lblOperador.getText());
                l.setFuncionalidade("Cancelamento Pedido");
                l.setDescricao("Cancelou o pedido ->" + nPedido);
                l.gravaLog(l);
                btnCancelarPedido.setEnabled(false);
                
                //tlPedido.atualizaPedidos(); Excluir depois de teste de exclusão do pedido
                tlPedido.atuDetalheDoPedido(nmesa, nPedido);
                cp.carregaComboPedido(jcomboPedido);
                jcomboPedido.setSelectedIndex(0);
                if (jcomboPedido.getItemCount() <= 1) {
                    jcomboPedido.setEnabled(false);
                }
                btnListar.setEnabled(false);
                this.dispose();
                tlPedido.dispose();
                tela.alteraCorMesa(btnMesa);
                
            }
        }
//        cp.carregaComboPedido(jcomboPedido);
//        jcomboPedido.setSelectedIndex(0);
//        if (jcomboPedido.getItemCount()<=1){
//            jcomboPedido.setEnabled(false);
//        }
//        btnListar.setEnabled(false);


    }//GEN-LAST:event_btnCancelarPedidoActionPerformed
    // Conta o número de itens do ResultSetInformado retornando um inteiro com
    // a quantidade retornada pelo ResultSet

    private int contaItens(ResultSet rs) {
        int itens = 0;
        try {

            while (rs.next()) {
                itens = itens + rs.getInt("qtd");

            }
        } catch (SQLException e) {
            System.out.println("br.com.bar.view.TelaGerenciarPedido.contaItens()" + e);
        }
        return itens;
    }
    private void jcomboPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcomboPedidoMouseClicked
        btnCancelarPedido.setEnabled(false);
    }//GEN-LAST:event_jcomboPedidoMouseClicked

    private void lblRemoverItemDoPedidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoverItemDoPedidoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblRemoverItemDoPedidoMouseEntered

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
    private javax.swing.JPanel bordas;
    private javax.swing.JButton btnCancelarPedido;
    private javax.swing.JButton btnListar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcomboPedido;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblItensDoPedido;
    private javax.swing.JLabel lblNumeroMesa;
    private javax.swing.JLabel lblNumeroMesa1;
    private javax.swing.JLabel lblNumeroMesa3;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblQtdItens;
    private javax.swing.JLabel lblRemoverItemDoPedido;
    private javax.swing.JTable tblDetalhe;
    private javax.swing.JTextField txtIDItem;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtQtd;
    // End of variables declaration//GEN-END:variables

    private void listaItensDoPedido() {
        // Lista produtos do pedido
        int itens = 0;
        String numeroPedido = jcomboPedido.getSelectedItem().toString();
        lblNumeroMesa.setText(cm.localizaNumeroMesa(numeroPedido));

        ResultSet rs = cp.detalhePorPedidoId(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString());

        itens = contaItens(rs);
        if (itens > 0) {
            // Se possui itens no pedido não permite o cancelamento do pedido
            btnCancelarPedido.setEnabled(false);

        } else {

            btnCancelarPedido.setEnabled(true);
        }
        tblDetalhe.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedidoId(lblNumeroMesa.getText(), jcomboPedido.getSelectedItem().toString())));
        modelGerPedido.redimensionaColunas(tblDetalhe);
        lblQtdItens.setText(String.valueOf(itens));
    }
}
