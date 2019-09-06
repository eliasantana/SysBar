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
public class TableModelTeste extends AbstractTableModel {

    private final ArrayList<Delivery> modelDelivery = new ArrayList<>();
   
    String colunas[] = {"Coluna1","Coluna2","Coluna3","Coluna4"};
    public void adicionaPratoCozinha (Delivery d){
        modelDelivery.add(d);
    }
    
    boolean[] canEdit = new boolean[] {
        false,false,false,false,false
    };
    
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
           
            default:
                return modelDelivery.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

    @Override
    public boolean isCellEditable(int linha, int coluna){
        return false;
    }
    
     public void redimensionaColunas(JTable tabela) {
         DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
         direita.setHorizontalAlignment(SwingConstants.RIGHT);
         
        //"PEDIDO","MESA","ENTREGADOR", "CLIENTE","SAÍDA"
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(60); 
       
      
        
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(1).setCellRenderer(direita);    
         
       
    }

    
    
}
