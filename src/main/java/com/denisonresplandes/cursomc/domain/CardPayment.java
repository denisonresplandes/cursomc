package com.denisonresplandes.cursomc.domain;

import java.util.Objects;

import com.denisonresplandes.cursomc.domain.enums.PaymentStatus;

import jakarta.persistence.Entity;

@Entity
public class CardPayment extends Payment {

	private static final long serialVersionUID = 1L;
	
	private static final int MINIMUM_INSTALLMENTS = 1;
	private static final int MAXIMUM_INSTALLMENTS = 12;
	
	private Integer numberOfInstallments;

	protected CardPayment() { }

	public CardPayment(Order order, PaymentStatus status, Integer numberOfInstallments) {
		super(order, status);
		validateAttribs(numberOfInstallments);
		this.numberOfInstallments = numberOfInstallments;
	}
	
	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}
	
	public void setNumberOfInstallments(Integer numberOfInstallments) {
		validateAttribs(numberOfInstallments);
		this.numberOfInstallments = numberOfInstallments;
	}
	
	private void validateAttribs(Integer numberOfInstallments) {
		Objects.requireNonNull(numberOfInstallments);
		if (numberOfInstallments < MINIMUM_INSTALLMENTS || 
				numberOfInstallments > MAXIMUM_INSTALLMENTS) {
			throw new IllegalArgumentException("numberOfInstallments is invalid");
		}
	}
}
