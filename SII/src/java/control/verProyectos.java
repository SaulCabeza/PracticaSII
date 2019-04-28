package control;

import java.util.LinkedList;
import proyectosii.Proyectos;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class verProyectos {
    
    private LinkedList<Proyectos> listarProyectos;
    
    public void listar() {
        listarProyectos = new LinkedList<Proyectos>();
        for (int i = 5; i < 10; i++) {
            
        }
    }
}
