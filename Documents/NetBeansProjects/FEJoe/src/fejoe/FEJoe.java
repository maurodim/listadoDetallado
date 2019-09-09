/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fejoe;

import Configuracion.Propiedades;
import conversiones.Numeros;
import interfaces.FacturableE;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetos.DetalleFacturas;
import objetos.FacturaElectronica;
import objetos.Opcionales;
import objetos.TiposIva;
import pantallas.IngresoDni;

/**
 *
 * @author Usuario
 */
public class FEJoe {
    public static String mail;
    public static String nombreVendedor;
    
    public static void main(String[] args) {
        File carpeta = new File("Facturas Electronicas");
        File configuracion = new File("Configuracion");
        File archivo = new File("OPG.dat");
        File fwO=new File("respuesta.dat");
        FileWriter fw;
        PrintWriter pw;
        nombreVendedor=null;
        if (!carpeta.isDirectory()) {
            carpeta.mkdirs();
        }
        if (!configuracion.isDirectory()) {
            configuracion.mkdirs();
        }
        if(!fwO.isFile()){
            
        }else{
            try {
                //fwO.delete();
                fw=new FileWriter("respuesta.dat");
                pw=new PrintWriter(fw);
                    pw.println("");
                    pw.println("");
                    
                    if(null != pw){
                        pw.close();
                    }
            } catch (IOException ex) {
                Logger.getLogger(FEJoe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!archivo.isFile()) {
            JOptionPane.showMessageDialog(null, "NO EXISTE EL ARCHIVO CONECTOR, POR FAVOR INFORME DEL ERROR. GRACIAS");

        } else {
            try {
                Propiedades.CargarPropiedades();
                FacturableE factu = new FacturaElectronica();
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                
                

                int condicion = 1;
                int idCliente = 1;
                int tVta = 1;//SI ES SERVICIO=2 O PRODUCTO=1
                int idPed = 0;//si es 1-homologacion o 0-produccion
                int ptoVta = Integer.parseInt(Propiedades.getPTO());

                String cuitCliente = "";
                int tipoD = 80;//80- cuit 96- dni
                int tipoC = 0;// = 1; // tipo comprobante segun excel de la tabla
                Double montoT = null;// = 142.17;
                Double montoB = null;// = 117.50;
                Double montoI = null;// = 24.67;
                String razon = null;// = "CONFEDERACION ARGENTINA DE LA MED EMPRESA (CAME)";
                String direc = null;// = "L. N. ALEM 452 - 1003 CABA";
                mail=null;//=direccion de mail para envio de comprobante
                boolean calcular=false;
                
                String condicionIvaC = null;// = "1";//tipo iva comprador 1-responsable inscripto 4- exento 5-consumidor final

                ArrayList<DetalleFacturas> lstDetalle = new ArrayList();
                DetalleFacturas detalleF=null;

                String linea;
                String linea1;
                Boolean detalle = false;
                StringBuilder sb;
                int renglon = 0;
                int renglonI = 0;
                double precioUni=0.00;
                int canti=0;
                int alerta=0;
                int sbPos=0;
                String ddetalle;
                while ((linea = br.readLine()) != null) {
                    //linea=br.readLine();
                    if (detalle) {
                        //aca se carga el detalle del pedido a imprimir
                        renglonI++;
                        switch(renglonI){
                            case 1:
                                
                                detalleF.setCodigo(linea);
                                break;
                            case 2:
                                sb=new StringBuilder(linea.replace("_"," "));
                                //ddetalle=sb;
                                sbPos=sb.indexOf("   ");
                                detalleF.setDescripcion(sb.toString());
                                
                                System.out.println("renglon "+sb.toString()+" cantidad: "+sb.length());
                                break;
                            case 3:
                                canti=Integer.parseInt(linea);
                                detalleF.setCantidadS(linea);
                                break;
                            case 4:
                                detalleF.setDescuentoS(linea);
                                break;
                            case 5:
                                //linea=linea.replace(",", ".");
                                precioUni=Double.parseDouble(linea.replace(",", "."));
                                precioUni=Math.round((precioUni / canti) * 100.0) / 100.0;
                                detalleF.setPrecioUnitarioS(String.valueOf(precioUni));
                               
                        }
                        
                    } else {
                        //se cargan los distintos parametro para la fc;
                        switch(renglon){
                            case 0:
                                idCliente=Integer.parseInt(linea);
                                break;
                            case 1:
                                cuitCliente=linea;
                                cuitCliente=cuitCliente.replace("-","");
                                break;
                            case 2:
                                try{
                                    tipoC=Integer.parseInt(linea);
                                }catch(java.lang.NumberFormatException ex){
                                    tipoC=3;
                                    alerta=1;
                                    linea1=linea.replace(".","");
                                montoT=Numeros.ConvertirStringADouble(linea1.replace(",", "."));
                                }
                                break;
                            case 3:
                                if(alerta==0){
                                linea1=linea.replace(".","");
                                montoT=Numeros.ConvertirStringADouble(linea1.replace(",", "."));
                                }else{
                                   linea1=linea.replace(".","");
                                montoI=Numeros.ConvertirStringADouble(linea1.replace(",", "."));
                                if(montoI==0)calcular=true; 
                                }
                                break;
                            case 4:
                                if(alerta==0){
                                linea1=linea.replace(".","");
                                montoI=Numeros.ConvertirStringADouble(linea1.replace(",", "."));
                                if(montoI==0)calcular=true;
                                }else{
                                    linea1=linea.replace(".","");
                                montoB=Numeros.ConvertirStringADouble(linea1.replace(",", "."));
                                }
                                //calcular=true;
                                break;
                            case 5:
                                if(alerta==0){
                                linea1=linea.replace(".","");
                                montoB=Numeros.ConvertirStringADouble(linea1.replace(",", "."));
                                }
                                break;
                            case 6:
                                if(alerta==0){
                                    
                                }else{
                                    razon=linea;
                                }
                                break;
                            case 7:
                                if(alerta==0){
                                razon=linea;
                                }else{
                                    condicionIvaC=linea;
                                }
                                
                                break;
                            case 8:
                                if(alerta==0){
                                condicionIvaC=linea;
                                }else{
                                    direc=linea;
                                }
                                
                                break;
                            case 9:
                                if(alerta==0){
                                    direc=linea;
                                }
                                break;
                            case 10:
                                mail=linea;
                                break;
                            case 11:
                                nombreVendedor=linea;
                                break;
                        }

                    }
                    if (linea.equals("<items>")) {
                        detalle = true;
                        detalleF=new DetalleFacturas();
                    }
                    if (linea.equals("</items>")) {
                        detalle = false;
                        renglonI = 0;
                        lstDetalle.add(detalleF);
                    }

                    System.out.println(linea);
                    renglon++;
                }

                ArrayList listadoI = new ArrayList();
                ArrayList listadoT = new ArrayList();
                
                double montoCalculado=montoB + montoI;
                if(calcular){
                    if(montoB == 0){
                        montoB=montoT / 1.21;
                    }
                    montoB=Math.round(montoB * 100.0)/ 100.0;
                    montoI=montoB * 0.21;
                    montoI=Math.round(montoI * 100.0)/ 100.0;
                    montoT=montoB + montoI;
                }
                double resultado=0.00;
                if(montoT < montoCalculado){
                    resultado=montoT / montoCalculado;
                    //resultado=Math.round(resultado * 100.0) / 100.0;
                    
                }
                if(resultado == 0){
                    
                }else{
                    montoB=montoB * resultado;
                    montoB=Math.round(montoB * 100.0) / 100.0;
                    montoI=montoI * resultado;
                    montoI=Math.round(montoI * 100.0) / 100.0;
                    Iterator itDet=lstDetalle.listIterator();
                    double preURes=0.00;
                    resultado=Math.round(resultado * 100.0) / 100.0;
                    while(itDet.hasNext()){
                        detalleF=(DetalleFacturas) itDet.next();
                        preURes=detalleF.getPrecioUnitario();
                        preURes=preURes * resultado;
                        //preURes=Math.round(preURes * 100.0)/ 100.0;
                        detalleF.setPrecioUnitarioS(String.valueOf(preURes));
                        System.out.println("codigo "+detalleF.getIdArticulo()+" cantidad "+detalleF.getCantidad()+" precio unitario "+preURes);
                    }
                }
                //TiposIva iva=new TiposIva(5,50,10.5f,21);
                TiposIva iva = new TiposIva(5, montoB, montoI, 21);
                listadoI.add(iva);
                
                if(args.length == 0){
                    
                
                IngresoDni ingreso = new IngresoDni(null, true);
                ingreso.setLocationRelativeTo(null);
                ingreso.setVisible(true);
                
                
                Opcionales opcion = new Opcionales();
                opcion.setCodigo("5");
                opcion.setTexto(ingreso.getExcepcionS());
                ArrayList<Opcionales> lstOpcionales = new ArrayList();
                lstOpcionales.add(opcion);
                Integer nro = null;
                if(tipoC== 2 || tipoC== 3)mail=null;
                if(razon !=null){
                nro = factu.generar(null, condicion, Propiedades.getARCHIVOKEY(), Propiedades.getARCHIVOCRT(), idCliente, cuitCliente, tipoC, montoT, montoB, montoI, ptoVta, Propiedades.getCUIT(), tVta, listadoI, listadoT, razon, direc, condicionIvaC, lstDetalle, idPed, Propiedades.getNOMBRECOMERCIO(), Propiedades.getRAZONSOCIAL(), "resp inscripto", Propiedades.getDIRECCION(), Propiedades.getTELEFONO(), Propiedades.getINGBRUTOS(), Propiedades.getINICIOACT(), lstOpcionales);
                //nro=null;
                System.out.println("comprobante n° " + nro);
                fr.close();
                }
                fw=new FileWriter("respuesta.dat");
                if(nro != null){
                    
                    pw=new PrintWriter(fw);
                    pw.println(String.valueOf(ptoVta));
                    pw.println(String.valueOf(nro));
                    
                    if(null != pw){
                        pw.close();
                    }
                }
                
                }else{
                    FacturaElectronica facturaE=new FacturaElectronica();
                    String cae = args[0];
                    String vencimiento=args[1];
                    String numero = args[2];
                    mail=null;
                    factu.reimprimir(condicion, idCliente, cuitCliente, tipoC, montoT, montoB, montoI, ptoVta, Propiedades.getCUIT(), tVta, listadoI, listadoT, razon, direc, condicionIvaC, lstDetalle, idPed, Propiedades.getNOMBRECOMERCIO(), Propiedades.getRAZONSOCIAL(), "resp inscripto", Propiedades.getDIRECCION(), Propiedades.getTELEFONO(), Propiedades.getINGBRUTOS(), Propiedades.getINICIOACT(), cae,numero,vencimiento);
                    
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FEJoe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FEJoe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FEJoe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
