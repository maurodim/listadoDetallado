/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import com.barcodelib.barcode.QRCode;

/**
 *
 * @author mauro
 */
public class GenerarQr {
    int udm=0,resol=72,rot=0;
    float mi=0.00f,md=0.00f,ms=0.00f,min=0.00f,tam=3;

    public GenerarQr(String dato,String nombre) {
        try{
            QRCode c=new QRCode();
            c.setData(dato);
            c.setDataMode(QRCode.MODE_BYTE);
            c.setUOM(udm);
            c.setLeftMargin(mi);
            c.setRightMargin(md);
            c.setTopMargin(ms);
            c.setBottomMargin(min);
            c.setResolution(resol);
            c.setRotate(rot);
            c.setModuleSize(tam);
            
            
            //String archivo="imagenes/"+nombre+"_qr.gif";
            c.renderBarcode(nombre);
            
        }catch(Exception e){
            //System.out.println(e);
        }
    }
    
}
