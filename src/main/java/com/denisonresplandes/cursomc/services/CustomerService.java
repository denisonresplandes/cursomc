package com.denisonresplandes.cursomc.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denisonresplandes.cursomc.domain.Customer;
import com.denisonresplandes.cursomc.exceptions.ResourceNotFoundException;
import com.denisonresplandes.cursomc.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Customer findById(Integer id) {
		validateId(id);
		Customer customer = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(String.format("%s Id: %d, Type: %s", 
					"Resource not found!", id, Customer.class.getSimpleName())));
		return customer;
	}
	
	private void validateId(Integer id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("id is null");
		}
	}
}
