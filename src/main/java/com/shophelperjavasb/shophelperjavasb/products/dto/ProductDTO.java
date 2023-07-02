package com.shophelperjavasb.shophelperjavasb.products.dto;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO {
    private int id;
    private String productName;
    private int price;

    public static ProductDTO from(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }
    public static List<ProductDTO> from(List<Product> products){
        return products.stream()
                .map(ProductDTO::from)
                .collect(Collectors.toList());
    }
}
