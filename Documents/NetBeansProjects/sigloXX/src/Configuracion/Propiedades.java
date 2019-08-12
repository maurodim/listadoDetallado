/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import ObjetosBackUp.BackUp;
import ObjetosBackUp.Backapear;
import interfaces.Transaccionable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.ConeccionInstalacion;
import objetos.ConeccionLocal;

/**
 *
 * @author mauro
 */
public class Propiedades {
    static String SERVER;
    static String BD="siglox";
    static String USUARIO;
    static String CLAVE;
    static String CREADA;
    static String ARCHIVOBK;
    static String NOMBRECOMERCIO;
    static String LOGO;
    static String IMAGEN;
    static String CORREOCIERREDECAJA;
    static String CORREOCC;
    static String CORREOCCC;

    public static String getCORREOCIERREDECAJA() {
        return CORREOCIERREDECAJA;
    }

    public static String getCORREOCC() {
        return CORREOCC;
    }

    public static String getCORREOCCC() {
        return CORREOCCC;
    }
    

    public static String getSERVER() {
        return SERVER;
    }

    public static String getBD() {
        return BD;
    }

    public static String getUSUARIO() {
        return USUARIO;
    }

    public static String getCLAVE() {
        return CLAVE;
    }

    public static String getCREADA() {
        return CREADA;
    }

    public static String getARCHIVOBK() {
        return ARCHIVOBK;
    }

    public static String getNOMBRECOMERCIO() {
        return NOMBRECOMERCIO;
    }

    public static String getLOGO() {
        return LOGO;
    }

    public static String getIMAGEN() {
        return IMAGEN;
    }
    
    
    public static void CargarPropiedades(){
        File archivo = new File ("Configuracion\\bbsGestion.properties");
         if(archivo.exists()){
         FileReader fr = null;
            try {
                fr = new FileReader (archivo);
                BufferedReader br = new BufferedReader(fr);
                // Lectura del fichero
                String linea;
                int renglon=0;
                //Transaccionable tra=new Conecciones();
                while((linea=br.readLine())!=null){
                    
                    
                    renglon++;
                    switch (renglon){
                        case 2:
                            SERVER=linea.substring(7);
                            break;
                        case 3:
                            BD=linea.substring(3);
                            break;
                        case 4:
                            USUARIO=linea.substring(8);
                            break;
                        case 5:
                            CLAVE=linea.substring(6);
                            break;
                        case 6:
                            CREADA=linea.substring(7);
                            break;
                        case 7:
                            ARCHIVOBK=linea.substring(10);
                            break;
                        case 9:
                            NOMBRECOMERCIO=linea.substring(15);
                            break;
                        case 10:
                            LOGO=linea.substring(5);
                            break;
                        case 11:
                            IMAGEN=linea.substring(7);
                            break;
                        default:
                            break;
                            
                    }
                    
                    System.out.println(renglon+" // "+SERVER+" // "+BD+" // "+USUARIO+" // "+CLAVE+" // "+CREADA+" // "+ARCHIVOBK+" // "+NOMBRECOMERCIO);
                    // if(tra.guardarRegistro(linea));
      }
            if(CREADA.equals("0")){

                        Transaccionable tra=new ConeccionInstalacion();
                        tra.guardarRegistro("create database "+BD);
                        tra.guardarRegistro("use "+BD);
                        tra.guardarRegistro("grant usage on *.* to '"+USUARIO+"'@'localhost' identified by '"+CLAVE+"'");
                        tra.guardarRegistro("grant all privileges on "+BD+".* to '"+USUARIO+"'@'localhost'");
                        tra.guardarRegistro("flush privileges");
                        //tra.guardarRegistro("quit");
                        Backapear back=new BackUp();
                        back.RecuperarArchivos("Configuracion/"+ARCHIVOBK,BD);
                    }
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //BD="siglox";
        
        
        
    }
    private void CrearBase(){
        Boolean veridi=false;
        Transaccionable tra=new ConeccionInstalacion();
        tra.guardarRegistro("create database "+BD);
        
        //return veridi;
    }
}
