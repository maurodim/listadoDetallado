/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraficasManantial;

import Articulos.Pantallas.IngresoDeMercaderia;
import Cajas.ArqueoDeCaja;
import Cajas.CajaAbm;
import ClientesPantallas.AbmClientes;
import Articulos.Pantallas.RubrosAbm;
import Actualizaciones.Actualiza;
import Actualizaciones.Actualiza1;
import Articulos.Pantallas.AbmIva;
import Proveedores.Proveedores;
import Depositos.Depositos;
import Cajas.Cajas;
import Sucursales.ListasDePrecios;
import Sucursales.Sucursales;
import Cajas.Usuarios;
import facturacion.clientes.Clientes;
import interfacesPrograma.Cajeables;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Articulos.Pantallas.ArticulosAbm;
import Cajas.CajaHistoricos;
import Cajas.PagosAbm;
import ConfiguracionR.Configuracion;
import ConfiguracionR.Propiedades;
import Etiquetador.Pantallas.Etiquetador;
import Look.Fondo;
import Proveedores.AbmProveedores;
import ClientesPantallas.ListasDePreciosAbm;
import facturacion.pantallas.IngresoDeFacturas;
import facturacion.pantallas.NotaDeCredito;
import facturacion.pantallas.NotaDeDebito;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author mauro
 */
public class Inicio extends javax.swing.JFrame {
    public static Integer niv;
    public static Usuarios usuario;
    public static Sucursales sucursal;
    public static Depositos deposito;
    public static Cajas caja;
    public static String fechaDia;
    public static Date fechaVal;
    public static Integer numeroCajaAdministradora=0;
    private BufferedImage img;
    public static Boolean coneccionRemota=true;
    public static Integer actualizable=0;
    public static int actualizacionesClientes=0;
    public InputStream foto1=this.getClass().getResourceAsStream("/imagen/nuevos/Manantial-logo-wide.png");

    public void setNiv(Integer nive) {
        niv = nive;
        permisos(niv);
    }
    
    
    /**
     * Creates new form Inicio
     */
    public Inicio(Integer nivel) {
        //Articulos.CargarMap();
        //if(coneccionRemota){
        //Articulos.CargarMap();
        Inicio.coneccionRemota=false;
        if(Inicio.coneccionRemota){
            Proveedores.cargarListadoProv1();
        }else{
            Proveedores.cargarListadoProv();
        }
        Clientes.cargarMap();
        actualizacionesClientes=2;
        ListasDePrecios.cargarMap();
        //Cajas.BackapearCajas();
        Cajas.LeerCajaAdministradora();
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
        fechaDia=ano+"-"+mes+"-"+dia;
	//System.err.println(fechaDia);
        //fecha="23/12/2011";
        String fh=ano+"-"+mes+"-"+dia;
        SimpleDateFormat ff=new SimpleDateFormat("yyyy-mm-dd");
        fechaVal = null;    
        try {
            fechaVal = ff.parse(fh);
        } catch (ParseException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //}else{
        
        Actualiza actu=new Actualiza();
        actu.start();
        Actualiza1 actu1=new Actualiza1();
        actu1.start();
        //}
        initComponents();
        Image icon=new ImageIcon(getClass().getResource("/imagen/Mlogo.png")).getImage();
        this.setIconImage(icon);
        
        int ancho=jDesktopPane1.getWidth()-50;
        int alto=jDesktopPane1.getHeight()-20;
        System.out.println("ancho "+ancho+" alto "+alto);
        
        cargarImagen(jDesktopPane1,foto1,ancho,alto);
        System.out.println("alto "+alto+" y ancho "+ancho);
        Runtime jpfBatch=Runtime.getRuntime();
        try {
            if(Propiedades.getPUBLICIDAD()==0){
            jpfBatch.exec("java -jar Web/Web.jar");
            }
            //permisos(nivel);
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Inicio() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
public  void cargarImagen(javax.swing.JDesktopPane jDeskp,InputStream fileImagen,int aa,int allt)
    {   
        /*
        Image img=new ImageIcon(this.getClass().getResource("Manantial-logo-wide.png")).getImage();
        try{
            Graphics2D g2d=(Graphics2D)jDeskp.getGraphics();
            double x=img.getWidth(null);
            double y=img.getHeight(null);
            g2d.scale(getWidth() / x,getHeight() / y);
            g2d.drawImage(img, 0, 0, jDeskp);
        }catch(Exception e){
            System.out.println("error "+e);
        }
        
        */
        try{   
            BufferedImage image = ImageIO.read(fileImagen);
            
              jDeskp.setBorder(new Fondo(image)); }
        catch (Exception e){   System.out.println("Imagen no disponible");   }
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            if(coneccionRemota){
                img = ImageIO.read(new URL("http://www.maurodi.net/imagenes/saynomore.jpg"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        jDesktopPane1 = new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(img, 0, 0, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800,600);
            }

        };
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISITEMA DE GESTION BAMBU SOFTWARE");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jDesktopPane1.setAutoscrolls(true);
        jDesktopPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDesktopPane1ComponentShown(evt);
            }
        });

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/administracion.png"))); // NOI18N
        jMenu1.setText("Administracion");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/arqueoDeCaja.png"))); // NOI18N
        jMenuItem14.setText("Cajas");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem14);

        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/informes.png"))); // NOI18N
        jMenuItem21.setText("Informes");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem21);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/pagos.png"))); // NOI18N
        jMenuItem11.setText("Formas de Pago");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/consulta.png"))); // NOI18N
        jMenuItem13.setText("Archivos Iva");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem13);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/lista.png"))); // NOI18N
        jMenuItem15.setText("Listas de Precios");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem15);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/compras.png"))); // NOI18N
        jMenu2.setText("Compras");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/tractorunitblack.png"))); // NOI18N
        jMenuItem6.setText("Ingreso de Mercaderia");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/articulos.png"))); // NOI18N
        jMenuItem12.setText("Articulos");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/rubros.png"))); // NOI18N
        jMenuItem8.setText("Rubros");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/stock.png"))); // NOI18N
        jMenuItem18.setText("Ajustar Stock");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/excel.png"))); // NOI18N
        jMenuItem9.setText("Importar Artículos Por Excel");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/etiquetas.png"))); // NOI18N
        jMenuItem10.setText("Generar Etiquetas");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/ventas.png"))); // NOI18N
        jMenu3.setText("Ventas");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/cash_register.png"))); // NOI18N
        jMenuItem5.setText("Abrir Caja");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/facturacion.png"))); // NOI18N
        jMenuItem2.setText("Facturacion");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/arqueoDeCaja.png"))); // NOI18N
        jMenuItem3.setText("Arqueo de Caja");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/cierreCaja.png"))); // NOI18N
        jMenuItem4.setText("Cierre de Caja");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/credito.png"))); // NOI18N
        jMenuItem1.setText("Nta de Crédito");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/debito.png"))); // NOI18N
        jMenuItem7.setText("Nta de Débito");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/man_black.png"))); // NOI18N
        jMenu4.setText("Proveedores");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/clientes.png"))); // NOI18N
        jMenu6.setText("Clientes");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/procesar.png"))); // NOI18N
        jMenu5.setText("Configuración");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/web.png"))); // NOI18N
        jMenu7.setText("Promociones");
        jMenu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu7MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu7);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/manual.png"))); // NOI18N
        jMenu8.setText("Ayuda");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDesktopPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDesktopPane1ComponentShown
        Loguin log=new Loguin();
        //this.jDesktopPane1.add(log);
        log.setVisible(true);
        //log.toFront();
        log.pack();
        
    }//GEN-LAST:event_jDesktopPane1ComponentShown

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        IngresoDeFacturas ingPed=new IngresoDeFacturas();
        jDesktopPane1.add(ingPed);
        ingPed.setVisible(true);
        ingPed.toFront();
        try {
            ingPed.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       this.jMenuItem2.setEnabled(true);
       Cajeables caj=new Cajas();
       if(caj.VerificarCaja(usuario.getNumero(),sucursal.getNumero(),fechaDia)){
           Inicio.caja=(Cajas)caj.CargarCaja(usuario.getNumero(),sucursal.getNumero(),fechaDia);
       }else{
       Inicio.caja=new Cajas(1);
       Double saldo=Double.parseDouble(JOptionPane.showInputDialog("Ingrese Saldo Inicial","0.00"));
       //System.out.println("SALDO INGRESADO "+saldo);
       Inicio.caja.setSaldoInicial(saldo);
       
       Inicio.caja=(Cajas) caj.AbrirCaja(caja);
       }
       Inicio.sucursal.setCaja(caja);
       this.jMenuItem5.setEnabled(false);
       //System.out.println("CAJA Nº "+caja.getNumero());
       
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //Cajeables caj=new Cajas();
        //Cajas cajas=(Cajas)caj.ArquearCaja(caja);
        //System.out.println("SALDO DE CAJA "+cajas.getSaldoFinal());
        CajaAbm arq=new CajaAbm();
        jDesktopPane1.add(arq);
        arq.setVisible(true);
        arq.toFront();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        IngresoDeMercaderia ing=new IngresoDeMercaderia();
        jDesktopPane1.add(ing);
        ing.setVisible(true);
        ing.toFront();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
       
       jMenu1.setEnabled(true);
       jMenu2.setEnabled(true);
       jMenu3.setEnabled(true);
       jMenu4.setEnabled(true);
       //jMenu5.setEnabled(true);
       jMenu6.setEnabled(true);
       //jMenu7.setEnabled(Inicio.usuario.getMenu().getMenu7());
              this.jMenuItem2.setEnabled(true);
       Cajeables caj=new Cajas();
       if(caj.VerificarCaja(usuario.getNumero(),sucursal.getNumero(),fechaDia)){
           this.caja=(Cajas)caj.CargarCaja(usuario.getNumero(),sucursal.getNumero(),fechaDia);
       }else{
       this.caja=new Cajas(1);
       Double saldo=Double.parseDouble(JOptionPane.showInputDialog("Ingrese Saldo Inicial","0.00"));
       //System.out.println("SALDO INGRESADO "+saldo);
       this.caja.setSaldoInicial(saldo);
       
       this.caja=(Cajas) caj.AbrirCaja(caja);
       }
       this.sucursal.setCaja(caja);
       this.jMenuItem5.setEnabled(false);

    }//GEN-LAST:event_formComponentShown

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        CajaHistoricos cajA=new CajaHistoricos();
        jDesktopPane1.add(cajA);
        cajA.setVisible(true);
        cajA.toFront();
        try {
            cajA.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        RubrosAbm rubross=new RubrosAbm();
        jDesktopPane1.add(rubross);
        rubross.setVisible(true);
        rubross.toFront();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        ArticulosAbm artt=new ArticulosAbm();
        jDesktopPane1.add(artt);
        try {
            artt.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        artt.setVisible(true);
        artt.toFront();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        ArqueoDeCaja cajaA=new ArqueoDeCaja();
        jDesktopPane1.add(cajaA);
        cajaA.setTitle("CIERRE DE CAJA");
        cajaA.setVisible(true);
        cajaA.toFront();
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        EmisorDeInformes emisor=new EmisorDeInformes();
        jDesktopPane1.add(emisor);
        emisor.setVisible(true);
        emisor.toFront();
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
        
    }//GEN-LAST:event_jMenu6ActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
         AbmClientes clie=new AbmClientes();
        jDesktopPane1.add(clie);
        try {
            clie.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        clie.setVisible(true);
        clie.toFront();
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        AbmProveedores abmP=new AbmProveedores();
        jDesktopPane1.add(abmP);
        abmP.setVisible(true);
        abmP.toFront();
        try {
            abmP.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        StockAbm stock=new StockAbm();
        Inicio.jDesktopPane1.add(stock);
        stock.setVisible(true);
        stock.toFront();
                
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        NotaDeCredito credito=new NotaDeCredito();
        Inicio.jDesktopPane1.add(credito);
        credito.setVisible(true);
        credito.toFront();
        try {
            credito.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        NotaDeDebito debito=new NotaDeDebito();
        Inicio.jDesktopPane1.add(debito);
        debito.setVisible(true);
        debito.toFront();
        try {
            debito.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        AbmIva importacion=new AbmIva();
        Inicio.jDesktopPane1.add(importacion);
        importacion.setVisible(true);
        importacion.toFront();
        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        Etiquetador etiq=new Etiquetador();
        Inicio.jDesktopPane1.add(etiq);
        etiq.setVisible(true);
        etiq.toFront();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        PagosAbm pago=new PagosAbm();
        Inicio.jDesktopPane1.add(pago);
        pago.setVisible(true);
        pago.toFront();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        Configuracion configuracion=new Configuracion();
        Inicio.jDesktopPane1.add(configuracion);
        configuracion.setVisible(true);
        configuracion.toFront();
        try {
            configuracion.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        Citi.pantallasCiti.EmisorDeInformes citi=new Citi.pantallasCiti.EmisorDeInformes();
        Inicio.jDesktopPane1.add(citi);
        citi.setVisible(true);
        citi.toFront();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked
        Runtime jpfBatch=Runtime.getRuntime();
        try {
            jpfBatch.exec("java -jar Web/Web.jar");
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu7MouseClicked

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked
        try {
            String url="manual\\ayuda.html";//JOptionPane.showInputDialog("Ingrese destino");//"manual\\ayuda.html";
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu8MouseClicked

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        ListasDePreciosAbm lst=new ListasDePreciosAbm();
        Inicio.jDesktopPane1.add(lst);
        lst.setVisible(true);
        lst.toFront();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }
    public void permisos(Integer nivel){
        jMenu1.setEnabled(Inicio.usuario.getMenu().getMenu1());
       jMenu2.setEnabled(Inicio.usuario.getMenu().getMenu2());
       jMenu3.setEnabled(Inicio.usuario.getMenu().getMenu3());
       jMenu4.setEnabled(Inicio.usuario.getMenu().getMenu4()); 
       //jMenu5.setEnabled(Inicio.usuario.getMenu().getMenu5());
       this.jMenuItem2.setEnabled(true);
       Cajeables caj=new Cajas();
       if(caj.VerificarCaja(usuario.getNumero(),sucursal.getNumero(),fechaDia)){
           this.caja=(Cajas)caj.CargarCaja(usuario.getNumero(),sucursal.getNumero(),fechaDia);
       }else{
       this.caja=new Cajas(1);
       Double saldo=Double.parseDouble(JOptionPane.showInputDialog("Ingrese Saldo Inicial","0.00"));
       //System.out.println("SALDO INGRESADO "+saldo);
       this.caja.setSaldoInicial(saldo);
       
       this.caja=(Cajas) caj.AbrirCaja(caja);
       }
       this.sucursal.setCaja(caja);
       this.jMenuItem5.setEnabled(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane1;
    public static javax.swing.JMenu jMenu1;
    public static javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
