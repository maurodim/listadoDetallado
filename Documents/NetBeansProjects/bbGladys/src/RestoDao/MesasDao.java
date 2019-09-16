/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestoDao;

import Resto.Mesas;
import RestoInterface.Restable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;

/**
 *
 * @author Usuario
 */
public class MesasDao implements Restable {

    @Override
    public Object Nuevo(Object objeto) {
        Mesas mesa = (Mesas) objeto;
        Transaccionable tra = new Conecciones();
        String sql = "insert into mesas (descripcion) values ('" + mesa.getDescripcion() + "')";
        tra.guardarRegistro(sql);
        sql = "select last_insert_id()";
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
        try {
            while (rs.next()) {
                mesa.setId(rs.getInt(1));
            }
            rs.close();
            tra.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MesasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mesa;
    }

    @Override
    public Object Modificar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList Listar(Object objeto) {
        ArrayList<Mesas> lst = new ArrayList();
        String sql;
        Mesas mesa;
        if (objeto != null) {
            mesa = (Mesas) objeto;
            sql = "select *,mozos.nombre from mesas left join mozos on mozos.id=mesas.idmozo where idmozo=" + mesa.getIdMozo();
        } else {

            sql = "select *,mozos.nombre from mesas left join mozos on mozos.id=mesas.idmozo order by descripcion";
        }
        Transaccionable tra = new Conecciones();
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
        try {
            while (rs.next()) {
                mesa = new Mesas();
                mesa.setId(rs.getInt("id"));
                mesa.setDescripcion(rs.getString("descripcion"));
                mesa.setIdMozo(rs.getInt("idmozo"));
                mesa.setNombreMozo(rs.getString("nombre"));
                lst.add(mesa);
            }
            rs.close();
            tra.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MesasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public void Asignar(ArrayList lst) {
        Iterator it = lst.listIterator();
        Transaccionable tra = new Conecciones();
        String sql = null;
        Mesas mesa;
        while (it.hasNext()) {
            mesa = (Mesas) it.next();
            sql = "update mesas set idmozo=" + mesa.getIdMozo() + " where id=" + mesa.getId();
            tra.guardarRegistro(sql);
        }
        tra.cerrarConexion();
    }

    @Override
    public DefaultComboBoxModel MostrarEnCombo(ArrayList lst) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel MostrarEnTable(ArrayList lst) {
        DefaultTableModel modelo=new DefaultTableModel();
        Iterator it=lst.listIterator();
        Mesas mesa;
        modelo.addColumn("ID");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("MOZO");
        Object[] fila=new Object[3];
        while(it.hasNext()){
            mesa=(Mesas) it.next();
            fila[0]=mesa.getId();
            fila[1]=mesa.getDescripcion();
            fila[2]=mesa.getNombreMozo();
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public ArrayList ListarCVariables(Object objeto, int var) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object CargarPorId(int id) {
        
        String sql;
        Mesas mesa = new Mesas();
        
            sql = "select *,mozos.nombre from mesas left join mozos on mozos.id=mesas.idmozo where mesas.id=" + id;
        
        Transaccionable tra = new Conecciones();
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
        try {
            while (rs.next()) {
                
                mesa.setId(rs.getInt("id"));
                mesa.setDescripcion(rs.getString("descripcion"));
                mesa.setIdMozo(rs.getInt("idmozo"));
                mesa.setNombreMozo(rs.getString("nombre"));
                
            }
            rs.close();
            tra.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MesasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mesa;
    }

}
