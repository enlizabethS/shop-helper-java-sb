package com.shophelperjavasb.shophelperjavasb.purchase.controllers;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.purchase.controllers.api.PurchasesApi;
import com.shophelperjavasb.shophelperjavasb.purchase.dto.NewPurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchase.dto.PurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchase.services.PurchasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchasesController implements PurchasesApi {
    private final PurchasesService purchasesService;

    @Override
    public ResponseEntity<PurchaseDto> createPurchase(AuthenticatedUser currentUser, NewPurchaseDto newPurchaseDto) {
        return null;
    }

    @Override
    public ResponseEntity<PurchaseDto> getPurchaseById(int purchaseId) {
        return null;
    }

    @Override
    public ResponseEntity<PurchaseDto> updatePurchase(int purchaseId, NewPurchaseDto newPurchaseDto) {
        return null;
    }

    @Override
    public ResponseEntity<PurchaseDto> updateStatusPurchase(int purchaseId, String newStatus) {
        return null;
    }
}
