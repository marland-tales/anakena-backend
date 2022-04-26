package com.catsjump.anakena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.catsjump.anakena.domain.Customer;

@Repository
//Repository eh uma anotacao do Spring que abstrai o uso do JPA

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Transactional(readOnly=true)
//indicando como nao transacional, diminui o lockIn no DB e performa a aplicacao
	
	Customer findByEmail(String email);
//usando findByEmail, constroe-se o metodo a partir da implementacao do framework, desta forma as consultas JPQL são geradas automaticamente
	
}