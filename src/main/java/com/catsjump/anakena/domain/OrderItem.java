package com.catsjump.anakena.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Double discount;
	private Integer quantidade;
	private Double amount;

	public OrderItem() {
	}

	public OrderItem(CustomerOrder customerOrder, Product product, Double discount, Integer quantidade, Double amount) {
		super();
		id.setCustomerOrder(customerOrder);
		id.setProduct(product);
		this.discount = discount;
		this.quantidade = quantidade;
		this.amount = amount;
	}

	@JsonIgnore
	public CustomerOrder getCustomerOrder() {
		return id.getCustomerOrder();
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public OrderItemPK getId() {
		return id;
	}

	public void setId(OrderItemPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return amount;
	}

	public void setPreco(Double amount) {
		this.amount = amount;
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}