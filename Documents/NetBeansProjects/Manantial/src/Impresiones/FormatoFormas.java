package Impresiones;

/**
 *
 * @author Usuario
 */
public class FormatoFormas {
    private String descripcion;
    private String monto;

    public FormatoFormas(String descripcion, String monto) {
        this.descripcion = descripcion;
        this.monto = monto;
    }

    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
    
    
}
