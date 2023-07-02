package com.shophelperjavasb.shophelperjavasb.purchases.dto;

import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class PurchaseResponseDto {
    @Schema(description = "id of the purchase")
    private Long id;
    @Schema(description = "id of the user")
    private Long userId;
    @Schema(description = "id of the product")
    private Long productId;
    @Schema(description = "number of products")
    private int quantity;
    @Schema(description = "purchase creation date")
    private LocalDateTime createdDate;
    @Schema(description = "id of the shipper")
    private Long shipperId;
    @Schema(description = "status of the purchase")
    private String status;

    public static PurchaseResponseDto from(Purchase purchase) {
        return PurchaseResponseDto.builder()
            .id(purchase.getId())
            .userId(purchase.getUser().getId())
            .productId(purchase.getProduct().getId())
            .shipperId(purchase.getShipper().getId())
            .quantity(purchase.getQuantity())
            .createdDate(purchase.getCreatedDate())
            .status(purchase.getStatus().name())
            .build();
    }

    public static List<PurchaseResponseDto> from(List<Purchase> purchases) {
        return purchases.stream()
            .map(PurchaseResponseDto::from)
            .collect(Collectors.toList());
    }
}
