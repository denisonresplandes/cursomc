package com.denisonresplandes.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	private String name;
	
	public Category(Integer id, String name) {
		validateName(name);
		Objects.requireNonNull(id);
		this.id = id;
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
	
	private void validateName(String name) {
		Objects.requireNonNull(name);
		if (name.isBlank()) 
			throw new IllegalArgumentException("name is blank!");
	}
}
