package com.denisonresplandes.cursomc.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denisonresplandes.cursomc.domain.Category;
import com.denisonresplandes.cursomc.exceptions.ResourceNotFoundException;
import com.denisonresplandes.cursomc.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public Category findById(Integer id) {
		validateId(id);
		Category category = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(String.format("%s Id: %d, Type: %s", 
					"Resource not found!", id, Category.class.getSimpleName())));
		return category;
	}
	
	private void validateId(Integer id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("id is null");
		}
	}
}
