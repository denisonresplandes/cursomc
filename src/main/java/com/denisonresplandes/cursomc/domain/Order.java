package com.denisonresplandes.cursomc.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<OrderItem> orderItems;
	
	{
		this.orderItems = new HashSet<>();
	}
	
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
	
	public Set<Product> getProducts() {
		Set<Product> products = this.orderItems.stream()
			.map(OrderItem::getProduct)
			.collect(Collectors.toSet());
		// retorna uma view imutável e leve, sem cópia.
		return Collections.unmodifiableSet(products);
	}
	
	public void removeOrderItem(OrderItem oi) {
		Objects.requireNonNull(oi);
		if (this.orderItems.remove(oi)) {
			oi.getProduct().removeOrderItemInternal(oi);
		}		
	}
		
	public void addOrderItem(OrderItem oi) {
		Objects.requireNonNull(oi);
		// camada extra de robustez
		if (!this.equals(oi.getOrder())) {
	        throw new IllegalStateException("OrderItem does not belong to this Order");
	    }
		if (this.orderItems.add(oi)) {
			oi.getProduct().addOrderItemInternal(oi);
		}
	}
	
	public void addOrderItems(Set<OrderItem> ois) {
		Objects.requireNonNull(ois);
		ois.forEach(this::addOrderItem);
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
