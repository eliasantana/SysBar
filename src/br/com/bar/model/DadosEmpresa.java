/*
 * Esta classe representa o cadastro dos dados da empresa
 * Create: 23/11/2018
 * 
 */
package br.com.bar.model;

/**
 *
 * @author Elias Santana
 */
public class DadosEmpresa {
    
    private int id;
    private String nome_empresa;
    private String endereco; 
    private int numero;
    private String bairro;
    private String cep; 
    private String cidade;    
    private String uf; 
    private String telefone;
    private String celular; 
    private String email; 
    private String logo;
    private String cnpj;
    private String urlbackup;
    private int imprimir_na_tela;
    private String licenca;
    private String dts;
    private String complemento;
    private int bkp_auto;
            
    
    
    public void DadadosEmpresa(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

   
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getUrlbackup() {
        return urlbackup;
    }

    public void setUrlbackup(String urlbackup) {
        this.urlbackup = urlbackup;
    }

    public void setImprimir_na_tela(int imprimir_na_tela) {
        this.imprimir_na_tela = imprimir_na_tela;
    }

    public int getImprimir_na_tela() {
        return imprimir_na_tela;
    }

    public void setLicenca(String chave) {
        this.licenca = chave;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setDts(String dts) {
        this.dts = dts;
    }

    public String getDts() {
        return dts;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setBkp_auto(int bkp_auto) {
        this.bkp_auto = bkp_auto;
    }

    public int getBkp_auto() {
        return bkp_auto;
    }
    
    public String retornaCnpj(){        
        String cnPj = this.cnpj;
        cnPj = cnPj.replace(".","");
        cnPj = cnPj.replace("-","");    
        cnPj = cnPj.replace("/","");
        return cnPj;
    }
    
    
}
