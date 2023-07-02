package com.shophelperjavasb.shophelperjavasb.purchases.repositories;

import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesRepository extends JpaRepository<Purchase, Long> {
}
