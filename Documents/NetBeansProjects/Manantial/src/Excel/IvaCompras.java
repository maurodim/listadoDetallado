/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import interfaces.Transaccionable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosR.Conecciones;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author Usuario
 */
public class IvaCompras {

    public void GenerrarInformeIvaCompras(String desde, String hasta) throws SQLException, FileNotFoundException {
        try {
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet("Iva Compras");
            /*
            * GENERAR LAS SIGUIENTES HOJAS
            * 1- DETALLE DE MOVIMIENTOS DE CAJA - LEE EN MOVIMIENTOS CAJA INDENTIFICANDO EL TIPO DE MOVIMIENTO, USUARIOS Y
            * NUMERO DE CAJA
            * 2- DETALLE DE ARTICULOS VENDIDOS: LISTADO DE MOVIEMIENTOS DE ARTICULOS, CON USUARIOS Y CAJA
            * 3- DETALLE DE GASTOS : MOVIMIENTOS DE CAJA DETALLANDO LOS GASTOS
            *
             */

            String ttx = "celda numero :";
            HSSFRow fila = null;
            HSSFCell celda;
            HSSFCell celda1;
            HSSFCell celda2;
            HSSFCell celda3;
            HSSFCell celda4;
            HSSFCell celda5;
            HSSFCell celda6;
            HSSFCell celda7;
            HSSFCell celda8;
            HSSFFont fuente = libro.createFont();
            //fuente.setFontHeight((short)21);
            fuente.setFontName(fuente.FONT_ARIAL);
            fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            String form = null;
            //String sql="SELECT fecha,tipo,numero,pto,alicuota,razon,(gravado) as gravadoR,(iva) as impuestoR,(total) as totalR FROM comprasfiscal where fechaRegistro between '"+desde+" 00:00:00.000' and '"+hasta+" 00:00:00.000' order by numero";
            String sql = "select comprasfiscal.fecha,comprasfiscal.tipo,comprasfiscal.NETONOGRAVADO,comprasfiscal.EXENTAS,comprasfiscal.PERCEPCIONIVA,comprasfiscal.IMPUESTOSNACIONALES,comprasfiscal.PERCEPCIONIB,comprasfiscal.IMPMUNICIPALES,comprasfiscal.IMPINTERNOS,comprasfiscal.CANTIDADALICUOTAIVA,comprasfiscal.OTROSTRIBUTOS,comprasfiscal.pto,comprasfiscal.id,comprasfiscal.razon,comprasfiscal.numero,comprasfiscal.cuit,comprasfiscal.total as totalR,comprasfiscalalicuota.gravado as gravadoR,comprasfiscalalicuota.alicuota,comprasfiscalalicuota.iva as impuestoR,tipocomprobantes.descripcion as tipocomprobante from comprasfiscal left join comprasfiscalalicuota on comprasfiscalalicuota.idcompras=comprasfiscal.id left join tipocomprobantes on tipocomprobantes.NUMEROAFIP=cast(comprasfiscal.TIPO as Integer) where comprasfiscal.fechaRegistro between '" + desde + " 00:00:00.000' and '" + hasta + " 00:00:00.000' order by comprasfiscal.fecha";
            System.out.println(sql);
            Transaccionable tra = new Conecciones();
            ResultSet rs = tra.leerConjuntoDeRegistros(sql);
            HSSFCellStyle titulo = libro.createCellStyle();
            titulo.setFont(fuente);
            //titulo.setFillBackgroundColor((short)22);
            titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            //for(int a=0;a < 100;a++){
            int col = 0;
            int a = 0;
            if (a == 0) {
                fila = hoja.createRow(a);
                celda = fila.createCell(0);
                celda.setCellStyle(titulo);
                celda.setCellValue("Fecha");
                celda1 = fila.createCell(1);
                celda1.setCellStyle(titulo);
                celda1.setCellValue("Tipo comp.");
                celda2 = fila.createCell(2);
                celda2.setCellStyle(titulo);
                celda2.setCellValue("Numero");
                celda3 = fila.createCell(3);
                celda3.setCellStyle(titulo);
                celda3.setCellValue("Razon Social");
                celda3 = fila.createCell(4);
                celda3.setCellStyle(titulo);
                celda3.setCellValue("Gravado");
                //

                celda4 = fila.createCell(5);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("Alícuota");

                celda4 = fila.createCell(6);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("Iva");
                //comienzan todos los impuestos internos
                celda4 = fila.createCell(7);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("No Gravado");
                celda4 = fila.createCell(8);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("Exento");
                celda4 = fila.createCell(9);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("Percep. IVA");
                celda4 = fila.createCell(10);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("Imp. Nacionales");
                celda4 = fila.createCell(11);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("Percep. IB");
                celda4 = fila.createCell(12);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("Imp. Municipales");
                celda4 = fila.createCell(13);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("Imp. Internos");
                celda4 = fila.createCell(14);
                celda4.setCellStyle(titulo);
                celda4.setCellValue("Otros Tributos");
                //fin de los impuestos internos

                celda5 = fila.createCell(15);
                celda5.setCellStyle(titulo);
                celda5.setCellValue("Total");

            }
            int cantidadAlicuotas = 1;
            int contadorAlicuotas = 1;
            int alicuo=0;
            String ivaA;
            while (rs.next()) {
                a++;
                //col=rs.getInt("tipoMovimiento");
                switch (col) {
                    case 1:

                        break;
                    default:

                        break;
                }
                fila = hoja.createRow(a);
                celda = fila.createCell(0);
                ttx = ttx;
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda.setCellValue(rs.getString("fecha"));
                celda1 = fila.createCell(1);
                ttx = ttx;
                celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda1.setCellValue(rs.getString("tipocomprobante"));
                celda2 = fila.createCell(2);
                celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
                String numero = rs.getString("pto") + "-" + rs.getString("numero").replaceFirst("80", "00");
                celda2.setCellValue(numero);
                celda6 = fila.createCell(3);
                celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda6.setCellValue(rs.getString("razon"));
                cantidadAlicuotas = rs.getInt("cantidadalicuotaiva");
                celda3 = fila.createCell(4);
                celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                celda3.setCellValue(rs.getDouble("gravadoR"));
                //comienzo impuestos
                ivaA=rs.getString("alicuota");
                alicuo=Integer.parseInt(ivaA);
                switch(alicuo){
                    case 1:
                        ivaA="NO GRAVADO";
                        break;
                    case 2:
                        ivaA="EXENTO";
                        break;
                    case 3:
                        ivaA="0 %";
                        break;
                    case 4:
                        ivaA="10.5 %";
                        break;
                    case 5:
                        ivaA="21 %";
                        break;
                    case 6:
                        ivaA="27 %";
                        break;
                }
                celda4 = fila.createCell(5);
                celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda4.setCellValue(ivaA);
                celda4 = fila.createCell(6);
                celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                celda4.setCellValue(rs.getDouble("impuestoR"));
                if (contadorAlicuotas == 1) {
                    celda4 = fila.createCell(7);
                    celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda4.setCellValue(rs.getDouble("netonogravado"));
                    celda4 = fila.createCell(8);
                    celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda4.setCellValue(rs.getDouble("exentas"));
                    celda4 = fila.createCell(9);
                    celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda4.setCellValue(rs.getDouble("percepcioniva"));
                    celda4 = fila.createCell(10);
                    celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda4.setCellValue(rs.getDouble("impuestosnacionales"));
                    celda4 = fila.createCell(11);
                    celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda4.setCellValue(rs.getDouble("percepcionib"));
                    celda4 = fila.createCell(12);
                    celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda4.setCellValue(rs.getDouble("impmunicipales"));
                    celda4 = fila.createCell(13);
                    celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda4.setCellValue(rs.getDouble("impinternos"));
                    celda4 = fila.createCell(14);
                    celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda4.setCellValue(rs.getDouble("otrostributos"));
                    //fin de impuestos

                    celda5 = fila.createCell(15);
                    //celda5.setCellFormula(rs.getString("observaciones"));
                    celda5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda5.setCellValue(rs.getDouble("totalR"));
                    //celda5.setCellValue(rs.getDate("fecha"));
                }
                if(cantidadAlicuotas > 1){
                    contadorAlicuotas++;
                }
                if (cantidadAlicuotas < contadorAlicuotas) {
                    contadorAlicuotas = 1;
                }

            }

            rs.close();
            //texto+="\r\n";
            String ruta = "fiscal/" + desde + "_" + hasta + " Iva Compras.xls";
            try {
                FileOutputStream elFichero = new FileOutputStream(ruta);
                try {
                    libro.write(elFichero);
                    elFichero.close();
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + ruta);
                } catch (IOException ex) {
                    Logger.getLogger(IvaCompras.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IvaCompras.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (InstantiationException ex) {
            Logger.getLogger(IvaCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(IvaCompras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
