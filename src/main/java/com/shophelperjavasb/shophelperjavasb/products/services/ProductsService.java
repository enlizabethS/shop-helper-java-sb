package com.shophelperjavasb.shophelperjavasb.products.services;

import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductNameDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductProfileDTO;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface ProductsService {
    List<ProductProfileDTO> getProductsByName(ProductNameDto productName);

    ProductProfileDTO saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

    ProductProfileDTO getById(Long productId);

    ProductDTO getInfoById(Long productId);

    void deleteById(Long productId);
}
