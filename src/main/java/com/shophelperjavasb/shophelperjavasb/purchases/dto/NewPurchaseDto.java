package com.shophelperjavasb.shophelperjavasb.purchases.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewPurchaseDto {
    @Schema(description = "the product the user is buying")
    private int productId;
    @Schema(description = "number of products")
    private int quantity;
    @Schema(description = "id of the provider")
    private int shipperId;
}
