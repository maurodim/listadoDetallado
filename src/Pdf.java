
import Objetos.PdfListado;
import com.lowagie.text.DocumentException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File carpeta=new File("c:/listadosHdr");
        if(!carpeta.isDirectory()){
            carpeta.mkdirs();
        }
        PdfListado pdf=new PdfListado();
        pdf.start();
    }
    
}
