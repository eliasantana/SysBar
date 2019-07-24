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
public class TableModelCozinha extends AbstractTableModel {

    private final ArrayList<ModelPedidoCozinha> listaPratosCozinha = new ArrayList<>();
   
    String colunas[] = {"SEQ", "PRATO", "QTD", "GARÇOM", "MESA", "COZINHEIRO", "HORÁRIO", "ESPERA", "STATUS"};
    public void adicionaPratoCozinha (ModelPedidoCozinha pc){
        listaPratosCozinha.add(pc);
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
                return listaPratosCozinha.get(row).getSeq();
            case 1:
                return listaPratosCozinha.get(row).getPrato();
            case 2:
                return listaPratosCozinha.get(row).getQtd();
            case 3:
                return listaPratosCozinha.get(row).getGarcom();
            case 4:
                return listaPratosCozinha.get(row).getnMesa();
            case 5:
                return listaPratosCozinha.get(row).getCozinheiro();
            case 6:
                return listaPratosCozinha.get(row).getSolicitacao();
            case 7:
                return listaPratosCozinha.get(row).gettEspera();
            case 8:
                return listaPratosCozinha.get(row).getStatus();
            default:
                return listaPratosCozinha.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(65); // SEQ
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(230);//PRATO
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(40); //QTD
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(200); //GARÇOM
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(45); //N.MESA
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(200); //COZINHEIRO
        tabela.getColumn(tabela.getColumnName(6)).setPreferredWidth(45); //SOLICITAÇÃO
        tabela.getColumn(tabela.getColumnName(7)).setPreferredWidth(60); //T.ESPERA
        tabela.getColumn(tabela.getColumnName(8)).setPreferredWidth(115); //ESTATUS
        
    }

      public void adicionaCoresTabela(JTable tabela) {
        
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //A coluna do status é 8
                Object ref = table.getValueAt(row, 8);//Coluna Status
                //Coloca cor em todas as linhas,COLUNA(8) que tem o valor "Pendente"
                
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
