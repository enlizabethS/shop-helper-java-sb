package com.shophelperjavasb.shophelperjavasb.purchases.dto;

import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseDto {
    @Schema(description = "id of the purchase")
    private Long id;
    @Schema(description = "number of products")
    private int quantity;
    @Schema(description = "purchase creation date")
    private LocalDateTime createdDate;
    @Schema(description = "status of the purchase")
    private String status;

    public static PurchaseDto from(Purchase purchase) {
        return PurchaseDto.builder()
            .id(purchase.getId())
            .quantity(purchase.getQuantity())
            .status(purchase.getStatus().name())
            .createdDate(purchase.getCreatedDate())
            .build();
    }
}
