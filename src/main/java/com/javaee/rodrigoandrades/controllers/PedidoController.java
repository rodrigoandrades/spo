package com.javaee.rodrigoandrades.controllers;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaee.rodrigoandrades.domain.Produto;
import com.javaee.rodrigoandrades.services.ProdutoService;

@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class PedidoController {

	private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

	private ProdutoService produtoService;

	public PedidoController() {
		produtoService = new ProdutoService();
	}

	@GET
	public List<Produto> getAll() {
		logger.info("getAllVehicles: {}");
		List<Produto> vehicles = produtoService.getAll();
		return vehicles;
	}
	/*

	@GET
	@Path("{id : \\d+}")
	public Response getById(@PathParam("id") Integer id) {
		logger.info("getById : {}", id);
		Produto vehicle = produtoService.findById(id);
		if (vehicle == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(vehicle).build();
	}

	@POST
	public Response create(Produto vehicle, @Context UriInfo uriInfo) {
		logger.info("create: {}", vehicle);
		Produto savedVehicle = produtoService.saveVehicle(vehicle);
		logger.debug("Vehicle created with id = ", savedVehicle.getId());
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(savedVehicle.getId().toString());
		return Response.created(builder.build()).entity(savedVehicle).build();
	}

	@PUT
	@Path("/{id : \\d+}")
	public Response update(@PathParam("id") Integer id, Produto vehicle) {
		logger.info("Vehicle ID: {} ", id, vehicle);
		Produto vehicleSaved = vehicleService.findById(id);
		if (vehicleSaved == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		vehicle.setId(id);
		vehicleSaved = vehicleService.saveVehicle(vehicle);
		return Response.ok().entity(vehicleSaved).build();
	}

	@DELETE
	@Path("/{id : \\d+}")
	public Response delete(@PathParam("id") Integer id) {
		logger.info("Vehicle ID: {} ", id);
		vehicleService.deleteById(id);
		return Response.noContent().build();
	}
	*/
}