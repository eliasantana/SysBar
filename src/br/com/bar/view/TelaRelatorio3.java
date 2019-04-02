/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.dao.ReportUtil;
import br.com.bar.model.DadosEmpresa;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerContasApagar;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerGrupo;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Elias Santana
 */
public class TelaRelatorio3 extends javax.swing.JFrame {

    /**
     * Creates new form TelaRelatorio3
     */
    ArrayList<String> listaRelProduto = new ArrayList<>();
    ArrayList<String> listaRelFinanceiro = new ArrayList<>();
    ArrayList<String> listaRelFuncionario = new ArrayList<>();
    ArrayList<String> listaRelMesas = new ArrayList<>();

    ReportUtil rpu = new ReportUtil();
    ControlerContasApagar contasApagar = new ControlerContasApagar();
    ControlerDadosEmpresa ce = new ControlerDadosEmpresa();
    ControlerGrupo g = new ControlerGrupo();

    Util u = new Util();
    Log l = new Log();
    DadosEmpresa dados = ce.selecionaDados();
    int range;

    public TelaRelatorio3() {
        initComponents();
        btnImprimir.setEnabled(false);
        Calendar c = Calendar.getInstance();

        dtInicio.setVisible(false);
        dtInicio.setDate(new Date());
        dtFim.setVisible(false);
        dtFim.setDate(new Date());
        lblDe.setVisible(false);
        lblAte.setVisible(false);

        // Produto      
        listaRelProduto.add("Selecione...");
        listaRelProduto.add("Em Estoque - Por Grupo(s)");
        listaRelProduto.add("Posição do Estoque - Compras");
        //listaRelProduto.add("Movimentação"); // Falta

        // Financeiro
        listaRelFinanceiro.add("Selecione...");
        listaRelFinanceiro.add("Caixa Sintético"); // Limite para pesquisa: 6 Meses
        listaRelFinanceiro.add("Entradas de Caixa"); // Limite para pesquisa: 1 Mês
        listaRelFinanceiro.add("Saídas de Caixa"); //  Limite para pesquisa: 3 meses 
        listaRelFinanceiro.add("Comissão (Sintético)"); // Limite para pesquisa: 3 meses 
        listaRelFinanceiro.add("Comissão (Analítico)"); //Limite para pesquisa: 1 mes    

        lblCargo.setVisible(false);
        lblOperaodr.setVisible(false);

        jcomboGrupo.setVisible(false);
    }

    public void listaRelatorios(ArrayList<String> lista) {
        comboRelatorio.removeAllItems();
        for (int i = 0; i < lista.size(); i++) {
            comboRelatorio.addItem(lista.get(i));
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblOperaodr = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        radioProduto = new javax.swing.JRadioButton();
        radioFinanceiro = new javax.swing.JRadioButton();
        comboRelatorio = new javax.swing.JComboBox<>();
        dtInicio = new com.toedter.calendar.JDateChooser();
        dtFim = new com.toedter.calendar.JDateChooser();
        btnImprimir = new javax.swing.JButton();
        jcomboGrupo = new javax.swing.JComboBox<>();
        lblDe = new javax.swing.JLabel();
        lblAte = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setForeground(new java.awt.Color(102, 102, 102));
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(243, 156, 18));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(38, 53, 91));

        jLabel2.setFont(new java.awt.Font("Yu Gothic Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 0, -1, -1));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Relatórios");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 20, 200, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cadastro-64.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 20, -1, -1));

        lblOperaodr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblOperaodr.setText("Operador");
        jPanel2.add(lblOperaodr, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("Cargo");
        jPanel2.add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(radioProduto);
        radioProduto.setText("Produtos");
        radioProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                radioProdutoFocusLost(evt);
            }
        });
        radioProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioProdutoMouseClicked(evt);
            }
        });
        radioProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioProdutoActionPerformed(evt);
            }
        });
        jPanel3.add(radioProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        buttonGroup1.add(radioFinanceiro);
        radioFinanceiro.setText("Financeiro");
        radioFinanceiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioFinanceiroMouseClicked(evt);
            }
        });
        jPanel3.add(radioFinanceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        comboRelatorio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboRelatorioItemStateChanged(evt);
            }
        });
        comboRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRelatorioActionPerformed(evt);
            }
        });
        jPanel3.add(comboRelatorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 291, 33));

        dtInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtInicioPropertyChange(evt);
            }
        });
        jPanel3.add(dtInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, 31));

        dtFim.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                dtFimInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        dtFim.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtFimPropertyChange(evt);
            }
        });
        jPanel3.add(dtFim, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 141, 31));

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Impressora.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel3.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jcomboGrupo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcomboGrupoItemStateChanged(evt);
            }
        });
        jcomboGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboGrupoActionPerformed(evt);
            }
        });
        jPanel3.add(jcomboGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, 30));

        lblDe.setText("De");
        jPanel3.add(lblDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 30, -1));

        lblAte.setText("Até");
        jPanel3.add(lblAte, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 50, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(342, 330));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // Fecha Janela
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void radioProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioProdutoMouseClicked
        // TODO add your handling code here:
        if (radioProduto.isSelected()) {
            listaRelatorios(listaRelProduto);
            desabilitaData();
        }
    }//GEN-LAST:event_radioProdutoMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // Abre relatório na tela
        String combo = comboRelatorio.getSelectedItem().toString();

        switch (combo) {

            case "Em Estoque - Por Grupo(s)":
                if ("Selecione...".equals(jcomboGrupo.getSelectedItem().toString())) {

                    try {
                        rpu.imprimeRelatorioTela("relProdutos.jasper", rodape(dados));
                    } catch (JRException e) {
                        System.out.println("br.com.bar.view.TelaRelatorio3.ImprimirActionPerformed() - Produto em Estoque" + e);
                    }
                } else {
                    try {

                        HashMap map = new HashMap();
                        map.put("grupo", jcomboGrupo.getSelectedItem().toString());
                        rpu.imprimeRelatorioTela("relProdutos_x_grupo.jasper", rodape(dados, map));

                    } catch (JRException e) {
                        System.out.println("br.com.bar.view.TelaRelatorio3.ImprimirActionPerformed() - Produto em Estoque" + e);
                    }
                }
                break;

            case "Posição do Estoque - Compras":
                try {
                    rpu.imprimeRelatorioTela("relPosicaoEstoque.jasper", rodape(dados));
                } catch (JRException e) {
                    System.out.println("br.com.bar.view.TelaRelatorio3.ImprimirActionPerformed() Posição do Estoque" + e);
                }
                break;

            case "Comissão (Sintético)":
                //RANGE - 
                try {
                    HashMap map = new HashMap();
                    String dataInicio = u.formataDataBanco(dtInicio.getDate());
                    String dataFim = u.formataDataBanco(dtFim.getDate());

                    map.put("dtInicio", dataInicio);
                    map.put("dtFim", dataFim);

                    rpu.imprimeRelatorioTela("relSintComissao.jasper", rodape(dados, map));

                } catch (JRException e) {
                    System.out.println("br.com.bar.view.TelaRelatorio3.ImprimirActionPerformed() - Sintético" + e);
                }
                break;

            case "Comissão (Analítico)":
                try {
                    HashMap map = new HashMap();
                    String dataInicio = u.formataDataBanco(dtInicio.getDate());
                    String dataFim = u.formataDataBanco(dtFim.getDate());

                    map.put("dataInicio", dataInicio);
                    map.put("dataFim", dataFim);

                    rpu.imprimeRelatorioTela("comissaoGeral.jasper", rodape(dados, map));

                } catch (JRException e) {
                    System.out.println("br.com.bar.view.TelaRelatorio3.ImprimirActionPerformed() - Comissão Análítico" + e);
                }
                break;

            case "Saídas de Caixa":
                try {
                    HashMap map = new HashMap();
                    String dataInicio = u.formataDataBanco(dtInicio.getDate());
                    String dataFim = u.formataDataBanco(dtFim.getDate());

                    map.put("dt_inicio", dataInicio);
                    map.put("dt_fim", dataFim);

                    rpu.imprimeRelatorioTela("Saidas.jasper", rodape(dados, map));

                } catch (JRException e) {
                    System.out.println("br.com.bar.view.TelaRelatorio3.ImprimirActionPerformed() - Saída de Caixa" + e);
                }
                break;

            case "Entradas de Caixa":
                try {
                    HashMap map = new HashMap();
                    String dataInicio = u.formataDataBanco(dtInicio.getDate());
                    String dataFim = u.formataDataBanco(dtFim.getDate());

                    map.put("inicio", dataInicio);
                    map.put("fim", dataFim);

                    rpu.imprimeRelatorioTela("relCaixa.jasper", rodape(dados, map));

                } catch (JRException e) {
                    System.out.println("br.com.bar.view.TelaRelatorio3.ImprimirActionPerformed() - Entrada de Caixa" + e);
                }
                break;
            case "Caixa Sintético":
                try {
                    HashMap map = new HashMap();
                    String dataInicio = u.formataDataBanco(dtInicio.getDate());
                    String dataFim = u.formataDataBanco(dtFim.getDate());

                    map.put("inicio", dataInicio);
                    map.put("fim", dataFim);

                    rpu.imprimeRelatorioTela("caixaSintetico.jasper", rodape(dados, map));

                } catch (JRException e) {
                    System.out.println("br.com.bar.view.TelaRelatorio3.ImprimirActionPerformed() - Caixa Sintético" + e);
                }
                break;

            case "Funcionários Geral":
                try {

                    rpu.imprimeRelatorioTela("relGeralDeFuncionarios.jasper", rodape(dados));

                } catch (JRException e) {
                    System.out.println("br.com.bar.view.TelaRelatorio3.ImprimirActionPerformed() - Entrada de Caixa" + e);
                }
                break;

        }

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void radioFinanceiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioFinanceiroMouseClicked
        if (radioFinanceiro.isSelected()) {
            listaRelatorios(listaRelFinanceiro);

        }
    }//GEN-LAST:event_radioFinanceiroMouseClicked

    private void comboRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRelatorioActionPerformed
        try {

            String opcao = comboRelatorio.getSelectedItem().toString();

            if ("Posição do Estoque - Compras".equals(opcao)) {
                jcomboGrupo.setVisible(false);
            } else {
                if (!"Selecione...".equals(opcao)) {

                    btnImprimir.setEnabled(true);
                }else {
                     btnImprimir.setEnabled(false);
                }
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_comboRelatorioActionPerformed

    private void comboRelatorioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboRelatorioItemStateChanged
        try {

            String opcao = comboRelatorio.getSelectedItem().toString();

            if (null == opcao) {
                desabilitaData();
            } else {
                switch (opcao) {

                    case "Comissão (Sintético)":
                        habilitaData();
                        // RANGE - 3 Meses
                        u.limitaDataCombo(dtFim,0);
                        u.limitaDataCombo(dtInicio,3,1);
                        break;
                    case "Contas Vecidas":
                        habilitaData();
                        break;
                    case "Comissão (Analítico)":
                        habilitaData();
                        // RANGE - 1 Meses
                        u.limitaDataCombo(dtFim,0);
                        u.limitaDataCombo(dtInicio,1,1);
                        break;
                    case "Saídas de Caixa":
                        habilitaData();
                        // RANGE - 3 Meses
                        u.limitaDataCombo(dtFim,0);
                        u.limitaDataCombo(dtInicio,3,1);
                        break;
                    case "Entradas de Caixa":
                        habilitaData();
                        // RANGE - 1 Meses
                        u.limitaDataCombo(dtFim,0);
                        u.limitaDataCombo(dtInicio,1,1);
                        break;
                    case "Caixa Sintético":
                        habilitaData();
                        // RANGE - 1 Meses
                        u.limitaDataCombo(dtFim,0);
                        u.limitaDataCombo(dtInicio,1,1);
                        break;
                    case "Em Estoque - Por Grupo(s)":
                        jcomboGrupo.setVisible(true);
                        g.carregaComboGrupoProduto(jcomboGrupo);
                        break;

                    default:
                        desabilitaData();
                        break;
                }
            }

        } catch (NullPointerException e) {
            System.out.println("br.com.bar.view.TelaRelatorio3.comboReltorioItemStateChanged()");
        }
    }//GEN-LAST:event_comboRelatorioItemStateChanged

    private void radioProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radioProdutoFocusLost

    }//GEN-LAST:event_radioProdutoFocusLost

    private void radioProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioProdutoActionPerformed

    private void jcomboGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboGrupoActionPerformed

    }//GEN-LAST:event_jcomboGrupoActionPerformed

    private void jcomboGrupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcomboGrupoItemStateChanged

    }//GEN-LAST:event_jcomboGrupoItemStateChanged

    private void dtFimPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFimPropertyChange
        

    }//GEN-LAST:event_dtFimPropertyChange

    private void dtInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtInicioPropertyChange
        

    }//GEN-LAST:event_dtInicioPropertyChange

    private void dtFimInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dtFimInputMethodTextChanged

    }//GEN-LAST:event_dtFimInputMethodTextChanged
    public void recebeOperador(String operador, String perfil) {
        lblOperaodr.setText(operador);
        lblCargo.setText(perfil);

    }

    public void habilitaData() {
        dtInicio.setVisible(true);
        dtFim.setVisible(true);
        lblDe.setVisible(true);
        lblAte.setVisible(true);
    }

    public void desabilitaData() {
        dtInicio.setVisible(false);
        dtFim.setVisible(false);
        lblAte.setVisible(false);
        lblDe.setVisible(false);
    }

    private HashMap rodape(DadosEmpresa d) {
        HashMap map = new HashMap();
        map.put("end1", dados.getNome_empresa() + " - " + " Endereço: " + dados.getEndereco() + "," + dados.getNumero() + " - " + "Bairro: " + dados.getBairro() + " - " + " Cidade: " + dados.getCidade());
        map.put("end2", "CEP: " + dados.getCep() + " - " + "UF :" + dados.getUf() + " Telefone: " + dados.getTelefone() + " email-" + dados.getEmail());
        map.put("cnpj", "C.N.P.J " + dados.getCnpj());
        map.put("logo", d.getLogo());

        return map;
    }

    private HashMap rodape(DadosEmpresa d, HashMap map) {

        map.put("end1", dados.getNome_empresa() + " - " + " Endereço: " + dados.getEndereco() + "," + dados.getNumero() + " - " + "Bairro: " + dados.getBairro() + " - " + " Cidade: " + dados.getCidade());
        map.put("end2", "CEP: " + dados.getCep() + " - " + "UF :" + dados.getUf() + " Telefone: " + dados.getTelefone() + " email-" + dados.getEmail());
        map.put("cnpj", "C.N.P.J " + dados.getCnpj());
        map.put("logo", d.getLogo());

        return map;
    }

    private int totalizaDias(Date dtIni, Date dtFim) {
        int dias;
        Calendar cIni = Calendar.getInstance();
        Calendar cFim = Calendar.getInstance();
        cIni.setTime(dtIni);
        cFim.setTime(dtFim);

        dias = (cFim.get(Calendar.DAY_OF_YEAR) - cIni.get(Calendar.DAY_OF_YEAR));
        return dias;
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
            java.util.logging.Logger.getLogger(TelaRelatorio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRelatorio3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboRelatorio;
    private com.toedter.calendar.JDateChooser dtFim;
    private com.toedter.calendar.JDateChooser dtInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox<String> jcomboGrupo;
    private javax.swing.JLabel lblAte;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblDe;
    private javax.swing.JLabel lblOperaodr;
    private javax.swing.JRadioButton radioFinanceiro;
    private javax.swing.JRadioButton radioProduto;
    // End of variables declaration//GEN-END:variables
}
