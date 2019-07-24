/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citi.objetosDao;

import FacturaElectronica.Objetos.TiposIva;
import interfaces.Transaccionable;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import objetosCiti.Comprasfiscal;
import objetosR.Conecciones;

/**
 *
 * @author Usuario
 */
public class ComprasfiscalJpaController implements Serializable {

    private Transaccionable tra;

    public ComprasfiscalJpaController() {
        try {
            //this.emf = Persistence.createEntityManagerFactory("GeneradorCitiPU");
            tra = new Conecciones();
        } catch (InstantiationException ex) {
            Logger.getLogger(ComprasfiscalJpaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ComprasfiscalJpaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ComprasfiscalJpaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //private EntityManagerFactory emf = null;
/*
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     */
    public Integer create(Comprasfiscal comprasfiscal) {
        //tra=new Conecciones();

        String sql = "insert into comprasfiscal (fecha,tipo,pto,numero,gravado,alicuota,iva,total,razon,cuit,netonogravado,exentas,percepcioniva,impuestosnacionales,percepcionib,impmunicipales,impinternos,cantidadalicuotaiva,otrostributos) values ('" + comprasfiscal.getFecha() + "','" + comprasfiscal.getTipo() + "','" + comprasfiscal.getPto() + "','" + comprasfiscal.getNumero() + "'," + comprasfiscal.getGravado() + ",'" + comprasfiscal.getAlicuota() + "'," + comprasfiscal.getIva() + "," + comprasfiscal.getTotal() + ",'" + comprasfiscal.getRazon() + "','" + comprasfiscal.getCuit() + "'," + comprasfiscal.getNetonogravado() + "," + comprasfiscal.getExentas() + "," + comprasfiscal.getPercepcioniva() + "," + comprasfiscal.getImpuestosnacionales() + "," + comprasfiscal.getPercepcionib() + "," + comprasfiscal.getImpmunicipales() + "," + comprasfiscal.getImpinternos() + "," + comprasfiscal.getCantidadalicuotaiva() + "," + comprasfiscal.getOtrostributos() + ")";
        System.out.println(sql);
        tra.guardarRegistro(sql);
        Integer id = null;
        sql = "select id from comprasfiscal order by id desc fetch first 1 rows only";
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
        try {
            while (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
            Iterator it = comprasfiscal.getLstAlicuotas().listIterator();
            String codIva;
            while (it.hasNext()) {
                TiposIva tipoIva = (TiposIva) it.next();
                codIva = String.format("%04d", tipoIva.getId());
                sql = "insert into comprasfiscalalicuota (tipo,pto,numero,gravado,alicuota,iva,idcompras) values ('" + comprasfiscal.getTipo() + "','" + comprasfiscal.getPto() + "','" + comprasfiscal.getNumero() + "'," + tipoIva.getBaseImponible() + ",'" + codIva + "'," + tipoIva.getImporte() + "," + id + ")";
                System.out.println("sql" + sql);
                System.out.println("tipo iva" + tipoIva.getId() + " gravado " + tipoIva.getBaseImponible() + " importe " + tipoIva.getImporte() + " descripcion " + tipoIva.getDescripcion());
                tra.guardarRegistro(sql);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ComprasfiscalJpaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
        //tra.cerrar();

        /*
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(comprasfiscal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
         */
    }

    public Comprasfiscal CargarCompra(Integer idMovimiento) {
        String sql = "select * from comprasfiscal where id=" + idMovimiento;
        ResultSet rs = tra.leerConjuntoDeRegistros(sql);
        Comprasfiscal compras = new Comprasfiscal();
        try {
            while (rs.next()) {
                
                compras.setId(rs.getInt("id"));
                compras.setFecha(rs.getString("fecha"));
                //String ptoVta=String.format("%05d", this.numeroPuntoDeVenta);
                compras.setTipo(rs.getString("tipo"));
                compras.setPto(rs.getString("pto"));
                compras.setNumero(rs.getString("numero"));
                //alicuota=String.format("%04d",tipoIva.getId());
                compras.setGravado(rs.getDouble("gravado"));
                compras.setAlicuota(rs.getString("alicuota"));
                compras.setIva(rs.getDouble("iva"));
                compras.setNetonogravado(rs.getDouble("netonogravado"));
                compras.setExentas(rs.getDouble("exentas"));
                compras.setPercepcioniva(rs.getDouble("percepcioniva"));
                compras.setImpuestosnacionales(rs.getDouble("impuestosnacionales"));
                compras.setPercepcionib(rs.getDouble("percepcionib"));
                compras.setImpmunicipales(rs.getDouble("impmunicipales"));
                compras.setImpinternos(rs.getDouble("impinternos"));
                compras.setOtrostributos(rs.getDouble("otrostributos"));
                //montoTotal=montoGravado+montoIva+netoNoGravado+exento+percepcionIva+impuestosNacionales+percepcionIb+impuestosMunicipales+impuestosInternos+otros;
                compras.setTotal(rs.getDouble("total"));
                //compras.setIdcliente(cliT.getNumero());
                //compras.setTipoClienteId(cliT.getCondicionDeIva());
                compras.setRazon(rs.getString("razon"));
                compras.setCuit(rs.getString("cuit"));
                compras.setAlicuota(rs.getString("alicuota"));
                //compras.setLstAlicuotas((ArrayList) lsstIva);
                compras.setCantidadalicuotaiva((short) rs.getInt("cantidadalicuotaiva"));
            }
            if (compras.getId() != null) {
                sql = "select * from comprasfiscalalicuota where idcompras=" + compras.getId();
                ArrayList<TiposIva> lstAlicuota=new ArrayList();
                rs=tra.leerConjuntoDeRegistros(sql);
                TiposIva tipoIva;
                while(rs.next()){
                    tipoIva=new TiposIva(rs.getInt("tipo"),rs.getDouble("gravado"),rs.getDouble("iva"),rs.getShort("alicuota"));
                    lstAlicuota.add(tipoIva);
                }
                compras.setLstAlicuotas(lstAlicuota);
                rs.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ComprasfiscalJpaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compras;
    }

}
