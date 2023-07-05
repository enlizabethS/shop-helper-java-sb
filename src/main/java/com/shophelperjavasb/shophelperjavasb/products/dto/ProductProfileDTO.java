package com.shophelperjavasb.shophelperjavasb.products.dto;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductProfileDTO {
    private Long id;
    private String name;
    private int quantity;
    private double price;
    private Long previewImageId;
    private LocalDateTime createdDate;

    public static ProductProfileDTO from(Product product) {
        return ProductProfileDTO.builder()
            .id(product.getId())
            .name(product.getName())
            .quantity(product.getQuantity())
            .price(product.getPrice())
            .previewImageId(product.getPreviewImageId())
            .createdDate(product.getCreatedDate())
            .build();
    }

    public static List<ProductProfileDTO> from(List<Product> products) {
        return products.stream()
            .map(ProductProfileDTO::from)
            .collect(Collectors.toList());
    }
}
