/*
 * Nome da Classe: Util.  Criação: 01/07/2018
 * Esta Classe é resposável por concentrar os métodos com funcionalidades
 * Genéricas que podem ser utilizadas em outros lugares do sistema.
 * 
 */
package br.com.bar.util;

import br.com.bar.model.DadosEmpresa;
import br.com.br.controler.ControlerDadosEmpresa;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.joda.time.DateTime;

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
     * Recebe uma data no formato string (dd/mm/yyyy) e converte para o formado
     * (yyyy-mm-dd)
     *
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
     *
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
     * Recebe um objeto Date e retorna uma String no formado dd/MM/YYYY
     *
     * @param data - Recebe a data informada;
     * @return mes - Retorna um objeto tipo Date;
     */
    public String formataDataBr(Date data) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        String mes = df.format(data);

        return mes;
    }

    /**
     * Recebe um objeto DefaultCategoryDataSet e uma string com o título do
     * gráfico
     *
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
            f.setAlwaysOnTop(true);
            f.setSize(800, 600);
            f.setVisible(true);
        } catch (SecurityException e) {
            System.out.println("Erro ao gerarGraficoDeBarras");
        }

    }

    public void geraGraficoPizza(PieDataset data, String titulo) {
        JFreeChart chart = ChartFactory.createPieChart3D(titulo, data);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.black);
        ChartFrame f = new ChartFrame("Ranking de Vendas por Garçom", chart);
        f.setSize(800, 600);
        f.setAlwaysOnTop(true);
        f.setVisible(true);
    }
    /**
     * Formata a data Informada
     * @param data Objeto Date
     * @param opcao Tipo de Formatação d-> yyyy-MM-dd h->HHmmss dh->dd-MM-yyy HH:mm br->dd/MM/yyy
     * 
     */
    public String formataDataHora(Date data, String opcao) {

        String d = "";
        String paternMySql = "yyyy-MM-dd";
        //String paternHora = "hh:mm:ss";
        String paternHora = "HHmmss";
        String paternDataHora = "dd-MM-yyy HH:mm";
        String paternDtBr = "dd/MM/yyy";
        // Verifica opção de formatação e aplica o patern
        switch (opcao) {

            case "d": // Formada data no formato do bando de dados Mysql
                SimpleDateFormat df = new SimpleDateFormat(paternMySql);
                d = df.format(data);
                break;
            case "h":// Formada hora no formato do bando de dados Mysql
                SimpleDateFormat dfh = new SimpleDateFormat(paternHora);
                d = dfh.format(data);
                break;
            case "dh":// Formada hora no formato do bando de dados Mysql
                SimpleDateFormat dfDataHora = new SimpleDateFormat(paternDataHora);
                d = dfDataHora.format(data);
                break;
            case "br":// Formada hora no formato do bando de dados Mysql
                SimpleDateFormat dtBr = new SimpleDateFormat(paternDtBr);
                d = dtBr.format(data);
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
        try {
            // Inicialização do sistema com banco vazio
            java.sql.Date date = java.sql.Date.valueOf(data);
        } catch (Exception e) {

            System.out.println("br.com.bar.util.Util.retornaTotalDeDias()" + e);

        }

        SimpleDateFormat df = new SimpleDateFormat();
        // Pega a Data Atual
        LocalDate localDate = LocalDate.now();
        //Converte para LocalDate
        long dias = 0;
        // Caso o cadastro esteja vazio gera java.lang.NullPointerException
        try {

            LocalDate dtFinal = LocalDate.parse(data);

            dias = ChronoUnit.DAYS.between(localDate, dtFinal);
        } catch (Exception e) {
            System.out.println("br.com.bar.util.Util.retornaTotalDeDias()");
        }

        return dias;
    }

    public long retornaTotalDeDias(String data, String dataFim) {

        //Converte para LocalDate
        long dias = 0;
        // Caso o cadastro esteja vazio gera java.lang.NullPointerException
        try {

            LocalDate dtFinal = LocalDate.parse(data);
            LocalDate dtIncio = LocalDate.parse(dataFim);

            dias = ChronoUnit.DAYS.between(dtFinal, dtIncio);
        } catch (Exception e) {

        }

        return dias;
    }

    /**
     * Este método converte um objeto String(yyyy-MM-dd) em um Date. Adicionado:
     * Na versão 1.9.6
     *
     * @param stringData Data a ser convertida.
     * @return dataConvertida Retorna um Date.
     */
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

    // Método de Validação de CPF
    public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        // Remove pontos e vírgulas do CPF informado
        String removPonto = CPF.replace(".", "");
        String removHifem = removPonto.replace("-", "");
        CPF = removHifem;
        // Realiza a verificação para o cpf
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String imprimeCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

    public boolean validaEmail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    public void redimensionaColunas(JTable tabela, int[] colunas) {
        /*
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(70);
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(230);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(42);
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(60);
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(90);
        adicionaCoresTabela(tabela);*/

        System.out.println(colunas.length);

    }
    // Carrega ícone na janela

    public void setIcon(JFrame frame) {

        String nomeImagem = "masterfood64x64.png";

        frame.setIconImage(new javax.swing.ImageIcon(getClass().getResource("../imagens/" + nomeImagem)).getImage());
    }

    public void setIcon(JDialog dialog) {

        String nomeImagem = "masterfood64x64.png";

        dialog.setIconImage(new javax.swing.ImageIcon(getClass().getResource("../imagens/" + nomeImagem)).getImage());
    }

    public boolean isCNPJ(String CNPJ) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public String imprimeCNPJ(String CNPJ) {
        // máscara do CNPJ: 99.999.999.9999-99
        return (CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "."
                + CNPJ.substring(5, 8) + "/" + CNPJ.substring(8, 12) + "-"
                + CNPJ.substring(12, 14));
    }

    /**
     * Limita a data de um componente JDateSchooser estipulando um limite MÍNIMO
     * e MÁXIMO de datas para o componente.
     *
     * @param dtChooser Componente JDateSchooser a ser limitado.
     * @param limiteMeses Quantidade de Meses anterior ao mês atual que será
     * habilitado para seleção.
     *
     */
    public void limitaDataCombo(JDateChooser dtChooser, int limiteMeses) {

        //Atribui ao componente JDateSchooser uma data mínima selecionável.
        Calendar cMax = Calendar.getInstance();
        cMax.set(Calendar.YEAR, cMax.get(Calendar.YEAR)); // Ano máximo Selecionável
        cMax.set(Calendar.MONTH, cMax.get(Calendar.MONTH)); // MÊs máximo Selecionável
        cMax.set(Calendar.DATE, cMax.get(Calendar.DATE));
        dtChooser.setMaxSelectableDate(cMax.getTime());

        //Atribui ao componente JDateSchooser uma data mínima selecionável.
        Calendar cMin = Calendar.getInstance();
        cMin.set(Calendar.YEAR, cMin.get(Calendar.YEAR)); // Ano Atual
        cMin.set(Calendar.MONTH, cMin.get(Calendar.MONTH) - limiteMeses); // Meses Selecionável

        // Se o limite de meses informado for iagual a 0 'zero' será setada a data atual
        // como limite mínimo selecionável. Caso seja diferente liberará do dia 1 ao 
        // último dia do mês.
        if (limiteMeses == 0) {
            cMin.set(Calendar.DATE, cMin.get(Calendar.DATE));// Data Atual
        } else {
            cMin.set(Calendar.DATE, cMin.get(Calendar.DATE));// Data Atual            
        }
        //Seta a data Mínima no JdateChooser.
        dtChooser.setMinSelectableDate(cMin.getTime());

    }

    /**
     * Este método limita a data de um componente JDateSchooser estipulando, um
     * limite MÍNIMO e MÁXIMO para o componente.
     *
     * @param dtChooser Componente JDateSchooser a ser limitado.
     * @param limiteMeses quantidade de Meses anterior ao mês atual que será
     * habilitado para seleção.
     * @param dia Dia a partir de onde se poderá selecionar uma data no mês
     * anterior.
     */
    public void limitaDataCombo(JDateChooser dtChooser, int limiteMeses, int dia) {

        //Atribui ao componente JDateSchooser uma data mínima selecionável.
        Calendar cMax = Calendar.getInstance();
        cMax.set(Calendar.YEAR, cMax.get(Calendar.YEAR)); // Ano máximo Selecionável
        cMax.set(Calendar.MONTH, cMax.get(Calendar.MONTH)); // MÊs máximo Selecionável
        cMax.set(Calendar.DATE, cMax.get(Calendar.DATE));
        dtChooser.setMaxSelectableDate(cMax.getTime());

        //Atribui ao componente JDateSchooser uma data mínima selecionável.
        Calendar cMin = Calendar.getInstance();
        cMin.set(Calendar.YEAR, cMin.get(Calendar.YEAR)); // Ano Atual
        cMin.set(Calendar.MONTH, cMin.get(Calendar.MONTH) - limiteMeses); // Meses Selecionável

        // Se o limite de meses informado for iagual a 0 'zero' será setada a data atual
        // como limite mínimo selecionável. Caso seja diferente liberará do dia 1 ao 
        // último dia do mês.
        if (limiteMeses == 0) {
            cMin.set(Calendar.DATE, cMin.get(Calendar.DATE));// Data Atual
        } else {
            cMin.set(Calendar.DATE, dia);// Data Atual            
        }
        //Seta a data Mínima no JdateChooser.
        dtChooser.setMinSelectableDate(cMin.getTime());

    }

    public void abrirCalculadora() {

        try {
            Runtime.getRuntime().exec("cmd /c calc.exe");
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validaPlaca(String placa, String tipo) {
        Pattern pattern;
        Matcher matcher;
        boolean resp=false;
        switch (tipo.toUpperCase()) {
             
            case "M":
                // Padrão Mercosul
                pattern = Pattern.compile("[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}");   //RIO2A18
                matcher = pattern.matcher(tipo.toUpperCase());
                resp = matcher.matches();
                break;

            case "N":
                pattern = Pattern.compile("[A-Z]{3}-[0-9]{4}");
                matcher = pattern.matcher(tipo.toUpperCase());
                resp = matcher.matches();
                break;
        }

        return resp;
    }
    
    /**
     * Formata um obj do tipo java.util.Date em java.joda.DateTime
     * @param d Data a ser formatada 
     * @return retorna uma string no formato DateTime 2019-09-13T17:24:12.167-03:00
     */
    public String formataDateTime(Date d){
        
        Date data = d;
        DateTime dtTime = new DateTime(data);
        
        return dtTime.toString();
        
    }
    
    public String retiraAcento(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
    
    public  void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
            

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
   

}
