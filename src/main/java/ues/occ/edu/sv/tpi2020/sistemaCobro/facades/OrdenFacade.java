/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.tpi2020.sistemaCobro.facades;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ues.occ.edu.sv.tpi2020.sistemaCobro.entities.Orden;
import org.apache.commons.lang.RandomStringUtils;

@Stateless
@LocalBean
public class OrdenFacade extends AbstractFacade<Orden> implements GenericLocalInterface<Orden> {

    @PersistenceContext(unitName = "ues.occ.edu.sv.tpi2020_sistema-cobro_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenFacade() {
        super(Orden.class);
    }

    public String generateUniqueId() {
        boolean idUnique = true;
        String idOrden = "";
        while (idUnique) {
            idOrden = RandomStringUtils.randomAlphanumeric(20);
            idUnique = !getEntityManager().createQuery("SELECT n from Orden n WHERE n.idOrden=:id").setParameter("id", idOrden).getResultList().isEmpty();
        }
        return idOrden;
    }
    
    public List<String> soloActivos() {
        return getEntityManager().createQuery("SELECT n.idOrden FROM Orden n WHERE n.activoOrden = true").getResultList();
    }
}
