/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.AutenticaUsuario;
import br.com.bar.dao.ConexaoBd;
import br.com.bar.dao.CriptoGrafa;
import br.com.bar.dao.Email;
import br.com.bar.model.DadosEmpresa;
import br.com.bar.model.Funcionario;
import br.com.bar.util.ConexaoInternet;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerAtivacao;
import br.com.br.controler.ControlerCaixa;

import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerFuncionario;
import br.com.br.controler.ControlerParametro;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author elias
 */
public class TelaLogin extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ControlerDadosEmpresa d = new ControlerDadosEmpresa();
    ControlerAtivacao ativacao = new ControlerAtivacao();
    AutenticaUsuario autentica = new AutenticaUsuario();
    CriptoGrafa criptoGrafa = new CriptoGrafa();
    ControlerFuncionario cf = new ControlerFuncionario();
    String caminho = "C:\\SysBar\\bkp_exec.txt";
    Util u = new Util();

    Date dataAtual = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("h:mm");
    String bloqueio = "";
    /**
     * Creates new form TelaLogin
     */
    ControlerParametro cp = new ControlerParametro();
    int acumula = 0;

    public TelaLogin() {
        initComponents();
        String login = cf.localizaIdLogin("admin");
        // Verifia a existência do usuário admins caso não existe o usuário será criado 
        if ("".equals(login)){
            Funcionario f = new Funcionario();
            f.setNome("Admin");
            f.setLogin("admin");
            f.setCargo("Gerente");
            f.setSenha("admin");
            f.setStatus("0");
            f.setBloqueado("0");
            
            if (!cf.adicionaFuncionario(f)){
                lblMsg.setText("Não foi possível criar o usuário padrão!");
            }
        }
        
        DadosEmpresa dados = d.selecionaDados();
        conexao = ConexaoBd.conector();
        // Carrega o ícone setado no Cadastro Empresa
        lblLogo.setIcon(u.carregaLogo());
        txtSenha.setEnabled(false);
        btnLogin.setEnabled(false);
        // Descriptogafa a chave informada no cadastro empresa
        String chave = criptoGrafa.decripta(dados.getLicenca());
        long dias = u.retornaTotalDeDias(chave);
        cf.carregaComboFuncionarioAtivo(comboLogin);
                //Converta data de validade no formato 'dd/mm/yyyy'
                //Adiciona msg de Licença válida até na tela de Login
                String dataBr = u.formataDataBr(u.converteData(chave));
                lblValidade.setText("Licença Válida até: "+dataBr);
        if (dias > 10) {
            if (conexao != null) {
                // Retorna os dados da empresa
                DadosEmpresa dadosEmpresa = d.selecionaDados();
                lblLicenca.setText("Copyright todos os direitos reservados para");
                lbllicenca2.setText(dadosEmpresa.getNome_empresa());
                lblCnpjEmpresa.setText(dadosEmpresa.getCnpj());
                
            } else {
                // Caso a conexão retorne null chama da tela de parâmetro
                TelaPametro param = new TelaPametro();
                // Fixa a janela ja frente da sdemais
                param.setAlwaysOnTop(true);
                // Exibe Tela de Parâmetro 
                param.setVisible(true);
            }

        } else if (dias > 0 && dias <= 10) {
            // Chma o gerenciador de licença e permite o login
            DadosEmpresa dadosEmpresa = d.selecionaDados();
            lblLicenca.setText("Copyright Todos os Direitos reservados para");
            lbllicenca2.setText(dadosEmpresa.getNome_empresa());
            lblCnpjEmpresa.setText(dadosEmpresa.getCnpj());

            TelaGerenciadorDeLicenca g = new TelaGerenciadorDeLicenca();
            g.recebeDias(dias, "A Licença atual expira em " + String.valueOf(dias) + " dia(s)!");
            g.setAlwaysOnTop(true);
            g.setVisible(true);
            
            ConexaoInternet ci = new ConexaoInternet();
            
            if (ci.temConexao()){
                StringBuilder sb = new StringBuilder();
                sb.append("<html>");
                sb.append("<BR><H3>**************** ATENÇÃO ****************</H3>");
                sb.append("<br><br>");
                sb.append("Olá, ").append("<b>").append(dados.getNome_empresa()).append("</b>").append(" a sua licença atual vence em <font color='red'><b>").append(String.valueOf(dias)).append(" </b></font>dias!. " );
                sb.append("<br><br>");
                sb.append("Evite o bloqueio do sistema, solicite sua renovação já! ");
                sb.append("<br><br>");
                sb.append("<b>MASTERFOOD </b><br> SISTEMA DE GERENCIAMENTO DE BARES E RESTAURANTES</b><br>");
                sb.append("<b>E-MAIL:</b> suporte@rese7.com.br");
                sb.append("<br><b>CONTATO(S):</b> (81) 98966-1904 | (81) 99897-8092 <br><br>");
                sb.append("<font color='red'><b>").append(lblValidade.getText().toUpperCase()).append("</font></b>");
                
                Email e = new Email();
                try {
                    e.htmlMail(dados.getEmail(), "suporte@rese7.com.br", "Aviso - Sua licença está vencendo!", sb, "Suporte MasterFood");
                    
                } catch (EmailException ex) {
                    System.out.println("Não foi possível enviar o Email");
                }
            }

        } else {
            // Chama o gerenciador  de licença e saí do sistema
            TelaGerenciadorDeLicenca g = new TelaGerenciadorDeLicenca();
            g.recebeDias(dias, "Licença Expirada!!!");
            g.setAlwaysOnTop(true);
            g.setVisible(true);

            comboLogin.setEnabled(false);
            lblMsg.setText("Licença Expirada!!!");
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

        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblVersao = new javax.swing.JLabel();
        lblValidade = new javax.swing.JLabel();
        lblLicenca = new javax.swing.JLabel();
        lbllicenca2 = new javax.swing.JLabel();
        lblCnpjEmpresa = new javax.swing.JLabel();
        lblVersao1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblMsg = new javax.swing.JLabel();
        comboLogin = new javax.swing.JComboBox<>();
        lblMsg2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(52, 73, 94));
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(52, 73, 94));
        jPanel1.setLayout(null);

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/logo.png"))); // NOI18N
        jPanel1.add(lblLogo);
        lblLogo.setBounds(20, 20, 250, 190);

        lblVersao.setForeground(new java.awt.Color(255, 255, 255));
        lblVersao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVersao.setText("Software licenciado para");
        jPanel1.add(lblVersao);
        lblVersao.setBounds(60, 210, 170, 20);

        lblValidade.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblValidade.setForeground(new java.awt.Color(255, 255, 255));
        lblValidade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValidade.setText("xxxx");
        jPanel1.add(lblValidade);
        lblValidade.setBounds(30, 400, 240, 20);

        lblLicenca.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblLicenca.setForeground(new java.awt.Color(255, 255, 255));
        lblLicenca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLicenca.setText("xxxxx");
        jPanel1.add(lblLicenca);
        lblLicenca.setBounds(0, 300, 290, 20);

        lbllicenca2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        lbllicenca2.setForeground(new java.awt.Color(255, 255, 255));
        lbllicenca2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbllicenca2.setText("empresa");
        jPanel1.add(lbllicenca2);
        lbllicenca2.setBounds(20, 220, 260, 40);

        lblCnpjEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lblCnpjEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCnpjEmpresa.setText("cnpj");
        jPanel1.add(lblCnpjEmpresa);
        lblCnpjEmpresa.setBounds(20, 260, 260, 14);

        lblVersao1.setForeground(new java.awt.Color(255, 255, 255));
        lblVersao1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVersao1.setText("   2.0.2-nf-40 - 03/06/2020");
        jPanel1.add(lblVersao1);
        lblVersao1.setBounds(20, 280, 240, 20);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("suporte@rese7.com.br");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(30, 360, 240, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText(" Rese7 Soluções em TI");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(30, 320, 240, 20);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Suporte");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(30, 340, 240, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("(81) 98966-1904 | (81) 99897-8092");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(30, 380, 240, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 300, 430);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(null);

        txtSenha.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSenha.setText("admin");
        txtSenha.setBorder(null);
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSenhaKeyReleased(evt);
            }
        });
        jPanel2.add(txtSenha);
        txtSenha.setBounds(40, 200, 250, 40);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lock.png"))); // NOI18N
        jLabel4.setText("Senha");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(40, 170, 110, 30);

        btnLogin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLogin.setText("Entrar");
        btnLogin.setBorder(null);
        btnLogin.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                btnLoginAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogin);
        btnLogin.setBounds(40, 310, 250, 40);

        jPanel3.setBackground(new java.awt.Color(52, 73, 94));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        jPanel3.add(jLabel5);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(280, 0, 40, 40);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        jLabel6.setText("Login");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(40, 80, 250, 30);

        lblMsg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblMsg);
        lblMsg.setBounds(40, 390, 270, 30);

        comboLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoginActionPerformed(evt);
            }
        });
        jPanel2.add(comboLogin);
        comboLogin.setBounds(40, 110, 250, 40);

        lblMsg2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMsg2.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblMsg2);
        lblMsg2.setBounds(40, 240, 250, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(290, 0, 320, 430);

        setSize(new java.awt.Dimension(609, 429));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        realizaLogin();
    }//GEN-LAST:event_btnLoginActionPerformed
    private void realizaLogin() {

        // Autentica Usuário
        ControlerParametro p = new ControlerParametro();
        //Excluir após teste com usuário admin
        //String cargoFuncionario = cf.cargoFuncionario(comboLogin.getSelectedItem().toString());//Retorna o cargo do funcionário    
        if ("Selecione...".equals(comboLogin.getSelectedItem().toString())) {
            //lblMsg.setText("*Opção Inválida!"); --> Excluir após validação
        } else if (autentica.isExistsSenha(txtSenha.getText().toLowerCase())) { // Verifica se a senha Existe

            if (autentica.autentica2(comboLogin.getSelectedItem().toString(), txtSenha.getText().toLowerCase())) {
                // Executa módulo de Backup na inicialização após verificação.

                String cargo = autentica.enviarCargo();
                //System.out.println("Cargo: " + cargo);
                switch (cargo) {
                    case "Gerente": // Vai para tela principal
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.recebeOperador(autentica.enviaOperador().toUpperCase(), autentica.enviarCargo());
                        principal.setVisible(true);
                        this.dispose();
                        break;

                    case "Estoquista": // Vai para tela Estoque
                        TelaMovimentacao estoque = new TelaMovimentacao();
                        // Instancia a tela principal
                        TelaPrincipal tela = new TelaPrincipal();
                        estoque.recebeOperador(tela, autentica.enviaOperador(), autentica.enviarCargo());
                        estoque.setVisible(true);
                        this.dispose();
                        break;
                    case "Garçom":
                        TelaPedido3 tp3 = new TelaPedido3();
                        tp3.recebeOperador(autentica.enviaOperador(), autentica.enviarCargo());
                        tp3.setVisible(true);
                        this.dispose();
                        break;
                    case "Caixa": // Vai para tela Estoque
                        // Chama tela do Caixa
                        ControlerCaixa controlerCaixa = new ControlerCaixa();
                        // Se o operador possui movimentação abre Tela de Caixa       
                        int statusCaixa = controlerCaixa.retornaStatusCaixa(Integer.parseInt(cf.localizaIdLogin(autentica.enviaOperador())));
                        if (statusCaixa==1){
                            JOptionPane.showMessageDialog(this, "Caixa Fechado, Infome ao seu Gerente");
                            TelaLogin login = new TelaLogin();
                            login.setVisible(true);
                        }else {
                                TelaCaixa caixa = new TelaCaixa();
                                TelaPrincipal telaPrincipal = new TelaPrincipal();
                                caixa.recebeOperador(telaPrincipal, autentica.enviaOperador().toUpperCase(), autentica.enviarCargo());
                                caixa.setVisible(true);   
                        }
                        this.dispose();
                        break;
                    case "Cozinheiro": // Vai para Cozinha
                        TelaConzinha cozinha = new TelaConzinha();
                        cozinha.recebeOperador(autentica.enviaOperador().toUpperCase(), autentica.enviarCargo());
                        cozinha.setVisible(true);
                        this.dispose();
                        break;
                }
            } else {
                if (!"Admin".equals(comboLogin.getSelectedItem().toString())){
                    realizaControleLogin();                    
                }else{
                    lblMsg2.setText("*Senha inválida, digite novamente!");
                }
            }

        } else {
            //Verifica o cargo do usuário que está logando-se no sistema
            //Caso o usuário seja gerente o ontrole de login não realizará o bloqueio caso 
            //Seja excedida as tentativas.
                    
            if (!"admin".equals(comboLogin.getSelectedItem().toString())){                
                realizaControleLogin();
            }else{
                lblMsg2.setText("*Senha inválida, digite novamente!");
            }
        }
    }
    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed

    }//GEN-LAST:event_txtSenhaActionPerformed
    private void realizaControleLogin() {
        int contagem = contador();
        int tentavias = 3 - contagem;

        if (contagem > 2) { // Valor anterior 3
            cp.bloqueiaLogin(cf.localizaIdLogin(comboLogin.getSelectedItem().toString()));
            comboLogin.setSelectedIndex(0);
            acumula = 0;
            txtSenha.setText(null);
            txtSenha.setEnabled(true);
            lblMsg2.setText(null);
            btnLogin.setEnabled(false);

        } else {

            lblMsg2.setText("*Senha inválida, digite novamente!");
            lblMsg.setForeground(Color.red);
            lblMsg.setText("*Resta(m) " + tentavias + " tentativa(s) antes do bloqueio!");
        }
    }
    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        // Realiza login
        lblMsg.setText(null);
        lblMsg2.setText(null);

        btnLogin.setEnabled(true);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            realizaLogin();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // Fecha Janela
        System.exit(0);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginMouseClicked

    private void btnLoginAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_btnLoginAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginAncestorAdded

    private void comboLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoginActionPerformed
        lblMsg.setText(null);

        boolean bloqueado = autentica.taBloqueado(comboLogin.getSelectedItem().toString());

        if ("Selecione...".equals(comboLogin.getSelectedItem().toString())) {
            txtSenha.setEnabled(false);
            lblMsg.setText(null);
            lblMsg2.setText(null);
            txtSenha.setText(null);
            btnLogin.setEnabled(false);
        } else {
            if (!bloqueado) {
                txtSenha.setEnabled(true);
                acumula = 0;
                btnLogin.setEnabled(true);
                txtSenha.requestFocus();
                //
                txtSenha.setText(null);
                lblMsg.setText(null);
                lblMsg2.setText(null);
                        
            } else {
                lblMsg.setText("*Usuário bloqueado, procure um Administrador!");
                txtSenha.setEnabled(false);
            }

        }

    }//GEN-LAST:event_comboLoginActionPerformed

    private void txtSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyReleased
        txtSenha.setText(u.tamanhoMaximo(txtSenha.getText(), 10));
    }//GEN-LAST:event_txtSenhaKeyReleased

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox<String> comboLogin;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCnpjEmpresa;
    private javax.swing.JLabel lblLicenca;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblMsg2;
    private javax.swing.JLabel lblValidade;
    private javax.swing.JLabel lblVersao;
    private javax.swing.JLabel lblVersao1;
    private javax.swing.JLabel lbllicenca2;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables

    private int contador() {
        int contador = 1;
        acumula = acumula + contador;
        System.out.println(acumula);
        return acumula;
    }

}
