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
    
    public static void main(String[] args) {
        File carpeta = new File("Facturas Electronicas");
        File configuracion = new File("Configuracion");
        File archivo = new File("OPG.dat");
        if (!carpeta.isDirectory()) {
            carpeta.mkdirs();
        }
        if (!configuracion.isDirectory()) {
            configuracion.mkdirs();
        }
        if (!archivo.isFile()) {
            JOptionPane.showMessageDialog(null, "NO EXISTE EL ARCHIVO CONECTOR, POR FAVOR INFORME DEL ERROR. GRACIAS");

        } else {
            try {
                Propiedades.CargarPropiedades();
                FacturableE factu = new FacturaElectronica();
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw=new FileWriter("respuesta.dat");
                PrintWriter pw;

                int condicion = 1;
                int idCliente = 1;
                int tVta = 1;//SI ES SERVICIO=2 O PRODUCTO=1
                int idPed = 1;//si es homologacion o produccion
                int ptoVta = Integer.parseInt(Propiedades.getPTO());

                String cuitCliente = "30538872128";
                int tipoD = 80;//80- cuit 96- dni
                int tipoC = 0;// = 1; // tipo comprobante segun excel de la tabla
                Double montoT = null;// = 142.17;
                Double montoB = null;// = 117.50;
                Double montoI = null;// = 24.67;
                String razon = null;// = "CONFEDERACION ARGENTINA DE LA MED EMPRESA (CAME)";
                String direc = null;// = "L. N. ALEM 452 - 1003 CABA";
                mail=null;//=direccion de mail para envio de comprobante
                
                String condicionIvaC = null;// = "1";//tipo iva comprador 1-responsable inscripto 4- exento 5-consumidor final

                ArrayList<DetalleFacturas> lstDetalle = new ArrayList();
                DetalleFacturas detalleF=null;

                String linea;
                Boolean detalle = false;
                int renglon = 0;
                int renglonI = 0;
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
                                detalleF.setDescripcion(linea);
                                break;
                            case 3:
                                detalleF.setCantidadS(linea);
                                break;
                            case 4:
                                detalleF.setDescuentoS(linea);
                                break;
                            case 5:
                                detalleF.setPrecioUnitarioS(linea);
                               
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
                                tipoC=Integer.parseInt(linea);
                                break;
                            case 3:
                                montoT=Numeros.ConvertirStringADouble(linea);
                                break;
                            case 4:
                                montoI=Numeros.ConvertirStringADouble(linea);
                                break;
                            case 5:
                                montoB=Numeros.ConvertirStringADouble(linea);
                                break;
                            case 7:
                                razon=linea;
                                break;
                            case 8:
                                condicionIvaC=linea;
                                break;
                            case 9:
                                direc=linea;
                                break;
                            case 10:
                                mail=linea;
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
                
                //TiposIva iva=new TiposIva(5,50,10.5f,21);
                TiposIva iva = new TiposIva(5, montoB, montoI, 21);
                listadoI.add(iva);
                IngresoDni ingreso = new IngresoDni(null, true);
                ingreso.setLocationRelativeTo(null);
                ingreso.setVisible(true);
                
                
                Opcionales opcion = new Opcionales();
                opcion.setCodigo("5");
                opcion.setTexto(ingreso.getExcepcionS());
                ArrayList<Opcionales> lstOpcionales = new ArrayList();
                lstOpcionales.add(opcion);

                Integer nro = factu.generar(null, condicion, Propiedades.getARCHIVOKEY(), Propiedades.getARCHIVOCRT(), idCliente, cuitCliente, tipoC, montoT, montoB, montoI, ptoVta, Propiedades.getCUIT(), tVta, listadoI, listadoT, razon, direc, condicionIvaC, lstDetalle, idPed, Propiedades.getNOMBRECOMERCIO(), Propiedades.getRAZONSOCIAL(), "resp inscripto", Propiedades.getDIRECCION(), Propiedades.getTELEFONO(), Propiedades.getINGBRUTOS(), Propiedades.getINICIOACT(), lstOpcionales);
                System.out.println("comprobante n° " + nro);
                fr.close();
                if(nro != null){
                    pw=new PrintWriter(fw);
                    pw.println(String.valueOf(ptoVta));
                    pw.println(String.valueOf(nro));
                    
                    if(null != pw){
                        pw.close();
                    }
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
