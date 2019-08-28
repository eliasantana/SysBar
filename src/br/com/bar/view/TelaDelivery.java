/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.model.Cliente;
import br.com.bar.model.Entregador;
import br.com.bar.model.TableModelCliente;
import br.com.bar.model.TableModelDelivery;
import br.com.bar.model.TableModelItensDelivery;
import br.com.bar.util.FormataValor;
import br.com.br.controler.ControlerCliente;
import br.com.br.controler.ControlerDelivery;
import br.com.br.controler.ControlerEntregador;
import br.com.br.controler.ControlerPedido;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elias Santana
 */
public class TelaDelivery extends javax.swing.JFrame {

    ControlerCliente cl = new ControlerCliente();
    ControlerEntregador ce = new ControlerEntregador();
    ControlerDelivery cd = new ControlerDelivery();

    TableModelCliente modelCliente = new TableModelCliente();
    TableModelDelivery modelDelivey = new TableModelDelivery();
    TableModelItensDelivery modelItens = new TableModelItensDelivery();

    TelaCadastroCliente cadastroCliente;
    Cliente c;
    TelaPedido2 telaPEdido;

    public TelaDelivery() {
        initComponents();
        tbDelivery.setModel(modelDelivey);
        modelDelivey.redimensionaColunas(tbDelivery);
        
        tbcliente.setModel(modelCliente);
        modelCliente.redimensionaColunas(tbcliente);
        
        tbDetalhePedido.setModel(modelItens);
        modelItens.redimensionaColunas(tbDetalhePedido);
        
        estadoinicial();
        ce.listaEntregador(comboEntregador);
        lblCargo.setVisible(false);
        lblOperador.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbcliente = new javax.swing.JTable();
        txtNomeCliente = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDelivery = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lblAbrirPedido = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDetalhePedido = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        lblPedido = new javax.swing.JLabel();
        lblNomeCliente = new javax.swing.JLabel();
        lblNpedido = new javax.swing.JLabel();
        lblTxServico = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblValorTxServico = new javax.swing.JLabel();
        lblTotalPedido = new javax.swing.JLabel();
        lblTotalGeral = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        labelTaxaDeEntrega = new javax.swing.JLabel();
        lblTxEntrega = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblLocalDeEntrega = new javax.swing.JLabel();
        labelEntregador = new javax.swing.JLabel();
        comboEntregador = new javax.swing.JComboBox<>();
        lblPedido1 = new javax.swing.JLabel();
        lblnMesa = new javax.swing.JLabel();
        lblEntregar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCliente = new javax.swing.JMenu();
        menuNovo = new javax.swing.JMenuItem();
        menuEditar = new javax.swing.JMenuItem();
        menuExcluir = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);

        jLabel2.setText("Pesquisar Cliente");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(12, 13, 120, 14);

        tbcliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        tbcliente.setRowHeight(22);
        tbcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbcliente);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(12, 81, 520, 75);

        txtNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeClienteKeyReleased(evt);
            }
        });
        jPanel2.add(txtNomeCliente);
        txtNomeCliente.setBounds(12, 33, 250, 30);

        jLabel22.setText("Pedidos aguardando entrega");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(10, 210, 500, 30);

        tbDelivery.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "PEDIDO", "MESA", "CLIENTE", "ENTREGADOR", "SAÍDA"
            }
        ));
        tbDelivery.setRowHeight(22);
        tbDelivery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDeliveryMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbDelivery);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 240, 520, 75);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAbrirPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Pedido.png"))); // NOI18N
        lblAbrirPedido.setText("Abrir Pedido");
        lblAbrirPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAbrirPedidoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAbrirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblAbrirPedido)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(190, 170, 130, 40);

        tbDetalhePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CÓD", "PRODUTO", "QTD", "VLR UNITÁRIO R$", "VLR TOTAL R$"
            }
        ));
        tbDetalhePedido.setRowHeight(22);
        jScrollPane3.setViewportView(tbDetalhePedido);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 360, 520, 139);

        jLabel6.setText("Itens do Pedido");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 330, 150, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3);
        jLabel3.setBounds(270, 30, 32, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(220, 60, 540, 510);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(null);

        lblCliente.setText("Cliente");
        jPanel3.add(lblCliente);
        lblCliente.setBounds(12, 13, 93, 14);

        lblPedido.setText("Mesa");
        jPanel3.add(lblPedido);
        lblPedido.setBounds(120, 90, 110, 14);

        lblNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(lblNomeCliente);
        lblNomeCliente.setBounds(12, 33, 300, 22);

        lblNpedido.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNpedido.setText(" ");
        jPanel3.add(lblNpedido);
        lblNpedido.setBounds(20, 110, 96, 22);

        lblTxServico.setText("Tx. Serviço");
        jPanel3.add(lblTxServico);
        lblTxServico.setBounds(120, 140, 97, 14);

        jLabel14.setText("Total Pedido");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(20, 140, 80, 14);

        lblValorTxServico.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblValorTxServico.setText("0,00");
        jPanel3.add(lblValorTxServico);
        lblValorTxServico.setBounds(120, 170, 79, 22);

        lblTotalPedido.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTotalPedido.setText("0,00");
        jPanel3.add(lblTotalPedido);
        lblTotalPedido.setBounds(20, 170, 80, 22);

        lblTotalGeral.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblTotalGeral.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalGeral.setText("0,00");
        jPanel3.add(lblTotalGeral);
        lblTotalGeral.setBounds(20, 300, 260, 60);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Total Geral R$");
        jPanel3.add(jLabel17);
        jLabel17.setBounds(20, 280, 130, 22);

        labelTaxaDeEntrega.setText("Tx. Entrega");
        jPanel3.add(labelTaxaDeEntrega);
        labelTaxaDeEntrega.setBounds(230, 210, 90, 14);

        lblTxEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTxEntrega.setText("0,00");
        jPanel3.add(lblTxEntrega);
        lblTxEntrega.setBounds(230, 230, 80, 22);

        jLabel20.setText("Local de Entrega");
        jPanel3.add(jLabel20);
        jLabel20.setBounds(20, 210, 130, 14);

        lblLocalDeEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(lblLocalDeEntrega);
        lblLocalDeEntrega.setBounds(20, 230, 290, 30);

        labelEntregador.setText("Entregador");
        jPanel3.add(labelEntregador);
        labelEntregador.setBounds(20, 370, 100, 14);

        comboEntregador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEntregadorActionPerformed(evt);
            }
        });
        jPanel3.add(comboEntregador);
        comboEntregador.setBounds(20, 390, 170, 40);

        lblPedido1.setText("Pedido");
        jPanel3.add(lblPedido1);
        lblPedido1.setBounds(20, 90, 90, 14);

        lblnMesa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblnMesa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(lblnMesa);
        lblnMesa.setBounds(120, 110, 50, 20);

        lblEntregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEntregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/entrega.png"))); // NOI18N
        lblEntregar.setText("Entregar");
        lblEntregar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblEntregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEntregarMouseClicked(evt);
            }
        });
        jPanel3.add(lblEntregar);
        lblEntregar.setBounds(90, 450, 100, 40);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(770, 60, 320, 510);

        jPanel1.setBackground(new java.awt.Color(52, 73, 94));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/masterfood6_144x136.png"))); // NOI18N

        lblOperador.setBackground(new java.awt.Color(0, 0, 0));
        lblOperador.setText("operador");

        lblCargo.setBackground(new java.awt.Color(0, 0, 0));
        lblCargo.setText("operador");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblOperador)
                        .addGap(46, 46, 46)
                        .addComponent(lblCargo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOperador)
                    .addComponent(lblCargo))
                .addContainerGap(197, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 210, 580);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Delivery");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(470, 0, 196, 50);

        menuCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/funcionario (2).png"))); // NOI18N
        menuCliente.setText("Cliente");

        menuNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menuNovo.setText("Novo");
        menuNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoActionPerformed(evt);
            }
        });
        menuCliente.add(menuNovo);

        menuEditar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        menuEditar.setText("Editar");
        menuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditarActionPerformed(evt);
            }
        });
        menuCliente.add(menuEditar);

        menuExcluir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        menuExcluir.setText("Excluir");
        menuCliente.add(menuExcluir);

        jMenuBar1.add(menuCliente);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1116, 650));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboEntregadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEntregadorActionPerformed
        // Habilita o combo se ouver um número de pedido
        if ("Selecione...".equals(comboEntregador.getSelectedItem().toString())) {
            lblEntregar.setEnabled(false);
        } else {
            lblEntregar.setEnabled(true);
        }
    }//GEN-LAST:event_comboEntregadorActionPerformed

    private void txtNomeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Pesquisa Cliente
            tbcliente.setModel(DbUtils.resultSetToTableModel(cl.listaCliente(txtNomeCliente.getText())));
            modelCliente.redimensionaColunas(tbcliente);
        }

    }//GEN-LAST:event_txtNomeClienteKeyPressed

    private void txtNomeClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClienteKeyReleased

    private void tbclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclienteMouseClicked
        int linhaTabela = tbcliente.getSelectedRow();
        lblNomeCliente.setText(tbcliente.getModel().getValueAt(linhaTabela, 0).toString());
        lblLocalDeEntrega.setText(tbcliente.getModel().getValueAt(linhaTabela, 2).toString());
        lblTxEntrega.setText(tbcliente.getModel().getValueAt(linhaTabela, 3).toString());
        lblAbrirPedido.setEnabled(true);
        // Lista os pedidos do cliente selecionado
        tbDelivery.setModel(DbUtils.resultSetToTableModel(cd.listaDeliveryCliente(lblNomeCliente.getText())));
        modelDelivey.redimensionaColunas(tbDelivery);
        
        int linhasTbDelivery = tbDelivery.getRowCount();
        if (linhasTbDelivery==0){
            tbDetalhePedido.setModel(modelItens);
            modelItens.redimensionaColunas(tbDetalhePedido);
            lblNpedido.setText("0,00");
            lblnMesa.setText("0,00");
            lblTotalPedido.setText("0,00");
            lblValorTxServico.setText("0,00");
            lblTotalGeral.setText("0,00");
            comboEntregador.setSelectedIndex(0);
            comboEntregador.setEnabled(false);
            labelEntregador.setEnabled(false);
        }

    }//GEN-LAST:event_tbclienteMouseClicked

    private void lblAbrirPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAbrirPedidoMouseClicked

        if (lblAbrirPedido.isEnabled()) {

            if (telaPEdido == null) {
                telaPEdido = new TelaPedido2();
                telaPEdido.recebeCliente(this, "delivery", lblNomeCliente.getText());
                telaPEdido.recebeOperador(lblOperador.getText(), lblCargo.getText());
            }

            telaPEdido.setVisible(true);
        }
    }//GEN-LAST:event_lblAbrirPedidoMouseClicked

    private void menuNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovoActionPerformed

        if (cadastroCliente == null) {
            cadastroCliente = new TelaCadastroCliente();

            cadastroCliente.recebeOperador(lblOperador.getText(), lblCargo.getText(), "Adicionar");
        }

        cadastroCliente.setVisible(true);
    }//GEN-LAST:event_menuNovoActionPerformed

    private void menuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuEditarActionPerformed

    private void tbDeliveryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDeliveryMouseClicked

        /*
           Captura excessão NullPointerException gerada caso a tabela
           detalheDelivery esteja Vazia.
         */
        try {
            int linha = tbDelivery.getSelectedRow();
            lblNpedido.setText(tbDelivery.getModel().getValueAt(linha, 0).toString());
            String numeroMesa = tbDelivery.getModel().getValueAt(linha, 1).toString();

            lblnMesa.setText(numeroMesa);
            ControlerPedido cp = new ControlerPedido();
            // Lista itens do pedido
            tbDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(numeroMesa, lblNpedido.getText())));
            modelItens.redimensionaColunas(tbDetalhePedido);

            if (!"".equals(tbDetalhePedido.getValueAt(0, 0).toString())) {
                calcula_pedido();
                // Libera combo entregador se o pedido ainda não saiu para entrega.               

            }

        } catch (Exception e) {
            System.out.println("br.com.bar.view.TelaDelivery.tbDeliveryMouseClicked()" + e);
        }
    }//GEN-LAST:event_tbDeliveryMouseClicked

    private void lblEntregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEntregarMouseClicked
        if (lblEntregar.isEnabled()) {

            if (comboEntregador.getSelectedIndex() != 0) {
                Entregador e = ce.localizaEntregador(comboEntregador.getSelectedItem().toString());
                e.setStatus(1);

                if (ce.atualizaStatusEntregador(e)) {
                    cd.atualizaHoraSaida(lblNpedido.getText(), e.getNome());
                    JOptionPane.showMessageDialog(this, "Pedido entregue!");
                    // Desabilita combo 
                    comboEntregador.setSelectedIndex(0);
                    comboEntregador.setEnabled(false);
                    labelEntregador.setEnabled(false);
                    //Limpa as tabelas delivery e itens
                    tbDelivery.setModel(DbUtils.resultSetToTableModel(cd.listaDeliveryCliente("")));
                    modelDelivey.redimensionaColunas(tbDelivery);
                    ControlerPedido cp = new ControlerPedido();
                    tbDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido("", "")));
                    modelItens.redimensionaColunas(tbDetalhePedido);
                    limpaCampos();
                    lblAbrirPedido.setEnabled(false);
                    comboEntregador.removeAll();
                    ce.listaEntregador(comboEntregador);
                    txtNomeCliente.setText(null);
                }

            }
        }
    }//GEN-LAST:event_lblEntregarMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
            tbcliente.setModel(DbUtils.resultSetToTableModel(cl.listaCliente(txtNomeCliente.getText())));
            modelCliente.redimensionaColunas(tbcliente);
    }//GEN-LAST:event_jLabel3MouseClicked

    public void recebeOperador(String operador, String cargo) {
        lblOperador.setText(operador);
        lblCargo.setText(cargo);
    }

    /**
     * Recebe um pedido da tela de Pedido
     *
     * @param idPedido Número do Pedido
     * @param numeroMesa Número da mesa
     */
    public void recebePedido(String idPedido, String numeroMesa) {
        lblNpedido.setText(idPedido);
        lblnMesa.setText(numeroMesa);
        comboEntregador.setEnabled(true);
        lblEntregar.setEnabled(true);
        // Lista delivery pelo número do pedido
        tbDelivery.setModel(DbUtils.resultSetToTableModel(cd.listaDelivery(idPedido)));
        modelDelivey.redimensionaColunas(tbDelivery);
        // Lista todos os deliverys do cliente
        tbDelivery.setModel(DbUtils.resultSetToTableModel(cd.listaDeliveryCliente(lblNomeCliente.getText())));
        modelDelivey.redimensionaColunas(tbDelivery);
        // Lista os itens do pedido
        ControlerPedido cp = new ControlerPedido();
        tbDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(numeroMesa, idPedido)));
        modelItens.redimensionaColunas(tbDetalhePedido);
        //Totalizar pedido
        calcula_pedido();
        double tpedido = Double.parseDouble(lblTotalPedido.getText().replace(",", "."));
        double txEnt = Double.parseDouble(lblTxEntrega.getText().replace(",", "."));
        double servico = Double.parseDouble(lblValorTxServico.getText().replace(",", "."));
        double tgeral = Double.parseDouble(lblTotalGeral.getText().replace(",", "."));

        boolean result = cd.AtualizaVlrDelivery(tpedido, txEnt, servico, tgeral, idPedido);
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
            java.util.logging.Logger.getLogger(TelaDelivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDelivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDelivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDelivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDelivery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboEntregador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelEntregador;
    private javax.swing.JLabel labelTaxaDeEntrega;
    private javax.swing.JLabel lblAbrirPedido;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblEntregar;
    private javax.swing.JLabel lblLocalDeEntrega;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNomeCliente;
    private javax.swing.JLabel lblNpedido;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPedido;
    private javax.swing.JLabel lblPedido1;
    private javax.swing.JLabel lblTotalGeral;
    private javax.swing.JLabel lblTotalPedido;
    private javax.swing.JLabel lblTxEntrega;
    private javax.swing.JLabel lblTxServico;
    private javax.swing.JLabel lblValorTxServico;
    private javax.swing.JLabel lblnMesa;
    private javax.swing.JMenu menuCliente;
    private javax.swing.JMenuItem menuEditar;
    private javax.swing.JMenuItem menuExcluir;
    private javax.swing.JMenuItem menuNovo;
    private javax.swing.JTable tbDelivery;
    private javax.swing.JTable tbDetalhePedido;
    private javax.swing.JTable tbcliente;
    private javax.swing.JTextField txtNomeCliente;
    // End of variables declaration//GEN-END:variables

    private void estadoinicial() {

        lblAbrirPedido.setEnabled(false);
        menuEditar.setEnabled(false);
        menuExcluir.setEnabled(false);
        comboEntregador.setEnabled(false);
        lblEntregar.setEnabled(false);

    }

    private void calcula_pedido() {
        FormataValor fv = new FormataValor();
        double totalPedido = 0;
        double txServico = 0;
        double txEntrega = 0;
        double totalGeral = 0;

        int linhasTabela = tbDetalhePedido.getRowCount();
        for (int i = 0; i < linhasTabela; i++) {
            double vlrLinha = Double.parseDouble(tbDetalhePedido.getValueAt(i, 4).toString().replace(",", "."));
            totalPedido = totalPedido + vlrLinha;

        }

        try {
            lblTotalPedido.setText(fv.Formata(String.valueOf(totalPedido)));
            txEntrega = Double.parseDouble(lblTxEntrega.getText().replace(",", "."));
            // Calcula a taxa de serviço em 10%
            txServico = totalPedido * 0.10;
            lblValorTxServico.setText(fv.Formata(String.valueOf(txServico)));
            totalGeral = totalPedido + txServico + txEntrega;
            lblTotalGeral.setText(fv.Formata(String.valueOf(totalGeral)));

        } catch (NumberFormatException e) {
            System.out.println("br.com.bar.view.TelaDelivery.calcula_pedido()" + e);
        }
        int linha = tbDelivery.getSelectedRow();

        labelEntregador.setEnabled(true);
        comboEntregador.setEnabled(true);

    }
    //  Limpa todos os valores

    private void limpaCampos() {
        lblNomeCliente.setText(null);
        lblNpedido.setText(null);
        lblnMesa.setText(null);
        lblTotalPedido.setText("0,00");
        lblValorTxServico.setText("0,00");
        lblTotalGeral.setText("0,00");
        lblLocalDeEntrega.setText(null);
        lblTxEntrega.setText("0,00");
    }
}
