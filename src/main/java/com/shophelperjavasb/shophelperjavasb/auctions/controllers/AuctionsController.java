package com.shophelperjavasb.shophelperjavasb.auctions.controllers;

import com.shophelperjavasb.shophelperjavasb.auctions.controllers.api.AuctionsApi;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.NewAuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.services.AuctionsService;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuctionsController implements AuctionsApi {
    private final AuctionsService auctionsService;

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<AuctionDto> createPurchase(AuthenticatedUser currentUser, NewAuctionDto newAuctionDto) {
        return ResponseEntity.status(201)
            .body(auctionsService.createNew(currentUser, newAuctionDto));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<AuctionDto> getById(Long auctionId) {
        return ResponseEntity.ok(auctionsService.getById(auctionId));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<AuctionDto> updateStatus(Long auctionId, String newStatus) {
        return ResponseEntity.ok(auctionsService.updateStatus(auctionId, newStatus));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<AuctionDto> updateBids(Long auctionId, Long bidId) {
        return ResponseEntity.ok(auctionsService.updateBids(auctionId, bidId));
    }
}
