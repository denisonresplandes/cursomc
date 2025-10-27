package com.denisonresplandes.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denisonresplandes.cursomc.domain.Order;
import com.denisonresplandes.cursomc.services.OrderService;

@RestController
@RequestMapping("/pedidos")
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
}
