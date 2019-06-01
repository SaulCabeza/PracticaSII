/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import proyectosii.Usuario;

@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable{
    
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String home() {
        if (usuario != null) {
            String rol = usuario.getRol();
            switch (rol) {
                case "ADMIN":
                    return "inicio.xhtml";
                case "NORMAL":
                    return "inicio.xhtml";
                default:
                    return "inicio.xhtml";
            }
        } else {
            return "index.xhtml";
        }
    }
    
    public boolean darRol(){
        return this.usuario.getRol().equals("ADMIN");
    }

    public String logout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "index.xhtml";
    }

    public ControlAutorizacion() {
        
    }
}
