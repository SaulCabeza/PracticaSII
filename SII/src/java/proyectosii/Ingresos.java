/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosii;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Saul
 */
@Entity
@Table(name = "INGRESOS")
@NamedQueries({
    @NamedQuery(name = "Ingresos.findAll", query = "SELECT i FROM Ingresos i")})
public class Ingresos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_INGRESO")
    private BigDecimal codigoIngreso;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @JoinColumn(name = "PROYECTOS_C\u00d3DIGO", referencedColumnName = "C\u00d3DIGO")
    @ManyToOne(optional = false)
    private Proyectos proyectosCódigo;

    public Ingresos() {
    }

    public Ingresos(BigDecimal codigoIngreso) {
        this.codigoIngreso = codigoIngreso;
    }

    public Ingresos(BigDecimal codigoIngreso, Date fecha, String descripcion, BigInteger cantidad) {
        this.codigoIngreso = codigoIngreso;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public BigDecimal getCodigoIngreso() {
        return codigoIngreso;
    }

    public void setCodigoIngreso(BigDecimal codigoIngreso) {
        this.codigoIngreso = codigoIngreso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Proyectos getProyectosCódigo() {
        return proyectosCódigo;
    }

    public void setProyectosCódigo(Proyectos proyectosCódigo) {
        this.proyectosCódigo = proyectosCódigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoIngreso != null ? codigoIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingresos)) {
            return false;
        }
        Ingresos other = (Ingresos) object;
        if ((this.codigoIngreso == null && other.codigoIngreso != null) || (this.codigoIngreso != null && !this.codigoIngreso.equals(other.codigoIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectosii.Ingresos[ codigoIngreso=" + codigoIngreso + " ]";
    }
    
}
