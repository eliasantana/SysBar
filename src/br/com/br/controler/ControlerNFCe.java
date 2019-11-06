/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.Autorizar;
import br.com.bar.model.NFCeCancelamento;
import br.com.bar.model.Nfce;
import br.com.bar.model.TesteJesonString;
import br.com.bar.util.Util;
import java.util.HashMap;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.codehaus.jettison.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Elias Santana
 */
public class ControlerNFCe {

    /* Aqui são criados as hash's que receberão os dados da nota. */
    HashMap<String, String> nfce = new HashMap<>();
    HashMap<String, String> itens = null;
    HashMap<String, String> formasPagamento = null;
    Util u = new Util();

    public String nfcEAutorizar(String nPedido, HashMap<String, String> intens, HashMap<String, String> formasPagamento) throws JSONException {

        String login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";

        /* Substituir pela sua identificação interna da nota. */
        String ref = nPedido; // Código do pedido

        /* Para ambiente de produção use a variável abaixo:
         String server = "https://api.focusnfe.com.br/"; */
        String server = "https://homologacao.focusnfe.com.br/";
        String url = server.concat("v2/nfce?ref=" + ref + "&completa=1");

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        /* Aqui são criados as hash's que receberão os dados da nota. */
        this.itens = intens;
        this.formasPagamento = formasPagamento;

        //-=--=--=-=-=-= CAMPOS DA NFCe =-==-=-=-==-=- 
        Date dataAtual = new Date();
        String data = u.formataDateTime(dataAtual);

        nfce.put("consumidor_final", "1");
        nfce.put("presenca_comprador", "1");
        nfce.put("forma_pagamento", "01");
        nfce.put("natureza_operacao", "Venda ao Consumidor");
        nfce.put("tipo_documento", "1"); // 1 - Nota Fiscal de Saída
        nfce.put("cpf_destinatario", "");
        nfce.put("icms_valor_total", "0.0000");
        nfce.put("icms_modalidade_base_calculo", "3");
        nfce.put("valor_frete", "0.0");
        nfce.put("modalidade_frete", "9");// 0 – Por conta do emitente; 1 – Por conta do destinatário; 2 – Por conta de terceiros; 9 – Sem frete;
        nfce.put("icms_base_calculo", "0.0000");
        nfce.put("data_emissao", data);
        nfce.put("cnpj_emitente", "34257575000106");

        //itens.put("ipi_codigo_enquadramento_legal", "999");

        /* Depois de fazer o input dos dados, são criados os objetos JSON já com os valores das hash's. */
        JSONObject json = new JSONObject(nfce);
        JSONObject jsonItens = new JSONObject(itens);

        /* Aqui adicionamos os objetos JSON nos campos da API como array no JSON principal. */
        json.append("items", jsonItens);

        JSONObject jsonPagamento = new JSONObject(formasPagamento);
        json.append("formas_pagamento", jsonPagamento);

        /* É recomendado verificar como os dados foram gerados em JSON e se ele está seguindo a estrutura especificada em nossa documentação.
		System.out.print(json); */
        WebResource request = client.resource(url);

        ClientResponse resposta = request.post(ClientResponse.class, json);

        int httpCode = resposta.getStatus();

        String body = resposta.getEntity(String.class);


        /* As três linhas a seguir exibem as informações retornadas pela nossa API. 
		 * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
        System.out.print("HTTP Code: ");
        System.out.print(httpCode);
        System.out.printf(body);

        return body;
    }

    /**
     * Realiza consulta da situação do cupom fiscal informado
     *
     * @param codPedidoNota Número da nota a ser consultada
     * @param arqRetorno Nome do arquivo de retorno com o resutado da pesquisa
     */
    public void consultarNFCE(String codPedidoNota, String arqRetorno) {
        int ambiente = 1;
        String login;
        String server;
        if (ambiente == 1) {
            // Token para emissão em ambiente de Produção
            login = "DhdwJcAsy0jGvNDRv7mGZyWeJ19CBRUT";
            /* Para ambiente de produção use a variável abaixo:*/
            server = "https://api.focusnfe.com.br/";
        } else {
            // Token para emissão em ambiente de Homologação
            login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";
            server = "https://homologacao.focusnfe.com.br/";
        }

        /* Substituir pela sua identificação interna da nota. */
        String ref = codPedidoNota;

        String url = server.concat("v2/nfce/" + ref + "?completa=0");

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        WebResource request = client.resource(url);
        ClientResponse resposta = request.get(ClientResponse.class);
        int httpCode = resposta.getStatus();
        String body = resposta.getEntity(String.class);

        /* As três linhas abaixo imprimem as informações retornadas pela API. 
         * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
        System.out.print("HTTP Code: ");
        System.out.print(httpCode);
        System.out.printf(body);

        try {
            FileOutputStream out = new FileOutputStream("c://sysbar/" + arqRetorno);
            PrintStream ps = new PrintStream(out);
            System.setOut(ps);
            System.out.println(body);
            //System.out.println(json);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Autorizar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
 * Carrega a lista de Backup dos último 6 meses 
 * @param tabela objeto Jtable para onde deverá ser carregada a lista de DANFs (NFC-e) a serem baixadas
 * @param  cnpjEmitente CNPJ cadastrado na Base de Dados
 * @return Retorna um ArrayList contendo os Meses e sua URL de download 
 */
    public ArrayList carrregaDownloadNFCE(JTable tabela, String cnpjEmitente) throws JSONException {
        int ambiente = 1;
        String login;
        String server;       
        ArrayList<String> urlList = new ArrayList<>();

        if (ambiente == 1) {
            // Token para emissão em ambiente de Produção
            login = "DhdwJcAsy0jGvNDRv7mGZyWeJ19CBRUT";
            /* Para ambiente de produção use a variável abaixo:*/
            
            server = "https://api.focusnfe.com.br/v2/backups/"+cnpjEmitente;
        } else {
            // Token para emissão em ambiente de Homologação
            login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";
            
            server = "https://api.focusnfe.com.br/v2/backups/"+cnpjEmitente;
        }

        String url = server;

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        WebResource request = client.resource(url);
        ClientResponse resposta = request.get(ClientResponse.class);
        int httpCode = resposta.getStatus();
        String body = resposta.getEntity(String.class);

        /* As três linhas abaixo imprimem as informações retornadas pela API. 
         * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
        //System.out.print("HTTP Code: ");
        //System.out.print(httpCode);
        //System.out.printf(body);
        JSONArray arrjson = new JSONArray(body);   // Captura retorno da API
        for (int i = 0; i < arrjson.length(); i++) {
            // Pega o objeto no array de Json
            JSONObject obj = arrjson.getJSONObject(i);
            String xmls = obj.get("xmls").toString();
            if (xmls.equals("null")) {
            } else {
                tabela.setValueAt(obj.get("mes"), i, 0);
                //tabela.setValueAt(obj.get("xmls"), i, 1);
                urlList.add(obj.getString("xmls"));
                String mes;
                String ano;
                mes = tabela.getValueAt(i, 0).toString();
                ano = tabela.getValueAt(i, 0).toString();
                mes = mes.substring(4);
                ano = ano.substring(0, 4);
                // Pega o valor que representa o mês na string de retorno e informa
                // o mês por extenso
                switch (mes) {
                    case "01":
                        tabela.setValueAt(ano + " - JANEIRO", i, 0);
                        break;
                    case "02":
                        tabela.setValueAt(ano + " - FEVEREIRO", i, 0);
                        break;
                    case "03":
                        tabela.setValueAt(ano + " - MARÇO", i, 0);
                        break;
                    case "04":
                        tabela.setValueAt(ano + " - ABRIL", i, 0);
                        break;
                    case "05":
                        tabela.setValueAt(ano + " - MAIO", i, 0);
                        break;
                    case "06":
                        tabela.setValueAt(ano + " - JUNHO", i, 0);
                        break;
                    case "07":
                        tabela.setValueAt(ano + " - JULHO", i, 0);
                        break;
                    case "08":
                        tabela.setValueAt(ano + " - AGOSTO", i, 0);
                        break;
                    case "09":
                        tabela.setValueAt(ano + " - SETEMBRO", i, 0);
                        break;
                    case "10":
                        tabela.setValueAt(ano + " - OUTUBRO", i, 0);
                        break;
                    case "11":
                        tabela.setValueAt(ano + " - NOVEMBRO", i, 0);                       
                        break;
                    case "12":
                        tabela.setValueAt(ano + " - DEZEMBRO", i, 0);
                        break;
                }
            }

        }
        return urlList;
    }

    /**
     * Realiza o cancelamento de um cupom fiscal
     *
     * @param motivo Motido do Cancelamento
     * @param nPedido Número da Nota ('Número do pedido')
     * @param arquivoRetorno Nome do Arquivo de Retorno contendo os dados
     * retornados.
     * @return httpCod Retorna o Código de resposta da API
     */
    public int cancelaNFCe(String motivo, String nPedido, String arquivoRetorno) {
        int ambiente = 1;
        String login;
        if (ambiente == 1) {
            // Token para emissão em ambiente de Produção
            login = "DhdwJcAsy0jGvNDRv7mGZyWeJ19CBRUT";
        } else {
            // Token para emissão em ambiente de Homologação
            login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";
        }

        NFCeCancelamento cancelamento = new NFCeCancelamento();

        String server;
        /* Substituir pela sua identificação interna da nota. */
        String ref = nPedido;
        if (ambiente == 1) {
            /* Para ambiente de produção use a variável abaixo:*/
            server = "https://api.focusnfe.com.br/";
        } else {
            /* Para ambiente de Homologação use a variável abaixo:*/
            server = "https://homologacao.focusnfe.com.br/";
        }

        String url = server.concat("v2/nfce/" + ref);

        /* Aqui criamos um hashmap para receber a chave "justificativa" e o valor desejado. */
        HashMap<String, String> justificativa = new HashMap();
        //justificativa.put("justificativa", "Informe aqui a sua justificativa para realizar o cancelamento da NFCe.");
        justificativa.put("justificativa", motivo);

        /* Criamos um objeto JSON para receber a hash com os dados esperado pela API. */
        JSONObject json = new JSONObject(justificativa);

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        WebResource request = client.resource(url);

        ClientResponse resposta = request.delete(ClientResponse.class, json);

        int httpCode = resposta.getStatus();

        String body = resposta.getEntity(String.class);

        /* As três linhas abaixo imprimem as informações retornadas pela API. 
        * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
        System.out.print("HTTP Code: ");
        System.out.print(httpCode);
        System.out.printf(body);

        try {
            FileOutputStream out = new FileOutputStream("c://sysbar/" + arquivoRetorno);
            PrintStream ps = new PrintStream(out);
            System.setOut(ps);

            System.out.println(body);
            //System.out.println(json);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Autorizar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return httpCode;
    }

    public boolean enviaEmail(String refNPedido, ArrayList<String> listaDeEmail) throws JSONException {

        String login = "DhdwJcAsy0jGvNDRv7mGZyWeJ19CBRUT";
        boolean resp = false;
        /* Substituir pela sua identificação interna da nota. */
        String ref = refNPedido;

        /* Para ambiente de produção use a variável abaixo:*/
        String server = "https://api.focusnfe.com.br/";
        //String server = "https://homologacao.focusnfe.com.br/";

        String url = server.concat("v2/nfce/" + ref + "/email");

        /* Criamos o um objeto JSON que receberá um JSON Array com a lista de e-mails. */
        JSONObject json = new JSONObject();
        JSONArray listaEmails = new JSONArray();
        listaEmails.put("eliasantana@hotmail.com");
        if (listaDeEmail.size() > 1) {

            for (int i = 0; i < listaDeEmail.size(); i++) {
                json.accumulate("emails", listaDeEmail.get(i));
            }
        } else {

            json.put("emails", listaEmails);
        }

        /* Testar se o JSON gerado está dentro do formato esperado.*/
        System.out.print(json);

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        WebResource request = client.resource(url);

        ClientResponse resposta = request.post(ClientResponse.class, json);

        int httpCode = resposta.getStatus();

        String body = resposta.getEntity(String.class);

        /* As três linhas abaixo imprimem as informações retornadas pela API. 
         * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
        System.out.print("HTTP Code: ");
        System.out.print(httpCode);
        System.out.printf(body);

        if (httpCode == 200) {
            resp = true;
        }

        return resp;
    }

    /**
     * Converte um arquivo de retorno String no formato JSon e converte em
     * JSONObjet utilizando a biblioteca org.json.simple
     *
     * @param nomeArquivo Nome do arquivo a ser lido
     * @return Retorna um Objeto NFCe contendo:
     *
     * Chave de Autorização, Url de Consulta da Nota Série e Número Url do QrCod
     * Número do Protocolo
     * @throws org.json.simple.parser.ParseException
     * @throws java.io.IOException
     */
    public Nfce lerRetorno(String nomeArquivo) throws ParseException, IOException {
        org.json.simple.JSONObject jo;
        JSONParser parser = new JSONParser();
        Nfce nota = new Nfce();

        try {
            //jo = (org.json.simple.JSONObject) parser.parse(new FileReader("C:\\Sysbar\\consulta.json"));
            jo = (org.json.simple.JSONObject) parser.parse(new FileReader("C:\\Sysbar\\" + nomeArquivo));
            nota.setChave_nfe((String) jo.get("chave_nfe"));
            nota.setUrl_consulta_nf((String) jo.get("url_consulta_nf"));
            nota.setSerie((String) jo.get("serie"));
            nota.setNumero((String) jo.get("numero"));
            nota.setQrcode_url((String) jo.get("qrcode_url"));
            nota.setNumero_protocolo((String) jo.get("numero_protocolo"));
            nota.setData_emissao((String) jo.get("data_emissao"));
            nota.setInformacoes_adicionais_contribuinte((String) jo.get("informacoes_adicionais_contribuinte"));

            //System.out.println(nota.toString());
        } catch (FileNotFoundException | ParseException ex) {
            Logger.getLogger(TesteJesonString.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nota;
    }

    /**
     * Converte um arquivo de retorno String no formato JSon e converte em
     * JSONObjet utilizando a biblioteca org.json.simple
     *
     * @param nomeArquivo Nome do arquivo a ser lido
     * @return Retorna um Objeto NFCeCancelamento contendo:
     *
     * Status Sefaz Mensagem Sefaz Status Caminho XML do Cancelamento
     * @throws org.json.simple.parser.ParseException
     * @throws java.io.IOException
     */
    public NFCeCancelamento lerRetornoCancelamento(String nomeArquivo) throws ParseException, IOException {
        org.json.simple.JSONObject jo;
        JSONParser parser = new JSONParser();
        NFCeCancelamento cancelamento = new NFCeCancelamento();

        try {

            jo = (org.json.simple.JSONObject) parser.parse(new FileReader("C:\\Sysbar\\" + nomeArquivo));
            cancelamento.setStatus_sefaz((String) jo.get("status_sefaz"));
            cancelamento.setMensagem_sefaz((String) jo.get("mensagem_sefaz"));
            cancelamento.setStatus((String) jo.get("status"));
            cancelamento.setCaminho_xml_cancelamento((String) jo.get("caminho_xml_cancelamento"));

            //System.out.println(nota.toString());
        } catch (FileNotFoundException | ParseException ex) {
            Logger.getLogger(TesteJesonString.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("br.com.br.controler.ControlerNFCe.lerRetornoCancelamento()" + ex);
        }

        return cancelamento;
    }

    /**
     * Registra o cancelamento do cupom na base local
     *
     * @param nCupom Número do Cupom
     * @param operador Operador que realizou o cancelamento da nota.
     */
    public void registraCancelamento(String nCupom, String operador) {

        String sql = "INSERT INTO tbhistorico_cancelamento (numero_cupom,data, operador) VALUES (?,current_timestamp(),?)";

        try {
            Connection conexao = ConexaoBd.conector();
            PreparedStatement pst;
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nCupom);
            pst.setString(2, operador);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerNFCe.gravaCancelamento()" + e);
        }
    }

    /**
     * Verificana base local se o cupom já foi cancelado
     *
     * @param nCupom Número do Cupom
     * @return Retorna TRUE ou FALSE
     */
    public boolean estaCancelada(String nCupom) {

        boolean resp = false;
        Connection conexao;
        PreparedStatement pst;
        ResultSet rs;

        String sql = "SELECT * FROM tbhistorico_cancelamento WHERE numero_cupom=?";

        try {
            conexao = ConexaoBd.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nCupom);
            rs = pst.executeQuery();
            while (rs.next()) {
                resp = true;
            }

            conexao.close();
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerNFCe.foiCancelada()");
        }

        return resp;
    }

}
