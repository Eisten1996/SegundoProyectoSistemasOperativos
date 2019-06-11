/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturaProcesos;

/**
 *
 * @author Dipper
 */
public class CProgramas {
    private String nombreImagen;
    private String PID;
    private String nombreSesion;
    private String numSesion;
    private String usoMemoria;
    private float usuario;
    private float sistema;

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

    public CProgramas() {
    }

    public CProgramas(String nombreImagen, String PID, String nombreSesion, String numSesion, String usoMemoria) {
        this.nombreImagen = nombreImagen;
        this.PID = PID;
        this.nombreSesion = nombreSesion;
        this.numSesion = numSesion;
        this.usoMemoria = usoMemoria;
    }
    

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getNombreSesion() {
        return nombreSesion;
    }

    public void setNombreSesion(String nombreSesion) {
        this.nombreSesion = nombreSesion;
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
     
}
