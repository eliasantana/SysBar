/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.dao;

import br.com.bar.model.DadosEmpresa;
import br.com.bar.view.TelaPametro;
import br.com.br.controler.ControlerDadosEmpresa;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author elias
 */
public class Backup {
    ControlerDadosEmpresa empresa = new ControlerDadosEmpresa();
    public boolean Backup() {
        boolean resp = false;

        try {

            Runtime.getRuntime().exec("cmd /c start C:/SysBar/backup/backup.bat"); // Executa programa para execução do backup
            resp = true;

        } catch (IOException e) {
            System.err.println(e);
        }

        return resp;

    }
    
   public void realizaBackup(){
       
        //Realiza Backup do Sistema
        // Instancia a classe backup
        Backup bkp = new Backup();
        // Returna true se o backup for realizado com sucesso!
        boolean bkpCriado = bkp.Backup();
        
        if (bkpCriado) {
            System.out.println("Criando Arquivo de Backup!");
            
            try {
                // Inicia a Thread que aguardará 15 segundos 
                //Thread.sleep(15000);
                Thread.sleep(10000);
                // Localiza o arquivo de backup.
                File f = new File("C:/SysBar/backup/bkpdumpSYSBAR.sql");
                
                // Verifica se o arquivo existe
                if (f.exists()) {
                    // Captura a data e a hora        
                    System.out.println("Preparando o arquivo!...");
                    String formatoData = "ddMMyyy";
                    String formatoHora = "hhmmss";
                    Date data = new Date();
                    SimpleDateFormat df = new SimpleDateFormat(formatoData);
                    SimpleDateFormat hf = new SimpleDateFormat(formatoHora);
                    String dt = df.format(data);
                    String h = hf.format(data);
                    DadosEmpresa dados = new DadosEmpresa();
                    // Carrega dados do cadastro da empresa
                    dados = empresa.selecionaDados();
                    // Renomeia o arquivo concatenando a data e a hora em que o arquivo foi criado.
                    
                    //File f2 = new File("C:/SysBar/backup/bkpdumpSYSBAR" + dt + h + ".sql");
                    File f2 = new File("C:/SysBar/backup/bkp-"+dados.getNome_empresa()+"-" + dt +"-"+ h + ".sql");
                   
                    // Retor na verdadeiro se o arquivo for renomeado com sucesso.
                    boolean resp = f.renameTo(f2); 
                    
                    if (resp) {
                        System.out.println("Bakup finalizado com sucesso!"); 
                        String destino = dados.getUrlbackup()+"\\"+f2.getName();
                        // Copiar o arquivo de backup para o local de bakup selecionado no cadastro.
                        
                        copyBakup(f2.getAbsolutePath(), destino);
                    } else {
                        System.out.println("Falha ao renomear o arquivo de backup");

                    }
                } else {
                    System.out.println("Arquivo não Existe");

                }
            } catch (InterruptedException e) {
                System.out.println("Erro na Thread!");
            }
        }
   }
   
   public void copyBakup(String origem, String destino){
         //System.out.println("Backup copiado de->" + origem + " para " + destino);
         Path pathOrigem = Paths.get(origem);
         Path pathDestino = Paths.get(destino);
         System.out.println(pathDestino.toString());
         System.out.println(pathOrigem.toString());
         
        try {
            Files.copy(pathOrigem, pathDestino);
            System.out.println("Backup copiado com sucesso!");
        } catch (IOException ex) {
            Logger.getLogger(TelaPametro.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("br.com.bar.view.TelaPametro.copyBakup()" +ex);           
        }
    }
}
