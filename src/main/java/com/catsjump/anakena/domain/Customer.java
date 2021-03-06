package com.catsjump.anakena.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.catsjump.anakena.domain.enums.CustomerType;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String name;
	
	@Column(unique=true)
//anotacao do JPA para o banco garantir que nao teremos repeticao por email - garantindo integridade
	
	private String email;
	private String cpfOuCnpj;
	private Integer type;

	@OneToMany(mappedBy = "customer", cascade=CascadeType.ALL)
//cascade = JPA aplica a regra de relacionamento -  operacao que afetar o cliente afetara tambem o endereco
	private List<Address> addresses = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "phone")
	private Set<String> phones = new HashSet<>();

	@JsonBackReference
	@OneToMany(mappedBy = "customer")
	private List<CustomerOrder> customerOrder = new ArrayList<>();

	public Customer() {
	}

	public Customer(Integer id, String name, String email, String cpfOuCnpj, CustomerType type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.type = (type == null) ? null : type.getCod();
//inspecao condicional alternaria para validar se preenchimento dos campos
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public CustomerType getTipo() {
		return CustomerType.toEnum(type);
	}

	public void setTipo(CustomerType type) {
		this.type = type.getCod();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public List<CustomerOrder> getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(List<CustomerOrder> customerOrder) {
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
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}