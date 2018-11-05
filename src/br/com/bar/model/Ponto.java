/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

public class Ponto {

    private int tbcadfuncionario_id;
    private String data;
    private String entrada ;
    private String saida ;
    private String ent_inter;
    private String sai_inter ; 
    private String h_trab ; 
    private String h_extra ; 
    private int homologado ;
    private int tbocorrencia_id;

    public int getTbcadfuncionario_id() {
        return tbcadfuncionario_id;
    }

    public void setTbcadfuncionario_id(int datatbcadfuncionario_id) {
        this.tbcadfuncionario_id = datatbcadfuncionario_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getEnt_inter() {
        return ent_inter;
    }

    public void setEnt_inter(String ent_inter) {
        this.ent_inter = ent_inter;
    }

    public String getSai_inter() {
        return sai_inter;
    }

    public void setSai_inter(String sai_inter) {
        this.sai_inter = sai_inter;
    }

    public String getH_trab() {
        return h_trab;
    }

    public void setH_trab(String h_trab) {
        this.h_trab = h_trab;
    }

    public String getH_extra() {
        return h_extra;
    }

    public void setH_extra(String h_extra) {
        this.h_extra = h_extra;
    }

    public int getHomologado() {
        return homologado;
    }

    public void setHomologado(int homologado) {
        this.homologado = homologado;
    }

    public int getTbocorrencia_id() {
        return tbocorrencia_id;
    }

    public void setTbocorrencia_id(int tbocorrencia_id) {
        this.tbocorrencia_id = tbocorrencia_id;
    }

    
        
}
