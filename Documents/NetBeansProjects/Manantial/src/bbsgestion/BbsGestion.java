/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbsgestion;

import Actualizaciones.BkDeConeccion;
import Administracion.Licencias;
import Administracion.LicenciasControl;
import ConfiguracionR.Propiedades;
import Cajas.Usuarios;
import Conversores.Numeros;
import interfaceGraficasManantial.Inicio;
import interfaces.Backpeable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.xml.sax.SAXException;

/**
 *
 * @author mauro
 */
public class BbsGestion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*
        ArrayList usuariosList=new ArrayList();
        Usuarios usuarios=new Usuarios();
        usuariosList=usuarios.listarUsuario();
         */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            JFrame.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            //UIManager.setLookAndFeel("ch.randelshofer.quaqua.BasicQuaquaLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        File folder = new File("Gestion");
        File archivos = new File("Informes");
        File bases = new File("Facturas Electronicas");
        File fiscal=new File("Fiscal");
        File configuracion = new File("Configuracion");
        File etiquetas=new File("Etiquetas");
        //File imagenes=new File("C:\\Gestion\\imagenes\\saynomore.jpg");
        File bk;
        //FileInputStream fregis = new FileInputStream("C:\\Users\\mauro\\Pictures\\Camera Uploads\\snm.jpg"); 

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        if (!folder.isDirectory()) {
            //System.out.println("EXISTE EL DIRECTORIO");
            folder.mkdirs();
        } else {
            //System.out.println("NOOOOOOOOOOOOOOO EXISTE EL DIRECTORIO");

        }
        if (!archivos.isDirectory()) {
            archivos.mkdirs();
        }
        if (!configuracion.isDirectory()) {
            configuracion.mkdirs();
        }
        if(!bases.isDirectory()){
            bases.mkdirs();
        }
        if(!fiscal.isDirectory()){
            fiscal.mkdirs();
        }
        if(!etiquetas.isDirectory()){
            etiquetas.mkdirs();
        }
        /*
        if(!imagenes.isFile()){
            //imagenes.mkdirs();
            FileOutputStream fsalida = new FileOutputStream("C:\\Gestion\\imagenes\\saynomore.jpg", true); 
        int b = fregis.read(); 
        while (b != -1) { 
        fsalida.write(b); 
        b = fregis.read(); 

        } 
        
        fsalida.flush(); 
        fsalida.close();
         
       
            
        }
          
        fregis.close();
         */
        bk = new File("Gestion\\backUp.sql");
        //String sql="select * from movimientoscaja into outfile "+bk+" FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\n\r'";
        // Transaccionable tra=null;

        //tra.guardarRegistro(sql);
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("Gestion\\erroresDeConeccion.txt");
            if (archivo.exists()) {
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);

                // Lectura del fichero
                String linea;
                //Transaccionable tra=null;

                while ((linea = br.readLine()) != null) {

                    //System.out.println(linea);
                    // if(tra.guardarRegistro(linea));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        File archivo1 = null;

        archivo = new File("Gestion\\idEquipo.txt");
        try {

            if (Propiedades.CargarPropiedades1()) {
                Usuarios usuario = new Usuarios();
                Usuarios usuarios = new Usuarios();
                //try{
                //usuarios=(Usuarios) usuario.validarClave(jTextField1.getText(),new String(jPasswordField1.getPassword()));
                //}catch(Exception ex){
                Backpeable bk1 = new BkDeConeccion();
                usuarios = (Usuarios) bk1.leerUsuarios("adm", "adm");
                //}
                Licencias licencia=new Licencias();
                Licencias licenciaWeb=new Licencias();
                LicenciasControl control=new LicenciasControl();
                ArrayList lstLic=new ArrayList();
                lstLic=control.ListarLicencias();
                control.ActualizarLicencia(lstLic);
                licenciaWeb=(Licencias) control.LeerYActualizarLicencia();
                if(licenciaWeb != null){
                    System.out.println("Licencia actual cargada en propiedades "+Propiedades.getIDLICENCIA()+" -- licencia web "+licenciaWeb.getId());
                if(Propiedades.getIDLICENCIA() != licenciaWeb.getId()){
                    control.UpdateLicenciaLocal(licenciaWeb);
                    JOptionPane.showMessageDialog(null, "LICENCIA MODIFICADA");
                }
                licencia=(Licencias) control.LeerActualLocal(licenciaWeb.getId());
                
                //sleep(6000);
                }else{
                    licencia=(Licencias) control.LeerActualLocal(Propiedades.getIDLICENCIA());
                }
                System.out.println("cantidad "+licencia.getActualFc()+" presupuestos "+licencia.getActualPresupuestos()+" vencimeinto "+licencia.getFechadeVencimiento());
                if(licencia.getActualFc() < 3 || licencia.getActualPresupuestos() < 3){
                    JOptionPane.showMessageDialog(null, "Renueve su Licencia, quedán pocos comprobantes disponibles!!");
                }
                java.util.Date vencimiento=(java.util.Date) Numeros.ConvertirStringEnDate(licencia.getFechadeVencimiento());
                int dias=Numeros.RestarAFechaActual(vencimiento);
                if(dias < 5 && dias > -1){
                    JOptionPane.showMessageDialog(null, "Renueve su Licencia, quedán "+dias+" para su vencimiento!!");
                    
                }
                if(dias < 0 ){
                    JOptionPane.showMessageDialog(null, "Fecha de Licencia CADUCADA, Por favor Renueve Licencia!!");
                    if(licencia.getId()==1){
                        
                    }else{
                        System.exit(0);
                    }
                }
//ACA DEBERÍA PONER LAS ALERTAS PARA QUE CARGUEN UNA NUEVA LICENCIA
                
                
                /*
                PortalWeb puerta=new PortalWeb();
                puerta.setCuit(Propiedades.getCUIT());
                puerta.setRazonSocial(Propiedades.getNOMBRECOMERCIO());
                puerta.GenerarCertificadosAfip();
                */
                if(dias < 0){
                    if(licencia.getId()==1){
                        //Calendar calen=Numeros.SumarDiasDate(vencimiento,licencia.getDiasLicencia());
                        java.util.Date fecc=Numeros.SumarDiasDate(vencimiento,licencia.getDiasLicencia());
                        String veneN=Numeros.ConvertirDateAString(fecc);
                        licencia.setFechadeVencimiento(veneN);
                        control.RenovarLicencia(licencia);
                        JOptionPane.showMessageDialog(null, "LICENCIA GRATUITA RENOVADA");
                    }else{
                        JOptionPane.showMessageDialog(null, "Renueve su Licencia, LICENCIA CADUCADA");
                    }
                }else{
                Inicio in = new Inicio(2);
                //Inicio in=new Inicio();
                Inicio.niv = usuarios.getNivelDeAutorizacion();
                Inicio.usuario = usuarios;
                Inicio.sucursal = usuarios.getSucursal();
                Inicio.deposito = Inicio.sucursal.getDepositos();
                in.setTitle(" MANANTIAL GESTIÓN ");
                in.setExtendedState(JFrame.MAXIMIZED_BOTH);
                in.setVisible(true);
                in.toFront();
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(BbsGestion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BbsGestion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BbsGestion.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SAXException ex) {
            Logger.getLogger(BbsGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*LoguinBbsGestion lBb=new LoguinBbsGestion();
        lBb.setVisible(true);
        lBb.pack();
         */
    }
}
