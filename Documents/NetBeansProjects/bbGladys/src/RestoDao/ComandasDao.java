/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestoDao;

import Resto.Comandas;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;

/**
 *
 * @author Usuario
 */
public class ComandasDao {
    public Comandas GenerarNueva(int idMesa,int idMozo){
        Comandas comanda=new Comandas();
        Transaccionable tra=new Conecciones();
        String sql="insert into comandas (idmesa,idmozo) values ("+idMesa+","+idMozo+")";
        tra.guardarRegistro(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros("select last_insert_id()");
        try {
            while(rs.next()){
                comanda.setId(rs.getInt(1));
                comanda.setIdMesa(idMesa);
                comanda.setIdMozo(idMozo);
            }
            rs.close();
            tra.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ComandasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comanda;
    }
}
