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
import jakarta.persistence.OneToMany;

@Entity
public class State implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "state")
	private Set<City> cities;

	{
		cities = new HashSet<>();
	}
	
	protected State() { }
	
	public State(String name) {
		Objects.requireNonNull(name);
		if (name.isBlank()) {
			throw new IllegalArgumentException("name is blank");
		}
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Set<City> getCities() {
		return Set.copyOf(cities);
	}
	
	public void addCity(City city) {
		Objects.requireNonNull(city);
		cities.add(city);
	}
	
	public void addAllCities(Set<City> cities) {
		Objects.requireNonNull(cities);
		cities.forEach(this::addCity);
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
		State other = (State) obj;
		return Objects.equals(id, other.id);
	}
}
