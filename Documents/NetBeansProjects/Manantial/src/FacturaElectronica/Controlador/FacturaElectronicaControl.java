/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturaElectronica.Controlador;

import FacturaElectronica.Objetos.FacturaElectronica;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosR.Conecciones;

/**
 *
 * @author Usuario
 */
public class FacturaElectronicaControl {
    
    public FacturaElectronica Cargar(Integer numero, Integer tipo){
        FacturaElectronica factu=new FacturaElectronica();
        try {
            Transaccionable tra=new Conecciones();
            String sql="select * from fiscal where numero='"+numero+"' and tipo='"+tipo+"'";
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                factu.setFecha(rs.getString("fecha"));
                factu.setTipoComprobante(rs.getString("tipo"));
                factu.setId(rs.getInt("id"));
                //factu.seta
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(FacturaElectronicaControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FacturaElectronicaControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaElectronicaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factu;
    }
}
