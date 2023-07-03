package com.shophelperjavasb.shophelperjavasb.purchases.repositories;

import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasesRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUser_Id(Long userId);
}
