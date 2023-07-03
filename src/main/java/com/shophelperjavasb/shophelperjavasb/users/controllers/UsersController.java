package com.shophelperjavasb.shophelperjavasb.users.controllers;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.ProfileDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UsersPage;
import com.shophelperjavasb.shophelperjavasb.users.controllers.api.UsersApi;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import com.shophelperjavasb.shophelperjavasb.users.repositories.UsersRepository;
import com.shophelperjavasb.shophelperjavasb.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {
    private final UsersService usersService;
    private final UsersRepository usersRepository;

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
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User user = usersService.getUserById(userId);

        if (user == null) {
            return new ResponseEntity<>("User with ID " + userId + " not found", HttpStatus.NOT_FOUND);
        }
        // Обновляем данные пользователя
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        usersRepository.save(user);

        return new ResponseEntity<>("User update", HttpStatus.OK);
    }

    // не менять - получать профиль
    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<ProfileDto> getProfile(AuthenticatedUser currentUser) {

        Long  currentUserId = currentUser.getUser().getId();
        ProfileDto profile = usersService.getProfile(currentUserId);

        return ResponseEntity.ok(profile);
    }

    // не менять - получать продукты текущего пользователя
    @Override
    public ResponseEntity<List<ProductDTO>> getMyProducts(AuthenticatedUser currentUser) {
        return ResponseEntity.ok(usersService.getMyProducts(currentUser));
    }

    // не менять - получать покупки текущего пользователя
    @Override
    public ResponseEntity<List<PurchaseResponseDto>> getMyPurchases(AuthenticatedUser currentUser) {
        return ResponseEntity.ok(usersService.getMyPurchases(currentUser));
    }

    // не менять - получать аукционы текущего пользователя
    @Override
    public ResponseEntity<List<AuctionDto>> getMyAuctions(AuthenticatedUser currentUser) {
        return ResponseEntity.ok(usersService.getMyAuctions(currentUser));
    }

    // не менять - получать ставки текущего пользователя
    @Override
    public ResponseEntity<List<BidDto>> getMyBids(AuthenticatedUser currentUser) {
        return ResponseEntity.ok(usersService.getMyBids(currentUser));
    }
}

