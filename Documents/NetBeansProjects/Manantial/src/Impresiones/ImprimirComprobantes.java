/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impresiones;

import Cajas.Cajas;
import ConfiguracionR.Propiedades;
import Conversores.NumberToLetterConverter;
import Conversores.Numeros;
import FacturaElectronica.Objetos.FacturaElectronica;
import FacturaElectronica.Objetos.DetalleFacturas;
import FacturaElectronica.Objetos.TiposIva;
import facturacion.clientes.Clientes;
import facturacion.clientes.Facturable;
import facturacion.clientes.FormasDePago;
import facturacion.clientes.MovimientosClientes;
import interfaceGraficasManantial.Inicio;
import interfaces.Personalizable;
import interfacesPrograma.Facturar;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import objetosR.Localidades;

/**
 *
 * @author Usuario
 */
public class ImprimirComprobantes {

    private FacturaElectronica doc = new FacturaElectronica();
    private ArrayList lstDetalle;
    private int punto;
    private Double numero;

    public void setLstDetalle(ArrayList lstDetalle) {
        this.lstDetalle = lstDetalle;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public void setNumero(Double numero) {
        this.numero = numero;
    }

    public void setDoc(FacturaElectronica doc) {
        this.doc = doc;
    }

    public void ImprimirPresupuesto(Integer idCotizacion, Integer tipo) {

        Facturable cotizable = new MovimientosClientes();
        MovimientosClientes cotizacion = new MovimientosClientes();
        cotizacion = (MovimientosClientes) cotizable.cargarEncabezadoFactura(idCotizacion, tipo);
        ArrayList listadoDetalle = new ArrayList();
        facturacion.clientes.DetalleFacturas detalleDeCotizacion = new facturacion.clientes.DetalleFacturas();
        Facturable cotiz = new facturacion.clientes.DetalleFacturas();
        listadoDetalle = cotiz.cargarDetallefactura(cotizacion.getId());
        Clientes cliente = new Clientes();
        Facturar factu = new Clientes();
        cliente = (Clientes) factu.cargarPorCodigoAsignado(cotizacion.getIdCliente());
        Localidades localidad = new Localidades();
        Personalizable pp = new Localidades();
        Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH);
        mes++;
        int ano = fecha.get(Calendar.YEAR);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fec = dia + "/" + mes + "/" + ano;
        String hrs = hora + "," + minuto + ":" + segundo;
        String nro = String.valueOf(cotizacion.getNumeroFactura());
        nro = String.format("%0" + (10 - nro.length()) + "d%s", 0, nro);
        String letras = NumberToLetterConverter.convertNumberToLetter(cotizacion.getTotal());
        String total = Numeros.ConvetirNumeroDosDigitos(cotizacion.getTotal());
        String descuento = Numeros.ConvetirNumeroDosDigitos(cotizacion.getDescuento());

        ImpresoraServiceImpl impresoraServicio = new ImpresoraServiceImpl();

        //-----------------TICKET DE COMPRA-------------------------------
        // Ejemplo para envio de informacion e impresion ticket
        // Creando las instancias
        ModeloTicket modeloTicket = new ModeloTicket();

        FormatoComerciante formatoComerciante = new FormatoComerciante();
        FormatoCliente formatoCliente = new FormatoCliente();
        List<FormatoArticulos> listaArticulos = new ArrayList();
        FormatoFactura formatoFactura = new FormatoFactura();

        // Rellenando cada instancia con los valores de interes
        formatoComerciante.setNombreDelLocal(Propiedades.getRAZONSOCIAL());
        formatoComerciante.setCuitLocal(Propiedades.getCUIT());
        //formatoComerciante.setIngresosBrutos("");
        formatoComerciante.setTelefono(Propiedades.getTELEFONO());
        formatoComerciante.setDireccion(Propiedades.getDIRECCION());
        //formatoComerciante.setRazonSocial("IVA RESPONSABLE INSCRIPTO");

        formatoCliente.setNombreCliente(cliente.getRazonSocial());
        formatoCliente.setCuitCliente(cliente.getNumeroDeCuit());
        formatoCliente.setCondIva(cliente.getCondicionIva());
        formatoCliente.setTelefonoCliente(cliente.getTelefono());
        formatoCliente.setDireccionCliente(cliente.getDireccion());

        //formatoFactura.setIva("21,00 %");
        formatoFactura.setNroFactura(nro);
        //formatoFactura.setTotalSinIva("3615,27");
        //formatoFactura.setSubTotal("3615,27");
        //formatoFactura.setMontoIva("759,30");
        formatoFactura.setNoGravados(descuento);

        formatoFactura.setTotal(total);

        //formatoFactura.setSuPago("4375,02");
        //formatoFactura.setSumaSuPago("SON PESOS: "+letras);
        //formatoFactura.setSuVuelto("0,00");
        Iterator it = listadoDetalle.listIterator();
        String descripcionArt = "";
        String unitario = "";
        String Ttotal = "";
        Double montoTo;
        while (it.hasNext()) {
            detalleDeCotizacion = (facturacion.clientes.DetalleFacturas) it.next();
            if (detalleDeCotizacion.getDescripcionArticulo() != null) {
                if (detalleDeCotizacion.getDescripcionArticulo().length() > 27) {
                    descripcionArt = detalleDeCotizacion.getDescripcionArticulo().substring(0, 27);
                } else {
                    descripcionArt = detalleDeCotizacion.getDescripcionArticulo();
                }

            } else {
                descripcionArt = "Rec.";
            }

            System.out.println("");
            unitario = "$ " + Numeros.ConvetirDoubleAString(detalleDeCotizacion.getPrecioUnitario());
            montoTo = detalleDeCotizacion.getPrecioUnitario() * detalleDeCotizacion.getCantidad();
            Ttotal = Numeros.ConvetirDoubleAString(montoTo);
            descripcionArt = String.format("%-27s", descripcionArt);
            //descripcionArt=descripcionArt.replace("0"," ");
            unitario = String.format("%9s", unitario);
            Ttotal = String.format("%9s", Ttotal);
            listaArticulos.add(new FormatoArticulos(String.valueOf(detalleDeCotizacion.getIdArticulo()), descripcionArt, String.valueOf(detalleDeCotizacion.getCantidad()), Ttotal, unitario, "0.00", Numeros.ConvetirDoubleAString(detalleDeCotizacion.getPrecioUnitario())));

        }

        modeloTicket.setFormatoComerciante(formatoComerciante);
        modeloTicket.setFormatoCliente(formatoCliente);
        modeloTicket.setFormatoFactura(formatoFactura);
        modeloTicket.setArticulos(listaArticulos);
        //modeloTicket.setCodigoBarra("9781935182618");
        modeloTicket.setPiePagina("DOCUMENTO NO VALIDO COMO FACTURA...!!!");

        // Imprimiendo ticket
        String ticketFull = modeloTicket.procesarTicket(28);
        impresoraServicio.imprimirTicket(ticketFull);

    }

    public void ImprimirTicketFiscal(String razonSocialVendedor, String nombreVendedor, String cVendedor, String cIva, String direccionVendedor, String telefonoVendedor, String iBrutos, String incioActividades) {

        String condIvaTxt = null;
        if (cIva.equals("1")) {
            condIvaTxt = "Resp. Inscripto";
        }
        if (cIva.equals("4")) {
            condIvaTxt = "Sujeto Exento";
        }
        if (cIva.equals("5")) {
            condIvaTxt = "Cons Final";
        }
        if (cIva.equals("6")) {
            condIvaTxt = "Resp. Monotributo";
        }

        int comF = 0;
        if (doc.getTipoComprobante().equals("tcFacturaA")) {
            comF = 1;
        }
        if (doc.getTipoComprobante().equals("tcNotaDebitoA")) {
            comF = 2;
        }
        if (doc.getTipoComprobante().equals("tcNotaCreditoA")) {
            comF = 3;
        }
        if (doc.getTipoComprobante().equals("tcFacturaB")) {
            comF = 6;
        }
        if (doc.getTipoComprobante().equals("tcNotaDebitoB")) {
            comF = 7;
        }
        if (doc.getTipoComprobante().equals("tcNotaCreditoB")) {
            comF = 8;
        }
        if (doc.getTipoComprobante().equals("tcFacturaC")) {
            comF = 11;
        }
        if (doc.getTipoComprobante().equals("tcNotaDebitoC")) {
            comF = 12;
        }
        if (doc.getTipoComprobante().equals("tcNotaCreditoC")) {
            comF = 13;
        }
        String nn = String.valueOf(this.numero);
        String nro = "000" + this.punto + "-" + String.format("%0" + (8 - nn.length()) + "d%s", 0, nn);
        int posic = nro.length();
        posic = posic - 2;
        nro = nro.substring(0, posic);

        ImpresoraServiceImpl impresoraServicio = new ImpresoraServiceImpl();

        //-----------------TICKET DE COMPRA-------------------------------
        // Ejemplo para envio de informacion e impresion ticket
        // Creando las instancias
        ModeloTicket modeloTicket = new ModeloTicket();

        FormatoComerciante formatoComerciante = new FormatoComerciante();
        FormatoCliente formatoCliente = new FormatoCliente();
        List<FormatoArticulos> listaArticulos = new ArrayList();
        FormatoFactura formatoFactura = new FormatoFactura();

        // Rellenando cada instancia con los valores de interes
        formatoComerciante.setRazonSocial(nombreVendedor);
        formatoComerciante.setNombreDelLocal(razonSocialVendedor);
        formatoComerciante.setCuitLocal(cVendedor);
        formatoComerciante.setIngresosBrutos(iBrutos);
        formatoComerciante.setTelefono(telefonoVendedor);
        formatoComerciante.setDireccion(direccionVendedor);
        formatoComerciante.setCondicionIva(condIvaTxt);

        formatoCliente.setNombreCliente(doc.getRazonSocial());
        formatoCliente.setCuitCliente(doc.getCustomerId());
        formatoCliente.setCondIva(doc.getCondicionIvaCliente());
        //formatoCliente.setTelefonoCliente(doc.get);
        formatoCliente.setDireccionCliente(doc.getDireccionCliente());

        String totalF = Numeros.ConvertirNumero(doc.getImporteTotal());
        Double sub = doc.getImporteNeto();
        Double iva = doc.getImpuestoLiquido();
        String subTotal = "$ " + Numeros.ConvertirNumero(sub);
        String ivaS = "$ " + Numeros.ConvertirNumero(iva);

        if (doc.getListadoIva() != null) {
            TiposIva tipos = (TiposIva) doc.getListadoIva().get(0);
            switch (tipos.getId()) {
                case 3:

            }
            formatoFactura.setIva(tipos.getDescripcion());
            //tipos = (TiposIva) doc.getListadoIva().get(1);

        } else {
            formatoFactura.setIva("00,00 %");
        }
        formatoFactura.setNroFactura(nro);
        //formatoFactura.setTotalSinIva("3615,27");
        formatoFactura.setSubTotal(totalF);
        formatoFactura.setMontoIva(ivaS);
        //formatoFactura.setNoGravados("0,00");

        formatoFactura.setTotal(totalF);

        //formatoFactura.setSuPago("4375,02");
        //formatoFactura.setSumaSuPago("4375,02");
        //formatoFactura.setSuVuelto("0,00");
        Iterator it = doc.getListadoDetalle().listIterator();
        DetalleFacturas saldo;

        String monto;
        String descripcion;
        String total;
        String descripcionArt;
        int items = 0;
        double tot;
        String totUni;
        while (it.hasNext()) {
            saldo = (DetalleFacturas) it.next();
            //vencimiento=saldo.getVencimientoString();

            descripcion = "Numero Resumen de cta ";

            monto = Numeros.ConvertirNumero(saldo.getPrecioUnitario());
            //recargo = "";
            total = "nada";
            //recargo=String.valueOf(saldo.getRecargo());
            //tot=tot + saldo.getTotal();
            //total=String.valueOf(saldo.getTotal());

            if (saldo.getIdArticulo() == 0) {
                items = 1;
            }
            if (items == 1) {

                if (saldo.getDescripcion().length() > 27) {
                    descripcionArt = saldo.getDescripcion().substring(0, 27);
                } else {
                    descripcionArt = saldo.getDescripcion();
                }
                totUni = "";
            } else {

                if (saldo.getDescripcion() != null) {
                    if (saldo.getDescripcion().length() > 27) {
                        descripcionArt = saldo.getDescripcion().substring(0, 27);
                    } else {
                        descripcionArt = saldo.getDescripcion();
                    }
                } else {
                    descripcionArt = "";
                }

                tot = saldo.getCantidad() * saldo.getPrecioUnitario();
                totUni = Numeros.ConvetirNumeroDosDigitos(tot);
                //tot=tot * 1.21;
            }
            descripcionArt = String.format("%-27s", descripcionArt);
            //descripcionArt=descripcionArt.replace("0"," ");
            totUni = String.format("%9s", totUni);
            //totUni=String.format("%- "+(13 - totUni.length())+"d%s","_",totUni);
            listaArticulos.add(new FormatoArticulos(String.valueOf(saldo.getIdArticulo()), descripcionArt, String.valueOf(saldo.getCantidad()), totUni, Numeros.ConvertirNumero(saldo.getPrecioUnitario()), null, null));

        }

        modeloTicket.setFormatoComerciante(formatoComerciante);
        modeloTicket.setFormatoCliente(formatoCliente);
        modeloTicket.setFormatoFactura(formatoFactura);
        modeloTicket.setArticulos(listaArticulos);

        String ano = doc.getCaeVto().substring(0, 4);
        String mm = doc.getCaeVto().substring(4, 6);
        String dd = doc.getCaeVto().substring(6);
        String vencimiento = "C.A.E. Nº: " + doc.getCae();
        String vencimiento1 = "Fecha de Vto. C.A.E.: " + dd + "/" + mm + "/" + ano;

        //modeloTicket.setCodigoBarra("9781935182618");
        modeloTicket.setPiePagina(vencimiento);
        modeloTicket.setPiePagina1(vencimiento1);

        // Imprimiendo ticket
        modeloTicket.setIdComprobante(comF);
        String ticketFull = modeloTicket.procesarTicket(2);
        impresoraServicio.imprimirTicket(ticketFull);

    }

    public void ImprimirTicketFiscalA(String razonSocialVendedor, String nombreVendedor, String cVendedor, String cIva, String direccionVendedor, String telefonoVendedor, String iBrutos, String incioActividades) {

        String condIvaTxt = null;
        if (cIva.equals("1")) {
            condIvaTxt = "Resp. Inscripto";
        }
        if (cIva.equals("4")) {
            condIvaTxt = "Sujeto Exento";
        }
        if (cIva.equals("5")) {
            condIvaTxt = "Cons Final";
        }
        if (cIva.equals("6")) {
            condIvaTxt = "Resp. Monotributo";
        }

        int comF = 0;
        if (doc.getTipoComprobante().equals("tcFacturaA")) {
            comF = 1;
        }
        if (doc.getTipoComprobante().equals("tcNotaDebitoA")) {
            comF = 2;
        }
        if (doc.getTipoComprobante().equals("tcNotaCreditoA")) {
            comF = 3;
        }
        if (doc.getTipoComprobante().equals("tcFacturaB")) {
            comF = 6;
        }
        if (doc.getTipoComprobante().equals("tcNotaDebitoB")) {
            comF = 7;
        }
        if (doc.getTipoComprobante().equals("tcNotaCreditoB")) {
            comF = 8;
        }
        if (doc.getTipoComprobante().equals("tcFacturaC")) {
            comF = 11;
        }
        if (doc.getTipoComprobante().equals("tcNotaDebitoC")) {
            comF = 12;
        }
        if (doc.getTipoComprobante().equals("tcNotaCreditoC")) {
            comF = 13;
        }
        String nn = String.valueOf(this.numero);
        String nro = "000" + this.punto + "-" + String.format("%0" + (8 - nn.length()) + "d%s", 0, nn);
        int posic = nro.length();
        posic = posic - 2;
        nro = nro.substring(0, posic);

        ImpresoraServiceImpl impresoraServicio = new ImpresoraServiceImpl();

        //-----------------TICKET DE COMPRA-------------------------------
        // Ejemplo para envio de informacion e impresion ticket
        // Creando las instancias
        ModeloTicket modeloTicket = new ModeloTicket();

        FormatoComerciante formatoComerciante = new FormatoComerciante();
        FormatoCliente formatoCliente = new FormatoCliente();
        List<FormatoArticulos> listaArticulos = new ArrayList();
        FormatoFactura formatoFactura = new FormatoFactura();

        // Rellenando cada instancia con los valores de interes
        formatoComerciante.setRazonSocial(nombreVendedor);
        formatoComerciante.setNombreDelLocal(razonSocialVendedor);

        formatoComerciante.setCuitLocal(cVendedor);
        formatoComerciante.setIngresosBrutos(iBrutos);
        formatoComerciante.setTelefono(telefonoVendedor);
        formatoComerciante.setDireccion(direccionVendedor);
        formatoComerciante.setCondicionIva(condIvaTxt);

        formatoCliente.setNombreCliente(doc.getRazonSocial());
        formatoCliente.setCuitCliente(doc.getCustomerId());
        formatoCliente.setCondIva(doc.getCondicionIvaCliente());
        //formatoCliente.setTelefonoCliente(doc.get);
        formatoCliente.setDireccionCliente(doc.getDireccionCliente());

        String totalF = Numeros.ConvertirNumero(doc.getImporteTotal());
        Double sub = doc.getImporteNeto();
        Double iva = doc.getImpuestoLiquido();
        String subTotal = Numeros.ConvertirNumero(sub);
        String ivaS = "$ " + Numeros.ConvertirNumero(iva);

        if (doc.getListadoIva() != null) {
            TiposIva tipos;
            //formatoFactura.setIva(tipos.getDescripcion());
            Iterator itT = doc.getListadoIva().listIterator();
            while (itT.hasNext()) {
                tipos = (TiposIva) itT.next();
                switch (tipos.getId()) {
                    case 3:
                        formatoFactura.setMontoIva(Numeros.ConvertirNumero(tipos.getImporte()));
                        break;
                    case 4:
                        formatoFactura.setMontoIva1(Numeros.ConvertirNumero(tipos.getImporte()));
                        break;
                    case 5:
                        formatoFactura.setMontoIva2(Numeros.ConvertirNumero(tipos.getImporte()));
                        break;
                    case 6:
                        formatoFactura.setMontoIva3(Numeros.ConvertirNumero(tipos.getImporte()));
                        break;
                }
            }
            //tipos = (TiposIva) doc.getListadoIva().get(1);

        } else {
            formatoFactura.setIva("00,00 %");
        }
        formatoFactura.setNroFactura(nro);
        //formatoFactura.setTotalSinIva("3615,27");
        formatoFactura.setSubTotal(subTotal);
        //formatoFactura.setMontoIva(ivaS);
        //formatoFactura.setNoGravados("0,00");

        formatoFactura.setTotal(totalF);

        //formatoFactura.setSuPago("4375,02");
        //formatoFactura.setSumaSuPago("4375,02");
        //formatoFactura.setSuVuelto("0,00");
        Iterator it = doc.getListadoDetalle().listIterator();
        DetalleFacturas saldo;

        String monto;
        String descripcion;
        String total;
        String descripcionArt;
        int items = 0;
        double tot;
        String totUni;
        while (it.hasNext()) {
            saldo = (DetalleFacturas) it.next();
            //vencimiento=saldo.getVencimientoString();

            descripcion = "Numero Resumen de cta ";

            monto = Numeros.ConvertirNumero(saldo.getPrecioUnitario());
            //recargo = "";
            total = "nada";
            //recargo=String.valueOf(saldo.getRecargo());
            //tot=tot + saldo.getTotal();
            //total=String.valueOf(saldo.getTotal());

            if (saldo.getIdArticulo() == 0) {
                items = 1;
            }
            if (items == 1) {

                if (saldo.getDescripcion().length() > 27) {
                    descripcionArt = saldo.getDescripcion().substring(0, 27);
                } else {
                    descripcionArt = saldo.getDescripcion();
                }
                totUni = "";
            } else {

                if (saldo.getDescripcion() != null) {
                    if (saldo.getDescripcion().length() > 27) {
                        descripcionArt = saldo.getDescripcion().substring(0, 27);
                    } else {
                        descripcionArt = saldo.getDescripcion();
                    }
                } else {
                    descripcionArt = "";
                }

                //as
                tot = saldo.getCantidad() * saldo.getPrecioGravadoArticulo();
                totUni = Numeros.ConvetirNumeroDosDigitos(tot);
                //tot=tot * 1.21;
            }
            descripcionArt = String.format("%-27s", descripcionArt);
            //descripcionArt=descripcionArt.replace("0"," ");
            totUni = String.format("%9s", totUni);
            listaArticulos.add(new FormatoArticulos(String.valueOf(saldo.getIdArticulo()), descripcionArt, String.valueOf(saldo.getCantidad()), totUni, Numeros.ConvertirNumero(saldo.getPrecioGravadoArticulo()), saldo.getAlicuota(), null));

        }

        modeloTicket.setFormatoComerciante(formatoComerciante);
        modeloTicket.setFormatoCliente(formatoCliente);
        modeloTicket.setFormatoFactura(formatoFactura);
        modeloTicket.setArticulos(listaArticulos);

        String ano = doc.getCaeVto().substring(0, 4);
        String mm = doc.getCaeVto().substring(4, 6);
        String dd = doc.getCaeVto().substring(6);
        String vencimiento = "C.A.E. Nº: " + doc.getCae();
        String vencimiento1 = "Fecha de Vto. C.A.E.: " + dd + "/" + mm + "/" + ano;

        //modeloTicket.setCodigoBarra("9781935182618");
        modeloTicket.setPiePagina(vencimiento);
        modeloTicket.setPiePagina1(vencimiento1);

        // Imprimiendo ticket
        modeloTicket.setIdComprobante(comF);
        String ticketFull = modeloTicket.procesarTicket(1);
        impresoraServicio.imprimirTicket(ticketFull);
    }

    public void ReImprimirFiscalB(Integer idCotizacion, Integer tipo) {
        
        Facturable cotizable = new MovimientosClientes();
        MovimientosClientes cotizacion = new MovimientosClientes();
        cotizacion = (MovimientosClientes) cotizable.cargarEncabezadoFactura(idCotizacion, tipo);
        ArrayList listadoDetalle = new ArrayList();
        facturacion.clientes.DetalleFacturas detalleDeCotizacion = new facturacion.clientes.DetalleFacturas();
        Facturable cotiz = new facturacion.clientes.DetalleFacturas();
        listadoDetalle = cotiz.cargarDetallefactura(cotizacion.getId());
        Clientes cliente = new Clientes();
        Facturar factu = new Clientes();
        cliente = (Clientes) factu.cargarPorCodigoAsignado(cotizacion.getIdCliente());
        Localidades localidad = new Localidades();
        Personalizable pp = new Localidades();
        Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH);
        mes++;
        int ano = fecha.get(Calendar.YEAR);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fec = dia + "/" + mes + "/" + ano;
        String hrs = hora + "," + minuto + ":" + segundo;
        String nro = String.valueOf(cotizacion.getNumeroFactura());
        nro = String.format("%0" + (10 - nro.length()) + "d%s", 0, nro);
        String letras = NumberToLetterConverter.convertNumberToLetter(cotizacion.getTotal());
        String total = Numeros.ConvetirNumeroDosDigitos(cotizacion.getTotal());
        String descuento = Numeros.ConvetirNumeroDosDigitos(cotizacion.getDescuento());

        ImpresoraServiceImpl impresoraServicio = new ImpresoraServiceImpl();

        //-----------------TICKET DE COMPRA-------------------------------
        // Ejemplo para envio de informacion e impresion ticket
        // Creando las instancias
        ModeloTicket modeloTicket = new ModeloTicket();

        FormatoComerciante formatoComerciante = new FormatoComerciante();
        FormatoCliente formatoCliente = new FormatoCliente();
        List<FormatoArticulos> listaArticulos = new ArrayList();
        FormatoFactura formatoFactura = new FormatoFactura();

        // Rellenando cada instancia con los valores de interes
        formatoComerciante.setNombreDelLocal(Propiedades.getNOMBRECOMERCIO());
        formatoComerciante.setCuitLocal(Propiedades.getCUIT());
        //formatoComerciante.setIngresosBrutos("");
        formatoComerciante.setTelefono(Propiedades.getTELEFONO());
        formatoComerciante.setDireccion(Propiedades.getDIRECCION());
        //formatoComerciante.setRazonSocial("IVA RESPONSABLE INSCRIPTO");

        formatoCliente.setNombreCliente(cliente.getRazonSocial());
        formatoCliente.setCuitCliente(cliente.getNumeroDeCuit());
        formatoCliente.setCondIva(cliente.getCondicionIva());
        formatoCliente.setTelefonoCliente(cliente.getTelefono());
        formatoCliente.setDireccionCliente(cliente.getDireccion());

        String totalF = Numeros.ConvertirNumero(doc.getImporteTotal());
        Double sub = doc.getImporteNeto();
        Double iva = doc.getImpuestoLiquido();
        String subTotal = "$ " + Numeros.ConvertirNumero(sub);
        String ivaS = "$ " + Numeros.ConvertirNumero(iva);

        if (doc.getListadoIva() != null) {
            TiposIva tipos = (TiposIva) doc.getListadoIva().get(0);
            switch (tipos.getId()) {
                case 3:

            }
            formatoFactura.setIva(tipos.getDescripcion());
            //tipos = (TiposIva) doc.getListadoIva().get(1);

        } else {
            formatoFactura.setIva("00,00 %");
        }
        formatoFactura.setNroFactura(nro);
        //formatoFactura.setTotalSinIva("3615,27");
        formatoFactura.setSubTotal(totalF);
        formatoFactura.setMontoIva(ivaS);
        //formatoFactura.setNoGravados("0,00");

        formatoFactura.setTotal(totalF);

        //formatoFactura.setSuPago("4375,02");
        //formatoFactura.setSumaSuPago("4375,02");
        //formatoFactura.setSuVuelto("0,00");
        Iterator it = doc.getListadoDetalle().listIterator();
        DetalleFacturas saldo;

        String monto;
        String descripcion;
        String total1;
        String descripcionArt;
        int items = 0;
        double tot;
        String totUni;
        while (it.hasNext()) {
            saldo = (DetalleFacturas) it.next();
            //vencimiento=saldo.getVencimientoString();

            descripcion = "Numero Resumen de cta ";

            monto = Numeros.ConvertirNumero(saldo.getPrecioUnitario());
            //recargo = "";
            total1 = "nada";
            //recargo=String.valueOf(saldo.getRecargo());
            //tot=tot + saldo.getTotal();
            //total=String.valueOf(saldo.getTotal());

            if (saldo.getIdArticulo() == 0) {
                items = 1;
            }
            if (items == 1) {

                if (saldo.getDescripcion().length() > 27) {
                    descripcionArt = saldo.getDescripcion().substring(0, 27);
                } else {
                    descripcionArt = saldo.getDescripcion();
                }
                totUni = "";
            } else {

                if (saldo.getDescripcion() != null) {
                    if (saldo.getDescripcion().length() > 27) {
                        descripcionArt = saldo.getDescripcion().substring(0, 27);
                    } else {
                        descripcionArt = saldo.getDescripcion();
                    }
                } else {
                    descripcionArt = "";
                }

                tot = saldo.getCantidad() * saldo.getPrecioUnitario();
                totUni = Numeros.ConvetirNumeroDosDigitos(tot);
                //tot=tot * 1.21;
            }
            descripcionArt = String.format("%-27s", descripcionArt);
            //descripcionArt=descripcionArt.replace("0"," ");
            totUni = String.format("%9s", totUni);
            //totUni=String.format("%- "+(13 - totUni.length())+"d%s","_",totUni);
            listaArticulos.add(new FormatoArticulos(String.valueOf(saldo.getIdArticulo()), descripcionArt, String.valueOf(saldo.getCantidad()), totUni, Numeros.ConvertirNumero(saldo.getPrecioUnitario()), null, null));

        }

        modeloTicket.setFormatoComerciante(formatoComerciante);
        modeloTicket.setFormatoCliente(formatoCliente);
        modeloTicket.setFormatoFactura(formatoFactura);
        modeloTicket.setArticulos(listaArticulos);

        /*
        String ano = doc.getCaeVto().substring(0, 4);
            String mm = doc.getCaeVto().substring(4, 6);
            String dd = doc.getCaeVto().substring(6);
            String vencimiento = "C.A.E. Nº: " + doc.getCae();
            String vencimiento1 = "Fecha de Vto. C.A.E.: " + dd + "/" + mm + "/" + ano;
        
        //modeloTicket.setCodigoBarra("9781935182618");
        modeloTicket.setPiePagina(vencimiento);
        modeloTicket.setPiePagina1(vencimiento1);
        
       
        // Imprimiendo ticket
        modeloTicket.setIdComprobante(comF);
        String ticketFull = modeloTicket.procesarTicket(2);
        impresoraServicio.imprimirTicket(ticketFull);
         */
    }

    public void ReImprimirFiscalA(Integer idCotizacion, Integer tipo) {
        Facturable cotizable = new MovimientosClientes();
        MovimientosClientes cotizacion = new MovimientosClientes();
        cotizacion = (MovimientosClientes) cotizable.cargarEncabezadoFactura(idCotizacion, tipo);
        ArrayList listadoDetalle = new ArrayList();
        facturacion.clientes.DetalleFacturas detalleDeCotizacion = new facturacion.clientes.DetalleFacturas();
        Facturable cotiz = new facturacion.clientes.DetalleFacturas();
        listadoDetalle = cotiz.cargarDetallefactura(cotizacion.getId());
        Clientes cliente = new Clientes();
        Facturar factu = new Clientes();
        cliente = (Clientes) factu.cargarPorCodigoAsignado(cotizacion.getIdCliente());
        Localidades localidad = new Localidades();
        Personalizable pp = new Localidades();
        Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH);
        mes++;
        int ano = fecha.get(Calendar.YEAR);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fec = dia + "/" + mes + "/" + ano;
        String hrs = hora + "," + minuto + ":" + segundo;
        String nro = String.valueOf(cotizacion.getNumeroFactura());
        nro = String.format("%0" + (10 - nro.length()) + "d%s", 0, nro);
        String letras = NumberToLetterConverter.convertNumberToLetter(cotizacion.getTotal());
        String total = Numeros.ConvetirNumeroDosDigitos(cotizacion.getTotal());
        String descuento = Numeros.ConvetirNumeroDosDigitos(cotizacion.getDescuento());

        ImpresoraServiceImpl impresoraServicio = new ImpresoraServiceImpl();

        //-----------------TICKET DE COMPRA-------------------------------
        // Ejemplo para envio de informacion e impresion ticket
        // Creando las instancias
        ModeloTicket modeloTicket = new ModeloTicket();

        FormatoComerciante formatoComerciante = new FormatoComerciante();
        FormatoCliente formatoCliente = new FormatoCliente();
        List<FormatoArticulos> listaArticulos = new ArrayList();
        FormatoFactura formatoFactura = new FormatoFactura();

        // Rellenando cada instancia con los valores de interes
        formatoComerciante.setNombreDelLocal(Propiedades.getNOMBRECOMERCIO());
        formatoComerciante.setCuitLocal(Propiedades.getCUIT());
        //formatoComerciante.setIngresosBrutos("");
        formatoComerciante.setTelefono(Propiedades.getTELEFONO());
        formatoComerciante.setDireccion(Propiedades.getDIRECCION());
        //formatoComerciante.setRazonSocial("IVA RESPONSABLE INSCRIPTO");

        formatoCliente.setNombreCliente(cliente.getRazonSocial());
        formatoCliente.setCuitCliente(cliente.getNumeroDeCuit());
        formatoCliente.setCondIva(cliente.getCondicionIva());
        formatoCliente.setTelefonoCliente(cliente.getTelefono());
        formatoCliente.setDireccionCliente(cliente.getDireccion());

        String totalF = Numeros.ConvetirNumeroDosDigitos(doc.getImporteTotal());
        Double sub = doc.getImporteNeto();
        Double iva = doc.getImpuestoLiquido();
        String subTotal = Numeros.ConvetirNumeroDosDigitos(sub);
        String ivaS = "$ " + Numeros.ConvertirNumero(iva);

        if (doc.getListadoIva() != null) {
            TiposIva tipos;
            //formatoFactura.setIva(tipos.getDescripcion());
            Iterator itT = doc.getListadoIva().listIterator();
            while (itT.hasNext()) {
                tipos = (TiposIva) itT.next();
                switch (tipos.getId()) {
                    case 3:
                        formatoFactura.setMontoIva(Numeros.ConvertirNumero(tipos.getImporte()));
                        break;
                    case 4:
                        formatoFactura.setMontoIva1(Numeros.ConvertirNumero(tipos.getImporte()));
                        break;
                    case 5:
                        formatoFactura.setMontoIva2(Numeros.ConvertirNumero(tipos.getImporte()));
                        break;
                    case 6:
                        formatoFactura.setMontoIva3(Numeros.ConvertirNumero(tipos.getImporte()));
                        break;
                }
            }
            //tipos = (TiposIva) doc.getListadoIva().get(1);

        } else {
            formatoFactura.setIva("00,00 %");
        }
        formatoFactura.setNroFactura(nro);
        //formatoFactura.setTotalSinIva("3615,27");
        formatoFactura.setSubTotal(subTotal);
        //formatoFactura.setMontoIva(ivaS);
        //formatoFactura.setNoGravados("0,00");

        formatoFactura.setTotal(totalF);

        //formatoFactura.setSuPago("4375,02");
        //formatoFactura.setSumaSuPago("4375,02");
        //formatoFactura.setSuVuelto("0,00");
        Iterator it = doc.getListadoDetalle().listIterator();
        DetalleFacturas saldo;

        String monto;
        String descripcion;
        String total1;
        String descripcionArt;
        int items = 0;
        double tot;
        String totUni;
        while (it.hasNext()) {
            saldo = (DetalleFacturas) it.next();
            //vencimiento=saldo.getVencimientoString();

            descripcion = "Numero Resumen de cta ";

            monto = Numeros.ConvertirNumero(saldo.getPrecioUnitario());
            //recargo = "";
            total1 = "nada";
            //recargo=String.valueOf(saldo.getRecargo());
            //tot=tot + saldo.getTotal();
            //total=String.valueOf(saldo.getTotal());

            if (saldo.getIdArticulo() == 0) {
                items = 1;
            }
            if (items == 1) {

                if (saldo.getDescripcion().length() > 27) {
                    descripcionArt = saldo.getDescripcion().substring(0, 27);
                } else {
                    descripcionArt = saldo.getDescripcion();
                }
                totUni = "";
            } else {

                if (saldo.getDescripcion() != null) {
                    if (saldo.getDescripcion().length() > 27) {
                        descripcionArt = saldo.getDescripcion().substring(0, 27);
                    } else {
                        descripcionArt = saldo.getDescripcion();
                    }
                } else {
                    descripcionArt = "";
                }

                //as
                tot = saldo.getCantidad() * saldo.getPrecioGravadoArticulo();
                totUni = Numeros.ConvetirNumeroDosDigitos(tot);
                //tot=tot * 1.21;
            }
            descripcionArt = String.format("%-27s", descripcionArt);
            //descripcionArt=descripcionArt.replace("0"," ");
            totUni = String.format("%9s", totUni);
            listaArticulos.add(new FormatoArticulos(String.valueOf(saldo.getIdArticulo()), descripcionArt, String.valueOf(saldo.getCantidad()), totUni, Numeros.ConvertirNumero(saldo.getPrecioGravadoArticulo()), saldo.getAlicuota(), null));

        }

        modeloTicket.setFormatoComerciante(formatoComerciante);
        modeloTicket.setFormatoCliente(formatoCliente);
        modeloTicket.setFormatoFactura(formatoFactura);
        modeloTicket.setArticulos(listaArticulos);

        /*
        String ano = doc.getCaeVto().substring(0, 4);
            String mm = doc.getCaeVto().substring(4, 6);
            String dd = doc.getCaeVto().substring(6);
            String vencimiento = "C.A.E. Nº: " + doc.getCae();
            String vencimiento1 = "Fecha de Vto. C.A.E.: " + dd + "/" + mm + "/" + ano;
        
        //modeloTicket.setCodigoBarra("9781935182618");
        modeloTicket.setPiePagina(vencimiento);
        modeloTicket.setPiePagina1(vencimiento1);
        
       
        // Imprimiendo ticket
        modeloTicket.setIdComprobante(comF);
        String ticketFull = modeloTicket.procesarTicket(1);
        impresoraServicio.imprimirTicket(ticketFull);
         */
    }

    public void ImprimirCierreDeCaja(ArrayList listado) {

        Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH);
        mes++;
        int ano = fecha.get(Calendar.YEAR);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fec = dia + "/" + mes + "/" + ano;
        String hrs = hora + "," + minuto + ":" + segundo;
        // formulario izquierdo

        ImpresoraServiceImpl impresoraServicio = new ImpresoraServiceImpl();

        //-----------------TICKET DE COMPRA-------------------------------
        // Ejemplo para envio de informacion e impresion ticket
        // Creando las instancias
        ModeloTicket modeloTicket = new ModeloTicket();

        FormatoComerciante formatoComerciante = new FormatoComerciante();
        FormatoCliente formatoCliente = new FormatoCliente();
        List<FormatoArticulos> listaArticulos = new ArrayList();
        List<FormatoFormas> listaFormas = new ArrayList();
        FormatoFactura formatoFactura = new FormatoFactura();
        FormatoPie formatoPie = new FormatoPie();

        // Rellenando cada instancia con los valores de interes
        formatoComerciante.setNombreDelLocal(Propiedades.getNOMBRECOMERCIO());
        formatoComerciante.setCuitLocal(Propiedades.getCUIT());
        formatoComerciante.setIngresosBrutos(Propiedades.getINGBRUTOS());
        formatoComerciante.setTelefono(Propiedades.getTELEFONO());
        formatoComerciante.setDireccion(Propiedades.getDIRECCION());
        formatoComerciante.setRazonSocial(Propiedades.getRAZONSOCIAL());

        String nro = String.valueOf(Inicio.caja.getNumero());
        String numeroC = String.format("%0" + (10 - nro.length()) + "d%s", 0, nro);
        formatoFactura.setSuPago(numeroC);

        int renglon = 50;
        Double totalVentas = 0.00;
        Double totalGtos = 0.00;
        Double saldoInicial = 0.00;
        Double totalRetiros = 0.00;
        Double saldoCaja = 0.00;
        Double pagoProveedores = 0.00;

        ArrayList<FormasDePago> listadoF = new ArrayList();
        FormasDePago formaP = new FormasDePago();
        listadoF = formaP.ListarFormas();
        int idFormaP;
        double montoForma = 0.00;

        Iterator it = listado.listIterator();

        while (it.hasNext()) {
            Cajas caja = (Cajas) it.next();
            switch (caja.getTipoMovimiento()) {
                case 1:
                    totalVentas = totalVentas + caja.getMontoMovimiento();
                    idFormaP = caja.getIdForma1();
                    idFormaP = idFormaP - 1;
                    formaP = listadoF.get(idFormaP);
                    montoForma = formaP.getMonto();
                    montoForma = montoForma + caja.getMonto1();
                    formaP.setMonto(montoForma);
                    if (caja.getMonto2() > 0) {
                        idFormaP = caja.getIdForma2();
                        idFormaP = idFormaP - 1;
                        formaP = listadoF.get(idFormaP);
                        montoForma = formaP.getMonto();
                        montoForma = montoForma + caja.getMonto2();
                        formaP.setMonto(montoForma);
                    }

                    break;
                case 4:
                    totalRetiros = totalRetiros + caja.getMontoMovimiento();
                    break;
                case 11:
                    pagoProveedores = pagoProveedores + caja.getMontoMovimiento();
                    break;
                case 12:
                    totalGtos = totalGtos + caja.getMontoMovimiento();
                    break;
                case 10:
                    saldoCaja = saldoCaja + caja.getMontoMovimiento();
                    break;
                case 9:
                    saldoInicial = saldoInicial + caja.getMontoMovimiento();
                    break;
                default:
                    break;
            }
            if (caja.getTipoMovimiento() == 1) {

            } else {
                renglon = renglon + 10;
                String descripcionArt = String.format("%-27s", caja.getDescripcionMovimiento());
                String totUni = String.format("%9s", caja.getMontoMovimiento());
                if (descripcionArt != null) {
                    listaArticulos.add(new FormatoArticulos("0", descripcionArt, String.valueOf(caja.getNumeroDeComprobante()), totUni, "0.00", "0.00", "0.00"));
                }
            }
        }

        Iterator itF = listadoF.listIterator();
        while (itF.hasNext()) {
            formaP = (FormasDePago) itF.next();
            listaFormas.add(new FormatoFormas(String.format("%-27s", formaP.getDescripcionFormaDePago()), String.format("%9s", formaP.getMonto())));
        }

        String monto = "$"+String.format("%9s", saldoInicial);
        formatoPie.setSaldoInicial(monto);
        monto = "$"+String.format("%9s", totalVentas);
        formatoPie.setTotalVentas(monto);
        monto = "$"+String.format("%9s", totalGtos);
        formatoPie.setTotlaGastos(monto);
        monto = "$"+String.format("%9s", totalRetiros);
        formatoPie.setRetiros(monto);
        monto = "$"+String.format("%9s", pagoProveedores);
        formatoPie.setPagos(monto);
        Double saldo = saldoInicial + totalVentas + totalGtos + pagoProveedores + saldoCaja + totalRetiros;
        monto = "$"+String.format("%9s", saldo);
        formatoPie.setSaldo(monto);

        monto = "$"+String.format("%9s", saldoInicial);

        modeloTicket.setFormatoComerciante(formatoComerciante);
        modeloTicket.setFormatoCliente(formatoCliente);
        modeloTicket.setFormatoPie(formatoPie);
        modeloTicket.setFormatoFactura(formatoFactura);
        modeloTicket.setArticulos(listaArticulos);
        modeloTicket.setFormas(listaFormas);
        //modeloTicket.setCodigoBarra("9781935182618");
        modeloTicket.setPiePagina("DOCUMENTO NO VALIDO COMO FACTURA...!!!");

        // Imprimiendo ticket
        String ticketFull = modeloTicket.procesarTicket(55);
        impresoraServicio.imprimirTicket(ticketFull);

    }

    public void ImprimirRetiroEfectivo(Object factura) {
        Cajas caja = (Cajas) factura;
        Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH);
        mes++;
        int ano = fecha.get(Calendar.YEAR);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fec = dia + "/" + mes + "/" + ano;
        String hrs = hora + "," + minuto + ":" + segundo;
        // formulario izquierdo

        ImpresoraServiceImpl impresoraServicio = new ImpresoraServiceImpl();

        //-----------------TICKET DE COMPRA-------------------------------
        // Ejemplo para envio de informacion e impresion ticket
        // Creando las instancias
        ModeloTicket modeloTicket = new ModeloTicket();
        FormatoComerciante formatoComerciante = new FormatoComerciante();
        FormatoCliente formatoCliente = new FormatoCliente();
        List<FormatoArticulos> listaArticulos = new ArrayList();
        FormatoFactura formatoFactura = new FormatoFactura();

        // Rellenando cada instancia con los valores de interes
        formatoComerciante.setNombreDelLocal(Propiedades.getNOMBRECOMERCIO());
        formatoComerciante.setCuitLocal(Propiedades.getCUIT());
        formatoComerciante.setIngresosBrutos(Propiedades.getINGBRUTOS());
        formatoComerciante.setTelefono(Propiedades.getTELEFONO());
        formatoComerciante.setDireccion(Propiedades.getDIRECCION());
        formatoComerciante.setRazonSocial(Propiedades.getRAZONSOCIAL());

        Double monto = caja.getMontoMovimiento() * -1;
        String totalF = Numeros.ConvertirNumero(monto);
        formatoFactura.setTotal(totalF);
        String nro = String.valueOf(caja.getNumeroDeComprobante());
        String numeroC = String.format("%0" + (10 - nro.length()) + "d%s", 0, nro);
        formatoFactura.setNroFactura(numeroC);
        nro = String.valueOf(Inicio.caja.getNumero());
        numeroC = String.format("%0" + (10 - nro.length()) + "d%s", 0, nro);
        formatoFactura.setSuPago(numeroC);

        modeloTicket.setFormatoComerciante(formatoComerciante);
        modeloTicket.setFormatoCliente(formatoCliente);
        modeloTicket.setFormatoFactura(formatoFactura);
        modeloTicket.setArticulos(listaArticulos);
        //modeloTicket.setCodigoBarra("9781935182618");
        modeloTicket.setPiePagina("DOCUMENTO NO VALIDO COMO FACTURA...!!!");

        // Imprimiendo ticket
        String ticketFull = modeloTicket.procesarTicket(50);
        impresoraServicio.imprimirTicket(ticketFull);
    }
}
