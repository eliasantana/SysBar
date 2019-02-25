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
public class TableModelFornecedores extends AbstractTableModel {

    private final ArrayList<Fornecedor> listaFornecedor = new ArrayList<>();
    String colunas[] = {"RAZÃO SOCIAL", "CNPJ", "REPRESENTANTE", "TELEFONE", "E-MAIL", "STATUS"};

    @Override
    public int getRowCount() {
        return listaFornecedor.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaFornecedor.get(row).getNome();
            case 1:
                return listaFornecedor.get(row).getCnpj();
            case 2:
                return listaFornecedor.get(row).getRepresentante();
            case 3:
                return listaFornecedor.get(row).getTelefone();
            case 4:
                return listaFornecedor.get(row).getEmail();
            case 5:
                return listaFornecedor.get(row).getStatus();

            default:
                return listaFornecedor.get(row);
        }

    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

    public void redimensionaColunas(JTable tabela) {
        //RAZÃO SOCIAL, CNPJ, REPRESENTANTE, TELEFONE, E-MAIL, STATUS
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(240);
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(120);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(200);
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(100);
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(230);
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(60);
        adicionaCoresTabela(tabela);
    }

    public void adicionaCoresTabela(JTable tabela) {

        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //A coluna do status é 8
                Object ref = table.getValueAt(row, 5);//Coluna Status
                //Coloca cor em todas as linhas,COLUNA(8) que tem o valor "Pendente"

                if (ref != null && ref.equals("Inativo")) {//Se Status for igual a "Pendente"
                    setBackground(Color.red);//Preenche a linha de vermelho
                    setForeground(Color.white);//E a fonte de branco                
                } else {
                    boolean sel = isSelected;
                    if (sel == true) {
                        setBackground(getBackground());
                        setForeground(getForeground());
                    } else {//Se Status não for "Pendente" 
                        setBackground(Color.WHITE);//Preenche a linha de branco
                        setForeground(new Color(51, 51, 51));//E a fonte de preto
                    }
                }
                return this;
            }
        });
    }

}
