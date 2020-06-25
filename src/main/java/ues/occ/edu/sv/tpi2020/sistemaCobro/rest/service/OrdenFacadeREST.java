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
import ues.occ.edu.sv.tpi2020.sistemaCobro.entities.Orden;
import ues.occ.edu.sv.tpi2020.sistemaCobro.facades.OrdenFacade;
import java.util.Date;
import java.sql.Timestamp;
import ues.occ.edu.sv.tpi2020.sistemaCobro.facades.MetodoPagoFacade;

@Stateless
@Path("orden")
public class OrdenFacadeREST implements Serializable {

    @Inject
    OrdenFacade ordFacade;

    @Inject
    MetodoPagoFacade metPago;

    @PersistenceContext(unitName = "ues.occ.edu.sv.tpi2020_sistema-cobro_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    //working
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        Orden ord = new Orden(ordFacade.generateUniqueId(), new Timestamp(new Date().getTime()), json.get("totalOrden").getAsFloat(), json.get("nombreCliente").getAsString(), metPago.find(json.get("idMetodoPago").getAsInt()), true, json.get("observacionesOrden").getAsString());
        if (ordFacade.create(ord)) {
            return Response.status(Response.Status.OK).header("mensaje", "se creo con exito").entity(ord.getIdOrden()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //working
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        if (ordFacade.edit(new Orden(json.get("idOrden").getAsString(), new Timestamp(new Date().getTime()), json.get("totalOrden").getAsFloat(), json.get("nombreCliente").getAsString(), metPago.find(json.get("idMetodoPago").getAsInt()), json.get("activoOrden").getAsBoolean(), json.get("observacionesOrden").getAsString()))) {
            return Response.status(Response.Status.OK).header("mensaje", "se creo con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //working
    @PUT
    @Path("remove/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") String id) {
        Orden ord = ordFacade.find(id);
        ord.setActivoOrden(false);
        if (ordFacade.edit(ord)) {
            System.out.println("me eliminé"+ord.getIdOrden());
            return Response.status(Response.Status.OK).header("mensaje", "se elimino con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //working
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Orden find(@PathParam("id") String id) {
        return ordFacade.find(id);
    }

    //working
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orden> findAll() {
        return ordFacade.findAll();
    }

    @GET
    @Path("soloActivos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> soloActivos() {
        return ordFacade.soloActivos();
    }

    //working
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orden> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return ordFacade.findRange(from, to);
    }

    //working
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(ordFacade.count());
    }

}
