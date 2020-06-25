/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.tpi2020.sistemaCobro.rest.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ues.occ.edu.sv.tpi2020.sistemaCobro.entities.MetodoPago;
import ues.occ.edu.sv.tpi2020.sistemaCobro.facades.MetodoPagoFacade;

@Stateless
@Path("metodoPago")
public class MetodoPagoFacadeREST implements Serializable {

    @Inject
    MetodoPagoFacade metPagoFacade;

    @PersistenceContext(unitName = "ues.occ.edu.sv.tpi2020_sistema-cobro_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    //Working
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        if (metPagoFacade.create(new MetodoPago(null, json.get("nombrePago").getAsString(), json.get("activoMet").getAsBoolean(), json.get("descripcionMetodo").getAsString()))) {
            return Response.status(Response.Status.OK).header("mensaje", "se creo con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //working
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        if (metPagoFacade.edit(new MetodoPago(json.get("idMetodoPago").getAsInt(), json.get("nombrePago").getAsString(), json.get("activoMet").getAsBoolean(), json.get("descripcionMetodo").getAsString()))) {
            return Response.status(Response.Status.OK).header("mensaje", "se edito con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //working
    @PUT
    @Path("remove/{id}")
    public Response remove(@PathParam("id") Integer id) {
        MetodoPago met = metPagoFacade.find(id);
        met.setActivoMet(false);
        if (metPagoFacade.edit(met)) {
            return Response.status(Response.Status.OK).header("mensaje", "se edito con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //working
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MetodoPago find(@PathParam("id") Integer id) {
        return metPagoFacade.find(id);
    }

    //working
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MetodoPago> findAll() {
        return metPagoFacade.findAll();
    }

    //working
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MetodoPago> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return metPagoFacade.findRange(from, to);
    }

    //working
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(metPagoFacade.count());
    }
}
