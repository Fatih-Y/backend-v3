package com.pinsoft.shopapp.repository;

import com.pinsoft.shopapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends  JpaRepository<Product, Integer> {
	
	 List<Product> findByName(String name);
}
