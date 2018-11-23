/*
 * Esta classe concentra e define o acesso a classe controle e seus métodos
 * 
 * 
 */
package br.com.br.controler;

import br.com.bar.dao.ConexaoBd;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Elisa Santana
 */
public class ControlerDadosEmpresa {
   
    Connection conexao = ConexaoBd.conector();
    PreparedStatement pst=null;
    Resultset rs = null;
    
    
    
    
}
