package com.shophelperjavasb.shophelperjavasb.auctions.services;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.NewBidDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.shared.dto.PutStatusDto;

public interface BidsService {
    BidDto createNewBid(AuthenticatedUser currentUser, NewBidDto newBidDto);

    BidDto getById(Long bidId);

    BidDto updateStatus(Long bidId, PutStatusDto newStatus);
}
