/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
import interfaces.Transaccionable;
import java.io.FileInputStream;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetos.ConeccionLocal;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 *
 * @author mauro di
 */
public class LeerExcel {
    private String sql;
    private Transaccionable tra;
    
   public void leerExcel1(String fileName) throws SQLException {
       tra=new ConeccionLocal();
       List cellDataList = new ArrayList();
try
{
/**
* Create a new instance for FileInputStream class
*/
FileInputStream fileInputStream = new FileInputStream(fileName);
/**
* Create a new instance for POIFSFileSystem class
*/
POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
/*
* Create a new instance for HSSFWorkBook Class
*/
HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
HSSFSheet hssfSheet = workBook.getSheetAt(0);
/**
* Iterate the rows and cells of the spreadsheet
* to get all the datas.
*/
Iterator rowIterator = hssfSheet.rowIterator();
while (rowIterator.hasNext())
{
HSSFRow hssfRow = (HSSFRow) rowIterator.next();
Iterator iterator = hssfRow.cellIterator();
List cellTempList = new ArrayList();
while (iterator.hasNext())
{
HSSFCell hssfCell = (HSSFCell) iterator.next();
cellTempList.add(hssfCell);
}
cellDataList.add(cellTempList);
}
}
catch (Exception e)
{
e.printStackTrace();
}
/**
* Call the printToConsole method to print the cell data in the
* console.
*/
printToConsole(cellDataList);
}
/**
* This method is used to print the cell data to the console.
* @param cellDataList - List of the data's in the spreadsheet.
*/
private void printToConsole(List cellDataList)
{
    String error=""; 
    int fila=0;
    
    Boolean verif=false;
    ArrayList lstArt=new ArrayList();
    String unidadDeMedida="";
    Double peso=0.00;
    ResultSet rs;
    sql="";
    for (int i = 0; i < cellDataList.size(); i++)
    {
        List cellTempList = (List) cellDataList.get(i);
        
        
        String descrip="";
        String comprobante = null;
       String fecha = null;
       String numeroF = null;
       String idCliente = null;
       String cliente = null;
       String condicion = null;
       String cuit = null;
       String neto = null;
       String iva = null;
       String total = null;
       String espacio;
       Integer aa=0;
       String periodo="2016-03";
       Integer numero=0;
       String comparativo;
       Integer guardar=0;
       String tipoComprobante = null;
       String fecha1 = null;
       String numeroComprobante = null;
       String rubro = null;
       String descripcion = null;
       String descripcion1;
       Double totD=0.00;
       
       Integer id=0;
       
        for (int j = 0; j < cellTempList.size(); j++)
        {
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
            String stringCellValue = hssfCell.toString();
            //System.err.println("Contenido: "+j+" "+stringCellValue);
            descrip="";
            if(i > 0){
                switch (j){
                    case 0:
                        if(stringCellValue.equals("")){
                            rubro="NN";
                        }else{
                            rubro=stringCellValue;
                            descrip=String.valueOf(stringCellValue);
                        }
                        //System.out.println("rubro: "+j+" "+stringCellValue);
                        break;
                    case 1:
                        descripcion="";
                        if(stringCellValue.equals("")){
                            //System.out.println("Precio: "+j+" VACIO");
                            guardar=1;
                        }else{
                        descripcion=stringCellValue;
                        //System.out.println("descripcion: "+j+" "+stringCellValue);
                            guardar=0;
                        }
                        break;
                    case 2:
                        descripcion1="";
                        if(stringCellValue.equals("")){
                            
                        }else{
                        descripcion1=stringCellValue;
                        descripcion=descripcion+" "+descripcion1;
                        descripcion=descripcion.replaceAll("'"," ");
                        }
                        //System.out.println("descripcion2: "+j+" "+stringCellValue);
                        break;
                    case 3:
                        if(stringCellValue.equals("")){
                            System.out.println("Precio: "+j+" VACIO");
                            totD=0.00;
                        }else{
                            total=stringCellValue;
                            total=total.substring(1);
                            totD=Numeros.ConvertirStringADouble(total);
                            total=Numeros.ConvetirNumeroDosDigitos(totD);
                            
                        }
                        if(guardar==0){
                        System.out.println("Rubro: "+rubro+" Descripcion "+descripcion);
                        System.out.println("Precio: "+j+" "+stringCellValue);
                        sql="insert into articulos (nombre,precio,rubron) values ('"+descripcion+"',"+totD+",'"+rubro+"')";
                        tra.guardarRegistro(sql);
                        /*
                        sql="select last_insert_id()";
                        rs=tra.leerConjuntoDeRegistros(sql);
                        id=0;
                            try {
                                while(rs.next()){
                                    id=rs.getInt(1);
                                }
                                //rs.close();
                                sql="update articulos set barras="+id+" where id="+id;
                                tra.guardarRegistro(sql);
                            } catch (SQLException ex) {
                                Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            */
                        }
                {
                    try {
                        sleep(15);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    default:
                        System.out.println(sql);
                        //tra.guardarRegistro(sql);
                        fila++;
                        break;
                        
                }
                
                
                
            }
            
            //fac.modificar(cliente);
            
        }
        
    }
    sql="update articulos set barras=id";
    tra.guardarRegistro(sql);
    JOptionPane.showMessageDialog(null,"PROCESO EXITOSO \n CANTIDAD DE FILAS PROCESADAS "+fila);
   }
}
