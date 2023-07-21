package com.shophelperjavasb.shophelperjavasb.users.services;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductPreviewDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.*;
import com.shophelperjavasb.shophelperjavasb.users.model.User;

import java.util.List;

public interface UsersService {
    UsersPage getAll();

    ProfileDto getProfile(Long currentUserId);

    UserDto getUser(Long userId);

    User getUserById(Long userId);

    UserResponseDto updateUser(AuthenticatedUser currentUser, UserUpdateDto updatedUser);

    List<ProductPreviewDto> getMyProducts(AuthenticatedUser currentUser);

    List<PurchaseResponseDto> getMyPurchases(AuthenticatedUser currentUser);

    List<AuctionDto> getMyAuctions(AuthenticatedUser currentUser);

    List<BidDto> getMyBids(AuthenticatedUser currentUser);
}
