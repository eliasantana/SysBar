/*
 * Esta classe concentra e define o acesso a classe controle e seus métodos
 * Create: 24/11/2018
 * Versão: 1.0
 * 
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.DadosEmpresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Elisa Santana
 */
public class ControlerDadosEmpresa {
    
    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Este método adiciona dados no banco de dados
     *
     * @param d Dados Empresa
     * @return res retorna TRUE ou FALSE
     */
    public boolean adicionaDados(DadosEmpresa d) {

        boolean resp = false;
        String sql = "INSERT INTO tb_dados_empresa  "
                + "(nome_empresa, endereco, numero, bairro, cep, cidade, uf, telefone, celular, email, logo, cnpj, urlbackup) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, d.getNome_empresa());
            pst.setString(2, d.getEndereco());
            pst.setInt(3, d.getNumero());
            pst.setString(4, d.getBairro());
            pst.setString(5, d.getCep());

            pst.setString(6, d.getCidade());
            pst.setString(7, d.getUf());
            pst.setString(8, d.getTelefone());
            pst.setString(9, d.getCelular());
            pst.setString(10, d.getEmail());
            pst.setString(11, d.getLogo());
            pst.setString(12, d.getCnpj());
            pst.setString(13, d.getUrlbackup());

            pst.executeUpdate();

            resp = true;

        } catch (SQLException e) {

            System.out.println("br.com.br.controler.ControlerDadosEmpresa.adicionaDados()" + e);
        }
        return resp;
    }

    /**
     * Seleciona os dados da empresa no banco e retorna um objeto do tipo dados
     *
     * @return d dados
     *
     */
    public DadosEmpresa selecionaDados() {
        //nome_empresa, endereco, numero, bairro, cep, cidade, uf, telefone, celular, email, logo, cnpj, urlbackup
        String sql = "SELECT * FROM tb_dados_empresa ";
        DadosEmpresa d = new DadosEmpresa();

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                d.setId(rs.getInt("id"));
                d.setNome_empresa(rs.getString("nome_empresa"));
                d.setEndereco(rs.getString("endereco"));
                d.setBairro(rs.getString("bairro"));
                d.setNumero(rs.getInt("numero"));
                d.setCep(rs.getString("cep"));
                d.setCidade(rs.getString("cidade"));
                d.setUf(rs.getString("uf"));
                d.setTelefone(rs.getString("telefone"));
                d.setCelular(rs.getString("celular"));
                d.setEmail(rs.getString("email"));
                d.setLogo(rs.getString("logo"));
                d.setCnpj(rs.getString("cnpj"));
                d.setUrlbackup(rs.getString("urlbackup"));
                d.setImprimir_na_tela(rs.getInt("imprimir_na_tela"));
                d.setLicenca(rs.getString("chave"));
                d.setDts(rs.getString("dts"));
                d.setBkp_auto(rs.getInt("bkp_auto"));
                d.setAtivaDelivery(rs.getInt("delivery"));
                d.setAtivaCozinha(rs.getInt("cozinha"));
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDadosEmpresa.selecionaDados()" + e);
        }
        
        return d;
    }

    public boolean excluiEmpresa(DadosEmpresa empresa) {
        boolean resp = false;
        String sql = "DELETE FROM tb_dados_empresa WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, empresa.getId());
            pst.executeUpdate();

            resp = true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDadosEmpresa.excluiEmpresa()" + e);
        }
        return resp;
    }

    public boolean alteraDados(DadosEmpresa d) {

        boolean resp = false;
              
        String sql = "UPDATE tb_dados_empresa  SET"
                + " nome_empresa=?, endereco=?, numero=?, bairro=?, cep=?, cidade=?, uf=?, telefone=?, celular=?, email=?, logo=?, cnpj=?,complemento=?, urlbackup=?, imprimir_na_tela=?  "
                + " WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, d.getNome_empresa());
            pst.setString(2, d.getEndereco());
            pst.setInt(3, d.getNumero());
            pst.setString(4, d.getBairro());
            pst.setString(5, d.getCep());

            pst.setString(6, d.getCidade());
            pst.setString(7, d.getUf());
            pst.setString(8, d.getTelefone());
            pst.setString(9, d.getCelular());
            pst.setString(10, d.getEmail());
            pst.setString(11, d.getLogo());
            pst.setString(12, d.getCnpj());
            pst.setString(13, d.getComplemento());
            pst.setString(14, d.getUrlbackup());
            pst.setInt(15, d.getImprimir_na_tela());
            pst.setInt(16, d.getId());
            
            pst.executeUpdate();

            resp = true;
        } catch (SQLException e) {
                System.out.println("br.com.br.controler.ControlerDadosEmpresa.alteraDados()" +e);
        }

        return resp;
    }
    
    public int localizaIdEmpresa(String nome){
       int id=0;
        String sql="SELECT id FROM tb_dados_empresa WHERE nome_empresa=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rs=pst.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt(("id"));
            }
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDadosEmpresa.localizaIdEmpresa()" + e);
        }
        return id;
    }
    
    public boolean ativaBackup(String valor){
        String sql="UPDATE tb_dados_empresa SET bkp_auto=? WHERE id>0";
        boolean resp=false;
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, valor);
            pst.executeUpdate();
            resp=true;
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerDadosEmpresa.ativaBackup()"+e);
        }
        return resp;
    }
    
    // Verifica o tipo de Backup
    public boolean tipoBAckup(){
            boolean resp=false;
            int auto=0;
         String sql="SELECT bkp_auto FROM tb_dados_empresa";
         try {
            pst=conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
               auto = rs.getInt("bkp_auto");
            }
            if (auto==1){
                resp=true;
            }
        } catch (SQLException e) {
             System.out.println("br.com.br.controler.ControlerDadosEmpresa.tipoBAckup()");
        }
         return resp;
    }
    
    //Ativa ou Desativa o parâmetro Delivery    
    public boolean ativaDelivery(int vlr){
        boolean resp = false;
        
        String sql="UPDATE tb_dados_empresa SET delivery =? WHERE ID > 0";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setInt(1,vlr);
            pst.executeUpdate();
            resp=true;
            
        } catch (SQLException e) {
            System.out.println("Não foi possível ativar o Delivery "+e);
        }
        
        return resp;
        
    }
    
    //Ativa ou desativa o envio de pratos para a cozinha
    public boolean ativaEnvioCozinha(int vlr){
        boolean resp = false;
        
        String sql="UPDATE tb_dados_empresa SET cozinha =? WHERE ID > 0";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setInt(1,vlr);
            pst.executeUpdate();
            resp=true;
            
        } catch (SQLException e) {
            System.out.println("Não foi possível ativar o evio de prato para a cozinha "+e);
        }
        
        return resp;
        
    }
}
