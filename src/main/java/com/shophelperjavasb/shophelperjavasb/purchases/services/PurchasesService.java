package com.shophelperjavasb.shophelperjavasb.purchases.services;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.NewPurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;

public interface PurchasesService {
    PurchaseResponseDto createPurchase(
        AuthenticatedUser currentUser,
        NewPurchaseDto newPurchaseDto);

    PurchaseResponseDto getById(Long purchaseId);

    PurchaseDto updateById(
        Long purchaseId,
        NewPurchaseDto newPurchaseDto);

    PurchaseDto updateStatus(
        Long purchaseId,
        String newStatus);
}
