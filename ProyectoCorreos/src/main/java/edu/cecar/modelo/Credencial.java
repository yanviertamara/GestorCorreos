/**
 *Clase: Credenciales
 *
 *@version: 0.1
 *
 *Fecha de Creación: 20/03/2020
 *
 *Fecha de Modificación: 
 *
 *@autor: Yanvier
 *
 *Copyright: CECAR
 *
*/
package edu.cecar.modelo;

import java.util.List;

/**
 *
 * 
 */
public class Credencial {
    
    private String email;
    private String password;
    private String tipoCuenta;

    public Credencial() {
    }

    public Credencial(String email, String password, String tipoCuenta) {
        this.email = email;
        this.password = password;
        this.tipoCuenta = tipoCuenta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}