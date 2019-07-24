/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citi.Fiscal;

import ConversoresCiti.Numeros;
import interfaces.Transaccionable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosR.Conecciones;




/**
 *
 * @author mauro
 */
public class ArchivoCiti {
    private Integer id;
    private Transaccionable tra;
    private String archivo;
    private String Sentencia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getSentencia() {
        return Sentencia;
    }

    public void setSentencia(String Sentencia) {
        this.Sentencia = Sentencia;
    }
    
    public Boolean GenerarArchivoAlicuota(String fechaDesde,String fechaHasta){
        
        FileWriter fichero=null;
        Boolean respuesta=false;
        try {
            tra=new Conecciones();
        String sql="select * from fiscal where fechaRegistro between '"+fechaDesde+" 00:00:00.000' and '"+fechaHasta+" 00:00:00.000' order by fecha";
        //String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),LPAD(REPLACE(format(gravado,2),'.',''),15,'0'),lpad(alicuota,4,'0'),LPAD(REPLACE(format(impuesto,2),'.',''),15,'0'))as codigo from fiscal where fecha like '201607%' group by numero order by fecha";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            String nombreFichero="fiscal/"+fechaDesde+"_"+fechaHasta+"_"+Numeros.ConvertirFechaFiscal()+"_alicuota.txt";
            fichero=new FileWriter(nombreFichero);
            PrintWriter pw=new PrintWriter(fichero);
            String sent;
            int reng=0;
            String tipo;
            String pto;
            String numero;
            String gravado;
            String alicuota;
            String impuesto;
            
            while(rs.next()){
                tipo=rs.getString("tipo");
                pto=rs.getString("pto");
                numero=rs.getString("numero");
                gravado=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("gravado")).replace(",","");
                alicuota=rs.getString("alicuota");
                impuesto=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("impuesto")).replace(",","");
                tipo=String.format("%0"+(3- tipo.length())+"d%s",0,tipo);
                numero=String.format("%0"+(20 - numero.length())+"d%s",0,numero);
                gravado=String.format("%0"+(15 - gravado.length())+"d%s",0,gravado);
                impuesto=String.format("%0"+(15 - impuesto.length())+"d%s",0,impuesto);
                sent=tipo+pto+numero+gravado+alicuota+impuesto;
                reng++;
                System.out.println("renglon "+reng+" dato:"+sent.length()+ " gravado "+gravado+" total "+rs.getDouble("total")+" impuesto "+impuesto);
                pw.println(sent);
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                fichero.close();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;
    }
    public Boolean GenerarArchivoComprobantes(String fechaDesde,String fechaHasta){
        
        FileWriter fichero=null;
        Boolean respuesta=false;
        try {
            tra=new Conecciones();
        String sql;
        /*
        String sql="select Concat(lpad(fecha,8,'0'),lpad(CONVERT(tipo USING utf8),3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),lpad(numero,20,'0'),lpad(tipoClienteId,2,'0'),lpad(cuit,20,convert('0' using utf8)),lpad(razon,30,convert(' ' using utf8)),lpad(REPLACE(format(total,2),'.',''),15,'0'),"
                + "CONVERT('000000000000000' USING utf8),"
                + "CONVERT('000000000000000' USING utf8),"
                + "CONVERT('000000000000000' USING utf8),"
                + "CONVERT('000000000000000' USING utf8),"2
                + "CONVERT('000000000000000' USING utf8),"2
                + "CONVERT('000000000000000' USING utf8),"2
                + "CONVERT('000000000000000' USING utf8),"2
                + "CONVERT('PES' USING utf8),"
                + "CONVERT('0001000000' USING utf8),"
                + "CONVERT('1' USING utf8),"
                + "CONVERT('0' USING utf8)," +
"CONVERT('000000000000000' USING utf8),"
                + "lpad(CONVERT('0' USING utf8),8,'0')) as dato from fiscal where fechaRegistro between '"+fechaDesde+"' and '"+fechaHasta+"' group by numero order by fecha";
//        String sql="select Concat(lpad(fecha,8,'0'),lpad(CONVERT(tipo USING utf8),3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),lpad(numero,20,'0'),lpad(tipoClienteId,2,'0'),lpad(cuit,20,convert('0' using utf8)),lpad(razon,30,convert(' ' using utf8)),lpad(REPLACE(format(total,2),'.',''),15,'0'),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('PES' USING utf8),CONVERT('0001000000' USING utf8),CONVERT('1' USING utf8),CONVERT('0' USING utf8)," +
//"CONVERT('000000000000000' USING utf8),lpad(CONVERT('0' USING utf8),8,'0')) as dato from fiscal where fecha like '201607%' group by numero order by fecha";
*/        
sql="select * from fiscal where numero not like 'null' fechaRegistro between '"+fechaDesde+" 00:00:00.000' and '"+fechaHasta+" 00:00:00.000'";
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        //FileWriter fichero=null;
        //Boolean respuesta=false;
        
            String nombreFichero="fiscal/"+Numeros.ConvertirFechaFiscal()+"_comprobantes.txt";
            fichero=new FileWriter(nombreFichero);
            
            PrintWriter pw=new PrintWriter(fichero);
            String sent;
            String fecha;
            String tipo;
            String pto;
            String numero;
            String tipoClienteId;
            String cuit;
            String razon;
            String total;
            String espacio="000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000PES00010000001000000000000000000000000";
            while(rs.next()){
                fecha=rs.getString("fecha");
                
                tipo=rs.getString("tipo");
                pto=rs.getString("pto");
                numero=rs.getString("numero");
                tipoClienteId=String.valueOf(rs.getInt("tipoclienteid"));
                cuit=rs.getString("cuit");
                razon=rs.getString("razon");
                total=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("total"));
                //total=String.valueOf(rs.getDouble("total"));
                total=total.replace(",","");
                System.out.println("TOTAL antes "+total);
                //total=total.replace(",","");
                //fecha=String.format("%0" + (8 - fecha.length()) + "d%s", 0, fecha);
                tipo=String.format("%0" + (3 - tipo.length()) + "d%s", 0, tipo);
                //pto=String.format("%0"+(5 - pto.length())+"d%s",0,pto);
                numero=String.format("%0"+(20 - numero.length())+"d%s",0,numero);
                //tipoClienteId=String.format("%0"+(2 - tipoClienteId.length())+"d%s",0,tipoClienteId);
                cuit=String.format("%0"+(20 - cuit.length())+"d%s",0,cuit);
                razon=String.format("%0"+(30 - razon.length())+"d%s",0,razon);
                razon=razon.replace("0"," ");
                total=String.format("%0"+(15 - total.length())+"d%s",0,total);
                sent=fecha+tipo+pto+numero+numero+tipoClienteId+cuit+razon+total+espacio;
                
                
                //sent=rs.getString("dato");
                System.out.println("TOTAL "+total);
                System.out.println("cantidad items: "+sent.length()+"fecha "+fecha.length()+" tipo "+tipo.length()+"pto "+pto.length()+" numero "+numero.length()+" numero "+numero.length()+" tipoclienteid"+tipoClienteId.length()+" cuit "+cuit.length()+" total "+total.length()+" espacio "+espacio.length());
                pw.println(sent);
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                fichero.close();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;
    }
    
    public Boolean EliminarRegistrosVentas(String fechaDesde,String fechaHasta){
        try {
            tra=new Conecciones();
            String sql="delete from fiscal where fechaRegistro between '"+fechaDesde+" 00:00:00.000' and '"+fechaHasta+" 00:00:00.000'";
            //String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),LPAD(REPLACE(format(gravado,2),'.',''),15,'0'),lpad(alicuota,4,'0'),LPAD(REPLACE(format(impuesto,2),'.',''),15,'0'))as codigo from fiscal where fecha like '201607%' group by numero order by fecha";
            tra.guardarRegistro(sql);
            
            
        } catch (InstantiationException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public Boolean GenerarArchivoAlicuotaCompras(String fechaDesde,String fechaHasta){
        
        FileWriter fichero=null;
        Boolean respuesta=false;
        try {
            tra=new Conecciones();
        String sql="select comprasfiscal.tipo,comprasfiscal.pto,comprasfiscal.id,comprasfiscal.numero,comprasfiscal.cuit,comprasfiscalalicuota.gravado,comprasfiscalalicuota.alicuota,comprasfiscalalicuota.iva from comprasfiscal left join comprasfiscalalicuota on comprasfiscalalicuota.idcompras=comprasfiscal.id where comprasfiscal.fechaRegistro between '"+fechaDesde+" 00:00:00.000' and '"+fechaHasta+" 00:00:00.000' order by comprasfiscal.fecha";
        //String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),LPAD(REPLACE(format(gravado,2),'.',''),15,'0'),lpad(alicuota,4,'0'),LPAD(REPLACE(format(impuesto,2),'.',''),15,'0'))as codigo from fiscal where fecha like '201607%' group by numero order by fecha";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            String nombreFichero="fiscal/"+fechaDesde+"_"+fechaHasta+"_"+Numeros.ConvertirFechaFiscal()+"_alicuotaCompras.txt";
            fichero=new FileWriter(nombreFichero);
            PrintWriter pw=new PrintWriter(fichero);
            String sent;
            int reng=0;
            String tipo;
            String pto;
            String numero;
            String cuit;
            String gravado;
            String alicuota;
            String iva;
            
            while(rs.next()){
                tipo=rs.getString("tipo");
                pto=rs.getString("pto");
                numero=rs.getString("numero");
                cuit=rs.getString("cuit");
                cuit=String.format("%0"+(20 - cuit.length())+"d%s",0,cuit);
                gravado=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("gravado")).replace(",","");
                alicuota=rs.getString("alicuota");
                iva=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("iva")).replace(",","");
                gravado=String.format("%0"+(15 - gravado.length())+"d%s",0,gravado);
                iva=String.format("%0"+(15 - iva.length())+"d%s",0,iva);
                //if(alicuota)
                sent=tipo+pto+numero+"80"+cuit+gravado+alicuota+iva;
                reng++;
                System.out.println("renglon "+reng+" dato:"+sent);
                pw.println(sent);
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                fichero.close();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;
    }
    public Boolean GenerarArchivoComprobantesCompras(String fechaDesde,String fechaHasta){
        
        FileWriter fichero=null;
        Boolean respuesta=false;
        try {
            tra=new Conecciones();
        String sql="select * from comprasfiscal where fechaRegistro between '"+fechaDesde+" 00:00:00.000' and '"+fechaHasta+" 00:00:00.000' order by fecha";
/*"select Concat(lpad(fecha,8,'0'),lpad(CONVERT(tipo USING utf8),3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),lpad(' ',16,' '),lpad('80',2,'0'),lpad(cuit,20,convert('0' using utf8)),lpad(razon,30,convert(' ' using utf8)),lpad(replace(replace(REPLACE(format(total,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(netonogravado,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(exentas,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(percepcioniva,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(impuestosnacionales,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(percepcionib,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(impmunicipales,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(impinternos,2),'.',''),'-',''),',',''),15,'0'),CONVERT('PES' USING utf8),CONVERT('0001000000' USING utf8),CONVERT(cantidadalicuotaiva USING utf8),CONVERT('0' USING utf8)," +
"lpad(replace(replace(REPLACE(format(iva,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(otrostributos,2),'.',''),'-',''),',',''),15,'0'),CONVERT('00000000000                              000000000000000' USING utf8)) as dato from comprasfiscal where fechaRegistro between '"+fechaDesde+"' and '"+fechaHasta+"' group by numero order by fecha";
*/
//        String sql="select Concat(lpad(fecha,8,'0'),lpad(CONVERT(tipo USING utf8),3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),lpad(numero,20,'0'),lpad(tipoClienteId,2,'0'),lpad(cuit,20,convert('0' using utf8)),lpad(razon,30,convert(' ' using utf8)),lpad(REPLACE(format(total,2),'.',''),15,'0'),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('PES' USING utf8),CONVERT('0001000000' USING utf8),CONVERT('1' USING utf8),CONVERT('0' USING utf8)," +
//"CONVERT('000000000000000' USING utf8),lpad(CONVERT('0' USING utf8),8,'0')) as dato from fiscal where fecha like '201607%' group by numero order by fecha";
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            String nombreFichero="fiscal/"+fechaDesde+"_"+fechaHasta+"_"+Numeros.ConvertirFechaFiscal()+"_comprobantesCompras.txt";
            fichero=new FileWriter(nombreFichero);
            
            PrintWriter pw=new PrintWriter(fichero);
            String sent;
            int reng=0;
            String fecha;
            String tipo;
            String pto;
            String numero;
            String cuit;
            String razon;
            String total;
            String netoNoGravado;
            String exentas;
            String percepcionIva;
            String impuestosNacionales;
            String percepcionIb;
            String impuestosMunicipales;
            String impuestosInternos;
            String cantidadAlicuotaIva;
            String iva;
            String otrosTributos;
            String espacio;
            String cantidadAlicuota;
            
            while(rs.next()){
                fecha=rs.getString("fecha");
                tipo=rs.getString("tipo");
                pto=rs.getString("pto");
                numero=rs.getString("numero");
                cuit=rs.getString("cuit");
                cuit=String.format("%0"+(20-cuit.length())+"d%s",0,cuit);
                razon=rs.getString("razon");
                razon=String.format("%0"+(30 - razon.length())+"d%s",0,razon);
                razon=razon.replace("0"," ");
                total=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("total")).replace(",","");
                total=String.format("%0"+(15 - total.length())+"d%s",0,total);
                netoNoGravado=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("netonogravado")).replace(",","");
                netoNoGravado=String.format("%0"+(15 - netoNoGravado.length())+"d%s",0,netoNoGravado);
                exentas=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("exentas")).replace(",","");
                exentas=String.format("%0"+(15 - exentas.length())+"d%s",0,exentas);
                percepcionIva=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("percepcioniva")).replace(",","");
                percepcionIva=String.format("%0"+(15 - percepcionIva.length())+"d%s",0,percepcionIva);
                impuestosNacionales=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("impuestosnacionales")).replace(",","");
                impuestosNacionales=String.format("%0"+(15 - impuestosNacionales.length())+"d%s",0,impuestosNacionales);
                percepcionIb=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("percepcionib")).replace(",","");
                percepcionIb=String.format("%0"+(15 - percepcionIb.length())+"d%s",0,percepcionIb);
                impuestosMunicipales=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("impmunicipales")).replace(",","");
                impuestosMunicipales=String.format("%0"+(15 - impuestosMunicipales.length())+"d%s",0,impuestosMunicipales);
                impuestosInternos=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("impinternos")).replace(",", "");
                impuestosInternos=String.format("%0"+(15 - impuestosInternos.length())+"d%s",0,impuestosInternos);
                cantidadAlicuota=String.valueOf(rs.getInt("cantidadalicuotaiva"));
                
                iva=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("iva")).replace(",","");
                iva=String.format("%0"+(15 - iva.length())+"d%s",0,iva);
                otrosTributos=Numeros.ConvetirNumeroDosDigitos(rs.getDouble("otrostributos")).replace(",","");
                otrosTributos=String.format("%0"+(15 - otrosTributos.length())+"d%s",0,otrosTributos);
                espacio="00000000000                              000000000000000";
                
                
                sent=fecha+tipo+pto+numero+"                80"+cuit+razon+total+netoNoGravado+exentas+percepcionIva+impuestosNacionales+percepcionIb+impuestosMunicipales+impuestosInternos+"PES0001000000"+cantidadAlicuota+"0"+iva+otrosTributos+espacio;
                reng++;
                System.out.println("renglon "+reng+" dato:"+sent.length());
                System.out.println("campos "+sent.length());
                pw.println(sent);
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                fichero.close();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;
    }
    public Boolean EliminarRegistrosCompras(String fechaDesde,String fechaHasta){
        try {
            tra=new Conecciones();
            String sql="delete from comprasfiscal where fechaRegistro between '"+fechaDesde+"' and '"+fechaHasta+"'";
            //String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),LPAD(REPLACE(format(gravado,2),'.',''),15,'0'),lpad(alicuota,4,'0'),LPAD(REPLACE(format(impuesto,2),'.',''),15,'0'))as codigo from fiscal where fecha like '201607%' group by numero order by fecha";
            tra.guardarRegistro(sql);
            
            
        } catch (InstantiationException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
