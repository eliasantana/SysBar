/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.model.Produto;
import br.com.bar.model.ProdutoPedido;
import br.com.bar.model.TableModelDetalhePedido;
import br.com.bar.util.FormataValor;
import br.com.br.controler.ControlerEstoque;
import br.com.br.controler.ControlerFuncionario;
import br.com.br.controler.ControlerMesa;
import br.com.br.controler.ControlerPedido;
import br.com.br.controler.ControlerProduto;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elias Santana
 */
public class TelaDetalheMesa extends javax.swing.JFrame {

    ControlerPedido cp = new ControlerPedido();
    ControlerProduto cproduto = new ControlerProduto();
    ControlerMesa cm = new ControlerMesa();
    ControlerFuncionario cf = new ControlerFuncionario();
    ControlerEstoque ec = new ControlerEstoque();

    TableModelDetalhePedido modelDetPedido = new TableModelDetalhePedido();
    Produto pLocalizado;
    FormataValor fv = new FormataValor();
    ArrayList<String> obsevacaoPrato;
    String operador, cargo, idOperador;
    public TelaDetalheMesa() {
        initComponents();
        txtQtd.setEnabled(false);
        limpaform();
        
    }

    //Atualiza a tabela detalhe mesa    
    private void atualizaTabela(String nMesa) {
        tbDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(nMesa, lblNPedido.getText())));
        modelDetPedido.redimensionaColunas(tbDetalhePedido);
    }

    public void recebeMesa(String nMesa) {
        lblNumeroMesa.setText(nMesa);
        lblNPedido.setText(cp.LocalizaIdPedido(nMesa));
        atualizaTabela(nMesa);
        lblIdMesa.setText(cm.localizaIdMesa(nMesa));
        String garcom = cf.retornaGarcom(lblNumeroMesa.getText());
        String[] nomeSeparado = garcom.split(" ");
        lblGarcom.setText(nomeSeparado[0]);
        lblIdGarcom.setText(cf.localizaId(garcom));
    }
    
     // Recebe o operador Logado
    public void recebeOperador(String operador, String cargo){
        this.operador = operador;
        this.cargo = cargo;
        idOperador = cf.localizaIdLogin(operador);        
    }


    // Este método adiciona um item ao pedido e é chamado pela execusão dos 
    // Eventos KeyPressed e LostFocus
    private void adicionaItemNoPedido() {

        try {
            // Caso a quantidade não seja informada pede ao usuário que digite novamente!
            if (txtQtd.getText().isEmpty()) {
                lblMensagem.setText("*Informe uma quantidade válida!");
                lblMensagem.setForeground(Color.red);
                txtQtd.requestFocus();

            } else {
                lblMensagem.setText(null);

                // Confirma pedido
                // Instancia um objeto Produto Pedido
                ProdutoPedido pp = new ProdutoPedido();
                // Seta dados no objeto
                pp.setTbproduto_id(txtCodigoProduto.getText());
                pp.setQtd(txtQtd.getText());
                pp.setValorUnit(lblVlrUnitario.getText().replaceAll(",", "."));
                pp.setTotal(lblTotal.getText().replaceAll(",", "."));
                pp.setCadmesa_id(lblIdMesa.getText());
                pp.setCadpedido_id_pedido(lblNPedido.getText());
                pp.setTbcadfuncionario_id(lblIdGarcom.getText());

                int qtdEstoque = ec.temNoEstoque(pp.getTbproduto_id());

                if (Integer.parseInt(pp.getQtd()) <= qtdEstoque) {

                    // Adiciona o produto ao pedido
                    if (cproduto.adicionaProdutoAoPedido(pp)) {
                        lblMensagem.setText("*Produto adicionado com sucesso!");
                    }
                    // Retira o produto do estoque                 
                    ec.retiraEstoque(pp, pp.getQtd());

                    // Registra movimentação 
                    if (ec.registraMovimentacao(pp.getTbproduto_id(), pp.getQtd(), ec.localizaIdOperacao("Venda"), null)) {
                        System.out.println("Movimentação registrada!");
                    }

                    // ------ Verifica Grupo de Produto e Envia para acozinha ---/
                    String grupo = cproduto.localizaGrupoProduto(Integer.parseInt(txtCodigoProduto.getText()));

                    if (grupo.equals("Cozinha")) {

                        ArrayList<String> pCozinha = new ArrayList<>();

                        //codProduto, produto, qtd, funcionario, mesa, data, status
                        pCozinha.add(lblDescricao.getText()); // Produto
                        pCozinha.add(txtCodigoProduto.getText()); // // Código Produto
                        pCozinha.add(txtQtd.getText()); // Qtd         
                        pCozinha.add(lblGarcom.getText()); // Nome do Funcionario
                        pCozinha.add(lblNumeroMesa.getText()); // Numero da mesa
                        pCozinha.add("Pendente"); // Status Pendente - Liberado
                        pCozinha.add(lblNPedido.getText());
                        Date dtAtual = new Date();
                        Timestamp tms = new Timestamp(dtAtual.getTime());
                        pCozinha.add(String.valueOf(tms)); // Data Atual
                        int op = JOptionPane.showConfirmDialog(this, "Deseja adicionar uma observação?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        // Chama a tela de observação
                        if (op == JOptionPane.YES_OPTION) {

                            TelaObservacaoProduto telaObs = new TelaObservacaoProduto();
                            telaObs.recebeTela3(this, pCozinha); // Envia dados do produto
                            telaObs.setAlwaysOnTop(true);
                            telaObs.setVisible(true);

                        } else {
                            pCozinha.add(null);//Obsrevação do prato                               
                            // Envia prato para cozinha
                            enviaParaCozinha(pCozinha);
                        }

                    }

                    // Limpa label de mensagem de produto indisponível 
                    //lblMensagem.setText(null);

                    bloqueiaCampos();
                    txtCodigoProduto.setEnabled(true);
                    txtQtd.setEnabled(false);

                    //limpaform();
                } else {
                    // Exibe mensagem de produto indisponpivel no momento
                    lblMensagem.setForeground(Color.red);
                    lblMensagem.setText("*Quantidade insuficiente! " + "Existe apenas " + qtdEstoque + " unidade(s) em estoque.");
                    txtQtd.requestFocus();
                    //txtCodigoProduto.setText(null);
                    txtQtd.setText(null);

                }
                //Atualiza a tabela de detalhe do pedido    
                atualizaTabela(lblNumeroMesa.getText());

            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println("br.com.bar.view.TelaPedido2.txtQtdKeyPressed()" + e);
            lblMensagem.setText("*Quantidade inválida, tente novamente!");
            lblMensagem.setOpaque(false);
            lblMensagem.setForeground(Color.red);
            txtQtd.requestFocus();
        }
        limpaform();
    }

    /**
     * Recebe dados atualizados do produto para envio a cozinha .
     *
     * @param obs Tela de Observação
     * @param listaAtualizada - Listagem
     */
    public void recebeObsPrato(ArrayList<String> listaAtualizada, TelaObservacaoProduto obs) {
        this.obsevacaoPrato = listaAtualizada;
        obs.dispose();
        enviaParaCozinha(listaAtualizada);
    }

    // Recebe um ArrayList de String com os dados do prato a ser enviado para cozinha
    // na inclusão do produto no pedido.
    private void enviaParaCozinha(ArrayList<String> prato) {

        if (cp.enviaProdutoCozinha(prato)) {
            JOptionPane.showMessageDialog(this, "Solicitação de prato enviada para a cozinha!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao tentar enviar solicitação de prato para a cozinha - Contate o SUPORTE!");
        }

    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblNumeroMesa = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblNPedido = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblGarcom = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalhePedido = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtCodigoProduto = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        txtQtd = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblVlrUnitario = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        lblMensagem = new javax.swing.JLabel();
        lblIdMesa = new javax.swing.JLabel();
        lblIdProduto = new javax.swing.JLabel();
        lblIdGarcom = new javax.swing.JLabel();

        jLabel19.setText("jLabel19");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MasterFood - Detalhe Mesa");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/anexar.png"))); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/enviar32x32_2.png"))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnCozinha.png"))); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cadeado.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Anexar ao Delivery");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Reenvio Cozinha");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Status Cozinha");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Bloquear Tela");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(41, 41, 41))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 550));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Detalhe do Pedido");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 710, 60));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Número Mesa");

        lblNumeroMesa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNumeroMesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroMesa.setText("0");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Número Pedido");

        lblNPedido.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNPedido.setText("0");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Garçom");

        lblGarcom.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGarcom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGarcom.setText("nome");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumeroMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblGarcom, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumeroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGarcom, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, -1, 522));

        tbDetalhePedido.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "PRODUTO", "QTD", "VLR UNITÁRIO R$", "VLR TOTAL R$"
            }
        ));
        tbDetalhePedido.setRowHeight(21);
        jScrollPane1.setViewportView(tbDetalhePedido);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 688, 328));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Código");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, -1));

        txtCodigoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtCodigoProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCodigoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProdutoKeyPressed(evt);
            }
        });
        jPanel3.add(txtCodigoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 41));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Qtd");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 50, -1));

        txtQtd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtQtd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQtd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtQtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtdKeyReleased(evt);
            }
        });
        jPanel3.add(txtQtd, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 50, 41));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("VLR Total");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 80, -1));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 50, 43));

        lblVlrUnitario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVlrUnitario.setText("R$");
        jPanel3.add(lblVlrUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 80, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Produto");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 140, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("VLR Unitário");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 90, -1));

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTotal.setText("R$");
        jPanel3.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 80, 40));

        lblDescricao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDescricao.setText("[NOME DO PRODUTO]");
        jPanel3.add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 280, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 690, 90));

        jPanel4.setBackground(new java.awt.Color(38, 53, 61));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/masterfood6_144x136.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel22)
                .addContainerGap(397, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 560));

        lblMensagem.setForeground(new java.awt.Color(255, 51, 51));
        getContentPane().add(lblMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 155, 450, 20));

        lblIdMesa.setText("idMesa");
        getContentPane().add(lblIdMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, -1, -1));

        lblIdProduto.setText("idProduto");
        getContentPane().add(lblIdProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 520, -1, -1));

        lblIdGarcom.setText("idGarçom");
        getContentPane().add(lblIdGarcom, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 520, -1, -1));

        setSize(new java.awt.Dimension(1177, 594));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdutoKeyPressed
        // Adiciona um item ao pedido
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCodigoProduto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código Inválido!");
            } else {
                Produto produto = new Produto();
                produto.setId(txtCodigoProduto.getText());

                try {
                    pLocalizado = cproduto.localizaProduto(produto);
                    if (pLocalizado.getNome() == null) {
                        lblDescricao.setText("[Produto não localizado!]");
                    } else {
                        lblDescricao.setText(pLocalizado.getNome());
                        lblIdProduto.setText(pLocalizado.getId());
                        lblVlrUnitario.setText(String.format("%9.2f", Double.parseDouble(pLocalizado.getValor())));
                        txtQtd.setEnabled(true);
                        txtQtd.requestFocus();
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Produto não localizado!");
                }

                System.out.println(pLocalizado.getNome());
            }
        }
    }//GEN-LAST:event_txtCodigoProdutoKeyPressed

    private void txtQtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdKeyPressed
        double quantidade = 0;
        if (txtCodigoProduto.getText().isEmpty()) { // Verifica se o código do produto está vazio
            JOptionPane.showMessageDialog(this, "Informe o código do produto!", "Atenção!", JOptionPane.ERROR_MESSAGE);
        } else {
            // Verifica se a tecla pressionada é a tecla ENTER e realiza o cálculo            
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    quantidade = Double.parseDouble(txtQtd.getText());
                } catch (NumberFormatException e) {
                    //System.out.println("br.com.bar.view.TelaDetalheMesa.txtQtdKeyPressed()"+e);
                }
                // Calcula o total do produto
                double vlr = Double.parseDouble(lblVlrUnitario.getText().replace(",", "."));
                lblTotal.setText(totaliza(quantidade, vlr));
                adicionaItemNoPedido();
                limpaform();

            }
        }
    }//GEN-LAST:event_txtQtdKeyPressed

    private void txtQtdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdKeyReleased
        txtQtd.setText(txtQtd.getText().replaceAll("[^0-9]", ""));
    }//GEN-LAST:event_txtQtdKeyReleased

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
       // Bloqeia Tela
        TelaBloqueio b = new TelaBloqueio();
        b.setModal(true);
        b.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked
    // Totaliza item localizado e retorna o total formatado ocm duas casas decimais
    private String totaliza(double qtd, double valorUnit) {

        double total = qtd * valorUnit;

        return String.format("%9.2f", total);

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
            java.util.logging.Logger.getLogger(TelaDetalheMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDetalheMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDetalheMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDetalheMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDetalheMesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblGarcom;
    private javax.swing.JLabel lblIdGarcom;
    private javax.swing.JLabel lblIdMesa;
    private javax.swing.JLabel lblIdProduto;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblNPedido;
    private javax.swing.JLabel lblNumeroMesa;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVlrUnitario;
    private javax.swing.JTable tbDetalhePedido;
    private javax.swing.JFormattedTextField txtCodigoProduto;
    private javax.swing.JFormattedTextField txtQtd;
    // End of variables declaration//GEN-END:variables

    private void bloqueiaCampos() {
        txtQtd.setEnabled(false);
    }

    private void limpaform() {
        txtQtd.setText(null);
        txtCodigoProduto.setText(null);
        txtCodigoProduto.requestFocus();
        lblDescricao.setText(null);
        lblVlrUnitario.setText("0,00");
        lblTotal.setText("0,00");
    }
}
