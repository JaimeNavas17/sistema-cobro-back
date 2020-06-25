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
import ues.occ.edu.sv.tpi2020.sistemaCobro.entities.Producto;
import ues.occ.edu.sv.tpi2020.sistemaCobro.facades.ProductoFacade;

@Stateless
@Path("producto")
public class ProductoFacadeREST implements Serializable {

    @Inject
    ProductoFacade prodFacade;

    @PersistenceContext(unitName = "ues.occ.edu.sv.tpi2020_sistema-cobro_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    //working
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        if (prodFacade.create(new Producto(json.get("upc").getAsString(), json.get("nombreProd").getAsString(), json.get("precioUnit").getAsFloat(), json.get("descripcion").getAsString(), json.get("stockProd").getAsInt(), json.get("activoProd").getAsBoolean(), json.get("idCategoria").getAsInt(), json.get("idMarca").getAsInt()))) {
            return Response.status(Response.Status.OK).header("mensaje", "se creo con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //Working
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(String jsonString) {
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        if (prodFacade.edit(new Producto(json.get("upc").getAsString(), json.get("nombreProd").getAsString(), json.get("precioUnit").getAsFloat(), json.get("descripcion").getAsString(), json.get("stockProd").getAsInt(), json.get("activoProd").getAsBoolean(), json.get("idCategoria").getAsInt(), json.get("idMarca").getAsInt()))) {
            return Response.status(Response.Status.OK).header("mensaje", "se creo con exito").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("mensaje", "Sin exito").build();
        }
    }

    //working
    @PUT
    @Path("remove/{id}")
    public void remove(@PathParam("id") String id) {
        Producto prod = prodFacade.find(id);
        prod.setActivoProd(false);
        prodFacade.edit(prod);
    }

    @PUT
    @Path("stock/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void modStockProd(@PathParam("id") String id, String jsonString) {
        Producto prod = prodFacade.find(id);
        JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
        prod.setStockProd(json.get("stockProd").getAsInt());
        System.out.println("Se modificó el stock de:" +prod.getUpc());
        prodFacade.edit(prod);
    }

    //working
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto find(@PathParam("id") String id
    ) {
        return prodFacade.find(id);
    }

    //working
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> findAll() {
        return prodFacade.findAll();
    }

    //working
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> findRange(@PathParam("from") Integer from,
            @PathParam("to") Integer to
    ) {
        return prodFacade.findRange(from, to);
    }

    //working
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(prodFacade.count());
    }
}
