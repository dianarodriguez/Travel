/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author MiPC
 */
@Entity
@Table(name = "reservacion")
@NamedQueries({
    @NamedQuery(name = "Reservacion.findAll", query = "SELECT r FROM Reservacion r"),
    @NamedQuery(name = "Reservacion.findByNombre", query = "SELECT r FROM Reservacion r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Reservacion.findByApellido1", query = "SELECT r FROM Reservacion r WHERE r.apellido1 = :apellido1"),
    @NamedQuery(name = "Reservacion.findByApellido2", query = "SELECT r FROM Reservacion r WHERE r.apellido2 = :apellido2"),
    @NamedQuery(name = "Reservacion.findByCi", query = "SELECT r FROM Reservacion r WHERE r.ci = :ci"),
    @NamedQuery(name = "Reservacion.findByDireccion", query = "SELECT r FROM Reservacion r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "Reservacion.findByAsiento", query = "SELECT r FROM Reservacion r WHERE r.asiento = :asiento"),
    @NamedQuery(name = "Reservacion.findByDestino", query = "SELECT r FROM Reservacion r WHERE r.destino = :destino"),
    @NamedQuery(name = "Reservacion.findByDiasalida", query = "SELECT r FROM Reservacion r WHERE r.diasalida = :diasalida"),
    @NamedQuery(name = "Reservacion.findByMessalida", query = "SELECT r FROM Reservacion r WHERE r.messalida = :messalida"),
    @NamedQuery(name = "Reservacion.findByYearsalida", query = "SELECT r FROM Reservacion r WHERE r.yearsalida = :yearsalida"),
    @NamedQuery(name = "Reservacion.findByHorasalida", query = "SELECT r FROM Reservacion r WHERE r.horasalida = :horasalida"),
    @NamedQuery(name = "Reservacion.findByMinutosalida", query = "SELECT r FROM Reservacion r WHERE r.minutosalida = :minutosalida"),
    @NamedQuery(name = "Reservacion.findByPrecio", query = "SELECT r FROM Reservacion r WHERE r.precio = :precio"),
    @NamedQuery(name = "Reservacion.findByNroreserv", query = "SELECT r FROM Reservacion r WHERE r.nroreserv = :nroreserv"),
    @NamedQuery(name = "Reservacion.findByRectificada", query = "SELECT r FROM Reservacion r WHERE r.rectificada = :rectificada")})
public class Reservacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido1")
    private String apellido1;
    @Column(name = "apellido2")
    private String apellido2;
    @Column(name = "ci")
    private String ci;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "asiento")
    private Integer asiento;
    @Column(name = "destino")
    private String destino;
    @Column(name = "diasalida")
    private String diasalida;
    @Column(name = "messalida")
    private String messalida;
    @Column(name = "yearsalida")
    private String yearsalida;
    @Column(name = "horasalida")
    private String horasalida;
    @Column(name = "minutosalida")
    private String minutosalida;
    @Column(name = "precio")
    private Double precio;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "nroreserv")
    private Integer nroreserv;
    @Column(name = "rectificada")
    private String rectificada;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reservacion")
    private Pasajesrectificados pasajesrectificados;
    @JoinColumn(name = "nrov", referencedColumnName = "nroviaje")
    @ManyToOne
    private Viaje viaje;

    public Reservacion() {
    }

    public Reservacion(Integer nroreserv) {
        this.nroreserv = nroreserv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getAsiento() {
        return asiento;
    }

    public void setAsiento(Integer asiento) {
        this.asiento = asiento;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDiasalida() {
        return diasalida;
    }

    public void setDiasalida(String diasalida) {
        this.diasalida = diasalida;
    }

    public String getMessalida() {
        return messalida;
    }

    public void setMessalida(String messalida) {
        this.messalida = messalida;
    }

    public String getYearsalida() {
        return yearsalida;
    }

    public void setYearsalida(String yearsalida) {
        this.yearsalida = yearsalida;
    }

    public String getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }

    public String getMinutosalida() {
        return minutosalida;
    }

    public void setMinutosalida(String minutosalida) {
        this.minutosalida = minutosalida;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getNroreserv() {
        return nroreserv;
    }

    public void setNroreserv(Integer nroreserv) {
        this.nroreserv = nroreserv;
    }

    public String getRectificada() {
        return rectificada;
    }

    public void setRectificada(String rectificada) {
        this.rectificada = rectificada;
    }

    public Pasajesrectificados getPasajesrectificados() {
        return pasajesrectificados;
    }

    public void setPasajesrectificados(Pasajesrectificados pasajesrectificados) {
        this.pasajesrectificados = pasajesrectificados;
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
        hash += (nroreserv != null ? nroreserv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservacion)) {
            return false;
        }
        Reservacion other = (Reservacion) object;
        if ((this.nroreserv == null && other.nroreserv != null) || (this.nroreserv != null && !this.nroreserv.equals(other.nroreserv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Reservacion[nroreserv=" + nroreserv + "]";
    }

}
