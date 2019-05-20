/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Articulos.Pantallas;

import Conversores.Numeros;
import Excel.InformeArticulos;
import interfaces.Componable;
import interfacesPrograma.Facturar;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import Articulos.Articulos;
import Articulos.Articulos;
import Articulos.Rubrable;
import Articulos.Rubros;
import Articulos.Rubros;
import Excel.PlanillaStock;
import interfaceGraficasManantial.Inicio;
import interfaces.Personalizable;
import javax.swing.table.DefaultTableModel;
import tablas.MiModeloTablaArticulos;

/**
 *
 * @author mauro
 */
public class ArticulosAbm extends javax.swing.JInternalFrame {

    private ArrayList listadoA = new ArrayList();
    private ArrayList listadoRubro;
    private Rubros rubro;
    private Componable rubC;
    private Personalizable rub;
    private Rubrable rubR;

    /**
     * Creates new form ArticulosAbm
     */
    public ArticulosAbm() {
        initComponents();
        rub = new Rubros();
        rubR = new Rubros();
        listadoRubro = new ArrayList();
        rubro = new Rubros();
        listadoRubro = rub.listar();
        this.jComboBox1.setModel(rubR.mostrarEnBox(listadoRubro));

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
        DefaultTableModel tabla=new DefaultTableModel();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Alta, Baja y modificacion de Articulos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Mlogo.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTable1.setModel(tabla);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/nuevo.png"))); // NOI18N
        jButton1.setText("Nuevo Articulo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/editar.png"))); // NOI18N
        jButton4.setText("Modificar Articulo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingrese descripcion de articulo y presione F1");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel2.setText("Ingrese codigo de barra y preione F1");

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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/excel_icone.png"))); // NOI18N
        jButton2.setText("Listar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/excel_icone.png"))); // NOI18N
        jButton5.setText("Planilla de Stock");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("%");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/modificar.png"))); // NOI18N
        jButton6.setText("Mod Costo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setText("%");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevos/modificar.png"))); // NOI18N
        jButton7.setText("Mod Venta");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6.setText("Ingrese el porcentaje de modificación ");

        jLabel3.setText("Para Modificar Precio por rubro, seleccionelo");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 16));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(100, 26));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton7))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 90, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jButton4)
                .addGap(307, 307, 307)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(349, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(jButton1)
                    .addGap(124, 124, 124)
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(671, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Articulos articulo = (Articulos) listadoA.get(this.jTable1.getSelectedRow());
        //System.out.println(" codigo elegido "+articulo.getCodigoAsignado());
        ArticulosMod articM = new ArticulosMod(articulo);
        Inicio.jDesktopPane1.add(articM);
        articM.setVisible(true);
        articM.toFront();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArticulosMod articMo = new ArticulosMod();
        Inicio.jDesktopPane1.add(articMo);
        articMo.setVisible(true);
        articMo.toFront();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            //System.out.println("ENTRO CON F1¡¡¡¡¡");
            Facturar fart = new Articulos();
            ArrayList listadoDeBusqueda = fart.listadoBusqueda(this.jTextField1.getText().toUpperCase());
            cargarLista(listadoDeBusqueda);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            //System.out.println("ENTRO CON F1¡¡¡¡¡");
            Facturar fart = new Articulos();

            ArrayList listadoDeBusqueda = fart.listarClientes(this.jTextField2.getText().toUpperCase());
            cargarLista(listadoDeBusqueda);
        }

    }//GEN-LAST:event_jTextField2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InformeArticulos informe = new InformeArticulos();
        try {
            informe.GenerarInforme(listadoA);
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosAbm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        PlanillaStock planilla = new PlanillaStock();
        try {
            planilla.GenerarInforme(listadoA);
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosAbm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        rubro = (Rubros) listadoRubro.get(this.jComboBox1.getSelectedIndex());
        this.jTextField3.requestFocus();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        Double porc = Numeros.ConvertirStringADouble(this.jTextField3.getText());
        if (JOptionPane.showConfirmDialog(this, "Ajusta Valores de los precios de Costo en los Articulos en un " + porc + "%?", "Modificar Costo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 1) {

        } else {

            porc = porc / 100;
            porc = 1 + porc;
            if(rubro.getId() != null){
                
            }else{
                rubro.setId(1);
            }
            rubR.modificarCostoPorRubro(rubro.getId(), porc);
            super.updateUI();

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Double porc = Numeros.ConvertirStringADouble(this.jTextField4.getText());
        if (JOptionPane.showConfirmDialog(this, "Ajusta Valores de los precios de VENTA en los Articulos en un " + porc + "%?", "Modificar Costo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 1) {

        } else {

            porc = porc / 100;
            porc = 1 + porc;
            if(rubro.getId() != null){
                
            }else{
                rubro.setId(1);
            }
            rubR.modificarPrecioRubro(rubro.getId(), porc);
            super.updateUI();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        
        MiModeloTablaArticulos modelArticulos = new MiModeloTablaArticulos();
        Facturar fact = new Articulos();
        listadoA = fact.listadoBusqueda("");
        Iterator list = listadoA.listIterator();
        jTable1.setModel(modelArticulos);
        modelArticulos.addColumn("CODIGO");
        modelArticulos.addColumn("DESCRIPCION");
        modelArticulos.addColumn("STOCK");
        modelArticulos.addColumn("STOCK MIN");
        modelArticulos.addColumn("COSTO");
        modelArticulos.addColumn("P. VENTA");
        //modelArticulos.addColumn("MAYORISTA");
        Object[] fila = new Object[6];
        while (list.hasNext()) {
            Articulos articulos = (Articulos) list.next();
            fila[0] = articulos.getCodigoAsignado();
            fila[1] = articulos.getDescripcionArticulo();
            fila[2] = articulos.getStockActual();
            fila[3] = articulos.getStockMinimo();
            fila[4] = Math.round(articulos.getPrecioDeCosto() * 100.0) / 100.0;
            fila[5] = Math.round(articulos.getPrecioUnitarioNeto() * 100.0) / 100.0;
            //fila[6] = articulos.getPrecioServicio();
            modelArticulos.addRow(fila);
        }
        //this.updateUI();
    }//GEN-LAST:event_formInternalFrameActivated

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        System.out.println("ENTROOOOOO");
    }//GEN-LAST:event_formComponentShown

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
         MiModeloTablaArticulos modelArticulos = new MiModeloTablaArticulos();
        Facturar fact = new Articulos();
        listadoA = fact.listadoBusqueda("");
        Iterator list = listadoA.listIterator();
        jTable1.setModel(modelArticulos);
        modelArticulos.addColumn("CODIGO");
        modelArticulos.addColumn("DESCRIPCION");
        modelArticulos.addColumn("STOCK");
        modelArticulos.addColumn("STOCK MIN");
        modelArticulos.addColumn("COSTO");
        modelArticulos.addColumn("P. VENTA");
        //modelArticulos.addColumn("MAYORISTA");
        Object[] fila = new Object[6];
        while (list.hasNext()) {
            Articulos articulos = (Articulos) list.next();
            fila[0] = articulos.getCodigoAsignado();
            fila[1] = articulos.getDescripcionArticulo();
            fila[2] = articulos.getStockActual();
            fila[3] = articulos.getStockMinimo();
            fila[4] = Math.round(articulos.getPrecioDeCosto() * 100.0) / 100.0;
            fila[5] = Math.round(articulos.getPrecioUnitarioNeto() * 100.0) / 100.0;
            modelArticulos.addRow(fila);
        }
        System.out.println("entro en mouse");
    }//GEN-LAST:event_formMouseClicked
    public void agregarRenglon() {
        MiModeloTablaArticulos busC = new MiModeloTablaArticulos();
        this.jTable1.removeAll();
        Double montoTotal = 0.00;
        //ArrayList listadoPedidos=new ArrayList();
        this.jTable1.setModel(busC);
        Articulos pedidos = null;
        busC.addColumn("CODIGO");
        busC.addColumn("DESCRIPCION");
        busC.addColumn("STOCK");
        busC.addColumn("STOCK MIN");
        busC.addColumn("COSTO");
        busC.addColumn("P VENTA");
        //busC.addColumn("SERVICIO");
        Object[] fila = new Object[6];
        Iterator irP = listadoA.listIterator();
        while (irP.hasNext()) {
            pedidos = (Articulos) irP.next();
            //fila[0]=pedidos.getCodigo();
            fila[0] = pedidos.getCodigoAsignado();
            fila[1] = pedidos.getDescripcionArticulo();
            fila[2] = pedidos.getStockActual();
            fila[3] = pedidos.getStockMinimo();
            fila[4] = Math.round(pedidos.getPrecioDeCosto() * 100.0) / 100.0;
            fila[5] = Math.round(pedidos.getPrecioUnitarioNeto() * 100.0) / 100.0;
            busC.addRow(fila);
        }
    }

    private void cargarLista(ArrayList lista) {
        DefaultListModel modelo = new DefaultListModel();
        Iterator il = lista.listIterator();
        Articulos art = new Articulos();
        while (il.hasNext()) {
            art = (Articulos) il.next();
            //System.out.println("DESCRIPCION "+art.getDescripcionArticulo());
            modelo.addElement(art.getCodigoAsignado() + " " + art.getDescripcionArticulo());
        }
        ListadoDeArticulos1 listadoDeArt = new ListadoDeArticulos1();
        listadoDeArt.jList1.setModel(modelo);
        listadoDeArt.setVisible(true);
        listadoDeArt.jList1.requestFocus();
        int posicion = listadoDeArt.jList1.getSelectedIndex();
        Articulos articulo = (Articulos) lista.get(posicion);
        //System.out.println(" codigo elegido "+articulo.getCodigoAsignado());
        ArticulosMod articM = new ArticulosMod(articulo);
        Inicio.jDesktopPane1.add(articM);
        articM.setVisible(true);
        articM.toFront();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
