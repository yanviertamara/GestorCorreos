/**
 *Clase: ControladorMensaje
 *
 *@version: 0.1
 *
 *Fecha de Creación: 26/03/2020
 *
 *Fecha de Modificación:
 *
 *@autor: Yanvier
 *
 *Copyright: CECAR
 *
 */
package edu.cecar.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 *
 */
public class ControladorMensaje {

    public void analizaParteDeMensaje(Part unaParte) {
        try {

            if (unaParte.isMimeType("multipart/*")) {
                System.out.println("Multipart");
                Multipart multi;
                List<BodyPart> partes = new ArrayList<>();

                multi = (Multipart) unaParte.getContent();

                for (int j = 0; j < multi.getCount(); j++) {
                    BodyPart bp = multi.getBodyPart(j);
                    partes.add(bp);

                }
                analizarMultipart(partes);

            } else {

                if (unaParte.isMimeType("text/*")) {
                    System.out.println("Texto " + unaParte.getContentType());
                    System.out.println(unaParte.getContent());

                    JFrame frame = new JFrame();
                    JEditorPane pane = new JEditorPane();
                    JScrollPane scrollPane = new JScrollPane(pane);
                    pane.setContentType("text/html");

                    String data = (String) unaParte.getContent();
                    pane.setText(data);
                    pane.setEditable(false);
                    frame.add(scrollPane);

                    frame.setSize(800, 600);
                    

                    frame.setVisible(true);
                    System.out.println("---------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void analizarMultipart(List<BodyPart> part) {
        JFrame frame = new JFrame();
        JEditorPane pane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(pane);

        for (int i = 0; i < part.size(); i++) {
            try {
                BodyPart unaParte = part.get(i);
                if (unaParte.isMimeType("text/*")) {
                    System.out.println("Texto " + unaParte.getContentType());
                    System.out.println(unaParte.getContent());

                    pane.setContentType("text/html ; charset= utf-8");
                    String data = (String) unaParte.getContent();
                    pane.setText(data);
                    pane.setEditable(false);
                    frame.add(scrollPane);
                    frame.setSize(800, 600);
                    

                    frame.setVisible(true);
                    System.out.println("---------------------------------");
                }
            } catch (MessagingException ex) {
                //Logger.getLogger(prueba4.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(prueba4.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
