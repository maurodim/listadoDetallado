/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbsgestion;

import Configuracion.Propiedades;
import facturacion.pantallas.IngresoDePedidos;
import interfaceGraficas.ArticulosAbm;
import interfaceGraficas.LoguinBbsGestion;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.jvnet.substance.SubstanceLookAndFeel;
import pantalla.Ventana;

/**
 *
 * @author mauro
 */
public class BbsGestion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        /*
        ArrayList usuariosList=new ArrayList();
        Usuarios usuarios=new Usuarios();
        usuariosList=usuarios.listarUsuario();
         */
        //Bienvenida bienvenida=new Bienvenida();
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            JFrame.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
            //SubstanceLookAndFeel.setSkin("org.jvnet.substance.ModerateSkin");
            //SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.SaharaSkin");
            //SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenGraphiteGlassSkin");//Oscuro
            //UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            //UIManager.setLookAndFeel("ch.randelshofer.quaqua.BasicQuaquaLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Ventana ventana = new Ventana();
        ventana.setVisible(true);

        File folder = new File("C:\\Gestion");
        File archivos = new File("C:\\Informes");
        File bases = new File("C:\\Gestion\\DB");
        //File imagenes=new File("C:\\Gestion\\imagenes\\saynomore.jpg");
        File bk;
        //FileInputStream fregis = new FileInputStream("C:\\Users\\mauro\\Pictures\\Camera Uploads\\snm.jpg"); 

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        if (!bases.isDirectory()) {

        }
        if (!folder.isDirectory()) {
            //System.out.println("EXISTE EL DIRECTORIO");
            folder.mkdirs();
        } else {
            //System.out.println("NOOOOOOOOOOOOOOO EXISTE EL DIRECTORIO");

        }
        if (!archivos.isDirectory()) {
            archivos.mkdirs();
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
        bk = new File("C:\\Gestion\\backUp.sql");
        //String sql="select * from movimientoscaja into outfile "+bk+" FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\n\r'";
        // Transaccionable tra=new Conecciones();
        //tra.guardarRegistro(sql);

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("C:\\Gestion\\erroresDeConeccion.txt");
            if (archivo.exists()) {
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);

                // Lectura del fichero
                String linea;
                //Transaccionable tra=new Conecciones();
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
        Propiedades.CargarPropiedades();
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BbsGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*ELIEL
        Color colorF = null;
        for (int aa = 0; aa < 5; aa++) {
            JFrame inicioF = new JFrame();
            JPanel jPanel = new JPanel();

            switch (aa) {
                case 1:
                    jPanel.setBackground(Color.red);
                    break;
                case 2:
                    jPanel.setBackground(Color.BLUE);
                    break;
                case 3:
                    jPanel.setBackground(Color.YELLOW);
                    break;
                case 4:
                    jPanel.setBackground(Color.GREEN);
                    break;
                default:
                    jPanel.setBackground(Color.ORANGE);
                    break;
            }
            for (int ab = 1; ab < 11; ab++) {
                ArticulosAbm ingPed = new ArticulosAbm();
                jPanel.add(ingPed);
                ingPed.setTitle("Mozo " + aa+" mesa - "+ab);
                ingPed.setVisible(true);
                ingPed.toFront();
            }
            //this.jPanel1.add(jPanel);
            inicioF.add(jPanel);
            inicioF.setTitle("Mesas Mozo " + aa);
            inicioF.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            inicioF.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicioF.setVisible(true);

        }
        */
        LoguinBbsGestion lBb=new LoguinBbsGestion();
        lBb.setVisible(true);
        lBb.pack();
         
    }
}