package com.shophelperjavasb.shophelperjavasb.users.services;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.ProfileDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UsersPage;
import com.shophelperjavasb.shophelperjavasb.users.model.User;

import java.util.List;

public interface UsersService {
    UsersPage getAll();

    // не менять - получать профиль
    ProfileDto getProfile(Long currentUserId);

    UserDto getUser(Long userId);

    User getUserById(Long userId);

    // не менять - получать продукты текущего пользователя
    List<ProductDTO> getMyProducts(AuthenticatedUser currentUser);

    // не менять - получать покупки текущего пользователя
    List<PurchaseResponseDto> getMyPurchases(AuthenticatedUser currentUser);

    // не менять - получать аукционы текущего пользователя
    List<AuctionDto> getMyAuctions(AuthenticatedUser currentUser);

    // не менять - получать ставки текущего пользователя
    List<BidDto> getMyBids(AuthenticatedUser currentUser);
}
