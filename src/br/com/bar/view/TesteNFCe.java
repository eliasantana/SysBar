/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.view;

import br.com.bar.dao.CriptoGrafa;
import br.com.bar.dao.ReportUtil;
import br.com.bar.model.Autorizar;
import br.com.bar.model.DadosEmpresa;
import br.com.bar.model.ProdutoNota;
import br.com.bar.util.FormataValor;
import br.com.bar.util.Util;
import br.com.br.controler.ControlerDadosEmpresa;
import br.com.br.controler.ControlerNFCe;
import br.com.br.controler.ControlerPedido;
import br.com.br.controler.ControlerProduto;
import com.google.zxing.WriterException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Elias Santana
 */
public class TesteNFCe extends javax.swing.JFrame {

    ControlerProduto cp = new ControlerProduto();
    ControlerPedido cpedido = new ControlerPedido();
    ControlerNFCe nfceControler = new ControlerNFCe();
    Util u = new Util();
    /* Aqui são criados as hash's que receberão os dados da nota. */
    HashMap<String, String> nfce = new HashMap();
    HashMap<String, String> itens = new HashMap<>();
    HashMap<String, String> formasPagamento = new HashMap();
    int linhas = 0;
    int totLinha;
    int linhaAtual;

    public TesteNFCe() {
       
        initComponents();
        tbDetalhePedido.setModel(DbUtils.resultSetToTableModel(cpedido.detalhePorPedido("1", "180")));
        linhas = tbDetalhePedido.getRowCount();
        System.out.println("Total de Linhas: " + linhas);
        linhaAtual = tbDetalhePedido.getSelectedRow();
        //aplicaMascara(campoFormatado);
        
        FormataValor fv = new FormataValor();
        fv.aplicaMascara(valor, 7,1);
        valor.setText("0,00");
        
        Calendar c = Calendar.getInstance();
        Date dtAtual = new Date();
        String data = "dd/MM/yyyy";
        jDateChooser1.setDateFormatString(data);
        jDateChooser1.setDate(dtAtual);
        System.out.println(data);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adicionar = new javax.swing.JButton();
        listar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalhePedido = new javax.swing.JTable();
        jSpinnerQtd = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        btnValidaEmail = new javax.swing.JButton();
        txtEmail1 = new javax.swing.JTextField();
        txtEmail2 = new javax.swing.JTextField();
        txtEmail3 = new javax.swing.JTextField();
        lblEmail1 = new javax.swing.JLabel();
        lblEmail2 = new javax.swing.JLabel();
        lblEmail3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        valor = new javax.swing.JFormattedTextField();
        jButton5 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLocaleChooser1 = new com.toedter.components.JLocaleChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        adicionar.setText("Criptografa");
        adicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adicionarMouseClicked(evt);
            }
        });
        adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarActionPerformed(evt);
            }
        });

        listar.setText("NfCe");
        listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarActionPerformed(evt);
            }
        });

        tbDetalhePedido = new javax.swing.JTable(){
            public boolean isCellEditable (int rowIndex, int colIndex){
                return false;
            }
        };
        tbDetalhePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDetalhePedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetalhePedidoMouseClicked(evt);
            }
        });
        tbDetalhePedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDetalhePedidoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbDetalhePedido);

        jButton1.setText("Abrir Relatório QR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnValidaEmail.setText("Validar");
        btnValidaEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidaEmailActionPerformed(evt);
            }
        });

        lblEmail1.setText("jLabel1");

        lblEmail2.setText("jLabel1");

        lblEmail3.setText("jLabel1");

        jButton2.setText("Enviar Email NFC");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Dados Empresa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Tela1");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        valor.setText("0,00");
        valor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                valorMouseClicked(evt);
            }
        });

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSpinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(jButton3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEmail1)
                                            .addComponent(lblEmail2)
                                            .addComponent(lblEmail3)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(listar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(adicionar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addGap(117, 117, 117)
                                                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtEmail2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnValidaEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmail3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jButton4)
                        .addGap(166, 166, 166)
                        .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addGap(563, 563, 563)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(jLocaleChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listar)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEmail2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEmail3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLocaleChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnValidaEmail)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed

        ControlerDadosEmpresa dados = new ControlerDadosEmpresa();
        DadosEmpresa d = dados.selecionaDados();
        CriptoGrafa cripto = new CriptoGrafa();
        // -----------------------------------------------------------
        //Coluna do Banco de Dados responsável pela ativação fiscal
        //String fiscal = null;
        //String fiscal ="Elias25252525";
        String fiscal = "MzQuMjU3LjU3NS8wMDAxLTA2";
        String nomeEmpresa = d.getNome_empresa();
        System.out.println(nomeEmpresa);
        int retorno = 0;
        if (fiscal != null) {
            // Encript CNPJ
            String cnpjEncriptado = cripto.encripta(d.getCnpj());
            if (fiscal.equals(cnpjEncriptado)) {
                retorno = 1;
            }
        }
        System.out.println("Resposta Fiscal: " + retorno);
        if (retorno == 1) {
            System.out.println("Imprimir Cupom Fiscal.....");
        } else {
            System.out.println("Systema sem licença fiscal......");

        }
    }//GEN-LAST:event_adicionarActionPerformed

    private void listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarActionPerformed
        int linhas = tbDetalhePedido.getRowCount();

        // Ler produtos do pedido e adiciona na lista
        ArrayList<ProdutoNota> listaProduto = new ArrayList<>();
        for (int i = 0; i < linhas; i++) {
            ProdutoNota p = new ProdutoNota();
            p.setCodProduto(tbDetalhePedido.getValueAt(i, 0).toString());
            p.setDescricao(tbDetalhePedido.getValueAt(i, 1).toString());
            p.setQtd(tbDetalhePedido.getValueAt(i, 2).toString());
            p.setvUnit(tbDetalhePedido.getValueAt(i, 3).toString().replace(",", "."));
            p.setVlrTotal(tbDetalhePedido.getValueAt(i, 4).toString().replace(",", "."));

            listaProduto.add(p);
        }

        JSONObject json;
        JSONObject jsonItens;

        String login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";

        /* Substituir pela sua identificação interna da nota. */
        String ref = "705";  // Rererência ao número idPedido

        /* Para ambiente de produção use a variável abaixo:
        String server = "https://api.focusnfe.com.br/"; */
        String server = "https://homologacao.focusnfe.com.br/";
        //String server = "http://homologacao.acrasnfe.acras.com.br/";

        String url = server.concat("v2/nfce?ref=" + ref + "&completa=1");

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        // Clacular e Adicionar total da compra
        /* Depois de fazer o input dos dados, são criados os objetos JSON já com os valores das hash's. */
        //-=--=--=-=-=-= CAMPOS DA NFCe =-==-=-=-==-=- 
        Date dataAtual = new Date();
        String data = u.formataDateTime(dataAtual);

        nfce.put("data_emissao", data);
        nfce.put("consumidor_final", "1");
        nfce.put("modalidade_frete", "9");// 0 – Por conta do emitente; 1 – Por conta do destinatário; 2 – Por conta de terceiros; 9 – Sem frete;
        nfce.put("natureza_operacao", "Venda ao Consumidor");
        nfce.put("tipo_documento", "1"); // 1 - Nota Fiscal de Saída
        nfce.put("finalidade_emissao", "1"); // 1 – Normal; 2 – Complementar; 3 – Nota de ajuste; 4 – Devolução.
        nfce.put("presenca_comprador", "1");
        nfce.put("indicador_inscricao_estadual_destinatario", "1");
        nfce.put("cnpj_emitente", "34257575000106");
        nfce.put("cpf_destinatario", "");
        nfce.put("id_estrangeiro_destinatario", "1234567");
        nfce.put("nome_destinatario", "NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        nfce.put("informacoes_adicionais_contribuinte", "Documento emitido por ME ou EPP optante pelo Simples Nacional nao gera direito a credito fiscal de ICMS lei 123/2006.");
        //nfce.put("valor_produtos", ""); // Calculado automaticamente se não informado
        //nfce.put("valor_desconto", "0.0000");
        //nfce.put("valor_total", "1.0000");
        nfce.put("forma_pagamento", "01");
        nfce.put("icms_base_calculo", "0.0000");
        nfce.put("icms_valor_total", "0.0000");
        nfce.put("icms_base_calculo_st", "0.0000");
        nfce.put("icms_valor_total_st", "0.0");
        nfce.put("icms_modalidade_base_calculo", "3");
        nfce.put("valor_frete", "0.0");

        json = new JSONObject(this.nfce);

        for (int i = 0; i < listaProduto.size(); i++) {

            /*CAMPOS  ITENS*/
            itens.put("numero_item", String.valueOf(i));
            itens.put("unidade_comercial", "UN");
            itens.put("unidade_tributavel", "UN");
            itens.put("codigo_ncm", "94019090");
            itens.put("codigo_produto", listaProduto.get(i).getCodProduto());
            itens.put("descricao", listaProduto.get(i).getDescricao());
            itens.put("cfop", "5102");
            itens.put("valor_unitario_comercial", listaProduto.get(i).getvUnit());
            itens.put("valor_unitario_tributavel", listaProduto.get(i).getvUnit());
            itens.put("valor_bruto", listaProduto.get(i).getVlrTotal());
            itens.put("quantidade_comercial", listaProduto.get(i).getQtd());
            itens.put("quantidade_tributavel", listaProduto.get(i).getQtd());
            itens.put("quantidade", listaProduto.get(i).getQtd());
            itens.put("icms_origem", "0");
            itens.put("icms_base_calculo", "1.00");
            itens.put("icms_modalidade_base_calculo", "3");
            itens.put("valor_frete", "0.0");
            itens.put("valor_outras_despesas", "0.0");
            itens.put("icms_situacao_tributaria", "102");//?

            jsonItens = new JSONObject(this.itens);
            System.out.println("Produto: " + listaProduto.get(i).getDescricao());
            try {
                json.append("items", jsonItens);
            } catch (JSONException ex) {
                Logger.getLogger(TesteNFCe.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro ao Adicionar Itens no Obj. Json");
            }

        }

        formasPagamento.put("forma_pagamento", "99");
        formasPagamento.put("valor_pagamento", "93.65");

        JSONObject jsonPagamento = new JSONObject(this.formasPagamento);

        try {
            json.append("formas_pagamento", jsonPagamento);
        } catch (JSONException ex) {
            Logger.getLogger(TesteNFCe.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao Adicionar Forma de Pagamento no Obj. Json");
        }

        System.out.println("Tamnho da lista:" + listaProduto.size());

        // Imprime dados do Json
        try {
            FileOutputStream out = new FileOutputStream("c://sysbar/saida.json");
            PrintStream ps = new PrintStream(out);
            System.setOut(ps);

            System.out.print(json);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Autorizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* 
        // Verifica dados gerados no array de Json
        //System.out.print(json); 
        /* É recomendado verificar como os dados foram gerados em JSON e se ele está seguindo a estrutura especificada em nossa documentação. 
        WebResource request = client.resource(url);

        ClientResponse resposta = request.post(ClientResponse.class, json);

        int httpCode = resposta.getStatus();

        String body = resposta.getEntity(String.class);

        /* As três linhas a seguir exibem as informações retornadas pela nossa API. 
         * Aqui o seu sistema deverá interpretar e lidar com o retorno. 
        System.out.print("HTTP Code: ");
        System.out.print(httpCode);
        System.out.println(body);
         */


    }//GEN-LAST:event_listarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ReportUtil ru = new ReportUtil();
        HashMap<String, String> map = new HashMap<>();
        map.put("chave_nfe", "NFe26190934257575000106650010000000091397401706");
        map.put("url_consulta_nf", "nfce.sefaz.pe.gov.br/nfce/consulta");
        map.put("serie", "1");
        map.put("numero", "1");
        map.put("qrcode_url", "http://nfcehomolog.sefaz.pe.gov.br/nfce/consulta?p=26190934257575000106650010000000091397401706|2|2|1|7FD968E4418F6BBBB92880FC356E1DF194AD9516");
        String qr = "http://nfcehomolog.sefaz.pe.gov.br/nfce/consulta?p=26190934257575000106650010000000091397401706|2|2|1|7FD968E4418F6BBBB92880FC356E1DF194AD9516";
        Util util = new Util();
        String caminho = "C:/Sysbar/qr.jpg";
        try {
            util.generateQRCodeImage(qr, 120, 120, caminho);
        } catch (WriterException | IOException ex) {
            Logger.getLogger(TesteNFCe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ru.imprimeRelatorioTela("cancelamento.jasper", map, "COMPROVANTE DE CANCELAMENTO");
        } catch (JRException ex) {
            Logger.getLogger(TesteNFCe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnValidaEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidaEmailActionPerformed

        ArrayList<String> listaEmail = new ArrayList<>();

        if (u.validaEmail(txtEmail1.getText())) {
            listaEmail.add(txtEmail1.getText());
            lblEmail1.setText("Válido");
        } else {
            lblEmail1.setText("inválido");
        }

        if (u.validaEmail(txtEmail2.getText())) {
            listaEmail.add(txtEmail2.getText());
            lblEmail2.setText("Válido");
        } else {
            lblEmail1.setText("inválido");
        }

        if (u.validaEmail(txtEmail3.getText())) {
            listaEmail.add(txtEmail1.getText());
            lblEmail3.setText("Válido");
        } else {
            lblEmail3.setText("Inválido");
        }

        System.out.println("Tamnho da Lista: " + listaEmail.toString());
        System.out.println("Tamnho: " + listaEmail.size());

        if (listaEmail.size() > 0) {
            ControlerNFCe cNfce = new ControlerNFCe();
            try {
                if (cNfce.enviaEmail("718", listaEmail)) {
                    JOptionPane.showMessageDialog(this, "Mensagem enviad com sucesso!");
                }
            } catch (JSONException ex) {
                Logger.getLogger(TesteNFCe.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_btnValidaEmailActionPerformed

    private void adicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adicionarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_adicionarMouseClicked

    private void tbDetalhePedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalhePedidoMouseClicked
        linhas = tbDetalhePedido.getSelectedRow();

        if (evt.getClickCount() == 2) {
            int qtd = Integer.parseInt(tbDetalhePedido.getModel().getValueAt(linhas, 2).toString());
            String nomeProduto = tbDetalhePedido.getModel().getValueAt(linhas, 1).toString();
            TelaAdicionaProduto tap = new TelaAdicionaProduto();
            tap.recebeValoresProduto(1, 1, 100, 1, qtd, nomeProduto);
            tap.setVisible(true);
        }
    }//GEN-LAST:event_tbDetalhePedidoMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ControlerNFCe testenf = new ControlerNFCe();

        ArrayList<String> email = new ArrayList<>();
        email.add("eliasantanasilva@gmail.com");
        email.add("janiel.freitas@gmail.com");

        try {
            testenf.enviaEmail("166", email);

        } catch (JSONException ex) {
            Logger.getLogger(TesteNFCe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tbDetalhePedido.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void tbDetalhePedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetalhePedidoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_DOWN && linhaAtual < linhas - 1) {
            linhaAtual = linhaAtual + 1;
            lblEmail1.setText(String.valueOf(linhaAtual));
            System.out.println(tbDetalhePedido.getValueAt(linhaAtual, 1));
        } else if (evt.getKeyCode() == KeyEvent.VK_UP && linhaAtual > 0) {
            linhaAtual = linhaAtual - 1;
            lblEmail1.setText(String.valueOf(linhaAtual));
            System.out.println(tbDetalhePedido.getValueAt(linhaAtual, 1));
        }
    }//GEN-LAST:event_tbDetalhePedidoKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //LocalDate dt = LocalDate.now();
        ControlerDadosEmpresa cde = new ControlerDadosEmpresa();
        DadosEmpresa d = cde.selecionaDados();
        
        System.out.println(d.retornaCnpj());        

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TelaBloqueio tb = new TelaBloqueio();
        tb.setModal(true);
        tb.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String nomeImagem = "masterfood64x64.png";      
        
        Object[] options = {"   Sim   ","   Não   "}; 
        int op = JOptionPane.showOptionDialog(this, "Deseja Realmente Sair do Programa?", "Saír do Programa", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[1]);
        System.out.println(op);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void valorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valorMouseClicked
       valor.setText(null);
    }//GEN-LAST:event_valorMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
       
    }//GEN-LAST:event_formKeyPressed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        
    }//GEN-LAST:event_formFocusLost

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
            java.util.logging.Logger.getLogger(TesteNFCe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesteNFCe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesteNFCe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesteNFCe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TesteNFCe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JButton btnValidaEmail;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.components.JLocaleChooser jLocaleChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerQtd;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblEmail2;
    private javax.swing.JLabel lblEmail3;
    private javax.swing.JButton listar;
    private javax.swing.JTable tbDetalhePedido;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtEmail3;
    private javax.swing.JFormattedTextField valor;
    // End of variables declaration//GEN-END:variables

    private void aplicaMascara(JFormattedTextField campo) {

        DecimalFormat decimal = new DecimalFormat("##,###,###.00");
        decimal.setMaximumIntegerDigits(7);
        decimal.setMinimumIntegerDigits(1);
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        campo.setFormatterFactory(dfFactory);
    }
}
