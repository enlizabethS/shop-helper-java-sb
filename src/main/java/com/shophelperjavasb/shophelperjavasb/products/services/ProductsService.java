package com.shophelperjavasb.shophelperjavasb.products.services;

import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;

import java.util.List;

public interface ProductsService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductInfo(Long id);

    ProductDTO createProduct(ProductDTO productDto);

    void deleteProduct(Long id);

    List<ProductDTO> getUserProducts(Long userId);


}
