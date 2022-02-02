package com.catsjump.anakena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catsjump.anakena.domain.State;

@Repository
//Repository eh uma anotacao do Spring que abstrai o uso do JPA

public interface StateRepository extends JpaRepository<State, Integer> {
	
}
