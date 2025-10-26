package com.denisonresplandes.cursomc.configs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.denisonresplandes.cursomc.domain.Address;
import com.denisonresplandes.cursomc.domain.CardPayment;
import com.denisonresplandes.cursomc.domain.Category;
import com.denisonresplandes.cursomc.domain.City;
import com.denisonresplandes.cursomc.domain.Customer;
import com.denisonresplandes.cursomc.domain.Order;
import com.denisonresplandes.cursomc.domain.OrderItem;
import com.denisonresplandes.cursomc.domain.PaymentBankSlip;
import com.denisonresplandes.cursomc.domain.Product;
import com.denisonresplandes.cursomc.domain.State;
import com.denisonresplandes.cursomc.domain.enums.PaymentStatus;
import com.denisonresplandes.cursomc.domain.enums.TypeCustomer;
import com.denisonresplandes.cursomc.repositories.AddressRepository;
import com.denisonresplandes.cursomc.repositories.CategoryRepository;
import com.denisonresplandes.cursomc.repositories.CityRepository;
import com.denisonresplandes.cursomc.repositories.CustomerRepository;
import com.denisonresplandes.cursomc.repositories.OrderItemRepository;
import com.denisonresplandes.cursomc.repositories.OrderRepository;
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
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category("Informática");
		Category cat2 = new Category("Escritório");

//		List.of(cat1, cat2).forEach(categoryRepository::save);
		categoryRepository.saveAll(List.of(cat1, cat2));
		
		Product prod1 = new Product("Computador", 2000.00);
		Product prod2 = new Product("Impressora", 800.00);
		Product prod3 = new Product("Mouse", 80.00);
		
		prod1.addCategories(Set.of(cat1));
		prod2.addCategories(Set.of(cat1, cat2));
		prod3.addCategories(Set.of(cat1));
		
//		List.of(prod1, prod2, prod3).forEach(productRepository::save);
		productRepository.saveAll(List.of(prod1, prod2, prod3));
		
		State state1 = new State("Minas Gerais");
		State state2 = new State("São Paulo");
		
		City city1 = new City("Uberlândia", state1);
		City city2 = new City("São Paulo", state2);
		City city3 = new City("Campinas", state2);
		
		state1.addCity(city1);
		state2.addCity(city2);
		state2.addCity(city3);
		
		stateRepository.saveAll(List.of(state1, state2));
		cityRepository.saveAll(List.of(city1, city2, city3));
		
		Customer customer1 = new Customer("Maria Silva", "maria@gmail.com", "36378912377", 
				TypeCustomer.NATURAL_PERSON);
		
		customer1.addPhones(Set.of("27363323", "93838393"));
		
		Address address1 = new Address("Rua Flores", "300", "Apto 203", "Jardim", 
				"38220834", customer1, city1);
		Address address2 = new Address("Avenida Matos", "105", "Sala 800", "Centro", 
				"38777012", customer1, city2);
		
		customer1.addAddress(address1);
		customer1.addAddress(address2);
		
		customerRepository.save(customer1);
		addressRepository.saveAll(List.of(address1, address2));
		
		Order o1 = new Order(ZonedDateTime.parse("2025-10-23T10:32:00-03:00"), 
				customer1, address1);
		Order o2 = new Order(ZonedDateTime.parse("2025-10-10T19:35:00-03:00"), 
				customer1, address2);
		
		o1.setPayment(new CardPayment(o1, PaymentStatus.PAID_OFF, 6));
		o2.setPayment(new PaymentBankSlip(o2, PaymentStatus.PENDING, LocalDate
				.parse("2025-10-20"), null));
		
		customer1.addOrder(o1);
		customer1.addOrder(o2);
		
		orderRepository.saveAll(List.of(o1, o2));
		
		OrderItem oi1 = new OrderItem(o1, prod1, 0.00, 1, new BigDecimal("2000.00"));
		OrderItem oi2 = new OrderItem(o1, prod3, 0.00, 2, new BigDecimal("80.00"));
		OrderItem oi3 = new OrderItem(o2, prod2, 100.00, 1, new BigDecimal("800.00"));
		
		o1.addOrderItem(oi1);
		o1.addOrderItem(oi2);
		o2.addOrderItem(oi3);
		
		orderItemRepository.saveAll(List.of(oi1, oi2, oi3));
	}
}
