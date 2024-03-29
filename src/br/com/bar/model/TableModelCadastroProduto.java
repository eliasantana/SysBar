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
public class TableModelCadastroProduto extends AbstractTableModel {

    private final ArrayList<Produto> listaProduto = new ArrayList<>();
    String colunas[] = {"CÓDIGO","DESCRIÇÃO","QTD","VALOR R$","MIN","MAX","GRUPO"};
    
     
    @Override
    public int getRowCount() {
        return listaProduto.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:                
                return listaProduto.get(row).getId();
            case 1:                
                return listaProduto.get(row).getNome();                       
            case 2:                
                return listaProduto.get(row).getQtd();                       
            case 3:                
                return listaProduto.get(row).getValor();                       
            case 4:                
                return listaProduto.get(row).getQtdMin();                       
            case 5:                
                return listaProduto.get(row).getQtdMax();                       
            case 6:                
                return listaProduto.get(row).getTbGrupoId();
            default:
                return listaProduto.get(row);
        }
       
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {
        
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();        
        
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(60); 
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(300);
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(40);
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(80);
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(40);
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(55);
        tabela.getColumn(tabela.getColumnName(6)).setPreferredWidth(133);
        // Atribui o alinhamento as colunas
//        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
//        tabela.getColumnModel().getColumn(1).setCellRenderer(esquerda);
//        tabela.getColumnModel().getColumn(2).setCellRenderer(direita);
//        tabela.getColumnModel().getColumn(3).setCellRenderer(direita);
//        tabela.getColumnModel().getColumn(4).setCellRenderer(direita);
//        tabela.getColumnModel().getColumn(5).setCellRenderer(direita);
//        tabela.getColumnModel().getColumn(6).setCellRenderer(esquerda);
        adicionaCoresTabela(tabela);
        
    }
       // trocado tabela -> table
       public void adicionaCoresTabela(JTable table) {
        
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                Object ref = table.getValueAt(row, 2);//Coluna qtd
                Object refMin = table.getValueAt(row, 4);//Coluna qtdMin
                //Coloca cor em todas as linhas,COLUNA(8) que tem o valor "Pendente"
                
                 if (ref.equals(refMin)) {
                    setBackground(Color.YELLOW);//Preenche a linha de vermelho
                    setForeground(Color.BLACK);//E a fonte de branco
                    
                }  else if (Integer.parseInt(ref.toString()) < Integer.parseInt(refMin.toString())){
                    setBackground(Color.red);//Preenche a linha de vermelho
                    setForeground(Color.WHITE);//E a fonte de branco
                }else {
                    boolean sel = isSelected;
                    if (sel == true) {
                        setBackground(getBackground());
                        setForeground(getForeground());                        
                    } else {
                         //Se Status não for "Pendente" 
                        setBackground(Color.WHITE);//Preenche a linha de branco
                        setForeground(new Color(51, 51, 51));//E a fonte de preto
                       
                    }
                }
                return this;
            }
        });
         

    }

}
