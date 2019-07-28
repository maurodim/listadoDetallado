/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Configuracion.Propiedades;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conversiones.NumberToLetterConverter;
import conversiones.Numeros;
import fejoe.FEJoe;
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

/**
 *
 * @author Usuario
 */
public class pdfFactura {

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
    
    
    //valores comprobantes encabezados
    private String letra;
    private String descComprobante;

    // Fonts definitions (Definición de fuentes).
    
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC, BaseColor.RED);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    private static final Font paragraphFontBold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);

    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
    private static final Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
    private static final Font extraSmallBold = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD);
    private static final Font extraSmall = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL);

    //private static final String iTextExampleImage = "/home/xules/codigoxules/iText-Example-image.png";
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

    public pdfFactura(String razonSocialVendedor, String nombreVendedor, String cVendedor, String cIva, String direccionVendedor, String telefonoVendedor, String iBrutos, String incioActividades) {
        this.razonSocialVendedor = razonSocialVendedor;
        this.nombreVendedor = nombreVendedor;

        this.cVendedor = cVendedor;
        this.cIva = cIva;
        this.direccionVendedor = direccionVendedor;
        this.telefonoVendedor = telefonoVendedor;
        this.iBrutos = iBrutos;
        this.incioActividades = incioActividades;
    }

    public String run() throws DocumentException {
        documento = new Document();
        int i = 1;
        clienteF = doc.getAfipPlastCbte().replace(":", "_");
        num = String.valueOf(this.numero);
        int nume = num.length();
        nume = nume - 2;
        num = num.substring(0, nume);
        String arch = "Facturas Electronicas\\" + num + "_" + doc.getDescripcionTipoComprobante() + ".pdf";
        String nombreArch=num + "_" + doc.getDescripcionTipoComprobante() + ".pdf";
        File fich = new File(arch);
        while (fich.exists()) {
            i++;
            arch = "Facturas Electronicas\\" + num + i + "_" + doc.getDescripcionTipoComprobante() + ".pdf";
            fich = new File(arch);
        }
        FileOutputStream fichero;

        try {

            encabezado = new EncabezadoPdf(nombreVendedor, razonSocialVendedor, direccionVendedor, telefonoVendedor, this.punto, this.numero, cVendedor, iBrutos, incioActividades, cIva);

            cliente = new EncabezadoClientes(doc.getRazonSocial(), doc.getCondicionIvaCliente(), doc.getDireccionCliente(), doc.getCustomerTypeDoc(), doc.getCustomerId());
            saldo = new DetalleFacturas();
            //Facturable cotizable=new DetalleFacturas();
            listado = new ArrayList();
            //listado=cotizable.cargarDetallefactura(doc.getIdFactura());
            listado = doc.getListadoDetalle();

            fichero = new FileOutputStream(arch);
            writer = PdfWriter.getInstance(documento, fichero);
            cb=new PdfContentByte(writer);
            documento.open();
            
            String ano1 = doc.getCaeVto().substring(0, 4);
            String mm = doc.getCaeVto().substring(4, 6);
            String dd = doc.getCaeVto().substring(6);
            vencimiento = "C.A.E. Nº: " + doc.getCae();
            vencimiento1 = "Fecha de Vto. C.A.E.: " + dd + "/" + mm + "/" + ano1;
            
            //determinamso el tipo de comprobante
            
            comF = 0;
            if (doc.getTipoComprobante().equals("tcFacturaA")) {
                comF = 1;
                letra="A";
                descComprobante="FACTURA "+letra;
            }
            if (doc.getTipoComprobante().equals("tcNotaDebitoA")) {
                comF = 2;
                letra="A";
                descComprobante="NOTA DE DEBITO "+letra;
            }
            if (doc.getTipoComprobante().equals("tcNotaCreditoA")) {
                comF = 3;
                letra="A";
                descComprobante="NOTA DE CREDITO "+letra;
            }
            if (doc.getTipoComprobante().equals("tcFacturaB")) {
                comF = 6;
                letra="B";
                descComprobante="FACTURA "+letra;
            }
            if (doc.getTipoComprobante().equals("tcNotaDebitoB")) {
                comF = 7;
                letra="B";
                descComprobante="NOTA DE DEBITO "+letra;
            }
            if (doc.getTipoComprobante().equals("tcNotaCreditoB")) {
                comF = 8;
                letra="B";
                descComprobante="NOTA DE CREDITO "+letra;
            }
            if (doc.getTipoComprobante().equals("tcFacturaC")) {
                comF = 11;
                letra="C";
                descComprobante="FACTURA "+letra;
            }
            if (doc.getTipoComprobante().equals("tcNotaDebitoC")) {
                comF = 12;
                letra="C";
                descComprobante="NOTA DE DEBITO "+letra;
            }
            if (doc.getTipoComprobante().equals("tcNotaCreditoC")) {
                comF = 13;
                letra="C";
                descComprobante="NOTA DE CREDITO "+letra;
            }
            
            
            
            // We add metadata to PDF
            // Añadimos los metadatos del PDF
            documento.addTitle(descComprobante+" ELECTRÓNICA");
            documento.addSubject("FACTURA ELECTRÓNICA - AFIP");
            documento.addKeywords("Web Service Afip");
            documento.addAuthor("Bambusoft");
            documento.addCreator("Bambusoft");

            // First page
            // Primera página 
            Chunk chunk = new Chunk("", chapterFont);
            //chunk.setBackground(BaseColor.GRAY);
            // Let's create de first Chapter (Creemos el primer capítulo)
            Chapter chapter = new Chapter(new Paragraph(chunk), 1);
            chapter.setNumberDepth(0);
            chapter.add(new Paragraph("", paragraphFont));
            // We add an image (Añadimos una imagen)
            int numColumns = 3;
            int numRows = 6;
            PdfPTable tableGral=new PdfPTable(1);
            float[] ancho={40f};
            tableGral.setWidthPercentage(100);
            //tableGral.getDefaultCell().setBorder(1);
            PdfPCell celdaGral;
            PdfPTable table = new PdfPTable(numColumns);
            table.setWidthPercentage(100);
            float[] medidaCeldas = {2.25f, 0.55f, 2.25f};
            table.setWidths(medidaCeldas);
            table.getDefaultCell().setBorder(0);
            // Now we fill the PDF table 
            // Ahora llenamos la tabla del PDF
            PdfPCell columnHeader = null;
            // Fill table rows (rellenamos las filas de la tabla). 

            //table.addCell(columnHeader);
            /*
                columnHeader = new PdfPCell(new Phrase("COL " + column));
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                columnHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(columnHeader);
            
            table.setHeaderRows(1);
             */
            // Fill table rows (rellenamos las filas de la tabla).
            Paragraph paragraphE;
            Paragraph parrafo;
            PdfPTable tabla2;
            PdfPCell celda;
            chunk=new Chunk(Propiedades.getWEB(), smallBold);
            chunk.setAnchor(Propiedades.getLINK());
            
            //comienzo renglon 1
            for (int column = 0; column < numColumns; column++) {
                switch (column) {
                    case 0:
                        tabla2 = new PdfPTable(1);
                        tabla2.getDefaultCell().setBorder(0);
                        chapterFont.setColor(189, 61, 33);
                        parrafo = new Paragraph(nombreVendedor, chapterFont);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setPaddingLeft(5);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph(chunk);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setPaddingLeft(5);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph(razonSocialVendedor, extraSmallBold);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setPaddingLeft(5);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph(direccionVendedor, extraSmallBold);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setPaddingLeft(5);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph(telefonoVendedor, extraSmallBold);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setPaddingLeft(5);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph("IVA: Responsable Inscripto", extraSmallBold);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setPaddingLeft(5);
                        tabla2.addCell(celda);
                        //paragraphE=new Paragraph(tabla2);
                        columnHeader = new PdfPCell(tabla2);
                        columnHeader.setBorder(0);
                        columnHeader.setBorderWidthLeft(0.5f);
                        columnHeader.setBorderWidthTop(0.5f);
                        break;
                    case 1:
                        tabla2 = new PdfPTable(1);
                        //tabla2.getDefaultCell().setBorder(0);
                        parrafo = new Paragraph("ORIGINAL", extraSmallBold);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(1);
                        celda.setBorderWidthLeft(1);
                        celda.setBorderWidthRight(1);
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph(letra, categoryFont);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(1);

                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla2.addCell(celda);
                        String comS=String.valueOf(comF);
                        comS=String.format("%0" + (2 - comS.length()) + "d%s", 0, comS);
                        parrafo = new Paragraph("COD. "+comS, extraSmall);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla2.addCell(celda);

                        columnHeader = new PdfPCell(tabla2);
                        columnHeader.setBorder(0);
                        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

                        //columnHeader.setBorderWidthTop(0.5f);
                        break;
                    case 2:
                        tabla2 = new PdfPTable(1);
                        tabla2.getDefaultCell().setBorder(0);
                        parrafo = new Paragraph(descComprobante, paragraphFont);
                        celda = new PdfPCell(parrafo);
                        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celda.setPaddingLeft(30);
                        celda.setBorder(0);
                        tabla2.addCell(celda);
                        String puntoS=String.valueOf(punto);
                        puntoS=String.format("%0" + (4 - puntoS.length()) + "d%s", 0, puntoS);
                        String numNro = String.format("%0" + (8 - num.length()) + "d%s", 0, num);
                        parrafo = new Paragraph(puntoS + "-" + numNro, paragraphFontBold);
                        celda = new PdfPCell(parrafo);
                        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celda.setPaddingLeft(30);
                        celda.setBorder(0);
                        tabla2.addCell(celda);

                        String fechaCae = doc.getFechaCae();
                        String ano = fechaCae.substring(0, 4);
                        String mes = fechaCae.substring(4, 6);
                        String dia = fechaCae.substring(6);
                        fechaCae = dia + "/" + mes + "/" + ano;

                        parrafo = new Paragraph("Fecha: " + fechaCae, paragraphFont);
                        celda = new PdfPCell(parrafo);
                        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celda.setPaddingLeft(30);
                        celda.setBorder(0);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph("C.U.I.T.: " + cVendedor, extraSmall);
                        celda = new PdfPCell(parrafo);
                        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celda.setPaddingLeft(30);
                        celda.setBorder(0);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph("ING BRUTOS: " + iBrutos, extraSmall);
                        celda = new PdfPCell(parrafo);
                        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celda.setPaddingLeft(30);
                        celda.setBorder(0);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph("INICIO DE ACTIVIDADES" + incioActividades, extraSmall);
                        celda = new PdfPCell(parrafo);
                        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celda.setPaddingLeft(30);
                        celda.setBorder(0);
                        tabla2.addCell(celda);

                        columnHeader = new PdfPCell(tabla2);
                        columnHeader.setBorder(0);
                        columnHeader.setBorderWidthRight(0.5f);
                        columnHeader.setBorderWidthTop(0.5f);
                        break;

                }
                
                //columnHeader.setBorderWidthTop(0.5f);
                table.addCell(columnHeader);
            }
            //fin renglon1
            //comienzo renglon2 - datos del cliente
            for (int column = 0; column < numColumns; column++) {
                switch (column) {
                    case 0:
                        tabla2 = new PdfPTable(1);
                        tabla2.getDefaultCell().setBorder(0);
                        parrafo = new Paragraph(nombreVendedor, smallNormal);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setPaddingLeft(5);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph(razonSocialVendedor, smallNormal);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setPaddingLeft(5);
                        tabla2.addCell(celda);
                        parrafo = new Paragraph(direccionVendedor, smallNormal);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setPaddingLeft(5);
                        tabla2.addCell(celda);
                        
                        //paragraphE=new Paragraph(tabla2);
                        columnHeader = new PdfPCell(tabla2);
                        columnHeader.setPaddingBottom(5);
                        columnHeader.setBorder(0);
                        columnHeader.setBorderWidthLeft(0.5f);
                        columnHeader.setBorderWidthTop(0.5f);
                        break;
                    case 1:
                        tabla2 = new PdfPTable(1);
                        //tabla2.getDefaultCell().setBorder(0);
                        parrafo = new Paragraph("", extraSmallBold);
                        celda = new PdfPCell(parrafo);
                        celda.setBorder(0);
                        celda.setBorderWidthTop(0.5f);
                        tabla2.addCell(celda);
                        

                        columnHeader = new PdfPCell(tabla2);
                        columnHeader.setBorder(0);
                        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

                        //columnHeader.setBorderWidthTop(0.5f);
                        break;
                    case 2:
                        tabla2 = new PdfPTable(1);
                        tabla2.getDefaultCell().setBorder(0);
                        parrafo = new Paragraph("IVA.:", smallNormal);
                        celda = new PdfPCell(parrafo);
                        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celda.setPaddingLeft(20);
                        celda.setBorder(0);
                        tabla2.addCell(celda);
                        String numNro = String.format("%0" + (8 - num.length()) + "d%s", 0, num);
                        parrafo = new Paragraph("000" + this.punto + "-" + numNro, smallNormal);
                        celda = new PdfPCell(parrafo);
                        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celda.setPaddingLeft(20);
                        celda.setBorder(0);
                        tabla2.addCell(celda);

                        String fechaCae = doc.getFechaCae();
                        String ano = fechaCae.substring(0, 4);
                        String mes = fechaCae.substring(4, 6);
                        String dia = fechaCae.substring(6);
                        fechaCae = dia + "/" + mes + "/" + ano;

                        parrafo = new Paragraph("Fecha" + fechaCae, smallNormal);
                        celda = new PdfPCell(parrafo);
                        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                        celda.setPaddingLeft(20);
                        celda.setBorder(0);
                        tabla2.addCell(celda);
                        

                        columnHeader = new PdfPCell(tabla2);
                        columnHeader.setBorder(0);
                        columnHeader.setBorderWidthRight(0.5f);
                        columnHeader.setBorderWidthTop(0.5f);
                        
                        break;

                }
                
                //columnHeader.setBorderWidthTop(0.5f);
                table.addCell(columnHeader);
            }
            //fin renglon 2
            celdaGral=new PdfPCell(table);
            tableGral.addCell(celdaGral);
            
            
            //inicio renglon 3 - cuerpo detalle productos
            //inicio espacio entre detalle y encabezado
            celdaGral=new PdfPCell(new Paragraph("\n"));
            tableGral.addCell(celdaGral);
            //
            table=new PdfPTable(1);
            
            celda=new PdfPCell(CargarCuerpo());
            //celda.setFixedHeight(800f);
            table.addCell(celda);
            celdaGral=new PdfPCell(table);
            tableGral.addCell(celdaGral);
            //fin renglon 3
            //inicio pie de pagina
            table=new PdfPTable(1);
            celda=new PdfPCell(CargarPie());
            table.addCell(celda);
            celdaGral=new PdfPCell(table);
            tableGral.addCell(celdaGral);
            //final pie de pagina
            
            chapter.add(tableGral);
            documento.add(chapter);

            // Second page - some elements
            // Segunda página - Algunos elementos
            documento.close();

            File f = new File(arch);
            if (f.exists()) {

                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + arch);
                
                String destinoMail=FEJoe.mail;
                Mail mail=new Mail();
                System.out.println("direccion archivo "+f.getAbsolutePath());
                mail.setDireccionFile(f.getAbsolutePath());
                mail.setAsunto("factura electrónica");
                mail.setDetalleListado(nombreArch);
                mail.enviarMailFacturaElectronica(destinoMail,f.getAbsolutePath());
                
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
            Logger.getLogger(pdfFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arch;

    }

    private void nuevaPagina() {
        try {
            //imprimirPie();
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
            //imprimirEncabezado();
            renglon = 610;

            //aca empieza la iteracion
            //encabezados
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 9);
            cb.setTextMatrix(40, renglon);
            cb.showText("COD");
            cb.setTextMatrix(70, renglon);
            cb.showText("DESCRIPCION");
            cb.setTextMatrix(370, renglon);
            cb.showText("CANT.");
            cb.setTextMatrix(450, renglon);
            cb.showText("IVA");
            cb.setTextMatrix(500, renglon);
            cb.showText("P. TOTAL.");
            renglon = renglon - 20;

            //fin encabezados
            //imprimirCuerpo();
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    private Paragraph EncabezadoTabla() {
        Paragraph parrafo1 = new Paragraph();
        PdfPTable tableE = new PdfPTable(3);
        PdfPCell celda;
        Paragraph parrafo2;
        for (int colu = 0; colu < 3; colu++) {
            parrafo2 = new Paragraph(nombreVendedor + "\n" + razonSocialVendedor + "\n" + direccionVendedor);
            celda = new PdfPCell(parrafo2);
            tableE.addCell(celda);
        }
        parrafo1.add(tableE);
        return parrafo1;
    }
    private PdfPTable CargarCuerpo(){
        PdfPTable tablaD=new PdfPTable(6);
        tablaD.setWidthPercentage(100);
            float[] medidaCeldas = {2.55f,15.25f, 2.55f, 2.55f, 2.55f, 2.55f};
        try {
            tablaD.setWidths(medidaCeldas);
        } catch (DocumentException ex) {
            Logger.getLogger(pdfFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
            tablaD.getDefaultCell().setBorder(0);
        PdfPCell celda;
        Paragraph parrafo;
        
        
        //encabezado de detalle
        PdfPCell columnHeader;
            // Fill table rows (rellenamos las filas de la tabla).                
                parrafo=new Paragraph("ART",smallBold);
                columnHeader = new PdfPCell(parrafo);
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                columnHeader.setBackgroundColor(new BaseColor(247, 243,242));
                tablaD.addCell(columnHeader);
                parrafo=new Paragraph("DESCRIPCION",smallBold);
                columnHeader = new PdfPCell(parrafo);
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                columnHeader.setBackgroundColor(new BaseColor(247, 243,242));
                tablaD.addCell(columnHeader);
                parrafo=new Paragraph("CANT.",smallBold);
                columnHeader = new PdfPCell(parrafo);
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                columnHeader.setBackgroundColor(new BaseColor(247, 243,242));
                tablaD.addCell(columnHeader);
                parrafo=new Paragraph("P. UNIT.",smallBold);
                columnHeader = new PdfPCell(parrafo);
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                columnHeader.setBackgroundColor(new BaseColor(247, 243,242));
                tablaD.addCell(columnHeader);
                parrafo=new Paragraph("IVA",smallBold);
                columnHeader = new PdfPCell(parrafo);
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                columnHeader.setBackgroundColor(new BaseColor(247, 243,242));
                tablaD.addCell(columnHeader);
                parrafo=new Paragraph("TOTAL",smallBold);
                columnHeader = new PdfPCell(parrafo);
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                columnHeader.setBackgroundColor(new BaseColor(247, 243,242));
                tablaD.addCell(columnHeader);
            
            tablaD.setHeaderRows(1);
        //fin encabezado de detalle
        String descripcion;
            String monto;
            String recargo;
            String total;
            String totalFinal;
            Double tot = 0.00;
            Double totalD = 0.00;
            Double grav = 0.00;
            Double totalS = 0.00;
            
            
            int tama=listado.size();
            int renglones=34;
            int restantes=renglones - tama;
            itl=listado.listIterator();
            String descripcionArt = null;
            int items = 0;
            Integer renItem = 1;
            while (itl.hasNext()) {
                saldo = (DetalleFacturas) itl.next();
                
//vencimiento=saldo.getVencimientoString();

                descripcion = "";

                monto = Numeros.ConvertirNumero(saldo.getPrecioUnitario());
                recargo = "";
                total = "nada";
                //recargo=String.valueOf(saldo.getRecargo());
                //tot=tot + saldo.getTotal();
                //total=String.valueOf(saldo.getTotal());
                
                
                //codigo
                parrafo=new Paragraph(String.valueOf(saldo.getIdArticulo()),smallNormal);
                celda=new PdfPCell(parrafo);
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablaD.addCell(celda);
                //descripcion
                if (saldo.getDescripcion().length() > 40) {
                        descripcionArt = saldo.getDescripcion().substring(0, 40);
                    } else {
                        descripcionArt = saldo.getDescripcion();
                    }
                parrafo=new Paragraph(descripcionArt,smallNormal);
                celda=new PdfPCell(parrafo);
                celda.setBorder(0);
                tablaD.addCell(celda);
                //cantidad
                parrafo=new Paragraph(String.valueOf(saldo.getCantidad()),smallNormal);
                celda=new PdfPCell(parrafo);
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablaD.addCell(celda);
                
                //precio unitario s/iva
                parrafo=new Paragraph(Numeros.ConvertirNumero(saldo.getPrecioUnitario()),smallNormal);
                celda=new PdfPCell(parrafo);
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablaD.addCell(celda);
                // porcentaje iva
                parrafo=new Paragraph("21.%",smallNormal);
                celda=new PdfPCell(parrafo);
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablaD.addCell(celda);
                tot = saldo.getCantidad() * saldo.getPrecioUnitario();
                
                //precio total unitario s/iva
                parrafo=new Paragraph(Numeros.ConvertirNumero(tot),smallNormal);
                celda=new PdfPCell(parrafo);
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablaD.addCell(celda);
                grav = grav + tot;
                        totalS = totalS + (tot);
                //renglones++;
            }
            for(int resto=0;resto < restantes;resto++){
                for(int colu=0;colu < 6;colu++){
                    parrafo=new Paragraph(" ",smallNormal);
                celda=new PdfPCell(parrafo);
                celda.setBorder(0);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablaD.addCell(celda);
                }
            }
        return tablaD;
    }
    private PdfPTable CargarPie(){
        PdfPTable tablaP=new PdfPTable(2);//table del pie de documento
        float[] medida={20.5f,8f};
        try {
            tablaP.setWidths(medida);
        } catch (DocumentException ex) {
            Logger.getLogger(pdfFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        PdfPCell celdaG;//celda general del pie
        PdfPTable tablaQr=new PdfPTable(2);
        tablaQr.getDefaultCell().setBorder(0);
        float[] medi={7f,13.5f};
        try {
            tablaQr.setWidths(medi);
        } catch (DocumentException ex) {
            Logger.getLogger(pdfFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        PdfPCell celdaQr;
        PdfPTable tablaTo=new PdfPTable(2);
        PdfPCell celdaTo;
        Paragraph parrafoP;
        //inicio QR y total
        String totalF = Numeros.ConvertirNumero(doc.getImporteTotal());
        String totalFF=totalF.replace(",", ".");
            try {
                if (imagen1 != null) {

            } else {
                imagen1 = Image.getInstance(doc.getNombreQr());
                }
                imagen1.scaleAbsolute(80, 80);
                celdaQr=new PdfPCell(imagen1);
                celdaQr.setPaddingTop(10);
                celdaQr.setPaddingLeft(10);
                celdaQr.setPaddingBottom(10);
                celdaQr.setPaddingRight(10);
                celdaQr.setBorder(0);
                tablaQr.addCell(celdaQr);
                parrafoP=new Paragraph("SON PESOS:"+NumberToLetterConverter.convertNumberToLetter(totalFF),extraSmallBold);
                celdaQr=new PdfPCell(parrafoP);
                celdaQr.setHorizontalAlignment(Element.ALIGN_LEFT);
                celdaQr.setVerticalAlignment(Element.ALIGN_BOTTOM);
                celdaQr.setPaddingBottom(10);
                celdaQr.setPaddingLeft(10);
                celdaQr.setBorder(0);
                tablaQr.addCell(celdaQr);
                
                
            } catch (BadElementException ex) {
                Logger.getLogger(pdfFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(pdfFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
            celdaG=new PdfPCell(tablaQr);
            tablaP.addCell(celdaG);//primer celda superior izquierda (contiene QR y total en letras
            //inicio totales
            
            
            
            
            parrafoP=new Paragraph("SUB TOTAL",smallBold);
            celdaTo=new PdfPCell(parrafoP);
            celdaTo.setBorder(0);
            celdaTo.setBorderWidthRight(1);
            celdaTo.setPaddingTop(20);
            celdaTo.setPaddingRight(5);
            celdaTo.setBackgroundColor(new BaseColor(247, 243,242));
            celdaTo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaTo.addCell(celdaTo);
            
            Double sub = doc.getImporteNeto();
                Double iva = doc.getImpuestoLiquido();
                
            parrafoP=new Paragraph("$ " + Numeros.ConvertirNumero(sub),smallNormal);
            celdaTo=new PdfPCell(parrafoP);
            celdaTo.setBorder(0);
            celdaTo.setPaddingTop(20);
            celdaTo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaTo.addCell(celdaTo);
            
            if (doc.getListadoIva() != null) {
                    Iterator iIva = doc.getListadoIva().listIterator();
                    TiposIva tipos;
                    while (iIva.hasNext()) {
                        tipos = (TiposIva) iIva.next();
                        
                    

                    //renglon = renglon - 10;
                
            
            parrafoP=new Paragraph(tipos.getDescripcion(),smallBold);
            celdaTo=new PdfPCell(parrafoP);
            celdaTo.setBorder(0);
            celdaTo.setPaddingTop(20);
            celdaTo.setPaddingBottom(10);
            celdaTo.setPaddingRight(5);
            celdaTo.setBorderWidthRight(1);
            celdaTo.setBackgroundColor(new BaseColor(247, 243,242));
            celdaTo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaTo.addCell(celdaTo);
            
            parrafoP=new Paragraph("$ " + Numeros.ConvertirNumero(tipos.getImporte()),smallNormal);
            celdaTo=new PdfPCell(parrafoP);
            celdaTo.setBorder(0);
            celdaTo.setPaddingTop(20);
            celdaTo.setPaddingBottom(10);
            celdaTo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaTo.addCell(celdaTo);
                    }
            }
            parrafoP=new Paragraph("TOTAL",smallBold);
            celdaTo=new PdfPCell(parrafoP);
            celdaTo.setBorder(0);
            
            celdaTo.setBorderWidthTop(1);
            celdaTo.setBorderWidthRight(1);
            celdaTo.setPaddingTop(10);
            celdaTo.setPaddingRight(5);
            celdaTo.setBackgroundColor(new BaseColor(247, 243,242));
            celdaTo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaTo.addCell(celdaTo);
            
            parrafoP=new Paragraph("$ "+totalF,smallBold);
            celdaTo=new PdfPCell(parrafoP);
            celdaTo.setBorder(0);
            
            celdaTo.setBorderWidthTop(1);
            celdaTo.setPaddingTop(10);
            celdaTo.setBackgroundColor(new BaseColor(247, 243,242));
            celdaTo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaTo.addCell(celdaTo);
            
            
            //final totales
            celdaG=new PdfPCell(tablaTo);
            celdaG.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaP.addCell(celdaG);
            
            
        //
        //inicio codigo de barra
            PdfPTable table=new PdfPTable(1);
            table.setWidthPercentage(100);
            PdfPCell celda;
            if (codeEAN != null) {

            } else {
                codeEAN = new Barcode128();
                codeEAN.setCodeType(Barcode.CODE128);

                String ccuit = encabezado.getCuit().replace("-", "");
                codigoB = ccuit + doc.getNumeroPuntoDeVenta() + comF + doc.getCae() + doc.getCaeVto() + "3";
            }
            codeEAN.setCode(codigoB);
            img = codeEAN.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.BLACK);
            img.scaleAbsolute(300,35);
            celda=new PdfPCell(img);
            celda.setPaddingTop(10);
            celda.setPaddingBottom(10);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(celda);
            celdaG=new PdfPCell(table);
            celdaG.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaP.addCell(celdaG);
            //fin codigo de barra
            //inicio datos cae
            PdfPTable tableCae=new PdfPTable(1);
            parrafoP=new Paragraph(vencimiento,smallBold);
            celda=new PdfPCell(parrafoP);
            celda.setBorder(0);
            tableCae.addCell(celda);
            parrafoP=new Paragraph(vencimiento1,extraSmall);
            celda=new PdfPCell(parrafoP);
            celda.setBorder(0);
            tableCae.addCell(celda);
            parrafoP=new Paragraph("Datos de Facturación Electrónica",extraSmall);
            celda=new PdfPCell(parrafoP);
            celda.setBorder(0);
            tableCae.addCell(celda);
            
            //fin datos cae
            
            celdaG=new PdfPCell(tableCae);
            celdaG.setPaddingLeft(20);
            celdaG.setPaddingTop(10);
            celdaG.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaP.addCell(celdaG);
            
        
        return tablaP;
    }
}
