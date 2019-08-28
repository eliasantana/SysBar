/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Elias Santana
 */
public class TableModelDelivery extends AbstractTableModel {

    private final ArrayList<Delivery> modelDelivery = new ArrayList<>();
   
    String colunas[] = {"PEDIDO","MESA","CLIENTE","ENTREGADOR","SAÍDA"};
    public void adicionaPratoCozinha (Delivery d){
        modelDelivery.add(d);
    }
    
    @Override
    public int getRowCount() {
        return modelDelivery.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return modelDelivery.get(row).getIdPedido();
            case 1:
                return modelDelivery.get(row).getData();
            case 2:
                return modelDelivery.get(row).getIdEntregador();     
            case 3:
                return modelDelivery.get(row).getIdCliente();     
               
            default:
                return modelDelivery.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {
         DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
         direita.setHorizontalAlignment(SwingConstants.RIGHT);
         
        //"PEDIDO","MESA","ENTREGADOR", "CLIENTE","SAÍDA"
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(60); 
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(210);
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(100); 
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(80); 
        
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(1).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(4).setCellRenderer(direita);
       
    }

    
    
}
