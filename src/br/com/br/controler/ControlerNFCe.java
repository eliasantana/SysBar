/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;
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
import java.util.Date;
import org.codehaus.jettison.json.JSONArray;

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
    
    public void nfcEAutorizar(String nPedido, HashMap<String, String> intens, HashMap<String, String> formasPagamento) throws JSONException {

        String login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";

        /* Substituir pela sua identificação interna da nota. */
        String ref = nPedido;  // Rererência ao número idPedido

        /* Para ambiente de produção use a variável abaixo:
        String server = "https://api.focusnfe.com.br/"; */
        String server = "https://homologacao.focusnfe.com.br/";

        String url = server.concat("v2/nfce?ref=" + ref + "&completa=1");

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

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
        
        this.itens = intens;
        this.formasPagamento = formasPagamento;  
         

        /* Depois de fazer o input dos dados, são criados os objetos JSON já com os valores das hash's. */
        JSONObject json = new JSONObject(this.nfce);
        JSONObject jsonItens = new JSONObject(this.itens);
        JSONObject jsonPagamento = new JSONObject(this.formasPagamento);

        /* Aqui adicionamos os objetos JSON nos campos da API como array no JSON principal. */
        json.append("items", jsonItens);
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
        
        
    }

    public void consultarNFCE() {
        
        String login = "Token_enviado_pelo_suporte";

        /* Substituir pela sua identificação interna da nota. */
        String ref = "12345";

        /* Para ambiente de produção use a variável abaixo:
        String server = "https://api.focusnfe.com.br/"; */
        String server = "https://homologacao.focusnfe.com.br/";

        String url = server.concat("v2/nfce/" + ref + "?completa=1");

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
    }
    
    public void nfcCancelamento(String motivo){
        String login = "Token_enviado_pelo_suporte";

        /* Substituir pela sua identificação interna da nota. */
        String ref = "12345";

        /* Para ambiente de produção use a variável abaixo:
        String server = "https://api.focusnfe.com.br/"; */
        String server = "https://homologacao.focusnfe.com.br/";

        String url = server.concat("v2/nfce/"+ref);

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
    }
    
    public void enviaEmail() throws JSONException{
        String login = "Token_enviado_pelo_suporte";

        /* Substituir pela sua identificação interna da nota. */
        String ref = "12345";

        /* Para ambiente de produção use a variável abaixo:
        String server = "https://api.focusnfe.com.br/"; */
        String server = "https://homologacao.focusnfe.com.br/";

        String url = server.concat("v2/nfce/"+ref+"/email");

        /* Criamos o um objeto JSON que receberá um JSON Array com a lista de e-mails. */
        JSONObject json = new JSONObject ();    
        JSONArray listaEmails = new JSONArray();
        listaEmails.put("email_01@acras.com.br");
        listaEmails.put("email_02@acras.com.br");
        listaEmails.put("email_03@acras.com.br");
        json.put("emails", listaEmails);    

        /* Testar se o JSON gerado está dentro do formato esperado.
        System.out.print(json); */

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
    }
    
            
}

            
            

