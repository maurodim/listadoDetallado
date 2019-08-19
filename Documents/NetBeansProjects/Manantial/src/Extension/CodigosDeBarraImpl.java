/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extension;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author andy
 */
public class CodigosDeBarraImpl implements CodigosDeBarra{

    public CodigosDeBarraImpl() {
    }
    
    private Barcode128 barraGrafica(String texto, float altoBarra, float factorGrosorBarra){
        Barcode128 b128 = new Barcode128();
        
        if(altoBarra > 0.0f) b128.setBarHeight(altoBarra);
        if(factorGrosorBarra > 0.0f) {b128.setN(factorGrosorBarra); System.out.println("modificando factor X"+factorGrosorBarra);}
        
        b128.setCode(texto);
        b128.setCodeType(Barcode128.EAN13);
        
        
        
        System.out.println("Informacion -> "+b128.getBarcodeSize().toString());
       /*
        The default parameters are:
        x = 0.8f;
        font = BaseFont.createFont("Helvetica", "winansi", false);
        size = 8;
        baseline = size;
        barHeight = size * 3;
        textAlignment = Element.ALIGN_CENTER;
        codeType = CODE128;*/
 
        return b128;
    }
    @Override
    public Image barraCode128(String texto){
//        Barcode128 b128 = new Barcode128();
//        b128.setCode(texto);
//        b128.setCodeType(Barcode128.CODE128);
        
       /* b128.setBarHeight(20);
        b128.setN(1.0f);*/
        
        Barcode128 b128 = this.barraGrafica(texto, 0, 0);
        
        System.out.println("info BarrCode128: "+b128.getBarcodeSize().toString());
        
        Image imagen = b128.createAwtImage(Color.BLACK, Color.WHITE);
        return imagen;
    }

    @Override
    public com.itextpdf.text.Image barraCode128Pdf(String texto, float altoBarra, float factorGrosorBarra, PdfContentByte cb,int anch,int altt) {
       Barcode128 b128 = this.barraGrafica(texto, altoBarra, factorGrosorBarra);
       //b128.setGuardBars(true);
       b128.setX(0.70f);
       
       com.itextpdf.text.Image imagen = b128.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.GRAY);
       //imagen=redimensionar(imagen,50,20);
       //imagen.setAnnotation(new Annotation("B128", texto));
       imagen.scaleAbsolute(anch, altt);
      return imagen;
    }
    
    
    @Override
    public Image redimensionar(Image original, int nuevoAncho, int nuevoAlto){
        int anchoImagenOriginal = original.getWidth(null);
        int altoImagenOriginal = original.getHeight(null);
        
        BufferedImage biOriginal = new BufferedImage(anchoImagenOriginal, altoImagenOriginal, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2dOriginal = biOriginal.createGraphics();
        g2dOriginal.drawImage(original, 0, 0, null);
        g2dOriginal.dispose();
       
        return biOriginal.getScaledInstance(nuevoAncho, 
                                            nuevoAlto,
                                            Image.SCALE_SMOOTH);
    }
}
