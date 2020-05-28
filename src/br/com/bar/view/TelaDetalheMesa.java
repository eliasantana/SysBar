/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.model.DadosEmpresa;
import br.com.bar.model.Produto;
import br.com.bar.model.ProdutoPedido;
import br.com.bar.model.TableModelDetalhePedido;
import br.com.bar.util.FormataValor;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerCozinha;
import br.com.br.controler.ControlerDadosEmpresa;
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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elias Santana
 * javax.swing.JFrame
 */
public class TelaDetalheMesa extends javax.swing.JFrame {

    TelaPedido3 tela;
    TelaGerenciarPedido gp;
    TelaPesquisaPreco pesquisa; // Armazena uma instância da tela Detalhe Mesa
    TelaConzinha telaCozinha;
    TelaCaixa cx;
    
    TelaAlteraSenha2 telaAlteraSenha;
    Util u = new Util();
    JButton btnMesa; // Botão com o número da mesa
    ControlerPedido cp = new ControlerPedido();
    ControlerProduto cproduto = new ControlerProduto();
    ControlerMesa cm = new ControlerMesa();
    ControlerFuncionario cf = new ControlerFuncionario();
    ControlerEstoque ec = new ControlerEstoque();
    ControlerCozinha cc = new ControlerCozinha();
    ControlerDadosEmpresa de = new ControlerDadosEmpresa();
    DadosEmpresa d =de.selecionaDados();
    
    TableModelDetalhePedido modelDetPedido = new TableModelDetalhePedido();
    Produto pLocalizado;
    FormataValor fv = new FormataValor();
    ArrayList<String> obsevacaoPrato; // Armazena dados do prato enviado a cozinha
    String operador, cargo, idOperador;
    TelaStatusCozinha status;   // Armazena uma stância da tela Cozinha
    String nomeGarcomCompleto;  // Armazena o nome completo do garçom
    int idProduto;              // Armazeana o id ao clicar no item sobre a tabela
    String descricao;           // Armazeana a desclição ao clicar na tabela
    String qtd;                 // Armazeana a quantidade do produto Selecionado
    Object[] opcao = {"   Não  ","   Sim   "}; // Armazena opções de botões para a tela de ocnfirmação 
    public TelaDetalheMesa() {
        initComponents();
        txtQtd.setEnabled(false);
        limpaform();
        lblIdMesa.setVisible(false);
        lblIdProduto.setVisible(false);
        lblIdGarcom.setVisible(true);
        lblBtnReenvioCozinha.setEnabled(false);
        lblTextoReenvioCozinha.setEnabled(false);
        lblAnexarDelivery.setVisible(true);
        lblTextoDelivery.setVisible(true);
        panelFechar.setVisible(false);
        txtIdProduto.setVisible(false);
        lblCozinha.setVisible(false);
        lblTextoCozinha.setVisible(false);
        
        if (d.getAtivaDelivery()==0){
            lblAnexarDelivery.setVisible(false);
            lblTextoDelivery.setVisible(false);
        }
    }

    //Atualiza a tabela detalhe mesa    
    private void atualizaTabela(String nMesa) {
        tbDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(nMesa, lblNPedido.getText())));
        modelDetPedido.redimensionaColunas(tbDetalhePedido);        
    }

    public void recebeTela(TelaPedido3 tp3, JButton btn) {
        this.tela = tp3;
        this.btnMesa = btn;

    }

    // Recebe Instancia da Tela de Caixa
    public void recebeTelaCaixa(TelaCaixa tela) {
        this.cx = tela;

    }

    // Limpa mensagem de produto enviado para cozinha
    private void limpaMensagem(){
        lblMensagem.setText(null);
    } 
            
    public void recebeMesa(String nMesa) {
        lblNumeroMesa.setText(nMesa);
        lblNPedido.setText(cp.LocalizaIdPedido(nMesa));
        atualizaTabela(nMesa);
        lblIdMesa.setText(cm.localizaIdMesa(nMesa));
        nomeGarcomCompleto = cf.retornaGarcom(lblNumeroMesa.getText());
        String[] nomeSeparado = nomeGarcomCompleto.split(" ");
        lblGarcom.setText(nomeSeparado[0]);
        lblIdGarcom.setText(cf.localizaId(nomeGarcomCompleto));
    }

    // Recebe o operador Logado
    public void recebeOperador(String operador, String cargo) {
        this.operador = operador;
        this.cargo = cargo;
        idOperador = cf.localizaIdLogin(operador);

        if (!"Gerente".equals(cargo)) {
            lblTextoCozinha.setVisible(false);
            lblCozinha.setVisible(false);
            lblGestaoPedidos.setVisible(false);
            lblTextoGestao.setVisible(false);
        }
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
                //lblMensagem.setText(null);

                // Confirma pedido
                // Instancia um objeto Produto Pedido
                ProdutoPedido pp = new ProdutoPedido();
                // Seta dados no objeto
                // Excluir após validação de Janiel linha 144
                //pp.setTbproduto_id(txtCodigoProduto.getText());
                pp.setTbproduto_id(txtIdProduto.getText());
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
                        txtIdProduto.setText(null);
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
                        // Envia para cozinha se o campo na tb_dados_empresa for igual a 1
                        if (d.getAtivaCozinha()==1){
                            
                            ArrayList<String> pCozinha = new ArrayList<>();

                            //codProduto, produto, qtd, funcionario, mesa, data, status
                            pCozinha.add(lblDescricao.getText()); // Produto
                            pCozinha.add(txtCodigoProduto.getText()); // // Código Produto
                            pCozinha.add(txtQtd.getText()); // Qtd         
                            pCozinha.add(nomeGarcomCompleto); // Nome do Funcionario
                            pCozinha.add(lblNumeroMesa.getText()); // Numero da mesa
                            pCozinha.add("Pendente"); // Status Pendente - Liberado
                            pCozinha.add(lblNPedido.getText());
                            Date dtAtual = new Date();
                            Timestamp tms = new Timestamp(dtAtual.getTime());
                            pCozinha.add(String.valueOf(tms)); // Data Atual
                            int op = JOptionPane.showOptionDialog(this, "Deseja adicionar uma observação?", "Atenção", 
                                     JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[1]);
                            // Chama a tela de observação
                            if (op == 1) {

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
            // System.out.println("br.com.bar.view.TelaPedido2.txtQtdKeyPressed()" + e);
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
            lblMensagem.setText("*Solicitação de prato enviada para a cozinha!");
        } else {
            lblMensagem.setText("Erro ao tentar enviar solicitação de prato para a cozinha - Contate o SUPORTE!");
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
        lblAnexarDelivery = new javax.swing.JLabel();
        lblBtnReenvioCozinha = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTextoDelivery = new javax.swing.JLabel();
        lblTextoReenvioCozinha = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTextoStatusCozinha = new javax.swing.JLabel();
        lblStatusCozinha = new javax.swing.JLabel();
        lblAlterarSenha = new javax.swing.JLabel();
        lblTextoAlterarSenha = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblNumeroMesa = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblNPedido = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblGarcom = new javax.swing.JLabel();
        lblCozinha = new javax.swing.JLabel();
        lblTextoCozinha = new javax.swing.JLabel();
        lblGestaoPedidos = new javax.swing.JLabel();
        lblTextoGestao = new javax.swing.JLabel();
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
        lblIdMesa = new javax.swing.JLabel();
        lblIdProduto = new javax.swing.JLabel();
        lblIdGarcom = new javax.swing.JLabel();
        lblMensagem = new javax.swing.JLabel();
        panelSuperior = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        panelFechar = new javax.swing.JPanel();
        lblFechar = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();

        jLabel19.setText("jLabel19");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MasterFood - Detalhe Mesa");
        setExtendedState(6);
        setMaximumSize(new java.awt.Dimension(32767, 32767));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        lblAnexarDelivery.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnexarDelivery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/anexar.png"))); // NOI18N
        lblAnexarDelivery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnexarDeliveryMouseClicked(evt);
            }
        });

        lblBtnReenvioCozinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBtnReenvioCozinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/enviar32x32_2.png"))); // NOI18N
        lblBtnReenvioCozinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBtnReenvioCozinhaMouseClicked(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cadeado.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        lblTextoDelivery.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextoDelivery.setText("Delivery");

        lblTextoReenvioCozinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextoReenvioCozinha.setText("Reenvio Cozinha");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Bloquear Tela");

        lblTextoStatusCozinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextoStatusCozinha.setText("Status Cozinha");

        lblStatusCozinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatusCozinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnCozinha.png"))); // NOI18N
        lblStatusCozinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStatusCozinhaMouseClicked(evt);
            }
        });

        lblAlterarSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlterarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/chave48x48.png"))); // NOI18N
        lblAlterarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlterarSenhaMouseClicked(evt);
            }
        });
        lblAlterarSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lblAlterarSenhaKeyReleased(evt);
            }
        });

        lblTextoAlterarSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextoAlterarSenha.setText("Alterar Senha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblTextoDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAlterarSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAnexarDelivery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTextoReenvioCozinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBtnReenvioCozinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStatusCozinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTextoStatusCozinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTextoAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lblAnexarDelivery)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTextoDelivery)
                .addGap(18, 18, 18)
                .addComponent(lblBtnReenvioCozinha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTextoReenvioCozinha)
                .addGap(21, 21, 21)
                .addComponent(lblStatusCozinha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTextoStatusCozinha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(lblAlterarSenha)
                .addGap(3, 3, 3)
                .addComponent(lblTextoAlterarSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(240, 180, 130, 500);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Mesa");

        lblNumeroMesa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNumeroMesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroMesa.setText("0");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText(" Pedido");

        lblNPedido.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNPedido.setText("0");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Atendente");

        lblGarcom.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGarcom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGarcom.setText("nome");

        lblCozinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCozinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cozinha32x32.png"))); // NOI18N
        lblCozinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCozinhaMouseClicked(evt);
            }
        });

        lblTextoCozinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextoCozinha.setText("Cozinha");

        lblGestaoPedidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGestaoPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/relatorios32x32.png"))); // NOI18N
        lblGestaoPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGestaoPedidosMouseClicked(evt);
            }
        });

        lblTextoGestao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextoGestao.setText("Gestão de Pedidos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNumeroMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTextoGestao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGarcom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTextoCozinha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCozinha, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lblGestaoPedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumeroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGarcom, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(lblGestaoPedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTextoGestao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCozinha, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTextoCozinha)
                .addContainerGap())
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(1170, 180, 110, 500);

        tbDetalhePedido = new javax.swing.JTable(){
            public boolean isCellEditable (int rowIndex, int colIndex){
                return false;
            }
        };
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
        tbDetalhePedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetalhePedidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDetalhePedido);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(380, 180, 790, 500);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Código");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, -1));

        txtCodigoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtCodigoProduto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCodigoProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodigoProdutoMouseClicked(evt);
            }
        });
        txtCodigoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProdutoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProdutoKeyReleased(evt);
            }
        });
        jPanel3.add(txtCodigoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 120, 41));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Qtd");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 50, -1));

        txtQtd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtQtd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQtd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtQtd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtQtdMouseClicked(evt);
            }
        });
        txtQtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtdKeyReleased(evt);
            }
        });
        jPanel3.add(txtQtd, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 40, 41));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("VLR Total");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 80, -1));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 40, 40));

        lblVlrUnitario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVlrUnitario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVlrUnitario.setText("R$");
        jPanel3.add(lblVlrUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 70, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Produto");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 20, 140, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("VLR Unitário");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 90, -1));

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("R$");
        jPanel3.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, 80, 40));

        lblDescricao.setBackground(new java.awt.Color(204, 204, 204));
        lblDescricao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDescricao.setText("[NOME DO PRODUTO]");
        lblDescricao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 40, 370, 41));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(380, 60, 790, 90);

        jPanel4.setBackground(new java.awt.Color(38, 53, 61));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/masterfood6_220x220.png"))); // NOI18N

        lblIdMesa.setText("idMesa");

        lblIdProduto.setText("idProduto");

        lblIdGarcom.setText("idGarçom");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblIdMesa)
                .addGap(47, 47, 47)
                .addComponent(lblIdProduto)
                .addGap(24, 24, 24)
                .addComponent(lblIdGarcom)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 418, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdMesa)
                    .addComponent(lblIdProduto)
                    .addComponent(lblIdGarcom))
                .addGap(87, 87, 87))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 240, 790);

        lblMensagem.setForeground(new java.awt.Color(0, 0, 255));
        getContentPane().add(lblMensagem);
        lblMensagem.setBounds(400, 150, 450, 20);

        panelSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Detalhes do Pedido");
        panelSuperior.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 0, 360, 60));

        getContentPane().add(panelSuperior);
        panelSuperior.setBounds(260, 0, 930, 60);

        panelFechar.setBackground(new java.awt.Color(38, 53, 61));

        lblFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        lblFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelFecharLayout = new javax.swing.GroupLayout(panelFechar);
        panelFechar.setLayout(panelFecharLayout);
        panelFecharLayout.setHorizontalGroup(
            panelFecharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panelFecharLayout.setVerticalGroup(
            panelFecharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        getContentPane().add(panelFechar);
        panelFechar.setBounds(1240, 0, 40, 40);
        getContentPane().add(txtIdProduto);
        txtIdProduto.setBounds(280, 100, 59, 40);

        setSize(new java.awt.Dimension(1295, 752));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdutoKeyPressed
        // Adiciona um item ao pedido    
        txtCodigoProduto.setText(u.tamanhoMaximo(txtCodigoProduto.getText(), 11));
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            lblMensagem.setText(null);
            if (txtCodigoProduto.getText().isEmpty()) {
                // Chama tela pesquisa de preço
                if (pesquisa == null) {
                    pesquisa = new TelaPesquisaPreco();
                    pesquisa.setAlwaysOnTop(true);
                    pesquisa.defineOrigem(this, 1);
                    pesquisa.setTitle("MasterFood - Pesquisa de Produtos");
                }
                pesquisa.setVisible(true);
            } else {
                Produto produto = new Produto();
                produto.setId(txtCodigoProduto.getText());

                try {
                    pLocalizado = cproduto.localizaProduto(produto);
                    if (pLocalizado.getNome() == null) {
                        lblDescricao.setText("[Produto não localizado]");
                    } else {
                        lblDescricao.setText(pLocalizado.getNome());
                        lblIdProduto.setText(pLocalizado.getId());
                        txtIdProduto.setText(pLocalizado.getId());
                        lblVlrUnitario.setText(String.format("%9.2f", Double.parseDouble(pLocalizado.getValor())));
                        txtQtd.setEnabled(true);
                        txtQtd.requestFocus();
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Produto não localizado!");
                }  
            }
        }
    }//GEN-LAST:event_txtCodigoProdutoKeyPressed

    private void txtQtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdKeyPressed

        double quantidade = 0;

        try {
            quantidade = Double.parseDouble(txtQtd.getText());
        } catch (NumberFormatException e) {

        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Verifica se a tecla pressionada é a tecla ENTER e realiza o cálculo      
            if (quantidade == 0) {
                JOptionPane.showMessageDialog(this, "Informe uma quantidade válida!", "Atenção!", JOptionPane.ERROR_MESSAGE);
            } else {
                adicionaItemNoPedido();
                if (cx != null) {
                    cx.atualizaPedidoNoCaixa();
                }
                txtCodigoProduto.setText(null);
            }
        }
    }//GEN-LAST:event_txtQtdKeyPressed

    private void txtQtdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdKeyReleased
        txtQtd.setText(u.tamanhoMaximo(txtQtd.getText(), 2));
        try {
            txtQtd.setText(txtQtd.getText().replaceAll("[^0-9]", ""));
            double quantidade = Double.parseDouble(txtQtd.getText());
            // Calcula o total do produto
            double vlr = Double.parseDouble(lblVlrUnitario.getText().replace(",", "."));
            lblTotal.setText(totaliza(quantidade, vlr));
        } catch (NumberFormatException e) {
            //System.out.println("Ouve uma excessão aqui"+e);
            if (txtQtd.getText().length() == 0) {
                lblTotal.setText("0,00");

            }
        }
    }//GEN-LAST:event_txtQtdKeyReleased

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // Bloqeia Tela
        TelaBloqueio b = new TelaBloqueio();
        b.setAlwaysOnTop(true);
        b.setModal(true);
        b.setVisible(true);
        limpaMensagem();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // Chama tela pesquisa de preço
        if (pesquisa == null) {
            pesquisa = new TelaPesquisaPreco();
            pesquisa.defineOrigem(this, 1);
            pesquisa.setTitle("MasterFood - Pesquisa de Produtos");
            pesquisa.setAlwaysOnTop(true);
        }
        pesquisa.setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void lblCozinhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCozinhaMouseClicked
        // Chama Tela Cozinha
        if (telaCozinha == null) {
            telaCozinha = new TelaConzinha();
            telaCozinha.recebeOperador(operador, cargo);
            telaCozinha.setAlwaysOnTop(true);
        }
        telaCozinha.setVisible(true);
        limpaMensagem();

    }//GEN-LAST:event_lblCozinhaMouseClicked

    private void lblStatusCozinhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStatusCozinhaMouseClicked
        if (lblStatusCozinha.isEnabled()) {
            // Se tela Status não estiver instanciada. Instancia e abre a tela.
            if (status == null) {
                status = new TelaStatusCozinha();
                status.setAlwaysOnTop(true);
            }
            String nPedido = lblNPedido.getText();
            String numeroMesa = lblNumeroMesa.getText();

            status.recebeOperador(nomeGarcomCompleto, nPedido, numeroMesa);
            status.setVisible(true);

        }
        limpaMensagem();
    }//GEN-LAST:event_lblStatusCozinhaMouseClicked

    private void tbDetalhePedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalhePedidoMouseClicked

        txtCodigoProduto.setText(null);
        int linha = tbDetalhePedido.getSelectedRow();
        idProduto = Integer.parseInt(tbDetalhePedido.getModel().getValueAt(linha, 0).toString());
        descricao = tbDetalhePedido.getModel().getValueAt(linha, 1).toString();
        qtd = tbDetalhePedido.getModel().getValueAt(linha, 2).toString();

        // Só habilita botão de reenvio cozinha se estiver habilitado na tabela tb_dados_empresa
        if (d.getAtivaCozinha()==1){
            
            String grupo = cproduto.localizaGrupoProduto(idProduto);

            if (grupo.toLowerCase().equals("cozinha")) {
                // Habilita os botões [REEVIO COZINHA] se o prato não existir.
                if (!cc.temNaCozinha(String.valueOf(idProduto), lblNPedido.getText())) {
                    lblBtnReenvioCozinha.setEnabled(true);
                    lblTextoReenvioCozinha.setEnabled(true);
                } else {
                    lblBtnReenvioCozinha.setEnabled(false);
                    lblTextoReenvioCozinha.setEnabled(false);
                    lblStatusCozinha.setEnabled(true);
                    lblTextoStatusCozinha.setEnabled(true);
                }
            } else {
                lblBtnReenvioCozinha.setEnabled(false);
                lblTextoReenvioCozinha.setEnabled(false);
            }
        }
        
    }//GEN-LAST:event_tbDetalhePedidoMouseClicked

    public void atuDetalheDoPedido(String nMesa, String nPedido) {
        // Lista os produtos do pedido selecionado
        tbDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.detalhePorPedido(nMesa, nPedido)));
        modelDetPedido.redimensionaColunas(tbDetalhePedido);
    }

    public void atualizaPedidos() {
        // Atualiza pedido após. Este método é executado após remoção do pedido 
        // Na tela de Gerenciamento de Peidido.
        tbDetalhePedido.setModel(DbUtils.resultSetToTableModel(cp.listaPedidos(lblIdGarcom.getText())));
        modelDetPedido.redimensionaColunas(tbDetalhePedido);
    }

    public void atualizaTela() {
        pesquisa = null;
    }
    public void atualizaTelaGestão() {
        gp = null;
    }

    private void lblGestaoPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGestaoPedidosMouseClicked
        if (gp == null) {
            gp = new TelaGerenciarPedido();
            gp.selecionaPedido(lblNPedido.getText(), lblNumeroMesa.getText());
            gp.recebeTela(tela, btnMesa);
            gp.setAlwaysOnTop(true);
            gp.recebeOperador(this, operador, cargo);
        }
        gp.setVisible(true);
        limpaMensagem();
    }//GEN-LAST:event_lblGestaoPedidosMouseClicked

    private void lblAlterarSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlterarSenhaMouseClicked
        if (telaAlteraSenha == null) {
            telaAlteraSenha = new TelaAlteraSenha2();
            telaAlteraSenha.setAlwaysOnTop(true);
            telaAlteraSenha.recebeTela(this, "DetalheMesa"); // Envia o Jframe como parâmetro e informa o nome do Jframe
            telaAlteraSenha.receberOperador(operador);

        }
        telaAlteraSenha.setVisible(true);
        limpaMensagem();
    }//GEN-LAST:event_lblAlterarSenhaMouseClicked

    private void txtCodigoProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdutoKeyReleased
        txtCodigoProduto.setText(txtCodigoProduto.getText().replaceAll("[^0-9]",""));
        
        // Limpa dos campos código e quantidade e bloqueia o TextFild quantidade

        if (txtCodigoProduto.getText().length() == 0) {
            txtCodigoProduto.setText(null);
            lblDescricao.setText(null);
            lblVlrUnitario.setText("0,00");
            lblTotal.setText("0,00");
            txtQtd.setText(null);
            txtQtd.setEnabled(false);
        }
    }//GEN-LAST:event_txtCodigoProdutoKeyReleased

    private void lblBtnReenvioCozinhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBtnReenvioCozinhaMouseClicked
        // Reenvia para to para a cozinha.
        if (lblBtnReenvioCozinha.isEnabled()) {

            ArrayList<String> pCozinha = new ArrayList<>();

            //codProduto, produto, qtd, funcionario, mesa, data, status
            pCozinha.add(descricao); // Descrição do Produto
            pCozinha.add(String.valueOf(idProduto)); // // Código Produto
            pCozinha.add(qtd); // Qtd         
            pCozinha.add(nomeGarcomCompleto); // Nome do Funcionario
            pCozinha.add(lblNumeroMesa.getText()); // Numero da mesa
            pCozinha.add("Pendente"); // Status Pendente - Liberado
            pCozinha.add(lblNPedido.getText());
            Date dtAtual = new Date();
            
            Timestamp tms = new Timestamp(dtAtual.getTime());
            pCozinha.add(String.valueOf(tms)); // Data Atual 
            int op = JOptionPane.showOptionDialog(this, "Deseja adicionar uma observação?", "Atenção", JOptionPane.YES_NO_OPTION, 
                     JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[1]);

            // Chama a tela de observação
            if (op == 1) {

                TelaObservacaoProduto telaObs = new TelaObservacaoProduto();
                telaObs.recebeTela3(this, pCozinha); // Envia dados do produto
                telaObs.setAlwaysOnTop(true);
                telaObs.setVisible(true);
            } else {

                pCozinha.add(null);//Obsrevação do prato                               
                // Envia prato para cozinha
                enviaParaCozinha(pCozinha);
            }
            // Envia prato para a cozinha
            //enviaParaCozinha(pCozinha);
            lblBtnReenvioCozinha.setEnabled(false);
            lblBtnReenvioCozinha.setEnabled(false);
        }
    }//GEN-LAST:event_lblBtnReenvioCozinhaMouseClicked

    private void lblAlterarSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblAlterarSenhaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAlterarSenhaKeyReleased

    private void txtCodigoProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigoProdutoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProdutoMouseClicked

    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        dispose();
    }//GEN-LAST:event_lblFecharMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
       limpaMensagem();
    }//GEN-LAST:event_formMouseClicked

    private void txtQtdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQtdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdMouseClicked

    private void lblAnexarDeliveryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnexarDeliveryMouseClicked
        
        if (!"delivery".equals(lblGarcom.getText().toLowerCase())){
            JOptionPane.showMessageDialog(this, "Esse pedido não pode ser anexado ao Delivery! ");
        }
        else {
            if (tbDetalhePedido.getRowCount() > 0){
                
                TelaDelivery td = new TelaDelivery();
                td.recebePedido(lblNPedido.getText(), lblNumeroMesa.getText(), lblIdGarcom.getText());
                td.setAlwaysOnTop(true);
                td.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this, "Antes de anexar o pedido deve conter pelo menos 1 (um) item!");
            }
        }
        
    }//GEN-LAST:event_lblAnexarDeliveryMouseClicked
    // Totaliza item localizado e retorna o total formatado ocm duas casas decimais
    private String totaliza(double qtd, double valorUnit) {

        double total = qtd * valorUnit;

        return fv.Formata(String.valueOf(total));

    }

    // Recebe o produto pesquisado e retorna: Código, Valor Unitário, Descrição.
    public void recebeProduto(Produto p) {

        txtCodigoProduto.setText(p.getCodigoProduto());
        txtIdProduto.setText(p.getId());
        lblDescricao.setText(p.getNome());
        lblVlrUnitario.setText(p.getValor());
        txtQtd.setEnabled(true);
        txtQtd.requestFocus();
    }

    // Limpa a variável responsável por armazenar a instância da tela na primeira vez 
    // que ela é aberta.
    public void atualizaTelaSenha() {
        telaAlteraSenha = null;

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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlterarSenha;
    private javax.swing.JLabel lblAnexarDelivery;
    private javax.swing.JLabel lblBtnReenvioCozinha;
    private javax.swing.JLabel lblCozinha;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblFechar;
    private javax.swing.JLabel lblGarcom;
    private javax.swing.JLabel lblGestaoPedidos;
    private javax.swing.JLabel lblIdGarcom;
    private javax.swing.JLabel lblIdMesa;
    private javax.swing.JLabel lblIdProduto;
    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblNPedido;
    private javax.swing.JLabel lblNumeroMesa;
    private javax.swing.JLabel lblStatusCozinha;
    private javax.swing.JLabel lblTextoAlterarSenha;
    private javax.swing.JLabel lblTextoCozinha;
    private javax.swing.JLabel lblTextoDelivery;
    private javax.swing.JLabel lblTextoGestao;
    private javax.swing.JLabel lblTextoReenvioCozinha;
    private javax.swing.JLabel lblTextoStatusCozinha;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVlrUnitario;
    private javax.swing.JPanel panelFechar;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JTable tbDetalhePedido;
    private javax.swing.JFormattedTextField txtCodigoProduto;
    private javax.swing.JTextField txtIdProduto;
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
