package control;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import proyectosii.Ingresos;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "ingresoBean")
@SessionScoped

public class ControladorIngresos {
    
    private Ingresos ing;
    private String nombreIg;
    private ArrayList<Ingresos> listarIngresos;
    private ArrayList<Ingresos> listaBusqueda;
    
    public void buscar() {
        listaBusqueda = new ArrayList<>();
        for(Ingresos in : listarIngresos){
            if(in.getDescripcion().toUpperCase().startsWith(nombreIg.toUpperCase())) listaBusqueda.add(in);
        }
    }
    
    public ControladorIngresos(){
        ing = new Ingresos();
        nombreIg = "";
        listaBusqueda = new ArrayList<>();
        listarIngresos = new ArrayList<>();
        listarIngresos.add(new Ingresos(BigDecimal.valueOf(200), Date.valueOf("2019-5-12"), "Construcción", BigInteger.valueOf(81238)));
        listarIngresos.add(new Ingresos(BigDecimal.valueOf(201), Date.valueOf("2019-10-12"), "Construcción", BigInteger.valueOf(1223134)));
        listarIngresos.add(new Ingresos(BigDecimal.valueOf(358), Date.valueOf("2019-9-9"), "Escuela", BigInteger.valueOf(223315)));
    }

    public ArrayList<Ingresos> getListarIngresos() {
        return listarIngresos;
    }

    public void setListaIngresos(ArrayList<Ingresos> listaIngresos) {
        this.listarIngresos = listaIngresos;
    }
    
    public void addIngreso() {
        boolean ins = true;
        if(ing.getCodigoIngreso() == null || ing.getCodigoIngreso().intValue() < 0) ins = false;
        for(Ingresos in : listarIngresos){
            if (in.getCodigoIngreso().equals(ing.getCodigoIngreso())) {
                ins = false;
                break;
            }
        }
        if(ins) listarIngresos.add(new Ingresos(ing.getCodigoIngreso(), ing.getFecha(), ing.getDescripcion(), ing.getCantidad()));
        else{
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de ingreso inválido", "Código de ingreso inválido"));
        }
        ing = new Ingresos();
    }

    public void eliminarIngreso(Ingresos in) {
        listarIngresos.remove(in);
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
    
}
