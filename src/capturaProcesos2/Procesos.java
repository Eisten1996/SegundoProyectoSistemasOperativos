/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hyperic.sigar.ProcCredName;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcUtil;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.cmd.SigarCommandBase;

/**
 *
 * @author Dipper
 */
public class Procesos extends SigarCommandBase{
    ArrayList<ListaProcesos> lp = new ArrayList<>();
    String a,b,c;
    @Override
    public void output(String[] args) throws SigarException {
        long[] pids;
        if (args.length == 0) {
            pids = this.proxy.getProcList();
        } else {
            pids = this.shell.findPids(args);
        }

        for (int i = 0; i < pids.length; i++) {
            long pid = pids[i];
            try {
                output(pid);
            } catch (SigarException e) {
                this.err.println("Exception getting process info for "
                        + pid + ": " + e.getMessage());
            }
        }
    }

    public String join(List info) {
        StringBuilder buf = new StringBuilder();
        Iterator i = info.iterator();
        boolean hasNext = i.hasNext();
        while (hasNext) {
            buf.append((String) i.next());
            hasNext = i.hasNext();
            if (hasNext) {
                buf.append("\t");
            }
        }

        return buf.toString();
    }
    

    public List getInfo(SigarProxy sigar, long pid)
            throws SigarException {

        String unknown = "Sistema";

        List info = new ArrayList();

        try {
            ProcCredName cred = sigar.getProcCredName(pid);
            info.add(cred.getUser());
            a=cred.getUser();
        } catch (SigarException e) {
            info.add(unknown);
        }

        try {
            ProcMem mem = sigar.getProcMem(pid);
            info.add(Sigar.formatSize(mem.getRss()));
            b=Sigar.formatSize(mem.getRss());

        } catch (SigarException e) {
            info.add(unknown);
        }

        String name = ProcUtil.getDescription(sigar, pid);
        info.add(name);
        c=name;
        lp.add(new ListaProcesos(a, b, c));
        return info;
    }

    public void output(long pid) throws SigarException {
        
        println(join(getInfo(this.proxy, pid)));

    }
    
    public static void main(String[] args) {
        System.out.println();
    }
}
