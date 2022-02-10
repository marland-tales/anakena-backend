package com.catsjump.anakena.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.CustomerOrder;
import com.catsjump.anakena.repositories.CustomerOrderRepository;
import com.catsjump.anakena.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerOrderService {

 @Autowired
//anotacao para instanciar automaticamente as instancias declaradas, por injecao de dependencia ou inversao de controle
	private CustomerOrderRepository repo;

 public CustomerOrder find(Integer id) {
	 Optional<CustomerOrder> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
	 "Objeto não encontrado! Id: " + id + ", Tipo: " + CustomerOrder.class.getName()));
	}
}