package com.shophelperjavasb.shophelperjavasb.products.services;

import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductPage;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductProfileDTO;
import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface ProductsService {
    List<Product> listProducts (String productName);

    ProductPage getAll();

    ProductDTO getProduct(Long productId );

    ProductProfileDTO getProfile(Long currentProductId);

    void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

    User getUserByPrincipal (Principal principal);

    Image toImageEntity(MultipartFile file) throws IOException;

    void deleteProduct(User user, Long id);

    Product getProductById(Long id);
}
