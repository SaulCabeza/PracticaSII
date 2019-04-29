package control;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import proyectosii.Proyectos;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ManagedBean(name = "proyecto", eager = true)
@SessionScoped

public class ControladorProyecto implements Serializable {

    private ArrayList<Proyectos> listProyectos;
    private BigDecimal código;
    private String nombreProyecto;
    private String descripción;
    private BigInteger repartoCombustible;
    private BigInteger repartoMantenimiento;
    private BigInteger repartoContenedor;
    private BigInteger costeTotal;


    public void setCosteTotal(BigInteger costeTotal) {
        this.costeTotal = costeTotal;
    }

    public BigInteger getCosteTotal() {
        return costeTotal;
    }

    public BigDecimal getCódigo() {
        return código;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public String getDescripción() {
        return descripción;
    }

    public BigInteger getRepartoCombustible() {
        return repartoCombustible;
    }

    public BigInteger getRepartoMantenimiento() {
        return repartoMantenimiento;
    }

    public BigInteger getRepartoContenedor() {
        return repartoContenedor;
    }

    public void setCódigo(BigDecimal código) {
        this.código = código;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public void setRepartoCombustible(BigInteger repartoCombustible) {
        this.repartoCombustible = repartoCombustible;
    }

    public void setRepartoMantenimiento(BigInteger repartoMantenimiento) {
        this.repartoMantenimiento = repartoMantenimiento;
    }

    public void setRepartoContenedor(BigInteger repartoContenedor) {
        this.repartoContenedor = repartoContenedor;
    }
    

    public ControladorProyecto() {
        listProyectos = new ArrayList<Proyectos>();
        listProyectos.add(new Proyectos(new BigDecimal("1"), "pr1", "prueba", new BigInteger("20"), new BigInteger("30"), new BigInteger("40"), new BigInteger("4000")));
        listProyectos.add(new Proyectos(new BigDecimal("2"), "pr1", "prueba", new BigInteger("20"), new BigInteger("30"), new BigInteger("40"), new BigInteger("4000")));
    }    
    
    public ArrayList<Proyectos> getListProyectos() {
        return listProyectos;
    }

    public ArrayList<Proyectos> listar() {
        return this.listProyectos;
    }

    public void setListProyectos(Proyectos pr) {
        this.listProyectos.add(pr);
    }

    public void add() {
        Proyectos pr = new Proyectos(this.código, this.nombreProyecto, this.descripción, this.repartoCombustible, this.repartoMantenimiento, this.repartoContenedor, this.costeTotal);
        if(listProyectos.contains(pr)){
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código de proyecto inválido", "Código de proyecto inválido"));
        }else{
            listProyectos.add(pr);
        }
    }

    public String eliminar(Proyectos pr) {
        listProyectos.remove(pr);
        return "proyectos.xhtml";
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.código);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ControladorProyecto other = (ControladorProyecto) obj;
        if (!Objects.equals(this.código, other.código)) {
            return false;
        }
        return true;
    }
    
}

