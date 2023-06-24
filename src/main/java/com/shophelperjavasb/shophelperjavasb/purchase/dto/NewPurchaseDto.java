package com.shophelperjavasb.shophelperjavasb.purchase.dto;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.shippers.model.Shipper;
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
public class NewPurchaseDto {
    @Schema(description = "the user who makes the purchase")
    private User user;
    @Schema(description = "the product the user is buying")
    private Product product;
    @Schema(description = "number of products")
    private int quantity;
    @Schema(description = "purchase creation date")
    private LocalDateTime createdDate;
    @Schema(description = "id of the provider")
    private Shipper shipper;
    @Schema(description = "status of the purchase")
    private String status;
}
