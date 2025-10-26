package com.denisonresplandes.cursomc.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "_order") // h2 data base
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private ZonedDateTime date;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "id_customer")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "id_delivery_address")
	private Address deliveryAddress;
	
	protected Order() { }

	public Order(ZonedDateTime date, Customer customer, Address deliveryAddress) {
		validateAttribs(date, customer, deliveryAddress);
		this.date = date;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
	}
		
	public ZonedDateTime getDate() {
		return date;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		Objects.requireNonNull(payment, "payment is null");
		this.payment = payment;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
	
	public void changeDeliveryAddress(Address newAddress) {
		Objects.requireNonNull(newAddress);
		this.deliveryAddress = newAddress;
	}

	private void validateAttribs(ZonedDateTime date, Customer customer, 
			Address deliveryAddress) {
		Objects.requireNonNull(date, "date is null");
		Objects.requireNonNull(customer, "customer is null");
		Objects.requireNonNull(deliveryAddress, "deliveryAddress is null");		
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}		
}
