package com.catsjump.anakena.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catsjump.anakena.domain.Customer;
import com.catsjump.anakena.services.CustomerService;

@RestController
//RequestController eh uma anotacao do Spring usada para gerenciar controladores REST

@RequestMapping(value="/customers")
//RequestMapping eh usado para definir o endpoint
public class CustomerResource {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
//existem metodos do RequestMapping, por exemplo o Method que eh usado para definir o verbo/metodo
	public ResponseEntity<?> find (@PathVariable Integer id)  {
//ResponseEntity tipo especial do Spring que encapsula varias informacoes de resposta HTTP para um servico REST
//@PathVariable anotacao Spring para capturar o valor recebido no path e setar como argumento do metodo
		Customer obj = service.find(id);
		return ResponseEntity.ok().body(obj);
				
	}

}
