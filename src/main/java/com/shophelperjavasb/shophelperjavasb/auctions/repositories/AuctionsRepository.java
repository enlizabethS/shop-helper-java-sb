package com.shophelperjavasb.shophelperjavasb.auctions.repositories;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionsRepository extends JpaRepository<Auction, Long> {
    List<Auction> findAllByUser_Id(Long userId);
}
