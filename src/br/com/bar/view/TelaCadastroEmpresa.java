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
        // Instancia o objeto e executa o método retornando um objetodo tipo
        // Dados empresa
        radioVisualizar.setSelected(true);
        txtUrlBackup.setVisible(false);

        d = dados.selecionaDados();

        txtNomeEmpresa.setText(d.getNome_empresa());
        txtEndereco.setText(d.getEndereco());
        txtNumero.setText(String.valueOf(d.getNumero()));
        txtBairro.setText(d.getBairro());
        txtCep.setText(d.getCep());
        txtCidade.setText(d.getCidade());
        txtUF.setText(d.getUf());
        txtTelefone.setText(d.getTelefone());
        txtCelular.setText(d.getCelular());
        txtEmail.setText(d.getEmail());
        txtCnpj.setText(d.getCnpj());
        txtUrlBackup.setText(d.getUrlbackup());
        lblCaminho.setText(txtUrlBackup.getText());

        d = dados.selecionaDados();

        lblLogo.setIcon(u.carregaLogo());
    }

    // Recebe dados para identificação do operador
    public void recebeOperador(String operador, String pefil, String dataAtual) {

        lblOperador.setText(operador);
        lblPerfil.setText(pefil);
        lblData.setText(dataAtual);

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
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblFechar = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNomeEmpresa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        txtBairro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JFormattedTextField();
        txtUrlBackup = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        btnSelecionarArquivo = new javax.swing.JButton();
        txtNumero = new javax.swing.JFormattedTextField();
        lblExcluir = new javax.swing.JLabel();
        lblAdicionar = new javax.swing.JLabel();
        lblEditar = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtUF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        lblCaminho = new javax.swing.JLabel();
        jpanelImpressao = new javax.swing.JPanel();
        radioDireto = new javax.swing.JRadioButton();
        radioVisualizar = new javax.swing.JRadioButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(38, 53, 61));
        jPanel1.setForeground(new java.awt.Color(52, 73, 94));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/logo.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dados Empresa");

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Adicionar Logo");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel2)))
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 344, 540);

        jPanel2.setBackground(new java.awt.Color(38, 53, 61));

        lblFechar.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        lblFechar.setForeground(new java.awt.Color(255, 255, 255));
        lblFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechar.setText("X");
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
            .addComponent(lblFechar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(857, 0, 46, 43);

        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("operador");
        getContentPane().add(lblOperador);
        lblOperador.setBounds(420, 40, 99, 32);

        lblPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblPerfil.setText("Perfil");
        getContentPane().add(lblPerfil);
        lblPerfil.setBounds(530, 40, 110, 32);

        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblData.setText("Data");
        getContentPane().add(lblData);
        lblData.setBounds(660, 40, 110, 32);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 36)); // NOI18N
        jLabel3.setText("Cadastro Empresa");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(360, 80, 310, 50);
        getContentPane().add(txtNomeEmpresa);
        txtNomeEmpresa.setBounds(360, 220, 227, 30);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel5.setText("Nome da Empresa");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(360, 200, 180, 16);
        getContentPane().add(txtEndereco);
        txtEndereco.setBounds(610, 220, 263, 30);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel6.setText("Endereço");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(610, 200, 180, 16);

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel7.setText("Bairro");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(410, 260, 140, 16);
        getContentPane().add(txtCidade);
        txtCidade.setBounds(740, 280, 120, 30);

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel8.setText("Número");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(360, 260, 60, 16);

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel9.setText("Cidade");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(740, 260, 60, 16);
        getContentPane().add(txtCep);
        txtCep.setBounds(600, 280, 130, 30);
        getContentPane().add(txtBairro);
        txtBairro.setBounds(410, 280, 180, 30);

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel10.setText("CEP");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(600, 260, 70, 16);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel11.setText("UF");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(360, 380, 120, 16);

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
        jLabel15.setBounds(430, 380, 60, 16);

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtCnpj);
        txtCnpj.setBounds(430, 400, 210, 30);

        txtUrlBackup.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        getContentPane().add(txtUrlBackup);
        txtUrlBackup.setBounds(360, 490, 210, 30);

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel16.setText("Caminho Backup do Bando de Dados");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(650, 370, 210, 16);

        btnSelecionarArquivo.setText("Salvar Brackup em ...");
        btnSelecionarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarArquivoActionPerformed(evt);
            }
        });
        getContentPane().add(btnSelecionarArquivo);
        btnSelecionarArquivo.setBounds(360, 440, 210, 40);

        txtNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        getContentPane().add(txtNumero);
        txtNumero.setBounds(360, 280, 46, 30);

        lblExcluir.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        lblExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Lixeira.png"))); // NOI18N
        lblExcluir.setText("Excluir");
        lblExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExcluirMouseClicked(evt);
            }
        });
        getContentPane().add(lblExcluir);
        lblExcluir.setBounds(790, 150, 90, 32);

        lblAdicionar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        lblAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/adicionas32x32.png"))); // NOI18N
        lblAdicionar.setText("Adicionar");
        lblAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdicionarMouseClicked(evt);
            }
        });
        getContentPane().add(lblAdicionar);
        lblAdicionar.setBounds(580, 150, 100, 32);

        lblEditar.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        lblEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lapis.png"))); // NOI18N
        lblEditar.setText("Alterar");
        lblEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarMouseClicked(evt);
            }
        });
        getContentPane().add(lblEditar);
        lblEditar.setBounds(690, 150, 90, 32);
        getContentPane().add(txtEmail);
        txtEmail.setBounds(640, 340, 220, 30);
        getContentPane().add(txtUF);
        txtUF.setBounds(360, 400, 60, 30);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel14.setText("Telefone");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(360, 320, 120, 16);

        lblCaminho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/bd.png"))); // NOI18N
        lblCaminho.setText("Caminho");
        getContentPane().add(lblCaminho);
        lblCaminho.setBounds(660, 390, 190, 40);

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
                .addContainerGap(73, Short.MAX_VALUE))
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
        jpanelImpressao.setBounds(650, 450, 210, 80);

        setSize(new java.awt.Dimension(902, 542));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        // Fecha Janela
        this.dispose();
    }//GEN-LAST:event_lblFecharMouseClicked

    private void lblAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdicionarMouseClicked

        // Instancia objeto dados
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
        d.setUf(txtUF.getText());
        String btnSelecionado=buttonGroup1.getSelection().toString();
        
        if ("Imprimir Direto".equals(btnSelecionado)){
            // 1 - Imprime na Tela 0 - Imprime Direto
            d.setImprimir_na_tela(0); // Na tela
        }else {
            d.setImprimir_na_tela(1); // Direto para impressora
        }

        if ("".equals(txtNomeEmpresa.getText()) || txtNomeEmpresa.getText() == null) {
            JOptionPane.showMessageDialog(null, "Dados inválidos, preencha todos os campos para continuar!");
        } else {
            if (dados.adicionaDados(d)) {
                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Impossível salvar dados");

            }
        }


    }//GEN-LAST:event_lblAdicionarMouseClicked

    private void btnSelecionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarArquivoActionPerformed
        //Seleciona local do Arquivo

        JFileChooser chooser = new JFileChooser(p.getCAMINHO_FOTO_FUNCIONARIO());
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

        if (dados.excluiEmpresa(objEmpresa)) {
            JOptionPane.showMessageDialog(null, "Dados Excluídos com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, " Não foi possível excluir os dados!");
        }
    }//GEN-LAST:event_lblExcluirMouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked


    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // Seleciona logo do sistema
        //Seleciona local do Arquivo

        JFileChooser chooser = new JFileChooser(p.getCAMINHO_FOTO_FUNCIONARIO());
        chooser.setDialogTitle("Seleciona caminho do Backup");
        chooser.showOpenDialog(this);

        File file = chooser.getSelectedFile();
        String caminho = file.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(caminho);

        Icon i = new ImageIcon(imageIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH));

        lblLogo.setIcon(i);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void lblEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarMouseClicked
        
         // Instancia objeto dados
        d.setNome_empresa(txtNomeEmpresa.getText());
        d.setEndereco(txtEndereco.getText());
        d.setBairro(txtBairro.getText());
        try {

            d.setNumero(Integer.parseInt(txtNumero.getText()));
        } catch (Exception e) {
            System.out.println("br.com.bar.view.TelaCadastroEmpresa.lblAlterarMouseClicked()" + e);
        }
        d.setCep(txtCep.getText());
        d.setCidade(txtCidade.getText());
        d.setTelefone(txtTelefone.getText());
        d.setCelular(txtCelular.getText());
        d.setEmail(txtEmail.getText());
        d.setCnpj(txtCnpj.getText());
        d.setUrlbackup(txtUrlBackup.getText());
        d.setUf(txtUF.getText());
       String btnSelecionado=buttonGroup1.getSelection().toString();
        System.out.println(btnSelecionado);
        if ((radioDireto.isSelected())){
            // 1 - Imprime na Tela 0 - Imprime Direto
            d.setImprimir_na_tela(1); // Direto para impressora
        }else {
            d.setImprimir_na_tela(0); // Na tela
        }
        
        if ("".equals(txtNomeEmpresa.getText()) || txtNomeEmpresa.getText() == null) {
            JOptionPane.showMessageDialog(null, "Dados inválidos, preencha todos os campos para continuar!");
        } else {
            if (dados.alteraDados(d)) {
                JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Impossível alterar dados");

            }
        }
        
    }//GEN-LAST:event_lblEditarMouseClicked

    private void radioVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioVisualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioVisualizarActionPerformed

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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jpanelImpressao;
    private javax.swing.JLabel lblAdicionar;
    private javax.swing.JLabel lblCaminho;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblEditar;
    private javax.swing.JLabel lblExcluir;
    private javax.swing.JLabel lblFechar;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JRadioButton radioDireto;
    private javax.swing.JRadioButton radioVisualizar;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNomeEmpresa;
    private javax.swing.JFormattedTextField txtNumero;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JTextField txtUF;
    private javax.swing.JFormattedTextField txtUrlBackup;
    // End of variables declaration//GEN-END:variables
}
