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
public class TableModelGerenciarPedido extends AbstractTableModel {

    private final ArrayList<ModelGerenciarPedido> modelGerPedido = new ArrayList<>();
    String colunas[] = {"CÓDIGO", "PRODUTO", "QTD", "VLR UNITÁRIO R$", "VLR TOTAL R$", "CÓD. INTERNO"};
    
    public void adicionaPratoCozinha (ModelGerenciarPedido pc){
        modelGerPedido.add(pc);
    }
    
    @Override
    public int getRowCount() {
        return modelGerPedido.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return modelGerPedido.get(row).getCodigo();
            case 1:
                return modelGerPedido.get(row).getProduto();
            case 2:
                return modelGerPedido.get(row).getQtd();
            case 3:
                return modelGerPedido.get(row).getValorUnit();
            case 4:
                return modelGerPedido.get(row).getValorTotal();
            case 5:
                return modelGerPedido.get(row).getCodInterno();            
            default:
                return modelGerPedido.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {
         //"CÓDIGO", "PRODUTO", "QTD", "VLR UNITÁRIO R$", "VLR TOTAL R$", "CÓD. INTERNO"
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(50); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(378);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(50); 
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(115); 
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(110); 
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(105); 
       
         // Oculta a primeira colunas
        tabela.getColumnModel().getColumn(5).setPreferredWidth(0);
        tabela.getColumnModel().getColumn(5).setMinWidth(0);
        tabela.getColumnModel().getColumn(5).setMaxWidth(0);
        
        // Aplica alinhamento as colunas
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(2).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(3).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(4).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(5).setCellRenderer(direita);
        
        
    }

    
}
