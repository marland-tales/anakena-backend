package com.catsjump.anakena.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.Category;
import com.catsjump.anakena.domain.Product;
import com.catsjump.anakena.repositories.CategoryRepository;
import com.catsjump.anakena.repositories.ProductRepository;
import com.catsjump.anakena.services.exceptions.ObjectNotFoundException;


@Service
public class ProductService {

 @Autowired
//anotacao para instanciar automaticamente as instancias declaradas, por injecao de dependencia ou inversao de controle
	private ProductRepository repo;

 @Autowired
	private CategoryRepository categoryRepository;
 
 public Product find(Integer id) {
	 Optional<Product> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
	 "Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
	}
 
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		
		return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);	
	}

 
}