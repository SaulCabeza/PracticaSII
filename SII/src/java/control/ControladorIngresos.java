package control;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import proyectosii.Ingresos;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import proyectosii.Proyectos;
import servicio.IngresosFacade;
import servicio.ProyectosFacade;

@ManagedBean(name = "ingresoBean")
@ViewScoped

public class ControladorIngresos implements Serializable {

    @EJB
    private IngresosFacade ingf;
    @EJB
    private ProyectosFacade proyf;

    private Ingresos ing;
    private Ingresos editingr;
    private BigDecimal pr;
    private BigDecimal editpr;
    private String nombreIg;

    private ArrayList<Ingresos> listarIngresos;
    private ArrayList<Ingresos> listaBusqueda;
    private ArrayList<Proyectos> listaProy;
    
    public ControladorIngresos() {
        editingr = new Ingresos();
        ing = new Ingresos();
        nombreIg = "";
        listaBusqueda = new ArrayList<>();
        listarIngresos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        listarIngresos = new ArrayList<>(ingf.findAll());
        listaProy = new ArrayList<>(proyf.findAll());
    }

    public BigDecimal getEditpr() {
        return editpr;
    }

    public void setEditpr(BigDecimal editpr) {
        this.editpr = editpr;
    }
    
    public Ingresos getEditingr() {
        return editingr;
    }

    public void setEditingr(Ingresos editingr) {
        this.editingr = editingr;
    }

    public BigDecimal getPr() {
        return pr;
    }

    public void setPr(BigDecimal pr) {
        this.pr = pr;
    }

    public ArrayList<Proyectos> getListaProy() {
        return listaProy;
    }

    public void setListaProy(ArrayList<Proyectos> listaProy) {
        this.listaProy = listaProy;
    }


    public void buscar() {
        listaBusqueda = new ArrayList<>();
        for (Ingresos in : listarIngresos) {
            if (in.getDescripcion().toUpperCase().startsWith(nombreIg.toUpperCase())) {
                listaBusqueda.add(in);
            }
        }
    }

    public ArrayList<Ingresos> getListarIngresos() {
        return listarIngresos;
    }

    public void setListaIngresos(ArrayList<Ingresos> listaIngresos) {
        this.listarIngresos = listaIngresos;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addIngreso() throws Exception {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (listarIngresos == null) listarIngresos = new ArrayList<>();
        if (ing.getDescripcion() == null || ing.getDescripcion().equals("")) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado una descripción", "Ingreso sin descripción"));
        if (ing.getFecha()== null) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado una fecha", "Ingreso sin fecha"));
        if (ing.getCantidad() == null) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha proporcionado una cantidad", "Ingreso sin cantidad"));
        if (pr == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede crear un ingreso si no se asocia a un proyecto", "No se puede crear un ingreso si no se asocia a un proyecto"));
        }else {
            try {
                Ingresos i = new Ingresos(ing.getCodigoIngreso(), ing.getFecha(), ing.getDescripcion(), ing.getCantidad());
                if (pr != null) {
                    i.setProyectosCódigo(darProyecto(pr));
                }
                ingf.create(i);
            } catch (Exception e) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error en la transacción"));
            } finally {
                listarIngresos = new ArrayList<>(ingf.findAll());
            }
        ing = new Ingresos();
        }
    }

    public void editar(Ingresos ingr) {
        editpr = ingr.getProyectosCódigo().getCódigo();
        editingr = new Ingresos(ingr.getCodigoIngreso(), ingr.getFecha(), ingr.getDescripcion(), ingr.getCantidad());
        ingr.setEditable(true);
        for(Ingresos in : listarIngresos) if(in.getCodigoIngreso()!=ingr.getCodigoIngreso()) in.setEditable(false);
    }

    public void guardar(Ingresos ingr) {
        Ingresos i = new Ingresos(editingr.getCodigoIngreso(), editingr.getFecha(), editingr.getDescripcion(), editingr.getCantidad());
        i.setProyectosCódigo(darProyecto(editpr));
        ingf.edit(i);
        ingr.setEditable(false);
        listarIngresos = new ArrayList<>(ingf.findAll());
    }

    public void eliminarIngreso(Ingresos in) {
        ingf.remove(in);
        listarIngresos = new ArrayList<>(ingf.findAll());
    }

    public Ingresos getIng() {
        return ing;
    }

    public void setIng(Ingresos ing) {
        this.ing = ing;
    }

    public String getNombreIg() {
        return nombreIg;
    }

    public void setNombreIg(String nombreIg) {
        this.nombreIg = nombreIg;
    }

    public ArrayList<Ingresos> getListaBusqueda() {
        return listaBusqueda;
    }

    public void setListaBusqueda(ArrayList<Ingresos> listaBusqueda) {
        this.listaBusqueda = listaBusqueda;
    }

    private Proyectos darProyecto(BigDecimal pr) {
        Proyectos res = new Proyectos();
        for (Proyectos p : listaProy) {
            if (pr.equals(p.getCódigo())) {
                res = p;
                break;
            }
        }
        return res;
    }
}
