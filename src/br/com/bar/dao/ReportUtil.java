/*
 *Esta classe é responsável por concentrar os métodos responsáveis pela impressão
 * dos relatórios do Sistema
 * Create:
 * Versão 1.1
 */
package br.com.bar.dao;

import br.com.bar.model.DadosEmpresa;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
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
                System.out.println("br.com.bar.dao.ReportUtil.imprimiRelatorioTela() " +relatorio+" " +e);           
        }      

    }
    // Este método abre na tela um relatório passado como parâmetro
    public void imprimiRelatorioTela(String relatorio) throws JRException {
        // Instancia o objeto 

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(url + relatorio, null, conexao);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (NullPointerException e) {
                System.out.println("br.com.bar.dao.ReportUtil.imprimiRelatorioTela() " +relatorio+" " +e);
                JOptionPane.showMessageDialog(null, "Você não possuí movimentação!");
                
            
        }      

    }
    
    //Imprime Relatório direto na impressora
    public void impressaoDireta(String relatorio, HashMap map){
        try {
            JasperPrint printLocal = JasperFillManager.fillReport(url+relatorio,map,conexao);
            JasperPrintManager.printPage(printLocal, 0, false);
            
        } catch (JRException e) {
            System.out.println("br.com.bar.dao.ReportUtil.impressaoDireta()"+e);
        }
    }
    
    public HashMap rodape(DadosEmpresa d) {
        HashMap map = new HashMap();
        map.put("end1", d.getNome_empresa() + " - " + " Endereço: " + d.getEndereco() + "," + d.getNumero() + " - " + "Bairro: " + d.getBairro() + " - " + " Cidade: " + d.getCidade());
        map.put("end2", "CEP: "+d.getCep() + " - " + "UF :"  +d.getUf() + " Telefone: " + d.getTelefone() + " email-" + d.getEmail());
        map.put("cnpj", "C.N.P.J " + d.getCnpj());
        map.put("logo", d.getLogo());
        
        return map;
    }
    public HashMap rodape(DadosEmpresa d, HashMap map) {
        
        map.put("end1", d.getNome_empresa() + " - " + " Endereço: " + d.getEndereco() + "," + d.getNumero() + " - " + "Bairro: " + d.getBairro() + " - " + " Cidade: " + d.getCidade());
        map.put("end2", "CEP: "+d.getCep() + " - " + "UF :"  +d.getUf() + " Telefone: " + d.getTelefone() + " email-" + d.getEmail());
        map.put("cnpj", "C.N.P.J " + d.getCnpj());
        map.put("logo", d.getLogo());
        
        return map;
    }
}
