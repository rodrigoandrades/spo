package com.javaee.rodrigoandrades.controllers;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaee.rodrigoandrades.domain.Pedido;
import com.javaee.rodrigoandrades.domain.Produto;
import com.javaee.rodrigoandrades.mail.EmailConfig;
import com.javaee.rodrigoandrades.services.PedidoService;
import com.javaee.rodrigoandrades.services.ProdutoService;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class PedidoController {
	private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

	private PedidoService pedidoService;
	private ProdutoService produtoService;
	private EmailConfig config;
	
	public PedidoController() {
		pedidoService = new PedidoService();
		produtoService = new ProdutoService();
		config = new EmailConfig();
	}

	@GET
	public List<Pedido> getAll() {
		logger.info("getAllPedidos: {}");
		List<Pedido> pedidos = pedidoService.getAll();
		return pedidos;
	}

	@GET
	@Path("{id : \\d+}")
	public Response getById(@PathParam("id") Integer id) {
		logger.info("getById : {}", id);
		Pedido pedido = pedidoService.findById(id);
		if (pedido == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(pedido).build();
	}

	@POST
	public Response create(Pedido pedido, @Context UriInfo uriInfo) {
		logger.info("create: {}", pedido);
		
		Produto produtoSaved = produtoService.findById(pedido.getIdProduto());
		if (produtoSaved == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		Pedido savedPedido = pedidoService.savePedido(pedido);
		sendMail(pedido);
		
		logger.debug("Pedido created with id = ", savedPedido.getId());
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(savedPedido.getId().toString());
		return Response.created(builder.build()).entity(savedPedido).build();
	}
	
	private void sendMail(Pedido pedido){
		final String fromEmail = "rodrigomail2007@gmail.com";
		final String password = "*****";
		final String toEmail = pedido.getEmail();

		StringBuilder email = new StringBuilder();
		email.append(pedido.getNome()).append(", sua compra foi ralizada com sucesso!\n\n");
		email.append("Produto: ").append(produtoService.findById(pedido.getIdProduto()).getNome()).append("\n");
		email.append("Valor: ").append(produtoService.findById(pedido.getIdProduto()).getPreco()).append("\n\n");
		email.append("Seu produto será entregue no seguinte endereço: ").append(pedido.getEndereco()).append("\n\n");
		email.append("Obrigado.\n");
		
		config.sendEmail(fromEmail, password, toEmail, "Compra aprovada", email.toString());
	}
}