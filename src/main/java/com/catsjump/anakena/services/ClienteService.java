package com.catsjump.anakena.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.Cliente;
import com.catsjump.anakena.repositories.ClienteRepository;
import com.catsjump.anakena.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

 @Autowired
//anotacao para instanciar automaticamente as instancias declaradas, por injecao de dependencia ou inversao de controle
	private ClienteRepository repo;

 public Cliente find(Integer id) {
	 Optional<Cliente> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
	 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}