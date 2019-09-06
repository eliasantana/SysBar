/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.util.Util;
import br.com.br.controler.ControlerProduto;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author elias
 */
public class TelaEstoque extends javax.swing.JFrame {

    /**
     * Creates new form TelaEstoque
     */
    ControlerProduto cp = new ControlerProduto();
    Util u = new Util();
    // Instancia e armazena o objeto tela principal.
    TelaPrincipal principal;
    
    public TelaEstoque() {
        initComponents();
        // Pega aa Data Atual
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        lblCargo.setVisible(false);
        lblNomeOperador.setVisible(false);
        this.setAlwaysOnTop(true);
    }

    public void recebeOperador(TelaPrincipal tela, String nomeOperador, String cargo) {
        lblCargo.setText(cargo);
        lblNomeOperador.setText(nomeOperador);
        this.principal=tela;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        borda = new javax.swing.JPanel();
        btnProduto1 = new javax.swing.JPanel();
        lblGerenciarEstoque = new javax.swing.JLabel();
        lblFornecedores5 = new javax.swing.JLabel();
        btnGraficoRanking = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnPrecos = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnLog = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnRAnking = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnCaixa = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        painelEsquerdo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        lblNomeOperador = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        borda.setBackground(new java.awt.Color(204, 204, 204));
        borda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        borda.setForeground(new java.awt.Color(153, 153, 153));
        borda.setLayout(null);

        btnProduto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnProduto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProduto1MouseClicked(evt);
            }
        });
        btnProduto1.setLayout(null);

        lblGerenciarEstoque.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblGerenciarEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/estoque.png"))); // NOI18N
        lblGerenciarEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGerenciarEstoqueMouseClicked(evt);
            }
        });
        lblGerenciarEstoque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblGerenciarEstoqueKeyPressed(evt);
            }
        });
        btnProduto1.add(lblGerenciarEstoque);
        lblGerenciarEstoque.setBounds(11, 1, 180, 96);

        lblFornecedores5.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblFornecedores5.setText("Estoque");
        lblFornecedores5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFornecedores5MouseClicked(evt);
            }
        });
        btnProduto1.add(lblFornecedores5);
        lblFornecedores5.setBounds(90, 40, 90, 20);

        borda.add(btnProduto1);
        btnProduto1.setBounds(20, 120, 190, 100);

        btnGraficoRanking.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnGraficoRanking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGraficoRankingMouseClicked(evt);
            }
        });
        btnGraficoRanking.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/btngrafico2-48.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        btnGraficoRanking.add(jLabel12);
        jLabel12.setBounds(9, 4, 180, 90);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel3.setText("Produtos");
        btnGraficoRanking.add(jLabel3);
        jLabel3.setBounds(80, 30, 81, 20);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel4.setText("mais Vendidos");
        btnGraficoRanking.add(jLabel4);
        jLabel4.setBounds(80, 50, 100, 20);

        borda.add(btnGraficoRanking);
        btnGraficoRanking.setBounds(20, 230, 190, 100);

        btnPrecos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnPrecos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrecosMouseClicked(evt);
            }
        });
        btnPrecos.setLayout(null);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/money.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        btnPrecos.add(jLabel15);
        jLabel15.setBounds(10, 0, 180, 100);

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(52, 73, 94));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Preços");
        btnPrecos.add(jLabel20);
        jLabel20.setBounds(90, 30, 90, 30);

        borda.add(btnPrecos);
        btnPrecos.setBounds(220, 120, 190, 100);

        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        borda.add(jLabel13);
        jLabel13.setBounds(450, 400, 190, 100);

        btnLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogMouseClicked(evt);
            }
        });
        btnLog.setLayout(null);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/log64x64.png"))); // NOI18N
        btnLog.add(jLabel16);
        jLabel16.setBounds(0, 0, 190, 100);

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(52, 73, 94));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Log");
        btnLog.add(jLabel21);
        jLabel21.setBounds(80, 30, 100, 30);

        borda.add(btnLog);
        btnLog.setBounds(420, 230, 190, 100);

        btnRAnking.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnRAnking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRAnkingMouseClicked(evt);
            }
        });
        btnRAnking.setLayout(null);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/grafico_linha48x48.png"))); // NOI18N
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });
        btnRAnking.add(jLabel24);
        jLabel24.setBounds(10, 0, 180, 100);

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(52, 73, 94));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Ranking");
        btnRAnking.add(jLabel25);
        jLabel25.setBounds(70, 30, 110, 30);

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(52, 73, 94));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("de Vendas");
        btnRAnking.add(jLabel28);
        jLabel28.setBounds(70, 50, 110, 30);

        borda.add(btnRAnking);
        btnRAnking.setBounds(220, 230, 190, 100);

        btnCaixa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btnCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCaixaMouseClicked(evt);
            }
        });
        btnCaixa.setLayout(null);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/caixa.png"))); // NOI18N
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        btnCaixa.add(jLabel17);
        jLabel17.setBounds(10, 0, 180, 100);

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(52, 73, 94));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Caixa");
        btnCaixa.add(jLabel23);
        jLabel23.setBounds(90, 30, 90, 30);

        borda.add(btnCaixa);
        btnCaixa.setBounds(420, 120, 190, 100);

        painelEsquerdo.setBackground(new java.awt.Color(243, 156, 18));
        painelEsquerdo.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(52, 73, 94));

        jLabel1.setBackground(new java.awt.Color(52, 73, 94));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/fecharWhite24x24.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelEsquerdo.add(jPanel2);
        jPanel2.setBounds(590, 0, 40, 40);

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel8.setText("Gestão");
        painelEsquerdo.add(jLabel8);
        jLabel8.setBounds(120, 10, 280, 60);

        lblCargo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/perfil3.png"))); // NOI18N
        lblCargo.setText("jLabel6");
        painelEsquerdo.add(lblCargo);
        lblCargo.setBounds(370, 20, 110, 40);

        lblNomeOperador.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        lblNomeOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/usuario (2).png"))); // NOI18N
        lblNomeOperador.setText("jLabel6");
        painelEsquerdo.add(lblNomeOperador);
        lblNomeOperador.setBounds(370, 50, 110, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/gerenciar64x64.png"))); // NOI18N
        painelEsquerdo.add(jLabel2);
        jLabel2.setBounds(40, 10, 90, 80);

        borda.add(painelEsquerdo);
        painelEsquerdo.setBounds(0, 0, 628, 90);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(borda, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(borda, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 0, 630, 360);

        setSize(new java.awt.Dimension(628, 359));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // Atualiza Tela
        principal.atualizaInformativo();
        // Sai da tela estoque
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnProduto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProduto1MouseClicked

    }//GEN-LAST:event_btnProduto1MouseClicked

    private void btnGraficoRankingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGraficoRankingMouseClicked


    }//GEN-LAST:event_btnGraficoRankingMouseClicked

    private void lblGerenciarEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGerenciarEstoqueMouseClicked
       
        TelaMovimentacao m = new TelaMovimentacao();
        m.setAlwaysOnTop(true);
        m.recebeOperador(principal,lblNomeOperador.getText(), lblCargo.getText());
        m.setVisible(true);
    }//GEN-LAST:event_lblGerenciarEstoqueMouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked


    }//GEN-LAST:event_jLabel13MouseClicked

    private void btnPrecosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrecosMouseClicked

    }//GEN-LAST:event_btnPrecosMouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // Calcula reajuste de produtos
        TelaReajuste reajuste = new TelaReajuste();
        reajuste.setAlwaysOnTop(true);
        reajuste.setVisible(true);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // Cria  gráfico de produtos mais vendidos
        ResultSet rs = cp.rankingProdutosVendidos();
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        try {
            while (rs.next()) {
                ds.addValue(rs.getDouble("quantidade"), "Quantidade", rs.getString("nome"));
            }

        } catch (SQLException e) {
            System.out.println("erro ao Ranking de listarProdutos");
        }

        JFreeChart graficoBarras = ChartFactory.createBarChart3D("Produtos mais Vendidos", "Prodtutos", "Quantidade", ds, PlotOrientation.HORIZONTAL, true, false, true);
        CategoryPlot categoryPlot = graficoBarras.getCategoryPlot();
        graficoBarras.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.ORANGE);
        categoryPlot.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame("Gráfico - Produto Mais Vendidos", graficoBarras);
        frame.setAlwaysOnTop(true);
        frame.setSize(800, 650);
        frame.setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void lblFornecedores5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFornecedores5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblFornecedores5MouseClicked

    private void btnLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogMouseClicked

        // Abre tela de log
        TelaLog tl = new TelaLog();
        tl.recebeOperador(lblNomeOperador.getText(), lblCargo.getText());
        tl.setAlwaysOnTop(true);
        tl.setVisible(true);
    }//GEN-LAST:event_btnLogMouseClicked

    private void btnRAnkingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRAnkingMouseClicked
      
    }//GEN-LAST:event_btnRAnkingMouseClicked

    private void btnCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCaixaMouseClicked
       
    }//GEN-LAST:event_btnCaixaMouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
         // Abre a tela de gerenciamento de caixa
        TelaGerenciamentoDeCaixa gx = new TelaGerenciamentoDeCaixa();
        gx.recebeOperador(lblNomeOperador.getText(), lblCargo.getText());
        gx.setAlwaysOnTop(true);
        gx.setVisible(true);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
       // Chama da tela de Ranking de Vendas
       TelaRanking ranking = new TelaRanking();
       ranking.setAlwaysOnTop(true);
       ranking.setVisible(true);
    }//GEN-LAST:event_jLabel24MouseClicked

    private void lblGerenciarEstoqueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblGerenciarEstoqueKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblGerenciarEstoqueKeyPressed

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
            java.util.logging.Logger.getLogger(TelaEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel borda;
    private javax.swing.JPanel btnCaixa;
    private javax.swing.JPanel btnGraficoRanking;
    private javax.swing.JPanel btnLog;
    private javax.swing.JPanel btnPrecos;
    private javax.swing.JPanel btnProduto1;
    private javax.swing.JPanel btnRAnking;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblFornecedores5;
    private javax.swing.JLabel lblGerenciarEstoque;
    private javax.swing.JLabel lblNomeOperador;
    private javax.swing.JPanel painelEsquerdo;
    // End of variables declaration//GEN-END:variables
}
