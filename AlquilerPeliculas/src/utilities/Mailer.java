package utilities;

import entities.Mail;
//import com.sun.mail.smtp.SMTPTransport;

//import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {


    public static void EnviarEmail(Mail correo) throws MessagingException {
        Properties props = new Properties(); //propiedades a agragar  
        props.put("mail.smtp.port", "587"); //puesto de salida  
        props.put("mail.smtp.starttls.enable", "true"); //inicializar el servidor  
        props.put("mail.smtp.auth", "true"); //autentificacion   
        String identifica = correo.getOrigen().substring(  
        		correo.getOrigen().indexOf("@") + 1,
        		correo.getOrigen().indexOf("@") + 6);
        if (identifica.equals("gmail")) {  
            props.put("mail.smtp.host", "smtp.gmail.com"); //tipo de servidor        
            props.put("mail.smtp.socketFactory.port", "465"); //activar el puerto  
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
            props.put("mail.smtp.socketFactory.fallback", "false");  
        } else {  
            props.put("mail.smtp.host", "smtp.live.com"); //tipo de servidor  
        }
        Session session=Session.getInstance(props,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                //return new PasswordAuthentication(correo.getOrigen(), correo.getPassword());
            	return null;
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(correo.getOrigen()));
        for (String string : correo.getTo()) {
           msg.addRecipient(RecipientType.TO, new InternetAddress(string));
        }
        for (String string : correo.getCc()) {
               msg.addRecipient(RecipientType.CC, new InternetAddress(string));
        }
        for (String string : correo.getBcc()) {
               msg.addRecipient(RecipientType.BCC, new InternetAddress(string));
        }
        msg.setSubject(correo.getAsunto());
        msg.setText(correo.getCuerpo());
        Transport.send(msg);
   }


}