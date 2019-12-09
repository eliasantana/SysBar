/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.ReportUtil;
import br.com.bar.model.DadosEmpresa;
import br.com.bar.model.Nfce;
import br.com.bar.model.TableModelReimpressao;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerNFCe;
import br.com.br.controler.ControlerPedido;
import com.google.zxing.WriterException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Elias Santana
 */
public class TelaReImpressao extends JDialog {

    ControlerPedido cp = new ControlerPedido();
    ReportUtil rsp = new ReportUtil();
    Util u = new Util();
    ControlerDadosEmpresa de = new ControlerDadosEmpresa();
    TableModelReimpressao modelReimpressao = new TableModelReimpressao();

    public TelaReImpressao() {
        initComponents();
        lblImprimir.setEnabled(false);
        lblPesquisar.setEnabled(true);
        modelReimpressao.redimensionaColunas(tblPedidos);

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        lblPesquisar = new javax.swing.JLabel();
        lblImprimir = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumeroMesa = new javax.swing.JFormattedTextField();
        checkDiaAnterior = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(38, 53, 61));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Reimpressão de Cupom");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(2, 0, 680, 34);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        tblPedidos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MESA", "PEDIDO", "TX. SERVIÇO R$", "DESCONTO R$", "TOTAL R$", "GARÇOM"
            }
        ));
        tblPedidos.setRowHeight(25);
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedidos);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 660, 150);

        lblPesquisar.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        lblPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/lopa32x32.png"))); // NOI18N
        lblPesquisar.setText("Pesquisar");
        lblPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPesquisarMouseClicked(evt);
            }
        });
        jPanel1.add(lblPesquisar);
        lblPesquisar.setBounds(90, 70, 109, 28);

        lblImprimir.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        lblImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/Impressora.png"))); // NOI18N
        lblImprimir.setText("Reimprimir");
        lblImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImprimirMouseClicked(evt);
            }
        });
        jPanel1.add(lblImprimir);
        lblImprimir.setBounds(550, 70, 122, 29);

        jLabel3.setText("Informe o número da Mesa");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(12, 44, 160, 20);

        txtNumeroMesa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtNumeroMesa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNumeroMesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroMesaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroMesaKeyReleased(evt);
            }
        });
        jPanel1.add(txtNumeroMesa);
        txtNumeroMesa.setBounds(10, 70, 70, 30);

        checkDiaAnterior.setText("Dia anterior");
        checkDiaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDiaAnteriorActionPerformed(evt);
            }
        });
        jPanel1.add(checkDiaAnterior);
        checkDiaAnterior.setBounds(441, 70, 100, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(1, 0, 680, 270);

        setSize(new java.awt.Dimension(681, 271));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void lblPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPesquisarMouseClicked
        // Lista todos os pedidos fechados na data atual.
        listar();

    }//GEN-LAST:event_lblPesquisarMouseClicked

    private void lblImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImprimirMouseClicked
        // Realiza a reimpressão do cupom selecionado.
        if (lblImprimir.isEnabled()) {
            Nfce nota = new Nfce();
            int linha = tblPedidos.getSelectedRow();

            String nMesa = tblPedidos.getModel().getValueAt(linha, 0).toString();
            String idPedido = tblPedidos.getModel().getValueAt(linha, 1).toString();
            //double total = Double.parseDouble(tblPedidos.getModel().getValueAt(linha, 4).toString().replaceAll(",", "."));
            String totalCompra = tblPedidos.getModel().getValueAt(linha, 4).toString().replace(".", "");
            totalCompra = totalCompra.replace(",", ".");
            double total = Double.parseDouble(totalCompra);

            double desc = Double.parseDouble(tblPedidos.getModel().getValueAt(linha, 3).toString().replaceAll(",", "."));
            double tx = (Double.parseDouble(tblPedidos.getModel().getValueAt(linha, 2).toString().replaceAll(",", ".")));

            ControlerNFCe controlerNFCe = new ControlerNFCe();
            controlerNFCe.consultarNFCE(idPedido, "consulta.json"); // Realiza a consulta e gera o arquivo de retorno

            try {
                // Ler o arquivo de retorno e devolve um obj do tipo NFCe com os dados lidos
                nota = controlerNFCe.lerRetorno("consulta.json");
            } catch (ParseException | IOException ex) {
                Logger.getLogger(TelaReImpressao.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                // Gera QRcod
                if (nota.getQrcode_url() != null) {

                    u.generateQRCodeImage(nota.getQrcode_url(), 120, 120, "C:/Sysbar/qr.jpg");
                }
            } catch (WriterException | IOException ex) {
                Logger.getLogger(TelaReImpressao.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Imprime cupom de pagamento
            HashMap dados = new HashMap();
            Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            if (checkDiaAnterior.isSelected()) {
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, -1);
                dt.setTime(c.getTime().getTime());
            }
            dados.put("data", df.format(dt));
            dados.put("garcom", tblPedidos.getModel().getValueAt(linha, 5).toString());
            dados.put("titulo", "COMPROVANTE DE PAGAMENTO");
            dados.put("tx", tx);
            dados.put("id_pedido", idPedido);

            dados.put("npessoas", 1); // Não tenho
            dados.put("total_pessoas", total); // Não tenho
            DadosEmpresa dadosEmpresa = de.selecionaDados();
            dados.put("mesa", nMesa);
            dados.put("nome_empresa", dadosEmpresa.getNome_empresa());
            dados.put("end", dadosEmpresa.getEndereco() + ", " + dadosEmpresa.getNumero() + ", " + dadosEmpresa.getBairro() + " - " + dadosEmpresa.getCep());
            dados.put("end2", dadosEmpresa.getCidade() + " - " + dadosEmpresa.getUf() + " - " + dadosEmpresa.getTelefone());
            dados.put("cnpj", dadosEmpresa.getCnpj());
            dados.put("desc", desc);
            dados.put("forma_pag", cp.retornaFormaPagto(idPedido));
            // Adiciona os dados fiscais lidos no arquivo de retorno de autorizacao.
            dados.put("chave_nfe", nota.getChave_nfe());
            dados.put("url_consulta_nf", nota.getUrl_consulta_nf());
            dados.put("serie", nota.getSerie());
            dados.put("numero", nota.getNumero());
            // Retorna Dados do pagamento 
            try {
                ArrayList<Double> lista = cp.retornaVlrPagamentoMisto(idPedido);

                dados.put("dinheiro", lista.get(0));
                dados.put("credito", lista.get(1));
                dados.put("debito", lista.get(2));
                dados.put("voucher", lista.get(3));

            } catch (IndexOutOfBoundsException e) {
                // Caso o pedido seja diferente de Misto retorna zero para todos os valores.

                dados.put("dinheiro", 0.00);
                dados.put("credito", 0.00);
                dados.put("debito", 0.00);
                dados.put("voucher", 0.00);
            }

            try {
                if (dadosEmpresa.getImprimir_na_tela() == 0) {
                    //rsp.imprimeRelatorioTela("cupom2.jasper", dados);
                    rsp.imprimeRelatorioTela("cupom2_7.jasper", dados, "Comprovante de Pagamento");
                } else {
                    //rsp.impressaoDireta("cupom2.jasper", dados);
                    rsp.impressaoDireta("cupom2_7.jasper", dados);
                }
            } catch (JRException ex) {
                System.out.println("br.com.bar.view.TelaReImpressao.lblImprimirMouseClicked()" + ex);
            }

            lblImprimir.setEnabled(false);
            txtNumeroMesa.setText(null);
            //tblPedidos.setModel(DbUtils.resultSetToTableModel(cp.listaPedidosReimpressao(txtNumeroMesa.getText())));
            modelReimpressao.redimensionaColunas(tblPedidos);
        }

    }//GEN-LAST:event_lblImprimirMouseClicked

    private void txtNumeroMesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroMesaKeyPressed
        lblPesquisar.setEnabled(true);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            listar();
        }
    }//GEN-LAST:event_txtNumeroMesaKeyPressed

    private void txtNumeroMesaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroMesaKeyReleased

    }//GEN-LAST:event_txtNumeroMesaKeyReleased

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        lblImprimir.setEnabled(true);
    }//GEN-LAST:event_tblPedidosMouseClicked

    private void checkDiaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDiaAnteriorActionPerformed
        if (checkDiaAnterior.isSelected()) {
            tblPedidos.setModel(modelReimpressao);
            modelReimpressao.redimensionaColunas(tblPedidos);
        } else {
             listar();
        }

    }//GEN-LAST:event_checkDiaAnteriorActionPerformed

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
            java.util.logging.Logger.getLogger(TelaReImpressao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaReImpressao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaReImpressao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaReImpressao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaReImpressao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkDiaAnterior;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImprimir;
    private javax.swing.JLabel lblPesquisar;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JFormattedTextField txtNumeroMesa;
    // End of variables declaration//GEN-END:variables

    private void listar() {
        // Captura a data atual
        Calendar c = Calendar.getInstance();
        // Se o CheckBox "Dia anterior" estiver marcado retorna a data um dia.
        if (checkDiaAnterior.isSelected()) {
            c.add(Calendar.DATE, -1);
        }
        // Formada a data no Formato 'yyyy-MM-dd' (Mysql).
        String data = u.formataDataBanco(c.getTime());

        ResultSet rs = cp.listaPedidosReimpressao(txtNumeroMesa.getText(), data);
        tblPedidos.setModel(DbUtils.resultSetToTableModel(rs));
        modelReimpressao.redimensionaColunas(tblPedidos);

        try {
            if (rs.isFirst()) {

            } else {
                lblImprimir.setEnabled(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaReImpressao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
