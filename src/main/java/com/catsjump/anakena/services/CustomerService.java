package com.catsjump.anakena.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.Customer;
import com.catsjump.anakena.repositories.CustomerRepository;
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
}