/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

import br.com.bar.dao.Log;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elias Santana
 */
public class TableModelLog extends AbstractTableModel {

    private final ArrayList<Log> listaDeLog = new ArrayList<>();
    String colunas[] = {"DATA", "HORA", "AÇÃO", "DESCRIÇÃO"};
    
     
    @Override
    public int getRowCount() {
        return listaDeLog.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:                
                return listaDeLog.get(row).getData();
            case 1:                
                return listaDeLog.get(row).getHora();
            case 2:                
                return listaDeLog.get(row).getFuncionalidade();
            case 3:                
                return listaDeLog.get(row).getDescricao();            
            default:
                return listaDeLog.get(row);
        }
       
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {
         //{"DATA", "HORA", "AÇÃO", "DESCRIÇÃO"}
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(100); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(100);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(250); 
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(530);         
        
    }

}
