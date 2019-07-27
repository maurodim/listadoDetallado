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
public class TiposIva {
    private int id;
    private double baseImponible;
    private double importe;
    private String descripcion;

    public TiposIva(int id, double baseImponible, double importe,double alicuota) {
        this.id = id;
        this.baseImponible = baseImponible;
        this.importe = importe;
        this.descripcion="Iva "+alicuota+"%";
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
    public int getId() {
        return id;
    }

    public double getBaseImponible() {
        return baseImponible;
    }

    public double getImporte() {
        return importe;
    }
    
    
}
