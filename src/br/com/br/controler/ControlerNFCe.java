/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;
import br.com.bar.model.Autorizar;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String url = server.concat("v2/nfce?ref="+ ref+"&completa=1");

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

    public void consultarNFCE(String codPedidoNota, String arqRetorno) {
        
         String login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";

        /* Substituir pela sua identificação interna da nota. */
        String ref = codPedidoNota;

        /* Para ambiente de produção use a variável abaixo:
        String server = "https://api.focusnfe.com.br/"; */
        String server = "https://homologacao.focusnfe.com.br/";

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
            FileOutputStream out = new FileOutputStream("c://sysbar/"+arqRetorno);
            PrintStream ps = new PrintStream(out);
            System.setOut(ps);
           
             System.out.println(body);
            //System.out.println(json);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Autorizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
    public void nfcCancelamento(String motivo, String nPedido){
        String login = "npCjoFHIFKfhGjjC0VHDMVn1Bt5P0dim";

        /* Substituir pela sua identificação interna da nota. */
        String ref = nPedido;

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
    
    /**
     * Converte um arquivo de retorno String no formato JSon e converte em
     * JSONObjet utilizando a biblioteca org.json.simple
     * @param nomeArquivo  Nome do arquivo a ser lido
     * @return Retorna um Objeto NFCe contendo: 
     * 
     *      Chave de Autorização,
     *      Url de Consulta da Nota
     *      Série e Número
     *      Url do QrCod
     *      Número do Protocolo
     * @throws org.json.simple.parser.ParseException
     * @throws java.io.IOException
     */
    public Nfce lerRetorno(String nomeArquivo) throws ParseException, IOException{
        org.json.simple.JSONObject jo;
        JSONParser parser = new JSONParser();
        Nfce nota = new Nfce();
        
        try {
           //jo = (org.json.simple.JSONObject) parser.parse(new FileReader("C:\\Sysbar\\consulta.json"));
           jo = (org.json.simple.JSONObject) parser.parse(new FileReader("C:\\Sysbar\\"+nomeArquivo));
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
}

            
            

