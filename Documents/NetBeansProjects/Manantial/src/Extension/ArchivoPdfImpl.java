/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extension;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 *
 * @author andy
 */
public class ArchivoPdfImpl implements ArchivoPdf{
    private CodigosDeBarra codigoBarra;
    private Document documento;
    private FileOutputStream fisicoPdf;
    private PdfWriter escritor;
    private List<FormatoCodigoBarras> listado;
    private Path rutaArchivo;

    public ArchivoPdfImpl() {
        codigoBarra = new CodigosDeBarraImpl();
        listado = new ArrayList<>();
        rutaArchivo = null;
    }

    @Override
    public Path getRutaArchivo() {
        return rutaArchivo;
    }
    
    @Override
    public void crearDocumento(float margenIzquierdo, float margenDerecho, float margenSuperior, float margenInferior) {
        documento = new Document(PageSize.LETTER, 
                                 margenIzquierdo, 
                                 margenDerecho, 
                                 margenSuperior, 
                                 margenInferior);
    }
    
    private void cerrarDocumento(){
        try {
              
               documento.close();
               fisicoPdf.close();
               escritor.close();
           } catch (IOException ex) {
               Logger.getLogger(ArchivoPdfImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    @Override
    public void nombrePdf(String nombreArchivo, Path directorio){
       Path rutaPdf = directorio.resolve(nombreArchivo);
       System.out.println("rutaPdf: "+rutaPdf); 
        
       if(Files.exists(directorio) && nombreArchivo.contains(".pdf")){
           try {
               // Borrando el archivo pdf repetido
               Files.deleteIfExists(rutaPdf);
               
               // Creando nuevo archivo pdf
               fisicoPdf = new FileOutputStream(
                    Files.createFile(rutaPdf)
                         .toFile()
               );
               
               // Asignando la ruta de referencia
               rutaArchivo = rutaPdf;
               
               // Creando la instancias con los parametros del documento digital y fisico
               escritor = PdfWriter.getInstance(documento, fisicoPdf);
               escritor.setInitialLeading(0); // Espaciado interlineado
               
               // Abriendo el documento
               documento.open();
               
           } catch (IOException | DocumentException ex) {
               Logger.getLogger(ArchivoPdfImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else{
           System.out.println("Directorio no existe: "+directorio.toString()+" o archivo no cumple con requerimientos:"+nombreArchivo);
           this.cerrarDocumento();
       }
        
       
    }
    
    private void agregarImagen(Image imagen, float anchoPreferido, float altoPreferido){
        imagen.scaleToFit(anchoPreferido, altoPreferido);
        imagen.setAlignment(Chunk.ALIGN_CENTER);
    }
    
    
    private PdfPTable crearCuadriculaConImagenes(int columnas, List<FormatoCodigoBarras> listado){
        PdfPTable tabla = new PdfPTable(columnas);
        PdfPCell celda = new PdfPCell();
        
        listado.forEach(elemento -> { // Recorriendo todas las imagenes de interes con la cantidad necesaria de cada una
            
            IntStream.range(0, elemento.getCantidadRequerida())
                     .forEach(indice ->{
                        //celdas = new PdfPCell(elemento.getImagen(), true);
                        celda.setImage(elemento.getImagen());
                        //celda.setPadding(indice);
                        //celda.addElement(elemento.getImagen());
                        tabla.addCell(celda);
                     });
        });
        
        //celdas.setFixedHeight();
        
        return tabla;
    }
    
    @Override
    public void nuevaEtiqueta(String contenido, int cantidadRequerida, float altoBarra, float factorGrosorBarra,int ancho,int alto ){
        listado.add(
                new FormatoCodigoBarras(
                        codigoBarra.barraCode128Pdf(contenido,altoBarra, factorGrosorBarra, escritor.getDirectContent(),ancho,alto),cantidadRequerida,ancho,alto));
        
    }
    
    @Override
    public void generandoGridConEtiquetas(int columnasGrid){
        try {
            documento.add(this.crearCuadriculaConImagenes(columnasGrid, listado));
            
        } catch (DocumentException ex) {
            Logger.getLogger(ArchivoPdfImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.cerrarDocumento();
        }    
    }
   /* 
    public static void main(String [] args){
        ArchivoPdf apdf = new ArchivoPdfImpl();
        apdf.crearDocumento(10, 10, 20, 0);
        apdf.nombrePdf("pruabasEtiquetas.pdf", Paths.get("/home/andy/PDF"));
        apdf.nuevaEtiqueta("135135456", 5, 0, 0);
        apdf.nuevaEtiqueta("865521548", 25, 0, 0);
        apdf.nuevaEtiqueta("967741213", 30, 0, 0);
        apdf.nuevaEtiqueta("885544771", 15, 0, 0);       
        apdf.generandoGridConEtiquetas(5);
    }*/
    
    
}// Fin de la clase
