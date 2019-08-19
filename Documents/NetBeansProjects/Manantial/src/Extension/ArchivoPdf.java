/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extension;

import java.nio.file.Path;

/**
 *
 * @author andy
 */
public interface ArchivoPdf {
    public void crearDocumento(float margenIzquierdo, float margenDerecho, float margenSuperior, float margenInferior);
    public void nombrePdf(String nombreArchivo, Path directorio);
    public void nuevaEtiqueta(String contenido, int cantidadRequerida, float altoBarra, float factorGrosorBarra,int ancho,int alto );
    public void generandoGridConEtiquetas(int columnasGrid);
    public Path getRutaArchivo();
}
