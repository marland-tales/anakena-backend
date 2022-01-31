package com.catsjump.anakena.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.Category;
import com.catsjump.anakena.repositories.CategoryRepository;
import com.catsjump.anakena.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

 @Autowired
//anotacao para instanciar automaticamente as instancias declaradas, por injecao de dependencia ou inversao de controle
	private CategoryRepository repo;

 public Category find(Integer id) {
	 Optional<Category> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
	 "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
}