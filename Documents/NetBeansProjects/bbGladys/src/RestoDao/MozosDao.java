/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestoDao;

import Resto.Mozos;
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
public class MozosDao implements Restable{

    @Override
    public Object Nuevo(Object objeto) {
        Mozos mozo=(Mozos) objeto;
        Transaccionable tra=new Conecciones();
        String sql="insert into mozos (nombre,color) values ('"+mozo.getNombre()+"',"+mozo.getColor()+")";
        tra.guardarRegistro(sql);
        sql="select last_insert_id()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                mozo.setId(rs.getInt(1));
            }
            rs.close();
            tra.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MozosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mozo;
    }

    @Override
    public Object Modificar(Object objeto) {
        Mozos mozo=(Mozos) objeto;
        Transaccionable tra=new Conecciones();
        String sql="update mozos set nombre='"+mozo.getNombre()+"',color="+mozo.getColor()+" where id="+mozo.getId();
        tra.guardarRegistro(sql);
        tra.cerrarConexion();
        return mozo;
    }

    @Override
    public ArrayList Listar(Object objeto) {
        ArrayList<Mozos> lst=new ArrayList();
        Mozos mozo;
        String sql="select * from mozos order by nombre";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                mozo=new Mozos();
                mozo.setId(rs.getInt("id"));
                mozo.setNombre(rs.getString("nombre"));
                mozo.setColor(rs.getInt("color"));
                lst.add(mozo);
            }
            rs.close();
            tra.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MozosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public void Asignar(ArrayList lst) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultComboBoxModel MostrarEnCombo(ArrayList lst) {
        Iterator it=lst.listIterator();
        Mozos mozo;
        DefaultComboBoxModel modelo=new DefaultComboBoxModel();
        while(it.hasNext()){
            mozo=(Mozos) it.next();
            modelo.addElement(mozo.getNombre());
        }
        return modelo;
    }

    @Override
    public DefaultTableModel MostrarEnTable(ArrayList lst) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarCVariables(Object objeto, int var) {
        ArrayList<Mozos> lst=new ArrayList();
        Mozos mozo;
        String sql="select *,mozos.nombre,mozos.color from mesas left join mozos on mozos.id=mesas.idmozo where mesas.idmozo > 0 group by mesas.idmozo order by mozos.nombre";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                mozo=new Mozos();
                mozo.setId(rs.getInt("idmozo"));
                mozo.setNombre(rs.getString("nombre"));
                mozo.setColor(rs.getInt("color"));
                lst.add(mozo);
            }
            rs.close();
            tra.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MozosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public Object CargarPorId(int id) {
         
        Mozos mozo=new Mozos();
        String sql="select * from mozos where id="+id;
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                mozo.setId(rs.getInt("id"));
                mozo.setNombre(rs.getString("nombre"));
                mozo.setColor(rs.getInt("color"));
               
            }
            rs.close();
            tra.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MozosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mozo;
    }
    
    
}
