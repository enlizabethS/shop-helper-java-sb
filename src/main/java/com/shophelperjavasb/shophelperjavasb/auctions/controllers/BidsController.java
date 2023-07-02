package com.shophelperjavasb.shophelperjavasb.auctions.controllers;

import com.shophelperjavasb.shophelperjavasb.auctions.controllers.api.BidsApi;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.NewBidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.services.BidsService;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BidsController implements BidsApi {
    private final BidsService bidsService;

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<BidDto> createNew(AuthenticatedUser currentUser, NewBidDto newBidDto) {
        return ResponseEntity.status(201)
            .body(bidsService.createNewBid(currentUser, newBidDto));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<BidDto> getById(Long bidId) {
        return ResponseEntity.ok(bidsService.getById(bidId));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<BidDto> updateStatus(Long bidId, String newStatus) {
        return ResponseEntity.ok(bidsService.updateStatus(bidId, newStatus));
    }
}
