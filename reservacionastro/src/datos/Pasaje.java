/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MiPC
 */
@Entity
@Table(name = "pasaje")
@NamedQueries({
    @NamedQuery(name = "Pasaje.findAll", query = "SELECT p FROM Pasaje p"),
    @NamedQuery(name = "Pasaje.findByNroasiento", query = "SELECT p FROM Pasaje p WHERE p.nroasiento = :nroasiento"),
    @NamedQuery(name = "Pasaje.findByDisponibilidad", query = "SELECT p FROM Pasaje p WHERE p.disponibilidad = :disponibilidad"),
    @NamedQuery(name = "Pasaje.findByNropasaje", query = "SELECT p FROM Pasaje p WHERE p.nropasaje = :nropasaje")})
public class Pasaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nroasiento")
    private Integer nroasiento;
    @Column(name = "disponibilidad")
    private String disponibilidad;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "nropasaje")
    private Integer nropasaje;
    @JoinColumn(name = "nrovia", referencedColumnName = "nroviaje")
    @ManyToOne(optional = false)
    private Viaje viaje;

    public Pasaje() {
    }

    public Pasaje(Integer nropasaje) {
        this.nropasaje = nropasaje;
    }

    public Integer getNroasiento() {
        return nroasiento;
    }

    public void setNroasiento(Integer nroasiento) {
        this.nroasiento = nroasiento;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Integer getNropasaje() {
        return nropasaje;
    }

    public void setNropasaje(Integer nropasaje) {
        this.nropasaje = nropasaje;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nropasaje != null ? nropasaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasaje)) {
            return false;
        }
        Pasaje other = (Pasaje) object;
        if ((this.nropasaje == null && other.nropasaje != null) || (this.nropasaje != null && !this.nropasaje.equals(other.nropasaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Pasaje[nropasaje=" + nropasaje + "]";
    }

}
