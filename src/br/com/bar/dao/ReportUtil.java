/*
 *Esta classe é responsável por concentrar os métodos responsáveis pela impressão
 * dos relatórios do Sistema
 * Create:
 * Versão 1.1
 */
package br.com.bar.dao;

import br.com.bar.model.DadosEmpresa;

import java.io.IOException;

import java.sql.Connection;

import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
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

    /**
     * Este método abre o relatório passado como parâmetro
     * @param relatorio Nome do relatório a ser aberto.
     * @param map Mapa de parâmetro necessário para execução do relatório.
     * @param titulo Titulo do Relatório
     * @exception JRException
     */ 
    public void imprimeRelatorioTela(String relatorio, HashMap map, String titulo) throws JRException {
        // Instancia o objeto 
        /*
            Este métodos imprime um cumpom em tela, aqui foi necessário realizar as seguintes modificações
            - Criar um novo Frame e adicionar a visualização do cupom neste frame JDialog
              tornando-o modal para que apareça sempre emfrente a janela naqual o método foi chamado.
        */
            
        try {
            // Instancia um JDialog
            JDialog viewer = new JDialog(new javax.swing.JFrame(), titulo, true);
            viewer.setSize(800, 600);
            viewer.setLocationRelativeTo(null);
            //viewer.setAlwaysOnTop(true);
            JasperPrint jasperPrint = JasperFillManager.fillReport(url + relatorio, map, conexao);
            //JasperViewer.viewReport(jasperPrint, false);
            JasperViewer viewerJasper = new JasperViewer(jasperPrint);
            // Adiciona a janela de exibição do relatório dentro do novo JDialog
            viewer.getContentPane().add(viewerJasper.getContentPane());
            // Seta nova janela como modal.
            viewer.setModal(true);            
            // Exibe o relatório em tela.
            viewer.setVisible(true);
        } catch (NullPointerException e) {
            System.out.println("br.com.bar.dao.ReportUtil.imprimiRelatorioTela() " + relatorio + " " + e);
        }       

    }
    public void imprimeRelatorioTelaPDF(String relatorio, HashMap map, String titulo) throws JRException, IOException {
                   
        try {
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(url + relatorio, map, conexao);
            JasperExportManager.exportReportToPdfFile(jasperPrint, url);
            
            
            
        } catch (NullPointerException e) {
            System.out.println("br.com.bar.dao.ReportUtil.imprimiRelatorioTela() " + relatorio + " " + e);
        }       

    }

    // Este método abre na tela um relatório passado como parâmetro
    public void imprimiRelatorioTela(String relatorio) throws JRException {
        // Instancia o objeto 
        
            
        try {
         // Instancia um JDialog
            JDialog viewer = new JDialog(new javax.swing.JFrame(), "Comprovante", true);
            viewer.setSize(800, 600);
            viewer.setLocationRelativeTo(null);
            viewer.setAlwaysOnTop(true);
            JasperPrint jasperPrint = JasperFillManager.fillReport(url + relatorio, null, conexao);
            JasperViewer viewerJasper = new JasperViewer(jasperPrint);
             // Adiciona a janela de exibição do relatório dentro do novo JDialog
            viewer.getContentPane().add(viewerJasper.getContentPane());  // Seta nova janela como modal.
            viewer.setModal(true);    // Exibe o relatório em tela.
            viewer.setVisible(true);  //JasperViewer.viewReport(jasperPrint, false);

        } catch (NullPointerException e) {
            System.out.println("br.com.bar.dao.ReportUtil.imprimiRelatorioTela() " + relatorio + " " + e);
            JOptionPane.showMessageDialog(null, "Você não possuí movimentação!");

        }

    }

    //Imprime Relatório direto na impressora
    public void impressaoDireta(String relatorio, HashMap map) {
        try {
            JasperPrint printLocal = JasperFillManager.fillReport(url + relatorio, map, conexao);
            JasperPrintManager.printPage(printLocal, 0, false);

        } catch (JRException e) {
            System.out.println("br.com.bar.dao.ReportUtil.impressaoDireta()" + e);
        }
    }

    public HashMap rodape(DadosEmpresa d) {
        HashMap map = new HashMap();
        map.put("end1", d.getNome_empresa() + " - " + " Endereço: " + d.getEndereco() + "," + d.getNumero() + " - " + "Bairro: " + d.getBairro() + " - " + " Cidade: " + d.getCidade());
        map.put("end2", "CEP: " + d.getCep() + " - " + "UF :" + d.getUf() + " Telefone: " + d.getTelefone() + " email-" + d.getEmail());
        map.put("cnpj", "C.N.P.J " + d.getCnpj());
        map.put("logo", d.getLogo());

        return map;
    }

    public HashMap rodape(DadosEmpresa d, HashMap map) {

        map.put("end1", d.getNome_empresa() + " - " + " Endereço: " + d.getEndereco() + "," + d.getNumero() + " - " + "Bairro: " + d.getBairro() + " - " + " Cidade: " + d.getCidade());
        map.put("end2", "CEP: " + d.getCep() + " - " + "UF :" + d.getUf() + " Telefone: " + d.getTelefone() + " email-" + d.getEmail());
        map.put("cnpj", "C.N.P.J " + d.getCnpj());
        map.put("logo", d.getLogo());

        return map;
    }
}
