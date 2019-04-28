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
@Table(name = "EGRESOS")
@NamedQueries({
    @NamedQuery(name = "Egresos.findAll", query = "SELECT e FROM Egresos e")})
public class Egresos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_EGRESO")
    private BigDecimal codigoEgreso;
    @Basic(optional = false)
    @Column(name = "NOMBRE_BENEFICIARIO")
    private String nombreBeneficiario;
    @Basic(optional = false)
    @Column(name = "CONCEPTO")
    private String concepto;
    @Basic(optional = false)
    @Column(name = "IMPORTE")
    private BigInteger importe;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "ORDENES_PAGO_NUMERO", referencedColumnName = "NUMERO")
    @ManyToOne
    private OrdenesPago ordenesPagoNumero;
    @JoinColumn(name = "PROYECTOS_C\u00d3DIGO", referencedColumnName = "C\u00d3DIGO")
    @ManyToOne(optional = false)
    private Proyectos proyectosCódigo;

    public Egresos() {
    }

    public Egresos(BigDecimal codigoEgreso) {
        this.codigoEgreso = codigoEgreso;
    }

    public Egresos(BigDecimal codigoEgreso, String nombreBeneficiario, String concepto, BigInteger importe, Date fecha) {
        this.codigoEgreso = codigoEgreso;
        this.nombreBeneficiario = nombreBeneficiario;
        this.concepto = concepto;
        this.importe = importe;
        this.fecha = fecha;
    }

    public BigDecimal getCodigoEgreso() {
        return codigoEgreso;
    }

    public void setCodigoEgreso(BigDecimal codigoEgreso) {
        this.codigoEgreso = codigoEgreso;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigInteger getImporte() {
        return importe;
    }

    public void setImporte(BigInteger importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public OrdenesPago getOrdenesPagoNumero() {
        return ordenesPagoNumero;
    }

    public void setOrdenesPagoNumero(OrdenesPago ordenesPagoNumero) {
        this.ordenesPagoNumero = ordenesPagoNumero;
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
        hash += (codigoEgreso != null ? codigoEgreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Egresos)) {
            return false;
        }
        Egresos other = (Egresos) object;
        if ((this.codigoEgreso == null && other.codigoEgreso != null) || (this.codigoEgreso != null && !this.codigoEgreso.equals(other.codigoEgreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectosii.Egresos[ codigoEgreso=" + codigoEgreso + " ]";
    }
    
}
