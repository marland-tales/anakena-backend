package com.catsjump.anakena.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.catsjump.anakena.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
//abstract garante que nao seja possivel instanciar diretamente objetos do tipo Pagamento, obriga que seja criado a partir da subclasses, por exemplo: Pagamento pagto1 = new PagamentoComCartao
public abstract class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer status;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
//mapeamento do atributo pedido referenciando que o Id do Pagamento assume o Id do Pedido - usando @OneToOne, @joinColumn e @MapsId 
	private CustomerOrder customerOrder;

	public Payment() {
	}

	public Payment(Integer id, PaymentStatus status, CustomerOrder pedido) {
		super();
		this.id = id;
		this.status = status.getCod();
		this.customerOrder = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentStatus getEstado() {
		return PaymentStatus.toEnum(status);
	}

	public void setEstado(PaymentStatus status) {
		this.status = status.getCod();
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setPedido(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}