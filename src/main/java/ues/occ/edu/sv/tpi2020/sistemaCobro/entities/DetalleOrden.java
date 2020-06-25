/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.tpi2020.sistemaCobro.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalle_orden", catalog = "tpi_cobros", schema = "")
@NamedQueries({
    @NamedQuery(name = "DetalleOrden.findAll", query = "SELECT d FROM DetalleOrden d"),
    @NamedQuery(name = "DetalleOrden.findByIdOrden", query = "SELECT d FROM DetalleOrden d WHERE d.detalleOrdenPK.idOrden = :idOrden"),
    @NamedQuery(name = "DetalleOrden.findByCantidadProd", query = "SELECT d FROM DetalleOrden d WHERE d.cantidadProd = :cantidadProd"),
    @NamedQuery(name = "DetalleOrden.findByPrecioUnit", query = "SELECT d FROM DetalleOrden d WHERE d.precioUnit = :precioUnit"),
    @NamedQuery(name = "DetalleOrden.findByUpc", query = "SELECT d FROM DetalleOrden d WHERE d.detalleOrdenPK.upc = :upc"),
    @NamedQuery(name = "DetalleOrden.findByDescuento", query = "SELECT d FROM DetalleOrden d WHERE d.descuento = :descuento")})
public class DetalleOrden implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleOrdenPK detalleOrdenPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_prod", nullable = false)
    private int cantidadProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_unit", nullable = false)
    private float precioUnit;
    @Column(name = "descuento")
    private Integer descuento;
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orden orden;
    @JoinColumn(name = "upc", referencedColumnName = "upc", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public DetalleOrden() {
    }

    public DetalleOrden(DetalleOrdenPK detalleOrdenPK) {
        this.detalleOrdenPK = detalleOrdenPK;
    }

    public DetalleOrden(DetalleOrdenPK detalleOrdenPK, int cantidadProd, float precioUnit, int descuento) {
        this.detalleOrdenPK = detalleOrdenPK;
        this.cantidadProd = cantidadProd;
        this.precioUnit = precioUnit;
        this.descuento = descuento;
    }

    public DetalleOrden(String idOrden, String upc) {
        this.detalleOrdenPK = new DetalleOrdenPK(idOrden, upc);
    }

    public DetalleOrdenPK getDetalleOrdenPK() {
        return detalleOrdenPK;
    }

    public void setDetalleOrdenPK(DetalleOrdenPK detalleOrdenPK) {
        this.detalleOrdenPK = detalleOrdenPK;
    }

    public int getCantidadProd() {
        return cantidadProd;
    }

    public void setCantidadProd(int cantidadProd) {
        this.cantidadProd = cantidadProd;
    }

    public float getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(float precioUnit) {
        this.precioUnit = precioUnit;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleOrdenPK != null ? detalleOrdenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOrden)) {
            return false;
        }
        DetalleOrden other = (DetalleOrden) object;
        return !((this.detalleOrdenPK == null && other.detalleOrdenPK != null) || (this.detalleOrdenPK != null && !this.detalleOrdenPK.equals(other.detalleOrdenPK)));
    }

    @Override
    public String toString() {
        return "ues.occ.edu.sv.tpi2020.sistemaCobro.entities.DetalleOrden[ detalleOrdenPK=" + detalleOrdenPK + " ]";
    }

}
