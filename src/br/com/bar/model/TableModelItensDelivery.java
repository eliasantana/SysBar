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

/**
 *
 * @author Elias Santana
 */
public class TableModelItensDelivery extends AbstractTableModel {

    private final ArrayList<ModelCaixa> listaPratosCozinha = new ArrayList<>();
   
    String colunas[] = {"CÓDIGO", "PRODUTO", "QTD", "VLR UNITÁRIO R$", "VLR TOTAL R$"};
    public void adicionaPratoCozinha (ModelCaixa c){
        listaPratosCozinha.add(c);
    }
    
    @Override
    public int getRowCount() {
        return listaPratosCozinha.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaPratosCozinha.get(row).getCodigo();
            case 1:
                return listaPratosCozinha.get(row).getProduto();
            case 2:
                return listaPratosCozinha.get(row).getQtd();
            case 3:
                return listaPratosCozinha.get(row).getValorUnitario();
            case 4:
                return listaPratosCozinha.get(row).getValorTotal();
           
            default:
                return listaPratosCozinha.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {
        // Define alinhamento da coluna.
        //SwingConstants.RIGHT
       
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60); // CÓDIGO
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(180);//PRODUTO
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(50); //QUANTIDADE
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(120); //VALOR UNITÁRIO
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(100); //TOTAL
        // Aplica alinhamento
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(2).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(3).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(4).setCellRenderer(direita);
        

    }

     

    
}
