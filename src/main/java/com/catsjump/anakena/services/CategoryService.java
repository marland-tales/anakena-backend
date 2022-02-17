package com.catsjump.anakena.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.Category;
import com.catsjump.anakena.repositories.CategoryRepository;
import com.catsjump.anakena.services.exceptions.DataIntegrityException;
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
 
 public Category insert(Category obj){
	 obj.setId(null);
	 return repo.save(obj);
 }
 
 public Category update (Category obj) {
	 find(obj.getId());

	 return repo.save(obj);
 }
/*usando o metodo find o id sera buscado antes de efetuar o update, e caso nao exista, o proprio metodo find gerara a excecao
 *metodo save do Repository do SpringData serve para inserir e atualizar. A diferenca eh que quando o id esta valendo nulo,
 * ele insere, e quando o id esta preenchido, ele atualiza
 * */
 
 public void delete(Integer id){
	 find(id);
	 try {
		  repo.deleteById(id);
	 }
	 catch (DataIntegrityViolationException e) {
		 throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
	}
 }
//lancando uma excecao personalizada 
  
}