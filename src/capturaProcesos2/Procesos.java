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
public class Procesos extends SigarCommandBase {

    ArrayList<ListaProcesos> lp = new ArrayList<>();
    String a, b, c;
    int num = 0;
    String procesos = "";

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
            a = (String) i.next();
            b = (String) i.next();
            c = (String) i.next();
            buf.append(a);
            buf.append("\t");
            buf.append(b);
            buf.append("\t");
            buf.append(c);
            buf.append("\t");
            lp.add(new ListaProcesos(a, b, c));
            hasNext = i.hasNext();
        }
        return buf.toString();
    }

    public List getInfo(SigarProxy sigar, long pid)
            throws SigarException {

        String unknown = "Sistemaaa";

        List info = new ArrayList();

        try {
            ProcCredName cred = sigar.getProcCredName(pid);
            info.add(cred.getUser());
        } catch (SigarException e) {
            info.add(unknown);
        }

        try {
            ProcMem mem = sigar.getProcMem(pid);
            String j = Sigar.formatSize(mem.getRss());
            Float k;
            if (j.substring(j.length() - 1, j.length()).equalsIgnoreCase("M")) {
                j = j.substring(0, j.length() - 1);
            } else {
                k = Float.parseFloat(j.substring(0, j.length() - 1)) / 1024;
                j = String.valueOf(k);
            }

            info.add(j);

        } catch (SigarException e) {
            info.add(unknown);
        }

        String name = ProcUtil.getDescription(sigar, pid);
        info.add(name);
        return info;
    }

    public void output(long pid) throws SigarException {

        //println(join(getInfo(this.proxy, pid)));
        procesos = procesos + join(getInfo(this.proxy, pid)) + "\n";

    }

    public String listProcess(String[] args) throws SigarException {
        output(args);
        return procesos;
    }

}
