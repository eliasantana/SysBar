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
public class TableModelGrupoProduto extends AbstractTableModel {

    private final ArrayList<Grupo> listaGrupo = new ArrayList<>();
    String colunas[] = {"CÓDIGO", "DESCRIÇÃO"};
    
     
    @Override
    public int getRowCount() {
        return listaGrupo.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:                
                return listaGrupo.get(row).getId();
            case 1:                
                return listaGrupo.get(row).getNomeGrupo();                       
            default:
                return listaGrupo.get(row);
        }
       
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {
         //{"CÓDIGO", "DESCRIÇÃO"}
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(310);
      
    }

}
