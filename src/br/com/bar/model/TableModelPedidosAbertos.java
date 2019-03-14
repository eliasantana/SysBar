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
 * Criado: 28/01/2019
 */
public class TableModelPedidosAbertos extends AbstractTableModel{
    
    private final ArrayList<ModelPedidosAbertos> listaPedidos = new ArrayList<>();
    String[] colunas={"MESA", "PEDIDO", "DATA", "STATUS", "GARÇOM"};

    @Override
    public int getRowCount() {
       return listaPedidos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;    
    }

    @Override
    public Object getValueAt(int row , int col) {
        
        switch (col){
            case 0:
                return listaPedidos.get(row).getnMesa();
            case 1:
                return listaPedidos.get(row).getnPedido();
            case 2:
                return listaPedidos.get(row).getnMesa();            
            case 3:
                return listaPedidos.get(row).getStatus();
            case 4:
                return listaPedidos.get(row).getGarcom();
            default: 
                return listaPedidos.get(row);
                
        }
    }
    
     @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }
    
     public void redimensionaColunas(JTable tabela) {

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(80);     //N.MESA
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(80);    //N. PEDIDO
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(120);   //DATA
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(130);   //STATUS
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(270);    //GARÇOM
       
       
    }
    
}
