package com.shophelperjavasb.shophelperjavasb.auctions.controllers.api;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.AuctionDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.NewAuctionDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.shared.dto.PutStatusDto;
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
    @Tag(name = "Auctions")
})
@RequestMapping("/api/auctions")
public interface AuctionsApi {
    @Operation(summary = "Create", description = "Create new auction")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "New auction",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuctionDto.class))})}
    )
    @PostMapping
    ResponseEntity<AuctionDto> createPurchase(
        @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser,
        @RequestBody NewAuctionDto newAuctionDto);

    @Operation(summary = "Get by id", description = "Get auction by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "auction by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuctionDto.class))})}
    )
    @GetMapping("/{auction-id}")
    ResponseEntity<AuctionDto> getById(@PathVariable("auction-id") Long auctionId);

    @Operation(summary = "Update status", description = "Update auction status by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update auction status by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuctionDto.class))})}
    )
    @PutMapping("/{auction-id}/status")
    ResponseEntity<AuctionDto> updateStatus(@PathVariable("auction-id") Long auctionId, @RequestBody PutStatusDto newStatus);

    @Operation(summary = "Update bids", description = "Update auction bids by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update auction bids by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuctionDto.class))})}
    )
    @PutMapping("/{auction-id}")
    ResponseEntity<AuctionDto> updateBids(@PathVariable("auction-id") Long auctionId, @RequestBody Long bidId);

    @Operation(summary = "Get bids", description = "Get all bids by auction id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all bids by auction id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BidDto.class))})}
    )
    @GetMapping("/{auction-id}/bids")
    ResponseEntity<List<BidDto>> getBidsByAuction(@PathVariable("auction-id") Long auctionId);
}
