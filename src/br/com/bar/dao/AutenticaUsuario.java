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
                usuarioLogin = rs.getString("login");
                usuarioSenha = rs.getString("senha");
                usuarioCargo = rs.getString("cargo");
                usuarioStatus = rs.getString("status");
                usuarioBloqueio = rs.getString("bloqueado");

            }
            // Se usuário for bloqueado exibe mensagem de usuário bloqueado
            if (null == usuarioLogin) {

                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
            } else {

                if (usuarioBloqueio.equals("1")) {
                    JOptionPane.showMessageDialog(null, "Usuário Bloqueado! \n Procure um Administrador!");
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

}
