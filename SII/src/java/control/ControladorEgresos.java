package control;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.sql.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import proyectosii.Egresos;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ManagedBean(name = "egresoBean")
@SessionScoped

public class ControladorEgresos {
    
    private Egresos egreso;
    private String nombreEg;
    private ArrayList<Egresos> listaBusqueda;
    private ArrayList<Egresos> listarEgresos;
    
    public void buscar() {
        listaBusqueda = new ArrayList<>();
        for (Egresos eg : listarEgresos) {
            if (eg.getConcepto().toUpperCase().startsWith(nombreEg.toUpperCase())) listaBusqueda.add(eg);
        }
    }
    
    public ControladorEgresos() {
        egreso = new Egresos();
        nombreEg = "";
        listaBusqueda = new ArrayList<Egresos>();
        listarEgresos = new ArrayList<Egresos>();
        listarEgresos.add(new Egresos(BigDecimal.valueOf(200), "Juán Perez", "Alquiler", BigInteger.valueOf(400), Date.valueOf("2018-5-12")));
        listarEgresos.add(new Egresos(BigDecimal.valueOf(320), "Luis Sánchez", "Pago de Combustible", BigInteger.valueOf(40), Date.valueOf("2019-3-18")));
        listarEgresos.add(new Egresos(BigDecimal.valueOf(200), "José Rodríguez", "Pago de Mantenimiento", BigInteger.valueOf(15), Date.valueOf("2019-5-7")));
    }

    public ArrayList<Egresos> getListarEgresos() {
        return listarEgresos;
    }

    public void setListarEgresos(ArrayList<Egresos> listarEgresos) {
        this.listarEgresos = listarEgresos;
    }

    public void addEgreso() {
        boolean ins = true;
        if (egreso.getCodigoEgreso() == null || egreso.getCodigoEgreso().intValue() < 0) ins = false;
        for (Egresos eg : listarEgresos) {
            if (eg.getCodigoEgreso().equals(egreso.getCodigoEgreso())) {
                ins = false;
                break;
            }
        }
        if (ins) listarEgresos.add(new Egresos(egreso.getCodigoEgreso(), egreso.getNombreBeneficiario(), egreso.getConcepto(), egreso.getImporte(), egreso.getFecha()));
        else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de egreso inválido", "Código de egreso inválido"));
        }
        egreso = new Egresos();
    }
    
    public String deleteEgreso(Egresos eg) {
        listarEgresos.remove(eg);
        return "egresos.xhtml";
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
    
}
