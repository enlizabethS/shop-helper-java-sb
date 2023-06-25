package com.shophelperjavasb.shophelperjavasb.purchases.services;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.NewPurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.StatusPurchaseDto;

public interface PurchasesService {
    PurchaseDto createPurchase(
        AuthenticatedUser currentUser,
        NewPurchaseDto newPurchaseDto);

    PurchaseDto getById(int purchaseId);

    PurchaseDto updateById(
        int purchaseId,
        NewPurchaseDto newPurchaseDto);

    PurchaseDto updateStatus(
        int purchaseId,
        StatusPurchaseDto newStatus);
}
