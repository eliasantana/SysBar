/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author elias
 */
public class ControlerParametro {

    
    //Connection conexao = ConexaoBd.conector();
    //PreparedStatement pst = null;
    //ResultSet rs = null;
    
    private final static String NOME_EMPRESA = "SysBar - Sistema Gerenciamentode Bar";
    private final static String CAMINHO_PARAMETRO = "C:/SysBar/param.txt";
    private final static String CAMINHO_FOTO_FUNCIONARIO = "C:/SysBar/Fotos";
    private final static String RELATORIOS ="/../Sysbar/rel/";

    public static String getRELATORIOS() {
        return RELATORIOS;
    }

    public static String getCAMINHO_FOTO_FUNCIONARIO() {
        return CAMINHO_FOTO_FUNCIONARIO;
    }

    public static String getCAMINHO_PARAMETRO() {
        return CAMINHO_PARAMETRO;
    }

    private final static int QTD_TENTATIVA = 3;

    public static int getQTD_TENTATIVA() {
        return QTD_TENTATIVA;
    }

    public void criarArquivoParametro(String ipServidor, String usuario, String senha, String nomeBanco, String porta) {

        try {

            FileWriter arquivoParametro = new FileWriter(CAMINHO_PARAMETRO);
            PrintWriter gravaARquivo = new PrintWriter(arquivoParametro);

            gravaARquivo.println(ipServidor);
            gravaARquivo.println(usuario);
            gravaARquivo.println(senha);
            gravaARquivo.println(nomeBanco);
            gravaARquivo.println(porta);

            gravaARquivo.flush();
            gravaARquivo.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo de parâmetro"+e);
        }

    }

    public ArrayList<String> lerArquivoParametro() {
        if (arquivoExiste()){
            
        }else {
            System.out.println("não localizado");
            criarArquivoParametro("localhost", "usuario", "password", "dbbar", "3306");
        }
        ArrayList<String> parametros = new ArrayList<>();

        try {
            
            File arquivoParametro = new File(CAMINHO_PARAMETRO);

            FileReader ler = new FileReader(arquivoParametro);

            BufferedReader reader = new BufferedReader(ler);
            String dados = null;

            while ((dados = reader.readLine()) != null) {
                parametros.add(dados);
                
            }

        } catch (IOException e) {
            
        }

        return parametros;
    }

    public String localizaIdFuncionario(String login) {
        Connection conexao = ConexaoBd.conector();
        PreparedStatement pst = null;
        ResultSet rs = null;

        String id = null;
        String sql = "SELECT id,login,status FROM tbcadfuncionario where login=?";

        //Localiza id do usuário
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            rs = pst.executeQuery();

            while (rs.next()) {

                id = rs.getString("id");

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro localizaIdFuncionario" + e);

        }
        return id;
    }

    public void bloqueiaLogin(String id) {
        Connection conexao = ConexaoBd.conector();
        PreparedStatement pst = null;
        ResultSet rs = null;
        if (id != null) {

            String sql2 = "UPDATE tbcadfuncionario set bloqueado=1 where id=?";

            try {
                pst = conexao.prepareStatement(sql2);
                pst.setString(1, id);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Usuário bloqueado, procure o administrador do sistema!");

            } catch (HeadlessException | SQLException e) {
                System.out.println("br.com.br.controler.ControlerParametro.bloqueiaLogin()"+e);

            }
        }
    }

    public boolean arquivoExiste() {

        File arqParam = new File(CAMINHO_PARAMETRO);

        if (arqParam.exists()) {

            return true;
        } else {

            return false;
        }
    }

}
