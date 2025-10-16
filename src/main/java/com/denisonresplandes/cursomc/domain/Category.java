package com.denisonresplandes.cursomc.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products;
	
	{
		this.products = new HashSet<>();
	}
	
	protected Category() { }
	
	public Category(String name) {
		validateName(name);
		this.name = name;
	}
		
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		validateName(name);
		this.name = name;
	}
	
	public Set<Product> getProducts() {
		return Set.copyOf(products);
	}
	
	public void addProduct(Product product) {
		Objects.requireNonNull(product);
		if (products.add(product)) {
			// garante a sincronização do lado inverso
			product.getCategoriesInternal().add(this);
		}
	}
	
	public void addAllProducts(Set<Product> newProducts) {
		Objects.requireNonNull(newProducts);
		newProducts.forEach(this::addProduct);
	}
	
	// método auxilar para o inverso do 
	// relacionamento bidirecional
	Set<Product> getProductsInternal() {
		return products;
	}
	
	private void validateName(String name) {
		Objects.requireNonNull(name);
		if (name.isBlank()) 
			throw new IllegalArgumentException("name is blank!");
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}
	
}
