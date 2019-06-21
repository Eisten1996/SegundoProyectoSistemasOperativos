/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.hyperic.sigar.SigarException;

public class Grafico1 extends JFrame {

    private final JPanel contentPane;
    Memoria m;
    int cont = 0;
    float sistema = 0, fox = 0, usuario = 0, wo = 0;
    String a1, a2, a3, a4, a5, a;
    ArrayList<CProgramas> cp = new ArrayList<>();
    Procesos gpl = new Procesos();
    CProgramas pro = new CProgramas();
    CProgramas firefox = new CProgramas();
    CProgramas word = new CProgramas();
    ArrayList<CProgramas> fire = new ArrayList<>();
    ArrayList<CProgramas> wordA = new ArrayList<>();
    String proc = gpl.obtenerListaDeProcesos();
    final StringTokenizer st = new StringTokenizer(proc, "  ");

    public Grafico1() {
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

        while (st.hasMoreTokens() && cont < gpl.procesos + 14) {
            if (cont < 18) {
                a = st.nextToken();
                // System.out.println(a);
                // System.out.println(cont);
            }
            cont++;
            if (cont > 18) {
                a1 = st.nextToken();
                if (a1.equalsIgnoreCase("Memory")) {
                    a1 = " " + st.nextToken();
                    //  System.out.print(a1);
                } else if (a1.equalsIgnoreCase("NVIDIA")) {
                    a1 = " " + st.nextToken() + " " + st.nextToken();
                    // System.out.print(a1);
                }
                a2 = st.nextToken();
                // System.out.print(a2);
                a3 = st.nextToken();
                // System.out.print(a3);
                a4 = st.nextToken();
                //System.out.print(a4);
                a5 = st.nextToken();
                // System.out.print(a5);
                if (a1.equalsIgnoreCase("firefox.exe")) {
                    fire.add(new CProgramas(a1, a2, a3, a4, a5));
                } else if (a1.equalsIgnoreCase("WINWORD.exe")) {
                    wordA.add(new CProgramas(a1, a2, a3, a4, a5));
                } else {
                    cp.add(new CProgramas(a1, a2, a3, a4, a5));
                }

                st.nextToken();
                // System.out.println(a);
                // System.out.println(cont);
            }
            if (cont == 18) {
                a1 = st.nextToken() + " " + st.nextToken() + " " + st.nextToken();
                // System.out.print(a1);
                a2 = st.nextToken();
                // System.out.print(a2);
                a3 = st.nextToken();
                // System.out.print(a3);
                a4 = st.nextToken();
                // System.out.print(a4);
                a5 = st.nextToken();
                // System.out.print(a5);
                cp.add(new CProgramas(a1, a2, a3, a4, a5));
                st.nextToken();
                // System.out.println(a);
                // System.out.println(cont);
            }

        }
        for (CProgramas cProgramas : cp) {
            if ("0".equals(cProgramas.getNumSesion())) {
                sistema = sistema + Float.parseFloat(cProgramas.getUsoMemoria());
            } else {
                usuario = usuario + Float.parseFloat(cProgramas.getUsoMemoria());
            }

        }

        for (CProgramas cProgramas : fire) {
            fox = fox + Float.parseFloat(cProgramas.getUsoMemoria());
        }

        for (CProgramas cProgramas : wordA) {
            wo = wo + Float.parseFloat(cProgramas.getUsoMemoria());
        }

        pro.setSistema(sistema);
        pro.setUsuario(usuario);

        firefox.setUsuario(fox);

        word.setUsuario(wo);

        System.out.println(pro.getSistema());
        System.out.println(pro.getUsuario());
        System.out.println(firefox.getUsuario());
        System.out.println(word.getUsuario());
        sistema = 0;
        usuario = 0;
        wo = 0;
        fox = 0;

        try {
            this.m = new Memoria();
        } catch (SigarException ex) {
            Logger.getLogger(Grafico1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        System.out.println("Ram Total:\t" + mt);
        System.out.println("Ram Usada:\t" + mu);
        System.out.println("Ram Libre:\t" + ml);
        System.out.println("Swap Total:\t" + st);
        System.out.println("Swap Usada:\t" + su);
        System.out.println("Swap Libre:\t" + sl);

        int a = (int) (pro.getSistema() / (7) - 450 / 7);
        int b = (int) (pro.getUsuario() / (7) - 450 / 7);
        int f = (int) (firefox.getUsuario() / (20));
        int w = (int) (word.getUsuario() / (20));
        
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
        g.fill3DRect(100 + a, 400, b - a, 100, true);
        if (f != 0) {
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
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("0", 100, 520);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("" + Math.round(pro.getSistema()) + " MB", 100 / 2 + a, 520);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("" + Math.round(pro.getUsuario()) + " MB", (100 / 2 + b), 520);
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
        g.drawString(""+Math.round(mt)+" MB", 1200, 520);

    }
}
