package com.catsjump.anakena.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.catsjump.anakena.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("slipPayment")
public class SlipPayment extends Payment {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/mm/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/mm/yyyy")
	private Date paymentDate;

	public SlipPayment() {
	}

	public SlipPayment(Integer id, PaymentStatus status, CustomerOrder customerOrder, Date dueDate, Date paymentDate) {
		super(id, status, customerOrder);
		this.paymentDate = paymentDate;
		this.dueDate = dueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}	

}