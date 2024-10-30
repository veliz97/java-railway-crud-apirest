package com.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apirest.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    
}