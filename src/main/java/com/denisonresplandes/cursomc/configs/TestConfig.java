package com.denisonresplandes.cursomc.configs;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.denisonresplandes.cursomc.domain.Category;
import com.denisonresplandes.cursomc.domain.City;
import com.denisonresplandes.cursomc.domain.Product;
import com.denisonresplandes.cursomc.domain.State;
import com.denisonresplandes.cursomc.repositories.CategoryRepository;
import com.denisonresplandes.cursomc.repositories.CityRepository;
import com.denisonresplandes.cursomc.repositories.ProductRepository;
import com.denisonresplandes.cursomc.repositories.StateRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category("Informática");
		Category cat2 = new Category("Escritório");

//		List.of(cat1, cat2).forEach(categoryRepository::save);
		categoryRepository.saveAll(List.of(cat1, cat2));
		
		Product prod1 = new Product("Computador", 2000.00);
		Product prod2 = new Product("Impressora", 800.00);
		Product prod3 = new Product("Mouse", 80.00);
		
		prod1.addAllCategories(Set.of(cat1));
		prod2.addAllCategories(Set.of(cat1, cat2));
		prod3.addAllCategories(Set.of(cat1));
		
//		List.of(prod1, prod2, prod3).forEach(productRepository::save);
		productRepository.saveAll(List.of(prod1, prod2, prod3));
		
		State state1 = new State("Minas Gerais");
		State state2 = new State("São Paulo");

		City city1 = new City("Uberlândia", state1);
		City city2 = new City("São Paulo", state2);
		City city3 = new City("Campinas", state2);
		
//		state1.addCity(city1);
//		state2.addAllCities(Set.of(city2, city3));
		
		stateRepository.saveAll(List.of(state1, state2));
		cityRepository.saveAll(List.of(city1, city2, city3));
	}

}
