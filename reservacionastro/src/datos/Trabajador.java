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
@Table(name = "trabajador")
@NamedQueries({
    @NamedQuery(name = "Trabajador.findAll", query = "SELECT t FROM Trabajador t"),
    @NamedQuery(name = "Trabajador.findByNombre", query = "SELECT t FROM Trabajador t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Trabajador.findByApellido", query = "SELECT t FROM Trabajador t WHERE t.apellido = :apellido"),
    @NamedQuery(name = "Trabajador.findByCi", query = "SELECT t FROM Trabajador t WHERE t.ci = :ci"),
    @NamedQuery(name = "Trabajador.findByOcupacion", query = "SELECT t FROM Trabajador t WHERE t.ocupacion = :ocupacion"),
    @NamedQuery(name = "Trabajador.findByDireccion", query = "SELECT t FROM Trabajador t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "Trabajador.findByCorreo", query = "SELECT t FROM Trabajador t WHERE t.correo = :correo"),
    @NamedQuery(name = "Trabajador.findBySalario", query = "SELECT t FROM Trabajador t WHERE t.salario = :salario")})
public class Trabajador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Id
    @Basic(optional = false)
    @Column(name = "ci")
    private String ci;
    @Column(name = "ocupacion")
    private String ocupacion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "correo")
    private String correo;
    @Column(name = "salario")
    private Double salario;

    public Trabajador() {
    }

    public Trabajador(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
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
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        if ((this.ci == null && other.ci != null) || (this.ci != null && !this.ci.equals(other.ci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Trabajador[ci=" + ci + "]";
    }

}
