package com.catsjump.anakena.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.Categoria;
import com.catsjump.anakena.repositories.CategoriaRepository;
import com.catsjump.anakena.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

 @Autowired
//anotacao para instanciar automaticamente as instancias declaradas, por injecao de dependencia ou inversao de controle
	private CategoriaRepository repo;

 public Categoria find(Integer id) {
	 Optional<Categoria> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
	 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
}