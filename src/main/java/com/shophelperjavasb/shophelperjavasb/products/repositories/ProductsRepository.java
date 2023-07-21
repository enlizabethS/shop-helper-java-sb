package com.shophelperjavasb.shophelperjavasb.products.repositories;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUser_Id(Long userId);
    List<Product> findAllByTitleContainingIgnoreCase(String productName);
    List<Product> findAllByUserNotAndTitleContainingIgnoreCase(User user, String productName);
}
