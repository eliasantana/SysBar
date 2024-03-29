/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.Log;
import br.com.bar.model.DadosEmpresa;
import br.com.bar.model.Funcionario;
import br.com.bar.model.Mesa;
import br.com.bar.model.Pedido;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerDelivery;
import br.com.br.controler.ControlerFuncionario;
import br.com.br.controler.ControlerMesa;
import br.com.br.controler.ControlerPedido;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Elias Santana
 *
 */
public class TelaPedido3 extends javax.swing.JFrame {

    ControlerMesa controlerMesa = new ControlerMesa();
    ControlerPedido cp = new ControlerPedido();
    ControlerFuncionario cf = new ControlerFuncionario();
    ControlerDadosEmpresa dadosEmpresa = new ControlerDadosEmpresa();
    
    Util u = new Util();
    Funcionario funcLogado;
    ResultSet rs;
    ArrayList<Mesa> listaDeMesas;
    String operador, cargo;
    TimerTask task;
    // Limite em segundo para bloquear a tela
    int limite = 120;
    // Contador - Segundos
    int s = 0;
    public TelaPedido3() {
        initComponents();
        atualiza();
       
    }

    public void recebeOperador(String operador, String cargo) {
        this.operador = operador;
        this.cargo = cargo;
        funcLogado = cf.localizaFuncionario(cf.localizaIdLogin(operador));
       
        // Para usuário com perfil garçom será cronometrado 12 minutos entre
        // Cada atualização automática
        
        if ("Garçom".equals(cargo)){
            cronometro();
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

        jMenuItem1 = new javax.swing.JMenuItem();
        panelaMesas = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MasterFood - Pedidos - Listagem de Mesas");
        setExtendedState(6);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        panelaMesas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelaMesasFocusGained(evt);
            }
        });

        javax.swing.GroupLayout panelaMesasLayout = new javax.swing.GroupLayout(panelaMesas);
        panelaMesas.setLayout(panelaMesasLayout);
        panelaMesasLayout.setHorizontalGroup(
            panelaMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelaMesasLayout.setVerticalGroup(
            panelaMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bar/imagens/cadeado3.png"))); // NOI18N
        jMenu1.setText("Bloquear");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelaMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelaMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(416, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        TelaBloqueio b = new TelaBloqueio();
        b.setModal(true);
        b.setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void panelaMesasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelaMesasFocusGained

    }//GEN-LAST:event_panelaMesasFocusGained

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        atualizaTela();
    }//GEN-LAST:event_formMouseEntered

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if ("Garçom".equals(cargo)){
            task.cancel();
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(TelaPedido3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPedido3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPedido3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPedido3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPedido3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel panelaMesas;
    // End of variables declaration//GEN-END:variables

    private void atualiza() {
        //panelaMesas.removeAll();

        //panelaMesas.repaint();
        DadosEmpresa de = dadosEmpresa.selecionaDados();
        int opDelivery = de.getAtivaDelivery();
        listaDeMesas = controlerMesa.listaTodasAsMesas(opDelivery);
        panelaMesas.setLayout(new GridLayout(3, 2));

        for (int i = 0; i < listaDeMesas.size(); i++) {
            JButton btn = new JButton();
            btn.setBounds(0, 0, 100, 50);
            btn.setFont(new Font("Arial", Font.PLAIN, 20));
            btn.setText(listaDeMesas.get(i).getNumeroMesa());
            String idmesa = listaDeMesas.get(i).getId();
            String garcom = cf.retornaGarcom(btn.getText());
            String idgarcom = cf.localizaId(garcom);

            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if (controlerMesa.estaLivre(btn.getText())) {
                        Object [] obj = {"   Não   ","   Sim   "};
                        int op = JOptionPane.showOptionDialog(null, "Deseja abrir um novo pedido para a mesa " + btn.getText() + "?", "Atenção!", 
                                 JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null,obj,obj[1]);
                        if (op == 1) {
                            //JOptionPane.showMessageDialog(null, "Abrir novo Pedido");
                            // Abre pedido
                            Pedido p = new Pedido();
                            p.setCadMesaId(idmesa);
                            //String dataAtual = cp.myDataAtual();
                            Date data = new Date();
                            Timestamp dtAtualTms = new Timestamp(data.getTime());
                            //p.setData(dataAtual);
                            p.setData(String.valueOf(dtAtualTms));
                            p.setStatus("0"); // Pedido Aberto
                            p.setIdFuncionario(idgarcom);
                            //p.setId_pedido(txtNumeroPedido.getText());

                            if (cp.geraPedido(p)) {
                                // Troca Status da Mesa
                                controlerMesa.trocaStatusMesa(btn.getText(), "1");

                                //Log
                                String idPedidoGErado = cp.idUltimoPedido(idgarcom);
                                Log l = new Log();
                                l.setUsuario(operador);
                                l.setFuncionalidade("Pedido");
                                String idFuncLogado = funcLogado.getId();

                                if (idFuncLogado.equals(idgarcom)) {
                                    l.setDescricao("Abriu o pedido -> " + idPedidoGErado);
                                } else {
                                    l.setUsuario(operador);
                                    l.setDescricao("Abriu o pedido -> " + idPedidoGErado + " [USUÁRIO LOGADO: " + funcLogado.getLogin() + " ]");
                                }

                                l.gravaLog(l);

                            }
                            // Fim da abertura de pedido
                            btn.setForeground(Color.white);
                            btn.setBackground(Color.RED);
                            // Chama tela de Detalhe Mesa 
                            TelaDetalheMesa dtlMesa = new TelaDetalheMesa();                            
                            dtlMesa.setAlwaysOnTop(true);
                            dtlMesa.recebeMesa(btn.getText());
                            dtlMesa.recebeOperador(operador, cargo);                            
                            dtlMesa.setVisible(true);                       

                        }
                    } else {
                        TelaDetalheMesa dtlMesa = new TelaDetalheMesa();
                        dtlMesa.setAlwaysOnTop(true);
                        dtlMesa.recebeMesa(btn.getText());
                        dtlMesa.recebeOperador(operador, cargo);
                        dtlMesa.recebeTela(TelaPedido3.this, btn);
                        dtlMesa.setVisible(true);

                    }
                }

                @Override
                public void mousePressed(MouseEvent me) {

                }

                @Override
                public void mouseReleased(MouseEvent me) {

                }

                @Override
                public void mouseEntered(MouseEvent me) {

                }

                @Override
                public void mouseExited(MouseEvent me) {

                }

            });
            if ("0".equals(listaDeMesas.get(i).getStatus())) {
                btn.setForeground(Color.BLACK);
                btn.setBackground(new Color(0, 255, 127));
            } else {
                btn.setForeground(Color.white);
                btn.setBackground(Color.RED);
            }
            panelaMesas.add(btn);
        }
    }

    public void alteraCorMesa(JButton btn) {
        btn.setForeground(Color.BLACK);
        btn.setBackground(new Color(0, 255, 127));
    }
// Atualiza tela
    private void atualizaTela() {
        panelaMesas.removeAll();
        atualiza();
        revalidate();
        
    }

// Cronometro
private void cronometro() {
        long segundos = 1000;
        Timer timer = new Timer();

        task = new TimerTask() {
            @Override
            public void run() {
                //Realiza uma contagem a incremento de 1 acadas segundo.

                s = s + 1;
                //System.out.println(s);
                // Verifica se a contagem atingiu o limite informado para bloqueio da tela.
                if (s == limite) {
                    //System.out.println("Atualizando");
                    atualizaTela();
                    s=0;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, segundos);

    }    
    
}
