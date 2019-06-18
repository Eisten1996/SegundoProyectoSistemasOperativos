/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos2;

/**
 *
 * @author Dipper
 */
public class ListaProcesos {
    
    private String numSesion;
    private String usoMemoria;
    private String nomProceso;
    private float usuario;
    private float sistema;

    public ListaProcesos() {
    }

    public ListaProcesos(String numSesion, String usoMemoria, String nomProceso) {
        this.numSesion = numSesion;
        this.usoMemoria = usoMemoria;
        this.nomProceso = nomProceso;
    }
    
    

    public String getNumSesion() {
        return numSesion;
    }

    public void setNumSesion(String numSesion) {
        this.numSesion = numSesion;
    }

    public String getUsoMemoria() {
        return usoMemoria;
    }

    public void setUsoMemoria(String usoMemoria) {
        this.usoMemoria = usoMemoria;
    }

    public String getNomProceso() {
        return nomProceso;
    }

    public void setNomProceso(String nomProceso) {
        this.nomProceso = nomProceso;
    }

    public float getUsuario() {
        return usuario;
    }

    public void setUsuario(float usuario) {
        this.usuario = usuario;
    }

    public float getSistema() {
        return sistema;
    }

    public void setSistema(float sistema) {
        this.sistema = sistema;
    }
    
}
