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
public class EncabezadoClientes {
    private final String razonSocial;
    private final String condicionIva;
    private final String direccion;
    private final String tipoDocumento;
    private final String numeroDocumento;

    public EncabezadoClientes(String razonSocial, String condicionIva, String direccion, String tipoDocumento, String numeroDocumento) {
        this.razonSocial = razonSocial;
        this.condicionIva = condicionIva;
        this.direccion = direccion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }
    
    
    public String getRazonSocial() {
        return razonSocial;
    }

    public String getCondicionIva() {
        return condicionIva;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    
    
}
