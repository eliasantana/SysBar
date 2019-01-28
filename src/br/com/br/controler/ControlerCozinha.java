/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import br.com.bar.model.TableModelCozinha;
import br.com.bar.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 *
 * @author elias
 */
public class ControlerCozinha {

    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    Util u = new Util();
    TableModelCozinha modelTelaCozinha = new TableModelCozinha();
    // Lista pratos enviados para cozinha
    // Adicionado na versão
    /*
    public ResultSet listaProdutosCozinha() {
        /* ******* ATENCAO ********
        ANALISAR SE O TIME ZONE PERMANCERA COM -03H00 RELACIONADAS A HORA ATUAL 
        SENDO NECESSARIO ALTERAR A INSTRUCAO ABAIXO:
        >> TIME_FORMAT(TIME(DATE_ADD(curtime(), INTERVAL +3 HOUR)),'%T')
        
        String sql = "SELECT \n"
                + "	 id AS 'SEQ',\n"
                + "	 produto AS 'PRATO',\n"
                + "	 qtd AS 'QTD',\n"
                + "	 funcionario AS 'GARÇOM',\n"
                + "	 mesa AS 'N. MESA',\n"
                + "     CASE \n"
                + "       WHEN cozinheiro IS NULL THEN 'Não informado'\n"
                + "	   ELSE cozinheiro\n"
                + "	 END AS 'COZINHEIRO',\n"
                + "     TIME_FORMAT(hora_solicitacao,'%T') AS 'SOLICITADO',\n"
                + "     TIMEDIFF(TIME_FORMAT(TIME(DATE_ADD(curtime(), INTERVAL +3 HOUR)),'%T'), "
                + "     TIME_FORMAT(hora_solicitacao,'%T')) AS 'T. ESPERA',\n"
                + "     status AS 'STATUS'\n"
                + "\n"
                + "  FROM dbbar.tbcozinha \n"
                + " WHERE status IN ('Pendente', 'Em preparação');";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCozinha.listaProdutosCozinha()"+e);
        }

        return rs;
    }*/
    public ResultSet listaProdutosCozinha() {
        /* ******* ATENCAO ********
        ANALISAR SE O TIME ZONE PERMANCERA COM -03H00 RELACIONADAS A HORA ATUAL 
        SENDO NECESSARIO ALTERAR A INSTRUCAO ABAIXO:
        >> TIME_FORMAT(TIME(DATE_ADD(curtime(), INTERVAL +3 HOUR)),'%T')
        */
        String sql = "SELECT \n"
                + "	 id AS 'SEQ',\n"
                + "	 produto AS 'PRATO',\n"
                + "	 qtd AS 'QTD',\n"
                + "	 funcionario AS 'GARÇOM',\n"
                + "	 mesa AS 'N. MESA',\n"
                + "     CASE \n"
                + "       WHEN cozinheiro IS NULL THEN 'Não informado'\n"
                + "	   ELSE cozinheiro\n"
                + "	 END AS 'COZINHEIRO',\n"
                + "     TIME_FORMAT(hora_solicitacao,'%T') AS 'SOLICITADO',\n"
                + "     TIMEDIFF(TIME_FORMAT(TIME(DATE_ADD(curtime(), INTERVAL +3 HOUR)),'%T'), "
                + "     TIME_FORMAT(hora_solicitacao,'%T')) AS 'T. ESPERA',\n"
                + "     status AS 'STATUS'\n"
                + "\n"
                + "  FROM dbbar.tbcozinha \n"
                + " WHERE status IN ('Pendente', 'Em preparação');";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCozinha.listaProdutosCozinha()"+e);
        }

        return rs;
    }
    

    public ArrayList estatisticas(ResultSet rs) {
        int pendente = 0;
        int liberados = 0;
        String status = null;

        ArrayList<Integer> resultado;
        resultado = new ArrayList<>();

        try {
            while (rs.next()) {

                status = rs.getString("status");
                if (status.equals("Pendente")) {
                    pendente = pendente + 1;
                } else {
                    liberados = liberados + 1;
                }
            }
            resultado.add(pendente);
            resultado.add(liberados);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro estatiscicas()" + e);
        }
        return resultado;
    }

    public boolean liberaProduto(String id, String tempoPreparacao) {

        String sql = "UPDATE tbcozinha SET status='Liberado', tempo_preparacao=? WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tempoPreparacao);
            pst.setString(2, id);
            pst.executeUpdate();
            
            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "erro liberaProduto()" + e);
        }

        return false;
    }

    // Retorna a lista dos produtos enviados para a cozinha pelo operador garçom
    public ResultSet statusCozinha(String operador) {

        // Listas os pratos enviados a cozinha pelo garçom
        String sql = "SELECT\n"
                + "npedido as 'N.PEDIDO',\n"
                + "produto as 'PRATO', \n"
                + "qtd as 'QTD', \n"
                + "mesa as 'N. MESA', \n"
                + "status as 'STATUS'\n"
                + "FROM \n"
                + "dbbar.tbcozinha where funcionario=? and data=curdate()\n"
                + "ORDER BY id asc;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, operador);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erroStatusCozinha" + e);
        }

        return rs;
    }

    public int pratoPendente() {

        int qtd = 0;
        String sql = "SELECT * FROM dbbar.tbcozinha where status='pendente'";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                qtd = qtd + 1;
            }

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCozinha.pratoPendente()");
        }

        return qtd;
    }

    // Remove produto da tabela cozinha 
    // Recurso utilizado quando o produto for descartado
    public boolean removePrato(String idProduto) {
        boolean resp = false;

        String sql = "DELETE FROM tbcozinha where id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idProduto);
            pst.executeUpdate();
            resp = true;

        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCozinha.removePrato()" + e);
        }

        return resp;
    }

    /*
     * Registra a hora em que foi iniciada a preparação do prato pelo cozinheiro
     */
    public void registraPreparo(String idTbCozinha, String nomeCozinheiro) {

        Date dtAtual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String horaPreparo = (sdf.format(dtAtual));

        String sql = "UPDATE tbcozinha SET hora_preparacao=?, cozinheiro=?, status='Em preparação' WHERE id=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, horaPreparo);
            pst.setString(2, nomeCozinheiro);
            pst.setString(3, idTbCozinha);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Preparo iniciado com sucesso!");
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerCozinha.registraPreparo()" + e);

        }
    }
    /*Excluir caso não seja possível identificar  o prato na cozinha
    // Verifica se o produto a ser enviado para a cozinha já existe na tabela
    public boolean temTrodutoNaCozinha(String idProduto, String nPedido){
       boolean resp=false;
       String sql="SELECT codProduto,produto,npedido,status FROM tbcozinha WHERE  codproduto=? and npedido=?  "
               + "and status in('Pendente','Em preparação');";
        ArrayList<String> listaCozinha = new ArrayList<>();
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, idProduto);
            pst.setString(2, nPedido);
            rs=pst.executeQuery();
            String msg = null;
            String  msgExibicao = null;   
            
            while (rs.next()){
                msg = rs.getString("codProduto")+ " " +rs.getString("Produto")+" "+rs.getString("Produto")+"\n";
                msgExibicao = msgExibicao+msg;
                System.out.println("Msg"+msgExibicao);
                resp=true;
            }
            if ("".equals(msgExibicao)){
                
            JOptionPane.showMessageDialog(null, msgExibicao);
            }
        } catch (SQLException e) {
            System.out.println("br.com.br.controler.ControlerProduto.temTrodutoNaCozinha()"+e);
        }
        
        return resp;
    }
   */
}
