package com.javaee.rodrigoandrades.controllers;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaee.rodrigoandrades.domain.Produto;
import com.javaee.rodrigoandrades.services.ProdutoService;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ProdutoController {
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	private ProdutoService produtoService;

	public ProdutoController() {
		produtoService = new ProdutoService();
	}

	@GET
	public List<Produto> getAll() {
		logger.info("getAllProducts: {}");
		List<Produto> vehicles = produtoService.getAll();
		return vehicles;
	}
}