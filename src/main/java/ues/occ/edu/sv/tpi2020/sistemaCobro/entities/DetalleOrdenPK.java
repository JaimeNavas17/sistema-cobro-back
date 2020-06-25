/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.tpi2020.sistemaCobro.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Embeddable
public class DetalleOrdenPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_orden", nullable = false, length = 20)
    private String idOrden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "upc", nullable = false, length = 20)
    private String upc;

    public DetalleOrdenPK() {
    }

    public DetalleOrdenPK(String idOrden, String upc) {
        this.idOrden = idOrden;
        this.upc = upc;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        hash += (upc != null ? upc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOrdenPK)) {
            return false;
        }
        DetalleOrdenPK other = (DetalleOrdenPK) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return !((this.upc == null && other.upc != null) || (this.upc != null && !this.upc.equals(other.upc)));
    }

    @Override
    public String toString() {
        return "ues.occ.edu.sv.tpi2020.sistemaCobro.entities.DetalleOrdenPK[ idOrden=" + idOrden + ", upc=" + upc + " ]";
    }
    
}
