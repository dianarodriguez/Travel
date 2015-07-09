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
@Table(name = "listaespera")
@NamedQueries({
    @NamedQuery(name = "Listaespera.findAll", query = "SELECT l FROM Listaespera l"),
    @NamedQuery(name = "Listaespera.findByNombre", query = "SELECT l FROM Listaespera l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Listaespera.findByCi", query = "SELECT l FROM Listaespera l WHERE l.ci = :ci"),
    @NamedQuery(name = "Listaespera.findByRuta", query = "SELECT l FROM Listaespera l WHERE l.ruta = :ruta"),
    @NamedQuery(name = "Listaespera.findByNroviaje", query = "SELECT l FROM Listaespera l WHERE l.nroviaje = :nroviaje"),
    @NamedQuery(name = "Listaespera.findByDia", query = "SELECT l FROM Listaespera l WHERE l.dia = :dia"),
    @NamedQuery(name = "Listaespera.findByMes", query = "SELECT l FROM Listaespera l WHERE l.mes = :mes"),
    @NamedQuery(name = "Listaespera.findByYear", query = "SELECT l FROM Listaespera l WHERE l.year = :year"),
    @NamedQuery(name = "Listaespera.findByHora", query = "SELECT l FROM Listaespera l WHERE l.hora = :hora"),
    @NamedQuery(name = "Listaespera.findByMin", query = "SELECT l FROM Listaespera l WHERE l.min = :min"),
    @NamedQuery(name = "Listaespera.findByNro", query = "SELECT l FROM Listaespera l WHERE l.nro = :nro")})
public class Listaespera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    @Id
    @Basic(optional = false)
    @Column(name = "ci")
    private String ci;
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "nroviaje")
    private Integer nroviaje;
    @Column(name = "dia")
    private String dia;
    @Column(name = "mes")
    private String mes;
    @Column(name = "year")
    private String year;
    @Column(name = "hora")
    private String hora;
    @Column(name = "min")
    private String min;
    @Column(name = "nro")
    private Integer nro;

    public Listaespera() {
    }

    public Listaespera(String ci) {
        this.ci = ci;
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

    public Integer getNroviaje() {
        return nroviaje;
    }

    public void setNroviaje(Integer nroviaje) {
        this.nroviaje = nroviaje;
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

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ci != null ? ci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listaespera)) {
            return false;
        }
        Listaespera other = (Listaespera) object;
        if ((this.ci == null && other.ci != null) || (this.ci != null && !this.ci.equals(other.ci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Listaespera[ci=" + ci + "]";
    }

}
