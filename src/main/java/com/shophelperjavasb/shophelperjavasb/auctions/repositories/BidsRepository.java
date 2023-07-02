package com.shophelperjavasb.shophelperjavasb.auctions.repositories;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidsRepository extends JpaRepository<Bid, Long> {
}
