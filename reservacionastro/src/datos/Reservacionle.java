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
@Table(name = "reservacionle")
@NamedQueries({
    @NamedQuery(name = "Reservacionle.findAll", query = "SELECT r FROM Reservacionle r"),
    @NamedQuery(name = "Reservacionle.findByNombre", query = "SELECT r FROM Reservacionle r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Reservacionle.findByCi", query = "SELECT r FROM Reservacionle r WHERE r.ci = :ci"),
    @NamedQuery(name = "Reservacionle.findByRuta", query = "SELECT r FROM Reservacionle r WHERE r.ruta = :ruta"),
    @NamedQuery(name = "Reservacionle.findByDia", query = "SELECT r FROM Reservacionle r WHERE r.dia = :dia"),
    @NamedQuery(name = "Reservacionle.findByMes", query = "SELECT r FROM Reservacionle r WHERE r.mes = :mes"),
    @NamedQuery(name = "Reservacionle.findByYear", query = "SELECT r FROM Reservacionle r WHERE r.year = :year"),
    @NamedQuery(name = "Reservacionle.findByAsiento", query = "SELECT r FROM Reservacionle r WHERE r.asiento = :asiento"),
    @NamedQuery(name = "Reservacionle.findByPrecio", query = "SELECT r FROM Reservacionle r WHERE r.precio = :precio"),
    @NamedQuery(name = "Reservacionle.findById", query = "SELECT r FROM Reservacionle r WHERE r.id = :id")})
public class Reservacionle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ci")
    private String ci;
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "dia")
    private String dia;
    @Column(name = "mes")
    private String mes;
    @Column(name = "year")
    private String year;
    @Column(name = "asiento")
    private Integer asiento;
    @Column(name = "precio")
    private Double precio;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Reservacionle() {
    }

    public Reservacionle(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getAsiento() {
        return asiento;
    }

    public void setAsiento(Integer asiento) {
        this.asiento = asiento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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
        if (!(object instanceof Reservacionle)) {
            return false;
        }
        Reservacionle other = (Reservacionle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Reservacionle[id=" + id + "]";
    }

}
