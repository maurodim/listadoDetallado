package objetos;

import com.itextpdf.text.DocumentException;
import conversiones.Numeros;
import feafip.bi.ClassFactory;
import feafip.bi.IwsPadron;
import feafip.bi.Iwsfev1;
import feafip.bi.TipoComprobante;
import interfaces.FacturableE;
import interfaces.Instalable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FacturaElectronica implements FacturableE, Instalable {

    private String valor;
    private String resultado;
    private String respuesta;
    private String cae;
    private String caeVto;
    private String fechaCae;
    private String afipQty;
    private String afipPlastId;
    private String afipPlastCbte;
    private Integer id;
    private Integer idFactura;
    private Integer idCliente;
    private String fecha;
    private String customerId;
    private String customerTypeDoc;
    private String tipoComprobante;
    private Double importeTotal;
    private Double importeNeto;
    private Double impuestoLiquido;
    private String condVta;
    private Integer estado;
    private Connection conexion;
    private String archivoKey;
    private String archivoCrt;
    private String condicionIvaVendedor;
    private int numeroPuntoDeVenta;
    private int tipoCompro;
    private TipoComprobante tipoComp;
    private String cuitVendedor;
    private int tipoVta;
    private ArrayList listadoIva;
    private ArrayList listadoTributos;
    private String razonSocial;
    private String direccionCliente;
    private String condicionIvaCliente;
    private ArrayList listadoDetalle;
    private int numeroTipoComprobante;
    private String descripcionTipoComprobante;
    private String nombreQr;
    private Integer idPedido;
    private String archivoPdf;
    PreparedStatement st;
    private String nombreV;
    private String cuitV;
    private String cIvaV;
    private String direccionV;
    private String telefonoV;
    private String ingBrutosV;
    private String inicioActV;
    private String razonSocialVendedor;
    private ArrayList lstOpcionales;
    private Double numeroC;
    
    public ArrayList getLstOpcionales() {
        return lstOpcionales;
    }
    

    public String getCuitVendedor() {
        return cuitVendedor;
    }

    public String getNombreV() {
        return nombreV;
    }

    public String getCuitV() {
        return cuitV;
    }

    public String getcIvaV() {
        return cIvaV;
    }

    public String getDireccionV() {
        return direccionV;
    }

    public String getTelefonoV() {
        return telefonoV;
    }

    public String getIngBrutosV() {
        return ingBrutosV;
    }

    public String getInicioActV() {
        return inicioActV;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public ArrayList getListadoIva() {
        return listadoIva;
    }

    public ArrayList getListadoTributos() {
        return listadoTributos;
    }

    public String getNombreQr() {
        return nombreQr;
    }

    public int getNumeroTipoComprobante() {
        return numeroTipoComprobante;
    }

    public String getDescripcionTipoComprobante() {
        return descripcionTipoComprobante;
    }

    public ArrayList getListadoDetalle() {
        return listadoDetalle;
    }

    public String getCondicionIvaCliente() {
        return condicionIvaCliente;
    }

    public int getNumeroPuntoDeVenta() {
        return numeroPuntoDeVenta;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getCondVta() {
        return condVta;
    }

    public void setCondVta(String condVta) {
        this.condVta = condVta;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTypeDoc() {
        return customerTypeDoc;
    }

    public void setCustomerTypeDoc(String customerTypeDoc) {
        this.customerTypeDoc = customerTypeDoc;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Double getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(Double importeNeto) {
        this.importeNeto = importeNeto;
    }

    public Double getImpuestoLiquido() {
        return impuestoLiquido;
    }

    public void setImpuestoLiquido(Double impuestoLiquido) {
        this.impuestoLiquido = impuestoLiquido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getCae() {
        return cae;
    }

    public void setCae(String cae) {
        this.cae = cae;
    }

    public String getCaeVto() {
        return caeVto;
    }

    public void setCaeVto(String caeVto) {
        this.caeVto = caeVto;
    }

    public String getFechaCae() {
        return fechaCae;
    }

    public void setFechaCae(String fechaCae) {
        this.fechaCae = fechaCae;
    }

    public String getAfipQty() {
        return afipQty;
    }

    public void setAfipQty(String afipQty) {
        this.afipQty = afipQty;
    }

    public String getAfipPlastId() {
        return afipPlastId;
    }

    public void setAfipPlastId(String afipPlastId) {
        this.afipPlastId = afipPlastId;
    }

    public String getAfipPlastCbte() {
        return afipPlastCbte;
    }

    public void setAfipPlastCbte(String afipPlastCbte) {
        this.afipPlastCbte = afipPlastCbte;
    }

    private Integer guardarEnFiscal() {
        String fecha = Numeros.ConvertirFechaFiscal();
        //Transaccionable tra=new Conecciones();

        String tipo = String.valueOf(this.numeroTipoComprobante);
        String numero = String.valueOf(this.afipPlastId);

        int tipoClienteId = Integer.parseInt(this.customerTypeDoc);
        /*
               if(this.numeroTipoComprobante==11){
                   tipoClienteId=99;
               }else{
                   tipoClienteId=80;
               }
         */
        String razonS = this.razonSocial;
        String cuit = this.customerId;
        if (cuit.equals("1")) {
            cuit = "0";
        }
        String sql = "insert into fiscal (fecha,tipo,pto,numero,gravado,impuesto,total,idcliente,tipoClienteId,razon,cuit) values (lpad(" + fecha + ",8,'0'),'" + tipo + "',lpad(" + this.numeroPuntoDeVenta + ",5,'0'),'" + numero + "'," + this.importeNeto + "," + this.impuestoLiquido + "," + this.importeTotal + "," + this.idCliente + "," + tipoClienteId + ",'" + razonS + "','" + cuit + "')";
        //System.out.println("fiscal: " + sql);
        try {
            st = this.conexion.prepareStatement(sql);
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FacturaElectronica.class.getName()).log(Level.SEVERE, null, ex);
        }
        //tra.guardarRegistro(sql);

        //sql="insert into facturas (idcliente,total,tipo,idusuario,idpedido,archivo,numerofactura,saldo,subtotal) values ("+this.idCliente+","+this.importeTotal+","+tipo+",1,"+this.idPedido+",'"+this.archivoPdf+"',"+numero+","+this.importeTotal+","+this.importeNeto+")";
        return Integer.parseInt(numero);
    }

    private Object leer() {
        // Los nombres de los parametros de las funciones se obtienen en FEAFIP.pdf

        //URLs de autenticacion y negocio. Cambiarlas por las de producción al implementarlas en el cliente(abajo)
        String URLWSAA = null;
        String URLWSW = null;
        System.out.println("ID PEDIDOOOOOO "+this.idPedido);
        if (this.idPedido == 1) {
            URLWSAA = "https://wsaahomo.afip.gov.ar/ws/services/LoginCms";
            URLWSW = "https://wswhomo.afip.gov.ar/wsfev1/service.asmx";
        } else {
            URLWSAA = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
            // Producción: https://wsaa.afip.gov.ar/ws/services/LoginCms
            URLWSW = "https://servicios1.afip.gov.ar/wsfev1/service.asmx";
            // Producción: https://servicios1.afip.gov.ar/wsfev1/service.asmx
            //URLWSW = "https://wswhomo.afip.gov.ar/wsfev1/service.asmx";
        }
        double nro;

        int ptoVta = this.numeroPuntoDeVenta; // ATENCION! SI RECIBE UN ERROR DE FECHA O NUMERO DE COMPROBANTE EN ESTA DEMO CAMBIE ESTE VALOR POR OTRO DE 1 A 9999

        //tipoComp = TipoComprobante.tcFacturaC; // Factura A(Ver excel referencias codigos AFIP)
        Format formatter = new SimpleDateFormat("yyyyMMdd");
        String FechaComp = formatter.format(new Date());

        Iwsfev1 wsfev1 = ClassFactory.createwsfev1();
        double cuitV = Double.parseDouble(this.cuitVendedor);
        double cuitC = Double.parseDouble(this.customerId);
        int customerTD = Integer.parseInt(this.customerTypeDoc);
        String montoT = String.valueOf(this.importeTotal);
        String montoN = String.valueOf(this.importeNeto);
        String montoI = String.valueOf(this.impuestoLiquido);
        //wsfev1.cuit(20229053834.0);  // Cuit del vendedor
        wsfev1.cuit(cuitV);
        //i BigDecimal roundFinalPrice=new BigDecimal(cuitV).setScale(2,BigDecimal.ROUND_HALF_UP);
        //String cuitClien=roundFinalPrice.toPlainString();

        //JOptionPane.showMessageDialog(null,"Fecha "+FechaComp);
        wsfev1.url(URLWSW);
        //System.out.println(URLWSW + " wsaa " + URLWSAA);
        if (wsfev1.login(this.archivoCrt, this.archivoKey, URLWSAA)) {
            if (!wsfev1.sfRecuperaLastCMP(ptoVta, this.tipoComp.comEnumValue())) {
                JOptionPane.showMessageDialog(null, wsfev1.errorDesc());
            } else {
                nro = wsfev1.sfLastCMP() + 1;
                wsfev1.reset();
                //System.out.println("importes total: " + this.importeTotal + " neto: " + this.importeNeto);
                if (this.condicionIvaVendedor.equals("1") || this.condicionIvaVendedor.equals("4")) {
                    //System.out.println(this.tipoVta + customerTD + cuitC + nro + nro + FechaComp + this.importeTotal + "0" + this.importeNeto + "0" + "--" + "--" + "--" + "PES" + "1");
                    if (this.tipoVta == 1) {
                        wsfev1.agregaFactura(this.tipoVta, customerTD, cuitC, nro, nro, FechaComp, this.importeTotal, 0, this.importeNeto, 0, "", "", "", "PES", 1);
                    }
                    if (this.tipoVta == 2) {
                        wsfev1.agregaFactura(this.tipoVta, customerTD, cuitC, nro, nro, FechaComp, this.importeTotal, 0, this.importeNeto, 0, FechaComp, FechaComp, FechaComp, "PES", 1);
                    }
                    //wsfev1.agregaFactura(2, 99,0.0, nro, nro, FechaComp, 1.5, 0, 1.5, 0, FechaComp, FechaComp, FechaComp, "PES", 1);

                    TiposIva tipoI;
                    Tributos tributos;
                    Opcionales opcional;
                    if (this.listadoIva != null) {
                        Iterator itI = this.listadoIva.listIterator();
                        //this.impuestoLiquido=0.00;
                        while (itI.hasNext()) {
                            tipoI = (TiposIva) itI.next();
                            double imponible = Math.round(tipoI.getBaseImponible() * 100.0) / 100.0;
                            double importe = Math.round(tipoI.getImporte() * 100.0) / 100.0;
                            wsfev1.agregaIVA(tipoI.getId(), imponible, importe); // Ver Excel de referencias de codigos AFIP
                            System.out.println("importes iva // imponible: " + tipoI.getBaseImponible() + " importe: " + tipoI.getImporte());
                            this.impuestoLiquido = this.impuestoLiquido + tipoI.getImporte();
                            //wsfev1.agregaIVA(1, 0,0); // Ver Excel de referencias de codigos AFIP
                        }
                    }
                    if (this.listadoTributos != null) {
                        Iterator itT = this.listadoTributos.listIterator();
                        while (itT.hasNext()) {
                            tributos = (Tributos) itT.next();
                            wsfev1.agregaTributo(tributos.getId(), tributos.getDescripcion(), tributos.getBaseImponible(), tributos.getAlicuota(), tributos.getImporte());
                            //System.out.println("importe tributo // imponible: " + tributos.getBaseImponible() + " importe: " + tributos.getImporte());

                        }
                    }
                    if(this.lstOpcionales != null){
                        Iterator itO=this.lstOpcionales.listIterator();
                        while(itO.hasNext()){
                            opcional =(Opcionales) itO.next();
                            wsfev1.agregaOpcional(opcional.getCodigo(), opcional.getTexto());
                        }
                    }
                } else {
                    System.out.println("fechas " + FechaComp);
                    if (this.tipoVta == 1) {
                        wsfev1.agregaFactura(this.tipoVta, customerTD, cuitC, nro, nro, FechaComp, this.importeTotal, 0, this.importeNeto, 0, "", "", "", "PES", 1);
                    }
                    if (this.tipoVta == 2) {
                        wsfev1.agregaFactura(this.tipoVta, customerTD, cuitC, nro, nro, FechaComp, this.importeTotal, 0, this.importeNeto, 0, FechaComp, FechaComp, FechaComp, "PES", 1);
                    }
//wsfev1.agregaFactura(this.tipoVta, customerTD, cuitC, nro, nro, FechaComp, this.importeTotal, 0, this.importeNeto, 0, FechaComp, FechaComp, FechaComp, "PES", 1);

                }
                if (!wsfev1.autorizar(ptoVta, (TipoComprobante) this.tipoComp)) {
                    System.out.println(wsfev1.errorDesc());
                    JOptionPane.showMessageDialog(null, wsfev1.errorDesc());
                } else {
                    if (wsfev1.sfResultado(0).equals("A")) {
                        //JOptionPane.showMessageDialog(null,"Felicitaciones! Si ve este mensaje instalo correctamente FEAFIP. CAE y Vencimiento: " + wsfev1.sfcae(0) + " " + wsfev1.sfVencimiento(0)+" numero comprobante "+nro);
                        this.cae = wsfev1.sfcae(0);
                        this.caeVto = wsfev1.sfVencimiento(0);
                        String num = String.valueOf(nro);
                        int nume = num.length();
                        nume = nume - 2;
                        num = num.substring(0, nume);
                        this.afipPlastId = num;
                        this.afipPlastCbte = num;
                        this.tipoComprobante = String.valueOf((TipoComprobante) this.tipoComp);
                        this.fechaCae = FechaComp;

                        //IContribuyente iContribuyente=ClassFactory.createContribuyente();
                        //System.out.println(iContribuyente.condicionIVADesc()+" numero iva "+iContribuyente.condicionIVA().comEnumValue());
                        this.estado = 1;
                        //ACA DEBERÍA PASAR LOS VALORES A PDF PARA QUE SE GENERE LA FACTURA
                        String dato = "Vendedor: " + this.cuitVendedor + " customerId:" + this.customerId + "cae:" + this.cae + "vto:" + this.caeVto + " Punto de venta:" + this.numeroPuntoDeVenta;
                        String nombreQr = "imagenes/" + num + "_" + this.descripcionTipoComprobante + ".gif";
                        GenerarQr qr = new GenerarQr(dato, nombreQr);
                        this.nombreQr = nombreQr;
                        //pdfsJavaGenerador pdf = new pdfsJavaGenerador(this.razonSocialVendedor, this.nombreV, this.cuitVendedor, this.condicionIvaVendedor, this.direccionV, this.telefonoV, this.ingBrutosV, this.inicioActV);
                        pdfFactura pdf = new pdfFactura(this.razonSocialVendedor, this.nombreV, this.cuitVendedor, this.condicionIvaVendedor, this.direccionV, this.telefonoV, this.ingBrutosV, this.inicioActV);
                        //EncabezadoPdf encab=new EncabezadoPdf();
                        pdf.setDoc(this);
                        pdf.setPunto(this.numeroPuntoDeVenta);
                        pdf.setNumero(nro);
                        try {
                            this.archivoPdf = pdf.run();
                        } catch (DocumentException ex) {
                            Logger.getLogger(FacturaElectronica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        numeroC=nro;
                        return nro;
                        //return this.guardarEnFiscal();
                    } else {
                        JOptionPane.showMessageDialog(null, wsfev1.autorizarRespuestaObs(0));
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, wsfev1.errorDesc());
        }

        return this;
    }

    @Override
    public Object recuperar(Object Fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer guardar(Object Fe) {
        Integer id = 0;

        return id;
    }

    @Override
    public Object modificar(Object Fe) {
        FacturaElectronica fE = new FacturaElectronica();

        return fE;
    }

    @Override
    public ArrayList listarPorEstado(Integer estado) {
        ArrayList listado = new ArrayList();

        return listado;
    }

    @Override
    public Object cargar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList listadoC) {
        DefaultTableModel listado = new DefaultTableModel();

        /*
        FacturaElectronica cotizacion;
        ClientesTango cliente;
        Facturar bus=new ClientesTango();
        Facturable ff=new Facturas();
        Facturas factura;
        Iterator iL=listadoC.listIterator();
        listado.addColumn("Fecha");
        listado.addColumn("Cliente");
        listado.addColumn("Nº Factura");
        listado.addColumn("Monto");
        listado.addColumn("Id Afip");
        Object[] fila=new Object[5];
        while(iL.hasNext()){
            cotizacion=(FacturaElectronica)iL.next();
            cliente=new ClientesTango();
            factura=new Facturas();
            fila[0]=String.valueOf(cotizacion.getFecha());
            cliente=(ClientesTango)bus.cargarPorCodigoAsignado(cotizacion.getIdCliente());
            fila[1]=cliente.getRazonSocial();
            fila[2]=cotizacion.getAfipPlastCbte();
            //factura=(Facturas)ff.
            fila[3]=String.valueOf(cotizacion.getImporteTotal());
            fila[4]=cotizacion.getAfipPlastId();
            listado.addRow(fila);
        }
         */
        return listado;
    }

    @Override
    public Object reEnviar(Object fe) {
        FacturaElectronica fE = new FacturaElectronica();

        return fE;

    }

    @Override
    public void eliminar(Object fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String reimprimir(Object fe) {

        return null;

    }

    @Override
    public String imprimir(Object fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean InstalarTablas() {
        //creacion de tabla facturaelectronica

        Boolean ver = true;
        /*
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro("CREATE TABLE facturaelectronica (id int(11) NOT NULL,cae varchar (20),cae_vto varchar(8),fecha_cae varchar(20),afipqty varchar(4),afipplastid varchar(20),afipplastcbte varchar(10),idfactura int(11) not null,idcliente int(11) not null,estado int(11) not null default '0',customerid varchar (11),customertypedoc varchar(2),tipo_comprobante varchar(1),importe_total varchar(30),importe_neto varchar(25),impto_liq varchar(25),fecha timestamp not null default current_timestamp)ENGINE=InnoDB DEFAULT CHARSET=utf8"); //(idcliente,total,tipo,idusuario,idpedido,idremito,numerofactura,estado,saldo,subtotal,descuento,porcentajeD)
        tra.guardarRegistro("ALTER TABLE facturaelectronica ADD PRIMARY KEY (id)");
        tra.guardarRegistro("ALTER TABLE facturaelectronica MODIFY id int(11) NOT NULL AUTO_INCREMENT");
        tra.guardarRegistro("alter table facturaelectronica modify tipo_comprobante varchar(2)");
        
         */
        //String sql="insert into facturaelectronica (cae,cae_vto,fecha_cae,afipqty,afipplastid,afipplastcbte,idfactura,idcliente,estado,customerid,customertypedoc,tipo_comprobante,importe_total,importe_neto,impto_liq) values ('"+ffE.getCae()+"','"+ffE.getCaeVto()+"','"+ffE.getFechaCae()+"','"+ffE.getAfipQty()+"','"+ffE.getAfipPlastId()+"','"+ffE.getAfipPlastCbte()+"',"+ffE.getIdFactura()+","+ffE.getIdCliente()+","+estado+",'"+ffE.getCustomerId()+"','"+ffE.getCustomerTypeDoc()+"','"+ffE.getTipoComprobante()+"','"+ffE.getImporteTotal()+"','"+ffE.getImporteNeto()+"','"+ffE.getImpuestoLiquido()+"')";
        return ver;
    }

    @Override
    public Boolean ActualizarTablas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer generar(Connection conexion, int Condicion, String archivoKey, String archivoCrt, Integer idCliente, String cuitCliente, int tipoComprobante, Double montoTotal, Double montoBruto, Double montoIva, int ptoDeVenta, String cuitVendedor, int tipoV, ArrayList lstI, ArrayList lstT, String razonSocial, String direccion, String condicionIvaCliente, ArrayList lstDetalle, Integer idPedido, String nombreVendedor, String razonSocialVend, String condIvaVendedor, String direccionVendedor, String telefonoVendedor, String ingBrutosVendedor, String inicioActVendedor,ArrayList listadoOpcionales) {
        FacturaElectronica fE = new FacturaElectronica();
        fE.listadoIva = new ArrayList();
        fE.listadoTributos = new ArrayList();
        fE.conexion = conexion;
        fE.condicionIvaVendedor = String.valueOf(Condicion);
        fE.archivoKey = archivoKey;
        fE.archivoCrt = archivoCrt;
        fE.idPedido = idPedido;
        //fE.archivoKey="clave.key";
        //fE.archivoCrt="certificado.crt";
        //fE.idPedido = 1;
        fE.idCliente = idCliente;
        fE.nombreV = nombreVendedor;
        fE.razonSocialVendedor = razonSocialVend;
        fE.cIvaV = condIvaVendedor;
        fE.direccionV = direccionVendedor;
        fE.telefonoV = telefonoVendedor;
        fE.ingBrutosV = ingBrutosVendedor;
        fE.inicioActV = inicioActVendedor;
        //System.out.println("cantidad " + cuitCliente.length());
        if (cuitCliente.length() == 8 || cuitCliente.length() == 11 || cuitCliente.length() == 1 || cuitCliente.length() == 7) {
            if (cuitCliente.length() == 1) {
                cuitCliente = "0";
            }
        } else {

            cuitCliente = JOptionPane.showInputDialog(null, "Ingrese numero de CUIT/CUIL o DNI Sin puntos ni guiones ", cuitCliente);
            if (idCliente.equals("0")) {
                cuitCliente = "0";
            }
        }
        cuitCliente = cuitCliente.replace("-", "");
        cuitCliente = cuitCliente.trim();
        Integer cantCuit = cuitCliente.length();
        int tipDocumento = 0;
        switch (cantCuit) {
            case 1:
                cuitCliente = "0";
                tipDocumento = 99;//SIN INDETIFICAR
                break;
            case 11:

                tipDocumento = 80;//CUIT

                break;
            case 8:
                tipDocumento = 96;//DNI
                break;
            case 7:
                tipDocumento = 96;//DNI
                break;
        }
        String tipoDocumentoCliente = String.valueOf(tipDocumento);

        fE.customerId = cuitCliente;
        fE.customerTypeDoc = String.valueOf(tipoDocumentoCliente);
        fE.numeroPuntoDeVenta = ptoDeVenta;
        fE.importeTotal = montoTotal;
        fE.importeNeto = montoBruto;
        fE.impuestoLiquido = montoIva;
        fE.tipoCompro = tipoComprobante;
        fE.cuitVendedor = cuitVendedor;
        fE.tipoVta = tipoV;
        fE.listadoIva = lstI;
        fE.listadoTributos = lstT;
        fE.lstOpcionales=listadoOpcionales;
        fE.razonSocial = razonSocial;
        fE.direccionCliente = direccion;
        fE.condicionIvaCliente = condicionIvaCliente;
        fE.listadoDetalle = lstDetalle;
        //if (fE.condicionIvaVendedor.equals("1") || fE.condicionIvaVendedor.equals("4")) { //segun tabla de tipos de contribuyentes - resp inscripto

        if (fE.tipoCompro == 6) {//antes 1
            fE.tipoComp = TipoComprobante.tcFacturaB;//factura B a consumidor final
        }
        if (fE.tipoCompro == 1) {//antes 2
            fE.tipoComp = TipoComprobante.tcFacturaA;//1 FACTURA A 
        }
        if (fE.tipoCompro == 2) {//antes 9
            fE.tipoComp = TipoComprobante.tcNotaDebitoA;//2
        }
        if (fE.tipoCompro == 3) {// antes 10
            fE.tipoComp = TipoComprobante.tcNotaCreditoA;//3 NOTA DE CREDITO A
        }
        if (fE.tipoCompro == 7) { // antes 11
            fE.tipoComp = TipoComprobante.tcNotaDebitoB;
        }
        if (fE.tipoCompro == 8) { // antes 12
            fE.tipoComp = TipoComprobante.tcNotaCreditoB;//tipComprobante=8;
        }
        if (fE.tipoCompro == 8) { // antes 8
            fE.tipoComp = TipoComprobante.tcNotaCreditoB;//NTA DE CREDITO B A CONS FINAL y exento
        }
        if (fE.tipoCompro == 6) { // antes 3
            fE.tipoComp = TipoComprobante.tcFacturaB;// factura B A EXENTO
        }
        // } else {
        if (fE.tipoCompro == 11) { // antes 1
            fE.tipoComp = TipoComprobante.tcFacturaC;
        }
        /*
            if (fE.tipoCompro == 11) { // antes 2
                fE.tipoComp = TipoComprobante.tcFacturaC;//1
            }
         */
        if (fE.tipoCompro == 12) { // antes 9
            fE.tipoComp = TipoComprobante.tcNotaDebitoC;//2
        }
        if (fE.tipoCompro == 13) { // antes 10
            fE.tipoComp = TipoComprobante.tcNotaCreditoC;//3
        }
        /*
            if (fE.tipoCompro == 11) { // antes 11
                fE.tipoComp = TipoComprobante.tcNotaDebitoC;
            }
            if (fE.tipoCompro == 12) { // antes 12
                fE.tipoComp = TipoComprobante.tcNotaCreditoC;
            }
         */
        //}
        if (fE.tipoComp.equals(TipoComprobante.tcFacturaA)) {
            fE.numeroTipoComprobante = 1;
        }
        if (fE.tipoComp.equals(TipoComprobante.tcNotaDebitoA)) {
            fE.numeroTipoComprobante = 2;
        }
        if (fE.tipoComp.equals(TipoComprobante.tcNotaCreditoA)) {
            fE.numeroTipoComprobante = 3;
        }
        if (fE.tipoComp.equals(TipoComprobante.tcFacturaB)) {
            fE.numeroTipoComprobante = 6;
        }
        if (fE.tipoComp.equals(TipoComprobante.tcNotaDebitoB)) {
            fE.numeroTipoComprobante = 7;
        }
        if (fE.tipoComp.equals(TipoComprobante.tcNotaCreditoB)) {
            fE.numeroTipoComprobante = 8;
        }
        if (fE.tipoComp.equals(TipoComprobante.tcFacturaC)) {
            fE.numeroTipoComprobante = 11;
        }
        if (fE.tipoComp.equals(TipoComprobante.tcNotaDebitoC)) {
            fE.numeroTipoComprobante = 12;
        }
        if (fE.tipoComp.equals(TipoComprobante.tcNotaCreditoC)) {
            fE.numeroTipoComprobante = 13;
        }

        fE.descripcionTipoComprobante = fE.tipoComp.name();

        //System.out.println("Descripcion tipo de comprobante " + fE.tipoComp.name());
        fE.leer();
        Integer nroC=fE.numeroC.intValue();
        return nroC;//fE.guardarEnFiscal();
    }

    @Override
    public String solicitarCertificado(String cuit1) {
        IwsPadron padron = ClassFactory.createwsPadron();
        double cuit = 20229053834.0;
        if (padron.descargarConstancia(cuit, "c:\\datos\\constancia.pdf")) {
            //System.out.println("Constancia descargada con éxito");
        } else {
            //System.out.println(padron.errorDesc());
        }
        return "Constancias\\" + cuit + "_constancia.pdf";
    }
}
