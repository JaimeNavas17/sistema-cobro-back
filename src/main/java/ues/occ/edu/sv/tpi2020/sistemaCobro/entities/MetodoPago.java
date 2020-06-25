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
@Table(name = "metodo_pago", catalog = "tpi_cobros", schema = "")
@NamedQueries({
    @NamedQuery(name = "MetodoPago.findAll", query = "SELECT m FROM MetodoPago m"),
    @NamedQuery(name = "MetodoPago.findByIdMetodoPago", query = "SELECT m FROM MetodoPago m WHERE m.idMetodoPago = :idMetodoPago"),
    @NamedQuery(name = "MetodoPago.findByNombrePago", query = "SELECT m FROM MetodoPago m WHERE m.nombrePago = :nombrePago"),
    @NamedQuery(name = "MetodoPago.findByActivoMet", query = "SELECT m FROM MetodoPago m WHERE m.activoMet = :activoMet"),
    @NamedQuery(name = "MetodoPago.findByDescripcionMetodo", query = "SELECT m FROM MetodoPago m WHERE m.descripcionMetodo = :descripcionMetodo")})
public class MetodoPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_metodo_pago", nullable = false)
    private Integer idMetodoPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_pago", nullable = false, length = 255)
    private String nombrePago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activoMet", nullable = false)
    private boolean activoMet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "descripcion_metodo", nullable = false, length = 1000)
    private String descripcionMetodo;

    public MetodoPago() {
    }

    public MetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public MetodoPago(Integer idMetodoPago, String nombrePago, boolean activoMet, String descripcionMetodo) {
        this.idMetodoPago = idMetodoPago;
        this.nombrePago = nombrePago;
        this.activoMet = activoMet;
        this.descripcionMetodo = descripcionMetodo;
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getNombrePago() {
        return nombrePago;
    }

    public void setNombrePago(String nombrePago) {
        this.nombrePago = nombrePago;
    }

    public boolean getActivoMet() {
        return activoMet;
    }

    public void setActivoMet(boolean activoMet) {
        this.activoMet = activoMet;
    }

    public String getDescripcionMetodo() {
        return descripcionMetodo;
    }

    public void setDescripcionMetodo(String descripcionMetodo) {
        this.descripcionMetodo = descripcionMetodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetodoPago != null ? idMetodoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodoPago)) {
            return false;
        }
        MetodoPago other = (MetodoPago) object;
        if ((this.idMetodoPago == null && other.idMetodoPago != null) || (this.idMetodoPago != null && !this.idMetodoPago.equals(other.idMetodoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.occ.edu.sv.tpi2020.sistemaCobro.entities.MetodoPago[ idMetodoPago=" + idMetodoPago + " ]";
    }
    
}
