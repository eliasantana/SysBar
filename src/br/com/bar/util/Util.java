/*
 * Nome da Classe: Util.  Criação: 01/07/2018
 * Esta Classe é resposável por concentrar os métodos com funcionalidades
 * Genéricas que podem ser utilizadas em outros lugares do sistema.
 * 
 */
package br.com.bar.util;

import br.com.bar.model.DadosEmpresa;
import br.com.br.controler.ControlerDadosEmpresa;
import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Elias Santana da Silva
 * @version 1.0
 */
public class Util {

    ControlerDadosEmpresa cd = new ControlerDadosEmpresa();

    /**
     * Retorna o número que representa o mês atual
     *
     * @param data - Data informada;
     * @return mes - Número do Mês da data informada;
     * @since Desde versão 1.0.8;
     *
     */
    public String formataData(Date data) {

        SimpleDateFormat df = new SimpleDateFormat("M");
        String mes = df.format(data);

        return mes;
    }

    /**
     * Retorna uma string com número que representa o ano atual.
     *
     * @param data - Data informada;
     * @return ano;
     * @since Desde versão 1.0.8;
     *
     */
    public String retornaAno(Date data) {

        SimpleDateFormat df = new SimpleDateFormat("YYYY");
        String ano = df.format(data);

        return ano;
    }
    
    /**
     * Recebe uma data no formato string (dd/mm/yyyy) e converte para o formado (yyyy-mm-dd)
     * @param data - Data Informada;
     * @return dataBanco - String no formado (yyyy-mm-dd);
     */
    public String formataDataBanco(String data) {

        String[] dataSeparada = data.split("/");       
        String dataBanco = String.valueOf(dataSeparada[2] + "-" + dataSeparada[1] + "-" + dataSeparada[0]);
        return dataBanco;
    }
    /**
     * Recebe um objeto Date e retorna uma string no formado MySql ano-mes-dia
     * @param data - Recebe a data informada;
     * @return dataBanco - Retorna uma String no formato MySql (yyyy-MM-dd); 
     */
    
    public String formataDataBanco(Date data) {
        
        String dataBanco = null;
        try {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //String dataBanco = String.valueOf(ldata.getYear()+String.valueOf(ldata.getMonth()+String.valueOf(ldata.getDayOfMonth())));
            dataBanco = df.format(data);

        } catch (Exception e) {
            System.out.println("Erro ao formatar data" + e);

        }
        return dataBanco;
    }
    /**
     * Recebe um objeto Date e retorna o mês no formado Date
     * @param data - Recebe a data informada;
     * @return mes - Retorna um objeto tipo Date;
     */  
    
    public String formataDataBr(Date data) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        String mes = df.format(data);

        return mes;
    }
     /**
     * Recebe um objeto DefaultCategoryDataSet e uma string com o título do gráfico
     * @param data - Parâmetro do tipo com o tipo de categoria;
     * @param titulo - Titulo do grárico;
     *
     */    
    public void geraGraficoBarras(DefaultCategoryDataset data, String titulo) {

        try {
            // Criando Gráfico
            JFreeChart chart = ChartFactory.createBarChart3D(titulo, "Volume de Vendas", "Valores em R$", data, PlotOrientation.VERTICAL, true, true, true);
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.BLACK);
            // Tamanho da Janela
            ChartFrame f = new ChartFrame("Gráfico - Ranking de Vendas por Garçom", chart);
            f.setSize(800, 600);
            f.setVisible(true);
        } catch (Exception e) {
            System.out.println("Erro ao gerarGraficoDeBarras");
        }

    }

    public String formataDataHora(Date data, String opcao) {

        String d = "";
        String paternMySql = "yyyy-MM-dd";
        //String paternHora = "hh:mm:ss";
        String paternHora = "HHmmss";
        // Verifica opção de formatação e aplica o patern
        switch (opcao) {

            case "d":
                SimpleDateFormat df = new SimpleDateFormat(paternMySql);
                d = df.format(data);
                break;
            case "h":
                SimpleDateFormat dfh = new SimpleDateFormat(paternHora);
                d = dfh.format(data);
                break;
        }

        return d;
    }

    public Icon carregaLogo() {
        DadosEmpresa d = cd.selecionaDados();
        ImageIcon imageIcon = new ImageIcon(d.getLogo());

        Icon i = new ImageIcon(imageIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH));

        return i;
    }

    public long retornaTotalDeDias(String data) {
        // Converte  para o tipo date a String informada
        java.sql.Date date = java.sql.Date.valueOf(data);
        SimpleDateFormat df = new SimpleDateFormat();
        // Pega a Data Atual
        LocalDate localDate = LocalDate.now();
        //Converte para LocalDate
        LocalDate dtFinal = LocalDate.parse(data);

        long dias = ChronoUnit.DAYS.between(localDate, dtFinal);

        return dias;
    }

    public Date converteData(String stringData) {

        java.sql.Date dataConvertida = java.sql.Date.valueOf(stringData);

        return dataConvertida;

    }

    // Limita tamanho de um campo textFild    
    public String tamanhoMaximo(String texto, int tamanho) {

        String str = "";
        if (texto.length() > tamanho) {
            str = texto.substring(0, tamanho);
            texto = str;
        }
        return texto;
    }

    // Valida lista de ComboBox passada como parâmetro de retorna um Boolean como resultado
    public boolean validaCombo(ArrayList<JComboBox> listaDeCombos) {
        boolean resp = false;
        int igual = 0;

        for (int i = 0; listaDeCombos.size() > i; i++) {
            if ("Selecione...".equals(listaDeCombos.get(i).getSelectedItem().toString())) {
                igual++;

            }
        }

        if (igual == 0) {
            resp = true;
        } else {
            JOptionPane.showMessageDialog(null, "A opção [Selecione...] não é válida!");
        }
        return resp;
    }
}
