/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resto;

/**
 *
 * @author Usuario
 */
public class Mesas {
    private int id;
    private String descripcion;
    private int idMozo;
    private String nombreMozo;
    private Integer idComanda;

    public Integer getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
    }
    

    public String getNombreMozo() {
        return nombreMozo;
    }

    public void setNombreMozo(String nombreMozo) {
        this.nombreMozo = nombreMozo;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdMozo() {
        return idMozo;
    }

    public void setIdMozo(int idMozo) {
        this.idMozo = idMozo;
    }
    
    
}
