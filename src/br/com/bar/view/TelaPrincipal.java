
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.util.Util;
import br.com.br.controler.ControlerContasApagar;
import br.com.br.controler.ControlerCozinha;
import br.com.br.controler.ControlerEstoque;
import br.com.br.controler.ControlerMesa;
import br.com.br.controler.ControlerParametro;
import java.awt.Color;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
//javax.swing.JFrame
public class TelaPrincipal extends javax.swing.JFrame {

    Date data = new Date();
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    ControlerMesa m = new ControlerMesa();
    Connection conexao = null;
    ControlerParametro param = new ControlerParametro();
    ControlerContasApagar cc = new ControlerContasApagar();
    ControlerCozinha cz = new ControlerCozinha();
    ControlerEstoque estoque = new ControlerEstoque();
    Util u = new Util();

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        lblLogo.setIcon(u.carregaLogo());
        lblData.setText(df.format(data)); // Exibe data atual
        lblCargo.setVisible(false);
        // Verifica contas vencidas em aberto.
        atualizaInformativo();
        /*
            if (cc.contasVencidas()) {

                jLabel7.setForeground(Color.red);
                lblAviso.setText("*Você possui Contas vencidas ou com vencimento para hoje!");

            }

            if (estoque.estoqueBaixo()){
                if ("".equals(lblAviso.getText())){
                    lblAviso.setText("*Existe(m) produto(s) com estoque inferior a quantidade desejada!");
                }else {
                    lblAviso2.setText("*Existe(m) produto(s) com estoque inferior a quantidade desejada!");
                }
                lblGestao.setForeground(Color.red);
            }
            lblAviso.setForeground(Color.red);
            lblAviso2.setForeground(Color.red);
         */
        // Determina tempo de execução
        long timeMilis = 6000; // milisegundos
        Timer timer = new Timer();
        TimerTask atualizaEstatistica = new TimerTask() {
            @Override
            public void run() {
                estatistica();
            }

        };
        timer.scheduleAtFixedRate(atualizaEstatistica, 0, timeMilis);

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

        peinelEsquerdo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblNmesaLivre = new javax.swing.JLabel();
        lblLivres = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblNmesaOcupada = new javax.swing.JLabel();
        lblOcupadas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblCargo = new javax.swing.JLabel();
        btnProduto = new javax.swing.JPanel();
        lblGestao = new javax.swing.JLabel();
        btnConfiguracao = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnConasApagar = new javax.swing.JPanel();
        lblBtnPagamentos = new javax.swing.JLabel();
        btnCaixa = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnFuncionarios1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnRelatorios = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblFEchar = new javax.swing.JLabel();
        btnLancarPedido = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblmsg1 = new javax.swing.JLabel();
        btnFuncionarios2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblmsg2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        peinelEsquerdo.setBackground(new java.awt.Color(52, 73, 94));
        peinelEsquerdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(52, 73, 94));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Light", 0, 14), new java.awt.Color(255, 255, 255)))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/logo.png"))); // NOI18N
        jPanel2.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 215, 180));

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lblOperador.setForeground(new java.awt.Color(255, 255, 255));
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario_branco.png"))); // NOI18N
        lblOperador.setText("jLabel5");
        jPanel2.add(lblOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 116, 30));

        lblData.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/calendar24x24.png"))); // NOI18N
        lblData.setText("jLabel5");
        jPanel2.add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 120, 30));

        jPanel4.setBackground(new java.awt.Color(52, 73, 94));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Taxa de Ocupação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255)))))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNmesaLivre.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblNmesaLivre.setForeground(new java.awt.Color(255, 255, 255));
        lblNmesaLivre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNmesaLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/mesa.png"))); // NOI18N
        lblNmesaLivre.setText("jLabel16");
        jPanel4.add(lblNmesaLivre, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 27, 90, 30));

        lblLivres.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lblLivres.setForeground(new java.awt.Color(255, 255, 255));
        lblLivres.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLivres.setText("Livres");
        lblLivres.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel4.add(lblLivres, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 27, 60, 30));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Livres");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 27, 50, 30));

        lblNmesaOcupada.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblNmesaOcupada.setForeground(new java.awt.Color(255, 255, 255));
        lblNmesaOcupada.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNmesaOcupada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/mesavermelha (2).png"))); // NOI18N
        lblNmesaOcupada.setText("jLabel17");
        jPanel4.add(lblNmesaOcupada, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 57, 92, 30));

        lblOcupadas.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lblOcupadas.setForeground(new java.awt.Color(255, 255, 255));
        lblOcupadas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOcupadas.setText("Ocupadas");
        lblOcupadas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel4.add(lblOcupadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 57, 60, 30));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ocupadas");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 57, -1, 30));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 260, 100));

        peinelEsquerdo.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 510));

        getContentPane().add(peinelEsquerdo);
        peinelEsquerdo.setBounds(0, 0, 300, 530);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(null);

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("jLabel6");
        jPanel1.add(lblCargo);
        lblCargo.setBounds(30, 50, 124, 32);

        btnProduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProdutoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProdutoMouseEntered(evt);
            }
        });

        lblGestao.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lblGestao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/gerenciar64x64.png"))); // NOI18N
        lblGestao.setText("Gestão");
        lblGestao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblGestaoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout btnProdutoLayout = new javax.swing.GroupLayout(btnProduto);
        btnProduto.setLayout(btnProdutoLayout);
        btnProdutoLayout.setHorizontalGroup(
            btnProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGestao, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnProdutoLayout.setVerticalGroup(
            btnProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGestao, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btnProduto);
        btnProduto.setBounds(230, 210, 195, 99);

        btnConfiguracao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnConfiguracao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfiguracaoMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/configuracaoCinzaEscuro.png"))); // NOI18N
        jLabel6.setText("Configurações");

        javax.swing.GroupLayout btnConfiguracaoLayout = new javax.swing.GroupLayout(btnConfiguracao);
        btnConfiguracao.setLayout(btnConfiguracaoLayout);
        btnConfiguracaoLayout.setHorizontalGroup(
            btnConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnConfiguracaoLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        btnConfiguracaoLayout.setVerticalGroup(
            btnConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnConfiguracaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btnConfiguracao);
        btnConfiguracao.setBounds(30, 320, 195, 99);

        btnConasApagar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnConasApagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConasApagarMouseClicked(evt);
            }
        });

        lblBtnPagamentos.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        lblBtnPagamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/contas.png"))); // NOI18N
        lblBtnPagamentos.setText("Pagamentos");

        javax.swing.GroupLayout btnConasApagarLayout = new javax.swing.GroupLayout(btnConasApagar);
        btnConasApagar.setLayout(btnConasApagarLayout);
        btnConasApagarLayout.setHorizontalGroup(
            btnConasApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnConasApagarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBtnPagamentos, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnConasApagarLayout.setVerticalGroup(
            btnConasApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnConasApagarLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(lblBtnPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(btnConasApagar);
        btnConasApagar.setBounds(430, 210, 195, 99);

        btnCaixa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCaixaMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnBaixar2.png"))); // NOI18N
        jLabel12.setText("Caixa");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnCaixaLayout = new javax.swing.GroupLayout(btnCaixa);
        btnCaixa.setLayout(btnCaixaLayout);
        btnCaixaLayout.setHorizontalGroup(
            btnCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnCaixaLayout.setVerticalGroup(
            btnCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btnCaixa);
        btnCaixa.setBounds(430, 100, 195, 99);

        btnFuncionarios1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnFuncionarios1.setPreferredSize(new java.awt.Dimension(167, 98));
        btnFuncionarios1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFuncionarios1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFuncionarios1MouseEntered(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/refeicao.png"))); // NOI18N
        jLabel11.setText("Cozinha");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnFuncionarios1Layout = new javax.swing.GroupLayout(btnFuncionarios1);
        btnFuncionarios1.setLayout(btnFuncionarios1Layout);
        btnFuncionarios1Layout.setHorizontalGroup(
            btnFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnFuncionarios1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
        );
        btnFuncionarios1Layout.setVerticalGroup(
            btnFuncionarios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
        );

        jPanel1.add(btnFuncionarios1);
        btnFuncionarios1.setBounds(230, 100, 195, 99);

        btnRelatorios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnRelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRelatoriosMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/relatorios48x48.png"))); // NOI18N
        jLabel16.setText("Relatórios");

        javax.swing.GroupLayout btnRelatoriosLayout = new javax.swing.GroupLayout(btnRelatorios);
        btnRelatorios.setLayout(btnRelatoriosLayout);
        btnRelatoriosLayout.setHorizontalGroup(
            btnRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRelatoriosLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        btnRelatoriosLayout.setVerticalGroup(
            btnRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRelatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btnRelatorios);
        btnRelatorios.setBounds(230, 320, 195, 99);

        jPanel3.setBackground(new java.awt.Color(52, 73, 94));

        lblFEchar.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        lblFEchar.setForeground(new java.awt.Color(255, 255, 255));
        lblFEchar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFEchar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        lblFEchar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFEcharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblFEchar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblFEchar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(615, 0, 40, 41);

        btnLancarPedido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnLancarPedido.setPreferredSize(new java.awt.Dimension(193, 108));
        btnLancarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLancarPedidoMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/pedidos.png"))); // NOI18N
        jLabel17.setText("Pedidos");

        javax.swing.GroupLayout btnLancarPedidoLayout = new javax.swing.GroupLayout(btnLancarPedido);
        btnLancarPedido.setLayout(btnLancarPedidoLayout);
        btnLancarPedidoLayout.setHorizontalGroup(
            btnLancarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLancarPedidoLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        btnLancarPedidoLayout.setVerticalGroup(
            btnLancarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLancarPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btnLancarPedido);
        btnLancarPedido.setBounds(30, 100, 195, 99);

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/power.png"))); // NOI18N
        jLabel10.setText("Sair");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10);
        jLabel10.setBounds(500, 450, 110, 69);

        lblmsg1.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        jPanel1.add(lblmsg1);
        lblmsg1.setBounds(30, 430, 590, 30);

        btnFuncionarios2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnFuncionarios2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFuncionarios2MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/arquivo.png"))); // NOI18N
        jLabel13.setText("Cadastros");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnFuncionarios2Layout = new javax.swing.GroupLayout(btnFuncionarios2);
        btnFuncionarios2.setLayout(btnFuncionarios2Layout);
        btnFuncionarios2Layout.setHorizontalGroup(
            btnFuncionarios2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnFuncionarios2Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnFuncionarios2Layout.setVerticalGroup(
            btnFuncionarios2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
        );

        jPanel1.add(btnFuncionarios2);
        btnFuncionarios2.setBounds(30, 210, 195, 99);

        lblmsg2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        jPanel1.add(lblmsg2);
        lblmsg2.setBounds(30, 460, 480, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(300, 0, 660, 530);

        setSize(new java.awt.Dimension(955, 530));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblFEcharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFEcharMouseClicked

        // Fecha tela principal
        if (cz.pratoPendente() > 0) {
            JOptionPane.showMessageDialog(null, "Existem pratos com liberação pendente!");
        }

        int op = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar esta tela?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (op == JOptionPane.YES_OPTION) {

            System.exit(0);
        }
    }//GEN-LAST:event_lblFEcharMouseClicked

    private void btnProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProdutoMouseClicked
        // Chama tela de Cadastro de Produto
        TelaEstoque p = new TelaEstoque();
        p.recebeOperador(this, lblOperador.getText(), lblCargo.getText());
        p.setVisible(true);

    }//GEN-LAST:event_btnProdutoMouseClicked

    private void btnConfiguracaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracaoMouseClicked
        // Chama a tela de configuração

        TelaConfiguracao config = new TelaConfiguracao();
        config.recebeOperador(lblOperador.getText(), lblCargo.getText());
        config.setVisible(true);
    }//GEN-LAST:event_btnConfiguracaoMouseClicked

    private void btnConasApagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConasApagarMouseClicked

        TelaContasApagar contas = new TelaContasApagar();
        contas.recebeOperador(this, lblOperador.getText(), lblCargo.getText());
        contas.setModal(true);
        contas.setVisible(true);


    }//GEN-LAST:event_btnConasApagarMouseClicked

    private void btnCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCaixaMouseClicked

    }//GEN-LAST:event_btnCaixaMouseClicked

    private void btnFuncionarios1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFuncionarios1MouseClicked

    }//GEN-LAST:event_btnFuncionarios1MouseClicked

    private void btnFuncionarios1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFuncionarios1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFuncionarios1MouseEntered

    private void btnRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatoriosMouseClicked
        // Abre tela de Relatórios
        TelaRelatorio3 tr3 = new TelaRelatorio3();
        tr3.recebeOperador(lblOperador.getText(), lblCargo.getText());
        tr3.setVisible(true);

    }//GEN-LAST:event_btnRelatoriosMouseClicked

    private void btnProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProdutoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProdutoMouseEntered

    public void estatistica() {
        ArrayList<Double> estatiscas = new ArrayList<>();
        estatiscas = m.estatistica();

        lblLivres.setText(String.format("%9.0f", estatiscas.get(0)) + "%");
        lblOcupadas.setText(String.format("%9.0f", estatiscas.get(1)) + "%");

        lblNmesaLivre.setText(String.format("%9.0f", estatiscas.get(2)));
        lblNmesaOcupada.setText(String.format("%9.0f", estatiscas.get(3)));
    }
    private void btnLancarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLancarPedidoMouseClicked
        // Chama a tela Pedido 2
        TelaPedido2 pedido2 = new TelaPedido2();
        pedido2.recebeOperador(lblOperador.getText(), lblCargo.getText());
        pedido2.setVisible(true);

    }//GEN-LAST:event_btnLancarPedidoMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // Chama tela do Caixa

        TelaCaixa caixa = new TelaCaixa();
        caixa.recebeOperador(this, lblOperador.getText(), lblCargo.getText());
        caixa.setVisible(true);

    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // Sai do sistema e Chama a tela de login e avisa ao usuário se existe pedios em aberto.

        ArrayList<Double> dadosOcupacao = new ArrayList<>();

        dadosOcupacao = m.estatistica();
        double nMesasOcupadas = dadosOcupacao.get(1);

        if (nMesasOcupadas > 0) {
            int op = JOptionPane.showConfirmDialog(null, "Existem pedidos abertos! Deseja sair mesmo assim?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (op == JOptionPane.YES_OPTION) {

                dispose();
                TelaLogin login = new TelaLogin();
                login.setVisible(true);
            }

        } else {
            dispose();
            TelaLogin login = new TelaLogin();
            login.setVisible(true);
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // Vai para tela Cozinha
        TelaConzinha cozinha = new TelaConzinha();
        cozinha.recebeOperador(lblOperador.getText(), lblCargo.getText());

        cozinha.setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void lblGestaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblGestaoKeyPressed

    }//GEN-LAST:event_lblGestaoKeyPressed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        TelaCadastro cadastro = new TelaCadastro();
        cadastro.recebeOperador(lblOperador.getText(), lblCargo.getText());
        cadastro.setAlwaysOnTop(true);
        cadastro.setVisible(true);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void btnFuncionarios2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFuncionarios2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFuncionarios2MouseClicked

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCaixa;
    private javax.swing.JPanel btnConasApagar;
    private javax.swing.JPanel btnConfiguracao;
    private javax.swing.JPanel btnFuncionarios1;
    private javax.swing.JPanel btnFuncionarios2;
    private javax.swing.JPanel btnLancarPedido;
    private javax.swing.JPanel btnProduto;
    private javax.swing.JPanel btnRelatorios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblBtnPagamentos;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblFEchar;
    private javax.swing.JLabel lblGestao;
    private javax.swing.JLabel lblLivres;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNmesaLivre;
    private javax.swing.JLabel lblNmesaOcupada;
    private javax.swing.JLabel lblOcupadas;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblmsg1;
    private javax.swing.JLabel lblmsg2;
    private javax.swing.JPanel peinelEsquerdo;
    // End of variables declaration//GEN-END:variables

    public void atualizaInformativo() {
        
        if (cc.contasVencidas()) {

            lblBtnPagamentos.setForeground(Color.red);
            lblmsg1.setText("*Você possui Contas vencidas ou com vencimento para hoje!");

        } else {
            lblmsg1.setText(null);
            lblBtnPagamentos.setForeground(Color.black);
        }

        if (estoque.estoqueBaixo()) {
            lblmsg2.setText("*Existe(m) produto(s) com estoque inferior a quantidade desejada!");
            lblGestao.setForeground(Color.red);
        }else {
            lblmsg2.setText(null);
            lblGestao.setForeground(Color.black);
        }
        
        if (estoque.estoqueMinimo()>0){
            lblmsg2.setText("*Existe(m) produto(s) com quantidade mínima em estoque!");
            lblGestao.setForeground(Color.red);
        }
        
        lblmsg1.setForeground(Color.red);
        lblmsg2.setForeground(Color.red);
    }
}
