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
import br.com.bar.view.TelaGerenciadorDeLicenca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public boolean renovaLicenca(String dataRenovacao, String chave) {

        boolean resp = false;
        String sql = "UPDATE tb_dados_empresa SET dts=?, chave=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, dataRenovacao);
            pst.setString(2, chave);
            pst.setInt(3, empresa.getId());

            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerAtivacao.renovaLicenca()" + e);
        }
        return resp;
    }
    // Adiciona uma licença trial caso não exista dados cadastrados na tabela cadastro_empresa.
    public boolean adicionaLicenca(String chave) {

        boolean resp = false;
        String sql = "INSERT INTO tb_dados_empresa (chave) VALUES (?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, chave);
           
            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerAtivacao.adicionaLicencaTrial()"+e);
        }
        return resp;
    }

    // Valida a licenda de acordo com a chave armazenada no banco
    public boolean validaLicenca() {
        boolean resp;
        String chave = c.decripta(empresa.getLicenca());

        long diasRestantes = u.retornaTotalDeDias(chave);

        System.out.println("Válida Até: " + chave);
        System.out.println("Dias Restantes: " + diasRestantes);
        

        // Checa se a licença é válida
        if (diasRestantes < 0) {
            
            resp=false;
            System.out.println("Licença Expirada!");
        } else {
            resp=true;
            System.out.println("Licença Válida!");
        }

        return resp;
    }
    
    

}
