package com.catsjump.anakena.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catsjump.anakena.domain.Address;
import com.catsjump.anakena.domain.City;
import com.catsjump.anakena.domain.Customer;
import com.catsjump.anakena.domain.enums.TipoCliente;
import com.catsjump.anakena.dto.CustomerDTO;
import com.catsjump.anakena.dto.NewCustomerDTO;
import com.catsjump.anakena.repositories.AddressRepository;
import com.catsjump.anakena.repositories.CityRepository;
import com.catsjump.anakena.repositories.CustomerRepository;
import com.catsjump.anakena.services.exceptions.DataIntegrityException;
import com.catsjump.anakena.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

 @Autowired
//anotacao para instanciar automaticamente as instancias declaradas, por injecao de dependencia ou inversao de controle
	private CustomerRepository repo;
 
 @Autowired
	private AddressRepository addressRepository;
 
 @Transactional
//neste metodo eh feito um insert em duas tabelas relacionadas entre sim, entao a anotacao transacional garante que a persistencia ocorra numa unica transacao
 public Customer insert(Customer obj){
	 obj.setId(null);
	 obj = repo.save(obj);
	 addressRepository.saveAll(obj.getAddresses());
	 return obj;
 } 
 
 public Customer find(Integer id) {
	 Optional<Customer> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
	 "Objeto não encontrado! Id: " + id + ", Tipo: " + Customer.class.getName()));
	}

 public Customer update (Customer obj) {
	 Customer newObj = find(obj.getId());
//instancia o objeto a partir do banco de dados, com isso estara monitorado pelo JPA
	 updateData(newObj, obj);
//atualiza o objeto com os dados que foram recebidos na requisicao
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
 
 public Customer fromDTO(NewCustomerDTO objDTO) {
//sobrecarga de metodo
	 Customer ctm = new Customer(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
//instanciando cliente
	 City cit = new City(objDTO.getCityId(), null, null);
//instanciando cidade para ser usada no endereco
	 Address adr = new Address(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), ctm, cit);
//instanciando endereco (para instanciar a cidade eh necessario obter do banco de dados usando o codigo de cidade recebido na entrada no DTO
	 ctm.getAddresses().add(adr);
	 ctm.getPhones().add(objDTO.getPhone1());
	 if (objDTO.getPhone2()!=null) {
		 ctm.getPhones().add(objDTO.getPhone2());
	 }
	 if (objDTO.getPhone3()!=null) {
		 ctm.getPhones().add(objDTO.getPhone3());
	 }
	return ctm;
 }
 
 private void updateData(Customer newObj, Customer obj) {
	 newObj.setName(obj.getName());
	 newObj.setEmail(obj.getEmail());
}
 //o new.Obj que foi buscado no banco de dados no metodo mais acima, foram atualizados para os novos valores fornecidos no obj
}