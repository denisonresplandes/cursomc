package com.denisonresplandes.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "id_state")
	private State state;
	
	protected City() { }
	
	public City(String name, State state) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(state);
		if (name.isBlank()) {
			throw new IllegalArgumentException("name is blank");
		}
		this.name = name;
		this.state = state;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public State getState() {
		return state;
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
		City other = (City) obj;
		return Objects.equals(id, other.id);
	}
}
