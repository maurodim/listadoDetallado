/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Compras;

import Proveedores.Proveedores;
import Administracion.TipoComprobante;
import Conversores.Numeros;
import interfaceGraficasManantial.Inicio;
import interfaces.Adeudable;
import interfaces.Comprobable;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import interfacesPrograma.Facturar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosR.Conecciones;

/**
 *
 * @author mauro
 */
public class FacturaProveedor implements Comprobable,Facturar,Adeudable{
    private Integer id;
    private String numeroFactura;
    private Integer numeroProveedor;
    private String nombreProveedor;
    private Double montoFinal;
    private Integer idRemito;
    private Integer idCaja;
    private Date fecha;
    private Integer pagada;
    private Integer idUsuario;
    private Integer idSucursal;
    private static Integer numeroRecibo;
    private static Integer numeroFacturaP;
    private String observaciones;

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }
    

    public Integer getPagada() {
        return pagada;
    }

    public void setPagada(Integer pagada) {
        this.pagada = pagada;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    public FacturaProveedor() {
        this.numeroProveedor=0;
        this.numeroFactura="0";
        this.pagada=0;
        this.idSucursal=Inicio.sucursal.getNumero();
        this.idUsuario=Inicio.usuario.getNumeroId();
        
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumero(String numero) {
        this.numeroFactura = numero;
    }

    public Integer getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(Integer numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(Double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

    
    private static void numeroActualRecibo(){
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql="select * from tipocomprobantes where id=11";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            numeroRecibo=rs.getInt("numeroActivo");
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void GuardarNumeroRecibo(){
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql="update tipocomprobantes set numeroActivo="+numeroRecibo+" where id=11";
        tra.guardarRegistro(sql);
    }
    private static void numeroActualFactura(){
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql="select * from tipocomprobantes where numero=6";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            numeroFacturaP=rs.getInt("numeroActivo");
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void GuardarNumeroFactura(){
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql="update tipocomprobantes set numeroActivo="+numeroFacturaP+" where numero=6";
        tra.guardarRegistro(sql);
    }
    
    @Override
    public Integer nuevoComprobante(Object objeto) {
        FacturaProveedor fact=(FacturaProveedor)objeto;
        Integer idFactura=0;
        String sql="insert into movimientosproveedores (numeroProveedor,monto,numeroComprobante,idRemito,idUsuario,tipoComprobante,idSucursal) values ("+fact.getNumeroProveedor()+","+fact.getMontoFinal()+",'"+fact.getNumeroFactura()+"',"+fact.getIdRemito()+","+fact.getIdUsuario()+",5,"+fact.getIdSucursal()+")";
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(tra.guardarRegistro(sql)){
            sql="select * from movimientosproveedores order by id desc fetch first 1 rows only";
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                    idFactura=rs.getInt(1);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return idFactura;
    }

    @Override
    public Boolean agregarItem(Object item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarComprobante(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean eliminarComprobante(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leerComprobante(Integer numero) {
        FacturaProveedor factu = null;
        try {
            Transaccionable tra=new Conecciones();
            String sql="select * from movimientosproveedores where id="+numero;
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                factu=new FacturaProveedor();
                factu.setId(rs.getInt("id"));
                factu.setNumeroProveedor(rs.getInt("numeroproveedor"));
                factu.setFecha(rs.getDate("fecha"));
                factu.setMontoFinal(rs.getDouble("monto"));
                factu.setNumero(rs.getString("numerocomprobante"));
                factu.setObservaciones(rs.getString("observaciones"));
            }
            rs.close();
        } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factu;
    }

    @Override
    public ArrayList listarComprobantesPorFecha(Date fecha) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarComprobantesPorDeposito(Integer numeroDeposito) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarComprobantesPorProveedor(Integer numeroProveedor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean guardar(Object oob) {
        Boolean verif=false;
        FacturaProveedor fact=(FacturaProveedor)oob;
        // ACA VOY A GUARDAR EN MOVIEMINTOS DE CAJA Y MODIFICAR EN MOVIMIENTOS DE PROVEEDORES
        Double monto=fact.getMontoFinal() * -1;
        //insert into movimientoscaja(numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,pagado,tipoCliente,idCliente) values (1,1,(select tipocomprobantes.numeroActivo + 1 from tipocomprobantes where numero=11) ,6,1.00,2,1,1,2,1)
        String sql="insert into movimientoscaja(numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,pagado,tipoCliente,idCliente) values ("+fact.getIdUsuario()+","+fact.getIdSucursal()+",(select tipocomprobantes.numeroActivo + 1 from tipocomprobantes where id=11),6,"+monto+",2,"+fact.getIdCaja()+","+fact.getPagada()+",2,"+fact.getNumeroProveedor()+")";
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(tra.guardarRegistro(sql))//System.out.println(sql);
        sql="update movimientosproveedores set pagado="+fact.getPagada()+",fechaPago ='"+fact.getFecha()+"',idCaja="+fact.getIdCaja()+" where id="+fact.getId();
        if(tra.guardarRegistro(sql));//System.out.println(sql);       
        
        return verif;
        
        
    }

    @Override
    public Boolean modificar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean nuevo(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leer(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imprimirComprobante(int tipoComprobante, Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listadoBusqueda(String criterio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer guardarNuevoCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarDatosDelCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarClientes(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cargarPorCodigoDeBarra(String codigoDeBarra) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer leerNumeroDeComprobanteSiguiente(Integer numeroComprobante) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cargarPorCodigoAsignado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList ListarAPagar() {
        ArrayList listadoProveedoresPend=new ArrayList();
        FacturaProveedor fact;
        Personalizable per=new Proveedores();
        Proveedores proveedor=new Proveedores();
        String sql="select * from movimientosproveedores where pagado=0";
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                fact=new FacturaProveedor();
                int numP=rs.getInt("numeroProveedor");
                proveedor=(Proveedores)per.buscarPorNumero(numP);
                fact.setNumeroProveedor(proveedor.getNumero());
                fact.setNombreProveedor(proveedor.getNombre());
                fact.setMontoFinal(rs.getDouble("monto"));
                fact.setPagada(rs.getInt("pagado"));
                fact.setId(rs.getInt("id"));
                listadoProveedoresPend.add(fact);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoProveedoresPend;
    }

    @Override
    public ArrayList ListarACobrar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object ActualizarComprobante(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object PagarComprobante(Object objeto) {
       FacturaProveedor factProv=(FacturaProveedor)objeto;
       numeroActualRecibo();
       numeroRecibo++;
       String fech=Numeros.ConvertirFecha(Inicio.fechaVal);
       Transaccionable tra=null;
        try {
            tra = new Conecciones();
        
       String sql="insert into movimientosproveedores (numeroProveedor,monto,numeroComprobante,idUsuario,tipoComprobante,idSucursal,idRemito,pagado,idcaja,fechapago,numerofactura,numeroremito,subtotal,saldo,idcomprobante,porcentajedescuento,observaciones) values ("+factProv.getNumeroProveedor()+","+factProv.getMontoFinal()+",'"+numeroRecibo+"',"+factProv.getIdUsuario()+",11,"+factProv.getIdSucursal()+",0,1,"+Inicio.caja.getNumero()+",'"+Inicio.fechaDia+"','00','00',"+factProv.getMontoFinal()+",0.00,0,0.00,'EFECTIVO')";
       //String sql="update movimientosproveedores set pagado=1,numeroComprobante="+numeroRecibo+",idCaja="+Inicio.caja.getNumero()+",fechaPago='"+fech+"',idSucursal="+Inicio.sucursal.getNumero()+" where id="+factProv.getId();
       //System.out.println("VEAMOS "+sql);
       tra.guardarRegistro(sql);
       String ttx="PAGO A PROVEEDOR "+factProv.getNombreProveedor();
       Double monto=factProv.getMontoFinal();
       sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado,observaciones) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+numeroRecibo+",6,"+monto+",11,"+Inicio.caja.getNumero()+","+factProv.getNumeroProveedor()+",2,1,'"+ttx+"')";
        System.out.println("cajaa "+sql);
       tra.guardarRegistro(sql);
       GuardarNumeroRecibo();
       } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
       return factProv;
    }

    @Override
    public ArrayList ListarPorLocalidad(Integer idLocalidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listadoBusquedaFacturacion(String criterio, Double coeficiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargarPorCodigoDeBarraFacturacion(String cordigoDeBarra, double coeficiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargarPorCodigoAsignadoFacturacion(Integer id, double coeficiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object PagarConOtrosMedios(Object objeto) {
        FacturaProveedor factProv=(FacturaProveedor)objeto;
       numeroActualRecibo();
       numeroRecibo++;
       String fech=Numeros.ConvertirDateAString(factProv.getFecha())+" 00:00:00.000";
       Transaccionable tra=null;
        try {
            tra = new Conecciones();
        
       String sql="insert into movimientosproveedores (numeroProveedor,monto,numeroComprobante,idUsuario,tipoComprobante,idSucursal,idRemito,pagado,idcaja,fechapago,numerofactura,numeroremito,subtotal,saldo,idcomprobante,porcentajedescuento,observaciones,fecha) values ("+factProv.getNumeroProveedor()+","+factProv.getMontoFinal()+",'"+numeroRecibo+"',"+factProv.getIdUsuario()+",11,"+factProv.getIdSucursal()+",0,1,"+Inicio.caja.getNumero()+",'"+fech+"','00','00',"+factProv.getMontoFinal()+",0.00,0,0.00,'"+factProv.getObservaciones()+"','"+fech+"')";
       //String sql="update movimientosproveedores set pagado=1,numeroComprobante="+numeroRecibo+",idCaja="+Inicio.caja.getNumero()+",fechaPago='"+fech+"',idSucursal="+Inicio.sucursal.getNumero()+" where id="+factProv.getId();
       System.out.println("VEAMOS "+sql);
       tra.guardarRegistro(sql);
       String ttx="PAGO A PROVEEDOR "+factProv.getNombreProveedor();
       
       GuardarNumeroRecibo();
       } catch (InstantiationException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
       return factProv;
    }
    
    
}
