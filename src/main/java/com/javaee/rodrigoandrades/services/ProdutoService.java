package com.javaee.rodrigoandrades.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.javaee.rodrigoandrades.domain.Produto;

public class ProdutoService {
	private List<Produto> produtos = new ArrayList<Produto>();
	private Integer actualId = 1;

	public ProdutoService() {
		produtos.add(new Produto(actualId++, "iphone8"));
		produtos.add(new Produto(actualId++, "samsung galaxy s9"));
	}

	public List<Produto> getAll() {
		return produtos;
	}

	public Produto findById(Integer id) {
		Optional<Produto> vehicleOptional = produtos.stream().filter(produto -> produto.getId().equals(id)).findFirst();

		return vehicleOptional.orElse(null);
	}
}