/**
 *Clase: ControladorCorreo
 *
 *@version: 0.1
 *
 *Fecha de Creación: 19/03/2020
 *
 *Fecha de Modificación:
 *
 *@autor: Yanvier
 *
 *Copyright: CECAR
 *
 */
package edu.cecar.controlador;

import edu.cecar.modelo.Mensaje;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class ControladorCorreo {

    public List<Mensaje> leerCorreoGmail(String email, String pass) {
        List<Mensaje> emails = new ArrayList<>();

        // Se obtiene la Session
        Properties prop = new Properties();
        prop.setProperty("mail.imap.starttls.enable", "false");
        prop.setProperty(
                "mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.imap.socketFactory.fallback", "false");
        prop.setProperty("mail.imap.port", "993");
        prop.setProperty("mail.imap.socketFactory.port", "993");
        Session sesion = Session.getInstance(prop);

        try {

            Store store = sesion.getStore("imaps");
            store.connect(
                    "imap.gmail.com", email, pass);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] mensajes = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (int i = 0; i < mensajes.length; i++) {
                Mensaje mensaje = new Mensaje();
                Date fecha = mensajes[i].getReceivedDate();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

                String fechaEmail = formatter.format(fecha);
                String from = mensajes[i].getFrom()[0].toString();
                String asunto = mensajes[i].getSubject();
                String to = email;

                mensaje.setFrom(from);
                mensaje.setTo(to);
                mensaje.setFechaRecibido(fechaEmail);
                mensaje.setAsunto(asunto);
                mensaje.setContenido(mensajes[i]);
                emails.add(mensaje);

            }

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

        emails.sort(Comparator.comparing(Mensaje::getFechaRecibido).reversed());

        return emails;
    }

    public List<Mensaje> leerCorreoHotmail(String email, String pass) {
        List<Mensaje> emails = new ArrayList<>();

        // Se obtiene la Session
        Properties prop = new Properties();
        prop.setProperty("mail.imap.starttls.enable", "false");
        prop.setProperty(
                "mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.imap.socketFactory.fallback", "false");
        prop.setProperty("mail.imap.port", "993");
        prop.setProperty("mail.imap.socketFactory.port", "993");
        Session sesion = Session.getInstance(prop);

        try {

            Store store = sesion.getStore("imaps");
            store.connect(
                    "outlook.office365.com", email, pass);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] mensajes = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (int i = 0; i < mensajes.length; i++) {
                Mensaje mensaje = new Mensaje();
                Date fecha = mensajes[i].getReceivedDate();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

                String fechaEmail = formatter.format(fecha);
                String from = mensajes[i].getFrom()[0].toString();
                String asunto = mensajes[i].getSubject();
                String to = email;

                mensaje.setFrom(from);
                mensaje.setTo(to);
                mensaje.setFechaRecibido(fechaEmail);
                mensaje.setAsunto(asunto);
                mensaje.setContenido(mensajes[i]);
                emails.add(mensaje);

            }

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

        emails.sort(Comparator.comparing(Mensaje::getFechaRecibido).reversed());

        return emails;
    }

    public boolean enviarCorreo(String email, String pass, String tipoCuenta, String destinatario, String asunto, String mensaje) {
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", email);
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect(email, pass);
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
       
    }

}
