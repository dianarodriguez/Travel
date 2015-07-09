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
@Table(name = "passwords")
@NamedQueries({
    @NamedQuery(name = "Passwords.findAll", query = "SELECT p FROM Passwords p"),
    @NamedQuery(name = "Passwords.findByNombreusuario", query = "SELECT p FROM Passwords p WHERE p.nombreusuario = :nombreusuario"),
    @NamedQuery(name = "Passwords.findByPassword", query = "SELECT p FROM Passwords p WHERE p.password = :password"),
    @NamedQuery(name = "Passwords.findById", query = "SELECT p FROM Passwords p WHERE p.id = :id")})
public class Passwords implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nombreusuario")
    private String nombreusuario;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Passwords() {
    }

    public Passwords(Integer id) {
        this.id = id;
    }

    public Passwords(Integer id, String nombreusuario, String password) {
        this.id = id;
        this.nombreusuario = nombreusuario;
        this.password = password;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof Passwords)) {
            return false;
        }
        Passwords other = (Passwords) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Passwords[id=" + id + "]";
    }

}
