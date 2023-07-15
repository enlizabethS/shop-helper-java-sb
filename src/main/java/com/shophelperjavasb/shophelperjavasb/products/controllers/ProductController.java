package com.shophelperjavasb.shophelperjavasb.products.controllers;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.controllers.api.ProductApi;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDto;
import com.shophelperjavasb.shophelperjavasb.products.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        MultipartFile file1,
        MultipartFile file2,
        MultipartFile file3
    ) throws IOException {
        return ResponseEntity.status(201)
            .body(productService.createProduct(currentUser, productName, quantity, price, file1, file2, file3));
    }

//    @Override
//    public List<ProductDto> getAllProducts() {
//        return productService.getAllProducts();
//    }

//    @Override
//    public ProductDto getProductInfo(Long id) {
//        return productService.getProductInfo(id);
//    }

//    @Override
//    public void deleteProduct(Long id) {
//        productService.deleteProduct(id);
//    }
}
