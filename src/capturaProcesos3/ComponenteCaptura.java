/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author Dipper
 */
public class ComponenteCaptura extends JPanel implements ActionListener {

    //de la clase memoria
    Memoria m;
    //de la clase procesos
    Procesos p;
    //creamos boton
    JButton button;
    //valores que seran llenados 

    float fox2 = 0, fox3 = 0, wo2 = 0, wo3 = 0, ex2 = 0, ex3 = 0;
    //para los procesos sistemas y usuario
    ListaProcesos pro = new ListaProcesos();

    //para los procesos de los programas
    ListaProcesos mozi = new ListaProcesos();
    ListaProcesos mozi2 = new ListaProcesos();

    ListaProcesos word = new ListaProcesos();
    ListaProcesos word2 = new ListaProcesos();

    ListaProcesos excel = new ListaProcesos();
    ListaProcesos excel2 = new ListaProcesos();

    //constructor
    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    public ComponenteCaptura(String[] args) throws SigarException, InterruptedException {
        //instancia de procesos;
        p = new Procesos();
        //regresa datos de los procesos
        p.listProcess(args);
        //creamos un boton
        button = new JButton("Compactar");
        

    }

    //metodo para usar el boton
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            guardar(0.0f, 0.0f, 0.0f);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ComponenteCaptura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //metodo para guardar los procesos en un archivo
    public void guardar(Float num1, Float num2, Float num3) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("procesos.txt");
            pw = new PrintWriter(fichero);

            System.out.println("Escribiendo en el archivo.txt");

            pw.print(num1 + "!" + num2 + "!" + num3 + "!");
            System.out.println("");

        } catch (Exception e) {
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //metodo para leer los procesos en un archivo
    public void leer() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        StringTokenizer st;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("procesos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            System.out.println("Leyendo el contendio del archivo.txt");
            while ((linea = br.readLine()) != null) {
                System.out.print(linea);
                st = new StringTokenizer(linea, "!");
                if (st.hasMoreTokens()) {
                    fox3 = Float.parseFloat(st.nextToken());
                    wo3 = Float.parseFloat(st.nextToken());
                    ex3 = Float.parseFloat(st.nextToken());
                } else {
                    System.out.println("gaaa");
                }

            }
            System.out.println("");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        button.setSize(150, 30);
        button.setLocation(1000, 550);
        button.setVisible(true);

        float sistema = 0, usuario = 0, fox = 0, wo = 0, ex = 0;

        try {
            this.m = new Memoria();
        } catch (SigarException ep) {
            Logger.getLogger(ComponenteCaptura.class.getName()).log(Level.SEVERE, null, ep);
        }

        System.out.println(p.procesos);

        for (ListaProcesos listaProcesos : p.lp) {
            if ("Sistemaaa".equals(listaProcesos.getNumSesion())) {
                try {
                    sistema = sistema + Float.parseFloat(listaProcesos.getUsoMemoria());
                } catch (NumberFormatException n) {
                    sistema = sistema + 0;
                }

            } else {
                if (null == listaProcesos.getNomProceso()) {
                    try {
                        usuario = usuario + Float.parseFloat(listaProcesos.getUsoMemoria());
                    } catch (NumberFormatException n) {
                        sistema = sistema + 0;
                    }
                } else {
                    switch (listaProcesos.getNomProceso()) {
                        case "C:\\Program Files\\Mozilla Firefox\\firefox.exe":
                            try {
                                fox = fox + Float.parseFloat(listaProcesos.getUsoMemoria());
                            } catch (NumberFormatException n) {

                            }
                            break;
                        case "C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE":
                            try {
                                wo = wo + Float.parseFloat(listaProcesos.getUsoMemoria());
                            } catch (NumberFormatException n) {

                            }
                            break;
                        case "C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.EXE":
                            try {
                                ex = ex + Float.parseFloat(listaProcesos.getUsoMemoria());
                            } catch (NumberFormatException n) {

                            }
                            break;
                        default:
                            try {
                                usuario = usuario + Float.parseFloat(listaProcesos.getUsoMemoria());
                            } catch (NumberFormatException n) {
                                sistema = sistema + 0;
                            }
                            break;
                    }
                }

            }
        }
        leer();

        if (fox != 0) {
            fox2 = fox;
            guardar(fox2, wo3, ex3);
        }
        if (wo != 0) {
            wo2 = wo;
            guardar(fox3, wo2, ex3);
        }

        if (ex != 0) {
            ex2 = ex;
            guardar(fox3, wo3, ex2);
        }

        pro.setSistema(sistema);
        pro.setUsuario(usuario);

        mozi.setUsuario(fox);
        mozi2.setUsuario(fox3);
        word.setUsuario(wo);
        word2.setUsuario(wo3);
        excel.setUsuario(ex);
        excel2.setUsuario(ex3);

        
        fox2 = 0;
        wo2 = 0;
        ex2 = 0;
        fox3 = 0;
        wo3 = 0;
        ex3 = 0;

        //guarda los valores de sistema y usuario tiempo real
        System.out.println(pro.getSistema());
        System.out.println(pro.getUsuario());

        //guarda los valores de firefox word y excel tiempo real
        System.out.println(mozi.getUsuario());
        System.out.println(word.getUsuario());
        System.out.println(excel.getUsuario());

        //guardamos en variables locales los datos
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

        int sis = (int) (pro.getSistema() / 7);
        int usu = (int) (pro.getUsuario() / 7);

        //captura los procesos de mozila
        int foxi = (int) (mozi.getUsuario() / (7));
        //la ultima captura de los procesos de mozila para generar el espacio en blanco
        int foxva = (int) (mozi2.getUsuario() / (7));

        int dWord = (int) (word.getUsuario() / (7));
        int dWordva = (int) (word2.getUsuario() / (7));

        int dExcel = (int) (excel.getUsuario() / (7));
        int dExcelva = (int) (excel2.getUsuario() / (7));
        super.paint(g);

        //titulo de barra de procesos
        g.setColor(Color.BLACK);
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));
        g.drawString("Barra de procesos", 100, 350);
        //titulo del panel
        g.setFont(new Font("Helvetica", Font.PLAIN, 30));
        g.drawString("Rendimiento de Memoria Real", 450, 100);

        //barra de procesos
        g.drawRect(100, 400, (int) (mt / 7), 100);

        //barra de procesos sistemas
        g.setColor(new Color(68, 127, 128));
        g.fillRect(100, 400, sis, 100);

        //barra de procesos usuarios
        g.setColor(new Color(212, 254, 255));
        g.fillRect(100 + sis, 400, usu, 100);

        //si fox que es valor real de firefox no es 0
        if (foxi != 0) {
            g.setColor(new Color(179, 104, 59));
            g.fillRect(100 + sis + usu, 400, foxi, 100);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Helvetica", Font.PLAIN, 10));
            g.drawString("" + Math.round(pro.getUsuario() + mozi.getUsuario() + pro.getSistema()), (70 + sis + usu + foxi), 520);
            g.drawString("Firefox", (100 + sis + usu), 460);
            g.drawString("" + mozi.getUsuario() + " MB", 100 + sis + usu, 470);

            if (dWord != 0) {
                g.setColor(new Color(87, 149, 255));
                g.fillRect(100 + sis + usu + foxi, 400, dWord, 100);

                g.setColor(Color.BLACK);
                g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                g.drawString("" + Math.round(pro.getUsuario() + mozi.getUsuario() + pro.getSistema() + word.getUsuario()), (100 + sis + usu + foxi + dWord), 525);
                g.drawString("Word", (100 + sis + usu + foxi), 440);
                g.drawString("" + word.getUsuario() + " MB", 100 + sis + usu + foxi, 450);

                if (dExcel != 0) {
                    g.setColor(new Color(102, 179, 134));
                    g.fillRect(100 + sis + usu + foxi + dWord, 400, dExcel, 100);

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                    g.drawString("" + Math.round(pro.getUsuario() + mozi.getUsuario() + pro.getSistema() + word.getUsuario() + excel.getUsuario()), (100 + sis + usu + foxi + dWord + dExcel), 520);
                    g.drawString("Excel", (100 + sis + usu + foxi + dWord), 460);
                    g.drawString("" + excel.getUsuario() + " MB", 100 + sis + usu + foxi + dWord, 470);
                    g.drawString("Word", (100 + sis + usu + foxi), 440);
                    g.drawString("" + word.getUsuario() + " MB", 100 + sis + usu + foxi, 450);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawRect(100 + sis + usu + foxi + dWord, 400, dExcelva, 100);
                }
            } else {
                g.setColor(Color.BLACK);
                g.drawRect(100 + sis + usu + foxi, 400, dWordva, 100);

                if (dExcel != 0) {
                    g.setColor(new Color(102, 179, 134));
                    g.fillRect(100 + sis + usu + foxi + dWordva, 400, dExcel, 100);

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                    g.drawString("" + Math.round(pro.getUsuario() + mozi.getUsuario() + pro.getSistema() + word2.getUsuario() + excel.getUsuario()), (100 + sis + usu + foxi + dWordva + dExcel), 520);
                    g.drawString("Excel", (100 + sis + usu + foxi + dWordva ), 460);
                    g.drawString("" + excel.getUsuario() + " MB", 100 + sis + usu + foxi + dWordva, 470);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawRect(100 + sis + usu + foxi + dWordva, 400, dExcelva, 100);
                }
            }
        } else {
            g.setColor(Color.BLACK);
            g.drawRect(100 + sis + usu, 400, foxva, 100);

            if (dWord != 0) {
                g.setColor(new Color(87, 149, 255));
                g.fillRect(100 + sis + usu + foxva, 400, dWord, 100);

                g.setColor(Color.BLACK);
                g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                g.drawString("" + Math.round(pro.getUsuario() + mozi2.getUsuario() + pro.getSistema() + word.getUsuario()), (100 + sis + usu + foxva + dWord), 520);
                g.drawString("Word", (100 + sis + usu + foxva ), 440);
                g.drawString("" + word.getUsuario() + " MB", 100 + sis + usu + foxva , 450);

                if (dExcel != 0) {
                    g.setColor(new Color(102, 179, 134));
                    g.fillRect(100 + sis + usu + foxva + dWord, 400, dExcel, 100);

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                    g.drawString("" + Math.round(pro.getUsuario() + mozi2.getUsuario() + pro.getSistema() + word.getUsuario() + excel.getUsuario()), (100 + sis + usu + foxva + dWord + dExcel), 520);
                    g.drawString("Excel", (100 + sis + usu + foxva + dWord ), 460);
                    g.drawString("" + excel.getUsuario() + " MB", 100 + sis + usu + foxva + dWord , 470);
                    g.drawString("Word", (100 + sis + usu + foxva ), 440);
                    g.drawString("" + word.getUsuario() + " MB", 100 + sis + usu + foxva , 450);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawRect(100 + sis + usu + foxva + dWord, 400, dExcelva, 100);
                }
            } else {
                g.setColor(Color.BLACK);
                g.drawRect(100 + sis + usu + foxva, 400, dWordva, 100);

                if (dExcel != 0) {
                    g.setColor(new Color(102, 179, 134));
                    g.fillRect(100 + sis + usu + foxva + dWordva, 400, dExcel, 100);

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                    g.drawString("" + Math.round(pro.getUsuario() + mozi2.getUsuario() + pro.getSistema() + word2.getUsuario() + excel.getUsuario()), (70 + sis + usu + foxva + dWordva + dExcel), 520);
                    g.drawString("Excel", (100 + sis + usu + foxva + dWordva ), 460);
                    g.drawString("" + excel.getUsuario() + " MB", 100 + sis + usu + foxva + dWordva , 470);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawRect(100 + sis + usu + foxva + dWordva, 400, dExcelva, 100);
                }
            }
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString("0", 100, 520);
        g.drawString("" + Math.round(pro.getSistema()), 70 + sis, 520);
        g.drawString("" + (Math.round(pro.getUsuario()) + Math.round(pro.getSistema())), 70 + sis + usu, 520);

        g.drawString("Sistema Operativo", 100, 460);
        g.drawString("" + pro.getSistema() + " MB", 100, 470);

        g.drawString("Usuario", 100 + sis, 460);
        g.drawString("" + pro.getUsuario() + " MB", 100 + sis, 470);

        g.drawString("" + Math.round(mt) + " MB", 1200, 520);

        g.setFont(new Font("Helvetica", Font.PLAIN, 20));
        g.drawString("RAM : " + Math.round(mt) + " MB", 100, 150);
        g.drawString("RAM EN USO: " + Math.round(mu) + " MB", 100, 200);
        g.drawString("RAM LIBRE: " + Math.round(ml) + " MB", 100, 250);

    }

}
