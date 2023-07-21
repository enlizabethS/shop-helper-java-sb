package com.shophelperjavasb.shophelperjavasb.auctions.services.impl;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.NewAuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.model.Auction;
import com.shophelperjavasb.shophelperjavasb.auctions.model.Bid;
import com.shophelperjavasb.shophelperjavasb.auctions.repositories.BidsRepository;
import com.shophelperjavasb.shophelperjavasb.auctions.services.AuctionsService;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ProductsRepository;
import com.shophelperjavasb.shophelperjavasb.auctions.repositories.AuctionsRepository;
import com.shophelperjavasb.shophelperjavasb.shared.dto.PutStatusDto;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionsServiceImpl implements AuctionsService {
    private final AuctionsRepository auctionsRepository;
    private final ProductsRepository productsRepository;
    private final BidsRepository bidsRepository;

    @Override
    public AuctionDto createNew(AuthenticatedUser currentUser, NewAuctionDto newAuctionDto) {
        User user = currentUser.getUser();

        Product product = productsRepository.findById(newAuctionDto.getProductId())
            .orElseThrow(() -> new NotFoundException("Auction with id <" + newAuctionDto.getProductId() + "> not found"));

        Auction auction = Auction.builder()
            .user(user)
            .product(product)
            .createdDate(LocalDateTime.now())
            .startDate(newAuctionDto.getStartDate())
            .expirationDate(newAuctionDto.getExpirationDate())
            .bids(new ArrayList<>())
            .status(Auction.Status.CREATED)
            .step(newAuctionDto.getStep())
            .build();

        auctionsRepository.save(auction);

        return AuctionDto.from(auction);
    }

    @Override
    public AuctionDto getById(Long auctionId) {
        Auction auction = auctionsRepository.findById(auctionId)
            .orElseThrow(() -> new NotFoundException("Auction with id <" + auctionId + "> not found"));

        return AuctionDto.from(auction);
    }

    @Override
    public AuctionDto updateStatus(Long auctionId, PutStatusDto newStatus) {
        Auction auction = auctionsRepository.findById(auctionId)
            .orElseThrow(() -> new NotFoundException("Auction with id <" + auctionId + "> not found"));

        if (newStatus.getStatus().equals("CREATED") ||
            newStatus.getStatus().equals("PERFORMED") ||
            newStatus.getStatus().equals("DONE")
        ) {
            auction.setStatus(Auction.Status.valueOf(newStatus.getStatus()));

            auctionsRepository.save(auction);

            return AuctionDto.from(auction);
        }

        return null;
    }

    @Override
    public AuctionDto updateBids(Long auctionId, Long bidId) {
        Auction auction = auctionsRepository.findById(auctionId)
            .orElseThrow(() -> new NotFoundException("Auction with id <" + auctionId + "> not found"));

        Bid bid = bidsRepository.findById(bidId)
                .orElseThrow(()->new NotFoundException("Bid with id <" + bidId + "> not found"));

        List<Bid> bids = auction.getBids();
        bids.add(auction.getBids().size() + 1, bid);
        auction.setBids(bids);

        auctionsRepository.save(auction);

        return AuctionDto.from(auction);
    }

    @Override
    public List<BidDto> getBidsByAuction(Long auctionId) {
        List<Bid> bids = bidsRepository.findAllByAuction_Id(auctionId);

        return BidDto.from(bids);
    }
}
