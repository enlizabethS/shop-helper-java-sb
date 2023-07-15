package com.shophelperjavasb.shophelperjavasb.products.repositories;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserId(Long userId);
}
