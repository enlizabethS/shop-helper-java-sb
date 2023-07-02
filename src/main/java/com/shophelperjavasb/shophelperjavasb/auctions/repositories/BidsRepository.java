package com.shophelperjavasb.shophelperjavasb.auctions.repositories;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidsRepository extends JpaRepository<Bid, Long> {
    List<Bid> findAllByAuction_Id(Long auctionId);
}
