/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.tpi2020.sistemaCobro.facades;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ues.occ.edu.sv.tpi2020.sistemaCobro.entities.MetodoPago;

@Stateless
@LocalBean
public class MetodoPagoFacade extends AbstractFacade<MetodoPago> implements GenericLocalInterface<MetodoPago> {

    @PersistenceContext(unitName = "ues.occ.edu.sv.tpi2020_sistema-cobro_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MetodoPagoFacade() {
        super(MetodoPago.class);
    }
    
}
