/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author Desarrollo
 */
public class Panel1 extends JFrame {

    private final JPanel contentPane;
    Memoria m;
    Procesos p;
    float sistema = 0, fox = 0, usuario = 0, wo = 0;
    ListaProcesos pro = new ListaProcesos();
    

    public Panel1(String[] args) throws SigarException {
        p = new Procesos();
        p.listProcess(args);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.DARK_GRAY);
        setBounds(0, 0, 900, 900);
    }

    @Override
    public void paint(Graphics g) {
        try {
            this.m = new Memoria();
        } catch (SigarException ex) {
            Logger.getLogger(Panel1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(p.procesos);

        for (ListaProcesos listaProcesos : p.lp) {
            if ("Sistemaaa".equals(listaProcesos.getNumSesion())) {
                sistema = sistema + Float.parseFloat(listaProcesos.getUsoMemoria());
            } else {
                usuario = usuario + Float.parseFloat(listaProcesos.getUsoMemoria());
            }
        }

        pro.setSistema(sistema);
        pro.setUsuario(usuario);

        System.out.println(pro.getSistema());
        System.out.println(pro.getUsuario());

        sistema = 0;
        usuario = 0;

        super.paint(g);

        long memoriaTotal = m.memoria.getTotal();
        long memoriaUsada = m.memoria.getActualUsed();
        long memoriaLibre = m.memoria.getActualFree();
        long swapTotal = m.swap.getTotal();
        long swapUsada = m.swap.getUsed();
        long swapLibre = m.swap.getFree();
        double mt = memoriaTotal / 1024;
        mt = mt / 1024;
        double mu = memoriaUsada / 1024;
        mu = mu / 1024;
        double ml = memoriaLibre / 1024;
        ml = ml / 1024;
        double st = swapTotal / 1024;
        st = st / 1024;
        double su = swapUsada / 1024;
        su = su / 1024;
        double sl = swapLibre / 1024;
        sl = sl / 1024;
        double men=st-mt;
        System.out.println("Ram Total:\t" + mt);
        System.out.println("Ram Usada:\t" + mu);
        System.out.println("Ram Libre:\t" + ml);
        System.out.println("Swap Total:\t" + st);
        System.out.println("Swap Usada:\t" + su);
        System.out.println("Swap Libre:\t" + sl);

        int a = (int) (pro.getSistema() / 7);
        int b = (int) ((pro.getUsuario() / 7)-men/7);

        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.PLAIN, 16));
        g.drawString("Barra de procesos", 100, 450);
        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.PLAIN, 30));
        g.drawString("Rendimiento de Memoria Real", 450, 100);

        g.setColor(Color.WHITE);
        g.fill3DRect(100, 400, (int) (mt / 7), 100, true);
        g.setColor(Color.red);
        g.fill3DRect(100, 400, a, 100, true);
        g.setColor(Color.blue);
        g.fill3DRect(100 + a, 400, b + a, 100, true);
        /*if (f != 0) {
            g.setColor(Color.green);
            g.fill3DRect(100 + b, 400, f, 100, true);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Helvetica", Font.PLAIN, 10));
            g.drawString("" + Math.round(pro.getUsuario() + f) + " MB", (60 + b + f), 530);
            g.setColor(Color.green);
            g.fill3DRect(100, 590, 15, 15, true);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Helvetica", Font.PLAIN, 10));
            g.drawString("Firefox", 120, 600);

        }
        if (w != 0) {
            g.setColor(Color.magenta);
            g.fill3DRect(100 + b + f, 400, f + w, 100, true);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Helvetica", Font.PLAIN, 10));
            g.drawString("" + Math.round(pro.getUsuario() + f + w) + " MB", (60 + b + f + w * 7), 540);
            g.setColor(Color.magenta);
            g.fill3DRect(100, 610, 15, 15, true);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Helvetica", Font.PLAIN, 10));
            g.drawString("Word", 120, 620);
        }*/
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("0", 100, 520);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("" + Math.round(pro.getSistema()) + " MB", 70 + a, 520);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("" + (Math.round(pro.getUsuario())+ Math.round(pro.getSistema())-men) + " MB", 300  + b+a, 520);
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));
        g.drawString("RAM : " + Math.round(mt) + " MB", 100, 150);
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));
        g.drawString("RAM EN USO: " + Math.round(mu) + " MB", 100, 200);
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));
        g.drawString("RAM LIBRE: " + Math.round(ml) + " MB", 100, 250);

        g.setColor(Color.red);
        g.fill3DRect(100, 550, 15, 15, true);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("Sistema Operativo", 120, 560);
        g.setColor(Color.blue);
        g.fill3DRect(100, 570, 15, 15, true);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("Usuario", 120, 580);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("" + Math.round(mt) + " MB", 1200, 520);

    }
}
