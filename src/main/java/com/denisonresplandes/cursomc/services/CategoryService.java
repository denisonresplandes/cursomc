package com.denisonresplandes.cursomc.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denisonresplandes.cursomc.domain.Category;
import com.denisonresplandes.cursomc.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public Category findById(Integer id) {
		validateId(id);
		return repository.findById(id).orElse(null);
	}
	
	private void validateId(Integer id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("id is null");
		}
	}
}
