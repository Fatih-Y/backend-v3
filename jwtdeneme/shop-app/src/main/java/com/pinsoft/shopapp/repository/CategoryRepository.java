package com.pinsoft.shopapp.repository;

import com.pinsoft.shopapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository extends  JpaRepository<Category, Integer> {
	
	 Category findByName(String name);

}
