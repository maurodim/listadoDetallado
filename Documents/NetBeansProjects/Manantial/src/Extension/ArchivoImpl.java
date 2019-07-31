/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extension;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

/**
 *
 * @author andy
 */
public class ArchivoImpl implements Archivo{

    public ArchivoImpl() {
    }
    
    /*rutaIcono: jar:file:/home/andy/Documentos/DESARROLLO/NetBeansProjects/STDXML/dist/run388905976/STDXML.jar!/stdxml/recursos/icono-stdxml250.png
      ruta ->file:/home/andy/Documentos/DESARROLLO/NetBeansProjects/STDXML/dist/run388905976/STDXML.jar!/stdxml/modelo/ArchivoImpl.class
     path: /home/andy/Documentos/DESARROLLO/NetBeansProjects/STDXML/dist/run388905976/sociedades.txt
    */
    
    /**Este metodo entrega una ruta nueva recorriendo toda la ruta hasta que la condicion se cumpla truncando la misma*/
    private String truncarRutaExtension(String rutaArchivo, String condicion){
        Path rutaActual = Paths.get(rutaArchivo);
        String truncada = "";
        
        Iterator<Path> elementos = rutaActual.iterator();
        while(elementos.hasNext()){
            Path analisis = elementos.next();
            
            if(analisis.getFileName().toString().contains(condicion)){
              //truncada += analisis.toString().concat(File.separator);
              break;
            }else{
              truncada += analisis.toString().concat(File.separator);
            }
            
        }
        
        System.out.println("ruta Final= "+truncada);
        return truncada;
    }
    
    @Override
    public Path rutaNormalizadaLocal(String nombreArchivo) throws MalformedURLException, URISyntaxException{
        String rutaOriginal = getClass().getResource("ArchivoImpl.class").getFile();
        String rutaReal = null;
        
        System.out.println("RutaOriginal: "+rutaOriginal);
        
        if(rutaOriginal.contains(".exe")){ // Desde un jar
            rutaReal = rutaOriginal.substring(0, rutaOriginal.indexOf(".exe")).concat(nombreArchivo);
        }else{ // desde netbeans
            rutaReal = "file:"+rutaOriginal.substring(0, rutaOriginal.indexOf("ArchivoImpl.class")).concat(nombreArchivo);
        }
         
        System.out.println("ruta nueva ->"+rutaOriginal); 
        URL urlNueva = new URL(rutaReal);
         
        return Paths.get(urlNueva.toURI());  
    }
    
    @Override
    public Path normalizarRuta(String rutaActual){
        Path rutaModificada = null;
        URL urlNueva = null;
       
        try {
            urlNueva = Paths.get(rutaActual).toUri().toURL();
            //System.out.println("rutaExterna1: "+urlNueva.getPath());
            
            rutaModificada = Paths.get(urlNueva.toURI());
            //System.out.println("rutaExterna2: "+ruta.toString());
            
        } catch (MalformedURLException | URISyntaxException ex) { 
            Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rutaModificada;
    }
    
    @Override
    public Stream<String> leerAchivoLocal(String nombreArchivo) {
        Stream<String> lineas = null;
        Path ruta = null;
          
        try {
            
            ruta = this.rutaNormalizadaLocal(nombreArchivo);
             System.out.println("path: "+ruta.toString());
    
            lineas = Files.lines(ruta, Charset.forName("UTF-8"));
           
            
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          // if(lineas != null) lineas.close();
        }
        
        return lineas;
    }
    
    @Override
    public Stream<String> leerAchivoExterno(String rutaArchivo) {
        Stream<String> lineas = null;
        
        try {
            lineas = Files.lines(this.normalizarRuta(rutaArchivo), Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lineas;
    }

    @Override
    public Stream<Path> listarDirectoriosyArchivos(String rutaBase) {
        Stream<Path> directorios = null;
        
        try {
            Path rutaNormalizada = this.normalizarRuta(rutaBase);
            System.out.println("Privilegios: -> Lectura = "+Files.isReadable(rutaNormalizada));
           
            directorios = Files.walk(rutaNormalizada);
            
        } catch (IOException ex) {
            Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return directorios;
    }

    @Override
    public List<Path> listarDirectoriosyArchivosSupervisado(String rutaBase) {
      Supervisor supervisor = new Supervisor();
        
        try {
            Path rutaNormalizada = this.normalizarRuta(rutaBase);
            System.out.println("Privilegios: -> Lectura = "+Files.isReadable(rutaNormalizada));
           
            // Iniciando el analisis supervisado del directorio
            Files.walkFileTree(rutaNormalizada, supervisor);
            
        } catch (IOException ex) {
            Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Supervisor.contenido;
    }
    
    

    @Override
    public void copiarArchivo(Path archivoOriginal, Path rutaArchivo) {
        
        try {
            System.out.println("copia: "+Files.copy(archivoOriginal, rutaArchivo, StandardCopyOption.REPLACE_EXISTING));
                  
        } catch (IOException ex) {
            Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public Path verificarDuplicidadDirectorio(Path ruta) {
      Path salida = ruta;
       // System.out.println("verificar: "+ruta);
       // System.out.println("repetido: "+Files.exists(ruta));
      if(Files.exists(ruta)){
           LocalDateTime ldt = LocalDateTime.now();
           String fechaHora = ldt.toString() // 2018-11-14T13:06:42.703
                                 .replaceAll("-", "")
                                 .replaceAll(":", "")
                                 .replace("T", "-")
                                 .replace(".", "");
         salida = Paths.get(ruta.toString().concat("(").concat(fechaHora).concat(")"));
      }
       
      return salida;
    }

    private Path verificarArchivoExistente(Path archivoActual){
        Path salida = archivoActual;
        
        
        if(Files.exists(archivoActual)){
           String nombreArchivo = archivoActual.getFileName().toString();
           // System.out.println("existe: "+archivoActual.getFileName()); 
           
           
           if(nombreArchivo.contains("(")){ // se verifica si el existente ya fue numerado anteriormente para buscar la secuencia
              String numero = nombreArchivo.substring(nombreArchivo.indexOf("(")+1, nombreArchivo.indexOf(")"));
             //  System.out.println("numero: "+numero);
              int indice = Integer.valueOf(numero);
              
              String nuevoNombre = nombreArchivo.replace("("+indice+")", "("+(indice+1)+")");
              Path nuevaRuta = archivoActual.getParent().resolve( nuevoNombre );
              
            //   System.out.println("traia numeracion: nuevo: "+nuevaRuta);
              
               salida = this.verificarArchivoExistente(nuevaRuta);
                      
           }else{ // si no ha sido numerado se agrega un numero
                 
             salida = this.verificarArchivoExistente(
                     archivoActual.getParent().resolve(archivoActual.getFileName().toString().replace(".", "(1)."))
             );
             
            // System.out.println("agregandoNumeracion-> "+salida);
           }        
            
         //  System.out.println("duplicadoCorregido:: "+salida);
        }
       
        
       return salida;
    }
    
    @Override
    public void crearNuevoArchivoSecuencial(Path archivoFuente, Path archivoDestino){
        archivoDestino = this.verificarArchivoExistente(archivoDestino);
        
        try {
            System.out.println("destino: "+archivoDestino);
            Files.copy(archivoFuente, archivoDestino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Path fusionRutas(Path directorioBase, Path directorioOrigen, Path directorioDestino) {
       return Paths.get(
               directorioDestino.toString(), 
               directorioBase.subpath(
                       directorioOrigen.getNameCount()-1, 
                       directorioBase.getNameCount() 
               ).toString()
       );
    }
    
    

    @Override
    public Path verificarDirectorioBaseDestino(Path directorioBase, Path directorioOrigen, Path directorioDestino) {
       Path fusion = this.fusionRutas(directorioBase, directorioOrigen, directorioDestino); 
       Path nuevoDirectorioBase = this.verificarDuplicidadDirectorio(fusion);
       
       if(fusion.compareTo(nuevoDirectorioBase) == 0 ) 
           return null;
       else
           return nuevoDirectorioBase;          
    }

    @Override
    public void escribirArchivo(Path rutaDestino, List<String> lineas) {
       
        try {
             if(Files.exists(rutaDestino)){ // escribe al final
                    Files.write(rutaDestino, lineas, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
             }else{ // escribe todo el archivo
                   Files.write(rutaDestino, lineas, Charset.forName("UTF-8"), StandardOpenOption.WRITE);
             }
             
        } catch (IOException ex) {
                Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public boolean guardarImagenLocal(String nombreArchivoDestino, Image imagen) {
        boolean guardado = false;
        try {
            Path nuevaRuta = this.rutaNormalizadaLocal(nombreArchivoDestino);
            // Construyendo buffered image
            BufferedImage bImage = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // Obteniendo grafica
            Graphics2D bImageGraphics = bImage.createGraphics();

            // Dibujando Image (image) en BufferedImage (bImage)
            bImageGraphics.drawImage(imagen, null, null);

            // casteando
            RenderedImage rImage = (RenderedImage) bImage;
            
            // Guardando en HDD
            ImageIO.write(rImage, "jpg", nuevaRuta.toFile());
            
            guardado = true;
        } catch (MalformedURLException | URISyntaxException ex) {
            Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return guardado; 
    }
    
    /*
    public static void main(String args[]){
       
            ArchivoImpl archivo = new ArchivoImpl();
            //archivo.rutaNormalizadaLocal("andy.jpg");
           String ruta = "/home/andy/Documentos/DESARROLLO/NetBeansProjects/MANANTIAL/Manantial/Documents.jar";
           ruta = "file:/home/andy/Documentos/DESARROLLO/NetBeansProjects/STDXML/dist/run388905976/STDXML.jar!/stdxml/modelo/ArchivoImpl.class";
           System.out.println(ruta);
           archivo.truncarRutaExtension(ruta, 
                                         ".jar");
            
    
    }
    */
    
    
    
}