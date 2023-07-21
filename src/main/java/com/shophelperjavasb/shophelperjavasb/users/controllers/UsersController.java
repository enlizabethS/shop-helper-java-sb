package com.shophelperjavasb.shophelperjavasb.users.controllers;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductPreviewDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.*;
import com.shophelperjavasb.shophelperjavasb.users.controllers.api.UsersApi;
import com.shophelperjavasb.shophelperjavasb.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {
    private final UsersService usersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UsersPage> getAll() {
        return ResponseEntity.ok(usersService.getAll());
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> getUser( Long userId) {
        return ResponseEntity.ok(usersService.getUser(userId));
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUser(AuthenticatedUser currentUser, UserUpdateDto updatedUser) {
        return ResponseEntity.ok(usersService.updateUser(currentUser, updatedUser));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<ProfileDto> getProfile(AuthenticatedUser currentUser) {

        Long  currentUserId = currentUser.getUser().getId();
        ProfileDto profile = usersService.getProfile(currentUserId);

        return ResponseEntity.ok(profile);
    }

    @Override
    public ResponseEntity<List<ProductPreviewDto>> getMyProducts(AuthenticatedUser currentUser) {
        return ResponseEntity.ok(usersService.getMyProducts(currentUser));
    }

    @Override
    public ResponseEntity<List<PurchaseResponseDto>> getMyPurchases(AuthenticatedUser currentUser) {
        return ResponseEntity.ok(usersService.getMyPurchases(currentUser));
    }

    @Override
    public ResponseEntity<List<AuctionDto>> getMyAuctions(AuthenticatedUser currentUser) {
        return ResponseEntity.ok(usersService.getMyAuctions(currentUser));
    }

    @Override
    public ResponseEntity<List<BidDto>> getMyBids(AuthenticatedUser currentUser) {
        return ResponseEntity.ok(usersService.getMyBids(currentUser));
    }
}

