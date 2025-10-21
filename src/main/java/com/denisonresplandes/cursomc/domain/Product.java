package com.denisonresplandes.cursomc.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Product implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "product_category",
		joinColumns = @JoinColumn(name = "id_product"),
		inverseJoinColumns = @JoinColumn(name = "id_category"))
	private Set<Category> categories;
	
	{
		this.categories = new HashSet<>();
	}
	
	protected Product() { }
	
	public Product(String name, Double price) {
		validateName(name);
		validatePrice(price);
		this.name = name;
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		validatePrice(price);
		this.price = price;
	}
		
	public Set<Category> getCategories() {
		return Set.copyOf(categories);
	}
	
	public void removeCategory(Category category) {
	    Objects.requireNonNull(category);
	    if (categories.remove(category)) {
	    	// sincronização
	        category.removeProductInternal(this);
	    }
	}
	
	public void removeCategories(Set<Category> oldCategories) {
		Objects.requireNonNull(oldCategories);
		oldCategories.forEach(this::removeCategory);
	}
	
	public void addCategory(Category category) {
		Objects.requireNonNull(category);
		if (categories.add(category)) {
			// garante a sincronização do lado inverso
			category.addProductInternal(this);
		}
	}
	
	public void addCategories(Set<Category> newCategories) {
		Objects.requireNonNull(newCategories);
		newCategories.forEach(this::addCategory);
	}
	
	// método auxilar para o inverso do 
	// relacionamento bidirecional
	void addCategoryInternal(Category category) {
		categories.add(category);
	}
	
	void removeCategoryInternal(Category category) {
		categories.remove(category);
	}
	
	private void validateName(String name) {
		Objects.requireNonNull(name);
		if (name.isBlank()) { 
			throw new IllegalArgumentException("name is blank");
		}
	}
	
	private void validatePrice(Double price) {		
		Objects.requireNonNull(price);
		if (price.compareTo(0.0) <= 0) {
			throw new IllegalArgumentException("price is invalid");
		}
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
}
