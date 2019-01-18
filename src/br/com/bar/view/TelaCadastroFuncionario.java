/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.Funcionario;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerFuncionario;
import br.com.br.controler.ControlerParametro;
import java.awt.Image;
import java.io.File;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author elias
 */
public class TelaCadastroFuncionario extends javax.swing.JFrame {

    ControlerParametro parametro = new ControlerParametro();
    ControlerFuncionario funcionario = new ControlerFuncionario();
    Util u = new Util();
    // Inicia Instância de log
    Log l = new Log();
    String nome=null;
    public TelaCadastroFuncionario() {
        initComponents();
        txtCaminho.setVisible(false);
        txtId.setVisible(false);
        txtOperacao.setVisible(false);
        jtableGuias.setVisible(false);
    }

    public void recebeOperador(String operador, String cargo, String operacao) {
        Date data = new Date();
        lblData.setText(u.formataDataBr(data));

        lblCargo.setText(cargo);
        lblOperador.setText(operador);
        txtOperacao.setText(operacao);

        if ("Adicionar".equals(txtOperacao.getText())) {
            lblTiulo.setText("Incluir");
            lblSubTitulo.setText("Funcionário");
            jDateValiadeCNH.setEnabled(false);
            comboBloqueio.setEnabled(false);
            comboSituacao.setEnabled(false);
            jDateDesligamento.setEnabled(false);
            btnRecontrata.setEnabled(false);

        } else if ("Alterar".equals(txtOperacao.getText())) {
            lblTiulo.setText("Alterar");
            lblSubTitulo.setText("Funcionário");
            comboBloqueio.setEnabled(true);
            comboSituacao.setEnabled(true);
            comboBloqueio.setEnabled(false);
            jDateAdmissao.setEnabled(false);
            jDateNascimento.setEnabled(false);
            if (null != jDateDesligamento.getDate()) {

                comboSituacao.setEnabled(false);
                jDateDesligamento.setEnabled(false);
                btnSalvar.setEnabled(false);
            }
            if (null == jDateDesligamento.getDate()) {
                btnRecontrata.setEnabled(false);
            }

        } else {
            lblTiulo.setText("Consultar");
            lblSubTitulo.setText("Funcionário");
            bloqueiaTudo();
        }
        l.setUsuario(operador);

        if ("Adicionar".equals(operacao)) {

        } else {
            //carregaFoto();
            bloqueiaCampos();
        }
        if ("Alterar".equals(txtOperacao.getText()) && "".equals(txtCnh.getText())) {
            txtCnh.setEnabled(true);

        }

        carregaFoto();
    }

    public void recebeFuncionario(Funcionario f) {
        nome=f.getNome();
        txtId.setText(f.getId());
        txtNome.setText(f.getNome());
        txtEndereço.setText(f.getEndereco());
        txtBairro.setText(f.getBairro());
        txtCep.setText(f.getBairro());
        txtCidade.setText(f.getCidade());
        txtCep.setText(f.getCep());
        txtTelefone.setText(f.getTelefone());
        txtEmail.setText(f.getEmail());
        txtLogin.setText(f.getLogin());
        txtSenha.setText(f.getSenha());
        txtComplemento.setText(f.getComplemento());
        txtNumero.setText(f.getNumero());
        txtRg.setText(f.getRg());
        txtCpf.setText(f.getCpf());
        txtCnh.setText(f.getCnh());
        txtTelRecado.setText(f.getTelefone_recado());
        comboCargo.setSelectedItem(f.getCargo());
        txtCaminho.setText(f.getFoto());
        comboUf.setSelectedItem(f.getUf());
        txtHistorico.setText(f.getObservacao());
        System.out.println(f.getDtDesligamento());

        if (null != f.getDtAdmissao()) {
            jDateAdmissao.setDate(u.converteData(f.getDtAdmissao()));
        }
        if (null != f.getDtDesligamento()) {
            jDateDesligamento.setDate(u.converteData(f.getDtDesligamento()));
        }
        if (null != f.getDtvalidadeCNH()) {
            jDateValiadeCNH.setDate(u.converteData(f.getDtvalidadeCNH()));
        }
        if (null != f.getDtNascimento()) {
            jDateNascimento.setDate(u.converteData(f.getDtNascimento()));
        }

        if (f.getStatus().equals("0")) {
            comboSituacao.setSelectedItem("Ativo");
        } else {
            comboSituacao.setSelectedItem("Inativo");
        }

        if (f.getBloqueado().equals("0")) {
            comboBloqueio.setSelectedItem("Desbloqueado");
        } else {
            comboBloqueio.setSelectedItem("Bloqueado");
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEndereço = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        txtCep = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        comboCargo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboSituacao = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        comboBloqueio = new javax.swing.JComboBox<>();
        btnFoto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCnh = new javax.swing.JFormattedTextField();
        lbltemMesa = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtTelRecado = new javax.swing.JFormattedTextField();
        lblTelefone1 = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnHistorico = new javax.swing.JToggleButton();
        btnRecontrata = new javax.swing.JButton();
        txtRg = new javax.swing.JTextField();
        comboUf = new javax.swing.JComboBox<>();
        jDateValiadeCNH = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jDateAdmissao = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jDateNascimento = new com.toedter.calendar.JDateChooser();
        lblTelefone2 = new javax.swing.JLabel();
        jDateDesligamento = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        lblTiulo = new javax.swing.JLabel();
        lblSubTitulo = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCaminho = new javax.swing.JTextField();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblFechar = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        txtOperacao = new javax.swing.JTextField();
        jtableGuias = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtHistorico = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        lblNome.setText("Nome *");
        jPanel1.add(lblNome);
        lblNome.setBounds(10, 8, 130, 20);

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });
        jPanel1.add(txtNome);
        txtNome.setBounds(10, 27, 280, 30);

        txtEndereço.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEndereçoKeyReleased(evt);
            }
        });
        jPanel1.add(txtEndereço);
        txtEndereço.setBounds(10, 80, 470, 30);

        jLabel2.setText("UF");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 160, 60, 20);

        jLabel3.setText("CEP");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(250, 110, 70, 20);

        jLabel4.setText("Complemento");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 110, 100, 20);

        txtBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBairroKeyReleased(evt);
            }
        });
        jPanel1.add(txtBairro);
        txtBairro.setBounds(130, 130, 110, 30);

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(txtCep);
        txtCep.setBounds(250, 130, 90, 30);

        jLabel5.setText("Cidade");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(350, 110, 90, 20);

        txtCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCidadeKeyReleased(evt);
            }
        });
        jPanel1.add(txtCidade);
        txtCidade.setBounds(350, 130, 200, 30);

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        jPanel1.add(txtEmail);
        txtEmail.setBounds(100, 180, 230, 30);

        jLabel6.setText("E-mail");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(100, 160, 100, 20);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(570, 10, 140, 150);

        lblTelefone.setText("Data de Nascimento");
        jPanel1.add(lblTelefone);
        lblTelefone.setBounds(290, 260, 130, 20);

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefoneKeyReleased(evt);
            }
        });
        jPanel1.add(txtTelefone);
        txtTelefone.setBounds(10, 280, 130, 30);

        comboCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "Garçom", "Caixa", "Gerente", "Cozinheiro", "Estoquista" }));
        jPanel1.add(comboCargo);
        comboCargo.setBounds(10, 340, 130, 30);

        jLabel9.setText("Cargo");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(10, 320, 110, 14);

        jLabel10.setText("Login");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(340, 160, 90, 20);

        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoginKeyReleased(evt);
            }
        });
        jPanel1.add(txtLogin);
        txtLogin.setBounds(340, 180, 100, 30);

        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSenhaKeyReleased(evt);
            }
        });
        jPanel1.add(txtSenha);
        txtSenha.setBounds(450, 180, 100, 30);

        jLabel11.setText("Senha");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(450, 160, 90, 20);

        comboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        comboSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSituacaoActionPerformed(evt);
            }
        });
        jPanel1.add(comboSituacao);
        comboSituacao.setBounds(146, 340, 100, 30);

        jLabel12.setText("Situação");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(150, 320, 70, 14);

        jLabel13.setText("Bloqueio");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(250, 320, 120, 14);

        comboBloqueio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desbloqueado", "Bloqueado" }));
        jPanel1.add(comboBloqueio);
        comboBloqueio.setBounds(250, 340, 110, 30);

        btnFoto.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 24)); // NOI18N
        btnFoto.setForeground(new java.awt.Color(52, 73, 94));
        btnFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/camera.png"))); // NOI18N
        btnFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFotoMouseClicked(evt);
            }
        });
        jPanel1.add(btnFoto);
        btnFoto.setBounds(610, 160, 70, 50);

        jLabel1.setText("CPF");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(150, 210, 70, 20);

        jLabel7.setText("RG");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(10, 210, 50, 20);

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCpfFocusLost(evt);
            }
        });
        txtCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCpfKeyReleased(evt);
            }
        });
        jPanel1.add(txtCpf);
        txtCpf.setBounds(150, 230, 130, 30);

        jLabel8.setText("Data de Desligamento");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(560, 260, 140, 20);

        txtCnh.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
        txtCnh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCnhKeyReleased(evt);
            }
        });
        jPanel1.add(txtCnh);
        txtCnh.setBounds(290, 230, 130, 30);

        lbltemMesa.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        jPanel1.add(lbltemMesa);
        lbltemMesa.setBounds(0, 370, 340, 20);

        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });
        jPanel1.add(txtNumero);
        txtNumero.setBounds(490, 80, 60, 30);

        jLabel22.setText("Endereço");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(10, 60, 90, 14);

        jLabel23.setText("Número");
        jPanel1.add(jLabel23);
        jLabel23.setBounds(490, 60, 60, 14);

        try {
            txtTelRecado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(txtTelRecado);
        txtTelRecado.setBounds(150, 280, 130, 30);

        lblTelefone1.setText("Telefone");
        jPanel1.add(lblTelefone1);
        lblTelefone1.setBounds(10, 260, 90, 20);

        txtComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtComplementoKeyReleased(evt);
            }
        });
        jPanel1.add(txtComplemento);
        txtComplemento.setBounds(10, 130, 110, 30);

        jLabel24.setText("Bairro");
        jPanel1.add(jLabel24);
        jLabel24.setBounds(130, 110, 100, 20);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/salvar24x24.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalvarMouseClicked(evt);
            }
        });
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        btnSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnSalvarKeyReleased(evt);
            }
        });
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(362, 339, 91, 32);

        btnHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/consultar.png"))); // NOI18N
        btnHistorico.setText("Histórico");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });
        jPanel1.add(btnHistorico);
        btnHistorico.setBounds(456, 339, 120, 32);

        btnRecontrata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/atualizar2.png"))); // NOI18N
        btnRecontrata.setText("Recontratar");
        btnRecontrata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecontrataActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecontrata);
        btnRecontrata.setBounds(580, 339, 130, 32);

        txtRg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRgKeyReleased(evt);
            }
        });
        jPanel1.add(txtRg);
        txtRg.setBounds(10, 230, 130, 30);

        comboUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE" }));
        comboUf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUfActionPerformed(evt);
            }
        });
        jPanel1.add(comboUf);
        comboUf.setBounds(10, 180, 80, 30);
        jPanel1.add(jDateValiadeCNH);
        jDateValiadeCNH.setBounds(430, 230, 120, 30);

        jLabel14.setText("CNH");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(290, 210, 80, 20);
        jPanel1.add(jDateAdmissao);
        jDateAdmissao.setBounds(430, 280, 120, 30);

        jLabel16.setText("Validade CNH");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(430, 210, 140, 20);

        jLabel18.setText("Data de Admissão");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(430, 260, 110, 20);
        jPanel1.add(jDateNascimento);
        jDateNascimento.setBounds(290, 280, 130, 30);

        lblTelefone2.setText("Celular");
        jPanel1.add(lblTelefone2);
        lblTelefone2.setBounds(150, 260, 90, 20);

        jDateDesligamento.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jDateDesligamentoInputMethodTextChanged(evt);
            }
        });
        jPanel1.add(jDateDesligamento);
        jDateDesligamento.setBounds(560, 280, 130, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 100, 720, 390);

        jPanel4.setBackground(new java.awt.Color(243, 156, 18));
        jPanel4.setLayout(null);

        lblTiulo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 48)); // NOI18N
        lblTiulo.setText("Cadastro");
        jPanel4.add(lblTiulo);
        lblTiulo.setBounds(30, 0, 270, 78);

        lblSubTitulo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 24)); // NOI18N
        lblSubTitulo.setText("de Funcionários");
        jPanel4.add(lblSubTitulo);
        lblSubTitulo.setBounds(130, 50, 200, 40);
        jPanel4.add(txtId);
        txtId.setBounds(380, 10, 60, 30);
        jPanel4.add(txtCaminho);
        txtCaminho.setBounds(450, 10, 100, 30);

        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("Operador");
        jPanel4.add(lblOperador);
        lblOperador.setBounds(420, 50, 114, 30);

        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("Operador");
        jPanel4.add(lblCargo);
        lblCargo.setBounds(510, 50, 100, 30);

        jPanel5.setBackground(new java.awt.Color(52, 73, 94));

        lblFechar.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        lblFechar.setForeground(new java.awt.Color(255, 255, 255));
        lblFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechar.setText("x");
        lblFechar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblFechar))
        );

        jPanel4.add(jPanel5);
        jPanel5.setBounds(680, 0, 40, 40);

        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/calendario24x24.png"))); // NOI18N
        lblData.setText("Data");
        jPanel4.add(lblData);
        lblData.setBounds(610, 50, 110, 30);
        jPanel4.add(txtOperacao);
        txtOperacao.setBounds(560, 10, 100, 30);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 730, 100);

        jtableGuias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableGuiasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtableGuiasMouseEntered(evt);
            }
        });

        txtHistorico.setColumns(20);
        txtHistorico.setRows(5);
        jScrollPane3.setViewportView(txtHistorico);

        jtableGuias.addTab("Histórico", jScrollPane3);

        getContentPane().add(jtableGuias);
        jtableGuias.setBounds(0, 490, 720, 220);

        setSize(new java.awt.Dimension(720, 716));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        if ("Alterar".equals(txtOperacao.getText())||"Detalhe".equals(txtOperacao.getText())||"Adicionar".equals(txtOperacao.getText())){
            this.dispose();
            TelaPesquisaFuncionario tpf = new TelaPesquisaFuncionario();
            tpf.recebeOperador(lblOperador.getText(), lblCargo.getText());
            tpf.atualizaTabela("");
            tpf.setVisible(true);
            
        }else{             
            this.dispose();
            TelaPesquisaFuncionario tpf = new TelaPesquisaFuncionario();
            tpf.recebeOperador(lblOperador.getText(), lblCargo.getText());
            tpf.setVisible(true);
        }
    }//GEN-LAST:event_lblFecharMouseClicked

    private void btnFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFotoMouseClicked
        if (btnFoto.isEnabled()) {

            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Fotos", "*.jpg,*.png");
            // Instancia selecionador de arquivos
            JFileChooser selecionaArquivo = new JFileChooser(parametro.getCAMINHO_FOTO_FUNCIONARIO());
            selecionaArquivo.addChoosableFileFilter(extensionFilter);
            selecionaArquivo.setDialogTitle("Selecionar Foto");

            if (selecionaArquivo.APPROVE_OPTION == selecionaArquivo.showOpenDialog(this)) {
                // Recebe arquivos captura seu caminho
                File file = selecionaArquivo.getSelectedFile();
                String caminho = file.getAbsolutePath();
                txtCaminho.setText(caminho);
                // adiciona imagem ao label foto
                ImageIcon imageIcon = new ImageIcon(caminho);
                Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH));
                lblFoto.setIcon(icon);
            }
        }

    }//GEN-LAST:event_btnFotoMouseClicked

    private void comboSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSituacaoActionPerformed
        // Seleciona bloqueio
        if ("Inativo".equals(comboSituacao.getSelectedItem().toString())) {
            comboBloqueio.setSelectedItem("Bloqueado");
            comboBloqueio.setEnabled(false);
            /*
           if (null==jDateDesligamento.getDate()){
               //Data Atual
               Date dt = new Date();
               jDateDesligamento.setDate(dt);
           } 
             */
        } else {
            comboBloqueio.setSelectedItem("Desbloqueado");
            jDateDesligamento.setDate(null);
        }
    }//GEN-LAST:event_comboSituacaoActionPerformed

    private void jtableGuiasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableGuiasMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jtableGuiasMouseEntered

    private void jtableGuiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableGuiasMouseClicked

    }//GEN-LAST:event_jtableGuiasMouseClicked

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed
        // Ativa ou desativa o histórico
        if (btnHistorico.isSelected()) {
            btnHistorico.setText("Ocultar");
            jtableGuias.setVisible(true);

        } else {
            btnHistorico.setText("Histórico");
            jtableGuias.setVisible(false);
        }
    }//GEN-LAST:event_btnHistoricoActionPerformed

    private void btnSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if ("Adicionar".equals(txtOperacao.getText())) {
            // Adiciona um novo funcionário
            Funcionario f = new Funcionario();

            f.setFoto(txtCaminho.getText());
            f.setNome(txtNome.getText());
            f.setEndereco(txtEndereço.getText());
            f.setBairro(txtBairro.getText());
            f.setCep(txtCep.getText());
            f.setCidade(txtCidade.getText());
            f.setEmail(txtEmail.getText());
            f.setLogin(txtLogin.getText());
            f.setSenha(txtSenha.getText());
            f.setTelefone(txtTelefone.getText());
            f.setObservacao(txtHistorico.getText());
            f.setCargo(comboCargo.getSelectedItem().toString());
            f.setNumero(txtNumero.getText());
            f.setComplemento(txtComplemento.getText());
            f.setUf(comboUf.getSelectedItem().toString());
            f.setTelefone_recado(txtTelRecado.getText());

            if (null != jDateAdmissao.getDate()) {
                f.setDtAdmissao(u.formataDataBanco(jDateAdmissao.getDate()));
            }
            if (null != jDateValiadeCNH.getDate()) {
                f.setDtvalidadeCNH(u.formataDataBanco(jDateValiadeCNH.getDate()));
            }
            if (null != jDateNascimento.getDate()) {
                f.setDtNascimento(u.formataDataBanco(jDateNascimento.getDate()));
            }

            String status = comboSituacao.getSelectedItem().toString();
            if (status.equals("Ativo")) {
                //Ativo
                f.setStatus("0");
            } else {
                //Inativo
                f.setStatus("1");
            }
            String bloqueio = comboBloqueio.getSelectedItem().toString();
            if (bloqueio.equals("Desbloqueado")) {

                f.setBloqueado("0");
            } else {
                f.setBloqueado("1");

            }

            f.setCpf(txtCpf.getText());
            f.setCnh(txtCnh.getText());
            f.setRg(txtRg.getText());

            if (valida()) {
                int op = JOptionPane.showConfirmDialog(null, "Confirma a inclusão do funcionário?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                if (op == JOptionPane.YES_OPTION) {

                    if (funcionario.temFuncionario(f.getNome())) {
                        JOptionPane.showMessageDialog(null, "Este funcionário já existe!");
                    } else {
                        if (funcionario.adicionaFuncionario(f)) {
                            limpaForm();
                            JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!");

                            // Início do registro de log
                            l.setFuncionalidade("Salvar");
                            l.setDescricao(l.getUsuario() + " adicionou ->" + f.getNome() + " ao cadastro de funcionários");
                            l.gravaLog(l);
                            //Fim do registro de log
                        }
                    }
                }
            }

        } else {
            // Altera dados dos funcionários

            Funcionario f = new Funcionario();
            f.setId(txtId.getText());
            f.setEndereco(txtEndereço.getText());
            f.setBairro(txtBairro.getText());
            f.setCep(txtCep.getText());
            f.setCidade(txtCidade.getText());
            f.setEmail(txtEmail.getText());
            f.setSenha(txtSenha.getText());
            f.setTelefone(txtTelefone.getText());
            f.setCargo(comboCargo.getSelectedItem().toString());
            f.setFoto(txtCaminho.getText());
            f.setObservacao(txtHistorico.getText());
            f.setCargo(comboCargo.getSelectedItem().toString());
            f.setNumero(txtNumero.getText());
            f.setComplemento(txtComplemento.getText());
            f.setUf(comboUf.getSelectedItem().toString());
            f.setTelefone_recado(txtTelRecado.getText());
            f.setCnh(txtCnh.getText());

            if (null != jDateDesligamento.getDate()) {
                f.setDtDesligamento(u.formataDataBanco(jDateDesligamento.getDate()));
            }
            if (null != jDateValiadeCNH.getDate()) {
                f.setDtvalidadeCNH(u.formataDataBanco(jDateValiadeCNH.getDate()));
            }
            if (null != jDateAdmissao.getDate()) {
                f.setDtAdmissao(u.formataDataBanco(jDateAdmissao.getDate()));
            }

            // Captura a situação
            String situacao = comboSituacao.getSelectedItem().toString();

            if (situacao.equals("Ativo")) {

                f.setStatus("0");
            } else {
                f.setStatus("1");
            }

            // Recebe o tipo de bloqueio
            String bloqueio = comboBloqueio.getSelectedItem().toString();

            if (bloqueio.equals("Bloqueado")) {

                f.setBloqueado("1");
            } else {
                f.setBloqueado("0");
            }
            //Instancia o controle do funcionário

            ControlerFuncionario cf = new ControlerFuncionario();

            // Verifica se o funcionário está bloqueado ou Inativo
            if (f.getStatus().equals("1") && f.getBloqueado().equals("1")) {
                // Verifica se o funcionário possui mesa cadastrada sob sua responsabilidade
                if (cf.temMesa(f)) {
                    // Se o funcionário possuir mesa, exibe mensagem e solicita transferida para outro 
                    JOptionPane.showMessageDialog(null, "Transfira as mesas do funcionário antes de continuar!");
                } else {
                    if (valida()) {

                        int op = JOptionPane.showConfirmDialog(null, "Confirma a alteração dos Dados?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                        if (op == JOptionPane.YES_OPTION) {

                            // Altera dados do funcionário
                            if (cf.alterar(f, txtId.getText())) {
                                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
                                this.dispose();

                                TelaPesquisaFuncionario tpf = new TelaPesquisaFuncionario();
                                tpf.atualizaTabela(f.getNome());
                                tpf.recebeOperador(lblOperador.getText(), lblCargo.getText());
                                tpf.atualizaTabela("");
                                tpf.setVisible(true);
                                // Início do registro de log
                                l.setFuncionalidade("Alterar");
                                l.setDescricao(l.getUsuario() + " alterou o registro do funcionário ->" + f.getNome());
                                l.gravaLog(l);

                                //Fim do registro de log
                            } else {
                                JOptionPane.showMessageDialog(null, "Erro ao tentar alterar os dados - contate o SUPORTE!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Alteração Candelada com sucesso!");
                        }
                    }
                    /*
                    else {
                        JOptionPane.showMessageDialog(null, "Não foi possível alterar os dados do funcionário!");
                    }
                     */
                }
            } else {
                int op = JOptionPane.showConfirmDialog(null, "Confirma a alteração dos Dados?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    if (valida()) {

                        if (cf.alterar(f, txtId.getText())) {

                            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
                            this.dispose();
                            TelaPesquisaFuncionario tpf = new TelaPesquisaFuncionario();
                            //tpf.atualizaTabela(f.getNome());
                            tpf.recebeOperador(lblOperador.getText(), lblCargo.getText());
                            tpf.atualizaTabela("");
                            tpf.setVisible(true);
                            // Início do registro de log
                            l.setFuncionalidade("Alterar");
                            l.setDescricao(l.getUsuario() + " alterou o registro do funcionário ->" + f.getNome());
                            l.gravaLog(l);
                            //Fim do registro de log
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao tentar alterar os dados - contate o SUPORTE!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Alteração Candelada com sucesso!");
                    }
                }
            }

        }


    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSalvarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalvarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarKeyReleased

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        // Aceita apenas número
        txtNumero.setText(txtNumero.getText().replaceAll("[^0-9]", ""));
        txtNumero.setText(u.tamanhoMaximo(txtNumero.getText(), 4));
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void comboUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUfActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboUfActionPerformed

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        // Limita tanho do campo NOME
        txtNome.setText(u.tamanhoMaximo(txtNome.getText(), 45));
    }//GEN-LAST:event_txtNomeKeyReleased

    private void txtEndereçoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEndereçoKeyReleased
        // Limita tanho do campo ENDEREÇO
        txtEndereço.setText(u.tamanhoMaximo(txtEndereço.getText(), 45));
    }//GEN-LAST:event_txtEndereçoKeyReleased

    private void txtRgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRgKeyReleased
        // Limita Tamanho do campo RG
        txtRg.setText(u.tamanhoMaximo(txtRg.getText(), 13));


    }//GEN-LAST:event_txtRgKeyReleased

    private void txtCpfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyReleased
        // Limita tamanho do campo CPF
        txtCpf.setText(u.tamanhoMaximo(txtCpf.getText(), 14));
    }//GEN-LAST:event_txtCpfKeyReleased

    private void txtCnhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCnhKeyReleased
        // Limita tamanho do campo CNH
        //txtCnh.setText(txtCnh.getText().replaceAll("[^0-9]", ""));
        txtCnh.setText(u.tamanhoMaximo(txtCnh.getText(), 11));
        try {

            if (Integer.parseInt(txtCnh.getText()) > 0) {
                jDateValiadeCNH.setEnabled(true);
            } else {
                jDateValiadeCNH.setEnabled(false);
            }
        } catch (NumberFormatException e) {

        }
    }//GEN-LAST:event_txtCnhKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // Limita tamnho do campo EMAIL
        txtEmail.setText(u.tamanhoMaximo(txtEmail.getText(), 45));
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyReleased
        // Limita tamanho do campo LOGIN
        txtLogin.setText(u.tamanhoMaximo(txtLogin.getText(), 10));
    }//GEN-LAST:event_txtLoginKeyReleased

    private void txtSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyReleased
        // TODO add your handling code here:
        txtSenha.setText(u.tamanhoMaximo(txtSenha.getText(), 10));
    }//GEN-LAST:event_txtSenhaKeyReleased

    private void txtTelefoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefoneKeyReleased
        // limita tamanho do campo TELEFONE

    }//GEN-LAST:event_txtTelefoneKeyReleased

    private void txtBairroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBairroKeyReleased
        // Limita tamanho do campo BAIRRO
        txtBairro.setText(u.tamanhoMaximo(txtBairro.getText(), 35));
    }//GEN-LAST:event_txtBairroKeyReleased

    private void txtComplementoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplementoKeyReleased
        // Limita tamanho do cmapo COMPLEMENTO
        txtComplemento.setText(u.tamanhoMaximo(txtComplemento.getText(), 35));

    }//GEN-LAST:event_txtComplementoKeyReleased

    private void txtCidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCidadeKeyReleased
        // Limita tamanho do compo CIDADE
        txtCidade.setText(u.tamanhoMaximo(txtCidade.getText(), 40));
    }//GEN-LAST:event_txtCidadeKeyReleased

    private void jDateDesligamentoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jDateDesligamentoInputMethodTextChanged
        // TODO add your handling code here:
        if (null != jDateDesligamento.getDate()) {

        } else {
            comboSituacao.setSelectedItem("Inativo");
        }
    }//GEN-LAST:event_jDateDesligamentoInputMethodTextChanged

    private void btnRecontrataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecontrataActionPerformed
        // 
        jDateAdmissao.setEnabled(true);
        jDateDesligamento.setDate(null);
        jDateAdmissao.setDate(null);
        btnSalvar.setEnabled(true);
        jDateDesligamento.setEnabled(true);
        comboSituacao.setSelectedItem("Ativo");
        comboSituacao.setEnabled(true);

    }//GEN-LAST:event_btnRecontrataActionPerformed

    private void txtCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusLost
        // Valida o cpf Informado
        if (Util.isCPF(txtCpf.getText())) {

        } else {
            JOptionPane.showMessageDialog(null, "Digite um CPF válido!");
            txtCpf.requestFocus();
        }
    }//GEN-LAST:event_txtCpfFocusLost

    public void limpaForm() {
        // Limpa formulário
        txtId.setText(null);
        txtNome.setText(null);
        txtEndereço.setText(null);
        txtBairro.setText(null);
        txtCep.setText(null);
        txtEmail.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
        txtTelefone.setText(null);
        txtCidade.setText(null);
        txtComplemento.setText(null);
        txtNumero.setText(null);
        comboUf.setSelectedItem("Selecione...");
        comboCargo.setSelectedItem("Selecione...");
        comboBloqueio.setSelectedItem("Desbloqueado");
        comboCargo.setSelectedItem("Cargos");
        comboSituacao.setSelectedItem("Ativo");
        txtCaminho.setText(null);
        lblFoto.setIcon(null);
        txtRg.setText(null);
        txtCpf.setText(null);
        txtCnh.setText(null);
        txtTelRecado.setText(null);
        jDateAdmissao.setDate(null);
        jDateDesligamento.setDate(null);
        jDateNascimento.setDate(null);
        jDateValiadeCNH.setDate(null);
    }

    private void bloqueiaTudo() {
        // Limpa formulário
        txtId.setEnabled(false);
        txtNome.setEnabled(false);
        txtEndereço.setEnabled(false);
        txtBairro.setEnabled(false);
        txtCep.setEnabled(false);
        txtEmail.setEnabled(false);
        txtLogin.setEnabled(false);
        txtSenha.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtCidade.setEnabled(false);
        txtComplemento.setText(null);
        txtNumero.setEnabled(false);
        txtCaminho.setEnabled(false);
        txtRg.setEnabled(false);
        txtCpf.setEnabled(false);
        txtCnh.setEnabled(false);
        txtTelRecado.setEnabled(false);
        comboUf.setEnabled(false);
        comboCargo.setEnabled(false);
        comboBloqueio.setEnabled(false);
        comboCargo.setEnabled(false);
        comboSituacao.setEnabled(false);
        jDateAdmissao.setEnabled(false);
        jDateDesligamento.setEnabled(false);
        jDateNascimento.setEnabled(false);
        jDateValiadeCNH.setEnabled(false);
        txtComplemento.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnHistorico.setEnabled(false);
        btnRecontrata.setEnabled(false);
        btnFoto.setEnabled(false);
        jtableGuias.setVisible(true);
        txtHistorico.setEnabled(false);

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnFoto;
    private javax.swing.JToggleButton btnHistorico;
    private javax.swing.JButton btnRecontrata;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> comboBloqueio;
    private javax.swing.JComboBox<String> comboCargo;
    private javax.swing.JComboBox<String> comboSituacao;
    private javax.swing.JComboBox<String> comboUf;
    private com.toedter.calendar.JDateChooser jDateAdmissao;
    private com.toedter.calendar.JDateChooser jDateDesligamento;
    private com.toedter.calendar.JDateChooser jDateNascimento;
    private com.toedter.calendar.JDateChooser jDateValiadeCNH;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jtableGuias;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblFechar;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblSubTitulo;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTelefone1;
    private javax.swing.JLabel lblTelefone2;
    private javax.swing.JLabel lblTiulo;
    private javax.swing.JLabel lbltemMesa;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCaminho;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCnh;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereço;
    private javax.swing.JTextArea txtHistorico;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtOperacao;
    private javax.swing.JTextField txtRg;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JFormattedTextField txtTelRecado;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables

    private void carregaFoto() {
        if ("".equals(txtCaminho.getText())) {

        } else {

            ImageIcon imageIcon = new ImageIcon(txtCaminho.getText());
            Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(136, 146, Image.SCALE_SMOOTH));
            lblFoto.setIcon(icon);
        }
    }

    private void bloqueiaCampos() {
        if ("Alterar".equals(txtOperacao.getText())) {
            txtNome.setEnabled(false);
            txtLogin.setEnabled(false);
            txtRg.setEnabled(false);
            txtCpf.setEnabled(false);
            txtCnh.setEnabled(false);
        }

    }

    /* 
       Este método valida os campos do cadastro de Funcioinário retornando um Boolean
       como resposta final.
     */
    public boolean valida() {
        // Seta  resposta padrão com TRUE.
        boolean resp = true;

        if ("".equals(txtNome.getText())) {
            JOptionPane.showMessageDialog(null, "Informe um Nome para continuar!");
            resp = false;
        } else if ("".equals(txtEndereço.getText())) {
            JOptionPane.showMessageDialog(null, "Informe um Endereço para continuar!");
            resp = false;
        } else if ("".equals(txtNumero.getText())) {
            JOptionPane.showMessageDialog(null, "Informe o Número para continuar!");
            resp = false;
        } else if ("".equals(txtBairro.getText())) {
            JOptionPane.showMessageDialog(null, "Informe um Bairro para continuar!");
            resp = false;
        } else if ("     -   ".equals(txtCep.getText()) || "00000-000".equals(txtCep.getText())) {
            JOptionPane.showMessageDialog(null, "Informe um CEP para continuar!");
            resp = false;
        } else if ("".equals(txtCidade.getText())) {
            JOptionPane.showMessageDialog(null, "Informe uma Cidade para continuar!");
            resp = false;
        } else if ("Selecione...".equals(comboUf.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Informe uma UF para continuar!");
            resp = false;
        } else if ("".equals(txtLogin.getText())) {
            JOptionPane.showMessageDialog(null, "Informe um Login para continuar!");
            resp = false;
        } else if ("".equals(txtSenha.getText())) {
            JOptionPane.showMessageDialog(null, "Informe uma Senha para continuar!");
            resp = false;
        } else if ("".equals(txtRg.getText()) || "0000000000000".equals(txtRg.getText())) {
            JOptionPane.showMessageDialog(null, "Informe um RG para continuar!");
            resp = false;
        } else if ("   .   .   -  ".equals(txtCpf.getText()) || "000.000.000-00".equals(txtCpf.getText())) {
            JOptionPane.showMessageDialog(null, "Informe um CPF para continuar!");
            resp = false;
            /*
           - Caso CNH não seja preenchido segue com a verificação dos demais componentes do formulário
           - Se CNH seja preenchido, questiona o preenchimento da validade da CNH e segue com a validação
             dos demais componentes.
             */

        } else if (!txtCnh.getText().isEmpty()) { // Se CNH estiver preenchida questiona a validade.
            if ("0".equals(txtCnh.getText())) {
                JOptionPane.showMessageDialog(null, "Informe uma CNH válida para continuar!");
                resp = false;
            } else if (null == jDateValiadeCNH.getDate()) { // Se a data não for informada avisa o usuário.
                JOptionPane.showMessageDialog(null, "Informe uma Data de Validade para continuar!");
                resp = false;
            } else {  // Segue com a validação dos demais campos                            
                if ("(  )     -    ".equals(txtTelRecado.getText()) || "(00)00000-0000".equals(txtTelRecado.getText())) {
                    JOptionPane.showMessageDialog(null, "Informe um número de Celular para continuar!");
                    resp = false;
                } else if (null == jDateAdmissao.getDate()) {
                    JOptionPane.showMessageDialog(null, "Informe a Data de Admissão para continuar!");
                    resp = false;
                } else if (null != jDateDesligamento.getDate()) {
                    Date dtAtual = new Date();
                    Date dtDeslig = jDateDesligamento.getDate();
                    if (dtDeslig.after(dtAtual)) {
                        JOptionPane.showMessageDialog(null, "A Data de Desligamento não pode ser maior que a data atual!");
                        resp = false;
                    }

                } else if ("Inativo".equals(comboSituacao.getSelectedItem().toString()) && null == jDateDesligamento.getDate()) {
                    JOptionPane.showMessageDialog(null, "Informe a Data de Desligamento para continuar!");
                    resp = false;
                } else if ("Selecione...".equals(comboCargo.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(null, "Informe um Cargo para continuar!");
                    resp = false;
                }
            }
        } else { // Comtinua validação se CNH e Validade estiverem preechidos corretamente.

            if ("(  )     -    ".equals(txtTelRecado.getText()) || "(00)00000-0000".equals(txtTelRecado.getText())) {
                JOptionPane.showMessageDialog(null, "Informe um número de Celular para continuar!");
                resp = false;
            } else if (null == jDateAdmissao.getDate()) {
                JOptionPane.showMessageDialog(null, "Informe a Data de Admissão para continuar!");
                resp = false;
            } else if (null != jDateDesligamento.getDate()) {
                Date dtAtual = new Date();
                Date dtDeslig = jDateDesligamento.getDate();
                if (dtDeslig.after(dtAtual)) {
                    JOptionPane.showMessageDialog(null, "A Data de Desligamento não pode ser maior que a data atual!");
                    resp = false;
                }
            } else if ("Inativo".equals(comboSituacao.getSelectedItem().toString()) && null == jDateDesligamento.getDate()) {
                JOptionPane.showMessageDialog(null, "Informe a Data de Desligamento para continuar!");
                resp = false;
            } else if ("Selecione...".equals(comboCargo.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(null, "Informe um Cargo para continuar!");
                resp = false;
            }

        }
        return resp;
    }
}
