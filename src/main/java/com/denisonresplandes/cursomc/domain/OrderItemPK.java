package com.denisonresplandes.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public final class OrderItemPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_order")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;
	
	OrderItemPK() {}
	
	public OrderItemPK(Order order, Product product) {
		Objects.requireNonNull(order);
		Objects.requireNonNull(product);
		this.order = order;
		this.product = product;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public Product getProduct() {
		return product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects
				.equals(product, other.product);
	}		
}
