package com.catsjump.anakena.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.Customer;
import com.catsjump.anakena.dto.CustomerDTO;
import com.catsjump.anakena.repositories.CustomerRepository;
import com.catsjump.anakena.services.exceptions.DataIntegrityException;
import com.catsjump.anakena.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

 @Autowired
//anotacao para instanciar automaticamente as instancias declaradas, por injecao de dependencia ou inversao de controle
	private CustomerRepository repo;

 public Customer find(Integer id) {
	 Optional<Customer> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
	 "Objeto não encontrado! Id: " + id + ", Tipo: " + Customer.class.getName()));
	}

 public Customer update (Customer obj) {
	 Customer newObj = find(obj.getId());
//instancia o objeto a partir do banco de dados, com isso estara monitorado pelo JPA
	 updateData(newObj, obj);
//atualiza o objeto com os dados que foram enviados na requisicao
	 return repo.save(newObj);
//persiste no banco de dados
 	}
 
 public void delete(Integer id){
	 find(id);
	 try {
		  repo.deleteById(id);
	 }
	 catch (DataIntegrityViolationException e) {
		 throw new DataIntegrityException("Não é possível excluir um cliente que possua pedidos");
	}
 }

 public List<Customer> findAll(){
	 return repo.findAll();
 } 
 
 public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
	 PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
	 return repo.findAll(pageRequest);
}

 
 public Customer fromDTO(CustomerDTO objDTO) {
	return new Customer(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
 }
 
 private void updateData(Customer newObj, Customer obj) {
	 newObj.setName(obj.getName());
	 newObj.setEmail(obj.getEmail());
}
 //o new.Obj que foi buscado no banco de dados no metodo mais acima, foram atualizados para os novos valores fornecidos no obj
}