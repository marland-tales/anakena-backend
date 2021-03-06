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
	private Date instant;

//	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL, mappedBy="customerOrder")
//cascadeType eh uma peculiaridade do JPA, erro de entidade transiente quando vai salvar um pedido/payment 
	private Payment payment;

//	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
//@manyToOne permite determinar o tipo de relacionamento e @JoinColumns determina a FK 

	@ManyToOne
	@JoinColumn(name="delivery_address_id")
	private Address deliveryAddress;
	
	@OneToMany(mappedBy="id.customerOrder")
	private Set<OrderItem> itens = new HashSet<>();

	public CustomerOrder() {
	}

	public CustomerOrder(Integer id, Date instant, Customer customer, Address deliveryAddress) {
		super();
		this.id = id;
		this.instant = instant;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
	}
	
	public double getTotal() {
		double sum = 0.0;
		for (OrderItem oi : itens) {
			sum = sum+ oi.getSubTotal();
		}
		return sum;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
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