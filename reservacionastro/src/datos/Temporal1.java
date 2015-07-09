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
@Table(name = "temporal1")
@NamedQueries({
    @NamedQuery(name = "Temporal1.findAll", query = "SELECT t FROM Temporal1 t"),
    @NamedQuery(name = "Temporal1.findByDestino", query = "SELECT t FROM Temporal1 t WHERE t.destino = :destino"),
    @NamedQuery(name = "Temporal1.findByDia", query = "SELECT t FROM Temporal1 t WHERE t.dia = :dia"),
    @NamedQuery(name = "Temporal1.findByMes", query = "SELECT t FROM Temporal1 t WHERE t.mes = :mes"),
    @NamedQuery(name = "Temporal1.findByYear", query = "SELECT t FROM Temporal1 t WHERE t.year = :year"),
    @NamedQuery(name = "Temporal1.findByHora", query = "SELECT t FROM Temporal1 t WHERE t.hora = :hora"),
    @NamedQuery(name = "Temporal1.findByMin", query = "SELECT t FROM Temporal1 t WHERE t.min = :min"),
    @NamedQuery(name = "Temporal1.findById", query = "SELECT t FROM Temporal1 t WHERE t.id = :id")})
public class Temporal1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "destino")
    private String destino;
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
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Temporal1() {
    }

    public Temporal1(Integer id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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
        if (!(object instanceof Temporal1)) {
            return false;
        }
        Temporal1 other = (Temporal1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Temporal1[id=" + id + "]";
    }

}
