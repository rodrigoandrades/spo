package com.javaee.rodrigoandrades.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.javaee.rodrigoandrades.domain.Produto;

public class PedidoService {
	private List<Produto> vehicles = new ArrayList<Produto>();
	private Integer actualId = 10;
/*
	public ProdutoService2() {
		for (int i = 0; i < 10; i++) {
			Produto vehicle = new Produto();
			vehicle.setId(i);
			vehicle.setName("Subaru" + i);
			vehicle.setYear(2018);
			vehicles.add(vehicle);
		}
	}
*/
	public List<Produto> getAll() {
		return vehicles;
	}

	public Produto findById(Integer id) {
		Optional<Produto> vehicleOptional = vehicles.stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst();

		return vehicleOptional.orElse(null);
	}

	public Produto saveVehicle(Produto vehicle) {
		if (vehicle.getId() != null) {
			this.deleteById(vehicle.getId());
		} else {
			actualId++;
			vehicle.setId(actualId);
		}
		this.vehicles.add(vehicle);
		return vehicle;
	}

	public void deleteById(Integer id) {
		this.vehicles.removeIf(vehicle -> vehicle.getId().equals(id));
	}
}