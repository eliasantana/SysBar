/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author elias
 */


public class Log {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    private String data;
    private String hora;
    private String usuario;
    private String funcionalidade;
    private String descricao;

    Date dataAtual = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat shf = new SimpleDateFormat("h:mm");

    public Log() {
    }

    public Log(String usuario, String funcionalidade, String descricao) {
        this.usuario = usuario;
        this.funcionalidade = funcionalidade;
        this.descricao = descricao;
    }


    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(String funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
    

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }
    
       
    
    public void gravaLog(Log l){
        
        String sql="INSERT INTO tb_log (data,hora,usuario,funcionalidade,descricao) VALUES (?,?,?,?,?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, sdf.format(dataAtual));
            pst.setString(2, shf.format(dataAtual));
            pst.setString(3, l.getUsuario());
            pst.setString(4, l.getFuncionalidade());
            pst.setString(5, l.getDescricao());
            
            pst.executeUpdate();
            
            
        } catch (SQLException e) {
            System.out.println("br.com.bar.dao.Log.gravaLog()" + e);
        }
        
    }
    
    
    
}
