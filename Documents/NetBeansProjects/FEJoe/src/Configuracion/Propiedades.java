/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import Conversores.Numeros;
import interfaces.Transaccionable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Propiedades {

    static String NOMBRECOMERCIO;
    static String CORREOCIERREDECAJA;
    static String CORREOCC;
    static String DIRECCION;
    static String TELEFONO;
    static String CUIT;
    static String INGBRUTOS;
    static String INICIOACT;
    static String PTO;
    static String CONDICION;
    static String RAZONSOCIAL;
    static String ARCHIVOKEY;
    static String ARCHIVOCRT;
    static String CONDICIONIVA;
    static String PUNTODEVENTA;
    static String TIPODEVENTA;

    public static String getNOMBRECOMERCIO() {
        return NOMBRECOMERCIO;
    }

    public static String getCORREOCIERREDECAJA() {
        return CORREOCIERREDECAJA;
    }

    public static String getCORREOCC() {
        return CORREOCC;
    }

    public static String getDIRECCION() {
        return DIRECCION;
    }

    public static String getTELEFONO() {
        return TELEFONO;
    }

    public static String getCUIT() {
        return CUIT;
    }

    public static String getINGBRUTOS() {
        return INGBRUTOS;
    }

    public static String getINICIOACT() {
        return INICIOACT;
    }

    public static String getPTO() {
        return PTO;
    }

    public static String getCONDICION() {
        return CONDICION;
    }

    public static String getRAZONSOCIAL() {
        return RAZONSOCIAL;
    }

    public static String getARCHIVOKEY() {
        return ARCHIVOKEY;
    }

    public static String getARCHIVOCRT() {
        return ARCHIVOCRT;
    }

    public static String getCONDICIONIVA() {
        return CONDICIONIVA;
    }

    public static String getPUNTODEVENTA() {
        return PUNTODEVENTA;
    }

    public static String getTIPODEVENTA() {
        return TIPODEVENTA;
    }

    public static void CargarPropiedades() throws ParseException {
        File archivo = new File("Configuracion\\bbsJoe.properties");
        Properties p = new Properties();
        if (archivo.exists()) {

//Process px=Runtime.getRuntime().exec("c:/xampp/xampp_start.exe");
            //sleep(2000);
            int verificado = 0;

            try {
                p.load(new FileReader(archivo));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
            }
            Enumeration<Object> keys = p.keys();

            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                System.out.println(key + " = " + p.get(key));
            }

            //FileReader fr = null;
            //fr = new FileReader (archivo);
            //BufferedReader br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            int renglon = 0;
            //Transaccionable tra=new Conecciones();
            //while((linea=br.readLine())!=null){

            NOMBRECOMERCIO = p.getProperty("NOMBRECOMERCIO");

            CORREOCIERREDECAJA = p.getProperty("MAIL");

            CORREOCC = p.getProperty("MAILCC");

            DIRECCION = p.getProperty("DIRECCION");

            TELEFONO = p.getProperty("TELEFONO");

            CUIT = p.getProperty("CUIT");
            INGBRUTOS = p.getProperty("INGBRUTOS");
            INICIOACT = p.getProperty("INICIOACT");
            PTO = p.getProperty("PTO");
            //CONDICION=p.getProperty("CONDICION");
            CONDICION = "2";

            RAZONSOCIAL = p.getProperty("RAZONSOCIAL");

            ARCHIVOKEY = p.getProperty("ARCHIVOKEY");
            ARCHIVOCRT = p.getProperty("ARCHIVOCRT");
            CONDICIONIVA = p.getProperty("CONDICIONIVA");
            PUNTODEVENTA = p.getProperty("PUNTODEVENTA");
            TIPODEVENTA = p.getProperty("TIPODEVENTA");

            //System.out.println(renglon+" // "+linea);
            // if(tra.guardarRegistro(linea));
            //JOptionPane.showMessageDialog(null,"NO SE HA PODIDO ESTABLECER CONEXION CON INTERNET, POR FAVOR VERIFIQUE DICHA CONEXION");
            //Date fecha = Numeros.ConvertirStringEnDate(VERIF);
            DecimalFormat fr1 = new DecimalFormat("00");
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = new GregorianCalendar();
            String dia = Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
            String mes = Integer.toString(c2.get(Calendar.MONTH));
            String ano = Integer.toString(c2.get(Calendar.YEAR));

            int da = Integer.parseInt(dia);
            int me = Integer.parseInt(mes);
            me++;
            dia = fr1.format(da);
            mes = fr1.format(me);
            String fechaDia = ano + "-" + mes + "-" + dia;
            //System.err.println(fechaDia);
            //fecha="23/12/2011";
            String fh = ano + "-" + mes + "-" + dia;
            SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaVal = null;

            fechaVal = Numeros.ConvertirStringEnDate(fh);
            //fechaVal = ff.parse(fh);

        }
    }
    //BD="siglox";

}
