package com.denisonresplandes.cursomc.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.denisonresplandes.cursomc.domain.Category;
import com.denisonresplandes.cursomc.repositories.CategoryRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category("Informática");
		Category cat2 = new Category("Escritório");
		categoryRepository.saveAll(List.of(cat1, cat2));
	}

}
