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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "marca", catalog = "tpi_cobros", schema = "")
@NamedQueries({
    @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m"),
    @NamedQuery(name = "Marca.findByIdMarca", query = "SELECT m FROM Marca m WHERE m.idMarca = :idMarca"),
    @NamedQuery(name = "Marca.findByNombreMarca", query = "SELECT m FROM Marca m WHERE m.nombreMarca = :nombreMarca"),
    @NamedQuery(name = "Marca.findByActivoMarca", query = "SELECT m FROM Marca m WHERE m.activoMarca = :activoMarca"),
    @NamedQuery(name = "Marca.findByDescripMarca", query = "SELECT m FROM Marca m WHERE m.descripMarca = :descripMarca")})
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_marca", nullable = false)
    private Integer idMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_marca", nullable = false, length = 255)
    private String nombreMarca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activoMarca", nullable = false)
    private boolean activoMarca;
    @Size(max = 1000)
    @Column(name = "descrip_marca", length = 1000)
    private String descripMarca;

    public Marca() {
    }

    public Marca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Marca(Integer idMarca, String nombreMarca, boolean activoMarca, String descripMarca) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
        this.activoMarca = activoMarca;
        this.descripMarca = descripMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public boolean getActivoMarca() {
        return activoMarca;
    }

    public void setActivoMarca(boolean activoMarca) {
        this.activoMarca = activoMarca;
    }

    public String getDescripMarca() {
        return descripMarca;
    }

    public void setDescripMarca(String descripMarca) {
        this.descripMarca = descripMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarca != null ? idMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) object;
        return !((this.idMarca == null && other.idMarca != null) || (this.idMarca != null && !this.idMarca.equals(other.idMarca)));
    }

    @Override
    public String toString() {
        return "ues.occ.edu.sv.tpi2020.sistemaCobro.entities.Marca[ idMarca=" + idMarca + " ]";
    }
    
}
