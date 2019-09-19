/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

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

public class Autorizar {

    public static void main(String[] args) throws JSONException {
        Util u = new Util();
        String login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";

        /* Substituir pela sua identificação interna da nota. */
        String ref = "72525"; // Código do pedido

        /* Para ambiente de produção use a variável abaixo:
         String server = "https://api.focusnfe.com.br/"; */
        String server = "https://homologacao.focusnfe.com.br/";        
        String url = server.concat("v2/nfce?ref="+ ref+"&completa=1");

        /* Configuração para realizar o HTTP BasicAuth. */
        Object config = new DefaultClientConfig();
        Client client = Client.create((ClientConfig) config);
        client.addFilter(new HTTPBasicAuthFilter(login, ""));

        /* Aqui são criados as hash's que receberão os dados da nota. */
        HashMap<String, String> nfce = new HashMap<String, String>();
        HashMap<String, String> itens = new HashMap<String, String>();
        HashMap<String, String> formasPagamento = new HashMap();

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
        
        // Itens da NfCe

        itens.put("numero_item", "1");
        itens.put("codigo_produto","0123456");
        itens.put("valor_desconto", "0.00");
        itens.put("quantidade_comercial", "1");
        itens.put("cfop", "5102"); // Venda de mercadoria adquirida ou recebida de terceiros
        itens.put("icms_situacao_tributaria", "103");
        itens.put("unidade_comercial", "un");
        itens.put("descricao", "NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        itens.put("pis_situacao_tributaria", "07");
        itens.put("codigo_ncm", "11041900");
        itens.put("quantidade_tributavel", "1");
        itens.put("unidade_tributavel", "un");
        itens.put("cofins_situacao_tributaria", "07");
        itens.put("valor_unitario_comercial", "1");
        itens.put("icms_origem", "0");
        itens.put("valor_bruto", "1");
        itens.put("valor_unitario_tributavel", "1");
        itens.put("valor_frete", "0");
        //itens.put("ipi_codigo_enquadramento_legal", "999");

        /* Depois de fazer o input dos dados, são criados os objetos JSON já com os valores das hash's. */
        JSONObject json = new JSONObject(nfce);
        JSONObject jsonItens = new JSONObject(itens);


        /* Aqui adicionamos os objetos JSON nos campos da API como array no JSON principal. */
        json.append("items", jsonItens);
        formasPagamento.put("forma_pagamento", "99");
        formasPagamento.put("valor_pagamento", "1");

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
        
        

//        try {
//            FileOutputStream out = new FileOutputStream("c://sysbar/saida.json");
//            PrintStream ps = new PrintStream(out);
//            System.setOut(ps);
//            System.out.println(json);
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Autorizar.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
   }
}
