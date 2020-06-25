/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.tpi2020.sistemaCobro.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "orden", catalog = "tpi_cobros", schema = "")
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o"),
    @NamedQuery(name = "Orden.findByIdOrden", query = "SELECT o FROM Orden o WHERE o.idOrden = :idOrden"),
    @NamedQuery(name = "Orden.findByFechaOrd", query = "SELECT o FROM Orden o WHERE o.fechaOrd = :fechaOrd"),
    @NamedQuery(name = "Orden.findByTotalOrden", query = "SELECT o FROM Orden o WHERE o.totalOrden = :totalOrden"),
    @NamedQuery(name = "Orden.findByNombreCliente", query = "SELECT o FROM Orden o WHERE o.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "Orden.findByActivoOrden", query = "SELECT o FROM Orden o WHERE o.activoOrden = :activoOrden"),
    @NamedQuery(name = "Orden.findByObservacionesOrden", query = "SELECT o FROM Orden o WHERE o.observacionesOrden = :observacionesOrden")})
@XmlRootElement
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_orden", nullable = false, length = 20)
    private String idOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ord", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_orden", nullable = false)
    private double totalOrden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_cliente", nullable = false, length = 255)
    private String nombreCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activoOrden", nullable = false)
    private boolean activoOrden;
    @Size(max = 1000)
    @Column(name = "observaciones_orden", length = 1000)
    private String observacionesOrden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden")
    private List<DetalleOrden> detalleOrdenList;
    @JoinColumn(name = "id_metodo_pago", referencedColumnName = "id_metodo_pago", nullable = false)
    @ManyToOne(optional = false)
    private MetodoPago idMetodoPago;

    public Orden() {
    }

    public Orden(String idOrden) {
        this.idOrden = idOrden;
    }
                    //`id_orden`, `fecha_ord`, `total_orden`, `nombre_cliente`, `id_metodo_pago`, `activoOrden`, `observaciones_orden
    public Orden(String idOrden, Date fechaOrd, double totalOrden, String nombreCliente, MetodoPago idMetodoPago, boolean activoOrden, String observaciones) {
        this.idOrden = idOrden;
        this.fechaOrd = fechaOrd;
        this.totalOrden = totalOrden;
        this.nombreCliente = nombreCliente;
        this.idMetodoPago = idMetodoPago;
        this.activoOrden = activoOrden;
        this.observacionesOrden = observaciones;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFechaOrd() {
        return fechaOrd;
    }

    public void setFechaOrd(Date fechaOrd) {
        this.fechaOrd = fechaOrd;
    }

    public double getTotalOrden() {
        return totalOrden;
    }

    public void setTotalOrden(double totalOrden) {
        this.totalOrden = totalOrden;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public boolean getActivoOrden() {
        return activoOrden;
    }

    public void setActivoOrden(boolean activoOrden) {
        this.activoOrden = activoOrden;
    }

    public String getObservacionesOrden() {
        return observacionesOrden;
    }

    public void setObservacionesOrden(String observacionesOrden) {
        this.observacionesOrden = observacionesOrden;
    }

    @JsonbTransient
    @XmlTransient
    public List<DetalleOrden> getDetalleOrdenList() {
        return detalleOrdenList;
    }

    public void setDetalleOrdenList(List<DetalleOrden> detalleOrdenList) {
        this.detalleOrdenList = detalleOrdenList;
    }

    public MetodoPago getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(MetodoPago idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        return !((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden)));
    }

    @Override
    public String toString() {
        return "ues.occ.edu.sv.tpi2020.sistemaCobro.entities.Orden[ idOrden=" + idOrden + " ]";
    }
    
}
