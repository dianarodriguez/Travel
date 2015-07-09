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
@Table(name = "pasajesle")
@NamedQueries({
    @NamedQuery(name = "Pasajesle.findAll", query = "SELECT p FROM Pasajesle p"),
    @NamedQuery(name = "Pasajesle.findByRuta", query = "SELECT p FROM Pasajesle p WHERE p.ruta = :ruta"),
    @NamedQuery(name = "Pasajesle.findByPrecio", query = "SELECT p FROM Pasajesle p WHERE p.precio = :precio"),
    @NamedQuery(name = "Pasajesle.findByAsiento", query = "SELECT p FROM Pasajesle p WHERE p.asiento = :asiento"),
    @NamedQuery(name = "Pasajesle.findByDiasalida", query = "SELECT p FROM Pasajesle p WHERE p.diasalida = :diasalida"),
    @NamedQuery(name = "Pasajesle.findByMes", query = "SELECT p FROM Pasajesle p WHERE p.mes = :mes"),
    @NamedQuery(name = "Pasajesle.findByYear", query = "SELECT p FROM Pasajesle p WHERE p.year = :year"),
    @NamedQuery(name = "Pasajesle.findByHora", query = "SELECT p FROM Pasajesle p WHERE p.hora = :hora"),
    @NamedQuery(name = "Pasajesle.findByMin", query = "SELECT p FROM Pasajesle p WHERE p.min = :min"),
    @NamedQuery(name = "Pasajesle.findByIdpasajesle", query = "SELECT p FROM Pasajesle p WHERE p.idpasajesle = :idpasajesle"),
    @NamedQuery(name = "Pasajesle.findByDisponible", query = "SELECT p FROM Pasajesle p WHERE p.disponible = :disponible")})
public class Pasajesle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "asiento")
    private Integer asiento;
    @Column(name = "diasalida")
    private String diasalida;
    @Column(name = "mes")
    private String mes;
    @Column(name = "year")
    private String year;
    @Column(name = "hora")
    private String hora;
    @Column(name = "min")
    private String min;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idpasajesle")
    private Integer idpasajesle;
    @Column(name = "disponible")
    private String disponible;

    public Pasajesle() {
    }

    public Pasajesle(Integer idpasajesle) {
        this.idpasajesle = idpasajesle;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getAsiento() {
        return asiento;
    }

    public void setAsiento(Integer asiento) {
        this.asiento = asiento;
    }

    public String getDiasalida() {
        return diasalida;
    }

    public void setDiasalida(String diasalida) {
        this.diasalida = diasalida;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public Integer getIdpasajesle() {
        return idpasajesle;
    }

    public void setIdpasajesle(Integer idpasajesle) {
        this.idpasajesle = idpasajesle;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpasajesle != null ? idpasajesle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasajesle)) {
            return false;
        }
        Pasajesle other = (Pasajesle) object;
        if ((this.idpasajesle == null && other.idpasajesle != null) || (this.idpasajesle != null && !this.idpasajesle.equals(other.idpasajesle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Pasajesle[idpasajesle=" + idpasajesle + "]";
    }

}
