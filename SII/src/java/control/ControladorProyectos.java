package control;

import proyectosii.Proyectos;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import servicio.ProyectosFacade;

@ManagedBean(name = "proyectoBean")
@ViewScoped

public class ControladorProyectos implements Serializable{
    
    @EJB
    private ProyectosFacade proyf;
    
    private Proyectos proy;
    private Proyectos editproy;
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
        editproy = new Proyectos();
        proy = new Proyectos();
        nombrePr = "";
        listarProyectos = new ArrayList<>();
        listaBusqueda = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        listarProyectos = new ArrayList<>(proyf.findAll());
    }

    public ArrayList<Proyectos> getListarProyectos() {
        return listarProyectos;
    }

    public void setListarProyectos(ArrayList<Proyectos> listarProyectos) {
        this.listarProyectos = listarProyectos;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addProyecto() throws Exception {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (listarProyectos == null) listarProyectos = new ArrayList<>();
        else if (proy.getNombreProyecto() == null || proy.getNombreProyecto().equals("")) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado un nombre para el proyecto", "Proyecto sin nombre"));
        else if (proy.getDescripción() == null || proy.getDescripción().equals("")) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado una descripción", "Proyecto sin descripción"));
        else if (proy.getCosteTotal() == null) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado un coste total", "Proyecto sin coste total"));
        else {
            try {
                Proyectos p = new Proyectos(proy.getCódigo(), proy.getNombreProyecto(), proy.getDescripción(), proy.getRepartoCombustible(), proy.getRepartoMantenimiento(), proy.getRepartoContenedor(), proy.getCosteTotal());
                proyf.create(p);
            } catch (Exception e) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error en la transacción"));
            } finally {
                listarProyectos = new ArrayList<>(proyf.findAll());
            }
            proy = new Proyectos();
        }  
    }
    
    public void editar(Proyectos p) {
        editproy = new Proyectos(p.getCódigo(), p.getNombreProyecto(), p.getDescripción(), p.getRepartoCombustible(), p.getRepartoMantenimiento(), p.getRepartoContenedor(), p.getCosteTotal());
        p.setEditable(true);
        for(Proyectos pr : listarProyectos) if(pr.getCódigo() != p.getCódigo()) pr.setEditable(false);
    }
   
    public void guardar(Proyectos pr) {
        Proyectos p = new Proyectos(editproy.getCódigo(), editproy.getNombreProyecto(), editproy.getDescripción(), editproy.getRepartoCombustible(), editproy.getRepartoMantenimiento(), editproy.getRepartoContenedor(), editproy.getCosteTotal());
        proyf.edit(p);
        pr.setEditable(false);
        listarProyectos = new ArrayList<>(proyf.findAll());
    }
    
    public void deleteProyecto(Proyectos pr) {
        proyf.remove(pr);
        listarProyectos = new ArrayList<>(proyf.findAll());
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

    public ProyectosFacade getProyf() {
        return proyf;
    }

    public void setProyf(ProyectosFacade proyf) {
        this.proyf = proyf;
    }

    public Proyectos getEditproy() {
        return editproy;
    }

    public void setEditproy(Proyectos editproy) {
        this.editproy = editproy;
    }
    
}
