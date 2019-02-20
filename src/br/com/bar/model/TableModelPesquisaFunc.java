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
public class TableModelPesquisaFunc extends AbstractTableModel {

    private final ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
   
    String colunas[] = {"CÓDIGO", "NOME", "CPF", "RG", "CELULAR", "ADMISSÃO", "STATUS", "BLOQUEIO"};
    
    public void adicionaPratoCozinha (Funcionario f){
        listaFuncionarios.add(f);
    }
    
    @Override
    public int getRowCount() {
        return listaFuncionarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   
    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return listaFuncionarios.get(row).getId();
            case 1:
                return listaFuncionarios.get(row).getNome();
            case 2:
                return listaFuncionarios.get(row).getCpf();
            case 3:
                return listaFuncionarios.get(row).getRg();
            case 4:
                return listaFuncionarios.get(row).getTelefone();
            case 5:
                return listaFuncionarios.get(row).getDtAdmissao();
            case 6:
                return listaFuncionarios.get(row).getStatus();
            case 7:
                return listaFuncionarios.get(row).getBloqueado();
           
            default:
                return listaFuncionarios.get(row);
        }
    }

    @Override
    public String getColumnName(int row) {
        return this.colunas[row];
    }

     public void redimensionaColunas(JTable tabela) {

    String colunas[] = {"CÓDIGO", "NOME", "CPF", "RG", "CELULAR", "ADMISSÃO", "STATUS", "BLOQUEIO"};
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumn(tabela.getColumnName(0)).setPreferredWidth(65); //CÓDIGO
        tabela.getColumn(tabela.getColumnName(1)).setPreferredWidth(200);// NOME
        tabela.getColumn(tabela.getColumnName(2)).setPreferredWidth(100); // CPF
        tabela.getColumn(tabela.getColumnName(3)).setPreferredWidth(80); //RG
        tabela.getColumn(tabela.getColumnName(4)).setPreferredWidth(110); //CELULAR
        tabela.getColumn(tabela.getColumnName(5)).setPreferredWidth(80); // ADMISSÃO
        tabela.getColumn(tabela.getColumnName(6)).setPreferredWidth(80); // STATUS
        tabela.getColumn(tabela.getColumnName(7)).setPreferredWidth(100); // BLOQUEIO
         adicionaCoresTabela(tabela);

    }

      public void adicionaCoresTabela(JTable tabela) {
        
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //A coluna do status é 8
                Object ref = table.getValueAt(row, 7);//Coluna Bloqueio
                //Coloca cor em todas as linhas,COLUNA(8) que tem o valor "Pendente"
                
                 if (ref != null && ref.equals("Bloqueado")) {//Se Status for igual a "Pendente"
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
