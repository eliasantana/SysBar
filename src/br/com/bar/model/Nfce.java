/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Elias Santana
 */
public class Nfce {
        private String consumidor_final;
        private String presenca_comprador;
        private String forma_pagamentoNfe;
        private String natureza_operacao;
        private String tipo_documento;
        private String cpf_destinatario;
        private String icms_valor_total;
        private String icms_modalidade_base_calculo;
        private String valor_freteNfe;
        private String modalidade_frete;
        private String icms_base_calculo;
        private String data_emissao;
        private String cnpj_emitente;
        
        private String numero_item;
        private String codigo_produto;
        private String valor_desconto;
        private String quantidade_comercial;
        private String cfop;
        private String icms_situacao_tributaria;
        private String unidade_comercial;
        private String descricao;
        private String pis_situacao_tributaria;
        private String codigo_ncm;
        private String quantidade_tributavel;
        private String unidade_tributavel;
        private String cofins_situacao_tributaria;
        private String valor_unitario_comercial;
        private String icms_origem;
        private String valor_bruto;
        private String valor_unitario_tributavel;
        private String valor_frete;
        private String forma_pagamento;
        private String valor_pagamento;

    public Nfce() {
    }

    public String getConsumidor_final() {
        return consumidor_final;
    }

    public void setConsumidor_final(String consumidor_final) {
        this.consumidor_final = consumidor_final;
    }

    public String getPresenca_comprador() {
        return presenca_comprador;
    }

    public void setPresenca_comprador(String presenca_comprador) {
        this.presenca_comprador = presenca_comprador;
    }

    public String getForma_pagamentoNfe() {
        return forma_pagamentoNfe;
    }

    public void setForma_pagamentoNfe(String forma_pagamentoNfe) {
        this.forma_pagamentoNfe = forma_pagamentoNfe;
    }

    public String getNatureza_operacao() {
        return natureza_operacao;
    }

    public void setNatureza_operacao(String natureza_operacao) {
        this.natureza_operacao = natureza_operacao;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getCpf_destinatario() {
        return cpf_destinatario;
    }

    public void setCpf_destinatario(String cpf_destinatario) {
        this.cpf_destinatario = cpf_destinatario;
    }

    public String getIcms_valor_total() {
        return icms_valor_total;
    }

    public void setIcms_valor_total(String icms_valor_total) {
        this.icms_valor_total = icms_valor_total;
    }

    public String getIcms_modalidade_base_calculo() {
        return icms_modalidade_base_calculo;
    }

    public void setIcms_modalidade_base_calculo(String icms_modalidade_base_calculo) {
        this.icms_modalidade_base_calculo = icms_modalidade_base_calculo;
    }

    public String getValor_freteNfe() {
        return valor_freteNfe;
    }

    public void setValor_freteNfe(String valor_freteNfe) {
        this.valor_freteNfe = valor_freteNfe;
    }

    public String getModalidade_frete() {
        return modalidade_frete;
    }

    public void setModalidade_frete(String modalidade_frete) {
        this.modalidade_frete = modalidade_frete;
    }

    public String getIcms_base_calculo() {
        return icms_base_calculo;
    }

    public void setIcms_base_calculo(String icms_base_calculo) {
        this.icms_base_calculo = icms_base_calculo;
    }

    public String getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(String data_emissao) {
        this.data_emissao = data_emissao;
    }

    public String getCnpj_emitente() {
        return cnpj_emitente;
    }

    public void setCnpj_emitente(String cnpj_emitente) {
        this.cnpj_emitente = cnpj_emitente;
    }

    public String getNumero_item() {
        return numero_item;
    }

    public void setNumero_item(String numero_item) {
        this.numero_item = numero_item;
    }

    public String getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(String codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public String getValor_desconto() {
        return valor_desconto;
    }

    public void setValor_desconto(String valor_desconto) {
        this.valor_desconto = valor_desconto;
    }

    public String getQuantidade_comercial() {
        return quantidade_comercial;
    }

    public void setQuantidade_comercial(String quantidade_comercial) {
        this.quantidade_comercial = quantidade_comercial;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getIcms_situacao_tributaria() {
        return icms_situacao_tributaria;
    }

    public void setIcms_situacao_tributaria(String icms_situacao_tributaria) {
        this.icms_situacao_tributaria = icms_situacao_tributaria;
    }

    public String getUnidade_comercial() {
        return unidade_comercial;
    }

    public void setUnidade_comercial(String unidade_comercial) {
        this.unidade_comercial = unidade_comercial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPis_situacao_tributaria() {
        return pis_situacao_tributaria;
    }

    public void setPis_situacao_tributaria(String pis_situacao_tributaria) {
        this.pis_situacao_tributaria = pis_situacao_tributaria;
    }

    public String getCodigo_ncm() {
        return codigo_ncm;
    }

    public void setCodigo_ncm(String codigo_ncm) {
        this.codigo_ncm = codigo_ncm;
    }

    public String getQuantidade_tributavel() {
        return quantidade_tributavel;
    }

    public void setQuantidade_tributavel(String quantidade_tributavel) {
        this.quantidade_tributavel = quantidade_tributavel;
    }

    public String getUnidade_tributavel() {
        return unidade_tributavel;
    }

    public void setUnidade_tributavel(String unidade_tributavel) {
        this.unidade_tributavel = unidade_tributavel;
    }

    public String getCofins_situacao_tributaria() {
        return cofins_situacao_tributaria;
    }

    public void setCofins_situacao_tributaria(String cofins_situacao_tributaria) {
        this.cofins_situacao_tributaria = cofins_situacao_tributaria;
    }

    public String getValor_unitario_comercial() {
        return valor_unitario_comercial;
    }

    public void setValor_unitario_comercial(String valor_unitario_comercial) {
        this.valor_unitario_comercial = valor_unitario_comercial;
    }

    public String getIcms_origem() {
        return icms_origem;
    }

    public void setIcms_origem(String icms_origem) {
        this.icms_origem = icms_origem;
    }

    public String getValor_bruto() {
        return valor_bruto;
    }

    public void setValor_bruto(String valor_bruto) {
        this.valor_bruto = valor_bruto;
    }

    public String getValor_unitario_tributavel() {
        return valor_unitario_tributavel;
    }

    public void setValor_unitario_tributavel(String valor_unitario_tributavel) {
        this.valor_unitario_tributavel = valor_unitario_tributavel;
    }

    public String getValor_frete() {
        return valor_frete;
    }

    public void setValor_frete(String valor_frete) {
        this.valor_frete = valor_frete;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public String getValor_pagamento() {
        return valor_pagamento;
    }

    public void setValor_pagamento(String valor_pagamento) {
        this.valor_pagamento = valor_pagamento;
    }
        
        
    
    
}
