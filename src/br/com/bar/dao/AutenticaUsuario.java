/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class AutenticaUsuario {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;

    String usuarioLogin = null;
    String usuarioSenha = null;
    String usuarioCargo = null;
    String usuarioStatus = null;
    String usuarioBloqueio = null;

    // Autentica Usuário
    public boolean autentica(String login, String senha) {

        String sql = "SELECT login,senha,cargo,status,bloqueado FROM tbcadfuncionario where login=? and senha=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);

            rs = pst.executeQuery();
            // Localiza dados do usuário 
            while (rs.next()) {
                usuarioLogin = rs.getString("login").toLowerCase();
                usuarioSenha = rs.getString("senha").toLowerCase();
                usuarioCargo = rs.getString("cargo");
                usuarioStatus = rs.getString("status");
                usuarioBloqueio = rs.getString("bloqueado");

            }
            // Se usuário for bloqueado exibe mensagem de usuário bloqueado
            if (null == usuarioLogin) {

                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Atenção", JOptionPane.ERROR_MESSAGE);
            } else {

                if (usuarioBloqueio.equals("1")) {
                    JOptionPane.showMessageDialog(null, "Usuário Bloqueado! \n Procure um Administrador!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                } else {

                    // Caso usuário não seja bloqueado ou localizado será checado a senha e login
                    if (usuarioLogin.equals(login) && usuarioSenha.equals(senha)) {

                        return true;

                    }
                }//login nulo
            }
        } catch (HeadlessException | SQLException e) {

        }

        return false;

    }

    // Autentica Usuário
    public boolean autentica2(String login, String senha) {
        boolean resp = false;
        String sql = "SELECT login,senha,cargo,status,bloqueado FROM tbcadfuncionario where login=? and senha=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);

            rs = pst.executeQuery();
            // Localiza dados do usuário 
            while (rs.next()) {
                usuarioLogin = rs.getString("login").toLowerCase();
                usuarioSenha = rs.getString("senha").toLowerCase();
                usuarioCargo = rs.getString("cargo");
                usuarioStatus = rs.getString("status");
                usuarioBloqueio = rs.getString("bloqueado");

            }
            // Caso usuário não senha localizado pula a comparação de senha.
            if (usuarioSenha!=null){
                   if (usuarioSenha.equals(senha.toLowerCase())) {

                    resp = true;
             }
            }
           

          

        } catch (HeadlessException | SQLException e) {
            System.out.println("br.com.bar.dao.AutenticaUsuario.autentica2()" + e);
        }

        return resp;
    }

    public String enviaOperador() {

        return usuarioLogin;
    }

    public String enviarCargo() {
        return usuarioCargo;
    }

    public String getStatus() {
        return usuarioStatus;
    }

    public String getBloqueio() {
        return usuarioBloqueio;
    }

    /**
     * Verifica se o usuário existe na base.
     *
     * @param usuario Usuário a ser pesquisado na base
     * @return Retorna TRUE caso o usuário exista.
     */
    public boolean isExistsLogin(String usuario) {
        boolean resp = false;
        String sql = "SELECT * FROM tbcadfuncionario WHERE login=?";
        String user = "";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario);
            rs = pst.executeQuery();

            while (rs.next()) {
                user = rs.getString("login");
            }

            if (usuario.equals(user)) {
                resp = true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.bar.dao.AutenticaUsuario.isExistsLogin()" + e);
        }
        return resp;
    }

    /**
     * Realiza a verificação da senha informada.
     *
     * @param senha Senha a ser verificada na base.
     * @return Retorna true para senha válida.
     */
    public boolean isExistsSenha(String senha) {
        boolean resp = false;
        String sql = "SELECT * FROM tbcadfuncionario WHERE senha=?";
        String password = "";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, senha);
            rs = pst.executeQuery();
            while (rs.next()) {
                password = rs.getString("senha");
            }
            // Verifica se a senha informada é igual a senha da base.
            if (senha.equals(password)) {
                resp = true;
            }
        } catch (SQLException e) {
            System.out.println("br.com.bar.dao.AutenticaUsuario.isExistsSenha()" + e);
        }
        return resp;
    }

    // Retorna a situação de Bloqueio
    public boolean taBloqueado(String login) {
        //String sql = "SELECT login,senha,cargo,status,bloqueado FROM tbcadfuncionario where login=? and senha=?";
        String sql = "SELECT login,senha,cargo,status,bloqueado FROM tbcadfuncionario where login=?";
        boolean resp = false;
        int bloqueio = 0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            //pst.setString(2, senha);
            rs = pst.executeQuery();

            while (rs.next()) {
                bloqueio = rs.getInt("bloqueado");
            }

            if (bloqueio == 1) {
                resp = true;
            }
        } catch (SQLException e) {
        }
        return resp;
    }

}
