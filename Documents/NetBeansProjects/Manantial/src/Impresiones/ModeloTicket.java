package Impresiones;

import ConfiguracionR.Propiedades;
import Conversores.Numeros;
import Extension.CodigosDeBarraImpl;
import java.awt.Image;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author andy
 */
public class ModeloTicket {
    //Debería recibir como parámetros:
//-Encabezados (razon social, nombre de fantasía, dirección, teléfono, cuit, ing brutos)
//-Datos comprador(nombre, cond iva, direccion, telefono, cuit)
//
//    Array con detalle de articulos(cod, descripcion, cantidad, monto total)
//    -Totales(subtotal, iva,total)
//    -Código de barra debería ser optativo, se envía el string y el jar que lo genere e incluya
//    -Pie (aquí deberíamos poder enviar 3 strings que se utilicen como renglones del final de comprobante) para ser utilizados para saludo o algún código fiscal que sea necesario
//    Todos éstos valores lo ideal es que sean pasados como string así podemos manejar los formatos en el sistema

    // Encabezado
    private FormatoComerciante formatoComerciante;
    //Pie de cierre de caja
    private FormatoPie formatoPie;
    // Datos Comprador
    private FormatoCliente formatoCliente;

    // Datos Articulos
    private List<FormatoArticulos> articulos;

    // Datos extras de la factura
    private FormatoFactura formatoFactura;

    // Codigo de barras
    private String codigoBarra;

    // Pie de Pagina
    private String piePagina;
    private String piePagina1;

    //identificado de tipo de comprobante
    private int idComprobante;

    public FormatoPie getFormatoPie() {
        return formatoPie;
    }

    public void setFormatoPie(FormatoPie formatoPie) {
        this.formatoPie = formatoPie;
    }
    
    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public FormatoComerciante getFormatoComerciante() {
        return formatoComerciante;
    }

    public void setFormatoComerciante(FormatoComerciante formatoComerciante) {
        this.formatoComerciante = formatoComerciante;
    }

    public FormatoCliente getFormatoCliente() {
        return formatoCliente;
    }

    public void setFormatoCliente(FormatoCliente formatoCliente) {
        this.formatoCliente = formatoCliente;
    }

    public List<FormatoArticulos> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<FormatoArticulos> articulos) {
        this.articulos = articulos;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getPiePagina() {
        return piePagina;
    }

    public String getPiePagina1() {
        return piePagina1;
    }

    public void setPiePagina(String piePagina) {
        this.piePagina = piePagina;
    }

    public void setPiePagina1(String piePagina1) {
        this.piePagina1 = piePagina1;
    }

    public FormatoFactura getFormatoFactura() {
        return formatoFactura;
    }

    public void setFormatoFactura(FormatoFactura formatoFactura) {
        this.formatoFactura = formatoFactura;
    }

    /**
     * Formato de ticket. com = comerciante; cli = cliente y fact = otro proceso
     * relacionado a la factura
     */
    private String formatoTicket1() {
        String contenido
                = "                $com.local               \n"
                + "                                         \n"
                + "CUIT Nro.: $com.cuit                     \n"
                + "$com.ingresos                            \n"
                + "$com.telefono                            \n"
                + "$com.direccion                           \n"
                + "$com.razon_social                        \n"
                + "=========================================\n"
                + "TIQUE FACTURA          Nro. $fact.nro    \n"
                + "                       Fecha: $fact.fecha\n"
                + "                       Hora:  $fact.hora \n"
                + "=========================================\n"
                + "CUIT Nro. $cli.cuit                      \n"
                + "$cli.condIva                             \n"
                + "$cli.cliente                             \n"
                + "$cli.telefono                            \n"
                + "$cli.dir                                 \n"
                + "=========================================\n"
                + "Cant./Precio Unit                        \n"
                + "Descripcion (%IVA)                IMPORTE\n"
                + "=========================================\n"
                + "$productos                               \n"
                + "                                         \n"
                + "TOT. NETO SIN IVA              $fact.Niva\n"
                + "=========================================\n"
                + "                                         \n"
                + "SUBTOTAL: $fact.iva             $fact.sub\n"
                + "IVA: $fact.iva                 $fact.Miva\n"
                + "CONCEPTO NO GRAVADOS             $fact.ng\n"
                + "                                         \n"
                + "TOTAL                           $fact.tot\n"
                + "                                         \n"
                + "RECIBI(MOS)                              \n"
                + "Su pago                        $fact.val1\n"
                + "Suma de sus pagos              $fact.val2\n"
                + "Su Vuelto                      $fact.val3\n"
                + "                                         \n"
                + "$fact.pie                                \n"
                + "                                         \n"
                + "                                         \n"
                + "\n";
        //System.out.println("contenido ticket1 \n"+contenido);
        return contenido;
    }

    private String formatoTicketPresu() {
        String contenido
                = "                $com.local               \n"
                + "                                         \n"
                + "CUIT Nro.: $com.cuit                     \n"
                + "TEL.:$com.telefono                       \n"
                + "DIR.:$com.direccion                      \n"
                + "=========================================\n"
                + "PRESUPUESTO            Nro. $fact.nro    \n"
                + "                       Fecha: $fact.fecha\n"
                + "                       Hora:  $fact.hora \n"
                + "=========================================\n"
                + "$cli.cliente                             \n"
                + "TEL.:$cli.telefono                       \n"
                + "DIR.:$cli.dir                            \n"
                + "=========================================\n"
                + "Cant./Precio Unit                        \n"
                + "Descripcion                       IMPORTE\n"
                + "=========================================\n"
                + "$productos                               \n"
                + "                                         \n"
                + "=========================================\n"
                + "                                         \n"
                + "DESCUENTO                        $fact.ng\n"
                + "                                         \n"
                + "TOTAL                           $fact.tot\n"
                + "                                         \n"
                + "$fact.pie                                \n"
                + "                                         \n"
                + "                                         \n"
                + "\n";
        //System.out.println("contenido presupuesto \n"+contenido);
        return contenido;
    }

    private String formatoTicketElectronico(int id) {
        String contenido
                = "                $com.local               \n"
                + "                                         \n"
                + "RAZON SOCIAL: $com.razon_social          \n"
                + "CUIT Nro.: $com.cuit                     \n"
                + "IIBB: $com.ingresos                      \n"
                + "TEL.: $com.telefono                      \n"
                + "DIR.:$com.direccion                      \n"
                + "COND IVA:$com.condicion                  \n"
                + "=========================================\n"
                + "TIQUE FACTURA          Nro. $fact.nro    \n"
                + "                       Fecha: $fact.fecha\n"
                + "                       Hora:  $fact.hora \n"
                + "=========================================\n"
                + "CUIT Nro. $cli.cuit                      \n"
                + "Nro. Cliente:$cli.condIva                \n"
                + "$cli.cliente                             \n"
                + "Dir.: $cli.dir                           \n"
                + "=========================================\n"
                + "Cant./Precio Unit (%IVA)                 \n"
                + "Descripcion                       IMPORTE\n"
                + "=========================================\n"
                + "$productos                               \n"
                + "                                         \n"
                + "=========================================\n"
                + "                                         \n"
                + "SUBTOTAL:                       $fact.sub\n"
                + "IVA:  0.0%                      $fact.Miv\n"
                + "IVA: 10.5%                      $fact.1Mi\n"
                + "IVA: 21.0%                      $fact.2Mi\n"
                + "IVA: 27.0%                      $fact.3Mi\n"
                + "                                         \n"
                + "TOTAL                           $fact.tot\n"
                + "                                         \n"
                + "$fact.pie                                \n"
                + "$fact.vtoc                               \n"
                + "                                         \n"
                + "Factura electrónica emitida con          \n"
                + "manantialgestion.com                     \n"
                + "                                         \n"
                + "                                         \n"
                + "                                         \n"
                + "\n";

        switch (this.idComprobante) {
            case 1:
                contenido = contenido.replace("TIQUE FACTURA", "FACTURA A    ");
                break;
            case 2:
                contenido = contenido.replace("TIQUE FACTURA", "NTA DEBITO A ");
                break;
            case 3:
                contenido = contenido.replace("TIQUE FACTURA", "NTA CREDITO A");
                break;
            case 6:
                contenido = contenido.replace("TIQUE FACTURA", "FACTURA B    ");
                break;
            case 7:
                contenido = contenido.replace("TIQUE FACTURA", "NTA DEBITO B ");
                break;
            case 8:
                contenido = contenido.replace("TIQUE FACTURA", "NTA CREDITO B");
                break;
            case 11:
                contenido = contenido.replace("TIQUE FACTURA", "FACTURA C    ");
                break;
            case 12:
                contenido = contenido.replace("TIQUE FACTURA", "NTA DEBITO C ");
                break;
            case 13:
                contenido = contenido.replace("TIQUE FACTURA", "NTA CREDITO C");
                break;
        }
        //System.out.println("contenido fc \n"+contenido);
        return contenido;
    }
    private String formatoTicketElectronicoB(int id) {
        String contenido
                = "                $com.local               \n"
                + "                                         \n"
                + "RAZON SOCIAL: $com.razon_social          \n"
                + "CUIT Nro.: $com.cuit                     \n"
                + "IIBB: $com.ingresos                      \n"
                + "TEL.: $com.telefono                      \n"
                + "DIR.:$com.direccion                      \n"
                + "COND IVA:$com.condicion                  \n"
                + "=========================================\n"
                + "TIQUE FACTURA         Nro. $fact.nro    \n"
                + "                       Fecha: $fact.fecha\n"
                + "                       Hora:  $fact.hora \n"
                + "=========================================\n"
                + "CUIT Nro. $cli.cuit                      \n"
                + "Nro. Cliente:$cli.condIva                \n"
                + "$cli.cliente                             \n"
                + "Dir.: $cli.dir                           \n"
                + "=========================================\n"
                + "Cant./Precio Unit                        \n"
                + "Descripcion                       IMPORTE\n"
                + "=========================================\n"
                + "$productos                               \n"
                + "                                         \n"
                + "=========================================\n"
                + "                                         \n"
                + "SUBTOTAL:                       $fact.sub\n"
                + "                                         \n"
                + "TOTAL                           $fact.tot\n"
                + "                                         \n"
                + "$fact.pie                                \n"
                + "$fact.vtoc                               \n"
                + "                                         \n"
                + "Factura electrónica emitida con          \n"
                + "manantialgestion.com                     \n"
                + "                                         \n"
                + "                                         \n"
                + "                                         \n"
                + "\n";

        switch (this.idComprobante) {
            case 1:
                contenido = contenido.replace("TIQUE FACTURA", "FACTURA A    ");
                break;
            case 2:
                contenido = contenido.replace("TIQUE FACTURA", "NOTA DEBITO A ");
                break;
            case 3:
                contenido = contenido.replace("TIQUE FACTURA", "NOTA CREDITO A");
                break;
            case 6:
                contenido = contenido.replace("TIQUE FACTURA", "FACTURA B    ");
                break;
            case 7:
                contenido = contenido.replace("TIQUE FACTURA", "NOTA DEBITO B ");
                break;
            case 8:
                contenido = contenido.replace("TIQUE FACTURA", "NOTA CREDITO B");
                break;
            case 11:
                contenido = contenido.replace("TIQUE FACTURA", "FACTURA C    ");
                break;
            case 12:
                contenido = contenido.replace("TIQUE FACTURA", "NOTA DEBITO C ");
                break;
            case 13:
                contenido = contenido.replace("TIQUE FACTURA", "NOTA CREDITO C");
                break;
        }
        System.out.println("contenido fc B\n"+contenido);
        return contenido;
    }
    private String adaptarLinea(String texto) {
        int longitudHorizontal = 43;

        return null;
    }
    
    private String formatoRetiros() {
        String contenido
                = "                $com.local               \n"
                + "                                         \n"
                + "CUIT Nro.: $com.cuit                     \n"
                + "$com.ingresos                            \n"
                + "$com.telefono                            \n"
                + "$com.direccion                           \n"
                + "$com.razon_social                        \n"
                + "=========================================\n"
                + "           RETIRO DE EFECTIVO            \n"
                + "                       Nro. $fact.nro    \n"
                + "                       Fecha: $fact.fecha\n"
                + "                       Hora:  $fact.hora \n"
                + "                       Caja:  $fact.caja \n"
                + "=========================================\n"
                + "                                         \n"
                + "TOTAL                           $fact.tot\n"
                + "                                         \n"
                + "                                         \n"
                + "                                         \n"
                + "\n";
        //System.out.println("contenido ticket1 \n"+contenido);
        return contenido;
    }
    
    private String formatoCierreCaja() {
        String contenido
                = "                $com.local               \n"
                + "                                         \n"
                + "CUIT Nro.: $com.cuit                     \n"
                + "$com.ingresos                            \n"
                + "$com.telefono                            \n"
                + "$com.direccion                           \n"
                + "$com.razon_social                        \n"
                + "=========================================\n"
                + "        RESUMEN DE CIERRE DE CAJA        \n"
                + "                       Fecha: $fact.fecha\n"
                + "                       Hora:  $fact.hora \n"
                + "                       Caja:  $fact.caja \n"
                + "=========================================\n"
                + "Num. Comprobante                         \n"
                + "Descripcion                       IMPORTE\n"
                + "=========================================\n"
                + "$productos                               \n"
                + "                                         \n"
                + "=========================================\n"
                + "SALDO INICIAL:               $cli.saldoin\n"
                + "TOTAL VENTAS:                $cli.totalve\n"
                + "TOTAL GASTOS:                $cli.gastos \n"
                + "TOTAL RETIROS EFECTIVO:      $cli.retiros\n"
                + "TOTAL PAGOS A PROVEEDORES:   $cli.pagos  \n"
                + "                                         \n"
                + "                                         \n"
                + "                                         \n"
                + "                                         \n"
                + "\n";
        //System.out.println("contenido ticket1 \n"+contenido);
        return contenido;
    }
    
    private String estructuracionArticulosCierre() {
        return this.articulos.stream()
                .map(items -> items.getDescripcion()
                + "    $ "
                + items.getMontoTotal()
                + "\n"
                ).collect(Collectors.joining(""));

    }
    private String estructuracionArticulos() {
        return this.articulos.stream()
                .map(items -> items.getCantidad()
                + " X $ "
                + items.getPrecioUnitario() + " (" + items.getPorcientoIva()
                + ")"
                + "\n"
                + items.getDescripcion()
                + "    $ "
                + items.getMontoTotal()
                + "\n"
                ).collect(Collectors.joining(""));

    }
    private String estructuracionArticulosB() {
        return this.articulos.stream()
                .map(items -> items.getCantidad()
                + " X $ "
                + items.getPrecioUnitario()
                + "\n"
                + items.getDescripcion()
                + "    $ "
                + items.getMontoTotal()
                + "\n"
                ).collect(Collectors.joining(""));

    }

    private Image codigoBarra128() {
        return new CodigosDeBarraImpl().barraCode128(codigoBarra);
    }

    private void mostrarCodigoBarra() {
        //----------------

        JFrame jf = new JFrame("Codigo Barra");
        JDialog emergente = new JDialog(jf,
                "Codigo Barra",
                true);

        ImageIcon visual = new ImageIcon(this.codigoBarra128());
        System.out.println("imagen: " + visual.getIconWidth() + " " + visual.getIconHeight());

        JLabel etiqueta = new JLabel(visual);

        emergente.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        emergente.setSize(visual.getIconWidth() + 50, visual.getIconHeight() + 20);
        emergente.setLocationRelativeTo(null);
        emergente.getContentPane().add(etiqueta);
        emergente.setVisible(true);
        //-----------------

    }

    private String procesamiento(String contenido) {

        contenido = contenido.replace("$com.local", formatoComerciante.getNombreDelLocal());
        
        if(formatoComerciante.getCuitLocal()!=null){
        contenido = contenido.replace("$com.cuit", formatoComerciante.getCuitLocal());
        }else{
            contenido = contenido.replace("$com.cuit", "");
        }
        if(formatoComerciante.getIngresosBrutos()!=null){
        contenido = contenido.replace("$com.ingresos", formatoComerciante.getIngresosBrutos());
        }else{
            contenido = contenido.replace("$com.ingresos", "");
        }
        if(formatoComerciante.getTelefono()!=null){
        contenido = contenido.replace("$com.telefono", formatoComerciante.getTelefono());
        }else{
        contenido = contenido.replace("$com.telefono", "");    
        }
        if(formatoComerciante.getDireccion()!=null){
        contenido = contenido.replace("$com.direccion", formatoComerciante.getDireccion());
        }else{
        contenido = contenido.replace("$com.direccion", "");    
        }
        if(formatoComerciante.getRazonSocial()!=null){
        contenido = contenido.replace("$com.razon_social", formatoComerciante.getRazonSocial());
        }else{
        contenido = contenido.replace("$com.razon_social", "");    
        }

        contenido = contenido.replace("$fact.nro", formatoFactura.getNroFactura());
        contenido = contenido.replace("$fact.fecha", formatoFactura.getFecha());
        contenido = contenido.replace("$fact.hora", formatoFactura.getHora());
        if(formatoFactura.getIva()!=null){
            contenido = contenido.replace("$fact.iva", formatoFactura.getIva());
        }else{
            
        }
        /*
        contenido = contenido.replace("$cli.cuit", formatoCliente.getCuitCliente());
        if(formatoCliente.getCondIva()!= null){
        contenido = contenido.replace("$cli.condIva", formatoCliente.getCondIva());
        }
        */
        contenido = contenido.replace("$cli.cliente", formatoCliente.getNombreCliente());
        contenido = contenido.replace("$cli.telefono", formatoCliente.getTelefonoCliente());
        contenido = contenido.replace("$cli.dir", formatoCliente.getDireccionCliente());

        contenido = contenido.replace("$productos", this.estructuracionArticulos());

        if(formatoFactura.getTotalSinIva()!=null){
        contenido = contenido.replace("$fact.Niva", formatoFactura.getTotalSinIva());
        }
        if(formatoFactura.getSubTotal()!=null){
        contenido = contenido.replace("$fact.sub", "$ "+formatoFactura.getSubTotal());
        }
        if(formatoFactura.getNoGravados() !=null){
        contenido = contenido.replace("$fact.ng", "$ "+formatoFactura.getNoGravados());
        }
        if(formatoFactura.getMontoIva()!=null){
        contenido = contenido.replace("$fact.Miv", "$ "+formatoFactura.getMontoIva());
        }
        contenido = contenido.replace("$fact.tot", "$ "+formatoFactura.getTotal());
        /*
        contenido = contenido.replace("$fact.val1", formatoFactura.getSuPago());
        contenido = contenido.replace("$fact.val2", formatoFactura.getSumaSuPago());
        contenido = contenido.replace("$fact.val3", formatoFactura.getSuVuelto());
        */
        contenido = contenido.replace("$fact.pie", this.piePagina);

        //System.out.println(contenido);
        if(Propiedades.getACLARACIONPIE()==1){
            contenido = contenido.replace("Factura electrónica emitida con", "");
            contenido = contenido.replace("manantialgestion.com", "");
        }

        return contenido;
    }

    private String procesamientoFc(String contenido) {

        contenido = contenido.replace("$com.local", formatoComerciante.getNombreDelLocal());
        
        contenido = contenido.replace("$com.cuit", formatoComerciante.getCuitLocal());
        contenido = contenido.replace("$com.ingresos", formatoComerciante.getIngresosBrutos());
        contenido = contenido.replace("$com.telefono", formatoComerciante.getTelefono());
        contenido = contenido.replace("$com.direccion", formatoComerciante.getDireccion());
        contenido = contenido.replace("$com.razon_social", formatoComerciante.getRazonSocial());
        contenido = contenido.replace("$com.condicion", formatoComerciante.getCondicionIva());

        contenido = contenido.replace("$fact.nro", formatoFactura.getNroFactura());
        contenido = contenido.replace("$fact.fecha", formatoFactura.getFecha());
        contenido = contenido.replace("$fact.hora", formatoFactura.getHora());
        /*
        contenido = contenido.replace("$fact.iva", formatoFactura.getIva());
        contenido = contenido.replace("$fact.iva1", formatoFactura.getIva1());
        contenido = contenido.replace("$fact.iva2", formatoFactura.getIva2());
        contenido = contenido.replace("$fact.iva3", formatoFactura.getIva3());
        */
        contenido = contenido.replace("$cli.cuit", formatoCliente.getCuitCliente());
        contenido = contenido.replace("$cli.condIva", formatoCliente.getCondIva());
        contenido = contenido.replace("$cli.cliente", formatoCliente.getNombreCliente());
        //contenido = contenido.replace("$cli.telefono", formatoCliente.getTelefonoCliente());
        contenido = contenido.replace("$cli.dir", formatoCliente.getDireccionCliente());

        contenido = contenido.replace("$productos", this.estructuracionArticulos());

        //contenido = contenido.replace("$fact.Niva", formatoFactura.getTotalSinIva());
        contenido = contenido.replace("$fact.sub","$ "+String.format("%9s", formatoFactura.getSubTotal()));
        //contenido = contenido.replace("$fact.ng", formatoFactura.getNoGravados());
        if(formatoFactura.getMontoIva()!=null){
        contenido = contenido.replace("$fact.Miv", "$ "+String.format("%9s",formatoFactura.getMontoIva()));
        }else{
            contenido = contenido.replace("$fact.Miv", "$ "+String.format("%9s","0.00"));
        }
        if(formatoFactura.getMontoIva1()!=null){
        contenido = contenido.replace("$fact.1Mi", "$ "+String.format("%9s",formatoFactura.getMontoIva1()));
        }else{
            contenido = contenido.replace("$fact.1Mi",  "$ "+String.format("%9s","0.00"));
        }
        if(formatoFactura.getMontoIva2()!=null){
        contenido = contenido.replace("$fact.2Mi", "$ "+String.format("%9s",formatoFactura.getMontoIva2()));
        }else{
            contenido = contenido.replace("$fact.2Mi",  "$ "+String.format("%9s","0.00"));
        }
        if(formatoFactura.getMontoIva3()!=null){
        contenido = contenido.replace("$fact.3Mi", "$ "+String.format("%9s",formatoFactura.getMontoIva3()));
        }else{
            contenido = contenido.replace("$fact.3Mi",  "$ "+String.format("%9s","0.00"));
        }
        
        contenido = contenido.replace("$fact.tot", "$ "+String.format("%9s",formatoFactura.getTotal()));
        //contenido = contenido.replace("$fact.val1", formatoFactura.getSuPago());
        //contenido = contenido.replace("$fact.val2", formatoFactura.getSumaSuPago());
        //contenido = contenido.replace("$fact.val3", formatoFactura.getSuVuelto());
        contenido = contenido.replace("$fact.pie", this.piePagina);
        contenido = contenido.replace("$fact.vtoc", this.piePagina1);
        if(Propiedades.getACLARACIONPIE()==1){
            contenido = contenido.replace("Factura electrónica emitida con", "");
            contenido = contenido.replace("manantialgestion.com", "");
        }
        //System.out.println(contenido);

        return contenido;
    }
    private String procesamientoFcB(String contenido) {

        contenido = contenido.replace("$com.local", formatoComerciante.getNombreDelLocal());
        
        contenido = contenido.replace("$com.cuit", formatoComerciante.getCuitLocal());
        contenido = contenido.replace("$com.ingresos", formatoComerciante.getIngresosBrutos());
        contenido = contenido.replace("$com.telefono", formatoComerciante.getTelefono());
        contenido = contenido.replace("$com.direccion", formatoComerciante.getDireccion());
        contenido = contenido.replace("$com.razon_social", formatoComerciante.getRazonSocial());
        contenido = contenido.replace("$com.condicion", formatoComerciante.getCondicionIva());

        contenido = contenido.replace("$fact.nro", formatoFactura.getNroFactura());
        contenido = contenido.replace("$fact.fecha", formatoFactura.getFecha());
        contenido = contenido.replace("$fact.hora", formatoFactura.getHora());
        /*
        contenido = contenido.replace("$fact.iva", formatoFactura.getIva());
        contenido = contenido.replace("$fact.iva1", formatoFactura.getIva1());
        contenido = contenido.replace("$fact.iva2", formatoFactura.getIva2());
        contenido = contenido.replace("$fact.iva3", formatoFactura.getIva3());
        */
        contenido = contenido.replace("$cli.cuit", formatoCliente.getCuitCliente());
        contenido = contenido.replace("$cli.condIva", formatoCliente.getCondIva());
        contenido = contenido.replace("$cli.cliente", formatoCliente.getNombreCliente());
        //contenido = contenido.replace("$cli.telefono", formatoCliente.getTelefonoCliente());
        contenido = contenido.replace("$cli.dir", formatoCliente.getDireccionCliente());

        contenido = contenido.replace("$productos", this.estructuracionArticulosB());

        //contenido = contenido.replace("$fact.Niva", formatoFactura.getTotalSinIva());
        contenido = contenido.replace("$fact.sub", "$ "+formatoFactura.getSubTotal());
        //contenido = contenido.replace("$fact.ng", formatoFactura.getNoGravados());
        /*
        contenido = contenido.replace("$fact.Miva", formatoFactura.getMontoIva());
        contenido = contenido.replace("$fact.Miva1", formatoFactura.getMontoIva1());
        contenido = contenido.replace("$fact.Miva2", formatoFactura.getMontoIva2());
        contenido = contenido.replace("$fact.Miva3", formatoFactura.getMontoIva3());
        */
        contenido = contenido.replace("$fact.tot", "$ "+formatoFactura.getTotal());
        //contenido = contenido.replace("$fact.val1", formatoFactura.getSuPago());
        //contenido = contenido.replace("$fact.val2", formatoFactura.getSumaSuPago());
        //contenido = contenido.replace("$fact.val3", formatoFactura.getSuVuelto());
        contenido = contenido.replace("$fact.pie", this.piePagina);
        contenido = contenido.replace("$fact.vtoc", this.piePagina1);
        if(Propiedades.getACLARACIONPIE()==1){
            contenido = contenido.replace("Factura electrónica emitida con", "");
            contenido = contenido.replace("manantialgestion.com", "");
        }
        //System.out.println(contenido);

        return contenido;
    }
    
    private String procesamientoRetiro(String contenido) {

        contenido = contenido.replace("$com.local", formatoComerciante.getNombreDelLocal());
        
        contenido = contenido.replace("$com.cuit", formatoComerciante.getCuitLocal());
        contenido = contenido.replace("$com.ingresos", formatoComerciante.getIngresosBrutos());
        contenido = contenido.replace("$com.telefono", formatoComerciante.getTelefono());
        contenido = contenido.replace("$com.direccion", formatoComerciante.getDireccion());
        contenido = contenido.replace("$com.razon_social", formatoComerciante.getRazonSocial());
        //contenido = contenido.replace("$com.condicion", formatoComerciante.getCondicionIva());

        contenido = contenido.replace("$fact.nro", formatoFactura.getNroFactura());
        contenido = contenido.replace("$fact.fecha", formatoFactura.getFecha());
        contenido = contenido.replace("$fact.hora", formatoFactura.getHora());
        contenido = contenido.replace("$fact.caja", formatoFactura.getSuPago());
        
        contenido = contenido.replace("$fact.tot", "$ "+formatoFactura.getTotal());
        
        //System.out.println(contenido);

        return contenido;
    }
    private String procesamientoCierre(String contenido) {

        contenido = contenido.replace("$com.local", formatoComerciante.getNombreDelLocal());
        
        contenido = contenido.replace("$com.cuit", formatoComerciante.getCuitLocal());
        contenido = contenido.replace("$com.ingresos", formatoComerciante.getIngresosBrutos());
        contenido = contenido.replace("$com.telefono", formatoComerciante.getTelefono());
        contenido = contenido.replace("$com.direccion", formatoComerciante.getDireccion());
        contenido = contenido.replace("$com.razon_social", formatoComerciante.getRazonSocial());
        

        contenido = contenido.replace("$fact.caja", formatoFactura.getSuPago());
        contenido = contenido.replace("$fact.fecha", formatoFactura.getFecha());
        contenido = contenido.replace("$fact.hora", formatoFactura.getHora());
        

        contenido = contenido.replace("$productos", this.estructuracionArticulosCierre());
        
        contenido = contenido.replace("$cli.saldoin", formatoPie.getSaldoInicial());
        contenido = contenido.replace("$cli.totalve", formatoPie.getTotalVentas());
        contenido = contenido.replace("$cli.gastos", formatoPie.getTotlaGastos());
        contenido = contenido.replace("$cli.retiros", formatoPie.getRetiros());
        contenido = contenido.replace("$cli.pagos", formatoPie.getPagos());
        
        
        
        //System.out.println(contenido);

        return contenido;
    }

    public String procesarTicket(int modelo) {
        String procesado = null;

        switch (modelo) {
            case 1:
                procesado = this.procesamientoFc(this.formatoTicketElectronico(this.idComprobante));
                //this.mostrarCodigoBarra();
                break;
            case 2:
                procesado = this.procesamientoFcB(this.formatoTicketElectronicoB(this.idComprobante));
                //this.mostrarCodigoBarra();
                break;
            case 28:
                procesado = this.procesamiento(this.formatoTicketPresu());
                //this.mostrarCodigoBarra();
                break;
            case 50:
                procesado = this.procesamientoRetiro(this.formatoRetiros());
                //this.mostrarCodigoBarra();
                break;
                case 55:
                procesado = this.procesamientoCierre(this.formatoCierreCaja());
                //this.mostrarCodigoBarra();
                break;
            default:
                procesado = this.procesamiento(this.formatoTicket1());
                break;
        }
        System.out.println("modelo :"+modelo);
        System.out.println(procesado);
        return procesado;
    }

}
