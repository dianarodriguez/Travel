/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MiPC
 */
@Entity
@Table(name = "viaje")
@NamedQueries({
    @NamedQuery(name = "Viaje.findAll", query = "SELECT v FROM Viaje v"),
    @NamedQuery(name = "Viaje.findByNroautobus", query = "SELECT v FROM Viaje v WHERE v.nroautobus = :nroautobus"),
    @NamedQuery(name = "Viaje.findByRuta", query = "SELECT v FROM Viaje v WHERE v.ruta = :ruta"),
    @NamedQuery(name = "Viaje.findByCichofer", query = "SELECT v FROM Viaje v WHERE v.cichofer = :cichofer"),
    @NamedQuery(name = "Viaje.findByCicopiloto", query = "SELECT v FROM Viaje v WHERE v.cicopiloto = :cicopiloto"),
    @NamedQuery(name = "Viaje.findByDiasalida", query = "SELECT v FROM Viaje v WHERE v.diasalida = :diasalida"),
    @NamedQuery(name = "Viaje.findByMessalida", query = "SELECT v FROM Viaje v WHERE v.messalida = :messalida"),
    @NamedQuery(name = "Viaje.findByYearsalida", query = "SELECT v FROM Viaje v WHERE v.yearsalida = :yearsalida"),
    @NamedQuery(name = "Viaje.findByDiallegada", query = "SELECT v FROM Viaje v WHERE v.diallegada = :diallegada"),
    @NamedQuery(name = "Viaje.findByMesllegada", query = "SELECT v FROM Viaje v WHERE v.mesllegada = :mesllegada"),
    @NamedQuery(name = "Viaje.findByYearllegada", query = "SELECT v FROM Viaje v WHERE v.yearllegada = :yearllegada"),
    @NamedQuery(name = "Viaje.findByHorasalida", query = "SELECT v FROM Viaje v WHERE v.horasalida = :horasalida"),
    @NamedQuery(name = "Viaje.findByMinutosalida", query = "SELECT v FROM Viaje v WHERE v.minutosalida = :minutosalida"),
    @NamedQuery(name = "Viaje.findByHorallegada", query = "SELECT v FROM Viaje v WHERE v.horallegada = :horallegada"),
    @NamedQuery(name = "Viaje.findByMinutollegada", query = "SELECT v FROM Viaje v WHERE v.minutollegada = :minutollegada"),
    @NamedQuery(name = "Viaje.findByNroviaje", query = "SELECT v FROM Viaje v WHERE v.nroviaje = :nroviaje"),
    @NamedQuery(name = "Viaje.findByPrecio", query = "SELECT v FROM Viaje v WHERE v.precio = :precio")})
public class Viaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nroautobus")
    private String nroautobus;
    @Basic(optional = false)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @Column(name = "cichofer")
    private String cichofer;
    @Basic(optional = false)
    @Column(name = "cicopiloto")
    private String cicopiloto;
    @Basic(optional = false)
    @Column(name = "diasalida")
    private String diasalida;
    @Basic(optional = false)
    @Column(name = "messalida")
    private String messalida;
    @Basic(optional = false)
    @Column(name = "yearsalida")
    private String yearsalida;
    @Basic(optional = false)
    @Column(name = "diallegada")
    private String diallegada;
    @Basic(optional = false)
    @Column(name = "mesllegada")
    private String mesllegada;
    @Basic(optional = false)
    @Column(name = "yearllegada")
    private String yearllegada;
    @Basic(optional = false)
    @Column(name = "horasalida")
    private String horasalida;
    @Basic(optional = false)
    @Column(name = "minutosalida")
    private String minutosalida;
    @Basic(optional = false)
    @Column(name = "horallegada")
    private String horallegada;
    @Basic(optional = false)
    @Column(name = "minutollegada")
    private String minutollegada;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "nroviaje")
    private Integer nroviaje;
    @Column(name = "precio")
    private Double precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viaje")
    private List<Pasaje> pasajeList;
    @OneToMany(mappedBy = "viaje")
    private List<Reservacion> reservacionList;

    public Viaje() {
    }

    public Viaje(Integer nroviaje) {
        this.nroviaje = nroviaje;
    }

    public Viaje(Integer nroviaje, String nroautobus, String ruta, String cichofer, String cicopiloto, String diasalida, String messalida, String yearsalida, String diallegada, String mesllegada, String yearllegada, String horasalida, String minutosalida, String horallegada, String minutollegada) {
        this.nroviaje = nroviaje;
        this.nroautobus = nroautobus;
        this.ruta = ruta;
        this.cichofer = cichofer;
        this.cicopiloto = cicopiloto;
        this.diasalida = diasalida;
        this.messalida = messalida;
        this.yearsalida = yearsalida;
        this.diallegada = diallegada;
        this.mesllegada = mesllegada;
        this.yearllegada = yearllegada;
        this.horasalida = horasalida;
        this.minutosalida = minutosalida;
        this.horallegada = horallegada;
        this.minutollegada = minutollegada;
    }

    public String getNroautobus() {
        return nroautobus;
    }

    public void setNroautobus(String nroautobus) {
        this.nroautobus = nroautobus;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getCichofer() {
        return cichofer;
    }

    public void setCichofer(String cichofer) {
        this.cichofer = cichofer;
    }

    public String getCicopiloto() {
        return cicopiloto;
    }

    public void setCicopiloto(String cicopiloto) {
        this.cicopiloto = cicopiloto;
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

    public String getDiallegada() {
        return diallegada;
    }

    public void setDiallegada(String diallegada) {
        this.diallegada = diallegada;
    }

    public String getMesllegada() {
        return mesllegada;
    }

    public void setMesllegada(String mesllegada) {
        this.mesllegada = mesllegada;
    }

    public String getYearllegada() {
        return yearllegada;
    }

    public void setYearllegada(String yearllegada) {
        this.yearllegada = yearllegada;
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

    public String getHorallegada() {
        return horallegada;
    }

    public void setHorallegada(String horallegada) {
        this.horallegada = horallegada;
    }

    public String getMinutollegada() {
        return minutollegada;
    }

    public void setMinutollegada(String minutollegada) {
        this.minutollegada = minutollegada;
    }

    public Integer getNroviaje() {
        return nroviaje;
    }

    public void setNroviaje(Integer nroviaje) {
        this.nroviaje = nroviaje;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<Pasaje> getPasajeList() {
        return pasajeList;
    }

    public void setPasajeList(List<Pasaje> pasajeList) {
        this.pasajeList = pasajeList;
    }

    public List<Reservacion> getReservacionList() {
        return reservacionList;
    }

    public void setReservacionList(List<Reservacion> reservacionList) {
        this.reservacionList = reservacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroviaje != null ? nroviaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viaje)) {
            return false;
        }
        Viaje other = (Viaje) object;
        if ((this.nroviaje == null && other.nroviaje != null) || (this.nroviaje != null && !this.nroviaje.equals(other.nroviaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Viaje[nroviaje=" + nroviaje + "]";
    }

}
