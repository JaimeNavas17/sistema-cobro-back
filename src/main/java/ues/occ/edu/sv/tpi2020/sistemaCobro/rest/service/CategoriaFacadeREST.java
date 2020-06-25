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
import ues.occ.edu.sv.tpi2020.sistemaCobro.entities.Categoria;
import ues.occ.edu.sv.tpi2020.sistemaCobro.facades.CategoriaFacade;

@Stateless
@Path("categoria")
public class CategoriaFacadeREST implements Serializable {

    @Inject
    CategoriaFacade catFacade;

    @PersistenceContext(unitName = "ues.occ.edu.sv.tpi2020_sistema-cobro_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    //working
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        if (catFacade.create(new Categoria(null, json.get("nombreCat").getAsString(), json.get("activoCat").getAsBoolean(), json.get("descripcion").getAsString()))) {
            return Response.status(Response.Status.OK).header("mensaje", "se creo con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //Working
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        if (catFacade.edit(new Categoria(json.get("idCategoria").getAsInt(), json.get("nombreCat").getAsString(), json.get("activoCat").getAsBoolean(), json.get("descripcion").getAsString()))) {
            return Response.status(Response.Status.OK).header("mensaje", "se Modifico con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //Working
    @PUT
    @Path("remove/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") int id) {
        Categoria cat = catFacade.find(id);
        cat.setActivoCat(false);
        if (catFacade.edit(cat)) {
            return Response.status(Response.Status.OK).header("mensaje", "se elimino con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //Working
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Categoria find(@PathParam("id") Integer id) {
        return catFacade.find(id);
    }

    //Working
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> findAll() {
        return catFacade.findAll();
    }

    //Working
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return catFacade.findRange(from, to);
    }

    //Working
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(catFacade.count());
    }

}
