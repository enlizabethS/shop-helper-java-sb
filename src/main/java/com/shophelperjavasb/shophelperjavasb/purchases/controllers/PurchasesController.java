package com.shophelperjavasb.shophelperjavasb.purchases.controllers;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.purchases.controllers.api.PurchasesApi;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.NewPurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.services.PurchasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchasesController implements PurchasesApi {
    private final PurchasesService purchasesService;

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<PurchaseResponseDto> createPurchase(
        AuthenticatedUser currentUser,
        NewPurchaseDto newPurchaseDto
    ) {

        return ResponseEntity.status(201)
            .body(purchasesService.createPurchase(
                currentUser,
                newPurchaseDto));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<PurchaseResponseDto> getById(Long purchaseId) {
        return ResponseEntity.ok(purchasesService.getById(purchaseId));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<PurchaseDto> updateById(Long purchaseId, NewPurchaseDto newPurchaseDto) {
        return ResponseEntity.status(200)
            .body(purchasesService.updateById(purchaseId, newPurchaseDto));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<PurchaseDto> updateStatus(Long purchaseId, String newStatus) {
        return ResponseEntity.status(200)
            .body(purchasesService.updateStatus(purchaseId, newStatus));
    }
}
