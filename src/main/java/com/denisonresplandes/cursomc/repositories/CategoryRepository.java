package com.denisonresplandes.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.denisonresplandes.cursomc.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}
