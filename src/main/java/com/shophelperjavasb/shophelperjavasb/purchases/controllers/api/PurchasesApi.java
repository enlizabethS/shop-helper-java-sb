package com.shophelperjavasb.shophelperjavasb.purchases.controllers.api;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.NewPurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.StatusPurchaseDto;
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
    @Tag(name = "Purchases")
})
@RequestMapping("/api/purchases")
public interface PurchasesApi {
    @Operation(summary = "Create", description = "Create new purchase")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "New purchase",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PurchaseDto.class))})}
    )
    @PostMapping
    ResponseEntity<PurchaseDto> createPurchase(
        @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser,
        @RequestBody NewPurchaseDto newPurchaseDto);

    @Operation(summary = "Get by id", description = "Get purchase by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Purchase by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PurchaseDto.class))})}
    )
    @GetMapping("/{purchase-id}")
    ResponseEntity<PurchaseDto> getById(@PathVariable("purchase-id") int purchaseId);

    @Operation(summary = "Update by id", description = "Update the entire purchase item by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update purchase by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PurchaseDto.class))})}
    )
    @PutMapping("/{purchase-id}")
    ResponseEntity<PurchaseDto> updateById(@PathVariable("purchase-id") int purchaseId, @RequestBody NewPurchaseDto newPurchaseDto);

    @Operation(summary = "Update status", description = "Update purchase status by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update purchase status by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PurchaseDto.class))})}
    )
    @PatchMapping("/{purchase-id}")
    ResponseEntity<PurchaseDto> updateStatus(@PathVariable("purchase-id") int purchaseId, @RequestBody StatusPurchaseDto newStatus);
}
