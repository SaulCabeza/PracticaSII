/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosii;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Saul
 */
@Entity
@Table(name = "ORDENES_PAGO")
@NamedQueries({
    @NamedQuery(name = "OrdenesPago.findAll", query = "SELECT o FROM OrdenesPago o")})
public class OrdenesPago implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUMERO")
    private BigDecimal numero;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "EMISOR")
    private String emisor;
    @OneToMany(mappedBy = "ordenesPagoNumero")
    private List<Egresos> egresosList;

    public OrdenesPago() {
    }

    public OrdenesPago(BigDecimal numero) {
        this.numero = numero;
    }

    public OrdenesPago(BigDecimal numero, Date fecha, String emisor) {
        this.numero = numero;
        this.fecha = fecha;
        this.emisor = emisor;
    }

    public BigDecimal getNumero() {
        return numero;
    }

    public void setNumero(BigDecimal numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public List<Egresos> getEgresosList() {
        return egresosList;
    }

    public void setEgresosList(List<Egresos> egresosList) {
        this.egresosList = egresosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenesPago)) {
            return false;
        }
        OrdenesPago other = (OrdenesPago) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectosii.OrdenesPago[ numero=" + numero + " ]";
    }
    
}
