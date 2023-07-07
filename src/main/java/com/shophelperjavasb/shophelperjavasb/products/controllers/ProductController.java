package com.shophelperjavasb.shophelperjavasb.products.controllers;

import com.shophelperjavasb.shophelperjavasb.products.controllers.api.ProductApi;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.services.impl.ProductsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController implements ProductApi {
    private final ProductsServiceImpl productService;


    @Override
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public ProductDTO getProductInfo(Long id) {
        return productService.getProductInfo(id);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDto) {
        return productService.createProduct(productDto);
    }

    @Override
    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }

    @Override
    public List<ProductDTO> getUserProducts(Long userId) {
        return productService.getUserProducts(userId);
    }
}
