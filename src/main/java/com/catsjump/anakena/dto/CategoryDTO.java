package com.catsjump.anakena.dto;

import java.io.Serializable;

import com.catsjump.anakena.domain.Category;


//DTO boa pratica de engenharia de software para tratar isoladamente a manipulacao do objeto
public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoryDTO () {
	}
	
	public CategoryDTO (Category obj) {
		id = obj.getId();
		nome = obj.getNome();
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
	
	
	
}
