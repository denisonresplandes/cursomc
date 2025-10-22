package com.denisonresplandes.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denisonresplandes.cursomc.domain.Customer;
import com.denisonresplandes.cursomc.services.CustomerService;

@RestController
@RequestMapping("/clientes")
public class CustomerResource {

	@Autowired
	private CustomerService service;
	
	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
}
