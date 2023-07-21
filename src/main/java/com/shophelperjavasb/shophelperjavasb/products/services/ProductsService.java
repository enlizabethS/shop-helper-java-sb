package com.shophelperjavasb.shophelperjavasb.products.services;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.FilterTitleDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductPreviewDto;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ProductsService {
    ProductDto createProduct(
        AuthenticatedUser currentUser,
        String productName,
        String quantity,
        String price,
        String description,
        MultipartFile file1,
        MultipartFile file2,
        MultipartFile file3
    ) throws IOException;

    void delete(Long productId);

    ProductDto getById(Long productId);

    List<ProductPreviewDto> findByTitle(AuthenticatedUser currentUser, FilterTitleDto filter);
}
