/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

/**
 *
 * @author Dipper
 */
public class Memoria {

    Sigar sg = new Sigar();
    Mem memoria;
    Swap swap;
   /* double memTotal = memoria.getTotal();
    double memLibre = memoria.getFree();
    double memUsada = memoria.getUsed();*/

    public Memoria() throws SigarException {
        memoria = sg.getMem();
        swap = sg.getSwap();
        
    }

}
