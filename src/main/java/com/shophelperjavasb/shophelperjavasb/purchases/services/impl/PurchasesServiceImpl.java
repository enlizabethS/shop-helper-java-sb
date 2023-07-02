package com.shophelperjavasb.shophelperjavasb.purchases.services.impl;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ProductsRepository;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.NewPurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import com.shophelperjavasb.shophelperjavasb.purchases.repositories.PurchasesRepository;
import com.shophelperjavasb.shophelperjavasb.purchases.services.PurchasesService;
import com.shophelperjavasb.shophelperjavasb.shippers.model.Shipper;
import com.shophelperjavasb.shophelperjavasb.shippers.repositories.ShippersRepository;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PurchasesServiceImpl implements PurchasesService {
    private final PurchasesRepository purchasesRepository;
    private final ProductsRepository productsRepository;
    private final ShippersRepository shippersRepository;

    @Override
    public PurchaseResponseDto createPurchase(
        AuthenticatedUser currentUser,
        NewPurchaseDto newPurchaseDto
    ) {
        User user = currentUser.getUser();

        Product product = productsRepository.findById(newPurchaseDto.getProductId())
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + newPurchaseDto.getProductId() + "> not found"));

        Shipper shipper = shippersRepository.findById(newPurchaseDto.getShipperId())
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + newPurchaseDto.getShipperId() + "> not found"));

        Purchase newPurchase = Purchase.builder()
            .user(user)
            .product(product)
            .quantity(newPurchaseDto.getQuantity())
            .shipper(shipper)
            .status(Purchase.Status.CREATED)
            .createdDate(LocalDateTime.now())
            .build();

        purchasesRepository.save(newPurchase);

        return PurchaseResponseDto.from(newPurchase);
    }

    @Override
    public PurchaseResponseDto getById(Long purchaseId) {
        Purchase purchase = purchasesRepository.findById(purchaseId)
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + purchaseId + "> not found"));

        return PurchaseResponseDto.from(purchase);
    }

    @Override
    public PurchaseDto updateById(Long purchaseId, NewPurchaseDto newPurchaseDto) {
        Purchase originPurchase = purchasesRepository.findById(purchaseId)
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + purchaseId + "> not found"));

        originPurchase.setQuantity(newPurchaseDto.getQuantity());

        purchasesRepository.save(originPurchase);

        return PurchaseDto.from(originPurchase);
    }

    @Override
    public PurchaseDto updateStatus(Long purchaseId, String newStatus) {
        Purchase originPurchase = purchasesRepository.findById(purchaseId)
            .orElseThrow(() -> new NotFoundException("Purchase with id <" + purchaseId + "> not found"));

        if (newStatus.equals("CREATED") ||
            newStatus.equals("PERFORMED") ||
            newStatus.equals("DONE")
        ) {
            originPurchase.setStatus(Purchase.Status.valueOf(newStatus));

            purchasesRepository.save(originPurchase);

            return PurchaseDto.from(originPurchase);
        }

        return null;
    }
}

//    @Override
//    public PurchaseDto updatePurchase(int purchaseId, NewPurchaseDto newPurchaseDto) {
//        Purchase purchase = purchasesRepository.findById(purchaseId)
//            .orElseThrow(() -> new NotFoundException("Purchase with id <" + purchaseId + "> not found"));
//
//        purchase.setUser(newPurchaseDto.getUser());
//        purchase.setProduct(newPurchaseDto.getProduct());
//        purchase.setQuantity(newPurchaseDto.getQuantity());
//        purchase.setShipper(newPurchaseDto.getShipper());
//        purchase.setStatus(valueOf(newPurchaseDto.getStatus()));
//
//        purchasesRepository.save(purchase);
//
//        return PurchaseDto.from(purchase);
//    }

//    @Override
//    public PurchaseDto updateStatusPurchase(int purchaseId, String newStatus) {
//        Purchase purchase = purchasesRepository.findById(purchaseId)
//            .orElseThrow(() -> new NotFoundException("Purchase with id <" + purchaseId + "> not found"));
//
//        if (newStatus.equals("CREATED") ||
//            newStatus.equals("PERFORMED") ||
//            newStatus.equals("DONE")) {
//            purchase.setStatus(valueOf(newStatus));
//        }
//
//        return PurchaseDto.from(purchase);
//    }
