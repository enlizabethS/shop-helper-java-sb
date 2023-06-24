package com.shophelperjavasb.shophelperjavasb.purchase.services;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.purchase.dto.NewPurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchase.dto.PurchaseDto;

public interface PurchasesService {
    PurchaseDto createPurchase(AuthenticatedUser currentUser,
                               NewPurchaseDto newPurchaseDto,
                               int productId,
                               int quantity,
                               int shipperId);

    PurchaseDto getPurchaseById(int purchaseId);

    PurchaseDto updatePurchase(int purchaseId, NewPurchaseDto newPurchaseDto);

    PurchaseDto updateStatusPurchase(int purchaseId, String newStatus);
}
