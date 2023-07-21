package com.shophelperjavasb.shophelperjavasb.products.dto;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class ProductPreviewDto {
    @Schema(defaultValue = "id of the product")
    private Long id;
    @Schema(defaultValue = "name of the product")
    private String title;
    @Schema(defaultValue = "quantity of the product")
    private int quantity;
    @Schema(defaultValue = "price of the product")
    private double price;
    @Schema(defaultValue = "id preview image")
    private Long previewImageId;

    public static ProductPreviewDto from(Product product) {
        return ProductPreviewDto.builder()
            .id(product.getId())
            .title(product.getTitle())
            .quantity(product.getQuantity())
            .price(product.getPrice())
            .previewImageId(product.getPreviewImageId())
            .build();
    }

    public static List<ProductPreviewDto> from(List<Product> products) {
        return products.stream()
            .map(ProductPreviewDto::from)
            .collect(Collectors.toList());
    }
}
