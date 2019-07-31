/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturaElectronica.Objetos;

import ConfiguracionR.Propiedades;
import Conversores.Numeros;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import facturacion.clientes.FormasDePago;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import objetosR.Mail;

/**
 *
 * @author mauro di
 */
public class pdfsJavaGenerador {

    private FacturaElectronica doc = new FacturaElectronica();
    private ArrayList lstDetalle;
    private int punto;
    private Double numero;
    private EncabezadoClientes cliente;
    private Document documento;
    private EncabezadoPdf encabezado;
    private PdfContentByte cb;
    private PdfWriter writer;
    private BaseFont bf;
    private Rectangle recta;
    private int renglon;
    private Integer comF;
    private String num;
    private String clienteF;
    private Image imagen1;
    private Image imagen;
    private Image img;
    private Barcode128 codeEAN;
    private String codigoB;
    private String vencimiento;
    private String vencimiento1;
    ArrayList listado;
    DetalleFacturas saldo;
    Iterator itl;
    int copia;

    private PreparedStatement st1;
    private String nombreVendedor;
    private String cVendedor;
    private String cIva;
    private String direccionVendedor;
    private String telefonoVendedor;
    private String iBrutos;
    private String incioActividades;
    private String razonSocialVendedor;

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public void setNumero(Double numero) {
        this.numero = numero;
    }

    public void setLstDetalle(ArrayList lstDetalle) {
        this.lstDetalle = new ArrayList();
        this.lstDetalle = lstDetalle;
    }

    public void setDoc(FacturaElectronica doc) {
        this.doc = doc;
    }

    public pdfsJavaGenerador(String razonSocialVendedor, String nombreVendedor, String cVendedor, String cIva, String direccionVendedor, String telefonoVendedor, String iBrutos, String incioActividades) {
        this.razonSocialVendedor = razonSocialVendedor;
        this.nombreVendedor = nombreVendedor;

        this.cVendedor = cVendedor;
        this.cIva = cIva;
        this.direccionVendedor = direccionVendedor;
        this.telefonoVendedor = telefonoVendedor;
        this.iBrutos = iBrutos;
        this.incioActividades = incioActividades;
    }

    public String run() {
        documento = new Document();
        int i = 1;
        clienteF = doc.getAfipPlastCbte().replace(":", "_");
        num = String.valueOf(this.numero);
        int nume = num.length();
        nume = nume - 2;
        num = num.substring(0, nume);
        String arch = "Facturas Electronicas\\" + num + "_" + doc.getDescripcionTipoComprobante() + ".pdf";

        File fich = new File(arch);
        while (fich.exists()) {
            i++;
            arch = "Facturas Electronicas\\" + num + i + "_" + doc.getDescripcionTipoComprobante() + ".pdf";
            fich = new File(arch);
        }
        FileOutputStream fichero;

        try {

            encabezado = new EncabezadoPdf(nombreVendedor, razonSocialVendedor, direccionVendedor, telefonoVendedor, this.punto, this.numero, cVendedor, iBrutos, incioActividades, cIva);

            cliente = new EncabezadoClientes(doc.getRazonSocial(), doc.getCondicionIvaCliente(), doc.getDireccionCliente(), doc.getCustomerTypeDoc(), doc.getCustomerId(), doc.getMailCliente());
            saldo = new DetalleFacturas();
            //Facturable cotizable=new DetalleFacturas();
            listado = new ArrayList();
            //listado=cotizable.cargarDetallefactura(doc.getIdFactura());
            listado = doc.getListadoDetalle();
            fichero = new FileOutputStream(arch);
            writer = PdfWriter.getInstance(documento, fichero);
            documento.open();
            cb = writer.getDirectContent();
            //LineSeparator linea=new LineSeparator();
            recta = new Rectangle(20, 800, 580, 830);
            /*
            if(Propiedades.getLOGO() != ""){
                Image imagen= Image.getInstance(Propiedades.getLOGO());
                imagen.scaleAbsolute(190, 110);
                documento.add(imagen);
            }
             */
            String ano = doc.getCaeVto().substring(0, 4);
            String mm = doc.getCaeVto().substring(4, 6);
            String dd = doc.getCaeVto().substring(6);
            vencimiento = "C.A.E. Nº: " + doc.getCae();
            vencimiento1 = "Fecha de Vto. C.A.E.: " + dd + "/" + mm + "/" + ano;
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            //cb.setFontAndSize(bf,16);
            for (int aa = 0; aa < 1; aa++) {
                copia = aa;
                if (aa > 0) {
                    documento.newPage();
                }
                cb.setFontAndSize(bf, 12);
                cb.beginText();
                //linea.setLineWidth((float) 0.1);
                //linea.drawLine(cb,20,570,830);
                //linea.draw(cb, 20,530,20, 30, 530);
                /*
            SE DIBUJA TODO EL FORMULARIO Y LUEGO SE COMPLETA
           
                 */
                imprimirEncabezado();
                renglon = 610;

                //aca empieza la iteracion
                //encabezados
                bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                cb.setFontAndSize(bf, 9);
                cb.setTextMatrix(40, renglon);
                cb.showText("COD");
                cb.setTextMatrix(70, renglon);
                cb.showText("DESCRIPCION");
                if (comF == 1 || comF == 2 || comF == 3) {
                    cb.setTextMatrix(270, renglon);
                    cb.showText("ALICUOTA");
                    
                }
                cb.setTextMatrix(330, renglon);
                cb.showText("CANT.");
                cb.setTextMatrix(370, renglon);
                cb.showText("P. UNIT.");
                cb.setTextMatrix(450, renglon);
                cb.showText("DESC.");
                cb.setTextMatrix(500, renglon);
                cb.showText("P. TOTAL.");
                renglon = renglon - 20;
                itl = listado.listIterator();
                //fin encabezados
                imprimirCuerpo();
                imprimirPie();
                //factura.setTotal(totalS);

                cb.endText();

            }

            documento.close();

            File f = new File(arch);
            if (f.exists()) {
                if (Propiedades.getTIQUEADORA() == 0) {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + arch);
                }
                if (cliente.getMailC().isEmpty()) {

                } else {
                    Mail mail = new Mail();
                    mail.setDireccionFile(arch);
                    mail.setDetalleListado(doc.getDescripcionTipoComprobante() + " " + num + ".pdf");
                    mail.enviarMailFacturaElectronica(cliente.getMailC(), doc.getDescripcionTipoComprobante() + " " + num);
                }
            }
            int confirmacion = 0;
            /*
            if(doc.getArchivo().isEmpty()){
                
            }else{
                confirmacion=JOptionPane.showConfirmDialog(null, "DESEA NOTIFICAR POR MAIL?");
            if(confirmacion==0){
                //JOptionPane.showMessageDialog(null,"acepto");
                
            }
            }
             */
            //System.out.println("eligio " + confirmacion);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);

        } catch (DocumentException | IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arch;

    }

    private void nuevaPagina() {
        try {
            imprimirPie();
            cb.endText();
            documento.newPage();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            //linea.setLineWidth((float) 0.1);
            //linea.drawLine(cb,20,570,830);
            //linea.draw(cb, 20,530,20, 30, 530);
            /*
            SE DIBUJA TODO EL FORMULARIO Y LUEGO SE COMPLETA
            
             */
            imprimirEncabezado();
            renglon = 610;

            //aca empieza la iteracion
            //encabezados
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 9);
            cb.setTextMatrix(40, renglon);
            cb.showText("COD");
            cb.setTextMatrix(70, renglon);
            cb.showText("DESCRIPCION");
            if (comF == 1 || comF == 2 || comF == 3) {
                cb.setTextMatrix(270, renglon);
                    cb.showText("ALICUOTA");
            }

            cb.setTextMatrix(330, renglon);
            cb.showText("CANT.");
            cb.setTextMatrix(370, renglon);
            cb.showText("P. UNIT.");
            cb.setTextMatrix(450, renglon);
            cb.showText("DESC.");
            cb.setTextMatrix(500, renglon);
            cb.showText("P. TOTAL.");
            renglon = renglon - 20;

            //fin encabezados
            imprimirCuerpo();
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void imprimirEncabezado() {
        try {
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);

            cb.rectangle(recta);

            recta = new Rectangle(20, 700, 580, 800);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);
            recta = new Rectangle(270, 760, 330, 800);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);
            //linea.drawLine(cb,300,45,700);
            recta = new Rectangle(20, 680, 580, 697);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);
            recta = new Rectangle(20, 625, 580, 677);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);
            recta = new Rectangle(20, 70, 580, 190);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);

            cb.setTextMatrix(270, 810);
            if (copia == 0) {
                cb.showText("ORIGINAL");
            }
            if (copia == 1) {
                cb.showText("DUPLICADO");
            }
            if (copia == 2) {
                cb.showText("TRIPLICADO");
            }
            cb.setFontAndSize(bf, 19);
            cb.setTextMatrix(25, 770);
            String nombreCom = encabezado.getRazonSocial();
            if (nombreCom.length() > 21) {
                nombreCom = encabezado.getRazonSocial().substring(0, 19);
            }
            cb.showText(nombreCom);
            //cb.setTextMatrix(90,750);
            //cb.showText("ANTONIO");
            //cb.showText("eR&Re");
            //cb.add(imagen);
            cb.setFontAndSize(bf, 9);
            cb.setTextMatrix(25, 740);
            cb.showText("Razón Social: " + encabezado.getNombreComercio());
            cb.setTextMatrix(25, 730);
            cb.showText("Domicilio Comercial: " + encabezado.getDireccion());
            //cb.showText("PAPELES");
            //bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            //cb.setFontAndSize(bf,10);
            cb.setTextMatrix(25, 720);
            cb.showText("Telefono: " + encabezado.getTelefono());
            cb.setTextMatrix(25, 710);
            String condIvaTxt = "";
            if (encabezado.getCondicionIva().equals("1")) {
                condIvaTxt = "Resp. Inscripto";
            }
            if (encabezado.getCondicionIva().equals("4")) {
                condIvaTxt = "Sujeto Exento";
            }
            if (encabezado.getCondicionIva().equals("5")) {
                condIvaTxt = "Cons Final";
            }
            if (encabezado.getCondicionIva().equals("6")) {
                condIvaTxt = "Resp. Monotributo";
            }

            cb.showText("Condición frente la IVA: " + condIvaTxt);

            //cb.showText("de Rivadeneira Enrique y Rivadeneira Jorge S.H.");
            //bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 22);
            cb.setTextMatrix(360, 770);

            comF = 0;
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
            String len = doc.getAfipPlastCbte();
            int cantiL = len.length();
            String cero = "0";
            int reemplazo = 8 - cantiL;
            int finall = reemplazo + 1;
            reemplazo = reemplazo - 1;
            String numero = "0";
            for (int a = 1; a < finall; a++) {
                numero += cero;
                if (a == reemplazo) {
                    a = finall;
                    numero += len;
                }

            }

            switch (comF) {
                case 1:
                    cb.showText("FACTURA A");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf, 26);
                    cb.showText("A");
                    cb.setFontAndSize(bf, 8);
                    cb.setTextMatrix(285, 765);
                    cb.showText("COD. 0" + comF);

                    break;
                case 2:
                    cb.showText("N DE DEBITO A");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf, 26);
                    cb.showText("A");
                    cb.setFontAndSize(bf, 8);
                    cb.setTextMatrix(285, 765);
                    cb.showText("COD. 0" + comF);
                    break;
                case 3:
                    cb.showText("N DE CREDITO A");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf, 26);
                    cb.showText("A");
                    cb.setFontAndSize(bf, 8);
                    cb.setTextMatrix(285, 765);
                    cb.showText("COD. 0" + comF);
                    break;
                case 6:
                    cb.showText("FACTURA B");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf, 26);
                    cb.showText("B");
                    cb.setFontAndSize(bf, 8);
                    cb.setTextMatrix(285, 765);
                    cb.showText("COD. 0" + comF);
                    break;
                case 7:
                    cb.showText("N DE DEBITO B");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf, 26);
                    cb.showText("B");
                    cb.setFontAndSize(bf, 8);
                    cb.setTextMatrix(285, 765);
                    cb.showText("COD. 0" + comF);
                    break;
                case 8:
                    cb.showText("N DE CREDITO B");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf, 26);
                    cb.showText("B");
                    cb.setFontAndSize(bf, 8);
                    cb.setTextMatrix(285, 765);
                    cb.showText("COD. 0" + comF);
                    break;
                case 11:
                    cb.showText("FACTURA C");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf, 26);
                    cb.showText("C");
                    cb.setFontAndSize(bf, 8);
                    cb.setTextMatrix(285, 765);
                    cb.showText("COD. 0" + comF);
                    break;
                case 12:
                    cb.showText("N DE DEBITO C");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf, 26);
                    cb.showText("C");
                    cb.setFontAndSize(bf, 8);
                    cb.setTextMatrix(285, 765);
                    cb.showText("COD. 0" + comF);
                    break;
                case 13:
                    cb.showText("N DE CREDITO C");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf, 26);
                    cb.showText("C");
                    cb.setFontAndSize(bf, 8);
                    cb.setTextMatrix(285, 765);
                    cb.showText("COD. 0" + comF);
                    break;
            }
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 9);
            cb.setTextMatrix(360, 755);

            cb.showText("Punto de Venta: 000" + this.punto + " Comp. Nro:" + num);
            //bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 9);
            cb.setTextMatrix(360, 745);
            String fechaCae = doc.getFechaCae();
            String ano = fechaCae.substring(0, 4);
            String mes = fechaCae.substring(4, 6);
            String dia = fechaCae.substring(6);
            fechaCae = dia + "/" + mes + "/" + ano;
            cb.showText("Fecha de emisión: " + fechaCae);
            cb.setTextMatrix(360, 730);
            cb.showText("CUIT: " + encabezado.getCuit());
            cb.setTextMatrix(360, 720);
            cb.showText("Ing. Brutos: " + encabezado.getIngresosBrutos());
            cb.setTextMatrix(360, 710);
            cb.showText("Inicio Activ.: " + encabezado.getInicioActividades());
            //cb.setTextMatrix(380,740);
            //cb.showText("Fecha "+doc.getFechaCae());
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 9);
            cb.setTextMatrix(25, 685);

            cb.showText("Período Facturado Desde: " + fechaCae);
            cb.setTextMatrix(240, 685);
            cb.showText("Hasta: " + fechaCae);
            cb.setTextMatrix(360, 685);
            cb.showText("Fecha de Vto. para el pago: " + fechaCae);

            //fin segundo recuadro
            cb.setTextMatrix(40, 660);
            cb.showText("Razon Social :" + cliente.getRazonSocial());
            cb.setTextMatrix(380, 660);
            String condV = "";
            FormasDePago forma = new FormasDePago();
            forma = (FormasDePago) forma.CargarForma(doc.getCondicionDeVenta());

            condV = forma.getDescripcionFormaDePago();

            cb.showText("Cond. Vta: " + condV);
            cb.setTextMatrix(380, 650);
            try {
                Integer condIvv = Integer.parseInt(cliente.getCondicionIva());
                switch (condIvv) {
                    case 1:
                        cb.showText("Cond. Iva: Responsable Inscripto");
                        break;
                    case 5:
                        cb.showText("Cond. Iva: Cons. Final");
                        break;
                    case 4:
                        cb.showText("Cond. Iva: Exento");
                        break;
                    case 6:
                        cb.showText("Cond. Iva: Resp. Monotributo");
                        break;
                    default:
                        cb.showText("Cond. Iva: Consumidor Final");
                        break;
                }
            } catch (java.lang.NumberFormatException ex) {
                cb.showText("Cond. Iva: " + cliente.getCondicionIva());
            }
            //cb.showText("Cond. Iva: "+cliente.getCondicionIva());
            cb.setTextMatrix(40, 650);
            cb.showText("Direccion: " + cliente.getDireccion());
            cb.setTextMatrix(40, 640);

            //localidad=per.buscarPorNumero(cliente.getLocalidad())
            //cb.showText("Localidad :("+cliente.getCodigoPostal()+") - "+cliente.getLocalidad());
            //cb.setTextMatrix(40,650);
            //cb.showText("Telefono: "+cliente.getTelefono());
            cb.setTextMatrix(380, 640);
            Integer tipo = Integer.parseInt(String.valueOf(doc.getCustomerTypeDoc()));
            switch (tipo) {
                case 80:
                    cb.showText("Cuit: " + doc.getCustomerId());
                    break;
                case 86:
                    cb.showText("Cuil: " + doc.getCustomerId());
                    break;
                case 96:
                    cb.showText("Dni: " + doc.getCustomerId());
                    break;
            }

        } catch (DocumentException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void imprimirCuerpo() {
        try {
            String descripcion;
            String monto;
            String recargo;
            String total;
            String totalFinal;
            Double tot = 0.00;
            Double sinIva = 0.00;
            Double totalD = 0.00;
            Double grav = 0.00;
            Double totalS = 0.00;
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 8);

            String descripcionArt = null;
            int items = 0;
            Integer renItem = 1;
            while (itl.hasNext()) {
                saldo = (DetalleFacturas) itl.next();
                //vencimiento=saldo.getVencimientoString();

                descripcion = "Numero Resumen de cta ";

                monto = Numeros.ConvertirNumero(saldo.getPrecioUnitario());
                recargo = "";
                total = "nada";
                //recargo=String.valueOf(saldo.getRecargo());
                //tot=tot + saldo.getTotal();
                //total=String.valueOf(saldo.getTotal());

                if (saldo.getIdArticulo() == 0) {
                    items = 1;
                }
                if (items == 1) {
                    cb.setTextMatrix(70, renglon);

                    if (saldo.getDescripcion().length() > 40) {
                        descripcionArt = saldo.getDescripcion().substring(0, 40);
                    } else {
                        descripcionArt = saldo.getDescripcion();
                    }
                    cb.showText(descripcionArt);
                    renglon = renglon - 10;
                } else {
                    cb.setTextMatrix(40, renglon);

                    cb.showText(String.valueOf(saldo.getIdArticulo()));
                    cb.setTextMatrix(70, renglon);
                    if (saldo.getDescripcion() != null) {
                        if (saldo.getDescripcion().length() > 40) {
                            descripcionArt = saldo.getDescripcion().substring(0, 40);
                        } else {
                            descripcionArt = saldo.getDescripcion();
                        }
                    } else {
                        descripcionArt = "";
                    }
                    cb.showText(descripcionArt);
                    cb.setTextMatrix(330, renglon);
                    cb.showText(String.valueOf(saldo.getCantidad()));
                    tot = saldo.getCantidad() * saldo.getPrecioUnitario();
                    sinIva = saldo.getCantidad() * saldo.getPrecioGravadoArticulo();
                    //tot=tot * 1.21;
                    if (comF == 1 || comF == 2 || comF == 3) {
                        cb.setTextMatrix(280, renglon);
                        cb.showText(saldo.getAlicuota());
                        cb.setTextMatrix(370, renglon);
                        cb.showText(Numeros.ConvertirNumero(saldo.getPrecioGravadoArticulo()));
                        cb.setTextMatrix(450, renglon);
                        if (saldo.getDescuento() != null) {
                            cb.showText(String.valueOf(saldo.getDescuento()));
                            totalD = totalD + saldo.getDescuento();
                        } else {
                            cb.showText("0.00");
                        }
                        cb.setTextMatrix(500, renglon);

                        cb.showText(Numeros.ConvertirNumero(sinIva));
                        grav = grav + tot;
                        totalS = totalS + (tot);
                        //cb.setTextMatrix(440,renglon);

                        //cb.showText(Numeros.ConvertirNumero(tot));
                        renglon = renglon - 10;
                        //System.out.println("renglon " + renglon);

                    } else {
                        //tot=tot * 1.21;
                        cb.setTextMatrix(370, renglon);
                        cb.showText(Numeros.ConvertirNumero(saldo.getPrecioUnitario()));
                        cb.setTextMatrix(450, renglon);
                        if (saldo.getDescuento() != null) {
                            cb.showText(String.valueOf(saldo.getDescuento()));
                            totalD = totalD + saldo.getDescuento();
                        } else {
                            cb.showText("0.00");
                        }
                        cb.setTextMatrix(500, renglon);
                        cb.showText(Numeros.ConvertirNumero(tot));
                        grav = grav + tot;
                        totalS = totalS + (tot);
                        //cb.setTextMatrix(440,renglon);

                        //cb.showText(Numeros.ConvertirNumero(tot));
                        renglon = renglon - 10;
                        //System.out.println("renglon " + renglon);
                    }
                }
                items = 0;
                if (renItem == 25) {
                    nuevaPagina();
                    renItem = 0;
                }

                renItem++;

            }
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void imprimirCuerpoA() {
        try {
            String descripcion;
            String monto;
            String recargo;
            String total;
            String totalFinal;
            Double tot = 0.00;
            Double totalD = 0.00;
            Double grav = 0.00;
            Double totalS = 0.00;
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 8);

            String descripcionArt = null;
            int items = 0;
            Integer renItem = 1;
            while (itl.hasNext()) {
                saldo = (DetalleFacturas) itl.next();
                //vencimiento=saldo.getVencimientoString();

                descripcion = "Numero Resumen de cta ";

                monto = Numeros.ConvertirNumero(saldo.getPrecioUnitario());
                recargo = "";
                total = "nada";
                //recargo=String.valueOf(saldo.getRecargo());
                //tot=tot + saldo.getTotal();
                //total=String.valueOf(saldo.getTotal());

                if (saldo.getIdArticulo() == 0) {
                    items = 1;
                }
                if (items == 1) {
                    cb.setTextMatrix(70, renglon);

                    if (saldo.getDescripcion().length() > 40) {
                        descripcionArt = saldo.getDescripcion().substring(0, 40);
                    } else {
                        descripcionArt = saldo.getDescripcion();
                    }
                    cb.showText(descripcionArt);
                    renglon = renglon - 10;
                } else {
                    cb.setTextMatrix(40, renglon);

                    cb.showText(String.valueOf(saldo.getIdArticulo()));
                    cb.setTextMatrix(70, renglon);
                    if (saldo.getDescripcion() != null) {
                        if (saldo.getDescripcion().length() > 40) {
                            descripcionArt = saldo.getDescripcion().substring(0, 40);
                        } else {
                            descripcionArt = saldo.getDescripcion();
                        }
                    } else {
                        descripcionArt = "";
                    }
                    cb.showText(descripcionArt);
                    cb.setTextMatrix(330, renglon);
                    cb.showText(String.valueOf(saldo.getCantidad()));
                    tot = saldo.getCantidad() * saldo.getPrecioUnitario();
                    //tot=tot * 1.21;
                    if (comF == 1 || comF == 2 || comF == 3) {

                        cb.setTextMatrix(370, renglon);
                        cb.showText(Numeros.ConvertirNumero(saldo.getPrecioUnitario()));
                        cb.setTextMatrix(450, renglon);
                        if (saldo.getDescuento() != null) {
                            cb.showText(String.valueOf(saldo.getDescuento()));
                            totalD = totalD + saldo.getDescuento();
                        } else {
                            cb.showText("0.00");
                        }
                        cb.setTextMatrix(500, renglon);
                        cb.showText(Numeros.ConvertirNumero(tot));
                        grav = grav + tot;
                        totalS = totalS + (tot);
                        //cb.setTextMatrix(440,renglon);

                        //cb.showText(Numeros.ConvertirNumero(tot));
                        renglon = renglon - 10;
                        //System.out.println("renglon " + renglon);

                    } else {
                        //tot=tot * 1.21;
                        cb.setTextMatrix(370, renglon);
                        cb.showText(Numeros.ConvertirNumero(saldo.getPrecioUnitario()));
                        cb.setTextMatrix(450, renglon);
                        if (saldo.getDescuento() != null) {
                            cb.showText(String.valueOf(saldo.getDescuento()));
                            totalD = totalD + saldo.getDescuento();
                        } else {
                            cb.showText("0.00");
                        }
                        cb.setTextMatrix(500, renglon);
                        cb.showText(Numeros.ConvertirNumero(tot));
                        grav = grav + tot;
                        totalS = totalS + (tot);
                        //cb.setTextMatrix(440,renglon);

                        //cb.showText(Numeros.ConvertirNumero(tot));
                        renglon = renglon - 10;
                        //System.out.println("renglon " + renglon);
                    }
                }
                items = 0;
                if (renItem == 25) {
                    nuevaPagina();
                    renItem = 0;
                }

                renItem++;

            }
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void imprimirPie() {
        try {
            String totalF = Numeros.ConvetirNumeroDosDigitos(doc.getImporteTotal());

            //String letras=NumberToLetterConverter.convertNumberToLetter(factura.getTotal());
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 8);

            if (comF == 1 || comF == 2 || comF == 3) {

                renglon = 180;
                cb.setTextMatrix(380, renglon);
                cb.showText("Importe Neto Grav.");
                cb.setTextMatrix(480, renglon);
                //cb.showText(letras);

                // cb.setTextMatrix(40,renglon);
                Double sub = doc.getImporteNeto();
                Double iva = doc.getImpuestoLiquido();
                cb.showText("$ " + Numeros.ConvertirNumero(sub));

                if (doc.getListadoIva() != null) {
                    Iterator iIva = doc.getListadoIva().listIterator();
                    TiposIva tipos;
                    while (iIva.hasNext()) {
                        tipos = (TiposIva) iIva.next();
                        renglon = renglon - 10;
                        cb.setTextMatrix(380, renglon);
                        cb.showText(tipos.getDescripcion());
                        cb.setTextMatrix(480, renglon);
                        cb.showText("$ " + Numeros.ConvertirNumero(tipos.getImporte()));
                    }

                    renglon = renglon - 10;
                }
                if (doc.getListadoTributos() != null) {
                    Iterator iTri = doc.getListadoTributos().listIterator();
                    Tributos tributo;
                    while (iTri.hasNext()) {
                        tributo = (Tributos) iTri.next();
                        cb.setTextMatrix(380, renglon);
                        cb.showText(tributo.getDescripcion());
                        cb.setTextMatrix(480, renglon);
                        cb.showText("$ " + String.valueOf(tributo.getImporte()));
                    }
                    renglon = renglon - 10;
                }
                renglon = renglon - 10;

                cb.setTextMatrix(380, renglon);
                cb.showText("Importe Total");
                cb.setTextMatrix(480, renglon);
                cb.showText("$ " + totalF);

            } else {

                renglon = 120;
                cb.setTextMatrix(380, renglon);
                cb.showText("Subtotal.");
                cb.setTextMatrix(480, renglon);
                cb.showText("$ " + totalF);

                renglon = renglon - 10;

                cb.setTextMatrix(380, renglon);
                cb.showText("Importe Otros Tributos");
                cb.setTextMatrix(480, renglon);
                cb.showText("$ 0.00");
                renglon = renglon - 10;
                cb.setTextMatrix(380, renglon);
                cb.showText("Importe Total");
                cb.setTextMatrix(480, renglon);
                cb.showText("$ " + totalF);
            }

            //pie de documento
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 9);
            renglon = renglon - 10;
            
            cb.setTextMatrix(380, renglon);
            cb.showText(vencimiento);
            renglon = renglon - 10;
            cb.setTextMatrix(170,renglon);
            if(Propiedades.getACLARACIONPIE()==0){
               cb.setFontAndSize(bf, 7);
                cb.showText("Factura electrónica emitida con manantialgestion.com");
            cb.setFontAndSize(bf, 9);
        }
            cb.setTextMatrix(380, renglon);
            cb.showText(vencimiento1);
            cb.setTextMatrix(20, renglon);
            if (imagen1 != null) {

            } else {
                imagen1 = Image.getInstance(doc.getNombreQr());
            }
            //imagen.scaleAbsolute(84, 410);
            imagen1.setAbsolutePosition(30, 75);
            documento.add(imagen1);

            renglon = 30;
            //System.out.println("renglon imagenes afip " + renglon);
            if (imagen != null) {

            } else {
                imagen = Image.getInstance("imagenes/afip.JPG");
            }
            //imagen.scaleAbsolute(84, 410);
            imagen.setAbsolutePosition(20, renglon);
            documento.add(imagen);
            if (codeEAN != null) {

            } else {
                codeEAN = new Barcode128();
                codeEAN.setCodeType(Barcode.CODE128);

                String ccuit = encabezado.getCuit().replace("-", "");
                codigoB = ccuit + doc.getNumeroPuntoDeVenta() + comF + doc.getCae() + doc.getCaeVto() + "3";
            }
            codeEAN.setCode(codigoB);
            //codeEAN.setSize(5);
            if (img != null) {

            } else {
                img = codeEAN.createImageWithBarcode(cb, Color.BLACK, Color.black);
            }

            img.setAbsolutePosition(360, renglon);
            documento.add(img);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
