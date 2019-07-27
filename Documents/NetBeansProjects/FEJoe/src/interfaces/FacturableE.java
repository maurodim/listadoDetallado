/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public interface FacturableE {
    public Object recuperar(Object Fe);
    public Integer guardar(Object Fe);
    public Object modificar(Object Fe);
    public ArrayList listarPorEstado(Integer estado);
    public Object cargar(Integer id);
    public DefaultTableModel mostrarListado(ArrayList listado);
    public Object reEnviar(Object fe);
    public void eliminar(Object fe);
    public String reimprimir(Object fe);
    public String imprimir(Object fe);

    /**
     *
     * @param conexion
     * @param Condicion
     * @param archivoKey
     * @param archivoCrt
     * @param idCliente
     * @param cuitCliente
     * @param tipoComprobante
     * @param montoTotal
     * @param montoBruto
     * @param montoIva
     * @param ptoDeVenta
     * @param cuitVendedor
     * @param tipoVenta
     * @param ivas
     * @param tributos
     * @param razonSocial
     * @param direccion
     * @param condicionIvaCliente
     * @param lstDetalle
     * @param idPedido
     * @param nombreVendedor
     * @param condIvaVendedor
     * @param direccionVendedor
     * @param telefonoVendedor
     * @param ingBrutosVendedor
     * @param inicioActVendedor
     * @param lstOpcionales
     * @return
     */
    public Integer generar(Connection conexion,int Condicion,String archivoKey,String archivoCrt,Integer idCliente,String cuitCliente,int tipoComprobante,Double montoTotal,Double montoBruto,Double montoIva,int ptoDeVenta,String cuitVendedor,int tipoVenta,ArrayList ivas,ArrayList tributos,String razonSocial,String direccion,String condicionIvaCliente,ArrayList lstDetalle,Integer idPedido,String nombreVendedor,String razonSocialVendedor,String condIvaVendedor,String direccionVendedor,String telefonoVendedor,String ingBrutosVendedor,String inicioActVendedor,ArrayList listadoO);
    public String solicitarCertificado(String cuit);
}
