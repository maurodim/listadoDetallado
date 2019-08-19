/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extension;

import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Image;

/**
 *
 * @author andy
 */
public interface CodigosDeBarra {
    /**Este metodo permite crear el codigo de barra CODE-128 a partir de una cadena de caracteres 
     
     * @param texto Cadena de caracteres
     * @return Image Imagen resultante codificado
     */
    public Image barraCode128(String texto);
    
    public com.itextpdf.text.Image barraCode128Pdf(String texto, float altoBarra, float factorGrosorBarra, PdfContentByte cb,int ancho,int alto);
    
    /**Este metodo permite redimensionar la imagen original a los nuevos valores de interes mediante un escalamiento Smooth 
     * 
     * @param original imagen de interes que se desea redimensionar
     * @param nuevoAncho valor al que se quiere redimensionar la imagen en X
     * @param nuevoAlto valor al que se quiere redimensionar la imagen en Y
     * 
     * @return Image imagen escalada
     */
    public Image redimensionar(Image original, int nuevoAncho, int nuevoAlto);
    
}
