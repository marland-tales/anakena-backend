package com.catsjump.anakena.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catsjump.anakena.domain.Categoria;

@RestController
//RequestController eh uma anotacao do Spring usada para gerenciar controladores REST

@RequestMapping(value="/categorias")
//RequestMapping eh usado para definir o endpoint

public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
//existem metodos do RequestMapping, por exemplo o Method que eh usado para definir o verbo/metodo
	public 	List<Categoria> listar () {
		
		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
		
		
		
	}

}
