/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturaElectronica.Objetos;

import Conversores.Numeros;


/**
 *
 * @author mauro di
 */
public class DetalleFacturas{
    private String codigo;
    private String descripcion;
    private String cantidadS;
    private String precioUnitarioS;
    private String descuentoS;
    private String precioTotalS;
    private Double cantidad;
    private Double precioUnitario;
    private Double descuento;
    private Double precioTotal;
    private Integer idArticulo;
    private String alicuota;
    private Double precioGravadoArticulo;

    public Double getPrecioGravadoArticulo() {
        return precioGravadoArticulo;
    }

    public void setPrecioGravadoArticulo(Double precioGravadoArticulo) {
        this.precioGravadoArticulo = precioGravadoArticulo;
    }
    
    public String getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(String alicuota) {
        this.alicuota = alicuota;
    }
    

    public String getDescripcion() {
        return descripcion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public Double getDescuento() {
        return descuento;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
        this.idArticulo=Integer.parseInt(codigo);
    }

    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidadS(String cantidadS) {
        this.cantidadS = cantidadS;
        this.cantidad=Numeros.ConvertirStringADouble(cantidadS);
    }

    public void setPrecioUnitarioS(String precioUnitarioS) {
        this.precioUnitarioS = precioUnitarioS;
        this.precioUnitario=Numeros.ConvertirStringADouble(precioUnitarioS);
    }

    public void setDescuentoS(String descuentoS) {
        this.descuentoS = descuentoS;
        this.descuento=Numeros.ConvertirStringADouble(descuentoS);
    }

    public void setPrecioTotalS(String precioTotalS) {
        this.precioTotalS = precioTotalS;
        this.precioTotal=Numeros.ConvertirStringADouble(precioTotalS);
    }
    
    
    
}
