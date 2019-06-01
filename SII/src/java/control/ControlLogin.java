package control;

import proyectosii.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ejb.EJB;
import servicio.UsuarioFacade;

@Named(value = "login")
@RequestScoped
public class ControlLogin {

    private String usuario;
    private String contrasenia;
    private String rol;
    private List<Usuario> usuarios;
    @EJB
    private UsuarioFacade userf;
    @Inject
    private ControlAutorizacion ctrl;

    /**
     * Creates a new instance of Login
     */
    public ControlLogin() {
        usuarios = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
         usuarios = userf.findAll();
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
    
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String autenticar() {
        usuarios = userf.findAll();
        for(Usuario u : usuarios){
            if(usuario.equals(u.getUsuario()) && contrasenia.equals(u.getContrasenia())) {
                ctrl.setUsuario(u);
                return ctrl.home();
            }
        }
        return "index.xhtml";
    } 
}
