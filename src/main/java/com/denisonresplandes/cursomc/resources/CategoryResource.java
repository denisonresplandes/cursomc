package com.denisonresplandes.cursomc.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denisonresplandes.cursomc.domain.Category;

@RestController
@RequestMapping("/categorias")
public class CategoryResource {

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> categories = List.of(
				new Category(1, "Informática"), 
				new Category(2, "Escritório"));
		return ResponseEntity.ok(categories);
	}
}
