/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bar.util;

import br.com.bar.view.TelaBloqueio;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Elias Santana
 * @since TESTE INTEGRADO 25/03/2019
 * Esta classe responsável por realizar a detecção da tecla pressionada no teclado.
 */
public class LeitorDeTeclas implements KeyListener{

    @Override
    public void keyTyped(KeyEvent ke) {
       
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        switch (ke.getKeyCode()){
            case(KeyEvent.VK_F8):
                //Chama a tela de Bloqueio
                TelaBloqueio tb = new TelaBloqueio();
                tb.setModal(true);
                tb.setVisible(true);
                break;        
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       
    }
    
    
    
}
