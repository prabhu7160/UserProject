package com.vois.user.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vois.user.entities.Products;

public interface ProductRepo extends JpaRepository<Products, Integer> {
	

		Optional<Products> findByProductName(String email);
	}
