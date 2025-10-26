package com.denisonresplandes.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import com.denisonresplandes.cursomc.domain.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer status;
	
	@OneToOne
	@JoinColumn(name = "id_order")
	@MapsId // a PK de payment será a mesma PK de Order. Nome da PK será id_order.
	private Order order;
	
	protected Payment() { }

	public Payment(Order order, PaymentStatus status) {
		validateAttribs(order, status);
		this.status = status.getCode();
		this.order = order;
	}
	
	public Integer getId() {
		return id;
	}
	
	public PaymentStatus getStatus() {
		return PaymentStatus.fromCode(status);
	}
	
	public void setStatus(PaymentStatus status) {
		this.status = status.getCode();
	}
	
	private void validateAttribs(Order order, PaymentStatus status) {
		Objects.requireNonNull(order, "order is null");
		Objects.requireNonNull(status, "status is null");
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}		
}