package com.shophelperjavasb.shophelperjavasb.auctions.controllers.api;

import com.shophelperjavasb.shophelperjavasb.auctions.dto.BidDto;
import com.shophelperjavasb.shophelperjavasb.auctions.dto.NewBidDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
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

@Tags(value = {
    @Tag(name = "Bids")
})
@RequestMapping("/api/bids")
public interface BidsApi {
    @Operation(summary = "Create", description = "Create new bid")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "New bid",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BidDto.class))})}
    )
    @PostMapping
    ResponseEntity<BidDto> createNew(
        @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser,
        @RequestBody NewBidDto newBidDto
    );

    @Operation(summary = "Get by id", description = "Get bid by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bid by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BidDto.class))})}
    )
    @GetMapping("/{bid-id}")
    ResponseEntity<BidDto> getById(@PathVariable("bid-id") Long bidId);

    @Operation(summary = "Update status", description = "Update bid status by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update bid status by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BidDto.class))})}
    )
    @PutMapping("/{bid-id}/status")
    ResponseEntity<BidDto> updateStatus(@PathVariable("bid-id") Long purchaseId, @RequestBody String newStatus);
}
