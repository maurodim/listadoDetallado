/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;


import Configuracion.Propiedades;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author Administrador
 */
public class Mail {
    private final Properties propiedades=new Properties();
    private String password=Propiedades.getCLAVEMAIL();
    private Session sesion;
    private String direccionFile;
    private String detalleListado;
    private String asunto;

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    
    public void setDetalleListado(String detalleListado) {
        this.detalleListado = detalleListado;
    }
    
    
    public void setDireccionFile(String direccionFile) {
        this.direccionFile = direccionFile;
    }
    
    private void init(){
        propiedades.put("mail.smtp.host",Propiedades.getSERVIDORMAIL());
        propiedades.put("mail.smtp.starttls.enable","false");
        propiedades.put("mail.smtp.port",Propiedades.getPUERTOMAIL());
        propiedades.put("mail.smtp.mail.sender",Propiedades.getMAIL());
        propiedades.put("mail.smtp.user",Propiedades.getUSUARIOMAIL());
        propiedades.put("mail.smtp.auth","true");
        sesion=Session.getDefaultInstance(propiedades);
        
    }
    

    /**
     *
     * @param mailCliente
     * @param numeroComprobante
     * @throws MessagingException
     */
    public void enviarMailFacturaElectronica(String mailCliente,String numeroComprobante) throws MessagingException{
        init();
        try{
            asunto=Propiedades.getNOMBRECOMERCIO()+" le envía su comprobante electrónico";
            MimeMessage mensaje=new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress((String)propiedades.get("mail.smtp.mail.sender")));
            mensaje.addRecipient(Message.RecipientType.TO,new InternetAddress(mailCliente));
            //mensaje.addRecipient(Message.RecipientType.CC,new InternetAddress("comercial@sidercon.com"));
            mensaje.setSubject(asunto);
            //System.out.println("mail en envio "+asunto+" clave: "+password);
            BodyPart texto=new MimeBodyPart();
            texto.setText(Propiedades.getNOMBRECOMERCIO()+" le agradece su compra   \n Saludos");
            BodyPart adjunto=new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(direccionFile)));
            adjunto.setFileName(detalleListado);
            MimeMultipart multiParte=new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            //mensaje.setText("El reparto del vehiculo esta cerrado para el reparto. Motivo :CAPACIDAD DE CARGA COMPLETADA");
            mensaje.setContent(multiParte);
            Transport t=sesion.getTransport("smtp");
            t.connect((String)propiedades.get("mail.smtp.user"), password);
            t.sendMessage(mensaje,mensaje.getAllRecipients());
            t.close();
        }catch(MessagingException me){
            System.err.println("EL MENSAJE NO SE PUDO ENVIAR "+me);
        }
    }
}
