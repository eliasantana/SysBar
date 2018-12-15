/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.dao.CriptoGrafa;
import br.com.bar.model.DadosEmpresa;
import br.com.bar.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Elias Santana
 */
public class ControlerAtivacao {
    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    CriptoGrafa c = new CriptoGrafa();
    ControlerDadosEmpresa ce = new ControlerDadosEmpresa();
    DadosEmpresa empresa = ce.selecionaDados();
    Util u = new Util();
    public ControlerAtivacao() {
        
    }
    // Atualiza a licença de uso
    public boolean renovaLicenca(String dataRenovacao,String chave){
        
        boolean resp = false;
        String sql="UPDATE tb_dados_empresa SET dts=?, chave=? WHERE id=?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, dataRenovacao);
            pst.setString(2, chave);
            pst.setInt(3, empresa.getId());
            
            pst.executeUpdate();
            resp=true;
                    
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerAtivacao.renovaLicenca()"+e);
        }
        return  resp;
    }
    
    public boolean validaLicenca(String chave){
        Calendar c = Calendar.getInstance();
        
        String dataAtual=u.formataDataBanco(c.getTime());
        System.out.println("Data Atual" + dataAtual);
        boolean resp=false;
        
         
        
        return  resp;
    }
    
}
