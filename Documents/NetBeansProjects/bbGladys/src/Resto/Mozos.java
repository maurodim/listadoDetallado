/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resto;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Mozos {
    private int id;
    private String nombre;
    private ArrayList mesasAsignadas;
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    

    public ArrayList getMesasAsignadas() {
        return mesasAsignadas;
    }

    public void setMesasAsignadas(ArrayList mesasAsignadas) {
        this.mesasAsignadas = mesasAsignadas;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}