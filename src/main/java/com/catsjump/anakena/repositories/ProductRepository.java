package com.catsjump.anakena.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.catsjump.anakena.domain.Category;
import com.catsjump.anakena.domain.Product;

@Repository
//Repository eh uma anotacao do Spring que abstrai o uso do JPA

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Transactional(readOnly=true)
//readOnly=true nao onera o banco lockando todos os registros
	
	@Query("SELECT DISTINCT obj "
			+ "FROM Product obj "
			+ "INNER JOIN obj.categories cat "
			+ "WHERE obj.name LIKE %:name% AND cat IN :categories")
	
	Page<Product> findDistinctByNameContainingAndCategoriesIn(
	@Param("name") String name, 
	@Param("categories") List<Category> categories, 
	Pageable pageRequest);
	
//	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);
	
}
