package com.shophelperjavasb.shophelperjavasb.auctions.services;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.NewAuctionDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.shared.dto.PutStatusDto;

import java.util.List;

public interface AuctionsService {
    AuctionDto createNew(
        AuthenticatedUser currentUser,
        NewAuctionDto newAuctionDto
    );

    AuctionDto getById(Long auctionId);

    AuctionDto updateStatus(
        Long auctionId,
        PutStatusDto newStatus);

    AuctionDto updateBids(
        Long auctionId,
        Long bidId
    );

    List<BidDto> getBidsByAuction(Long auctionId);
}
