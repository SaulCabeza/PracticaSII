package control;


import java.util.ArrayList;
import proyectosii.Proyectos;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class ControladorProyectos {
    
    private ArrayList<Proyectos> listarProyectos;
    
    public ControladorProyectos() {
        listarProyectos = new ArrayList<Proyectos>();
        listarProyectos.add(new Proyectos());
        
    }
    
    
}
