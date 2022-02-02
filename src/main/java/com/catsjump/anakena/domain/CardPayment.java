package com.catsjump.anakena.domain;

import javax.persistence.Entity;

import com.catsjump.anakena.domain.enums.PaymentStatus;

@Entity
public class CardPayment extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public CardPayment() {
	}

	public CardPayment(Integer id, PaymentStatus estado, CustomerOrder pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}



}