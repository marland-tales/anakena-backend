package com.catsjump.anakena.dto;

import java.io.Serializable;

import com.catsjump.anakena.domain.Product;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	private String name;
	private Double price; 
	
	public ProductDTO() {		
	}

	public ProductDTO(Product obj) {
		id = obj.getId();
		name = obj.getName();
		price = obj.getPrice();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
		
}