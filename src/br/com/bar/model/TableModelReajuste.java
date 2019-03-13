/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elias Santana
 */
public class TableModelReajuste extends AbstractTableModel {

    private final ArrayList<Produto> listaproduto = new ArrayList<>();
    String colunas[] = {"CÓDIGO", "DESCRIÇÃO", "VALOR R$"};
    
     
    @Override
    public int getRowCount() {
        return listaproduto.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:                
                return listaproduto.get(row).getId();
            case 1:                
                return listaproduto.get(row).getNome();
            case 2:                
                return listaproduto.get(row).getValor();                  
            default:
                return listaproduto.get(row);
        }
       
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {
         //{"CÓDIGO", "DESCRIÇÃO", "VALOR R$"}
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(225);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(80);        
    }

}
