package com.javaee.rodrigoandrades.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.javaee.rodrigoandrades.domain.Produto;

public class ProdutoService {
	private List<Produto> produtos = new ArrayList<Produto>();
	private Integer actualId = 1;

	public ProdutoService() {
		produtos.add(new Produto(actualId++, "Smartphone Motorola Moto G5s XT1792 Prata com 32GB, Tela de 5.2'', Dual Chip, Android 7.1, 4G, Câmera 16MP, Processador Octa-Core e 2GB de RAM", 809.10));
		produtos.add(new Produto(actualId++, "Motorola Moto G5S - 32 GB - Platina - GSM", 899.10));
		produtos.add(new Produto(actualId++, "Smartphone Samsung Galaxy J7 Metal Dual Chip Android 6.0 Tela 5.5'' 16GB 4G Câmera 13MP - Dourado", 641.51));
		produtos.add(new Produto(actualId++, "LG K10 NOVO - 32 GB - Preto - GSM", 597.50));
		produtos.add(new Produto(actualId++, "Samsung Galaxy J7 Pro - 64 GB - Azul - GSM", 1143.60));
	}

	public List<Produto> getAll() {
		return produtos;
	}

	public Produto findById(Integer id) {
		Optional<Produto> vehicleOptional = produtos.stream().filter(produto -> produto.getId().equals(id)).findFirst();

		return vehicleOptional.orElse(null);
	}
}