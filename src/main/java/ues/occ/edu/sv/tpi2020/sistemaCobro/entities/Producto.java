/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.tpi2020.sistemaCobro.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "producto", catalog = "tpi_cobros", schema = "")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByUpc", query = "SELECT p FROM Producto p WHERE p.upc = :upc"),
    @NamedQuery(name = "Producto.findByNombreProd", query = "SELECT p FROM Producto p WHERE p.nombreProd = :nombreProd"),
    @NamedQuery(name = "Producto.findByPrecioUnit", query = "SELECT p FROM Producto p WHERE p.precioUnit = :precioUnit"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByStockProd", query = "SELECT p FROM Producto p WHERE p.stockProd = :stockProd"),
    @NamedQuery(name = "Producto.findByActivoProd", query = "SELECT p FROM Producto p WHERE p.activoProd = :activoProd"),
    @NamedQuery(name = "Producto.findByIdCategoria", query = "SELECT p FROM Producto p WHERE p.idCategoria = :idCategoria"),
    @NamedQuery(name = "Producto.findByIdMarca", query = "SELECT p FROM Producto p WHERE p.idMarca = :idMarca")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "upc", nullable = false, length = 20)
    private String upc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre_prod", nullable = false, length = 500)
    private String nombreProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_unit", nullable = false)
    private float precioUnit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "descripcion", nullable = true, length = 1000)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_prod", nullable = false)
    private int stockProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activoProd", nullable = false)
    private boolean activoProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_categoria", nullable = false)
    private int idCategoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_marca", nullable = false)
    private int idMarca;

    public Producto() {
    }

    public Producto(String upc) {
        this.upc = upc;
    }

    public Producto(String upc, String nombreProd, float precioUnit, String descripcion, int stockProd, boolean activoProd, int idCategoria, int idMarca) {
        this.upc = upc;
        this.nombreProd = nombreProd;
        this.precioUnit = precioUnit;
        this.descripcion = descripcion;
        this.stockProd = stockProd;
        this.activoProd = activoProd;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public float getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(float precioUnit) {
        this.precioUnit = precioUnit;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStockProd() {
        return stockProd;
    }

    public void setStockProd(int stockProd) {
        this.stockProd = stockProd;
    }

    public boolean getActivoProd() {
        return activoProd;
    }

    public void setActivoProd(boolean activoProd) {
        this.activoProd = activoProd;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (upc != null ? upc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        return !((this.upc == null && other.upc != null) || (this.upc != null && !this.upc.equals(other.upc)));
    }

    @Override
    public String toString() {
        return "ues.occ.edu.sv.tpi2020.sistemaCobro.entities.Producto[ upc=" + upc + " ]";
    }
    
}
