/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.dao;

import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author elias
 */
public class ReportUtil {

    JasperPrint print;
    Connection conexao = ConexaoBd.conector();

    public void abreRelatorio(
            String caminho, 
            String nomeRel,
            HashMap filtro
            ) {
        
        try {
            
            print = JasperFillManager.fillReport(caminho + nomeRel, filtro, conexao);
            JasperViewer.viewReport(print, false);
            
        } catch (JRException e) {
            System.out.println("br.com.bar.dao.ReportUtil.abreRelatorio()"+e);
            
        }
        
    }
    
    
}
