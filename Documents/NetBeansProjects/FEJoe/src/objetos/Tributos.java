/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author mauro
 */
public class Tributos {
    private int id;
    private String descripcion;
    private float baseImponible;
    private float alicuota;
    private float importe;

    public Tributos(int id, String descripcion, float baseImponible, float alicuota, float importe) {
        this.id = id;
        this.descripcion = descripcion;
        this.baseImponible = baseImponible;
        this.alicuota = alicuota;
        this.importe = importe;
    }

    
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getBaseImponible() {
        return baseImponible;
    }

    public float getAlicuota() {
        return alicuota;
    }

    public float getImporte() {
        return importe;
    }
    
    
}
