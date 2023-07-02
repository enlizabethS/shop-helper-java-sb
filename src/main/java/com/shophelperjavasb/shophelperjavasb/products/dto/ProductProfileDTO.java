package com.shophelperjavasb.shophelperjavasb.products.dto;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    public static ProductProfileDTO from(Product product) {
        return ProductProfileDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .previewImageId(product.getPreviewImageId())
                .build();
    }
}
