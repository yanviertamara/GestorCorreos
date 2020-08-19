/**
 *Clase: Mensaje
 *
 *@version: 0.1
 *
 *Fecha de Creación: 25/03/2020
 *
 *Fecha de Modificación: 
 *
 *@autor: Yanvier
 *
 *Copyright: CECAR
 *
*/
package edu.cecar.modelo;

import javax.mail.Part;

/**
 *
 * 
 */
public class Mensaje {
    
    private String from;
    private String to;
    private String asunto;
    private String fechaRecibido;
    private Part contenido;
    public Mensaje() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(String fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public Part getContenido() {
        return contenido;
    }

    public void setContenido(Part contenido) {
        this.contenido = contenido;
    }
    
    
}
