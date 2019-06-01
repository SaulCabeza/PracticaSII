package control;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import proyectosii.Egresos;
import javax.faces.context.FacesContext;
import proyectosii.OrdenesPago;
import proyectosii.Proyectos;
import servicio.EgresosFacade;
import servicio.OrdenesPagoFacade;
import servicio.ProyectosFacade;

@ManagedBean(name = "egresoBean")
@ViewScoped

public class ControladorEgresos {
    
    @EJB
    private EgresosFacade egf;
    @EJB
    private ProyectosFacade proyf;
    @EJB
    private OrdenesPagoFacade ordf;
    
    private Egresos egreso;
    private Egresos editegreso;
    private BigDecimal pr;
    private BigDecimal editpr;
    private BigDecimal op;
    private BigDecimal editop;
    private String nombreEg;
    
    private ArrayList<Egresos> listaBusqueda;
    private ArrayList<Egresos> listarEgresos;
    private ArrayList<Proyectos> listaProy;
    private ArrayList<OrdenesPago> listaOrdP;
    
    public void buscar() {
        listaBusqueda = new ArrayList<>();
        for (Egresos eg : listarEgresos) {
            if (eg.getConcepto().toUpperCase().startsWith(nombreEg.toUpperCase())) listaBusqueda.add(eg);
        }
    }
    
    public ControladorEgresos() {
        egreso = new Egresos();
        editegreso = new Egresos();
        nombreEg = "";
        listaBusqueda = new ArrayList<Egresos>();
        listarEgresos = new ArrayList<Egresos>();
    }

    @PostConstruct
    public void init(){
        listarEgresos = new ArrayList<>(egf.findAll());
        listaProy = new ArrayList<>(proyf.findAll());
        listaOrdP = new ArrayList<>(ordf.findAll());
        
    }
    
    public ArrayList<Egresos> getListarEgresos() {
        return listarEgresos;
    }

    public void setListarEgresos(ArrayList<Egresos> listarEgresos) {
        this.listarEgresos = listarEgresos;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addEgreso() throws Exception{
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (listarEgresos == null) listarEgresos = new ArrayList<>();
        else if (egreso.getConcepto() == null || egreso.getConcepto().equals("")) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado un concepto", "Egreso sin concepto"));
        else if (egreso.getFecha() == null) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado una fecha", "Egreso sin fecha"));
        else if (egreso.getNombreBeneficiario() == null) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado un beneficiario", "Egreso sin beneficiario"));
        else if (egreso.getImporte() == null) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado un importe", "Egreso sin importe"));
        else if (pr == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede crear un ingreso si no se asocia a un proyecto", "No se puede crear un ingreso si no se asocia a un proyecto"));
        }else {
            try {
                Egresos e = new Egresos(egreso.getCodigoEgreso(), egreso.getNombreBeneficiario(), egreso.getConcepto(), egreso.getImporte(), egreso.getFecha());
                if (pr != null) {
                    e.setProyectosCódigo(darProyecto(pr));
                }
                if (op != null) {
                    e.setOrdenesPagoNumero(darOrden(op));
                }
                egf.create(e);
            } catch (Exception e) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error en la transacción"));
            } finally {
                listarEgresos = new ArrayList<>(egf.findAll());
            }
            egreso = new Egresos();
        }
    }
    
    public void editar(Egresos eg) {
        editpr = eg.getProyectosCódigo().getCódigo();
        if(eg.getOrdenesPagoNumero() != null){
           editop = eg.getOrdenesPagoNumero().getNumero();
        }
        editegreso = new Egresos(eg.getCodigoEgreso(),eg.getNombreBeneficiario(),eg.getConcepto(),eg.getImporte(),eg.getFecha());
        eg.setEditable(true);
        for(Egresos e : listarEgresos) if (e.getCodigoEgreso()!=eg.getCodigoEgreso()) e.setEditable(false);
    }
    
    public void guardar(Egresos eg) {
        Egresos e = new Egresos(editegreso.getCodigoEgreso(),editegreso.getNombreBeneficiario(),editegreso.getConcepto(),editegreso.getImporte(),editegreso.getFecha());
        e.setProyectosCódigo(darProyecto(editpr));
        if(editop != null) e.setOrdenesPagoNumero(darOrden(editop));
        egf.edit(e);
        eg.setEditable(false);
        listarEgresos = new ArrayList<>(egf.findAll());
    }
    
    public void deleteEgreso(Egresos eg) {
        egf.remove(eg);
        listarEgresos = new ArrayList<>(egf.findAll());
    }
    
    public Egresos getEgreso() {
        return egreso;
    }

    public void setEgreso(Egresos egreso) {
        this.egreso = egreso;
    }

    public String getNombreEg() {
        return nombreEg;
    }

    public void setNombreEg(String nombreEg) {
        this.nombreEg = nombreEg;
    }

    public ArrayList<Egresos> getListaBusqueda() {
        return listaBusqueda;
    }

    public void setListaBusqueda(ArrayList<Egresos> listaBusqueda) {
        this.listaBusqueda = listaBusqueda;
    }

    public EgresosFacade getEgf() {
        return egf;
    }

    public void setEgf(EgresosFacade egf) {
        this.egf = egf;
    }

    public ProyectosFacade getProyf() {
        return proyf;
    }

    public void setProyf(ProyectosFacade proyf) {
        this.proyf = proyf;
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

    public Egresos getEditegreso() {
        return editegreso;
    }

    public void setEditegreso(Egresos editegreso) {
        this.editegreso = editegreso;
    }

    public BigDecimal getEditpr() {
        return editpr;
    }

    public void setEditpr(BigDecimal editpr) {
        this.editpr = editpr;
    }

    public OrdenesPagoFacade getOrdf() {
        return ordf;
    }

    public void setOrdf(OrdenesPagoFacade ordf) {
        this.ordf = ordf;
    }

    public ArrayList<OrdenesPago> getListaOrdP() {
        return listaOrdP;
    }

    public void setListaOrdP(ArrayList<OrdenesPago> listaOrdP) {
        this.listaOrdP = listaOrdP;
    }

    public BigDecimal getOp() {
        return op;
    }

    public void setOp(BigDecimal op) {
        this.op = op;
    }

    public BigDecimal getEditop() {
        return editop;
    }

    public void setEditop(BigDecimal editop) {
        this.editop = editop;
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
    
    private OrdenesPago darOrden(BigDecimal op) {
        OrdenesPago res = new OrdenesPago();
        for (OrdenesPago o : listaOrdP) {
            if (op.equals(o.getNumero())) {
                res = o;
                break;
            }
        }
        return res;
    }
}
