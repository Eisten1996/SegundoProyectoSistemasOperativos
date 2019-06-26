/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos2;

import org.hyperic.sigar.SigarException;

/**
 *
 * @author Desarrollo
 */
public class Principal {

    public static void main(String[] args) throws SigarException, InterruptedException {

        int i = 0;
        while (i == 0) {
            Panel1 frame = new Panel1(args);
            frame.setSize(1300, 700);
            frame.setTitle("Captura de Procesos");
            frame.setVisible(true);
            Thread.sleep(3000);
        }
    }
}
