package control;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import proyectosii.OrdenesPago;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import proyectosii.Egresos;
import servicio.EgresosFacade;
import servicio.OrdenesPagoFacade;

@ManagedBean(name = "ordenPagoBean")
@ViewScoped

public class ControladorOrdenesPago {
    
    @EJB
    private OrdenesPagoFacade ordenPf;
    @EJB
    private EgresosFacade egresof;

    private OrdenesPago ordenP;
    private OrdenesPago editordenP;
    private String nombreOrd;
    
    private ArrayList<OrdenesPago> listaBusqueda;
    private ArrayList<OrdenesPago> listarOrdenesPago;
    
    public void buscar() {
        listaBusqueda = new ArrayList<>();
        for (OrdenesPago ord : listarOrdenesPago) {
            if (ord.getEmisor().toUpperCase().startsWith(nombreOrd.toUpperCase())) listaBusqueda.add(ord);
        }
    }
    
    public ControladorOrdenesPago() {
        editordenP = new OrdenesPago();
        ordenP = new OrdenesPago();
        nombreOrd = "";
        listaBusqueda = new ArrayList<>();
        listarOrdenesPago = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        listarOrdenesPago = new ArrayList<>(ordenPf.findAll());
    }

    public ArrayList<OrdenesPago> getListarOrdenesPago() {
        return listarOrdenesPago;
    }

    public void setListarOrdenesPago(ArrayList<OrdenesPago> listOP) {
        this.listarOrdenesPago = listOP;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addOrdenPago() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (listarOrdenesPago == null) listarOrdenesPago = new ArrayList<>();
        else if (ordenP.getEmisor() == null || ordenP.getEmisor().equals("")) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado un emisor", "Orden de pago sin emisor"));
        else if (ordenP.getFecha() == null) ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha proporcionado una fecha", "Orden de pago sin fecha"));
        else {
            try {
                OrdenesPago op = new OrdenesPago(ordenP.getNumero(), ordenP.getFecha(), ordenP.getEmisor());
                ordenPf.create(op);
            } catch (Exception e) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error en la transacción"));
            } finally {
                listarOrdenesPago = new ArrayList<>(ordenPf.findAll());
            }
            ordenP = new OrdenesPago();
        }   
    }
    
    public void editar(OrdenesPago ordP) {
        editordenP = new OrdenesPago(ordP.getNumero(), ordP.getFecha(), ordP.getEmisor());
        ordP.setEditable(true);
        for(OrdenesPago op : listarOrdenesPago) if(op.getNumero() != ordP.getNumero()) op.setEditable(false);
    }
    
    public void guardar(OrdenesPago ordP) {
       OrdenesPago op = new OrdenesPago(editordenP.getNumero(), editordenP.getFecha(), editordenP.getEmisor());
       ordenPf.edit(op);
       ordP.setEditable(false);
       listarOrdenesPago = new ArrayList<>(ordenPf.findAll());
    }
    
    public void deleteOrdenPago(OrdenesPago op) {
        List<Egresos> l = egresof.findAll();
        for(Egresos e : l){
            if(e.getOrdenesPagoNumero().getNumero().equals(op.getNumero())){
                e.setOrdenesPagoNumero(null);
                egresof.edit(e);
            }
        }
        ordenPf.remove(op);
        
        listarOrdenesPago = new ArrayList<>(ordenPf.findAll());
    }

    public OrdenesPago getOrdenP() {
        return ordenP;
    }

    public void setOrdenP(OrdenesPago ordenP) {
        this.ordenP = ordenP;
    }

    public String getNombreOrd() {
        return nombreOrd;
    }

    public void setNombreOrd(String nombreOrd) {
        this.nombreOrd = nombreOrd;
    }

    public ArrayList<OrdenesPago> getListaBusqueda() {
        return listaBusqueda;
    }

    public void setListaBusqueda(ArrayList<OrdenesPago> listaBusqueda) {
        this.listaBusqueda = listaBusqueda;
    }

    public OrdenesPagoFacade getOrdenPf() {
        return ordenPf;
    }

    public void setOrdenPf(OrdenesPagoFacade ordenPf) {
        this.ordenPf = ordenPf;
    }

    public OrdenesPago getEditordenP() {
        return editordenP;
    }

    public void setEditordenP(OrdenesPago editordenP) {
        this.editordenP = editordenP;
    }
    
     public EgresosFacade getEgresof() {
        return egresof;
    }

    public void setEgresof(EgresosFacade egresof) {
        this.egresof = egresof;
    }
    
    
}
