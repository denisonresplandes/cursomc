package com.denisonresplandes.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denisonresplandes.cursomc.domain.Category;
import com.denisonresplandes.cursomc.services.CategoryService;

@RestController
@RequestMapping("/categorias")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> categories = List.of(
				new Category("Informática"), 
				new Category("Escritório"));
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
}
