package com.pooranjoyb.product.service.product.repository;

import com.pooranjoyb.product.service.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
