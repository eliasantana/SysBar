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
public class TableModelGerenciarPedido extends AbstractTableModel {

    private final ArrayList<ModelGerenciarPedido> modelGerPedido = new ArrayList<>();
    String colunas[] = {"CÓD", "PRODUTO", "QTD", "VLR UNITÁRIO R$", "VLR TOTAL R$", "CÓD. INTERNO"};
    
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
         //"CÓD", "PRODUTO", "QTD", "VLR UNITÁRIO R$", "VLR TOTAL R$", "CÓD. INTERNO"
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(50); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(315);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(50); 
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(115); 
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(110); 
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(105); 
       

    }

    
}
