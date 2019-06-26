/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos3;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author Desarrollo
 */
public class Principal {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) throws SigarException, InterruptedException {

        int i = 0;
        JFrame frame = new JFrame("Captura Procesos");
        ComponenteCaptura cc;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (i == 0) {
            cc = new ComponenteCaptura(args);
            JButton boton = cc.button;
            cc.add(boton);
            frame.add(cc);
            frame.getContentPane();
            frame.setSize(1366, 768);
            frame.setVisible(true);
            cc.update(cc.getGraphics());
            Thread.sleep(2000);

        }
    }


    
}
