package com.catsjump.anakena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catsjump.anakena.domain.CustomerOrder;

@Repository
//Repository eh uma anotacao do Spring que abstrai o uso do JPA

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
	
}
