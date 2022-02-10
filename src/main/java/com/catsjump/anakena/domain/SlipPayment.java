package com.catsjump.anakena.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.catsjump.anakena.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SlipPayment extends Payment {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/mm/yyyy")
	private Date dataVencimento;
	
	@JsonFormat(pattern="dd/mm/yyyy")
	private Date dataPagamento;

	public SlipPayment() {
	}

	public SlipPayment(Integer id, PaymentStatus estado, CustomerOrder pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}	

}