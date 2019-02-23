/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.model.DadosEmpresa;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerParametro;
import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class TelaCadastroEmpresa extends javax.swing.JFrame {

    ControlerDadosEmpresa dados = new ControlerDadosEmpresa();
    ControlerParametro p = new ControlerParametro();
    DadosEmpresa d = dados.selecionaDados();
    Util u = new Util();

    /**
     * Creates new form TelaCadastroEmpresa
     */
    public TelaCadastroEmpresa() {
        initComponents();
       
        txtIdEmpresa.setVisible(false);
        // Instancia o objeto e executa o método retornando um objetodo tipo
        // Dados empresa
        radioVisualizar.setSelected(true);
        txtUrlBackup.setVisible(false);

        d = dados.selecionaDados();
        // Oculta o botão adicionar se o dados da empresa for diferente de vazio
        //if (d.getNome_empresa().isEmpty()){
        //  lblAdicionar.setVisible(true);
        // }
       
        txtNomeEmpresa.setText(d.getNome_empresa());
        txtEndereco.setText(d.getEndereco());
        txtNumero.setText(String.valueOf(d.getNumero()));
        txtBairro.setText(d.getBairro());
        txtCep.setText(d.getCep());
        txtCidade.setText(d.getCidade());
        txtTelefone.setText(d.getTelefone());
        txtCelular.setText(d.getCelular());
        txtEmail.setText(d.getEmail());
        txtCnpj.setText(d.getCnpj());
        txtUrlBackup.setText(d.getUrlbackup());
        lblCaminho.setText(txtUrlBackup.getText());
        txturlLogo.setText(d.getLogo());
        txtIdEmpresa.setText(String.valueOf(d.getId()));
        comboUf.setSelectedItem(d.getUf());
        lblLogo.setIcon(u.carregaLogo());
        //Desabilita itens
        btnSelecionarArquivo.setVisible(true);
        lblTextocaminhoBanco.setVisible(true);
        txturlLogo.setVisible(false);
        lblCaminho.setVisible(true);
        //u.setIcon(this);
        lblOperador.setVisible(false);
        lblPerfil.setVisible(false);
    }

    // Recebe dados para identificação do operador
    public void recebeOperador(String operador, String perfil) {

        lblOperador.setText(operador);
        lblPerfil.setText(perfil);

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
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txturlLogo = new javax.swing.JTextField();
        txtIdEmpresa = new javax.swing.JTextField();
        txtUrlBackup = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        lblFechar = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNomeEmpresa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JFormattedTextField();
        lblExcluir = new javax.swing.JLabel();
        lblEditar = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jpanelImpressao = new javax.swing.JPanel();
        radioDireto = new javax.swing.JRadioButton();
        radioVisualizar = new javax.swing.JRadioButton();
        lblTextocaminhoBanco = new javax.swing.JLabel();
        lblCaminho = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnSelecionarArquivo = new javax.swing.JButton();
        comboUf = new javax.swing.JComboBox<>();
        txtCep = new javax.swing.JFormattedTextField();
        txtNumero = new javax.swing.JFormattedTextField();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(38, 53, 61));
        jPanel1.setForeground(new java.awt.Color(52, 73, 94));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/logo.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Carrega Logo do Sistema");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/foto (2).png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        txtUrlBackup.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUrlBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txturlLogo))
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(146, 146, 146))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(txturlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUrlBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 354, 590);

        jPanel2.setBackground(new java.awt.Color(38, 53, 61));

        lblFechar.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        lblFechar.setForeground(new java.awt.Color(255, 255, 255));
        lblFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        lblFechar.setPreferredSize(new java.awt.Dimension(40, 40));
        lblFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(857, 0, 46, 40);

        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("operador");
        getContentPane().add(lblOperador);
        lblOperador.setBounds(689, 90, 100, 32);

        lblPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblPerfil.setText("Perfil");
        getContentPane().add(lblPerfil);
        lblPerfil.setBounds(800, 90, 100, 32);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 36)); // NOI18N
        jLabel3.setText("Cadastro Empresa");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(480, 20, 310, 50);

        txtNomeEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeEmpresaKeyReleased(evt);
            }
        });
        getContentPane().add(txtNomeEmpresa);
        txtNomeEmpresa.setBounds(360, 160, 330, 30);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel5.setText("Nome da Empresa");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(360, 140, 180, 16);
        getContentPane().add(txtEndereco);
        txtEndereco.setBounds(360, 220, 250, 30);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel6.setText("Endereço");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(360, 200, 180, 16);

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel7.setText("Bairro");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(360, 260, 140, 16);

        txtCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCidadeKeyReleased(evt);
            }
        });
        getContentPane().add(txtCidade);
        txtCidade.setBounds(610, 280, 210, 30);

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel8.setText("Complemento");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(680, 200, 110, 16);

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel9.setText("Cidade");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(610, 260, 60, 16);

        txtBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBairroKeyReleased(evt);
            }
        });
        getContentPane().add(txtBairro);
        txtBairro.setBounds(360, 280, 140, 30);

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel10.setText("CEP");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(510, 260, 70, 16);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel11.setText("UF");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(830, 260, 60, 16);

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel12.setText("e-mail");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(640, 320, 60, 16);

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtTelefone);
        txtTelefone.setBounds(360, 340, 130, 30);

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtCelular);
        txtCelular.setBounds(500, 340, 130, 30);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel13.setText("Celular");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(500, 320, 60, 16);

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel15.setText("CNPJ");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(700, 140, 60, 16);

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtCnpj);
        txtCnpj.setBounds(700, 160, 190, 30);

        lblExcluir.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        lblExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        lblExcluir.setText("Limpar");
        lblExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(lblExcluir);
        lblExcluir.setBounds(800, 520, 90, 50);

        lblEditar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        lblEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/salvar32x32.png"))); // NOI18N
        lblEditar.setText("Salvar");
        lblEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarMouseClicked(evt);
            }
        });
        getContentPane().add(lblEditar);
        lblEditar.setBounds(710, 520, 80, 50);

        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(640, 340, 250, 30);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel14.setText("Telefone");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(360, 320, 120, 16);

        jpanelImpressao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de Impressão - Recibo")));

        buttonGroup1.add(radioDireto);
        radioDireto.setText("Imprimir Direto");

        buttonGroup1.add(radioVisualizar);
        radioVisualizar.setText("Visualizar Primeiro");
        radioVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelImpressaoLayout = new javax.swing.GroupLayout(jpanelImpressao);
        jpanelImpressao.setLayout(jpanelImpressaoLayout);
        jpanelImpressaoLayout.setHorizontalGroup(
            jpanelImpressaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelImpressaoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jpanelImpressaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioDireto)
                    .addComponent(radioVisualizar))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jpanelImpressaoLayout.setVerticalGroup(
            jpanelImpressaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelImpressaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioDireto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioVisualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jpanelImpressao);
        jpanelImpressao.setBounds(640, 380, 250, 80);

        lblTextocaminhoBanco.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        lblTextocaminhoBanco.setText("Local de Backup:");
        getContentPane().add(lblTextocaminhoBanco);
        lblTextocaminhoBanco.setBounds(360, 470, 210, 16);

        lblCaminho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/bd.png"))); // NOI18N
        lblCaminho.setText("Caminho");
        getContentPane().add(lblCaminho);
        lblCaminho.setBounds(360, 490, 340, 40);

        txtComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtComplementoKeyReleased(evt);
            }
        });
        getContentPane().add(txtComplemento);
        txtComplemento.setBounds(680, 220, 210, 30);

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel16.setText("Número");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(620, 200, 60, 16);

        btnSelecionarArquivo.setText("Selecionar ...");
        btnSelecionarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarArquivoActionPerformed(evt);
            }
        });
        getContentPane().add(btnSelecionarArquivo);
        btnSelecionarArquivo.setBounds(450, 460, 100, 30);

        comboUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE" }));
        comboUf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUfActionPerformed(evt);
            }
        });
        getContentPane().add(comboUf);
        comboUf.setBounds(830, 280, 60, 30);

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtCep);
        txtCep.setBounds(510, 280, 90, 30);

        txtNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("####"))));
        getContentPane().add(txtNumero);
        txtNumero.setBounds(620, 220, 46, 30);

        setSize(new java.awt.Dimension(903, 585));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        // Fecha Janela
        this.dispose();
    }//GEN-LAST:event_lblFecharMouseClicked

    private void btnSelecionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarArquivoActionPerformed
        //Seleciona local do Arquivo

        JFileChooser chooser = new JFileChooser("c:/");
        chooser.setDialogTitle("Selecionar Logo");
        chooser.showOpenDialog(this);

        File file = chooser.getCurrentDirectory();
        String caminho = file.getAbsolutePath();
        txtUrlBackup.setText(caminho);
        lblCaminho.setText(caminho);

    }//GEN-LAST:event_btnSelecionarArquivoActionPerformed

    private void lblExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExcluirMouseClicked
        // Exclui empresa
        DadosEmpresa objEmpresa = dados.selecionaDados();
        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir \n os dados da empresa?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {

            if (dados.excluiEmpresa(objEmpresa)) {
                JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, " Não foi possível excluir os dados desta empresa!");
            }
        } else {
            JOptionPane.showMessageDialog(null, " Exclusão cancelada com sucesso!");

        }
    }//GEN-LAST:event_lblExcluirMouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked


    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // Seleciona logo do sistema
        //Seleciona local do Arquivo

        JFileChooser chooser = new JFileChooser(p.getCAMINHO_FOTO_FUNCIONARIO());
        chooser.setDialogTitle("Selecionar Logo do Sistema");
        chooser.showOpenDialog(this);

        File file = chooser.getSelectedFile();
        // Captura Exceção duranete  a seleção do arquivo de logo no cadastro da
        // Empresa
        try {

            String caminho = file.getAbsolutePath();
            txturlLogo.setText(caminho);
            ImageIcon imageIcon = new ImageIcon(caminho);
            Icon i = new ImageIcon(imageIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH));
            lblLogo.setIcon(i);
        } catch (NullPointerException e) {
            System.out.println("br.com.bar.view.TelaCadastroEmpresa.jLabel2MouseClicked()");
        }

    }//GEN-LAST:event_jLabel2MouseClicked

    private void lblEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarMouseClicked

        // Instancia objeto dados
        d.setId(Integer.parseInt(txtIdEmpresa.getText()));
        d.setNome_empresa(txtNomeEmpresa.getText());
        d.setEndereco(txtEndereco.getText());
        d.setBairro(txtBairro.getText());

        try {

            d.setNumero(Integer.parseInt(txtNumero.getText()));
        } catch (NumberFormatException e) {
            System.out.println("br.com.bar.view.TelaCadastroEmpresa.lblAlterarMouseClicked()" + e);
        }
        d.setCep(txtCep.getText());
        d.setCidade(txtCidade.getText());
        d.setTelefone(txtTelefone.getText());
        d.setCelular(txtCelular.getText());
        d.setEmail(txtEmail.getText());
        d.setCnpj(txtCnpj.getText());
        d.setUrlbackup(txtUrlBackup.getText());
        d.setUf(comboUf.getSelectedItem().toString());
        d.setLogo(txturlLogo.getText());

        System.out.println("nome Empresa" + d.getNome_empresa());
        if ((radioDireto.isSelected())) {
            // 1 - Imprime na Tela 0 - Imprime Direto
            d.setImprimir_na_tela(1); // Direto para impressora
        } else {
            d.setImprimir_na_tela(0); // Na tela
        }

        if ("".equals(txtNomeEmpresa.getText()) || txtNomeEmpresa.getText() == null) {
            JOptionPane.showMessageDialog(null, "Dados inválidos, preencha todos os campos para continuar!");
        } else {
            int op = JOptionPane.showConfirmDialog(null, "Confirma a alteração dos dados?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {

                if (dados.alteraDados(d)) {
                    JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível alterar os dados!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Alteração cancelada com sucesso!");
            }
        }

    }//GEN-LAST:event_lblEditarMouseClicked

    private void radioVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioVisualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioVisualizarActionPerformed

    private void comboUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboUfActionPerformed

    private void txtNomeEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeEmpresaKeyReleased
        // Limita Tamanho do Campo ENDEREÇO
        u.tamanhoMaximo(txtEndereco.getText(), 45);
    }//GEN-LAST:event_txtNomeEmpresaKeyReleased

    private void txtComplementoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplementoKeyReleased
        // Limita Tamanho do Campo COMPLEMENTO
        u.tamanhoMaximo(txtComplemento.getText(), 30);
    }//GEN-LAST:event_txtComplementoKeyReleased

    private void txtBairroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBairroKeyReleased
        // Limita Tamanho do Campo BAIRRO
        u.tamanhoMaximo(txtBairro.getText(), 35);
    }//GEN-LAST:event_txtBairroKeyReleased

    private void txtCidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCidadeKeyReleased
        // Limita Tamanho do Campo CIDADE
        u.tamanhoMaximo(txtCidade.getText(), 45);
    }//GEN-LAST:event_txtCidadeKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // Limita Tamanho do Campo
        u.tamanhoMaximo(txtEmail.getText(), 245);

    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost

    }//GEN-LAST:event_txtEmailFocusLost

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
            java.util.logging.Logger.getLogger(TelaCadastroEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroEmpresa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionarArquivo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboUf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jpanelImpressao;
    private javax.swing.JLabel lblCaminho;
    private javax.swing.JLabel lblEditar;
    private javax.swing.JLabel lblExcluir;
    private javax.swing.JLabel lblFechar;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblTextocaminhoBanco;
    private javax.swing.JRadioButton radioDireto;
    private javax.swing.JRadioButton radioVisualizar;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtIdEmpresa;
    private javax.swing.JTextField txtNomeEmpresa;
    private javax.swing.JFormattedTextField txtNumero;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JFormattedTextField txtUrlBackup;
    private javax.swing.JTextField txturlLogo;
    // End of variables declaration//GEN-END:variables
}
