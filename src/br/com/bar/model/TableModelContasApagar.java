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
public class TableModelContasApagar extends AbstractTableModel {

    private final ArrayList<ModelContasApagar> contas = new ArrayList<>();
    String colunas[] = {"CÓDIGO", "DESCRIÇÃO", "VALOR R$", "VENCIMENTO", "PAGAMENTO","VLR PAGO R$","OPERADOR","GRUPO"};
    
     
    @Override
    public int getRowCount() {
        return contas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:                
                return contas.get(row).getCodigo();
            case 1:                
                return contas.get(row).getDescricao();
            case 2:                
                return contas.get(row).getValor();
            case 3:                
                return contas.get(row).getVencimento();
            case 4:                
                return contas.get(row).getPagamento();
            case 5:                
                return contas.get(row).getPago();
            case 6:                
                return contas.get(row).getOperador();
            case 7:                
                return contas.get(row).getGrupo();
            default:
                return contas.get(row);
        }
       
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {
         //"CÓDIGO", "DESCRIÇÃO", "VALOR", "VENCIMENTO", "PAGAMENTO","PAGO","OPERADOR","GRUPO"
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(230);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(80); 
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(90); 
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(90); 
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(90); 
        tabela.getColumn(tabela.getColumnName(6)).setPreferredWidth(102); 
        tabela.getColumn(tabela.getColumnName(7)).setPreferredWidth(120); 
        

    }

}
