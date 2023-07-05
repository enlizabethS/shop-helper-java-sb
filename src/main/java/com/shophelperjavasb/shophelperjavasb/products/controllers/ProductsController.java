package com.shophelperjavasb.shophelperjavasb.products.controllers;

import com.shophelperjavasb.shophelperjavasb.products.controllers.api.ProductsApi;
import com.shophelperjavasb.shophelperjavasb.products.dto.NewProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductNameDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductProfileDTO;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.products.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductsController implements ProductsApi {
    private final ProductsService productsService;

    @Override
    public ResponseEntity<List<ProductProfileDTO>> productsByName(ProductNameDto productName) {
        return ResponseEntity.ok(productsService.getProductsByName(productName));
    }

    @Override
    public ResponseEntity<ProductProfileDTO> productById(Long productId) {
        return ResponseEntity.ok(productsService.getById(productId));
    }

    @Override
    public ResponseEntity<ProductDTO> productInfo(Long productId) {
        return ResponseEntity.ok(productsService.getInfoById(productId));
    }

    @Override
    public ResponseEntity<ProductProfileDTO> createProduct(
        MultipartFile file1,
        MultipartFile file2,
        MultipartFile file3,
        Product product,
        Principal principal
    ) throws IOException {
        return ResponseEntity.status(201)
            .body(productsService.saveProduct(principal, product, file1, file2, file3));
    }

    @Override
    public void deleteProduct(Long productId) {
        productsService.deleteById(productId);
    }
}
