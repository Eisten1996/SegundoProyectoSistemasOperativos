/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Dipper
 */
public class Procesos {

    int procesos = 0;

    CProgramas programa = new CProgramas();

    public String obtenerListaDeProcesos() {
        Process p;
        Runtime runTime;
        String process = null;
        try {
            System.out.println("Processes Reading is started...");

            //Get Runtime environment of System
            runTime = Runtime.getRuntime();

            //Execute command thru Runtime
            p = runTime.exec("tasklist");      // For Windows
            //p=r.exec("ps ux");              //For Linux
            try ( //Create Inputstream for Read Processes
                    InputStream inputStream = p.getInputStream(); 
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream); 
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                //Read the processes from sysrtem and add & as delimeter for tokenize the output
                String line = bufferedReader.readLine();
                process = "  ";
                while (line != null) {
                    line = bufferedReader.readLine();
                    process += line + "  ";
                    procesos++;
                }

            }

            System.out.println("Processes are read.");
            System.out.println(procesos);
        } catch (IOException e) {
            System.out.println("Exception arise during the read Processes");
        }
        return process;
    }
}
