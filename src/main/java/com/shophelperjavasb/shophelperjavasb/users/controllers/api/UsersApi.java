package com.shophelperjavasb.shophelperjavasb.users.controllers.api;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.*;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(value = {
    @Tag(name = "Users")
})
@RequestMapping("/api/users")
public interface UsersApi {
    @Operation(summary = "Get all")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all users",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UsersPage.class))
            }
        )
    })
    @GetMapping
    ResponseEntity<UsersPage> getAll();

    @Operation(summary = "Get user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get user by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))
            }
        )
    })
    @GetMapping("/{user-id}")
    ResponseEntity<UserDto> getUser(@PathVariable("user-id") Long  userId);

    @Operation(summary = "Update user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update user by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))
            }
        )
    })
    @PutMapping("/{userId}")
    ResponseEntity<UserResponseDto> updateUser(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser, @RequestBody UserUpdateDto updatedUser);

    // не менять - получать профиль
    @Operation(summary = "Profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get profile",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProfileDto.class))
            }
        )
    })
    @GetMapping("/my/profile")
    ResponseEntity<ProfileDto> getProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser);

    // не менять - получать продукты текущего пользователя
    @Operation(summary = "Products")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all products of the user",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class))
            }
        )
    })
    @GetMapping("/my/products")
    ResponseEntity<List<ProductDTO>> getMyProducts(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser);

    // не менять - получать покупки текущего пользователя
    @Operation(summary = "Purchases")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all purchases of the user",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PurchaseResponseDto.class))
            }
        )
    })
    @GetMapping("/my/purchases")
    ResponseEntity<List<PurchaseResponseDto>> getMyPurchases(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser);

    // не менять - получать аукционы текущего пользователя
    @Operation(summary = "Auctions")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all auctions of the user",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuctionDto.class))
            }
        )
    })
    @GetMapping("/my/auctions")
    ResponseEntity<List<AuctionDto>> getMyAuctions(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser);

    // не менять - получать ставки текущего пользователя
    @Operation(summary = "Bids")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all bids of the user",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BidDto.class))
            }
        )
    })
    @GetMapping("/my/bids")
    ResponseEntity<List<BidDto>> getMyBids(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser);
}
