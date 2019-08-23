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
public class TableModelCliente extends AbstractTableModel {

    private final ArrayList<Cliente> modelCliente = new ArrayList<>();
   
    String colunas[] = {"NOME", "E-MAIL", "LOCALIDADE","TAXA"};
    public void adicionaPratoCozinha (Cliente c){
        modelCliente.add(c);
    }
    
    @Override
    public int getRowCount() {
        return modelCliente.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return modelCliente.get(row).getNome();
            case 1:
                return modelCliente.get(row).getEmail();
            case 2:
                return modelCliente.get(row).getLocalidade().getNome();     
            default:
                return modelCliente.get(row).getLocalidade().getTaxa();
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {

         DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
         direita.setHorizontalAlignment(SwingConstants.RIGHT);
         
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(205); // NOME
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(160);//E-MAIL
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(90); //LOCALIDADE - NOME
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(58); //LOCALIDADE - TAXA
        
        tabela.getColumnModel().getColumn(3).setCellRenderer(direita);
    }

    
    
}
