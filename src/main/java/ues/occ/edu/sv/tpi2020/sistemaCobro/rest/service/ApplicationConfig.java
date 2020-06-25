/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.tpi2020.sistemaCobro.rest.service;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest-resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ues.occ.edu.sv.tpi2020.sistemaCobro.CORS.NewCrossOriginResourceSharingFilter.class);
        resources.add(ues.occ.edu.sv.tpi2020.sistemaCobro.rest.service.CategoriaFacadeREST.class);
        resources.add(ues.occ.edu.sv.tpi2020.sistemaCobro.rest.service.DetalleOrdenFacadeREST.class);
        resources.add(ues.occ.edu.sv.tpi2020.sistemaCobro.rest.service.MarcaFacadeREST.class);
        resources.add(ues.occ.edu.sv.tpi2020.sistemaCobro.rest.service.MetodoPagoFacadeREST.class);
        resources.add(ues.occ.edu.sv.tpi2020.sistemaCobro.rest.service.OrdenFacadeREST.class);
        resources.add(ues.occ.edu.sv.tpi2020.sistemaCobro.rest.service.ProductoFacadeREST.class);
    }
    
}
