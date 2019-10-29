/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Elias Santana
 */
public class TableModelStatusCozinha extends AbstractTableModel {
    // PARATO,  QTD, COZINHEIRO, STATUS

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void redimensionaColunas(JTable tabela) {
          // MESA, PEDIDO, PRATO, QTD, COZINHEIRO,STATUS
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60);
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(60);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(230);
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(40);
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(203);
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(100);
       
        adicionaCoresTabela(tabela);
        
        // Não é possível aplicar alinhamento as células da tavela quando
        // já houver aplicado cor as linhas da tabela.

    }

    public void adicionaCoresTabela(JTable tabela) {
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //A coluna do status é 8
                Object ref = table.getValueAt(row, 5);//Coluna Status
                //Coloca cor em todas as linhas,COLUNA(3) que tem o valor "Pendente"
                if (ref != null && ref.equals("Em preparação")) {//Se Status for igual a "Pendente"
                    setBackground(Color.YELLOW);//Preenche a linha de vermelho
                    setForeground(Color.BLACK);//E a fonte de branco
                } else if (ref != null && ref.equals("Liberado")) {//Se Status for igual a "Liberado"
                    setBackground(Color.GREEN);//Preenche a linha de verde
                    setForeground(Color.black);//E a fonte de branco
                } else if (ref != null && ref.equals("Pendente")) {//Se Status for igual a "Liberado"
                    setBackground(Color.red);//Preenche a linha de verde
                    setForeground(Color.WHITE);//E a fonte de branco
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
