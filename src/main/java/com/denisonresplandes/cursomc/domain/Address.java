package com.denisonresplandes.cursomc.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String street; 			// logradouro
	private String number;
	private String complement;
	private String neighborhood; 	// bairro
	private String postalCode;		// CEP
	
	@ManyToOne
	@JoinColumn(name = "id_customer")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "id_city")
	private City city;
	
	protected Address() { }
	
	public Address(String street, String number, String complement, String neighborhood, 
			String postalCode, Customer customer, City city) {
		validateAttribs(street, number, complement, neighborhood, 
				postalCode, customer, city);
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.postalCode = postalCode;
		this.customer = customer;
		this.city = city;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		Objects.requireNonNull(complement);
		if (complement.isBlank())
			throw new IllegalArgumentException("complement is blank");
		this.complement = complement;
	}

	public Integer getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public String getNumber() {
		return number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	void setCustomerInternal(Customer client) {
		this.customer = client;
	}
	
	public City getCity() {
		return city;
	}
	
	private void validateAttribs(String street, String number, String complement, String neighborhood,
			String postalCode, Customer customer, City city) {
		Objects.requireNonNull(street);
		Objects.requireNonNull(number);
		Objects.requireNonNull(complement);
		Objects.requireNonNull(postalCode);
		Objects.requireNonNull(customer);
		Objects.requireNonNull(city);
		Objects.requireNonNull(neighborhood);
		if (street.isBlank() || number.isBlank() || complement.isBlank() || 
				postalCode.isBlank() || neighborhood.isBlank())
			throw new IllegalArgumentException("one or more address attributes are blank");
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
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}
}
