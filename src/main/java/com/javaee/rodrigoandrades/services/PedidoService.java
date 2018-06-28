package com.javaee.rodrigoandrades.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.javaee.rodrigoandrades.domain.Pedido;

public class PedidoService {
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private Integer actualId = 1;
	
	public List<Pedido> getAll() {
		return pedidos;
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> pedidoOptional = pedidos.stream().filter(pedido -> pedido.getId().equals(id)).findFirst();

		return pedidoOptional.orElse(null);
	}
	
	public Pedido savePedido(Pedido pedido) {
		if (pedido.getId() != null) {
			this.deleteById(pedido.getId());
		} else {
			actualId++;
			pedido.setId(actualId);
		}
		this.pedidos.add(pedido);
		return pedido;
	}
	
	public void deleteById(Integer id) {
		this.pedidos.removeIf(pedido -> pedido.getId().equals(id));
	}
}