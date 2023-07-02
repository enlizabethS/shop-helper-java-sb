package com.shophelperjavasb.shophelperjavasb.auctions.services.impl;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.NewBidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.model.Auction;
import com.shophelperjavasb.shophelperjavasb.auctions.model.Bid;
import com.shophelperjavasb.shophelperjavasb.auctions.repositories.BidsRepository;
import com.shophelperjavasb.shophelperjavasb.auctions.services.BidsService;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.purchases.repositories.AuctionsRepository;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BidsServiceImpl implements BidsService {
    private final BidsRepository bidsRepository;
    private final AuctionsRepository auctionsRepository;

    @Override
    public BidDto createNewBid(AuthenticatedUser currentUser, NewBidDto newBidDto) {
        User user = currentUser.getUser();

        Auction auction = auctionsRepository.findById(newBidDto.getAuctionId())
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + newBidDto.getAuctionId() + "> not found"));

        Bid newBid = Bid.builder()
            .user(user)
            .amount(newBidDto.getAmount())
            .auction(auction)
            .createdDate(LocalDateTime.now())
            .status(Bid.Status.ACTIVE)
            .build();

        bidsRepository.save(newBid);

        return BidDto.from(newBid);
    }

    @Override
    public BidDto getById(Long bidId) {
        Bid bid = bidsRepository.findById(bidId)
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + bidId + "> not found"));

        return BidDto.from(bid);
    }

    @Override
    public BidDto updateStatus(Long bidId, String newStatus) {
        Bid bid = bidsRepository.findById(bidId)
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + bidId + "> not found"));

        if (newStatus.equals("ACTIVE") ||
        newStatus.equals("INACTIVE")) {
            bid.setStatus(Bid.Status.valueOf(newStatus));
        }

        return null;
    }
}
