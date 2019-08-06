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
public class TableModelGrupoProduto extends AbstractTableModel {

    private final ArrayList<Grupo> listaGrupo = new ArrayList<>();
    String colunas[] = {"CÓDIGO", "DESCRIÇÃO"};

    @Override
    public int getRowCount() {
        return listaGrupo.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaGrupo.get(row).getId();
            case 1:
                return listaGrupo.get(row).getNomeGrupo();
            default:
                return listaGrupo.get(row);
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
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60);
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(315);

        // Alinha a direira conteúdo da coluna
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
        // Alinha a direita conteúdo da coluna
        tabela.getColumnModel().getColumn(1).setCellRenderer(esquerda);
        
        // Alinha a esquerda o cabeçalho da coluna
        //tabela.getColumnModel().getColumn(1).setHeaderRenderer(esquerda);

    }

}
