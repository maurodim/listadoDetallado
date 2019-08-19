/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Articulos.Pantallas;

import Proveedores.Proveedores;
import Conversores.Numeros;
import interfaces.Editables;
import interfaces.Personalizable;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import Articulos.Articulos;
import Articulos.Rubrable;
import Articulos.Rubros;
import Articulos.SubRubros;
import Etiquetador.Pantallas.ConfiguraEtiqueta;
import Extension.Archivo;
import Extension.ArchivoImpl;
import Extension.ArchivoPdf;
import Extension.ArchivoPdfImpl;
import Extension.CodigosDeBarra;
import Extension.CodigosDeBarraImpl;
import FacturaElectronica.Controlador.TiposIvaControl;
import FacturaElectronica.Objetos.TiposIva;
import Impresiones.ImpresoraServiceImpl;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author mauro
 */
public class ArticulosMod extends javax.swing.JInternalFrame {
    private Articulos arti=new Articulos();
    private Integer accion=0;
    private Double ajuste=0.00;
    public static ArrayList combo;
    private ArrayList lstPorSuc=new ArrayList();
    private ArrayList lstProveedores=new ArrayList();
    private ArrayList lstRubros=new ArrayList();
    private ArrayList lstSubRubros=new ArrayList();
    private String textoTemporal="";
    private CodigosDeBarra codigosDeBarra;
    private List<TiposIva> lstIva;
    private TiposIva tipoIva;
    private TiposIvaControl controlIva;

    public ArticulosMod(Articulos art) {
        arti=art;
        Editables edi=new Articulos();
        Articulos arr=new Articulos();
        lstPorSuc=edi.ListarPorSucursal(arti);
        Double totalActual=0.00;
        Iterator itL=lstPorSuc.listIterator();
        while(itL.hasNext()){
            arr=(Articulos)itL.next();
            totalActual=totalActual + arr.getCantidad();
        }
        arti.setStockActual(totalActual);
        Personalizable rub=new Rubros();
        lstRubros=rub.listar();
        Rubrable sR=new SubRubros();
        lstSubRubros=sR.listarPorRubro(arti.getRubroId());
        
        initComponents();
        
        codigosDeBarra=new CodigosDeBarraImpl();
        etiqueta.setText("");
        combo=new ArrayList();
        Iterator itR=lstRubros.listIterator();
        Rubros rubro=new Rubros();
        int rengl=0;
        int posicion=0;
        Integer rubroI=0;
        int artI=arti.getRubroId();
        while(itR.hasNext()){
            rubro=(Rubros)itR.next();
            rubroI=rubro.getId();
            
            if(rubroI==artI){
               posicion=rengl; 
            }
            rengl++;
        }
        this.jComboBox1.setSelectedIndex(posicion);
        Iterator itSr=lstSubRubros.listIterator();
        SubRubros sub=new SubRubros();
        rengl=0;
        posicion=0;
        rubroI=0;
        artI=arti.getIdSubRubro();
        while(itSr.hasNext()){
            sub=(SubRubros)itSr.next();
            rubroI=sub.getId();
            
            if(rubroI == artI){
                posicion=rengl;
            }
            System.out.println("subrubro: "+rubroI+" señalador "+artI);
            rengl++;
        }
        System.out.println("posicion "+posicion+"cantidad "+lstSubRubros.size()+" seleccionado "+artI);
        try{
        //this.jComboBox3.setSelectedIndex(posicion);
        }catch(java.lang.IllegalArgumentException ex){
            System.out.println(ex);
        }
        this.jTextField1.setText(arti.getDescripcionArticulo());
        
        //this.jTextField2.setText(String.valueOf(totalActual));
        this.jTextField3.setText(String.valueOf(arti.getStockMinimo()));
        this.jTextField4.setText(String.valueOf(Numeros.Redondear(arti.getPrecioDeCosto())));
        this.jTextField5.setText(String.valueOf(Numeros.Redondear(arti.getPrecioUnitarioNeto())));
        this.jTextField7.setText(String.valueOf(arti.getCodigoDeBarra()));
        //this.jTextField6.setText(String.valueOf(arti.getPrecioServicio()));
       // this.jTextField9.setText(String.valueOf(arti.getPrecioServicio1()));
        //this.jCheckBox1.setSelected(arti.getModificaPrecio());
        //this.jCheckBox2.setSelected(arti.getModificaServicio());
        
        controlIva=new TiposIvaControl();
        lstIva=new ArrayList();
        lstIva=controlIva.ListarTipos();
        this.tipo_iva_box.setModel(controlIva.MostrarEnCombo((ArrayList) lstIva));
        Iterator itIva=lstIva.listIterator();
        TiposIva tip;
        int posci=0;
        while(itIva.hasNext()){
            tip=(TiposIva) itIva.next();
            if(arti.getTipoIva()==tip.getId()){
                tipoIva=tip;
                this.tipo_iva_box.setSelectedIndex(posci);
            }
            posci++;
        }
        
        this.stock_lbl.setText("Stock Actual: "+arti.getStockActual());
        if(arti.getModificaPrecio())this.jCheckBox3.setSelected(true);
        //this.jPanel2.setVisible(false);
        this.jTextField7.selectAll();
        this.jTextField7.requestFocus();
        accion=2;
    }
    
    
    /**
     * Creates new form ArticulosMod
     */
    public ArticulosMod() {
        initComponents();
        codigosDeBarra=new CodigosDeBarraImpl();
        this.jButton3.setVisible(false);
        this.etiqueta.setVisible(false);
        combo=new ArrayList();
        this.setTitle("CARGA DE NUEVO ARTICULO");
        //this.jPanel2.setVisible(false);
        
        controlIva=new TiposIvaControl();
        lstIva=new ArrayList();
        lstIva=controlIva.ListarTipos();
        this.tipo_iva_box.setModel(controlIva.MostrarEnCombo((ArrayList) lstIva));
        
                this.tipo_iva_box.setSelectedIndex(2);
          
        this.stock_lbl.setText("Stock Actual: 0");
        this.jTextField7.requestFocus();
        accion=1;
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
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        etiqueta = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tipo_iva_box = new javax.swing.JComboBox();
        stock_lbl = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Modificacion de Articulos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Mlogo.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(740, 585));

        jLabel1.setText("Descripcion");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel2.setText("Proveedor");

        jLabel3.setText("Stock Mínimo :");

        jTextField3.setText("0");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel4.setText("Precio de Costo:");

        jTextField4.setText("0");
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jLabel5.setText("Precio de Venta :");

        jTextField5.setText("0");

        jLabel6.setText("Rubro");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/guardar.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Codigo de Barra");

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
        });

        jCheckBox3.setText("Permite Modificar Precio");
        jCheckBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox3MouseClicked(evt);
            }
        });
        jCheckBox3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCheckBox3PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Personalizable perR=new Rubros();
        Rubros rubro=new Rubros();
        lstRubros=perR.listar();
        Iterator iR=lstRubros.listIterator();
        while(iR.hasNext()){
            rubro=(Rubros)iR.next();
            jComboBox1.addItem(rubro.getDescripcion());
        }
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        Personalizable per=new Proveedores();
        Proveedores proveedor=new Proveedores();
        lstProveedores=per.listar();
        Iterator iP=lstProveedores.listIterator();
        while(iP.hasNext()){
            proveedor=(Proveedores)iP.next();
            jComboBox2.addItem(proveedor.getNombre());
        }

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/etiquetas.png"))); // NOI18N
        jButton3.setText("Generar Etiqueta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        etiqueta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta.setPreferredSize(new java.awt.Dimension(595, 94));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/eliminar_des.png"))); // NOI18N
        jButton2.setText("Eliminar Artículo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("% de IVA");

        stock_lbl.setText("jLabel9");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField7)
                                    .addComponent(jComboBox1, 0, 211, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tipo_iva_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stock_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jTextField1)
                            .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tipo_iva_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stock_lbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 38, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        arti.setCodigoDeBarra(this.jTextField7.getText());
        arti.setDescripcionArticulo(this.jTextField1.getText().toUpperCase());
        //Double cant=Numeros.ConvertirStringADouble(this.jTextField2.getText());
        
        Double cant=0.0000;
        arti.setStockActual(cant);
        cant=Numeros.ConvertirStringADouble(this.jTextField3.getText());
        arti.setStockMinimo(cant);
        cant=Numeros.ConvertirStringADouble(this.jTextField4.getText());
        arti.setPrecioDeCosto(cant);
        cant=Numeros.ConvertirStringADouble(this.jTextField5.getText());
        
        //cant=Numeros.ConvertirStringADouble(this.jTextField6.getText());
        arti.setPrecioServicio(0.0000);
        //cant=Numeros.ConvertirStringADouble(this.jTextField9.getText());
        arti.setPrecioServicio1(0.0000);
        arti.setModificaPrecio(this.jCheckBox3.isSelected());
        arti.setModificaServicio(false);
        tipoIva=lstIva.get(this.tipo_iva_box.getSelectedIndex());
        arti.setSubTotal(Numeros.CalcularSubTotal(cant,tipoIva.getAlicuota()));
        arti.setIva(Numeros.CalcularIva(arti.getSubTotal(), tipoIva.getAlicuota()));
        arti.setTipoIva(tipoIva.getId());
        cant=arti.getSubTotal() + arti.getIva();
        arti.setPrecioUnitarioNeto(cant);
        //arti.setModificaPrecio(this.jCheckBox1.isSelected());
        //arti.setModificaServicio(this.jCheckBox2.isSelected());
        
        //Proveedores proveedor=new Proveedores();
        //proveedor=(Proveedores)lstProveedores.get(this.jComboBox2.getSelectedIndex());
        //
        arti.setProveedorId(1);
        Rubros rubro=new Rubros();
        rubro=(Rubros)lstRubros.get(this.jComboBox1.getSelectedIndex());
        arti.setRubroId(rubro.getId());
        if(lstSubRubros.size() > 0){
        SubRubros sub=new SubRubros();
        //sub=(SubRubros)lstSubRubros.get(this.jComboBox3.getSelectedIndex());
        arti.setIdSubRubro(0);
        }else{
            arti.setIdSubRubro(0);
        }
        if(this.jCheckBox3.isSelected()){
            arti.setIdCombo(1);
        }else{
            arti.setIdCombo(0);
        }
        if(arti.getIdCombo() > 0)arti.setCombo(combo);
        Editables edit=new Articulos();
        if(accion==2){
            edit.ModificaionObjeto(arti);
            //edit.EliminacionDeObjeto(arti);
        }else{
            edit.AltaObjeto(arti);
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCheckBox3PropertyChange
       // TODO add your handling code here: 
    }//GEN-LAST:event_jCheckBox3PropertyChange

    private void jCheckBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox3MouseClicked
        
        if(this.jCheckBox3.isSelected()){
            System.out.println("entrooooooo");
            /*
            Combos combo=new Combos();
            combo.setVisible(true);
            arti.setIdCombo(1);
                    */
            
            
        }
        
    }//GEN-LAST:event_jCheckBox3MouseClicked

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)this.jTextField1.requestFocus();
    }//GEN-LAST:event_jTextField7KeyPressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int seleccion=this.jComboBox1.getSelectedIndex();
        Rubros rub=new Rubros();
        rub=(Rubros)lstRubros.get(seleccion);
        Rubrable rubra=new SubRubros();
        lstSubRubros.clear();
        lstSubRubros=rubra.listarPorRubro(rub.getId());
        //this.jComboBox3.removeAllItems();
        Iterator iSb=lstSubRubros.listIterator();
        SubRubros subR=new SubRubros();
        while(iSb.hasNext()){
            subR=(SubRubros)iSb.next();
            //this.jComboBox3.addItem(subR.getDescripcion());
        }
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(this.jTextField7.getText() != null){
            
            textoTemporal=this.jTextField7.getText();
            
            System.out.println("ancho "+etiqueta.getPreferredSize().getWidth()+" alto "+etiqueta.getPreferredSize().getHeight());
            etiqueta.setIcon(new ImageIcon(
            codigosDeBarra.redimensionar(codigosDeBarra.barraCode128(textoTemporal), 
                    (int)etiqueta.getPreferredSize().getWidth(), 
                    (int)etiqueta.getPreferredSize().getHeight())));
            String serialCodigoBarra = textoTemporal;
            CodigosDeBarra codigosDeBarra = new CodigosDeBarraImpl();
            Image imagen = codigosDeBarra.barraCode128(serialCodigoBarra);
            int ancho = 0;
            int alto = 0;
            
            imagen = codigosDeBarra.redimensionar(imagen, 100, 30);
            
            // Imagen desde la memoria
            ImpresoraServiceImpl impresoraServicio = new ImpresoraServiceImpl();
            
                String serialCodigoBarraImp = this.jTextField1.getText();
                ConfiguraEtiqueta eti=new ConfiguraEtiqueta(null,true);
                eti.setVisible(true);
                
                int cantidadCopias=eti.getCantidad();//Integer.parseInt(JOptionPane.showInputDialog("INGRESE LA CANTIDAD DE COPIAS A IMPRIMIR"));
                int cantidaPorLinea=eti.getRenglon();//Integer.parseInt(JOptionPane.showInputDialog("INGRESE LA CANTIDAD DE ETIQUETAS POR LINEA QUE DESEA IMPRIMIR"));
                ancho=eti.getAncho();
                alto=eti.getAlto();
           /* CodigosDeBarra codigosDeBarra = new CodigosDeBarraImpl();
            Image imagen = codigosDeBarra.barraCode128(serialCodigoBarra); // Imagen Original
            //imagen = codigosDeBarra.redimensionar(imagen, 38, 21); // Escalamiento de la imagen (opcional)
            
            // Imagen desde la memoria
           // impresoraServicio.imprimirCodigoDeBarra(imagen, serialCodigoBarra, 0, 0); // imagen, codigo, margen en X, margen en Y
           impresoraServicio.imprimirCodigoBarraMatriz(imagen, 0, 0);*/
        
        Archivo archivo = new ArchivoImpl();
        Path rutaArchivo = null;
        try {
            rutaArchivo = archivo.rutaNormalizadaLocal("Etiquetas-"+System.currentTimeMillis()+".pdf");
        } catch (MalformedURLException ex) {
            Logger.getLogger(ArticulosMod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ArticulosMod.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArchivoPdf apdf = new ArchivoPdfImpl();
        
        //apdf.crearDocumento(0, 0, 5, 0);
        apdf.crearDocumento(0, 0, 5, 0);
        apdf.nombrePdf(rutaArchivo.getFileName().toString(),Paths.get("Etiquetas"));
        //apdf.nombrePdf(rutaArchivo.getFileName().toString(),  rutaArchivo.getParent()); // Ruta raiz del proyecto.
        //apdf.nombrePdf("Etiquetas-"+System.currentTimeMillis()+".pdf", Paths.get("/home/andy/PDF")); // Ejemplo para cualquier otra ruta
        apdf.nuevaEtiqueta(serialCodigoBarra,cantidadCopias, 0, 0,ancho,alto);
        //apdf.nuevaEtiqueta("865521548", 25, 0, 0);
        //apdf.nuevaEtiqueta("967741213", 30, 0, 0);
        //apdf.nuevaEtiqueta("885544771", 15, 0, 0);       
        apdf.generandoGridConEtiquetas(cantidaPorLinea);
            try {
                /*
                try ( //String ruta="Informes\\listadoDeArticulos.xls";
                FileOutputStream elFichero = new FileOutputStream(apdf.getRutaArchivo())) {
                libro.write(elFichero);
                }
                */
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+apdf.getRutaArchivo());
                
                
                //impresoraServicio.imprimirPDF(apdf.getRutaArchivo());
                //    impresoraServicio.imprimirCodigoDeBarra(imagen, serialCodigoBarra,10,10);
            } catch (IOException ex) {
                Logger.getLogger(ArticulosMod.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.jTextField3.selectAll();
            this.jTextField3.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.jTextField5.selectAll();
            this.jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.jTextField4.selectAll();
            this.jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(arti.getStockActual()==0){
        if (JOptionPane.showConfirmDialog(this, "Ésta por eliminar el artículo "+arti.getDescripcionArticulo()+" del sistema, CONFIRMA DICHA OPERACIÓN?", "Eliminar Artículo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 1) {

            } else {
            Editables edi=new Articulos();
            edi.EliminacionDeObjeto(arti);
            this.dispose();
        }
        }else{
            JOptionPane.showMessageDialog(null, "EL STOCK DEL PRODUCTO DEBE SER 0 PARA PODER ELIMINARLO, POR FAVOR AJUSTE STOCK Y LUEGO PROCEDA.GRACIAS");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel etiqueta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel stock_lbl;
    private javax.swing.JComboBox tipo_iva_box;
    // End of variables declaration//GEN-END:variables
}
