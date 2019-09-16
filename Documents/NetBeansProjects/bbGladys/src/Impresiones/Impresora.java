package Impresiones;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Configuracion.Propiedades;
import Conversores.Numeros;
import Depositos.RemitosInternos;
import Resto.Mesas;
import RestoDao.MesasDao;
import RestoInterface.Restable;
import Sucursales.Cajas;
import interfaceGraficas.Inicio;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.imageio.ImageIO;
import objetos.Articulos;
import objetos.Comprobantes;

/**
 *
 * @author hernan
 */
public class Impresora {

    Font fuente = new Font("Arial", Font.PLAIN, 9);
    Font fuente1 = new Font("Arial", Font.BOLD, 16);
    Font fuente3 = new Font("Arial", Font.PLAIN, 7);
    Font fuente4 = new Font("Arial", Font.BOLD, 7);
    Font fuente5 = new Font("Arial", Font.PLAIN, 16);
    Font fuente6 = new Font("Arial", Font.BOLD, 9);
    Font fuente7 = new Font("Sans Serif", Font.BOLD, 7);
    Font fuente8 = new Font("Arial", Font.PLAIN, 12);
    Font fuente9 = new Font("Arial", Font.BOLD, 5);
    Font fuente10 = new Font("Arial", Font.PLAIN, 12);
    Font fuente11 = new Font("Arial", Font.PLAIN,11);
    Font fuente12 = new Font("Arial", Font.BOLD, 11);
    PrintJob pj;
    Graphics pagina;

    /**
     * ******************************************************************
     * A continuación el constructor de la clase. Aquí lo único que	* hago es
     * tomar un objeto de impresion.	*
	*******************************************************************
     */
    public Impresora() {
        pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "SCAT", null);

    }

    /**
     * ******************************************************************
     * A continuación el método "imprimir(String)", el encargado de * colocar en
     * el objeto gráfico la cadena que se le pasa como * parámetro y se imprime.
     * *
	*******************************************************************
     */
    public void ImprimirRetiroDeEfectivo(Object factura) throws IOException {
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

        pagina = pj.getGraphics();
        try {
            BufferedImage imagen = ImageIO.read(new File("C://Gestion//imagen//logo.png"));
            pagina.drawImage(imagen, 63, 20, 174, 93, null);
            pagina.setFont(fuente11);
            pagina.setColor(Color.black);
            pagina.drawString("COMPROBANTE N° 00" + Inicio.sucursal.getNumero() + "-000" + caja.getNumeroDeComprobante(), 30, 130);
            pagina.setFont(fuente);
            pagina.drawString("FECHA :" + fec, 30, 140);
            pagina.drawString("SUCURSAL :" + Inicio.sucursal.getDescripcion(), 30, 150);
            pagina.drawString("CAJERO :" + Inicio.usuario.getNombre(), 30, 160);
            pagina.setFont(fuente11);
            Double monto = caja.getMontoMovimiento() * -1;
            pagina.drawString("MONTO : $ " + monto, 30, 190);
            pagina.setFont(fuente);
            pagina.drawString("CAJA N°: " + Inicio.caja.getNumero(), 30, 200);
            pagina.drawString("HORA :" + hrs, 30, 210);
            pagina.setFont(fuente1);
            pagina.drawString("RETIRO DE EFECTIVO ", 50, 280);
            //formulario derecho

            pagina.drawImage(imagen, 363, 20, 174, 93, null);
            pagina.setFont(fuente11);
            pagina.setColor(Color.black);
            pagina.drawString("COMPROBANTE N° 00" + Inicio.sucursal.getNumero() + "-000" + caja.getNumeroDeComprobante(), 320, 130);
            pagina.setFont(fuente);
            pagina.drawString("FECHA :" + fec, 320, 140);
            pagina.drawString("SUCURSAL :" + Inicio.sucursal.getDescripcion(), 320, 150);
            pagina.drawString("CAJERO :" + Inicio.usuario.getNombre(), 320, 160);
            pagina.setFont(fuente11);
            pagina.drawString("MONTO : $ " + monto, 320, 190);
            pagina.setFont(fuente);
            pagina.drawString("CAJA N°: " + Inicio.caja.getNumero(), 320, 200);
            pagina.drawString("HORA :" + hrs, 320, 210);
            pagina.setFont(fuente1);
            pagina.drawString("RETIRO DE EFECTIVO ", 350, 280);

            pagina.dispose();
            pj.end();
        } catch (Exception e) {
            System.out.println("LA IMPRESION HA SIDO CANCELADA..." + e);
        }

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

        pagina = pj.getGraphics();
        try {
            pagina.setFont(fuente7);
            pagina.drawString("CIERRE DE CAJA ", 150, 10);
            pagina.setFont(fuente3);
            pagina.setColor(Color.black);
            pagina.drawString("FECHA :" + fec, 30, 30);
            pagina.drawString("SUCURSAL :" + Propiedades.getNOMBRECOMERCIO(), 170, 30);
            pagina.drawString("CAJERO :" + Inicio.usuario.getNombre(), 30, 40);
            pagina.drawString("CAJA N°: " + Inicio.caja.getNumero(), 170, 40);
            pagina.drawString("HORA :" + hrs, 250, 40);
            pagina.setFont(fuente7);
            pagina.drawString("DETALLE", 30, 50);
            pagina.setFont(fuente10);
            int renglon = 50;
            Double totalVentas = 0.00;
            Double totalGtos = 0.00;
            Double saldoInicial = 0.00;
            Double totalRetiros = 0.00;
            Double saldoCaja = 0.00;
            Double pagoProveedores = 0.00;
            Iterator it = listado.listIterator();
            while (it.hasNext()) {
                Cajas caja = (Cajas) it.next();
                switch (caja.getTipoMovimiento()) {
                    case 1:
                        totalVentas = totalVentas + caja.getMontoMovimiento();
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
                    pagina.drawString(caja.getNumeroDeComprobante() + " - " + caja.getDescripcionMovimiento() + " $" + caja.getMontoMovimiento(), 30, renglon);
                }
            }
            pagina.setFont(fuente);
            pagina.drawString("SALDO INICIAL : $" + saldoInicial, 320, 50);
            pagina.drawString("TOTAL VENTAS : $" + totalVentas, 320, 60);
            pagina.drawString("TOTAL GASTOS : $" + totalGtos, 320, 70);
            pagina.drawString("TOTAL RETIROS EFECTIVO : $" + totalRetiros, 320, 80);
            pagina.drawString("TOTAL PAGO A PROVEEDORES : $" + pagoProveedores, 320, 90);
            pagina.drawString("SALDO DE CAJA : $" + saldoCaja, 320, 100);
            Double saldo = saldoInicial + totalVentas + totalGtos + pagoProveedores + saldoCaja + totalRetiros;
            pagina.setFont(fuente11);
            pagina.drawString("QUEDA EN CAJA : $" + saldo, 320, 120);

            pagina.dispose();
            pj.end();
        } catch (Exception e) {
            System.out.println("LA IMPRESION HA SIDO CANCELADA..." + e);
        }
    }

    public void ImprimirRemitoInterno(Object factura) throws IOException {
        RemitosInternos caja = (RemitosInternos) factura;
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
        ArrayList articList = new ArrayList();
        pagina = pj.getGraphics();
        try {
            BufferedImage imagen = ImageIO.read(new File("C://Gestion//imagen//logo.png"));
            pagina.drawImage(imagen, 123, 20, 174, 93, null);
            pagina.setFont(fuente11);
            pagina.setColor(Color.black);
            pagina.drawString("COMPROBANTE N° 00" + Inicio.deposito.getNumero() + "-000" + caja.getNumero(), 30, 130);
            pagina.setFont(fuente11);
            pagina.drawString("REMITO INTERNO", 320, 130);
            pagina.setFont(fuente);
            pagina.drawString("FECHA :" + fec, 30, 140);
            pagina.setFont(fuente11);
            pagina.drawString("Deposito Destino :" + caja.getDepositoDestino(), 30, 150);
            pagina.drawString("Deposito Origen :" + caja.getDepositoOrigen(), 30, 160);
            pagina.setFont(fuente);
            pagina.drawString("HORA :" + hrs, 320, 140);
            pagina.drawString("Usuario :" + Inicio.usuario.getNombre(), 320, 150);
            pagina.setFont(fuente11);
            //Double monto=caja.getMontoMovimiento();
            //pagina.drawString(" : $ "+monto,20,190);
            //pagina.setFont(fuente1);
            //pagina.drawString("RETIRO DE EFECTIVO ", 50,280);
            //formulario derecho
            pagina.setFont(fuente11);
            pagina.drawString("ARTICULO", 30, 190);
            pagina.drawString("CANTIDAD", 300, 190);
            int columna = 200;
            String cann = "";
            Iterator itRem = caja.getArticulos().listIterator();
            pagina.setFont(fuente);
            while (itRem.hasNext()) {
                Articulos articulo = (Articulos) itRem.next();
                articList.add(articulo);
                pagina.drawString(articulo.getDescripcionArticulo(), 30, columna);
                cann = String.valueOf(articulo.getCantidad());
                pagina.drawString(cann, 300, columna);
                columna = columna + 10;
            }
            columna = columna + 20;
            pagina.setFont(fuente11);
            pagina.drawString("FIRMA RECEPCION:___________________________________", 30, columna);

            //SALTO DE PAGINA
            pagina.dispose();
            pagina = pj.getGraphics();
            // aqui comienza el control interno
            //BufferedImage imagen= ImageIO.read(new File("C://Gestion//imagen//logo.png"));
            pagina.drawImage(imagen, 123, 20, 174, 93, null);
            pagina.setFont(fuente11);
            pagina.setColor(Color.black);
            pagina.drawString("COMPROBANTE N° 00" + Inicio.deposito.getNumero() + "-000" + caja.getNumero(), 30, 130);
            pagina.setFont(fuente11);
            pagina.drawString("CONTROL INTERNO", 320, 130);
            pagina.setFont(fuente);
            pagina.drawString("FECHA :" + fec, 30, 140);
            pagina.setFont(fuente11);
            pagina.drawString("Deposito Destino :" + caja.getDepositoDestino(), 30, 150);
            pagina.drawString("Deposito Origen :" + caja.getDepositoOrigen(), 30, 160);
            pagina.setFont(fuente);
            pagina.drawString("HORA :" + hrs, 320, 140);
            pagina.drawString("Usuario :" + Inicio.usuario.getNombre(), 320, 150);
            pagina.setFont(fuente11);
            //Double monto=caja.getMontoMovimiento();
            //pagina.drawString(" : $ "+monto,20,190);
            //pagina.setFont(fuente1);
            //pagina.drawString("RETIRO DE EFECTIVO ", 50,280);
            //formulario derecho
            pagina.setFont(fuente11);
            pagina.drawString("ARTICULO", 30, 190);
            pagina.drawString("CANTIDAD", 250, 190);
            pagina.drawString("COSTO", 330, 190);
            pagina.drawString("VENTA", 410, 190);
            columna = 200;
            cann = "";
            String costo = "";
            String venta = "";

            //articList=caja.getArticulos();
            int tamano = caja.getArticulos().size();
            System.out.println("CANTIDAD DE ARTICULOS :" + caja.getArticulos().size());

            //itRem.remove();
            Iterator itRem1 = articList.listIterator();
            pagina.setFont(fuente);
            Double costoTotal = 0.00;
            while (itRem1.hasNext()) {
                Articulos articulo = (Articulos) itRem1.next();
                pagina.drawString(articulo.getDescripcionArticulo(), 30, columna);
                cann = String.valueOf(articulo.getCantidad());
                costo = String.valueOf(articulo.getPrecioDeCosto());
                venta = String.valueOf(articulo.getPrecioUnitarioNeto());
                pagina.drawString(cann, 250, columna);
                pagina.drawString(costo, 330, columna);
                pagina.drawString(venta, 410, columna);
                costoTotal = costoTotal + (articulo.getPrecioDeCosto() * articulo.getCantidad());
                columna = columna + 10;
            }
            columna = columna + 20;
            pagina.setFont(fuente11);
            pagina.drawString("COSTO TOTAL :" + String.valueOf(costoTotal), 250, columna);

            pagina.dispose();
            pj.end();
        } catch (Exception e) {
            System.out.println("LA IMPRESION HA SIDO CANCELADA..." + e);
        }

    }

    public void ImprimiComprobante(Object factura) throws IOException {
        Comprobantes caja = (Comprobantes) factura;
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
        ArrayList articList = new ArrayList();
        pagina = pj.getGraphics();
        try {
            //BufferedImage imagen= ImageIO.read(new File("C://Gestion//imagen//logo.png"));
            //pagina.drawImage(imagen,123,20,174,93,null);

            // aqui comienza el control interno
            //BufferedImage imagen= ImageIO.read(new File("C://Gestion//imagen//logo.png"));
            //pagina.drawImage(imagen,123,20,174,93,null);
            pagina.setFont(fuente11);
            pagina.setColor(Color.black);
            pagina.drawString("COMPROBANTE N° 001-000" + caja.getNumero(), 40, 35);
            pagina.setFont(fuente11);
            pagina.drawString("", 320, 30);
            pagina.setFont(fuente10);
            pagina.drawString("FECHA :" + fec, 40, 50);
            Mesas mesa;
            Restable resto = new MesasDao();
            mesa = (Mesas) resto.CargarPorId(caja.getIdMesa());
            pagina.setFont(fuente11);
            pagina.drawString("Mesa :" + mesa.getId(), 40, 65);
            //pagina.drawString("Deposito Origen :"+caja.getDepositoOrigen(),20,160);
            pagina.setFont(fuente10);
            pagina.drawString("HORA :" + hrs, 480, 50);
            pagina.drawString("Mozo :" + mesa.getNombreMozo(), 480, 65);
            pagina.drawLine(30, 70, 600, 70);
            pagina.setFont(fuente11);
            //Double monto=caja.getMontoMovimiento();
            //pagina.drawString(" : $ "+monto,20,190);
            //pagina.setFont(fuente1);
            //pagina.drawString("RETIRO DE EFECTIVO ", 50,280);
            //formulario derecho
            pagina.setFont(fuente11);
            pagina.drawString("CANT.", 20, 82);
            pagina.drawString("ARTICULO", 60, 82);
            pagina.drawString("COD.", 350, 82);
            pagina.drawString("P. UNIT", 410, 82);
            pagina.drawString("P. TOTAL", 480, 82);
            int columna = 95;

            pagina.drawLine(30, columna, 600, columna);
            columna = columna + 15;
            String cann = "";
            String costo = "";
            String venta = "";
            String unitario = "";

            //articList=caja.getArticulos();
            int tamano = caja.getListadoDeArticulos().size();
            System.out.println("CANTIDAD DE ARTICULOS :" + caja.getListadoDeArticulos().size());

            //itRem.remove();
            Iterator itRem1 = caja.getListadoDeArticulos().listIterator();
            pagina.setFont(fuente10);

            Double costoTotal = 0.00;
            while (itRem1.hasNext()) {
                Articulos articulo = (Articulos) itRem1.next();
                //pagina.drawString("O", 25, columna);
                //pagina.drawOval(20, columna - 5, 5, 5);

                pagina.drawString(articulo.getCodigoAsignado(), 350, columna);
                pagina.drawString(articulo.getDescripcionArticulo(), 60, columna);
                cann = String.valueOf(articulo.getCantidad());
                costo = String.valueOf(articulo.getPrecioDeCosto());
                unitario = "$ " + Numeros.ConvetirNumeroDosDigitos(articulo.getPrecioUnitarioNeto());
                venta = "$ " + Numeros.ConvetirNumeroDosDigitos(articulo.getPrecioUnitario());
                pagina.drawString(cann, 25, columna);
                pagina.drawString(unitario, 410, columna);
                pagina.drawString(venta, 480, columna);
                if (articulo.getPrecioDeCosto() != null) {
                    costoTotal = costoTotal + (articulo.getPrecioDeCosto() * articulo.getCantidad());
                }
                columna = columna + 15;
            }
            columna = columna + 10;
            pagina.drawLine(30, columna, 600, columna);
            columna = columna + 12;
            pagina.setFont(fuente11);
            pagina.drawString("PRECIO TOTAL : $" + Numeros.ConvetirNumeroDosDigitos(caja.getMontoTotal()), 350, columna);
            columna = columna + 10;
            pagina.drawLine(30, columna, 600, columna);
            columna = columna + 10;
            pagina.setFont(fuente11);
            pagina.drawString("Gracias por su compra", 40, columna);
            columna = columna + 10;
            pagina.drawString(Propiedades.getNOMBRECOMERCIO(), 40, columna);
            columna = columna + 10;
            pagina.drawString("", 40, columna);
            pagina.dispose();
            pj.end();
        } catch (Exception e) {
            System.out.println("LA IMPRESION HA SIDO CANCELADA..." + e);
        }

    }

    public void ImprimirComanda(Mesas mesa,ArrayList listado) {
        Mesas caja = (Mesas) mesa;
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
        ArrayList articList = new ArrayList();
        pagina = pj.getGraphics();
        try {
            //BufferedImage imagen= ImageIO.read(new File("C://Gestion//imagen//logo.png"));
            //pagina.drawImage(imagen,123,20,174,93,null);

            // aqui comienza el control interno
            //BufferedImage imagen= ImageIO.read(new File("C://Gestion//imagen//logo.png"));
            //pagina.drawImage(imagen,123,20,174,93,null);
            pagina.setFont(fuente11);
            pagina.setColor(Color.black);
            pagina.drawString("N° 001-000" + caja.getIdComanda(), 40, 30);
            pagina.setFont(fuente11);
            pagina.drawString("COMANDA DE PEDIDOS", 210, 30);
            pagina.setFont(fuente);
            pagina.drawString("FECHA :" + fec, 40, 40);
            
            //Restable resto = new MesasDao();
            //mesa = (Mesas) resto.CargarPorId(caja.getIdMesa());
            pagina.setFont(fuente11);
            pagina.drawString("Mesa :" + mesa.getId()+" - "+mesa.getDescripcion(), 40, 50);
            //pagina.drawString("Deposito Origen :"+caja.getDepositoOrigen(),20,160);
            pagina.setFont(fuente);
            pagina.drawString("HORA :" + hrs, 220, 40);
            pagina.drawString("Mozo :" + mesa.getNombreMozo(), 220, 50);
            pagina.drawLine(30, 60, 600, 60);
            pagina.setFont(fuente11);
            //Double monto=caja.getMontoMovimiento();
            //pagina.drawString(" : $ "+monto,20,190);
            //pagina.setFont(fuente1);
            //pagina.drawString("RETIRO DE EFECTIVO ", 50,280);
            //formulario derecho
            pagina.setFont(fuente11);
            pagina.drawString("CANT.", 30, 70);
            pagina.drawString("ARTICULO", 90, 70);
            pagina.drawString("COD.", 250, 70);
            
            int columna = 80;

            pagina.drawLine(30, columna, 600, columna);
            columna = columna + 20;
            String cann = "";
            String costo = "";
            String venta = "";
            String unitario = "";

            //articList=caja.getArticulos();
            int tamano = listado.size();
            System.out.println("CANTIDAD DE ARTICULOS :" + listado.size());

            //itRem.remove();
            Iterator itRem1 = listado.listIterator();
            pagina.setFont(fuente8);

            Double costoTotal = 0.00;
            while (itRem1.hasNext()) {
                Articulos articulo = (Articulos) itRem1.next();
                //pagina.drawString("O", 25, columna);
                pagina.drawOval(20, columna - 5, 5, 5);

                pagina.drawString(articulo.getCodigoAsignado(), 250, columna);
                pagina.drawString(articulo.getDescripcionArticulo(), 90, columna);
                cann = String.valueOf(articulo.getCantidad());
                
                pagina.drawString(cann, 30, columna);
                
                columna = columna + 15;
            }
            columna = columna + 15;
            pagina.drawLine(30, columna, 600, columna);
           
            pagina.dispose();
            pj.end();
        } catch (Exception e) {
            System.out.println("LA IMPRESION HA SIDO CANCELADA..." + e);
        }

    }

}//FIN DE LA CLASE Impresora

