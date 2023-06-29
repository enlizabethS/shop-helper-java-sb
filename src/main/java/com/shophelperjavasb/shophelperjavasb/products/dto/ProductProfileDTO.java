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
    private int id;
    private String productName;
    private int quantity;
    private int price;
    private Long previewImageId;

    public static ProductProfileDTO from(Product product) {
        return ProductProfileDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .previewImageId(product.getPreviewImageId())
                .build();
    }
}
