/**
 *Clase: ControladorSms
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

import com.twilio.Twilio;

/**
 *
 * 
 */
public class ControladorSms {
    public static final String ACCOUNT_SID = "AC6cc4e0e79c61343058ddf27a47374a07";
    public static final String AUTH_TOKEN = "a1e81e110acc8ca88703fb8cc8ac7263";
    //public static String phone = "+573166845456";

    public void enviarSMS(String phone) throws Exception {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                new com.twilio.type.PhoneNumber(phone),
                new com.twilio.type.PhoneNumber("+12058464995"),
                "Mensaje Recibido Revise Gestor de Correos")
                .create();
        System.out.println(message.getSid());
    }

}
