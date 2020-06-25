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
import ues.occ.edu.sv.tpi2020.sistemaCobro.entities.Marca;
import ues.occ.edu.sv.tpi2020.sistemaCobro.facades.MarcaFacade;

@Stateless
@Path("marca")
public class MarcaFacadeREST implements Serializable {
    
    @Inject
    MarcaFacade marcaFacade;

    @PersistenceContext(unitName = "ues.occ.edu.sv.tpi2020_sistema-cobro_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    //working
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        System.out.println(json.get("nombreMarca").getAsString());
        System.out.println(json.get("activoMarca").getAsBoolean());
        System.out.println(json.get("descripMarca").getAsString());
        if (marcaFacade.create(new Marca(null, json.get("nombreMarca").getAsString(), json.get("activoMarca").getAsBoolean(), json.get("descripMarca").getAsString()))) {
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
         System.out.println(json.get("idMarca").getAsInt());
         System.out.println(json.get("nombreMarca").getAsString());
         System.out.println(json.get("activoMarca").getAsBoolean());
         System.out.println(json.get("descripMarca").getAsString());
        if (marcaFacade.edit(new Marca(json.get("idMarca").getAsInt(), json.get("nombreMarca").getAsString(), json.get("activoMarca").getAsBoolean(), json.get("descripMarca").getAsString()))) {
            return Response.status(Response.Status.OK).header("mensaje", "se creo con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //working
    @PUT
    @Path("remove/{id}")
    public Response remove(@PathParam("id") int id) {
        Marca mar = marcaFacade.find(id);
        mar.setActivoMarca(false);
        if (marcaFacade.edit(mar)) {
            return Response.status(Response.Status.OK).header("mensaje", "se elimino con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //working
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Marca find(@PathParam("id") String id) {
        return marcaFacade.find(id);
    }

    //working
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Marca> findAll() {
        return marcaFacade.findAll();
    }

    //working
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Marca> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return marcaFacade.findRange(from, to);
    }

    //working
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(marcaFacade.count());
    }

    
}
