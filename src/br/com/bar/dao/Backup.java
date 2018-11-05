/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.dao;

import java.io.IOException;

/**
 *
 * @author elias
 */
public class Backup {

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
}
