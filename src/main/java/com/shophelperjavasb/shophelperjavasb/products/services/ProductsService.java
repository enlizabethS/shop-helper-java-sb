package com.shophelperjavasb.shophelperjavasb.products.services;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductsService {
    ProductDto createProduct(
        AuthenticatedUser currentUser,
        String productName,
        String quantity,
        String price,
        MultipartFile file1,
        MultipartFile file2,
        MultipartFile file3
    ) throws IOException;

//    List<ProductDTO> getAllProducts();

//    ProductDTO getProductInfo(Long id);

//    void deleteProduct(Long id);

//    List<ProductDTO> getUserProducts(Long userId);
}
