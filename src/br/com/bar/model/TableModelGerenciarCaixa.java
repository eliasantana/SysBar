/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Elias Santana
 */
public class TableModelGerenciarCaixa extends AbstractTableModel {

    private final ArrayList<ModelGerenciarCaixa> listaCaixas = new ArrayList<>();
   
    String colunas[] = {"CÓD. INTERNO", "DATA", "SALDO R$", "OPERADOR"};
    public void adicionaPratoCozinha (ModelGerenciarCaixa m){
        listaCaixas.add(m);
    }
    
    @Override
    public int getRowCount() {
        return listaCaixas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaCaixas.get(row).getCodigo();
            case 1:
                return listaCaixas.get(row).getData();
            case 2:
                return listaCaixas.get(row).getSaldo();     
            case 3:
                return listaCaixas.get(row).getOperador();     
            default:
                return listaCaixas.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }
    //{"CÓD. INTERNO", "DATA", "SALDO R$, OPERADOR"}
     public void redimensionaColunas(JTable tabela) {
        
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(100); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(90);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(90); 
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(275);
        
        // Oculta a primeira colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabela.getColumnModel().getColumn(0).setMinWidth(0);
        tabela.getColumnModel().getColumn(0).setMaxWidth(0);
        
        // Alinha a direita a coluna valor
        tabela.getColumnModel().getColumn(2).setCellRenderer(direita);
    }

    
    
}
