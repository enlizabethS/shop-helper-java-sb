package com.shophelperjavasb.shophelperjavasb.products.services;

import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductPage;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductProfileDTO;

public interface ProductServices {
    ProductPage getAll();
    ProductDTO getProduct( int productId );
    ProductProfileDTO getProfile(int currentProductId);
}
