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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MiPC
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByCi", query = "SELECT u FROM Usuario u WHERE u.ci = :ci"),
    @NamedQuery(name = "Usuario.findByOcupacion", query = "SELECT u FROM Usuario u WHERE u.ocupacion = :ocupacion"),
    @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByLogueado", query = "SELECT u FROM Usuario u WHERE u.logueado = :logueado"),
    @NamedQuery(name = "Usuario.findByNombreusuario", query = "SELECT u FROM Usuario u WHERE u.nombreusuario = :nombreusuario")})
public class Usuario implements Serializable {
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
    @Basic(optional = false)
    @Column(name = "logueado")
    private String logueado;
    @Basic(optional = false)
    @Column(name = "nombreusuario")
    private String nombreusuario;
    @JoinColumn(name = "idrol", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String ci) {
        this.ci = ci;
    }

    public Usuario(String ci, String logueado, String nombreusuario) {
        this.ci = ci;
        this.logueado = logueado;
        this.nombreusuario = nombreusuario;
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

    public String getLogueado() {
        return logueado;
    }

    public void setLogueado(String logueado) {
        this.logueado = logueado;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.ci == null && other.ci != null) || (this.ci != null && !this.ci.equals(other.ci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Usuario[ci=" + ci + "]";
    }

}
