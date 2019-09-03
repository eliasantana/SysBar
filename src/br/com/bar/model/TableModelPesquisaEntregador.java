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
public class TableModelPesquisaEntregador extends AbstractTableModel {

    private final ArrayList<Entregador> listaDeEntregador = new ArrayList<>();
   //NOME, CELULAR, VEÍCULO, PLACA, E-MAIL, CPF, RG, STATUS
    String colunas[] = {"NOME", "CELULAR", "VEÍCULO", "PLACA","CPF", "RG", "STATUS"};
    
    public void adicionaEntregador (Entregador e){
        listaDeEntregador.add(e);
    }
    
    @Override
    public int getRowCount() {
        return listaDeEntregador.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaDeEntregador.get(row).getNome();
            case 1:
                return listaDeEntregador.get(row).getTelefone();
            case 2:
                return listaDeEntregador.get(row).getVeiculo();
            case 3:
                return listaDeEntregador.get(row).getPlaca();                      
            case 4:
                return listaDeEntregador.get(row).getCpf();
            case 5:
                return listaDeEntregador.get(row).getRg();
            case 6:
                return listaDeEntregador.get(row).getStatus();           
            default:
                //return listaDeEntregador.get(row);
                return listaDeEntregador.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {

   //String colunas[] = {"NOME", "CELULAR", "VEÍCULO", "PLACA","CPF", "RG", "STATUS"};
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(170); //NOME
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(120);// CELULAR
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(70); // VEÍCULO
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(80); //PLACA       
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(110); // CPF
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(90); // RG
        tabela.getColumn(tabela.getColumnName(6)).setPreferredWidth(80); // SATUS
        
         adicionaCoresTabela(tabela);

    }

      public void adicionaCoresTabela(JTable tabela) {
        
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //A coluna do status é 8
                Object ref = table.getValueAt(row, 6);//Coluna sTATUS
                //Coloca cor em todas as linhas,COLUNA(6) que tem o valor "1"
                
                 if (ref != null && ref.equals("OCUPADO")) {//Se Status for igual a "Pendente"
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
