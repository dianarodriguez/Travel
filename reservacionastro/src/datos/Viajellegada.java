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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MiPC
 */
@Entity
@Table(name = "viajellegada")
@NamedQueries({
    @NamedQuery(name = "Viajellegada.findAll", query = "SELECT v FROM Viajellegada v"),
    @NamedQuery(name = "Viajellegada.findByDestino", query = "SELECT v FROM Viajellegada v WHERE v.destino = :destino"),
    @NamedQuery(name = "Viajellegada.findByCapacidad", query = "SELECT v FROM Viajellegada v WHERE v.capacidad = :capacidad"),
    @NamedQuery(name = "Viajellegada.findByNroautobus", query = "SELECT v FROM Viajellegada v WHERE v.nroautobus = :nroautobus"),
    @NamedQuery(name = "Viajellegada.findById", query = "SELECT v FROM Viajellegada v WHERE v.id = :id")})
public class Viajellegada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "destino")
    private String destino;
    @Column(name = "capacidad")
    private Integer capacidad;
    @Column(name = "nroautobus")
    private String nroautobus;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Viajellegada() {
    }

    public Viajellegada(Integer id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getNroautobus() {
        return nroautobus;
    }

    public void setNroautobus(String nroautobus) {
        this.nroautobus = nroautobus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viajellegada)) {
            return false;
        }
        Viajellegada other = (Viajellegada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Viajellegada[id=" + id + "]";
    }

}
