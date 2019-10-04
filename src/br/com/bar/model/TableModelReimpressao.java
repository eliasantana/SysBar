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
import org.mozilla.javascript.tools.debugger.SwingGui;

/**
 *
 * @author Elias Santana
 */
public class TableModelReimpressao extends AbstractTableModel {

    private final ArrayList<ModelReimpressao> listaReimpressao = new ArrayList<>();
    // MESA, PEDIDO, TX. SERVIÇO R$, DESCONTO R$, TOTAL R$, GARÇOM
    String colunas[] = {"MESA", "PEDIDO", "TX. SERVIÇO R$", "DESCONTO R$", "TOTAL R$","GARÇOM"};
    public void adicionaPratoCozinha (ModelReimpressao r){
        listaReimpressao.add(r);
    }
    
    @Override
    public int getRowCount() {
        return listaReimpressao.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaReimpressao.get(row).getMesa();
            case 1:
                return listaReimpressao.get(row).getPedido();
            case 2:
                return listaReimpressao.get(row).getComissao();
            case 3:
                return listaReimpressao.get(row).getDesc();
            case 4:
                return listaReimpressao.get(row).getTotal();
            case 5:
                return listaReimpressao.get(row).getGarcom();
           
            default:
                return listaReimpressao.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }
    //MESA, PEDIDO, TOTAL R$, DESCONTO R$, TX. SERVIÇO, GARÇOM
    
    // // MESA, PEDIDO, TX. SERVIÇO R$, DESCONTO R$, TOTAL R$, GARÇOM
     public void redimensionaColunas(JTable tabela) {
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(50); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(70);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(110); 
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(100); 
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(110); 
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(199); 
        //tabela.getColumnModel().getColumn(5).setMaxWidth(0);
        //tabela.getColumnModel().getColumn(5).setMaxWidth(0);
        
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(1).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(2).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(3).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(4).setCellRenderer(direita);

    }

}
