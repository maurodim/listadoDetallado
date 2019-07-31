/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.pantallas;

import Administracion.Licencias;
import Administracion.LicenciasControl;
import ClientesPantallas.NuevoCliente;
import Pedidos.IngresoDePedidos;
import Conversores.Numeros;
import facturacion.clientes.Clientes;

import interfaceGraficasManantial.Inicio;
import interfacesPrograma.Facturar;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Articulos.Articulos;
import Articulos.Rubrable;
import Articulos.Rubros;
import Articulos.SubRubros;
import Articulos.TablaGenericaProductos;
import ConfiguracionR.Propiedades;
import ListasDePrecios.Articulable;
import ListasDePrecios.ArticulosAsignados;
import facturacion.clientes.Facturable;
import facturacion.clientes.MovimientosClientes;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import objetosR.Comprobantes;
import objetosR.Conecciones;
import tablas.MiModeloTablaFacturacion;
import Articulos.ModificableArticulos;
import FacturaElectronica.Controlador.TiposIvaControl;
import FacturaElectronica.Interfaces.FacturableE;
import FacturaElectronica.Objetos.DetalleFacturas;
import FacturaElectronica.Objetos.FacturaElectronica;
import FacturaElectronica.Objetos.TiposIva;
import facturacion.clientes.FormasDePago;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author hernan
 */
public class NotaDeCredito extends javax.swing.JInternalFrame {

    /**
     * Creates new form IngresoDePedidos
     */
    public static Clientes cliT;
    private ArrayList detalleDelPedido=new ArrayList();
    private Articulos arti;
    private static ArrayList listadoDeBusqueda=new ArrayList();
    private static Double montoTotal=0.00;
    private static Comprobantes comp=new Comprobantes();
    private MovimientosClientes factura;
    private Rubros rubro=new Rubros();
    private SubRubros subRubro;
    private ArrayList listadoSubRubros;
    private Rubrable ruble=new Rubros();
    private ArrayList listadoR=new ArrayList();
    private DefaultComboBoxModel combox=new DefaultComboBoxModel();
    private TableColumn columnaCodigo;
    private String valorCargado;
    private Double porcentajeDescuento;
    private Double subTotal;
    private TablaGenericaProductos tgp;
    private Double montoDescuento;
    private ArrayList listadoFormas;
    private FormasDePago formas;
    private ArrayList listadoIva;
    private ArrayList listadoIvaD;
    private Double montoIva;
    private TiposIvaControl control;
    private TiposIva tipoIva;
    
    public NotaDeCredito() {
        //Articulos.CargarMap();
        cliT=new Clientes("1");
        //cliT=(ClientesTango)oob;
        //comp.setCliente(cliT);
        initComponents();
        tgp = new TablaGenericaProductos();
        listadoFormas=new ArrayList();
        porcentajeDescuento=0.00;
        montoDescuento=0.00;
        subTotal=0.00;
        this.jCheckBox2.setEnabled(true);
        this.jCheckBox2.isSelected();
        this.jLabel6.setText(cliT.getRazonSocial());
        this.jLabel7.setVisible(false);
        this.jTextField4.setVisible(false);
        this.jCheckBox1.setVisible(false);
        this.jCheckBox2.setEnabled(false);
        listadoIva = new ArrayList();
        control = new TiposIvaControl();
        listadoIva = control.ListarTipos();
        //this.jTextField5.requestFocus();
        //this.jPanel2.requestFocus();
        
    }
    public NotaDeCredito(Object ped){
        factura=new MovimientosClientes();
        //Pedidos pedido=new Pedidos();
        porcentajeDescuento=0.00;
        subTotal=0.00;
        ArrayList listadoPed=new ArrayList();
        factura=(MovimientosClientes)ped;
        Facturar fact=new Clientes();
        //Pedable peda=new Pedidos();
        //DetallePedidos detallePedido=new DetallePedidos();
        Facturable detP=new facturacion.clientes.DetalleFacturas();
        //factura.setIdPedido(pedido.getId());
        cliT=new Clientes();
        //ListasDePrecios lista=new ListasDePrecios(cliT.getListaDePrecios());
        cliT=(Clientes)fact.cargarPorCodigoAsignadoFacturacion(factura.getIdCliente(),cliT.getCoeficienteListaDeprecios());
        listadoPed=detP.cargarDetallefactura(factura.getId());
        detalleDelPedido=detP.convertirAArticulos(listadoPed);
        
//cliT=(ClientesTango)oob;
        //comp.setCliente(cliT);
        initComponents();
        listadoFormas=new ArrayList();
        //porcentajeDescuento=pedido.getPorcentajeDescuento();
        subTotal=0.00;
        Iterator irP=detalleDelPedido.listIterator();
        int fil=0;
        ArrayList paraEliminar=new ArrayList();
        Articulos arrrt;
        while(irP.hasNext()){
            arrrt=new Articulos();
            arrrt=(Articulos) irP.next();
            //fila[0]=pedidos.getCodigo();
            if(arrrt.getNumeroId()==0){
               paraEliminar.add(fil);
            }
            fil++;
        }
        if(paraEliminar.size() > 0){
            Iterator iEl=paraEliminar.listIterator();
            int pos=0;
            while(iEl.hasNext()){
                pos=(Integer)iEl.next();
                detalleDelPedido.remove(pos);
            }
        }
        
        agregarRenglonTabla();
        this.jButton3.setVisible(false);
        this.jButton5.setVisible(false);
        //this.jCheckBox2.isSelected();
        this.jLabel6.setText(cliT.getRazonSocial());
        this.jLabel7.setVisible(false);
        this.jTextField4.setVisible(false);
        this.jCheckBox1.setVisible(false);
        //this.jCheckBox2.setEnabled(false);
        this.jTextField1.requestFocus();
        listadoIva = new ArrayList();
        control = new TiposIvaControl();
        listadoIva = control.ListarTipos();
        //this.jPanel2.requestFocus();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MiModeloTablaFacturacion facturas=new MiModeloTablaFacturacion();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Emisión de NOTA DE CRÉDITO");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Mlogo.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Total Factura"));

        jTable1.setModel(facturas);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("TOTAL FACTURA :");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/impresora.png"))); // NOI18N
        jButton1.setText("FACT ELECT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/eliminar_des.png"))); // NOI18N
        jButton2.setText("Eliminar Item");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField3.setText("0");
        jTextField3.setToolTipText("Presione Enter para aplicar descuento general");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel5.setText("%DESCUENTO");
        jLabel5.setEnabled(false);

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("PAGADO");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/cierreCaja.png"))); // NOI18N
        jButton6.setText("Modificar Precio");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6)))
                    .addComponent(jScrollPane1))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2))
                    .addComponent(jButton1))
                .addGap(12, 12, 12))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(507, 207));

        jLabel3.setText("Descripcion (F1 Busca)");

        jLabel4.setText("CANTIDAD :");

        jTextField1.requestFocus();
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField2.setPreferredSize(new java.awt.Dimension(40, 20));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel6.setText("<HTML><strong>jLabel6</strong></html>");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/cliente_seleccion.png"))); // NOI18N
        jButton3.setText("Ingresar Cliente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/clientes_nuevo.png"))); // NOI18N
        jButton5.setText("Nuevo Cliente");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setText("PRECIO :");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("iNCLUYE SERVICIO ?");
        jCheckBox1.setEnabled(false);
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jLabel12.setText("<html>PRESIONE F1 PARA CONSULTAR POR DESCRIPCION<br> PRESIONE F3 PARA SELECCIONAR FORMA DE PAGO<br> PRESION F7 PARA CARGAR CANTIDAD </html>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(536, 536, 536))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(38, 38, 38)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1136, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //System.out.println("ENTRO CON EL ENTER¡¡¡¡¡¡");
            listadoDeBusqueda.clear();
            Facturar fart=new Articulos();
            arti=new Articulos();
            arti=(Articulos)fart.cargarPorCodigoDeBarraFacturacion(jTextField1.getText(),cliT.getCoeficienteListaDeprecios());
            if(arti.getCodigoDeBarra().equals("")){
                
             jTextField1.setText("");   
            }else{
            listadoDeBusqueda.add(arti);
            //jTextField1.setText(arti.getCodigoAsignado());
            jTextField2.setText("1");
            this.jLabel8.setText(arti.getDescripcionArticulo());
            if(arti.getModificaPrecio()){
                this.jLabel7.setVisible(true);
                this.jTextField4.setVisible(true);
                this.jTextField4.setText(String.valueOf(arti.getPrecioUnitarioNeto()));
                //this.jTextField4.setEnabled(true);
               // this.jCheckBox1.setVisible(false);
                
            }else{
            
                this.jLabel7.setVisible(false);
                this.jTextField4.setVisible(false);
                

                if(arti.getPrecioServicio() > 0){
                    this.jLabel7.setVisible(true);
                    this.jTextField4.setVisible(true);
                    
                    this.jTextField4.setText(Numeros.ConvertirNumero(arti.getPrecioServicio()));
                    //this.jTextField4.setEnabled(false);
                    this.jCheckBox1.setVisible(true);
                    Calendar calendario=new GregorianCalendar();
                    int hora=calendario.get(Calendar.HOUR_OF_DAY);
                    //System.out.println("LA HORA ACTUAL ES :"+hora);
                    if(hora >= 0 || hora < 8){
                        if(arti.getModificaServicio()){
                         //System.err.println("SI TIENE QUE MODIFICAR EL SERVICIO");  
                         this.jCheckBox1.setEnabled(false);
                        }else{
                        //System.err.println("NO DEBE MODIFICAR EL SERVICIO");
                            this.jCheckBox1.setEnabled(true);
                        }
                        }
                }
            }
            
            if(cliT.getCondicionDeVenta()==2)this.jCheckBox2.setEnabled(true);
            CargarCantidad();
            //this.jTextField2.selectAll();
            //this.jTextField2.requestFocus();
            }
        }
        if(evt.getKeyCode()==KeyEvent.VK_F1){
            valorCargado=jTextField1.getText();
        Facturar fart=new Articulos();
       // this.jTable2.removeAll();
            ModificableArticulos modiA=new Articulos();
            Articulable modi=new ArticulosAsignados();
            listadoDeBusqueda.clear();
            listadoDeBusqueda=fart.listadoBusquedaFacturacion(this.jTextField1.getText(),cliT.getCoeficienteListaDeprecios());
            //listadoDeBusqueda=modi.filtrador(listadoSubRubros,listadoR);
//            this.jTable2.setModel(modiA.mostrarListadoBusqueda(listadoDeBusqueda));
//            columnaCodigo = this.jTable2.getColumn("Precio");
//            columnaCodigo.setPreferredWidth(60);
//            columnaCodigo.setMaxWidth(60);
//            columnaCodigo = this.jTable2.getColumn("Stock");
//            columnaCodigo.setPreferredWidth(60);
//            columnaCodigo.setMaxWidth(60);
//            this.jTable2.requestFocus();
            
            // Configurando parametros de algunas columnas de interes
            List<String> columnasTabla = new ArrayList<>();
            columnasTabla.add("Código:100:100");
            columnasTabla.add("Descripcion:600:600");
            columnasTabla.add("Stock:60:60");

            // Desplegando ventana emergente
            int elementoSeleccionado = tgp.desplegarPopUp("Seleccion Item", modiA.mostrarListadoBusqueda(listadoDeBusqueda), columnasTabla);
            arti = (Articulos) listadoDeBusqueda.get(elementoSeleccionado);
            jTextField1.setText(arti.getCodigoDeBarra());

        }
        
        if(evt.getKeyCode()==KeyEvent.VK_F3){
            SelectorFormaDePago slector=new SelectorFormaDePago(null,true,montoTotal);
            slector.monto_txt.setText(String.valueOf(montoTotal));
            slector.monto_txt.selectAll();
            slector.jComboBox1.requestFocus();
            slector.setVisible(true);
            
            if(slector.saldo > montoTotal){
                
                double monto=slector.saldo - montoTotal;
                String descripcion=JOptionPane.showInputDialog("Ingrese descripción del recargo ","");
                Articulos pedidos=new Articulos();
        pedidos.setNumeroId(0);
        pedidos.setCantidad(1.00);
        pedidos.setPrecioUnitarioNeto(monto);
        pedidos.setPrecioDeCosto(0.00);
        pedidos.setDescripcionArticulo(descripcion.toUpperCase());
        pedidos.setCodigoAsignado(String.valueOf(0));
        pedidos.setIdCombo(0);
        detalleDelPedido.add(pedidos);
        agregarRenglonTabla();
        montrarMonto();
                
            }
            listadoFormas=slector.listadoPagos;
        }
        if (evt.getKeyCode() == KeyEvent.VK_F7) {
            //System.out.println("ENTRO CON EL ENTER¡¡¡¡¡¡");
            if (jTextField1.getText().equals("")) {
                CargarCantidad carga = new CargarCantidad(null, true);
                carga.setCoefi(cliT.getCoeficienteListaDeprecios());
                carga.setVisible(true);
                arti = carga.arti;
            } else {
                listadoDeBusqueda.clear();
                Facturar fart = new Articulos();
                arti = new Articulos();
                arti = (Articulos) fart.cargarPorCodigoDeBarraFacturacion(jTextField1.getText(), cliT.getCoeficienteListaDeprecios());
            }
            /*
            listadoDeBusqueda.clear();
            Facturar fart = new Articulos();
            arti = new Articulos();
            arti = (Articulos) fart.cargarPorCodigoDeBarra(jTextField1.getText());
            */
            if (arti.getCodigoDeBarra().equals("")) {
                
                jTextField1.setText("");
            } else {
                listadoDeBusqueda.add(arti);
                jTextField1.setText(arti.getCodigoAsignado());
                jTextField2.setText("1");
                this.jLabel8.setText(arti.getDescripcionArticulo());
                if (arti.getModificaPrecio()) {
                    this.jLabel7.setVisible(true);
                    this.jTextField4.setVisible(true);
                    this.jTextField4.setText(String.valueOf(arti.getPrecioUnitarioNeto()));
                    //this.jTextField4.setEnabled(true);
                    // this.jCheckBox1.setVisible(false);

                } else {
                    
                    this.jLabel7.setVisible(false);
                    this.jTextField4.setVisible(false);
                    
                }
                
                if (cliT.getCondicionDeVenta() == 2) {
                    this.jCheckBox2.setEnabled(true);
                }
                
                //CARGAR ARTICULO AUTOMATICAMENTE
                //CargarCantidad();
                this.jTextField2.selectAll();
                this.jTextField2.requestFocus();
                
            
                
        }
                
            
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Double cantt=Double.parseDouble(this.jTextField2.getText());
            cantt=cantt * -1;
            Double precioUni=0.00;
            if(cantt < 1000){
            if(arti.getModificaPrecio()){
                this.jTextField4.selectAll();
                this.jTextField4.requestFocus();
            }else{
                if(arti.getPrecioServicio()>0){
                 this.jTextField4.requestFocus();   
                }else{
                    Articulos articul=new Articulos();
                    articul.setCantidad(cantt);
                    articul.setCodigoAsignado(arti.getCodigoAsignado());
                    
                    articul.setCodigoDeBarra(arti.getCodigoDeBarra());
                    articul.setDescripcionArticulo(arti.getDescripcionArticulo());
                    articul.setNumeroId(arti.getNumeroId());
                    articul.setPrecioDeCosto(arti.getPrecioDeCosto());
                    articul.setPrecioUnitario(arti.getPrecioUnitarioNeto());
                    articul.setPrecioUnitarioNeto(arti.getPrecioUnitarioNeto());
                    articul.setIdCombo(arti.getIdCombo());
                    articul.setCombo(arti.getCombo());
            detalleDelPedido.add(articul);
            agregarRenglonTabla();
//                Double montoTotalX=(arti.getPrecioUnitario() * arti.getCantidad());
//                montoTotal=montoTotal + montoTotalX;
                 montrarMonto();
                 //System.err.println("MONTO TOTAL "+montoTotal);
                 this.jLabel8.setText("");
       //          this.jTable2.removeAll();
                this.jButton1.setVisible(true);
            jTextField1.setText(valorCargado);
           // this.jTextField5.selectAll();
            this.jTextField2.setText("");
            //this.jTextField5.requestFocus();
                }
                }
            }else{
                JOptionPane.showMessageDialog(this,"LA CANTIDAD INGRESADA ES EXCESIVA, POR FAVOR VERIFÍQUELA");
            }
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        detalleDelPedido.clear();
        listadoDeBusqueda.clear();
        montoTotal=0.00;
    }//GEN-LAST:event_formComponentHidden

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        detalleDelPedido.clear();
        listadoDeBusqueda.clear();
        montoTotal=0.00;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LicenciasControl control1=new LicenciasControl();
            Licencias lice=(Licencias) control1.LeerActualLocal(Propiedades.getIDLICENCIA());
            Boolean correcto=true;
            if(lice.getActualFc()> 0){
        
        String cadena=cliT.getCodigoCliente()+" - "+cliT.getRazonSocial()+"\n"+cliT.getDireccion();
        int ok=0;
        
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
        String fecha=dia+"/"+mes+"/"+ano;
        String fecha2=ano+"-"+mes+"-"+dia;
        int comprobanteTipo=8;
        if(cliT.getTipoIva()==1)comprobanteTipo=3;
        if(cliT.getTipoIva()==5){
            if(montoTotal > 1000){
             if(cliT.getRazonSocial().equals("CONSUMIDOR FINAL") || cliT.getNumeroDeCuit().equals("0"))   {
                 cliT.setRazonSocial(JOptionPane.showInputDialog("Ingrese el nombre del cliente",cliT.getRazonSocial()));
                 cliT.setNumeroDeCuit(JOptionPane.showInputDialog("Ingrese el numero de documento del cliente",cliT.getNumeroDeCuit()));
             }
             comprobanteTipo=8;
            }else{
            comprobanteTipo=8;
            }
        }
        
        if(cliT.getTipoIva()==4)comprobanteTipo=8;
        if(Propiedades.getCONDICIONIVA().equals("1")){
            }else{
                comprobanteTipo=13;
            }
        Comprobantes comprobante=new Comprobantes();
        comprobante.setFe(true);
        comprobante.setImpacta(true);
        comprobante.setCliente(cliT);
        comprobante.setTipoMovimiento(1);
        comprobante.setTipoComprobante(comprobanteTipo);
        comprobante.setFechaEmision((Date.valueOf(fecha2)));
        comprobante.setListadoDeArticulos(detalleDelPedido);
        comprobante.setUsuarioGenerador(Inicio.usuario.getNumero());
        comprobante.setIdSucursal(Inicio.sucursal.getNumero());
        comprobante.setIdDeposito(Inicio.deposito.getNumero());
        Integer numeroCaja=Inicio.caja.getNumero();
        comprobante.setIdCaja(numeroCaja);
        if(montoTotal == 0.00){
            String sqM="usuario :"+Inicio.usuario.getNombre()+" sucursal "+Inicio.sucursal.getNumero()+" idcaja "+Inicio.caja.getNumero();
            JOptionPane.showMessageDialog(this,"OJO EL MONTO DE ESTE COMPROBANTE ES $ 0, AVISE PARA DETECTAR EL ERROR");
            FileWriter fichero=null;
            PrintWriter pw=null;
            try {
                fichero = new FileWriter("C:\\Gestion\\"+Inicio.fechaDia+" - errores en comprobantes.txt",true);
                pw=new PrintWriter(fichero);
                pw.println(sqM);
            } catch (IOException ex1) {
                Logger.getLogger(NotaDeCredito.class.getName()).log(Level.SEVERE, null, ex1);
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
        }
        //montoTotal=montoTo;
            montoTotal=Math.round(montoTotal * 100.0) / 100.0;
            //subTotal=montoTotal / 1.21;
            //Double ivv=subTotal / 1.21;
            subTotal=Math.round(subTotal * 100.0) / 100.0;
            //Double ivv=Math.round(ivv * 100.0) / 100.0;
            Double sub=0.00;
            Double tot=montoTotal - subTotal;
            tot=Math.round(tot * 100.0) /100.0;
            porcentajeDescuento=0.00;
            /*
            if(porcentajeDescuento > 0.00){
                sub = subTotal * porcentajeDescuento;
                sub= montoTotal - sub;
            }else{
                sub=montoTotal;
            }
            */
            comprobante.setMontoTotal(montoTotal);
            comprobante.setSubTotal(subTotal);
            comprobante.setMontoIva(montoIva);
            comprobante.setMontoBruto(subTotal);
            Double descuen=0.00;//montoTotal - sub;
            comprobante.setDescuento(descuen);
            comprobante.setPorcentajeDescuento(porcentajeDescuento);
        int noFacturar=0;
        if(this.jCheckBox2.isSelected()){
            comprobante.setPagado(1);
        }else{
            comprobante.setPagado(0);
            /*
            * ACA DEBO COMPROBAR EL LIMITE DEL CLIENTE Y SI LO SUPERA LA COMPRA RECHAZAR LA VENTA
            *
            */
           
            
        }
        if(noFacturar==0){
            comprobante.setFiscal(1);
                Facturar fat=new Comprobantes();
                comprobante=(Comprobantes) fat.guardar(comprobante);
                // aqui hago el envio a factura  electronica, si aprueba no imprime
                
                 if(subTotal < 0)subTotal=subTotal * (-1);
                if(montoTotal < 0)montoTotal =montoTotal * (-1);
                if(montoIva < 0)montoIva=montoIva * (-1);
                
                FacturaElectronica fe=new FacturaElectronica();
                FacturableE fact=new FacturaElectronica();
                //ArrayList listadoIva=new ArrayList();
                //Double montoIva=0.00;
                
                int tipoComp=comprobante.getTipoComprobante();
                if(Propiedades.getCONDICIONIVA().equals("1")){
                    if(cliT.getTipoIva()==1){
                        tipoComp=3;
                    }else{
                        tipoComp=8;
                    }
                }else{
                    tipoComp=13;
                }
                if(tipoComp < 13){
                    Iterator it = listadoIva.listIterator();
                        listadoIvaD = new ArrayList();
                        while (it.hasNext()) {
                            tipoIva = (TiposIva) it.next();
                            if (tipoIva.getBaseImponible() == 0.00) {
                                //sell.add(posicionL);
                            } else {
                                /*
                                float subT = Float.parseFloat(formato1.format(tipoIva.getBaseImponible()));

                                float totT = Float.parseFloat(formato1.format(tipoIva.getImporte()));
                                tipoIva.setBaseImponible(subT);
                                tipoIva.setImporte(totT);
                                */
                                listadoIvaD.add(tipoIva);
                            }
                            //posicionL++;
                        }

                        //montoIva = tot;
                    } else {
                        listadoIvaD = null;
                        subTotal=montoTotal;
                    }
                ArrayList listadoTrib=null;
                ArrayList <DetalleFacturas> listadoDetalle=new ArrayList();
                Iterator itD=detalleDelPedido.listIterator();
                Articulos artic;
                DetalleFacturas detalle;
                double precio=0.00;
                 TiposIva alicuota;
                while(itD.hasNext()){
                    artic=(Articulos) itD.next();
                    detalle=new DetalleFacturas();
                    detalle.setCodigo(artic.getCodigoAsignado());
                    detalle.setDescripcion(artic.getDescripcionArticulo());
                    detalle.setCantidadS(String.valueOf(artic.getCantidad() * -1));
                    alicuota=(TiposIva) control.CargarIva(artic.getTipoIva());
                        detalle.setAlicuota(alicuota.getAlicuota()+"%");
                        precio = Math.round(artic.getPrecioUnitarioNeto() * 100.0) / 100.0;
                        
                        detalle.setPrecioUnitarioS(String.valueOf(precio));
                        precio=Math.round(artic.getSubTotal() * 100.0) / 100.0;
                        detalle.setPrecioGravadoArticulo(precio);
                    listadoDetalle.add(detalle);
                }
                //montoIva=tot;
                System.out.println(Propiedades.getARCHIVOCRT());
                int condicion=1;//Integer.parseInt(Propiedades.getCONDICIONIVA());
                int ptoVta=Integer.parseInt(Propiedades.getPUNTODEVENTA());
                int tipoVta=Integer.parseInt(Propiedades.getTIPODEVENTA());
                Integer idPed=1;
               
                System.out.println("totales: $"+subTotal+" _ $"+montoTotal+" _ $"+montoIva);
                
                //if(pedido.getId() != null)idPed=pedido.getId();
                
                //System.out.println("COMPROBANTE FISCAL N° "+fact.generar(null, condicion, Propiedades.getARCHIVOKEY(),Propiedades.getARCHIVOCRT(),cliT.getCodigoId(), cliT.getNumeroDeCuit(), comprobante.getTipoComprobante(), montoTotal, subTotal, montoIva, ptoVta, Propiedades.getCUIT(), tipoVta, listadoIva, listadoTrib, cliT.getRazonSocial(), cliT.getDireccion(), cliT.getCondicionIva(), listadoDetalle,idPed));
                
                comprobante.setTipoComprobante(tipoComp);
                /*
                if(comprobante.getTipoComprobante()==1){
                    tipoComp=6;
                }
                if(comprobante.getTipoComprobante()==2){
                    tipoComp=1;
                }
                if(comprobante.getTipoComprobante()==9){
                    tipoComp=2;
                }
                if(comprobante.getTipoComprobante()==10){
                    tipoComp=3;
                }
                if(comprobante.getTipoComprobante()==12){
                    tipoComp=8;
                }
                if(comprobante.getTipoComprobante()==8){
                    tipoComp=8;
                }
                if(comprobante.getTipoComprobante()==3){
                    tipoComp=6;
                }
                */
                Conecciones conx;
            try {
                conx = new Conecciones();
                Connection conexion=conx.obtenerConexion();
                FormasDePago formaP = new FormasDePago();
                        if(listadoFormas.size() > 0){
                        formaP=(FormasDePago) listadoFormas.get(0);
                        }else{
                           formaP=(FormasDePago) formaP.CargarForma(1);
                        }
                String numeFc;
        numeFc=fact.generar(conexion, condicion, Propiedades.getARCHIVOKEY(), Propiedades.getARCHIVOCRT(), cliT.getCodigoId(), cliT.getNumeroDeCuit(), tipoComp, montoTotal, subTotal, montoIva, ptoVta, Propiedades.getCUIT(), tipoVta, listadoIvaD, listadoTrib, cliT.getRazonSocial(), cliT.getDireccion(), String.valueOf(cliT.getTipoIva()), listadoDetalle,idPed,Propiedades.getNOMBRECOMERCIO(),Propiedades.getRAZONSOCIAL(),"resp inscripto",Propiedades.getDIRECCION(),Propiedades.getTELEFONO(),Propiedades.getINGBRUTOS(),Propiedades.getINICIOACT(),cliT.getEmail(),formaP.getNumeroFormaDePago());
        
        System.out.println("numero devuelto " + numeFc);
                        if (numeFc != null) {
                            try {
                                Integer fcNum = Integer.parseInt(numeFc);
                                comprobante.GuardarNumeroFiscalEnCaja(fcNum, comprobante.getNumeroRegistro(), tipoComp);
                                LicenciasControl licencia = new LicenciasControl();
                                licencia.RestarFc();
                            } catch (java.lang.NumberFormatException exx) {
                                System.out.println("comprobante a eliminar " + comprobante.getNumero() + " tipo " + comprobante.getTipoComprobante());
                                fat.eliminarComprobante(comprobante.getNumero(), comprobante.getTipoComprobante());
                                correcto = false;
                            }
                        } else {
                            System.out.println("comprobante a eliminar " + comprobante.getNumero() + " tipo " + comprobante.getTipoComprobante());
                        }
            } catch (InstantiationException ex) {
                Logger.getLogger(NotaDeCredito.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(NotaDeCredito.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(NotaDeCredito.class.getName()).log(Level.SEVERE, null, ex);
            }
        
                
                
                
                /*
                FacturaElectronica fe=new FacturaElectronica();
                try {

                    fe=(FacturaElectronica) fe.leer(comprobante);
                    if(fe.getRespuesta().equals("OK")){
                        //JOptionPane.showMessageDialog(this,"aprobada id: "+fe.getId());
                        pdfsJavaGenerador pdf=new pdfsJavaGenerador();
                        pdf.setDoc(fe);
                        pdf.setCliente(cliT);
                        pdf.run();
                        

                    }else{
                        if(fe.getRespuesta().equals("PARAMETROS"))JOptionPane.showMessageDialog(this,"Error en los parametros del cliente, modifiquelos en cae pendientes");
                        JOptionPane.showMessageDialog(this,"error en la coneccion, intentelo mas tarde");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(IngresoDePedidos.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println(ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(IngresoDePedidos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(IngresoDePedidos.class.getName()).log(Level.SEVERE, null, ex);
                }
                */
                /*
                * ACA DEBO LIMPIAR TODOS LOS CAMPOS Y VARIABLES DE LA PANTALLA
                *
                */
                //comp.setTipoComprobante(comprobanteTipo);
                //comp.setMontoTotal(montoTotal);
                if(correcto){
                detalleDelPedido.clear();
                agregarRenglonTabla();
                this.jCheckBox2.setSelected(true);
                //this.jCheckBox2.setEnabled(false);
                //this.jTable2.removeAll();
                listadoDeBusqueda.clear();
                //cargarLista(listadoDeBusqueda);
                //cliT=new Clientes("99");
                this.jLabel6.setText(cliT.getRazonSocial());
                this.jTextField2.setText("");
                jTextField1.setText("");
                jTextField1.requestFocus();
                }
        
        }else{
            JOptionPane.showMessageDialog(this,"El cliente supera el límite de crédito, debe abonar la venta");
            noFacturar=0;
            ok=1;
        }
        
            }else{
                JOptionPane.showMessageDialog(null,"NO PUEDE GENERAR MAS COMPROBANTES. POR FAVOR RENUEVE LA LICENCIA.GRACIAS");
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int posicion=this.jTable1.getSelectedRow();
        detalleDelPedido.remove(posicion);
        //detalleDelPedido.clear();
        agregarRenglonTabla();
        jTextField1.setText("");
        jTextField1.requestFocus();
        //listadoDeBusqueda.clear();
        //montoTotal=0.00;        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SeleccionDeClientes1 selCli=new SeleccionDeClientes1(2);
        Inicio.jDesktopPane1.add(selCli);
        selCli.setVisible(true);
        selCli.toFront();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        jTextField1.requestFocus();
    }//GEN-LAST:event_formComponentShown

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        NuevoCliente nCli=new NuevoCliente(2);
        Inicio.jDesktopPane1.add(nCli);
        nCli.setVisible(true);
        nCli.toFront();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Double servicio;
            //Articulos articuloss=new Articulos();
            if(this.jCheckBox1.isSelected()){
                servicio=arti.getPrecioServicio();
            }else{
                servicio=0.00;
            }
            if(arti.getModificaPrecio())servicio=Numeros.ConvertirStringADouble(String.valueOf(this.jTextField4.getText()));
            Double tota=servicio;
            //arti.setPrecioUnitarioNeto(tota);
            //arti.setPrecioServicio(servicio);
            Double cantt=Double.parseDouble(this.jTextField2.getText());
            Articulos articul=new Articulos();
                    articul.setCantidad(cantt);
                    articul.setCodigoAsignado(arti.getCodigoAsignado());
                    articul.setPrecioServicio(servicio);
                    articul.setCodigoDeBarra(arti.getCodigoDeBarra());
                    articul.setDescripcionArticulo(arti.getDescripcionArticulo());
                    articul.setNumeroId(arti.getNumeroId());
                    articul.setPrecioDeCosto(arti.getPrecioDeCosto());
                    articul.setPrecioUnitario(arti.getPrecioUnitarioNeto());
                    articul.setPrecioUnitarioNeto(tota);
                    articul.setModificaPrecio(arti.getModificaPrecio());
                    articul.setIdCombo(arti.getIdCombo());
                    articul.setCombo(arti.getCombo());
                    articul.setSubTotal(arti.getSubTotal());
            articul.setIva(arti.getIva());
            articul.setCoeficienteIva(arti.getCoeficienteIva());
            articul.setTipoIva(arti.getTipoIva());
                        detalleDelPedido.add(articul);
            agregarRenglonTabla();
//                Double montoTotalX=(arti.getPrecioUnitario() * arti.getCantidad());
//                montoTotal=montoTotal + montoTotalX;
                 montrarMonto();
                 //System.err.println("MONTO TOTAL "+montoTotal);
                 this.jLabel8.setText("");
                 //this.jTable2.removeAll();
                this.jButton1.setVisible(true);
            this.jTextField1.setText("");
            this.jTextField2.setText("");
            this.jTextField1.requestFocus();
            this.jLabel7.setVisible(false);
            this.jTextField4.setVisible(false);
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
       this.jTextField4.requestFocus();
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    //MODIFICAR PRECIO
      int posicion=this.jTable1.getSelectedRow();
      Articulos pedidos;
        pedidos=(Articulos)detalleDelPedido.get(posicion);
        Double precio=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo valor unitario s/iva",pedidos.getPrecioUnitarioNeto()));
        pedidos.setPrecioUnitarioNeto(precio);
//detalleDelPedido.clear();
        agregarRenglonTabla();
        System.out.println("total "+montoTotal);
        montrarMonto();
        jTextField1.setText("");
        jTextField1.requestFocus();
          
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(KeyEvent.VK_ENTER==evt.getKeyCode()){
            Double descuentoGral=Numeros.ConvertirStringADouble(this.jTextField3.getText());
            descuentoGral=descuentoGral / 100;
            porcentajeDescuento=descuentoGral;
            //cargarLista(detalleDelPedido);
            montrarMonto();
            agregarRenglonTabla();
            jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jTextField3KeyPressed
private void cargarLista(ArrayList lista){
    DefaultTableModel modelo=new DefaultTableModel();
    Iterator il=lista.listIterator();
    Articulos art=new Articulos();
    modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        Object [] fila=new Object[3];
        while(il.hasNext()){
            art=(Articulos)il.next();
            fila[0]=art.getDescripcionArticulo();
            fila[1]=" $"+Numeros.ConvertirNumero(art.getPrecioUnitarioNeto());
            fila[2]=String.valueOf(art.getStockActual());
            //modelo.addElement(articulo.getDescripcionArticulo()+" $"+Numeros.ConvertirNumero(articulo.getPrecioUnitarioNeto()));
            modelo.addRow(fila);
        }
    
    
//    this.jTable2.setModel(modelo);
//    columnaCodigo = this.jTable2.getColumn("Precio");
//    columnaCodigo.setPreferredWidth(60);
//    columnaCodigo.setMaxWidth(60);
//    columnaCodigo = this.jTable2.getColumn("Stock");
//    columnaCodigo.setPreferredWidth(60);
//    columnaCodigo.setMaxWidth(60);
        
        // Configurando parametros de algunas columnas de interes
        List<String> columnasTabla = new ArrayList<>();
        columnasTabla.add("Precio:60:60");
        columnasTabla.add("Stock:60:60");

        // Desplegando ventana emergente
        tgp.desplegarPopUp("Seleccion Item", modelo, columnasTabla);
    
        
        
        
}
private void agregarRenglonTabla(){
        MiModeloTablaFacturacion busC=new MiModeloTablaFacturacion();
        this.jTable1.removeAll();
        montoTotal = 0.00;
        subTotal = 0.00;
        montoIva = 0.00;
        listadoIva=null;
        listadoIva=control.ListarTipos();
        //ArrayList listadoPedidos=new ArrayList();
        this.jTable1.setModel(busC);
        Articulos pedidos;
        busC.addColumn("CODIGO");
        busC.addColumn("DESCRIPCION");
        busC.addColumn("COSTO");
        busC.addColumn("PRECIO UNITARIO S/IVA");
        busC.addColumn("CANTIDAD");
        busC.addColumn("PRECIO TOTAL");
        busC.addColumn("IVA");
        busC.addColumn("PRECIO FINAL");
        Object[] fila=new Object[8];
        Iterator irP=detalleDelPedido.listIterator();
        while(irP.hasNext()){
            pedidos=new Articulos();
            pedidos=(Articulos) irP.next();
            //fila[0]=pedidos.getCodigo();
            String codig=pedidos.getCodigoAsignado();
            String desc=pedidos.getDescripcionArticulo();
            String cant=String.valueOf(pedidos.getCantidad());
            
            fila[0]=codig;
            fila[1]=desc;
            Double precioUnitario=pedidos.getPrecioUnitarioNeto();
            
            //precioUnitario=precioUnitario * cliT.getCoeficienteListaDeprecios();
            
            Double valor=precioUnitario * pedidos.getCantidad();
            //precioUnitario= pedidos.getPrecioUnitario() * cliT.getCoeficienteListaDeprecios();
            //Double valor=(pedidos.getCantidad() * precioUnitario);
            //valor=valor * cliT.getCoeficienteListaDeprecios();
            pedidos.setPrecioUnitario(valor);
            String val=Numeros.ConvertirNumero(valor);
            montoTotal = montoTotal + valor;
            Double subb = Numeros.CalcularSubTotal(valor, pedidos.getCoeficienteIva());
            subTotal = subTotal + subb;
            double ivaa = Numeros.CalcularIva(subb, pedidos.getCoeficienteIva());
            montoIva = montoIva + ivaa;
            Iterator it = listadoIva.listIterator();
            while (it.hasNext()) {
                tipoIva = (TiposIva) it.next();
                if (tipoIva.getId() == pedidos.getTipoIva()) {
                    subb=subb * -1;
                    double base = tipoIva.getBaseImponible() + subb;
                    ivaa=ivaa * -1;
                    double ivva = tipoIva.getImporte() + ivaa;
                    tipoIva.setBaseImponible(base);
                    tipoIva.setImporte(ivva);
                }
            }
            precioUnitario = Numeros.Redondear(precioUnitario);
            Double precioSIva = pedidos.getSubTotal();
            precioSIva = Numeros.Redondear(precioSIva);
            Double iva = pedidos.getIva();
            Double pFinal = valor;
            pFinal = Numeros.Redondear(pFinal);
            
            fila[5]=val;
            fila[3]=Numeros.ConvertirNumero(precioSIva);
            fila[2]=Numeros.ConvertirNumero(pedidos.getPrecioDeCosto());
            
            fila[6]=Numeros.ConvertirNumero(iva);
            fila[4]=cant;
            
            fila[7]=Numeros.ConvertirNumero(pFinal);
            busC.addRow(fila);
        }
        if (porcentajeDescuento > 0.00) {
            Double subD = montoTotal * porcentajeDescuento;
            montoDescuento = Numeros.Redondear(subD);
            subTotal = subTotal * porcentajeDescuento;
            subTotal = Numeros.Redondear(subTotal);
            montoIva = montoIva * porcentajeDescuento;
            montoIva = Numeros.Redondear(montoIva);
            montoTotal = montoTotal - montoDescuento;
            Iterator it = listadoIva.listIterator();
            while (it.hasNext()) {
                tipoIva = (TiposIva) it.next();
                if (tipoIva.getBaseImponible() > 0.00) {
                    double base = tipoIva.getBaseImponible() * porcentajeDescuento;
                    double ivva = tipoIva.getImporte() * porcentajeDescuento;
                    tipoIva.setBaseImponible(base);
                    tipoIva.setImporte(ivva);
                }
            }

        }
       // subTotal = montoTotal / 1.21;
       // subTotal = Numeros.Redondear(subTotal);
        Double ivv = montoIva;
        Double sub = subTotal;
        Double tot = montoTotal;
        fila[0]="";
        fila[1]="<html><strong>SUBTOTAL</strong></html>";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        fila[5]="";
        fila[6]="";
        fila[7]="<html><strong>"+Numeros.ConvertirNumero(subTotal)+"</strong></html>";
        //Double descuen=tot - sub;
        busC.addRow(fila);
        fila[0]="";
        fila[1]="<html><strong>DESCUENTO </strong></html>";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        fila[5]="";
        fila[6]="";
        fila[7]="<html><strong> - "+Numeros.ConvertirNumero(montoDescuento)+"</strong></html>";
        busC.addRow(fila);
        fila[0]="";
        fila[1]="<html><strong>TOTAL</strong></html>";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        fila[5]="";
        fila[6]="";
        fila[7]="<html><strong>"+Numeros.ConvertirNumero(montoTotal)+"</strong></html>";
        busC.addRow(fila);
        columnaCodigo=this.jTable1.getColumn("CODIGO");
        columnaCodigo.setPreferredWidth(40);
        columnaCodigo.setMaxWidth(40);
        columnaCodigo=this.jTable1.getColumn("DESCRIPCION");
        columnaCodigo.setPreferredWidth(400);
        //columnaCodigo.setMaxWidth(400);
        columnaCodigo.setMinWidth(300);
        columnaCodigo=this.jTable1.getColumn("CANTIDAD");
        columnaCodigo.setPreferredWidth(80);
        columnaCodigo.setMaxWidth(80);
        //montoTotal=montoTotal * 1.21;
        String total=String.valueOf(montoTotal);
        this.jLabel1.setText("TOTAL COTIZACION:  "+total);
        listadoDeBusqueda.clear();
        //cargarLista(listadoDeBusqueda);
        this.jCheckBox1.setSelected(true);
        this.jCheckBox1.setVisible(false);
        if(detalleDelPedido.size()==0){
            this.jButton1.setEnabled(false);
        }else{
            this.jButton1.setEnabled(true);
        }
}
private void montrarMonto(){
    //System.err.println("DESCUENTO :"+cliT.getDescuento());
    String total1=Numeros.ConvertirNumero(montoTotal);
    String total="";
    if(cliT.getTipoIva()==1){
        String bruto=Numeros.ConvertirNumero( subTotal);
        String iva=Numeros.ConvertirNumero(montoIva);
        total="<html>Bruto :"+bruto+" <br>IVA "+iva+" <br>Neto "+total1+"</html>";
    }else{
        total="<html>Neto "+total1+"</html>";
    }
    //Double total=montoTotal * cliT.getDescuento();
    //comp.setMontoTotal(total);
    this.jLabel1.setText(total);
}
private void verificar(){
    int cantidad=this.jTable1.getRowCount();
    Articulos art=new Articulos();
    cantidad=cantidad - 1;
    for(int ah=0;ah < cantidad;ah++){
        
        art=(Articulos)detalleDelPedido.get(ah);
        //ah++;
        String descripcion=(String) this.jTable1.getValueAt(ah,1);
        art.setDescripcionArticulo(descripcion);
        String cant=String.valueOf(this.jTable1.getValueAt(ah, 2));
        Double cantida=Double.valueOf(cant).doubleValue();
        art.setCantidad(cantida);
        Double precioUni=(Double) this.jTable1.getValueAt(ah,3);
        Double tot=precioUni;
        art.setPrecioUnitario(tot);
        //montoTotal=montoTotal + tot;
        //System.err.println("nimero "+ah+" decripcion "+descripcion+" limite "+cantidad);
    }
}
private void CargarCantidad(){
        Double cantt=1.00;
            cantt=cantt * -1;
            Double precioUni=0.00;
            if(cantt < 1000){
            if(arti.getModificaPrecio()){
                this.jTextField4.selectAll();
                this.jTextField4.requestFocus();
            }else{
                if(arti.getPrecioServicio()>0){
                 this.jTextField4.requestFocus();   
                }else{
                    Articulos articul=new Articulos();
                    articul.setCantidad(cantt);
                    articul.setCodigoAsignado(arti.getCodigoAsignado());
                    
                    articul.setCodigoDeBarra(arti.getCodigoDeBarra());
                    articul.setDescripcionArticulo(arti.getDescripcionArticulo());
                    articul.setNumeroId(arti.getNumeroId());
                    articul.setPrecioDeCosto(arti.getPrecioDeCosto());
                    articul.setPrecioUnitario(arti.getPrecioUnitarioNeto());
                    articul.setPrecioUnitarioNeto(arti.getPrecioUnitarioNeto());
                    articul.setIdCombo(arti.getIdCombo());
                    articul.setCombo(arti.getCombo());
                    articul.setSubTotal(arti.getSubTotal());
            articul.setIva(arti.getIva());
            articul.setCoeficienteIva(arti.getCoeficienteIva());
            articul.setTipoIva(arti.getTipoIva());
            detalleDelPedido.add(articul);
            agregarRenglonTabla();
//                Double montoTotalX=(arti.getPrecioUnitario() * arti.getCantidad());
//                montoTotal=montoTotal + montoTotalX;
                 montrarMonto();
                 //System.err.println("MONTO TOTAL "+montoTotal);
                 this.jLabel8.setText("");
       //          this.jTable2.removeAll();
                this.jButton1.setVisible(true);
            jTextField1.setText(valorCargado);
           // this.jTextField5.selectAll();
            this.jTextField2.setText("");
            //this.jTextField5.requestFocus();
                }
                }
            }else{
                JOptionPane.showMessageDialog(this,"LA CANTIDAD INGRESADA ES EXCESIVA, POR FAVOR VERIFÍQUELA");
            }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
