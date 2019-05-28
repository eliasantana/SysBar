/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;
import java.util.HashMap;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author Elias Santana
 */
public class ControlerNFCe {

    /* Aqui são criados as hash's que receberão os dados da nota. */
    HashMap<String, String> nfce = new HashMap();
    HashMap<String, String> itens = new HashMap();
    HashMap<String, String> formasPagamento = new HashMap();

    public void nfcEAutorizar(HashMap<String, String> nfce, HashMap<String, String> intens, HashMap<String, String> formasPagamento) throws JSONException {

        String login = "Token_enviado_pelo_suporte";

        /* Substituir pela sua identificação interna da nota. */
        String ref = "12345";

        /* Para ambiente de produção use a variável abaixo:
        String server = "https://api.focusnfe.com.br/"; */
        String server = "https://homologacao.focusnfe.com.br/";

        String url = server.concat("v2/nfce?ref=" + ref + "&completa=1");

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        this.nfce = nfce;
        this.itens = intens;
        this.formasPagamento = formasPagamento;

        /*  -=--=--=-=-=-= CAMPOS DA NFCe =-==-=-=-==-=- 
        nfce.put("data_emissao", "2018-01-15T16:25:00");
        nfce.put("consumidor_final", "1");
        nfce.put("modalidade_frete", "9");
        nfce.put("natureza_operacao", "Venda ao Consumidor");
        nfce.put("tipo_documento", "1");
        nfce.put("finalidade_emissao", "1");
        nfce.put("presenca_comprador", "1");
        nfce.put("indicador_inscricao_estadual_destinatario", "9");
        nfce.put("cnpj_emitente", "51916585000125");
        nfce.put("cpf_destinatario", "");
        nfce.put("id_estrangeiro_destinatario", "1234567");
        nfce.put("nome_destinatario", "NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        nfce.put("informacoes_adicionais_contribuinte", "Documento emitido por ME ou EPP optante pelo Simples Nacional nao gera direito a credito fiscal de ICMS lei 123/2006.");
        nfce.put("valor_produtos", "1.0000");
        nfce.put("valor_desconto", "0.0000");
        nfce.put("valor_total", "1.0000");
        nfce.put("forma_pagamento", "0");
        nfce.put("icms_base_calculo", "0.0000");
        nfce.put("icms_valor_total", "0.0000");
        nfce.put("icms_base_calculo_st", "0.0000");
        nfce.put("icms_valor_total_st", "0.0");
        nfce.put("icms_modalidade_base_calculo", "3");
        nfce.put("valor_frete", "0.0");
         */
    /*CAMPOS  ITENS
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
*/
 
 /*     CAMPOS DA FORMA DE PAGAMENTO
        formasPagamento.put("forma_pagamento", "99");
        formasPagamento.put("valor_pagamento", "1.0000");
 */
         

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

            
            

