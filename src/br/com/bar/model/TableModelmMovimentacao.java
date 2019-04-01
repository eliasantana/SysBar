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
 */
public class TableModelmMovimentacao extends AbstractTableModel{
    
    private final  ArrayList<Produto> listaProdutoEstoque = new ArrayList<>();
    String colunas[] ={"CÓDIGO", "PRODUTO", "QTD", "GRUPO"};

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
                return listaProdutoEstoque.get(row).getId();
            case 1:
                return listaProdutoEstoque.get(row).getNome();
            case 2:
                return listaProdutoEstoque.get(row).getQtd();
            case 3:
                return listaProdutoEstoque.get(row).getTbGrupoId();            
            default: 
                return listaProdutoEstoque.get(row);
        }
    }
    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }
    
     public void redimensionaColunas(JTable tabela) {

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60);     
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(450);    
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(60);  
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(120);  
            
       
    }
}
