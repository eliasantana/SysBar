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
import sun.swing.SwingAccessor;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author Elias Santana
 */
public class TableModelDetalhePedido extends AbstractTableModel {

    private final ArrayList<ModelDetalhePedido> detalhePedido = new ArrayList<>();
    String colunas[] = {"CÓDIGO", "PRODUTO", "QUANTIDADE", "VLR UNITÁRIO R$", "VLR TOTAL R$"};
    
  
    
    @Override
    public int getRowCount() {
        return detalhePedido.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:                
                return detalhePedido.get(row).getCodigo();
            case 1:                
                return detalhePedido.get(row).getProduto();
            case 2:                
                return detalhePedido.get(row).getQuantidade();
            case 3:                
                return detalhePedido.get(row).getValorUnit();
            case 4:                
                return detalhePedido.get(row).getValorTotal();
            default:
                return detalhePedido.get(row);
        }
       
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {
         //CÓDIGO, PRODUTO, QUANTIDADE, VLR UNITÁRIO R$, VLR TOTAL R$
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(305);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(100); 
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(115); 
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(100); 
        
        // Aplica o alinhamento a coluna
        tabela.getColumnModel().getColumn(2).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(3).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(4).setCellRenderer(direita);
        
        

    }

}
