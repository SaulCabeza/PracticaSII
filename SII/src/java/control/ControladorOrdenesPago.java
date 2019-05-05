package control;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import proyectosii.OrdenesPago;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ManagedBean(name = "ordenPagoBean")
@SessionScoped

public class ControladorOrdenesPago implements Serializable{
    
    private OrdenesPago ordenP;
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
        ordenP = new OrdenesPago();
        nombreOrd = "";
        listaBusqueda = new ArrayList<OrdenesPago>();
        listarOrdenesPago = new ArrayList<OrdenesPago>();
        listarOrdenesPago.add(new OrdenesPago(BigDecimal.valueOf(200), new Date(2017, 12, 1), "José Rodríguez"));
        listarOrdenesPago.add(new OrdenesPago(BigDecimal.valueOf(300), new Date(2019,6,10), "Luis Sánchez"));
        listarOrdenesPago.add(new OrdenesPago(BigDecimal.valueOf(400), new Date(2019,7,12), "Juan Pérez"));
    }

    public ArrayList<OrdenesPago> getListarOrdenesPago() {
        return listarOrdenesPago;
    }

    public void setListarOrdenesPago(ArrayList<OrdenesPago> listOP) {
        this.listarOrdenesPago = listOP;
    }

    public void addOrdenPago() {
        boolean ins = true;
        if (ordenP.getNumero() == null || ordenP.getNumero().intValue() < 0) ins = false;
        for (OrdenesPago ord : listarOrdenesPago) {
            if (ord.getNumero().equals(ordenP.getNumero())) {
                ins = false;
                break;
            }
        }
        if (ins) listarOrdenesPago.add(new OrdenesPago(ordenP.getNumero(), ordenP.getFecha(), ordenP.getEmisor()));
        else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de órden inválido", "Código de órden inválido"));
        }
        ordenP = new OrdenesPago();
    }
    
    public String deleteOrdenPago(OrdenesPago op) {
        listarOrdenesPago.remove(op);
        return "ordenes.xhtml";
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
    
}
