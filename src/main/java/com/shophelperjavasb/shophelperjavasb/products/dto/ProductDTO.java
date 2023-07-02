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
    private Long id;
    private String name;
    private double price;

    public static ProductDTO from(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
    public static List<ProductDTO> from(List<Product> products){
        return products.stream()
                .map(ProductDTO::from)
                .collect(Collectors.toList());
    }
}
