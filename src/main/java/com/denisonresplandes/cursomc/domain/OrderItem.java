package com.denisonresplandes.cursomc.domain;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public final class OrderItem {

	@EmbeddedId
	private OrderItemPK id;
	private Double discount;
	private Integer quantity;
	private BigDecimal price;
	
	OrderItem() {}
	
	public OrderItem(Order order, Product product, Double discount,
			Integer quantity, BigDecimal price) {
		validateAttribs(discount, quantity, price);
		this.id = new OrderItemPK(order, product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public Double getDiscount() {
		return discount;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	private void validateAttribs(Double discount, Integer quantity, BigDecimal price) {
		Objects.requireNonNull(discount);
		Objects.requireNonNull(quantity);
		Objects.requireNonNull(price);
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}
