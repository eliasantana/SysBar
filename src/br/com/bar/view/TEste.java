/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.util.Util;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import org.apache.hadoop.hive.ql.parse.HiveParser;
import org.apache.hadoop.util.Daemon;

/**
 *
 * @author Elias Santana
 */
public class TEste extends javax.swing.JFrame {

    /**
     * Creates new form TEste
     */
    // Limite de espera
    int limite = 30;
    // Contagem do tempo em segundos
    int s = 0;

    public TEste() {
        initComponents();

        limitaDataCombo(jdateExemplo, 1, 1);
        cronometro();
        

    }

    private void cronometro() {
        long segundos = 1000;
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Realiza uma contagem a incremento de 1 acadas segundo.

                s = s + 1;
                lblCronometro.setText(String.valueOf(s));
                // Verifica se a contagem atingiu o limite informado para bloqueio da tela.
                if (s == limite) {
                    TelaBloqueio tb = new TelaBloqueio();
                    tb.setVisible(true);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, segundos);
    }

    /**
     * Este método limita a data de um componente JDateSchooser estipulando, um
     * limite MÍNIMO e MÁXIMO para o componente.
     *
     * @param dtChooser Componente JDateSchooser a ser limitado.
     * @param limiteMeses Quantidade de Meses a serem abilitado anterior ao mês
     * atual.
     *
     */
    private void limitaDataCombo(JDateChooser dtChooser, int limiteMeses) {

        //Atribui ao componente JDateSchooser uma data mínima selecionável.
        Calendar cMax = Calendar.getInstance();
        cMax.set(Calendar.YEAR, cMax.get(Calendar.YEAR)); // Ano máximo Selecionável
        cMax.set(Calendar.MONTH, cMax.get(Calendar.MONTH)); // MÊs máximo Selecionável
        cMax.set(Calendar.DATE, cMax.get(Calendar.DATE));
        dtChooser.setMaxSelectableDate(cMax.getTime());

        //Atribui ao componente JDateSchooser uma data mínima selecionável.
        Calendar cMin = Calendar.getInstance();
        cMin.set(Calendar.YEAR, cMin.get(Calendar.YEAR)); // Ano Atual
        cMin.set(Calendar.MONTH, cMin.get(Calendar.MONTH) - limiteMeses); // Meses Selecionável

        // Se o limite de meses informado for iagual a 0 'zero' será setada a data atual
        // como limite mínimo selecionável. Caso seja diferente liberará do dia 1 ao 
        // último dia do mês.
        if (limiteMeses == 0) {
            cMin.set(Calendar.DATE, cMin.get(Calendar.DATE));// Data Atual
        } else {
            cMin.set(Calendar.DATE, cMin.get(Calendar.DATE));// Data Atual            
        }
        //Seta a data Mínima no JdateChooser.
        dtChooser.setMinSelectableDate(cMin.getTime());

    }

    /**
     * Este método limita a data de um componente JDateSchooser estipulando, um
     * limite MÍNIMO e MÁXIMO para o componente.
     *
     * @param dtChooser Componente JDateSchooser a ser limitado.
     * @param limiteMeses Quantidade de Meses a serem abilitado anterior ao mês
     * atual.
     * @param dia Dia a partir de onde se pode selecionar no componente.
     */
    private void limitaDataCombo(JDateChooser dtChooser, int limiteMeses, int dia) {

        //Atribui ao componente JDateSchooser uma data mínima selecionável.
        Calendar cMax = Calendar.getInstance();
        cMax.set(Calendar.YEAR, cMax.get(Calendar.YEAR)); // Ano máximo Selecionável
        cMax.set(Calendar.MONTH, cMax.get(Calendar.MONTH)); // MÊs máximo Selecionável
        cMax.set(Calendar.DATE, cMax.get(Calendar.DATE));
        dtChooser.setMaxSelectableDate(cMax.getTime());

        //Atribui ao componente JDateSchooser uma data mínima selecionável.
        Calendar cMin = Calendar.getInstance();
        cMin.set(Calendar.YEAR, cMin.get(Calendar.YEAR)); // Ano Atual
        cMin.set(Calendar.MONTH, cMin.get(Calendar.MONTH) - limiteMeses); // Meses Selecionável

        // Se o limite de meses informado for iagual a 0 'zero' será setada a data atual
        // como limite mínimo selecionável. Caso seja diferente liberará do dia 1 ao 
        // último dia do mês.
        if (limiteMeses == 0) {
            cMin.set(Calendar.DATE, cMin.get(Calendar.DATE));// Data Atual
        } else {
            cMin.set(Calendar.DATE, dia);// Data Atual            
        }
        //Seta a data Mínima no JdateChooser.
        dtChooser.setMinSelectableDate(cMin.getTime());

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
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jdateIni = new com.toedter.calendar.JDateChooser();
        jdateFim = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jdateExemplo = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        btnCriaArquivo = new javax.swing.JButton();
        btnCriaArquivo1 = new javax.swing.JButton();
        lblCronometro = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();
        btnCriaArquivo2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("jRadioButton1");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("jRadioButton1");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jdateExemplo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jdateExemploFocusGained(evt);
            }
        });

        jButton3.setText("Horas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnCriaArquivo.setText("Cria arquivo");
        btnCriaArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriaArquivoActionPerformed(evt);
            }
        });

        btnCriaArquivo1.setText("Cria arquivo");
        btnCriaArquivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriaArquivo1ActionPerformed(evt);
            }
        });

        lblCronometro.setText("jLabel1");

        lblResultado.setText("jLabel1");

        btnCriaArquivo2.setText("Cria arquivo");
        btnCriaArquivo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriaArquivo2ActionPerformed(evt);
            }
        });

        jButton4.setText("Listar itens HashMap");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(44, 44, 44)
                                .addComponent(jRadioButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdateIni, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton3)
                                            .addComponent(jButton1))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdateFim, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCriaArquivo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCriaArquivo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCriaArquivo2)))
                        .addGap(103, 103, 103)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdateExemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblResultado)
                            .addComponent(lblCronometro)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jButton4)))
                .addGap(197, 197, 197))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jdateExemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdateIni, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdateFim, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCronometro))
                .addGap(18, 18, 18)
                .addComponent(lblResultado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCriaArquivo)
                            .addComponent(jButton4))
                        .addGap(28, 28, 28)
                        .addComponent(btnCriaArquivo1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(btnCriaArquivo2)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Date dtInicio = jdateIni.getDate();
        Date dtFim = jdateFim.getDate();
        Calendar cInicio = Calendar.getInstance();
        cInicio.setTime(dtInicio);
        Calendar cFim = Calendar.getInstance();
        cFim.setTime(dtFim);
        int dias = (cFim.get(Calendar.DAY_OF_YEAR) - cInicio.get(Calendar.DAY_OF_YEAR));
        System.out.println("Dias: " + dias);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int dias = 30;

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, dias);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Data: " + df.format(c.getTime()));

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minutos = c.get(Calendar.MINUTE);
        int ano = c.get(Calendar.YEAR);
        System.out.println("Hora do Dia: " + hora);
        System.out.println("Minutos: " + minutos);
        System.out.println("Ano: " + ano);


    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCriaArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriaArquivoActionPerformed
        String caminho = "C:\\SysBar\\teste.txt";
        File f = new File(caminho);
        try {
            FileWriter fw = new FileWriter(f);
            try (PrintWriter pw = new PrintWriter(fw)) {
                Date dtAtual = new Date();
                Util u = new Util();

                pw.print(u.formataDataBanco(dtAtual));
                pw.flush();
                System.out.println("Arquivo criado com sucesso!");
            }

        } catch (IOException ex) {
            Logger.getLogger(TEste.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnCriaArquivoActionPerformed

    private void btnCriaArquivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriaArquivo1ActionPerformed
        String caminho = "C:\\SysBar\\teste.txt";
        File f = new File(caminho);
        String dados = null;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            try {
                dados = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(TEste.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TEste.class.getName()).log(Level.SEVERE, null, ex);
        }
        dados = "2019-04-22";

        System.out.println("Dados: " + dados);
        Date dt = new Date();
        Util u = new Util();
        String dataHoje = u.formataDataBanco(dt);
        if (dataHoje.equals(dados)) {
            System.out.println("O arquivo é de hoje - Programa em Execução");
        } else {
            System.out.println("Arquivo  com data diferente: Excluir arquivo");

        }
    }//GEN-LAST:event_btnCriaArquivo1ActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        s = 0;
        lblResultado.setText("Desbloqueado!");
    }//GEN-LAST:event_formMouseMoved

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        s = 0;
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        s = 0;
    }//GEN-LAST:event_formKeyReleased

    private void jdateExemploFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdateExemploFocusGained
    
    }//GEN-LAST:event_jdateExemploFocusGained

    private void btnCriaArquivo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriaArquivo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCriaArquivo2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        HashMap<String,String>itens = new HashMap<>();
        itens.put("numero_item", "1");
        itens.put("unidade_comercial", "PC");
        itens.put("unidade_tributavel", "PC");
        itens.put("codigo_ncm", "94019090");
        itens.put("codigo_produto", "Div.13350000");
        itens.put("descricao", "NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        itens.put("cfop", "5102");
        itens.put("valor_unitario_comercial", "1.0000000000");
        itens.put("valor_unitario_tributavel", "1.0000000000");
        itens.put("valor_bruto", "1.0000");
        itens.put("quantidade_comercial", "1.0000");
        itens.put("quantidade_tributavel", "1.0000");
        itens.put("quantidade", "1.0000");
        itens.put("icms_origem", "0");
        itens.put("icms_base_calculo", "1.00");
        itens.put("icms_modalidade_base_calculo", "3");
        itens.put("valor_frete", "0.0");
        itens.put("valor_outras_despesas", "0.0");
        itens.put("icms_situacao_tributaria", "102");
        
        System.out.println(Calendar.getInstance().getTime());
            
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(TEste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TEste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TEste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TEste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TEste().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriaArquivo;
    private javax.swing.JButton btnCriaArquivo1;
    private javax.swing.JButton btnCriaArquivo2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private com.toedter.calendar.JDateChooser jdateExemplo;
    private com.toedter.calendar.JDateChooser jdateFim;
    private com.toedter.calendar.JDateChooser jdateIni;
    private javax.swing.JLabel lblCronometro;
    private javax.swing.JLabel lblResultado;
    // End of variables declaration//GEN-END:variables
}
