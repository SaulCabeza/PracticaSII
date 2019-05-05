package control;

import proyectosii.Proyectos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "proyectoBean")
@SessionScoped

public class ControladorProyectos implements Serializable{
    
    private Proyectos proy;
    private String nombrePr;
    private ArrayList<Proyectos> listaBusqueda;
    private ArrayList<Proyectos> listarProyectos;
    
    public void buscar() {
        listaBusqueda = new ArrayList<>();
        for (Proyectos p : listarProyectos) {
            if(p.getNombreProyecto().toUpperCase().startsWith(nombrePr.toUpperCase())) listaBusqueda.add(p);
        }
    }
    
    public ControladorProyectos() {
        proy = new Proyectos();
        nombrePr = "";
        listarProyectos = new ArrayList<Proyectos>();
        listaBusqueda = new ArrayList<Proyectos>();
        listarProyectos.add(new Proyectos(BigDecimal.valueOf(1), "pr1", "prueba", new BigInteger("20"), new BigInteger("30"), new BigInteger("40"), new BigInteger("4000")));
        listarProyectos.add(new Proyectos(BigDecimal.valueOf(2), "pr1", "prueba", new BigInteger("20"), new BigInteger("30"), new BigInteger("40"), new BigInteger("4000")));
        listarProyectos.add(new Proyectos(BigDecimal.valueOf(3), "pr1", "prueba", new BigInteger("20"), new BigInteger("30"), new BigInteger("40"), new BigInteger("4000")));
    }

    public ArrayList<Proyectos> getListarProyectos() {
        return listarProyectos;
    }

    public void setListarProyectos(ArrayList<Proyectos> listarProyectos) {
        this.listarProyectos = listarProyectos;
    }
    
    public void addProyecto() {
        boolean ins = true;
        if(proy.getCódigo() == null || proy.getCódigo().intValue() < 0) ins = false;
        for(Proyectos p : listarProyectos){
            if (p.getCódigo().equals(proy.getCódigo())) {
                ins = false;
                break;
            }
        }
        if(ins) listarProyectos.add(new Proyectos(proy.getCódigo(), proy.getNombreProyecto(), proy.getDescripción(), proy.getRepartoCombustible(), proy.getRepartoMantenimiento(), proy.getRepartoContenedor(), proy.getCosteTotal()));
        else{
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de proyecto inválido", "Código de proyecto inválido"));
        }
        proy = new Proyectos();
    }
    
    public String deleteProyecto(Proyectos pr) {
        listarProyectos.remove(pr);
        return "proyectos.xhtml";
    }

    public Proyectos getProy() {
        return proy;
    }

    public void setProy(Proyectos proy) {
        this.proy = proy;
    }

    public String getNombrePr() {
        return nombrePr;
    }

    public void setNombrePr(String nombrePr) {
        this.nombrePr = nombrePr;
    }

    public ArrayList<Proyectos> getListaBusqueda() {
        return listaBusqueda;
    }

    public void setListaBusqueda(ArrayList<Proyectos> listaBusqueda) {
        this.listaBusqueda = listaBusqueda;
    }
    
}
