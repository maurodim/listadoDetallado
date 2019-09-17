/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;



import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import com.lowagie.text.pdf.draw.LineSeparator;
import java.awt.Color;


import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mauro di
 */
public class PdfListado extends Thread{
    private ArrayList lstPedidos;
    private Connection cn;
    private Integer idListado;
    private Integer idRevision;
    private String vehiculo;
    private String kilos;
    private String fechaEntrega;
    private String rutaDestino;
    private String rutaDestinoRemoto;
    private String numeroPedido;
    private String nombreCliente;
    private String leyenda1;
    private String leyenda2;
    private String leyenda3;
    private String peso;
    private String saldoCliente;
    private String revision;
    private String fechaPedido;
    private String localidad;
    private String codigoArticulo;
    private String descripcion;
    private String cantidad;
    private String ordenDetrabajo;
    private String repetido;
    private String empresa;
    private String nombreVendedor;
    private String domicilioCliente;
    private String localidadCliente;
    private String telefonoCliente;
    private String codigoCliente;
    private Integer paginaActual;
    private Integer paginasTotal;
    private String kgEstructuraSuperior;
    private int cantidadDeVisitas;
    private String estructura;
    private int notificado;

    public void setNotificado(int notificado) {
        this.notificado = notificado;
    }

    
    public void setPaginasTotal(Integer paginasTotal) {
        this.paginasTotal = paginasTotal;
    }

    public void setKgEstructuraSuperior(String kgEstructuraSuperior) {
        this.kgEstructuraSuperior = kgEstructuraSuperior;
    }

    public void setCantidadDeVisitas(int cantidadDeVisitas) {
        this.cantidadDeVisitas = cantidadDeVisitas;
    }
    
    
    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
    

    public String getLocalidadCliente() {
        return localidadCliente;
    }

    public void setLocalidadCliente(String localidadCliente) {
        this.localidadCliente = localidadCliente;
    }
    

    public String getDomicilioCliente() {
        return domicilioCliente;
    }

    public void setDomicilioCliente(String domicilioCliente) {
        this.domicilioCliente = domicilioCliente;
    }
    

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    
    

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getLeyenda1() {
        return leyenda1;
    }

    public void setLeyenda1(String leyenda1) {
        this.leyenda1 = leyenda1;
    }

    public String getLeyenda2() {
        return leyenda2;
    }

    public void setLeyenda2(String leyenda2) {
        this.leyenda2 = leyenda2;
    }

    public String getLeyenda3() {
        return leyenda3;
    }

    public void setLeyenda3(String leyenda3) {
        this.leyenda3 = leyenda3;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getSaldoCliente() {
        return saldoCliente;
    }

    public void setSaldoCliente(String saldoCliente) {
        this.saldoCliente = saldoCliente;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getOrdenDetrabajo() {
        return ordenDetrabajo;
    }

    public void setOrdenDetrabajo(String ordenDetrabajo) {
        this.ordenDetrabajo = ordenDetrabajo;
    }

    public String getRepetido() {
        return repetido;
    }

    public void setRepetido(String repetido) {
        this.repetido = repetido;
    }
    
    

    public ArrayList getLstPedidos() {
        return lstPedidos;
    }

    public void setLstPedidos(ArrayList lstPedidos) {
        this.lstPedidos = lstPedidos;
    }

    public Integer getIdListado() {
        return idListado;
    }

    public void setIdListado(Integer idListado) {
        this.idListado = idListado;
    }

    public Integer getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(Integer idRevision) {
        this.idRevision = idRevision;
    }

    public String getRutaDestino() {
        return rutaDestino;
    }

    public void setRutaDestino(String rutaDestino) {
        this.rutaDestino = rutaDestino;
    }

    public String getRutaDestinoRemoto() {
        return rutaDestinoRemoto;
    }

    public void setRutaDestinoRemoto(String rutaDestinoRemoto) {
        this.rutaDestinoRemoto = rutaDestinoRemoto;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getKilos() {
        return kilos;
    }

    public void setKilos(String kilos) {
        this.kilos = kilos;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public PdfListado(Connection cn, Integer idListado, Integer idRevision, String vehiculo, String kilos, String fechaEntrega, String rutaDestino, String rutaDestinoRemoto,ArrayList lstPedidos) {
        this.cn = cn;
        this.idListado = idListado;
        this.idRevision = idRevision;
        this.vehiculo = vehiculo;
        this.kilos = kilos;
        this.fechaEntrega = fechaEntrega;
        this.rutaDestino = rutaDestino;
        this.rutaDestinoRemoto = rutaDestinoRemoto;
        this.lstPedidos=lstPedidos;
        this.paginaActual=1;
    }

    public PdfListado() {
        this.paginaActual=1;
    }

    

    
    
    

    
    
    @Override
    public void run(){
        Document documento=new Document();
        int i=1;
        //String clienteF=doc.getAfipPlastCbte().replace(":","_");
        String arch=this.getRutaDestino();
        
        
        File fich=new File(arch);
        /*
        while(fich.exists()){
            i++;
            int rev=this.idRevision++;
            arch="c:\\listadosHdr\\"+this.idListado+"-Rev "+rev+" - listado detallado de materiales.pdf";
            fich=new File(arch);
        }
        */
        FileOutputStream fichero;
        try {
            
            Date date = new Date();
            DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            fichero=new FileOutputStream(arch);
            PdfWriter writer=PdfWriter.getInstance(documento, fichero);
            documento.open();
            PdfContentByte cb=writer.getDirectContent();
            //FUNCIONES PARA LEER DATOS PARA COMPLETAR DEL LISTADO
            CompletarCampos();
            
            
            
            /*
            if(Propiedades.getLOGO() != ""){
                Image imagen= Image.getInstance(Propiedades.getLOGO());
                imagen.scaleAbsolute(190, 110);
                documento.add(imagen);
            }
            */
            String sql="update listadosdemateriales set pesoTotal="+this.kilos+" where numero="+this.idListado;
            System.out.println(sql);
            //Statement st2=this.getCn().createStatement();
            //st2.executeQuery(sql);
            String sql1=null;
            sql="select NRO_PEDIDO,saldoCliente,TALON_PEDI,COD_CLIENT,RAZON_SOC,LEYENDA_1,LEYENDA_2,LEYENDA_3,COD_VENDED,revision,(select pedidos_carga1.revision from pedidos_carga1 where listado="+this.getIdListado()+" order by revision desc limit 0,1)as maxRevision,vehiculo,round((select pesolistadoped.suma from pesolistadoped where pesolistadoped.NRO_PEDIDO = pedidos_carga1.NRO_PEDIDO and pesolistadoped.listado="+this.getIdListado()+" order by listado desc limit 0,1),2)as kilos,round((select sum(pesoPedidosListados.totalItem) from pesoPedidosListados where pesoPedidosListados.listado="+this.getIdListado()+" group by pesopedidoslistados.listado ),2)as totalKg,(select unidades.descripcion from unidades where unidades.numero=pedidos_carga1.vehiculo)as nombreVehiculo,left(pedidos_carga1.entrega,10)as entrega,left(pedidos_carga1.FEC_PEDIDO,10)as fechaPedido,(select zonas.descripcion from zonas where zonas.numero=pedidos_carga1.zona)as destino,(select clientesv.DOMICILIO from clientesv where clientesv.COD_CLIENT = pedidos_carga1.COD_CLIENT and clientesv.RAZON_SOCI like pedidos_carga1.RAZON_SOC limit 0,1)AS domicilio,(select clientesv.LOCALIDAD from clientesv where clientesv.COD_CLIENT = pedidos_carga1.COD_CLIENT limit 0,1)AS localidad,(SELECT vendedores.nombre from vendedores where vendedores.numero = pedidos_carga1.COD_VENDED)AS nombreVendedor from pedidos_carga1 where listado="+this.getIdListado()+" group by NRO_PEDIDO";
            //Statement st=this.getCn().createStatement();
            System.out.println(sql);
            Statement st1=this.getCn().createStatement();
            ResultSet rs1 = null;
            //ResultSet rs=st.executeQuery(sql);
            
            LineSeparator linea=new LineSeparator();
            Rectangle recta = null;
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,12);
            cb.beginText();
            cb.setTextMatrix(40,820);
            cb.showText("Listado de Preparación de Cargar Nº: "+this.getIdListado());
            //cb.showText("eR&Re");
            //cb.add(imagen);
            
            
            
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(40, 810);
            //String kk=String.valueOf(Math.round(this.getKilos() * 100.0) / 100.0);
            cb.showText("Total Kg.: "+this.getKilos());
            cb.setTextMatrix(160, 810);
            cb.showText("Estructura Superior (ES): "+this.kgEstructuraSuperior+" KG.");
            cb.setTextMatrix(370,810);
            cb.showText("Vehiculo: "+this.getVehiculo());
            cb.setTextMatrix(370,800);
            cb.showText("Fecha de entrega: "+this.getFechaEntrega());
            //cb.showText("PAPELES");
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(40,800);
            cb.showText("Fecha y Hora de Impresión: "+hourdateFormat.format(date));
            
            
             bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(40,790);
            cb.showText("Se corresponde con Rev: "+this.getIdRevision());
            //CANTIDAD DE VISITAS
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(180,790);
            cb.showText("CANT. VISITAS: "+String.valueOf(this.cantidadDeVisitas));
            
            //cb.showText("de Rivadeneira Enrique y Rivadeneira Jorge S.H.");
            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            //cb.setLineWidth(1f);
            //cb.roundRectangle(370,820,40,150, 10);
            linea.setLineWidth(2);
            linea.drawLine(cb,40,550,780);
            
            cb.setTextMatrix(230,768);
            cb.showText("DETALLADO");
            
            int renglon=0;
            linea.setLineWidth(1);
            linea.drawLine(cb,40,550,760);
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(40,750);
            cb.showText("|Emp./Nº Pedido");
            cb.setTextMatrix(130,750);
            cb.showText("|Vend.");
            cb.setTextMatrix(160,750);
            cb.showText("|Cod. - Cliente");
            cb.setTextMatrix(270,750);
            cb.showText("|Observaciones");
            cb.setTextMatrix(450, 750);
            cb.showText("| Peso Total");
            cb.setTextMatrix(550,750);
            cb.showText("|");
            linea.setLineWidth(1);
            linea.setLineColor(Color.GRAY);
            linea.drawLine(cb, 40, 550, 745);
            
            renglon=735;
            
            
            //ACA COMIENZA EL BUCLE DE ENCABEZADO DE PEDIDOS
            PdfListado pdf;
            Iterator it=this.getLstPedidos().listIterator();
            int contador=0;
            String domicilio="";
            while(it.hasNext()){
                pdf=(PdfListado) it.next();
            
                bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf,6);
                cb.setTextMatrix(40,renglon);
                cb.showText(pdf.getEmpresa()+"/"+pdf.getNumeroPedido());
                cb.setTextMatrix(130,renglon);
                cb.showText(pdf.getNombreVendedor());
                cb.setTextMatrix(160,renglon);
                
                cb.showText(pdf.getCodigoCliente()+" - "+pdf.getNombreCliente());
                bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf,6);
                
                bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf,8);
                cb.setTextMatrix(480,renglon);
                cb.showText("Peso: "+pdf.getKilos());

                renglon=renglon - 10;
                bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf,6);
                cb.setTextMatrix(40,renglon);
                if(pdf.getSaldoCliente()!= null){
                    cb.showText("Saldo Cta Cte: "+pdf.getSaldoCliente());
                }else{
                    cb.showText("Saldo Cta Cte: 0.00");
                }
                cb.setTextMatrix(130,renglon);
                domicilio=pdf.getDomicilioCliente()+" - "+pdf.getLocalidadCliente();
                if(domicilio.length() > 35)domicilio=domicilio.substring(0,35);
                
                cb.showText(domicilio);

                cb.setTextMatrix(270,renglon);
                cb.showText(pdf.getLeyenda1());
                bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf,10);
                cb.setTextMatrix(480,renglon);
                cb.showText("REPARTO");

                renglon=renglon -10;
                bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf,6);
                cb.setTextMatrix(40,renglon);
                cb.showText("Revision: "+pdf.getIdRevision());

                cb.setTextMatrix(130,renglon);
                cb.showText(pdf.getTelefonoCliente());
                cb.setTextMatrix(270,renglon);
                cb.showText(pdf.getLeyenda2());
                bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf,8);
                cb.setTextMatrix(480,renglon);
                //cb.setFontAndSize(bf,6);
                cb.showText(pdf.getLocalidad());
                renglon=renglon - 10;
                cb.setFontAndSize(bf,6);
                cb.setTextMatrix(40,renglon);
                if(pdf.getFechaPedido()!=null){
                cb.showText("Fecha Pedido: "+pdf.getFechaPedido().substring(0,10));
                }else{
                    cb.showText("Fecha Pedido: "+pdf.getFechaEntrega());
                }
                
                
                bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf,6);
                cb.setTextMatrix(270,renglon);
                cb.showText(pdf.getLeyenda3());
                renglon=renglon - 10;
                //INICIO DETALLE PEDIDO
                //ENCABEZADO
                bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf,6);
                cb.setTextMatrix(40, renglon);
                cb.showText("COD ARTICULO");
                cb.setTextMatrix(100, renglon);
                cb.showText("DESCRIPCION");
                cb.setTextMatrix(150,renglon);
                if(pdf.notificado==1){
                    bf = BaseFont.createFont(BaseFont.COURIER_BOLDOBLIQUE,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                    cb.setFontAndSize(bf,7);
                    String aviardo="    >>> PEDIDO YA ANTICIPADO A EDP-PISV <<<";
                    
                    cb.showText(aviardo);
                    bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                    cb.setFontAndSize(bf,6);
                }
                cb.setTextMatrix(350,renglon);
                cb.showText("CANTIDAD");
                //cb.setTextMatrix(350, renglon);
                //cb.showText("Nº DE ORDEN");
                cb.setTextMatrix(410, renglon);
                cb.showText("PESO");
                cb.setTextMatrix(480,renglon -5);
                cb.showText("MATERIAL");
                cb.setTextMatrix(480,renglon);
                cb.showText("REPETIDO");
                cb.setTextMatrix(520,renglon);
                cb.showText("ES");
                renglon=renglon - 10;
                
                

                //sql1="select * ,round(CANT_PEDID,2)as CANT_PEDID1,ROUND(CANT_FACT,2)AS CANT_FACT1,ROUND(CANT_DESC,2)AS CANT_DESC1,((select round(pesos.peso,2) from pesos where pesos.codigo = pedidos_carga1.COD_ARTIC limit 0,1) * CANT_PEDID) as pesoIndividual,(if(pedidos_carga1.COD_ARTIC ='30030011','',if(pedidos_carga1.COD_ARTIC='30030010','',(select sum(pedidoscantidadtotales.total) from pedidoscantidadtotales where pedidoscantidadtotales.NRO_PEDIDO like pedidos_carga1.NRO_PEDIDO and pedidoscantidadtotales.COD_ARTIC like pedidos_carga1.COD_ARTIC group by COD_ARTIC limit 0,1)-pedidos_carga1.CANT_PEDID)))as cantidadAntigua,if(pedidos_carga1.repetidoEnListado = 1,'X',' ')as repetidos,if(pedidos_carga1.es = 1,'X',' ')as estructura,(select datos.UMV from datos where datos.COD_ARTICULO = pedidos_carga1.COD_ARTIC limit 0,1)AS medida from pedidos_carga1 where NRO_PEDIDO = '"+pdf.getNumeroPedido()+"' and listado ="+this.getIdListado()+" group by ID_GVA03";
                

                sql1="select * ,round(CANT_PEDID,2)as CANT_PEDID1,ROUND(CANT_FACT,2)AS CANT_FACT1,ROUND(CANT_DESC,2)AS CANT_DESC1,((select round(pesos.peso,2) from pesos where pesos.codigo = pedidos_carga1.COD_ARTIC limit 0,1) * CANT_PEDID) as pesoIndividual,(if(pedidos_carga1.COD_ARTIC ='30030011','',if(pedidos_carga1.COD_ARTIC='30030010','',(select sum(pedidoscantidadtotales.total) from pedidoscantidadtotales where pedidoscantidadtotales.NRO_PEDIDO like pedidos_carga1.NRO_PEDIDO and pedidoscantidadtotales.COD_ARTIC like pedidos_carga1.COD_ARTIC group by COD_ARTIC limit 0,1)-pedidos_carga1.CANT_PEDID)))as cantidadAntigua,if(pedidos_carga1.repetidoEnListado = 1,'X',' ')as repetidos,(select datos.UMV from datos where datos.COD_ARTICULO = pedidos_carga1.COD_ARTIC limit 0,1)AS medida from pedidos_carga1 where NRO_PEDIDO = '"+pdf.getNumeroPedido()+"' and listado ="+this.getIdListado()+" group by ID_GVA03";
//corrige la duplicacion de items, con un group by en la segunda sentencia ( la que lee el detalle del pedido)
                System.out.println(sql1);
                
                rs1=st1.executeQuery(sql1);
                
                String canti;
                while(rs1.next()){
                    
                    canti="";
                    bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                    cb.setFontAndSize(bf,6);
                    cb.setTextMatrix(40, renglon);
                    cb.showText(rs1.getString("COD_ARTIC"));
                    cb.setFontAndSize(bf,7);
                    cb.setTextMatrix(100, renglon);
                    cb.showText(rs1.getString("DESC_ARTIC"));
                    cb.setTextMatrix(250,renglon);
                    cb.showText(rs1.getString("DESC_ADIC"));
                    cb.setTextMatrix(350,renglon);
                    canti=rs1.getString("CANT_PEDID1");
                    System.out.println("cantidad "+canti);
                    if(canti.equals("null")|| canti.equals("NULL")){
                        canti="0.00 "+rs1.getString("medida");
                    }else{
                        canti=canti+" "+rs1.getString("medida");
                    }
                    cb.showText(canti);
                    //cb.setTextMatrix(350, renglon);
                    //cb.showText(rs1.getInt("orden_num")+" ");
                    cb.setFontAndSize(bf,6);
                    cb.setTextMatrix(410, renglon);
                    cb.showText(redondeo(rs1.getDouble("pesoIndividual"))+ " Kg.");
                    cb.setTextMatrix(480,renglon);
                    
                    cb.showText(rs1.getString("repetidos"));
                    cb.setTextMatrix(520,renglon);
                    cb.showText(rs1.getString("estructura"));
                    renglon=renglon - 10;
                    if(renglon < 95){
                        //CONTADOR DE PAGINAS, SE RECIBE EL PARÁMETRO DE LAS PAGINAS TOTALES
                            cb.setFontAndSize(bf, 6);
                            cb.setTextMatrix(480,renglon);
                            cb.showText("Página "+this.paginaActual+" de "+this.paginasTotal);
                            this.paginaActual++;
                            
                                        cb.endText();
                                    documento.newPage();

                                    cb.beginText();

                            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,12);
                            //cb.beginText();
                            cb.setTextMatrix(40,820);
                            cb.showText("Listado de Preparación de Cargar Nº: "+this.getIdListado());
                            //cb.showText("eR&Re");
                            //cb.add(imagen);
                            
                            
                            cb.setFontAndSize(bf,10);
                            cb.setTextMatrix(40, 810);
                            cb.showText("Total Kg.: "+this.getKilos());
                            cb.setTextMatrix(160, 810);
                            cb.showText("Estructura Superior (ES): "+this.kgEstructuraSuperior+" KG.");
                            cb.setTextMatrix(370,810);
                            cb.showText("Vehiculo: "+this.getVehiculo());
                            cb.setTextMatrix(370,800);
                            cb.showText("Fecha de entrega: "+this.getFechaEntrega());
                            //cb.showText("PAPELES");
                            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,8);
                            cb.setTextMatrix(40,800);
                            cb.showText("Fecha y Hora de Impresión: "+hourdateFormat.format(date));
                             bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,8);
                            cb.setTextMatrix(40,790);
                            cb.showText("Se corresponde con Rev: "+this.getIdRevision());
                            //CANTIDAD DE VISITAS
                            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,8);
                            cb.setTextMatrix(180,790);
                            cb.showText("CANT. VISITAS: "+String.valueOf(this.cantidadDeVisitas));

                            //cb.showText("de Rivadeneira Enrique y Rivadeneira Jorge S.H.");
                            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,10);
                            //cb.setLineWidth(1f);
                            //cb.roundRectangle(370,820,40,150, 10);
                            linea.setLineWidth(2);
                            linea.drawLine(cb,40,550,780);

                            cb.setTextMatrix(230,768);
                            cb.showText("DETALLADO");
                            //int renglon=0;
                            linea.setLineWidth(1);
                            linea.drawLine(cb,40,550,760);
                            cb.setFontAndSize(bf,8);
                            cb.setTextMatrix(40,750);
                            cb.showText("|Emp./Nº Pedido");
                            cb.setTextMatrix(130,750);
                            cb.showText("|Vend.");
                            cb.setTextMatrix(160,750);
                            cb.showText("|Cod. - Cliente");
                            cb.setTextMatrix(270,750);
                            cb.showText("|Observaciones");
                            cb.setTextMatrix(450, 750);
                            cb.showText("| Peso Total");
                            cb.setTextMatrix(550,750);
                            cb.showText("|");
                            linea.setLineWidth(1);
                            linea.setLineColor(Color.GRAY);
                            linea.drawLine(cb, 40, 550, 745);

                            renglon=735;
                        
                        
                    }
                    
                }
                rs1.close();
                linea.setLineWidth(1);
                linea.setLineColor(Color.GRAY);
                linea.drawLine(cb, 40, 550, renglon);
                renglon=renglon - 10;
                if(renglon < 95){
                    //CONTADOR DE PAGINAS, SE RECIBE EL PARÁMETRO DE LAS PAGINAS TOTALES
                            cb.setFontAndSize(bf, 6);
                            renglon=renglon - 30;
                            cb.setTextMatrix(480,renglon);
                            cb.showText("Página "+this.paginaActual+" de "+this.paginasTotal);
                            this.paginaActual++;
                                        cb.endText();
                                    documento.newPage();

                                    cb.beginText();

                            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,12);
                            //cb.beginText();
                            cb.setTextMatrix(40,820);
                            cb.showText("Listado de Preparación de Cargar Nº: "+this.getIdListado());
                            //cb.showText("eR&Re");
                            //cb.add(imagen);
                            cb.setFontAndSize(bf,10);
                            cb.setTextMatrix(40, 810);
                            cb.showText("Total Kg.: "+this.getKilos());
                            cb.setTextMatrix(160, 810);
                            cb.showText("Estructura Superior (ES): "+this.kgEstructuraSuperior+" KG.");
                            cb.setTextMatrix(370,810);
                            cb.showText("Vehiculo: "+this.getVehiculo());
                            cb.setTextMatrix(370,800);
                            cb.showText("Fecha de entrega: "+this.getFechaEntrega());
                            //cb.showText("PAPELES");
                            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,8);
                            cb.setTextMatrix(40,800);
                            cb.showText("Fecha y Hora de Impresión: "+hourdateFormat.format(date));
                             bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,8);
                            cb.setTextMatrix(40,790);
                            cb.showText("Se corresponde con Rev: "+this.getIdRevision());
                            //CANTIDAD DE VISITAS
                            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,8);
                            cb.setTextMatrix(180,790);
                            cb.showText("CANT. VISITAS: "+String.valueOf(this.cantidadDeVisitas));


                            //cb.showText("de Rivadeneira Enrique y Rivadeneira Jorge S.H.");
                            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                            cb.setFontAndSize(bf,10);
                            //cb.setLineWidth(1f);
                            //cb.roundRectangle(370,820,40,150, 10);
                            linea.setLineWidth(2);
                            linea.drawLine(cb,40,550,780);

                            cb.setTextMatrix(230,768);
                            cb.showText("DETALLADO");
                            //int renglon=0;
                            linea.setLineWidth(1);
                            linea.drawLine(cb,40,550,760);
                            cb.setTextMatrix(40,750);
                            cb.showText("|Emp./Nº Pedido");
                            cb.setTextMatrix(130,750);
                            cb.showText("|Vend.");
                            cb.setTextMatrix(160,750);
                            cb.showText("|Cod. - Cliente");
                            cb.setTextMatrix(270,750);
                            cb.showText("|Observaciones");
                            cb.setTextMatrix(450, 750);
                            cb.showText("| Peso Total");
                            cb.setTextMatrix(550,750);
                            cb.showText("|");
                            linea.setLineWidth(1);
                            linea.setLineColor(Color.GRAY);
                            linea.drawLine(cb, 40, 550, 745);

                            renglon=735;
                        
                        
                    }

            }
            //rs1.close();
            //FIN PEDIDO
            
            
            
            //pie de documento
            //renglon=50;
            renglon=renglon - 20;
            cb.setTextMatrix(40,renglon);
            cb.showText("HORA:____________________________");
            cb.setTextMatrix(370,renglon);
            cb.showText("RECIBIO:_________________________");
            renglon=renglon - 10;
            cb.setTextMatrix(40,renglon);
            cb.showText("HORA:____________________________");
            cb.setTextMatrix(370,renglon);
            cb.showText("RECIBIO:_________________________");
            
            
            renglon=renglon - 30;
            //CONTADOR DE PAGINAS, SE RECIBE EL PARÁMETRO DE LAS PAGINAS TOTALES
                            cb.setFontAndSize(bf, 6);
                            cb.setTextMatrix(480,renglon);
                            cb.showText("Página "+this.paginaActual+" de "+this.paginasTotal);
                            
            
            cb.endText();
            documento.close();
            
            File f=new File(arch);
            if(f.exists()){
            
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+arch);
                System.out.println(arch);
                InputStream in=new FileInputStream(arch);
                if(this.getRutaDestinoRemoto() !=null){
                    File remoto=new File(this.getRutaDestinoRemoto());
                    OutputStream out=new FileOutputStream(remoto);
                     byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                      out.write(buf, 0, len);
                    }
                    out.close();
                }
                
                
                
               
                in.close();
                
            }
            int confirmacion=0;
            
            
            /*
            if(doc.getArchivo().isEmpty()){
                
            }else{
                confirmacion=JOptionPane.showConfirmDialog(null, "DESEA NOTIFICAR POR MAIL?");
            if(confirmacion==0){
                //JOptionPane.showMessageDialog(null,"acepto");
                
            }
            }
                    */
            System.out.println("eligio "+confirmacion);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PdfListado.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            
        } catch (DocumentException ex) {
            Logger.getLogger(PdfListado.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        } catch (IOException ex) {
            Logger.getLogger(PdfListado.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        } catch (SQLException ex) {
            Logger.getLogger(PdfListado.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }
        
        
    }
    private String redondeo(Double num){
        //String redondeado = null;
        DecimalFormat formato=new DecimalFormat("####.##");
        String doble=formato.format(num);
        return doble;
        
        //return redondeado;
    }
    private void CompletarCampos(){
        int visitasTotales=0;
        String sql="select count(*) as visitas from pedidos_carga1 where listado="+this.idListado+" group by cod_client";
        
        
        //String kk = null;
        //String fechaEntrega = null;
        //String destino2="prueba1.pdf";
        //destino="pruebaRemota.pdf";
        //String kg=0.00;
        
        
            Statement st;
        try {
            st = this.cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                visitasTotales++;
            }
            this.cantidadDeVisitas=visitasTotales;
            int paginaT=this.lstPedidos.size() * 6;
             System.err.println(paginaT+" renglones hasta ahora");
            sql="select count(*) as cantt from pedidos_carga1 where listado="+this.idListado;
            rs=st.executeQuery(sql);
            while(rs.next()){
                paginaT=paginaT + rs.getInt("cantt");
            }
            System.err.println(paginaT+" renglones hasta ahora");
            //PdfListado pdf=new PdfListado(cc1,numeroListado,numeroRevision,vehiculo,kk,fechaEntrega,destino2,destino,definitivo);
            Double calcu=paginaT / 64.00;
            int tot=(int) Math.ceil(calcu);
            System.err.println(tot+" renglones resultado");
            this.paginasTotal=tot;
            sql="select COD_ARTIC,NRO_PEDIDO,CANT_PEDID,((select round(pesos.peso,2) from pesos where pesos.codigo = pedidos_carga1.COD_ARTIC limit 0,1) * CANT_PEDID) as pesoIndividual from pedidos_carga1 where listado="+this.idListado+" and es=1 GROUP BY ID_GVA03";
            rs=st.executeQuery(sql);
            Double estr=0.00;
            while(rs.next()){
                estr=estr + rs.getDouble("pesoIndividual");
            }
            this.kgEstructuraSuperior=String.valueOf(Math.round(estr * 100.0) / 100.0);
            sql="select COD_ARTIC,NRO_PEDIDO,CANT_PEDID,((select round(pesos.peso,2) from pesos where pesos.codigo = pedidos_carga1.COD_ARTIC limit 0,1) * CANT_PEDID) as pesoIndividual from pedidos_carga1 where listado="+this.idListado+" GROUP BY ID_GVA03";
            System.out.println(sql);
            rs=st.executeQuery(sql);
           estr=0.00;
            while(rs.next()){
                estr=estr + rs.getDouble("pesoIndividual");
            }
            this.kilos=String.valueOf(Math.round(estr * 100.0) / 100.0);
            
        } catch (SQLException ex) {
            Logger.getLogger(PdfListado.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }
            
            
            
    }
    
}
