package com.shophelperjavasb.shophelperjavasb.purchase.repositories;

import com.shophelperjavasb.shophelperjavasb.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesRepository extends JpaRepository<Purchase, Integer> {
}
