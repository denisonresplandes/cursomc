package com.denisonresplandes.cursomc.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denisonresplandes.cursomc.domain.Order;
import com.denisonresplandes.cursomc.exceptions.ResourceNotFoundException;
import com.denisonresplandes.cursomc.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Order findById(Integer id) {
		validateId(id);
		Order order = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(String.format("%s Id: %d, Type: %s",
					"Resource not found!", id, Order.class.getSimpleName())));
		return order;
	}
	
	private void validateId(Integer id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("id is null");
		}
	}
}
