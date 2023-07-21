package com.shophelperjavasb.shophelperjavasb.products.controllers;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.controllers.api.ProductApi;
import com.shophelperjavasb.shophelperjavasb.products.dto.FilterTitleDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductPreviewDto;
import com.shophelperjavasb.shophelperjavasb.products.services.ProductsService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController implements ProductApi {
    private final ProductsService productService;

    @Override
    public ResponseEntity<ProductDto> createProduct(
        AuthenticatedUser currentUser,
        String productName,
        String quantity,
        String price,
        String description,
        MultipartFile file1,
        MultipartFile file2,
        MultipartFile file3
    ) throws IOException {
        return ResponseEntity.status(201)
            .body(productService.createProduct(currentUser, productName, quantity, price, description, file1, file2, file3));
    }

    @Override
    public ResponseEntity<ProductDto> getById(Long productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @Override
    public ResponseEntity<List<ProductPreviewDto>> findProductsByTitle(AuthenticatedUser currentUser, FilterTitleDto filter) {
        return ResponseEntity.ok(productService.findByTitle(currentUser, filter));
    }

    @Override
    public void delete(Long productId) {
        productService.delete(productId);
    }
}
