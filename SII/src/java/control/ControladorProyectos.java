package control;

import proyectosii.Proyectos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@ManagedBean(name = "proyectoBean")
@SessionScoped

public class ControladorProyectos {
    
    private ArrayList<Proyectos> listarProyectos;
    
    public ControladorProyectos() {
        listarProyectos = new ArrayList<Proyectos>();
        listarProyectos.add(new Proyectos(new BigDecimal("001"), "pr1", "prueba", new BigInteger("20"), new BigInteger("30"), new BigInteger("40"), new BigInteger("4000")));
        listarProyectos.add(new Proyectos(new BigDecimal("002"), "pr1", "prueba", new BigInteger("20"), new BigInteger("30"), new BigInteger("40"), new BigInteger("4000")));
        listarProyectos.add(new Proyectos(new BigDecimal("003"), "pr1", "prueba", new BigInteger("20"), new BigInteger("30"), new BigInteger("40"), new BigInteger("4000")));
    }

    public ArrayList<Proyectos> getListarProyectos() {
        return listarProyectos;
    }

    public void setListarProyectos(ArrayList<Proyectos> listarProyectos) {
        this.listarProyectos = listarProyectos;
    }
   
}
