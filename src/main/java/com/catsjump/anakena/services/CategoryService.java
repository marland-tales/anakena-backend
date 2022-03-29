package com.catsjump.anakena.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.Category;
import com.catsjump.anakena.dto.CategoryDTO;
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
	 Category newObj = find(obj.getId());
//instancia o objeto a partir do banco de dados, com isso estara monitorado pelo JPA
	 updateData(newObj, obj);
//atualiza o objeto com os dados que foram enviados na requisicao
	 return repo.save(newObj);
//persiste no banco de dados
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
  
 public List<Category> findAll(){
	 return repo.findAll();
 } 
 
 public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
	 PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
	 return repo.findAll(pageRequest);
}
//Page eh a anotacao que abstrai informacaoes e dados da paginacao - note que ha uma sobrecarga de metodos no findAll com pageRequest
 
 
 public Category fromDTO(CategoryDTO objDTO) {
	 return new Category(objDTO.getId(), objDTO.getName());
 }
 
 private void updateData(Category newObj, Category obj) {
	 newObj.setName(obj.getName());
}
 
}