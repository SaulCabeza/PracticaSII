package control;

import proyectosii.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "login")
@RequestScoped
public class ControlLogin {
    
    private String usuario;
    private String contrasenia;
    private List<Usuario> usuarios;
    
    @Inject
    private ControlAutorizacion ctrl;

    /**
     * Creates a new instance of Login
     */
    public ControlLogin() {
        usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("pepe", "asdf", Usuario.Rol.NORMAL));
        usuarios.add(new Usuario("manolo", "qwer", Usuario.Rol.ADMINISTRADOR));
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String autenticar() {
        for(Usuario u : usuarios){
            if(usuario.equals(u.getUsuario()) && contrasenia.equals(u.getContrasenia())) {
                ctrl.setUsuario(u);
                return ctrl.home();
            }
        }
        return "index.xhtml";
    }
    
}
