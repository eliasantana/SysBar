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
public class TableModelLodalidade extends AbstractTableModel {

    private final ArrayList<Localidade> listaLocalidade = new ArrayList<>();
    String colunas[] = {"CÓDIGO", "LOCALIDADE","TAXA"};

    @Override
    public int getRowCount() {
        return listaLocalidade.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaLocalidade.get(row).getId();
            case 1:
                return listaLocalidade.get(row).getNome();
            case 2:
                return listaLocalidade.get(row).getTaxa();
            default:
                return listaLocalidade.get(row);
        }

    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

    public void redimensionaColunas(JTable tabela) {
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
       
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(323); // LOCALIDADE PIEDADE1 CANDEIAS1 BOA VIAGEM1
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(60); // TAXA

        // Alinha a direira conteúdo da coluna
        tabela.getColumnModel().getColumn(0).setCellRenderer(esquerda);
        // Alinha a direita conteúdo da coluna
        tabela.getColumnModel().getColumn(1).setCellRenderer(direita);
        
        // Alinha a esquerda o cabeçalho da coluna
        //tabela.getColumnModel().getColumn(1).setHeaderRenderer(esquerda);

    }

}
