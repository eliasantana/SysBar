/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.util;

import br.com.bar.model.DadosEmpresa;
import br.com.br.controler.ControlerDadosEmpresa;
import java.awt.Color;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author elias
 */
public class Util {
     ControlerDadosEmpresa cd = new ControlerDadosEmpresa();
     
    // Retorna o número mês atual
    public String formataData(Date data) {

        SimpleDateFormat df = new SimpleDateFormat("M");
        String mes = df.format(data);

        return mes;
    }
    
    public String retornaAno(Date data){
        
        SimpleDateFormat df = new SimpleDateFormat("YYYY");
        String ano = df.format(data);
        
        return ano;
    }

    // Recebe uma data no formato string (dd/mm/yyyy) e converte para o formado (yyyy-mm-dd)
    
    public String  formataDataBanco(String data) {
       
        String[] dataSeparada = data.split("/");
              
       //String dataBanco = String.valueOf(ldata.getYear()+String.valueOf(ldata.getMonth()+String.valueOf(ldata.getDayOfMonth())));
       String dataBanco = String.valueOf(dataSeparada[2]+"-"+dataSeparada[1]+"-"+dataSeparada[0]);
       
       return dataBanco;
    }
    
    // Recebe um objeto date e retorna uma string no formado MySql ano-mes-dia
    public String  formataDataBanco(Date data) {
       String dataBanco=null;
        try {
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //String dataBanco = String.valueOf(ldata.getYear()+String.valueOf(ldata.getMonth()+String.valueOf(ldata.getDayOfMonth())));
            dataBanco = df.format(data);

        } catch (Exception e) {
            System.out.println("Erro ao formatar data" + e);
        }
       return dataBanco;
    }

    public String formataDataBr(Date data) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        String mes = df.format(data);

        return mes;
    }

    public void geraGraficoBarras(DefaultCategoryDataset data, String titulo) {

        try {

            // Criando Gráfico
            JFreeChart chart = ChartFactory.createBarChart3D(titulo, "Volume de Vendas", "Valores em R$", data, PlotOrientation.VERTICAL, true, true, true);
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.BLACK);

            // Tamanh da Janela
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

    public Icon carregaLogo(){
        DadosEmpresa d = cd.selecionaDados();
        ImageIcon imageIcon = new ImageIcon(d.getLogo());
        
        Icon i = new ImageIcon(imageIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH));
        
        return i;
    }
    
    public long retornaTotalDeDias(String data){
        // Converte  para o tipo date a String informada
        java.sql.Date date  = java.sql.Date.valueOf(data);
        SimpleDateFormat df = new SimpleDateFormat();
        // Pega a Data Atual
        LocalDate localDate = LocalDate.now();
        //Converte para LocalDate
        LocalDate dtFinal = LocalDate.parse(data);        
        
        long dias = ChronoUnit.DAYS.between(localDate, dtFinal);
        
        return dias;
    }
    
    public Date converteData(String stringData){
        
        java.sql.Date dataConvertida = java.sql.Date.valueOf(stringData);
        
        return dataConvertida;
    }
        
    
            
}
