/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

/**
 *
 * @author Elias Santana
 */
public class NFCeCancelamento {

    private String status_sefaz;
    private String mensagem_sefaz;
    private String status;
    private String caminho_xml_cancelamento;
    
    public NFCeCancelamento() {
        
        
    }

    public String getStatus_sefaz() {
        return status_sefaz;
    }

    public void setStatus_sefaz(String status_sefaz) {
        this.status_sefaz = status_sefaz;
    }

    public String getMensagem_sefaz() {
        return mensagem_sefaz;
    }

    public void setMensagem_sefaz(String mensagem_sefaz) {
        this.mensagem_sefaz = mensagem_sefaz;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaminho_xml_cancelamento() {
        return caminho_xml_cancelamento;
    }

    public void setCaminho_xml_cancelamento(String caminho_xml_cancelamento) {
        this.caminho_xml_cancelamento = caminho_xml_cancelamento;
    }

    @Override
    public String toString() {
        return "NFCeCancelamento{" + "status_sefaz=" + status_sefaz + ", mensagem_sefaz=" + mensagem_sefaz + ", status=" + status + ", caminho_xml_cancelamento=" + caminho_xml_cancelamento + '}';
    }
    
    
    
}
