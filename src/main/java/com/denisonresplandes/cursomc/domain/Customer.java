package com.denisonresplandes.cursomc.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.denisonresplandes.cursomc.domain.enums.TypeCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private Integer type;
	
	@ElementCollection
	@CollectionTable(name = "customer_phone", 
		joinColumns = @JoinColumn(name = "id_customer"))
	private Set<String> phones;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,
		orphanRemoval = true)
	private Set<Address> addresses;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE,
		orphanRemoval = true)
	private Set<Order> orders;
	
	{
		phones = new HashSet<>();
		addresses = new HashSet<>();
		orders = new HashSet<>();
	}

	protected Customer() {}
	
	public Customer(String name, String email, String cpfOrCnpj, TypeCustomer type) {
		validateAttribs(name, email, cpfOrCnpj, type);
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.type = type.getCode();
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Set<String> getPhones() {
		return Set.copyOf(phones);
	}
	
	public void addPhone(String phone) {
		Objects.requireNonNull(phone);
		if (phone.isBlank())
			throw new IllegalArgumentException("phone is blank");
		phones.add(phone);
	}
	
	public void addPhones(Set<String> phones) {
		Objects.requireNonNull(phones);
		phones.forEach(this::addPhone);
	}
	
	public void setEmail(String email) {
		Objects.requireNonNull(email);
		if (email.isBlank()) 
			throw new IllegalArgumentException("email is blank");
		this.email = email;
	}
	
	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}
	
	public TypeCustomer getType() {
		return TypeCustomer.fromCode(type);
	}
	
	public Set<Address> getAddresses() {
		return Set.copyOf(addresses);
	}
	
	public void addAddress(Address address) {
		Objects.requireNonNull(address);
		if (addresses.add(address)) {
			address.setCustomerInternal(this);
		}
	}
	
	public void removeAddress(Address address) {
		Objects.requireNonNull(address);
		if (addresses.remove(address)) {
			address.setCustomerInternal(null);
		}
	}
	
	public void addAddresses(Set<Address> newAddresses) {
		Objects.requireNonNull(newAddresses);
		newAddresses.forEach(this::addAddress);
	}
	
	public Set<Order> getOrders() {
		return orders;
	}
	
	public void addOrder(Order order) {
		Objects.requireNonNull(order);
		orders.add(order);
	}
	
	public void addOrders(Set<Order> orders) {
		Objects.requireNonNull(orders);
		orders.forEach(this::addOrder);
	}
		
	private void validateAttribs(String name, String email, String cpfOrCnpj, 
			TypeCustomer type) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(email);
		Objects.requireNonNull(cpfOrCnpj);
		Objects.requireNonNull(type);
		if (name.isBlank() || email.isBlank() || cpfOrCnpj.isBlank()) {
			throw new IllegalArgumentException("one or more customer attributes are blank");
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
		Customer other = (Customer) obj;
		return Objects.equals(id, other.id);
	}		
}