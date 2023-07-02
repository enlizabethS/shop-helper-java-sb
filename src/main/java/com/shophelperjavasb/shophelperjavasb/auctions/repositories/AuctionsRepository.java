package com.shophelperjavasb.shophelperjavasb.auctions.repositories;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionsRepository extends JpaRepository<Auction, Long> {
}
