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
public class TableModelPesquisaEstoque extends AbstractTableModel{
    
    private final  ArrayList<ModelProdutoEstoque> listaProdutoEstoque = new ArrayList<>();
    String colunas[] ={"CÓDIGO", "DESCRIÇÃO", "VALOR R$","ESTOQUE"};

    @Override
    public int getRowCount() {
        return listaProdutoEstoque.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch(col){
            case 0:
                return listaProdutoEstoque.get(row).getCodigo();
            case 1:
                return listaProdutoEstoque.get(row).getDescricao();
            case 2:
                return listaProdutoEstoque.get(row).getQtdEstoque();
            case 3:
                return listaProdutoEstoque.get(row).getValor();
            
            default: 
                return listaProdutoEstoque.get(row);
        }
    }
    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }
    
     public void redimensionaColunas(JTable tabela) {
         
         DefaultTableCellRenderer render = new DefaultTableCellRenderer();
         render.setHorizontalAlignment(SwingConstants.RIGHT);

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(68);     
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(400);    
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(100);  
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(100);
        
        tabela.getColumnModel().getColumn(2).setCellRenderer(render);
        tabela.getColumnModel().getColumn(3).setCellRenderer(render);
       
    }
}
