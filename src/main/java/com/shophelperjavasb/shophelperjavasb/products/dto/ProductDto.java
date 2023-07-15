package com.shophelperjavasb.shophelperjavasb.products.dto;

import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    @Schema(defaultValue = "id of the product")
    private Long id;
    @Schema(defaultValue = "name of the product")
    private String title;
    @Schema(defaultValue = "quantity of the product")
    private int quantity;
    @Schema(defaultValue = "price of the product")
    private double price;
    @Schema(defaultValue = "list id's of the images")
    List<Long> imagesId = new ArrayList<>();
    @Schema(defaultValue = "id preview image")
    private Long previewImageId;
    @Schema(defaultValue = "product creation date")
    private LocalDateTime createdDate;

    public static ProductDto from(Product product) {
        return ProductDto.builder()
            .id(product.getId())
            .title(product.getTitle())
            .quantity(product.getQuantity())
            .price(product.getPrice())
            .imagesId(product.getImages().size() > 0
            ? product.getImages().stream().map(Image::getId).collect(Collectors.toList())
                : null)
            .previewImageId(product.getPreviewImageId())
            .createdDate(product.getCreatedDate())
            .build();
    }

    public static List<ProductDto> from(List<Product> products) {
        return products.stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }
}
