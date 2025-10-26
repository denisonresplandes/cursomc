package com.denisonresplandes.cursomc.domain;

import java.time.LocalDate;
import java.util.Objects;

import com.denisonresplandes.cursomc.domain.enums.PaymentStatus;

import jakarta.persistence.Entity;

@Entity
public class PaymentBankSlip extends Payment {
	
	private static final long serialVersionUID = 1L;
	
	private LocalDate dueDate;
	private LocalDate paymentDate;
	
	protected PaymentBankSlip() { }
	
	public PaymentBankSlip(Order order, PaymentStatus status, LocalDate dueDate,
			LocalDate paymentDate) {
		super(order, status);
		Objects.requireNonNull(dueDate, "duoDate is null");
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public LocalDate getPaymentDate() {
		return paymentDate;
	}	
}
