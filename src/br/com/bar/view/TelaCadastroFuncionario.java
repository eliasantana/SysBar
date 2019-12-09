/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.Funcionario;
import br.com.bar.util.ClienteViaCepWS;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerFuncionario;
import br.com.br.controler.ControlerParametro;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author elias
 */
/*
    - public class TelaCadastroFuncionario extends javax.swing.JFrame
    - Teste de Alteração de herança da janela de jFrame para JDialog
 */
public class TelaCadastroFuncionario extends JDialog {

    ControlerParametro parametro = new ControlerParametro();
    ControlerFuncionario funcionario = new ControlerFuncionario();
    Util u = new Util();
    // Inicia Instância de log
    Log l = new Log();
    String nome = null;
    Object[] opcao = {"   Sim   ","   Não   "};
    // Formato do componente JDateSchoose
    String formato = "dd/MM/yyyy";
    public TelaCadastroFuncionario() {
        initComponents();
        txtCaminho.setVisible(false);
        txtId.setVisible(false);
        txtOperacao.setVisible(false);
        jtableGuias.setVisible(false);
        //Torna a janela Modal
        this.setModal(true);
        lblPerfil.setVisible(false);
        lblData.setVisible(false);
        lblOperador.setVisible(false);
        jDateAdmissao.setDateFormatString(formato);
        jDateDesligamento.setDateFormatString(formato);
        jDateNascimento.setDateFormatString(formato);
        jDateValiadeCNH.setDateFormatString(formato);
    }

    public void recebeOperador(String operador, String cargo, String operacao) {
        Date data = new Date();
        lblData.setText(u.formataDataBr(data));

        lblPerfil.setText(cargo);
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
            if ("Ativo".equals(comboSituacao.getSelectedItem().toString())) {
                comboBloqueio.setEnabled(true);

            }
            comboSituacao.setEnabled(true);
            //comboBloqueio.setEnabled(false);
            jDateAdmissao.setEnabled(false);
            jDateNascimento.setEnabled(false);
            jDateDesligamento.setEnabled(false);
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
            jDateValiadeCNH.setEnabled(false);
        }

        carregaFoto();
    }

    public void recebeFuncionario(Funcionario f) {
        nome = f.getNome();
        txtId.setText(f.getId());
        txtNome.setText(f.getNome());
        txtEndereco.setText(f.getEndereco());
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
        bordas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        lblUf = new javax.swing.JLabel();
        lblCep = new javax.swing.JLabel();
        lblComplemento = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        txtCep = new javax.swing.JFormattedTextField();
        lblCidade = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        lblDataNascimento = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        comboCargo = new javax.swing.JComboBox<>();
        lblCargo = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        comboSituacao = new javax.swing.JComboBox<>();
        lblSituação = new javax.swing.JLabel();
        lblBloqueio = new javax.swing.JLabel();
        comboBloqueio = new javax.swing.JComboBox<>();
        btnFoto = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        lblRg = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        lblDtaDesligamento = new javax.swing.JLabel();
        txtCnh = new javax.swing.JFormattedTextField();
        lbltemMesa = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblEndereco = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        txtTelRecado = new javax.swing.JFormattedTextField();
        lblTelefone = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnHistorico = new javax.swing.JToggleButton();
        btnRecontrata = new javax.swing.JButton();
        txtRg = new javax.swing.JTextField();
        comboUf = new javax.swing.JComboBox<>();
        jDateValiadeCNH = new com.toedter.calendar.JDateChooser();
        lblCnH = new javax.swing.JLabel();
        jDateAdmissao = new com.toedter.calendar.JDateChooser();
        lblValidade = new javax.swing.JLabel();
        lblDataAdmissao = new javax.swing.JLabel();
        jDateNascimento = new com.toedter.calendar.JDateChooser();
        lblCelular = new javax.swing.JLabel();
        jDateDesligamento = new com.toedter.calendar.JDateChooser();
        lblMsg = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        lblTiulo = new javax.swing.JLabel();
        lblSubTitulo = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCaminho = new javax.swing.JTextField();
        lblOperador = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
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

        bordas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bordas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        lblNome.setText("Nome *");
        jPanel1.add(lblNome);
        lblNome.setBounds(10, 8, 130, 20);

        txtNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeMouseClicked(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });
        jPanel1.add(txtNome);
        txtNome.setBounds(10, 27, 280, 30);

        txtEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnderecoKeyReleased(evt);
            }
        });
        jPanel1.add(txtEndereco);
        txtEndereco.setBounds(10, 80, 470, 30);

        lblUf.setText("UF");
        jPanel1.add(lblUf);
        lblUf.setBounds(10, 160, 60, 20);

        lblCep.setText("CEP");
        jPanel1.add(lblCep);
        lblCep.setBounds(250, 110, 70, 20);

        lblComplemento.setText("Complemento");
        jPanel1.add(lblComplemento);
        lblComplemento.setBounds(10, 110, 100, 20);

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
        txtCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCepKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCepKeyReleased(evt);
            }
        });
        jPanel1.add(txtCep);
        txtCep.setBounds(250, 130, 90, 30);

        lblCidade.setText("Cidade");
        jPanel1.add(lblCidade);
        lblCidade.setBounds(350, 110, 90, 20);

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

        lblEmail.setText("E-mail");
        jPanel1.add(lblEmail);
        lblEmail.setBounds(100, 160, 100, 20);

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

        lblDataNascimento.setText("Data de Nascimento");
        jPanel1.add(lblDataNascimento);
        lblDataNascimento.setBounds(290, 260, 130, 20);

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
        comboCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCargoActionPerformed(evt);
            }
        });
        jPanel1.add(comboCargo);
        comboCargo.setBounds(10, 340, 130, 30);

        lblCargo.setText("Cargo");
        jPanel1.add(lblCargo);
        lblCargo.setBounds(10, 320, 110, 14);

        lblLogin.setText("Login");
        jPanel1.add(lblLogin);
        lblLogin.setBounds(340, 160, 90, 20);

        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoginKeyReleased(evt);
            }
        });
        jPanel1.add(txtLogin);
        txtLogin.setBounds(340, 180, 100, 30);

        lblSenha.setText("Senha");
        jPanel1.add(lblSenha);
        lblSenha.setBounds(450, 160, 90, 20);

        comboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        comboSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSituacaoActionPerformed(evt);
            }
        });
        jPanel1.add(comboSituacao);
        comboSituacao.setBounds(146, 340, 100, 30);

        lblSituação.setText("Situação");
        jPanel1.add(lblSituação);
        lblSituação.setBounds(150, 320, 70, 14);

        lblBloqueio.setText("Bloqueio");
        jPanel1.add(lblBloqueio);
        lblBloqueio.setBounds(250, 320, 120, 14);

        comboBloqueio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desbloqueado", "Bloqueado" }));
        comboBloqueio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBloqueioActionPerformed(evt);
            }
        });
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

        lblCpf.setText("CPF");
        jPanel1.add(lblCpf);
        lblCpf.setBounds(150, 210, 70, 20);

        lblRg.setText("RG");
        jPanel1.add(lblRg);
        lblRg.setBounds(10, 210, 50, 20);

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

        lblDtaDesligamento.setText("Data de Desligamento");
        jPanel1.add(lblDtaDesligamento);
        lblDtaDesligamento.setBounds(560, 260, 140, 20);

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
        lbltemMesa.setBounds(10, 370, 340, 20);

        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });
        jPanel1.add(txtNumero);
        txtNumero.setBounds(490, 80, 60, 30);

        lblEndereco.setText("Endereço");
        jPanel1.add(lblEndereco);
        lblEndereco.setBounds(10, 60, 90, 14);

        lblNumero.setText("Número");
        jPanel1.add(lblNumero);
        lblNumero.setBounds(490, 60, 60, 14);

        try {
            txtTelRecado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelRecado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelRecadoKeyPressed(evt);
            }
        });
        jPanel1.add(txtTelRecado);
        txtTelRecado.setBounds(150, 280, 130, 30);

        lblTelefone.setText("Telefone");
        jPanel1.add(lblTelefone);
        lblTelefone.setBounds(10, 260, 90, 20);

        txtComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtComplementoKeyReleased(evt);
            }
        });
        jPanel1.add(txtComplemento);
        txtComplemento.setBounds(10, 130, 110, 30);

        lblBairro.setText("Bairro");
        jPanel1.add(lblBairro);
        lblBairro.setBounds(130, 110, 100, 20);

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

        jDateValiadeCNH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDateValiadeCNHFocusGained(evt);
            }
        });
        jDateValiadeCNH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateValiadeCNHMouseClicked(evt);
            }
        });
        jDateValiadeCNH.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateValiadeCNHPropertyChange(evt);
            }
        });
        jPanel1.add(jDateValiadeCNH);
        jDateValiadeCNH.setBounds(430, 230, 120, 30);

        lblCnH.setText("CNH");
        jPanel1.add(lblCnH);
        lblCnH.setBounds(290, 210, 80, 20);

        jDateAdmissao.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateAdmissaoPropertyChange(evt);
            }
        });
        jPanel1.add(jDateAdmissao);
        jDateAdmissao.setBounds(430, 280, 120, 30);

        lblValidade.setText("Validade CNH");
        jPanel1.add(lblValidade);
        lblValidade.setBounds(430, 210, 140, 20);

        lblDataAdmissao.setText("Data de Admissão");
        jPanel1.add(lblDataAdmissao);
        lblDataAdmissao.setBounds(430, 260, 110, 20);

        jDateNascimento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateNascimentoPropertyChange(evt);
            }
        });
        jPanel1.add(jDateNascimento);
        jDateNascimento.setBounds(290, 280, 130, 30);

        lblCelular.setText("Celular");
        jPanel1.add(lblCelular);
        lblCelular.setBounds(150, 260, 90, 20);

        jDateDesligamento.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jDateDesligamentoInputMethodTextChanged(evt);
            }
        });
        jDateDesligamento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateDesligamentoPropertyChange(evt);
            }
        });
        jPanel1.add(jDateDesligamento);
        jDateDesligamento.setBounds(560, 280, 130, 30);

        lblMsg.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lblMsg);
        lblMsg.setBounds(370, 370, 340, 20);
        jPanel1.add(txtSenha);
        txtSenha.setBounds(450, 180, 100, 30);

        bordas.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 101, 720, 390));

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

        lblPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblPerfil.setText("Operador");
        jPanel4.add(lblPerfil);
        lblPerfil.setBounds(510, 50, 100, 30);

        jPanel5.setBackground(new java.awt.Color(52, 73, 94));

        lblFechar.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        lblFechar.setForeground(new java.awt.Color(255, 255, 255));
        lblFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
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
                .addComponent(lblFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.add(jPanel5);
        jPanel5.setBounds(680, 0, 40, 40);

        lblData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/calendario24x24.png"))); // NOI18N
        lblData.setText("Data");
        jPanel4.add(lblData);
        lblData.setBounds(610, 50, 110, 30);
        jPanel4.add(txtOperacao);
        txtOperacao.setBounds(560, 10, 100, 30);

        bordas.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 720, 100));

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

        bordas.add(jtableGuias, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 491, 720, 220));

        getContentPane().add(bordas);
        bordas.setBounds(0, 0, 722, 710);

        setSize(new java.awt.Dimension(723, 716));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        if ("Alterar".equals(txtOperacao.getText()) || "Detalhe".equals(txtOperacao.getText()) ) {
            this.dispose();
            setVisible(false);
            TelaPesquisaFuncionario tpf = new TelaPesquisaFuncionario();
            tpf.recebeOperador(lblOperador.getText(), lblPerfil.getText());
            tpf.atualizaTabela("");
            tpf.setVisible(true);
            this.dispose();

        } else {
            this.dispose();
            /*
            TelaPesquisaFuncionario tpf = new TelaPesquisaFuncionario();
            tpf.recebeOperador(lblOperador.getText(), lblPerfil.getText());
            tpf.setVisible(true);*/
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
            jDateDesligamento.setEnabled(true);

        } else {
            comboBloqueio.setSelectedItem("Desbloqueado");
            jDateDesligamento.setDate(null);
        }
        lblCargo.setForeground(Color.BLACK);
        lblMsg.setText(null);
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
            f.setEndereco(txtEndereco.getText());
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
                int op = JOptionPane.showOptionDialog(this, "Confirma a inclusão do funcionário?", "Atenção!", JOptionPane.YES_NO_OPTION, 
                         JOptionPane.ERROR_MESSAGE,null,opcao,opcao[1]);
                if (op == 0) {

                    if (funcionario.temFuncionario(f.getNome())) {
                        JOptionPane.showMessageDialog(this, "Este funcionário já existe!");
                    } else {
                        if (funcionario.adicionaFuncionario(f)) {
                            limpaForm();
                            JOptionPane.showMessageDialog(this, "Funcionário adicionado com sucesso!");

                            // Início do registro de log
                            l.setFuncionalidade("Salvar");
                            l.setDescricao("Adicionou ->" + f.getNome() + " ao cadastro de funcionários");
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
            f.setEndereco(txtEndereco.getText());
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
                    JOptionPane.showMessageDialog(this, "Transfira as mesas do funcionário antes de continuar!");
                } else {
                    if (valida()) {
                        int op = JOptionPane.showOptionDialog(this, "Confirma a alteração dos Dados?", "Atenção!", JOptionPane.YES_NO_OPTION, 
                                 JOptionPane.ERROR_MESSAGE,null,opcao,opcao[1]);
                        if (op == 0) {

                            // Altera dados do funcionário
                            if (cf.alterar(f, txtId.getText())) {
                                JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso!");
                                this.dispose();

                                TelaPesquisaFuncionario tpf = new TelaPesquisaFuncionario();
                                tpf.atualizaTabela(f.getNome());
                                tpf.recebeOperador(lblOperador.getText(), lblPerfil.getText());
                                tpf.atualizaTabela("");
                                tpf.setVisible(true);
                                // Início do registro de log
                                l.setFuncionalidade("Alterar");
                                l.setDescricao("Alterou o registro do funcionário ->" + f.getNome());
                                l.gravaLog(l);

                                //Fim do registro de log
                            } else {
                                JOptionPane.showMessageDialog(this, "Erro ao tentar alterar os dados - contate o SUPORTE!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Alteração cancelada com sucesso!");
                        }
                    }
                   
                }
            } else {
                if (valida()) {
                    int op = JOptionPane.showOptionDialog(this, "Confirma a alteração dos Dados?", "Atenção!", JOptionPane.YES_NO_OPTION, 
                             JOptionPane.ERROR_MESSAGE,null,opcao,opcao[1]);
                    if (op == 0) {
                        if (cf.alterar(f, txtId.getText())) {
                            JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso!");
                            this.dispose();
                            TelaPesquisaFuncionario tpf = new TelaPesquisaFuncionario();
                            //tpf.atualizaTabela(f.getNome());
                            tpf.recebeOperador(lblOperador.getText(), lblPerfil.getText());
                            tpf.atualizaTabela("");
                            tpf.setVisible(true);
                            // Início do registro de log
                            l.setFuncionalidade("Alterar");
                            l.setDescricao("Alterou o registro do funcionário ->" + f.getNome());
                            l.gravaLog(l);
                            //Fim do registro de log
                        } else {
                            JOptionPane.showMessageDialog(this, "Erro ao tentar alterar os dados - contate o SUPORTE!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Alteração candelada com sucesso!");
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
        lblNumero.setForeground(Color.BLACK);
        lblMsg.setText(null);
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void comboUfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUfActionPerformed
        // TODO add your handling code here:
        lblUf.setForeground(Color.BLACK);
        lblMsg.setText(null);
    }//GEN-LAST:event_comboUfActionPerformed

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        // Limita tanho do campo NOME
        txtNome.setText(u.tamanhoMaximo(txtNome.getText(), 45));
        lblNome.setForeground(Color.BLACK);
        lblMsg.setText(null);
    }//GEN-LAST:event_txtNomeKeyReleased

    private void txtEnderecoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnderecoKeyReleased
        // Limita tanho do campo ENDEREÇO
        txtEndereco.setText(u.tamanhoMaximo(txtEndereco.getText(), 45));
        lblEndereco.setForeground(Color.BLACK);
        lblMsg.setText(null);
    }//GEN-LAST:event_txtEnderecoKeyReleased

    private void txtRgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRgKeyReleased
        // Limita Tamanho do campo RG
        txtRg.setText(u.tamanhoMaximo(txtRg.getText(), 13));
        lblRg.setForeground(Color.BLACK);
        lblMsg.setText(null);

    }//GEN-LAST:event_txtRgKeyReleased

    private void txtCpfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfKeyReleased
        // Limita tamanho do campo CPF
        txtCpf.setText(u.tamanhoMaximo(txtCpf.getText(), 14));
        lblCpf.setForeground(Color.BLACK);
        lblMsg.setText(null);
    }//GEN-LAST:event_txtCpfKeyReleased

    private void txtCnhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCnhKeyReleased
        // Limita tamanho do campo CNH
        //txtCnh.setText(txtCnh.getText().replaceAll("[^0-9]", ""));
        txtCnh.setText(u.tamanhoMaximo(txtCnh.getText(), 11));
        lblCnH.setForeground(Color.BLACK);
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
        lblEmail.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyReleased
        // Limita tamanho do campo LOGIN
        txtLogin.setText(u.tamanhoMaximo(txtLogin.getText(), 10));
        lblLogin.setForeground(Color.BLACK);
        lblMsg.setText(null);
    }//GEN-LAST:event_txtLoginKeyReleased

    private void txtTelefoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefoneKeyReleased
        // limita tamanho do campo TELEFONE

    }//GEN-LAST:event_txtTelefoneKeyReleased

    private void txtBairroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBairroKeyReleased
        // Limita tamanho do campo BAIRRO
        txtBairro.setText(u.tamanhoMaximo(txtBairro.getText(), 35));
        lblBairro.setForeground(Color.black);
        lblMsg.setText(null);
    }//GEN-LAST:event_txtBairroKeyReleased

    private void txtComplementoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplementoKeyReleased
        // Limita tamanho do cmapo COMPLEMENTO
        txtComplemento.setText(u.tamanhoMaximo(txtComplemento.getText(), 35));
        lblComplemento.setForeground(Color.black);
    }//GEN-LAST:event_txtComplementoKeyReleased

    private void txtCidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCidadeKeyReleased
        // Limita tamanho do compo CIDADE
        txtCidade.setText(u.tamanhoMaximo(txtCidade.getText(), 40));
        lblCidade.setForeground(Color.black);
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
        jDateDesligamento.setEnabled(false);
        jDateAdmissao.setDate(null);
        btnSalvar.setEnabled(true);

        comboSituacao.setSelectedItem("Ativo");
        comboSituacao.setEnabled(true);


    }//GEN-LAST:event_btnRecontrataActionPerformed

    private void txtCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusLost
        // Valida o cpf Informado
        if (Util.isCPF(txtCpf.getText())) {

        } else {
            JOptionPane.showMessageDialog(this, "Digite um CPF válido!");
            txtCpf.requestFocus();
        }
    }//GEN-LAST:event_txtCpfFocusLost

    private void txtNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeMouseClicked

    private void txtCepKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCepKeyReleased
        lblCep.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtCepKeyReleased

    private void jDateValiadeCNHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateValiadeCNHFocusGained

    }//GEN-LAST:event_jDateValiadeCNHFocusGained

    private void jDateValiadeCNHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateValiadeCNHMouseClicked

    }//GEN-LAST:event_jDateValiadeCNHMouseClicked

    private void jDateValiadeCNHPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateValiadeCNHPropertyChange
        lblMsg.setText(null);
        lblValidade.setForeground(Color.BLACK);
    }//GEN-LAST:event_jDateValiadeCNHPropertyChange

    private void txtTelRecadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelRecadoKeyPressed
        lblMsg.setText(null);
        lblCelular.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtTelRecadoKeyPressed

    private void jDateAdmissaoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateAdmissaoPropertyChange
        lblMsg.setText(null);
        lblDataAdmissao.setForeground(Color.BLACK);
    }//GEN-LAST:event_jDateAdmissaoPropertyChange

    private void jDateDesligamentoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateDesligamentoPropertyChange
        lblMsg.setText(null);
        lblDtaDesligamento.setForeground(Color.BLACK);
    }//GEN-LAST:event_jDateDesligamentoPropertyChange

    private void jDateNascimentoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateNascimentoPropertyChange
        lblMsg.setText(null);
        lblDataNascimento.setForeground(Color.BLACK);
    }//GEN-LAST:event_jDateNascimentoPropertyChange

    private void comboCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCargoActionPerformed
        lblCargo.setForeground(Color.BLACK);
        lblMsg.setText(null);
    }//GEN-LAST:event_comboCargoActionPerformed

    private void comboBloqueioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBloqueioActionPerformed
        lblCargo.setForeground(Color.BLACK);
        lblMsg.setText(null);

    }//GEN-LAST:event_comboBloqueioActionPerformed

    private void txtCepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCepKeyPressed
        // Busca o CEP informado
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (evt.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
                String cep = txtCep.getText().replace("-", "");
                String json = ClienteViaCepWS.buscarCep(cep);
                Map mapa = ClienteViaCepWS.formataCepWs(json);
                try {
                    txtEndereco.setText(mapa.get("logradouro").toString());
                    txtBairro.setText(mapa.get("bairro").toString());
                    txtCidade.setText(mapa.get("localidade").toString());
                    txtComplemento.setText(mapa.get("complemento").toString());
                    comboUf.setSelectedItem(mapa.get("uf"));

                } catch (NullPointerException e) {

                }
            }
        }
    }//GEN-LAST:event_txtCepKeyPressed

    public void limpaForm() {
        // Limpa formulário
        txtId.setText(null);
        txtNome.setText(null);
        txtEndereco.setText(null);
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
        txtHistorico.setText(null);
    }

    private void bloqueiaTudo() {
        // Limpa formulário
        txtId.setEnabled(false);
        txtNome.setEnabled(false);
        txtEndereco.setEnabled(false);
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
    private javax.swing.JPanel bordas;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jtableGuias;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblBloqueio;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCep;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblCnH;
    private javax.swing.JLabel lblComplemento;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDataAdmissao;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblDtaDesligamento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblFechar;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblRg;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSituação;
    private javax.swing.JLabel lblSubTitulo;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTiulo;
    private javax.swing.JLabel lblUf;
    private javax.swing.JLabel lblValidade;
    private javax.swing.JLabel lbltemMesa;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCaminho;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCnh;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextArea txtHistorico;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtOperacao;
    private javax.swing.JTextField txtRg;
    private javax.swing.JPasswordField txtSenha;
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
            lblMsg.setText("*Informe um NOME para continuar!");
            txtNome.requestFocus();
            lblNome.setForeground(Color.red);
            resp = false;
        } else if ("".equals(txtEndereco.getText())) {
            lblMsg.setText("*Informe um ENDEREÇO para continuar!");
            txtEndereco.requestFocus();
            lblEndereco.setForeground(Color.red);
            resp = false;
        } else if ("".equals(txtNumero.getText())) {
            lblMsg.setText("*Informe o NÚMERO para continuar!");
            txtNumero.requestFocus();
            lblNumero.setForeground(Color.red);
            resp = false;
        } else if ("".equals(txtBairro.getText())) {
            lblMsg.setText("*Informe um BAIRRO para continuar!");
            txtBairro.requestFocus();
            lblBairro.setForeground(Color.red);
            resp = false;
        } else if ("     -   ".equals(txtCep.getText())
                || txtCep.getText().equals("00000-000") || txtCep.getText().equals("11111-111")
                || txtCep.getText().equals("22222-222") || txtCep.getText().equals("33333-333")
                || txtCep.getText().equals("44444-444") || txtCep.getText().equals("55555-555")
                || txtCep.getText().equals("66666-666") || txtCep.getText().equals("77777-777")
                || txtCep.getText().equals("88888-888") || txtCep.getText().equals("99999-999")
                || txtCep.getText().length() != 9) {
            lblMsg.setText("*Informe um CEP para continuar!");
            txtCep.requestFocus();
            lblCep.setForeground(Color.red);
            resp = false;
        } else if ("".equals(txtCidade.getText())) {
            lblMsg.setText("*Informe uma CIDADE para continuar!");
            lblCidade.setForeground(Color.RED);
            txtCidade.requestFocus();

            resp = false;
        } else if ("Selecione...".equals(comboUf.getSelectedItem().toString())) {
            lblMsg.setText("*Informe uma UF para continuar!");
            lblUf.setForeground(Color.red);
            comboUf.requestFocus();
            resp = false;
        } else if ("".equals(txtLogin.getText())||txtLogin.getText().length()<7) {
            lblMsg.setText("*Informe um LOGIN com tamanho entre 7 e 10 caracteres!");
            txtLogin.requestFocus();
            lblLogin.setForeground(Color.RED);
            resp = false;
        } else if ("".equals(txtSenha.getText())||txtSenha.getText().length()<5) {
            lblMsg.setText("*Informe uma SENHA com tamanho entre 5 e 10 caracteres!");
            txtSenha.requestFocus();
            lblSenha.setForeground(Color.red);
            resp = false;
        } else if ("".equals(txtRg.getText())
                || txtRg.getText().equals("0000000000000") || txtRg.getText().equals("1111111111111")
                || txtRg.getText().equals("2222222222222") || txtRg.getText().equals("3333333333333")
                || txtRg.getText().equals("4444444444444") || txtRg.getText().equals("5555555555555")
                || txtRg.getText().equals("6666666666666") || txtRg.getText().equals("7777777777777")
                || txtRg.getText().equals("8888888888888") || txtRg.getText().equals("9999999999999")) {
            lblMsg.setText("*Informe um RG para continuar!");
            lblRg.setForeground(Color.RED);
            txtRg.requestFocus();
            resp = false;
        } else if (!Util.isCPF(txtCpf.getText())) {
            lblMsg.setText("*Informe um CPF para continuar!");
            lblCpf.setForeground(Color.RED);
            txtCpf.requestFocus();
            resp = false;
            /*
           - Caso CNH  seja preenchido segue com a verificação dos demais componentes do formulário
           - Se CNH seja preenchido, questiona o preenchimento da validade da CNH e segue com a validação
             dos demais componentes.
             */

        } else if (!txtCnh.getText().isEmpty()) { // Se CNH estiver preenchida questiona a validade.
            if ("".equals(txtRg.getText())
                    || txtCnh.getText().equals("00000000000") || txtCnh.getText().equals("11111111111")
                    || txtCnh.getText().equals("22222222222") || txtCnh.getText().equals("33333333333")
                    || txtCnh.getText().equals("44444444444") || txtCnh.getText().equals("55555555555")
                    || txtCnh.getText().equals("66666666666") || txtCnh.getText().equals("77777777777")
                    || txtCnh.getText().equals("88888888888") || txtCnh.getText().equals("99999999999")
                    || txtCnh.getText().length() != 11) {
                lblMsg.setText("*Informe uma CNH válida para continuar!");
                txtRg.requestFocus();
                lblCnH.setForeground(Color.RED);
                resp = false;
            } else if (null == jDateValiadeCNH.getDate()) { // Se a data não for informada avisa o usuário.
                lblMsg.setText("*Informe uma Data de Validade para continuar!");
                jDateValiadeCNH.requestFocus();
                lblValidade.setForeground(Color.RED);
                resp = false;
            } else {  // Segue com a validação dos demais campos                            
                if ("(  )     -    ".equals(txtTelRecado.getText())
                        || txtTelRecado.getText().equals("(00)00000-0000") || txtTelRecado.getText().equals("(11)11111-1111")
                        || txtTelRecado.getText().equals("(22)22222-2222") || txtTelRecado.getText().equals("(33)33333-3333")
                        || txtTelRecado.getText().equals("(44)44444-4444") || txtTelRecado.getText().equals("(55)55555-5555")
                        || txtTelRecado.getText().equals("(66)66666-6666") || txtTelRecado.getText().equals("(77)77777-7777")
                        || txtTelRecado.getText().equals("(88)88888-8888") || txtTelRecado.getText().equals("(99)99999-9999")
                        || txtTelRecado.getText().length() != 14) {
                    lblMsg.setText("*Informe um NÚMERO DE CELULAR para continuar!");
                    txtTelRecado.requestFocus();
                    lblCelular.setForeground(Color.RED);
                    resp = false;
                } else if (null == jDateNascimento.getDate()) {
                    lblMsg.setText("*Informe a DATA DE NASCIMENTO para continuar!");
                    jDateNascimento.requestFocus();
                    lblDataNascimento.setForeground(Color.RED);
                    resp = false;

                } else if (null == jDateAdmissao.getDate()) {
                    lblMsg.setText("*Informe a DATA DE ADMISSÃO para continuar!");
                    jDateAdmissao.requestDefaultFocus();
                    lblDataAdmissao.setForeground(Color.RED);
                    resp = false;

                } else if (null != jDateDesligamento.getDate()) {//Data de desligamento preenchida válida
                    Date dtAtual = new Date();
                    Date dtDeslig = jDateDesligamento.getDate();
                    if (dtDeslig.after(dtAtual)) {
                        lblMsg.setText("*A DATA DE DESLIGAMENTO não pode ser maior que a data atual!");
                        jDateDesligamento.requestFocus();
                        lblDtaDesligamento.setForeground(Color.RED);
                        resp = false;
                    } else if ("Ativo".equals(comboSituacao.getSelectedItem().toString())) {
                        lblMsg.setText("*Altere a Situação do funcionário para Inativo!");
                        resp = false;
                    }
                    //Data de Desligamento inválida. 
                    // Obs: Quando a data de Desligamento não preechida e campo situação ativo segue processamento.
                } else if (null == jDateDesligamento.getDate() && ("Inativo".equals(comboSituacao.getSelectedItem().toString()))) {
                    lblMsg.setText("*DATA DE DESLIGAMENTO inválida ou não informada!");
                    jDateDesligamento.requestFocus();
                    lblDtaDesligamento.setForeground(Color.RED);
                    resp = false;
                } else if ("Selecione...".equals(comboCargo.getSelectedItem().toString())) {
                    lblMsg.setText("*Informe um Cargo para continuar!");
                    comboCargo.requestFocus();
                    lblCargo.setForeground(Color.RED);
                    resp = false;

                }
            }
        } else { // Comtinua validação se CNH e Validade estiverem preechidos corretamente ou vazio.

            if ("(  )     -    ".equals(txtTelRecado.getText())
                    || txtTelRecado.getText().equals("(00)00000-0000") || txtTelRecado.getText().equals("(11)11111-1111")
                    || txtTelRecado.getText().equals("(22)22222-2222") || txtTelRecado.getText().equals("(33)33333-3333")
                    || txtTelRecado.getText().equals("(44)44444-4444") || txtTelRecado.getText().equals("(55)55555-5555")
                    || txtTelRecado.getText().equals("(66)66666-6666") || txtTelRecado.getText().equals("(77)77777-7777")
                    || txtTelRecado.getText().equals("(88)88888-8888") || txtTelRecado.getText().equals("(99)99999-9999")
                    || txtTelRecado.getText().length() != 14) {
                lblMsg.setText("*Informe um número de CELULAR para continuar!");
                txtTelRecado.requestFocus();
                lblCelular.setForeground(Color.RED);
                resp = false;
            } else if (null == jDateNascimento.getDate()) {

                lblMsg.setText("*Informe a DATA DE NASCIMENTO para continuar!");
                lblDataNascimento.setForeground(Color.RED);
                jDateNascimento.requestFocus();
                resp = false;

            } else if (null == jDateAdmissao.getDate()) {
                lblMsg.setText("*Informe a DATA DE ADMISSÃO para continuar!");
                lblDataAdmissao.setForeground(Color.RED);
                jDateAdmissao.requestFocus();
                resp = false;

            } else if (null != jDateDesligamento.getDate()) { // Data de Desligamento Válida.
                Date dtAtual = new Date();
                Date dtDeslig = jDateDesligamento.getDate();
                if (dtDeslig.after(dtAtual)) {
                    lblMsg.setText("*A Data de Desligamento não pode ser maior que a data atual!");
                    lblDtaDesligamento.setForeground(Color.RED);
                    jDateDesligamento.requestFocus();
                    resp = false;
                } else if ("Ativo".equals(comboSituacao.getSelectedItem().toString())) {
                    lblMsg.setText("*Altere a Situação do funcionário para Inativo!");
                    resp = false;
                }
                //Data de Desligamento inválida. 
                // Obs: Quando a data de Desligamento não preechida e campo situação ativo segue processamento.
            } else if (null == jDateDesligamento.getDate() && ("Inativo".equals(comboSituacao.getSelectedItem().toString()))) {
                lblMsg.setText("*Data de Desligamento inválida ou não informada!");
                jDateDesligamento.requestFocus();
                lblDtaDesligamento.setForeground(Color.RED);
                resp = false;
            } else if ("Selecione...".equals(comboCargo.getSelectedItem().toString())) {
                lblMsg.setText("*Informe um Cargo para continuar!");
                comboCargo.requestFocus();
                lblCargo.setForeground(Color.RED);
                resp = false;
            }
        }
        return resp;
    }
}
