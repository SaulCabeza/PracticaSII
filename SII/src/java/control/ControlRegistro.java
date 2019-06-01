/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.Serializable;
import java.util.List;
import proyectosii.Usuario;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import servicio.IngresosFacade;
import servicio.UsuarioFacade;

@ManagedBean(name = "registro")
@RequestScoped
public class ControlRegistro implements Serializable{

    @EJB
    private UsuarioFacade userf;
    @EJB
    private IngresosFacade ingf;

    private Usuario usuario;
    private String repass;

    public ControlRegistro() {
        usuario = new Usuario();
        repass = "";
    }

    public String registrarUsuario() {
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (usuario.getUsuario() == null || usuario.getUsuario().equals("")) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha introducido ningún nombre usuario", "Introduzca un nombre de usuario"));
            return "registro.xhtml";
        }
        if (comprobarUsuario()) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario ya está en uso", "Introduzca otro nombre de usuario"));
            return "registro.xhtml";
        } else {
            if (usuario.getContrasenia() == null) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado una contraseña", "No se ha proporcionado una contraseña"));
                return "registro.xhtml";
            }
            if (!usuario.getContrasenia().equals(repass)) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Las contraseñas no coinciden", "Las contraseñas no coinciden"));
                return "registro.xhtml";
            }
            try {
                System.out.println(usuario.getUsuario());
                System.out.println(usuario.getContrasenia());
                Usuario nuevo = new Usuario(usuario.getUsuario(), usuario.getContrasenia(), "NORMAL");
                userf.create(nuevo);
                return "index.xhtml";
            } catch (Exception e) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error en la transacción"));
            }
            
        }
        return "index.xhtml";

    }

    public boolean comprobarUsuario() {
        
        List<Usuario> usuarios = userf.findAll();
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario.getUsuario())) {
                return true;
            }
        }
        return false;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getRepass() {
        return repass;
    }

}
