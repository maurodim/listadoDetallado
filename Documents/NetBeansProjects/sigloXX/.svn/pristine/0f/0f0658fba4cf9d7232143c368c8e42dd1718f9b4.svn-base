/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conversores;

import interfaceGraficas.Inicio;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mauro
 */
public class Numeros {
    private static String doble;
    private static String flotante;
    private static String fecha;
    
    public static String ConvertirNumero(Double num){
        DecimalFormat formato=new DecimalFormat("####.#");
        doble=formato.format(num);
        return doble;
    }
    public static String ConvetirNumeroCuatroDigitos(Double num){
        DecimalFormat formato=new DecimalFormat("####.####");
        doble=formato.format(num);
        return doble;
    }
    public static String ConvetirNumeroDosDigitos(Double num){
        DecimalFormat formato=new DecimalFormat("####.00");
        doble=formato.format(num);
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
        String fecha1=ano+"-"+mes+"-"+dd;
        return fecha1;
    }
    public static Date ConvertirStringEnDate(String ff){
        SimpleDateFormat fh=new SimpleDateFormat("yyyy-mm-dd");
        Date fechaVal = null;    
        try {
            fechaVal = fh.parse(ff);
        } catch (ParseException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaVal;
    }
    public static Double ConvertirStringADouble(String num){
        if(num.equals("")){
            num="0.00";
        }
        //if(num.equals("00000002,104.50"))JOptionPane.showMessageDialog(null,"este es!!!");
        num=num.replace(",",".");
        System.out.println(" rsultado "+num);
        Double dd = null;
        try{
            dd=Double.parseDouble(num);
        }catch(java.lang.NumberFormatException ee){
            int pos=num.indexOf(".");
            String antes=num.substring(0, pos);
            pos++;
            String despues=num.substring(pos);
            num=antes+despues;
            dd=Double.parseDouble(num);
        }finally{
            return dd;
        }
    }
}
