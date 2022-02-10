package com.catsjump.anakena.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class State implements Serializable {
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String nome;

@JsonIgnore
@OneToMany(mappedBy="state")
private List<City> cities = new ArrayList<>();

public State() {
}

public State(Integer id, String nome) {
	super();
	this.id = id;
	this.nome = nome;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public List<City> getCitys() {
	return cities;
}

public void setCitys(List<City> cities) {
	this.cities = cities;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cities == null) ? 0 : cities.hashCode());
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
	State other = (State) obj;
	if (cities == null) {
		if (other.cities != null)
			return false;
	} else if (!cities.equals(other.cities))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
} 

}