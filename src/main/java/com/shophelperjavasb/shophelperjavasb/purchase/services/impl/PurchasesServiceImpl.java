package com.shophelperjavasb.shophelperjavasb.purchase.services.impl;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ProductsRepository;
import com.shophelperjavasb.shophelperjavasb.purchase.dto.NewPurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchase.dto.PurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchase.model.Purchase;
import com.shophelperjavasb.shophelperjavasb.purchase.repositories.PurchasesRepository;
import com.shophelperjavasb.shophelperjavasb.purchase.services.PurchasesService;
import com.shophelperjavasb.shophelperjavasb.shippers.model.Shipper;
import com.shophelperjavasb.shophelperjavasb.shippers.repositories.ShippersRepository;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.shophelperjavasb.shophelperjavasb.purchase.model.Purchase.Status.*;

@Service
@RequiredArgsConstructor
public class PurchasesServiceImpl implements PurchasesService {
    private final PurchasesRepository purchasesRepository;
//    private final AuthenticatedUser currentUser;
    private final ProductsRepository productsRepository;
    private final ShippersRepository shippersRepository;

    @Override
    public PurchaseDto createPurchase(
        AuthenticatedUser currentUser,
        NewPurchaseDto newPurchaseDto,
        int productId,
        int quantity,
        int shipperId
    ) {
        User user = currentUser.getUser();
        Product product = productsRepository.findById(productId)
            .orElseThrow(() -> new NotFoundException("Product with id <" + productId + "> not found"));
        Shipper shipper = shippersRepository.findById(shipperId)
            .orElseThrow(() -> new NotFoundException("Shipper with id <" + shipperId + "> not found"));

        Purchase purchase = Purchase.builder()
            .user(user)
            .product(product)
            .quantity(quantity)
            .shipper(shipper)
            .status(CREATED)
            .createdDate(LocalDateTime.now())
            .build();

        purchasesRepository.save(purchase);

        return PurchaseDto.from(purchase);
    }

    @Override
    public PurchaseDto getPurchaseById(int purchaseId) {
        Purchase purchase = purchasesRepository.findById(purchaseId)
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + purchaseId + "> not found"));

        return PurchaseDto.from(purchase);
    }

    @Override
    public PurchaseDto updatePurchase(int purchaseId, NewPurchaseDto newPurchaseDto) {
        Purchase purchase = purchasesRepository.findById(purchaseId)
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + purchaseId + "> not found"));

        purchase.setUser(newPurchaseDto.getUser());
        purchase.setProduct(newPurchaseDto.getProduct());
        purchase.setQuantity(newPurchaseDto.getQuantity());
        purchase.setShipper(newPurchaseDto.getShipper());
        purchase.setStatus(valueOf(newPurchaseDto.getStatus()));

        purchasesRepository.save(purchase);

        return PurchaseDto.from(purchase);
    }

    @Override
    public PurchaseDto updateStatusPurchase(int purchaseId, String newStatus) {
        Purchase purchase = purchasesRepository.findById(purchaseId)
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + purchaseId + "> not found"));

        if (newStatus.equals("CREATED") ||
            newStatus.equals("PERFORMED") ||
            newStatus.equals("DONE")) {
            purchase.setStatus(valueOf(newStatus));
        }

        return PurchaseDto.from(purchase);
    }
}
