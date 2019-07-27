/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class EncabezadoPdf {

    private final String nombreComercio;
    private final String razonSocial;
    private final String direccion;
    private final String telefono;
    private final int punto;
    private final double numero;
    private final String cuit;
    private final String ingresosBrutos;
    private final String inicioActividades;
    private final String condicionIva;
    private final String razonSocialV;

    public EncabezadoPdf(int punto, double numero) {
        this.punto = punto;
        this.numero = numero;
        this.nombreComercio="";
        this.condicionIva="";
        this.cuit="";
        this.direccion="";
        this.ingresosBrutos="";
        this.inicioActividades="";
        this.razonSocial="";
        this.telefono="";
        this.razonSocialV="";
    }

    public EncabezadoPdf(String nombreComercio, String razonSocial, String direccion, String telefono, int punto, double numero, String cuit, String ingresosBrutos, String inicioActividades, String condicionIva) {
        this.razonSocialV=razonSocial;
        this.nombreComercio = nombreComercio;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.punto = punto;
        this.numero = numero;
        this.cuit = cuit;
        this.ingresosBrutos = ingresosBrutos;
        this.inicioActividades = inicioActividades;
        this.condicionIva = condicionIva;
    }

    public String getCondicionIva() {
        return condicionIva;
    }

    public String getNombreComercio() {
        return nombreComercio;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getPunto() {
        return punto;
    }

    public double getNumero() {
        return numero;
    }

    public String getCuit() {
        return cuit;
    }

    public String getIngresosBrutos() {
        return ingresosBrutos;
    }

    public String getInicioActividades() {
        return inicioActividades;
    }

}
