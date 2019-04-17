/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosR;

import ConfiguracionR.Propiedades;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 * AQUI SE VAN A GESTIONAR TODAS LAS CONECCIONES DEL SISTEMA, LOS DATOS COMO OBJETOS
 * Y LAS INTERFACES VAN A GUARDAR, ELIMINAR O ACTUALIZAR LAS TABLAS, SE VA A PASAR EL STRING POR LA INTERFAZ 
 * PARA QUE AQUI SE REALICE LA TRANSACCION
 * 
 */
public class Conecciones implements Transaccionable{
    private Connection con;
    private PreparedStatement st;
    private String usuario;
    private String base;
    private String pass;
    private String servidor;
    private String driver1="org.apache.derby.jdbc.EmbeddedDriver";
    
    
    

    
    public Conecciones() throws InstantiationException, IllegalAccessException, SQLException {
               //Connection dbConnection = null;
               con=null;
 //String strUrl = "jdbc:derby://localhost:1527/respaldo;create=true";
 File miDir=new File(".");
 String directorio=miDir.getAbsolutePath();
        System.out.println(directorio);
               String strUrl = "jdbc:derby:base14//bambuPrueba.db";
        
        try {
            Class.forName(driver1).newInstance();
            con = DriverManager.getConnection (strUrl,"mauro","mauro"); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
        
    }
    private Connection obtener() throws InstantiationException, IllegalAccessException, SQLException{
        //Connection dbConnection = null;
               con=null;
 //String strUrl = "jdbc:derby://localhost:1527/respaldo;create=true";
               //String strUrl = "jdbc:derby:base14//bambuPrueba.db";
            
        
        try {
            
            File miDir=new File(".");
 String directorio=miDir.getAbsolutePath();
        String directo="C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Manantial\\";
            String strUrl = "jdbc:derby:base14\\bambuPrueba.db";
            
        System.out.println(strUrl);
        
            Class.forName(driver1).newInstance();
            con = DriverManager.getConnection (strUrl,"mauro","mauro");
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
        }
           return con; 
        
        /*
        try {
            MysqlDataSource dataSource=new MysqlDataSource();
            
            
            dataSource.setUser(Propiedades.getUSUARIO());//("ryrsistema");//("root");//
            dataSource.setDatabaseName(Propiedades.getBD());//ryr
            dataSource.setPassword(Propiedades.getCLAVE());//("Remoto");//4FTfQRKWPDe4KF9d//("");//
            dataSource.setServerName(Propiedades.getSERVER());//10.0.0.201//("localhost");//rrpapeles.dyndns.org
            
            
            
            return dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        */
    }
    @Override
    public Boolean guardarRegistro(String sql) {
        Boolean coneccion=true;
        try {
            //System.out.println("SENTENCIA "+sql);
            if(con==null){
                Transaccionable tt=new Conecciones();
            String ss="insert into fallas (st,estado) values ('"+sql+"',0)";
            tt.guardarRegistro(ss);
            Inicio.coneccionRemota=false;
            }else{
             st=con.prepareStatement(sql);   
            st.executeUpdate();
            Inicio.coneccionRemota=true;
            }
            //this.st.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            /*
            coneccion=false;
            FileWriter fichero=null;
            PrintWriter pw=null;
            Inicio.coneccionRemota=false;
            Transaccionable tt=new Conecciones();
            String ss="insert into fallas (st) values ('"+sql+"')";
            tt.guardarRegistro(sql);
            try {
                fichero = new FileWriter("C:\\Gestion\\"+Inicio.fechaDia+" - erroresDeConeccion.txt",true);
                pw=new PrintWriter(fichero);
                pw.println(sql);
                
            } catch (IOException ex1) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex1);
            }finally{
                         try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
            }
            */
        } catch (InstantiationException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coneccion;
    }

    @Override
    public Boolean guardarRegistros(ArrayList listadoDeSentencias) {
        String sql="";
        Boolean coneccionCorrecta=true;
        Iterator il=listadoDeSentencias.listIterator();
        while(il.hasNext()){
            sql=(String)il.next();
            try {
                st.executeUpdate(sql);
                Inicio.coneccionRemota=true;
            } catch (SQLException ex) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
                coneccionCorrecta=false;
                Inicio.coneccionRemota=false;
            }
        }
        
        return coneccionCorrecta;
    }

    @Override
    public ResultSet leerConjuntoDeRegistros(String sql) {
        ResultSet rs=null;
        try {
            
            System.out.println(sql);
            //sql="select * from mauro.usuarios where nombreUsuario like 'adm' and clave ='adm'";
           if(con!=null){
                st=con.prepareStatement(sql);
                st.execute();
                rs=st.getResultSet();
           }
            //}
        } catch (SQLException ex) {
            Inicio.coneccionRemota=false;
            System.out.println("NO SE CONECTA, ACA CARGA LOS OBJETOS");
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NO ENTRO LA CONECCION");
        }
        catch (NullPointerException ee){
            
            if(st!=null)Inicio.coneccionRemota=false;
            
            System.out.println("ERROR "+sql);
        }
        return rs;
    }
    private Boolean ValidarConeccion(){
        Boolean verificar=true;
        //if(st==null)verificar=false;
        if(con==null)verificar=false;
        return verificar;
    }

    @Override
    public Connection obtenerConexion() {
        try {
            return this.obtener();
        } catch (InstantiationException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.obtenerConexion();
    }
   

    }
    

