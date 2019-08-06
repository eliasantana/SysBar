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
import javax.swing.table.JTableHeader;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

/**
 *
 * @author Elias Santana
 */
public class TableModelNumeroMesas extends AbstractTableModel {

    private final ArrayList<Mesa> listaMesas = new ArrayList<>();

    String colunas[] = {"Mesas Disponíveis"};

    public void adicionaNumeroMesas(Mesa m) {
        listaMesas.add(m);
    }

    @Override
    public int getRowCount() {
        return listaMesas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaMesas.get(row).getId();
            case 1:
                return listaMesas.get(row).getId_funcionario();
            case 2:
                return listaMesas.get(row).getNumeroMesa();
            default:
                return listaMesas.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

    public void redimensionaColunas(JTable tabela) {
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(183); // CÓDIGO

        // Centraliza conteúdo da coluna
        tabela.getColumnModel().getColumn(0).setCellRenderer(center);
        //Centraliza cabeçalho da coluna
        JTableHeader header = tabela.getTableHeader();
        header.setDefaultRenderer(center);

    }

}
