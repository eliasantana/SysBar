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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Elias Santana
 */
public class TableModelMesas extends AbstractTableModel {

    private final ArrayList<Mesa> listaMesas = new ArrayList<>();
   
    String colunas[] = {"CÓD. INT.", "GARÇOM", "MESA"};
    public void adicionaPratoCozinha (Mesa m){
        listaMesas.add(m);
    }
    
    @Override
    public int getRowCount() {
        return listaMesas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaMesas.get(row).getId();
            case 1:
                return listaMesas.get(row).getId_funcionario();
            case 2:
                return listaMesas.get(row).getNumeroMesa();     
            default:
                return listaMesas.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(70); // CÓDIGO
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(60);//NÚMERO DA MESA
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(360); //FUNCIONÁRIO 
        
        // Oculta a primeira colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabela.getColumnModel().getColumn(0).setMinWidth(0);
        tabela.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    
    
}
