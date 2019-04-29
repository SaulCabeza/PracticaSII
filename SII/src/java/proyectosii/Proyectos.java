/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosii;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Saul
 */
@Entity
@Table(name = "PROYECTOS")
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p")})
public class Proyectos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "C\u00d3DIGO")
    private BigDecimal código;
    @Basic(optional = false)
    @Column(name = "NOMBRE_PROYECTO")
    private String nombreProyecto;
    @Basic(optional = false)
    @Column(name = "DESCRIPCI\u00d3N")
    private String descripción;
    @Basic(optional = false)
    @Column(name = "REPARTO_COMBUSTIBLE")
    private BigInteger repartoCombustible;
    @Basic(optional = false)
    @Column(name = "REPARTO_MANTENIMIENTO")
    private BigInteger repartoMantenimiento;
    @Basic(optional = false)
    @Column(name = "REPARTO_CONTENEDOR")
    private BigInteger repartoContenedor;
    @Basic(optional = false)
    @Column(name = "COSTE_TOTAL")
    private BigInteger costeTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyectosC\u00f3digo")
    private List<Egresos> egresosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyectosC\u00f3digo")
    private List<Ingresos> ingresosList;

    public Proyectos() {
    }

    public Proyectos(BigDecimal código) {
        this.código = código;
    }

    public Proyectos(BigDecimal código, String nombreProyecto, String descripción, BigInteger repartoCombustible, BigInteger repartoMantenimiento, BigInteger repartoContenedor, BigInteger costeTotal) {
        this.código = código;
        this.nombreProyecto = nombreProyecto;
        this.descripción = descripción;
        this.repartoCombustible = repartoCombustible;
        this.repartoMantenimiento = repartoMantenimiento;
        this.repartoContenedor = repartoContenedor;
        this.costeTotal = costeTotal;
    }

    public BigDecimal getCódigo() {
        return código;
    }

    public void setCódigo(BigDecimal código) {
        this.código = código;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public BigInteger getRepartoCombustible() {
        return repartoCombustible;
    }

    public void setRepartoCombustible(BigInteger repartoCombustible) {
        this.repartoCombustible = repartoCombustible;
    }

    public BigInteger getRepartoMantenimiento() {
        return repartoMantenimiento;
    }

    public void setRepartoMantenimiento(BigInteger repartoMantenimiento) {
        this.repartoMantenimiento = repartoMantenimiento;
    }

    public BigInteger getRepartoContenedor() {
        return repartoContenedor;
    }

    public void setRepartoContenedor(BigInteger repartoContenedor) {
        this.repartoContenedor = repartoContenedor;
    }

    public BigInteger getCosteTotal() {
        return costeTotal;
    }

    public void setCosteTotal(BigInteger costeTotal) {
        this.costeTotal = costeTotal;
    }

    public List<Egresos> getEgresosList() {
        return egresosList;
    }

    public void setEgresosList(List<Egresos> egresosList) {
        this.egresosList = egresosList;
    }

    public List<Ingresos> getIngresosList() {
        return ingresosList;
    }

    public void setIngresosList(List<Ingresos> ingresosList) {
        this.ingresosList = ingresosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (código != null ? código.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.código == null && other.código != null) || (this.código != null && !this.código.equals(other.código))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectosii.Proyectos[ c\u00f3digo=" + código + " ]";
    }
    
}
