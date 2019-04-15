/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recibos;

import facturacion.clientes.Clientes;
import facturacion.clientes.Facturable;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import interfacesPrograma.Busquedas;
import interfacesPrograma.Facturar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import objetosR.Conecciones;
import tablas.MiModeloTablaArticulos;

/**
 *
 * @author mauro di
 */
public class FormasDePago implements Formable{
    private Integer id;
    private String descripcion;
    private Double monto;
    private String banco;
    private String numero;
    private String vencimiento;
    private Integer idRecibo;
    private Integer idCliente;
    private static Transaccionable tra;
        
    private static ResultSet rs;
    private String sql;
    private Integer idTipoComprobante;
    private Integer idPago;

    public Integer getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }
    

    public Integer getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }


    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }
    

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public Boolean guardarCheques(Object listado) {
        FormasDePago forma=new FormasDePago();
        forma=(FormasDePago)listado;
        sql="insert into cheques (banco,idcliente,monto,vencimiento,numero,idrecibo) values ('"+forma.getBanco()+"',"+forma.getIdCliente()+","+forma.getMonto()+",'"+forma.getVencimiento()+"',"+forma.getNumero()+","+forma.getIdRecibo()+")";
        tra.guardarRegistro(sql);
        return true;
    }

    @Override
    public Boolean guardarEfectivo(Object listado) {
        FormasDePago forma=new FormasDePago();
        forma=(FormasDePago)listado;
        sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numerocomprobante,tipocomprobante,monto,tipomovimiento,idcaja,cantidad,idcliente,tipocliente,pagado) values ("+Inicio.usuario.getNumeroId()+",1,"+forma.getIdRecibo()+",8,"+forma.getMonto()+",6,"+Inicio.caja.getNumero()+",0,"+forma.getIdCliente()+",1,1)";
        tra.guardarRegistro(sql);
        return true;
    }

    @Override
    public ArrayList listarCheques() {
        sql="select * from cheques where estado=0 order by vencimiento";
        rs=tra.leerConjuntoDeRegistros(sql);
        FormasDePago pago=null;
        ArrayList lista=new ArrayList();
        try {
            while(rs.next()){
                pago=new FormasDePago();
                pago.setId(rs.getInt("id"));
                pago.setIdCliente(rs.getInt("idCliente"));
                pago.setBanco(rs.getString("banco"));
                pago.setIdRecibo(rs.getInt("idRecibo"));
                pago.setMonto(rs.getDouble("monto"));
                pago.setNumero(rs.getString("numero"));
                pago.setVencimiento(rs.getString("vencimiento"));
                lista.add(pago);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormasDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public ArrayList listarChequesPorCliente(Integer idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarChequesPorEstado(Integer idEstado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarChequesPorRecibo(Integer idRecibo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarChequesPorProveedor(Integer idProveedor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarChequesPorReciboDeProveedor(Integer idRecibo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarChequesEnTabla(ArrayList listado) {
        MiModeloTablaArticulos mod=new MiModeloTablaArticulos();
        FormasDePago pago=new FormasDePago();
        mod.addColumn("Cliente");
        mod.addColumn("Banco");
        mod.addColumn("Monto");
        mod.addColumn("Vencimiento");
        Object[] fila=new Object[4];
        Iterator it=listado.listIterator();
        Clientes cliente=new Clientes();
        Facturar bus=new Clientes();
        
        while(it.hasNext()){
            pago=(FormasDePago)it.next();
            cliente=(Clientes) bus.cargarPorCodigoAsignado(pago.getIdCliente());
            fila[0]=cliente.getRazonSocial();
            fila[1]=pago.getBanco();
            fila[2]=String.valueOf(pago.getMonto());
            fila[3]=pago.getVencimiento();
            mod.addRow(fila);
        }
        return mod;
    }

    @Override
    public DefaultListModel mostrarChequesEnListado(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPagosProveedores(Integer orden) {
        FormasDePago forma;
        ArrayList listado=new ArrayList();
        sql="select * from detallepagos where idcomprobante="+orden;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                forma=new FormasDePago();
                forma.setIdRecibo(rs.getInt("idcomprobante"));
                forma.setDescripcion(rs.getString("descripcion"));
                forma.setBanco(rs.getString("banco"));
                forma.setNumero(rs.getString("numero"));
                forma.setVencimiento(rs.getString("vencimiento"));
                forma.setMonto(rs.getDouble("monto"));
                listado.add(forma);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormasDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Boolean guardarPagoAProveedores(Object pagos) {
        FormasDePago forma=new FormasDePago();
        forma=(FormasDePago)pagos;
        sql="insert into detallepagos (idtipocomprobante,idcomprobante,idpago,descripcion,banco,monto,vencimiento,numero) values ("+forma.getIdTipoComprobante()+","+forma.getIdRecibo()+","+forma.getIdPago()+",'"+forma.getDescripcion()+"','"+forma.getBanco()+"',"+forma.getMonto()+",'"+forma.getVencimiento()+"',"+forma.getNumero()+")";
        tra.guardarRegistro(sql);
        return true;
    }
    
    
}
