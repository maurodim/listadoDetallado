/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cajas;

import Actualizaciones.BkDeConeccion;
import Conversores.Numeros;
import facturacion.clientes.Clientes;
import interfaceGraficasManantial.Inicio;
import Articulos.Pantallas.ListadoDeArticulos;
import interfaces.Transaccionable;
import interfacesPrograma.Cajeables;
import interfacesPrograma.Facturar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import Articulos.Articulos;
import Sucursales.Billetes;
import Sucursales.Sucursales;
import objetosR.Comprobantes;
import objetosR.ConeccionLocal;
import objetosR.Conecciones;
import objetosR.Operaciones;

/**
 *
 * @author mauro
 */
public class Cajas extends Sucursales implements Cajeables{
    private int numero;
    private int tipoMovimiento;
    private Double saldoInicial;
    private static Date fechaInicio;
    private int numeroDeComprobante;
    private int tipoDeComprobante;
    private Double montoMovimiento;
    private Comprobantes comprobante;
    private Double cambioEnCaja;
    private Double saldoFinal;
    private Double totalVentas;
    private Double totalGastos;
    private Double transferenciaACaja;
    private Double diferencia;
    private Boolean reandida;
    private Boolean estado;
    private Integer tipo;
    private static ArrayList listBilletes=new ArrayList();
    private static ArrayList listadoOperaciones=new ArrayList();
    private static ArrayList listOperaciones=new ArrayList();
    private static ArrayList listadoCajas=new ArrayList();
    private static ArrayList listadoGeneralDeCajas=new ArrayList();
    private String descripcionMovimiento;
    private static Integer numeroDeComprobanteBk=0;
    private static ArrayList listadoDeComprobantes=new ArrayList();
    private int idForma1;
    private int idForma2;
    private double monto1;
    private double monto2;
    private double totalOtrosPagos;
    private String descripcionForma1;
    private String descripcionForma2;
    private double totalEfectivo;

    public int getIdForma2() {
        return idForma2;
    }

    public void setIdForma2(int idForma2) {
        this.idForma2 = idForma2;
    }

    public double getMonto2() {
        return monto2;
    }

    public void setMonto2(double monto2) {
        this.monto2 = monto2;
    }

    
    public double getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(double totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }
    

    public String getDescripcionForma1() {
        return descripcionForma1;
    }

    public void setDescripcionForma1(String descripcionForma1) {
        this.descripcionForma1 = descripcionForma1;
    }

    public String getDescripcionForma2() {
        return descripcionForma2;
    }

    public void setDescripcionForma2(String descripcionForma2) {
        this.descripcionForma2 = descripcionForma2;
    }
    

    public double getTotalOtrosPagos() {
        return totalOtrosPagos;
    }

    public void setTotalOtrosPagos(double totalOtrosPagos) {
        this.totalOtrosPagos = totalOtrosPagos;
    }
    

    public int getIdForma1() {
        return idForma1;
    }

    public void setIdForma1(int idForma) {
        this.idForma1 = idForma;
    }

    public double getMonto1() {
        return monto1;
    }

    public void setMonto1(double monto1) {
        this.monto1 = monto1;
    }
    
    

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(Double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public Double getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(Double totalGastos) {
        this.totalGastos = totalGastos;
    }

    public Double getTransferenciaACaja() {
        return transferenciaACaja;
    }

    public void setTransferenciaACaja(Double transferenciaACaja) {
        this.transferenciaACaja = transferenciaACaja;
    }

    public Double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Double diferencia) {
        this.diferencia = diferencia;
    }

    public Boolean getReandida() {
        return reandida;
    }

    public void setReandida(Boolean reandida) {
        this.reandida = reandida;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
   
    

    public String getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    public void setDescripcionMovimiento(String descripcionMovimiento) {
        this.descripcionMovimiento = descripcionMovimiento;
    }
    

    public static ArrayList getListadoCajas() {
        return listadoCajas;
    }

    
    public ArrayList getListadoOperaciones() {
        return listadoOperaciones;
    }

    
    public ArrayList getListBilletes() {
        return listBilletes;
    }

    public void setListBilletes(ArrayList listBilletes) {
        this.listBilletes = listBilletes;
    }

    public Double getCambioEnCaja() {
        return cambioEnCaja;
    }

    public void setCambioEnCaja(Double cambioEnCaja) {
        this.cambioEnCaja = cambioEnCaja;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
    

    public Comprobantes getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobantes comprobante) {
        this.comprobante = comprobante;
    }

    public Cajas(int tipoMovimiento, int numeroDeComprobante, int tipoDeComprobante, Double montoMovimiento) {

        this.tipoMovimiento = tipoMovimiento;
        this.numeroDeComprobante = numeroDeComprobante;
        this.tipoDeComprobante = tipoDeComprobante;
        this.montoMovimiento = montoMovimiento;
        this.tipo=0;
       if(listBilletes.size()==0){
            Billetes.cargarLista();
            listBilletes=Billetes.getListadoBill();
        }
        if(listadoOperaciones.size()==0){
        Operaciones.cargarArrayCaja();
        listadoOperaciones=Operaciones.getListadoOp();
        }
        if(listOperaciones.size()==0){
        Operaciones.cargarArray();
        listOperaciones=Operaciones.getListOp();
        }
        LeerCajaAdministradora();
    }

    public Cajas() {

        //Billetes.cargarLista();
        if(listBilletes.size()==0){
            Billetes.cargarLista();
            listBilletes=Billetes.getListadoBill();
        }
        if(listadoOperaciones.size()==0){
        Operaciones.cargarArrayCaja();
        listadoOperaciones=Operaciones.getListadoOp();
        }
        if(listOperaciones.size()==0){
        Operaciones.cargarArray();
        listOperaciones=Operaciones.getListOp();
        }
        this.cambioEnCaja=0.00;
        this.numeroDeComprobante=0;
        this.tipoDeComprobante=0;
        this.tipoMovimiento=0;
        this.tipo=0;
        LeerCajaAdministradora();
    }

    public Cajas(int numero) {

        this.numero = numero;
       if(listBilletes.size()==0){
            Billetes.cargarLista();
            listBilletes=Billetes.getListadoBill();
        }
        if(listadoOperaciones.size()==0){
        Operaciones.cargarArrayCaja();
        listadoOperaciones=Operaciones.getListadoOp();
        }
        if(listOperaciones.size()==0){
        Operaciones.cargarArray();
        listOperaciones=Operaciones.getListOp();
        }
        this.tipo=0;
        LeerCajaAdministradora();
        
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public static Date getFechaInicio() {
        return fechaInicio;
    }

    public static void setFechaInicio(Date fechaInicio) {
        Cajas.fechaInicio = fechaInicio;
    }

    public int getNumeroDeComprobante() {
        return numeroDeComprobante;
    }

    public void setNumeroDeComprobante(int numeroDeComprobante) {
        this.numeroDeComprobante = numeroDeComprobante;
    }

    public int getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    public void setTipoDeComprobante(int tipoDeComprobante) {
        this.tipoDeComprobante = tipoDeComprobante;
    }

    public Double getMontoMovimiento() {
        return montoMovimiento;
    }

    public void setMontoMovimiento(Double montoMovimiento) {
        this.montoMovimiento = montoMovimiento;
    }
    private Integer NumeroDeComprobanteActivoMovCaja(){
        Integer numeroAct=0;
        String sql="select tipocomprobantes.numeroActivo from tipocomprobantes where id=6";
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        */
        
        try {
            tra=new Conecciones();
        //}
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
            while(rr.next()){
                numeroAct=rr.getInt("numeroActivo");
                
                
            }
            rr.close();
            numeroAct++;
            sql="update tipocomprobantes set numeroActivo=numeroActivo + 1 where id=6";
        if(tra.guardarRegistro(sql));//System.err.println(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return numeroAct;
    }
    
    public static void LeerCajaAdministradora(){
        String sql="select caja.numero from caja where tipo=1 and estado=0";
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        */
       
        try {
             tra=new Conecciones();
        //}
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
            Inicio.numeroCajaAdministradora=rs.getInt("numero");
                
            }
            //System.out.println("CAJA ADMINISTRADORAAAAAAAAAA "+Inicio.numeroCajaAdministradora);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NullPointerException ee){
            Inicio.coneccionRemota=false;
            LeerCajaAdministradora();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    private static void ListarCajas(){
                String sql="select * from caja";
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Cajas caja=new Cajas();
                caja.setNumero(rs.getInt("numero"));
                caja.setSucursal(new Sucursales(rs.getInt("numeroSucursal")));
                caja.setUsuario(new Usuarios(rs.getInt("numeroUsuario")));
                caja.setTipoMovimiento(rs.getInt("tipoMovimiento"));
                caja.setFechaInicio(rs.getDate("fecha"));
                caja.setSaldoInicial(rs.getDouble("saldoInicial"));
                caja.setTipo(rs.getInt("tipo"));
                Boolean esta=true;
                if(rs.getInt("estado")==0){
                    
                }else{
                    esta=false;
                }
                caja.setEstado(esta);
                listadoGeneralDeCajas.add(caja);
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static void ListarCajas1(){
        String sql="select * from caja order by numero desc limit 0,30";
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Cajas caja=new Cajas();
                caja.setNumero(rs.getInt("numero"));
                caja.setSucursal(new Sucursales(rs.getInt("numeroSucursal")));
                caja.setUsuario(new Usuarios(rs.getInt("numeroUsuario")));
                caja.setTipoMovimiento(rs.getInt("tipoMovimiento"));
                caja.setFechaInicio(rs.getDate("fecha"));
                caja.setSaldoInicial(rs.getDouble("saldoInicial"));
                caja.setTipo(rs.getInt("tipo"));
                Boolean esta=true;
                if(rs.getInt("estado")==0){
                    
                }else{
                    esta=false;
                }
                caja.setEstado(esta);
                listadoGeneralDeCajas.add(caja);
                
            }
            sql="select * from tipocomprobantes";
            rs=tra.leerConjuntoDeRegistros(sql);
            String aa;
            listadoDeComprobantes.clear();
            aa="";
            aa="insert into tipocomprobantes (numero,descripcion,numeroactivo,numerosucursal) values";
            while(rs.next()){
               //aa="";
               
               aa+="("+rs.getInt("numero")+",'"+rs.getString("descripcion")+"',"+rs.getInt("numeroActivo")+","+rs.getInt("numeroSucursal")+"),";
                //numeroDeComprobanteBk=rs.getInt("numeroActivo")
               
            }
            int tam=aa.length();
            tam=tam - 1;
            aa=aa.substring(0,tam);
               listadoDeComprobantes.add(aa);     
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NullPointerException ee){
            
        }
    }
    public static void BackapearCajas(){
        numeroDeComprobanteBk++;
        if(numeroDeComprobanteBk==1){
            // HAY QUE ANULAR EL LISTAR CAJAS CUANDO SE CORTA EL SERVIDOR
        ListarCajas1();
        if(listadoGeneralDeCajas.size() > 0){
        Iterator itL=listadoGeneralDeCajas.listIterator();
        Cajas caja=new Cajas();
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql="delete from caja";
        String ff="";
        tra.guardarRegistro(sql);
        while(itL.hasNext()){
            caja=(Cajas)itL.next();
            ff=Numeros.ConvertirFecha(caja.getFechaInicio());
            int esta=0;
                if(caja.getEstado()){
                    
                }else{
                    esta=1;
                }
            sql="insert into caja(numero,numerosucursal,numerousuario,tipomovimiento,saldoinicial,estado,tipo) values ("+caja.getNumero()+","+caja.getSucursal().getNumero()+","+caja.getUsuario().getNumeroId()+","+caja.getTipoMovimiento()+","+caja.getSaldoInicial()+","+esta+","+caja.getTipo()+")";
            //System.out.println("CAJA BACKAPEARCAJA --"+sql);
            tra.guardarRegistro(sql);
        }
        sql="delete from tipocomprobantes";
        tra.guardarRegistro(sql);
        Iterator ilLl=listadoDeComprobantes.listIterator();
        while(ilLl.hasNext()){
            sql=(String)ilLl.next();
            tra.guardarRegistro(sql);
            //System.out.println(sql);
        }
        }
        }
    }
    @Override
    public Object AbrirCaja(Object caja) {
        
            if(listadoCajas.size() > 0)listadoCajas.clear();
            //listadoCajas.clear();
            Cajas cajaNueva=(Cajas) caja;
            Integer tipo=0;
            if(Inicio.usuario.getNivelDeAutorizacion()==1){
                tipo=1;
            }
       try {
            Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
            int cajaNumeroAct=0;
            ResultSet rs;
            
            
            Double saldoI=cajaNueva.getSaldoInicial();
            String sql="insert into caja (numeroSucursal,numeroUsuario,tipoMovimiento,saldoInicial,tipo,estado) values ("+Inicio.sucursal.getNumero()+","+Inicio.usuario.getNumero()+",9,"+cajaNueva.saldoInicial+","+tipo+",0)";
            tra.guardarRegistro(sql);
            sql="select * from caja order by numero desc fetch first 1 rows only";
            rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                cajaNumeroAct=rs.getInt("numero");
            }
            //cajaNueva=new Cajas(cajaNumeroAct);
            /*
            sql="select * from caja order by id desc fetch first 1 rows only";
            rs=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                    cajaNueva.numero=rs.getInt(1);
                    System.out.println("ID CAJA "+cajaNueva.numero);
                }
            */
                
                int nume=NumeroDeComprobanteActivoMovCaja();
                sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,monto,tipoMovimiento,idCaja,numerocomprobante,tipocomprobante,pagado) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+cajaNueva.getSaldoInicial()+",9,"+cajaNumeroAct+","+nume+",0,1)";
                tra.guardarRegistro(sql);
                cajaNueva.setNumero(cajaNumeroAct);
                int pos=cajaNueva.getTipoMovimiento();
                /*
                if(listOperaciones.size()==0){
                            Operaciones.cargarArray();
                            listOperaciones=Operaciones.getListOp();
                            }
                 */ 
                
                Operaciones operacion=(Operaciones)listOperaciones.get(pos);
                String desc=operacion.getDescripcion();
                cajaNueva.setDescripcionMovimiento(desc);
            
            listadoCajas.add(cajaNueva);
            
            //sql="insert into caja (numero,numeroSucursal,numeroUsuario,tipoMovimiento,saldoInicial,tipo,estado) values ("+cajaNumeroAct+","+Inicio.sucursal.getNumero()+","+Inicio.usuario.getNumero()+",9,"+saldoI+","+tipo+",0)";
            //BkDeConeccion.guardarSentencias(sql);
            /*
            sql="select * from caja order by id desc fetch first 1 rows only";
            rs=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                    cajaNueva.numero=rs.getInt(1);
                    System.out.println("ID CAJA "+cajaNueva.numero);
                }
            */
                
                
                //sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,monto,tipoMovimiento,idCaja,numerocomprobante,tipocomprobante,pagado) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+cajaNueva.getSaldoInicial()+",9,"+cajaNueva.getNumero()+","+nume+",0,0)";
                //BkDeConeccion.guardarSentencias(sql);
            // return cajaNueva;
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cajaNueva;
    }

    @Override
    public Boolean CerrarCaja(Object caja) {
        Cajas cajj=(Cajas)caja;
        Boolean verif=false;
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        Double diferencia=0.00;
        //String sql="select * from saldofinalcaja where numero="+cajj.getNumero();
        String sql="select * from movimientoscaja where idcaja="+cajj.getNumero();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
                    Double dif2=0.00;

        try {
           
            Double totalVtas=0.00;
            Double totalGtos=0.00;
            Double inicial=0.00;
            while(rs.next()){
                switch (rs.getInt("tipomovimiento")){
                    case 9:
                        inicial=rs.getDouble("monto");
                        break;
                    case 7:
                        totalVtas=totalVtas + rs.getDouble("monto");
                        break;
                    case 13:
                        totalVtas=totalVtas + rs.getDouble("monto");
                        break;
                    case 1:
                       totalVtas=totalVtas + rs.getDouble("monto");
                        break;
                    default:
                        totalGtos=totalGtos + rs.getDouble("monto");
                        break;
                }
                
                
            }
            rs.close();
            diferencia=(inicial + totalVtas) + totalGtos;
            diferencia=diferencia - cajj.getSaldoFinal();
            dif2=diferencia - cajj.getMontoMovimiento();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String sql="insert into"cajj.getSaldoFinal()
        /*
        ACA TENGO QUE VER SI INDIVIDUALIZO EL EQUIPO, PUEDO PONER NUMEROEQUIPO * 1000000 Y ESE
        RESULTADO + EL IDCAJA - DE ESTA FORMA GENERO UN NUMERO UNICO DE CAJA PARA CADA EQUIPO Y SE REPLICA EN TODO EL 
        SISTEMA REMOTO - LOCALMENTE LO DEJO ASI PERO QUE SE BACKAPEE SOLAMENTE EL NUMERO DE CAJA ADMINISTRADORA
        
        */
        
        sql="update caja set diferencia="+dif2+",saldoFinal="+cajj.getCambioEnCaja()+",estado=1,fechaCierre='"+Inicio.fechaDia+"' where numero="+cajj.getNumero();
        
        verif=tra.guardarRegistro(sql);
        BkDeConeccion.guardarSentencias(sql);
        //tipoMovimiento 10
        sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+cajj.getNumeroDeComprobante()+","+cajj.getTipoDeComprobante()+","+cajj.getMontoMovimiento()+",10,"+cajj.getNumero()+",0,0,1)";
        verif=tra.guardarRegistro(sql);
        Double montt=0.00;
        if(Inicio.numeroCajaAdministradora.equals(cajj.getNumero())){
            montt=cajj.getMontoMovimiento();   
        }else{
            montt=cajj.getMontoMovimiento() * -1;
            sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+cajj.getNumeroDeComprobante()+","+cajj.getTipoDeComprobante()+","+montt+",10,"+Inicio.numeroCajaAdministradora+",0,0,1)";
            verif=tra.guardarRegistro(sql);
        }
        
        return verif;
    }

    @Override
    public Double SaldoDeCaja(ArrayList listadoBilletes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean NuevoMovimiento(Object factura) {
        Cajas caj=(Cajas)factura;
        Boolean ch=false;
        listadoCajas.add(caj);
        //System.err.println(Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+caj.getNumeroDeComprobante()+","+caj.getTipoDeComprobante()+","+caj.getMontoMovimiento()+","+caj.getTipoMovimiento()+","+caj.getNumero()+",0,");
        Integer num=caj.getNumeroDeComprobante();
        if(num==0){
            caj.setNumeroDeComprobante(NumeroDeComprobanteActivoMovCaja());
        }
        String sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+caj.getNumeroDeComprobante()+","+caj.getTipoDeComprobante()+","+caj.getMontoMovimiento()+","+caj.getTipoMovimiento()+","+caj.getNumero()+",0,0,1)";
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        ch=tra.guardarRegistro(sql);
        
        return ch;
    }

    @Override
    public Object ModificarMovimiento(Integer idMovimiento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean EliminarMovimiento(Integer idMovimiento,Integer idComprobante,Integer tipoMovimiento) {
        String sql="";
       Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
       Boolean verif=false;
       Cajas cajjaa=null;
       
       //JOptionPane.showMessageDialog(null," tipo de movimienot "+tipoMovimiento);
        switch (tipoMovimiento){
           case 1:
               //ventas -- leo en articulos para sacar el detalle, devuelvo un objeto comprobantes
               sql="delete movimientosarticulos where tipoComprobante="+idMovimiento+" and numeroComprobante="+idComprobante+" and numerousuario="+Inicio.usuario.getNumeroId()+" and tipoMovimiento=1";
               if(tra.guardarRegistro(sql))verif=true;
               //System.out.println(sql);
       
               break;
        
           case 4:
               //retiro de efectivo -- leo en movimientos de caja, devuelvo un objeto caja
               sql="delete movimientoscaja where tipoComprobante="+idMovimiento+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=4"; 
              if(tra.guardarRegistro(sql))verif=true;
               break;
           case 7:
               //Ingreso de caja -- leo en movimientos de caja, devuelvo un objeto caja
               sql="delete movimientoscaja where tipoComprobante="+idMovimiento+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=7"; 
               if(tra.guardarRegistro(sql))verif=true;
               break;
           case 9:
               //Saldo inicial -- leo en movimientos de caja, devuelvo un obejto caja
               sql="delete movimientoscaja where tipoComprobante="+idMovimiento+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=9"; 
               if(tra.guardarRegistro(sql))verif=true;
               break;
           case 10:
               //cierre caja -- leo en movimientos de caja, devuelvo un objeto caja
               sql="delete movimientoscaja  where idcaja="+Inicio.caja.getNumero()+" and movimientoscaja.tipoMovimiento=10"; 
               //System.out.println(sql);
               if(tra.guardarRegistro(sql))verif=true;
               break;
           case 11:
               //pago a proveedores -- leo en movimientos de caja, devuelvo un objeto caja
               sql="delete movimientoscaja where tipoComprobante="+idMovimiento+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=11"; 
               if(tra.guardarRegistro(sql))verif=true;
               break;
           case 12:
               //gastos de caja -- leo en movimientos de caja, devuelvo un objeto caja
               sql="delete movimientoscaja where tipoComprobante="+idMovimiento+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=12"; 
               if(tra.guardarRegistro(sql))verif=true;
               break;
           case 13:
               //cobro cta cte clientes -- leo en movimientos caja, devuelvo un obejto caja
               sql="delete movimientoscaja where tipoComprobante="+idMovimiento+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=4"; 
               if(tra.guardarRegistro(sql))verif=true;
               break;
               
           default:
               
               break;
       }
       
       return true;
    }

    @Override
    public Object ArquearCaja(Object caja) {
        if(listadoCajas.size() > 0)listadoCajas.clear();
        Cajas cajas=(Cajas)caja;
        Cajas cajass=null;
        Double saldoFinal=cajas.saldoInicial;
        String sql="select numeroComprobante,idcaja,tipomovimiento,pagado,monto,tipocomprobante,idforma1,monto1,idforma2,monto2,(select descripcion from formasdepago where formasdepago.id=movimientoscaja.idforma1) as descForma1,(select descripcion from formasdepago where formasdepago.id=movimientoscaja.idforma2) as descForma2,(select descripcion from tipocomprobantes where tipocomprobantes.numeroafip=movimientoscaja.tipocomprobante and tipocomprobantes.numeroafip > 0)as nombrecomprobante from movimientoscaja where idCaja="+cajas.numero;
        Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Double vtas=0.00;
        Double gtos=0.00;
        Double tarjetas=0.00;
        double mmonto=0.00;
        //int incluir=0;
        try {
            while(rs.next()){
                cajass=new Cajas();
                //incluir=0;
                
                cajass.setNumero(cajas.numero);
                cajass.setNumeroDeComprobante(rs.getInt("numeroComprobante"));
                cajass.setTipoMovimiento(rs.getInt("tipoMovimiento"));
                mmonto=rs.getDouble("monto");
                if(rs.getInt("pagado")== 1){
                    if(cajass.getTipoMovimiento()==1 || cajass.getTipoMovimiento()==7 || cajass.getTipoMovimiento()==13){
                        vtas=vtas + rs.getDouble("monto");
                        
                        
                    }
                }
                
                if(cajass.getTipoMovimiento()==12 || cajass.getTipoMovimiento()==4 || cajass.getTipoMovimiento()==11)gtos=gtos + rs.getDouble("monto");
                cajass.setMontoMovimiento(mmonto);
                cajass.setIdForma1(rs.getInt("idforma1"));
                        
                        cajass.setMonto1(rs.getDouble("monto1"));
                        cajass.setDescripcionForma1(rs.getString("descForma1"));
                        cajass.setIdForma2(rs.getInt("idforma2"));
                        
                        cajass.setMonto2(rs.getDouble("monto2"));
                        cajass.setDescripcionForma2(rs.getString("descForma2"));
                        cajass.setDescripcion(rs.getString("nombrecomprobante"));
                
                saldoFinal= saldoFinal + rs.getDouble("monto");
                cajass.setTipoDeComprobante(rs.getInt("tipoComprobante"));
                int pos=cajass.getTipoMovimiento() -1;
                Operaciones operacion=(Operaciones)listOperaciones.get(pos);
                 String desc=operacion.getDescripcion();
                cajass.setDescripcionMovimiento(desc);
                
                listadoCajas.add(cajass);
                
            }
            rs.close();
            cajas.saldoFinal=saldoFinal;
            cajas.totalVentas=vtas;
            cajas.totalGastos=gtos;
            cajas.totalOtrosPagos=tarjetas;
            cajas.totalEfectivo=vtas - tarjetas;
            
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NullPointerException ee){
            JOptionPane.showMessageDialog(null,"Fallo en la conexion, verifique si posee conexion a Internet");
        }
        
        return cajas;
    }

    @Override
    public Boolean VerificarCaja(int numeroDeUsuario, int numeroDeSucursal, String fecha) {
        Boolean verifi=false;
        String sql="select * from caja where numeroUsuario ="+numeroDeUsuario+" and numeroSucursal="+numeroDeSucursal+" and estado=0";
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        */ 
            
        try {
            tra=new Conecciones();
        //}
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
            verifi=true;
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        //if(Inicio.coneccionRemota)BackapearCajas();
        return verifi;
    }

    @Override
    public Object CargarCaja(int numeroDeUsuario, int numeroDeSucursal, String fecha) {
        Cajas cajas=new Cajas();
        String sql="select * from caja where numeroUsuario ="+numeroDeUsuario+" and numeroSucursal="+numeroDeSucursal+" and estado=0";
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        */ 
           
        try {
             tra=new Conecciones();
        //}
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                cajas.numero=rs.getInt("numero");
                cajas.saldoInicial=rs.getDouble("saldoInicial");
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cajas;
    }

    @Override
    public Object NuevoGasto(Object factura) {
       Cajas caj=(Cajas)factura;
        //Boolean ch=false;
       Integer num=caj.getNumeroDeComprobante();
        
            caj.setNumeroDeComprobante(NumeroDeComprobanteActivoMovCaja());
       
        listadoCajas.add(caj);
        
        Double monto=caj.getMontoMovimiento() * (-1);
        caj.setMontoMovimiento(monto);
        String sql="";
        
        
             Transaccionable tra;
        /*
             if(Inicio.coneccionRemota){
            tra=new Conecciones();
        
        */
         sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado,observaciones) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+caj.getNumeroDeComprobante()+","+caj.getTipoDeComprobante()+","+monto+","+caj.getTipoMovimiento()+","+caj.getNumero()+",0,2,0,'"+caj.getDescripcionMovimiento()+"')";
        try {
            /*
            }else{
            */
            tra=new Conecciones();
             tra.guardarRegistro(sql);
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
      //      sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+caj.getNumeroDeComprobante()+","+caj.getTipoDeComprobante()+","+monto+","+caj.getTipoMovimiento()+","+caj.getNumero()+",0,2,0)";
       //     sql="insert into fallas (st,estado) values ('"+sql+"',0)";
        //}
       
        //sql="update comprobantes "
        
        return caj;
    }

    @Override
    public DefaultListModel LeerComprobante(Integer idComprobante,Integer tipoComprobante,Integer tipoMovimiento) {
       ArrayList listado=new ArrayList();
       String sql="";
       Transaccionable tra=null;
        try {
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
       ResultSet rs;
       Cajas cajjaa=null;
       String resultado="";
       DefaultListModel modelo=new DefaultListModel();
       //JOptionPane.showMessageDialog(null," tipo de movimienot "+tipoMovimiento);
        switch (tipoMovimiento){
           case 1:
               //ventas -- leo en articulos para sacar el detalle, devuelvo un objeto comprobantes
               sql="select * from movimientosarticulos where tipoComprobante="+tipoComprobante+" and numeroComprobante="+idComprobante+" and tipoMovimiento=1";
               System.out.println(sql);
               rs=tra.leerConjuntoDeRegistros(sql);
               Comprobantes comprobante=new Comprobantes();
               Clientes cliente;
               Articulos articulo;
               ArrayList art=new ArrayList();
               Facturar fact=new Articulos();
               String renglon="";
               int reg=0;
               Double cant=0.00;
               Double total=0.00;
        try {
            while(rs.next()){
                articulo=new Articulos();
                cliente=new Clientes(String.valueOf(rs.getInt("numeroCliente")));
                comprobante.setCliente(cliente);
                //comprobante.setFechaEmision(rs.getDate("fecha"));
                reg++;
                if(rs.getInt("idarticulo") != 0){
                
                articulo=(Articulos) fact.cargarPorCodigoAsignado(rs.getInt("idArticulo"));
                }else{
                    articulo.setCodigoAsignado("0");
                    articulo.setDescripcionArticulo("Recargo");
                }
                cant=rs.getDouble("cantidad");
                cant=cant * -1;
                articulo.setPrecioUnitario(rs.getDouble("precioDeVenta"));
                articulo.setPrecioServicio(rs.getDouble("precioServicio"));
                
                //total=rs.getDouble("total");
                //articulo.setCantidad(cant);
                //articulo.setPrecioUnitario(rs.getDouble("precioDeVenta"));
                //articulo.setPrecioServicio(rs.getDouble("precioServicio"));
                if(reg==1){
                renglon="Cliente :"+cliente.getRazonSocial();
                modelo.addElement(renglon);
                }
                renglon="Articulo :"+articulo.getCodigoAsignado()+" descripcion:"+articulo.getDescripcionArticulo()+" cantidad:"+cant+" precio:"+articulo.getPrecioUnitario();
                modelo.addElement(renglon);
            }
            //renglon="Total Comprobante :"+total;
            //modelo.addElement(renglon);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
               break;
        
           case 4:
               //retiro de efectivo -- leo en movimientos de caja, devuelvo un objeto caja
               sql="select * from movimientoscaja where tipoComprobante="+tipoComprobante+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=4"; 
               rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               resultado="fecha :"+rs.getDate("fecha")+" Retiro de efectivo\n\r";
               Double monto=rs.getDouble("monto")* -1;
               resultado+="Monto :"+monto+" Observaciones :"+rs.getString("observaciones");
               modelo.addElement(resultado);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
               break;
           case 7:
               //Ingreso de caja -- leo en movimientos de caja, devuelvo un objeto caja
               sql="select * from movimientoscaja where tipoComprobante="+tipoComprobante+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=7"; 
               rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               resultado="fecha :"+rs.getDate("fecha")+" Ingreso de efectivo a caja\n\r";
               Double monto=rs.getDouble("monto");
               resultado+="Monto :"+monto;
               modelo.addElement(resultado);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
               break;
           case 9:
               //Saldo inicial -- leo en movimientos de caja, devuelvo un obejto caja
               sql="select * from movimientoscaja where tipoComprobante="+tipoComprobante+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=9"; 
               rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               resultado=" Saldo Inicial\n\r";
               Double monto=rs.getDouble("monto");
               resultado+="Monto :"+monto;
               modelo.addElement(resultado);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
               break;
           case 10:
               //cierre caja -- leo en movimientos de caja, devuelvo un objeto caja
               sql="select numerousuario,idcliente,numerosucursal,numerocomprobante,tipocomprobante,monto,tipomovimiento,idcaja,(select usuarios.nombre from usuarios where usuarios.NUMERO=movimientoscaja.numerousuario)as nombreU from movimientoscaja  where idcaja="+Inicio.caja.getNumero()+" and movimientoscaja.tipoMovimiento=10"; 
               //System.out.println(sql);
               rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               resultado=" Cierre de caja de :"+rs.getString("nombreU") +"\n\r";
               Double monto=rs.getDouble("monto");
               resultado+="Monto :"+monto;
               modelo.addElement(resultado);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
               break;
           case 11:
               //pago a proveedores -- leo en movimientos de caja, devuelvo un objeto caja
               sql="select fecha,idcliente,monto,observaciones,(select proveedores.nombre from proveedores where proveedores.id=movimientoscaja.idCliente)as nombreP from movimientoscaja where tipoComprobante="+tipoComprobante+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=11"; 
               rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               resultado="fecha :"+rs.getDate("fecha")+" Pago a proveedor: "+rs.getString("nombreP")+"\n\r";
               Double monto=rs.getDouble("monto")* -1;
               resultado+="Monto :"+monto+" Observaciones :"+rs.getString("observaciones");
               modelo.addElement(resultado);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
               break;
           case 12:
               //gastos de caja -- leo en movimientos de caja, devuelvo un objeto caja
               sql="select * from movimientoscaja where tipoComprobante="+tipoComprobante+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=12"; 
               rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               resultado=" Gasto de Caja\n\r";
               Double monto=rs.getDouble("monto")* -1;
               resultado+="Monto :"+monto+" Observaciones :"+rs.getString("observaciones");
               modelo.addElement(resultado);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
               break;
           case 13:
               //cobro cta cte clientes -- leo en movimientos caja, devuelvo un obejto caja
               sql="select fecha,idcliente,monto,(select clientes.RAZON_SOCI from clientes where clientes.id=movimientoscaja.idCliente)as nombreP from movimientoscaja where tipoComprobante="+tipoComprobante+" and numeroComprobante="+idComprobante+" and numeroUsuario="+Inicio.usuario.getNumeroId()+" and movimientoscaja.tipoMovimiento=13"; 
               System.out.println("cobranza "+sql);
               rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               resultado="fecha :"+rs.getDate("fecha")+" Cobranza Cliente: "+rs.getString("nombreP")+"\n\r";
               Double monto=rs.getDouble("monto");
               resultado+="Monto :"+monto;
               modelo.addElement(resultado);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
               break;
               
           default:
               
               break;
       }
       
       return modelo;
    }

    @Override
    public ArrayList ArquearHistoricos(String fecha,String hasta) {
        if(listadoCajas.size() > 0)listadoCajas.clear();
        
        //Cajas cajas=(Cajas)caja;
        Cajas cajass=null;
        //Double saldoFinal=cajas.saldoInicial;
        
        String sql="select numero,numeroComprobante,idcaja,tipomovimiento,pagado,monto,tipocomprobante,idforma1,monto1,idforma2,monto2,(select descripcion from formasdepago where formasdepago.id=movimientoscaja.idforma1) as descForma1,(select descripcion from formasdepago where formasdepago.id=movimientoscaja.idforma2) as descForma2,(select descripcion from tipocomprobantes where tipocomprobantes.numeroafip=movimientoscaja.tipocomprobante and tipocomprobantes.numeroafip > 0)as nombrecomprobante from movimientoscaja where fecha  between '"+fecha+" 00:00:00.000' and '"+hasta+" 00:00:00.000'";
        System.out.println(sql);
        Double mmonto=0.00;
        try {
            Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                cajass=new Cajas();
                
                
                cajass.setNumero(rs.getInt("numero"));
                cajass.setNumeroDeComprobante(rs.getInt("numeroComprobante"));
                cajass.setTipoMovimiento(rs.getInt("tipoMovimiento"));
                mmonto=rs.getDouble("monto");
                
                
                //if(cajass.getTipoMovimiento()==12 || cajass.getTipoMovimiento()==4 || cajass.getTipoMovimiento()==11)gtos=gtos + rs.getDouble("monto");
                cajass.setMontoMovimiento(mmonto);
                cajass.setIdForma1(rs.getInt("idforma1"));
                        
                        cajass.setMonto1(rs.getDouble("monto1"));
                        cajass.setDescripcionForma1(rs.getString("descForma1"));
                        cajass.setIdForma2(rs.getInt("idforma2"));
                        
                        cajass.setMonto2(rs.getDouble("monto2"));
                        cajass.setDescripcionForma2(rs.getString("descForma2"));
                        cajass.setDescripcion(rs.getString("nombrecomprobante"));
                
                //saldoFinal= saldoFinal + rs.getDouble("monto");
                cajass.setTipoDeComprobante(rs.getInt("tipoComprobante"));
                int pos=cajass.getTipoMovimiento() -1;
                Operaciones operacion=(Operaciones)listOperaciones.get(pos);
                 String desc=operacion.getDescripcion();
                cajass.setDescripcionMovimiento(desc);
                
                /*
                cajass.setNumero(rs.getInt("idCaja"));
                cajass.setNumeroDeComprobante(rs.getInt("numeroComprobante"));
                cajass.setTipoMovimiento(rs.getInt("tipoMovimiento"));
                cajass.setMontoMovimiento(rs.getDouble("monto"));
                //saldoFinal= saldoFinal + rs.getDouble("monto");
                cajass.setTipoDeComprobante(rs.getInt("tipoComprobante"));
                //cajass.setIdMovimiento(rs.getInt("id"));
                int pos=cajass.getTipoMovimiento() -1;
                Operaciones operacion=(Operaciones)listOperaciones.get(pos);
                 String desc=operacion.getDescripcion();
                cajass.setDescripcionMovimiento(desc);
                */
                listadoCajas.add(cajass);
                
            }
            rs.close();
            //cajas.saldoFinal=saldoFinal;
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NullPointerException ee){
            JOptionPane.showMessageDialog(null,"Fallo en la conexion, verifique si posee conexion a Internet");
        } catch (InstantiationException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listadoCajas;
    }
    
    
}
