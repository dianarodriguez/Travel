/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author MiPC
 */
@Entity
@Table(name = "pasajesrectificados")
@NamedQueries({
    @NamedQuery(name = "Pasajesrectificados.findAll", query = "SELECT p FROM Pasajesrectificados p"),
    @NamedQuery(name = "Pasajesrectificados.findByNroreserv", query = "SELECT p FROM Pasajesrectificados p WHERE p.nroreserv = :nroreserv")})
public class Pasajesrectificados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nroreserv")
    private Integer nroreserv;
    @JoinColumn(name = "nroreserv", referencedColumnName = "nroreserv", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Reservacion reservacion;

    public Pasajesrectificados() {
    }

    public Pasajesrectificados(Integer nroreserv) {
        this.nroreserv = nroreserv;
    }

    public Integer getNroreserv() {
        return nroreserv;
    }

    public void setNroreserv(Integer nroreserv) {
        this.nroreserv = nroreserv;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroreserv != null ? nroreserv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasajesrectificados)) {
            return false;
        }
        Pasajesrectificados other = (Pasajesrectificados) object;
        if ((this.nroreserv == null && other.nroreserv != null) || (this.nroreserv != null && !this.nroreserv.equals(other.nroreserv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Pasajesrectificados[nroreserv=" + nroreserv + "]";
    }

}
