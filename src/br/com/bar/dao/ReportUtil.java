/*
 *Esta classe é responsável por concentrar os métodos responsáveis pela impressão
 * dos relatórios do Sistema
 * Create:
 * Versão 1.1
 */
package br.com.bar.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Elias Santana
 *
 */
public class ReportUtil {

    JasperPrint print;
    Connection conexao = ConexaoBd.conector();
    String url = "C:/SysBar/Rel/";

    public void abreRelatorio(
            String caminho,
            String nomeRel,
            HashMap filtro
    ) {

        try {

            print = JasperFillManager.fillReport(caminho + nomeRel, filtro, conexao);
            JasperViewer.viewReport(print, false);

        } catch (JRException e) {
            System.out.println("br.com.bar.dao.ReportUtil.abreRelatorio()" + e);

        }

    }

    // Este método abre na tela um relatório passado como parâmetro
    public void imprimiRelatorioTela(String relatorio, HashMap map) throws JRException {
        // Instancia o objeto 

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(url + relatorio, map, conexao);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (NullPointerException e) {
                System.out.println("br.com.bar.dao.ReportUtil.imprimiRelatorioTela() " +relatorio +e);
                
            
        }
        

    }
}
