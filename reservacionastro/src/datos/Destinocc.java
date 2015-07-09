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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MiPC
 */
@Entity
@Table(name = "destinocc")
@NamedQueries({
    @NamedQuery(name = "Destinocc.findAll", query = "SELECT d FROM Destinocc d"),
    @NamedQuery(name = "Destinocc.findByNombre", query = "SELECT d FROM Destinocc d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Destinocc.findByPrecio", query = "SELECT d FROM Destinocc d WHERE d.precio = :precio")})
public class Destinocc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio")
    private Double precio;

    public Destinocc() {
    }

    public Destinocc(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destinocc)) {
            return false;
        }
        Destinocc other = (Destinocc) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Destinocc[nombre=" + nombre + "]";
    }

}
