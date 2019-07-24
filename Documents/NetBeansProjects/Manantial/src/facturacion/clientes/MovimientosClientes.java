package facturacion.clientes;

import Conversores.Numeros;
import interfaceGraficasManantial.Inicio;
import interfaces.Transaccionable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetosR.Conecciones;
import tablas.MiModeloTablaContacto;

/**
 *
 * @author mauro di
 */
public class MovimientosClientes implements Facturable, Movible, Editables {

    private Integer id;
    private Integer idCliente;
    private Date fecha;
    private Double total;
    private Integer tipo;
    private Integer idUsuario;
    private Integer idPedido;
    private Integer idRemito;
    private String archivo;
    private Integer numeroFactura;
    private Integer estado;
    private String descripcionTipo;
    private Double montoOriginal;
    private String numeroFiscal;
    private Double subTotal;
    private Double descuento;
    private Double porcentajeDescuento;
    private Double saldo;
    private String nombreCliente;

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getNumeroFiscal() {
        return numeroFiscal;
    }

    public void setNumeroFiscal(String numeroFiscal) {
        this.numeroFiscal = numeroFiscal;
    }

    public Double getMontoOriginal() {
        return montoOriginal;
    }

    public void setMontoOriginal(Double montoOriginal) {
        this.montoOriginal = montoOriginal;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    @Override
    public Integer nuevaFactura(Object ped) {
        MovimientosClientes factura = new MovimientosClientes();
        int idNuevo = 0;
        factura = (MovimientosClientes) ped;
        
        try {
            Transaccionable tra = new Conecciones();
        String sql = "insert into facturas (idcliente,total,tipo,idusuario,idpedido,idremito,numerofactura,estado,saldo,subtotal,descuento,porcentajeD) values (" + factura.getIdCliente() + "," + factura.getTotal() + "," + factura.getTipo() + "," + factura.getIdUsuario() + ",1,1," + factura.getNumeroFactura() + "," + factura.getEstado() + "," + factura.getTotal() + "," + factura.getSubTotal() + "," + factura.getDescuento() + "," + factura.getPorcentajeDescuento() + ")";
        tra.guardarRegistro(sql);
        
        sql = "select * from facturas order by id desc fetch first 1 rows only";
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
            while (rs.next()) {
                idNuevo = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idNuevo;
    }

    @Override
    public ArrayList cargarDetallefactura(Integer idPed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargarEncabezadoFactura(Integer idPed, Integer tipo) {
        MovimientosClientes factura = new MovimientosClientes();
        String sql = "select id,estado,fecha,idcliente,idpedido,idremito,idusuario,numerofactura,tipo,total,subtotal,descuento,porcentajeD,(select tipocomprobantes.descripcion from tipocomprobantes where tipocomprobantes.numeroafip=facturas.tipo)as descripcionTipo from facturas where numerofactura=" + idPed + " and tipo=" + tipo;
        
        try {
            Transaccionable tra = new Conecciones();
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
            while (rs.next()) {
                factura.setEstado(rs.getInt("estado"));
                factura.setFecha(rs.getDate("fecha"));
                factura.setId(rs.getInt("id"));
                factura.setIdCliente(rs.getInt("idcliente"));
                factura.setIdPedido(rs.getInt("idpedido"));
                factura.setIdRemito(rs.getInt("idremito"));
                factura.setIdUsuario(rs.getInt("idusuario"));
                factura.setNumeroFactura(rs.getInt("numerofactura"));
                factura.setTipo(rs.getInt("tipo"));
                factura.setTotal(rs.getDouble("total"));
                factura.setDescripcionTipo(rs.getString("descripcionTipo"));
                factura.setSubTotal(rs.getDouble("subtotal"));
                factura.setDescuento(rs.getDouble("descuento"));
                factura.setPorcentajeDescuento(rs.getDouble("porcentajeD"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return factura;
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorCliente(Integer idClient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorEstado(Integer idClient, int estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean modificarFactura(Object ped) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarFactura(Integer idPed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList lista) {
        MiModeloTablaContacto listado1 = new MiModeloTablaContacto();
        MovimientosClientes cotizacion;
        Iterator iL = lista.listIterator();
        listado1.addColumn("Recibo");
        listado1.addColumn("Fecha");
        listado1.addColumn("Numero");
        listado1.addColumn("Monto");
        listado1.addColumn("Saldo");
        listado1.addColumn("Remito");
        Object[] fila = new Object[6];
        while (iL.hasNext()) {
            cotizacion = (MovimientosClientes) iL.next();
            fila[0] = false;
            fila[1] = String.valueOf(cotizacion.getFecha());

            fila[2] = String.valueOf(cotizacion.getNumeroFactura());

            fila[3] = String.valueOf(cotizacion.getMontoOriginal());
            if(cotizacion.getEstado()==0){
            fila[4] = Numeros.ConvertirNumero(cotizacion.getTotal());
            }else{
                fila[4] = Numeros.ConvertirNumero(cotizacion.getMontoOriginal());
            }
            fila[5] = String.valueOf(cotizacion.getIdRemito());
            listado1.addRow(fila);
        }
        return listado1;
    }

    @Override
    public void transformarEnRemito(Object ped, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void transformarEnrecibo(Object ped, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void transformarEndetalle(Object ped, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList convertirAArticulos(ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorClienteNoRemitidas(Integer idClient) {
        ArrayList listado = new ArrayList();
        String sql = "select facturas.*,pedidos.saldo as saldo1,tipocomprobantes.descripcion as descripcionTipo,remitos.numeroremito as remito from facturas left join pedidos on pedidos.idfactura=facturas.id join tipocomprobantes on tipocomprobantes.id=facturas.tipo left join remitos on remitos.id=facturas.idremito where facturas.idcliente=" + idClient;
        System.out.println(sql);
        
        try {
            Transaccionable tra = new Conecciones();
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
        MovimientosClientes factura;
            while (rs.next()) {
                factura = new MovimientosClientes();
                factura.setId(rs.getInt("id"));
                factura.setEstado(rs.getInt("estado"));
                factura.setFecha(rs.getDate("fecha"));
                factura.setIdCliente(rs.getInt("idcliente"));
                factura.setIdPedido(rs.getInt("idpedido"));
                factura.setIdUsuario(rs.getInt("idusuario"));
                factura.setNumeroFactura(rs.getInt("numerofactura"));
                factura.setIdRemito(rs.getInt("remito"));
                factura.setTipo(rs.getInt("tipo"));
                factura.setTotal(rs.getDouble("saldo1"));
                factura.setMontoOriginal(rs.getDouble("total"));
                factura.setDescripcionTipo(rs.getString("descripciontipo"));
                factura.setNumeroFiscal(rs.getString("numerofactura"));
                factura.setSubTotal(rs.getDouble("subtotal"));
                factura.setDescuento(rs.getDouble("descuento"));
                factura.setPorcentajeDescuento(rs.getDouble("porcentajeD"));
                listado.add(factura);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public ArrayList listarAdeudadaas(Integer idClient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean identificarPedidoAFactura(Integer idPedido, Integer idFactura, Integer numeroFactura, Integer tipoDeComprobante) {
        try {
            String sql = "update pedidos set idfactura=" + idFactura + " where id=" + idPedido;
            Transaccionable tra = new Conecciones();
            tra.guardarRegistro(sql);
            System.out.println(sql);
            sql = "update facturas set idpedido=" + idPedido + ",numerofactura=" + numeroFactura + " where id=" + idFactura;
            tra.guardarRegistro(sql);
            System.out.println(sql);
            sql = "update movimientosclientes set tipocomprobante=" + tipoDeComprobante + ",movimientosclientes.monto=(SELECT facturas.total from facturas where facturas.id=" + idFactura + ") where idpedido=" + idPedido;
            tra.guardarRegistro(sql);
            System.out.println(sql);
            //update movimientosclientes set movimientosclientes.monto=(SELECT facturas.total from facturas where facturas.id=6) where movimientosclientes.idpedido=5
            
        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void actualizadorDeEstado(Object factu) {
        //ACA DEBO PONER EL NUMERO DE ESTADO SI SE HACE RECIBO Y REMITO Y CARGAR EL SALDO
    }

    @Override
    public Object cargarIdFactura(Integer id) {
        MovimientosClientes factura = new MovimientosClientes();
        String sql = "select *,(select tipocomprobantes.descripcion from tipocomprobantes where tipocomprobantes.id=facturas.tipo)as descripcionTipo from facturas where id=" + id;
        
        try {
            Transaccionable tra = new Conecciones();
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
            while (rs.next()) {
                factura.setEstado(rs.getInt("estado"));
                factura.setFecha(rs.getDate("fecha"));
                factura.setId(rs.getInt("id"));
                factura.setIdCliente(rs.getInt("idcliente"));
                factura.setIdPedido(rs.getInt("idpedido"));
                factura.setIdRemito(rs.getInt("idremito"));
                factura.setIdUsuario(rs.getInt("idusuario"));
                factura.setNumeroFactura(rs.getInt("numerofactura"));
                factura.setTipo(rs.getInt("tipo"));
                factura.setTotal(rs.getDouble("total"));
                factura.setDescripcionTipo(rs.getString("descripcionTipo"));
                factura.setSubTotal(rs.getDouble("subtotal"));
                factura.setDescuento(rs.getDouble("descuento"));
                factura.setPorcentajeDescuento(rs.getDouble("porcentajeD"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return factura;
    }

    @Override
    public ArrayList ListarMovimientos(Integer id) {
        ArrayList listado = new ArrayList();
        String sql = "SELECT movimientosclientes.*,tipocomprobantes.descripcion,pedidos.saldo,pedidos.idfactura,facturas.numerofactura, facturas.tipo FROM movimientosclientes left join tipocomprobantes on tipocomprobantes.numeroafip=movimientosclientes.tipocomprobante left join pedidos on pedidos.id=movimientosclientes.idpedido left JOIN facturas on facturas.id=pedidos.idfactura where movimientosclientes.numeroproveedor=" + id;
        System.out.println(sql);
        
        try {
            Transaccionable tra = new Conecciones();
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
        MovimientosClientes factura;
            while (rs.next()) {
                factura = new MovimientosClientes();
                factura.setId(rs.getInt("id"));
                factura.setEstado(rs.getInt("pagado"));
                factura.setFecha(rs.getDate("fecha"));
                factura.setIdCliente(rs.getInt("numeroproveedor"));
                factura.setIdPedido(rs.getInt("idpedido"));
                factura.setIdUsuario(rs.getInt("idusuario"));
                factura.setNumeroFactura(rs.getInt("numerofactura"));
                int editado = rs.getInt("editado");
                if (factura.numeroFactura != null) {

                } else {

                    factura.setNumeroFactura(rs.getInt("numerocomprobante"));
                }
                if (factura.numeroFactura == 0) {
                    factura.setNumeroFactura(rs.getInt("numerocomprobante"));
                }
                if (editado == 1) {
                    factura.setNumeroFactura(rs.getInt("numerocomprobante"));
                }
                factura.setIdRemito(rs.getInt("idremito"));
                factura.setTipo(rs.getInt("tipocomprobante"));
                factura.setTotal(rs.getDouble("monto"));
                //factura.setMontoOriginal(rs.getDouble("total"));
                factura.setSaldo(rs.getDouble("saldo"));
                factura.setDescripcionTipo(rs.getString("descripcion"));
                //factura.setNumeroFiscal(rs.getString("numerofactura"));
                //factura.setSubTotal(rs.getDouble("subtotal"));
                //factura.setDescuento(rs.getDouble("descuento"));
                //factura.setPorcentajeDescuento(rs.getDouble("porcentajeD"));
                listado.add(factura);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Double AjustarSaldo(Double saldoActual, Integer idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel MostrarMovimientos(ArrayList listado) {
        Iterator it = listado.listIterator();
        DefaultTableModel modelo = new DefaultTableModel();
        MovimientosClientes movi;
        modelo.addColumn("Numero");
        modelo.addColumn("Fecha");
        modelo.addColumn("Monto");
        modelo.addColumn("Saldo");
        modelo.addColumn("TipoComprobante");
        Object[] fila = new Object[5];
        while (it.hasNext()) {
            movi = (MovimientosClientes) it.next();
            fila[0] = movi.numeroFactura;
            fila[1] = movi.fecha;
            fila[2] = movi.total;
            if (movi.estado==1) {
                fila[3] = "0.00";
            } else {
                fila[3] = movi.total;
            }
            if (movi.tipo == 5) {
                fila[0] = movi.idPedido;
            }
            fila[4] = movi.descripcionTipo;
            modelo.addRow(fila);
        }

        return modelo;
    }

    @Override
    public DefaultTableModel MostrarListaDePrecios(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarPreciosCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarMovimientosPorFechas(Integer id, String desde, String hasta) {
        ArrayList listado = new ArrayList();
        String sql = "SELECT movimientosclientes.*,clientes.razon_soci,tipocomprobantes.descripcion,pedidos.saldo,pedidos.idfactura,facturas.numerofactura, facturas.tipo FROM movimientosclientes left join tipocomprobantes on tipocomprobantes.numeroafip=movimientosclientes.tipocomprobante left join pedidos on pedidos.id=movimientosclientes.idpedido left JOIN facturas on facturas.id=pedidos.idfactura join clientes on clientes.id=MOVIMIENTOSCLIENTES.NUMEROPROVEEDOR where movimientosclientes.fecha between '" + desde+" 00:00:00.000' and '"+hasta+" 00:00:00.000'";
        System.out.println(sql);
        
        try {
            Transaccionable tra = new Conecciones();
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
        MovimientosClientes factura;
            while (rs.next()) {
                factura = new MovimientosClientes();
                factura.setId(rs.getInt("id"));
                factura.setEstado(rs.getInt("pagado"));
                factura.setFecha(rs.getDate("fecha"));
                factura.setIdCliente(rs.getInt("numeroproveedor"));
                factura.setIdPedido(rs.getInt("idpedido"));
                factura.setIdUsuario(rs.getInt("idusuario"));
                factura.setNumeroFactura(rs.getInt("numerofactura"));
                factura.setNombreCliente(rs.getString("razon_soci"));
                int editado = rs.getInt("editado");
                if (factura.numeroFactura != null) {

                } else {

                    factura.setNumeroFactura(rs.getInt("numerocomprobante"));
                }
                if (factura.numeroFactura == 0) {
                    factura.setNumeroFactura(rs.getInt("numerocomprobante"));
                }
                if (editado == 1) {
                    factura.setNumeroFactura(rs.getInt("numerocomprobante"));
                }
                factura.setIdRemito(rs.getInt("idremito"));
                factura.setTipo(rs.getInt("tipocomprobante"));
                factura.setTotal(rs.getDouble("monto"));
                //factura.setMontoOriginal(rs.getDouble("total"));
                factura.setSaldo(rs.getDouble("saldo"));
                factura.setDescripcionTipo(rs.getString("descripcion"));
                //factura.setNumeroFiscal(rs.getString("numerofactura"));
                //factura.setSubTotal(rs.getDouble("subtotal"));
                //factura.setDescuento(rs.getDouble("descuento"));
                //factura.setPorcentajeDescuento(rs.getDouble("porcentajeD"));
                listado.add(factura);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Boolean AltaObjeto(Object objeto) {
        try {
            MovimientosClientes movi = (MovimientosClientes) objeto;
            Transaccionable tra = new Conecciones();
            String sql = "update movimientosclientes set editado=1,fecha='" + movi.getFecha() + "',tipocomprobante=" + movi.getTipo() + ", numerocomprobante='" + movi.getNumeroFiscal() + "' where id=" + movi.getId();
            tra.guardarRegistro(sql);
            
        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    @Override
    public Boolean ModificaionObjeto(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean EliminacionDeObjeto(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean MovimientoDeAjusteDeCantidades(Object objeto, Double cantidadMovimiento, String observaciones) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarPorSucursal(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean identificarPedidoAOtros(Integer idMovimiento, Integer idFactura, Integer numeroFactura, Integer tipoDeComprobante) {

        try {
            Transaccionable tra = new Conecciones();
            
            String sql = "update facturas set idpedido=" + idPedido + ",numerofactura=" + numeroFactura + " where id=" + idFactura;
            tra.guardarRegistro(sql);
            System.out.println(sql);
            sql = "update movimientosclientes set tipocomprobante=" + tipoDeComprobante + ",movimientosclientes.monto=(SELECT facturas.total from facturas where facturas.id=" + idFactura + ") where id=" + idMovimiento;
            tra.guardarRegistro(sql);
            System.out.println(sql);
            //update movimientosclientes set movimientosclientes.monto=(SELECT facturas.total from facturas where facturas.id=6) where movimientosclientes.idpedido=5

        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return true;
    }

    @Override
    public Integer nuevoMovimiento(Object ped) {
        MovimientosClientes pedido = (MovimientosClientes) ped;
        Integer id = 0;
        
        
        try {
            Transaccionable tra = new Conecciones();
        String sql = "insert into movimientosclientes (numeroProveedor,monto,pagado,numeroComprobante,idUsuario,idCaja,idSucursal,tipoComprobante,idpedido,editado) values (" + pedido.idCliente + ",round(" + pedido.total + ",4),0," + pedido.getNumeroFactura() + "," + Inicio.usuario.getNumeroId() + "," + Inicio.caja.getNumero() + "," + Inicio.sucursal.getNumero() + ",5," + pedido.getIdPedido() + ",1)";
        tra.guardarRegistro(sql);
        ResultSet rs = tra.leerConjuntoDeRegistros("select * from movimientosclientes order by id desc fetch first 1 rows only");
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
