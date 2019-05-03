/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fiscal;

import ConversoresCiti.Numeros;
import interfaces.Transaccionable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosDao.Conecciones;



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
        tra=new Conecciones();
        String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),LPAD(replace(replace(REPLACE(format(gravado,2),'.',''),'-',''),',',''),15,'0'),lpad(alicuota,4,'0'),LPAD(replace(replace(REPLACE(format(impuesto,2),'.',''),'-',''),',',''),15,'0'))as codigo from fiscal where fechaRegistro between '"+fechaDesde+"' and '"+fechaHasta+"' group by numero order by fecha";
        //String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),LPAD(REPLACE(format(gravado,2),'.',''),15,'0'),lpad(alicuota,4,'0'),LPAD(REPLACE(format(impuesto,2),'.',''),15,'0'))as codigo from fiscal where fecha like '201607%' group by numero order by fecha";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        FileWriter fichero=null;
        Boolean respuesta=false;
        try {
            String nombreFichero="fiscal/"+fechaDesde+"_"+fechaHasta+"_"+Numeros.ConvertirFechaFiscal()+"_alicuota.txt";
            fichero=new FileWriter(nombreFichero);
            PrintWriter pw=new PrintWriter(fichero);
            String sent;
            int reng=0;
            while(rs.next()){
                sent=rs.getString("codigo");
                reng++;
                System.out.println("renglon "+reng+" dato:"+sent);
                pw.println(sent);
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
        tra=new Conecciones();
        String sql="select Concat(lpad(fecha,8,'0'),lpad(CONVERT(tipo USING utf8),3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),lpad(numero,20,'0'),lpad(tipoClienteId,2,'0'),lpad(cuit,20,convert('0' using utf8)),lpad(razon,30,convert(' ' using utf8)),lpad(replace(replace(REPLACE(format(total,2),'.',''),'-',''),',',''),15,'0'),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('PES' USING utf8),CONVERT('0001000000' USING utf8),CONVERT('1' USING utf8),CONVERT('0' USING utf8)," +
"CONVERT('000000000000000' USING utf8),lpad(CONVERT('0' USING utf8),8,'0')) as dato from fiscal where fechaRegistro between '"+fechaDesde+"' and '"+fechaHasta+"' group by numero order by fecha";
//        String sql="select Concat(lpad(fecha,8,'0'),lpad(CONVERT(tipo USING utf8),3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),lpad(numero,20,'0'),lpad(tipoClienteId,2,'0'),lpad(cuit,20,convert('0' using utf8)),lpad(razon,30,convert(' ' using utf8)),lpad(REPLACE(format(total,2),'.',''),15,'0'),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('PES' USING utf8),CONVERT('0001000000' USING utf8),CONVERT('1' USING utf8),CONVERT('0' USING utf8)," +
//"CONVERT('000000000000000' USING utf8),lpad(CONVERT('0' USING utf8),8,'0')) as dato from fiscal where fecha like '201607%' group by numero order by fecha";
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        FileWriter fichero=null;
        Boolean respuesta=false;
        try {
            String nombreFichero="fiscal/"+fechaDesde+"_"+fechaHasta+"_"+Numeros.ConvertirFechaFiscal()+"_comprobantes.txt";
            fichero=new FileWriter(nombreFichero);
            
            PrintWriter pw=new PrintWriter(fichero);
            String sent;
            int reng=0;
            while(rs.next()){
                sent=rs.getString("dato");
                reng++;
                System.out.println("renglon "+reng+" dato:"+sent);
                System.out.println("campos "+sent.length());
                pw.println(sent);
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
        tra=new Conecciones();
        String sql="delete from fiscal where fechaRegistro between '"+fechaDesde+"' and '"+fechaHasta+"'";
        //String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),LPAD(REPLACE(format(gravado,2),'.',''),15,'0'),lpad(alicuota,4,'0'),LPAD(REPLACE(format(impuesto,2),'.',''),15,'0'))as codigo from fiscal where fecha like '201607%' group by numero order by fecha";
        tra.guardarRegistro(sql);
        
        return true;
    }
    
    public Boolean GenerarArchivoAlicuotaCompras(String fechaDesde,String fechaHasta){
        tra=new Conecciones();
        String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),lpad('80',2,'0'),lpad(cuit,20,convert('0' using utf8)),LPAD(replace(replace(REPLACE(format(gravado,2),'.',''),'-',''),',',''),15,'0'),lpad(alicuota,4,'0'),LPAD(replace(replace(REPLACE(format(iva,2),'.',''),'-',''),',',''),15,'0'))as codigo from comprasfiscal where fechaRegistro between '"+fechaDesde+"' and '"+fechaHasta+"' and cantidadalicuotaiva=1 group by numero order by fecha";
        //String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),LPAD(REPLACE(format(gravado,2),'.',''),15,'0'),lpad(alicuota,4,'0'),LPAD(REPLACE(format(impuesto,2),'.',''),15,'0'))as codigo from fiscal where fecha like '201607%' group by numero order by fecha";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        FileWriter fichero=null;
        Boolean respuesta=false;
        try {
            String nombreFichero="fiscal/"+fechaDesde+"_"+fechaHasta+"_"+Numeros.ConvertirFechaFiscal()+"_alicuotaCompras.txt";
            fichero=new FileWriter(nombreFichero);
            PrintWriter pw=new PrintWriter(fichero);
            String sent;
            int reng=0;
            while(rs.next()){
                sent=rs.getString("codigo");
                reng++;
                System.out.println("renglon "+reng+" dato:"+sent);
                pw.println(sent);
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
        tra=new Conecciones();
        String sql="select Concat(lpad(fecha,8,'0'),lpad(CONVERT(tipo USING utf8),3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),lpad(' ',16,' '),lpad('80',2,'0'),lpad(cuit,20,convert('0' using utf8)),lpad(razon,30,convert(' ' using utf8)),lpad(replace(replace(REPLACE(format(total,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(netonogravado,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(exentas,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(percepcioniva,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(impuestosnacionales,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(percepcionib,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(impmunicipales,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(impinternos,2),'.',''),'-',''),',',''),15,'0'),CONVERT('PES' USING utf8),CONVERT('0001000000' USING utf8),CONVERT(cantidadalicuotaiva USING utf8),CONVERT('0' USING utf8)," +
"lpad(replace(replace(REPLACE(format(iva,2),'.',''),'-',''),',',''),15,'0'),lpad(replace(replace(REPLACE(format(otrostributos,2),'.',''),'-',''),',',''),15,'0'),CONVERT('00000000000                              000000000000000' USING utf8)) as dato from comprasfiscal where fechaRegistro between '"+fechaDesde+"' and '"+fechaHasta+"' group by numero order by fecha";
//        String sql="select Concat(lpad(fecha,8,'0'),lpad(CONVERT(tipo USING utf8),3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),lpad(numero,20,'0'),lpad(tipoClienteId,2,'0'),lpad(cuit,20,convert('0' using utf8)),lpad(razon,30,convert(' ' using utf8)),lpad(REPLACE(format(total,2),'.',''),15,'0'),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('000000000000000' USING utf8),CONVERT('PES' USING utf8),CONVERT('0001000000' USING utf8),CONVERT('1' USING utf8),CONVERT('0' USING utf8)," +
//"CONVERT('000000000000000' USING utf8),lpad(CONVERT('0' USING utf8),8,'0')) as dato from fiscal where fecha like '201607%' group by numero order by fecha";
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        FileWriter fichero=null;
        Boolean respuesta=false;
        try {
            String nombreFichero="fiscal/"+fechaDesde+"_"+fechaHasta+"_"+Numeros.ConvertirFechaFiscal()+"_comprobantesCompras.txt";
            fichero=new FileWriter(nombreFichero);
            
            PrintWriter pw=new PrintWriter(fichero);
            String sent;
            int reng=0;
            while(rs.next()){
                sent=rs.getString("dato");
                reng++;
                System.out.println("renglon "+reng+" dato:"+sent);
                System.out.println("campos "+sent.length());
                pw.println(sent);
            }
        } catch (IOException ex) {
            Logger.getLogger(ArchivoCiti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
        tra=new Conecciones();
        String sql="delete from comprasfiscal where fechaRegistro between '"+fechaDesde+"' and '"+fechaHasta+"'";
        //String sql="select Concat(lpad(tipo,3,'0'),lpad(pto,5,'0'),lpad(numero,20,'0'),LPAD(REPLACE(format(gravado,2),'.',''),15,'0'),lpad(alicuota,4,'0'),LPAD(REPLACE(format(impuesto,2),'.',''),15,'0'))as codigo from fiscal where fecha like '201607%' group by numero order by fecha";
        tra.guardarRegistro(sql);
        
        return true;
    }
}