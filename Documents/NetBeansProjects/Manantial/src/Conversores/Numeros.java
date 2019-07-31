/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conversores;

import interfaceGraficasManantial.Inicio;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Numeros {
    private static String doble;
    private static String flotante;
    private static String fecha;
    
    public static String ConvertirNumero(Double num){
        DecimalFormat formato=new DecimalFormat("####.00");
        doble=formato.format(num);
        return doble;
    }
    public static String ConvertirNumeroAfip(Double num){
        if(num != null){
        DecimalFormat formato=new DecimalFormat("####.##");
        doble=formato.format(num);
        doble=doble.replace(",",".");
        }else{
            doble="0.00";
        }
        return doble;
    }
    public static String ConvertirFecha(Date ff){
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        fecha=ano+"-"+mes+"-"+dia;
        
        return fecha;
    }
    public static Calendar ConvertirStringEnCalendar(Date ff){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat fh=new SimpleDateFormat("yyyy-mm-dd");
        cal.setTime(ff);
        return cal;
    }
    public static Date ConvertirStringEnDate(String ff){
        //ff=ff.substring(0,10);
        System.out.println(ff);
        SimpleDateFormat fh=new SimpleDateFormat("yyyy-MM-dd");
        Date fechaVal = null;    
       
        
        try {
            //fechaVal = fh.parse(ff.substring(0, 10));
            fechaVal=(Date) fh.parse(ff);
        } catch (ParseException ex) {
            Logger.getLogger(Numeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return fechaVal;
    }
    public static Double ConvertirStringADouble(String num){
        num=num.replace(",",".");
        //System.out.println(" rsultado "+num);
        Double dd;
        try{
            dd=Double.parseDouble(num);
        }catch(NumberFormatException ex){
            dd=0.00;
        }
        return dd;
    }
    public static String ConvertirFechaLeidaDeDateChooser(Calendar dateC){
        DecimalFormat fr=new DecimalFormat("00");
        DecimalFormat formato=new DecimalFormat("####.####");
        //SiderconCapaatos.listaPedidos.clear();
        SimpleDateFormat dia=new SimpleDateFormat("dd/mm/yyyy");
        //Date mes=Calendar.getInstance().getTime();
        //dateChooserCombo1.setDateFormat(dia);
        Calendar fechaNueva=dateC;
        int ano=fechaNueva.get(Calendar.YEAR);
        int mes=fechaNueva.get(Calendar.MONTH);
        mes++;
        int dd=fechaNueva.get(Calendar.DAY_OF_MONTH);
        String ddia=fr.format(dd);
        String mmes=fr.format(mes);
        String fecha1=ano+"-"+mmes+"-"+ddia;
        return fecha1;
    }
    public static Date ConvertirStringEnSqlDate(String ff){
        //ff=ff.substring(0,10);
        System.out.println(ff);
        SimpleDateFormat fh=new SimpleDateFormat("yyyy-M-dd");
        Date fechaVal = null;    
       
        
        try {
            //fechaVal = fh.parse(ff.substring(0, 10));
            fechaVal=(Date) fh.parse(ff);
        } catch (ParseException ex) {
            Logger.getLogger(Numeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return fechaVal;
    }
    public static java.sql.Date ConvertirDateADate(java.util.Date ffch){
        
        java.sql.Date sqlDate = new java.sql.Date(ffch.getTime());
        return sqlDate;
    }
    public static String ConvertirFechaCalendarEnString(Calendar dateC){
        DecimalFormat fr=new DecimalFormat("00");
        DecimalFormat formato=new DecimalFormat("####.####");
        //SiderconCapaatos.listaPedidos.clear();
        SimpleDateFormat dia=new SimpleDateFormat("dd/mm/yyyy");
        //Date mes=Calendar.getInstance().getTime();
        //dateChooserCombo1.setDateFormat(dia);
        Calendar fechaNueva=dateC;
        int ano=fechaNueva.get(Calendar.YEAR);
        int mes=fechaNueva.get(Calendar.MONTH);
        mes++;
        int dd=fechaNueva.get(Calendar.DAY_OF_MONTH);
        String ddia=fr.format(dd);
        String mmes=fr.format(mes);
        String fecha1=ano+"-"+mmes+"-"+ddia;
        return fecha1;
    }
    public static String ConvertirNumeroExcell(Double num){
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
        simbolo.setDecimalSeparator(',');
        DecimalFormat formato=new DecimalFormat("####.#",simbolo);
        doble=formato.format(num);
        return doble;
    }
    public static Double ConvertirEnCoeficiente(Double numero){
        Double coe=numero / 100;
        coe=coe + 1;
        return coe;
    }
    public static String ReConvertirEnCoeficiente(Double numero){
        Double coe=numero - 1;
        coe=coe * 100;
        String valor=String.valueOf(coe);
        return valor;
    }
    public static Double ConvertirEnDescuento(Double numero){
        Double coe=numero / 100;
        //coe=coe + 1;
        return coe;
    }
    public static String ConvetirDoubleAString(Double num){
        DecimalFormat formato=new DecimalFormat("####.00");
        doble=formato.format(num);
        return doble;
    }
    public static String ConvetirNumeroDosDigitos(Double num){
        DecimalFormat formato=new DecimalFormat("####.00");
        doble=formato.format(num);
        return doble;
    }
    public static Double Redondear(Double nume){
        Double resultado=Math.round(nume * 100.0) / 100.0;
        return resultado;
    }
    public static String ConvertirFechaFiscal(){
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        fecha=ano+mes+dia;
        
        return fecha;
    }
    public static Calendar SumarDias(Date fecha,int dias){
        Calendar cal=Calendar.getInstance();
        //SimpleDateFormat fh=new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_WEEK, dias);
        return cal;
    }
    public static Calendar RestarDias(Date fecha,int dias){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat fh=new SimpleDateFormat("yyyy-mm-dd");
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_WEEK, dias);
        return cal;
    }
    public static int RestarAFechaActual(Date fechaInicial){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 
		//Date fechaInicial=dateFormat.parse("2016-02-14");
		Date fechaFinal=new Date();
 
		int dias=(int) ((fechaInicial.getTime()-fechaFinal.getTime())/86400000);
                return dias;
 
    }
    public static Date SumarDiasDate(Date fecha,int dias){
        Calendar cal=Calendar.getInstance();
        //SimpleDateFormat fh=new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_YEAR, dias);
        return cal.getTime();
    }
    public static String ConvertirDateAString(Date fecha){
        String resulta;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        resulta=dateFormat.format(fecha);
        return resulta;
    }
    public static double CalcularSubTotal(double monto,double coeficiente){
        List valores;
        double subtotal;
        double ivaCoeficiente;
        double montoIva;
        ivaCoeficiente=coeficiente / 100;
        ivaCoeficiente=1+ivaCoeficiente;
        subtotal=monto / (ivaCoeficiente);
        //subtotal=subtotal * cantidad;
        subtotal=Math.round(subtotal * 100.0) / 100.0;
        return subtotal;
        //valores=new Vector();
        
        
    }
    public static double CalcularIva(double subtotal,double coeficiente){
        //double subtotal;
        double ivaCoeficiente;
        double montoIva;
        ivaCoeficiente=coeficiente / 100;
        montoIva=subtotal * ivaCoeficiente;
        montoIva=Math.round(montoIva * 100.0) / 100.0;
        return montoIva;
    }
    
}
