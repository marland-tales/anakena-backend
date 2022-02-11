package com.catsjump.anakena.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class CustomerOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/mm/yyyy hh:mm")
	private Date instante;

//	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL, mappedBy="customerOrder")
//cascadeType eh uma peculiaridade do JPA, erro de entidade transiente quando vai salvar um pedido/payment 
	private Payment payment;

//	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Customer customer;
//@manyToOne permite determinar o tipo de relacionamento e @JoinColumns determina a FK 

	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private Address deliveryAddress;
	
	@OneToMany(mappedBy="id.customerOrder")
	private Set<OrderItem> itens = new HashSet<>();

	public CustomerOrder() {
	}

	public CustomerOrder(Integer id, Date instante, Customer customer, Address deliveryAddress) {
		super();
		this.id = id;
		this.instante = instante;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Customer getCliente() {
		return customer;
	}

	public void setCliente(Customer customer) {
		this.customer = customer;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Set<OrderItem> getItens() {
		return itens;
	}

	public void setItens(Set<OrderItem> itens) {
		this.itens = itens;
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
		CustomerOrder other = (CustomerOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}