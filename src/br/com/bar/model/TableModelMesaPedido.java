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
public class TableModelMesaPedido extends AbstractTableModel {

    private final ArrayList<Mesa> listaMesas = new ArrayList<>();
   
    String colunas[] = {"Mesas Disponíveis"};
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
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(182); // Número da mesa
       
    }

    
    
}
