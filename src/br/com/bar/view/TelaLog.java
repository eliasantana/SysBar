/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.dao.ReportUtil;
import br.com.bar.model.DadosEmpresa;
import br.com.bar.model.TableModelLog;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerFuncionario;
import br.com.br.controler.ControlerLog;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author elias
 */
public class TelaLog extends javax.swing.JFrame {

    /**
     * Creates new form TelaLog
     */
  
    ControlerLog lc = new ControlerLog();
    ControlerFuncionario f = new ControlerFuncionario();
    Connection conexao = ConexaoBd.conector();
    Util u = new Util();
    ReportUtil rpu = new ReportUtil();
    ControlerDadosEmpresa empresa = new ControlerDadosEmpresa();
    TableModelLog modelLog = new TableModelLog();

    int limite = 0;
    int offset = 0;
    int npagina = 0;
    int linhaTabela = 0;
    int total = 0;
    int visualizados = 0;

    public TelaLog() {
        initComponents();
        f.carregaComboFuncionario(comboUsuario);
        Date dataAtual = new Date();
        jDateChooserInicio.setDate(dataAtual);
        jDateChooserFim.setDate(dataAtual);
        btnImprimir.setEnabled(false);
        lblCargo.setVisible(false);
        lblOperador.setVisible(false);
        limite = Integer.parseInt(jSpinnerLimite.getValue().toString());
        
        desabilitaPaginacao();
        modelLog.redimensionaColunas(tblLog);
        btnListar.setEnabled(false);
        
        btnPrimeiro.setVisible(false);
        btnUltimo.setVisible(false);
        lblTotRegistro.setVisible(false);
        lblVisualizados.setVisible(false);
        
    }

    public void recebeOperador(String operador, String cargo) {

        lblOperador.setText(operador);
        lblCargo.setText(cargo);
        desabilitaPaginacao();
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
        lbltitulo = new javax.swing.JLabel();
        lblOperador = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbltitulo1 = new javax.swing.JLabel();
        painelCentral = new javax.swing.JPanel();
        jDateChooserInicio = new com.toedter.calendar.JDateChooser();
        jDateChooserFim = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLog = new javax.swing.JTable();
        comboUsuario = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JLabel();
        btnListar = new javax.swing.JLabel();
        btnAnteior = new javax.swing.JButton();
        jSpinnerLimite = new javax.swing.JSpinner();
        btnProx = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTotRegistro = new javax.swing.JLabel();
        btnUltimo = new javax.swing.JButton();
        btnPrimeiro = new javax.swing.JButton();
        lblVisualizados = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(243, 156, 18));
        jPanel1.setForeground(new java.awt.Color(243, 156, 18));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbltitulo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        lbltitulo.setText("de Logs");
        jPanel1.add(lbltitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 169, 35));

        lblOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperador.setText("jLabel6");
        jPanel1.add(lblOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(794, 84, 89, -1));

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("lblCargo");
        jPanel1.add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(901, 84, 89, -1));

        jPanel2.setBackground(new java.awt.Color(52, 73, 94));

        btnFechar.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFechar.setForeground(new java.awt.Color(255, 255, 255));
        btnFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        btnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/log64x64.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 9, -1, -1));

        lbltitulo1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 48)); // NOI18N
        lbltitulo1.setText("Gerenciamento ");
        jPanel1.add(lbltitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 9, 351, 60));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1030, 130);

        painelCentral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooserInicio.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jDateChooserInicioInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jDateChooserInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserInicioPropertyChange(evt);
            }
        });
        painelCentral.add(jDateChooserInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 69, 148, 40));

        jDateChooserFim.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jDateChooserFimInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jDateChooserFim.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserFimPropertyChange(evt);
            }
        });
        painelCentral.add(jDateChooserFim, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 69, 148, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Início");
        painelCentral.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 58, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Fim");
        painelCentral.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 48, 70, -1));

        tblLog = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DATA", "HORA", "FUNÇÃO", "AÇÃO"
            }
        ));
        tblLog.setRowHeight(18);
        jScrollPane1.setViewportView(tblLog);

        painelCentral.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, 1006, 304));

        comboUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboUsuarioItemStateChanged(evt);
            }
        });
        comboUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUsuarioActionPerformed(evt);
            }
        });
        painelCentral.add(comboUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 69, 210, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Usuário");
        painelCentral.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 48, 70, -1));

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Impressora32x32.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimirMouseClicked(evt);
            }
        });
        painelCentral.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 70, 116, 39));

        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        btnListar.setText("Pesquisar");
        btnListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnListarMouseEntered(evt);
            }
        });
        painelCentral.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 116, 37));

        getContentPane().add(painelCentral);
        painelCentral.setBounds(0, 90, 1030, 440);

        btnAnteior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnvoltar.png"))); // NOI18N
        btnAnteior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteiorActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnteior);
        btnAnteior.setBounds(320, 540, 60, 40);

        jSpinnerLimite.setModel(new javax.swing.SpinnerNumberModel(100, 100, null, 50));
        jSpinnerLimite.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerLimiteStateChanged(evt);
            }
        });
        getContentPane().add(jSpinnerLimite);
        jSpinnerLimite.setBounds(470, 540, 80, 40);

        btnProx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btnproximo32x32.png"))); // NOI18N
        btnProx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProxMouseClicked(evt);
            }
        });
        btnProx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProxActionPerformed(evt);
            }
        });
        getContentPane().add(btnProx);
        btnProx.setBounds(560, 540, 60, 40);

        jLabel5.setText("Registros por");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(390, 540, 130, 14);

        jLabel7.setText("Página");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(420, 560, 60, 14);

        lblTotRegistro.setText("0");
        getContentPane().add(lblTotRegistro);
        lblTotRegistro.setBounds(830, 550, 50, 20);

        btnUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/ultimo2.png"))); // NOI18N
        btnUltimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUltimoMouseClicked(evt);
            }
        });
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        getContentPane().add(btnUltimo);
        btnUltimo.setBounds(620, 540, 60, 40);

        btnPrimeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/primeiro.png"))); // NOI18N
        btnPrimeiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrimeiroMouseClicked(evt);
            }
        });
        btnPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeiroActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrimeiro);
        btnPrimeiro.setBounds(260, 540, 60, 40);

        lblVisualizados.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVisualizados.setText("0");
        getContentPane().add(lblVisualizados);
        lblVisualizados.setBounds(750, 550, 50, 20);

        setSize(new java.awt.Dimension(1027, 582));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseClicked
        // Fecha Janela
        dispose();
    }//GEN-LAST:event_btnFecharMouseClicked

    private void btnListarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListarMouseClicked
       
       
        if (btnListar.isEnabled()) {
            listar();
            offset = Integer.parseInt(jSpinnerLimite.getValue().toString());
            //System.out.println("Total:" + total);
            System.out.println("OffSet: " + offset);
            if (total > 0) {
                habilitaPaginacao();
                btnImprimir.setEnabled(true);
                if (total>limite){
                    visualizados = offset;
                    lblVisualizados.setText(String.valueOf(visualizados));
                }else {
                    lblVisualizados.setText(String.valueOf(visualizados));
                }
            } else {
                desabilitaPaginacao();
            }

        }
         int linhas = tblLog.getRowCount();
        if (linhas < Integer.parseInt(jSpinnerLimite.getValue().toString())){
            btnProx.setEnabled(false);
        }
        System.out.println("Linhas Tabelas "+linhas);

        
    }//GEN-LAST:event_btnListarMouseClicked

    private void comboUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUsuarioActionPerformed
        // TODO add your handling code here:
        offset = 0;
        npagina = 0;

        try {

            Date dataInicio = jDateChooserInicio.getDate();
            Date dataFim = jDateChooserFim.getDate();
            
            if (null != dataInicio) {

                String dtInicio = u.formataDataBanco(dataInicio);
                String dtFim = u.formataDataBanco(dataFim);

                long dias = u.retornaTotalDeDias(dtInicio, dtFim);
                if (dias > 30) {
                    JOptionPane.showMessageDialog(this, "O período máximo permitido para a pesquisa é de 30 dias!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    jDateChooserFim.setDate(new Date());
                    btnListar.setEnabled(false);
                } else {
                    btnListar.setEnabled(true);
                }
            }
        } catch (NullPointerException e) {
        }

    }//GEN-LAST:event_comboUsuarioActionPerformed

    private void comboUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboUsuarioItemStateChanged
        // TODO add your handling code here:
        //String funcionario = comboUsuario.getSelectedItem().toString();
        // tblLog.setModel(DbUtils.resultSetToTableModel(lc.listaLog(tblLog, funcionario)));

    }//GEN-LAST:event_comboUsuarioItemStateChanged

    private void btnImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimirMouseClicked

        // Imprime o resultado da pesquisa e exibe o relatório em tela
        if (btnImprimir.isEnabled()) {
            DadosEmpresa dados = empresa.selecionaDados();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dtIni = df.format(jDateChooserInicio.getDate().getTime());
            String dtfim = df.format(jDateChooserFim.getDate().getTime());

            HashMap param = new HashMap();
            param.put("nome", comboUsuario.getSelectedItem().toString());
            param.put("dtinicio", dtIni);
            param.put("dtfim", dtfim);
            // Adiciona o período ao relatório as datas no formato BR 
            param.put("dataInicio", u.formataDataBr(jDateChooserInicio.getDate()));
            param.put("dataFim", u.formataDataBr(jDateChooserFim.getDate()));
            param.put("logo", dados.getLogo());

            try {
                rpu.imprimeRelatorioTela("relatorioDeLog.jasper", param,"Relatório de Log");

            } catch (JRException e) {
                System.out.println("br.com.bar.view.TelaLog.jLabel5MouseClicked() e" + e);
            }
        }

    }//GEN-LAST:event_btnImprimirMouseClicked

    private void btnAnteiorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteiorActionPerformed
        habilitaPaginacao();
       
        if (offset > 0) {
            limite = Integer.parseInt(jSpinnerLimite.getValue().toString());
            offset =  offset- limite;
            lblVisualizados.setText(String.valueOf(offset));
            System.out.println("Anterior OffSET"+offset);
            System.out.println("Limite ANTERIOR: "+limite);
            listar();
        } else {
            /* 
            offset=0;
            listar();*/
            btnAnteior.setEnabled(false);
            btnPrimeiro.setEnabled(false);
            btnUltimo.setEnabled(true);
            btnProx.setEnabled(true);
            offset = 0;
            npagina = 0;
        }
        

    }//GEN-LAST:event_btnAnteiorActionPerformed

    private void btnProxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProxActionPerformed
        linhaTabela = tblLog.getRowCount();        
        lblVisualizados.setText(String.valueOf(jSpinnerLimite.getValue().toString()));
        if (linhaTabela < Integer.parseInt(jSpinnerLimite.getValue().toString())) {
            desabilitaPaginacao();
            btnPrimeiro.setEnabled(true);
            btnAnteior.setEnabled(true);

        } else {
            npagina = npagina + 1;
            offset = (limite * npagina) - limite;
            
            listar();
           
            if (offset == 0) {
                btnAnteior.setEnabled(true);
                btnPrimeiro.setEnabled(true);
            }

        }
         //visualizados = visualizados+Integer.parseInt(jSpinnerLimite.getValue().toString());
         
         if (visualizados==0){
             lblTotRegistro.setText(jSpinnerLimite.getValue().toString());
             offset=Integer.parseInt(jSpinnerLimite.getValue().toString());

         }
         
         if (offset>Integer.parseInt(lblTotRegistro.getText())){
             lblVisualizados.setText(lblTotRegistro.getText());
             btnPrimeiro.setEnabled(true);
             btnAnteior.setEnabled(true);
         }else {
             
             lblVisualizados.setText(String.valueOf(offset));
         }
         System.out.println("OFFSET PROXIMO:"+offset);
         System.out.println("Limite: "+limite);
                 

    }//GEN-LAST:event_btnProxActionPerformed

    private void jSpinnerLimiteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerLimiteStateChanged
        offset = 0;
        npagina = 0;
        limite = Integer.parseInt(jSpinnerLimite.getValue().toString());
        desabilitaPaginacao();
    }//GEN-LAST:event_jSpinnerLimiteStateChanged

    private void btnListarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListarMouseEntered

    private void btnProxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProxMouseClicked

    private void btnUltimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUltimoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUltimoMouseClicked

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed

        double resto = total % Integer.parseInt(jSpinnerLimite.getValue().toString());
        if (resto > 0) {
            double resultado = total / Integer.parseInt(jSpinnerLimite.getValue().toString());
            int pagina = limite * (int) resultado;
            offset = pagina;
            npagina = 0;
            listar();
            desabilitaPaginacao();
            btnPrimeiro.setEnabled(true);
            btnAnteior.setEnabled(true);

        } else {

        }

    }//GEN-LAST:event_btnUltimoActionPerformed

    private void btnPrimeiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrimeiroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrimeiroMouseClicked

    private void btnPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeiroActionPerformed
        offset = 0;
        npagina = 0;
        listar();
        desabilitaPaginacao();
        btnProx.setEnabled(true);
        btnUltimo.setEnabled(true);


    }//GEN-LAST:event_btnPrimeiroActionPerformed

    private void jDateChooserInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserInicioPropertyChange
        try {

            Date dtInicio = jDateChooserInicio.getDate();
            Date dtFim = jDateChooserFim.getDate();

            if (dtInicio.after(dtFim)) {
                JOptionPane.showMessageDialog(this, "A Data Inicio não pode ser maio que a Data Fim!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                btnListar.setEnabled(false);
            }

        } catch (NullPointerException e) {

        }

    }//GEN-LAST:event_jDateChooserInicioPropertyChange

    private void jDateChooserInicioInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jDateChooserInicioInputMethodTextChanged


    }//GEN-LAST:event_jDateChooserInicioInputMethodTextChanged

    private void jDateChooserFimInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jDateChooserFimInputMethodTextChanged

    }//GEN-LAST:event_jDateChooserFimInputMethodTextChanged

    private void jDateChooserFimPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserFimPropertyChange
        try {

            Date dtInicio = jDateChooserInicio.getDate();
            Date dtFim = jDateChooserFim.getDate();

            if (dtFim.before(dtInicio)) {
                JOptionPane.showMessageDialog(this, "A Data Fim não pode ser menor que a Data Início!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                jDateChooserFim.setDate(new Date());
                btnListar.setEnabled(false);
            } else if (dtFim.after(new Date())) {
                JOptionPane.showMessageDialog(this, "A Data Fim não pode ser maior que a Data Atual!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                jDateChooserFim.setDate(new Date());
                btnListar.setEnabled(false);
            }

        } catch (NullPointerException e) {

        }
    }//GEN-LAST:event_jDateChooserFimPropertyChange

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
            java.util.logging.Logger.getLogger(TelaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnteior;
    private javax.swing.JLabel btnFechar;
    private javax.swing.JLabel btnImprimir;
    private javax.swing.JLabel btnListar;
    private javax.swing.JButton btnPrimeiro;
    private javax.swing.JButton btnProx;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JComboBox<String> comboUsuario;
    private com.toedter.calendar.JDateChooser jDateChooserFim;
    private com.toedter.calendar.JDateChooser jDateChooserInicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerLimite;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblOperador;
    private javax.swing.JLabel lblTotRegistro;
    private javax.swing.JLabel lblVisualizados;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JLabel lbltitulo1;
    private javax.swing.JPanel painelCentral;
    private javax.swing.JTable tblLog;
    // End of variables declaration//GEN-END:variables

    private void listar() {
        // Lista todos os logs

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if ("Selecione...".equals(comboUsuario.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Selecione um usuário!");
        } else {

            try {
                // Pega data do componente e converte para string
                String dtInicio = df.format(jDateChooserInicio.getDate().getTime());
                String dtFim = df.format(jDateChooserFim.getDate().getTime());
               
                // Total de Registros Exibidos na tela
                lc.listaLog(tblLog, dtInicio, dtFim, comboUsuario.getSelectedItem().toString(), limite, offset);
                modelLog.redimensionaColunas(tblLog);
                //Totaliza os resitros da pesquisa
               
                total = lc.totalizaLog(dtInicio, dtFim, comboUsuario.getSelectedItem().toString());
                if (total < Integer.parseInt(jSpinnerLimite.getValue().toString())){
                    visualizados = total;
                    
                }else {                
                    visualizados = total -(total - limite);
                    
                }
               // offset = offset+limite;
                lblTotRegistro.setText(String.valueOf(total));
                
               //linhaTabela = tblLog.getRowCount();

              // visualizados = visualizados + linhaTabela;
            } catch (NumberFormatException e) {
                System.out.println("br.com.bar.view.TelaLog.btnListarMouseClicked()" + e);

            }
        }

    }

    private void desabilitaPaginacao() {
        btnAnteior.setEnabled(false);
        btnProx.setEnabled(false);
        btnUltimo.setEnabled(false);
        btnPrimeiro.setEnabled(false);

    }

    private void habilitaPaginacao() {

        btnProx.setEnabled(true);
        btnUltimo.setEnabled(true);

    }
}
