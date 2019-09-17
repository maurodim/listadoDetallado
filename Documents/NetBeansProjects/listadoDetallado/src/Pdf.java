
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mauro
 */
public class Pdf {
    static Integer numeroListado;
    static Integer numeroRevision;
    static Double totalKg;
    static String formularioDetallado;
    static String destino;
    static String destinoRemoto;
    static ArrayList listado;
    static Connection cc;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File carpeta=new File("c:/listadosHdr");
        if(!carpeta.isDirectory()){
            carpeta.mkdirs();
        }
        //PdfListado pdfD;
        
        
        //chequearListado(this.numeroListado);
        //Configu configuracion=Configu.DETALLADO;
         
        //cc=(Connection) new Coneccion();
        /*
        cc=Coneccion.ObtenerConeccion();
        Connection cc1=Coneccion.ObtenerConeccion();
        
        Map listDetallado=new HashMap();
        listDetallado.put("numeroListado",Pdf.numeroListado);
        listDetallado.put("numeroRevision", Pdf.numeroRevision);
        System.err.println("Listado "+Pdf.numeroListado+" kg "+Pdf.totalKg);
        listDetallado.put("kG",totalKg);
        String master = Pdf.formularioDetallado;//"C://src//listadosDePreparacion//revisionDeListados.jasper";
        //configuracion.valueOf(master);
        System.out.println("DIRECCION DE DESTINO //////////////////////////////////// "+master);
        String destino=Pdf.destinoRemoto;//"////COLOSSUS//logistica//Sist HDR//LPM//"+this.numeroListado+"-Rev 0 - listado detallado de materiales.pdf";
        String destino2="C://listadosHdr//"+Pdf.numeroListado+"-Rev 0 - listado detallado de materiales.pdf";
        
        String kg=String.valueOf(Math.round(totalKg * 100.0) / 100.0);
        Iterator it=Pdf.listado.listIterator();
        
        ArrayList definitivo=new ArrayList();
        PdfListado pdfD;
        //PedidosParaReparto pedi;
        //Clientes cliente;
        //ChequearCantidadesPedidos cheq=new Clientes();
        numeroListado=8151;
        numeroRevision=1;
        String vehiculo="El suzuki";
        //LO NUEVO
        
        String sql="select count(*) as visitas from pedidos_carga1 where listado="+numeroListado+" group by cod_client";
        
        
        String kk = null;
        String fechaEntrega = null;
        String destino2="prueba1.pdf";
        destino="pruebaRemota.pdf";
        //String kg=0.00;
        
        try {
            Statement st =cc.createStatement();
            
            ResultSet rs;
            
            
            sql="select * from pedidos_carga1 where listado="+numeroListado+" group by nro_pedido order by zona,RAZON_SOC";
            rs=st.executeQuery(sql);
            while(rs.next()){
            //pedi= (PedidosParaReparto) it.next();
            pdfD=new PdfListado();
            
            cliente=new Clientes();
            cliente.setCodigoCliente(pedi.getCodigoCliente());
            cliente.setEmpresa(pedi.getEmpresa());
            cliente=(Clientes) cheq.actualizar(cliente);
            
            pdfD.setIdListado(8151);
            pdfD.setIdRevision(1);
            pdfD.setVehiculo(rs.getString("vehiculo"));
            kk=String.valueOf(Math.round(rs.getDouble("peso") * 100.0) / 100.0);
            pdfD.setKilos(kk);
            pdfD.setFechaEntrega(rs.getString("entrega"));
            fechaEntrega=rs.getString("entrega");
            pdfD.setNumeroPedido(rs.getString("NRO_PEDIDO"));
            pdfD.setNombreCliente(rs.getString("RAZON_SOC"));
            pdfD.setLeyenda1(rs.getString("LEYENDA_1"));
            pdfD.setLeyenda2(rs.getString("LEYENDA_2"));
            pdfD.setLeyenda3(rs.getString("LEYENDA_3"));
            pdfD.setPeso(String.valueOf(rs.getDouble("peso")));
            pdfD.setSaldoCliente(rs.getString("saldoCliente"));
            pdfD.setRevision(String.valueOf(1));
            pdfD.setFechaPedido(rs.getString("FEC_PEDIDO"));
            pdfD.setLocalidad("SANTA FE");
            pdfD.setCodigoArticulo(rs.getString("COD_ARTIC"));
            pdfD.setDescripcion(rs.getString("DESC_ARTIC"));
            pdfD.setCantidad(String.valueOf(rs.getDouble("CANT_PEDID")));
            pdfD.setOrdenDetrabajo(String.valueOf(0));
            //pdfD.setRepetido(String.valueOf(pedi.get));
            pdfD.setEmpresa(rs.getString("TALON_PEDI"));
            pdfD.setNombreVendedor("mauro");
            pdfD.setDomicilioCliente(rs.getString("LEYENDA_1"));
            pdfD.setLocalidadCliente("rincon");
            pdfD.setTelefonoCliente("3425451500");
            pdfD.setCodigoCliente(rs.getString("COD_CLIENT"));
            pdfD.setNotificado(rs.getInt("notificado"));
            //visitasTotales=rs.getInt("cvisitas");
            System.out.println(pdfD.getLocalidadCliente()+" - "+pdfD.getTelefonoCliente()+" - "+pdfD.getLeyenda2());
            //System.err.println(paginaT+" renglones hasta ahora");
            definitivo.add(pdfD);
            
        }
            
            int paginaT=definitivo.size() * 6;
             System.err.println(paginaT+" renglones hasta ahora");
            sql="select count(*) as cantt from pedidos_carga1 where listado="+numeroListado;
            rs=st.executeQuery(sql);
            while(rs.next()){
                paginaT=paginaT + rs.getInt("cantt");
            }
            System.err.println(paginaT+" renglones hasta ahora");
            
            
            PdfListado pdf=new PdfListado(cc1,numeroListado,numeroRevision,vehiculo,kk,fechaEntrega,destino2,destino,definitivo);
            
            Double calcu=paginaT / 64.00;
            int tot=(int) Math.ceil(calcu);
            System.err.println(tot+" renglones resultado");
            pdf.setPaginasTotal(tot);
            //pdf.setCantidadDeVisitas(visitasTotales);
            
            
            pdf.start();
            
        } catch (SQLException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
    }
    
}
