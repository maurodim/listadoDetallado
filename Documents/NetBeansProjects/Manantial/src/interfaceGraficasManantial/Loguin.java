/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraficasManantial;

import Cajas.Usuarios;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Loguin extends javax.swing.JInternalFrame {

    /**
     * Creates new form Loguin
     */
    public Loguin() {
        initComponents();
        
        try {
            sleep(30);
        } catch (InterruptedException ex) {
            Logger.getLogger(Loguin.class.getName()).log(Level.SEVERE, null, ex);
        }
        Usuarios usuario=new Usuarios();
        Usuarios usuarios=new Usuarios();
        usuarios=(Usuarios) usuario.validarClave("adm",new String("adm"));
        
        //Inicio in=new Inicio(2);
        Inicio.niv=usuarios.getNivelDeAutorizacion();
        Inicio.usuario=usuarios;
        Inicio.sucursal=usuarios.getSucursal();
        Inicio.deposito=Inicio.sucursal.getDepositos();
        Inicio.jMenu1.setEnabled(usuarios.getMenu().getMenu1());
        Inicio.jMenu2.setEnabled(usuarios.getMenu().getMenu2());
        //Inicio.jMenu5.setEnabled(usuarios.getMenu().getMenu5());
        //Inicio.jMenu3.setEnabled(Inicio.usuario.getMenu().getMenu3());
        //Inicio.jMenu4.setEnabled(Inicio.usuario.getMenu().getMenu4());
       this.dispose();
        
    
    }
    public Loguin(boolean capaz) {
        initComponents();
        
        }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setTitle("Login Sistema Say No More");

        jLabel1.setText("LOGIN DE USUARIOS - INGRESE SU USUARIO Y CLAVE PARA COMENZAR A OPERAR");

        jLabel2.setText("USUARIO :");

        jLabel3.setText("CLAVE :");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(82, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        Usuarios usuario=new Usuarios();
        Usuarios usuarios=new Usuarios();
        usuarios=(Usuarios) usuario.validarClave("adm",new String("adm"));
        if(usuarios.getNumero()> 0){
        //Inicio in=new Inicio(2);
        Inicio.niv=usuarios.getNivelDeAutorizacion();
        Inicio.usuario=usuarios;
        Inicio.sucursal=usuarios.getSucursal();
        Inicio.deposito=Inicio.sucursal.getDepositos();
        Inicio.jMenu1.setEnabled(usuarios.getMenu().getMenu1());
        Inicio.jMenu2.setEnabled(usuarios.getMenu().getMenu2());
        //Inicio.jMenu5.setEnabled(usuarios.getMenu().getMenu5());
        //Inicio.jMenu3.setEnabled(Inicio.usuario.getMenu().getMenu3());
        //Inicio.jMenu4.setEnabled(Inicio.usuario.getMenu().getMenu4());
       this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
